package com.joymain.ng.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.FiBankbookBalanceDao;
import com.joymain.ng.dao.FiBankbookJournalDao;
import com.joymain.ng.dao.FiBankbookTempDao;
import com.joymain.ng.dao.FiProductPointBalanceDao;
import com.joymain.ng.dao.FiProductPointJournalDao;
import com.joymain.ng.dao.FiProductPointTempDao;
import com.joymain.ng.dao.JfiBankbookBalanceDao;
import com.joymain.ng.dao.JfiBankbookJournalDao;
import com.joymain.ng.dao.JpoUserCouponDao;
import com.joymain.ng.model.FiBankbookBalance;
import com.joymain.ng.model.FiBankbookJournal;
import com.joymain.ng.model.FiProductPointBalance;
import com.joymain.ng.model.FiProductPointJournal;
import com.joymain.ng.model.FiProductPointTemp;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JfiBankbookJournal;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiProductPointJournalManager;
import com.joymain.ng.service.JfiPosImportManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.GroupPage;

@Service("fiProductPointJournalManager")
@WebService(serviceName = "FiProductPointJournalService", endpointInterface = "com.joymain.ng.service.FiProductPointJournalManager")
public class FiProductPointJournalManagerImpl extends GenericManagerImpl<FiProductPointJournal, Long> implements FiProductPointJournalManager {
	
	private JfiBankbookJournalDao jfiBankbookJournalDao;
	private FiBankbookJournalDao fiBankbookJournalDao;
	private FiProductPointBalanceDao fiProductPointBalanceDao;
	private FiProductPointJournalDao fiProductPointJournalDao;
	private FiProductPointTempDao fiProductPointTempDao;
	
	private JfiBankbookBalanceDao jfiBankbookBalanceDao;
	
	private JpoUserCouponDao jpoUserCouponDao;
	FiBankbookBalanceDao fiBankbookBalanceDao;

    @Autowired
    public void setFiBankbookBalanceDao(FiBankbookBalanceDao fiBankbookBalanceDao) {
		this.fiBankbookBalanceDao = fiBankbookBalanceDao;
	}
	
	@Autowired
	public void setJpoUserCouponDao(JpoUserCouponDao jpoUserCouponDao) {
		this.jpoUserCouponDao = jpoUserCouponDao;
	}


	@Autowired
	public void setJfiBankbookJournalDao(JfiBankbookJournalDao jfiBankbookJournalDao) {
		this.jfiBankbookJournalDao = jfiBankbookJournalDao;
	}

	@Autowired
	public void setJfiBankbookBalanceDao(JfiBankbookBalanceDao jfiBankbookBalanceDao) {
		this.jfiBankbookBalanceDao = jfiBankbookBalanceDao;
	}

	@Autowired
    public FiProductPointJournalManagerImpl(FiProductPointJournalDao fiProductPointJournalDao) {
        super(fiProductPointJournalDao);
        this.fiProductPointJournalDao = fiProductPointJournalDao;
    }
	
	@Autowired
	public void setFiProductPointBalanceDao(FiProductPointBalanceDao fiProductPointBalanceDao) {
		this.fiProductPointBalanceDao = fiProductPointBalanceDao;
	}
	
	@Autowired
	public void setFiProductPointJournalDao(FiProductPointJournalDao fiProductPointJournalDao) {
		this.fiProductPointJournalDao = fiProductPointJournalDao;
	}
	
	@Autowired
	public void setFiProductPointTempDao(FiProductPointTempDao fiProductPointTempDao) {
		this.fiProductPointTempDao = fiProductPointTempDao;
	}

	
	/**
	 * 从产品积分帐户扣取金额
	 * @param isPayAllByProductPoint  是否产品积分全额支付，true表示产品积分全额支付，false表示部分支付
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * @param uniqueCode 业务唯一码,0为没有
	 * @param notes 存款说明
	 * @param dataType 数据来源,1:PC, 2:手机
	 * @throws AppException 存款不够的错误
	 */
	@Override
	public void saveFiProductDeduct_pay(boolean isPayAllByProductPoint,String companyCode, JsysUser sysUser,
			Integer[] moneyType, BigDecimal[] moneyArray, String operaterCode,
			String operaterName, String uniqueCode, String[] notes, String dataType,BigDecimal needPayMoney,String checkType) throws AppException {
		
		BigDecimal jBalance = new BigDecimal(0); //电子存折余额
		BigDecimal fiBalance = new BigDecimal(0); //基金余额
		if(!isPayAllByProductPoint) {
			if("2".equals(checkType)){//发展基金
				log.info("checkType    ----"+checkType);
				//查询发展基金账户
				FiBankbookBalance fiBankbookBalance = fiBankbookBalanceDao.getFiBankbookBalance(sysUser.getUserCode(), "1");
				if (fiBankbookBalance == null) {
					throw new AppException("发展基金帐户不存在!");
				}

				//判断是否基金重复审单
				if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
					JfiBankbookJournal lastJfiBankbookJournal = jfiBankbookJournalDao.getLastJfiBankbookJournalByUnique(uniqueCode,"D");
					if (lastJfiBankbookJournal != null && "D".equals(lastJfiBankbookJournal.getDealType())) {
						throw new AppException("发展基金已扣过款了！");
					}
				}
				log.info("===================发展基金saveFiProductDeduct_pay:"+moneyArray);
				if (fiBankbookBalance.getBalance().compareTo(needPayMoney) == -1) {
					throw new AppException("发展基金帐户余额不够");
				}

				fiBalance= fiBankbookBalance.getBalance();
			}else{//存折
				// =================================== 电子存折部分的判断 ================================
				//判断电子存折帐户是否存在
				JfiBankbookBalance jfiBankbookBalance = this.jfiBankbookBalanceDao.getJfiBankbookBalanceForUpdate(sysUser.getUserCode());
				if (jfiBankbookBalance == null) {
					throw new AppException("电子存折帐户不存在!");
				}

				//判断是否重复审单
				if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
					JfiBankbookJournal lastJfiBankbookJournal = jfiBankbookJournalDao.getLastJfiBankbookJournalByUnique(uniqueCode,"D");
					if (lastJfiBankbookJournal != null && "D".equals(lastJfiBankbookJournal.getDealType())) {
						throw new AppException("已扣过款了！");
					}
				}
				log.info("===================saveFiProductDeduct_pay:"+moneyArray);
				if (jfiBankbookBalance.getBalance().compareTo(needPayMoney) == -1) {
					throw new AppException("电子存折帐户余额不够");
				}
				jBalance= jfiBankbookBalance.getBalance();
				// =================================== 电子存折部分的判断 ================================
			}

		}
		Date currentDate = new Date();
		Date currentTime = new Date();
		BigDecimal money = new BigDecimal(0);
		for(int i = 0 ; i < moneyArray.length ; i++){
			log.info("moneyArray[i]:"+moneyArray[i]);
			if(moneyArray[i].compareTo(new BigDecimal("0"))==-1){
				throw new AppException("金额参数必须大于或等于零"+moneyArray[i]);
			}
			money = money.add(moneyArray[i]);
		}
		//首先验证余额是否足够
		//JfiBankbookJournal lastJfiBankbookJournal = this.jfiBankbookJournalDao.getLastJfiBankbookJournal(sysUser.getUserCode());
		FiProductPointJournal lastFiProductPointJournal = fiProductPointJournalDao.getLastFiProductPointJournal(sysUser.getUserCode());
		log.info("==============:"+lastFiProductPointJournal.getBalance()+"--"+money);
		
		//余额是否满足扣款
		BigDecimal remainMoney;
		if (lastFiProductPointJournal == null) {
			remainMoney = new BigDecimal(0);
		} else {
			remainMoney = lastFiProductPointJournal.getBalance();
		}
		if(!isPayAllByProductPoint) {
			if("2".equals(checkType)){
				//抵用券余额和基金余额之和
				BigDecimal sum = remainMoney.add(fiBalance);
				if (remainMoney.compareTo(money) == -1 && sum.compareTo(money) == -1) {
					throw new AppException("抵用券余额和基金余额之和不足已支付订单总额（"+money+"）");
				}
			}else {
				//抵用券余额和电子存在余额之和
				BigDecimal sum = remainMoney.add(jBalance);
				if (remainMoney.compareTo(money) == -1 && sum.compareTo(money) == -1) {
					throw new AppException("抵用券余额和电子存折余额之和不足已支付订单总额（"+money+"）");
				}
			}

		}
	}
	/**
	 * Add By WuCF 20170526
	 * 代金券支付金额判断
	 * @param isPayAllByProductPoint  是否产品积分全额支付，true表示产品积分全额支付，false表示部分支付
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * @param uniqueCode 业务唯一码,0为没有
	 * @param notes 存款说明
	 * @param dataType 数据来源,1:PC, 2:手机
	 * @throws AppException 存款不够的错误
	 */
	@Override
	public void saveFiCouponDeduct_pay(boolean isPayAllByProductPoint,String companyCode, JsysUser sysUser,
			Integer[] moneyType, BigDecimal[] moneyArray, String operaterCode,
			String operaterName, String uniqueCode, String[] notes, String dataType,BigDecimal needPayMoney,String checkType) throws AppException {
		
		BigDecimal jBalance = new BigDecimal(0); //货币余额
		if(!isPayAllByProductPoint) {
			if("2".equals(checkType)){//发展基金
				//查询发展基金账户
		        FiBankbookBalance fiBankbookBalance = fiBankbookBalanceDao.getFiBankbookBalance(sysUser.getUserCode(), "1");
		        if (fiBankbookBalance == null) {
					throw new AppException("发展基金帐户不存在!");
				}
				
				//判断是否基金重复审单
				if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
					JfiBankbookJournal lastJfiBankbookJournal = jfiBankbookJournalDao.getLastJfiBankbookJournalByUnique(uniqueCode,"D");
					if (lastJfiBankbookJournal != null && "D".equals(lastJfiBankbookJournal.getDealType())) {
						throw new AppException("发展基金已扣过款了！");
					}
				}
				log.info("===================发展基金saveFiProductDeduct_pay:"+moneyArray); 
				if (fiBankbookBalance.getBalance().compareTo(needPayMoney) == -1) {
					throw new AppException("发展基金帐户余额不够");
				}
				jBalance= fiBankbookBalance.getBalance();
			}else if("3".equals(checkType)){//抵用券
				 // 查询抵用券账户
		        FiProductPointBalance productPointBalance = fiProductPointBalanceDao.getFiProductPointBalance(sysUser.getUserCode(), "1");
		        if (productPointBalance == null) {
					throw new AppException("帐户不存在!");
				}
				
				//判断是否抵用券重复审单
				if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
					JfiBankbookJournal lastJfiBankbookJournal = jfiBankbookJournalDao.getLastJfiBankbookJournalByUnique(uniqueCode,"D");
					if (lastJfiBankbookJournal != null && "D".equals(lastJfiBankbookJournal.getDealType())) {
						throw new AppException("已扣过款了！");
					}
				}
				log.info("===================抵用券saveFiProductDeduct_pay:"+moneyArray); 
				if (productPointBalance.getBalance().compareTo(needPayMoney) == -1) {
					throw new AppException("帐户余额不够");
				}
				jBalance= productPointBalance.getBalance();
			}else{//存折
				// =================================== 电子存折部分的判断 ================================
				//判断电子存折帐户是否存在
				JfiBankbookBalance jfiBankbookBalance = this.jfiBankbookBalanceDao.getJfiBankbookBalanceForUpdate(sysUser.getUserCode());
				if (jfiBankbookBalance == null) {
					throw new AppException("电子存折帐户不存在!");
				}
				
				//判断是否电子存折则重复审单
				if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
					JfiBankbookJournal lastJfiBankbookJournal = jfiBankbookJournalDao.getLastJfiBankbookJournalByUnique(uniqueCode,"D");
					if (lastJfiBankbookJournal != null && "D".equals(lastJfiBankbookJournal.getDealType())) {
						throw new AppException("电子存折已扣过款了！");
					}
				}
				log.info("===================电子存折saveFiProductDeduct_pay:"+moneyArray); 
				if (jfiBankbookBalance.getBalance().compareTo(needPayMoney) == -1) {
					throw new AppException("电子存折帐户余额不够");
				}
				jBalance= jfiBankbookBalance.getBalance();
				// =================================== 电子存折部分的判断 ================================
			}
				
		}
	}

	
	
	@Override
	public List<FiProductPointJournal> getJfiProductPointJournalListByUserCodePage(
			GroupPage page, String userCode, String dealStartTime,
			String dealEndTime) {
		// TODO Auto-generated method stub
		return fiProductPointJournalDao.getJfiProductPointJournalListByUserCodePage(page,userCode,dealStartTime,dealEndTime);
	}
	
	public List<FiProductPointJournal> getFiProductPointJournalListByUser(String userCode, String dealStartTime, String dealEndTime,int pageNum,int pageSize){
		return fiProductPointJournalDao.getFiProductPointJournalListByUser(userCode, dealStartTime, dealEndTime,pageNum,pageSize);
	}
}