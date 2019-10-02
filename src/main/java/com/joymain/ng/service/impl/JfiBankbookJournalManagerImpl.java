package com.joymain.ng.service.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JfiBankbookBalanceDao;
import com.joymain.ng.dao.JfiBankbookJournalDao;
import com.joymain.ng.dao.JfiBankbookTempDao;
import com.joymain.ng.model.Jfi99billLog;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JfiBankbookJournal;
import com.joymain.ng.model.JfiBankbookTemp;
import com.joymain.ng.model.JfiChanjetLog;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.Jfi99billLogManager;
import com.joymain.ng.service.JfiBankbookJournalManager;
import com.joymain.ng.service.JfiChanjetLogManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.pay.PassbookVO;

@Service("jfiBankbookJournalManager")
@WebService(serviceName = "JfiBankbookJournalService", endpointInterface = "com.joymain.ng.service.JfiBankbookJournalManager")
public class JfiBankbookJournalManagerImpl extends GenericManagerImpl<JfiBankbookJournal, Long> implements JfiBankbookJournalManager {
    JfiBankbookJournalDao jfiBankbookJournalDao;
    private JfiBankbookBalanceDao jfiBankbookBalanceDao;
    private JfiBankbookTempDao jfiBankbookTempDao;
    private Jfi99billLogManager jfi99billLogManager;
    private JfiChanjetLogManager jfiChanjetLogManager;

    @Autowired
    public void setJfiChanjetLogManager(JfiChanjetLogManager jfiChanjetLogManager) {
		this.jfiChanjetLogManager = jfiChanjetLogManager;
	}
    
    @Autowired
    public void setJfi99billLogManager(Jfi99billLogManager jfi99billLogManager) {
		this.jfi99billLogManager = jfi99billLogManager;
	}

	@Autowired
    public void setJfiBankbookBalanceDao(JfiBankbookBalanceDao jfiBankbookBalanceDao) {
		this.jfiBankbookBalanceDao = jfiBankbookBalanceDao;
	}

    @Autowired
	public void setJfiBankbookTempDao(JfiBankbookTempDao jfiBankbookTempDao) {
		this.jfiBankbookTempDao = jfiBankbookTempDao;
	}

    @Autowired
	public void setJfiBankbookJournalDao(JfiBankbookJournalDao jfiBankbookJournalDao) {
		this.jfiBankbookJournalDao = jfiBankbookJournalDao;
	}

	@Autowired
    public JfiBankbookJournalManagerImpl(JfiBankbookJournalDao jfiBankbookJournalDao) {
        super(jfiBankbookJournalDao);
        this.jfiBankbookJournalDao = jfiBankbookJournalDao;
    }
	
	public Pager<JfiBankbookJournal> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		
		return jfiBankbookJournalDao.getPager(JfiBankbookJournal.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public List<JfiBankbookJournal> getJfiBankbookJournalListByUserCode(String userCode,String dealStartTime,String dealEndTime) {
		// TODO Auto-generated method stub
		return jfiBankbookJournalDao.getJfiBankbookJournalListByUserCode(userCode,dealStartTime,dealEndTime);
	}
	
	@Override
	public List<JfiBankbookJournal> getJfiBankbookJournalListByUserCodePage(GroupPage page,String userCode,String dealStartTime,String dealEndTime) {
		// TODO Auto-generated method stub
		return jfiBankbookJournalDao.getJfiBankbookJournalListByUserCodePage(page,userCode,dealStartTime,dealEndTime);
	}
	
	/**
	 * Add By WuCF 2013-11-25
	 * 分页展示数据   
	 * @param userCode
	 * @param dealStartTime
	 * @param dealEndTime
	 * @return
	 */
	@Override
	public List<JfiBankbookJournal> getJfiBankbookJournalListByUserCodePage(String userCode,String dealStartTime,String dealEndTime,int pageNum,int pageSize) {
		// TODO Auto-generated method stub
		return jfiBankbookJournalDao.getJfiBankbookJournalListByUserCodePage(userCode,dealStartTime,dealEndTime,pageNum,pageSize);
	}

	/**
	 * 从电子存折帐户扣取金额
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
	public void saveJfiBankbookDeduct(String companyCode, JsysUser sysUser,
			Integer[] moneyType, BigDecimal[] moneyArray, String operaterCode,
			String operaterName, String uniqueCode, String[] notes, String dataType)
			throws AppException {
		// TODO Auto-generated method stub
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
		
		BigDecimal money = new BigDecimal(0);
		for(int i = 0 ; i < moneyArray.length ; i++){
			if(moneyArray[i].compareTo(new BigDecimal("0"))==-1){
				throw new AppException("金额参数必须大于或等于零"+moneyArray[i]);
			}
			money = money.add(moneyArray[i]);
		}

		Date currentDate = new Date();
		Date currentTime = new Date();
		
		//首先验证余额是否足够
		JfiBankbookJournal lastJfiBankbookJournal = this.jfiBankbookJournalDao.getLastJfiBankbookJournal(sysUser.getUserCode());
		log.info("balance is:"+jfiBankbookBalance.getBalance().toString()+" and money is:"+money);
		if (jfiBankbookBalance.getBalance().compareTo(money) == -1) {
			throw new AppException("电子存折帐户余额不够");
		}
		//余额是否满足扣款
		BigDecimal remainMoney;
		BigDecimal tempMoney;
		if (lastJfiBankbookJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastJfiBankbookJournal.getBalance();
			tempMoney = lastJfiBankbookJournal.getBalance();
		}
		if (remainMoney.compareTo(money) == -1) {
			throw new AppException("error.fiBankbookJournal.balance.not.enough");
		}
		
		for(int i = 0 ; i < moneyArray.length ; i++){
			//插入存折临时记录
			JfiBankbookTemp jfiBankbookTemp = new JfiBankbookTemp();
			jfiBankbookTemp.setCompanyCode(companyCode);
			jfiBankbookTemp.setSysUser(sysUser);
			long totalCount = this.jfiBankbookTempDao.getCountByDate(companyCode, sysUser.getUserCode());
			jfiBankbookTemp.setSerial(totalCount + 1);
			jfiBankbookTemp.setDealType("D");
			jfiBankbookTemp.setDealDate(currentDate);
			jfiBankbookTemp.setMoneyType(moneyType[i]);
			jfiBankbookTemp.setMoney(moneyArray[i]);
			jfiBankbookTemp.setNotes(notes[i]);
			jfiBankbookTemp.setStatus(2);
			jfiBankbookTemp.setCreaterCode(operaterCode);
			jfiBankbookTemp.setCreaterName(operaterName);
			jfiBankbookTemp.setCreateTime(currentTime);
			jfiBankbookTemp.setCheckerCode(operaterCode);
			jfiBankbookTemp.setCheckerName(operaterName);
			jfiBankbookTemp.setCheckeTime(currentTime);
			jfiBankbookTemp.setCheckMsg("OK");
			jfiBankbookTemp.setCheckType(1);
			jfiBankbookTempDao.saveJfiBankbookTemp(jfiBankbookTemp);

			//插入存折流水记录
			JfiBankbookJournal fiBankbookJournal = new JfiBankbookJournal();
			fiBankbookJournal.setJfiBankbookTemp(jfiBankbookTemp);
			fiBankbookJournal.setCompanyCode(companyCode);
			fiBankbookJournal.setUserCode(sysUser.getUserCode());
			long todayCount = this.jfiBankbookJournalDao.getCountByDate(companyCode, sysUser.getUserCode());
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
			fiBankbookJournal.setLastJournalId(lastJfiBankbookJournal == null ? 0 : lastJfiBankbookJournal.getJournalId());
			fiBankbookJournal.setLastMoney(lastJfiBankbookJournal == null ? new BigDecimal(0) : lastJfiBankbookJournal.getBalance());
			fiBankbookJournal.setUniqueCode(uniqueCode);
			fiBankbookJournal.setDataType(dataType);
			jfiBankbookJournalDao.saveJfiBankbookJournal(fiBankbookJournal);
		}
		
		jfiBankbookBalance.setBalance(remainMoney.subtract(money));
		jfiBankbookBalanceDao.saveJfiBankbookBalance(jfiBankbookBalance);
		
	}
	
	/**
	 * 从电子存折帐户扣取金额
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
	public void saveJfiBankbookDeduct_pay(String companyCode, JsysUser sysUser,
			Integer[] moneyType, BigDecimal[] moneyArray, String operaterCode,
			String operaterName, String uniqueCode, String[] notes, String dataType)
			throws AppException {
		// TODO Auto-generated method stub
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
		
		log.info("===================saveJfiBankbookDeduct_pay:"+moneyArray); 
		BigDecimal money = new BigDecimal(0);
		for(int i = 0 ; i < moneyArray.length ; i++){
			log.info("moneyArray[i]:"+moneyArray[i]);
			if(moneyArray[i].compareTo(new BigDecimal("0"))==-1){
				throw new AppException("金额参数必须大于或等于零"+moneyArray[i]);
			}
			money = money.add(moneyArray[i]);
		}

		Date currentDate = new Date();
		Date currentTime = new Date();
		
		//首先验证余额是否足够
		JfiBankbookJournal lastJfiBankbookJournal = this.jfiBankbookJournalDao.getLastJfiBankbookJournal(sysUser.getUserCode());
		log.info("==============:"+jfiBankbookBalance.getBalance()+"--"+money);
		if (jfiBankbookBalance.getBalance().compareTo(money) == -1) {
			throw new AppException("电子存折帐户余额不够");
		}
		//余额是否满足扣款
		BigDecimal remainMoney;
		BigDecimal tempMoney;
		if (lastJfiBankbookJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastJfiBankbookJournal.getBalance();
			tempMoney = lastJfiBankbookJournal.getBalance();
		}
		if (remainMoney.compareTo(money) == -1) {
			throw new AppException("error.fiBankbookJournal.balance.not.enough");
		}
		/* 支付改造
		for(int i = 0 ; i < moneyArray.length ; i++){
			//插入存折临时记录
			JfiBankbookTemp jfiBankbookTemp = new JfiBankbookTemp();
			jfiBankbookTemp.setCompanyCode(companyCode);
			jfiBankbookTemp.setSysUser(sysUser);
			long totalCount = this.jfiBankbookTempDao.getCountByDate(companyCode, sysUser.getUserCode());
			jfiBankbookTemp.setSerial(totalCount + 1);
			jfiBankbookTemp.setDealType("D");
			jfiBankbookTemp.setDealDate(currentDate);
			jfiBankbookTemp.setMoneyType(moneyType[i]);
			jfiBankbookTemp.setMoney(moneyArray[i]);
			jfiBankbookTemp.setNotes(notes[i]);
			jfiBankbookTemp.setStatus(2);
			jfiBankbookTemp.setCreaterCode(operaterCode);
			jfiBankbookTemp.setCreaterName(operaterName);
			jfiBankbookTemp.setCreateTime(currentTime);
			jfiBankbookTemp.setCheckerCode(operaterCode);
			jfiBankbookTemp.setCheckerName(operaterName);
			jfiBankbookTemp.setCheckeTime(currentTime);
			jfiBankbookTemp.setCheckMsg("OK");
			jfiBankbookTemp.setCheckType(1);
			jfiBankbookTempDao.saveJfiBankbookTemp(jfiBankbookTemp);

			//插入存折流水记录
			JfiBankbookJournal fiBankbookJournal = new JfiBankbookJournal();
			fiBankbookJournal.setJfiBankbookTemp(jfiBankbookTemp);
			fiBankbookJournal.setCompanyCode(companyCode);
			fiBankbookJournal.setUserCode(sysUser.getUserCode());
			long todayCount = this.jfiBankbookJournalDao.getCountByDate(companyCode, sysUser.getUserCode());
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
			fiBankbookJournal.setLastJournalId(lastJfiBankbookJournal == null ? 0 : lastJfiBankbookJournal.getJournalId());
			fiBankbookJournal.setLastMoney(lastJfiBankbookJournal == null ? new BigDecimal(0) : lastJfiBankbookJournal.getBalance());
			fiBankbookJournal.setUniqueCode(uniqueCode);
			fiBankbookJournal.setDataType(dataType);
			jfiBankbookJournalDao.saveJfiBankbookJournal(fiBankbookJournal);
		}
		
		jfiBankbookBalance.setBalance(remainMoney.subtract(money));
		jfiBankbookBalanceDao.saveJfiBankbookBalance(jfiBankbookBalance);
		*/
	}
	
	/**
	 * 扣款操作 返回map值 isPay:扣款成果与否,msg:成功，失败消息
	 * @param vo
	 * @return
	 */
	public Map<String, Object> saveDebitPassBook(PassbookVO vo){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("isPay", true);
		map.put("msg", "扣款成功!");
		try{
			saveJfiBankbookDeduct(vo.getCompanyCode(), vo.getJsysUser(), vo.getMoneyTypes(), vo.getMoneys(), vo.getOperaterCode(), 
					vo.getOperaterName(),vo.getUniqueCode(), vo.getNotes(), vo.getDataType());
		}catch (AppException e) {
			e.printStackTrace();
			map.put("isPay", false);
			map.put("msg", e.getMessage());
		}
		return map;
		
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
	public void saveJfiPayDataVerify(final String companyCode, final JsysUser sysUser, final Integer[] moneyType, final BigDecimal[] moneyArray,
	        String operaterCode, final String operaterName, final String uniqueCode, final String[] notes, String dataType) throws Exception{
		//判断用户存折是否存在
		JfiBankbookBalance jfiBankbookBalance = this.jfiBankbookBalanceDao.getJfiBankbookBalanceForUpdate(sysUser.getUserCode());
		if (jfiBankbookBalance == null) {
			throw new Exception("电子存折帐户不存在！");
		}
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			JfiBankbookJournal lastJfiBankbookJournal = jfiBankbookJournalDao.getLastJfiBankbookJournalByUnique(uniqueCode,"A");
			if (lastJfiBankbookJournal != null && "A".equals(lastJfiBankbookJournal.getDealType())) {
				throw new Exception("重复操作！");
			}
		}
		
		BigDecimal money = new BigDecimal(0);
		for(int i = 0 ; i < moneyArray.length ; i++){
			if(moneyArray[i].compareTo(new BigDecimal("0"))==-1){
				throw new Exception("金额参数必须大于或等于零" + moneyArray[i]);
			}
			money = money.add(moneyArray[i]);
		}

		BigDecimal remainMoney;
		BigDecimal tempMoney;
		JfiBankbookJournal lastJfiBankbookJournal = this.jfiBankbookJournalDao.getLastJfiBankbookJournal(sysUser.getUserCode());
		if (lastJfiBankbookJournal == null) {
			remainMoney = new BigDecimal(0);
			tempMoney =  new BigDecimal(0);
		} else {
			remainMoney = lastJfiBankbookJournal.getBalance();
			tempMoney = lastJfiBankbookJournal.getBalance();
		}

		Date currentDate = new Date();
		Date currentTime = new Date();
		for(int i = 0 ; i < moneyArray.length ; i++){

			//插入存折临时记录
			JfiBankbookTemp jfiBankbookTemp = new JfiBankbookTemp();
			jfiBankbookTemp.setCompanyCode(companyCode);
			jfiBankbookTemp.setSysUser(sysUser);
			long totalCount = this.jfiBankbookTempDao.getCountByDate(companyCode, sysUser.getUserCode());
			jfiBankbookTemp.setSerial(totalCount + 1);
			jfiBankbookTemp.setDealType("A");
			jfiBankbookTemp.setDealDate(currentDate);
			jfiBankbookTemp.setMoneyType(moneyType[i]);
			jfiBankbookTemp.setMoney(moneyArray[i]);
			jfiBankbookTemp.setNotes(notes[i]);
			jfiBankbookTemp.setStatus(2);
			jfiBankbookTemp.setCreaterCode(operaterCode);
			jfiBankbookTemp.setCreaterName(operaterName);
			jfiBankbookTemp.setCreateTime(currentTime);
			jfiBankbookTemp.setCheckerCode(operaterCode);
			jfiBankbookTemp.setCheckerName(operaterName);
			jfiBankbookTemp.setCheckeTime(currentTime);
			jfiBankbookTemp.setCheckMsg("OK");
			jfiBankbookTemp.setCheckType(1);

			jfiBankbookTempDao.saveJfiBankbookTemp(jfiBankbookTemp);
			//插入存折流水记录
			JfiBankbookJournal jfiBankbookJournal = new JfiBankbookJournal();
			jfiBankbookJournal.setJfiBankbookTemp(jfiBankbookTemp);
			jfiBankbookJournal.setCompanyCode(companyCode);
			jfiBankbookJournal.setUserCode(sysUser.getUserCode());
			long todayCount = this.jfiBankbookTempDao.getCountByDate(companyCode, sysUser.getUserCode());
			jfiBankbookJournal.setSerial(todayCount + 1);
			jfiBankbookJournal.setDealType("A");
			jfiBankbookJournal.setDealDate(currentDate);
			jfiBankbookJournal.setMoneyType(moneyType[i]);
			jfiBankbookJournal.setMoney(moneyArray[i]);
			jfiBankbookJournal.setNotes(notes[i]);
			tempMoney = tempMoney.add(moneyArray[i]);
			jfiBankbookJournal.setBalance(tempMoney);
			jfiBankbookJournal.setCreaterCode(operaterCode);
			jfiBankbookJournal.setCreaterName(operaterName);
			jfiBankbookJournal.setCreateTime(currentTime);
			jfiBankbookJournal.setLastJournalId(lastJfiBankbookJournal == null ? 0 : lastJfiBankbookJournal.getJournalId());
			jfiBankbookJournal.setLastMoney(lastJfiBankbookJournal == null ? new BigDecimal(0) : lastJfiBankbookJournal.getBalance());
			jfiBankbookJournal.setUniqueCode(uniqueCode);
			jfiBankbookJournal.setDataType(dataType);

			jfiBankbookJournalDao.saveJfiBankbookJournal(jfiBankbookJournal);
		}

		jfiBankbookBalance.setBalance(remainMoney.add(money));
		jfiBankbookBalanceDao.save(jfiBankbookBalance);
	}
	
	/**
	 * 快钱数据进存折
	 * @throws Exception 
	 * @throws Exception 
	 */
	@Override
	public BigDecimal saveJfiPayDataVerifyByBill99(final String companyCode, final JsysUser sysUser, final BigDecimal totalMoney,
	        String operaterCode, final String operaterName, final String uniqueCode, Jfi99billLog jfi99billLog,final BigDecimal fee) throws Exception{
		BigDecimal[] moneyArray = new BigDecimal[2];
		moneyArray[0] = totalMoney.subtract(fee);
		moneyArray[1] = fee;
		
		Integer[] moneyType = new Integer[2];
		moneyType[0] = 29;
		moneyType[1] = 31;
		
		String[] notes = new String[2];
		notes[0] = "快钱支付";
		notes[1] = "快钱手续费";
		
		this.saveJfiPayDataVerify(companyCode, sysUser, moneyType, moneyArray, operaterCode, operaterName,uniqueCode,notes,"1");
		jfi99billLog.setInc("1");
		jfi99billLogManager.save(jfi99billLog);
		return moneyArray[1];
	}
	
	/**
	 * 畅捷通数据进存折
	 * @throws Exception 
	 */
	@Override
	public BigDecimal saveJfiPayDataVerifyByChanjet(final String companyCode, final JsysUser sysUser, final BigDecimal totalMoney,
	        String operaterCode, final String operaterName, final String uniqueCode, JfiChanjetLog jfiChanjetLog) throws Exception{
		
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = totalMoney;
		
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 90;//90:畅捷支付费用
		
		String[] notes = new String[1];
		notes[0] = "畅捷支付";
		
		this.saveJfiPayDataVerify(companyCode, sysUser, moneyType, moneyArray, operaterCode, operaterName,uniqueCode,notes,"1");
		jfiChanjetLog.setInc("1");
		jfiChanjetLogManager.save(jfiChanjetLog);
		return moneyArray[0];
	}
}