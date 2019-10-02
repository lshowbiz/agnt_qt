package com.joymain.ng.service.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.FiBcoinBalanceDao;
import com.joymain.ng.dao.FiBcoinJournalDao;
import com.joymain.ng.model.FiBcoinBalance;
import com.joymain.ng.model.FiBcoinJournal;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.MiCoinLog;
import com.joymain.ng.service.FiBcoinJournalManager;
import com.joymain.ng.service.MiCoinLogManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@Service("fiBcoinJournalManager")
@WebService(serviceName = "FiBcoinJournalService", endpointInterface = "com.joymain.ng.service.FiBcoinJournalManager")
public class FiBcoinJournalManagerImpl extends GenericManagerImpl<FiBcoinJournal, Long> implements FiBcoinJournalManager {
    FiBcoinJournalDao fiBcoinJournalDao;
    FiBcoinBalanceDao fiBcoinBalanceDao;

    @Autowired
    private MiCoinLogManager miCoinLogManager;
    @Autowired
    public void setFiBcoinBalanceDao(FiBcoinBalanceDao fiBcoinBalanceDao) {
		this.fiBcoinBalanceDao = fiBcoinBalanceDao;
	}
    
    @Autowired
    public FiBcoinJournalManagerImpl(FiBcoinJournalDao fiBcoinJournalDao) {
        super(fiBcoinJournalDao);
        this.fiBcoinJournalDao = fiBcoinJournalDao;
    }
	
	public Pager<FiBcoinJournal> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiBcoinJournalDao.getPager(FiBcoinJournal.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public List<FiBcoinJournal> getFiBcoinJournalListByUser(String userCode,
			String dealStartTime, String dealEndTime,int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		return fiBcoinJournalDao.getFiBcoinJournalListByUser(userCode, dealStartTime, dealEndTime,pageNum,pageSize);
	}
	
	@Override
	public List<FiBcoinJournal> getFiBcoinJournalListByUserPage(GroupPage page,String userCode,
			String dealStartTime, String dealEndTime) {
		// TODO Auto-generated method stub
		return fiBcoinJournalDao.getFiBcoinJournalListByUserPage(page,userCode, dealStartTime, dealEndTime);
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
	 */
	public void saveFiPayDataVerify(final String companyCode, final JsysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, final Long[] appId, final Date[] moneyDate,String dataType){
		//判断用户存折是否存在
		FiBcoinBalance fiBcoinBalance = this.fiBcoinBalanceDao.getFiBcoinBalanceForUpdate(sysUser.getUserCode());
		if (fiBcoinBalance == null) {
			throw new AppException("B分账户记录不存在");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			FiBcoinJournal lastFiBcoinJournal = fiBcoinJournalDao.getLastFiBcoinJournalByUnique(uniqueCode,"A");
			if (lastFiBcoinJournal != null && "A".equals(lastFiBcoinJournal.getDealType())) {
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
		FiBcoinJournal lastFiBcoinJournal = fiBcoinJournalDao.getLastFiBcoinJournal(sysUser.getUserCode());
		if (lastFiBcoinJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastFiBcoinJournal.getBalance();
			tempMoney = lastFiBcoinJournal.getBalance();
		}

		Date currentDate = new Date();
		Date currentTime = new Date();
		for(int i = 0 ; i < moneyArray.length ; i++){
			//插入存折流水记录
			FiBcoinJournal fiBcoinJournal = new FiBcoinJournal();
			fiBcoinJournal.setUserCode(sysUser.getUserCode());
			long todayCount = fiBcoinJournalDao.getCountByDate(sysUser.getUserCode());
			fiBcoinJournal.setSerial((int) (todayCount + 1));
			fiBcoinJournal.setDealType("A");
			fiBcoinJournal.setDealDate(currentDate);
			fiBcoinJournal.setCoin(moneyArray[i]);
			fiBcoinJournal.setNotes(notes[i]);
			tempMoney = tempMoney.add(moneyArray[i]);
			fiBcoinJournal.setBalance(tempMoney);
			fiBcoinJournal.setCreaterCode(operaterCode);
			fiBcoinJournal.setCreaterName(operaterName);
			fiBcoinJournal.setCreateTime(currentTime);
			fiBcoinJournal.setMoneyType(moneyType[i]);
			fiBcoinJournal.setUniqueCode(uniqueCode);
			fiBcoinJournal.setAppId(appId[i]);
			fiBcoinJournal.setDataType(dataType);
			fiBcoinJournalDao.saveFiBcoinJournal(fiBcoinJournal);
			
			//折合存入另外两张表
			//fiBcoinJournalDetailManager.saveFiPayDetailDataVerify(companyCode, ucMember, moneyType[i], moneyArray[i], operaterCode, operaterName, uniqueCode, notes[i], appId[i], moneyDate[i]);
			//moneyDate[i];
		}

		fiBcoinBalance.setBalance(remainMoney.add(money));
		fiBcoinBalanceDao.saveFiBcoinBalance(fiBcoinBalance);
	}
    
    /**
	 * 从用户帐户扣取金额
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
	        String operaterCode, final String operaterName, final String uniqueCode,  final String[] notes, final Long[] appId,String dataType) throws AppException{
		//判断用户存折是否存在
		FiBcoinBalance fiBcoinBalance = this.fiBcoinBalanceDao.getFiBcoinBalanceForUpdate(sysUser.getUserCode());
		if (fiBcoinBalance == null) {
			throw new AppException("存折记录不存在");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			FiBcoinJournal lastFiBcoinJournal = fiBcoinJournalDao.getLastFiBcoinJournalByUnique(uniqueCode,"D");
			if (lastFiBcoinJournal != null && "D".equals(lastFiBcoinJournal.getDealType())) {
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
		FiBcoinJournal lastFiBcoinJournal = this.fiBcoinJournalDao.getLastFiBcoinJournal(sysUser.getUserCode());
		if (lastFiBcoinJournal == null || lastFiBcoinJournal.getBalance().compareTo(money) == -1) {
			throw new AppException("error.fiBcoinJournal.balance.not.enough");
		}

		BigDecimal remainMoney;
		BigDecimal tempMoney;
		if (lastFiBcoinJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastFiBcoinJournal.getBalance();
			tempMoney = lastFiBcoinJournal.getBalance();
		}
		
		for(int i = 0 ; i < moneyArray.length ; i++){
			//插入存折流水记录
			FiBcoinJournal fiBcoinJournal = new FiBcoinJournal();
			fiBcoinJournal.setUserCode(sysUser.getUserCode());
			long todayCount = this.fiBcoinJournalDao.getCountByDate(sysUser.getUserCode());
			fiBcoinJournal.setSerial((int) (todayCount + 1));
			fiBcoinJournal.setDealType("D");
			fiBcoinJournal.setDealDate(currentDate);
			fiBcoinJournal.setMoneyType(moneyType[i]);
			fiBcoinJournal.setCoin(moneyArray[i]);
			fiBcoinJournal.setNotes(notes[i]);
			tempMoney = tempMoney.subtract(moneyArray[i]);
			fiBcoinJournal.setBalance(tempMoney);
			fiBcoinJournal.setCreaterCode(operaterCode);
			fiBcoinJournal.setCreaterName(operaterName);
			fiBcoinJournal.setCreateTime(currentTime);
			fiBcoinJournal.setUniqueCode(uniqueCode);
			fiBcoinJournal.setAppId(appId[i]);
			fiBcoinJournal.setDataType(dataType);
			this.fiBcoinJournalDao.saveFiBcoinJournal(fiBcoinJournal);
			
			//折合存入另外两张表
		}

		fiBcoinBalance.setBalance(remainMoney.subtract(money));
		fiBcoinBalanceDao.saveFiBcoinBalance(fiBcoinBalance);
	}

	/**
	 * 积分迁移至商城
	 * userCode 会员编号
	 * bcoinNum 需要迁移的积分数
	 */
	@Override
	public MiCoinLog sendBcoinsToShangCheng(JsysUser sysUser, BigDecimal bcoinNum,String mallType,String sendNo) throws AppException{
		// TODO Auto-generated method stub
		
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 1;
		
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = bcoinNum;
		
		String[] notes = new String[1];
		notes[0] = "积分迁移至瓜藤网";
		
		if("2".equals(mallType)){
			notes[0] = "积分迁移至脉宝网";
		}
		
		
		Long[] appId = new Long[1];
		appId[0] = 2l;
		
		//1.扣取欢乐积分
		this.saveJfiBankbookDeduct("CN", sysUser, moneyType, moneyArray, sysUser.getUserCode(), sysUser.getUserName(), "0", notes, appId,"1");
		
		//2.迁移（小叶需要添加方法）

		MiCoinLog miCoinLog = new MiCoinLog();
		miCoinLog.setCoin(bcoinNum);
		miCoinLog.setCoinType("B"); 
		miCoinLog.setCreateNo(sysUser.getUserCode());
		miCoinLog.setCreateTime(new Date());
		miCoinLog.setDealType(mallType);
		miCoinLog.setUserCode(sysUser.getUserCode());
		miCoinLog.setStatus("1");
		miCoinLog.setSendNo(sendNo);
		miCoinLogManager.saveMiCoinLog(miCoinLog);
		return miCoinLog;
	}
}