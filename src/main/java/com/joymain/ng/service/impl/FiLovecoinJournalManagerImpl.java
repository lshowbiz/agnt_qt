package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FiLovecoinBalanceDao;
import com.joymain.ng.dao.FiLovecoinJournalDao;
import com.joymain.ng.model.FiLovecoinBalance;
import com.joymain.ng.model.FiLovecoinJournal;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiLovecoinJournalManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.AppException;
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

@Service("fiLovecoinJournalManager")
@WebService(serviceName = "FiLovecoinJournalService", endpointInterface = "com.joymain.ng.service.FiLovecoinJournalManager")
public class FiLovecoinJournalManagerImpl extends GenericManagerImpl<FiLovecoinJournal, Long> implements FiLovecoinJournalManager {
    FiLovecoinJournalDao fiLovecoinJournalDao;
    private FiLovecoinBalanceDao fiLovecoinBalanceDao;

    @Autowired
    public void setFiLovecoinBalanceDao(FiLovecoinBalanceDao fiLovecoinBalanceDao) {
		this.fiLovecoinBalanceDao = fiLovecoinBalanceDao;
	}

    @Autowired
    public FiLovecoinJournalManagerImpl(FiLovecoinJournalDao fiLovecoinJournalDao) {
        super(fiLovecoinJournalDao);
        this.fiLovecoinJournalDao = fiLovecoinJournalDao;
    }
	
	public Pager<FiLovecoinJournal> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiLovecoinJournalDao.getPager(FiLovecoinJournal.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public List<FiLovecoinJournal> getFiLovecoinJournalsByUserCode(String userCode,
			String startTime, String endTime) {
		// TODO Auto-generated method stub
		return fiLovecoinJournalDao.getFiLovecoinJournalsByUserCode(userCode, startTime, endTime);
	}
	
	/**
	 * 爱心积分存钱进账
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * * @param uniqueCode 业务唯一码,0为没有
	 * @param notes 存款说明
	 */
	public void saveFiPayDataVerify(final String companyCode, final JsysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, final Long[] appId, final Date[] moneyDate) throws AppException{
		
		//判断爱心积分账户是否存在
		FiLovecoinBalance fiLovecoinBalance = this.fiLovecoinBalanceDao.getFiLovecoinBalanceForUpdate(sysUser.getUserCode());
		if (fiLovecoinBalance == null) {
			throw new AppException("爱心积分账户不存在!");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			FiLovecoinJournal lastFiLovecoinJournal = fiLovecoinJournalDao.getLastFiLovecoinJournalByUnique(uniqueCode,"A");
			if (lastFiLovecoinJournal != null && "A".equals(lastFiLovecoinJournal.getDealType())) {
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
		FiLovecoinJournal lastFiLovecoinJournal = fiLovecoinJournalDao.getLastFiLovecoinJournal(sysUser.getUserCode());
		if (lastFiLovecoinJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastFiLovecoinJournal.getBalance();
			tempMoney = lastFiLovecoinJournal.getBalance();
		}

		Date currentDate = new Date();
		Date currentTime = new Date();
		for(int i = 0 ; i < moneyArray.length ; i++){
			//插入存折流水记录
			FiLovecoinJournal fiLovecoinJournal = new FiLovecoinJournal();
			fiLovecoinJournal.setUserCode(sysUser.getUserCode());
			long todayCount = fiLovecoinJournalDao.getCountByDate(sysUser.getUserCode());
			fiLovecoinJournal.setSerial((int) (todayCount + 1));
			fiLovecoinJournal.setDealType("A");
			fiLovecoinJournal.setDealDate(currentDate);
			fiLovecoinJournal.setCoin(moneyArray[i]);
			fiLovecoinJournal.setNotes(notes[i]);
			tempMoney = tempMoney.add(moneyArray[i]);
			fiLovecoinJournal.setBalance(tempMoney);
			fiLovecoinJournal.setCreaterCode(operaterCode);
			fiLovecoinJournal.setCreaterName(operaterName);
			fiLovecoinJournal.setCreateTime(currentTime);
			fiLovecoinJournal.setMoneyType(moneyType[i]);
			fiLovecoinJournal.setUniqueCode(uniqueCode);
			fiLovecoinJournal.setAppId(appId[i]);
			fiLovecoinJournalDao.save(fiLovecoinJournal);
			
			//折合存入另外两张表
			//fiLovecoinJournalDetailManager.saveFiPayDetailDataVerify(companyCode, ucMember, moneyType[i], moneyArray[i], operaterCode, operaterName, uniqueCode, notes[i], appId[i], moneyDate[i]);
			//moneyDate[i];
		}

		fiLovecoinBalance.setBalance(remainMoney.add(money));
		fiLovecoinBalanceDao.save(fiLovecoinBalance);
	}
    
    /**
	 * 从用户爱心积分帐户扣取金额
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * * @param uniqueCode 业务唯一码,0为没有
	 * @param notes 存款说明
	 * @throws AppException 存款不够的错误
	 */
	public void saveJfiBankbookDeduct(final String companyCode, final JsysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode,  final String[] notes, final Long[] appId) throws AppException{
		//判断用户存折是否存在
		FiLovecoinBalance fiLovecoinBalance = this.fiLovecoinBalanceDao.getFiLovecoinBalanceForUpdate(sysUser.getUserCode());
		if (fiLovecoinBalance == null) {
			throw new AppException("爱心积分账户不存在");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			FiLovecoinJournal lastFiLovecoinJournal = fiLovecoinJournalDao.getLastFiLovecoinJournalByUnique(uniqueCode,"D");
			if (lastFiLovecoinJournal != null && "D".equals(lastFiLovecoinJournal.getDealType())) {
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
		FiLovecoinJournal lastFiLovecoinJournal = this.fiLovecoinJournalDao.getLastFiLovecoinJournal(sysUser.getUserCode());
		if (lastFiLovecoinJournal == null || lastFiLovecoinJournal.getBalance().compareTo(money) == -1) {
			throw new AppException("error.fiLovecoinJournal.balance.not.enough");
		}

		BigDecimal remainMoney;
		BigDecimal tempMoney;
		if (lastFiLovecoinJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastFiLovecoinJournal.getBalance();
			tempMoney = lastFiLovecoinJournal.getBalance();
		}
		
		for(int i = 0 ; i < moneyArray.length ; i++){
			//插入存折流水记录
			FiLovecoinJournal fiLovecoinJournal = new FiLovecoinJournal();
			fiLovecoinJournal.setUserCode(sysUser.getUserCode());
			long todayCount = this.fiLovecoinJournalDao.getCountByDate(sysUser.getUserCode());
			fiLovecoinJournal.setSerial((int) (todayCount + 1));
			fiLovecoinJournal.setDealType("D");
			fiLovecoinJournal.setDealDate(currentDate);
			fiLovecoinJournal.setMoneyType(moneyType[i]);
			fiLovecoinJournal.setCoin(moneyArray[i]);
			fiLovecoinJournal.setNotes(notes[i]);
			tempMoney = tempMoney.subtract(moneyArray[i]);
			fiLovecoinJournal.setBalance(tempMoney);
			fiLovecoinJournal.setCreaterCode(operaterCode);
			fiLovecoinJournal.setCreaterName(operaterName);
			fiLovecoinJournal.setCreateTime(currentTime);
			fiLovecoinJournal.setUniqueCode(uniqueCode);
			fiLovecoinJournal.setAppId(appId[i]);
			this.fiLovecoinJournalDao.save(fiLovecoinJournal);
			
			//折合存入另外两张表
		}

		fiLovecoinBalance.setBalance(remainMoney.subtract(money));
		fiLovecoinBalanceDao.save(fiLovecoinBalance);
	}
	
	/**
	 * 爱心积分账户开户
	 * @param userCode
	 */
	public void createLovecoin(String userCode){
		
		//判断账户是否存在
		FiLovecoinBalance tempFiLovecoinBalance = this.fiLovecoinBalanceDao.getFiLovecoinBalanceForUpdate(userCode);
		
		//不存在，则创建
		if (tempFiLovecoinBalance == null) {
			
			FiLovecoinBalance fiLovecoinBalance=new FiLovecoinBalance();
			fiLovecoinBalance.setUserCode(userCode);
			fiLovecoinBalance.setBalance(BigDecimal.ZERO);
			
			fiLovecoinBalanceDao.save(fiLovecoinBalance);
		}
	}
}