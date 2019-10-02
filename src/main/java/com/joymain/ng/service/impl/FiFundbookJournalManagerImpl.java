package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FiFundbookBalanceDao;
import com.joymain.ng.dao.FiFundbookJournalDao;
import com.joymain.ng.model.FiFundbookBalance;
import com.joymain.ng.model.FiFundbookJournal;
import com.joymain.ng.service.FiFundbookJournalManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

@Service("fiFundbookJournalManager")
@WebService(serviceName = "FiFundbookJournalService", endpointInterface = "com.joymain.ng.service.FiFundbookJournalManager")
public class FiFundbookJournalManagerImpl extends GenericManagerImpl<FiFundbookJournal, Long> implements FiFundbookJournalManager {
    FiFundbookJournalDao fiFundbookJournalDao;
    private FiFundbookBalanceDao fiFundbookBalanceDao;
    
    @Autowired
    public FiFundbookJournalManagerImpl(FiFundbookJournalDao fiFundbookJournalDao) {
        super(fiFundbookJournalDao);
        this.fiFundbookJournalDao = fiFundbookJournalDao;
    }
    
    @Autowired	
	public void setFiFundbookBalanceDao(FiFundbookBalanceDao fiFundbookBalanceDao) {
		this.fiFundbookBalanceDao = fiFundbookBalanceDao;
	}



	public Pager<FiFundbookJournal> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiFundbookJournalDao.getPager(FiFundbookJournal.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	
	public List<FiFundbookJournal> getFiFundbookJournalListByUserPage(GroupPage page, String userCode, String bankbookType, String dealStartTime,String dealEndTime){
		
		return fiFundbookJournalDao.getFiFundbookJournalListByUserPage(page,userCode, bankbookType, dealStartTime, dealEndTime);
	}
	
	/**
	 * 存入资金至产业基金帐户
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * @param uniqueCode 唯一码，用于防止重复,0为没有
	 * @param bankbookType 基金类型：1，分红基金；2，定向基金
	 * @param notes 存款说明
	 */
	public void saveFiPayDataVerify(final String companyCode, final String userCode, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, final String bankbookType, final String dataType){
		
		//判断用户帐户是否存在
		FiFundbookBalance fiFundbookBalanceTmp = this.fiFundbookBalanceDao.getFiFundbookBalanceByUserCodeAndFundbookType(userCode, bankbookType);
		FiFundbookBalance fiFundbookBalance = this.fiFundbookBalanceDao.getFiFundbookBalanceForUpdate(fiFundbookBalanceTmp.getFbbId());
		if (fiFundbookBalance == null) {
			
			throw new AppException("基金帐户不存在!");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			FiFundbookJournal lastFiFundbookJournal = fiFundbookJournalDao.getLastFiFundbookJournalByUnique(uniqueCode,"A");
			if (lastFiFundbookJournal != null && "A".equals(lastFiFundbookJournal.getDealType())) {
				throw new AppException("重复操作");
			}
		}
		
		BigDecimal money = new BigDecimal(0);
		for(int i = 0 ; i < moneyArray.length ; i++){
			if(moneyArray[i].compareTo(new BigDecimal("0"))==-1){
				throw new AppException("金额参数必须大于零");
			}
			money = money.add(moneyArray[i]);
		}

		BigDecimal remainMoney;
		BigDecimal tempMoney;
		FiFundbookJournal lastFiFundbookJournal = this.fiFundbookJournalDao.getLastFiFundbookJournal(userCode,bankbookType);
		if (lastFiFundbookJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastFiFundbookJournal.getBalance();
			tempMoney = lastFiFundbookJournal.getBalance();
		}

		Date currentDate = new Date();
		Date currentTime = new Date();
		for(int i = 0 ; i < moneyArray.length ; i++){

//			//插入存折临时记录
//			FiFundbookTemp fiFundbookTemp = new FiFundbookTemp();
//			fiFundbookTemp.setCompanyCode(companyCode);
//			fiFundbookTemp.setUserCode(sysUser.getUserCode());
//			long totalCount = this.fiFundbookTempDao.getCountByDate(companyCode, sysUser.getUserCode(), bankbookType);
//			fiFundbookTemp.setSerial(totalCount + 1);
//			fiFundbookTemp.setDealType("A");
//			fiFundbookTemp.setDealDate(currentDate);
//			fiFundbookTemp.setMoneyType(moneyType[i]);
//			fiFundbookTemp.setMoney(moneyArray[i]);
//			fiFundbookTemp.setNotes(notes[i]);
//			fiFundbookTemp.setStatus(2);
//			fiFundbookTemp.setCreaterCode(operaterCode);
//			fiFundbookTemp.setCreaterName(operaterName);
//			fiFundbookTemp.setCreateTime(currentTime);
//			fiFundbookTemp.setCheckerCode(operaterCode);
//			fiFundbookTemp.setCheckerName(operaterName);
//			fiFundbookTemp.setCheckeTime(currentTime);
//			fiFundbookTemp.setCheckMsg("OK");
//			fiFundbookTemp.setCheckType(1);
//			fiFundbookTemp.setBankbookType(bankbookType);
//
//			fiFundbookTempDao.saveFiFundbookTemp(fiFundbookTemp);
			//插入产业基金流水记录
			FiFundbookJournal fiFundbookJournal = new FiFundbookJournal();
			//fiFundbookJournal.setFiFundbookTemp(fiFundbookTemp);
			fiFundbookJournal.setCompanyCode(companyCode);
			fiFundbookJournal.setUserCode(userCode);
			long todayCount = this.fiFundbookJournalDao.getCountByDate(companyCode, userCode);
			fiFundbookJournal.setSerial(todayCount + 1);
			fiFundbookJournal.setDealType("A");
			fiFundbookJournal.setDealDate(currentDate);
			fiFundbookJournal.setMoneyType(moneyType[i]);
			fiFundbookJournal.setMoney(moneyArray[i]);
			fiFundbookJournal.setNotes(notes[i]);
			tempMoney = tempMoney.add(moneyArray[i]);
			fiFundbookJournal.setBalance(tempMoney);
			fiFundbookJournal.setCreaterCode(operaterCode);
			fiFundbookJournal.setCreaterName(operaterName);
			fiFundbookJournal.setCreateTime(currentTime);
			fiFundbookJournal.setLastJournalId(lastFiFundbookJournal == null ? 0 : lastFiFundbookJournal.getJournalId());
			fiFundbookJournal.setLastMoney(lastFiFundbookJournal == null ? new BigDecimal(0) : lastFiFundbookJournal.getBalance());
			fiFundbookJournal.setUniqueCode(uniqueCode);
			fiFundbookJournal.setBankbookType(bankbookType);
			fiFundbookJournal.setDataType(dataType);

			fiFundbookJournalDao.save(fiFundbookJournal);
		}

		fiFundbookBalance.setBalance(remainMoney.add(money));
		fiFundbookBalanceDao.save(fiFundbookBalance);
	}

	/**
	 * 从产业基金帐户扣取金额
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * @param uniqueCode 业务唯一码,0为没有
	 * @param notes 存款说明
	 * @param bankbookType 基金类型：1，分红基金；2，定向基金
	 * @throws AppException 存款不够的错误
	 */
	public void saveFiBankbookDeduct(final String companyCode, final String userCode, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode,  final String[] notes, final String bankbookType, final String dataType) throws AppException{
		
		//判断用户帐户是否存在
		FiFundbookBalance fiFundbookBalanceTmp = this.fiFundbookBalanceDao.getFiFundbookBalanceByUserCodeAndFundbookType(userCode, bankbookType);
		FiFundbookBalance fiFundbookBalance = this.fiFundbookBalanceDao.getFiFundbookBalanceForUpdate(fiFundbookBalanceTmp.getFbbId());
		if (fiFundbookBalance == null) {
			throw new AppException("基金帐户不存在!");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			FiFundbookJournal lastFiFundbookJournal = fiFundbookJournalDao.getLastFiFundbookJournalByUnique(uniqueCode,"D");
			if (lastFiFundbookJournal != null && "D".equals(lastFiFundbookJournal.getDealType())) {
				throw new AppException("重复操作");
			}
		}
		
		BigDecimal money = new BigDecimal(0);
		for(int i = 0 ; i < moneyArray.length ; i++){
			if(moneyArray[i].compareTo(new BigDecimal("0"))==-1){
				throw new AppException("金额参数必须大于零");
			}
			money = money.add(moneyArray[i]);
		}

		Date currentDate = new Date();
		Date currentTime = new Date();

		//首先验证余额是否足够
		FiFundbookJournal lastFiFundbookJournal = this.fiFundbookJournalDao.getLastFiFundbookJournal(userCode,bankbookType);
		if (lastFiFundbookJournal == null || lastFiFundbookJournal.getBalance().compareTo(money) == -1) {
			throw new AppException("发展基金帐户余额不足！");
		}

		BigDecimal remainMoney;
		BigDecimal tempMoney;
		if (lastFiFundbookJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastFiFundbookJournal.getBalance();
			tempMoney = lastFiFundbookJournal.getBalance();
		}
		if (remainMoney.compareTo(money) == -1) {
			throw new AppException("发展基金帐户余额不足！");
		}
		
		for(int i = 0 ; i < moneyArray.length ; i++){
//			//插入存折临时记录
//			FiFundbookTemp fiFundbookTemp = new FiFundbookTemp();
//			fiFundbookTemp.setCompanyCode(companyCode);
//			fiFundbookTemp.setSysUser(sysUser);
//			long totalCount = this.fiFundbookTempDao.getCountByDate(companyCode, sysUser.getUserCode(), bankbookType);
//			fiFundbookTemp.setSerial(totalCount + 1);
//			fiFundbookTemp.setDealType("D");
//			fiFundbookTemp.setDealDate(currentDate);
//			fiFundbookTemp.setMoneyType(moneyType[i]);
//			fiFundbookTemp.setMoney(moneyArray[i]);
//			fiFundbookTemp.setNotes(notes[i]);
//			fiFundbookTemp.setStatus(2);
//			fiFundbookTemp.setCreaterCode(operaterCode);
//			fiFundbookTemp.setCreaterName(operaterName);
//			fiFundbookTemp.setCreateTime(currentTime);
//			fiFundbookTemp.setCheckerCode(operaterCode);
//			fiFundbookTemp.setCheckerName(operaterName);
//			fiFundbookTemp.setCheckeTime(currentTime);
//			fiFundbookTemp.setCheckMsg("OK");
//			fiFundbookTemp.setCheckType(1);
//			fiFundbookTemp.setBankbookType(bankbookType);
//			fiFundbookTempDao.saveFiFundbookTemp(fiFundbookTemp);

			//插入存折流水记录
			FiFundbookJournal fiFundbookJournal = new FiFundbookJournal();
			//fiFundbookJournal.setFiFundbookTemp(fiFundbookTemp);
			fiFundbookJournal.setCompanyCode(companyCode);
			fiFundbookJournal.setUserCode(userCode);
			long todayCount = this.fiFundbookJournalDao.getCountByDate(companyCode, userCode);
			fiFundbookJournal.setSerial(todayCount + 1);
			fiFundbookJournal.setDealType("D");
			fiFundbookJournal.setDealDate(currentDate);
			fiFundbookJournal.setMoneyType(moneyType[i]);
			fiFundbookJournal.setMoney(moneyArray[i]);
			fiFundbookJournal.setNotes(notes[i]);
			tempMoney = tempMoney.subtract(moneyArray[i]);
			fiFundbookJournal.setBalance(tempMoney);
			fiFundbookJournal.setCreaterCode(operaterCode);
			fiFundbookJournal.setCreaterName(operaterName);
			fiFundbookJournal.setCreateTime(currentTime);
			fiFundbookJournal.setLastJournalId(lastFiFundbookJournal == null ? 0 : lastFiFundbookJournal.getJournalId());
			fiFundbookJournal.setLastMoney(lastFiFundbookJournal == null ? new BigDecimal(0) : lastFiFundbookJournal.getBalance());
			fiFundbookJournal.setUniqueCode(uniqueCode);
			fiFundbookJournal.setBankbookType(bankbookType);
			fiFundbookJournal.setDataType(dataType);
			
			this.fiFundbookJournalDao.save(fiFundbookJournal);
		}

		fiFundbookBalance.setBalance(remainMoney.subtract(money));
		fiFundbookBalanceDao.save(fiFundbookBalance);
	}
}