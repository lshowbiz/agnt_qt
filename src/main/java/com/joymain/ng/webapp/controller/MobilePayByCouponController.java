package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.model.JpmCouponInfo;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JpoUserCoupon;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiBankbookBalanceManager;
import com.joymain.ng.service.FiBankbookJournalManager;
import com.joymain.ng.service.JpmCouponInfoManager;
import com.joymain.ng.service.JpoMemberOrderListManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JpoUserCouponManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.webapp.util.PromotionsUtils;

/**
 * 手机代金券支付服务器端接口控制器
 * 
 * @author zengyz
 * 
 */
@Controller
@RequestMapping("mobileCpPay")
public class MobilePayByCouponController extends BaseFormController {

	private Log log = LogFactory.getLog(MobilePayByCouponController.class);

	@Autowired
	private JsysUserManager jsysUserManager;
	@Autowired
	private FiBankbookJournalManager fiBankbookJournalManager;

	@Autowired
	private FiBankbookBalanceManager fiBankbookBalanceManager;

	@Autowired
	private JpoMemberOrderManager jpoMemberOrderManager;
	@Autowired
	private JpoMemberOrderListManager jpoMemberOrderListManager;
	@Autowired
	private JpoMemberOrderDao jpoMemberOrderDao;

	@Autowired
	private JpoUserCouponManager jpoUserCouponManager;
	
	@Autowired
	private JpmCouponInfoManager jpmCouponInfoManager;

	@RequestMapping(value = "api/payOrderByCp")
	@ResponseBody
	public String payJpoMemberOrderByCoupon(HttpServletRequest request) {
		
		String userId = request.getParameter("userId");// 用户
		String token = request.getParameter("token");// 令牌

		String orderId  = request.getParameter("orderId");// 订单

		String userCpId = request.getParameter("cpId");// 用户代金券ID
		String paymentType = request.getParameter("paymentType");// 支付类型

		// 登录用户
		JsysUser user = jsysUserManager.getUserByToken(userId, token);
		// 手机用户登录出错
		Object object = jsysUserManager.getAuthErrorCode(user, "String");
		if (null != object) {
			return (String) object;
		}
		
		// 默认验证
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
		String checkMemberOrder = this.checkMemberOrder(jpoMemberOrder, user);
		if (checkMemberOrder.equals("error")) {
			return "error";
		}

		JpoMemberOrder order = jpoMemberOrderManager.newOrder(jpoMemberOrder);// 订单
		BigDecimal cpValue = new BigDecimal(0);
		BigDecimal amount=order.getAmount();
		JpoUserCoupon userCoupon = null;
		if (StringUtils.isNotEmpty(userCpId)) {
			userCoupon = jpoUserCouponManager.getJpoUserCouponById(userCpId);//会员代金券
			if (userCoupon.getStatus().equals("0")) {
				return "error";//代金券不可使用
			}
			if (StringUtils.isNotEmpty(userCoupon.getAbleStatus())&&userCoupon.getAbleStatus().equals("N")) {
				return "error";//代金券不可使用
			}
			
		}else{//如果没有会员代金券，则直接存折支付
			//jpoMemberOrderManager.checkJpoMemberOrder(order, user, "2");
			//return "1";//支付成功
			log.info("移动端代金券支付接口:必须使用代金券或代金券加其他支付方式才可支付");
			return "0";
		}
		
		if(null != userCoupon){
			JpmCouponInfo jpmCouponInfo = jpmCouponInfoManager.get(userCoupon.getCpId());//代金券
			cpValue = new BigDecimal(jpmCouponInfo.getCpValue());// 代金券金额
			order.setPayByCp("1");// 是否使用代金券
			order.setUserCpId(userCoupon.getId());//会员代金券ID
		}
		

		if (amount.compareTo(new BigDecimal("0")) == 1) {
			try {
				if (amount.compareTo(cpValue) == 0 || amount.compareTo(cpValue) == -1) {// 如果总金额小于等于代金券金额这代金券全额支付
					order.setCpValue(amount);// 代金券使用金额
				} else if (amount.compareTo(cpValue) == 1) {// 如果总金额大于代金券金额则多余的金额用存折支付
					order.setCpValue(cpValue);// 代金券使用金额
					
					if("2".equals(paymentType)){//发展基金
	            		order.setPayByJj("1");
	            		order.setJjAmount(jpoMemberOrder.getAmount().subtract(cpValue));//发展基金支付部分
	            	}else if("3".equals(paymentType)){//抵用券
	            		order.setPayByProduct("1");
	            		order.setProductPointAmount(jpoMemberOrder.getAmount().subtract(cpValue)); //抵用券支付部分
	            	}else{//电子存折
	            		
	            	}
				}
				
//				if (cpValue.compareTo(new BigDecimal("0"))==1) {
					jpoMemberOrderManager.checkJpoMemberOrderByCpValue(order, user, "2");
					return "1";
//				}else{
//					jpoMemberOrderManager.checkJpoMemberOrder(order, user, "2");
//					return "1";
//				}
				
			} catch (Exception e) {
				return "0";//支付失败
			}
		}
		return "0";
	}

	private String checkMemberOrder(JpoMemberOrder jpoMemberOrder, JsysUser user) {
		if (!checkNumByOrder(jpoMemberOrder)) {
			return "error";
		}
		if (validateOrder(jpoMemberOrder, user)) {
			log.info(jpoMemberOrder.getMemberOrderNo() + LocaleUtil.getLocalText("user.validate"));
			return "error";
		}
		// 订单为空，订单状态已审核，不是当前用户
		if (jpoMemberOrder == null || !"1".equals(jpoMemberOrder.getStatus())
				|| !user.getUserCode().equals(jpoMemberOrder.getSysUser().getUserCode())) {
			return "error";
		}
		// 判断是否包含三代无忧产品
		if (this.checkSDWYProductNo(jpoMemberOrder)) {
			return "error";
		}
		// 库存不足
		if (isOverQty(jpoMemberOrder)) {
			return "error";
		}
		// 验证是否停售
		String val = PromotionsUtils.verifyOrder(jpoMemberOrder);
		if (StringUtils.isNotEmpty(val)) {
			return "error";
		}
		// 验证产品购买数量是否超过限制
		if (isBuyPro(jpoMemberOrder)) {
			return "error";
		}
		return "success";
	}

	// 判断是否为三代无忧奖励计划产品、台湾旅游积分产品：产品编号N07010100101CN0,N07020100101CN0
	@SuppressWarnings("rawtypes")
	private boolean checkSDWYProductNo(JpoMemberOrder jpoMemberOrder) {

		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
		String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct()
				.getProductNo();

		if ("N07010100101CN0".equals(productNo) || "N07020100101CN0".equals(productNo)) {

			return true;
		}
		return false;
	}

	//
	@SuppressWarnings("rawtypes")
	private boolean checkNumByOrder(JpoMemberOrder jpoMemberOrder) {
		Iterator it = jpoMemberOrder.getJpoMemberOrderList().iterator();
		// 迭代出订单下商品
		while (it.hasNext()) {
			JpoMemberOrderList orderList = (JpoMemberOrderList) it.next();
			String proNo = orderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo();
			// 已购买数量
			Integer proNum = jpoMemberOrderListManager.getSumQtyByProNo(proNo);
			// 新订单数量+已购买数量=总数量
			Integer num = proNum + orderList.getQty();
			return checkNum(proNo, num);
		}
		return true;
	}

	//
	public boolean checkNum(String proNo, Integer num) {
		if (Constants.PROC.equals(proNo)) {
			if (num > Constants.PROCnum) {
				return false;
			}
		} else if (Constants.PROC1.equals(proNo)) {
			if (num > Constants.PROC1num) {
				return false;
			}
		} else if (Constants.PROC2.equals(proNo)) {
			if (num > Constants.PROC2num) {
				return false;
			}
		} else if (Constants.PROC3.equals(proNo)) {
			if (num > Constants.PROC3num) {
				return false;
			}
		} else if (Constants.PROC4.equals(proNo)) {
			if (num > Constants.PROC4num) {
				return false;
			}
		} else if (Constants.PROC5.equals(proNo)) {
			if (num > Constants.PROC5num) {
				return false;
			}
		} else if (Constants.PROC6.equals(proNo)) {
			if (num > Constants.PROC6num) {
				return false;
			}
		} else if (Constants.PROC7.equals(proNo)) {
			if (num > Constants.PROC7num) {
				return false;
			}
		}
		return true;
	}

}
