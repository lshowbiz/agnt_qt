package com.joymain.ng.webapp.controller;

import com.joymain.ng.webapp.util.OrderCheckUtils;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.Constants;
import com.joymain.ng.handle.GlobalVar;
import com.joymain.ng.model.FiBcoinBalance;
import com.joymain.ng.model.FiBcoinPayconfigDetail;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiBcoinBalanceManager;
import com.joymain.ng.service.FiBcoinPayconfigDetailManager;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.service.JpoMemberOrderListManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.MeteorUtil;
import com.joymain.ng.webapp.util.PromotionsUtils;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JpoInviteListManager;
import com.joymain.ng.service.JpoInviteListManager;
/**
 * 订单支付请求核心控制器（包括电子存折支付、第三方支付、混合支付）
 * 
 * @author jfoy
 * 
 */
@Controller
@RequestMapping("/jfiPayOrder")
@SuppressWarnings({"unused","rawtypes"})
public class JfiPayOrderController extends BaseFormController {

	private final Log log = LogFactory.getLog(this.getClass());

	private JpoMemberOrderManager jpoMemberOrderManager = null;

	private JfiBankbookBalanceManager jfiBankbookBalanceManager = null;
	
	@Autowired
	private JmiMemberManager jmiMemberManager;
	@Autowired
	private JpoInviteListManager jpoInviteListManager;
	@Autowired
	private JpoMemberOrderListManager jpoMemberOrderListManager;

	@Autowired
	private FiBcoinBalanceManager fiBcoinBalanceManager;// 积分换购方法

	@Autowired
	private FiBcoinPayconfigDetailManager fiBcoinPayconfigDetailManager;

	@Autowired
	public void setJpoMemberOrderManager(JpoMemberOrderManager jpoMemberOrderManager) {
		this.jpoMemberOrderManager = jpoMemberOrderManager;
	}

	@Autowired
	public void setJfiBankbookBalanceManager(JfiBankbookBalanceManager jfiBankbookBalanceManager) {
		this.jfiBankbookBalanceManager = jfiBankbookBalanceManager;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		boolean isFund = true; // 是否有基金按钮
		boolean coinPay = false;// 是否有积分按钮
		
		boolean isProductPoint = false;	//是否有产品积分支付按钮,重销单可以用产品积分方式支付 add by hdg 2017-01-03
		// 当前用户
		JsysUser loginSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		request.setAttribute("jsysUser", loginSysUser);
		// 当前订单ID
		String orderId = request.getParameter("orderId");
		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
		
		
		JmiMember jmiMember=jmiMemberManager.get(loginSysUser.getUserCode());
		request.setAttribute("member_level", jmiMember.getMemberLevel());
		request.setAttribute("order_type", jpoMemberOrder.getOrderType());
		request.setAttribute("is_use", jpoInviteListManager.getJpoInviteCodeByUserCode(loginSysUser.getUserCode()));

		
		//=====================Modify By WuCF 20170417 升级单针对期限90天的控制
		if(!jpoMemberOrderManager.upGradeInTime(loginSysUser.getUserCode(),orderId)){
			return new ModelAndView("jfiOrderProductMsg", "isOver", "此订单已过升级单支付期限!");
		}

		// flag(0:为电子存折 1:订单)
		String flag = request.getParameter("flag");
		if ("1".equals(flag)) {
			Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
			if ("CN".equals(jpoMemberOrder.getCompanyCode())) {

				while (its1.hasNext()) {
					JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();

					String status = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getStatus();// 商品状态，是否过期

					// 商品状态
					if (!"P26010100101CN0".equalsIgnoreCase(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo())) {
						if (!"1".equals(status)) {
							return new ModelAndView("jfiOrderProductMsg", "isOver", "产品(" + jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo()
									+ ")已停售!");
						}
					}
				}
				// 验证产品购买数量是否超过限制
				if (isOverQty(jpoMemberOrder)) {
					return new ModelAndView("jfiOrderProductMsg", "isOver", "产品已售完或剩余不足!");
				}
			} else {
				return new ModelAndView("jfiOrderProductMsg", "isOver", "订单不存在!");
			}

			// 设置支付后返回的url
			String url = "jpoMemberOrders/orderAll?needReload=1";

			if (!StringUtils.isEmpty(url)) {
				request.setAttribute("url", url);
			}

			// bug :2928 
			if (jpoMemberOrder == null || 
					jpoMemberOrder.getStatus().equals("2") || 
					jpoMemberOrder.getStatus().equals("3") ||
					! loginSysUser.getUserCode().equals(jpoMemberOrder.getSysUser().getUserCode())) {
				return new ModelAndView("redirect:jpoMemberOrders/orderAll?needReload=1");
			}
			
			if (isOverQty(jpoMemberOrder)) { // 库存
				return new ModelAndView("jfiOrderProductMsg", "isOver", "库存不足!");
			}
			
			if(validateOrder(jpoMemberOrder, loginSysUser)){
	        	log.info("orderIsPayVali:"+jpoMemberOrder.getMemberOrderNo());
	        	return new ModelAndView("jfiOrderProductMsg", "isOver", 
	        			LocaleUtil.getLocalText("user.validate"));
	        }
			// -----------【公告】2016年1月促销------------start
			Map<String, String> resultMap = PromotionsUtils.checkCX201601(jpoMemberOrder);
			if(resultMap == null){
				resultMap = PromotionsUtils.checkCX201603(jpoMemberOrder);
			}
			if (resultMap != null) {
				if (!"-1".equals(resultMap.get("index"))) {
					coinPay = true;
					if ("false".equals(resultMap.get("code"))) {
						return new ModelAndView("jfiOrderProductMsg", "isOver", resultMap.get("msg"));
					}
					if ("1".equals(resultMap.get("index")) && resultMap.get("listsCode").equals(StringUtils.join(GlobalVar.jpoList20160102, ","))) { // 枸杞子
						if (!jpoMemberOrder.getTeamCode().equals("CN32985884")) {// 冯波团队
							return new ModelAndView("redirect:jfiPayByJF?orderId=" + orderId);// 积分换购页面
						}
					}
				}
			}
			resultMap = PromotionsUtils.checkCX20160118(jpoMemberOrder);
			if (resultMap != null) {// 是促销的商品
				if ("1".equals(resultMap.get("code"))) { 
					isFund = false;
				}
				if ("2".equals(resultMap.get("code"))) { 
					return new ModelAndView("jfiOrderProductMsg", "isOver", resultMap.get("msg"));
				}
			}

			// -----------【公告】2016年1月促销------------end
			// 积分支付判断开关
			if (("1").equals(getAuthorityBcoinPayByOrder(jpoMemberOrder))) {
				coinPay = true;
			}
			
			//============================2016-05-25   2016年6月积分换购需求开发================================================start
			String jsonStr = "";
			String startTime = "";
			String endTime = "";
			//旋磁椅
			if (PromotionsUtils.integralCheck(jpoMemberOrder, GlobalVar.jpoList201606xcy,"201606xcy.startdate","201606xcy.enddate")) {
				coinPay = true;//可以积分支付
				isFund = true;//基金支付
			}
			//3D床垫、道和国韵（体验装）
	    	if (PromotionsUtils.integralCheck(jpoMemberOrder, GlobalVar.jpoList201606,"201606coincx.startdate","201606coincx.enddate")) {
				coinPay = true;//可以积分支付
				isFund = true;//基金支付
			}
	    	//道和国韵（体验装）
	    	if (PromotionsUtils.integralCheck(jpoMemberOrder, GlobalVar.jpoList201805,"201805coincx.startdate","201805coincx.enddate")) {
	    		coinPay = true;//可以积分支付
	    		isFund = true;//基金支付
	    	}
	    	
	    	//11月  重消单开启积分换购   GlobalVar.jpocoincxList201611 为不参加积分换购的特殊商品
	    	if (PromotionsUtils.coincxCheck(jpoMemberOrder, GlobalVar.jpocoincxList201611,"201611coincx.startdate","201611coincx.enddate")) {
				coinPay = true;//可以积分支付
				isFund = true;//基金支付
			}
	    	//重销单可以用产品积分方式支付 add by hdg 2017-01-03
			if("4".equals(jpoMemberOrder.getOrderType())) {
				isProductPoint = true;	//可以产品积分支付
				request.setAttribute("isProductPoint", isProductPoint);// 是否有产品积分支付按钮
			}
			//add by lijl 2017-02-23 抵用券订单类型：16， 2017-02-23的 新需求：如果是抵用券订单，直接跳到抵用券支付页面
			if("16".equals(jpoMemberOrder.getOrderType())) {
				return new ModelAndView("redirect:jfiPayByProPoint?orderId=" + orderId);// 抵用券支付页面
			}
			if("93".equals(jpoMemberOrder.getOrderType())) {
				return new ModelAndView("redirect:jfiPayByCoupon?orderId=" + orderId);// 抵用券支付页面
			}
			//============================2016-05-25  2016年6月积分换购需求开发================================================end

			request.setAttribute("isFund", isFund);// 是否有基金按钮
			request.setAttribute("coinPay", coinPay);// 是否有积分按钮

			// 积分支付判断开关
			if (("1").equals(getAuthorityBcoinPayByOrder(jpoMemberOrder))) {
				request.setAttribute("coinPay", "true");
			}

			request.setAttribute("jpoMemberOrder", jpoMemberOrder);
			JfiBankbookBalance jfiBankbookBalance = jfiBankbookBalanceManager.getJfiBankbookBalance(jpoMemberOrder.getSysUser().getUserCode());
			request.setAttribute("bankbook", jfiBankbookBalance.getBalance());

			
			//============================2016-05-24圣诗蔓积分换购(>=5:5)================================================start
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	Date now = Calendar.getInstance().getTime();
			String startCoin = ConfigUtil.getConfigValue("CN", "201605coincx.startdate");
	    	String endCoin = ConfigUtil.getConfigValue("CN", "201605coincx.enddate");
	    	try {
		    	if(startCoin != null && endCoin != null){
					if(now.after(sdf.parse(startCoin)) && now.before(sdf.parse(endCoin))){
						if(jpoIsOnly(jpoMemberOrder,GlobalVar.jpoList20160524)){
							FiBcoinBalance fiBcoinBalance = fiBcoinBalanceManager.get(jpoMemberOrder.getSysUser().getUserCode());
							BigDecimal coin = new BigDecimal(0);
							BigDecimal sumCoin = new BigDecimal(0);
							if(null != fiBcoinBalance){
								coin = fiBcoinBalance.getBalance();//积分余额
							}
							if(jpoMemberOrder.getAmount().multiply(new BigDecimal(0.5)).compareTo(coin)==1){
								sumCoin = coin;
							}else{
								sumCoin = jpoMemberOrder.getAmount().multiply(new BigDecimal(0.5));
							}
					        
							sumCoin = new BigDecimal(sumCoin.intValue());
							
							request.setAttribute("coin", coin);//积分余额
							request.setAttribute("isIntegra", false);
							request.setAttribute("sumCoin",sumCoin);
							//积分支付页面
					        return new ModelAndView("jfiPayByCoinAndBankbook");
						}
					}
				}
		    }catch(Exception e){
		    	log.info("支付跳转失败!",e);
	    	}
	        //============================2016-05-24圣诗蔓积分换购(>=5:5)================================================end
			
		}

		if (StringUtils.isEmpty(flag)) {
			request.setAttribute("flag", 0);
		} else {
			request.setAttribute("flag", flag);
		}

		return new ModelAndView("jfiPay99bill", "jfi99bill", null);
	}

	/**
	 * 13财月促销---积分换购
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	public boolean checkAbleCoinProductNo(JpoMemberOrder jpoMemberOrder) {

		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
		String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();

		// 13财月促销---积分换购,积分换购商品编码：P1508010101CN0
		if ("P1508010101CN0".equals(productNo)) {

			return true;
		}
		return false;
	}

	/**
	 * 判断是否为启智派商品：产品编号P24010100101CN0
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	public boolean checkQzpProductNo(JpoMemberOrder jpoMemberOrder) {

		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
		String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();

		if ("P24010100101CN0".equals(productNo)) {

			return true;
		}
		return false;
	}

	/**
	 * 中秋月饼促销，产品编号：P23030100101CN0,P23030200101CN0
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	private boolean isMooncakesProduct(JpoMemberOrder jpoMemberOrder) {

		boolean flag = false;

		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its1.hasNext()) {

			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();

			if (("P23030100101CN0").equals(productNo) || ("P23030200101CN0").equals(productNo)) {

				flag = true;// 含有月饼促销产品
				break;
			}
		}

		if (flag == true) {

			while (its1.hasNext()) {
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
				String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();

				if (!("P23030100101CN0").equals(productNo) && !("P23030200101CN0").equals(productNo)) {

					flag = false;
					break;
				}
			}
		}

		return flag;
	}

	/**
	 * 判断是否为三代无忧奖励计划产品、台湾旅游积分产品：产品编号N07010100101CN0,N07020100101CN0
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	public boolean checkTWLYJFProductNo(JpoMemberOrder jpoMemberOrder) {

		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
		String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();

		if ("N07010100101CN0".equals(productNo) || "N07020100101CN0".equals(productNo)) {

			return true;
		}
		return false;
	}

	/**
	 * 云南团队：产品编号P03190100102CN0,P03200100102CN0
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	public boolean checkYNProductNo(JpoMemberOrder jpoMemberOrder) {

		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
		String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();

		if ("P03190100102CN0".equals(productNo) || "P03200100102CN0".equals(productNo)) {

			return true;
		}
		return false;
	}

	/**
	 * 限制订购数量：产品编号P25090100101CN0,P25090100201CN0,P25090100301CN0,
	 * P25100100101CN0,
	 * P25100100201CN0,P25100100301CN0,P25110100101CN0,P25110100201CN0
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	public boolean checkNumProductNo(JpoMemberOrder jpoMemberOrder) {

		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
		String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();

		if ("P25090100201CN0".equals(productNo) || "P25090100301CN0".equals(productNo) || "P25100100101CN0".equals(productNo) || "P25100100201CN0".equals(productNo)
				|| "P25100100301CN0".equals(productNo) || "P25110100101CN0".equals(productNo) || "P25110100201CN0".equals(productNo) || "P25090100101CN0".equals(productNo)) {

			return true;
		}
		return false;
	}

	/**
	 * 2015五财月积分换购
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	public boolean checkFiveProductNo(JpoMemberOrder jpoMemberOrder) {

		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
		String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
		String teamId = jpoMemberOrder.getTeamCode();
		if ("P01170100101CN0".equals(productNo) || "P08420300201CN0".equals(productNo) || "P1508010101CN0".equals(productNo) || "P01290100201CN0".equals(productNo)) {
			if (null != teamId && !"".equals(teamId)) {
				if (!"CN16481747".equals(teamId) && !"CN18728599".equals(teamId) && !"CN30768473".equals(teamId) && !"CN12365064".equals(teamId)) {
					log.info("-------------------------------------------------1");
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 2015五财月积分换购
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	public boolean checkFiveYGDProductNo(JpoMemberOrder jpoMemberOrder) {

		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
		String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
		String teamId = jpoMemberOrder.getTeamCode();
		if ("P1604010201CN0".equals(productNo) || "P16120100201CN0".equals(productNo)) {
			if (null != teamId && !"".equals(teamId)) {
				if ("CN12365064".equals(teamId)) {
					log.info("-------------------------------------------------2");
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 2015五财月积分换购,限购数量的商品
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	public boolean checkFiveNumProductNo(JpoMemberOrder jpoMemberOrder) {

		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
		String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
		String teamId = jpoMemberOrder.getTeamCode();
		if ("P25040200201CN0".equals(productNo) || "P25040200301CN0".equals(productNo) || "P25040100201CN0".equals(productNo)) // ||
		// "P25040100301CN0".equals(productNo))
		{
			Iterator it = jpoMemberOrder.getJpoMemberOrderList().iterator();

			while (it.hasNext()) {
				JpoMemberOrderList orderList = (JpoMemberOrderList) it.next();
				log.info("-------------------------------------------------333333333333333333333" + jpoMemberOrderManager.getIsOver3(productNo, orderList.getQty()));
				if (jpoMemberOrderManager.getIsOver3(productNo, orderList.getQty()) == 0) {
					log.info("falsefalsefalsefalsefalsefalsefalsefalsefalsefalsefalsefalsefalse");
					return false;
				}
			}

			if (null != teamId && !"".equals(teamId)) {
				if (!"CN16481747".equals(teamId) && !"CN18728599".equals(teamId) && !"CN30768473".equals(teamId) && !"CN12365064".equals(teamId)) {
					log.info("-------------------------------------------------3");
					return true;
				}
			}
		}
		return false;
	}

	public boolean checkNumByOrder(JpoMemberOrder jpoMemberOrder) {
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

	/**
	 * 6财月促销商品检测
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	public boolean checkCX6(JpoMemberOrder jpoMemberOrder) {
		// 不参加团队：田阳2、5 TT2：CN16481747, TT5；CN18728599
		if (("CN16481747").equals(jpoMemberOrder.getTeamCode()) || ("CN18728599").equals(jpoMemberOrder.getTeamCode())) {

			return false;
		}

		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();

			// YGD积分换购：YGD：CN12365064 ,不参加产品：P1604010101CN0颐丽超音波熏香灯芯头
			if (("CN12365064").equals(jpoMemberOrder.getTeamCode())) {

				if ("P1604010101CN0".equals(productNo)) {

					return false;
				}
			}

			// 不参加产品：P03210100101CN0颐佳漱爽露便携装
			// ,P01050200101CN0中脉有乐生命活能饮（旅行装）,P01170400101CN0Cellight海洋单细胞海藻胶囊（5瓶装）
			// //Modify By WuCF 20150216 添加P04060100101CN0
			if ("P03210100101CN0".equals(productNo) || "P01050200101CN0".equals(productNo) || "P01170400101CN0".equals(productNo) || "P04060100101CN0".equals(productNo)) {

				return false;
			}

			// String productNo =
			// jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
			int buyNum = jpoMemberOrderList.getQty();

			// 限购产品：1、P04010212001CN0中脉远红磁性保健功能床垫（复合套装）规格：120CM*190CM 数量：1000
			// 2、 P04010119001CN0中脉远红磁性保健功能床垫（复合套装）规格：150CM*190CM 数量：1000
			// 3、 P04010118001CN0能量睡眠系列中脉健康床垫180cm 数量：2000
			// 4、 P04010115001CN0中脉远红磁性保健功能床垫150cm*200cm 数量：1000
			// 5、 P04010300101CN0 能量睡眠系列中脉健康床垫(单只装) 数量 2000
			if ("P04010212001CN0".equals(productNo) || "P04010119001CN0".equals(productNo) || "P04010118001CN0".equals(productNo) || "P04010115001CN0".equals(productNo)
					|| "P04010300101CN0".equals(productNo)) {
				FiBcoinPayconfigDetail fiBcoinPayconfigDetail = fiBcoinPayconfigDetailManager.getFiBcoinPayconfigDetailByProductId(productNo);
				if (fiBcoinPayconfigDetail != null) {

					if (fiBcoinPayconfigDetail.getNowQuantity() == 0 || fiBcoinPayconfigDetail.getNowQuantity() < buyNum) {
						return false;
					}
				} else {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * 判断是否可以用积分支付
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	public String getAuthorityBcoinPayByOrder(JpoMemberOrder jpoMemberOrder) {



		// 积分，只有特定的订单类型能用,4：重消单　9：一级店铺重消　14：二级店铺重消
		if (OrderCheckUtils.checkCoinPayOrderType(jpoMemberOrder)) {
			FiBcoinBalance fiBcoinBalance = fiBcoinBalanceManager.get(jpoMemberOrder.getSysUser().getUserCode());
			if (new BigDecimal(1).compareTo(fiBcoinBalance.getBalance()) == 1) {
				return "0";
			}

			// 5:辅消单
			if ("5".equals(jpoMemberOrder.getOrderType())) {
				// 判断是不是优惠订购的产品
				int preferential = jpoMemberOrderManager.getPreferentialOrder(jpoMemberOrder);
				if (preferential == 2) {// 2代表订单通过 需要计算物流费，允许用积分换购
					return "1";
				} else {
					return "0";
				}
			}
//  		201606 道和国韵1993 （1箱-体验装）积分换购冲突
//			if (checkAbleCoinProductNo(jpoMemberOrder)) {
//				return "1";
//			}

			// 判断是否为启智派商品
			if (checkQzpProductNo(jpoMemberOrder)) {
				return "1";
			}
		}

		return "0";
	}

}
