package com.joymain.ng.service.impl;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ibmmq.JpoMemberOrderCheckModel;
import com.joymain.ng.Constants;
import com.joymain.ng.dao.JmiMemberDao;
import com.joymain.ng.dao.JmiMemberUpgradeNoteDao;
import com.joymain.ng.dao.JpmProductSaleNewDao;
import com.joymain.ng.dao.JpoBankBookPayListDao;
import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.dao.JpoMemberOrderFeeDao;
import com.joymain.ng.dao.JpoMemberOrderListDao;
import com.joymain.ng.dao.JpoMovieDao;
import com.joymain.ng.dao.JsysUserDao;
import com.joymain.ng.handle.GlobalVar;
import com.joymain.ng.model.FiBcoinBalance;
import com.joymain.ng.model.JalCompany;
import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.model.JbdSummaryList;
import com.joymain.ng.model.JbdUserValidList;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiMemberUpgradeNote;
import com.joymain.ng.model.JmiStore;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JpmSalePromoter;
import com.joymain.ng.model.JpmSalepromoterPro;
import com.joymain.ng.model.JpoBankBookPayList;
import com.joymain.ng.model.JpoCheckedFailed;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderFee;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JpoMovie;
import com.joymain.ng.model.JpoShoppingCartOrder;
import com.joymain.ng.model.JsysListValue;
import com.joymain.ng.model.JsysRole;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.JsysUserRole;
import com.joymain.ng.service.FiBankbookJournalManager;
import com.joymain.ng.service.FiBcoinBalanceManager;
import com.joymain.ng.service.FiBcoinJournalManager;
import com.joymain.ng.service.FiCoinLogManager;
import com.joymain.ng.service.FiProductPointJournalManager;
import com.joymain.ng.service.JalCompanyManager;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.service.JbdSummaryListManager;
import com.joymain.ng.service.JbdUserCardListManager;
import com.joymain.ng.service.JfiBankbookJournalManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiStoreManager;
import com.joymain.ng.service.JmiTeamManager;
import com.joymain.ng.service.JpmCardSeqManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JpmSalePromoterManager;
import com.joymain.ng.service.JpoCheckedFailedManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JpoShoppingCartOrderManager;
import com.joymain.ng.service.JsysIdManager;
import com.joymain.ng.service.JsysListValueManager;
import com.joymain.ng.service.JsysRoleManager;
import com.joymain.ng.service.JsysUserRoleManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.DateUtil;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.data.CommonRecord;

@Service("jpoMemberOrderManager")
@SuppressWarnings( { "unused", "unchecked","rawtypes" })
@WebService(serviceName = "JpoMemberOrderService", endpointInterface = "com.joymain.ng.service.JpoMemberOrderManager")
public class JpoMemberOrderManagerImpl extends GenericManagerImpl<JpoMemberOrder, Long> implements JpoMemberOrderManager {

	@Autowired
	private JpoMemberOrderListDao jpoMemberOrderListDao;
	@Autowired
	private JpoMemberOrderFeeDao jpoMemberOrderFeeDao;
	@Autowired
	private JfiBankbookJournalManager jfiBankbookJournalManager;
	@Autowired
	private JsysUserDao jsysUserDao;
	@Autowired
	private JmiMemberDao jmiMemberDao;
	@Autowired
	private JmiMemberUpgradeNoteDao jmiMemberUpgradeNoteDao;
	@Autowired
	private JsysRoleManager sysRoleManager;
	@Autowired
	private JsysUserRoleManager sysUserRoleManager;
	@Autowired
	private JsysListValueManager sysListValueManager;
	@Autowired
	private JbdUserCardListManager jbdUserCardListManager;
	@Autowired
	private JpmCardSeqManager jpmCardSeqManager;
	@Autowired
	private JbdPeriodManager jbdPeriodManager;
	@Autowired
	private JmiStoreManager jmiStoreManager;
	@Autowired
	private FiCoinLogManager fiCoinLogManager;
	@Autowired
	private JpmProductSaleNewManager jpmProductSaleManager;
	@Autowired
	private JbdSummaryListManager jbdSummaryListManager;
	@Autowired
	private JalCompanyManager alCompanyManager;
	@Autowired
	private JalStateProvinceManager alStateProvinceManager;
	@Autowired
	private FiBcoinBalanceManager fiBcoinBalanceManager = null;
	@Autowired
	private FiBcoinJournalManager fiBcoinJournalManager = null;
	@Autowired
	private FiBankbookJournalManager fiBankbookJournalManager = null;
	@Autowired
	private JsysIdManager sysIdManager = null;

	private static final String fileName = "order_check.txt";
	@Autowired
	private JpmSalePromoterManager jpmSalePromoterManager;
	@Autowired
	private JmiMemberManager jmiMemberManager;
	@Autowired
	private JpoShoppingCartOrderManager jpoShoppingCartOrderManager;
	@Autowired
	private JmiTeamManager jmiTeamManager;

	@Autowired
	private JpoMovieDao jpoMovieDao;

	@Autowired
	private JpoCheckedFailedManager checkedFailedManager;
	
	@Autowired
	private JpmProductSaleNewDao jpmProductSaleNewDao;// Add By WuCF 20140312
	/* 支付改造
	@Autowired
	private MessageProducer messageProducer;
	*/
	private JpoMemberOrderDao jpoMemberOrderDao;
	@Autowired
	private JpoBankBookPayListDao jpoBankBookPayListDao;
	
	@Autowired
	private FiProductPointJournalManager fiProductPointJournalManager;
	
	@Autowired
    protected JsysUserRoleManager jsysUserRoleManager;
	
    @Autowired
    protected JsysRoleManager jsysRoleManager;
	
	@Autowired
	public JpoMemberOrderManagerImpl(JpoMemberOrderDao jpoMemberOrderDao) {
		super(jpoMemberOrderDao);
		this.jpoMemberOrderDao = jpoMemberOrderDao;
	}

	/**
	 * 时间段内获取商品订购量
	 * 
	 * @param productNo
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public int getProductsSum(String productNo, String startTime, String endTime, String payBy) {
		return jpoMemberOrderDao.getProductsSum(productNo, startTime, endTime, payBy);

	}

	/**
	 * 抢购时间：2010年5月11日-5月16日 剩下多少个
	 * 
	 * @param orderProductMax
	 * @return
	 */
	public int getProductsLeave(String productNo) {
		if ("P08140100101CN0".equals(productNo) || "P08150100101CN0".equals(productNo)) {
			int orderProductMax = 500;
			java.util.Calendar startc = java.util.Calendar.getInstance();
			startc.set(2010, 4, 18, 00, 00, 00);
			java.util.Date startDate = startc.getTime();
			Date curDate = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String curDateStr = dateFormat.format(curDate);
			int sumOrderProduct = this.getProductsSum(productNo, curDateStr + " 00:00:00", curDateStr + " 23:59:59", "");
			if (sumOrderProduct < orderProductMax) {// 超过规定的限度
				return orderProductMax - sumOrderProduct;
			}
		}
		return -1;
	}

	/**
	 * 抢购时间：2010年5月11日-5月16日
	 * 
	 * @param productNo
	 * @return
	 */
	public boolean getIsOver(String productNo) {
		if ("P08140100101CN0".equals(productNo) || "P08150100101CN0".equals(productNo)) {
			int orderProductMax = 500;
			java.util.Calendar startc = java.util.Calendar.getInstance();
			startc.set(2010, 4, 18, 00, 00, 00);
			java.util.Date startDate = startc.getTime();
			Date curDate = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String curDateStr = dateFormat.format(curDate);
			if (curDate.after(startDate)) {// 时间不为促销期
				return true;
			}
			int sumOrderProduct = this.getProductsSum(productNo, curDateStr + " 00:00:00", curDateStr + " 23:59:59", "");
			if (sumOrderProduct >= orderProductMax) {// 超过规定的限度
				return true;
			}
		}
		return false;
	}

	/**
	 * 抢购时间：2012年4月21日-5月4日
	 * 
	 * @param productNo
	 * @return
	 */
	public int getIsOver2(String productNo) {
		java.util.Calendar startc = java.util.Calendar.getInstance();
		startc.set(2012, 3, 21, 00, 00, 00);
		java.util.Calendar endc = java.util.Calendar.getInstance();
		endc.set(2012, 4, 5, 00, 00, 00);
		java.util.Date startDate = startc.getTime();
		java.util.Date endDate = endc.getTime();
		Date curDate = new Date();
		if ((curDate.after(startDate)) && (curDate.before(endDate))) {
			if ("P04010118001CN0".equals(productNo)) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String curDateStr = dateFormat.format(curDate);
				int orderProductMax = Integer.parseInt(Constants.sysConfigMap.get("CN").get("over2.p04010118001cn0"));
				int sumOrderProduct = this.getProductsSum(productNo, curDateStr + " 00:00:00", curDateStr + " 23:59:59", "byCoin");
				if (sumOrderProduct >= orderProductMax) {// 超过规定的限度
					return 0;
				} else {
					return orderProductMax - sumOrderProduct;
				}
			}
			if ("P08420300101CN0".equals(productNo)) {
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String curDateStr = dateFormat.format(curDate);
				int orderProductMax = Integer.parseInt(Constants.sysConfigMap.get("CN").get("over2.p08420300101cn0"));
				;
				int sumOrderProduct = this.getProductsSum(productNo, curDateStr + " 00:00:00", curDateStr + " 23:59:59", "byCoin");
				if (sumOrderProduct >= orderProductMax) {// 超过规定的限度
					return 0;
				} else {
					return orderProductMax - sumOrderProduct;
				}
			}
		}
		return -1;
	}

	/**
	 * 积分换购抢购
	 * 
	 * @param productNo
	 * @return
	 */
	public int getIsOver3(String productNo, Integer num) {
		int payByCoin = Integer.parseInt(this.getConfigValue("CN", "paybycoin"));
		if (payByCoin == 1) {
			Map limitProduct = JpoMemberOrderManagerImpl.getListOptions("CN", "limit.product");
			if (limitProduct.get(productNo) != null) {
				String limitTimeTmp = this.getConfigValue("CN", "limit.time");
				if (!"0".equals(limitTimeTmp)) {
					String[] limitTime = limitTimeTmp.split(",");
					int sumOrderProduct = num + this.getProductsSum(productNo, limitTime[0], limitTime[1], "byCoin");
					log.info("product num is --------------------------------" + num + "------------------------------sum is " + sumOrderProduct);
					int orderProductMax = Integer.parseInt(limitProduct.get(productNo).toString());
					if (sumOrderProduct > orderProductMax) {// 超过规定的限度
						log.info("max is ----------------------------------------------------" + orderProductMax);
						return 0;
					} else {
						log.info("max - sum is ----------------------------------------------------" + (orderProductMax - sumOrderProduct));
						return 2;
					}
				} else {
					return 1;// 限购时间设成0则不检测商品限量
				}
			} else {
				return 1;// 商品列表找不到商品则不检测商品限量
			}
		} else {
			return 1;// 积分换购没启用则所有商品都可以买
		}
	}

	/**
	 * 批量修改订单
	 * 
	 * @param poMemberOrder
	 * @param poMemberOrderSet
	 */
	public List<String> editJpoMemberOrderBatch(List<JpoMemberOrder> jpoMemberOrders, List<Set> jpoMemberOrderSets, List<Set> jpoMemberOrderFeeSets,
			List<JpoShoppingCartOrder> scoList) {

		List<String> ls = new ArrayList<String>();
		for (int i = 0; i < jpoMemberOrders.size(); i++) {
			String memberNo = this.editJpoMemberOrder(jpoMemberOrders.get(i), jpoMemberOrderSets.get(i), jpoMemberOrderFeeSets.get(i), scoList.get(i));
			ls.add(memberNo);
		}
		return ls;
	}

	/**
	 * 修改订单
	 * 
	 * @param poMemberOrder
	 * @param poMemberOrderSet
	 */
	public String editJpoMemberOrder(JpoMemberOrder jpoMemberOrder, Set jpoMemberOrderSet, Set jpoMemberOrderFeeSet, JpoShoppingCartOrder sco) {

		if (StringUtil.isEmpty(jpoMemberOrder.getMemberOrderNo())) {
			String memberOrderNo = sysIdManager.buildIdStr("pono");// 生成订单编号
			jpoMemberOrder.setMemberOrderNo(memberOrderNo);
		}
		Set jpoMemberOrderListSet = jpoMemberOrder.getJpoMemberOrderList();
		Iterator its1 = jpoMemberOrderListSet.iterator();
		while (its1.hasNext()) {

			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			jpoMemberOrderListDao.remove(jpoMemberOrderList.getMolId());
		}
		jpoMemberOrder.getJpoMemberOrderList().clear();
		jpoMemberOrder.setJpoMemberOrderList(jpoMemberOrderSet);

		Set jpoMemberOrderFeeListSet = jpoMemberOrder.getJpoMemberOrderFee();
		Iterator its2 = jpoMemberOrderFeeListSet.iterator();
		while (its2.hasNext()) {
			JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) its2.next();
			jpoMemberOrderFeeDao.remove(jpoMemberOrderFee.getMofId());
		}
		jpoMemberOrder.getJpoMemberOrderFee().clear();
		jpoMemberOrder.setJpoMemberOrderFee(jpoMemberOrderFeeSet);

		if (!this.getCheckOrderAmount(jpoMemberOrder)) {
			throw new AppException("订单总金额不一致");
		}

		jpoMemberOrderDao.save(jpoMemberOrder);
		jpoShoppingCartOrderManager.remove(sco);// 确认订单成功，则删除购物车中的数据

		/*
		 * if("JP".equals(jpoMemberOrder.getCompanyCode())&&"1".equals(jpoMemberOrder
		 * .getShippingPay())){ if(jpoMemberOrder.getMoId()!=null){
		 * pdSendInfoManager.doWhileVoidOrder(jpoMemberOrder); }
		 * if("1".equals(jpoMemberOrder.getShippingPay())){
		 * pdSendInfoManager.doWhileOrderConfirmed(jpoMemberOrder); } }
		 */
		return jpoMemberOrder.getMemberOrderNo();
	}

	/**
	 * 判断订单明细与总金额是否一至
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	private boolean getCheckOrderAmount(JpoMemberOrder jpoMemberOrder) {

		BigDecimal sumPrice = new BigDecimal(0);
		BigDecimal sumPV = new BigDecimal(0);

		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
			sumPV = sumPV.add(jpoMemberOrderList.getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
		}

		log.info("list add sum:"+sumPrice+" sumpv:"+sumPV);
		
		Iterator its2 = jpoMemberOrder.getJpoMemberOrderFee().iterator();
		while (its2.hasNext()) {
			JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) its2.next();
			if (jpoMemberOrderFee.getFee() != null) {
				sumPrice = sumPrice.add(jpoMemberOrderFee.getFee());
			} else {
				jpoMemberOrderFee.setFee(new BigDecimal(0));
			}
		}
		log.info("fee add sum:"+sumPrice);
		/* 支付改造 bug:2876
		if (jpoMemberOrder.getDiscountAmount() != null) {
			sumPrice = sumPrice.subtract(jpoMemberOrder.getDiscountAmount());
		}
		
		if (jpoMemberOrder.getDiscountPvAmt() != null) {
			sumPV = sumPV.subtract(jpoMemberOrder.getDiscountPvAmt());
		}

		log.info("PV:"+sumPV);
		*/
//		if (jpoMemberOrder.getJjAmount() != null) {
//			sumPrice = sumPrice.subtract(jpoMemberOrder.getJjAmount());
//		}
		
		log.info("add sum:"+sumPrice+" add pv:"+sumPV +" "
				+ " order amount="+jpoMemberOrder.getAmount2() +" "
				+ " order PV is:"+jpoMemberOrder.getPvAmt());
		
		if (jpoMemberOrder.getAmount2().compareTo(sumPrice) == 0 && 
				sumPV.compareTo(jpoMemberOrder.getPvAmt()) == 0) {
			return true;
		}

		return false;
	}

	/**
	 * 电子存折支付会员订单
	 * Modify 2015-09-21 分解支付方法
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 * @param dataType
	 *            数据来源，2：手机，null或者1：PC
	 */
	public synchronized void checkJpoMemberOrder(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType) throws AppException {
		this.checkJpoMemberOrderByJfiBankbook(jpoMemberOrder, operatorSysUser, dataType);
	}
	
	/**
	 * 审核会员订单
	 * 
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 * @param dataType
	 *            数据来源，2：手机，null或者1：PC
	 */
	public synchronized void checkJpoMemberOrderTemp(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType) throws AppException {

		log.info("check Order start ,orderNo is:" + jpoMemberOrder.getMemberOrderNo());
		String limitcheckorder = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "limitcheckorder");
		int freeStatus = jpoMemberOrder.getSysUser().getJmiMember().getFreezeStatus();
		String order_Type = jpoMemberOrder.getOrderType();

		log.info("orderType is:" + order_Type + ",freeStatus is:" + freeStatus);

		if ((!order_Type.equals(Constants.AUTO_PURCHASE)) && freeStatus == Constants.FREEZE_STATUS_ONE) {
			throw new AppException("冻结会员只允许支付续约单!");
		}
		if ("1".equals(limitcheckorder)) {
			throw new AppException("系统更新,请您稍后在审单");
		}
		if (!this.getCheckOrderAmount(jpoMemberOrder)) {
			throw new AppException("订单总金额不一致");
		}
		if (!"0".equals(jpoMemberOrder.getLocked())) {
			throw new AppException("订单已锁订");
		}
		if ("2".equals(jpoMemberOrder.getStatus())) {
			throw new AppException("订单已审核");
		}
		if ("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getActive())) {
			throw new AppException(this.getLocalLanguageByKey("checkError.Code.member", operatorSysUser));
		}
		JsysUser sysUserSp = jsysUserDao.get(jpoMemberOrder.getUserSpCode());
		if (!sysUserSp.getCompanyCode().equals(jpoMemberOrder.getCompanyCode()) && !"1".equals(jpoMemberOrder.getCompanyPay())) {
			throw new AppException("扣款人与订单不同国别");
		}

		String oldCard = "";
		String newCard = "";
		Date logCurDate = new Date();
		try {

			BigDecimal sumPrice = new BigDecimal(0);
			BigDecimal sumPV = new BigDecimal(0);

			Iterator<JpoMemberOrderList> itsTmp1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
			while (itsTmp1.hasNext()) {
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) itsTmp1.next();
				sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
				sumPV = sumPV.add(jpoMemberOrderList.getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
			}
			Iterator itsTmp2 = jpoMemberOrder.getJpoMemberOrderFee().iterator();
			while (itsTmp2.hasNext()) {
				JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) itsTmp2.next();
				sumPrice = sumPrice.add(jpoMemberOrderFee.getFee());
			}

			Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
			if ("CN".equals(jpoMemberOrder.getCompanyCode())) {
				while (its1.hasNext()) {
					JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
					String status = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getStatus();
					if ("0".equals(status)) {
						throw new AppException("产品(" + jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo() + ")已售完!");
					}
				}
			}
			// Date date =new Date(); //数据时间jmiMemberDao.getDsDate();
			Date date = DateUtil.getNowTimeFromDateServer();
			jpoMemberOrder.setSubmitStatus("2");
			jpoMemberOrder.setSubmitTime(date);
			jpoMemberOrder.setSubmitUserCode(operatorSysUser.getUserCode());
			jpoMemberOrder.setStatus("2");
			jpoMemberOrder.setCheckTime(date);
			jpoMemberOrder.setCheckDate(date);
			jpoMemberOrder.setCheckUserCode(operatorSysUser.getUserCode());

			int orderType = Integer.parseInt(order_Type);

			switch (orderType) {
			case 1:// 会员首购
				int businessFId = this.getJpoMemberOrderBusinessMF(jpoMemberOrder);
				if (businessFId == 10) {
					logCurDate = new Date();
					if (jpoMemberOrder.getSysUser().getJmiMember().getNotFirst() != 0) {
						throw new AppException("会员不处于待审状态");
					}
					// String cardType = "";
					// if("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getMemberType())
					// &&
					// !"0".equals(jpoMemberOrder.getSysUser().getJmiMember().getOriCard())){
					// if(jpoMemberOrder.getPvAmt().compareTo(new
					// BigDecimal("70"))!=-1){
					// cardType =
					// jpoMemberOrder.getSysUser().getJmiMember().getOriCard();
					// }else{
					// throw new
					// AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser)
					// + "D会员首购单必须大于或等于70PV");
					// }
					// }else
					// if("2".equals(jpoMemberOrder.getSysUser().getJmiMember().getMemberType())){
					// BigDecimal pv_amt =
					// jpoMemberOrder.getPvAmt().add(jpoMemberOrder.getSysUser().getJmiMember().getOriPv());
					// cardType =
					// this.getCalcFOrderCardType(pv_amt,jpoMemberOrder.getSysUser().getCompanyCode());
					// }else
					// if("4".equals(jpoMemberOrder.getSysUser().getJmiMember().getMemberType())){
					// if("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getOriCard())){
					// if(sumPrice.compareTo(new BigDecimal("140"))!=-1){
					// cardType = "4";
					// }else{
					// throw new AppException("PV不足");
					// }
					// }else{
					// if(sumPrice.compareTo(new BigDecimal("70"))!=-1){
					// cardType = "4";
					// }else{
					// throw new AppException("PV不足");
					// }
					// }
					// }else{
					// cardType =
					// this.getCalcFOrderCardType(jpoMemberOrder.getPvAmt(),jpoMemberOrder.getSysUser().getCompanyCode());
					// }
					//			
					// if(Integer.parseInt(cardType) >
					// Integer.parseInt(jpoMemberOrder.getSysUser().getJmiMember().getCardType())){
					// //获取新旧卡别
					// logCurDate=new Date();
					// oldCard=jmiMemberDao.get(jpoMemberOrder.getSysUser().getUserCode()).getCardType();
					// newCard=cardType;
					//				
					// //升级记录
					// this.getSaveJmiMemberUpgradeNote(jpoMemberOrder,
					// cardType, operatorSysUser, "1");
					// }
					// 角色
					String roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "jocs.member.role.normal");
					/*
					 * if("6".equals(cardType)){ //VIP会员 Iterator ite =
					 * jpoMemberOrder.getJpoMemberOrderList().iterator();
					 * while(ite.hasNext()){ JpoMemberOrderList
					 * jpoMemberOrderList = (JpoMemberOrderList) ite.next();
					 * if("P13010200201CN0"
					 * .equals(jpoMemberOrderList.getJpmProductSale
					 * ().getJpmProduct().getProductNo())){ //买凳子送二级馆 roleId =
					 * "cn.member.62"; } } }
					 */
					logCurDate = new Date();
//					this.getChangeJmiMemberRole(jpoMemberOrder.getSysUser(), roleId);

				} else {
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessFId, operatorSysUser));
				}
				JmiMember jmiMember = jmiMemberDao.get(jpoMemberOrder.getSysUser().getUserCode());
				jmiMember.setCheckDate(jpoMemberOrder.getCheckDate());
				jmiMember.setCheckNo(jpoMemberOrder.getCheckUserCode());
				// ==============续约
				JbdPeriod bdPeriodF = jbdPeriodManager.getBdPeriodByTime(jpoMemberOrder.getCheckDate(), null);
				Integer startMonthF = bdPeriodF.getWyear() * 100 + bdPeriodF.getWmonth();

				String yearF = startMonthF.toString().substring(0, 4);
				String monthF = startMonthF.toString().substring(4, 6);
				String periodF = jbdPeriodManager.getFutureBdYearMonth(yearF, monthF, 12);

				jmiMember.setStartWeek(startMonthF);
				jmiMember.setValidWeek(Integer.parseInt(periodF));
				// ==============
				if ("2".equals(jpoMemberOrder.getSysUser().getJmiMember().getMemberType())) {
					jmiMember.setOriPv(new BigDecimal("0"));
				}

				// 新加字段，不能再下首单
				jmiMember.setNotFirst(1);
				this.jmiMemberDao.save(jmiMember);
				break;
			case 2:// 会员升级
				int businessUId = this.getJpoMemberOrderBusinessMU(jpoMemberOrder);
				if (businessUId == 20) {

					String cardType = "";
					if ("0".equals(jpoMemberOrder.getIsSpecial())) {
						cardType = this.getCalcUOrderCardType(jpoMemberOrder);
					} else if ("1".equals(jpoMemberOrder.getIsSpecial())) {
						cardType = this.getCalcSpecialUOrderCardType(jpoMemberOrder);
					} else {
						cardType = this.getCalcUOrderCardType(jpoMemberOrder);
					}
					if ("".equals(cardType)) {
						throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser) + this.getLocalLanguageByKey("checkError.Code.21", operatorSysUser));// 升级单不足以升一级
					}
					// 获取新旧卡别
					oldCard = jmiMemberDao.get(jpoMemberOrder.getSysUser().getUserCode()).getCardType();
					newCard = cardType;
					// 升级记录
					this.getSaveJmiMemberUpgradeNote(jpoMemberOrder, cardType, operatorSysUser, "2");
					// 角色
					JmiStore jmiStore = jmiStoreManager.getJmiStoreByUserCode(jpoMemberOrder.getSysUser().getUserCode());
					String roleId;
					if ("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getIsstore())) {
						if ("6".equals(newCard)) {
							roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "member_role_id.store.vip");
						} else {
							roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "member_role_id.store");
						}
					} else if ("2".equals(jpoMemberOrder.getSysUser().getJmiMember().getIsstore())) {
						if ("6".equals(newCard)) {
							roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "member_role_id.store2.vip");
						} else {
							roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "member_role_id.store2");
						}
					} else if ("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getSubStoreStatus())) {
						// 二级已审
						roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "member_role_id.store1");
					} else if (jmiStore != null && "1".equals(jmiStore.getCheckStatus())) {
						// 一级已审
						roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "member_role_id.store0");
					} else {
						roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "member_role_id." + cardType);
					}

					this.getChangeJmiMemberRole(jpoMemberOrder.getSysUser(), roleId);
				} else {
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessUId, operatorSysUser));
				}
				break;
			case 3:// 会员续约
				int businessRSId = this.getJpoMemberOrderBusinessMRS(jpoMemberOrder);
				if (businessRSId == 30) {

					if (jpoMemberOrder.getSysUser().getJmiMember().getFreezeStatus() == 0) {
						throw new AppException("会员处于解冻状态！");
					} else if (jpoMemberOrder.getSysUser().getJmiMember().getFreezeStatus() == 1) {
						// 冻，126PV订单
						// ==============续约当年

						/*
						 * String years =
						 * jpoMemberOrder.getSysUser().getJmiMember
						 * ().getValidWeek().toString().substring(0, 4); String
						 * months =
						 * jpoMemberOrder.getSysUser().getJmiMember().getValidWeek
						 * ().toString().substring(4, 6); String periodS =
						 * jbdPeriodManager.getFutureBdYearMonth(years, months,
						 * 2); String yeare = periodS.substring(0, 4); String
						 * monthe = periodS.substring(4, 6); String periodE =
						 * jbdPeriodManager.getFutureBdYearMonth(yeare, monthe,
						 * 13);
						 */

						JbdPeriod bdPeriod = jbdPeriodManager.getBdPeriodByTime(new Date(), null);
						String periodS = jbdPeriodManager.getFutureBdYearMonth(bdPeriod.getWyear().toString(), bdPeriod.getWmonth().toString(), 1);
						String periodE = jbdPeriodManager.getFutureBdYearMonth(bdPeriod.getWyear().toString(), bdPeriod.getWmonth().toString(), 12);

						jpoMemberOrder.getSysUser().getJmiMember().setStartWeek(Integer.parseInt(periodS));
						jpoMemberOrder.getSysUser().getJmiMember().setValidWeek(Integer.parseInt(periodE));

						JbdPeriod bdPeriodSR = jbdPeriodManager.getBdPeriodByTime(jpoMemberOrder.getCheckDate(), null);
						Integer startMonthSR = bdPeriodSR.getWyear() * 100 + bdPeriodSR.getWmonth();

						if (startMonthSR <= Integer.parseInt(periodE)) {
							// 当前期别小于会员的冻结期别解冻
							jpoMemberOrder.getSysUser().getJmiMember().setFreezeStatus(0);
							JbdUserValidList jbdUserValidList = new JbdUserValidList();
							jbdUserValidList.setJmiMember(jpoMemberOrder.getSysUser().getJmiMember());
							jbdUserValidList.setNewFreezeStatus(0);
							jbdUserValidList.setOldFreezeStatus(1);
							jbdUserValidList.setWweek(bdPeriodSR.getWyear() * 100 + bdPeriodSR.getWweek());
							Set<JbdUserValidList> jbdUserValidListSet = new HashSet<JbdUserValidList>(0);
							jbdUserValidListSet.add(jbdUserValidList);
							jpoMemberOrder.getSysUser().getJmiMember().setJbdUserValidList(jbdUserValidListSet);

							// ==============
							// 角色
							String roleId;
							JmiStore jmiStore = jmiStoreManager.getJmiStoreByUserCode(jpoMemberOrder.getSysUser().getUserCode());
							if ("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getIsstore())) {
								// if("6".equals(jpoMemberOrder.getSysUser().getJmiMember().getCardType())){
								// roleId =
								// this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(),
								// "member_role_id.store.vip");
								// }else{
								roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "jocs.member.role.store1");
								// }
							} else if ("2".equals(jpoMemberOrder.getSysUser().getJmiMember().getIsstore())) {
								// if("6".equals(jpoMemberOrder.getSysUser().getJmiMember().getCardType())){
								// roleId =
								// this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(),
								// "member_role_id.store2.vip");
								// }else{
								roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "jocs.member.role.store2");
								// }
							} else if ("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getSubStoreStatus())) {
								// 二级已审
								roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "jocs.member.role.store2.x");
							} else if (jmiStore != null && "1".equals(jmiStore.getCheckStatus())) {
								// 一级已审
								roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "jocs.member.role.store1.x");
							} else {
								
								//通过身份判断角色
								Integer greadType = jpoMemberOrder.getSysUser().getJmiMember().getGradeType();
								if(greadType == 3){
									roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_ROLE_NORMAL);
								}else if(greadType == 2 || greadType == 1){
									roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_ROLE5);
								}else{
									roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_ROLE0);
								}
								
//								roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "jocs.member.role.normal");
							}
							// String roleId =
							// this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(),
							// "jocs.member.role.normal");
							this.getChangeJmiMemberRole(jpoMemberOrder.getSysUser(), roleId);
						}
					} else {
						throw new AppException("冻结状态不明确");
					}
				} else {
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessRSId, operatorSysUser));
				}
				break;
			case 4:// 会员重销
				int businessRId = this.getJpoMemberOrderBusinessMR(jpoMemberOrder);
				if (businessRId == 40) {

				} else {
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessRId, operatorSysUser));
				}
				break;
			case 5:// 辅销品订单
				int businessAId = this.getJpoMemberOrderBusinessMA(jpoMemberOrder);
				if (businessAId == 40) {

				} else {
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessAId, operatorSysUser));
				}
				break;
			case 6:// 店铺首购
				int businessSFId = this.getJpoMemberOrderBusinessSF(jpoMemberOrder);
				if (businessSFId == 60) {

					BigDecimal amount = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.f.order.amount"));
					BigDecimal pvAmt = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.f.order.pvamt"));

					if ("LC".equals(jpoMemberOrder.getProductType())) {
						if (pvAmt.compareTo(jpoMemberOrder.getPvAmt()) != 1) {

						} else {
							throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser) + "订购金额不正确");
						}
					} else {
						if (amount.compareTo(sumPrice) != 1) {

						} else {
							throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser) + "订购金额不正确");
						}
					}
					// 角色
					String roleId;
					// if("6".equals(jpoMemberOrder.getSysUser().getJmiMember().getCardType())){
					// roleId =
					// this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(),
					// "member_role_id.store.vip");
					// }else{
					// roleId =
					// this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(),
					// "member_role_id.store");
					// }

					roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "jocs.member.role.store1"); // 正式一级店铺
					this.getChangeJmiMemberRole(jpoMemberOrder.getSysUser(), roleId);
					jpoMemberOrder.getSysUser().getJmiMember().setIsstore("1");
					jpoMemberOrder.getSysUser().getJmiMember().setRecommendStore(jpoMemberOrder.getSysUser().getJmiMember().getRecommendNo());
				} else {
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessSFId, operatorSysUser));
				}
				break;
			case 7:// 店铺升级单
				break;
			case 8:// 店铺返单
				break;
			case 9:// 店铺重销
				int businessSRId = this.getJpoMemberOrderBusinessSR(jpoMemberOrder);
				if (businessSRId == 90) {

				} else {
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessSRId, operatorSysUser));
				}
				break;
			case 11:// 二级馆首购单
				int businessSSubFId = this.getJpoMemberOrderBusinessSSubF(jpoMemberOrder);
				if (businessSSubFId == 60) {
					String language = jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase();

					BigDecimal pvamt = new BigDecimal(Constants.sysConfigMap.get(language).get("store.f2.order.pvamt"));
					BigDecimal amount = new BigDecimal(Constants.sysConfigMap.get(language).get("store.f2.order.amount"));

					// JsysUser user = jpoMemberOrder.getSysUser();

					// String temCode = jmiTeamManager.teamStr(user);
					// if(temCode.equalsIgnoreCase("CN12365064")){
					// amount= new BigDecimal(Constants.sysConfigMap.
					// get(language).get("store.f2.order.amount_ygd"));
					// }

					if ("LC".equals(jpoMemberOrder.getProductType()) && pvamt.compareTo(jpoMemberOrder.getPvAmt()) != 1) {

					} else if (!"LC".equals(jpoMemberOrder.getProductType())) {
						if (amount.compareTo(sumPrice) == 1) {
							throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser) + "订购金额不正确");
						}
						if ("HK".equals(jpoMemberOrder.getCompanyCode())) {
							BigDecimal pv = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.f2.order.pv"));
							if (pv.compareTo(jpoMemberOrder.getPvAmt()) == 1) {
								throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser) + "订购PV不正确");
							}
						}
					} else {
						throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser) + "订购金额不正确");
					}
					// 角色
					String roleId;
					roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "jocs.member.role.store2");
					this.getChangeJmiMemberRole(jpoMemberOrder.getSysUser(), roleId);
					jpoMemberOrder.getSysUser().getJmiMember().setIsstore("2");
					jpoMemberOrder.getSysUser().getJmiMember().setSubStoreStatus("2");
					jpoMemberOrder.getSysUser().getJmiMember().setSubStoreCheckDate(jpoMemberOrder.getCheckDate());
				} else {
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessSSubFId, operatorSysUser));
				}
				break;
			case 12:// 二级馆升级单
				int businessSSubUId = this.getJpoMemberOrderBusinessSSubU(jpoMemberOrder);
				if (businessSSubUId == 90) {
					BigDecimal pv = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.u2.order.pv"));
					BigDecimal amount = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.u2.order.amount"));

					if ("LC".equals(jpoMemberOrder.getProductType())) {
						pv = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.u2.orderlc.pv"));
						amount = new BigDecimal("0");
					}

					java.util.Calendar startc = java.util.Calendar.getInstance();
					startc.set(2011, 6, 16, 00, 00, 00);
					java.util.Calendar endc = java.util.Calendar.getInstance();
					endc.set(2011, 7, 6, 00, 00, 00);
					java.util.Date startDate = startc.getTime();
					java.util.Date endDate = endc.getTime();
					Date curDate = new Date();
					if ((curDate.after(startDate)) && (curDate.before(endDate))) {
						Iterator its11 = jpoMemberOrder.getJpoMemberOrderList().iterator();
						while (its11.hasNext()) {
							JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its11.next();
							if ("P13010200201CN0".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())) {
								pv = new BigDecimal("0");
							}
						}
					}

					if (pv.compareTo(jpoMemberOrder.getPvAmt()) < 1) {
						if (amount.compareTo(sumPrice) < 1) {
							// 角色.
							String roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "jocs.member.role.store1");
							this.getChangeJmiMemberRole(jpoMemberOrder.getSysUser(), roleId);
							jpoMemberOrder.getSysUser().getJmiMember().setIsstore("1");
						} else {
							throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser) + "订购金额不正确");
						}
					} else {
						throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser) + "订购金额不正确");
					}
					JmiStore jmiStore = jmiStoreManager.getJmiStoreByUserCode(jpoMemberOrder.getSysUser().getUserCode());
					if (jmiStore != null) {
						jmiStore.setOrderTime(jpoMemberOrder.getCheckTime());
						jmiStore.setOrderDate(jpoMemberOrder.getCheckDate());
						jmiStoreManager.save(jmiStore);
					}
					jpoMemberOrder.getSysUser().getJmiMember().setRecommendStore(jpoMemberOrder.getSysUser().getJmiMember().getRecommendNo());
				} else {
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessSSubUId, operatorSysUser));
				}
				break;
			case 13:
				break;
			case 14:// 二级馆重销单
				int businessSSubRId = this.getJpoMemberOrderBusinessSSubR(jpoMemberOrder);
				if (businessSSubRId == 90) {

				} else {
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessSSubRId, operatorSysUser));
				}
				break;
			case 15:// 会员AUTOSHIP
				int businessASId = this.getJpoMemberOrderBusinessAS(jpoMemberOrder);
				if (businessASId == 40) {

				} else {
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessASId, operatorSysUser));
				}
				break;
			case 21:// 活力小铺首单
				int businessMFId = this.getJpoMemberOrderBusinessSMF(jpoMemberOrder);
				if (businessMFId == 90) {
					BigDecimal amount = new BigDecimal(this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "store.m.order.amount"));
					BigDecimal pv = new BigDecimal(this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "store.m.order.pv"));
					if (amount.compareTo(sumPrice) == 1) {
						throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser) + "订购金额不正确");
					}
					if (pv.compareTo(jpoMemberOrder.getPvAmt()) == 1) {
						throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser) + "订购PV不正确");
					}
				} else {
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessMFId, operatorSysUser));
				}
				break;
			case 24:// 活力小铺重销单
				int businessMRId = this.getJpoMemberOrderBusinessSMR(jpoMemberOrder);
				if (businessMRId == 90) {

				} else {
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessMRId, operatorSysUser));
				}
				break;
			}
			logCurDate = new Date();

			if ("4".equals(jpoMemberOrder.getOrderType()) || "9".equals(jpoMemberOrder.getOrderType()) || "14".equals(jpoMemberOrder.getOrderType())) {
				// 不冻，35PV订单
				String yearsMember = jpoMemberOrder.getSysUser().getJmiMember().getStartWeek().toString().substring(0, 4);
				String monthsMember = jpoMemberOrder.getSysUser().getJmiMember().getStartWeek().toString().substring(4, 6);
				List periodsMember = jbdPeriodManager.getBdPeriodsByMonth(yearsMember, monthsMember);
				JbdPeriod bdPeriod = (JbdPeriod) periodsMember.get(0);
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				CommonRecord crm = new CommonRecord();
				crm.addField("stauts", "2");
				crm.addField("userCode", jpoMemberOrder.getSysUser().getUserCode());
				crm.addField("orderType", "4,9,14");
				crm.addField("startLogTime", dateFormat.format(bdPeriod.getStartTime()));
				BigDecimal pvAmt = jpoMemberOrderDao.getJpoMemberOrderStatics(crm);
				pvAmt = pvAmt.add(jpoMemberOrder.getPvAmt());

				// 时间限制
				java.util.Calendar startcPre = java.util.Calendar.getInstance();
				startcPre.set(2012, 8, 29, 00, 00, 00);
				java.util.Date startDatePre = startcPre.getTime();
				Date curDatePre = new Date();
				if ("CN".equals(jpoMemberOrder.getCompanyCode())) {
					if (curDatePre.after(startDatePre) && new BigDecimal("42").compareTo(pvAmt) != 1) {
						JbdPeriod bdPeriodSR = jbdPeriodManager.getBdPeriodByTime(jpoMemberOrder.getCheckDate(), null);
						Integer startMonthSR = bdPeriodSR.getWyear() * 100 + bdPeriodSR.getWmonth();
						if (jpoMemberOrder.getSysUser().getJmiMember().getStartWeek() <= startMonthSR) {
							if (!StringUtil.isEmpty(jpoMemberOrder.getSysUser().getJmiMember().getValidWeek().toString())) {
								String years = jpoMemberOrder.getSysUser().getJmiMember().getValidWeek().toString().substring(0, 4);
								String months = jpoMemberOrder.getSysUser().getJmiMember().getValidWeek().toString().substring(4, 6);
								String periodS = jbdPeriodManager.getFutureBdYearMonth(years, months, 2);
								String yeare = periodS.substring(0, 4);
								String monthe = periodS.substring(4, 6);
								String periodE = jbdPeriodManager.getFutureBdYearMonth(yeare, monthe, 12);
								jpoMemberOrder.getSysUser().getJmiMember().setStartWeek(Integer.parseInt(periodS));
								jpoMemberOrder.getSysUser().getJmiMember().setValidWeek(Integer.parseInt(periodE));
							} else {
								throw new AppException("ValidWeek为空！");
							}
						}
					} else if (curDatePre.before(startDatePre) && new BigDecimal("21").compareTo(pvAmt) != 1) {
						JbdPeriod bdPeriodSR = jbdPeriodManager.getBdPeriodByTime(jpoMemberOrder.getCheckDate(), null);
						Integer startMonthSR = bdPeriodSR.getWyear() * 100 + bdPeriodSR.getWmonth();
						if (jpoMemberOrder.getSysUser().getJmiMember().getStartWeek() <= startMonthSR) {
							if (!StringUtil.isEmpty(jpoMemberOrder.getSysUser().getJmiMember().getValidWeek().toString())) {
								String years = jpoMemberOrder.getSysUser().getJmiMember().getValidWeek().toString().substring(0, 4);
								String months = jpoMemberOrder.getSysUser().getJmiMember().getValidWeek().toString().substring(4, 6);
								String periodS = jbdPeriodManager.getFutureBdYearMonth(years, months, 2);
								String yeare = periodS.substring(0, 4);
								String monthe = periodS.substring(4, 6);
								String periodE = jbdPeriodManager.getFutureBdYearMonth(yeare, monthe, 12);
								jpoMemberOrder.getSysUser().getJmiMember().setStartWeek(Integer.parseInt(periodS));
								jpoMemberOrder.getSysUser().getJmiMember().setValidWeek(Integer.parseInt(periodE));
							} else {
								throw new AppException("ValidWeek为空！");
							}
						}
					}
				} else if (!"CN".equals(jpoMemberOrder.getCompanyCode()) && new BigDecimal("21").compareTo(pvAmt) != 1) {
					JbdPeriod bdPeriodSR = jbdPeriodManager.getBdPeriodByTime(jpoMemberOrder.getCheckDate(), null);
					Integer startMonthSR = bdPeriodSR.getWyear() * 100 + bdPeriodSR.getWmonth();
					if (jpoMemberOrder.getSysUser().getJmiMember().getStartWeek() <= startMonthSR) {
						if (!StringUtil.isEmpty(jpoMemberOrder.getSysUser().getJmiMember().getValidWeek().toString())) {
							String years = jpoMemberOrder.getSysUser().getJmiMember().getValidWeek().toString().substring(0, 4);
							String months = jpoMemberOrder.getSysUser().getJmiMember().getValidWeek().toString().substring(4, 6);
							String periodS = jbdPeriodManager.getFutureBdYearMonth(years, months, 2);
							String yeare = periodS.substring(0, 4);
							String monthe = periodS.substring(4, 6);
							String periodE = jbdPeriodManager.getFutureBdYearMonth(yeare, monthe, 12);
							jpoMemberOrder.getSysUser().getJmiMember().setStartWeek(Integer.parseInt(periodS));
							jpoMemberOrder.getSysUser().getJmiMember().setValidWeek(Integer.parseInt(periodE));
						} else {
							throw new AppException("ValidWeek为空！");
						}
					}
				}
				// ==============续约次年
			}
		} catch (Exception e) {
			log.error("", e);
			throw new AppException("订单验证错误！");
		}
		// TODO Jun 促销策略
		log.info("loginUser is:[" + jpoMemberOrder.getSysUser().getUserCode() + "] " + "and user oldLever is:[" + jpoMemberOrder.getSysUser().getJmiMember().getMemberLevel() + "] "
				+ "and orderNo is:" + jpoMemberOrder.getMemberOrderNo());

		/*
		 * Date cur_date = Calendar.getInstance().getTime(); 正式环境未做时间同步, 才如此获取
		 */
		Date cur_date = DateUtil.getNowTimeFromDateServer();
		String stime = DateUtil.getDate(cur_date, "yyyy-MM-dd HH:mm:ss");

		List<JpmSalePromoter> spList = jpmSalePromoterManager.getSaleByDate(stime, Constants.JPMSALE_ACTIVA);

		log.info("curdate is:[" + stime + "] jpmSalePromoter list size is:" + "[" + spList.size() + "]and user newLever is:"
				+ jpoMemberOrder.getSysUser().getJmiMember().getCardType());
		try {
			for (JpmSalePromoter sp : spList) {
				if (isOrderSales(sp, jpoMemberOrder)) {// 判断团队
					// 策略类型:1折价促销, 2赠品促销,3积分促销,4订单总金额或PV
					String saleType = sp.getSaleType();
					if (saleType.equals(Constants.JPMSALE_SALETYPE_PRE)) {
						if (isOrderType(sp.getUserlevel(), jpoMemberOrder.getSysUser().getJmiMember().getMemberLevel().toString())) {
							jpoMemberOrder.getJpoMemberOrderList().addAll(bindPresentProduct(jpoMemberOrder, sp));
						}
					} else if (saleType.equals(Constants.JPMSALE_SALETYPE_INTEGRAL)) {// 积分赠送

						if ("1".equals(jpoMemberOrder.getPayByCoin()) && null != jpoMemberOrder.getDiscountAmount()) {
							// 积分支付的单pv为0
							jpoMemberOrder.setPvAmt(new BigDecimal(0));
						} else {
							// 非积分支付的单才参与送积分
							if (isOrderType(sp.getUserlevel(), newCard)) {

								// 赠送积分促销策略
								getBindIntegral(jpoMemberOrder, sp, "0", operatorSysUser, dataType);
								// 是否赠送推荐人0否, 1送
								String ispresent = sp.getIspresent();

								JsysUser ruser = jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getRecommendJmiMember().getSysUser();

								if (StringUtils.isNotBlank(ispresent) && ispresent.equals("1") && ruser != null && ruser.getUserCode() != null) {
									getBindIntegral(jpoMemberOrder, sp, "1", operatorSysUser, dataType);
								}
							}
						}

					} else if (saleType.equals(Constants.JPMSALE_SALETYPE_ORDER)) {

						log.info("newCard is:" + newCard + " and orderCard " + jpoMemberOrder.getSysUser().getJmiMember().getMemberLevel().toString());

						if (isOrderType(sp.getUserlevel(), jpoMemberOrder.getSysUser().getJmiMember().getMemberLevel().toString())) {
							// 按订单总金额,订单类型或PV送赠品
							Set<JpoMemberOrderList> orderSet = new HashSet<JpoMemberOrderList>();
							Set<JpoMemberOrderList> orderSetBind = getOrderAmountBindProduct(jpoMemberOrder, sp);
							if (orderSetBind != null) {
								orderSet = orderSetBind;
							}
							jpoMemberOrder.getJpoMemberOrderList().addAll(orderSet);
						}
					}
				}
			}
		} catch (AppException e) {
			log.error("e1: sale promoter failed," + "orderNo is:" + jpoMemberOrder.getMemberOrderNo(), e);
			throw new AppException("e1: sale Promoter failed," + "orderNo is:" + jpoMemberOrder.getMemberOrderNo());
			// e.printStackTrace();
		} catch (Exception e) {
			log.error("e2: sale promoter failed," + "orderNo is:" + jpoMemberOrder.getMemberOrderNo(), e);
			throw new AppException("e2: sale Promoter failed," + "orderNo is:" + jpoMemberOrder.getMemberOrderNo());
			// e.printStackTrace();
		}

		if ("TW".equals(jpoMemberOrder.getCompanyCode())) {
			if (jpoMemberOrder.getPvAmt().compareTo(new BigDecimal(70)) != -1) {
				JpoMemberOrderList jpoMemberOrderList = new JpoMemberOrderList();
				JpmProductSaleTeamType jpmProductSale = jpmProductSaleManager.getJpmProductSaleTeamType("104261");
				jpoMemberOrderList.setJpmProductSaleTeamType(jpmProductSale);
				jpoMemberOrderList.setJpoMemberOrder(jpoMemberOrder);
				jpoMemberOrderList.setPrice(new BigDecimal("0"));
				jpoMemberOrderList.setPv(new BigDecimal("0"));
				BigDecimal qtyBig = jpoMemberOrder.getPvAmt().divide(new BigDecimal(70), BigDecimal.ROUND_DOWN);
				jpoMemberOrderList.setQty(qtyBig.intValue());
				jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().setVolume(jpmProductSale.getJpmProductSaleNew().getVolume());
				jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().setWeight(jpmProductSale.getJpmProductSaleNew().getWeight());
				jpoMemberOrder.getJpoMemberOrderList().add(jpoMemberOrderList);
			}
		}
		try {
			String limitIsShipments = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "limitisshipments");// 1代表自动发货即审核完订单即可发货，0表示手动处理发货
			if ("1".equals(limitIsShipments)) {
				jpoMemberOrder.setIsShipping("1");// 1表示已经发货
			} else {
				jpoMemberOrder.setIsShipping("0");// 表示还没有发货
			}

			logCurDate = new Date();

			// 扣款
			logCurDate = new Date();
			this.getSaveMemberOrderDeduct(jpoMemberOrder, operatorSysUser, dataType);
			jpoMemberOrder.setIsPay("1");
			jpoMemberOrderDao.save(jpoMemberOrder);
			
			//Modify By WuCF 20150921，扣款完成后加入MQ审单后续操作
			/*if("1".equals(jpoMemberOrder.getIsPay())){
				JpoMemberOrderCheckModel checkModel = new JpoMemberOrderCheckModel();
				checkModel.setDataType(dataType);
				checkModel.setJpoMemberOrder(jpoMemberOrder);
				checkModel.setJsysUser(operatorSysUser);
				// 发送MQ消息
				messageProducer.send(checkModel);
			}*/

			/*logCurDate = new Date();
			if (!"21".equals(jpoMemberOrder.getOrderType()) && !"24".equals(jpoMemberOrder.getOrderType())) {
				// 插入每日计算表
				JbdSummaryList jbdSummaryList = new JbdSummaryList();
				jbdSummaryList.setUserCode(jpoMemberOrder.getSysUser().getUserCode());
				jbdSummaryList.setCardType(jpoMemberOrder.getSysUser().getJmiMember().getCardType());
				jbdSummaryList.setInType(4);
				jbdSummaryList.setCreateTime(jpoMemberOrder.getCheckTime());
				jbdSummaryList.setOrderType(jpoMemberOrder.getOrderType());
				jbdSummaryList.setOldCheckDate(null);
				jbdSummaryList.setNewCheckDate(jpoMemberOrder.getCheckDate());
				jbdSummaryList.setPvAmt(jpoMemberOrder.getPvAmt());
				jbdSummaryList.setOldLinkNo(null);
				jbdSummaryList.setNewLinkNo(null);
				jbdSummaryList.setOldRecommendNo(null);
				jbdSummaryList.setNewRecommendNo(null);
				jbdSummaryList.setNewCompanyCode(jpoMemberOrder.getCompanyCode());
				jbdSummaryList.setUserCreateTime(null);
				jbdSummaryList.setWweek(jbdPeriodManager.getBdPeriodByTimeFormated(jpoMemberOrder.getCheckDate(), null));
				jbdSummaryListManager.save(jbdSummaryList);
			}
			// 送JOYME号

			jpmCardSeqManager.saveUserJpmCardSeq(jpoMemberOrder, oldCard, newCard);*/
		} catch (Exception app) {
			log.error("", app);
			throw new AppException(this.getLocalLanguageByKey(app.getMessage(), operatorSysUser));
		}
		/*try {
			// TODO sms
			String isPost = ConfigUtil.getConfigValue("CN", "sms.ispost");
			String movieUrl = ConfigUtil.getConfigValue("CN", "sms.movieurl");

			log.info("isPost sms is:[" + isPost + "] and movieUrl is:" + movieUrl);

			if (Boolean.parseBoolean(isPost)) {
				JpoMovie extMovie = jpoMovieDao.getMovieByOrderNo(jpoMemberOrder.getMemberOrderNo());
				if (extMovie == null) {
					Iterator<JpoMemberOrderList> orderListIte = jpoMemberOrder.getJpoMemberOrderList().iterator();
					while (orderListIte.hasNext()) {
						JpoMemberOrderList orderList = orderListIte.next();
						String proNo = orderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo();

						if (proNo.equalsIgnoreCase(Constants.MOVIE_PRONO) || proNo.equalsIgnoreCase(Constants.MOVIE_PRONO2)) {
							String url1 = ListUtil.getListValue("CN", "smssend.url", "1");
							String url2 = ListUtil.getListValue("CN", "smssend.url", "2");
							List<JpoMovie> movieList = jpoMovieDao.findMovieByName('0');

							if (movieList != null && movieList.size() > 0) {
								JpoMovie movie = movieList.get(0);

								StringBuffer message = new StringBuffer();
								
								 * 您已经成功获取养生套餐包影票产品（共300张影票），用户名：XXXXXX，密码：XXXXXX
								 * ；
								 * 请凭此用户名及密码登录http://fm.daohegroup.cn/进行选票（2014
								 * 年2月18日零点正式开通）。
								 * 咨询客服：025-83617786，QQ平台：10310323，祝您观影愉快！
								 
								message.append("您已经成功获取养生套餐包影票产品（共300张影票）");
								message.append(",用户名：" + movie.getMaccount());
								message.append(",密码：" + movie.getMpwd());
								message.append("; 请凭此用户名及密码登录:" + movieUrl);
								message.append("进行选票(2014年2月18日零点正式开通).");
								message.append("咨询客服：025-83617786，QQ平台：10310323，祝您观影愉快！");

								SmsSend.sendSms(url1, url2, jpoMemberOrder.getMobiletele(), message.toString());

								// update jpoMovie setting OrderNo
								log.info("movieId is:" + movie.getmId() + " and orderNo is:" + jpoMemberOrder.getMemberOrderNo());
								movie.setOrderNo(jpoMemberOrder.getMemberOrderNo());
								movie.setStatus('1');
								movie.setMuseTime(new Date());
								jpoMovieDao.save(movie);

								// Modify By WuCF 将短信写入到数据库表 20140312
								JpmSmssendInfo jpmSmssendInfo = new JpmSmssendInfo();
								jpmSmssendInfo.setSmsType("3");// //短信类型
								// 例如：1：仓库发货确认
								// 2：找回密码 3：电影票
								// 目前只有三种，在列表中配置
								jpmSmssendInfo.setSmsRecipient(movie.getMaccount());// 短信接收人:用户会员编号
								jpmSmssendInfo.setSmsMessage(message.toString());// 短信内容
								jpmSmssendInfo.setSmsTime(new Date());// 发送时间
								jpmSmssendInfo.setSmsOperator("");// 操作人，可以填写空
								jpmSmssendInfo.setSmsStatus("1");// 保留字段，是否发送成功！
								// 默认为1：成功！
								// 现在短信还不能知道是否发送成功，没有返回值！
								jpmSmssendInfo.setRemark("会员中心");// 备注
								jpmSmssendInfo.setPhoneNum(jpoMemberOrder.getMobiletele());// 手机号
								jpmProductSaleNewDao.saveJpmSmssendInfo(jpmSmssendInfo);

							} else {
								log.warn("movie Ticket is null, orderNo is:" + jpoMemberOrder.getMemberOrderNo());
							}
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			log.warn("movie Error,orderNo is:" + jpoMemberOrder.getMemberOrderNo(), e);
		}

		// 更改订单系统 状态 -- 已确认
		jpoMemberOrder.setStatusSysMo(Long.parseLong("1"));
		jpoMemberOrderDao.save(jpoMemberOrder);
		// log.info("call function start. "+jpoMemberOrder.getStatus()+" "
		// + "and "+jpoMemberOrder.getIsPay());
		// jpoMemberOrderDao.callSTJFunction(jpoMemberOrder.getMemberOrderNo(),
		// 1);
		log.info("checkOrderEnd ,orderNo is:" + jpoMemberOrder.getMemberOrderNo());*/
	}

	/**
	 * 发展基金抵扣审核会员订单
	 * 
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 * @throws Exception
	 */
	public void checkJpoMemberOrderByJJ(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType) throws Exception {

		String userSpCode = jpoMemberOrder.getUserSpCode();
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = jpoMemberOrder.getJjAmount();
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 13;
		String[] notes = new String[1];
		notes[0] = this.getLocalLanguageByKey("poMemberOrder.orderConfirm", operatorSysUser) + jpoMemberOrder.getMemberOrderNo();
		if (!userSpCode.equals(jpoMemberOrder.getSysUser().getUserCode())) {
			notes[0] += "," + jpoMemberOrder.getSysUser().getUserCode();
		}
		JsysUser sysUserSp = jsysUserDao.get(userSpCode);

		// 判断订单是否包含旅游积分补款产品,决定资金类别是否为24
		if (this.jpoMemberOrderIsIncludeTourCoin(jpoMemberOrder)) {
			moneyType[0] = 24;
		}
		
		/* 支付改造
		fiBankbookJournalManager.saveFiBankbookDeduct("CN", sysUserSp, moneyType, moneyArray, operatorSysUser.getUserCode(), operatorSysUser.getUserName(), jpoMemberOrder
				.getMemberOrderNo(), notes, "1", dataType);
		*/
		log.info("checkJpoMemberOrderByJJ start:"+jpoMemberOrder.getMemberOrderNo()+" "
				+ "Thread id:"+Thread.currentThread().getId());
		checkJpoMemberOrder(jpoMemberOrder, operatorSysUser, dataType);
		log.info("checkJpoMemberOrderByJJ stop:"+jpoMemberOrder.getMemberOrderNo()+" "
				+ "Thread id:"+Thread.currentThread().getId());
		//Modify By WuCF 20150921 调用MQ消息队列
//		jpoMemberOrder.setStatus("3");
//		this.save(jpoMemberOrder);
//		this.jpoMemberOrderPayedSendToMQ(jpoMemberOrder, operatorSysUser, "1");
//		log.info("=======审单MQ：JpoMemberOrderManagerImpl");
	}

	 /**
     * 产品积分支付
     * @param jpoMemberOrder
     * @param operatorSysUser
     * @param dataType
     * @throws Exception
     * @author hdg 2017-01-03
     */
	public void checkJpoMemberOrderByProductPoint(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType) throws Exception {

		String userSpCode = jpoMemberOrder.getUserSpCode();
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = jpoMemberOrder.getProductPointAmount();
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 13;
		String[] notes = new String[1];
		notes[0] = this.getLocalLanguageByKey("poMemberOrder.orderConfirm", operatorSysUser) + jpoMemberOrder.getMemberOrderNo();
		if (!userSpCode.equals(jpoMemberOrder.getSysUser().getUserCode())) {
			notes[0] += "," + jpoMemberOrder.getSysUser().getUserCode();
		}
		JsysUser sysUserSp = jsysUserDao.get(userSpCode);

		// 判断订单是否包含旅游积分补款产品,决定资金类别是否为24
		if (this.jpoMemberOrderIsIncludeTourCoin(jpoMemberOrder)) {
			moneyType[0] = 24;
		}
		log.info("checkJpoMemberOrderByProduct start:"+jpoMemberOrder.getMemberOrderNo()+" " + "Thread id:"+Thread.currentThread().getId());
		checkJpoMemberOrderByProduct(jpoMemberOrder, operatorSysUser, dataType);
		log.info("checkJpoMemberOrderByProduct stop:"+jpoMemberOrder.getMemberOrderNo()+" " + "Thread id:"+Thread.currentThread().getId());
	}
	
	/**
	 * 产品积分支付会员订单
	 * @author hdg 2017-01-03 
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 * @param dataType 数据来源，2：手机，null或者1：PC
	 */
	public synchronized void checkJpoMemberOrderByProduct(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType) throws AppException {
		this.checkJpoMemberOrderPayByProductPoint(jpoMemberOrder, operatorSysUser, dataType);
	}
	
	/**
	 * 代金券支付会员订单
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 * @param dataType
	 * @throws AppException
	 */
	public synchronized void checkJpoMemberOrderByCp(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType) throws AppException {
		this.checkJpoMemberOrderPayByCp(jpoMemberOrder, operatorSysUser, dataType);
	}

	/**
	 * 产品积分支付 业务判断 + 扣款
	 * @author hdg 2017-01-03 
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 * @param dataType
	 */
	public void checkJpoMemberOrderPayByProductPoint(JpoMemberOrder jpoMemberOrder,JsysUser operatorSysUser, String dataType) throws AppException {
		if(checkJpoMemberOrderCanPay(jpoMemberOrder, operatorSysUser, dataType)) {
			// 扣款
			//this.getSaveMemberOrderDeduct(jpoMemberOrder, operatorSysUser, dataType);
			this.getSaveMemberOrderDeductByProduct(jpoMemberOrder, operatorSysUser, dataType);
		}
	}
	
	/**
	 * 代金券支付会员订单 业务判断 + 扣款
	 * @author hdg 2017-01-03 
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 * @param dataType
	 */
	public void checkJpoMemberOrderPayByCp(JpoMemberOrder jpoMemberOrder,JsysUser operatorSysUser, String dataType) throws AppException {
		if(checkJpoMemberOrderCanPay(jpoMemberOrder, operatorSysUser, dataType)) {
			// 扣款
			//this.getSaveMemberOrderDeduct(jpoMemberOrder, operatorSysUser, dataType);
			this.getSaveMemberOrderDeductByCp(jpoMemberOrder, operatorSysUser, dataType);
		}
	}
	/**
	 * 产品积分支付扣款
	 * @author hdg 2017-01-03
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 */
	private void getSaveMemberOrderDeductByProduct(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType) throws AppException {
		String companyCode = jpoMemberOrder.getCompanyCode();
		String userSpCode = jpoMemberOrder.getUserSpCode();
		JsysUser sysUserSp = jsysUserDao.get(userSpCode);

		if ("4".equals(jpoMemberOrder.getOrderType()) && "1".equals(jpoMemberOrder.getCompanyPay())) {
			JalCompany alCompany = alCompanyManager.getAlCompanyByCode(jpoMemberOrder.getCompanyCode());
			sysUserSp = jsysUserDao.getSysUser(alCompany.getPreAgentCode());
			if (sysUserSp == null) {
				throw new AppException("公司收款会员不存在!");
			}
		}
		BigDecimal isPayFee = new BigDecimal(0);
		String operaterCode = operatorSysUser.getUserCode();
		String operaterName = operatorSysUser.getUserName();
		String uniqueCode = jpoMemberOrder.getMemberOrderNo();

		BigDecimal[] moneyArray = new BigDecimal[jpoMemberOrder.getJpoMemberOrderFee().size() + 1];
		Integer[] moneyType = new Integer[jpoMemberOrder.getJpoMemberOrderFee().size() + 1];
		String[] notes = new String[jpoMemberOrder.getJpoMemberOrderFee().size() + 1];

		// 重新计算订单金额
		BigDecimal productMoney = new BigDecimal(0);
		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its1.hasNext()) {

			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			productMoney = productMoney.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));// 产品总金额
		}
		log.info("******productMoney1:"+productMoney);
		log.info("******jpoMemberOrder.getPayByProduct():"+jpoMemberOrder.getPayByProduct());
		log.info("******jpoMemberOrder.getProductPointAmount():"+jpoMemberOrder.getProductPointAmount());
		
		if ("1".equals(jpoMemberOrder.getPayByProduct()) && jpoMemberOrder.getProductPointAmount() != null) {// 积分支付

			productMoney = productMoney.subtract(jpoMemberOrder.getProductPointAmount());
		}
		BigDecimal needPayMoney = productMoney;
		log.info("******needPayMoney:"+needPayMoney);
		
		if ("1".equals(jpoMemberOrder.getPayByProduct()) && jpoMemberOrder.getProductPointAmount() != null) {// 基金支付

			productMoney = productMoney.subtract(jpoMemberOrder.getProductPointAmount());// 支付金额=产品总金额-基金支付金额

			if (productMoney.compareTo(new BigDecimal(0)) == -1) {// 支付金额小于0
				isPayFee = jpoMemberOrder.getProductPointAmount().subtract(productMoney);// 物流费=基金支付金额-产品总额
				productMoney = jpoMemberOrder.getAmount();
			}
		}

		moneyArray[0] = productMoney;// 支付金额
		moneyType[0] = 13;
		
		log.info("******moneyArray[0]:"+moneyArray[0]);
		
		notes[0] = this.getLocalLanguageByKey("poMemberOrder.orderConfirm", operatorSysUser) + jpoMemberOrder.getMemberOrderNo();
		if (!userSpCode.equals(jpoMemberOrder.getSysUser().getUserCode())) {
			notes[0] += "," + jpoMemberOrder.getSysUser().getUserCode();
		}

		Iterator its2 = jpoMemberOrder.getJpoMemberOrderFee().iterator();
		int i = 1;
		while (its2.hasNext()) {
			JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) its2.next();
			if (isPayFee.compareTo(new BigDecimal(0)) == 1) {//
				if (jpoMemberOrderFee.getFee().compareTo(isPayFee) == 1) {
					moneyArray[i] = jpoMemberOrderFee.getFee().subtract(isPayFee);
					isPayFee = new BigDecimal(0);
				} else {
					moneyArray[i] = new BigDecimal(0);
					isPayFee = isPayFee.subtract(jpoMemberOrderFee.getFee());
				}
			} else {
				moneyArray[i] = jpoMemberOrderFee.getFee();
			}

			if ("1".equals(jpoMemberOrderFee.getFeeType())) {
				notes[i] = this.getLocalLanguageByKey("poOrder.mailFee", operatorSysUser);
				moneyType[i] = 32;
			} else if ("2".equals(jpoMemberOrderFee.getFeeType())) {
				notes[i] = this.getLocalLanguageByKey("poOrder.handlingUSFee", operatorSysUser);
				moneyType[i] = 30;
			} else if ("3".equals(jpoMemberOrderFee.getFeeType())) {
				notes[i] = this.getLocalLanguageByKey("poOrder.sax", operatorSysUser);
				moneyType[i] = 33;
			} else if ("4".equals(jpoMemberOrderFee.getFeeType())) {
				notes[i] = this.getLocalLanguageByKey("poOrder.enrollerBonus", operatorSysUser);
				moneyType[i] = 63;
			} else {
				notes[i] = this.getLocalLanguageByKey("fiBankbookTemp.moneyType.7", operatorSysUser);
				moneyType[i] = 34;
			}
			i++;
		}

		// 免单
		if ("1".equals(jpoMemberOrder.getIsFree())) {
			for (int m = 0; m < moneyArray.length; m++) {
				moneyArray[m] = new BigDecimal("0");
			}
			needPayMoney = new BigDecimal("0"); 
		}

		// 判断订单是否包含旅游积分补款产品,决定资金类别是否为24
		if (this.jpoMemberOrderIsIncludeTourCoin(jpoMemberOrder)) {
			moneyType[0] = 24;
		}
	
		for(BigDecimal big : moneyArray){
			log.info("******moneyArray:"+big);
		}
		// 原来的
		//jfiBankbookJournalManager.saveJfiBankbookDeduct_pay(companyCode, sysUserSp, moneyType, moneyArray, operaterCode, operaterName, uniqueCode, notes, dataType);
		//对比产品积分支付金额和订单总金额，判断是否是产品积分全额支付
		BigDecimal orderAmt = jpoMemberOrder.getAmount();						//订单总金额
		BigDecimal productPointAmt = jpoMemberOrder.getProductPointAmount();	//产品积分支付金额
		boolean isPayAllByProductPoint = true;
		if(orderAmt.compareTo(productPointAmt)==1) {
			log.info("订单总金额大于产品积分支付金额,需要判断电子存折余额");
			isPayAllByProductPoint = false;
		} else {
			log.info("订单总金额小于产品积分支付金额,不需要判断电子存折余额");
		}
		String checkType = "1";
		if(jpoMemberOrder.getJjAmount()!=null && jpoMemberOrder.getJjAmount().compareTo(new BigDecimal(0)) >0){
			checkType = "2";
		}
		fiProductPointJournalManager.saveFiProductDeduct_pay(isPayAllByProductPoint,
			companyCode, sysUserSp, moneyType, moneyArray, operaterCode, operaterName, uniqueCode, notes, dataType,needPayMoney,checkType);
		saveBankBookPayList(jpoMemberOrder,dataType,moneyType[0]);
	}
	
	public void checkJpoMemberOrderByCoinAndBankbook(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser,String dataType, BigDecimal sumCoin ) {
		
		/****************************  /*事物统一，平移到manager层，否则会造成支付不成功，但这些数据保存了。  **************START**********/
			jpoMemberOrder.setAmount(jpoMemberOrder.getAmount().subtract(sumCoin));// 重算订单金额,扣去积分支付部分
			jpoMemberOrder.setDiscountAmount(sumCoin);
			jpoMemberOrder.setPvAmt(new BigDecimal("0"));
			
			Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
			
			while (its1.hasNext()) {
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
				jpoMemberOrderList.setPv(new BigDecimal("0"));
				jpoMemberOrderListDao.save(jpoMemberOrderList);
			}
			
			jpoMemberOrder.setPayByCoin("1");
			this.mergeOrder(jpoMemberOrder);
		/****************************  /*事物统一，平移到manager层，否则会造成支付不成功，但这些数据保存了。  **************END**********/
			
			/* 支付改造
			// 扣积分
			BigDecimal[] moneyArray = new BigDecimal[1];
			moneyArray[0] = sumCoin;
			Integer[] moneyType = new Integer[1];
			moneyType[0] = 1;
			String[] notes = new String[1];
			notes[0] = this.getLocalLanguageByKey("poMemberOrder.orderConfirm", operatorSysUser) + jpoMemberOrder.getMemberOrderNo();
			Long[] appId = new Long[1];
			appId[0] = 2l;
			fiBcoinJournalManager.saveJfiBankbookDeduct(jpoMemberOrder.getCompanyCode(), jpoMemberOrder.getSysUser(), moneyType, moneyArray, operatorSysUser.getUserCode(),
					operatorSysUser.getUserName(), jpoMemberOrder.getMemberOrderNo(), notes, appId, dataType);

			jpoMemberOrder.setAmount(jpoMemberOrder.getAmount().subtract(sumCoin));// 重算订单金额,扣去积分支付部分
			jpoMemberOrder.setDiscountAmount(sumCoin);
			
			jpoMemberOrder.setPvAmt(new BigDecimal("0"));
			//jpoMemberOrder.setDiscountPvAmt(new BigDecimal("0"));
			Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
			its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
			while (its1.hasNext()) {
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
				jpoMemberOrderList.setPv(new BigDecimal("0"));
				jpoMemberOrderListDao.save(jpoMemberOrderList);
			}
			
			
			jpoMemberOrder.setPayByCoin("1");
			dao.save(jpoMemberOrder);
			*/
			// 混合电子存折审单
			log.info("checkJpoMemberOrderByCoinAndBankbook start:"+jpoMemberOrder.getMemberOrderNo() +
					" Thread id:"+Thread.currentThread().getId());
			checkJpoMemberOrder(jpoMemberOrder, operatorSysUser, dataType);
			log.info("checkJpoMemberOrderByCoinAndBankbook stop:"+jpoMemberOrder.getMemberOrderNo()+
					" Thread id:"+Thread.currentThread().getId());
			
			
//			jpoMemberOrder.setStatus("3");
//			this.save(jpoMemberOrder);
//			this.jpoMemberOrderPayedSendToMQ(jpoMemberOrder, operatorSysUser, "1");

	}
	
	
	
	
	/**
	 * 积分抵扣审核会员订单
	 * 
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 */
	public void checkJpoMemberOrderByCoinAndBankbook(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType) {
		BigDecimal productAmount = new BigDecimal("0");
		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();

			// 爱心365不能使用积分
			String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
			if (!"P26010100101CN0".equals(productNo)) {

				productAmount = productAmount.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
			}
		}

		// 查询积分余额
		FiBcoinBalance fiBcoinBalance = fiBcoinBalanceManager.get(jpoMemberOrder.getSysUser().getUserCode());
		BigDecimal sumCoin = new BigDecimal("0");

		// 重消单5:5(一半积分、一半存折)
		if (productAmount.compareTo(fiBcoinBalance.getBalance().multiply(new BigDecimal("2"))) != 1) {
			sumCoin = productAmount.multiply(new BigDecimal("0.5"));
		} else {
			sumCoin = fiBcoinBalance.getBalance();
		}

		// 启智派商品换购比例：最高1000积分+2280元
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

		if (jpoMemberOrder.getAmount().compareTo(sumCoin.multiply(new BigDecimal("2"))) == -1) {
			throw new AppException("积分计算有误");
		}

		// 调用公共方法判断是否单独购买九款特殊商品（积分换购),返回true代表是
		if (jpoIsOnly(jpoMemberOrder)) {

			// 重新计算积分
			if (fiBcoinBalance.getBalance().compareTo(productAmount.multiply(new BigDecimal("0.75"))) == 1) {// 积分大于订单总额的75%

				sumCoin = productAmount.multiply(new BigDecimal("0.75"));// 允许使用订单金额75%的积分
			} else {

				sumCoin = fiBcoinBalance.getBalance();
			}
			if (sumCoin.compareTo(productAmount.multiply(new BigDecimal("0.75"))) == 1) {
				throw new AppException("积分计算有误");
			}
		}

		// 辅消品6折,重新计算
		if ("5".equals(jpoMemberOrder.getOrderType())) {
			if (productAmount.compareTo(fiBcoinBalance.getBalance().multiply(new BigDecimal("2.5"))) != 1) {
				sumCoin = productAmount.multiply(new BigDecimal("0.4"));
			} else {
				sumCoin = fiBcoinBalance.getBalance();
			}
			if (jpoMemberOrder.getAmount().compareTo(sumCoin.multiply(new BigDecimal("2.5"))) == -1) {
				throw new AppException("积分计算有误");
			}
		}

		// 如果计算出来的需支付积分不大于0,则直接转电子存折审单
		if (sumCoin.compareTo(new BigDecimal("0")) != 1) {
			log.info("checkJpoMemberOrderByCoinAndBankbook start:"+jpoMemberOrder.getMemberOrderNo() +
					" Thread id:"+Thread.currentThread().getId());
			checkJpoMemberOrder(jpoMemberOrder, operatorSysUser, dataType);
			log.info("checkJpoMemberOrderByCoinAndBankbook stop:"+jpoMemberOrder.getMemberOrderNo() +
					" Thread id:"+Thread.currentThread().getId());
			//Modify By WuCF 20150921 调用MQ消息队列
//			jpoMemberOrder.setStatus("3");
//			this.save(jpoMemberOrder);
//			this.jpoMemberOrderPayedSendToMQ(jpoMemberOrder, operatorSysUser, "1");
//			log.info("=======审单MQ：JpoMemberOrderManagerImpl"); 
			
		} else {
			/* 支付改造
			// 扣积分
			BigDecimal[] moneyArray = new BigDecimal[1];
			moneyArray[0] = sumCoin;
			Integer[] moneyType = new Integer[1];
			moneyType[0] = 1;
			String[] notes = new String[1];
			notes[0] = this.getLocalLanguageByKey("poMemberOrder.orderConfirm", operatorSysUser) + jpoMemberOrder.getMemberOrderNo();
			Long[] appId = new Long[1];
			appId[0] = 2l;
			fiBcoinJournalManager.saveJfiBankbookDeduct(jpoMemberOrder.getCompanyCode(), jpoMemberOrder.getSysUser(), moneyType, moneyArray, operatorSysUser.getUserCode(),
					operatorSysUser.getUserName(), jpoMemberOrder.getMemberOrderNo(), notes, appId, dataType);

			jpoMemberOrder.setAmount(jpoMemberOrder.getAmount().subtract(sumCoin));// 重算订单金额,扣去积分支付部分
			jpoMemberOrder.setDiscountAmount(sumCoin);
			jpoMemberOrder.setPvAmt(new BigDecimal("0"));
			jpoMemberOrder.setPayByCoin("1");

			its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
			while (its1.hasNext()) {
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
				jpoMemberOrderList.setPv(new BigDecimal("0"));
				jpoMemberOrderListDao.save(jpoMemberOrderList);
			}

			dao.save(jpoMemberOrder);
			*/
			
			/*******************手机端积分支付时加上积分代码*********  /*事物统一，平移到manager层，否则会造成支付不成功，但这些数据保存了。  **************START**********/
			jpoMemberOrder.setAmount(jpoMemberOrder.getAmount().subtract(sumCoin));// 重算订单金额,扣去积分支付部分
			jpoMemberOrder.setDiscountAmount(sumCoin);
			jpoMemberOrder.setPvAmt(new BigDecimal("0"));
			
			Iterator its2 = jpoMemberOrder.getJpoMemberOrderList().iterator();
			
			while (its2.hasNext()) {
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its2.next();
				jpoMemberOrderList.setPv(new BigDecimal("0"));
				jpoMemberOrderListDao.save(jpoMemberOrderList);
			}
			
			jpoMemberOrder.setPayByCoin("1");
			this.mergeOrder(jpoMemberOrder);
		/****************************  /*事物统一，平移到manager层，否则会造成支付不成功，但这些数据保存了。  **************END**********/
			
			
			// 混合电子存折审单
			log.info("checkJpoMemberOrderByCoinAndBankbook start:"+jpoMemberOrder.getMemberOrderNo());
			checkJpoMemberOrder(jpoMemberOrder, operatorSysUser, dataType);
			log.info("checkJpoMemberOrderByCoinAndBankbook stop:"+jpoMemberOrder.getMemberOrderNo());
//			jpoMemberOrder.setStatus("3");
//			this.save(jpoMemberOrder);
//			this.jpoMemberOrderPayedSendToMQ(jpoMemberOrder, operatorSysUser, "1");
		}

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
	 * 积分抵扣审核会员订单
	 * 
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 * @throws Exception
	 */
	public void checkJpoMemberOrderByCoin(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType) throws Exception {
		BigDecimal sumCoin = new BigDecimal("0");
		int qty = 0;
		Iterator its = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its.next();
			qty += jpoMemberOrderList.getQty();
		}
		sumCoin = new BigDecimal("830").multiply(new BigDecimal(qty));
		FiBcoinBalance fiBcoinBalance = fiBcoinBalanceManager.get(jpoMemberOrder.getSysUser().getUserCode());
		if (sumCoin.compareTo(fiBcoinBalance.getBalance()) == 1) {
			sumCoin = fiBcoinBalance.getBalance();
		}
		jpoMemberOrder.setAmount(jpoMemberOrder.getAmount().subtract(sumCoin));
		if (jpoMemberOrder.getAmount().compareTo(new BigDecimal("0")) == -1) {
			throw new AppException("审核失败");
		}

		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = sumCoin;
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 1;
		String[] notes = new String[1];
		notes[0] = this.getLocalLanguageByKey("poMemberOrder.orderConfirm", operatorSysUser) + jpoMemberOrder.getMemberOrderNo();
		Long[] appId = new Long[1];
		appId[0] = 2l;
		/* 支付改造
		fiBcoinJournalManager.saveJfiBankbookDeduct(jpoMemberOrder.getCompanyCode(), jpoMemberOrder.getSysUser(), moneyType, moneyArray, operatorSysUser.getUserCode(),
				operatorSysUser.getUserName(), jpoMemberOrder.getMemberOrderNo(), notes, appId, dataType);

		jpoMemberOrder.setDiscountAmount(sumCoin);
		jpoMemberOrder.setPayByCoin("1");

		dao.save(jpoMemberOrder);
		*/
		jpoMemberOrder.setDiscountAmount(sumCoin);
		jpoMemberOrder.setPayByCoin("1");
		if (jpoMemberOrder.getJjAmount() == null || jpoMemberOrder.getJjAmount().compareTo(new BigDecimal(0)) != 1) {
			log.info("pay coin start checkJpoMemberOrder:"+jpoMemberOrder.getMemberOrderNo()+
					" thread id:"+Thread.currentThread().getId());
			checkJpoMemberOrder(jpoMemberOrder, operatorSysUser, dataType);
			log.info("pay coin stop checkJpoMemberOrder:"+jpoMemberOrder.getMemberOrderNo()+
					" thread id:"+Thread.currentThread().getId());
		} else {
			log.info("pay coin start checkJpoMemberOrderByJJ:"+jpoMemberOrder.getMemberOrderNo()+
					" thread id:"+Thread.currentThread().getId());
			checkJpoMemberOrderByJJ(jpoMemberOrder, operatorSysUser, dataType);
			log.info("pay coin stop checkJpoMemberOrderByJJ:"+jpoMemberOrder.getMemberOrderNo()+
					" thread id:"+Thread.currentThread().getId());
		}
		

		//Modify By WuCF 20150921 调用MQ消息队列
//		jpoMemberOrder.setStatus("3");
//		this.save(jpoMemberOrder);
//		this.jpoMemberOrderPayedSendToMQ(jpoMemberOrder, operatorSysUser, "1");
//		log.info("=======审单MQ：JpoMemberOrderManagerImpl");
	}

	/**
	 * 代金券支付扣款
	 * @author hdg 2017-01-03
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 */
	private void getSaveMemberOrderDeductByCp(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType) throws AppException {
		String companyCode = jpoMemberOrder.getCompanyCode();
		String userSpCode = jpoMemberOrder.getUserSpCode();
		JsysUser sysUserSp = jsysUserDao.get(userSpCode);

		if ("4".equals(jpoMemberOrder.getOrderType()) && "1".equals(jpoMemberOrder.getCompanyPay())) {
			JalCompany alCompany = alCompanyManager.getAlCompanyByCode(jpoMemberOrder.getCompanyCode());
			sysUserSp = jsysUserDao.getSysUser(alCompany.getPreAgentCode());
			if (sysUserSp == null) {
				throw new AppException("公司收款会员不存在!");
			}
		}
		BigDecimal isPayFee = new BigDecimal(0);
		String operaterCode = operatorSysUser.getUserCode();
		String operaterName = operatorSysUser.getUserName();
		String uniqueCode = jpoMemberOrder.getMemberOrderNo();

		BigDecimal[] moneyArray = new BigDecimal[jpoMemberOrder.getJpoMemberOrderFee().size() + 1];
		Integer[] moneyType = new Integer[jpoMemberOrder.getJpoMemberOrderFee().size() + 1];
		String[] notes = new String[jpoMemberOrder.getJpoMemberOrderFee().size() + 1];

		// 重新计算订单金额
		BigDecimal productMoney = new BigDecimal(0);
		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its1.hasNext()) {

			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			productMoney = productMoney.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));// 产品总金额
		}
		log.info("******productMoney1:"+productMoney);
		log.info("******jpoMemberOrder.getPayByCp:"+jpoMemberOrder.getPayByCp());
		log.info("******jpoMemberOrder.getCpValue():"+jpoMemberOrder.getCpValue());
		
		if ("1".equals(jpoMemberOrder.getPayByCp()) && jpoMemberOrder.getCpValue() != null) {// 代金券金额

			productMoney = productMoney.subtract(jpoMemberOrder.getCpValue());
		}
		BigDecimal needPayMoney = productMoney;
		log.info("******needPayMoney:"+needPayMoney);//使用代金券后需要补金额
		
		if ("1".equals(jpoMemberOrder.getPayByCp()) && jpoMemberOrder.getCpValue() != null) {// 代金券金额

			productMoney = productMoney.subtract(jpoMemberOrder.getCpValue());// 支付金额=产品总金额-代金券金额

			if (productMoney.compareTo(new BigDecimal(0)) == -1) {// 支付金额小于0
				isPayFee = jpoMemberOrder.getCpValue().subtract(productMoney);// 物流费=基金支付金额-产品总额
				productMoney = jpoMemberOrder.getAmount();
			}
		}

		moneyArray[0] = productMoney;// 支付金额
		moneyType[0] = 13;
		
		log.info("******moneyArray[0]:"+moneyArray[0]);
		
		notes[0] = this.getLocalLanguageByKey("poMemberOrder.orderConfirm", operatorSysUser) + jpoMemberOrder.getMemberOrderNo();
		if (!userSpCode.equals(jpoMemberOrder.getSysUser().getUserCode())) {
			notes[0] += "," + jpoMemberOrder.getSysUser().getUserCode();
		}

		Iterator its2 = jpoMemberOrder.getJpoMemberOrderFee().iterator();
		int i = 1;
		while (its2.hasNext()) {
			JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) its2.next();
			if (isPayFee.compareTo(new BigDecimal(0)) == 1) {//
				if (jpoMemberOrderFee.getFee().compareTo(isPayFee) == 1) {
					moneyArray[i] = jpoMemberOrderFee.getFee().subtract(isPayFee);
					isPayFee = new BigDecimal(0);
				} else {
					moneyArray[i] = new BigDecimal(0);
					isPayFee = isPayFee.subtract(jpoMemberOrderFee.getFee());
				}
			} else {
				moneyArray[i] = jpoMemberOrderFee.getFee();
			}

			if ("1".equals(jpoMemberOrderFee.getFeeType())) {
				notes[i] = this.getLocalLanguageByKey("poOrder.mailFee", operatorSysUser);
				moneyType[i] = 32;
			} else if ("2".equals(jpoMemberOrderFee.getFeeType())) {
				notes[i] = this.getLocalLanguageByKey("poOrder.handlingUSFee", operatorSysUser);
				moneyType[i] = 30;
			} else if ("3".equals(jpoMemberOrderFee.getFeeType())) {
				notes[i] = this.getLocalLanguageByKey("poOrder.sax", operatorSysUser);
				moneyType[i] = 33;
			} else if ("4".equals(jpoMemberOrderFee.getFeeType())) {
				notes[i] = this.getLocalLanguageByKey("poOrder.enrollerBonus", operatorSysUser);
				moneyType[i] = 63;
			} else {
				notes[i] = this.getLocalLanguageByKey("fiBankbookTemp.moneyType.7", operatorSysUser);
				moneyType[i] = 34;
			}
			i++;
		}

		// 免单
		if ("1".equals(jpoMemberOrder.getIsFree())) {
			for (int m = 0; m < moneyArray.length; m++) {
				moneyArray[m] = new BigDecimal("0");
			}
			needPayMoney = new BigDecimal("0"); 
		}

		// 判断订单是否包含旅游积分补款产品,决定资金类别是否为24
		if (this.jpoMemberOrderIsIncludeTourCoin(jpoMemberOrder)) {
			moneyType[0] = 24;
		}
	
		for(BigDecimal big : moneyArray){
			log.info("******moneyArray:"+big);
		}
		// 原来的
		//jfiBankbookJournalManager.saveJfiBankbookDeduct_pay(companyCode, sysUserSp, moneyType, moneyArray, operaterCode, operaterName, uniqueCode, notes, dataType);
		//对比代金券支付金额和订单总金额，判断是否是代金券全额支付
		BigDecimal orderAmt = jpoMemberOrder.getAmount();						//订单总金额
		BigDecimal cpValue = jpoMemberOrder.getCpValue();	//代金券支付金额
		boolean isPayAllByProductPoint = true;
		if(orderAmt.compareTo(cpValue)==1) {
			log.info("订单总金额大于产品积分支付金额,需要判断其他货币余额");
			isPayAllByProductPoint = false;
		} else {
			log.info("订单总金额小于产品积分支付金额,不需要判断其他货币余额");
		}
		String checkType = "1";
		if("1".equals(jpoMemberOrder.getPayByJj())){
			checkType = "2";
		}else if("1".equals(jpoMemberOrder.getPayByProduct())){
			checkType = "3";
		}
		
		//UNDO：判断抵用券是否已经使用
		fiProductPointJournalManager.saveFiCouponDeduct_pay(isPayAllByProductPoint,
				companyCode, sysUserSp, moneyType, moneyArray, operaterCode, operaterName, uniqueCode, notes, dataType,needPayMoney,checkType);
		
		saveBankBookPayList(jpoMemberOrder,dataType,moneyType[0]);
	}
	
	
	// ============================================================以下为私有方法

	/**
	 * 计算升级单新卡别
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	private String getCalcUOrderCardType(JpoMemberOrder jpoMemberOrder) {
		JmiMember jmiMember = jpoMemberOrder.getSysUser().getJmiMember();

		java.util.Calendar startc = java.util.Calendar.getInstance();
		startc.set(2010, 9, 16, 00, 00, 00);
		java.util.Date startDate = startc.getTime();
		Date curDate = new Date();
		java.util.Calendar endc = java.util.Calendar.getInstance();
		endc.set(2010, 9, 21, 00, 00, 00);
		java.util.Date endDate = endc.getTime();
		if ("CN".equals(jpoMemberOrder.getCompanyCode()) && "4".equals(jmiMember.getCardType()) && "1".equals(jmiMember.getIsDiscount()) && curDate.after(startDate)
				&& curDate.before(endDate)) {
			if (jpoMemberOrder.getAmount().compareTo(new BigDecimal("3000")) != -1) {
				return "6";
			}
		}
		// 优惠顾客升级
		java.util.Calendar startcPre = java.util.Calendar.getInstance();
		startcPre.set(2012, 8, 29, 00, 00, 00);
		java.util.Date startDatePre = startcPre.getTime();
		Date curDatePre = new Date();
		if ("CN".equals(jpoMemberOrder.getCompanyCode()) && "5".equals(jmiMember.getCardType()) && curDatePre.after(startDatePre)) {
			String member_upgrade_pre = this.getConfigValue(jmiMember.getCompanyCode().toUpperCase(), "member_upgrade_pre");
			BigDecimal memberPv5 = new BigDecimal(this.getConfigValue(jmiMember.getCompanyCode().toUpperCase(), "cardtype.5.value"));
			BigDecimal orderPv = jpoMemberOrder.getPvAmt();
			int tmp = (Integer.parseInt(member_upgrade_pre));
			for (int i = tmp; i <= tmp; i--) {
				if (i >= 4) {

					if (orderPv.compareTo(new BigDecimal(this.getConfigValue(jmiMember.getCompanyCode().toUpperCase(), "cardtype.6.value")).subtract(memberPv5)) != -1) {
						return "6";
					}

				} else if (i < 4) {
					if (i <= 0) {
						continue;
					}
					if (orderPv.compareTo(new BigDecimal(this.getConfigValue(jmiMember.getCompanyCode().toUpperCase(), "cardtype." + (i + 1) + ".value")).subtract(memberPv5)) == -1) {
						continue;
					} else {
						return String.valueOf(i + 1);
					}
				} else {
					return "";
				}
			}
		}

		String member_upgrade = this.getConfigValue(jmiMember.getCompanyCode().toUpperCase(), "member_upgrade");
		int cardType = Integer.parseInt(jmiMember.getCardType());
		BigDecimal memberPv = new BigDecimal(this.getConfigValue(jmiMember.getCompanyCode().toUpperCase(), "cardtype." + jmiMember.getCardType() + ".value"));
		List<JsysListValue> sysListValues = sysListValueManager.getSysListValuesByCode("bd.cardtype", jmiMember.getCompanyCode().toUpperCase());

		BigDecimal orderPv = jpoMemberOrder.getPvAmt();
		if ("2".equals(jpoMemberOrder.getSysUser().getJmiMember().getMemberType())) {
			orderPv = orderPv.add(jmiMember.getOriPv());
			jmiMember.setOriPv(new BigDecimal("0"));
		}

		if (cardType == 5) {
			cardType = 0;
		}

		int tmp = (Integer.parseInt(member_upgrade) + cardType);
		if (tmp >= 5) {
			tmp++;
		}

		for (int i = tmp; i > cardType; i--) {
			if ("PH".equals(jmiMember.getCompanyCode())) {
				if (i >= sysListValues.size()) {
					continue;
				}
			} else {
				if (i > sysListValues.size() || i > 6) {
					continue;
				}
			}
			if (i == 5 || i == 0) {
				continue;
			}
			if (orderPv.compareTo(new BigDecimal(this.getConfigValue(jmiMember.getCompanyCode().toUpperCase(), "cardtype." + i + ".value")).subtract(memberPv)) == -1)
				continue;
			else {
				return String.valueOf(i);
			}
		}
		// return jmiMember.getCardType();
		return "";
	}

	/**
	 * 计算特殊升级单新卡别
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	private String getCalcSpecialUOrderCardType(JpoMemberOrder jpoMemberOrder) {
		JmiMember jmiMember = jpoMemberOrder.getSysUser().getJmiMember();
		String member_upgrade = this.getConfigValue(jmiMember.getCompanyCode().toUpperCase(), "member_upgrade");
		int cardType = Integer.parseInt(jmiMember.getCardType());
		BigDecimal memberPv = new BigDecimal(this.getConfigValue(jmiMember.getCompanyCode().toUpperCase(), "cardtype." + jmiMember.getCardType() + ".value"));
		List<JsysListValue> sysListValues = sysListValueManager.getSysListValuesByCode("bd.cardtype", jmiMember.getCompanyCode().toUpperCase());

		BigDecimal orderPv = jpoMemberOrder.getPvAmt();
		if ("2".equals(jpoMemberOrder.getSysUser().getJmiMember().getMemberType()) && new BigDecimal("0").compareTo(jmiMember.getOriPv()) == -1) {
			// (PV2 - PV1 - X + 35) / 2 = orderPv
			// PV2 - PV1 = orderPv * 2 - 35 + X
			orderPv = orderPv.multiply(new BigDecimal("2")).subtract(new BigDecimal("35")).add(jmiMember.getOriPv());
			jmiMember.setOriPv(new BigDecimal("0"));
		} else {
			orderPv = orderPv.multiply(new BigDecimal("2"));
		}

		if ("HK".equals(jpoMemberOrder.getCompanyCode())) {
			if ("1".equals(jmiMember.getCardType()) && orderPv.compareTo(new BigDecimal("630")) != -1) {
				orderPv = orderPv.subtract(new BigDecimal("70"));
			}
			if ("2".equals(jmiMember.getCardType()) && orderPv.compareTo(new BigDecimal("350")) != -1) {
				orderPv = orderPv.subtract(new BigDecimal("70"));
			}

		}

		for (int i = (Integer.parseInt(member_upgrade) + cardType); i > cardType; i--) {
			if ("PH".equals(jmiMember.getCompanyCode())) {
				if (i >= sysListValues.size()) {
					continue;
				}
			} else {
				if (i > sysListValues.size() || i > 6) {
					continue;
				}
			}
			if (i == 5 || i == 0) {
				continue;
			}
			if (orderPv.compareTo(new BigDecimal(this.getConfigValue(jmiMember.getCompanyCode().toUpperCase(), "cardtype." + i + ".value")).subtract(memberPv)) == -1)
				continue;
			else {
				return String.valueOf(i);
			}
		}
		return "";
	}

	private String getCalcFOrderCardType(BigDecimal pv, String companyCode) {
		BigDecimal pv6 = new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype." + 6 + ".value"));
		BigDecimal pv5 = new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype." + 5 + ".value"));
		BigDecimal pv4 = new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype." + 4 + ".value"));
		BigDecimal pv3 = new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype." + 3 + ".value"));
		BigDecimal pv2 = new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype." + 2 + ".value"));
		BigDecimal pv1 = new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype." + 1 + ".value"));
		BigDecimal pv0 = new BigDecimal(this.getConfigValue(companyCode.toUpperCase(), "cardtype." + 0 + ".value"));
		java.util.Calendar startc = java.util.Calendar.getInstance();
		startc.set(2010, 8, 4, 00, 00, 00);
		java.util.Date startDate = startc.getTime();

		java.util.Calendar startc2 = java.util.Calendar.getInstance();
		startc2.set(2012, 8, 29, 00, 00, 00);
		java.util.Date startDate2 = startc2.getTime();
		Date curDate = new Date();
		if (curDate.after(startDate) && !"PH".equals(companyCode) && pv.compareTo(pv6) != -1) {
			return "6";
		} else if (pv.compareTo(pv4) != -1) {
			return "4";
		} else if (pv.compareTo(pv3) != -1) {
			return "3";
		} else if (pv.compareTo(pv2) != -1) {
			return "2";
		} else if ("CN".equals(companyCode)) {
			if (curDate.after(startDate2) && pv.compareTo(pv5) != -1) {
				return "5";
			} else if (curDate.before(startDate2) && pv.compareTo(pv5) != -1) {
				return "1";
			}
		} else if (!"CN".equals(companyCode)) {
			if (pv.compareTo(pv1) != -1) {
				return "1";
			} else if (pv.compareTo(pv5) != -1) {
				return "5";
			}
		} else if (pv.compareTo(pv0) != -1) {
			return "0";
		}
		return "0";
	}

	/**
	 * 获取参数
	 * 
	 * @param companyCode
	 * @param configKey
	 * @return
	 */
	private String getConfigValue(String companyCode, String configKey) {
		return Constants.sysConfigMap.get(companyCode).get(configKey);
	}

	/**
	 * 根据键值获取对应的字符值<value, title>
	 * 
	 * @param msgKey
	 * @return
	 */
	public static final LinkedHashMap<String, String> getListOptions(String companyCode, String listCode) {
		Set valueSets = Constants.sysListMap.get(listCode).entrySet();
		LinkedHashMap<String, String> optionMap = new LinkedHashMap<String, String>();
		if (valueSets != null) {
			Iterator iter = valueSets.iterator();
			while (iter.hasNext()) {
				Map.Entry entry = (Map.Entry) iter.next();
				String[] values = (String[]) entry.getValue();

				if (StringUtils.contains(values[1], companyCode)) {
					// 如果当前用户所属公司在排除公司之内,则不显示
					continue;
				} else {
					optionMap.put(entry.getKey().toString(), values[0]);
				}
			}
		}

		return optionMap;
	}

	/**
	 * 扣款
	 * 
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 */
	private void getSaveMemberOrderDeduct(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType) {
		String companyCode = jpoMemberOrder.getCompanyCode();
		String userSpCode = jpoMemberOrder.getUserSpCode();
		JsysUser sysUserSp = jsysUserDao.get(userSpCode);

		if ("4".equals(jpoMemberOrder.getOrderType()) && "1".equals(jpoMemberOrder.getCompanyPay())) {
			JalCompany alCompany = alCompanyManager.getAlCompanyByCode(jpoMemberOrder.getCompanyCode());
			sysUserSp = jsysUserDao.getSysUser(alCompany.getPreAgentCode());
			if (sysUserSp == null) {
				throw new AppException("公司收款会员不存在!");
			}
		}
		BigDecimal isPayFee = new BigDecimal(0);
		String operaterCode = operatorSysUser.getUserCode();
		String operaterName = operatorSysUser.getUserName();
		String uniqueCode = jpoMemberOrder.getMemberOrderNo();

		BigDecimal[] moneyArray = new BigDecimal[jpoMemberOrder.getJpoMemberOrderFee().size() + 1];
		Integer[] moneyType = new Integer[jpoMemberOrder.getJpoMemberOrderFee().size() + 1];
		String[] notes = new String[jpoMemberOrder.getJpoMemberOrderFee().size() + 1];

		// 重新计算订单金额
		BigDecimal productMoney = new BigDecimal(0);
		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its1.hasNext()) {

			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			productMoney = productMoney.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));// 产品总金额
		}
		log.info("******productMoney1:"+productMoney);
		log.info("******jpoMemberOrder.getPayByCoin():"+jpoMemberOrder.getPayByCoin());
		
		if ("1".equals(jpoMemberOrder.getPayByCoin()) && jpoMemberOrder.getDiscountAmount() != null) {// 积分支付

			productMoney = productMoney.subtract(jpoMemberOrder.getDiscountAmount());
		}

		log.info("******productMoney2:"+productMoney);
		log.info("******jpoMemberOrder.getPayByJj():"+jpoMemberOrder.getPayByJj());
		
		if ("1".equals(jpoMemberOrder.getPayByJj()) && jpoMemberOrder.getJjAmount() != null) {// 基金支付

			productMoney = productMoney.subtract(jpoMemberOrder.getJjAmount());// 支付金额=产品总金额-基金支付金额

			if (productMoney.compareTo(new BigDecimal(0)) == -1) {// 支付金额小于0

				isPayFee = jpoMemberOrder.getJjAmount().subtract(productMoney);// 物流费=基金支付金额-产品总额
				// productMoney = new BigDecimal(0);
				productMoney = jpoMemberOrder.getAmount();
			}

		}

		moneyArray[0] = productMoney;// 支付金额
		moneyType[0] = 13;
		
		log.info("******moneyArray[0]:"+moneyArray[0]);
		
		notes[0] = this.getLocalLanguageByKey("poMemberOrder.orderConfirm", operatorSysUser) + jpoMemberOrder.getMemberOrderNo();
		if (!userSpCode.equals(jpoMemberOrder.getSysUser().getUserCode())) {
			notes[0] += "," + jpoMemberOrder.getSysUser().getUserCode();
		}

		Iterator its2 = jpoMemberOrder.getJpoMemberOrderFee().iterator();
		int i = 1;
		while (its2.hasNext()) {
			JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) its2.next();

			if (isPayFee.compareTo(new BigDecimal(0)) == 1) {//
				if (jpoMemberOrderFee.getFee().compareTo(isPayFee) == 1) {
					moneyArray[i] = jpoMemberOrderFee.getFee().subtract(isPayFee);
					isPayFee = new BigDecimal(0);
				} else {
					moneyArray[i] = new BigDecimal(0);
					isPayFee = isPayFee.subtract(jpoMemberOrderFee.getFee());
				}
			} else {
				moneyArray[i] = jpoMemberOrderFee.getFee();
			}

			// moneyType[i] = 1000 +
			// Integer.parseInt(jpoMemberOrderFee.getFeeType());
			if ("1".equals(jpoMemberOrderFee.getFeeType())) {
				notes[i] = this.getLocalLanguageByKey("poOrder.mailFee", operatorSysUser);
				moneyType[i] = 32;
			} else if ("2".equals(jpoMemberOrderFee.getFeeType())) {
				notes[i] = this.getLocalLanguageByKey("poOrder.handlingUSFee", operatorSysUser);
				moneyType[i] = 30;
			} else if ("3".equals(jpoMemberOrderFee.getFeeType())) {
				notes[i] = this.getLocalLanguageByKey("poOrder.sax", operatorSysUser);
				moneyType[i] = 33;
			} else if ("4".equals(jpoMemberOrderFee.getFeeType())) {
				notes[i] = this.getLocalLanguageByKey("poOrder.enrollerBonus", operatorSysUser);
				moneyType[i] = 63;
			} else {
				notes[i] = this.getLocalLanguageByKey("fiBankbookTemp.moneyType.7", operatorSysUser);
				moneyType[i] = 34;
			}
			i++;
		}

		// 免单
		if ("1".equals(jpoMemberOrder.getIsFree())) {
			for (int m = 0; m < moneyArray.length; m++) {
				moneyArray[m] = new BigDecimal("0");
			}
		}

		// 判断订单是否包含旅游积分补款产品,决定资金类别是否为24
		if (this.jpoMemberOrderIsIncludeTourCoin(jpoMemberOrder)) {
			moneyType[0] = 24;
		}
	
		for(BigDecimal big : moneyArray){
			log.info("******moneyArray:"+big);
		}
		log.info("orderNo is:"+jpoMemberOrder.getMemberOrderNo()+" "+" and "+jpoMemberOrder.getPayByJj()
				+ " amount:"+jpoMemberOrder.getAmount()+" and jjamount"+jpoMemberOrder.getJjAmount());
		
		if((jpoMemberOrder.getPayByJj() !=null && jpoMemberOrder.getPayByJj().equals("1") && jpoMemberOrder.getAmount().compareTo(jpoMemberOrder.getJjAmount())==0) 
		 ||(jpoMemberOrder.getPayByProduct() !=null && jpoMemberOrder.getPayByProduct().equals("1") && jpoMemberOrder.getAmount().compareTo(jpoMemberOrder.getProductPointAmount())==0)){
			
		} else {
			jfiBankbookJournalManager.saveJfiBankbookDeduct_pay(companyCode, sysUserSp, moneyType, moneyArray, operaterCode, operaterName, uniqueCode, notes, dataType);
		}
		saveBankBookPayList(jpoMemberOrder,dataType,moneyType[0]);
	}

	/**
	 * 判断订单是否包含旅游积分补款产品
	 * 
	 * @param jpoMemberOrder
	 *            订单对象
	 * @return 如果包含，返回true;否则，返回false
	 */
	public boolean jpoMemberOrderIsIncludeTourCoin(JpoMemberOrder jpoMemberOrder) {

		boolean flag = false;
		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			String proNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
			// 如果包含旅游积分补款的商品
			if (proNo.equalsIgnoreCase("N07010100101CN0")) {
				flag = true;
				break;
			}

		}
		return flag;
	}

	/**
	 * 更改会员角色
	 * 
	 * @param sysUser
	 * @param roleId
	 */
	private void getChangeJmiMemberRole(JsysUser sysUser, String roleId) {
		JsysRole sysRole = sysRoleManager.getSysRoleByCode(roleId);
		JsysUserRole sysUserRole = sysUserRoleManager.getSysUserRoleByUserCode(sysUser.getUserCode());
		sysUserRole.setRoleId(sysRole.getRoleId());
		sysUserRoleManager.save(sysUserRole);
	}

	/**
	 * 保存会员升级记录
	 * 
	 * @param jpoMemberOrder
	 * @param cardType
	 * @param operatorSysUser
	 */
	private void getSaveJmiMemberUpgradeNote(JpoMemberOrder jpoMemberOrder, String cardType, JsysUser operatorSysUser, String updateType) {
		Date logCurDate = new Date();
		JmiMember jmiMember = jmiMemberDao.get(jpoMemberOrder.getSysUser().getUserCode());
		JmiMemberUpgradeNote jmiMemberUpgradeNote = new JmiMemberUpgradeNote();
		jmiMemberUpgradeNote.setJmiMember(jmiMember);
		jmiMemberUpgradeNote.setOldCard(jmiMember.getCardType());
		jmiMemberUpgradeNote.setNewCard(cardType);
		jmiMemberUpgradeNote.setCompanyCode(jmiMember.getCompanyCode());
		jmiMemberUpgradeNote.setMemberOrderNo(jpoMemberOrder.getMemberOrderNo());
		jmiMemberUpgradeNote.setUpdateType(updateType);
		jmiMemberUpgradeNote.setUpdateDate(jpoMemberOrder.getCheckTime());
		jmiMemberUpgradeNote.setRemark("");
		jmiMemberUpgradeNoteDao.save(jmiMemberUpgradeNote);

		logCurDate = new Date();
		// 更新奖金级别表
		jbdUserCardListManager.saveJbdUserCardList(jmiMember.getUserCode(), jpoMemberOrder.getCheckDate(), cardType, updateType, "1");
		jmiMember.setCardType(cardType);

		/*
		 * logCurDate=new Date();
		 * if("0".equals(jmiMember.getCustomerLevel())&&"CN"
		 * .equals(jpoMemberOrder
		 * .getSysUser().getCompanyCode())&&("3".equals(cardType
		 * )||"4".equals(cardType)||"6".equals(cardType))){
		 * jmiMemberDao.sendPcn(jmiMember, "ModifyLevel", "1",
		 * "审核订单赠送PCN VIP用户", operatorSysUser, ""); }
		 * FileUtil.saveLogger(fileName, logCurDate, new Date(), "发送PCN");
		 */
	}

	/**
	 * 获取语言值
	 * 
	 * @param key
	 * @param sysUser
	 * @return
	 */
	private String getLocalLanguageByKey(String key, JsysUser sysUser) {
		String notes = "";
		String defaultMessage = key;
		if ("AA".equals(sysUser.getCompanyCode())) {
			notes = (String) Constants.localLanguageMap.get("zh_CN").get(defaultMessage);
		} else {
			notes = (String) Constants.localLanguageMap.get(sysUser.getDefCharacterCoding()).get(defaultMessage);
		}
		if (notes == null) {
			return defaultMessage;
		}
		return notes;
	}

	/**
	 * 获取语言值
	 * 
	 * @param key
	 * @param sysUser
	 * @return
	 */
	private String getLocalLanguageByKey(String key) {
		String notes = "";
		String defaultMessage = key;
		notes = (String) Constants.localLanguageMap.get("zh_CN").get(defaultMessage);
		if (notes == null) {
			return defaultMessage;
		}
		return notes;
	}
	/**
	 * 会员首购单业务判断1 10:成功 11:未知异常 12:已存在已审首购单
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessMF(JpoMemberOrder jpoMemberOrder) {
		if ("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getActive())) {
			return 13;// 死点
		}
		try {
			if (true) {// if("CN".equals(jpoMemberOrder.getCompanyCode())||
				// "HK".equals(jpoMemberOrder.getCompanyCode())){
				// 是否有已审首购单
				JsysUser sysUser = jpoMemberOrder.getSysUser();
				List jpoMemberOrders = jpoMemberOrderDao.getJpoMemberOrdersByTCS("1", sysUser.getUserCode(), "2");
				if (jpoMemberOrders.size() < 2) {
					return 10;// 成功
				} else {
					return 12;// 已存在已审首购单
				}
			}
		} catch (Exception e) {

		}
		return 11;// 未知异常
	}

	/**
	 * 会员升级单业务判断2 20:成功 22:不存在已审首购单
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessMU(JpoMemberOrder jpoMemberOrder) {
		
		JmiMember jmiMember = jmiMemberDao.getJmiMember(jpoMemberOrder.getSysUser().getUserCode());
     	Map<String,String> mapUp=new HashMap<String,String>();
     	mapUp.put("userCode", jmiMember.getUserCode());
     	mapUp.put("companyCode", jpoMemberOrder.getSysUser().getCompanyCode());
     	mapUp.put("orderType", Constants.ORDER_TYPE_2);
     	mapUp.put("status", "2");
		List orderup = jpoMemberOrderDao.getOrderByParam(mapUp);
		
		String upNum = ConfigUtil.getConfigValue(
				jpoMemberOrder.getSysUser().getCompanyCode(), "member_upgrade_num");
		log.info("member_upgrade_num is:"+upNum);
		if(upNum.equals("1")){
			if(orderup != null && orderup.size()>0){
				return 22;
			}
		}
		
		if(Constants.CARDTYPE_5000.equals(jmiMember.getMemberLevel().toString())){
    		return 22;
    	}
     	
     	return 20;
     	/* 三个月内下升级单 时间判断
		String upgrade_Month = ConfigUtil.getConfigValue(
				jpoShoppingCartOrder.getSysUser().getCompanyCode(),
				"member_upgrade_num");
		
		Map<String,String> orderFirstMap = new HashMap<String,String >();
		orderFirstMap.put("userCode", jpoShoppingCartOrder.getSysUser().getUserCode());
		orderFirstMap.put("companyCode", jpoShoppingCartOrder.getSysUser().getCompanyCode());
		orderFirstMap.put("orderType", Constants.ORDER_TYPE_1);
		orderFirstMap.put("status", "2");
		List orderList = jpoMemberOrderManager.getOrderByParam(orderFirstMap);
		
		JpoMemberOrder orderF = new JpoMemberOrder();
		if(orderList !=null && orderList.size()>0){
			orderF = (JpoMemberOrder)orderList.get(0);
		}
		
		Date checkDate = orderF.getCheckTime();
		
		Calendar curDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		endDate.setTime(checkDate); 
		endDate.set(Calendar.MONTH, endDate.get(Calendar.MONTH+Integer.parseInt(upgrade_Month)));
		endDate.set(Calendar.DAY_OF_MONTH, 1);
		if(curDate.after(endDate)){
			//已过升级时间
			msgList.add(LocaleUtil.getLocalText(
					jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),
					"miMember.isNotUpGrade"));
		}
     	*/
//		JsysUser sysUser = jpoMemberOrder.getSysUser();
//		// if("0".equals(sysUser.getJmiMember().getCardType())){
//		if (sysUser.getJmiMember().getNotFirst() == 0) {
//			return 22;// 待审会员
//		} else {
//			return 20;// 成功
//		}
		// 是否有已审首购单
		// List jpoMemberOrders = dao.getJpoMemberOrdersByTCS("1",
		// sysUser.getUserCode(), "2");
		// if(jpoMemberOrders.size()>0){
		// return 20;//成功
		// }else{
		// return 22;//不存在已审首购单
		// }
	}

	/**
	 * 会员续约业务判断3
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessMRS(JpoMemberOrder jpoMemberOrder) {
		JsysUser sysUser = jpoMemberOrder.getSysUser();
		if (sysUser.getJmiMember().getStartWeek() == 0 || sysUser.getJmiMember().getValidWeek() == 0) {
			return 22;// 待审会员
		}
		// if("0".equals(sysUser.getJmiMember().getCardType())){
		if (sysUser.getJmiMember().getNotFirst() == 0) {
			return 22;// 待审会员
		} else {
			return 30;// 成功
		}
	}

	/**
	 * 会员返单业务判断4
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessMR(JpoMemberOrder jpoMemberOrder) {
		JsysUser sysUser = jpoMemberOrder.getSysUser();
		// if("0".equals(sysUser.getJmiMember().getCardType())){
		if (sysUser.getJmiMember().getNotFirst() == 0) {
			return 22;// 待审会员
		} else {
			return 40;// 成功
		}
		// 是否有已审首购单
		// List jpoMemberOrders = dao.getJpoMemberOrdersByTCS("1",
		// sysUser.getUserCode(), "2");
		// if(jpoMemberOrders.size()>0){
		// return 40;//成功
		// }else{
		// return 22;//不存在已审首购单
		// }
	}

	/**
	 * 会员辅销品订单业务判断5
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessMA(JpoMemberOrder jpoMemberOrder) {
		JsysUser sysUser = jpoMemberOrder.getSysUser();
		// if("0".equals(sysUser.getJmiMember().getCardType())){
		if (sysUser.getJmiMember().getNotFirst() == 0) {
			return 22;// 待审会员
		} else {
			return 40;// 成功
		}
	}

	/**
	 * 店铺首购单业务判断6
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSF(JpoMemberOrder jpoMemberOrder) {

		if ("CN".equals(jpoMemberOrder.getCompanyCode())) {
			// 是否有已审首购单
			JsysUser sysUser = jpoMemberOrder.getSysUser();
			List jpoMemberOrders = jpoMemberOrderDao.getJpoMemberOrdersByTCS("6", sysUser.getUserCode(), "2");
			if (jpoMemberOrders.size() < 2) {
				return 60;// 成功
			} else {
				return 62;// 已存在已审首购单
			}
		}
		return 11;// 未知异常
	}

	/**
	 * 店铺返单业务判断8
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSB(JpoMemberOrder jpoMemberOrder) {

		return 1;// 未知异常
	}

	/**
	 * 店铺重销单业务判断9
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSR(JpoMemberOrder jpoMemberOrder) {
		JsysUser sysUser = jpoMemberOrder.getSysUser();
		JmiMember jmiMember = sysUser.getJmiMember();
		if ("1".equals(jmiMember.getIsstore())) {
			return 90;// 成功
		} else {
			return 92;// 不存在已审首购单
		}
	}

	/**
	 * 二级馆首购单业务判断11
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSSubF(JpoMemberOrder jpoMemberOrder) {
		// 是否有已审首购单
		JsysUser sysUser = jpoMemberOrder.getSysUser();
		List jpoMemberOrders = jpoMemberOrderDao.getJpoMemberOrdersByTCS("11", sysUser.getUserCode(), "2");
		if (jpoMemberOrders.size() < 2) {
			return 60;// 成功
		} else {
			return 62;// 已存在已审首购单
		}
	}

	/**
	 * 二级馆升级单业务判断12
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSSubU(JpoMemberOrder jpoMemberOrder) {
		JsysUser sysUser = jpoMemberOrder.getSysUser();
		JmiMember jmiMember = sysUser.getJmiMember();
		if ("2".equals(jmiMember.getIsstore())) {
			return 90;// 成功
		} else {
			return 92;// 不存在已审首购单
		}
	}

	/**
	 * 活力小铺首购单业务判断21
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSMF(JpoMemberOrder jpoMemberOrder) {
		JsysUser sysUser = jpoMemberOrder.getSysUser();
		JmiMember jmiMember = sysUser.getJmiMember();
		if (!"1".equals(jmiMember.getShopType()) && "6".equals(jmiMember.getCardType())) {
			return 90;// 成功
		} else {
			return 92;// 不存在已审首购单
		}
	}

	/**
	 * 活力小铺重消单业务判断24
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSMR(JpoMemberOrder jpoMemberOrder) {
		JsysUser sysUser = jpoMemberOrder.getSysUser();
		JmiMember jmiMember = sysUser.getJmiMember();
		if ("1".equals(jmiMember.getShopType())) {
			return 90;// 成功
		} else {
			return 92;// 不存在已审首购单
		}
	}

	/**
	 * 二级馆重销单业务判断14
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSSubR(JpoMemberOrder jpoMemberOrder) {
		JsysUser sysUser = jpoMemberOrder.getSysUser();
		JmiMember jmiMember = sysUser.getJmiMember();
		if ("2".equals(jmiMember.getIsstore())) {
			return 90;// 成功
		} else {
			return 92;// 不存在已审首购单
		}
	}

	/**
	 * 会员返单业务判断15
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessAS(JpoMemberOrder jpoMemberOrder) {
		JsysUser sysUser = jpoMemberOrder.getSysUser();
		// if("0".equals(sysUser.getJmiMember().getCardType())){
		if (sysUser.getJmiMember().getNotFirst() == 0) {
			return 22;// 待审会员
		} else {
			return 40;// 成功
		}
		// 是否有已审首购单
		// List jpoMemberOrders = dao.getJpoMemberOrdersByTCS("1",
		// sysUser.getUserCode(), "2");
		// if(jpoMemberOrders.size()>0){
		// return 40;//成功
		// }else{
		// return 22;//不存在已审首购单
		// }
	}

	/**
	 * <li>按商品绑定赠品</li>
	 * 
	 * @param jpoMemberOrder
	 * @param JpmSalePromoter
	 *            0:N 代表商品购买的数量>0，则商品买多少就送多少；1:2代表商品购买的数量>1 则赠送2个商品，1:N*3
	 *            代表购买的商品>1,则商品买多少就送购买的数量*3，1:1*3 代表购买的商品>1,则送购买的1*3
	 */
	private Set<JpoMemberOrderList> bindPresentProduct(JpoMemberOrder jpoMemberOrder, JpmSalePromoter sp) throws Exception {
		Set<JpoMemberOrderList> orderListSet = new HashSet<JpoMemberOrderList>();
		String sale_proNo = sp.getPresentno();
		try {
			String limit = sp.getPresentlimit();
			log.info("limit is:[" + limit + "]");

			String[] limitArr = new String[2];
			String shopNum = "", perNum = "", firStr = "", lasStr = "";
			int lasNum = 0;

			if (limit.length() > 3) {
				firStr = limit.substring(0, 3);
				lasStr = limit.substring(firStr.length(), limit.length());
				limitArr = firStr.split(":");
				shopNum = limitArr[0];
				perNum = limitArr[1];
				lasNum = Integer.parseInt(lasStr.substring(1));
			} else {
				limitArr = limit.split(":");
				shopNum = limitArr[0];
				perNum = limitArr[1];
			}

			log.info("shopNum is:[" + shopNum + "] and perNum is:[" + shopNum + "] " + "and firStr is:[" + firStr + "] and lasStr is:[" + lasStr + "] " + "and lasNum is:["
					+ lasNum + "]");

			Iterator<JpoMemberOrderList> orderItems = jpoMemberOrder.getJpoMemberOrderList().iterator();
			while (orderItems.hasNext()) {// 获取订单中购买的产品
				JpoMemberOrderList orderItem = orderItems.next();
				String order_ProNo = orderItem.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();

				if (sale_proNo.equalsIgnoreCase(order_ProNo)) {// 订单中购买的产品在促销配置中
					/**
					 * 赠送模式: 2:N 即: 2为购买最低限制 ,N为赠品数量,可填N或数字 如果是 2:N,则 购买数量大于2
					 * 则送同等数量的赠品 若为2:1 , 则买2个送1个. 或者2:1*2 即:购买2个指定商品,就送 qty*2的赠品
					 */
					int num = 1;
					if (orderItem.getQty() >= Integer.parseInt(shopNum)) {
						if (limit.length() > 3 && lasStr.startsWith("/")) {
							/*
							 * 避免除法运算为0时,赠送产品数量获取错误
							 */
							if (orderItem.getQty() > 1) {
								num = (int) Math.floor(orderItem.getQty() / lasNum);
							} else {
								if (perNum.equalsIgnoreCase("N"))
									num = orderItem.getQty();
								else
									num = Integer.parseInt(perNum);
							}

						} else if (limit.length() > 3 && lasStr.startsWith("*")) {
							num = orderItem.getQty() * lasNum;
						} else {
							if (perNum.equalsIgnoreCase("N"))
								num = orderItem.getQty();
							else
								num = Integer.parseInt(perNum);
						}
						// 绑定赠品
						orderListSet = bindProduct(sp, jpoMemberOrder, num);
					}

					if (log.isDebugEnabled()) {
						log.debug("saleProNo is:[" + sale_proNo + "] " + "and order ProNo is:[" + order_ProNo + "] " + "and persent num is:" + num + "]");
					}
				}

				/*
				 * 当购买数量,大于促销规定数量时,加送赠品 列:购买数量大于2加送多个赠品 例 2:PRNO3202,PRNO00921
				 */
				if (StringUtils.isNotBlank(sp.getAppendpresent())) {
					String appendPre = sp.getAppendpresent();
					log.info("appendPresent is:" + appendPre);

					String[] appPreArr = appendPre.split(":");
					String[] proArr = appPreArr[1].split(",");
					String saleNum = appPreArr[0];
					if (orderItem.getQty() >= Integer.parseInt(saleNum)) {

						for (int i = 0; i < proArr.length; i++) {
							JpmProductSaleTeamType pstt = jpmProductSaleManager.getJpmProductSaleTeamTypeList(proArr[i], jpoMemberOrder.getTeamCode(), null, jpoMemberOrder
									.getCompanyCode(), "0,1,2");

							if (log.isDebugEnabled())
								log.debug("pstt is:" + pstt);

							if (pstt != null && pstt.getJpmProductSaleNew() != null) {
								JpoMemberOrderList orderList = new JpoMemberOrderList();
								orderList.setJpmProductSaleTeamType(pstt);
								orderList.setProNo(pstt.getJpmProductSaleNew().getProductNo());
								orderList.setJpoMemberOrder(jpoMemberOrder);
								orderList.setPrice(new BigDecimal("0"));
								orderList.setProductType("");
								orderList.setPv(new BigDecimal("0"));
								orderList.setQty(1);
								orderList.setVolume(new BigDecimal("0"));
								orderList.setWeight(new BigDecimal("0"));

								// jpoMemberOrder.getJpoMemberOrderList().add(orderList);
								orderListSet.add(orderList);
							} else {
								log.warn("product not exist, proNo is:" + proArr[i] + " " + "and orderNo is:" + jpoMemberOrder.getMemberOrderNo());
							}
						}
					}
				}
			}
			return orderListSet;

		} catch (AppException e) {
			log.error("bind product failed ", e);
			throw new AppException("bind present failed!");
		} catch (Exception e) {
			log.error("bind present failed, " + "orderNo is:" + jpoMemberOrder.getMemberOrderNo(), e);
			throw new AppException("bind present failed!");
		}
	}

	/**
	 * 根据订单总金额或PV,或订单类型绑定赠品
	 * 
	 * @param order
	 * @param sp
	 */
	private Set<JpoMemberOrderList> getOrderAmountBindProduct(JpoMemberOrder order, JpmSalePromoter sp) throws Exception {

		Set<JpoMemberOrderList> set = new HashSet<JpoMemberOrderList>();

		log.info("orderNo :[" + order.getMemberOrderNo() + "] " + "present product num is:" + sp.getSaleProSet().size());

		try {
			/*
			 * 当总金额和PV都为0时 , 按订单类型绑定赠品
			 */
			if (sp.getAmount().compareTo(new BigDecimal(0)) == 0 && sp.getPv() == 0L) {

				String sale_OrderType = sp.getOrdertype();
				String orderType = order.getOrderType();
				log.info("sale orderType is:[" + sale_OrderType + "] and orderType is:" + orderType);

				if (isOrderType(sale_OrderType, orderType)) {
					return bindProduct(sp, order, 0);
				}
			} else {
				if (validateAmountOrPV(order, sp)) {
					return bindProduct(sp, order, 0);
				}
			}

		} catch (AppException e) {
			throw new AppException("sale promoter failed.");
		} catch (Exception e) {
			throw new AppException("sale promoter failed.");
		}
		return set;
	}

	/**
	 * 赠送积分
	 * 
	 * @param jpoMemberOrder
	 * @param JpmSalePromoter
	 * @param ispresent
	 * @param SysUser
	 */
	private void getBindIntegral(JpoMemberOrder jpoMemberOrder, JpmSalePromoter sp, String ispresent, JsysUser operatorUser, String dataType) throws Exception {
		try {
			if (validateAmountOrPV(jpoMemberOrder, sp)) {
				BigDecimal amount = jpoMemberOrder.getAmount();
				// 积分比例
				BigDecimal sale_integral = new BigDecimal(sp.getIntegral());
				Integer[] moneyType = new Integer[] { 10 };
				Long[] appId = new Long[] { 2L };
				String[] notes = new String[1];
				BigDecimal[] moneyArray = new BigDecimal[1];
				JsysUser sysUser = jpoMemberOrder.getSysUser();
				moneyArray[0] = jpoMemberOrder.getAmount();
				String isCoin = jpoMemberOrder.getPayByCoin() == null ? "1" : jpoMemberOrder.getPayByCoin();

				log.info("orderNo is:[" + jpoMemberOrder.getMemberOrderNo() + "] " + "and amount is:[" + amount.toString() + "] " + "and fund amount is:["
						+ jpoMemberOrder.getJjAmount() + "] " + "and isCoin is:[" + isCoin + "]");
				/*
				 * 使用积分购买是否参与,0否, 1是.
				 */
				if (isCoin.equals("1")) {
					// 是否赠送推荐人0否, 1
					if (ispresent.equals("0")) {

						notes = new String[] { "审单赠送积分=====" + jpoMemberOrder.getMemberOrderNo() };

						if (jpoMemberOrder.getJjAmount() != null)
							moneyArray[0] = ((amount.add(jpoMemberOrder.getJjAmount())).multiply(sale_integral));

						fiBcoinJournalManager.saveFiPayDataVerify("CN", sysUser, moneyType, moneyArray, operatorUser.getUserCode(), operatorUser.getUserName(), "0", notes, appId,
								null, dataType);

					} else {

						JmiMember recMember = jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getRecommendJmiMember();

						log.info("exitDate is:[" + recMember.getExitDate() + "] " + "and saleOrderType is:[" + sp.getPreordertype() + "] " + "and orderType is:["
								+ jpoMemberOrder.getOrderType() + "]");

						if (recMember.getExitDate() == null && sp.getPreordertype().indexOf(jpoMemberOrder.getOrderType()) > -1) {

							notes = new String[] { "审单赠送销售商积分=====" + jpoMemberOrder.getMemberOrderNo() };
							BigDecimal reIntegral = new BigDecimal(sp.getReintegral());

							if (jpoMemberOrder.getJjAmount() != null)
								moneyArray[0] = ((amount.add(jpoMemberOrder.getJjAmount())).multiply(reIntegral));

							JsysUser reSysUser = jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getRecommendJmiMember().getSysUser();

							fiBcoinJournalManager.saveFiPayDataVerify("CN", reSysUser, moneyType, moneyArray, operatorUser.getUserCode(), operatorUser.getUserName(), "0", notes,
									appId, null, dataType);

						}
					}
				}
				log.info("present money is:[" + moneyArray[0] + "] user is:" + sysUser.getUserCode());
			}

		} catch (AppException e) {
			throw new AppException("present fund error!");
		} catch (Exception e) {
			throw new AppException("present fund error!");
		}

	}

	/**
	 * 绑定商品
	 * 
	 * @param sp
	 * @param order
	 * @param flag
	 *            0:按订单类型或总金额,赠送产品数量取策略中的数量
	 * @throws Exception
	 */
	private Set<JpoMemberOrderList> bindProduct(JpmSalePromoter sp, JpoMemberOrder order, int flag) throws Exception {
		try {
			Set<JpoMemberOrderList> set = new HashSet<JpoMemberOrderList>();
			Iterator<JpmSalepromoterPro> iter = sp.getSaleProSet().iterator();
			while (iter.hasNext()) {
				JpmSalepromoterPro spro = iter.next();
				String proNo = spro.getProno();
				// 绑定赠品
				JpmProductSaleTeamType jpmProductSale = jpmProductSaleManager.getJpmProductSaleTeamTypeList(proNo, order.getTeamCode(), null, order.getCompanyCode(), "0,1,2");

				if (jpmProductSale != null) {
					JpoMemberOrderList jpoMemberOrderList = new JpoMemberOrderList();
					jpoMemberOrderList.setJpmProductSaleTeamType(jpmProductSale);
					jpoMemberOrderList.setJpoMemberOrder(order);
					jpoMemberOrderList.setProNo(jpmProductSale.getJpmProductSaleNew().getProductNo());
					jpoMemberOrderList.setPrice(new BigDecimal("0"));
					jpoMemberOrderList.setProductType("");
					jpoMemberOrderList.setPv(new BigDecimal("0"));
					jpoMemberOrderList.setVolume(new BigDecimal("0"));
					jpoMemberOrderList.setWeight(new BigDecimal("0"));

					if (flag > 0) {
						jpoMemberOrderList.setQty(flag);
					} else {
						jpoMemberOrderList.setQty(spro.getPronum().intValue());
					}
					set.add(jpoMemberOrderList);
				} else {
					throw new AppException("未找到赠品,赠品编号:" + proNo);
				}
			}
			return set;
		} catch (Exception e) {
			log.error("", e);
			throw e;
		}
	}

	public void setFiBankbookJournalManager(FiBankbookJournalManager fiBankbookJournalManager) {
		this.fiBankbookJournalManager = fiBankbookJournalManager;
	}

	public boolean getJpoMemberMark(String papernumber, String productNo, String orderType) {
		boolean validUser = false;// 没有购买过事业锦囊
		List list = jpoMemberOrderDao.getJpoMemberMark(papernumber, productNo, orderType);
		if (list != null) {
			if (list.size() > 0) {
				validUser = true;// 会员购买过事业锦囊;
			}
		}

		return validUser;
	}

	/**
	 * 根据订单类型,用户团队,国别 确定此订单是否参与促销
	 * 
	 * @param JpmSalePromoter
	 * @param jpoMemberOrder
	 * @return true or false;
	 */
	private boolean isOrderSales(JpmSalePromoter sp, JpoMemberOrder jpoMemberOrder) throws Exception {
		boolean flag = false;

		try {
			String sale_teams = sp.getTeamno();
			String sale_countrys = sp.getCompanyno();
			String sale_orders = sp.getOrdertype();

			String order_comCode = jpoMemberOrder.getCompanyCode();
			String order_type = jpoMemberOrder.getOrderType();
			String order_userCode = jmiTeamManager.teamStr(jpoMemberOrder.getSysUser());

			log.info("saleTeams is:[" + sale_teams + "] and order Team is:[" + order_userCode + "] " + "and country is:[" + sale_countrys + "] and order company:[" + order_comCode
					+ "] " + "and sale_orders is:[" + sale_orders + "] and orderType is:[" + order_type + "]");

			if (sale_teams == null) {
				sale_teams = "";
			}

			if (sale_countrys.indexOf(order_comCode) != -1 && sale_teams.indexOf(order_userCode) < 0 && isOrderType(sale_orders, order_type)) {
				flag = true;
			}

		} catch (AppException e) {
			throw new AppException("sale promoter failed.");
		} catch (Exception e) {
			log.error("", e);
			throw new AppException("sale promoter failed.");
		}
		log.info("isOrderSales return is:" + flag);
		return flag;
	}

	/**
	 * 验证订单总金额和PV是否达到指定值
	 * 
	 * @param jpoMemberOrder
	 * @param sp
	 * @return true or fasle;
	 */
	private boolean validateAmountOrPV(JpoMemberOrder jpoMemberOrder, JpmSalePromoter sp) {
		boolean flag = false;
		/*
		 * 1.总金额>0 和PV >0 的情况 2.总金额 <=0 和 PV >0 的情况 3.总金额>0 和 PV <=0 的情况
		 * 4.总金额==0 和 PV==0 的情况
		 */
		if (sp.getAmount().compareTo(new BigDecimal(0)) > 0 && sp.getPv() > 0L) {
			if (jpoMemberOrder.getAmount().compareTo(sp.getAmount()) >= 0 && jpoMemberOrder.getPvAmt().compareTo(new BigDecimal(sp.getPv())) >= 0) {
				flag = true;
			}
		} else if (sp.getAmount().compareTo(new BigDecimal(0)) <= 0 && sp.getPv() > 0L) {
			if (jpoMemberOrder.getPvAmt().compareTo(new BigDecimal(sp.getPv())) >= 0) {
				flag = true;
			}
		} else if (sp.getAmount().compareTo(new BigDecimal(0)) > 0 && sp.getPv() <= 0L) {
			if (jpoMemberOrder.getAmount().compareTo(sp.getAmount()) >= 0) {
				flag = true;
			}
		} else if (sp.getAmount().compareTo(new BigDecimal(0)) == 0 && sp.getPv() == 0L) {
			flag = true;
		}
		log.info("order amount is:[" + jpoMemberOrder.getAmount() + "] " + "and salePromoter amount is:[" + sp.getAmount() + "] " + "orderPV:[" + jpoMemberOrder.getPvAmt() + "] "
				+ "and salePromoter PV is:" + sp.getPv() + "] and flag is:" + flag);
		return flag;
	}

	/**
	 * 如果订单类型符合此策略定义的类型则返回true
	 * 
	 * @param saleType
	 *            以逗号隔开的字符串
	 * @param type
	 *            需要比对的字符
	 * @return true or false
	 * @throws Exception
	 */
	public boolean isOrderType(String saleType, String type) throws Exception {

		if (StringUtils.isBlank(saleType))
			return true;

		String[] saleTypeArr = saleType.split(",");
		boolean flag = false;
		for (int i = 1; i < saleTypeArr.length; i++) {
			if (saleTypeArr[i].equals(type)) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/**
	 * 查询首购单的审核时间
	 * 
	 * @param memberNo
	 * @return
	 */
	public String getMemberFirstOrderStatusTime(String memberNo) {
		return jpoMemberOrderDao.getMemberFirstOrderStatusTime(memberNo);
	}

	/**
	 * 查询首购单的审核时间
	 * 
	 * @param memberNo
	 * @return
	 */
	public String getMemberCheckDate(String memberNo) {
		return jpoMemberOrderDao.getMemberCheckDate(memberNo);
	}

	/**
	 * 会员编号查找
	 * 
	 * @param jpoMemberOrder
	 * @return
	 */
	public List getJpoMemberOrdersByMiMember(JpoMemberOrder jpoMemberOrder) {
		return jpoMemberOrderDao.getJpoMemberOrdersByMiMember(jpoMemberOrder);
	}

	// 根据输入的条件查询会员购买的订单
	public List<JpoMemberOrder> getOrderByParam(Map<String, String> map) {
		List<JpoMemberOrder> orderList = jpoMemberOrderDao.getOrderByParam(map);
		if (orderList != null && orderList.size() > 0)
			return vilidateProduct(orderList);
		else
			return null;
	}

	/**
	 * 查询指定会员订购的家套餐的数据的订单 Add By WuCF 20150410
	 */
	public List<JpoMemberOrder> getOrderByParamStj(Map<String, String> map) {
		List<JpoMemberOrder> orderList = jpoMemberOrderDao.getOrderByParamStj(map);
		return orderList;
	}

	/**
	 * 根据条件查询会员新增成功的订单：分页
	 * 
	 * @param map
	 *            ：条件
	 * @param pageNum
	 *            ：当前页码
	 * @param pageSize
	 *            ：分页单位
	 * @return
	 */
	public List<JpoMemberOrder> getOrderByParamPage(Map<String, String> map, int pageNum, int pageSize) {
		List<JpoMemberOrder> orderList = jpoMemberOrderDao.getOrderByParamPage(map, pageNum, pageSize);
		if (orderList != null && orderList.size() > 0) {
			return orderList;
		}
		return null;
	}

	public List<JpoMemberOrder> getJpoMemberOrder(List<String> memberOrders) {
		if (memberOrders != null && memberOrders.size() > 0) {
			return jpoMemberOrderDao.getJpoMemberOrder(memberOrders);
		}
		return null;
	}

	public List getMemberOrderNopay(String memberNo) {
		return jpoMemberOrderDao.getMemberOrderNopay(memberNo);
	}

	/**
	 * Add By WuCF 20131209 查询指定行数的数据
	 * 
	 * @param memberNo
	 * @return
	 */
	public List getMemberOrderNopay(String memberNo, Integer startIndex, Integer endIndex) {
		return jpoMemberOrderDao.getMemberOrderNopay(memberNo, startIndex, endIndex);
	}

	public Pager<JpoMemberOrder> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		return jpoMemberOrderDao.getPager(JpoMemberOrder.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	// 判断是不是优惠订购的产品
	public int getPreferentialOrder(JpoMemberOrder jpoMemberOrder) {
		Iterator ite = jpoMemberOrder.getJpoMemberOrderList().iterator();
		int booleanValue = 0;
		int number = 0;
		while (ite.hasNext()) {
			JpoMemberOrderList jpoMemberOrderListTmp = (JpoMemberOrderList) ite.next();
			if ("N06010100101CN0".equals(jpoMemberOrderListTmp.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo())) {
				booleanValue = 1;// 代表订单通过并且不计算物流费，不允许用积分换购
				if (number == 1) {
					booleanValue = 0;
					break;
				} else {
					number = 2;

				}
			} else {
				booleanValue = 2;// 代表订单通过 需要计算物流费，允许用积分换购
				if (number == 2) {
					booleanValue = 0;
					break;
				} else {
					number = 1;

				}

			}

		}
		return booleanValue;
	}

	/**
	 * 添加电影票产品描述
	 * 
	 * @param orderList
	 * @return
	 */
	private List<JpoMemberOrder> vilidateProduct(List<JpoMemberOrder> orderList) {
		try {
			String movieUrl = ConfigUtil.getConfigValue("CN", "sms.movieurl");
			for (JpoMemberOrder order : orderList) {
				Iterator<JpoMemberOrderList> orderItems_itr = order.getJpoMemberOrderList().iterator();
				while (orderItems_itr.hasNext()) {
					JpoMemberOrderList orderItem = orderItems_itr.next();
					String order_proNo = orderItem.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
					// P21010200101CN0 电影票编号
					if (order_proNo.equalsIgnoreCase("P21010200101CN0")) {
						JpoMovie movie = jpoMovieDao.getMovieByOrderNo(order.getMemberOrderNo());
						/*
						 * 您已经成功获取养生套餐包影票产品（共300张影票），用户名：XXXXXX，密码：XXXXXX；
						 * 请凭此用户名及密码登录http
						 * ://fm.daohegroup.cn/进行选票（2014年2月18日零点正式开通）。
						 * 咨询客服：025-83617786，QQ平台：10310323，祝您观影愉快！
						 */
						StringBuffer desc = new StringBuffer();
						desc.append("您已经成功获取养生套餐包影票产品（共300张影票）");
						desc.append(",用户名：" + movie.getMaccount());
						desc.append(",密码：" + movie.getMpwd());
						desc.append("; 请凭此用户名及密码登录:" + movieUrl);
						desc.append("进行选票(2014年2月18日零点正式开通).");
						desc.append("咨询客服：025-83617786，QQ平台：10310323，祝您观影愉快！");
						orderItem.setDesc(desc.toString());
					}
				}
			}
		} catch (Exception e) {
			log.error("validate product movie error.", e);
		}

		return orderList;
	}

	/**
	 * 根据订单号获取订单对象
	 */
	@Override
	public JpoMemberOrder getJpoMemberOrderByMemberOrderNo(String memberOrderNo) {
		// TODO Auto-generated method stub

		return jpoMemberOrderDao.getJpoMemberOrderByMemberOrderNo(memberOrderNo);
	}

	@Override
	public void modifyOrderStatusByMoId(Map<String, String> map) {
		jpoMemberOrderDao.modifyOrderStatusByMoId(map);
	}

	/**
	 * 判断是否单独购买九款特殊商品（积分换购),返回false代表是
	 * 
	 * @param jpoMemberOrder
	 *            ：订单
	 * @return true:包含
	 */
	protected boolean jpoIsOnly(JpoMemberOrder jpoMemberOrder) {

		boolean isOnly = true;
		Iterator its2 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its2.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its2.next();
			String carProNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();// 商品

			if (GlobalVar.jpoList.contains(carProNo)) {

				isOnly = true;// 在9款产品范围内
			} else {

				return false;// 只要查询到一款不在范围内，直接return false
			}
		}

		return isOnly;
	}

	/**
	 * 生态家套餐审单
	 * 
	 * @param orderList
	 * @param operatorSysUser
	 * @param dataType
	 * @throws Exception
	 */
	@Override
	public void checkJpoBigOrderByJJ(List<JpoMemberOrder> orderList, JsysUser operatorSysUser, String dataType) throws Exception {

		// 迭代订单列表
		for (JpoMemberOrder jpoMemberOrder : orderList) {

			// 调用审单方法
			log.info("userCode=" + operatorSysUser.getUserCode() + " " + "and order userSP=" + jpoMemberOrder.getUserSpCode() + " " + "and order userCode ="
					+ jpoMemberOrder.getSysUser().getUserCode());
			checkBigOrder(jpoMemberOrder, operatorSysUser, dataType);
			/********************* 扣款 start *************************/
			Integer[] moneyType = new Integer[1];
			moneyType[0] = 13;

			String[] notes = new String[1];
			notes[0] = this.getLocalLanguageByKey("poMemberOrder.orderConfirm", operatorSysUser) + jpoMemberOrder.getMemberOrderNo() + "," + operatorSysUser.getUserCode();

			// 优先使用发展基金支付
			if (jpoMemberOrder.getJjAmount().compareTo(BigDecimal.ZERO) == 1) {

				// 发展基金抵扣金额
				BigDecimal[] jjMoneyArray = new BigDecimal[1];
				jjMoneyArray[0] = jpoMemberOrder.getJjAmount();

				fiBankbookJournalManager.saveFiBankbookDeduct("CN", operatorSysUser, moneyType, jjMoneyArray, operatorSysUser.getUserCode(), operatorSysUser.getUserName(),
						jpoMemberOrder.getMemberOrderNo(), notes, "1", dataType);
			}

			// 存折支付
			if (jpoMemberOrder.getAmount().compareTo(BigDecimal.ZERO) == 1) {

				// 电子存折扣款金额
				BigDecimal[] czMoneyArray = new BigDecimal[1];
				czMoneyArray[0] = jpoMemberOrder.getAmount();

				jfiBankbookJournalManager.saveJfiBankbookDeduct("CN", operatorSysUser, moneyType, czMoneyArray, operatorSysUser.getUserCode(), operatorSysUser.getUserName(),
						jpoMemberOrder.getMemberOrderNo(), notes, dataType);
			}
			/********************* 扣款 end *************************/
		}
	}

	public JpoMemberOrder mergeOrder(JpoMemberOrder order) {
		return jpoMemberOrderDao.save(order);
	}

	public void mergeOrder(List<JpoMemberOrder> orders) {
		for (JpoMemberOrder order : orders) {
			jpoMemberOrderDao.save(order);
		}
	}

	/**
	 * 生态家套餐订单级联删除
	 * 
	 * @param order
	 * @return
	 */
	public boolean deleteOrderByMoids(String moids) {
		return jpoMemberOrderDao.deleteOrderByMoids(moids);
	}

	/**
	 * 生态家套餐审单
	 * 
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 * @param dataType
	 * @throws AppException
	 */
	public synchronized void checkBigOrder(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType) throws AppException {

		log.info("check Order start ,orderNo is:" + jpoMemberOrder.getMemberOrderNo());
		String limitcheckorder = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "limitcheckorder");
		int freeStatus = jpoMemberOrder.getSysUser().getJmiMember().getFreezeStatus();
		String order_Type = jpoMemberOrder.getOrderType();

		log.info("orderType is:" + order_Type + ",freeStatus is:" + freeStatus);

		if ((!order_Type.equals(Constants.AUTO_PURCHASE)) && freeStatus == Constants.FREEZE_STATUS_ONE) {
			throw new AppException("冻结会员只允许支付续约单!");
		}
		if ("1".equals(limitcheckorder)) {
			throw new AppException("系统更新,请您稍后在审单");
		}

		if (!"0".equals(jpoMemberOrder.getLocked())) {
			throw new AppException("订单已锁订");
		}
		if ("2".equals(jpoMemberOrder.getStatus())) {
			throw new AppException("订单已审核");
		}
		if ("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getActive())) {
			throw new AppException(this.getLocalLanguageByKey("checkError.Code.member", operatorSysUser));
		}
		Date date = DateUtil.getNowTimeFromDateServer();
		jpoMemberOrder.setSubmitStatus("2");
		jpoMemberOrder.setSubmitTime(date);
		jpoMemberOrder.setSubmitUserCode(operatorSysUser.getUserCode());
		jpoMemberOrder.setStatus("2");
		jpoMemberOrder.setCheckTime(date);
		jpoMemberOrder.setCheckDate(date);
		jpoMemberOrder.setCheckUserCode(operatorSysUser.getUserCode());

		int orderType = Integer.parseInt(order_Type);
		Date logCurDate = new Date();

		int businessFId = this.getJpoMemberOrderBusinessMF(jpoMemberOrder);
		if (businessFId == 10) {
			logCurDate = new Date();
			String roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "jocs.member.role.normal");
			this.getChangeJmiMemberRole(jpoMemberOrder.getSysUser(), roleId);

		} else {
			throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser) + this.getLocalLanguageByKey("checkError.Code." + businessFId, operatorSysUser));
		}
		String oldCard = "";
		String newCard = "";

		JmiMember jmiMember = jmiMemberDao.get(jpoMemberOrder.getSysUser().getUserCode());
		jmiMember.setCheckDate(jpoMemberOrder.getCheckDate());
		jmiMember.setCheckNo(jpoMemberOrder.getCheckUserCode());
		// ==============续约
		JbdPeriod bdPeriodF = jbdPeriodManager.getBdPeriodByTime(jpoMemberOrder.getCheckDate(), null);
		Integer startMonthF = bdPeriodF.getWyear() * 100 + bdPeriodF.getWmonth();

		String yearF = startMonthF.toString().substring(0, 4);
		String monthF = startMonthF.toString().substring(4, 6);
		String periodF = jbdPeriodManager.getFutureBdYearMonth(yearF, monthF, 12);

		jmiMember.setStartWeek(startMonthF);
		jmiMember.setValidWeek(Integer.parseInt(periodF));
		// ==============
		if ("2".equals(jpoMemberOrder.getSysUser().getJmiMember().getMemberType())) {
			jmiMember.setOriPv(new BigDecimal("0"));
		}

		// 新加字段，不能再下首单
		jmiMember.setNotFirst(1);
		jmiMemberDao.save(jmiMember);

		// TODO Jun 促销策略
		log.info("loginUser is:[" + jpoMemberOrder.getSysUser().getUserCode() + "] " + "and user oldLever is:[" + jpoMemberOrder.getSysUser().getJmiMember().getCardType() + "] "
				+ "and orderNo is:" + jpoMemberOrder.getMemberOrderNo());

		/*
		 * Date cur_date = Calendar.getInstance().getTime(); 正式环境未做时间同步, 才如此获取
		 */
		Date cur_date = DateUtil.getNowTimeFromDateServer();
		String stime = DateUtil.getDate(cur_date, "yyyy-MM-dd HH:mm:ss");

		List<JpmSalePromoter> spList = jpmSalePromoterManager.getSaleByDate(stime, Constants.JPMSALE_ACTIVA);

		log.info("curdate is:[" + stime + "] jpmSalePromoter list size is:" + "[" + spList.size() + "]and user newLever is:"
				+ jpoMemberOrder.getSysUser().getJmiMember().getCardType());
		try {
			for (JpmSalePromoter sp : spList) {
				if (isOrderSales(sp, jpoMemberOrder)) {// 判断团队
					// 策略类型:1折价促销, 2赠品促销,3积分促销,4订单总金额或PV
					String saleType = sp.getSaleType();
					if (saleType.equals(Constants.JPMSALE_SALETYPE_PRE)) {
						if (isOrderType(sp.getUserlevel(), jpoMemberOrder.getSysUser().getJmiMember().getMemberLevel().toString())) {
							jpoMemberOrder.getJpoMemberOrderList().addAll(bindPresentProduct(jpoMemberOrder, sp));
						}
					} else if (saleType.equals(Constants.JPMSALE_SALETYPE_INTEGRAL)) {// 积分赠送

						if ("1".equals(jpoMemberOrder.getPayByCoin()) && null != jpoMemberOrder.getDiscountAmount()) {
							// 积分支付的单pv为0
							jpoMemberOrder.setPvAmt(new BigDecimal(0));
						} else {
							// 非积分支付的单才参与送积分
							if (isOrderType(sp.getUserlevel(), newCard)) {

								// 赠送积分促销策略
								getBindIntegral(jpoMemberOrder, sp, "0", operatorSysUser, dataType);
								// 是否赠送推荐人0否, 1送
								String ispresent = sp.getIspresent();

								JsysUser ruser = jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().getRecommendJmiMember().getSysUser();

								if (StringUtils.isNotBlank(ispresent) && ispresent.equals("1") && ruser != null && ruser.getUserCode() != null) {
									getBindIntegral(jpoMemberOrder, sp, "1", operatorSysUser, dataType);
								}
							}
						}

					} else if (saleType.equals(Constants.JPMSALE_SALETYPE_ORDER)) {

						log.info("newCard is:" + newCard + " and orderCard " + jpoMemberOrder.getSysUser().getJmiMember().getMemberLevel().toString());

						if (isOrderType(sp.getUserlevel(), jpoMemberOrder.getSysUser().getJmiMember().getMemberLevel().toString())) {
							// 按订单总金额,订单类型或PV送赠品
							Set<JpoMemberOrderList> orderSet = new HashSet<JpoMemberOrderList>();
							Set<JpoMemberOrderList> orderSetBind = getOrderAmountBindProduct(jpoMemberOrder, sp);
							if (orderSetBind != null) {
								orderSet = orderSetBind;
							}
							jpoMemberOrder.getJpoMemberOrderList().addAll(orderSet);
						}
					}
				}
			}
		} catch (AppException e) {
			log.error("e1: sale promoter failed," + "orderNo is:" + jpoMemberOrder.getMemberOrderNo(), e);
			throw new AppException("e1: sale Promoter failed," + "orderNo is:" + jpoMemberOrder.getMemberOrderNo());
			// e.printStackTrace();
		} catch (Exception e) {
			log.error("e2: sale promoter failed," + "orderNo is:" + jpoMemberOrder.getMemberOrderNo(), e);
			throw new AppException("e2: sale Promoter failed," + "orderNo is:" + jpoMemberOrder.getMemberOrderNo());
			// e.printStackTrace();
		}

		// String limitIsShipments =
		// this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(),
		// "limitisshipments");//1代表自动发货即审核完订单即可发货，0表示手动处理发货
		// if("1".equals(limitIsShipments))
		// {
		// jpoMemberOrder.setIsShipping("1");//1表示已经发货
		// }else
		// {
		jpoMemberOrder.setIsShipping("0");// 表示还没有发货
		jpoMemberOrder.setIsShipments("0");
		// }
		dao.save(jpoMemberOrder);

		// 扣款
		// this.getSaveMemberOrderDeduct(jpoMemberOrder, operatorSysUser,
		// dataType);

		logCurDate = new Date();
		if (!"21".equals(jpoMemberOrder.getOrderType()) && !"24".equals(jpoMemberOrder.getOrderType())) {
			// 插入每日计算表
			JbdSummaryList jbdSummaryList = new JbdSummaryList();
			jbdSummaryList.setUserCode(jpoMemberOrder.getSysUser().getUserCode());
			jbdSummaryList.setCardType(jpoMemberOrder.getSysUser().getJmiMember().getMemberLevel().toString());
			jbdSummaryList.setInType(4);
			jbdSummaryList.setCreateTime(jpoMemberOrder.getCheckTime());
			jbdSummaryList.setOrderType(jpoMemberOrder.getOrderType());
			jbdSummaryList.setOldCheckDate(null);
			jbdSummaryList.setNewCheckDate(jpoMemberOrder.getCheckDate());
			jbdSummaryList.setPvAmt(jpoMemberOrder.getPvAmt());
			jbdSummaryList.setOldLinkNo(null);
			jbdSummaryList.setNewLinkNo(null);
			jbdSummaryList.setOldRecommendNo(null);
			jbdSummaryList.setNewRecommendNo(null);
			jbdSummaryList.setNewCompanyCode(jpoMemberOrder.getCompanyCode());
			jbdSummaryList.setUserCreateTime(null);
			jbdSummaryList.setWweek(jbdPeriodManager.getBdPeriodByTimeFormated(jpoMemberOrder.getCheckDate(), null));
			jbdSummaryListManager.save(jbdSummaryList);
		}
		jpoMemberOrder.setStatusSysMo(Long.parseLong("1"));
		jpoMemberOrderDao.save(jpoMemberOrder);
		log.info("checkOrderEnd ,orderNo is:" + jpoMemberOrder.getMemberOrderNo());
	}

	public JpoMemberOrder getOrderByType(String orderType, String userCode) {
		List<JpoMemberOrder> orderlist = jpoMemberOrderDao.getOrderByType(orderType, userCode);
		if (orderlist.isEmpty()) {
			return null;
		} else {
			return orderlist.get(0);
		}
	}

	/**
	 * 判断当前用户所属团队，是否绑定事业锦囊
	 * 
	 * @param userTemCode
	 *            用户所属团队顶点编号
	 * @return true or false
	 */
	public boolean isBindProduct(String userTemCode) {
		boolean b = false;
		Map<String, Object> temMap = jpmProductSaleNewDao.getJmiMemberTeamMap("0");
		Iterator it = temMap.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			Object key = entry.getKey();
			boolean value = (Boolean) entry.getValue();
			if (value) {
				if (key.equals(userTemCode)) {
					b = true;
					break;
				}
			}
		}
		return b;
	}

	/**
	 * 电子存折 业务判断 + 扣款
	 * 
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 * @param dataType
	 */
	@Override
	public void checkJpoMemberOrderByJfiBankbook(JpoMemberOrder jpoMemberOrder,
			JsysUser operatorSysUser, String dataType) throws AppException {
		if(checkJpoMemberOrderCanPay(jpoMemberOrder, operatorSysUser, dataType)){
			// 扣款
			this.getSaveMemberOrderDeduct(jpoMemberOrder, operatorSysUser, dataType);
			/* 支付改造
			jpoMemberOrder.setIsPay("1");
			jpoMemberOrderDao.save(jpoMemberOrder);
			*/
		}
	}

	/**
	 * 电子存折支付扣款并且发MQ消息
	 * 
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 * @param dataType
	 */
	@Override
	public void checkJpoMemberOrderByJfiBankbookSendMQ(
			JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser,
			String dataType) {
		//业务判断 + 扣款
		checkJpoMemberOrderByJfiBankbook(jpoMemberOrder, operatorSysUser, dataType);
		
		//发送mq
		jpoMemberOrderPayedSendToMQ(jpoMemberOrder, operatorSysUser, dataType);
		
	}

	/**
	 * 扣款业务判断 2015-9-21 w
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 * @param dataType
	 * @throws AppException
	 */
	@Override
	public boolean checkJpoMemberOrderCanPay(JpoMemberOrder jpoMemberOrder,
			JsysUser operatorSysUser, String dataType) {
		// TODO Auto-generated method stub
		boolean b = true;
		try {
			
		
			log.info("pay Order start ,orderNo is:" + jpoMemberOrder.getMemberOrderNo());
			String limitcheckorder = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "limitcheckorder");
			if ("1".equals(limitcheckorder)) {
				b=false;
				throw new AppException("系统更新,请您稍后在审单");
			}
			if (!this.getCheckOrderAmount(jpoMemberOrder)) {
				b=false;
				throw new AppException("订单总金额不一致");
			}
			if (!"0".equals(jpoMemberOrder.getLocked())) {
				b=false;
				throw new AppException("订单已锁订");
			}
			if ("2".equals(jpoMemberOrder.getStatus())) {
				b=false;
				throw new AppException("订单已审核");
			}
			if ("1".equals(jpoMemberOrder.getIsPay())) {
				b=false;
				throw new AppException("订单已支付");
			}
			if ("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getActive())) {
				b=false;
				throw new AppException(this.getLocalLanguageByKey("checkError.Code.member", operatorSysUser));
			}
			
			if(jpoMemberOrder.getSysUser().getJmiMember().getFreezeStatus() == 1 && !jpoMemberOrder.getOrderType().equals(Constants.AUTO_PURCHASE)){
				throw new AppException("会员已冻结!");
			}
			
			/*支付改造 bug:2515*/
			if(jpoMemberOrder.getOrderType().equals("1") && 
					jpoMemberOrder.getSysUser().getJmiMember().getNotFirst()==1){
				b=false;
				throw new AppException("已存在首购单");
			}
			
			JsysUser sysUserSp = jsysUserDao.get(jpoMemberOrder.getUserSpCode());
			if (!sysUserSp.getCompanyCode().equals(jpoMemberOrder.getCompanyCode()) && !"1".equals(jpoMemberOrder.getCompanyPay())) {
				b=false;
				throw new AppException("扣款人与订单不同国别");
			}
	
			BigDecimal sumPrice = new BigDecimal(0);
			BigDecimal sumPV = new BigDecimal(0);
	
			Iterator<JpoMemberOrderList> itsTmp1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
			while (itsTmp1.hasNext()) {
				JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) itsTmp1.next();
				sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
				sumPV = sumPV.add(jpoMemberOrderList.getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
			}
			Iterator itsTmp2 = jpoMemberOrder.getJpoMemberOrderFee().iterator();
			while (itsTmp2.hasNext()) {
				JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) itsTmp2.next();
				sumPrice = sumPrice.add(jpoMemberOrderFee.getFee());
			}
			Date logCurDate = new Date();
			Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
			if ("CN".equals(jpoMemberOrder.getCompanyCode())) {
				while (its1.hasNext()) {
					JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
					String status = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getStatus();
					if ("0".equals(status)) {
						throw new AppException("产品(" + jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo() + ")已售完!");
					}
				}
			}
	
			// Date date =new Date(); //数据时间jmiMemberDao.getDsDate();
			Date date = DateUtil.getNowTimeFromDateServer();
//			jpoMemberOrder.setSubmitStatus("2");
//			jpoMemberOrder.setSubmitTime(date);
//			jpoMemberOrder.setSubmitUserCode(operatorSysUser.getUserCode());
	//		jpoMemberOrder.setIsSended(0l); // 未推送给oms
			int orderType = Integer.parseInt(jpoMemberOrder.getOrderType());
	
			switch (orderType) {
			case 1:// 会员首购
				int businessFId = this.getJpoMemberOrderBusinessMF(jpoMemberOrder);
				if (businessFId == 10) {
					logCurDate = new Date();
					if (!"0".equals(jpoMemberOrder.getSysUser().getJmiMember().getCardType())) {
						throw new AppException("会员不处于待审状态");
					}
	
				} else {
					b=false;
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessFId, operatorSysUser));
				}
	
				break;
			case 2://会员升级
				int businessUId = this.getJpoMemberOrderBusinessMU(jpoMemberOrder);
				if(businessUId!=20){
					throw new AppException("已过升级时间或角色有误！");
				} 
				break;
			case 3:// 会员续约
				int businessRSId = this.getJpoMemberOrderBusinessMRS(jpoMemberOrder);
				if (businessRSId == 30) {
	
					if (jpoMemberOrder.getSysUser().getJmiMember().getFreezeStatus() == 0) {
						b=false;
						throw new AppException("会员处于解冻状态！");
					} else if (jpoMemberOrder.getSysUser().getJmiMember().getFreezeStatus() == 1) {
	
						// 冻，126PV订单
						// ==============续约当年
	
					} else {
						b=false;
						throw new AppException("冻结状态不明确");
					}
	
				} else {
					b=false;
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessRSId, operatorSysUser));
				}
				break;
	
			case 4:// 会员重销
				int businessRId = this.getJpoMemberOrderBusinessMR(jpoMemberOrder);
				if (businessRId == 40) {
	
				} else {
					b=false;
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessRId, operatorSysUser));
				}
				break;
			case 5:// 辅销品订单
				int businessAId = this.getJpoMemberOrderBusinessMA(jpoMemberOrder);
				if (businessAId == 40) {
	
				} else {
					b=false;
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessAId, operatorSysUser));
				}
				break;
			case 6:// 店铺首购
				int businessSFId = this.getJpoMemberOrderBusinessSF(jpoMemberOrder);
				if (businessSFId == 60) {
	
					BigDecimal amount = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.f.order.amount"));
					BigDecimal pvAmt = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.f.order.pvamt"));
	
					if ("LC".equals(jpoMemberOrder.getProductType())) {
						if (pvAmt.compareTo(jpoMemberOrder.getPvAmt()) != 1) {
	
						} else {
							b=false;
							throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser) + "订购金额不正确");
						}
					} else {
						if (amount.compareTo(sumPrice) != 1) {
	
						} else {
							b=false;
							throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser) + "订购金额不正确");
						}
					}
	
				} else {
					b=false;
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessSFId, operatorSysUser));
				}
				break;
			case 7:// 店铺升级单
				break;
			case 8:// 店铺返单
				break;
			case 9:// 店铺重销
				int businessSRId = this.getJpoMemberOrderBusinessSR(jpoMemberOrder);
				if (businessSRId == 90) {
	
				} else {
					b=false;
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessSRId, operatorSysUser));
				}
				break;
			case 11:// 二级馆首购单
				int businessSSubFId = this.getJpoMemberOrderBusinessSSubF(jpoMemberOrder);
				if (businessSSubFId == 60) {
					String language = jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase();
	
					BigDecimal pvamt = new BigDecimal(Constants.sysConfigMap.get(language).get("store.f2.order.pvamt"));
					BigDecimal amount = new BigDecimal(Constants.sysConfigMap.get(language).get("store.f2.order.amount"));
	
					JsysUser user = jpoMemberOrder.getSysUser();
	
					String temCode = jmiTeamManager.teamStr(user);
	//				if (temCode.equalsIgnoreCase("CN12365064")) {
	//					amount = new BigDecimal(Constants.sysConfigMap.get(language).get("store.f2.order.amount_ygd"));
	//				}
	
					if ("LC".equals(jpoMemberOrder.getProductType()) && pvamt.compareTo(jpoMemberOrder.getPvAmt()) != 1) {
	
					} else if (!"LC".equals(jpoMemberOrder.getProductType())) {
						if (amount.compareTo(sumPrice) == 1) {
							throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser) + "订购金额不正确");
						}
						if ("HK".equals(jpoMemberOrder.getCompanyCode())) {
							BigDecimal pv = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.f2.order.pv"));
							if (pv.compareTo(jpoMemberOrder.getPvAmt()) == 1) {
								throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser) + "订购PV不正确");
							}
						}
					} else {
						b=false;
						throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser) + "订购金额不正确");
					}
	
				} else {
					b=false;
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessSSubFId, operatorSysUser));
				}
	
				break;
			case 12:// 二级馆升级单
				int businessSSubUId = this.getJpoMemberOrderBusinessSSubU(jpoMemberOrder);
				if (businessSSubUId == 90) {
					BigDecimal pv = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.u2.order.pv"));
					BigDecimal amount = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.u2.order.amount"));
	
					if ("LC".equals(jpoMemberOrder.getProductType())) {
						pv = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.u2.orderlc.pv"));
						amount = new BigDecimal("0");
					}
	
					java.util.Calendar startc = java.util.Calendar.getInstance();
					startc.set(2011, 6, 16, 00, 00, 00);
					java.util.Calendar endc = java.util.Calendar.getInstance();
					endc.set(2011, 7, 6, 00, 00, 00);
					java.util.Date startDate = startc.getTime();
					java.util.Date endDate = endc.getTime();
					Date curDate = new Date();
					if ((curDate.after(startDate)) && (curDate.before(endDate))) {
						Iterator its11 = jpoMemberOrder.getJpoMemberOrderList().iterator();
						while (its11.hasNext()) {
							JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its11.next();
							if ("P13010200201CN0".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())) {
								pv = new BigDecimal("0");
							}
						}
					}
	
					if (pv.compareTo(jpoMemberOrder.getPvAmt()) < 1) {
						if (amount.compareTo(sumPrice) < 1) {
	
						} else {
							b=false;
							throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser) + "订购金额不正确");
						}
					} else {
						b=false;
						throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser) + "订购金额不正确");
					}
	
				} else {
					b=false;
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessSSubUId, operatorSysUser));
				}
				break;
			case 13:
				break;
			case 14:// 二级馆重销单
				int businessSSubRId = this.getJpoMemberOrderBusinessSSubR(jpoMemberOrder);
				if (businessSSubRId == 90) {
	
				} else {
					b=false;
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessSSubRId, operatorSysUser));
				}
				break;
			case 15:// 会员AUTOSHIP
				int businessASId = this.getJpoMemberOrderBusinessAS(jpoMemberOrder);
				if (businessASId == 40) {
	
				} else {
					b=false;
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessASId, operatorSysUser));
				}
				break;
			case 21:// 活力小铺首单
				int businessMFId = this.getJpoMemberOrderBusinessSMF(jpoMemberOrder);
				if (businessMFId == 90) {
					BigDecimal amount = new BigDecimal(this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "store.m.order.amount"));
					BigDecimal pv = new BigDecimal(this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "store.m.order.pv"));
					if (amount.compareTo(sumPrice) == 1) {
						b=false;
						throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser) + "订购金额不正确");
					}
					if (pv.compareTo(jpoMemberOrder.getPvAmt()) == 1) {
						b=false;
						throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser) + "订购PV不正确");
					}
				} else {
					b=false;
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessMFId, operatorSysUser));
				}
				break;
			case 24:// 活力小铺重销单
				int businessMRId = this.getJpoMemberOrderBusinessSMR(jpoMemberOrder);
				if (businessMRId == 90) {
	
				} else {
					b=false;
					throw new AppException(this.getLocalLanguageByKey("checkError.Code", operatorSysUser)
							+ this.getLocalLanguageByKey("checkError.Code." + businessMRId, operatorSysUser));
				}
				break;
			}
	
			log.info("checkPayOrderEnd ,orderNo is:" + jpoMemberOrder.getMemberOrderNo());
			
		} catch (Exception e) {
			log.error("",e);
			b = false;
			throw new AppException("payCheck failed," +
					"orderNo is:"+jpoMemberOrder.getMemberOrderNo() );
		}

		return b;
	}

	/**
	 * 支付成功的订单发送mq消息 2015-9-21 w
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 * @param dataType
	 */
	@Override
	public void jpoMemberOrderPayedSendToMQ(JpoMemberOrder jpoMemberOrder,
			JsysUser operatorSysUser, String dataType) {
		try {

			JpoCheckedFailed falid = new JpoCheckedFailed();
			falid.setJpoMemberOrder(jpoMemberOrder);
			falid.setOperatorSysuser(operatorSysUser);
			falid.setDataType(dataType);
			// 保存记录
			checkedFailedManager.save(falid);
			
			JpoMemberOrderCheckModel checkModel = new JpoMemberOrderCheckModel();
			checkModel.setDataType(dataType);
			checkModel.setJpoMemberOrder(jpoMemberOrder);
			checkModel.setJsysUser(operatorSysUser);
			/* 支付改造
			messageProducer.send(checkModel);
			*/
			// 审核中
//			jpoMemberOrder.setStatus("3");
//			jpoMemberOrderDao.save(jpoMemberOrder);

		} catch (AppException e) {

			throw new AppException("发送MQ消息失败!");
		} catch (Exception e) {

			throw new AppException("发送MQ消息失败!!");
		}
		
	}

	/**
	 * 更新订单经销商字段
	 * 
	 * @param orderId
	 * @param saleNo
	 */
	@Override
	public void updateJpoMemberOrderSaleNo(String orderId, String saleNo) {

		jpoMemberOrderDao.updateJpoMemberOrderSaleNo(orderId, saleNo);
	}

	public void saveOrderAndMember(JpoMemberOrder jpoMemberOrder,JmiMember jmiMember){
		dao.save(jpoMemberOrder);
		jmiMemberDao.save(jmiMember);
	}
	
	/**
	 * 
	 * @param order
	 * @param note
	 * @param dataType
	 * @param payType <p>1 第三方支付  2 电子存折支付 </br> 3 基金支付  4 电子存折+积分</br>
	 * 					5 电子存折+基金 6 电子存折+第三方 </br> 7 积分支付</p> 
	 * @param moneyType 资金类别
	 */
	public void saveBankBookPayList(JpoMemberOrder order,String dataType,Integer moneyType){
		JpoBankBookPayList bbp = new JpoBankBookPayList();
		
		bbp.setAmount(order.getAmount());
		bbp.setCreateTime(DateUtil.getNowTimeFromDateServer());
		bbp.setDataType(dataType);
		
		int payType = obtainPayType(order);
		log.info("payType is:"+payType+" and order ispay:"+order.getIsPay());
		
		switch (payType) {
		case 2:
			bbp.setDzAmt(order.getAmount());
			break;
		case 3:
			bbp.setJjAmt(order.getJjAmount());
			break;
		case 4:
			bbp.setDzAmt(order.getAmount());
			bbp.setJfAmt(order.getDiscountAmount());
			break;
		case 5:
			//2017-1-1 w
			bbp.setDzAmt(order.getAmount().subtract(order.getJjAmount()));
			bbp.setJjAmt(order.getJjAmount());
			break;
		case 7:
			bbp.setJfAmt(order.getDiscountAmount());
			break;
		case 16://产品积分支付类型判断，add by hdg 2017-01-04
			bbp.setDzAmt(order.getAmount().subtract(order.getProductPointAmount()));
			bbp.setProductAmt(order.getProductPointAmount());
			break;
		case 17://全额产品积分支付
			bbp.setProductAmt(order.getProductPointAmount());
			break;
		case 18://代金券支付 Modify By WuCF 20170522
			if(order.getCpValue()!=null){
				bbp.setCpValue(order.getCpValue());//代金券金额
				bbp.setDzAmt(order.getAmount().subtract(order.getCpValue()));
			}else{
				bbp.setDzAmt(order.getAmount());
			}
			
			if(order.getUserCpId()!=null){//会员拥有的代金券关系表主键
				bbp.setCpId(order.getUserCpId());
			}
			break;
		case 19://发展基金+代金券
			if(order.getCpValue()!=null){
				bbp.setCpValue(order.getCpValue());//代金券金额
				bbp.setJjAmt(order.getAmount().subtract(order.getCpValue()));
			}else{
				bbp.setJjAmt(order.getAmount());
			}
			
			if(order.getUserCpId()!=null){//会员拥有的代金券关系表主键
				bbp.setCpId(order.getUserCpId());
			}
			break;
		case 20://抵用券+代金券
			if(order.getCpValue()!=null){
				bbp.setCpValue(order.getCpValue());//代金券金额
				bbp.setProductAmt(order.getAmount().subtract(order.getCpValue()));
			}else{
				bbp.setProductAmt(order.getAmount());
			}
			if(order.getUserCpId()!=null){//会员拥有的代金券关系表主键
				bbp.setCpId(order.getUserCpId());
			}
			break;
		case 22://产品积分支付类型判断 抵用券+基金
			bbp.setJjAmt(order.getAmount().subtract(order.getProductPointAmount()));
			bbp.setProductAmt(order.getProductPointAmount());
			break;
		default:
			throw new AppException("money error.");
		}
		
		bbp.setInType(payType);
		bbp.setMemberOrderNo(order.getMemberOrderNo());
		bbp.setMoneyType(moneyType);
		bbp.setMoneyType1(moneyType);
		bbp.setCheckUserCode(order.getUserSpCode());
		bbp.setOrderType(order.getOrderType());
		bbp.setTeamCode(jmiTeamManager.teamStr(order.getSysUser()));
		bbp.setUserCode(order.getSysUser().getUserCode());
		bbp.setUserSPcode(order.getUserSpCode());
		
		String notes = getLocalLanguageByKey("poMemberOrder.orderConfirm") + 
				order.getMemberOrderNo();
		
		String userSpCode = order.getUserSpCode();
		if(userSpCode.equals(bbp.getUserCode())){
			notes += "," + userSpCode;
		} else {
			notes += "," + bbp.getUserCode();
		}
		
//		Iterator iter = order.getJpoMemberOrderFee().iterator();
//		while (iter.hasNext()) {
//			JpoMemberOrderFee fee = (JpoMemberOrderFee)iter.next();
//			if(fee.getFeeType().equals("1")){
//				notes += ",其中物流费为:"+fee.getFee().toString();
//				break;
//			}
//		}
		
		bbp.setNotes(notes);
		jpoBankBookPayListDao.save(bbp);
	}
	
	/**
	 * 
	 * @param order
	 * @return 
	 * <p>1 第三方支付  2 电子存折支付 </br> 
	 * 3 基金支付  4 电子存折+积分</br>
	 * 5 电子存折+基金 6 电子存折+第三方 </br>
	 * 7 积分支付</p> 
	 */
	private int obtainPayType(JpoMemberOrder order){
		int payType = 2;
	
		/* 电子存折 + 基金支付*/
//		if(order.getAmount().compareTo(new BigDecimal(0)) > 0 && 
//				order.getJjAmount().compareTo(new BigDecimal(0)) > 0){
//			payType=5; 
//		} else if(order.getAmount().compareTo(new BigDecimal(0)) > 0 && 
//				order.getDiscountAmount().compareTo(new BigDecimal(0)) > 0){
//			/*电子存折+积分*/
//			payType = 4;
//		} else if(order.getAmount().compareTo(new BigDecimal(0)) > 0 && order.getProductPointAmount()!=null && 
//				order.getProductPointAmount().compareTo(new BigDecimal(0)) > 0){
//			/*产品积分支付 + 电子存折*/
//			payType=16;
//		} else if(order.getAmount().compareTo(new BigDecimal(0)) > 0 && 
//				order.getJjAmount().compareTo(new BigDecimal(0)) <= 0){
//			/*电子存折*/
//			payType=2;
//		} else if(order.getAmount().compareTo(new BigDecimal(0)) <= 0 && 
//				order.getJjAmount().compareTo(new BigDecimal(0)) > 0){
//			/*基金支付*/
//			payType = 3;
//		} else if(order.getAmount().compareTo(new BigDecimal(0)) <= 0 && 
//				order.getDiscountAmount().compareTo(new BigDecimal(0)) > 0){
//			/*积分支付*/
//			payType=7;
//		}
		
		if(order.getAmount().compareTo(order.getJjAmount())==0){
			/*基金支付*/
			payType = 3;
		} else if(order.getJjAmount().compareTo(new BigDecimal(0))>0 &&
			   (order.getProductPointAmount()==null || order.getProductPointAmount().compareTo(new BigDecimal(0))==0)&&
				order.getJjAmount().compareTo(order.getAmount()) < 0){
			/* 电子存折 + 基金支付*/
			payType=5; 
		} else if(order.getProductPointAmount()!=null && 
				order.getAmount().compareTo(order.getProductPointAmount())==0){
			/*产品积分支付*/
			payType = 17;
		} else if(order.getProductPointAmount()!=null && 
				order.getProductPointAmount().compareTo(new BigDecimal(0))>0 &&
				(order.getJjAmount()==null || order.getJjAmount().compareTo(new BigDecimal(0))==0)&&
				order.getProductPointAmount().compareTo(order.getAmount()) < 0){
			/*产品积分支付 + 电子存折*/
			payType=16;
		} else if(order.getProductPointAmount()!=null &&
			order.getProductPointAmount().compareTo(new BigDecimal(0))>0 &&
			(order.getJjAmount()!=null || order.getJjAmount().compareTo(order.getAmount()) < 0)&&
			order.getProductPointAmount().compareTo(order.getAmount()) < 0){
			/*产品积分支付 + 基金*/
			payType=22;
		} else if(order.getDiscountAmount().compareTo(new BigDecimal(0))>0){
			/*积分支付*/
			payType=4;
		}else if(order.getPayByCp()!=null && //代金券支付
					order.getCpValue().compareTo(new BigDecimal(0))>0){
				/*代金券支付*/
				payType=18;
		} else if(order.getJjAmount().compareTo(new BigDecimal(0))==0 && 
				order.getDiscountAmount().compareTo(new BigDecimal(0))==0 &&
				order.getProductPointAmount()!=null &&
				order.getProductPointAmount().compareTo(new BigDecimal(0))==0) {
			/*电子存折*/
			payType=2;
		}/*else if(order.getAmount().compareTo(new BigDecimal(0)) <= 0 && 
				order.getDiscountAmount().compareTo(new BigDecimal(0)) > 0){
			积分支付
			payType=7;
		}*/
		
		if("93".equals(order.getOrderType())){//如果订单类型是代金券订单，无论是否使用代金券，也强行payType：18
			if("1".equals(order.getPayByJj())){
				payType=19;
			}else if("1".equals(order.getPayByProduct())){
				payType=20;
			}else{
				payType=18;
			}
		}
		
		log.info("order pay model is:"+payType);
		return payType;
	}

	@Override
	public JpoMemberOrder newOrder(JpoMemberOrder jpoMemberOrder) {
		JpoMemberOrder order = new JpoMemberOrder();
		order.setPayByJj(jpoMemberOrder.getPayByJj());
		order.setJjAmount(jpoMemberOrder.getJjAmount());
		order.setAmount(jpoMemberOrder.getAmount());
		order.setJpoMemberOrderList(jpoMemberOrder.getJpoMemberOrderList());
    	order.setMemberOrderNo(jpoMemberOrder.getMemberOrderNo());
    	order.setUserSpCode(jpoMemberOrder.getUserSpCode());
    	order.setDiscountAmount(jpoMemberOrder.getDiscountAmount());
    	order.setOrderType(jpoMemberOrder.getOrderType());
    	order.setSysUser(jpoMemberOrder.getSysUser());
    	order.setJpoMemberOrderFee(jpoMemberOrder.getJpoMemberOrderFee());
    	order.setPvAmt(jpoMemberOrder.getPvAmt());
    	order.setDiscountPvAmt(jpoMemberOrder.getDiscountPvAmt());
    	order.setAmount2(jpoMemberOrder.getAmount2());
    	order.setLocked(jpoMemberOrder.getLocked());
    	order.setLocked1(jpoMemberOrder.getLocked1());
    	order.setLocked2(jpoMemberOrder.getLocked2());
    	order.setStatus(jpoMemberOrder.getStatus());
    	order.setIsPay(jpoMemberOrder.getIsPay());
    	order.setCompanyCode(jpoMemberOrder.getCompanyCode());
		return order;
	}
	
	@Override
	public void updateJpoMemberOrderPaymentType(String moid,String paymentType) {
		jpoMemberOrderDao.updateJpoMemberOrderPaymentType(moid, paymentType);
	}

	/**
	 * 删除订单，需要备份到临时表中
	 */
	@Override
	public void removeJpoMemberOrder(String moId) {
		//删除订单
		jpoMemberOrderDao.removeJpoMemberOrder(moId);
	}
	
	/**
     * 升级单升级期限
     * @return  true 可以下升级单 
     */
    public boolean upGradeInTime(String userCodeStr,String orderId){
    	JsysUser sysUser = jsysUserDao.getSysUser(userCodeStr.trim());//获得会员对象
    	JpoMemberOrder jpoMemberOrder = this.get(Long.parseLong(orderId));
    	if(!"2".equals(jpoMemberOrder.getOrderType())){//非升级单  直接跳过,不验证
    		return true;
    	}
    	
    	String upgrade_Month_on = ConfigUtil.getConfigValue(sysUser.getCompanyCode(),
		"member_upgrade_on"); //有期限
    	String upgrade_Month = ConfigUtil.getConfigValue(sysUser.getCompanyCode(),
				"member_upgrade");  //天
		
    	String userCode = sysUser.getUserCode();
		JsysUserRole userRole = jsysUserRoleManager.getSysUserRoleByUserCode(userCode);
		JsysRole role = jsysRoleManager.get(userRole.getRoleId());
		 
		Map<String,String> orderFirstMap = new HashMap<String,String >();
		orderFirstMap.put("userCode", sysUser.getUserCode());
		orderFirstMap.put("companyCode", sysUser.getCompanyCode());
		orderFirstMap.put("orderType", Constants.ORDER_TYPE_1);
		orderFirstMap.put("status", "2");
		List orderList = getOrderByParam(orderFirstMap);
		
		Calendar curDate = Calendar.getInstance();
		Calendar endDate = Calendar.getInstance();
		
		JpoMemberOrder orderF = new JpoMemberOrder();
		if(orderList == null){
			endDate.setTime(sysUser.getJmiMember().getCheckDate()); 
		}else{
			orderF = (JpoMemberOrder)orderList.get(0);
			endDate.setTime(orderF.getCheckTime()); 
		}
		
		endDate.add(Calendar.DAY_OF_MONTH, 1);
		endDate.set(Calendar.HOUR_OF_DAY, 0);
		endDate.set(Calendar.MINUTE, 0);
		endDate.set(Calendar.SECOND, 0);
		endDate.set(Calendar.DATE, endDate.get(Calendar.DATE)+Integer.parseInt(upgrade_Month)); //天数
		
//		endDate.set(Calendar.MONTH, endDate.get(Calendar.MONTH)+Integer.parseInt(upgrade_Month));//月份期限
// 		endDate.set(Calendar.DAY_OF_MONTH, 1);
		
		log.info("............userCode is:"+userCode+" roleCode is:"+role.getRoleCode());
		log.info("优惠顾客:.."+role.getRoleCode().equalsIgnoreCase(Constants.ROLEID1));
		
		if(role.getRoleCode().equalsIgnoreCase(Constants.CN_MEMBER_1000)){
 			//优惠顾客升级截止日期
 			String startDateS = ConfigUtil.getConfigValue("CN", "member.roid.1.upgradedate");
 			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		    try {
 		    	if(startDateS != null){
 		    		Calendar up_DATE = Calendar.getInstance();
 	 		    	up_DATE.setTime(sdf.parse(startDateS));
 		    		if(curDate.after(up_DATE)){
 	 					return false;
 	 				}
 		    	}else{
 		    		log.info("member.roid.1.upgradedate 时间参数未设置.");
 		    	}
 				
 			} catch (ParseException e) {
 				log.info("member.roid.1.upgradedate 时间参数有误.");
 				e.printStackTrace();
 			}
 		} else {
 			
 			if(upgrade_Month_on.equals("1") && curDate.after(endDate)){
 				log.info("");
 	 			return  false;
 	 		}
 		}
 		
		/*if(!role.getRoleCode().equalsIgnoreCase(Constants.ROLEID1) && upgrade_Month_on.equals("1") && curDate.after(endDate)){
			return  false;
			//已过升级时间
//			msgList.add(LocaleUtil.getLocalText(
//					jpoShoppingCartOrder.getSysUser().getDefCharacterCoding(),
//					"miMember.isNotUpGrade"));
		}
		
	    //优惠顾客升级截止日期
	    String startDateS = ConfigUtil.getConfigValue("CN", "member.roid.1.upgradedate");
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	    
	    try {
			Date up_DATE = sdf.parse(startDateS);
			if(role.getRoleCode().equalsIgnoreCase(Constants.ROLEID1) && curDate.after(up_DATE)){
				return false;
			}
			
		} catch (ParseException e) {
			log.info("member.roid.1.upgradedate 时间参数有误.");
			e.printStackTrace();
		}*/
 		
 	
		
		return true; 
    	
    }


	public boolean checkCartProduct(String myUserCode) {
		//根据会员编号获取该会员身份证
		String paperNumber = jpoMemberOrderDao.getPaperNumber(myUserCode);
		//根据身份证号码 查询所有的会员
		List<JmiMember> jmimemberList = jpoMemberOrderDao.getJmimemberList(paperNumber);
		//判断该身份证下的会员是否已经购买过398会员首单了
		for (JmiMember jmiMember : jmimemberList) {
			String userCode = jmiMember.getUserCode();
			Map map = new HashMap<>();
			map.put("userCode", userCode);
			map.put("companyCode", "CN");
			map.put("orderType", "42");
			List<JpoMemberOrder> order = jpoMemberOrderDao.getOrderByParam(map);
			if (order != null && order.size() > 0) {
				return false;
			}

		}
		return true;
	}
	
	
	/**
     * 代金券支付
     * @param jpoMemberOrder
     * @param operatorSysUser
     * @param dataType
     * @throws Exception
     * @author hdg 2017-01-03
     */
	public void checkJpoMemberOrderByCpValue(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType) throws Exception {

		String userSpCode = jpoMemberOrder.getUserSpCode();
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = jpoMemberOrder.getProductPointAmount();
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 13;
		String[] notes = new String[1];
		notes[0] = this.getLocalLanguageByKey("poMemberOrder.orderConfirm", operatorSysUser) + jpoMemberOrder.getMemberOrderNo();
		if (!userSpCode.equals(jpoMemberOrder.getSysUser().getUserCode())) {
			notes[0] += "," + jpoMemberOrder.getSysUser().getUserCode();
		}
		JsysUser sysUserSp = jsysUserDao.get(userSpCode);

		// 判断订单是否包含旅游积分补款产品,决定资金类别是否为24
		if (this.jpoMemberOrderIsIncludeTourCoin(jpoMemberOrder)) {
			moneyType[0] = 24;
		}
		log.info("checkJpoMemberOrderByCp start:"+jpoMemberOrder.getMemberOrderNo()+" " + "Thread id:"+Thread.currentThread().getId());
		//抵用券调用：
		checkJpoMemberOrderByCp(jpoMemberOrder, operatorSysUser, dataType);
		log.info("checkJpoMemberOrderByCp stop:"+jpoMemberOrder.getMemberOrderNo()+" " + "Thread id:"+Thread.currentThread().getId());
	}
	
}
