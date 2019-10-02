package com.joymain.ng.service.impl;

import com.joymain.ng.util.ListUtil;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JmiRecommendRefDao;
import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.handle.GlobalVar;
import com.joymain.ng.model.FiBankbookBalance;
import com.joymain.ng.model.FiTransferAccount;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiRecommendRef;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiBankbookBalanceManager;
import com.joymain.ng.service.FiTransferAccountManager;
import com.joymain.ng.service.JbdMemberFrozenManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.service.MobilePaymentManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.webapp.util.OrderCheckUtils;


@Service("mobilePaymentManager")
@SuppressWarnings({ "unchecked", "rawtypes" })
@WebService(serviceName = "MobilePaymentService", endpointInterface = "com.joymain.ng.service.MobilePaymentManager")
public class MobilePaymentManagerImpl implements MobilePaymentManager {

	protected final transient Log log = LogFactory.getLog(getClass());
	@Autowired
	private JpoMemberOrderManager jpoMemberOrderManager;
	@Autowired
	private FiTransferAccountManager fiTransferAccountManager;
	@Autowired
	private JsysUserManager sysUserManager = null;
	@Autowired
	private JmiRecommendRefDao jmiRecommendRefDao;
	
	@Autowired
	private FiBankbookBalanceManager fiBankbookBalanceManager = null;
	
	private static final String SUCCESS_CODE = "s000";
	
	@Autowired
	private JpoMemberOrderDao jpoMemberOrderDao;
	
	@Autowired
	private JbdMemberFrozenManager jbdMemberFrozenManager;
	
	/**
	 * @Description 判断指定商品在订单中是否存在， 如有存在则返回true
	 * @author houxyu
	 * @date 2016-5-19
	 * @param jpoMemberOrder
	 * @param jpoList
	 * @return
	 */
	protected boolean jpoIsOnly(JpoMemberOrder jpoMemberOrder,List<String> jpoList){
		boolean isOnly = false;
		
		Iterator its2 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while(its2.hasNext()){
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its2.next();
    		String carProNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();//商品
    		
    		if(jpoList.contains(carProNo)){
    			isOnly = true;
    		}
    		
    		if(!jpoList.contains(carProNo)){
				isOnly = false;
				break;
			}
		}
		return isOnly;
	}

	/**
	 * 支付他人订单
	 */
	public String payOthersOrder(JsysUser jsysUser,String jsonMsg) {
		String errMsg = "未知错误";
		String code = "e001";
		JpoMemberOrder jpoMemberOrder = null;// 支付订单
		try {
			Map<String, String> map = parseJSON2MapString(jsonMsg);
			String userCode = map.get("userCode");//要支付的用户编码
			String orderId = map.get("orderId");
			String payJJ = map.get("payJJ");
			String password = map.get("password");
			while (true) {
				
				if (!StringUtil.isEmpty(userCode) && !checkUnderMember(jsysUser,userCode)) {
					errMsg = "会员不存在或不在推荐网络下!";
					break;
				}
				
				if (!StringUtils.isEmpty(orderId)) {
					jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(orderId);
					
					//=====================Modify By WuCF 20170417 升级单针对期限90天的控制
					if(!jpoMemberOrderManager.upGradeInTime(userCode,String.valueOf(jpoMemberOrder.getMoId()))){//被支付人的首单定制时间
						errMsg = "此订单已过升级单支付期限!";
						break;
					}
					
					//Modify By WuCF 20170115 克隆对象，用来作为传值的对象 
					JpoMemberOrder jpoMemberOrderTemp = (JpoMemberOrder)jpoMemberOrder.clone();
					
					JsysUser  user = sysUserManager.get(jpoMemberOrderTemp.getSysUser().getUserCode());
					if(validateOrder(jpoMemberOrderTemp, user)){
						errMsg = LocaleUtil.getLocalText("user.validate");
						break;
					}
					//Modify BY WuCF 20170120 支付异常信息详细提示
					if (!jsysUser.getCompanyCode().equals(jpoMemberOrderTemp.getCompanyCode())) {
						errMsg = LocaleUtil.getLocalText("opration.pay.fail")+ "!支付人国别与订单国别不一致!";
						break;
					}
					if (StringUtils.isEmpty(orderId) || jpoMemberOrderTemp == null) {
						errMsg = LocaleUtil.getLocalText("opration.pay.fail")+ "!此订单编号不存在！";
						break;
					}
					if ("2".equals(jpoMemberOrderTemp.getStatus())) {
						errMsg = LocaleUtil.getLocalText("opration.pay.fail")+ "!此订单已审核，不能重复支付！";
						break;
					}
					if (!jpoMemberOrderTemp.getSysUser().getUserCode().equals(userCode)) {
						errMsg = LocaleUtil.getLocalText("opration.pay.fail")+ "!他人支付方式不能支付会员自己的订单！";
						break;
					}
					/*if ("30".equals(jpoMemberOrderTemp.getOrderType())) {
						errMsg = LocaleUtil.getLocalText("opration.pay.fail")+ "!积分换购订单类型不能使用他人支付方式！";
						break;
					}
					if ("93".equals(jpoMemberOrder.getOrderType())) {// || "42".equals(jpoMemberOrder.getOrderType()) || "43".equals(jpoMemberOrder.getOrderType())
						errMsg = LocaleUtil.getLocalText("opration.pay.fail")+ "!代金券换购单类型不能使用他人支付方式！";
						break;
					}*/
					if(OrderCheckUtils.checkOrderType(jpoMemberOrder)){
						String displayValues=
							ListUtil.getListValue(jpoMemberOrder.getCompanyCode(), "orderpayother.limit",jpoMemberOrder.getOrderType());
						errMsg = LocaleUtil.getLocalText("opration.pay.fail")+"!"+ displayValues+"!类型不能使用他人支付方式!";
						break;
					}
					/*if (StringUtils.isEmpty(orderId) || "30".equals(jpoMemberOrderTemp.getOrderType()) || jpoMemberOrderTemp == null
							|| !jsysUser.getCompanyCode().equals(jpoMemberOrderTemp.getCompanyCode()) || "2".equals(jpoMemberOrderTemp.getStatus())
							|| !jpoMemberOrderTemp.getSysUser().getUserCode().equals(userCode)) {
						errMsg = LocaleUtil.getLocalText("opration.pay.fail")+ "该订单不允许他人支付!";
						break;
					}*/
					
					//============================2016-05-24圣诗蔓积分换购(>=5:5)================================================start
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    	Date now = Calendar.getInstance().getTime();
					String startCoin = ConfigUtil.getConfigValue("CN", "201605coincx.startdate");
			    	String endCoin = ConfigUtil.getConfigValue("CN", "201605coincx.enddate");
			    	try {
				    	if(startCoin != null && endCoin != null){
							if(now.after(sdf.parse(startCoin)) && now.before(sdf.parse(endCoin))){
								if(jpoIsOnly(jpoMemberOrderTemp,GlobalVar.jpoList20160524)){
									errMsg = "圣诗蔓积分换购订单不允许他人支付!";
									break;
								}
							}
						}
				    }catch(Exception e){
				    	log.info("支付跳转失败!",e);
			    	}

			        //============================2016-05-24圣诗蔓积分换购(>=5:5)================================================end
					
					String passwordMd5 = StringUtil.encodePassword(password, "md5");
					if (!passwordMd5.equals(jsysUser.getPassword2())) {
						errMsg = LocaleUtil.getLocalText(jsysUser.getDefCharacterCoding(), "fiMoney.fail.password");
						break;
					}

					if (!StringUtil.isEmpty(jpoMemberOrderTemp.getProductFlag())) {
						errMsg = "生态家套餐订购不能使用此支付方式！";
						break;
					}
				
					
					Map<String, String> retMap = OrderCheckUtils.checkMemberOrder(jpoMemberOrderTemp);
					if (!"0".equals(retMap.get("code"))) {
						errMsg = retMap.get("msg");
						//code = retMap.get("code");
						break;
					}

//					jpoMemberOrderTemp.setUserSpCode(jsysUser.getUserCode());
					if (StringUtils.isEmpty(payJJ)) {
						//Modify By WuCF 20170210设置支付人为当前登录人
						jpoMemberOrderTemp.setUserSpCode(jsysUser.getUserCode());
						
						log.info("payOthersOrder stop:"+jpoMemberOrderTemp.getMemberOrderNo()+
								" Thread id:"+Thread.currentThread().getId());
						jpoMemberOrderManager.checkJpoMemberOrder(jpoMemberOrderTemp, jsysUser, "1");
						log.info("payOthersOrder stop:"+jpoMemberOrderTemp.getMemberOrderNo()+
								" Thread id:"+Thread.currentThread().getId());
					} else {
						BigDecimal amount = new BigDecimal(payJJ);
						
						//===========================Modify By WuCF 20160808
						//手机端新增基金余额判断
						if(fiBankbookBalanceManager!=null){
							FiBankbookBalance fiBankbookBalance = fiBankbookBalanceManager.getFiBankbookBalance(jsysUser.getUserCode(), "1");
							log.info(jpoMemberOrderTemp.getMemberOrderNo()+"-"+userCode+"====="+amount+"-"+fiBankbookBalance.getBalance());
							if(amount.compareTo(fiBankbookBalance.getBalance())==1){
								errMsg = "基金余额不足!";
								break;
							}
						}
						
						if (amount.compareTo(new BigDecimal("0")) == 1) {
//							jpoMemberOrderTemp.setPayByJj("1");
							if (amount.compareTo(jpoMemberOrderTemp.getAmount()) != -1) {
//								jpoMemberOrderTemp.setJjAmount(jpoMemberOrderTemp.getAmount());
//								jpoMemberOrderTemp.setAmount(new BigDecimal("0"));
							} else {
//								jpoMemberOrderTemp.setJjAmount(amount);
//								jpoMemberOrderTemp.setAmount(jpoMemberOrderTemp.getAmount().subtract(amount));
							}
							log.info("payOthersOrder start:"+jpoMemberOrderTemp.getMemberOrderNo()+
									" Thread id:"+Thread.currentThread().getId());
							jpoMemberOrderTemp.setPayByJj("1");//基金支付
							jpoMemberOrderTemp.setJjAmount(amount);//基金金额
							//2017-1-1 w
//							jpoMemberOrderTemp.setAmount(jpoMemberOrderTemp.getAmount2().subtract(amount));//存折金额
							jpoMemberOrderTemp.setUserSpCode(jsysUser.getUserCode());//当前支付会员编号
							jpoMemberOrderManager.checkJpoMemberOrderByJJ(jpoMemberOrderTemp, jsysUser, "2");
							log.info("payOthersOrder stop:"+jpoMemberOrderTemp.getMemberOrderNo()+
									" Thread id:"+Thread.currentThread().getId());
						} else if (amount.compareTo(new BigDecimal("0")) == -1) {
							errMsg = "基金不能输入负数!";
							break;
						} else {
							//Modify By WuCF 20170222设置支付人为当前登录人
							jpoMemberOrderTemp.setUserSpCode(jsysUser.getUserCode());
							
							log.info("payOthersOrder start:"+jpoMemberOrderTemp.getMemberOrderNo()+
									" Thread id:"+Thread.currentThread().getId());
							jpoMemberOrderManager.checkJpoMemberOrder(jpoMemberOrderTemp, jsysUser, "2");
							log.info("payOthersOrder stop:"+jpoMemberOrderTemp.getMemberOrderNo()+
									" Thread id:"+Thread.currentThread().getId());
						}
					}
					errMsg = "成功!";
					code = SUCCESS_CODE;
					break;
				}
				break;
			}
		}catch(Exception e){
			e.printStackTrace();
			log.info("===e:"+e);
			throw new AppException(e.getMessage());
			//errMsg = e.getMessage();
			//code = "e002";
		} 
		/* 支付改造
		if (SUCCESS_CODE.equals(code)) {
			try {
				jpoMemberOrder.setStatus("3");
				jpoMemberOrderManager.save(jpoMemberOrder);
				
				log.info(jpoMemberOrder.getMemberOrderNo() +" , ispay1: " + jpoMemberOrder.getIsPay());
				jpoMemberOrder = jpoMemberOrderManager.getJpoMemberOrderByMemberOrderNo(jpoMemberOrder.getMemberOrderNo());
				log.info(jpoMemberOrder.getMemberOrderNo() +" , ispay2: " + jpoMemberOrder.getIsPay());
				
				jpoMemberOrderManager.jpoMemberOrderPayedSendToMQ(jpoMemberOrder, jsysUser, "1");
				log.info(jpoMemberOrder.getMemberOrderNo() + "=======审单MQ：JpoMemberOrderPayOtherFormController");
			} catch (Exception e) {
				log.error(jpoMemberOrder.getMemberOrderNo() + "发送mq消息失败：", e);
			}
		}
		*/
		//Modify By WUCF 20160805 新增订单支付来源标示字段：PAYMENT_TYPE修改
		log.info("updateJpoMemberOrderPaymentType start:"+jpoMemberOrder.getMemberOrderNo()+
				" Thread id:"+Thread.currentThread().getId());
		//jpoMemberOrder.setPaymentType("5");
		//jpoMemberOrderDao.save(jpoMemberOrder);
//		jpoMemberOrderManager.updateJpoMemberOrderPaymentType(String.valueOf(jpoMemberOrder.getMoId()), "5");
		log.info("updateJpoMemberOrderPaymentType stop:"+jpoMemberOrder.getMemberOrderNo()+
				" Thread id:"+Thread.currentThread().getId());
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("code", code);
		retMap.put("msg", errMsg);
		JSONObject jsonarray = JSONObject.fromObject(retMap);
		log.info("jsonarray.toString():"+jsonarray.toString());
		return jsonarray.toString();
	}

	/**
	 * 会员转账历史信息
	 */
	public String payTransfersDetails(JsysUser jsysUser,String jsonMsg) {
		Map<String, String> map = parseJSON2MapString(jsonMsg);
	
		String startDate = map.get("startDate");
		String endDate = map.get("endDate");
		String pageNum = map.get("pageNum");
		String pageSize = map.get("pageSize");
		GroupPage page = new GroupPage(Integer.valueOf(pageNum), 0, Integer.valueOf(pageSize), "");
		List list = fiTransferAccountManager.getFiTransferAccountListByUserCodePage(page, jsysUser.getUserCode(), startDate, endDate);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		JSONArray jsonArray = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			Map<String, Object> listMap = ((Map<String, Object>) list.get(i));
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("createTime", format.format(listMap.get("CREATE_TIME")) );
			jsonObject.put("userCode", listMap.get("DESTINATION_USER_CODE"));
			jsonObject.put("money", listMap.get("MONEY"));
			jsonObject.put("status", listMap.get("STATUS"));
			jsonObject.put("checkeTime", format.format(listMap.get("CHECKE_TIME")));
			jsonObject.put("notes", listMap.get("NOTES"));
			jsonArray.add(jsonObject);
		}
		return jsonArray.toString();
	}

	/**
	 * 申请转账
	 */
	public String payTransfers(JsysUser jsysUser,String jsonMsg) {

		String errMsg = "未知错误";
		String code = "e001";
		try {
			Map<String, String> map = parseJSON2MapString(jsonMsg);
			
			String userCode = map.get("userCode");// 收款会员编号
			String amount = map.get("amount");// 金额
			String remark = map.get("remark");// 摘要
			String password = map.get("password");// //前台输入支付密码
			FiTransferAccount fiTransferAccount = new FiTransferAccount();
			fiTransferAccount.setMoney(new BigDecimal(amount));
			fiTransferAccount.setDestinationUserCode(userCode);
			fiTransferAccount.setNotes(remark);
			while (true) {
				// 验证转账目标用户是否存在
				JsysUser destinationUser = this.sysUserManager.get(fiTransferAccount.getDestinationUserCode()); // 支付用户的
				if (destinationUser == null || !destinationUser.getUserType().equals("M")) {
					errMsg = LocaleUtil.getLocalText("error.destination.not.existed");
					break;
				}

				// 单笔转账交易额度控制，采用自动化配置方式
				String limitNum = ConfigUtil.getConfigValue(jsysUser.getCompanyCode().toUpperCase(), "transferaccount.max.value");
				BigDecimal limit = new BigDecimal(limitNum);
				// 如果单笔交易额度不小于5000
				if (limit.compareTo(fiTransferAccount.getMoney()) == -1 || limit.compareTo(fiTransferAccount.getMoney()) == 0) {
					errMsg = LocaleUtil.getLocalText("fiTransferAccount.paymax.error2");
					break;
				}

				// 单日转账交易额度控制，采用自动化配置方式
				String limitDayNum = ConfigUtil.getConfigValue(jsysUser.getCompanyCode().toUpperCase(), "transferaccount.daymax.value");
				BigDecimal limitDay = new BigDecimal(limitDayNum);

				// 获取当前用户当日转账总额
				BigDecimal sumDayTransferValue = fiTransferAccountManager.getSumTransferValueByTransferCode(jsysUser.getUserCode());
				BigDecimal sumDayTransferValues = sumDayTransferValue.add(fiTransferAccount.getMoney());

				// 如果单日最高额度不小于2W
				if (limitDay.compareTo(sumDayTransferValues) == -1) {
					errMsg = LocaleUtil.getLocalText("fiTransferAccount.paydaymax.error");
					break;
				}

				// 验证转账支付密码(提现密码)
				if (password == null || !StringUtil.encodePassword(password, "md5").equalsIgnoreCase(jsysUser.getPassword2())) {
					errMsg = LocaleUtil.getLocalText("fiTransferAccount.paypwd.error");
					break;
				}

				// 设置当前转账用户
				fiTransferAccount.setTransferUserCode(jsysUser.getUserCode());
				fiTransferAccount.setCreaterCode(jsysUser.getUserCode());
				fiTransferAccount.setCreaterName(jsysUser.getUserName());
				fiTransferAccount.setCreateTime(new Date());

				//Modify By WuCF 20160805 设置支付平台类型
				fiTransferAccount.setPaymentType("1");
				
				// 创建转账单
				try {
					fiTransferAccountManager.addTransferAccountsNew(fiTransferAccount, jsysUser,"2");
				} catch (Exception e) {
					errMsg = LocaleUtil.getLocalText("fiTransferAccount.add.error");
					log.debug("error:" + e.getMessage());
					break;
				}
				// 页面跳转和执行结果提示
				code = SUCCESS_CODE;
				errMsg = LocaleUtil.getLocalText("fiTransferAccount.add.success");
				break;
			}
		}catch(AppException e){
			throw new AppException(e.getMessage());
		} catch (Exception e) {
			code = "e002";
			e.printStackTrace();
			log.debug("error:" + e.getMessage());
		}
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("code", code);
		retMap.put("msg", errMsg);
		JSONObject jsonarray = JSONObject.fromObject(retMap);
		return jsonarray.toString();
	}

	/**
	 * 函数注释：parseJSON2MapString()<br>
	 * 用途：该方法用于json数据转换为<Map<String, String><br>
	 * 备注：***<br>
	 */
	private static Map<String, String> parseJSON2MapString(String jsonStr) {
		Map<String, String> map = new HashMap<String, String>();
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			if (null != v) {
				map.put(k.toString(), v.toString());
			}
		}
		return map;
	}

	private boolean checkUnderMember(JsysUser defSysUser,String userCode) {

		JmiRecommendRef defRecommendRef = jmiRecommendRefDao.get(defSysUser.getUserCode());
		JmiRecommendRef miRecommendRef = jmiRecommendRefDao.get(userCode);
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
	 * 判断会员是否冻结状态，重消期别是否过期，是否死点
	 * 若是冻结状态，或者死点，或者过期则返回true
	 * @param order
	 * @param user
	 * @return true or false
	 */
	private boolean validateOrder(JpoMemberOrder order,JsysUser user){
		JmiMember member = user.getJmiMember();
		log.info("member active is:"+member.getActive()+" "
				+ "and freeStatus :"+member.getFreezeStatus());
		if(! order.getOrderType().equals("3")){
			//冻结会员
			if(member.getFreezeStatus()!=null && 
					member.getFreezeStatus() == 1){
				return true;
			}
			
			List list = jbdMemberFrozenManager.getJbdMemberFrozen();
			if(list.contains(order.getSysUser().getUserCode())){
				return false;
			}
			//去掉免重销的时间验证
/*			Integer validweek = member.getValidWeek();
			if(validweek!=null){
				Calendar curDate = Calendar.getInstance();
				Calendar validweek_date= Calendar.getInstance();
				
				SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
				try {
					validweek_date.setTime(sf.parse(validweek.toString()));
//					int lastDay = validweek_date.getActualMaximum(Calendar.DAY_OF_MONTH);
//					validweek_date.set(Calendar.DAY_OF_MONTH,lastDay);
					
					validweek_date.set(Calendar.MONTH, validweek_date.get(Calendar.MONTH)+1);
					validweek_date.set(Calendar.DAY_OF_MONTH, 1);
					
					if(curDate.after(validweek_date)){
						return true;
					}		
				} catch (ParseException e) {
					log.error("ParseException",e);
					return true;
				}
			}*/
		}
		
		//死点会员
		if("1".equals(member.getActive())){
    		return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("code", "11");
		retMap.put("msg", "2");
		JSONArray jsonarray = JSONArray.fromObject(retMap);
		
		System.out.println(jsonarray.toString());
		System.out.println(JSONObject.fromObject(retMap));
	}
}
