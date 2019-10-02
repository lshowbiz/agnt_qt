package com.joymain.ng.service.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.FiBankbookBalanceDao;
import com.joymain.ng.dao.FiBankbookJournalDao;
import com.joymain.ng.dao.FiBankbookTempDao;
import com.joymain.ng.model.FiBankbookBalance;
import com.joymain.ng.model.FiBankbookJournal;
import com.joymain.ng.model.FiBankbookTemp;
import com.joymain.ng.model.JfiPosImport;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiBankbookJournalManager;
import com.joymain.ng.service.JfiPosImportManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@Service("fiBankbookJournalManager")
@WebService(serviceName = "FiBankbookJournalService", endpointInterface = "com.joymain.ng.service.FiBankbookJournalManager")
public class FiBankbookJournalManagerImpl extends GenericManagerImpl<FiBankbookJournal, Long> implements FiBankbookJournalManager {
    FiBankbookJournalDao fiBankbookJournalDao;
    private FiBankbookTempDao fiBankbookTempDao;
	private FiBankbookBalanceDao fiBankbookBalanceDao;
	private JfiPosImportManager jfiPosImportManager = null;
	
	@Autowired
	public void setJfiPosImportManager(JfiPosImportManager jfiPosImportManager) {
		this.jfiPosImportManager = jfiPosImportManager;
	}

	@Autowired
    public void setFiBankbookTempDao(FiBankbookTempDao fiBankbookTempDao) {
		this.fiBankbookTempDao = fiBankbookTempDao;
	}
	
    @Autowired
	public void setFiBankbookBalanceDao(FiBankbookBalanceDao fiBankbookBalanceDao) {
		this.fiBankbookBalanceDao = fiBankbookBalanceDao;
	}

	@Autowired
    public FiBankbookJournalManagerImpl(FiBankbookJournalDao fiBankbookJournalDao) {
        super(fiBankbookJournalDao);
        this.fiBankbookJournalDao = fiBankbookJournalDao;
    }
	
	public Pager<FiBankbookJournal> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiBankbookJournalDao.getPager(FiBankbookJournal.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	
	public List<FiBankbookJournal> getFiBankbookJournalListByUser(String userCode, String dealStartTime, String dealEndTime,int pageNum,int pageSize){
		return fiBankbookJournalDao.getFiBankbookJournalListByUser(userCode, dealStartTime, dealEndTime,pageNum,pageSize);
	}
	
	/**
	 * Add By WuCF 20131129
	 * 分页
	 */
	public List<FiBankbookJournal> getFiBankbookJournalListByUserPage(GroupPage page,String userCode, String dealStartTime, String dealEndTime){
		return fiBankbookJournalDao.getFiBankbookJournalListByUserPage(page,userCode, dealStartTime, dealEndTime);
	}

	/**
	 * 存入资金至用户帐户
	 * @param companyCode 公司编码
	 * @param userCode 用户编码
	 * @param moneyType 资金类别
	 * @param money 资金金额
	 * @param operaterCode 操作人编码
	 * @param operaterName 操作人姓名
	 * * @param uniqueCode 业务唯一码,0为没有
	 * @param notes 存款说明
	 * @throws Exception 
	 */
	public void saveFiPayDataVerify(final String companyCode, final JsysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, final String bankbookType,String dataType) throws Exception{
		//判断用户存折是否存在
		FiBankbookBalance fiBankbookBalanceTmp = this.fiBankbookBalanceDao.getFiBankbookBalanceByUserCodeAndBankbookType(sysUser.getUserCode(), bankbookType);
		FiBankbookBalance fiBankbookBalance = this.fiBankbookBalanceDao.getFiBankbookBalanceForUpdate(fiBankbookBalanceTmp.getFbbId());
		
		//FiBankbookBalance fiBankbookBalance = this.fiBankbookBalanceDao.getFiBankbookBalanceByUserCodeAndBankbookType(sysUser.getUserCode(), bankbookType);
		
		if (fiBankbookBalance == null) {
			throw new Exception("发展基金帐户不存在!");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			FiBankbookJournal lastFiBankbookJournal = fiBankbookJournalDao.getLastFiBankbookJournalByUnique(uniqueCode,"A");
			if (lastFiBankbookJournal != null && "A".equals(lastFiBankbookJournal.getDealType())) {
				throw new Exception("重复操作!");
			}
		}
		
		BigDecimal money = new BigDecimal(0);
		for(int i = 0 ; i < moneyArray.length ; i++){
			if(moneyArray[i].compareTo(new BigDecimal("0"))==-1){
				throw new Exception("金额参数必须大于零");
			}
			money = money.add(moneyArray[i]);
		}

		BigDecimal remainMoney;
		BigDecimal tempMoney;
		FiBankbookJournal lastFiBankbookJournal = this.fiBankbookJournalDao.getLastFiBankbookJournal(sysUser.getUserCode(),bankbookType);
		if (lastFiBankbookJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastFiBankbookJournal.getBalance();
			tempMoney = lastFiBankbookJournal.getBalance();
		}

		Date currentDate = new Date();
		Date currentTime = new Date();
		for(int i = 0 ; i < moneyArray.length ; i++){

			//插入存折临时记录
			FiBankbookTemp fiBankbookTemp = new FiBankbookTemp();
			fiBankbookTemp.setCompanyCode(companyCode);
			fiBankbookTemp.setUserCode(sysUser.getUserCode());
			long totalCount = this.fiBankbookTempDao.getCountByDate(companyCode, sysUser.getUserCode(), bankbookType);
			fiBankbookTemp.setSerial(totalCount + 1);
			fiBankbookTemp.setDealType("A");
			fiBankbookTemp.setDealDate(currentDate);
			fiBankbookTemp.setMoneyType(moneyType[i]);
			fiBankbookTemp.setMoney(moneyArray[i]);
			fiBankbookTemp.setNotes(notes[i]);
			fiBankbookTemp.setStatus(2);
			fiBankbookTemp.setCreaterCode(operaterCode);
			fiBankbookTemp.setCreaterName(operaterName);
			fiBankbookTemp.setCreateTime(currentTime);
			fiBankbookTemp.setCheckerCode(operaterCode);
			fiBankbookTemp.setCheckerName(operaterName);
			fiBankbookTemp.setCheckeTime(currentTime);
			fiBankbookTemp.setCheckMsg("OK");
			fiBankbookTemp.setCheckType(1);
			fiBankbookTemp.setBankbookType(bankbookType);

			fiBankbookTempDao.saveFiBankbookTemp(fiBankbookTemp);
			//插入存折流水记录
			FiBankbookJournal fiBankbookJournal = new FiBankbookJournal();
			fiBankbookJournal.setFiBankbookTemp(fiBankbookTemp);
			fiBankbookJournal.setCompanyCode(companyCode);
			fiBankbookJournal.setUserCode(sysUser.getUserCode());
			long todayCount = this.fiBankbookJournalDao.getCountByDate(companyCode, sysUser.getUserCode());
			fiBankbookJournal.setSerial(todayCount + 1);
			fiBankbookJournal.setDealType("A");
			fiBankbookJournal.setDealDate(currentDate);
			fiBankbookJournal.setMoneyType(moneyType[i]);
			fiBankbookJournal.setMoney(moneyArray[i]);
			fiBankbookJournal.setNotes(notes[i]);
			tempMoney = tempMoney.add(moneyArray[i]);
			fiBankbookJournal.setBalance(tempMoney);
			fiBankbookJournal.setCreaterCode(operaterCode);
			fiBankbookJournal.setCreaterName(operaterName);
			fiBankbookJournal.setCreateTime(currentTime);
			fiBankbookJournal.setLastJournalId(lastFiBankbookJournal == null ? 0 : lastFiBankbookJournal.getJournalId());
			fiBankbookJournal.setLastMoney(lastFiBankbookJournal == null ? new BigDecimal(0) : lastFiBankbookJournal.getBalance());
			fiBankbookJournal.setUniqueCode(uniqueCode);
			fiBankbookJournal.setBankbookType(bankbookType);
			fiBankbookJournal.setDataType(dataType);

			fiBankbookJournalDao.saveFiBankbookJournal(fiBankbookJournal);
		}

		fiBankbookBalance.setBalance(remainMoney.add(money));
		fiBankbookBalanceDao.saveFiBankbookBalance(fiBankbookBalance);
	}

	/**
	 * 从发展基金帐户扣取金额
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
	public void saveFiBankbookDeduct(final String companyCode, final JsysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode,  final String[] notes, final String bankbookType,String dataType) throws Exception{
		//判断用户存折是否存在
		FiBankbookBalance fiBankbookBalanceTmp = this.fiBankbookBalanceDao.getFiBankbookBalanceByUserCodeAndBankbookType(sysUser.getUserCode(), bankbookType);
		FiBankbookBalance fiBankbookBalance = this.fiBankbookBalanceDao.getFiBankbookBalanceForUpdate(fiBankbookBalanceTmp.getFbbId());
		
		//FiBankbookBalance fiBankbookBalance = this.fiBankbookBalanceDao.getFiBankbookBalanceByUserCodeAndBankbookType(sysUser.getUserCode(), bankbookType);
		if (fiBankbookBalance == null) {
			throw new Exception("发展基金帐户不存在!");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			FiBankbookJournal lastFiBankbookJournal = fiBankbookJournalDao.getLastFiBankbookJournalByUnique(uniqueCode,"D");
			if (lastFiBankbookJournal != null && "D".equals(lastFiBankbookJournal.getDealType())) {
				throw new Exception("已经扣过发展基金了!");
			}
		}
		
		BigDecimal money = new BigDecimal(0);
		for(int i = 0 ; i < moneyArray.length ; i++){
			if(moneyArray[i].compareTo(new BigDecimal("0"))==-1){
				throw new Exception("金额参数必须大于零");
			}
			money = money.add(moneyArray[i]);
		}

		Date currentDate = new Date();
		Date currentTime = new Date();

		//首先验证余额是否足够
		FiBankbookJournal lastFiBankbookJournal = this.fiBankbookJournalDao.getLastFiBankbookJournal(sysUser.getUserCode(),bankbookType);
		if (lastFiBankbookJournal == null || lastFiBankbookJournal.getBalance().compareTo(money) == -1) {
			throw new Exception("error.fiBankbookJournal.balance.not.enough");
		}

		BigDecimal remainMoney;
		BigDecimal tempMoney;
		if (lastFiBankbookJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastFiBankbookJournal.getBalance();
			tempMoney = lastFiBankbookJournal.getBalance();
		}
		if (remainMoney.compareTo(money) == -1) {
			throw new Exception("error.fiBankbookJournal.balance.not.enough");
		}
		
		for(int i = 0 ; i < moneyArray.length ; i++){
			//插入存折临时记录
			FiBankbookTemp fiBankbookTemp = new FiBankbookTemp();
			fiBankbookTemp.setCompanyCode(companyCode);
			fiBankbookTemp.setUserCode(sysUser.getUserCode());
			long totalCount = this.fiBankbookTempDao.getCountByDate(companyCode, sysUser.getUserCode(), bankbookType);
			fiBankbookTemp.setSerial(totalCount + 1);
			fiBankbookTemp.setDealType("D");
			fiBankbookTemp.setDealDate(currentDate);
			fiBankbookTemp.setMoneyType(moneyType[i]);
			fiBankbookTemp.setMoney(moneyArray[i]);
			fiBankbookTemp.setNotes(notes[i]);
			fiBankbookTemp.setStatus(2);
			fiBankbookTemp.setCreaterCode(operaterCode);
			fiBankbookTemp.setCreaterName(operaterName);
			fiBankbookTemp.setCreateTime(currentTime);
			fiBankbookTemp.setCheckerCode(operaterCode);
			fiBankbookTemp.setCheckerName(operaterName);
			fiBankbookTemp.setCheckeTime(currentTime);
			fiBankbookTemp.setCheckMsg("OK");
			fiBankbookTemp.setCheckType(1);
			fiBankbookTemp.setBankbookType(bankbookType);
			fiBankbookTempDao.saveFiBankbookTemp(fiBankbookTemp);

			//插入存折流水记录
			FiBankbookJournal fiBankbookJournal = new FiBankbookJournal();
			fiBankbookJournal.setFiBankbookTemp(fiBankbookTemp);
			fiBankbookJournal.setCompanyCode(companyCode);
			fiBankbookJournal.setUserCode(sysUser.getUserCode());
			long todayCount = this.fiBankbookJournalDao.getCountByDate(companyCode, sysUser.getUserCode());
			fiBankbookJournal.setSerial(todayCount + 1);
			fiBankbookJournal.setDealType("D");
			fiBankbookJournal.setDealDate(currentDate);
			fiBankbookJournal.setMoneyType(moneyType[i]);
			fiBankbookJournal.setMoney(moneyArray[i]);
			fiBankbookJournal.setNotes(notes[i]);
			tempMoney = tempMoney.subtract(moneyArray[i]);
			fiBankbookJournal.setBalance(tempMoney);
			fiBankbookJournal.setCreaterCode(operaterCode);
			fiBankbookJournal.setCreaterName(operaterName);
			fiBankbookJournal.setCreateTime(currentTime);
			fiBankbookJournal.setLastJournalId(lastFiBankbookJournal == null ? 0 : lastFiBankbookJournal.getJournalId());
			fiBankbookJournal.setLastMoney(lastFiBankbookJournal == null ? new BigDecimal(0) : lastFiBankbookJournal.getBalance());
			fiBankbookJournal.setUniqueCode(uniqueCode);
			fiBankbookJournal.setBankbookType(bankbookType);
			fiBankbookJournal.setDataType(dataType);
			this.fiBankbookJournalDao.saveFiBankbookJournal(fiBankbookJournal);
		}

		fiBankbookBalance.setBalance(remainMoney.subtract(money));
		fiBankbookBalanceDao.saveFiBankbookBalance(fiBankbookBalance);
	}

	/**
	 * POS数据进存折
	 * @param companyCode
	 * @param sysUser
	 * @param operaterCode
	 * @param operaterName
	 * @param jfiPosImport
	 * @throws Exception 
	 */
	@Override
	public void saveJfiPayDataVerifyByPosImport(String companyCode,
			JsysUser sysUser, String operaterCode, String operaterName,
			JfiPosImport jfiPosImport) throws Exception {

		jfiPosImportManager.save(jfiPosImport);
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = jfiPosImport.getAmount();
		
		Integer[] moneyType = new Integer[1];
		moneyType[0] = jfiPosImport.getMoneyType();//POS支付资金类别 (89：POS现场刷卡支付 ，35：银联POS，106：畅捷通POS)
		
		String[] notes = new String[1];
		notes[0] = "POS现场刷卡支付,会员手机验证";
		this.saveFiPayDataVerify(companyCode, sysUser, moneyType, moneyArray, operaterCode, operaterName,jfiPosImport.getPosNo(),notes,"1","1");
	}
}