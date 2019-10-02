package com.joymain.ng.service.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.FiTransferAccountDao;
import com.joymain.ng.dao.JfiBankbookBalanceDao;
import com.joymain.ng.dao.JfiBankbookJournalDao;
import com.joymain.ng.dao.JpoBankBookPayListDao;
import com.joymain.ng.model.FiTransferAccount;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.model.JfiBankbookJournal;
import com.joymain.ng.model.JpoBankBookPayList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiTransferAccountManager;
import com.joymain.ng.service.JfiBankbookJournalManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.AppException;
import com.joymain.ng.util.DateUtil;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.StringUtil;

@Service("fiTransferAccountManager")
@WebService(serviceName = "FiTransferAccountService", endpointInterface = "com.joymain.ng.service.FiTransferAccountManager")
public class FiTransferAccountManagerImpl extends GenericManagerImpl<FiTransferAccount, Long> implements FiTransferAccountManager {
    FiTransferAccountDao fiTransferAccountDao;
    
    //存折服务管理对象
	private JfiBankbookJournalManager jfiBankbookJournalManager;
	
	private JsysUserManager sysUserManager = null;
	
	private JpoBankBookPayListDao jpoBankBookPayListDao;
	
	private JfiBankbookJournalDao jfiBankbookJournalDao;
	private JfiBankbookBalanceDao jfiBankbookBalanceDao;
	
	@Autowired
	public void setJfiBankbookJournalDao(JfiBankbookJournalDao jfiBankbookJournalDao) {
		this.jfiBankbookJournalDao = jfiBankbookJournalDao;
	}

	@Autowired
	public void setJfiBankbookBalanceDao(JfiBankbookBalanceDao jfiBankbookBalanceDao) {
		this.jfiBankbookBalanceDao = jfiBankbookBalanceDao;
	}

	@Autowired
	public void setJpoBankBookPayListDao(JpoBankBookPayListDao jpoBankBookPayListDao) {
		this.jpoBankBookPayListDao = jpoBankBookPayListDao;
	}

	@Autowired
    public void setSysUserManager(JsysUserManager sysUserManager) {
		this.sysUserManager = sysUserManager;
	}
	
	@Autowired
	public void setJfiBankbookJournalManager(
			JfiBankbookJournalManager jfiBankbookJournalManager) {
		this.jfiBankbookJournalManager = jfiBankbookJournalManager;
	}

    @Autowired
    public FiTransferAccountManagerImpl(FiTransferAccountDao fiTransferAccountDao) {
        super(fiTransferAccountDao);
        this.fiTransferAccountDao = fiTransferAccountDao;
    }
	
	public Pager<FiTransferAccount> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiTransferAccountDao.getPager(FiTransferAccount.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public List getFiTransferAccountListByUserCode(String userCode,String dealStartTime,String dealEndTime) {
		// TODO Auto-generated method stub
		return fiTransferAccountDao.getFiTransferAccountListByUserCode(userCode,dealStartTime,dealEndTime);
	}
	
	@Override
	public List getFiTransferAccountListByUserCodePage(GroupPage page,String userCode,String dealStartTime,String dealEndTime) {
		// TODO Auto-generated method stub
		return fiTransferAccountDao.getFiTransferAccountListByUserCodePage(page,userCode,dealStartTime,dealEndTime);
	}

	@Override
	public BigDecimal getSumTransferValueByTransferCode(String transferCode) {
		// TODO Auto-generated method stub
		return fiTransferAccountDao.getSumTransferValueByTransferCode(transferCode);
	}
	
	/**
	 * 创建转账单
	 * 业务规则：1.从转账用户账户里面扣钱，成功则 ：2.创建转账单
	 * @throws Exception 
	 */
	@Override
	public void addTransferAccounts(FiTransferAccount fiTransferAccount, JsysUser transferUser) throws Exception{
		
		//转账金额
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0] = fiTransferAccount.getMoney();
		//资金类型
		Integer[] moneyType = new Integer[1];
		moneyType[0] = 9;//9代表转账
		//说明
		String[] notes = new String[1];
		if(StringUtil.isEmpty(fiTransferAccount.getNotes())){
			notes[0] = "转账支出";
		}else{
			notes[0] = "转账支出,"+fiTransferAccount.getNotes();
		}

		//1.从用户帐户扣取金额
		jfiBankbookJournalManager.saveJfiBankbookDeduct("CN",transferUser,moneyType,moneyArray,fiTransferAccount.getCreaterCode(),fiTransferAccount.getCreaterName(),"0",notes,"1");

		//转账目标用户
		JsysUser destinationUser=this.sysUserManager.get(fiTransferAccount.getDestinationUserCode());
		notes[0] = "来自"+fiTransferAccount.getTransferUserCode()+"的转账";
		//uniqueCode="ZZSK"+fiTransferAccount.getTaId().toString();
		
		//2.存入资金至转账目标用户帐户
		jfiBankbookJournalManager.saveJfiPayDataVerify("CN", destinationUser, moneyType, moneyArray, fiTransferAccount.getCheckerCode(), fiTransferAccount.getCheckerName(), "0", notes,"1");
		
		//3.生成转账单
		fiTransferAccount.setStatus(2);//已核准
		fiTransferAccount.setCheckerCode("root");
		fiTransferAccount.setCheckerName("系统自审");
		fiTransferAccount.setCheckeTime(new Date());
		fiTransferAccount.setDealDate(new Date());
		this.fiTransferAccountDao.save(fiTransferAccount);
	}
	
	/**
	 * Add By WuCF 20160811
	 * 创建转账单
	 * 业务规则：1.从转账用户账户里面扣钱，成功则 ：2.创建转账单
	 * @throws Exception 
	 */
	@Override
	public void addTransferAccountsNew(FiTransferAccount fiTransferAccount, JsysUser transferUser,String dataType) throws Exception{
		String uniqueCode = String.valueOf(new Date().getTime());
		//添加判断
		//1.电子存折帐户不存在
		JfiBankbookBalance jfiBankbookBalance = this.jfiBankbookBalanceDao.getJfiBankbookBalance(fiTransferAccount.getTransferUserCode());
		if (jfiBankbookBalance == null) {
			throw new AppException("电子存折帐户不存在!");
		}
		
		//2.已扣过款了！
		//判断是否重复审单
		if (!StringUtils.isEmpty(uniqueCode) && !"0".equals(uniqueCode)) {
			JfiBankbookJournal lastJfiBankbookJournal = jfiBankbookJournalDao.getLastJfiBankbookJournalByUnique(uniqueCode,"D");
			if (lastJfiBankbookJournal != null && "D".equals(lastJfiBankbookJournal.getDealType())) {
				throw new AppException("已扣过款了！");
			}
		}
		
		//3.金额参数必须大于或等于零
		if(fiTransferAccount.getMoney().compareTo(new BigDecimal("0"))==-1){
			throw new AppException("金额参数必须大于或等于零"+fiTransferAccount.getMoney());
		}
		
		//4.电子存折帐户余额不够
		//首先验证余额是否足够
		log.info("balance is:"+jfiBankbookBalance.getBalance().toString()+" and money is:"+fiTransferAccount.getMoney());
		if (jfiBankbookBalance.getBalance().compareTo(fiTransferAccount.getMoney()) == -1) {
			throw new AppException("电子存折帐户余额不够!");
		}
		
		
		//Modify By WuCF 20160811 转账用存储过程实现
		JpoBankBookPayList bbp = new JpoBankBookPayList();
		bbp.setAmount(fiTransferAccount.getMoney());//金额
		bbp.setCreateTime(DateUtil.getNowTimeFromDateServer());
		bbp.setDataType(dataType);//平台来源 1：PC   2：手机
		
		bbp.setInType(10);//类型
		bbp.setMemberOrderNo(uniqueCode);//唯一标示
		bbp.setMoneyType(9);//资金类别
		bbp.setMoneyType1(9);
		bbp.setCheckUserCode(fiTransferAccount.getTransferUserCode());//审核者
		bbp.setUserCode(fiTransferAccount.getDestinationUserCode());//转入者
		bbp.setUserSPcode(fiTransferAccount.getTransferUserCode());//转出者
		
		//3.生成转账单
		fiTransferAccount.setStatus(1);//已核准
		/*fiTransferAccount.setCheckerCode("");
		fiTransferAccount.setCheckerName("");
		fiTransferAccount.setCheckeTime(new Date());
		fiTransferAccount.setDealDate(new Date());*/
		String taId = this.fiTransferAccountDao.saveFiTransferAccount(fiTransferAccount);
		
		System.out.println("==taId:"+taId);
		//保存主键
		bbp.setLogId(taId);//存入转账数据主键值 
		jpoBankBookPayListDao.save(bbp);
	}
	
	public void checkTransferAccounts(FiTransferAccount fiTransferAccount) {
		
		
	}
}