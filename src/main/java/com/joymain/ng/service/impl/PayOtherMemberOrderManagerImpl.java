package com.joymain.ng.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.common.util.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joymain.ng.handle.GlobalVar;
import com.joymain.ng.model.FiBankbookBalance;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JmiRecommendRef;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JpoProductNumLimit;
import com.joymain.ng.model.JpoShoppingCartOrder;
import com.joymain.ng.model.JpoShoppingCartOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.PaymentMemberOrder;
import com.joymain.ng.model.PaymentOrderList;
import com.joymain.ng.service.FiBankbookBalanceManager;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.service.JmiRecommendRefManager;
import com.joymain.ng.service.JpoMemberOrderListManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JpoProductNumLimitManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.service.PayOtherMemberOrderManager;
import com.joymain.ng.util.ListUtil;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.MeteorUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.webapp.util.PromotionsUtils;

@Service("PayOtherMemberOrderService")
public class PayOtherMemberOrderManagerImpl implements PayOtherMemberOrderManager{

	protected final transient Log log = LogFactory.getLog(PayOtherMemberOrderManagerImpl.class);
	private JpoMemberOrderManager jpoMemberOrderManager;
	private JsysUserManager jsysUserManager;
	private JmiRecommendRefManager jmiRecommendRefManager;
	private JpoProductNumLimitManager jpoProductNumLimitManager;
	@Autowired
	private JpoMemberOrderListManager jpoMemberOrderListManager;
	private FiBankbookBalanceManager fiBankbookBalanceManager = null;
	
	private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
	
	@Autowired
	public void setJfiBankbookBalanceManager(
			JfiBankbookBalanceManager jfiBankbookBalanceManager) {
		this.jfiBankbookBalanceManager = jfiBankbookBalanceManager;
	}
	
	@Autowired
	public void setFiBankbookBalanceManager(FiBankbookBalanceManager fiBankbookBalanceManager) {
		this.fiBankbookBalanceManager = fiBankbookBalanceManager;
	}
	@Autowired
	public void setJpoProductNumLimitManager(
			JpoProductNumLimitManager jpoProductNumLimitManager) {
		this.jpoProductNumLimitManager = jpoProductNumLimitManager;
	}
	
	@Autowired
	public void setJmiRecommendRefManager(JmiRecommendRefManager jmiRecommendRefManager) {
		this.jmiRecommendRefManager = jmiRecommendRefManager;
	}

	@Autowired
	public void setJsysUserManager(JsysUserManager jsysUserManager) {
		this.jsysUserManager = jsysUserManager;
	}

	@Autowired
	public void setJpoMemberOrderManager(
			JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}
	
	
	public void payOtherOrderSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String userId = request.getParameter("userId");// 登录用户
		String token = request.getParameter("token");
		String userCode = request.getParameter("userCode");// 操作用户
		String memberOrderNo = request.getParameter("memberOrderNo");
		String ramount = request.getParameter("payJJ");//基金
		if(MeteorUtil.isBlank(ramount)){
			ramount = "0";
		}
		String password = request.getParameter("password");//支付密码
		PaymentMemberOrder pmo = new PaymentMemberOrder();
		JpoMemberOrder jpoMemberOrder = null;
			
//			JsysUser jsysUser = jsysUserManager.get(userId);
			JsysUser jsysUser = jsysUserManager.getUserByToken(userId, token);
			
			Object object = jsysUserManager.getAuthErrorCode(jsysUser, "List");
			if(null!=object){
				//鉴权失败、、直接返回错误信息
				pmo.setCode("e001");
				pmo.setMsg("token鉴权失败!");
				writeGridDataModelJson(response,pmo);
				return ;
			}
		
			jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(memberOrderNo);
			Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
			while (its1.hasNext()) {
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
				String status = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getStatus();
				if (!"1".equals(status)) {
					pmo.setCode("e005");
					pmo.setMsg("产品(" + jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo() + ")已售完!");
					writeGridDataModelJson(response,pmo);
					return ;
				}
			}


			if (!StringUtil.isEmpty(userCode) && !checkUnderMember(request , userId, userCode)) {
				pmo.setCode("e005");
				pmo.setMsg(LocaleUtil.getLocalText("opration.pay.fail"));
				writeGridDataModelJson(response,pmo);
				return ;
			}

			if (StringUtils.isEmpty(memberOrderNo) || "30".equals(jpoMemberOrder.getOrderType()) || jpoMemberOrder == null
					|| !jsysUser.getCompanyCode().equals(jpoMemberOrder.getCompanyCode()) || "2".equals(jpoMemberOrder.getStatus())
					|| !jpoMemberOrder.getSysUser().getUserCode().equals(userCode)) {
				pmo.setCode("e005");
				pmo.setMsg(LocaleUtil.getLocalText("opration.pay.fail"));
				writeGridDataModelJson(response,pmo);
				return ;
			}

			String passwordMd5 = StringUtil.encodePassword(password, "md5");
			if (!passwordMd5.equals(jsysUser.getPassword2())) {
				pmo.setCode("e005");
				pmo.setMsg(LocaleUtil.getLocalText(jsysUser.getDefCharacterCoding(), "fiMoney.fail.password"));
				writeGridDataModelJson(response,pmo);
				return ;
			}

			if (isOverQty(jpoMemberOrder)) {
				pmo.setCode("e005");
				pmo.setMsg(LocaleUtil.getLocalText("商品已售完或者库存不足！"));
				writeGridDataModelJson(response,pmo);
				return ;
			}
			
			boolean b = false;
			jpoMemberOrder.setUserSpCode(jsysUser.getUserCode());
			if(StringUtils.isEmpty(ramount)){
				log.info("payOtherOrderSubmit start:"+jpoMemberOrder.getMemberOrderNo()+
						" Thread id:"+Thread.currentThread().getId());
				jpoMemberOrderManager.checkJpoMemberOrder(jpoMemberOrder, jsysUser, "2");
				log.info("payOtherOrderSubmit stop:"+jpoMemberOrder.getMemberOrderNo()+
						" Thread id:"+Thread.currentThread().getId());
			}else{
				BigDecimal amount = new BigDecimal(ramount);
				if (amount.compareTo(new BigDecimal("0")) == 1) {
					jpoMemberOrder.setPayByJj("1");
					if (amount.compareTo(jpoMemberOrder.getAmount()) != -1) {
						jpoMemberOrder.setJjAmount(jpoMemberOrder.getAmount());
//						jpoMemberOrder.setAmount(new BigDecimal("0"));
					} else {
						jpoMemberOrder.setJjAmount(amount);
//						jpoMemberOrder.setAmount(jpoMemberOrder.getAmount().subtract(amount));
					}
					log.info("payOtherOrderSubmit start:"+jpoMemberOrder.getMemberOrderNo()+
							" Thread id:"+Thread.currentThread().getId());
					jpoMemberOrderManager.checkJpoMemberOrderByJJ(jpoMemberOrder, jsysUser, "2");
					log.info("payOtherOrderSubmit stop:"+jpoMemberOrder.getMemberOrderNo()+
							" Thread id:"+Thread.currentThread().getId());
				} else if (amount.compareTo(new BigDecimal("0")) == -1) {
					pmo.setCode("e005");
					pmo.setMsg(LocaleUtil.getLocalText("opration.pay.fail"));
					writeGridDataModelJson(response,pmo);
					return ;
				} else {
					log.info("payOtherOrderSubmit start:"+jpoMemberOrder.getMemberOrderNo()+
							" Thread id:"+Thread.currentThread().getId());
					jpoMemberOrderManager.checkJpoMemberOrder(jpoMemberOrder, jsysUser, "2");
					log.info("payOtherOrderSubmit stop:"+jpoMemberOrder.getMemberOrderNo()+
							" Thread id:"+Thread.currentThread().getId());
				}
			}
			// jpoMemberOrderManager.sendJmsCoin(jpoMemberOrder,
			// operatorSysUser);
			// 支付成功提示
//				this.saveMessage(request, getText("opration.pay.success"));
			b = true;
			/*
			 * 支付改造
			if (b) {
				try {
					jpoMemberOrder.setStatus("3");
					jpoMemberOrderManager.save(jpoMemberOrder);
					jpoMemberOrderManager.jpoMemberOrderPayedSendToMQ(jpoMemberOrder, jsysUser, "2");//本地测试发不出去。。。
//					log.info(jpoMemberOrder.getMemberOrderNo() + "=======审单MQ：JpoMemberOrderPayOtherFormController");
					pmo.setCode("s000");
					pmo.setMsg("成功");
					writeGridDataModelJson(response,pmo);
				} catch (Exception e) {
					log.error(jpoMemberOrder.getMemberOrderNo() + "发送mq消息失败：", e);
					pmo.setCode("e001");
					pmo.setMsg(jpoMemberOrder.getMemberOrderNo() + "发送mq消息失败");
					writeGridDataModelJson(response,pmo);
				}
			}
			*/
	}


	public void payOtherOrderForm(HttpServletRequest request, HttpServletResponse response)	throws Exception {
		String userId = request.getParameter("userId");
//		String token = request.getParameter("token");
		String userCode = request.getParameter("userCode");
		String memberOrderNo = request.getParameter("memberOrderNo");
		PaymentMemberOrder pmo = new PaymentMemberOrder();
		PaymentOrderList pol = null;
		
//		JsysUser jsysUser = jsysUserManager.getUserByToken(userId, token);
//		
//		Object object = jsysUserManager.getAuthErrorCode(jsysUser, "List");
//		if(null!=object){
//			//return (List)object;鉴权失败、、直接返回错误信息
//			pmo.setCode("e001");
//			pmo.setMsg("token鉴权失败!");
//			writeGridDataModelJson(response,pmo);
//			return ;
//		}
		
		JpoMemberOrder jpoMemberOrder = null;
		if (!StringUtils.isEmpty(memberOrderNo)) {
			memberOrderNo = memberOrderNo.trim();
			jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(memberOrderNo);
		}
		
		if(jpoMemberOrder == null){
			pmo.setCode("e005");
			pmo.setMsg("订单不存在!");
			writeGridDataModelJson(response,pmo);
			return ;
		}
		if (!StringUtil.isEmpty(userCode)) {
			userCode = userCode.trim();
			if(!checkUnderMember(request, userId,userCode)){
				pmo.setCode("e005");
				pmo.setMsg("会员不存在或不在推荐网络下");
				writeGridDataModelJson(response,pmo);
				return ;
			}
		}

		if (isOverQty(jpoMemberOrder)) {
			pmo.setCode("e005");
			pmo.setMsg("商品已售完或者库存不足！");
			writeGridDataModelJson(response,pmo);
			return ;
		}
		
		//===================================订单审核==================================start
		if(!checkMemberOrder(this.getClass(),jpoMemberOrder,pmo)){
			//如不通过
			writeGridDataModelJson(response,pmo);
			return ;
		}
		//===================================订单审核==================================end
		
		

		// Modify By WuCF 20150411 生态家套餐不能使用替他人支付
		if (null != jpoMemberOrder && !StringUtil.isEmpty(jpoMemberOrder.getProductFlag())) {
			pmo.setCode("e005");
			pmo.setMsg("生态家套餐订购不能使用此支付方式！");
			writeGridDataModelJson(response,pmo);
			return ;
		}

		// 若为：已核消订单，自己的订单，公益基金
		if (StringUtils.isEmpty(memberOrderNo) || jpoMemberOrder == null || "2".equals(jpoMemberOrder.getStatus())
				|| !jpoMemberOrder.getSysUser().getUserCode().equals(userCode) || "30".equals(jpoMemberOrder.getOrderType())) {
			pmo.setCode("e005");
			pmo.setMsg("会员订单不存在");
			writeGridDataModelJson(response,pmo);
			return ;
		}
		if (!StringUtils.isEmpty(memberOrderNo)) {
			// 查看是否有前台停售d商品
			Set<JpoMemberOrderList> jpoMemberOrderList = jpoMemberOrder.getJpoMemberOrderList();
			Iterator<JpoMemberOrderList> iterator = jpoMemberOrderList.iterator();
			boolean pay = true;
			while (iterator.hasNext()) {
				JpoMemberOrderList memberOrder = iterator.next();
				String status = memberOrder.getJpmProductSaleTeamType().getJpmProductSaleNew().getStatus();
				// 0.停售 1.销售 2前台停售
				if (!"1".equals(status)) {
					pay = false;
					String productNo = memberOrder.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo();
					pmo.setCode("e005");
					pmo.setMsg("编号为 " + productNo + " 的商品为前台停售状态，不能完成支付！");
					writeGridDataModelJson(response,pmo);
					return ;
				}
			}
			if (pay) {
				if(null!=jpoMemberOrder){
					pmo.setCode("s000");
					pmo.setMsg("成功");
					pmo.setUserCode(userCode);
					pmo.setMemberOrderNo(memberOrderNo);
					pmo.setOrderType(jpoMemberOrder.getOrderType());

					pmo.setDiscounAmount(jpoMemberOrder.getDiscountAmount());
					pmo.setJjAmount(jpoMemberOrder.getJjAmount());
					pmo.setAmount(jpoMemberOrder.getAmount());
					pmo.setPvAmt(jpoMemberOrder.getPvAmt());
					pmo.setOrderTime(MeteorUtil.doDateToConvert(jpoMemberOrder.getOrderTime()));
					pmo.setStatus(jpoMemberOrder.getStatus());
					pmo.setIsShipping(jpoMemberOrder.getIsShipping());
					pmo.setIsMobile(jpoMemberOrder.getIsMobile());
					pmo.setRemark(jpoMemberOrder.getRemark());
					List<PaymentOrderList> orderLists = new ArrayList<PaymentOrderList>();
					Set<JpoMemberOrderList> jol = jpoMemberOrder.getJpoMemberOrderList();
					
					LinkedHashMap<String, String> map = ListUtil.getListOptions(jpoMemberOrder.getCompanyCode(), "jpmproductsaleimage.url");
		    	    Collection<String> c = map.values();
//		    	    Collection<String> d = map.keySet();
		    	    String imgLinkurl = "";
		    	    for(String s:c){
		    	    	imgLinkurl = s;
		    	    }
					
					
					if(null!=jol && jol.size()>0){
						for (JpoMemberOrderList s : jol){
							pol = new PaymentOrderList();
							pol.setProductNo(s.getProNo());
							pol.setProductName(s.getProductName());
							pol.setPrice(s.getPrice());
							pol.setPv(s.getPv());
							pol.setQty(s.getQty());
							pol.setImageLink(imgLinkurl+s.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProductSaleImageList().get(1).getImageLink());
							orderLists.add(pol);
						}
					}
					pmo.setOrderLists(orderLists);
					FiBankbookBalance fiBankbookBalance = fiBankbookBalanceManager.getFiBankbookBalance(userId, "1");
					if(null == fiBankbookBalance){
						pmo.setFjBalanceAmount(new BigDecimal(0));
					}else{
						pmo.setFjBalanceAmount(fiBankbookBalance.getBalance());
					}
			        JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(userId);
			        if(null == jfiBankbookBalance){
						pmo.setBankbookAmount(new BigDecimal(0));
					}else{
						pmo.setBankbookAmount(jfiBankbookBalance.getBalance());
					}
				}
				//一切正常，填充展现数据并返回消息
				writeGridDataModelJson(response,pmo);
				return ;
				
			}
		}
	}
	
	private boolean checkUnderMember(HttpServletRequest request,String userId, String userCode) {

		JmiRecommendRef defRecommendRef = jmiRecommendRefManager.get(userId);

		JmiRecommendRef miRecommendRef = jmiRecommendRefManager.get(userCode);

		if (defRecommendRef == null || miRecommendRef == null) {
			return false;
		}
		// 判断推荐人是否在当前会员下
		String rdefIndex = defRecommendRef.getTreeIndex();
		String rIndex = miRecommendRef.getTreeIndex();
		if (rIndex.length() < rdefIndex.length() || !rdefIndex.equals(StringUtil.getLeft(rIndex, rdefIndex.length()))) {
			return false;
		}
		return true;
	}
	
	
	/**
	 * 支付促销，订单控制
	 */
	public static boolean checkMemberOrder(Class clazz, JpoMemberOrder jpoMemberOrder, PaymentMemberOrder pmo) {
		
		try {
			// =======================是否停售================
			String str = PromotionsUtils.verifyOrder(jpoMemberOrder);
			if (!MeteorUtil.isBlank(str)) {
				pmo.setCode("e005");
				pmo.setMsg(str);
				return false;
			}
			
			// =======================促销====================
			Map<String, String> resultMap = PromotionsUtils.checkCX201601(jpoMemberOrder);
			if (resultMap == null) {
				resultMap = PromotionsUtils.checkCX201603(jpoMemberOrder);
			}
			if (resultMap != null) {// 是促销的商品
				if (!"-1".equals(resultMap.get("index"))) {
					if ("false".equals(resultMap.get("code"))) {
						pmo.setCode("e005");
						pmo.setMsg(resultMap.get("msg"));
						return false;
					}
					if ("1".equals(resultMap.get("index")) && resultMap.get("listsCode").equals(org.apache.commons.lang.StringUtils.join(GlobalVar.jpoList20160102, ","))) { // 枸杞子
						if (!jpoMemberOrder.getTeamCode().equals(GlobalVar.teamCodeFB)) {// 冯波团队
							pmo.setCode("e005");
							pmo.setMsg("该单必需使用积分支付！");
							return false;
						}
					}
				}
			}
			resultMap = PromotionsUtils.checkCX20160118(jpoMemberOrder);
			if (resultMap != null) {// 是促销的商品
				if ("2".equals(resultMap.get("code"))) {
					pmo.setCode("e005");
					pmo.setMsg(resultMap.get("msg"));
					return false;
				}
			}
		} catch (Exception e) {
			pmo.setCode("e004");
			pmo.setMsg("数据异常,请联系管理员!");
			return false;
		}
		return true;
	}
	
	/**
	 * 模块单对象页面输出格式
	 * @param response
	 * @param m 结果集
	 * @throws IOException
	 */
	public void writeGridDataModelJson(HttpServletResponse response,PaymentMemberOrder pmo) throws IOException{
		ObjectMapper mapper = new ObjectMapper();
		String writeJson = mapper.writeValueAsString(pmo);
		 
		response.setCharacterEncoding("utf-8");
		response.getWriter().print(writeJson);
    }
	
	/**
	 * 验证产品购买数量是否超过限制 (2015 09 财月)
	 * @param jpoMemberOrder
	 * @return true or false
	 */
	private boolean isOverQty(JpoMemberOrder jpoMemberOrder){
		
		Integer proSum=0, proNoCount=-1,countQty=0;
		List<String> productNos = new ArrayList();
    	List<JpoProductNumLimit> jpoProductNumLimits = jpoProductNumLimitManager.getAll();
    	for (JpoProductNumLimit jpoProductNumLimit : jpoProductNumLimits) {
    		productNos.add(jpoProductNumLimit.getProductNo());
		}
    	
    	Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
    	
		while (its1.hasNext()) {
			
    		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
    		
    		String proNo =jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo();
    		
    		if(productNos.contains(proNo)){
    			
    			JpoProductNumLimit plimit =  jpoProductNumLimitManager.getNum(proNo);
    			proNoCount = plimit.getNum();;
    			
    			String statetime = null;
    	    	String endtime = null;
    	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    			if(!"".equals(plimit.getStartime()) && plimit.getStartime() != null){
        			statetime = sdf.format(plimit.getStartime());
        		}
        		if(!"".equals(plimit.getEndtime()) && plimit.getEndtime() != null){
 
        			endtime = sdf.format(plimit.getEndtime());
        		}
    			proSum = jpoMemberOrderListManager.getProSumByProNo(proNo, statetime, endtime);
    			countQty = jpoMemberOrderList.getQty();
        		
    			/*
    			 * 购买数量大于剩余数量, 或者统计数量大于等于库存数量
    			 */
    			if((proNoCount-proSum) < countQty || proSum >= proNoCount){
        			return true;
        		}
    		}
    	}
		return false;
	} 
	
	
	/**
	 * 验证产品购买数量是否超过限制 (2015 09 财月做成功能)
	 * @param jpoMemberOrder
	 * @return true or false
	 */
	private Integer isBuyProNumLimt(JpoShoppingCartOrder cartOrder,String pro){
		
    	Iterator its1 = cartOrder.getJpoShoppingCartOrderList().iterator();// .getJpoMemberOrderList().iterator();
    	
    	Integer proSum=0, proNoCount=-1,countQty=0;
    	List<String> productNos = new ArrayList();
    	List<JpoProductNumLimit> jpoProductNumLimits = jpoProductNumLimitManager.getAll();
    	for (JpoProductNumLimit jpoProductNumLimit : jpoProductNumLimits) {
    		productNos.add(jpoProductNumLimit.getProductNo());
		}
    	
    	JpoProductNumLimit plimit =  jpoProductNumLimitManager.getNum(pro);
    	String statetime = null;
    	String endtime = null;
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	if(productNos.contains(pro)){
    		proNoCount = plimit.getNum();;
    		if(!"".equals(plimit.getStartime()) && plimit.getStartime() != null){
    			statetime = sdf.format(plimit.getStartime());
    		}
    		if(!"".equals(plimit.getEndtime()) && plimit.getEndtime() != null){
    			endtime = sdf.format(plimit.getEndtime());
    		}
    		
    	}else{
    		proNoCount = -1;
    	}
    	
    	log.info("product sum is:["+proNoCount+"]");	
    	
		while (its1.hasNext()) {
			
			JpoShoppingCartOrderList jpoCatrList = 
    				(JpoShoppingCartOrderList) its1.next();
    		
    		String proNo =jpoCatrList.getJpmProductSaleTeamType().
    				getJpmProductSaleNew().getProductNo();
    		
			if(pro.equalsIgnoreCase(proNo))
			{
				//已审数量
				proSum = jpoMemberOrderListManager.getProSumByProNo(proNo, statetime, endtime);
				countQty = jpoCatrList.getQty();
				
				log.info("proNoCount =["+proNoCount+"] " +
	    				"and proSum is=["+proSum+"] countQty="+countQty);
				/*
				 * 购买数量大于剩余数量, 或者统计数量大于等于库存数量
				 */
				if(proNoCount!= -1 && ( proSum+countQty) > proNoCount ){
					log.info("最多：" + (proNoCount-proSum) +"  实际：" + countQty);
	    			return proNoCount-proSum;
	    		}
			}
    	}
		return -1;
	}
	
	
}
