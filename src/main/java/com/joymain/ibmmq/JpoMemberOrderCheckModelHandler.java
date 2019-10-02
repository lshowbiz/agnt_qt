package com.joymain.ibmmq;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.JmiMemberDao;
import com.joymain.ng.dao.JpmProductSaleNewDao;
import com.joymain.ng.dao.JpoMemberOrderDao;
import com.joymain.ng.dao.JpoMovieDao;
import com.joymain.ng.dao.JsysUserDao;
import com.joymain.ng.handle.GlobalVar;
import com.joymain.ng.model.JalCompany;
import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.model.JbdSummaryList;
import com.joymain.ng.model.JbdUserValidList;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiStore;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JpmSalePromoter;
import com.joymain.ng.model.JpmSalepromoterPro;
import com.joymain.ng.model.JpmSmssendInfo;
import com.joymain.ng.model.JpoCheckedFailed;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderFee;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JpoMovie;
import com.joymain.ng.model.JpoShoppingCart;
import com.joymain.ng.model.JsysRole;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.JsysUserRole;
import com.joymain.ng.service.FiBankbookJournalManager;
import com.joymain.ng.service.FiBcoinJournalManager;
import com.joymain.ng.service.JalCompanyManager;
import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.service.JbdSummaryListManager;
import com.joymain.ng.service.JfiBankbookJournalManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiStoreManager;
import com.joymain.ng.service.JmiTeamManager;
import com.joymain.ng.service.JpmCardSeqManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.service.JpmSalePromoterManager;
import com.joymain.ng.service.JpoCheckedFailedManager;
import com.joymain.ng.service.JpoMemberOrderManager;
import com.joymain.ng.service.JsysRoleManager;
import com.joymain.ng.service.JsysUserRoleManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.DateUtil;
import com.joymain.ng.util.ListUtil;
import com.joymain.ng.util.SmsSend;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.data.CommonRecord;


@Service
public class JpoMemberOrderCheckModelHandler extends GenericManagerImpl<JpoMemberOrder, Long> implements IJmsHandler {
	
	private final Log log = LogFactory.getLog(JpoMemberOrderCheckModelHandler.class);
	
	@Autowired
	private JbdPeriodManager jbdPeriodManager;  
	@Autowired
    private JsysRoleManager sysRoleManager;
	@Autowired
	private JsysUserRoleManager sysUserRoleManager;
    @Autowired
    private JmiMemberDao jmiMemberDao;
    @Autowired
    private JmiStoreManager jmiStoreManager;
    @Autowired
    private JmiTeamManager jmiTeamManager;
    @Autowired
    private JpoMemberOrderDao jpoMemberOrderDao;
    @Autowired
    private JpoMemberOrderManager jpoMemberOrderManager;
    
    @Autowired
    private JpmSalePromoterManager jpmSalePromoterManager;
    @Autowired
    private JpmProductSaleNewManager jpmProductSaleManager; 
    @Autowired
    private JbdSummaryListManager jbdSummaryListManager;
    @Autowired
    private JpmCardSeqManager jpmCardSeqManager;
     
    @Autowired
    private JpoMovieDao jpoMovieDao;
    
    @Autowired
    private JpmProductSaleNewDao jpmProductSaleNewDao;
    
    @Autowired
    private FiBcoinJournalManager fiBcoinJournalManager = null;
    @Autowired
    private FiBankbookJournalManager fiBankbookJournalManager = null;
    @Autowired
    private JalCompanyManager alCompanyManager;
   
    @Autowired
    private JfiBankbookJournalManager jfiBankbookJournalManager;
	  
	@Autowired
    private JsysUserDao jsysUserDao;
	@Autowired
	private JpoCheckedFailedManager checkedFailedManager;
	@Autowired
	private JmiMemberManager jmiMemberManager;
	
//	@Autowired
//    public JpoMemberOrderCheckModelHandler(JpoMemberOrderDao jpoMemberOrderDao) {
//        super(jpoMemberOrderDao);
//        this.jpoMemberOrderDao=jpoMemberOrderDao;
//    }
	 
//	@Override
//	public void handMessage(Object model) {
//		
//		//接收到消息进行处理
//		JpoMemberOrderCheckModel checkModel = (JpoMemberOrderCheckModel) model;
//		log.info(checkModel.getJpoMemberOrder().getMemberOrderNo()+"审单开始!");
////		JpoMemberOrder jpoMemberOrder = checkModel.getJpoMemberOrder();
//		JpoMemberOrder jpoMemberOrder = jpoMemberOrderDao.
//			get(checkModel.getJpoMemberOrder().getMoId());
//		
//		try {
//			if(checkModel.getJpoMemberOrder().getIsPay().equals("1")){
//				checkJpoMemberOrder(jpoMemberOrder, checkModel.getJsysUser(), checkModel.getDataType());
//			}else{
//				throw new Exception(checkModel.getJpoMemberOrder().getMemberOrderNo()+"审核失败,未支付!");
//			}
//			
//			log.info(checkModel.getJpoMemberOrder().getMemberOrderNo()+" 审核完成!");
//			
//		} catch (Exception e) {
//			jpoMemberOrder =  jpoMemberOrderDao.get(checkModel.getJpoMemberOrder().getMoId());
//			jpoMemberOrder.setStatus("4");
//			dao.save(jpoMemberOrder);
//			log.info(checkModel.getJpoMemberOrder().getMemberOrderNo()+"审单失败!");
//			log.error("",e);
//		}
//	}
	
	
	
	@Override
	public synchronized void  handMessage(Object model) {
		
		JpoMemberOrder jpoMemberOrder = new JpoMemberOrder();
		try {
			//接收到消息进行处理
			JpoMemberOrderCheckModel checkModel = (JpoMemberOrderCheckModel) model;
//			JpoMemberOrder jpoMemberOrder = checkModel.getJpoMemberOrder();
			jpoMemberOrder = jpoMemberOrderDao.
				get(checkModel.getJpoMemberOrder().getMoId());
			
			log.info(jpoMemberOrder.getMemberOrderNo()+"审单开始!");
			
			log.info(jpoMemberOrder.getMemberOrderNo()+" , 支付状态isPay: " + jpoMemberOrder.getIsPay());
			if(jpoMemberOrder.getIsPay() !=null && jpoMemberOrder.getIsPay().equals("1")){
				checkJpoMemberOrder(jpoMemberOrder, checkModel.getJsysUser(), checkModel.getDataType());
			}else{
				throw new Exception(jpoMemberOrder.getMemberOrderNo()+"审核失败,未支付!");
			}
			
			log.info(jpoMemberOrder.getMemberOrderNo()+" 审核完成!");
			
		} catch (Exception e) {
			//jpoMemberOrder =  checkModel.getJpoMemberOrder();
			jpoMemberOrder.setStatus("4");
			dao.save(jpoMemberOrder);
			log.error(jpoMemberOrder.getMemberOrderNo()+"审单失败!",e);
		}
	}

	
	/**
	 * 审核会员订单
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 * @param dataType 数据来源，2：手机，null或者1：PC
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true,rollbackFor=Exception.class)
	public synchronized JpoMemberOrder checkJpoMemberOrder(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType) throws AppException{
		
		log.info("check Order start ,orderNo is:"+jpoMemberOrder.getMemberOrderNo());
		Date s = new Date();
		
		String limitcheckorder = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "limitcheckorder");
		if("1".equals(limitcheckorder))
		{
			throw new AppException("系统更新,请您稍后在审单");
		}
		if(!this.getCheckOrderAmount(jpoMemberOrder)){
			throw new AppException("订单总金额不一致");
		}
		if(!"0".equals(jpoMemberOrder.getLocked())){
			throw new AppException("订单已锁订");
		}
		if("2".equals(jpoMemberOrder.getStatus())){
			throw new AppException("订单已审核");
		}
		if("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getActive())){
			throw new AppException(this.getLocalLanguageByKey("checkError.Code.member",operatorSysUser));
		}
		
		if(jpoMemberOrder.getSysUser().getJmiMember().getFreezeStatus() == 1 && !jpoMemberOrder.getOrderType().equals(Constants.AUTO_PURCHASE)){
			throw new AppException("会员已冻结!");
		}
		
		JsysUser sysUserSp = jsysUserDao.get(jpoMemberOrder.getUserSpCode());
		
		if(!sysUserSp.getCompanyCode().equals(jpoMemberOrder.getCompanyCode())&&!"1".equals(jpoMemberOrder.getCompanyPay())){
			throw new AppException("扣款人与订单不同国别");
		}
		

    	BigDecimal sumPrice = new BigDecimal(0);
    	BigDecimal sumPV = new BigDecimal(0);
    	
    	Iterator<JpoMemberOrderList> itsTmp1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
    	while (itsTmp1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) itsTmp1.next();
			sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
			sumPV =sumPV.add(jpoMemberOrderList.getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
		}
    	Iterator itsTmp2 = jpoMemberOrder.getJpoMemberOrderFee().iterator();
    	while (itsTmp2.hasNext()) {
			JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) itsTmp2.next();
			sumPrice = sumPrice.add(jpoMemberOrderFee.getFee());
		}
		Date logCurDate=new Date();
    	Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
    	if("CN".equals(jpoMemberOrder.getCompanyCode())){
        	while (its1.hasNext()) {
        		JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        		String status = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getStatus();
        		if("0".equals(status)){
        			throw new AppException("产品(" + jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo() + ")已售完!");
        		}
        	}
    	}
	//	Date date =new Date(); //数据时间jmiMemberDao.getDsDate();
		Date date=DateUtil.getNowTimeFromDateServer();
		jpoMemberOrder.setSubmitStatus("2");
		jpoMemberOrder.setSubmitTime(date);
		jpoMemberOrder.setSubmitUserCode(operatorSysUser.getUserCode());
		jpoMemberOrder.setStatus("2");  //已审核
		jpoMemberOrder.setCheckTime(date);
		jpoMemberOrder.setCheckDate(date);
		jpoMemberOrder.setCheckUserCode(operatorSysUser.getUserCode());
		
		int orderType = Integer.parseInt(jpoMemberOrder.getOrderType());
		JmiMember jmiMember = jmiMemberDao.get(jpoMemberOrder.getSysUser().getUserCode());
		String oldCard="";
		String newCard="";
		String roleId;
		switch(orderType){
			case 1://会员首购
				int businessFId = this.getJpoMemberOrderBusinessMF(jpoMemberOrder);
				if(businessFId==10){
					logCurDate=new Date();
					if(!"0".equals(jpoMemberOrder.getSysUser().getJmiMember().getCardType())){
						throw new AppException("会员不处于待审状态");
					}
//					roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_ROLE_NORMAL);
//					this.getChangeJmiMemberRole(jpoMemberOrder.getSysUser(), roleId);
				
				}else{
					throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessFId,operatorSysUser));
				}
				jmiMember = jmiMemberDao.get(jpoMemberOrder.getSysUser().getUserCode());
				jmiMember.setCheckDate(jpoMemberOrder.getCheckDate());
				jmiMember.setCheckNo(jpoMemberOrder.getCheckUserCode());
				//==============续约
				JbdPeriod bdPeriodF = jbdPeriodManager.getBdPeriodByTime(jpoMemberOrder.getCheckDate(),null);
				Integer startMonthF = bdPeriodF.getWyear()*100 + bdPeriodF.getWmonth();
				
				String yearF = startMonthF.toString().substring(0, 4);
				String monthF = startMonthF.toString().substring(4, 6);
				String periodF = jbdPeriodManager.getFutureBdYearMonth(yearF, monthF, 12);
				
				jmiMember.setStartWeek(startMonthF);
				jmiMember.setValidWeek(Integer.parseInt(periodF));
				//==============
				if("2".equals(jpoMemberOrder.getSysUser().getJmiMember().getMemberType())){
					jmiMember.setOriPv(new BigDecimal("0"));
				}
				
				//新加字段，不能再下首单
				jmiMember.setNotFirst(1);
				this.jmiMemberDao.save(jmiMember);
				break;
				
			case 3://会员续约
				int businessRSId = this.getJpoMemberOrderBusinessMRS(jpoMemberOrder);
				if(businessRSId==30){
					
					if(jpoMemberOrder.getSysUser().getJmiMember().getFreezeStatus()==0){
						throw new AppException("会员处于解冻状态！");
					}else if(jpoMemberOrder.getSysUser().getJmiMember().getFreezeStatus()==1){
						//冻，126PV订单
						//==============续约当年

					/*	String years = jpoMemberOrder.getSysUser().getJmiMember().getValidWeek().toString().substring(0, 4);
						String months = jpoMemberOrder.getSysUser().getJmiMember().getValidWeek().toString().substring(4, 6);
						String periodS = jbdPeriodManager.getFutureBdYearMonth(years, months, 2);
						String yeare = periodS.substring(0, 4);
						String monthe = periodS.substring(4, 6);
						String periodE = jbdPeriodManager.getFutureBdYearMonth(yeare, monthe, 13);*/
						
						
						JbdPeriod bdPeriod=jbdPeriodManager.getBdPeriodByTime(new Date(),null);
						String periodS = jbdPeriodManager.getFutureBdYearMonth(bdPeriod.getWyear().toString(), bdPeriod.getWmonth().toString(), 1);
						String periodE = jbdPeriodManager.getFutureBdYearMonth(bdPeriod.getWyear().toString(), bdPeriod.getWmonth().toString(), 12);
						
//						jpoMemberOrder.getSysUser().getJmiMember().setStartWeek(Integer.parseInt(periodS));
//						jpoMemberOrder.getSysUser().getJmiMember().setValidWeek(Integer.parseInt(periodE));
						jmiMember.setStartWeek(Integer.parseInt(periodS));
						jmiMember.setValidWeek(Integer.parseInt(periodE));
						
						JbdPeriod bdPeriodSR = jbdPeriodManager.getBdPeriodByTime(jpoMemberOrder.getCheckDate(),null);
						Integer startMonthSR = bdPeriodSR.getWyear()*100 + bdPeriodSR.getWmonth();
						
						if(startMonthSR<=Integer.parseInt(periodE)){
							//当前期别小于会员的冻结期别解冻
//							jpoMemberOrder.getSysUser().getJmiMember().setFreezeStatus(Constants.FREEZE_STATUS_ZERO); //解冻
							jmiMember.setFreezeStatus(Constants.FREEZE_STATUS_ZERO);
							JbdUserValidList jbdUserValidList = new JbdUserValidList();
							jbdUserValidList.setJmiMember(jpoMemberOrder.getSysUser().getJmiMember());
							jbdUserValidList.setNewFreezeStatus(0);
							jbdUserValidList.setOldFreezeStatus(1);
							jbdUserValidList.setWweek(bdPeriodSR.getWyear()*100 + bdPeriodSR.getWweek());
							Set<JbdUserValidList> jbdUserValidListSet = new HashSet<JbdUserValidList>(0);
							jbdUserValidListSet.add(jbdUserValidList);
							jpoMemberOrder.getSysUser().getJmiMember().setJbdUserValidList(jbdUserValidListSet);
							
							//==============
							//角色
//							wxm jocs
							JmiStore jmiStore = jmiStoreManager.getJmiStoreByUserCode(jpoMemberOrder.getSysUser().getUserCode());
							if("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getIsstore())){
									roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_STORE1);
							}else if("2".equals(jpoMemberOrder.getSysUser().getJmiMember().getIsstore())){
									roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_STORE2);
							}else if("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getSubStoreStatus())){
								//二级已审
								roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_STORE2_X);
							}else if(jmiStore!=null && "1".equals(jmiStore.getCheckStatus())){
								//一级已审
								roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_STORE1_X);
							}else{
								
								//通过身份判断角色,默认是0 优惠顾客 1 永远优惠顾客2 会员3,两个优惠顾客都是取优惠顾客角色,会员就取普通会员,0就取待审
								Integer greadType = jpoMemberOrder.getSysUser().getJmiMember().getGradeType();
								if(greadType == 3){
									roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_ROLE_NORMAL);
								}else if(greadType == 2 || greadType == 1){
									roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_ROLE5);
								}else {
									roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_ROLE0);
								}
								
//								roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_ROLE_NORMAL);
							}
							this.getChangeJmiMemberRole(jpoMemberOrder.getSysUser(), roleId);
						}
					}else{
						throw new AppException("冻结状态不明确");
					}
				}else{
					throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessRSId,operatorSysUser));
				}
				break;
			case 4://会员重销
				int businessRId = this.getJpoMemberOrderBusinessMR(jpoMemberOrder);
				if(businessRId==40){
					
				}else{
					throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessRId,operatorSysUser));
				}
				break;
			case 5://辅销品订单
				int businessAId = this.getJpoMemberOrderBusinessMA(jpoMemberOrder);
				if(businessAId==40){
				}else{
					throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessAId,operatorSysUser));
				}
				break;
			case 6://店铺首购
				int businessSFId = this.getJpoMemberOrderBusinessSF(jpoMemberOrder);
				if(businessSFId==60){

			    	BigDecimal amount = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get(Constants.STORE1_FIRST_AMOUNT));
			    	BigDecimal pvAmt = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get(Constants.STORE1_FIRST_PV));
			    	
			    	if("LC".equals(jpoMemberOrder.getProductType())){
				    	if(pvAmt.compareTo(jpoMemberOrder.getPvAmt())!=1){
				    		
				    	}else{
				    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
				    	}
			    	}else{
				    	if(amount.compareTo(sumPrice)!=1){

				    	}else{
				    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
				    	}
			    	}
					//角色
		    		roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_STORE1);
					this.getChangeJmiMemberRole(jpoMemberOrder.getSysUser(), roleId);
//					jpoMemberOrder.getSysUser().getJmiMember().setIsstore("1");
					jmiMember.setIsstore("1");
					jmiMember.setRecommendStore(jpoMemberOrder.getSysUser().getJmiMember().getRecommendNo());
					
					try {
						//会员修改推送至CRM接口
//						new Thread(new JmiMemberCreateAndUpdateRunnable(jmiMember,true,false)).start();
					} catch (Exception e) {
//						e.printStackTrace();
					}
					
				}else{
					throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessSFId,operatorSysUser));
				}
				break;
			case 7://店铺升级单
				break;
			case 8://店铺返单
				break;
			case 9://店铺重销
				int businessSRId = this.getJpoMemberOrderBusinessSR(jpoMemberOrder);
				if(businessSRId==90){
					
				}else{
					throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessSRId,operatorSysUser));
				}
				break;
			case 11://二级馆首购单
				int businessSSubFId = this.getJpoMemberOrderBusinessSSubF(jpoMemberOrder);
				if(businessSSubFId==60){
					String language = jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase();
					
			    	BigDecimal pvamt = new BigDecimal(Constants.sysConfigMap.
			    			get(language).get(Constants.STORE2_FIRST_PV));
			    	BigDecimal amount = new BigDecimal(Constants.sysConfigMap.
			    			get(language).get(Constants.STORE2_FIRST_AMOUNT));
			    	
			    	JsysUser user = jpoMemberOrder.getSysUser();
			  
//			    	ygd跟中脉同步
//			    	String temCode = jmiTeamManager.teamStr(user.getUserCode());
//			    	if(temCode.equalsIgnoreCase(Constants.TEAMCODE_YGD)){
//			    		amount= new BigDecimal(Constants.sysConfigMap.
//			    				get(language).get(Constants.STORE2_FIRST_AMOUNT_YGD));
//			    	}
			    	
			    	if("LC".equals(jpoMemberOrder.getProductType()) && pvamt.compareTo(jpoMemberOrder.getPvAmt())!=1){
			    		
			    	}else if(!"LC".equals(jpoMemberOrder.getProductType())){
			    		if(amount.compareTo(sumPrice)==1){
			    			throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
			    		}
			    		if("HK".equals(jpoMemberOrder.getCompanyCode())){
			    			BigDecimal pv = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get(Constants.STORE2_FIRST_PV_HK));
				    		if(pv.compareTo(jpoMemberOrder.getPvAmt())==1){
				    			throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购PV不正确");
				    		}
			    		}
			    	}else{
			    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
			    	}
					//角色
			    	roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_STORE2);  //jocs.member.role.store2
					this.getChangeJmiMemberRole(jpoMemberOrder.getSysUser(), roleId);
//					jpoMemberOrder.getSysUser().getJmiMember().setIsstore("2");
//					jpoMemberOrder.getSysUser().getJmiMember().setSubStoreStatus("2");
//					jpoMemberOrder.getSysUser().getJmiMember().setSubStoreCheckDate(jpoMemberOrder.getCheckDate());
					jmiMember.setIsstore("2");
					jmiMember.setSubStoreStatus("2");
					jmiMember.setSubStoreCheckDate(jpoMemberOrder.getCheckDate());
					
					try {
						//会员修改推送至CRM接口
//						new Thread(new JmiMemberCreateAndUpdateRunnable(jmiMember,true,false)).start();
					}catch(Exception e){
//						e.printStackTrace();
					}
					
				}else{
					throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessSSubFId,operatorSysUser));
				}
				break;
			case 12://二级馆升级单
				int businessSSubUId = this.getJpoMemberOrderBusinessSSubU(jpoMemberOrder);
				if(businessSSubUId==90){
					BigDecimal pv = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get(Constants.STORE2_UP_PV));
			    	BigDecimal amount = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get(Constants.STORE2_UP_AMOUNT));
					
					if("LC".equals(jpoMemberOrder.getProductType())){
						pv = new BigDecimal(Constants.sysConfigMap.get(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase()).get("store.u2.orderlc.pv"));
						amount = new BigDecimal("0");
					}
					
        			java.util.Calendar startc=java.util.Calendar.getInstance();
        	    	startc.set(2011, 6, 16, 00, 00, 00);
        	    	java.util.Calendar endc=java.util.Calendar.getInstance();
        	    	endc.set(2011, 7, 6, 00, 00, 00);
        	    	java.util.Date startDate=startc.getTime();
        	    	java.util.Date endDate=endc.getTime();
        	    	Date curDate=new Date();
        	    	if((curDate.after(startDate))&&(curDate.before(endDate))){
        	    		Iterator its11 = jpoMemberOrder.getJpoMemberOrderList().iterator();
        	        	while (its11.hasNext()) {
        	    			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its11.next();
        	        		if("P13010200201CN0".equals(jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo())){
        	        			pv = new BigDecimal("0");
        	        		}
        	    		}
        	    	}
					
					if(pv.compareTo(jpoMemberOrder.getPvAmt())<1){
						if(amount.compareTo(sumPrice)<1){
							//角色
							roleId = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), Constants.JOCS_STORE1);
							this.getChangeJmiMemberRole(jpoMemberOrder.getSysUser(), roleId);
//							jpoMemberOrder.getSysUser().getJmiMember().setIsstore("1");
							jmiMember.setIsstore("1");
							
						}else{
				    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
						}
			    	}else{
			    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
			    	}
			    	JmiStore jmiStore = jmiStoreManager.getJmiStoreByUserCode(jpoMemberOrder.getSysUser().getUserCode());
			    	if(jmiStore!=null){
			    		jmiStore.setOrderTime(jpoMemberOrder.getCheckTime());
			    		jmiStore.setOrderDate(jpoMemberOrder.getCheckDate());
			    		jmiStoreManager.save(jmiStore);
			    	}
			    	try {
						//会员修改推送至CRM接口
//						new Thread(new JmiMemberCreateAndUpdateRunnable(jmiMember,true,false)).start();
					} catch (Exception e) {
//						e.printStackTrace();
					}
					
					jmiMember.setRecommendStore(jpoMemberOrder.getSysUser().getJmiMember().getRecommendNo());
			    	
			    	/* jocs begin
			    	jpoMemberOrder.getSysUser().getJmiMember().setRecommendStore(jpoMemberOrder.getSysUser().getJmiMember().getRecommendNo());
			    	jocs end */
					
				}else{
					throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessSSubUId,operatorSysUser));
				}
				break;
			case 13:
				break;
			case 14://二级馆重销单
				int businessSSubRId = this.getJpoMemberOrderBusinessSSubR(jpoMemberOrder);
				if(businessSSubRId==90){
					
				}else{
					throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessSSubRId,operatorSysUser));
				}
				break;
			case 15://会员AUTOSHIP
				int businessASId = this.getJpoMemberOrderBusinessAS(jpoMemberOrder);
				if(businessASId==40){
					
				}else{
					throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessASId,operatorSysUser));
				}
				break;
			case 21://活力小铺首单
				int businessMFId = this.getJpoMemberOrderBusinessSMF(jpoMemberOrder);
				if(businessMFId==90){
					BigDecimal amount  =new BigDecimal(this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "store.m.order.amount"));
			    	BigDecimal pv  =new BigDecimal(this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode().toUpperCase(), "store.m.order.pv"));
			    	if(amount.compareTo(sumPrice)==1){
			    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购金额不正确");
			    	}
			    	if(pv.compareTo(jpoMemberOrder.getPvAmt())==1){
			    		throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + "订购PV不正确");
			    	}
				}else{
					throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessMFId,operatorSysUser));
				}
				break;
			case 24://活力小铺重销单
				int businessMRId = this.getJpoMemberOrderBusinessSMR(jpoMemberOrder);
				if(businessMRId==90){
					
				}else{
					throw new AppException(this.getLocalLanguageByKey("checkError.Code",operatorSysUser) + this.getLocalLanguageByKey("checkError.Code."+businessMRId,operatorSysUser));
				}
				break;
		}
		logCurDate=new Date();
		
		//重消单
		if("4".equals(jpoMemberOrder.getOrderType()) || "9".equals(jpoMemberOrder.getOrderType()) 
				|| "14".equals(jpoMemberOrder.getOrderType())){
			//不冻，35PV订单
			String yearsMember = jpoMemberOrder.getSysUser().getJmiMember().getStartWeek().toString().substring(0, 4);
			String monthsMember = jpoMemberOrder.getSysUser().getJmiMember().getStartWeek().toString().substring(4, 6);
			List periodsMember = jbdPeriodManager.getBdPeriodsByMonth(yearsMember, monthsMember);
			JbdPeriod bdPeriod = (JbdPeriod)periodsMember.get(0);
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			CommonRecord crm = new CommonRecord();
			crm.addField("stauts", "2");
			crm.addField("userCode", jpoMemberOrder.getSysUser().getUserCode());
			crm.addField("orderType", "4,9,14");
			crm.addField("startLogTime", dateFormat.format(bdPeriod.getStartTime()));
			BigDecimal pvAmt = jpoMemberOrderDao.getJpoMemberOrderStatics(crm);
			pvAmt = pvAmt.add(jpoMemberOrder.getPvAmt());
			
			//时间限制
			java.util.Calendar startcPre=java.util.Calendar.getInstance();
			startcPre.set(2012, 8, 29, 00, 00, 00);
			java.util.Date startDatePre=startcPre.getTime();
			Date curDatePre=new Date();
            if("CN".equals(jpoMemberOrder.getCompanyCode()) )
            {
        		if(curDatePre.after(startDatePre) && new BigDecimal("42").compareTo(pvAmt)!=1){
    				JbdPeriod bdPeriodSR = jbdPeriodManager.getBdPeriodByTime(jpoMemberOrder.getCheckDate(),null);
    				Integer startMonthSR = bdPeriodSR.getWyear()*100 + bdPeriodSR.getWmonth();
    				if(jpoMemberOrder.getSysUser().getJmiMember().getStartWeek()<=startMonthSR){
    					if(!StringUtil.isEmpty(jpoMemberOrder.getSysUser().getJmiMember().getValidWeek().toString())){
    						String years = jpoMemberOrder.getSysUser().getJmiMember().getValidWeek().toString().substring(0, 4);
    						String months = jpoMemberOrder.getSysUser().getJmiMember().getValidWeek().toString().substring(4, 6);
    						String periodS = jbdPeriodManager.getFutureBdYearMonth(years, months, 2);
    						String yeare = periodS.substring(0, 4);
    						String monthe = periodS.substring(4, 6);
    						String periodE = jbdPeriodManager.getFutureBdYearMonth(yeare, monthe, 12);
//    						jpoMemberOrder.getSysUser().getJmiMember().setStartWeek(Integer.parseInt(periodS));
//    						jpoMemberOrder.getSysUser().getJmiMember().setValidWeek(Integer.parseInt(periodE));
    						jmiMember.setStartWeek(Integer.parseInt(periodS));
    						jmiMember.setValidWeek(Integer.parseInt(periodE));
    					}else{
    						jpoMemberOrder.setStatus("4");//审核失败
    						throw new AppException("ValidWeek为空！");
    					}
    				}
    			}
            }
          
			//==============续约次年
		}
		
		//TODO Jun 促销策略
		log.info("loginUser is:["+jpoMemberOrder.getSysUser().getUserCode()+ "] " +
				"and user oldLever is:["+jpoMemberOrder.getSysUser().getJmiMember().getCardType()+"] " +
				"and orderNo is:"+jpoMemberOrder.getMemberOrderNo());
		
		
		/*
		 * Date cur_date = Calendar.getInstance().getTime();
		 * 正式环境未做时间同步, 才如此获取
		 */
		Date cur_date = DateUtil.getNowTimeFromDateServer();
		String stime = DateUtil.getDate(cur_date,"yyyy-MM-dd HH:mm:ss");
		
		List<JpmSalePromoter> spList = jpmSalePromoterManager.
				getSaleByDate(stime, Constants.JPMSALE_ACTIVA);
		
		log.info("curdate is:["+stime+"] jpmSalePromoter list size is:" +
				"["+spList.size()+"]and user newLever is:"+
				jpoMemberOrder.getSysUser().getJmiMember().getCardType());
		try{
			for(JpmSalePromoter sp: spList){
				if(isOrderSales(sp,jpoMemberOrder)){//判断团队
					//策略类型:1折价促销, 2赠品促销,3积分促销,4订单总金额或PV
					String saleType = sp.getSaleType();
					if(saleType.equals(Constants.JPMSALE_SALETYPE_PRE)){
						if(isOrderType(sp.getUserlevel(), jpoMemberOrder.getSysUser().getJmiMember().getMemberLevel().toString())){
							jpoMemberOrder.getJpoMemberOrderList().addAll(bindPresentProduct(jpoMemberOrder,sp));
						} 
					} else if(saleType.equals(Constants.JPMSALE_SALETYPE_INTEGRAL)){//积分赠送
						if(isOrderType(sp.getUserlevel(), jpoMemberOrder.getSysUser().getJmiMember().getMemberLevel().toString())){
							getBindIntegral(jpoMemberOrder,sp,"0",operatorSysUser,dataType);
							//是否赠送推荐人0否, 1送
							String ispresent = sp.getIspresent();
							if(StringUtils.isNotBlank(ispresent) && ispresent.equals("1")){
								getBindIntegral(jpoMemberOrder,sp,"1",operatorSysUser,dataType);
							}
						} 
						
					} else if(saleType.equals(Constants.JPMSALE_SALETYPE_ORDER)){
						
						log.info("newCard is:"+newCard+" and orderCard "+
								jpoMemberOrder.getSysUser().getJmiMember().getMemberLevel().toString());
						
						if(isOrderType(sp.getUserlevel(), jpoMemberOrder.getSysUser().getJmiMember().getMemberLevel().toString())){
							// 按订单总金额,订单类型或PV送赠品
							Set<JpoMemberOrderList> orderSet= new HashSet<JpoMemberOrderList>();
							orderSet= getOrderAmountBindProduct(jpoMemberOrder,sp);
							if(!orderSet.isEmpty()){
								jpoMemberOrder.getJpoMemberOrderList().addAll(orderSet);
							}
						}
					}
				}
			}
		}catch(AppException e){
			log.error("sale promoter failed," +
					"orderNo is:"+jpoMemberOrder.getMemberOrderNo(),e);
			throw new AppException("sale Promoter failed," +
					"orderNo is:"+jpoMemberOrder.getMemberOrderNo());
		}catch(Exception e){
			log.error("sale promoter failed," +
					"orderNo is:"+jpoMemberOrder.getMemberOrderNo(),e);
			throw new AppException("sale Promoter failed," +
					"orderNo is:"+jpoMemberOrder.getMemberOrderNo());
		}
			
		
			if("TW".equals(jpoMemberOrder.getCompanyCode())){
				if(jpoMemberOrder.getPvAmt().compareTo(new BigDecimal(70))!=-1){
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
			
			String limitIsShipments = this.getConfigValue(jpoMemberOrder.getSysUser().getCompanyCode(), "limitisshipments");//1代表自动发货即审核完订单即可发货，0表示手动处理发货
			if("1".equals(limitIsShipments))
			{
			   jpoMemberOrder.setIsShipping("1");//1表示已经发货
			}else
			{
				 jpoMemberOrder.setIsShipping("0");//表示还没有发货
			}
		

//			挨千杀的save one
//			dao.save(jpoMemberOrder);
		
			//扣款
//			logCurDate=new Date();
//			this.getSaveMemberOrderDeduct(jpoMemberOrder, operatorSysUser, dataType);
	
			
			//45万套餐 生成点位和拆单
			/*List<JpoMemberOrder> orders=new ArrayList<JpoMemberOrder>();
			orders.add(jpoMemberOrder);
			if (jpoMemberOrder.getProductFlag().equals("45")) {
				List<JmiMember> members = jmiMemberManager.generateMultiMember(jpoMemberOrder.getSysUser().getJmiMember(), 3, jpoMemberOrder.getSysUser());
				for (int i = 0; i < members.size(); i++) {
					JmiMember member =	members.get(i);
					JpoMemberOrder order =	generateMemberOrder(jpoMemberOrder,member,i);
			    	orders.add(order);
				}
			}*/
			

			logCurDate=new Date();
			if(!"21".equals(jpoMemberOrder.getOrderType()) && !"24".equals(jpoMemberOrder.getOrderType())){
				//插入每日计算表
				JbdSummaryList jbdSummaryList=new JbdSummaryList();
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
				jbdSummaryList.setOrderNo(String.valueOf(jpoMemberOrder.getMoId()));
				jbdSummaryListManager.save(jbdSummaryList);
			}
			
			//送JOYME号
			try{
				jpmCardSeqManager.saveUserJpmCardSeq(jpoMemberOrder, oldCard, newCard);
			}catch(AppException app){
				throw new AppException(this.getLocalLanguageByKey(app.getMessage(),operatorSysUser));
			}
			try{
				//TODO sms
				String isPost = ConfigUtil.getConfigValue("CN", "sms.ispost");
				String movieUrl = ConfigUtil.getConfigValue("CN", "sms.movieurl");
				
				log.info("isPost sms is:["+isPost+"] and movieUrl is:"+movieUrl);
				
				if(Boolean.parseBoolean(isPost)){
					JpoMovie extMovie = jpoMovieDao.getMovieByOrderNo(jpoMemberOrder.getMemberOrderNo());
					if(extMovie ==null){
						Iterator<JpoMemberOrderList> orderListIte = jpoMemberOrder.getJpoMemberOrderList().iterator();
						while(orderListIte.hasNext()){
							JpoMemberOrderList orderList = orderListIte.next();
							String proNo = orderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo();
							
							if(proNo.equalsIgnoreCase(Constants.MOVIE_PRONO) || proNo.equalsIgnoreCase(Constants.MOVIE_PRONO2)){
								String url1 = ListUtil.getListValue("CN", "smssend.url", "1");
								String url2 = ListUtil.getListValue("CN", "smssend.url", "2");
								List<JpoMovie> movieList = jpoMovieDao.findMovieByName('0');
								
								if(movieList !=null && movieList.size()>0){
									JpoMovie movie = movieList.get(0);
									
									StringBuffer message = new StringBuffer();
									/* 
									 * 您已经成功获取养生套餐包影票产品（共300张影票），用户名：XXXXXX，密码：XXXXXX；
									 * 请凭此用户名及密码登录http://fm.daohegroup.cn/进行选票（2014年2月18日零点正式开通）。
									 * 咨询客服：025-83617786，QQ平台：10310323，祝您观影愉快！
									 */
									message.append("您已经成功获取养生套餐包影票产品（共300张影票）");
									message.append(",用户名："+movie.getMaccount());
									message.append(",密码："+movie.getMpwd());
									message.append("; 请凭此用户名及密码登录:"+movieUrl);
									message.append("进行选票(2014年2月18日零点正式开通).");
									message.append("咨询客服：025-83617786，QQ平台：10310323，祝您观影愉快！");
									
									SmsSend.sendSms(url1, url2, jpoMemberOrder.getMobiletele(), message.toString());
									
									//update jpoMovie setting OrderNo
									log.info("movieId is:"+movie.getmId()+" and orderNo is:"+jpoMemberOrder.getMemberOrderNo());
									movie.setOrderNo(jpoMemberOrder.getMemberOrderNo());
									movie.setStatus('1');
									movie.setMuseTime(new Date());
									jpoMovieDao.save(movie);
								
									
									//Modify By WuCF 将短信写入到数据库表 20140312
									JpmSmssendInfo jpmSmssendInfo = new JpmSmssendInfo();
									jpmSmssendInfo.setSmsType("3");////短信类型  例如：1：仓库发货确认    2：找回密码   3：电影票  目前只有三种，在列表中配置
									jpmSmssendInfo.setSmsRecipient(movie.getMaccount());//短信接收人:用户会员编号
									jpmSmssendInfo.setSmsMessage(message.toString());//短信内容
									jpmSmssendInfo.setSmsTime(new Date());//发送时间
									jpmSmssendInfo.setSmsOperator("");//操作人，可以填写空
									jpmSmssendInfo.setSmsStatus("1");//保留字段，是否发送成功！ 默认为1：成功！ 现在短信还不能知道是否发送成功，没有返回值！
									jpmSmssendInfo.setRemark("会员中心");//备注
									jpmSmssendInfo.setPhoneNum(jpoMemberOrder.getMobiletele());//手机号
									jpmProductSaleNewDao.saveJpmSmssendInfo(jpmSmssendInfo);
									
								} else {
									log.warn("movie Ticket is null, orderNo is:"+jpoMemberOrder.getMemberOrderNo());
								}
								break;
							}
						}
					}
				}
			}catch(Exception e){
				log.warn("movie Error,orderNo is:"+jpoMemberOrder.getMemberOrderNo(),e);
			}
			
			try{
				//更改订单系统 状态 -- 已确认
				
				jpoMemberOrder.setStatusSysMo(1l);   //已确认
				jpoMemberOrder.setIsSended(0l);		//未推送
				
				log.info("jpoMemberOrder:...."+jpoMemberOrder);
				
//					this.jmiMemberDao.save(jmiMember);
//					this.dao.save(jpoMemberOrder);
				
				jpoMemberOrderManager.saveOrderAndMember(jpoMemberOrder, jmiMember);
				
				//审核成功,删除记录
                JpoCheckedFailed checkedFailed = checkedFailedManager.getByOrderNo(jpoMemberOrder);
                if(checkedFailed != null){
                    Integer i = checkedFailedManager.deleteJpoCheckedFaiiled(String.valueOf(jpoMemberOrder.getMoId()));
                    log.info("deletecheckedFailed is :" + i);
                }
				
				log.info("checkOrderEnd ,orderNo is:"+jpoMemberOrder.getMemberOrderNo());
				Date e = new Date();
				log.info("审单时长：" +( e.getTime()-s.getTime())+" 毫秒。");
				
				
			}catch(Exception e){
				log.error("", e);
			}
			
			return jpoMemberOrder;
	}

	/**
     * 获取参数
     * @param companyCode
     * @param configKey
     * @return
     */
    private String getConfigValue(String companyCode, String configKey){
    	return Constants.sysConfigMap.get(companyCode).get(configKey);
    }
    
    /**
	 * 判断订单明细与总金额是否一至
	 * @param jpoMemberOrder
	 * @return
	 */
	private boolean getCheckOrderAmount(JpoMemberOrder jpoMemberOrder){

    	BigDecimal sumPrice = new BigDecimal(0);
    	BigDecimal sumPV = new BigDecimal(0);
    	
    	Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
    	while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			sumPrice = sumPrice.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
			sumPV =sumPV.add(jpoMemberOrderList.getPv().multiply(new BigDecimal(jpoMemberOrderList.getQty())));
		}
    	
    	Iterator its2 = jpoMemberOrder.getJpoMemberOrderFee().iterator();
    	while (its2.hasNext()) {
			JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) its2.next();
			if(jpoMemberOrderFee.getFee()!=null){
				sumPrice = sumPrice.add(jpoMemberOrderFee.getFee());
			} else {
				jpoMemberOrderFee.setFee(new BigDecimal(0));
			}
		}
    	
    	if(jpoMemberOrder.getDiscountAmount()!=null){
    		sumPrice = sumPrice.subtract(jpoMemberOrder.getDiscountAmount());
    	}
    	
    	if(jpoMemberOrder.getDiscountPvAmt()!=null){
    		sumPV = sumPV.subtract(jpoMemberOrder.getDiscountPvAmt());
    	}
    	
    	if(jpoMemberOrder.getJjAmount()!=null){
    		sumPrice = sumPrice.subtract(jpoMemberOrder.getJjAmount());
    	}
    	
    	if(jpoMemberOrder.getAmount().compareTo(sumPrice)==0 && sumPV.compareTo(jpoMemberOrder.getPvAmt())==0){
    		return true;
    	}
    	
    	return false;
	}
	
	/**
	 * 获取语言值
	 * @param key
	 * @param sysUser
	 * @return
	 */
	private String getLocalLanguageByKey(String key,JsysUser sysUser){
		String notes = "";
		String defaultMessage = key;
		if ("AA".equals(sysUser.getCompanyCode())) {
			notes = (String) Constants.localLanguageMap.get("zh_CN").get(defaultMessage);
		} else {
			notes = (String) Constants.localLanguageMap.get(sysUser.getDefCharacterCoding()).get(defaultMessage);
		}
		if(notes==null){
			return defaultMessage;
		}
		return notes;
	}
	
	/**
	 * 会员首购单业务判断1
	 * 10:成功
	 * 11:未知异常
	 * 12:已存在已审首购单
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessMF(JpoMemberOrder jpoMemberOrder){
		if("1".equals(jpoMemberOrder.getSysUser().getJmiMember().getActive())){
			return 13;//死点
		}
		if(true){//if("CN".equals(jpoMemberOrder.getCompanyCode())|| "HK".equals(jpoMemberOrder.getCompanyCode())){
			//是否有已审首购单
			JsysUser sysUser = jpoMemberOrder.getSysUser();
			List jpoMemberOrders = jpoMemberOrderDao.getJpoMemberOrdersByTCS("1", sysUser.getUserCode(), "2");
			if(jpoMemberOrders.size()<2){
				return 10;//成功
			}else{
				return 12;//已存在已审首购单
			}
		}
		return 11;//未知异常
	}
	
	/**
	 * 会员续约业务判断3
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessMRS(JpoMemberOrder jpoMemberOrder){
		JsysUser sysUser = jpoMemberOrder.getSysUser();
		if(sysUser.getJmiMember().getStartWeek()==0 || sysUser.getJmiMember().getValidWeek()==0){
			return 22;//待审会员
		}
//		if("0".equals(sysUser.getJmiMember().getCardType())){
		if(sysUser.getJmiMember().getNotFirst() == 0){
			return 22;//待审会员
		}else{
			return 30;//成功
		}
	}
	
	/**
	 * 会员返单业务判断4
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessMR(JpoMemberOrder jpoMemberOrder){
		JsysUser sysUser = jpoMemberOrder.getSysUser();
//		if("0".equals(sysUser.getJmiMember().getCardType())){
		if(sysUser.getJmiMember().getNotFirst() == 0){
			return 22;//待审会员
		}else{
			return 40;//成功
		}
	}
	
	/**
	 * 会员辅销品订单业务判断5
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessMA(JpoMemberOrder jpoMemberOrder){
		JsysUser sysUser = jpoMemberOrder.getSysUser();
//		if("0".equals(sysUser.getJmiMember().getCardType())){
		if(sysUser.getJmiMember().getNotFirst() == 0){
			return 22;//待审会员
		}else{
			return 40;//成功
		}
	}
	
	/**
	 * 店铺首购单业务判断6
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSF(JpoMemberOrder jpoMemberOrder){
		
		if("CN".equals(jpoMemberOrder.getCompanyCode())){
			//是否有已审首购单
			JsysUser sysUser = jpoMemberOrder.getSysUser();
			List jpoMemberOrders = jpoMemberOrderDao.getJpoMemberOrdersByTCS("6", sysUser.getUserCode(), "2");
			if(jpoMemberOrders.size()<2){
				return 60;//成功
			}else{
				return 62;//已存在已审首购单
			}
		}
		return 11;//未知异常
	}
	/**
	 * 店铺重销单业务判断9
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSR(JpoMemberOrder jpoMemberOrder){
		JsysUser sysUser = jpoMemberOrder.getSysUser();
		JmiMember jmiMember = sysUser.getJmiMember();
		if("1".equals(jmiMember.getIsstore())){
			return 90;//成功
		}else{
			return 92;//不存在已审首购单
		}
	}
	
	/**
	 * 二级馆首购单业务判断11
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSSubF(JpoMemberOrder jpoMemberOrder){
		//是否有已审首购单
		JsysUser sysUser = jpoMemberOrder.getSysUser();
		List jpoMemberOrders = jpoMemberOrderDao.getJpoMemberOrdersByTCS("11", sysUser.getUserCode(), "2");
		if(jpoMemberOrders.size()<2){
			return 60;//成功
		}else{
			return 62;//已存在已审首购单
		}
	}
	
	/**
	 * 二级馆升级单业务判断12
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSSubU(JpoMemberOrder jpoMemberOrder){
		JsysUser sysUser = jpoMemberOrder.getSysUser();
		JmiMember jmiMember = sysUser.getJmiMember();
		if("2".equals(jmiMember.getIsstore())){
			return 90;//成功
		}else{
			return 92;//不存在已审首购单
		}
	}
	
	/**
	 * 二级馆重销单业务判断14
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSSubR(JpoMemberOrder jpoMemberOrder){
		JsysUser sysUser = jpoMemberOrder.getSysUser();
		JmiMember jmiMember = sysUser.getJmiMember();
		if("2".equals(jmiMember.getIsstore())){
			return 90;//成功
		}else{
			return 92;//不存在已审首购单
		}
	}
	/**
	 * 会员返单业务判断15
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessAS(JpoMemberOrder jpoMemberOrder){
		JsysUser sysUser = jpoMemberOrder.getSysUser();
		if("0".equals(sysUser.getJmiMember().getCardType())){
			return 22;//待审会员
		}else{
			return 40;//成功
		}
		//是否有已审首购单
//		List jpoMemberOrders = dao.getJpoMemberOrdersByTCS("1", sysUser.getUserCode(), "2");
//		if(jpoMemberOrders.size()>0){
//			return 40;//成功
//		}else{
//			return 22;//不存在已审首购单
//		}
	}
	/**
	 * 活力小铺首购单业务判断21
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSMF(JpoMemberOrder jpoMemberOrder){
		JsysUser sysUser = jpoMemberOrder.getSysUser();
		JmiMember jmiMember = sysUser.getJmiMember();
		if(!"1".equals(jmiMember.getShopType())&&"6".equals(jmiMember.getCardType())){
			return 90;//成功
		}else{
			return 92;//不存在已审首购单
		}
	}
	/**
	 * 活力小铺重消单业务判断24
	 * @param jpoMemberOrder
	 * @return
	 */
	private int getJpoMemberOrderBusinessSMR(JpoMemberOrder jpoMemberOrder){
		JsysUser sysUser = jpoMemberOrder.getSysUser();
		JmiMember jmiMember = sysUser.getJmiMember();
		if("1".equals(jmiMember.getShopType())){
			return 90;//成功
		}else{
			return 92;//不存在已审首购单
		}
	}
	
	/**
	 * 更改会员角色
	 * @param sysUser
	 * @param roleId
	 */
	private void getChangeJmiMemberRole(JsysUser sysUser, String roleId){
		JsysRole sysRole=sysRoleManager.getSysRoleByCode(roleId);
		JsysUserRole sysUserRole=sysUserRoleManager.getSysUserRoleByUserCode(sysUser.getUserCode());
		sysUserRole.setRoleId(sysRole.getRoleId());
		sysUserRoleManager.save(sysUserRole);
	}
	
	/**
	 * 根据订单类型,用户团队,国别 确定此订单是否参与促销
	 * @param JpmSalePromoter
	 * @param jpoMemberOrder
	 * @return true or false;
	 */
	private boolean isOrderSales(JpmSalePromoter sp,JpoMemberOrder jpoMemberOrder)throws Exception{
		boolean flag = false;
	
		try{
			String sale_teams = sp.getTeamno();
			String sale_countrys = sp.getCompanyno();
			String sale_orders = sp.getOrdertype();
			
			String order_comCode = jpoMemberOrder.getCompanyCode();
			String order_type = jpoMemberOrder.getOrderType();
			String order_userCode = jmiTeamManager.teamStr(jpoMemberOrder.getSysUser());
			
			log.info("saleTeams is:["+sale_teams+"] and order Team is:["+order_userCode+"] " +
					"and country is:["+sale_countrys+"] and order company:["+order_comCode+"] " +
					"and sale_orders is:["+sale_orders+"] and orderType is:["+order_type+"]");

			if(sale_teams==null){
				sale_teams="";
			}

			if(sale_countrys.indexOf(order_comCode) != -1 && 
					sale_teams.indexOf(order_userCode) <0 && 
					isOrderType(sale_orders, order_type)){
				flag = true;
			}
			
		}catch(AppException e){
			throw new AppException("sale promoter failed.");
		}catch(Exception e){
			log.error("",e);
			throw new AppException("sale promoter failed.");
		}
		log.info("isOrderSales return is:"+flag);
		return flag;
	}
	
	/**
	 * 如果订单类型符合此策略定义的类型则返回true
	 * @param saleType 以逗号隔开的字符串
	 * @param type  需要比对的字符
	 * @return true or false
	 * @throws Exception
	 */
	public boolean isOrderType(String saleType,String type) throws Exception{
		
		if(StringUtils.isBlank(saleType)) return true;
		
		String[] saleTypeArr = saleType.split(",");
		boolean flag = false;
		for(int i=1; i<saleTypeArr.length;i++){
			if(saleTypeArr[i].equals(type)){
				flag = true;
				break;
			}
		}
		return flag;
	}
	
	/**
	 * <li>按商品绑定赠品</li>
	 * @param jpoMemberOrder
	 * @param JpmSalePromoter
	 * 0:N 代表商品购买的数量>0，则商品买多少就送多少；1:2代表商品购买的数量>1 则赠送2个商品，
	 * 1:N*3 代表购买的商品>1,则商品买多少就送购买的数量*3，1:1*3 代表购买的商品>1,则送购买的1*3  
	 */
	private Set<JpoMemberOrderList> bindPresentProduct(JpoMemberOrder jpoMemberOrder,JpmSalePromoter sp) throws Exception{
		Set<JpoMemberOrderList> orderListSet = new HashSet<JpoMemberOrderList>();
		String sale_proNo = sp.getPresentno();
		try{
			String limit = sp.getPresentlimit();
			log.info("limit is:["+limit+"]");
			
			String[] limitArr = new String[2];
			String shopNum="",perNum="",firStr = "",lasStr = "";
			int lasNum=0;
			
			if(limit.length()>3){
				firStr = limit.substring(0,3);
				lasStr = limit.substring(firStr.length(),limit.length());
				limitArr= firStr.split(":");
				shopNum = limitArr[0];
				perNum = limitArr[1];
				lasNum = Integer.parseInt(lasStr.substring(1));
			} else {
				limitArr = limit.split(":");
				shopNum = limitArr[0];
				perNum = limitArr[1];
			}
			
			log.info("shopNum is:["+shopNum+"] and perNum is:["+shopNum+"] " +
					"and firStr is:["+firStr+"] and lasStr is:["+lasStr+"] " +
							"and lasNum is:["+lasNum+"]");
			
			Iterator<JpoMemberOrderList> orderItems = jpoMemberOrder.getJpoMemberOrderList().iterator();
			while(orderItems.hasNext()){//获取订单中购买的产品
				JpoMemberOrderList orderItem = orderItems.next();
				String order_ProNo = orderItem.getJpmProductSaleTeamType().
						getJpmProductSaleNew().getJpmProduct().getProductNo();
				
				if(sale_proNo.equalsIgnoreCase(order_ProNo)){//订单中购买的产品在促销配置中
					/**
		    		 * 赠送模式: 2:N 即: 2为购买最低限制 ,N为赠品数量,可填N或数字
		    		 * 如果是 2:N,则 购买数量大于2 则送同等数量的赠品
		    		 * 若为2:1 , 则买2个送1个.
		    		 * 或者2:1*2 即:购买2个指定商品,就送 qty*2的赠品
		    		 */
					int num=1; 
					if(orderItem.getQty()>=Integer.parseInt(shopNum)){
						if(limit.length()>3 && lasStr.startsWith("/")){
							/*
							 * 避免除法运算为0时,赠送产品数量获取错误
							 */
							if(orderItem.getQty()>1){
								num = (int)Math.floor(orderItem.getQty()/lasNum);
							} else {
								if(perNum.equalsIgnoreCase("N"))
									num = orderItem.getQty();
								else 
									num = Integer.parseInt(perNum);
							}
							
						} else if(limit.length()>3 && lasStr.startsWith("*")){
							num = orderItem.getQty()*lasNum;
						} else {
							if(perNum.equalsIgnoreCase("N"))
								num = orderItem.getQty();
							else 
								num = Integer.parseInt(perNum);
						}
						//绑定赠品
						orderListSet = bindProduct(sp, jpoMemberOrder, num);
						log.info("orderListSet:....." + orderListSet );
					}
					
					if(log.isDebugEnabled()){
						log.debug("saleProNo is:["+sale_proNo +"] " +
								"and order ProNo is:["+order_ProNo+"] " +
								"and persent num is:"+num+"]");
					}
				}
				
				/*
    			 * 当购买数量,大于促销规定数量时,加送赠品
    			 * 列:购买数量大于2加送多个赠品 例 2:PRNO3202,PRNO00921
    			 */
    			if(StringUtils.isNotBlank(sp.getAppendpresent())){
    				String appendPre = sp.getAppendpresent();
    				log.info("appendPresent is:"+appendPre);
    				
    				String[] appPreArr = appendPre.split(":");
    				String[] proArr = appPreArr[1].split(",");
    				String saleNum = appPreArr[0];
    				if(orderItem.getQty() >= Integer.parseInt(saleNum)){
    					
    					for(int i=0; i<proArr.length ;i++){
	    					JpmProductSaleTeamType pstt = jpmProductSaleManager.
	    						getJpmProductSaleTeamTypeList(
	    							proArr[i], jpoMemberOrder.getTeamCode(),
	    							null,jpoMemberOrder.getCompanyCode(),"0,1,2");
		    				
	    					if(log.isDebugEnabled())
		    					log.debug("pstt is:"+pstt);
	    					
		    				if(pstt !=null && pstt.getJpmProductSaleNew()!=null){
		    					JpoMemberOrderList orderList = new JpoMemberOrderList();
			    				orderList.setJpmProductSaleTeamType(pstt);
			    				orderList.setJpoMemberOrder(jpoMemberOrder);
			    				orderList.setPrice(new BigDecimal("0"));
			    				orderList.setProductType("");
			    				orderList.setPv(new BigDecimal("0"));
			    				orderList.setQty(1);
			    				orderList.setVolume(new BigDecimal("0"));
			    				orderList.setWeight(new BigDecimal("0"));
			    				
			    				//jpoMemberOrder.getJpoMemberOrderList().add(orderList);
			    				orderListSet.add(orderList);
		    				} else {
		    					log.warn("product not exist, proNo is:"+proArr[i]+" " +
		    							"and orderNo is:"+jpoMemberOrder.getMemberOrderNo());
		    				}
	    				}
    				}
    			}
			}
			return orderListSet;
			
		}catch(AppException e){
			log.error("bind product failed ",e);
			throw new AppException("bind present failed!");
		}catch(Exception e){
			log.error("bind present failed, " +
					"orderNo is:"+jpoMemberOrder.getMemberOrderNo(),e);
			throw new AppException("bind present failed!");
		}
	}
	
	/**
	 * 赠送积分
	 * @param jpoMemberOrder
	 * @param JpmSalePromoter
	 * @param ispresent
	 * @param SysUser
	 */
	private void getBindIntegral(JpoMemberOrder jpoMemberOrder,JpmSalePromoter sp,String ispresent,JsysUser operatorUser,String dataType)throws Exception {
		try{
			if(validateAmountOrPV(jpoMemberOrder,sp)){
				BigDecimal amount = jpoMemberOrder.getAmount();
				//积分比例
				BigDecimal sale_integral =new BigDecimal(sp.getIntegral());
				Integer[] moneyType = new Integer[]{10};
				Long[] appId = new Long[]{2L};
				String[] notes = new String[1];
				BigDecimal[] moneyArray = new BigDecimal[1];
				JsysUser sysUser = jpoMemberOrder.getSysUser();
				moneyArray[0] = jpoMemberOrder.getAmount();
				String isCoin = jpoMemberOrder.getPayByCoin()==null?"0":jpoMemberOrder.getPayByCoin();
				String isOrder = sp.getIsorder();  // 0是积分换购的单不参与赠送
				BigDecimal jjAmount = new BigDecimal(0);
				
				log.info("orderNo is:["+jpoMemberOrder.getMemberOrderNo()+"] " +
						"and amount is:["+amount.toString()+"] " +
						"and fund amount is:["+jpoMemberOrder.getJjAmount()+"] " +
						"and isCoin is:["+isCoin+"]");
				/*
				 * 使用积分购买是否参与,0否, 1是.
				 */
				if(isCoin.equals("0")){ //没有使用积分
					//是否赠送推荐人0否, 1
					if(ispresent.equals("0")){
						
						notes = new String[]{"审单赠送积分=====" + jpoMemberOrder.getMemberOrderNo()};
						
						if(jpoMemberOrder.getJjAmount()!=null){
							jjAmount = jpoMemberOrder.getJjAmount();
						}
						moneyArray[0] = (amount.add(jjAmount)).multiply(sale_integral);
					
						fiBcoinJournalManager.saveFiPayDataVerify("CN", sysUser, 
								moneyType, moneyArray, operatorUser.getUserCode(), 
								operatorUser.getUserName(), "0", notes, appId, null,dataType);
						
					}else{
						
						JmiMember recMember = jpoMemberOrder.getSysUser().
								getJmiMember().getJmiRecommendRef().getRecommendJmiMember();;
						
						log.info("exitDate is:["+recMember.getExitDate()+"] " +
								"and saleOrderType is:["+sp.getPreordertype()+"] " +
								"and orderType is:["+jpoMemberOrder.getOrderType()+"]");
						
						if(recMember.getExitDate() ==null &&
								sp.getPreordertype().indexOf(jpoMemberOrder.getOrderType()) >-1){
							
							notes = new String[]{"审单赠送推荐人积分=====" + jpoMemberOrder.getMemberOrderNo()};
							BigDecimal reIntegral =new BigDecimal(sp.getReintegral());
							
							if(jpoMemberOrder.getJjAmount()!=null){
								jjAmount = jpoMemberOrder.getJjAmount();
							}
							moneyArray[0] = (amount.add(jjAmount)).multiply(reIntegral);
							
							JsysUser reSysUser = jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().
							getRecommendJmiMember().getSysUser();
							
							fiBcoinJournalManager.saveFiPayDataVerify("CN", reSysUser,
									moneyType, moneyArray, operatorUser.getUserCode(), 
									operatorUser.getUserName(), "0", notes, appId, null,dataType);
							
						}
					}
				}else{
					if("1".equals(isOrder)){
						//是否赠送推荐人0否, 1
						if(ispresent.equals("0")){
							
							notes = new String[]{"审单赠送积分=====" + jpoMemberOrder.getMemberOrderNo()};
							
							if(jpoMemberOrder.getJjAmount()!=null){
								jjAmount = jpoMemberOrder.getJjAmount();
							}
							moneyArray[0] = (amount.add(jjAmount)).multiply(sale_integral);
						
							fiBcoinJournalManager.saveFiPayDataVerify("CN", sysUser, 
									moneyType, moneyArray, operatorUser.getUserCode(), 
									operatorUser.getUserName(), "0", notes, appId, null,dataType);
							
						}else{
							
							JmiMember recMember = jpoMemberOrder.getSysUser().
									getJmiMember().getJmiRecommendRef().getRecommendJmiMember();;
							
							log.info("exitDate is:["+recMember.getExitDate()+"] " +
									"and saleOrderType is:["+sp.getPreordertype()+"] " +
									"and orderType is:["+jpoMemberOrder.getOrderType()+"]");
							
							if(recMember.getExitDate() ==null &&
									sp.getPreordertype().indexOf(jpoMemberOrder.getOrderType()) >-1){
								
								notes = new String[]{"审单赠送推荐人积分=====" + jpoMemberOrder.getMemberOrderNo()};
								BigDecimal reIntegral =new BigDecimal(sp.getReintegral());
								
								if(jpoMemberOrder.getJjAmount()!=null){
									jjAmount = jpoMemberOrder.getJjAmount();
								}
								moneyArray[0] = (amount.add(jjAmount)).multiply(reIntegral);
								
								JsysUser reSysUser = jpoMemberOrder.getSysUser().getJmiMember().getJmiRecommendRef().
								getRecommendJmiMember().getSysUser();
								
								fiBcoinJournalManager.saveFiPayDataVerify("CN", reSysUser,
										moneyType, moneyArray, operatorUser.getUserCode(), 
										operatorUser.getUserName(), "0", notes, appId, null,dataType);
								
							}
						}
					}
				}
				log.info("present money is:["+moneyArray[0]+"] user is:"+sysUser.getUserCode());
			}
			
		}catch(AppException e){
			throw new AppException("present fund error!");
		}catch(Exception e){
			throw new AppException("present fund error!");
		}
		 	
    }
	
	/**
	 * 根据订单总金额或PV,或订单类型绑定赠品
	 * @param order
	 * @param sp
	 */
	private Set<JpoMemberOrderList> getOrderAmountBindProduct(JpoMemberOrder order,JpmSalePromoter sp) throws Exception{
		
		log.info("orderNo :["+order.getMemberOrderNo()+"] " +
				"present product num is:"+sp.getSaleProSet().size());
		
		try{
			/*
			 * 当总金额和PV都为0时 , 按订单类型绑定赠品
			 */
			if(sp.getAmount().compareTo(new BigDecimal(0))==0 && sp.getPv()==0L){
				
				String sale_OrderType = sp.getOrdertype();
				String orderType = order.getOrderType();
				log.info("sale orderType is:["+sale_OrderType+"] and orderType is:"+orderType);
				
				if(isOrderType(sale_OrderType,orderType)){
					return bindProduct(sp,order,0);
				}
			} else {
				
				if(validateAmountOrPV(order, sp)){
					return bindProduct(sp,order,0);
				}
			}
			
		}catch(AppException e){
			log.info(e);
			throw new AppException("sale promoter failed.");
		}catch(Exception e){
			log.info(e);
			throw new AppException("sale promoter failed.");
		}
		return new HashSet<JpoMemberOrderList>();
	}
	
	/**
	 * 扣款
	 * @param jpoMemberOrder
	 * @param operatorSysUser
	 */
	private void getSaveMemberOrderDeduct(JpoMemberOrder jpoMemberOrder, JsysUser operatorSysUser, String dataType){
		String companyCode = jpoMemberOrder.getCompanyCode();
		String userSpCode = jpoMemberOrder.getUserSpCode();
		JsysUser sysUserSp = jsysUserDao.get(userSpCode);
		
		if("4".equals(jpoMemberOrder.getOrderType())&&"1".equals(jpoMemberOrder.getCompanyPay())){
			JalCompany alCompany = alCompanyManager.getAlCompanyByCode(jpoMemberOrder.getCompanyCode());
			sysUserSp = jsysUserDao.getSysUser(alCompany.getPreAgentCode());
			if(sysUserSp==null){
				throw new AppException("公司收款会员不存在!"); 
			}
		}
		BigDecimal isPayFee = new BigDecimal(0);
		String operaterCode = operatorSysUser.getUserCode();
		String operaterName = operatorSysUser.getUserName();
		String uniqueCode = jpoMemberOrder.getMemberOrderNo();
		
		BigDecimal[] moneyArray = new BigDecimal[jpoMemberOrder.getJpoMemberOrderFee().size()+1];
		Integer[] moneyType = new Integer[jpoMemberOrder.getJpoMemberOrderFee().size()+1];
		String[] notes = new String[jpoMemberOrder.getJpoMemberOrderFee().size()+1];
		
		//重新计算订单金额
		BigDecimal productMoney = new BigDecimal(0);
		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its1.hasNext()) {
			
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			productMoney = productMoney.add(jpoMemberOrderList.getPrice().multiply(new BigDecimal(jpoMemberOrderList.getQty())));//产品总金额
		}
		
		if("1".equals(jpoMemberOrder.getPayByCoin()) && jpoMemberOrder.getDiscountAmount()!=null){//积分支付
			
			productMoney = productMoney.subtract(jpoMemberOrder.getDiscountAmount());
		}
		
		if("1".equals(jpoMemberOrder.getPayByJj()) && jpoMemberOrder.getJjAmount()!=null){//基金支付
			
			productMoney = productMoney.subtract(jpoMemberOrder.getJjAmount());//支付金额=产品总金额-基金支付金额
			
			if(productMoney.compareTo(new BigDecimal(0))==-1){//支付金额小于0
				
				isPayFee = jpoMemberOrder.getJjAmount().subtract(productMoney);//物流费=基金支付金额-产品总额
				//productMoney = new BigDecimal(0);
				productMoney = jpoMemberOrder.getAmount();
			}
			
		}
		
		moneyArray[0] = productMoney;//支付金额
		moneyType[0] = 13;
		notes[0] = this.getLocalLanguageByKey("poMemberOrder.orderConfirm",operatorSysUser) + jpoMemberOrder.getMemberOrderNo();
		if(!userSpCode.equals(jpoMemberOrder.getSysUser().getUserCode())){
			notes[0] += "," + jpoMemberOrder.getSysUser().getUserCode();
		}
		
    	Iterator its2 = jpoMemberOrder.getJpoMemberOrderFee().iterator();
    	int i = 1;
    	while (its2.hasNext()) {
			JpoMemberOrderFee jpoMemberOrderFee = (JpoMemberOrderFee) its2.next();
			
			if(isPayFee.compareTo(new BigDecimal(0))==1){//
				if(jpoMemberOrderFee.getFee().compareTo(isPayFee)==1){
					moneyArray[i] = jpoMemberOrderFee.getFee().subtract(isPayFee);
					isPayFee = new BigDecimal(0);
				}else{
					moneyArray[i] = new BigDecimal(0);
					isPayFee = isPayFee.subtract(jpoMemberOrderFee.getFee());
				}
			}else{
				moneyArray[i] = jpoMemberOrderFee.getFee();
			}
			
			//moneyType[i] = 1000 + Integer.parseInt(jpoMemberOrderFee.getFeeType());
			if("1".equals(jpoMemberOrderFee.getFeeType())){
				notes[i] = this.getLocalLanguageByKey("poOrder.mailFee",operatorSysUser);
				moneyType[i] = 32;
			}else if("2".equals(jpoMemberOrderFee.getFeeType())){
				notes[i] = this.getLocalLanguageByKey("poOrder.handlingUSFee",operatorSysUser);
				moneyType[i] = 30;
			}else if("3".equals(jpoMemberOrderFee.getFeeType())){
				notes[i] = this.getLocalLanguageByKey("poOrder.sax",operatorSysUser);
				moneyType[i] = 33;
			}else if("4".equals(jpoMemberOrderFee.getFeeType())){
				notes[i] = this.getLocalLanguageByKey("poOrder.enrollerBonus",operatorSysUser);
				moneyType[i] = 63;
			}else{
				notes[i] = this.getLocalLanguageByKey("fiBankbookTemp.moneyType.7",operatorSysUser);
				moneyType[i] = 34;
			}
			i++;
		}
    	
    	//免单
    	if("1".equals(jpoMemberOrder.getIsFree())){
        	for(int m = 0 ; m < moneyArray.length ; m++){
        		moneyArray[m] = new BigDecimal("0");
        	}
    	}
    	
    	//判断订单是否包含旅游积分补款产品,决定资金类别是否为24
    	if(this.jpoMemberOrderIsIncludeTourCoin(jpoMemberOrder)){
    		moneyType[0] = 24;
    	}
    	
		jfiBankbookJournalManager.saveJfiBankbookDeduct(companyCode, sysUserSp, moneyType, moneyArray, operaterCode, operaterName, uniqueCode, notes, dataType);
	}
	
	/**
	 * 绑定商品
	 * @param sp
	 * @param order
	 * @param flag 
	 * 0:按订单类型或总金额,赠送产品数量取策略中的数量
	 * @throws Exception
	 */
	private Set<JpoMemberOrderList> bindProduct(JpmSalePromoter sp ,JpoMemberOrder order,int flag) throws Exception{
		try{
			Set<JpoMemberOrderList> set = new HashSet<JpoMemberOrderList>();
			Iterator<JpmSalepromoterPro> iter = sp.getSaleProSet().iterator();
			while(iter.hasNext()){
				JpmSalepromoterPro spro = iter.next();
				String proNo = spro.getProno();
				//绑定赠品
				JpmProductSaleTeamType jpmProductSale = jpmProductSaleManager.
						getJpmProductSaleTeamTypeList(proNo, order.getTeamCode(), 
								null, order.getCompanyCode(),"0,1,2");
				
				if(jpmProductSale !=null){
					JpoMemberOrderList jpoMemberOrderList = new JpoMemberOrderList();
					jpoMemberOrderList.setJpmProductSaleTeamType(jpmProductSale);
					jpoMemberOrderList.setJpoMemberOrder(order);
					jpoMemberOrderList.setPrice(new BigDecimal("0"));
					jpoMemberOrderList.setProductType("");
					jpoMemberOrderList.setPv(new BigDecimal("0"));
					jpoMemberOrderList.setVolume(new BigDecimal("0"));
					jpoMemberOrderList.setWeight(new BigDecimal("0"));
					
					if(flag>0){
						jpoMemberOrderList.setQty(flag);
					}else {
						jpoMemberOrderList.setQty(spro.getPronum().intValue());	
					}
					set.add(jpoMemberOrderList);
				} else{
					throw new AppException("未找到赠品,赠品编号:"+proNo);
				}
			}
			return set;
		}catch(Exception e){
			log.error("",e);
			throw e;
		}
	}
	public void setFiBankbookJournalManager(
			FiBankbookJournalManager fiBankbookJournalManager) {
		this.fiBankbookJournalManager = fiBankbookJournalManager;
	}

	
	public boolean getJpoMemberMark(String papernumber, String productNo, String orderType) {
		boolean  validUser=false;//没有购买过事业锦囊
	    List  list=jpoMemberOrderDao.getJpoMemberMark(papernumber, productNo, orderType);
	  if(list!=null)
	  {
	       if(list.size()>0)
	     {
	    	validUser=true;//会员购买过事业锦囊;
	     }
	  }
	 
		return validUser;
	}
	
	/**
	 * 验证订单总金额和PV是否达到指定值
	 * @param jpoMemberOrder
	 * @param sp
	 * @return true or fasle;
	 */
	private boolean validateAmountOrPV(JpoMemberOrder jpoMemberOrder,JpmSalePromoter sp){
		boolean flag = false;
		

//		String str_startDate = LocaleUtil.getLocalText("zh_CN", "20151201cx.startDate");
//		String str_endDate = LocaleUtil.getLocalText("zh_CN", "20151201cx.endDate");

		String startDateS = ConfigUtil.getConfigValue("CN", "20151201cx.startdate");
		String endDateE = ConfigUtil.getConfigValue("CN", "20151201cx.enddate");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date curDate = Calendar.getInstance().getTime();
		
		try {
			
			if(sp.getSaleType().equals(Constants.JPMSALE_SALETYPE_ORDER) && curDate.after(sdf.parse(startDateS)) && curDate.before(sdf.parse(endDateE))){
				
				BigDecimal pv = jpoMemberOrder.getPvAmt();
				
				Iterator set = jpoMemberOrder.getJpoMemberOrderList().iterator();
				while (set.hasNext()) {
					JpoMemberOrderList orderList = (JpoMemberOrderList) set.next();
					String proNo = orderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getProductNo();
					log.info("jpoMemberOrder: "+ jpoMemberOrder.getMemberOrderNo() + "  proNo:.......20151201: " + proNo + "..." + GlobalVar.jpoList20151201.contains(proNo));
					
					if (GlobalVar.jpoList20151201.contains(proNo)) {  
						pv = pv.subtract(new BigDecimal(5500).multiply(new BigDecimal(orderList.getQty())));
					}
				}
				
				log.info(".......20151201pv: " + pv);
				
				if(pv.compareTo(new BigDecimal(5500))>=0){
					
					if(sp.getAmount().compareTo(new BigDecimal(0))>0 && sp.getPv()>0L){
						if(jpoMemberOrder.getAmount().compareTo(sp.getAmount())>=0 && 
								pv.compareTo(new BigDecimal(sp.getPv()))>=0){
								flag = true;
						}
					}else if(sp.getAmount().compareTo(new BigDecimal(0))<=0 && sp.getPv()>0L){
						if(pv.compareTo(new BigDecimal(sp.getPv())) >=0){
							flag = true;
						}
					} else if(sp.getAmount().compareTo(new BigDecimal(0))>0 && sp.getPv() <= 0L){
						if(jpoMemberOrder.getAmount().compareTo(sp.getAmount())>=0){
							flag = true;
						}
					} else if(sp.getAmount().compareTo(new BigDecimal(0))==0 && sp.getPv()==0L){
						flag = true;
					}
				}
				
				
			}else{
				
				/*
				 * 1.总金额>0 和PV >0        的情况
				 * 2.总金额 <=0   和 PV >0 的情况
				 * 3.总金额>0 和 PV <=0    的情况
				 * 4.总金额==0 和 PV==0    的情况
				 */
				if(sp.getAmount().compareTo(new BigDecimal(0))>0 && sp.getPv()>0L){
					if(jpoMemberOrder.getAmount().compareTo(sp.getAmount())>=0 && 
							jpoMemberOrder.getPvAmt().compareTo(new BigDecimal(sp.getPv()))>=0){
							flag = true;
					}
				}else if(sp.getAmount().compareTo(new BigDecimal(0))<=0 && sp.getPv()>0L){
					if(jpoMemberOrder.getPvAmt().compareTo(new BigDecimal(sp.getPv())) >=0){
						flag = true;
					}
				} else if(sp.getAmount().compareTo(new BigDecimal(0))>0 && sp.getPv() <= 0L){
					if(jpoMemberOrder.getAmount().compareTo(sp.getAmount())>=0){
						flag = true;
					}
				} else if(sp.getAmount().compareTo(new BigDecimal(0))==0 && sp.getPv()==0L){
					flag = true;
				}
				
			}
			
		} catch (ParseException e) {
			log.info(e);
			e.printStackTrace();
		}
		
		
		
		
		log.info("order amount is:["+jpoMemberOrder.getAmount()+"] " +
				"and salePromoter amount is:["+sp.getAmount()+"] " +
				"orderPV:["+jpoMemberOrder.getPvAmt()+"] " +
				"and salePromoter PV is:"+sp.getPv()+"] and flag is:"+flag);
		return flag;
	}
	

	/**
	 * 判断订单是否包含旅游积分补款产品
	 * @param jpoMemberOrder 订单对象
	 * @return 如果包含，返回true;否则，返回false
	 */
	public boolean jpoMemberOrderIsIncludeTourCoin(JpoMemberOrder jpoMemberOrder){
		
		boolean flag = false;
		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its1.hasNext()) {
			JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
			String proNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
			//如果包含旅游积分补款的商品
			if(proNo.equalsIgnoreCase("N07010100101CN0")){
				flag=true;
				break;
			}

		}
		return flag;
	}
	
	
	private JpoMemberOrder generateMemberOrder(JpoMemberOrder memberOrder , JmiMember jmiMember, int i){
		JpoMemberOrder jpoMemberOrder = new JpoMemberOrder();
		
	
          jpoMemberOrder.setSysUser(jmiMember.getSysUser());
          jpoMemberOrder.setCompanyCode(jmiMember.getCompanyCode());
          jpoMemberOrder.setUserSpCode(jmiMember.getUserCode());
          jpoMemberOrder.setCountryCode(jmiMember.getCountryCode());
          jpoMemberOrder.setOrderUserCode(jmiMember.getUserCode());
          jpoMemberOrder.setOrderTime(new Date());
          jpoMemberOrder.setSubmitTime(memberOrder.getSubmitTime());
          jpoMemberOrder.setSubmitUserCode(memberOrder.getSubmitUserCode());
          
          jpoMemberOrder.setLocked(memberOrder.getLocked());
          jpoMemberOrder.setOrderType(memberOrder.getOrderType());
          jpoMemberOrder.setPickup(memberOrder.getPickup());//是否自动提货
          
          jpoMemberOrder.setConsumerAmount(new BigDecimal(0));
          jpoMemberOrder.setAmount(new BigDecimal(0)); 
          jpoMemberOrder.setAmount2(new BigDecimal(0)); //Modify By WuCF 20160811 amount2的值不能修改，注释掉
          if(i==0){
        	  jpoMemberOrder.setPvAmt(new BigDecimal(22000));
          }else{
        	  jpoMemberOrder.setPvAmt(new BigDecimal(5500));
          }
        
          jpoMemberOrder.setPayMode(memberOrder.getPayMode());//付款方式
          jpoMemberOrder.setIsSpecial(memberOrder.getIsSpecial());//是否为特殊订单
  
          jpoMemberOrder.setProvince(memberOrder.getProvince());
          jpoMemberOrder.setCity(memberOrder.getCity());
          jpoMemberOrder.setDistrict(memberOrder.getDistrict());
          jpoMemberOrder.setAddress(memberOrder.getAddress());
          jpoMemberOrder.setFirstName(memberOrder.getFirstName());
          jpoMemberOrder.setLastName(memberOrder.getLastName());
          jpoMemberOrder.setPostalcode(memberOrder.getPostalcode());
          jpoMemberOrder.setMobiletele(memberOrder.getMobiletele());
          jpoMemberOrder.setPhone(memberOrder.getPhone());
          jpoMemberOrder.setIsMobile(memberOrder.getIsMobile());
          
          jpoMemberOrder.setStatus("2");
          jpoMemberOrder.setSubmitStatus("2");
          jpoMemberOrder.setIsPay("1");
          jpoMemberOrder.setCheckDate(new Date());
          jpoMemberOrder.setCheckDateUserCode(memberOrder.getCheckDateUserCode());
          
          JpoMemberOrderList jpoMemberOrderList = new JpoMemberOrderList();
//          jpoMemberOrderList.setJpmProductSaleTeamType(jpmProductSaleTeamType);
          jpoMemberOrder.getJpoMemberOrderList().add(jpoMemberOrderList);
          
		return jpoMemberOrder;
	}

}
