package com.joymain.ng.webapp.controller;

import com.joymain.ng.webapp.util.OrderCheckUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.Constants;
import com.joymain.ng.handle.GlobalVar;
import com.joymain.ng.model.FiBcoinBalance;
import com.joymain.ng.model.FiBcoinJournal;
import com.joymain.ng.model.FiBcoinPayconfigDetail;
import com.joymain.ng.model.FiProductPointBalance;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiBcoinBalanceManager;
import com.joymain.ng.service.FiBcoinJournalManager;
import com.joymain.ng.service.FiBcoinPayconfigDetailManager;
import com.joymain.ng.service.FiBcoinPayconfigManager;
import com.joymain.ng.service.FiProductPointBalanceManager;
import com.joymain.ng.service.JpoMemberOrderListManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.DateUtil;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.MeteorUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.webapp.util.PromotionsUtils;

/**
 * 手机积分支付服务器端接口控制器
 * 
 * @author hywen
 * 
 */
@Controller
@RequestMapping("mobileCoinPay")
@SuppressWarnings({ "rawtypes", "unused" })
public class MobilePayByCoinController extends BaseFormController {

	private Log log = LogFactory.getLog(MobilePayByCoinController.class);

	@Autowired
	private FiBcoinBalanceManager fiBcoinBalanceManager;

	@Autowired
	private FiBcoinJournalManager fiBcoinJournalManager;

	@Autowired
	private JsysUserManager jsysUserManager;

	@Autowired
	private JpoMemberOrderManager jpoMemberOrderManager;

	@Autowired
	private JpoMemberOrderListManager jpoMemberOrderListManager;

	@Autowired
	private FiBcoinPayconfigManager fiBcoinPayconfigManager = null;// 积分换购活动

	@Autowired
	FiBcoinPayconfigDetailManager fiBcoinPayconfigDetailManager;

	@Autowired
	FiProductPointBalanceManager fiProductPointBalanceManager;

	/**
	 * 查询欢乐积分帐户交易流水
	 * 
	 * @param userId
	 *            用户编码
	 * @param dealStartTime
	 *            开始时间
	 * @param dealEndTime
	 *            结束时间
	 * @return 交易流水
	 */
	@RequestMapping(value = "api/getCoinJournals")
	@ResponseBody
	public List getFiBcoinJournalListByUserCode(String userId, String token, String dealStartTime, String dealEndTime,
			int pageNum, int pageSize) {

		JsysUser user = jsysUserManager.getUserByToken(userId, token);
		Object object = jsysUserManager.getAuthErrorCode(user, "List");
		if (null != object) {
			return (List) object;
		}
		if (!StringUtil.isEmpty(dealStartTime) && !StringUtil.isEmpty(dealEndTime)
				&& dealStartTime.equals(dealEndTime)) {
			dealEndTime += " 23:59:59";
		}
		// 查询欢乐积分交易流水
		List<FiBcoinJournal> fiBcoinJournalList = fiBcoinJournalManager.getFiBcoinJournalListByUser(user.getUserCode(),
				dealStartTime, dealEndTime, pageNum, pageSize);

		return fiBcoinJournalList;
	}

	/**
	 * 查询欢乐积分帐户余额
	 * 
	 * @param userId
	 *            用户编码
	 * @return 欢乐积分帐户余额
	 */
	@RequestMapping(value = "api/getCoinBalance")
	@ResponseBody
	public String getFiBcoinBalanceManagerByUserCode(String userId, String token) {
		JsysUser user = jsysUserManager.getUserByToken(userId, token);
		Object object = jsysUserManager.getAuthErrorCode(user, "String");
		if (null != object) {
			return (String) object;
		}

		FiBcoinBalance fiBcoinBalance = fiBcoinBalanceManager.get(user.getUserCode());

		return fiBcoinBalance.getBalance().toString();
	}

	/**
	 * 使用欢乐积分支付订单
	 * 
	 * @param userId
	 *            用户编号
	 * @param orderId
	 *            订单id
	 * @param integral
	 *            积分值 默认传递error
	 * @return 返回码： 1代表支付成功, 0代表支付失败，error：错误，2：P04060100201CN0磁悬浮支付错误{code:错误编码
	 *         ,message:'消息内容'} 错误编码 0:成功, 1:失败
	 */
	@RequestMapping(value = "api/payOrderByCoin")
	@ResponseBody
	public String payJpoMemberOrderByFiCoin(String userId, String orderId, String integral, String token) {
		JsysUser user = jsysUserManager.getUserByToken(userId, token);
		Object object = jsysUserManager.getAuthErrorCode(user, "String");

		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
		if (null != object) {
			return (String) object;
		}
		// 订单为空，订单状态已审核，不是当前用户
		if (jpoMemberOrder == null || !"1".equals(jpoMemberOrder.getStatus())
				|| !userId.equals(jpoMemberOrder.getSysUser().getUserCode())) {
			return "error";
		}

		if (validateOrder(jpoMemberOrder, user)) {
			log.info(jpoMemberOrder.getMemberOrderNo() + LocaleUtil.getLocalText("user.validate"));
			return "error";
		}

		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		if ("CN".equals(jpoMemberOrder.getCompanyCode())) {
			while (its1.hasNext()) {
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
				String status = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getStatus();
				if (!"1".equals(status)) {
					// return new ModelAndView("jfiOrderProductMsg", "isOver",
					// "产品(" +
					// jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo()
					// + ")已售完!");
					return "error";
				}
			}
		} else {
			return "error";
		}

		// //端午商品促销控制
		// Integer proSum=0, proNoCount=0,countQty=0;
		// String proCount_str = ConfigUtil.getConfigValue("CN",
		// "product.count");
		// log.info("product sum is:["+proCount_str+"]");
		// if(StringUtils.isNotBlank(proCount_str)){
		// proNoCount = Integer.parseInt(proCount_str);
		// }
		// Iterator<JpoMemberOrderList> ite =
		// jpoMemberOrder.getJpoMemberOrderList().iterator();
		// while(ite.hasNext()){
		// JpoMemberOrderList orderList = ite.next();
		// String proNo = orderList.getJpmProductSaleTeamType().
		// getJpmProductSaleNew().getProductNo();
		// if(proNo.equalsIgnoreCase(Constants.PRONO) ||
		// proNo.equalsIgnoreCase(Constants.PRONO1) ||
		// proNo.equalsIgnoreCase(Constants.PRONO2)){
		//
		// Integer temQty = 0;
		// if(proNo.equalsIgnoreCase(Constants.PRONO)){
		// temQty =orderList.getQty();
		// }else if(proNo.equalsIgnoreCase(Constants.PRONO1)){
		// temQty = orderList.getQty() * 3;
		// } else if(proNo.equalsIgnoreCase(Constants.PRONO2)){
		// temQty= orderList.getQty() * 5;
		// }
		// countQty += temQty;
		//
		// proSum = jpoMemberOrderListManager.getSumQtyByProNo();
		//
		// if((proNoCount-proSum)<countQty){
		// return "error";
		// }
		// }
		//
		// log.info("proNoCount =["+proNoCount+"] " +
		// "and proSum is=["+proSum+"] countQty="+countQty);
		//
		// if(proSum >= proNoCount){
		// return "error";
		// }
		//
		// }
		//
		// 验证产品购买数量是否超过限制
		if (isBuyPro(jpoMemberOrder)) {
			return "error";
		}
		// 积分，只有特定的订单类型能用,4：重消单 9：一级店铺重消 14：二级店铺重消, 5:辅消单
		if (!OrderCheckUtils.checkCoinPayOrderType(jpoMemberOrder)) {
			return "error";
		}

		// FiBcoinBalance fiBcoinBalance =
		// fiBcoinBalanceManager.get(jpoMemberOrder.getSysUser().getUserCode());
		//
		// Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		//
		// BigDecimal productAmount = new BigDecimal("0");
		// its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		// while (its1.hasNext()) {
		// JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList)
		// its1.next();
		// productAmount =
		// productAmount.add(jpoMemberOrderList.getPrice().multiply(new
		// BigDecimal(jpoMemberOrderList.getQty())));
		// }
		// BigDecimal sumCoin = new BigDecimal("0");
		//
		// //重消单
		// if("4".equals(jpoMemberOrder.getOrderType())||"9".equals(jpoMemberOrder.getOrderType())||"14".equals(jpoMemberOrder.getOrderType())){
		//
		// int payByCoin =
		// Integer.parseInt(ConfigUtil.getConfigValue("CN","paybycoin"));
		// if(payByCoin==0){
		//
		// return "0";//不允许积分支付
		// }
		//
		// if(productAmount.compareTo(fiBcoinBalance.getBalance().multiply(new
		// BigDecimal("2")))!=1){
		// sumCoin = productAmount.multiply(new BigDecimal("0.5"));
		// }else{
		// sumCoin = fiBcoinBalance.getBalance();
		// }
		//
		// if(jpoMemberOrder.getAmount().compareTo(sumCoin.multiply(new
		// BigDecimal("2")))==-1){
		// return "0";//积分计算有误
		// }
		// }
		//
		// //辅消品
		// if("5".equals(jpoMemberOrder.getOrderType())){
		// if(productAmount.compareTo(fiBcoinBalance.getBalance().multiply(new
		// BigDecimal("2.5")))!=1){
		// sumCoin = productAmount.multiply(new BigDecimal("0.4"));
		// }else{
		// sumCoin = fiBcoinBalance.getBalance();
		// }
		// if(jpoMemberOrder.getAmount().compareTo(sumCoin.multiply(new
		// BigDecimal("2.5")))==-1){
		// return "0";//积分计算有误
		// }
		// }
		FiBcoinBalance fiBcoinBalance = fiBcoinBalanceManager.get(jpoMemberOrder.getSysUser().getUserCode());
		BigDecimal blance = fiBcoinBalance.getBalance(); // 当前积分
		Boolean isIntegra = true; // 判断是否走以前的积分支付方式
		boolean b = false; // 是否发送MQ
		if (!"error".equals(integral)) {
			int jf = Integer.parseInt(integral);// 输入积分
			if (jf <= 0) {
				return "error";
			}
			if (blance.compareTo(new BigDecimal(integral)) == -1) {
				return "error";
			}
			isIntegra = false;
		}

		if (isOverQty(jpoMemberOrder)) {
			return "error";// 库存不足
		}

		try {
			BigDecimal sumCoin = new BigDecimal(0);
			Map<String, String> resultMap = PromotionsUtils.checkCX201601(jpoMemberOrder);
			if (resultMap == null) {
				resultMap = PromotionsUtils.checkCX201603(jpoMemberOrder);
			}
			if (resultMap != null && !"-1".equals(resultMap.get("index"))) {// 是促销的商品
				if ("false".equals(resultMap.get("code"))) {
					return "error";
				}
				sumCoin = new BigDecimal(resultMap.get("maxJF")).setScale(0, RoundingMode.DOWN);
				if (fiBcoinBalance.getBalance().compareTo(sumCoin) != 1) {
					sumCoin = fiBcoinBalance.getBalance();
				}
				if (sumCoin.compareTo(new BigDecimal(integral)) == -1) {
					return "error";
				}
				isIntegra = false;
				// 审单扣款
				jpoMemberOrderManager.checkJpoMemberOrderByCoinAndBankbook(jpoMemberOrder, user, "2",
						new BigDecimal(integral));
				b = true;
			} else {
				// ============================2016-05-24圣诗蔓积分换购(>=5:5)================================================start
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date now = Calendar.getInstance().getTime();
				String startCoin = ConfigUtil.getConfigValue("CN", "201605coincx.startdate");
				String endCoin = ConfigUtil.getConfigValue("CN", "201605coincx.enddate");
				try {
					if (startCoin != null && endCoin != null) {
						if (now.after(sdf.parse(startCoin)) && now.before(sdf.parse(endCoin))) {
							if (jpoIsOnly(jpoMemberOrder, GlobalVar.jpoList20160524)) {
								BigDecimal coin = new BigDecimal(0);
								sumCoin = new BigDecimal(0);// 最大使用积分抵扣限制额
								if (null != fiBcoinBalance) {
									coin = fiBcoinBalance.getBalance();// 积分余额
								}
								if (jpoMemberOrder.getAmount().multiply(new BigDecimal(0.5)).compareTo(coin) == 1) {
									sumCoin = coin;
								} else {
									sumCoin = jpoMemberOrder.getAmount().multiply(new BigDecimal(0.5));
								}

								if (sumCoin.compareTo(new BigDecimal(integral)) == -1) {
									return "error"; // "该订单最多使用" + sumCoin +
													// "积分!"
								}
								// 积分支付页面
								isIntegra = false;
								// 审单扣款
								jpoMemberOrderManager.checkJpoMemberOrderByCoinAndBankbook(jpoMemberOrder, user, "2",
										new BigDecimal(integral));
								b = true;
							}
						}
					}
				} catch (Exception e) {
					// log.info("支付失败!",e);
					log.info(e.getMessage(), e);
					return "0";
				}
				// ============================2016-05-24圣诗蔓积分换购(>=5:5)================================================end

				// ============================2016-05-25
				// 2016年6月积分换购需求开发================================================start
				String jsonStr = "";
				// 旋磁椅
				jsonStr = PromotionsUtils.checkJfPay(jpoMemberOrder, GlobalVar.jpoList201606xcy, "201606xcy.startdate",
						"201606xcy.enddate", fiBcoinBalance, "0", "25000");
				if (!"-1".equals(jsonStr)) {// 验证通过 通过返回值得到可使用积分
					isIntegra = false;
					resultMap = MeteorUtil.parseJSON2MapString(jsonStr);
					if (null != resultMap) {
						sumCoin = new BigDecimal(resultMap.get("sumCoin"));
						if (sumCoin.compareTo(new BigDecimal(integral)) == -1) {
							return "error"; // "该订单最多使用" + sumCoin + "积分!"
						}
						jpoMemberOrderManager.checkJpoMemberOrderByCoinAndBankbook(jpoMemberOrder, user, "2",
								new BigDecimal(integral));
						b = true;
					}
				}
				// 3D床垫、道和国韵（体验装）
				jsonStr = PromotionsUtils.checkJfPay(jpoMemberOrder, GlobalVar.jpoList201606, "201606coincx.startdate",
						"201606coincx.enddate", fiBcoinBalance, "1", "0.5");
				if ("-1".equals(jsonStr)) {
					// 11月 重消单开启积分换购可用积分试算 GlobalVar.jpocoincxList201611
					// 为不参加积分换购的特殊商品
					jsonStr = PromotionsUtils.checkCoincxPay(jpoMemberOrder, GlobalVar.jpocoincxList201611,
							"201611coincx.startdate", "201611coincx.enddate", fiBcoinBalance, "0.5");
				}
				if (!"-1".equals(jsonStr)) {// 验证通过 通过返回值得到可使用积分
					isIntegra = false;
					resultMap = MeteorUtil.parseJSON2MapString(jsonStr);
					if (null != resultMap) {
						sumCoin = new BigDecimal(resultMap.get("sumCoin"));
						if (sumCoin.compareTo(new BigDecimal(integral)) == -1) {
							return "error"; // "该订单最多使用" + sumCoin + "积分!"
						}
						jpoMemberOrderManager.checkJpoMemberOrderByCoinAndBankbook(jpoMemberOrder, user, "2",
								new BigDecimal(integral));
						b = true;
					}
				}
				// 道和国韵（黑瓶装）
				jsonStr = PromotionsUtils.checkJfPay(jpoMemberOrder, GlobalVar.jpoList201805, "201805coincx.startdate",
						"201805coincx.enddate", fiBcoinBalance, "1", "0.5");
				if ("-1".equals(jsonStr)) {
					// 11月 重消单开启积分换购可用积分试算 GlobalVar.jpocoincxList201611
					// 为不参加积分换购的特殊商品
					jsonStr = PromotionsUtils.checkCoincxPay(jpoMemberOrder, GlobalVar.jpocoincxList201611,
							"201611coincx.startdate", "201611coincx.enddate", fiBcoinBalance, "0.5");
				}
				if (!"-1".equals(jsonStr)) {// 验证通过 通过返回值得到可使用积分
					isIntegra = false;
					resultMap = MeteorUtil.parseJSON2MapString(jsonStr);
					if (null != resultMap) {
						sumCoin = new BigDecimal(resultMap.get("sumCoin"));
						if (sumCoin.compareTo(new BigDecimal(integral)) == -1) {
							return "error"; // "该订单最多使用" + sumCoin + "积分!"
						}
						jpoMemberOrderManager.checkJpoMemberOrderByCoinAndBankbook(jpoMemberOrder, user, "2",
								new BigDecimal(integral));
						b = true;
					}
				}

				// ============================2016-05-25
				// 2016年6月积分换购需求开发================================================end
			}
		} catch (Exception e) {
			log.info(e.getMessage(), e);
			return "0";
		}
		// ============================2016年1月促销-积分换购================================================end
		log.info("bankbook insert start4:" + jpoMemberOrder.getMoId());
		if (isIntegra) {
			try {
				// 调用积分支付审单
				jpoMemberOrderManager.checkJpoMemberOrderByCoinAndBankbook(jpoMemberOrder, user, "2");
				b = true;
			} catch (Exception app) {
				return "0";
			}
		}
		log.info("bankbook insert stop4:" + jpoMemberOrder.getMoId());

		log.info("updateJpoMemberOrderPaymentType start4:" + jpoMemberOrder.getMoId());
		// Modify By WUCF 20160805 新增订单支付来源标示字段：PAYMENT_TYPE修改
		jpoMemberOrderManager.updateJpoMemberOrderPaymentType(String.valueOf(jpoMemberOrder.getMoId()), "4");
		log.info("updateJpoMemberOrderPaymentType stop4:" + jpoMemberOrder.getMoId());
		/*
		 * 支付改造 if(b){ try{ //发送MQ消息 jpoMemberOrder.setStatus("3");
		 * jpoMemberOrderManager.save(jpoMemberOrder);
		 * jpoMemberOrderManager.jpoMemberOrderPayedSendToMQ(jpoMemberOrder,
		 * user, "2"); log.info(
		 * "MobilePayMovieController=======手机支付审单MQ：  orderNo:" +
		 * jpoMemberOrder.getMemberOrderNo()); }catch (Exception e) {
		 * log.error(jpoMemberOrder.getMemberOrderNo()+"发送mq消息失败：",e); } }
		 */
		return "1";
	}

	/**
	 * 查询是否可以使用欢乐积分支付
	 * 
	 * @param userId
	 * @param orderId
	 *            订单ID
	 * @return 是否可以用积分支付，code 1：可用，0：不可 ,2: 只允许积分支付,3。库存不足，4只允许现金支付
	 */
	@RequestMapping(value = "api/coinPayAuthority", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getAuthorityBcoinPayByOrderId(String userId, String orderId, String token) {
		String fmtJson = "{code:%s,msg:\"%s\"}";
		JsysUser user = jsysUserManager.getUserByToken(userId, token);
		Object object = jsysUserManager.getAuthErrorCode(user, "String");
		Map<String, String> resultMap = null;
		try {
			// =====================Modify By WuCF 20170417 升级单针对期限90天的控制
			if (!jpoMemberOrderManager.upGradeInTime(userId, orderId)) {// 当前支付人的升级单期限
				return String.format(fmtJson, 0, "此订单已过升级单支付期限!");
			}

			if (null != object) {
				return String.format(fmtJson, 0, object + ".不通过!");// (String)
			}

			JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
			if (isOverQty(jpoMemberOrder)) { // 库存
				return String.format(fmtJson, 3, "库存不足.不通过!");
			}

			resultMap = PromotionsUtils.checkCX20160118(jpoMemberOrder);
			if (resultMap != null) {// 是促销的商品
				String code = "1".equals(resultMap.get("code")) ? "4" : "2".equals(resultMap.get("code")) ? "3" : "0";
				if (!"0".equals(code)) {
					return String.format(fmtJson, code, resultMap.get("msg"));
				}

				// 1：可用，0：不可 ,2: 只允许积分支付

				// 手机端
				// 0:存折,快钱,基金
				// 1:存折,快钱,基金,积分
				// 2:积分
				// 3:没有支付方式,提示用户
				// 4:存折,快钱
				// 5:存折,快钱,基金,抵用券

			}

			// 重消单新加抵用券支付方式
			/*if ("4".equals(jpoMemberOrder.getOrderType())) {
				*//*FiProductPointBalance productPointBalance = fiProductPointBalanceManager
						.getProductPointBalance(jpoMemberOrder.getSysUser().getUserCode(), "1");
				Integer type = isCoinPay(jpoMemberOrder);
				if (new BigDecimal(0.01).compareTo(productPointBalance.getBalance()) == 1) {
					return String.format(fmtJson, 0, "抵用券不足.不通过!");
				} else if (type == 0) {
					return String.format(fmtJson, 5, "抵用券支付.通过!");
				} else {
					return String.format(fmtJson, 3, "没有支付方式!");
				}*//*
				return String.format(fmtJson, 0, "");
			}*/


			
			//代金券支付订单类型支付
			if ("93".equals(jpoMemberOrder.getOrderType())) {
				FiProductPointBalance productPointBalance = fiProductPointBalanceManager.getProductPointBalance(jpoMemberOrder.getSysUser().getUserCode(), "1");
				Integer type = isCoinPay(jpoMemberOrder);
				if(type == 0 ){ 
					return String.format(fmtJson, 6, "代金券支付.通过!");
				}else{
					return String.format(fmtJson, 3, "没有支付方式!");
				}
			}
			
			// 重消单新加抵用券支付方式
			if ("16".equals(jpoMemberOrder.getOrderType())) {
				FiProductPointBalance productPointBalance = fiProductPointBalanceManager
						.getProductPointBalance(jpoMemberOrder.getSysUser().getUserCode(), "1");
				Integer type = isCoinPay(jpoMemberOrder);
				/*
				 * if (new
				 * BigDecimal(0.01).compareTo(productPointBalance.getBalance())
				 * == 1) { return String.format(fmtJson, 0, "抵用券不足.不通过!"); }else
				 */if (type == 0) {
					return String.format(fmtJson, 5, "抵用券支付.通过!");
				} else {
					return String.format(fmtJson, 3, "没有支付方式!");
				}
			}
			// 积分，只有特定的订单类型能用,4：重消单 9：一级店铺重消 14：二级店铺重消
			if (OrderCheckUtils.checkCoinPayOrderType(jpoMemberOrder)) {
				FiBcoinBalance fiBcoinBalance = fiBcoinBalanceManager.get(jpoMemberOrder.getSysUser().getUserCode());
				// 5:辅消单
				if ("5".equals(jpoMemberOrder.getOrderType())) {
					// 判断是不是优惠订购的产品
					int preferential = jpoMemberOrderManager.getPreferentialOrder(jpoMemberOrder);
					if (preferential == 2) {// 2代表订单通过 需要计算物流费，允许用积分换购
						return String.format(fmtJson, 1, "通过!");
					} else {
						return String.format(fmtJson, 0, "订单通过不通过!");
					}
				}
				if (new BigDecimal(1).compareTo(fiBcoinBalance.getBalance()) == 1) {
					return String.format(fmtJson, 0, "积分不足.不通过!");
				} else {
					Integer type = isCoinPay(jpoMemberOrder);
					return String.format(fmtJson, type, "通过!");
				}
			}

		} catch (Exception e) {
			log.info("接口调用异常!", e);
			return String.format(fmtJson, 0, e + ".不通过!");
		}
		return String.format(fmtJson, 0, "非促销，非辅消单!");
	}

	/**
	 * 支付方式： 手机端 0:存折,快钱,基金 1:存折,快钱,基金,积分 3:没有支付方式,提示用户 4:存折,快钱 5:存折,快钱,基金,抵用券
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	public Integer isCoinPay(JpoMemberOrder jpoMemberOrder) {
		Integer type = 0;

		if (checkQzpProductNo(jpoMemberOrder)) {
			type = 1; // String.format(fmtJson, 1, "启智派商品.通过!");
		}

		try {
			String str_startDate = LocaleUtil.getLocalText("zh_CN", "cx.startDate");
			String str_endDate = LocaleUtil.getLocalText("zh_CN", "cx.endDate");
			Date starDate = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss", str_startDate);
			Date endDate = DateUtil.convertStringToDate("yyyy-MM-dd hh:mm:ss", str_endDate);
			Date curDate = Calendar.getInstance().getTime();
			// 2-14至3-20
			if (curDate.after(starDate) && curDate.before(endDate)) {

				// 6财月促销 start
				if (this.checkCX6(jpoMemberOrder)) {
					type = 1; // return String.format(fmtJson, 1, "6财月促销
								// start.通过!");
				} else {
					// return String.format(fmtJson, 0, "6财月促销 start.不通过!");
				}
				// 6财月促销 end
			}
			// 2-14之前
			if (curDate.before(starDate)) {

				// 判断第五财月积分换购
				if (checkFiveProductNo(jpoMemberOrder)) {
					type = 1; // return String.format(fmtJson, 1, "5财月促销
								// start.通过!");
				}

				// 判断第五财月积分换购
				if (checkFiveYGDProductNo(jpoMemberOrder)) {
					type = 1; // return String.format(fmtJson, 1, "5财月促销
								// start.通过!");
				}

				// 判断第五财月积分换购
				if (checkFiveNumProductNo(jpoMemberOrder)) {
					type = 1; // return String.format(fmtJson, 1, "5财月促销
								// start.通过!");
				}

				if (!checkNumByOrder(jpoMemberOrder)) {
					type = 1; // return String.format(fmtJson, 0, "5财月促销
								// start.不通过!");
				}

				// 限制数量的商品
				if (checkNumProductNo(jpoMemberOrder)) {
					type = 1; // return String.format(fmtJson, 1, "5财月促销
								// start.通过!");
				}

				// 云南团队促销
				if (checkYNProductNo(jpoMemberOrder)) {
					type = 1; // return String.format(fmtJson, 1, "5财月促销
								// start.通过!");
				}
				// 中秋月饼促销
				if (this.isMooncakesProduct(jpoMemberOrder)) {
					type = 1; // return String.format(fmtJson, 1, "5财月促销
								// start.通过!");
				}

				// 调用公共方法判断是否单独购买九款特殊商品（积分换购),返回true代表是
				if (jpoIsOnly(jpoMemberOrder)) {
					type = 1; // return String.format(fmtJson, 1, "5财月促销
								// start.通过!");
				}
			}

			Map<String, String> resultMap = null;
			resultMap = PromotionsUtils.checkCX201601(jpoMemberOrder);
			if (resultMap == null) {
				resultMap = PromotionsUtils.checkCX201603(jpoMemberOrder);
			}
			if (null != resultMap && !"-1".equals(resultMap.get("index"))) {
				if ("false".equals(resultMap.get("code"))) {
					type = 3; // return String.format(fmtJson, 3,
								// resultMap.get("msg"));
				}
				if ("1".equals(resultMap.get("index"))
						&& resultMap.get("listsCode").equals(StringUtils.join(GlobalVar.jpoList20160102, ","))) { // 枸杞子
					if (!jpoMemberOrder.getTeamCode().equals("CN32985884")) {// 冯波团队
						type = 2; // return String.format(fmtJson, 2,
									// "非冯波团队,只允许积分支付.通过!");
					}
				}
				type = 1; // return String.format(fmtJson, 1,
							// resultMap.get("msg")+"---"+resultMap.get("listsCode"));
			}
			// ==================2016年1財月促销===========================================end

			// ============================2016-05-24圣诗蔓积分换购(>=5:5)================================================start
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date now = Calendar.getInstance().getTime();
			String startCoin = ConfigUtil.getConfigValue("CN", "201605coincx.startdate");
			String endCoin = ConfigUtil.getConfigValue("CN", "201605coincx.enddate");
			try {
				if (startCoin != null && endCoin != null) {
					if (now.after(sdf.parse(startCoin)) && now.before(sdf.parse(endCoin))) {
						if (jpoIsOnly(jpoMemberOrder, GlobalVar.jpoList20160524)) {
							type = 2; // return String.format(fmtJson, 2,
										// "圣诗蔓积分换购.通过!");
						}
					}
				}
			} catch (Exception e) {
				log.info("支付跳转失败!", e);
			}

			// ============================2016-05-24圣诗蔓积分换购(>=5:5)================================================end

			// ============================2016-05-25
			// 2016年6月积分换购需求开发================================================start
			String jsonStr = "";

			// 旋磁椅
			if (PromotionsUtils.integralCheck(jpoMemberOrder, GlobalVar.jpoList201606xcy, "201606xcy.startdate",
					"201606xcy.enddate")) {
				type = 1; // return String.format(fmtJson, 1,
							// "旋磁椅2016年6月积分换购.通过!");
			}
			// 3D床垫、道和国韵（体验装）
			if (PromotionsUtils.integralCheck(jpoMemberOrder, GlobalVar.jpoList201606, "201606coincx.startdate",
					"201606coincx.enddate")) {
				type = 1; // return String.format(fmtJson, 1,
							// "2016年6月积分换购.通过!");
			}
			// 3D床垫、道和国韵（体验装）
			if (PromotionsUtils.integralCheck(jpoMemberOrder, GlobalVar.jpoList201805, "201805coincx.startdate",
					"201805coincx.enddate")) {
				type = 1; // return String.format(fmtJson, 1,
				// "2018年5月积分换购.通过!");
			}
			// 调用公共方法判断是否单独购买九款特殊商品（积分换购),返回true代表是
			if (PromotionsUtils.coincxCheck(jpoMemberOrder, GlobalVar.jpocoincxList201611, "201611coincx.startdate",
					"201611coincx.enddate")) {
				type = 1; // return String.format(fmtJson, 1,
							// "2016年11月积分换购.通过!");
			}

		} catch (Exception e) {
			log.info("接口调用异常!", e);
			e.printStackTrace();
		}

		// ============================2016-05-25
		// 2016年6月积分换购需求开发================================================end

		return type;

	}

	// /**
	// * 是否含有其他商品
	// * @param its1
	// * @return
	// */
	// private boolean isHaveAotherProduct(JpoMemberOrder jpoMemberOrder) {
	// Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
	// while (its1.hasNext()) {
	// JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
	// String productNo =
	// jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
	//
	// if(!("P23010100101CN0").equals(productNo) &&
	// !("P23010200101CN0").equals(productNo) &&
	// !("P23010300101CN0").equals(productNo)){
	// return true;
	// }
	// }
	// return false;
	// }

	/**
	 * 积分试算
	 * 
	 * @param userId
	 *            用户编号
	 * @param orderId
	 *            订单id
	 * @return 返回：积分试算结果,用逗号隔开（如："使用存折金额,使用积分金额"）, 0代表试算失败或者遇到错误
	 */
	@RequestMapping(value = "api/comCoinByOrder")
	@ResponseBody
	public String comCoinByJpoMemberOrder(String userId, String orderId, String token) {

		JsysUser user = jsysUserManager.getUserByToken(userId, token);
		Object object = jsysUserManager.getAuthErrorCode(user, "String");

		if (null != object) {
			return (String) object;
		}

		JpoMemberOrder jpoMemberOrder = jpoMemberOrderManager.get(new Long(orderId));
		FiBcoinBalance fiBcoinBalance = fiBcoinBalanceManager.get(jpoMemberOrder.getSysUser().getUserCode());

		// 订单为空，订单状态已审核，不是当前用户
		if (jpoMemberOrder == null || !"1".equals(jpoMemberOrder.getStatus())
				|| !userId.equals(jpoMemberOrder.getSysUser().getUserCode())) {

			return "0";
		}

		// 积分，只有特定的订单类型能用,4：重消单 9：一级店铺重消 14：二级店铺重消, 5:辅消单
		if (!"5".equals(jpoMemberOrder.getOrderType()) && !"4".equals(jpoMemberOrder.getOrderType())
				&& !"9".equals(jpoMemberOrder.getOrderType()) && !"14".equals(jpoMemberOrder.getOrderType())
				&& !"40".equals(jpoMemberOrder.getOrderType())) {

			return "0";
		}

		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();

		BigDecimal productAmount = new BigDecimal("0");
		its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			productAmount = productAmount
					.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
		}
		BigDecimal sumCoin = new BigDecimal("0");

		// 重消单
		if ("4".equals(jpoMemberOrder.getOrderType()) || "9".equals(jpoMemberOrder.getOrderType())
				|| "14".equals(jpoMemberOrder.getOrderType()) || "40".equals(jpoMemberOrder.getOrderType())) {

			if (productAmount.compareTo(fiBcoinBalance.getBalance().multiply(new BigDecimal("2"))) != 1) {
				sumCoin = productAmount.multiply(new BigDecimal("0.5"));
			} else {
				sumCoin = fiBcoinBalance.getBalance();
			}
			if (jpoMemberOrder.getAmount().compareTo(sumCoin.multiply(new BigDecimal("2"))) == -1) {
				return "0";// 积分计算有误
			}

			// 启智派商品换购比例：允许积分换购：换购比例：最高1000积分+2280元
			if (checkQzpProductNo(jpoMemberOrder)) {

				its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
				int sumUseCoin = jpoMemberOrderList.getQty() * 1000;

				BigDecimal sumNum = new BigDecimal(sumUseCoin);// 每台最高可使用积分

				// 如果会员积分不足
				if (sumNum.compareTo(fiBcoinBalance.getBalance()) == 1) {

					sumCoin = fiBcoinBalance.getBalance();
				} else {// 会员积分足够

					sumCoin = sumNum;
				}
			}

			// 调用公共方法判断是否单独购买九款特殊商品（积分换购),返回false代表是
			if (jpoIsOnly(jpoMemberOrder)) {

				// 重新计算积分
				if (fiBcoinBalance.getBalance().compareTo(productAmount.multiply(new BigDecimal("0.75"))) == 1) {// 积分大于订单总额的75%

					sumCoin = productAmount.multiply(new BigDecimal("0.75"));// 允许使用订单金额75%的积分
				} else {

					sumCoin = fiBcoinBalance.getBalance();
				}
				if (sumCoin.compareTo(productAmount.multiply(new BigDecimal("0.75"))) != 0) {
					return "0";// 积分计算有误
				}
			}
		}

		// 辅消品
		if ("5".equals(jpoMemberOrder.getOrderType())) {
			if (productAmount.compareTo(fiBcoinBalance.getBalance().multiply(new BigDecimal("2.5"))) != 1) {
				sumCoin = productAmount.multiply(new BigDecimal("0.4"));
			} else {
				sumCoin = fiBcoinBalance.getBalance();
			}
			if (jpoMemberOrder.getAmount().compareTo(sumCoin.multiply(new BigDecimal("2.5"))) == -1) {
				return "0";// 积分计算有误
			}
		}

		// ============================2016-05-24圣诗蔓积分换购(>=5:5)================================================start
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = Calendar.getInstance().getTime();
		String startCoin = ConfigUtil.getConfigValue("CN", "201605coincx.startdate");
		String endCoin = ConfigUtil.getConfigValue("CN", "201605coincx.enddate");
		try {
			if (startCoin != null && endCoin != null) {
				if (now.after(sdf.parse(startCoin)) && now.before(sdf.parse(endCoin))) {
					if (jpoIsOnly(jpoMemberOrder, GlobalVar.jpoList20160524)) {
						BigDecimal coin = new BigDecimal(0);
						if (null != fiBcoinBalance) {
							coin = fiBcoinBalance.getBalance();// 积分余额
						}
						if (jpoMemberOrder.getAmount().multiply(new BigDecimal(0.5)).compareTo(coin) == 1) {
							sumCoin = coin;
						} else {
							sumCoin = jpoMemberOrder.getAmount().multiply(new BigDecimal(0.5));
						}
						sumCoin = new BigDecimal(sumCoin.intValue());
					}
				}
			}
		} catch (Exception e) {
			log.info("支付跳转失败!", e);
			return "0";
		}

		// ============================2016-05-24圣诗蔓积分换购(>=5:5)================================================end

		// ============================2016-05-25
		// 2016年6月积分换购需求开发================================================start
		String jsonStr = "";
		Map<String, String> resultMap = null;
		try {
			// 旋磁椅
			jsonStr = PromotionsUtils.checkJfPay(jpoMemberOrder, GlobalVar.jpoList201606xcy, "201606xcy.startdate",
					"201606xcy.enddate", fiBcoinBalance, "0", "25000");
			if (!"-1".equals(jsonStr)) {// 验证通过 通过返回值得到可使用积分
				resultMap = MeteorUtil.parseJSON2MapString(jsonStr);
				if (null != resultMap) {
					sumCoin = new BigDecimal(resultMap.get("sumCoin"));
				}
			}
			// 3D床垫、道和国韵（体验装）
			jsonStr = PromotionsUtils.checkJfPay(jpoMemberOrder, GlobalVar.jpoList201606, "201606coincx.startdate",
					"201606coincx.enddate", fiBcoinBalance, "1", "0.5");
			if ("-1".equals(jsonStr)) {
				// 11月 重消单开启积分换购可用积分试算 GlobalVar.jpocoincxList201611
				// 为不参加积分换购的特殊商品
				jsonStr = PromotionsUtils.checkCoincxPay(jpoMemberOrder, GlobalVar.jpocoincxList201611,
						"201611coincx.startdate", "201611coincx.enddate", fiBcoinBalance, "0.5");
			}
			if (!"-1".equals(jsonStr)) {// 验证通过 通过返回值得到可使用积分
				resultMap = MeteorUtil.parseJSON2MapString(jsonStr);
				if (null != resultMap) {
					sumCoin = new BigDecimal(resultMap.get("sumCoin"));
				}
			}
			//道和国韵（黑瓶装）
			jsonStr = PromotionsUtils.checkJfPay(jpoMemberOrder, GlobalVar.jpoList201805, "201805coincx.startdate",
					"201805coincx.enddate", fiBcoinBalance, "1", "0.5");
			if ("-1".equals(jsonStr)) {
				// 11月 重消单开启积分换购可用积分试算 GlobalVar.jpocoincxList201611
				// 为不参加积分换购的特殊商品
				jsonStr = PromotionsUtils.checkCoincxPay(jpoMemberOrder, GlobalVar.jpocoincxList201611,
						"201611coincx.startdate", "201611coincx.enddate", fiBcoinBalance, "0.5");
			}
			if (!"-1".equals(jsonStr)) {// 验证通过 通过返回值得到可使用积分
				resultMap = MeteorUtil.parseJSON2MapString(jsonStr);
				if (null != resultMap) {
					sumCoin = new BigDecimal(resultMap.get("sumCoin"));
				}
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.info("支付跳转失败!", e);
			return "0";
		}

		// ============================2016-05-25
		// 2016年6月积分换购需求开发================================================end

		// 试算结果
		if (sumCoin.compareTo(new BigDecimal("0")) == 1 || sumCoin.compareTo(new BigDecimal("0")) == 0) {

			// String msg = "您本张订单总金额" + jpoMemberOrder.getAmount() + "，使用金额" +
			// jpoMemberOrder.getAmount().subtract(sumCoin) + "，使用积分" + sumCoin
			// + "，PV为0";
			String msg = jpoMemberOrder.getAmount().subtract(sumCoin) + "," + sumCoin;

			return msg;
		}

		return "0";
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
		String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct()
				.getProductNo();

		if ("P24010100101CN0".equals(productNo)) {

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
		String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct()
				.getProductNo();
		String teamId = jpoMemberOrder.getTeamCode();
		if ("P01170100101CN0".equals(productNo) || "P08420300201CN0".equals(productNo)
				|| "P1508010101CN0".equals(productNo) || "P01290100201CN0".equals(productNo)) {
			if (null != teamId && !"".equals(teamId)) {
				if (!"CN16481747".equals(teamId) && !"CN18728599".equals(teamId) && !"CN30768473".equals(teamId)
						&& !"CN12365064".equals(teamId)) {
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
		String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct()
				.getProductNo();
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
		String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct()
				.getProductNo();
		String teamId = jpoMemberOrder.getTeamCode();
		if ("P25040200201CN0".equals(productNo) || "P25040200301CN0".equals(productNo)
				|| "P25040100201CN0".equals(productNo)) // ||
														// "P25040100301CN0".equals(productNo))
		{
			Iterator it = jpoMemberOrder.getJpoMemberOrderList().iterator();
			while (it.hasNext()) {
				JpoMemberOrderList orderList = (JpoMemberOrderList) it.next();
				if (jpoMemberOrderManager.getIsOver3(productNo, orderList.getQty()) == 0) {
					return false;
				}
			}
			if (null != teamId && !"".equals(teamId)) {
				if (!"CN16481747".equals(teamId) && !"CN18728599".equals(teamId) && !"CN30768473".equals(teamId)
						&& !"CN12365064".equals(teamId)) {
					return true;
				}
			}
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
		String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct()
				.getProductNo();

		if ("P03190100102CN0".equals(productNo) || "P03200100102CN0".equals(productNo)) {

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
			String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct()
					.getProductNo();

			if (("P23030100101CN0").equals(productNo) || ("P23030200101CN0").equals(productNo)) {

				flag = true;// 含有月饼促销产品
				break;
			}
		}

		if (flag == true) {

			while (its1.hasNext()) {
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
				String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct()
						.getProductNo();

				if (!("P23030100101CN0").equals(productNo) && !("P23030200101CN0").equals(productNo)) {

					flag = false;
					break;
				}
			}
		}

		return flag;
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
	 * 限制订购数量：产品编号P25090100201CN0,P25090100301CN0,P25100100101CN0,
	 * P25100100201CN0,P25100100301CN0,P25110100101CN0,P25110100201CN0
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	public boolean checkNumProductNo(JpoMemberOrder jpoMemberOrder) {

		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
		String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct()
				.getProductNo();

		if ("P25090100201CN0".equals(productNo) || "P25090100301CN0".equals(productNo)
				|| "P25100100101CN0".equals(productNo) || "P25100100201CN0".equals(productNo)
				|| "P25100100301CN0".equals(productNo) || "P25110100101CN0".equals(productNo)
				|| "P25110100201CN0".equals(productNo) || "P25090100101CN0".equals(productNo)) {

			return true;
		}
		return false;
	}

	/**
	 * 6财月促销商品检测
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	public boolean checkCX6(JpoMemberOrder jpoMemberOrder) {
		// 不参加团队：田阳2、5 TT2：CN16481747, TT5；CN18728599
		if (("CN16481747").equals(jpoMemberOrder.getTeamCode())
				|| ("CN18728599").equals(jpoMemberOrder.getTeamCode())) {

			return false;
		}

		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct()
					.getProductNo();

			// YGD积分换购：YGD：CN12365064 ,不参加产品：P1604010101CN0颐丽超音波熏香灯芯头
			if (("CN12365064").equals(jpoMemberOrder.getTeamCode())) {

				if ("P1604010101CN0".equals(productNo)) {

					return false;
				}
			}

			// 不参加产品：P03210100101CN0颐佳漱爽露便携装
			// ,P01050200101CN0中脉有乐生命活能饮（旅行装）,P01170400101CN0Cellight海洋单细胞海藻胶囊（5瓶装）//Modify
			// By WuCF 20150216 添加P04060100101CN0
			if ("P03210100101CN0".equals(productNo) || "P01050200101CN0".equals(productNo)
					|| "P01170400101CN0".equals(productNo) || "P04060100101CN0".equals(productNo)) {

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
			if ("P04010212001CN0".equals(productNo) || "P04010119001CN0".equals(productNo)
					|| "P04010118001CN0".equals(productNo) || "P04010115001CN0".equals(productNo)
					|| "P04010300101CN0".equals(productNo)) {
				FiBcoinPayconfigDetail fiBcoinPayconfigDetail = fiBcoinPayconfigDetailManager
						.getFiBcoinPayconfigDetailByProductId(productNo);
				if (fiBcoinPayconfigDetail != null) {

					if (fiBcoinPayconfigDetail.getNowQuantity() == 0
							|| fiBcoinPayconfigDetail.getNowQuantity() < buyNum) {
						return false;
					}
				} else {
					return false;
				}
			}
		}
		return true;
	}
}
