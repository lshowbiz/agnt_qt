package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.joymain.ng.util.chinapnr.ChinapnrMobileUtilImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JfiBankbookJournal;
import com.joymain.ng.model.JfiPayOrderCompany;
import com.joymain.ng.model.JfiPayRetMsg;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.service.JfiBankbookJournalManager;
import com.joymain.ng.service.JpoMemberOrderListManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JsysIdManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.ConvertUtil;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.bill99.Bill99;
import com.joymain.ng.util.bill99.Bill99Constants;
import com.joymain.ng.util.bill99.Bill99Util;
import com.joymain.ng.util.bill99.Bill99UtilImpl2;
import com.joymain.ng.webapp.util.PromotionsUtils;

/**
 * 手机电子存折支付服务器端接口控制器
 * @author hywen
 *
 */
@Controller
@RequestMapping("mobilePay")
@SuppressWarnings({"rawtypes","unused","unchecked"})
public class MobilePayController extends BaseFormController{
	
	private final Log log = LogFactory.getLog(MobilePayController.class);
	
	@Autowired
	private JfiBankbookJournalManager jfiBankbookJournalManager;
	
	@Autowired
	private JfiBankbookBalanceManager jfiBankbookBalanceManager;
	
	@Autowired
	private JsysUserManager jsysUserManager;
	
	@Autowired
	private JpoMemberOrderManager jpoMemberOrderManager;
	
	@Autowired
	private Bill99Util bill99Util;
	@Autowired
	private JpoMemberOrderListManager jpoMemberOrderListManager;
	@Autowired
	private JpoMemberOrderDao jpoMemberOrderDao;
	@Autowired
	private JsysIdManager sysIdManager;// 生成ID号
	/**
	 * 查询电子存折流水
	 * @param userId 用户编码
	 * @param dealStartTime 开始时间
	 * @param dealEndTime 结束时间
	 * @return 存折流水
	 */
    @RequestMapping(value="api/getBankbooks")
	@ResponseBody
	public List getJfiBankbookJournalListByUserCode(String userId, String token,String dealStartTime, String dealEndTime,int pageNum,int pageSize){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if(null!=object){
			return (List)object;
		}
//		if(!StringUtil.isEmpty(dealStartTime)&&!StringUtil.isEmpty(dealEndTime)&&dealStartTime.equals(dealEndTime)){
//			dealEndTime+=" 23:59:59";
//		}
		//查询存折
    	List<JfiBankbookJournal> jfiBankbookJournalList=jfiBankbookJournalManager.getJfiBankbookJournalListByUserCodePage(userId,dealStartTime,dealEndTime,pageNum, pageSize);
    	//reSetListValue(jfiBankbookJournalList);
    	return jfiBankbookJournalList;
	}
    //时间转换格式
    private void reSetListValue(List list){
		SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	for (int i = 0; i < list.size(); i++) {
			Map map=(Map) list.get(i);
			if(map.get("DEAL_DATE")!=null){
				map.put("DEAL_DATE_str", sdf.format(map.get("DEAL_DATE")));
			}
			if(map.get("CREATE_TIME")!=null){
				map.put("CREATE_TIME_str", sdf.format(map.get("CREATE_TIME")));
			}
		}
    }
    
    /**
     * 查询电子存折余额
     * @param userId 用户编码
     * @return 电子存折余额
     */
    @RequestMapping(value="api/getBalance")
	@ResponseBody
	public String getjfiBankbookBalanceManagerByUserCode(String userId,String token){
    	 JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	 Object object = jsysUserManager.getAuthErrorCode(user, "String");
 		 if(null!=object){
 		 	return (String)object;
 		 }
    	JfiBankbookBalance fiBankbookBalance=jfiBankbookBalanceManager.getJfiBankbookBalance(userId);
    	return fiBankbookBalance.getBalance().toString();
    }
    
    /**
     * 使用电子存折支付订单
     * @param userId 用户编号
     * @param orderId  订单id
     * @return 返回码：1代表支付成功, 0代表支付失败
     */
    @RequestMapping(value="api/payOrder")
	@ResponseBody
    public String payJpoMemberOrderByBankbook(String userId, String orderId,String token){
    	JsysUser user = jsysUserManager.getUserByToken(userId, token);
    	Object object = jsysUserManager.getAuthErrorCode(user, "String");
		if(null!=object){
			return (String)object;
		}
    	JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
    	
    	if(!checkNumByOrder(jpoMemberOrder))
        {
    	    return "error";
        }
    	
    	if(validateOrder(jpoMemberOrder,user)){
			log.info(jpoMemberOrder.getMemberOrderNo() + 
					LocaleUtil.getLocalText("user.validate"));
			return "error";
		} 
    	//订单为空，订单状态已审核，不是当前用户
    	if(jpoMemberOrder==null || !"1".equals(jpoMemberOrder.getStatus()) || !userId.equals(jpoMemberOrder.getSysUser().getUserCode())){
    		
    		return "error";
    	} 
    	
//    	//端午商品促销控制
//    	Integer proSum=0, proNoCount=0,countQty=0;
//		String proCount_str = ConfigUtil.getConfigValue("CN", "product.count");
//		log.info("product sum is:["+proCount_str+"]");
//		if(StringUtils.isNotBlank(proCount_str)){
//			proNoCount = Integer.parseInt(proCount_str);
//		}
//    	Iterator<JpoMemberOrderList> ite = jpoMemberOrder.getJpoMemberOrderList().iterator();
//    	while(ite.hasNext()){
//    		JpoMemberOrderList orderList = ite.next();
//    		String proNo = orderList.getJpmProductSaleTeamType().
//    				getJpmProductSaleNew().getProductNo();
//    		if(proNo.equalsIgnoreCase(Constants.PRONO) || 
//    				proNo.equalsIgnoreCase(Constants.PRONO1) || 
//    				proNo.equalsIgnoreCase(Constants.PRONO2)){
//    			
//    			Integer temQty = 0;
//    			if(proNo.equalsIgnoreCase(Constants.PRONO)){
//    				temQty =orderList.getQty();
//    			}else if(proNo.equalsIgnoreCase(Constants.PRONO1)){
//    				temQty = orderList.getQty() * 3;
//    			} else if(proNo.equalsIgnoreCase(Constants.PRONO2)){
//    				temQty= orderList.getQty() * 5;
//    			}
//    			countQty += temQty;
//    			
//    			proSum = jpoMemberOrderListManager.getSumQtyByProNo();
//    			
//    			if((proNoCount-proSum)<countQty){
//    				return "error";
//    			}
//    		}
//    		
//    		log.info("proNoCount =["+proNoCount+"] " +
//    				"and proSum is=["+proSum+"] countQty="+countQty);
//    		
//    		if(proSum >= proNoCount){
//    			return "error";
//    		}
//    	}
//    	//库存不足
        if (isOverQty(jpoMemberOrder)) {
        	return "error";
		}
    	//验证是否停售
        String val = PromotionsUtils.verifyOrder(jpoMemberOrder);
		if(StringUtils.isNotEmpty(val)){
			return "error";
		}
    	//验证产品购买数量是否超过限制
    	if(isBuyPro(jpoMemberOrder)){
    		return "error";
    	}
    	
    	JsysUser operatorSysUser = jsysUserManager.get(userId);
    	log.info("bankbook insert start1:"+orderId);
    	//支付审单
    	String result = this.checkFlagOne(orderId, operatorSysUser);
    	log.info("bankbook insert stop1:"+orderId);
    	boolean b = false;
    	if("1".equals(result)){
    		b = true;
    		log.info("updateJpoMemberOrderPaymentType start1:"+orderId);
    		//Modify By WUCF 20160805 新增订单支付来源标示字段：PAYMENT_TYPE修改
			jpoMemberOrderManager.updateJpoMemberOrderPaymentType(String.valueOf(jpoMemberOrder.getMoId()), "1");
			log.info("updateJpoMemberOrderPaymentType stop1:"+orderId);
//    		log.info("call function start, orderNo is:"+jpoMemberOrder.getMemberOrderNo());
//    		jpoMemberOrderDao.callSTJFunction(jpoMemberOrder.getMemberOrderNo(), 1);
//    		log.info("call function END. ");
    		/* 支付改造
    		if(b){
    			try{
    				//调用MQ消息队列
	    			jpoMemberOrder.setStatus("3");
	    			jpoMemberOrderManager.save(jpoMemberOrder);
	    			jpoMemberOrderManager.jpoMemberOrderPayedSendToMQ(jpoMemberOrder, operatorSysUser, "2");
	    			log.info("=======审单MQ：MobilePayController orderNo="+jpoMemberOrder.getMemberOrderNo());

    			}catch (Exception e) {
            		log.error(jpoMemberOrder.getMemberOrderNo()+" 发送mq消息失败：", e);
    			}
        	}
    		*/
    		return "1";//支付成功
    	}else{
    		return "0";//支付失败
    	}
    	
    }
    
    /**
     * 快钱支付加密
     * @param userId 用户userCode
     * @param orderId 订单ID
     * @param flag 标识:0：充值，1支付
     * @param token
     * @param jsonAttr 自定义块钱支付属性
     * @return 加密的对象
     */
    @RequestMapping(value="api/getBill")
	@ResponseBody
    public Object getBill99(String userId, String orderId,String bankId, String orderAmount, String payerContact, int flag,String jsonAttr, String token){
    	Map<String, String> map = new HashMap<String, String>();
    	Bill99 jfi99bill = new Bill99();
		try {
			JsysUser user = jsysUserManager.getUserByToken(userId, token);
			Object object = jsysUserManager.getAuthErrorCode(user, "Map");
			if (null != object) {
				return (Map) object;
			}
			if(flag==1){
				JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(Long.parseLong(orderId));
				// 库存不足
				if (isOverQty(jpoMemberOrder)) {
					//map = ConvertUtil.ConvertObjToMap(jfi99bill);
					map.put("code", "error");
					map.put("msg", "库存不足");
					return map;
				}
				String val = PromotionsUtils.verifyOrder(jpoMemberOrder);
				if (StringUtils.isNotEmpty(val)) {
					map.put("code", "error");
					map.put("msg", val);
					return map;
				}
				
				//==========================================Modify By WuCF 20160607 会员死点判断
//				JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(Long.parseLong(orderId));
				int freeStatus = jpoMemberOrder.getSysUser().getJmiMember().getFreezeStatus();
				String order_Type = jpoMemberOrder.getOrderType();
				if ((!order_Type.equals(Constants.AUTO_PURCHASE)) && freeStatus == Constants.FREEZE_STATUS_ONE) {
					map.put("code", "error");
					map.put("msg", "冻结会员只允许支付续约单!");
					return map;
				}
				
				if (!"0".equals(jpoMemberOrder.getLocked())) {
					map.put("code", "error");
					map.put("msg", "订单已锁订!");
					return map;
				}
				
				if ("2".equals(jpoMemberOrder.getStatus())) {
					map.put("code", "error");
					map.put("msg", "订单已审核");
					return map;
				}
				
				if(jpoMemberOrder.getOrderType().equals("1") && 
						jpoMemberOrder.getSysUser().getJmiMember().getNotFirst()==1){
					map.put("code", "error");
					map.put("msg", "已存在首购单");
					return map;
				}
				if(jpoMemberOrder.getSysUser().getJmiMember().getFreezeStatus() == 1 && !jpoMemberOrder.getOrderType().equals(Constants.AUTO_PURCHASE)){
					map.put("code", "error");
					map.put("msg", "会员已冻结!");
					return map;
				}
				
				if (!user.getCompanyCode().equals(jpoMemberOrder.getCompanyCode()) && !"1".equals(jpoMemberOrder.getCompanyPay())) {
					map.put("code", "error");
					map.put("msg", "扣款人与订单不同国别!");
					return map;
				}
			}else if(flag == 0 ||flag == 2 ){
				orderId = sysIdManager.buildIdStr("advicecode_cn");
			}
			
			/*
			map.put("payerContact", payerContact);// 手续费
			map.put("bankId", bankId);// 银行ID
			map.put("key", "7Y43ME4H3Y5D3RG9");*/
		
			Bill99UtilImpl2 impl = new Bill99UtilImpl2();
			JfiPayOrderCompany company = new JfiPayOrderCompany();
			//company.setBusiness(Bill99Constants.account.get("1001776337001"));
			company.setPayAmount(new BigDecimal(orderAmount));
			company.setUserCode(user.getUserCode());// 会员编号
			company.setOrderAmount(new BigDecimal(orderAmount));// 金额
			company.setPayType(flag+"");
			company.setOrderNo(orderId);
			company.setDataType("2");//表示为手机端
			company.setMerPriv(jsonAttr);//块钱属性字段
			JfiPayRetMsg ret = impl.PayCompanyHandle(company);
			Map<String, String> retMp = ret.getDataMap();
			map.clear();
			if(retMp==null || StringUtils.isEmpty(retMp.get("signMsg"))){
				map.put("retMsg", "");
				map.put("code", "error");
				map.put("msg", "数据生成错误");
			}else{
				map.put("retMsg", retMp.get("signMsgVal"));
				map.put("code", "true");
				map.put("msg", "数据获取成功!");
			}
			System.out.println("======***1:"+map.get("retMsg"));
			System.out.println("======***1:"+map.get("code"));
			System.out.println("======***1:"+map.get("msg"));
			log.info("======***1:"+map.get("retMsg"));
			log.info("======***1:"+map.get("code"));
			log.info("======***1:"+map.get("msg"));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("retMsg", "");
			map.put("code", "false");
			map.put("msg", "数据异常"+e.getMessage());
			return map;
		}
    }


	/**
	 * 汇付支付加密
	 * @param userId 用户userCode
	 * @param orderId 订单ID
	 * @param flag 标识:0：充值，1支付
	 * @param token
	 * @param jsonAttr 自定义块钱支付属性
	 * @return 加密的对象
	 */
	@RequestMapping(value="api/getHf",produces = "application/json; charset=utf-8")
	@ResponseBody
	public Object getHfPay(String userId, String orderId,String bankId, String orderAmount,String platformPayType, String payerContact, int flag,String jsonAttr, String token){
		Map<String, String> map = new HashMap<String, String>();
		String goodsDesc="";
		try {
			JsysUser user = jsysUserManager.getUserByToken(userId, token);
			Object object = jsysUserManager.getAuthErrorCode(user, "Map");
			if (null != object) {
				//return (Map) object;
				map.put("resp_code", "218003");
				map.put("resp_desc", "登录失败");
				return map;
			}
			if(flag==1){
				JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(Long.parseLong(orderId));
				if(jpoMemberOrder!=null){
					goodsDesc="订单编号:"+jpoMemberOrder.getMemberOrderNo();
				}

				// 库存不足
				if (isOverQty(jpoMemberOrder)) {
					//map = ConvertUtil.ConvertObjToMap(jfi99bill);
					map.put("resp_code", "218003");
					map.put("resp_desc", "库存不足");
					return map;
				}
				String val = PromotionsUtils.verifyOrder(jpoMemberOrder);
				if (StringUtils.isNotEmpty(val)) {
					map.put("resp_code", "218003");
					map.put("resp_desc", val);
					return map;
				}

				//==========================================Modify By WuCF 20160607 会员死点判断
//				JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(Long.parseLong(orderId));
				int freeStatus = jpoMemberOrder.getSysUser().getJmiMember().getFreezeStatus();
				String order_Type = jpoMemberOrder.getOrderType();
				if ((!order_Type.equals(Constants.AUTO_PURCHASE)) && freeStatus == Constants.FREEZE_STATUS_ONE) {
					map.put("resp_code", "218003");
					map.put("resp_desc", "冻结会员只允许支付续约单!");
					return map;
				}

				if (!"0".equals(jpoMemberOrder.getLocked())) {
					map.put("resp_code", "218003");
					map.put("resp_desc", "订单已锁订!");
					return map;
				}

				if ("2".equals(jpoMemberOrder.getStatus())) {
					map.put("resp_code", "218003");
					map.put("resp_desc", "订单已审核");
					return map;
				}

				if(jpoMemberOrder.getOrderType().equals("1") &&
						jpoMemberOrder.getSysUser().getJmiMember().getNotFirst()==1){
					map.put("resp_code", "218003");
					map.put("resp_desc", "已存在首购单");
					return map;
				}
				if(jpoMemberOrder.getSysUser().getJmiMember().getFreezeStatus() == 1 && !jpoMemberOrder.getOrderType().equals(Constants.AUTO_PURCHASE)){
					map.put("resp_code", "218003");
					map.put("resp_desc", "会员已冻结!");
					return map;
				}

				if (!user.getCompanyCode().equals(jpoMemberOrder.getCompanyCode()) && !"1".equals(jpoMemberOrder.getCompanyPay())) {
					map.put("resp_code", "218003");
					map.put("resp_desc", "扣款人与订单不同国别!");
					return map;
				}
			}else if(flag == 0 || flag == 2){
				orderId = sysIdManager.buildIdStr("advicecode_cn");
				goodsDesc="充值编号:"+orderId;
			}
			if(StringUtils.isEmpty(platformPayType)){
				map.put("resp_code", "218003");
				map.put("resp_desc", "参数有误!");
				return map;
			}


			ChinapnrMobileUtilImpl impl = new ChinapnrMobileUtilImpl();
			JfiPayOrderCompany company = new JfiPayOrderCompany();
			company.setPayAmount(new BigDecimal(orderAmount));
			company.setUserCode(user.getUserCode());// 会员编号
			company.setOrderAmount(new BigDecimal(orderAmount));// 金额
			company.setPayType(flag+"");
			company.setOrderNo(orderId);
			company.setDataType("2");//表示为手机端
			company.setMerPriv(jsonAttr);//块钱属性字段
			company.setPlatformPayType(platformPayType);//hf属性字段 05 支付宝，10 微信
			company.setGoodsDesc(goodsDesc);//hf属性字段   支付宝 商品描述

			JfiPayRetMsg ret = impl.PayCompanyHandle(company);
			Map<String, String> retMp = ret.getDataMap();
			map.clear();
			if(retMp==null || StringUtils.isEmpty(retMp.get("signMsg"))){
				map.put("resp_code", "218003");
				map.put("resp_desc", "数据生成错误");
			}else{
				Map<String,String> mapResult = JSON.parseObject(retMp.get("signMsg").toString(), Map.class );
				return mapResult;
			}
			log.info("======***1:"+map.get("resp_code"));
			log.info("======***1:"+map.get("resp_desc"));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
			map.put("resp_code", "218003");
			map.put("resp_desc", "数据异常"+e.getMessage());
			return map;
		}
	}


	/**
	 * 审核订单
	 * @param orderId
	 * @param operatorSysUser
	 */
	private String checkFlagOne(String orderId, JsysUser operatorSysUser){
		
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));

		try{
			JpoMemberOrder order = jpoMemberOrderManager.newOrder(jpoMemberOrder);
			log.info("checkJpoMemberOrder start:"+order.getMemberOrderNo());
			jpoMemberOrderManager.checkJpoMemberOrder(order, operatorSysUser,"2");
			log.info("checkJpoMemberOrder stop:"+order.getMemberOrderNo());
		}catch(AppException app){

			return app.getMessage();			
		}
		return "1";
	}
	
	public boolean checkNumByOrder(JpoMemberOrder jpoMemberOrder)
    {
        Iterator it = jpoMemberOrder.getJpoMemberOrderList().iterator();
        //迭代出订单下商品
        while (it.hasNext())
        {
            
            JpoMemberOrderList orderList = (JpoMemberOrderList) it.next();
            String proNo = orderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo();
            
            //已购买数量
            Integer proNum = jpoMemberOrderListManager.getSumQtyByProNo(proNo);
            
            //新订单数量+已购买数量=总数量
            Integer num = proNum + orderList.getQty(); 
            return checkNum(proNo,num);
            
        }
        return true;
    }
    
    public boolean checkNum(String proNo,Integer num)
    {
        if(Constants.PROC.equals(proNo))
        {
            if(num > Constants.PROCnum)
            {
                return false;
            }
        }
        else if(Constants.PROC1.equals(proNo))
        {
            if(num > Constants.PROC1num)
            {
                return false;
            }
        }
        else if(Constants.PROC2.equals(proNo))
        {
            if(num > Constants.PROC2num)
            {
                return false;
            }
        }
        else if(Constants.PROC3.equals(proNo))
        {
            if(num > Constants.PROC3num)
            {
                return false;
            }
        }
        else if(Constants.PROC4.equals(proNo))
        {
            if(num > Constants.PROC4num)
            {
                return false;
            }
        }
        else if(Constants.PROC5.equals(proNo))
        {
            if(num > Constants.PROC5num)
            {
                return false;
            }
        }
        else if(Constants.PROC6.equals(proNo))
        {
            if(num > Constants.PROC6num)
            {
                return false;
            }
        }
        else if(Constants.PROC7.equals(proNo))
        {
            if(num > Constants.PROC7num)
            {
                return false;
            }
        }
        return true;
    }
	public JpoMemberOrderDao getJpoMemberOrderDao() {
		return jpoMemberOrderDao;
	}
	public void setJpoMemberOrderDao(JpoMemberOrderDao jpoMemberOrderDao) {
		this.jpoMemberOrderDao = jpoMemberOrderDao;
	}
    
}
