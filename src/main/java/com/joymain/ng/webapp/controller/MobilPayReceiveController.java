package com.joymain.ng.webapp.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joymain.ng.model.Jfi99billLog;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.Jfi99billLogManager;
import com.joymain.ng.service.JfiBankbookJournalManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.mpos.CerEncode;

/**
 * 手机快钱支付收款处理控制器
 * 
 * @author syh
 * 
 */
@Controller
@RequestMapping("/mobilPayReceive*")
public class MobilPayReceiveController extends BaseFormController {

	private JpoMemberOrderManager jpoMemberOrderManager = null;
	private Jfi99billLogManager jfi99billLogManager = null;
	private JfiBankbookJournalManager jfiBankbookJournalManager = null;
	 private JsysUserManager sysUserManager = null;
	
	@Autowired
	public void setJpoMemberOrderManager(
			JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	@Autowired
	public void setJfi99billLogManager(Jfi99billLogManager jfi99billLogManager) {
		this.jfi99billLogManager = jfi99billLogManager;
	}

	@Autowired
	public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}
	
	@Autowired
	public void setSysUserManager(JsysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		/*
		 *  第1步：获取快钱发送的通知参数,设置到Jfi99billLog对象
		 */
		Jfi99billLog jfi99billLog =setJfi99billLog(request);
		
		/*
		 *  第2步：验签
		 */
		boolean flag = this.checkJfi99billLog(request);
		
		/**
		 * 第3步：查询历史数据，判断是否重复
		 */
		int rtnMsg = 0;//验签状态，0表示被篡改,1表示扣款失败,2自定义MD5签名被篡改(快钱签名被篡改),3支付数据重新发送,10表示成功校验
		
		//查询数据是否重发
		if(jfi99billLogManager.getSuccessJfi99billLogsByConditions(jfi99billLog.getDealId()).size()>0){
			
			rtnMsg = 3;//重发信息
		}else{
			rtnMsg = 10;//成功校验
		}
		
		boolean b = false;
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(jfi99billLog.getOrderId()));
    	
    	JsysUser operatorSysUser = sysUserManager.get(jfi99billLog.getUserCode());
		//验签通过
		if (flag) {
			
			/*
			 * 第4步：快钱数据入电子存折
			 */
			if(rtnMsg==10){
				
				JsysUser sysUser = sysUserManager.get(jfi99billLog.getUserCode());//查询会员对象
				
				Integer[] moneyType = new Integer[1];
				moneyType[0] = 29;
				String[] notes = new String[1];
				notes[0] = "快钱支付";
				
				BigDecimal[] moneyArray = new BigDecimal[1];
				moneyArray[0] = new BigDecimal(jfi99billLog.getPayAmount()).divide(new BigDecimal(100));

				jfiBankbookJournalManager.saveJfiBankbookDeduct_pay("CN", sysUser, moneyType, moneyArray, sysUser.getUserCode(), sysUser.getUserName(),jfi99billLog.getDealId(), notes, "2");
				
				//修改jfi99billLog对象，设置1代表进电子存折
	        	jfi99billLog.setInc("1");
	        	
	        	/*
	        	 * 第5步：后台自动采用电子存折支付审单
	        	 */
//	        	JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(jfi99billLog.getOrderId()));
	        	
//	        	JsysUser operatorSysUser = sysUserManager.get(jfi99billLog.getUserCode());
	        	
	        	//订单不为空，订单状态未审核
	        	if(jpoMemberOrder!=null && "1".equals(jpoMemberOrder.getStatus())){
	        		JpoMemberOrder order = jpoMemberOrderManager.newOrder(jpoMemberOrder);
	        		log.info("MobilPayReceiveController start:"+order.getMemberOrderNo());
	        		jpoMemberOrderManager.checkJpoMemberOrder(order, operatorSysUser,"2");
	        		log.info("MobilPayReceiveController stop:"+order.getMemberOrderNo());
	        		b = true;
	        		
	        	}else{
	        		
	        		return "error";
	        	}
			}			
		} else {//验证不通过

			rtnMsg = 0;//0表示被篡改
		}
		
		//签名字符串验证结果
		jfi99billLog.setVerifySignResult(String.valueOf(flag));
		
		//验签状态,10代表成功校验，3：重发信息
		jfi99billLog.setReturnMsg(String.valueOf(rtnMsg));
		
		/*
		 * 第6步：保存快钱返回数据
		 */
		this.jfi99billLogManager.save(jfi99billLog);

		/*
		 * 第7步：反馈结果信息给快钱读取
		 */
		if(flag && rtnMsg==10)
			request.setAttribute("returnMsg", "0");
		/*支付改造
		if(b){
			//2015-10-15 调用MQ消息队列
			jpoMemberOrder.setStatus("3");
			jpoMemberOrderManager.save(jpoMemberOrder);
			jpoMemberOrderManager.jpoMemberOrderPayedSendToMQ(jpoMemberOrder, operatorSysUser, "2");
			log.info("=======审单MQ：MobilPayReceiveController");
		}*/
		return "jfiPay99billReceive";
	}
	
	/**
	 * 验签
	 * @param request
	 * @return
	 */
	private boolean checkJfi99billLog(HttpServletRequest request){
		
		boolean flag=false;
		
		String signature = request.getParameter("signature");
		StringBuffer sb = new StringBuffer();
		sb.append(request.getParameter("processFlag"))
				.append(request.getParameter("txnType"))
				.append(request.getParameter("orgTxnType"));
		sb.append(request.getParameter("amt")).append(// 金额
				request.getParameter("externalTraceNo"))
				.append(request.getParameter("orgExternalTraceNo"))
				.append(request.getParameter("terminalOperId"));
		sb.append(request.getParameter("authCode"))
				.append(request.getParameter("RRN"))
				.append(request.getParameter("txnTime"))
				.append(request.getParameter("shortPAN"))
				.append(request.getParameter("responseCode"));
		sb.append(request.getParameter("cardType")).append(
				request.getParameter("issuerId"));
		CerEncode ce = new CerEncode();
		
		// 验签
		flag = ce.enCodeByCer(sb.toString(), signature);
		
		return flag;
	}
	
	/**
	 * 生成初始Jfi99billLog
	 */
	private Jfi99billLog setJfi99billLog(HttpServletRequest request){
		
		Jfi99billLog jfi99billLog = new Jfi99billLog();
		
        jfi99billLog.setInc("0");//1进电子存折 0没进电子存折
        jfi99billLog.setCompanyCode("CN");
        jfi99billLog.setUrl(request.getRequestURL().toString() + "?" + request.getQueryString());//快钱返回的RUL
        
        //获取订单编号、会员编号、flag
        String externalTraceNo[] = request.getParameter("externalTraceNo").split(",");
        String orderId = externalTraceNo[0];//订单编号
        String userCode = externalTraceNo[1];//会员编号
        String flag = externalTraceNo[2];//标识,1:订单支付,2：充值
        
        jfi99billLog.setUserCode(userCode);//会员编号
        jfi99billLog.setOrderId(orderId);//会员订单号
        
        jfi99billLog.setPayAmount(request.getParameter("amt"));//获取实际支付金额
        jfi99billLog.setFee("0");//快钱交易手续费
        
        jfi99billLog.setDealId(request.getParameter("authCode"));//获取快钱交易号，获取该交易在快钱的交易号,暂时读取授权号
        
        jfi99billLog.setBankId("issuerId");//获取银行代码 参见银行代码列表，暂时读取发卡机构
        jfi99billLog.setBankDealId(request.getParameter("RRN"));//获取银行交易参考号，暂时读取参考号
        jfi99billLog.setDealTime(request.getParameter("txnTime"));//获取在快钱交易时间
        jfi99billLog.setMerchantAcctId(request.getParameter("merchantId"));//获取人民币网关账户号,快钱商户号
        
        jfi99billLog.setErrCode(request.getParameter("responseCode"));//设置错误码，获取交易返回码
        jfi99billLog.setExt2(request.getParameter("externalTraceNo"));//设置扩展字段2,存入订单、会员编号、标识
        jfi99billLog.setPayType("2");//设置支付方式,代表手机支付
        jfi99billLog.setBillLanguage("1");//设置语言种类,1代表中文
        jfi99billLog.setSignType("1");//签名类型.固 1代表MD5签名 当前版本固定为1
        jfi99billLog.setSignMsg(request.getParameter("signature"));//获取加密签名串
        
        //获取处理结果,10代表 成功;11代表 失败
        if(("0").equals(request.getParameter("processFlag")))//获取处理结果  0代表 成功; 1代表 失败
        	jfi99billLog.setPayResult("10");
        if(("1").equals(request.getParameter("processFlag")))
        	jfi99billLog.setPayResult("11");
        
        jfi99billLog.setReferer("移动终端支付订单");//来源页
        
        return jfi99billLog;
	}
	
}
