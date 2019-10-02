package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JbdBonusBalanceDao;
import com.joymain.ng.dao.JbdSendRecordHistDao;
import com.joymain.ng.model.JbdBonusBalance;
import com.joymain.ng.model.JbdSendRecordHist;
import com.joymain.ng.model.JbdSendRecordNote;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.FiBankbookJournalManager;
import com.joymain.ng.service.JbdSendRecordHistManager;
import com.joymain.ng.service.JbdSendRecordNoteManager;
import com.joymain.ng.service.JfiBankbookJournalManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

@Service("jbdSendRecordHistManager")
@WebService(serviceName = "JbdSendRecordHistService", endpointInterface = "com.joymain.ng.service.JbdSendRecordHistManager")
public class JbdSendRecordHistManagerImpl extends GenericManagerImpl<JbdSendRecordHist, Long> implements JbdSendRecordHistManager {
    JbdSendRecordHistDao jbdSendRecordHistDao;

    @Autowired
    private JbdBonusBalanceDao jbdBonusBalanceDao;

    @Autowired
    private JbdSendRecordNoteManager jbdSendRecordNoteManager;

    @Autowired
    private JfiBankbookJournalManager jfiBankbookJournalManager;

    @Autowired
    private FiBankbookJournalManager fiBankbookJournalManager;
    
    @Autowired
    public JbdSendRecordHistManagerImpl(JbdSendRecordHistDao jbdSendRecordHistDao) {
        super(jbdSendRecordHistDao);
        this.jbdSendRecordHistDao = jbdSendRecordHistDao;
    }
	
	public Pager<JbdSendRecordHist> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		return jbdSendRecordHistDao.getPager(JbdSendRecordHist.class, searchBeans, OrderBys, currentPage, pageSize);
	}
    
	/**
	 * 查询明细－－创业销售奖
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	@Override
	public List getVentureSalesPv(String userCode, String wweek) {
		return jbdSendRecordHistDao.getVentureSalesPv(userCode,wweek);
	}

	/**
	 * 查询明细－－创业领导奖01--jbdMemberLinkCalcHist.ventureFund
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 * @param string
	 */
	@Override
	public List getVentureLeaderPvOne(String userCode, String wweek,
			String string) {
		return jbdSendRecordHistDao.getVentureLeaderPvOne(userCode,wweek,string);
	}
	public List getVentureLeaderDetail(String userCode, String wweek){
		return jbdSendRecordHistDao.getVentureLeaderDetail(userCode,wweek);
	}

	/**
	 * 查询明细－－成功销售奖---页面中没有该明细
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	@Override
	public List getSuccessSaleBonus(String userCode, String wweek) {
		return jbdSendRecordHistDao.getSuccessSaleBonus(userCode,wweek);
	}

	/**
	 * 查询明细－－加盟店店补
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 * @param string
	 */
	@Override
	public List getFranchises(String userCode, String wweek, String string) {
		return jbdSendRecordHistDao.getFranchises(userCode,wweek,string);
	}

	/**
	 * VENTURE_FUND 查询明细－－扶持奖( 创业基金)---jbdMemberLinkCalcHist.ventureFund
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	@Override
	public List getVentureFundPv(String userCode, String wweek) {
		return jbdSendRecordHistDao.getVentureFundPv(userCode,wweek);
	}

	/**
	 * 查询明细－－推荐奖金
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	@Override
	public List getJbdSellCalcRecommendBouns(String userCode, String wweek) {
		return jbdSendRecordHistDao.getJbdSellCalcRecommendBouns(userCode,wweek);
	}

	/**
	 * 查询明细－－店铺拓展奖
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	@Override
	public List getStoreExpandPv(String userCode, String wweek) {
		return jbdSendRecordHistDao.getStoreExpandPv(userCode,wweek);
	}

	/**
	 * 查询明细－－推广奖
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	@Override
	public List getPopularizeBonusPv(String userCode, String wweek) {
		return jbdSendRecordHistDao.getPopularizeBonusPv(userCode,wweek);
	}

	/**
	 * 查询明细－－店铺服务奖 
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	@Override
	public List getStoreServePv(String userCode, String wweek) {
		return jbdSendRecordHistDao.getStoreServePv(userCode,wweek);
	}

	/**
	 * 查询明细－－店铺推荐奖
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	@Override
	public List getStoreRecommendPv(String userCode, String wweek) {
		return jbdSendRecordHistDao.getStoreRecommendPv(userCode,wweek);
	}

	/**
	 * 查询明细－－创业销售奖－－查询明细－－创业销售奖明细查询
	 * @author Administrator
	 * @param userCode
	 * @param wweek
	 */
	@Override
	public List getJbdSellCalcSubDetailHist(String userCode, String wweek) {
		return jbdSendRecordHistDao.getJbdSellCalcSubDetailHist(userCode,wweek);
	}

	public List getJbdSuccessLeaderPv(String userCode, String wweek){
		return jbdSendRecordHistDao.getJbdSuccessLeaderPv(userCode,wweek);
	}

	@Override
	public List getSuccessLeaderPvDetail(String userCode, String wweek,
			String passStar, String startDate, String endDate,String algebra) {
		return jbdSendRecordHistDao.getSuccessLeaderPvDetail(userCode, wweek, passStar, startDate, endDate,algebra);
	}
	
	
	public void saveInFiBook(JsysUser defSysUser,String id,String remittanceBNum){
	    
	    Date curDate=new Date();
	    //String remittanceBNum="200";
	    
	    
	    JbdSendRecordHist jbdSendRecordHist=jbdSendRecordHistDao.getJbdSendRecordHistForUpdate(new Long(id));
	    
	    
	    
		jbdSendRecordHist.setRegisterStatus("2");
		jbdSendRecordHist.setSendDate(curDate);
		jbdSendRecordHist.setOperaterTime(curDate);
	    

		jbdSendRecordHist.setRemittanceBNum(remittanceBNum);
		jbdSendRecordHist.setOperaterCode(defSysUser.getUserCode());
		
		jbdSendRecordHistDao.save(jbdSendRecordHist);

		JbdBonusBalance jbdBonusBalance = null;
		
		jbdBonusBalance = jbdBonusBalanceDao.get(jbdSendRecordHist.getJmiMember().getUserCode());
		
		
		
    	jbdBonusBalance.setBonusBalance(jbdBonusBalance.getBonusBalance().subtract(jbdSendRecordHist.getRemittanceMoney()));
		jbdBonusBalanceDao.save(jbdBonusBalance);

		

		//更新中间表
		JbdSendRecordNote jbdSendRecordNote=jbdSendRecordNoteManager.getJbdSendRecordNoteForUpdate(jbdSendRecordHist.getId());

		
		
		
		jbdSendRecordNote.setRegisterStatus("2");
		jbdSendRecordNote.setSendDate(curDate);
		jbdSendRecordNote.setOperaterTime(curDate);
		jbdSendRecordNote.setRemittanceBNum(remittanceBNum);
		jbdSendRecordNote.setOperaterCode(defSysUser.getUserCode());
		jbdSendRecordNoteManager.save(jbdSendRecordNote);
		
		
	}
	private void saveJfiPayDataVerify(BigDecimal sendMoney,String companyCode,JsysUser sysUser,JsysUser defSysUser,String unique) throws Exception{

		Integer[] moneyType = new Integer[1];
		moneyType[0]=40;
		
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0]=sendMoney;

		String[] notes = new String[1];
		notes[0]="奖金转电子存折";
		
		jfiBankbookJournalManager.saveJfiPayDataVerify(companyCode, sysUser, moneyType,moneyArray, defSysUser.getUserCode(), defSysUser.getUserName(), "bd"+unique, notes,"1");

	}
	
	
	
	public void saveInDevFiBook(JsysUser defSysUser,String id) throws Exception{

	    Date curDate=new Date();
	    
	    
	    JbdSendRecordHist jbdSendRecordHist=jbdSendRecordHistDao.getJbdSendRecordHistForUpdate(new Long(id));
		
	    if(!"2".equals(jbdSendRecordHist.getSendStatusDev())){
	    	jbdSendRecordHist.setSendDateDev(curDate);
			jbdSendRecordHist.setSendUserDev(defSysUser.getUserCode());
			jbdSendRecordHist.setSendStatusDev("2");
			
			
			jbdSendRecordHistDao.save(jbdSendRecordHist);


			//更新中间表
			JbdSendRecordNote jbdSendRecordNote=jbdSendRecordNoteManager.getJbdSendRecordNoteForUpdate(jbdSendRecordHist.getId());
			jbdSendRecordNote.setSendDateDev(curDate);
			jbdSendRecordNote.setSendUserDev(defSysUser.getUserCode());
			jbdSendRecordNote.setSendStatusDev("2");
			jbdSendRecordNoteManager.save(jbdSendRecordNote);
			

		
	    }
	    
		
		
	}
	
	private void saveFiPayDataVerify(BigDecimal sendMoney,String companyCode,JsysUser sysUser,JsysUser defSysUser,String unique,String note,Integer moneyTypeInt)throws Exception{
		Integer[] moneyType = new Integer[1];
		//moneyType[0]=67;
		moneyType[0]=moneyTypeInt;
		
		BigDecimal[] moneyArray = new BigDecimal[1];
		moneyArray[0]=sendMoney;

		String[] notes = new String[1];
		//notes[0]="奖金转入发展基金";
		notes[0]=note;
		
		fiBankbookJournalManager.saveFiPayDataVerify(companyCode, sysUser, moneyType, moneyArray, defSysUser.getUserCode(), defSysUser.getUserName(), "bd_dev_"+unique, notes, "1", "1");
		
	}
	
	
	public void saveJbdSendType(String id,String sendType,JsysUser defSysUser) throws Exception{

	    JbdSendRecordHist jbdSendRecordHist=jbdSendRecordHistDao.get(new Long(id));
		if("1".equals(sendType)){
			
			if(jbdSendRecordHist.getRemittanceMoney().compareTo(new BigDecimal(0))==1){

				this.saveInFiBook(defSysUser, id, "200");
				this.saveJfiPayDataVerify(jbdSendRecordHist.getRemittanceMoney(), jbdSendRecordHist.getJmiMember().getCompanyCode(), jbdSendRecordHist.getJmiMember().getSysUser(), defSysUser, jbdSendRecordHist.getId()+"");
			}
			if(jbdSendRecordHist.getCurrentDev().compareTo(new BigDecimal(0))==1){

				this.saveInDevFiBook(defSysUser, id);
				this.saveFiPayDataVerify(jbdSendRecordHist.getCurrentDev(), jbdSendRecordHist.getJmiMember().getCompanyCode(), jbdSendRecordHist.getJmiMember().getSysUser(), defSysUser, jbdSendRecordHist.getId()+"", "奖金转入发展基金", 67);
			}
			
		}else if("2".equals(sendType)){
			BigDecimal totalMoney=jbdSendRecordHist.getRemittanceMoney().add(jbdSendRecordHist.getCurrentDev());
			if(totalMoney.compareTo(new BigDecimal(0))==1){
				
				BigDecimal giftMoney=totalMoney.multiply(new BigDecimal(0.2));
				giftMoney =  giftMoney.setScale(2, BigDecimal.ROUND_HALF_UP);
				this.saveInFiBook(defSysUser, id, "200");
				this.saveInDevFiBook(defSysUser, id);
				
				Integer[] moneyType = new Integer[3];
				//moneyType[0]=67;
				moneyType[0]=67;
				moneyType[1]=67;
				moneyType[2]=65;
				
				BigDecimal[] moneyArray = new BigDecimal[3];
				moneyArray[0]=jbdSendRecordHist.getRemittanceMoney();
				moneyArray[1]=jbdSendRecordHist.getCurrentDev();
				moneyArray[2]=giftMoney;

				String[] notes = new String[3];
				//notes[0]="奖金转入发展基金";
				notes[0]="奖金转入发展基金-原应发奖金";
				notes[1]="奖金转入发展基金";
				notes[2]="奖金转入发展基金20%";
				
				fiBankbookJournalManager.saveFiPayDataVerify(jbdSendRecordHist.getJmiMember().getCompanyCode(), jbdSendRecordHist.getJmiMember().getSysUser(), moneyType, moneyArray, defSysUser.getUserCode(), defSysUser.getUserName(), "bd_dev_"+jbdSendRecordHist.getId(), notes, "1", "1");
				
				
				
				
			/*	this.saveFiBankbookDeduct(, jbdSendRecordHist.getJmiMember().getCompanyCode(), jbdSendRecordHist.getJmiMember().getSysUser(), defSysUser, jbdSendRecordHist.getId()+"1", "奖金转入发展基金-原应发奖金", 67);
				this.saveFiBankbookDeduct(, jbdSendRecordHist.getJmiMember().getCompanyCode(), jbdSendRecordHist.getJmiMember().getSysUser(), defSysUser, jbdSendRecordHist.getId()+"2", "奖金转入发展基金", 67);
				this.saveFiBankbookDeduct(, jbdSendRecordHist.getJmiMember().getCompanyCode(), jbdSendRecordHist.getJmiMember().getSysUser(), defSysUser, jbdSendRecordHist.getId()+"3", "奖金转入发展基金20%", 65);
				*/
				
			}
			
			
		}
		jbdSendRecordHist.setSendType(sendType);
		this.save(jbdSendRecordHist);
	}

	@Override
	public JbdSendRecordHist getJbdSendRecordHistByUserCodeByWeek(String userCode, String wweek) {
		return jbdSendRecordHistDao.getJbdSendRecordHistByUserCodeByWeek(userCode, wweek);
	}

	@Override
	public List getVentureLeaderPvDetail(String userCode, String startDate,
			String endDate) {
		return jbdSendRecordHistDao.getVentureLeaderPvDetail(userCode, startDate, endDate);
	}

	@Override
	public List getServicePv(String userCode, String wweek) {
		return jbdSendRecordHistDao.getServicePv(userCode, wweek);
	}

	@Override
	public List getbdjPv201607(String userCode, String wweek) {
		// TODO Auto-generated method stub
		return jbdSendRecordHistDao.getbdjPv201607(userCode, wweek);
	}

	@Override
	public List getServicePv201607(String userCode, String wweek) {
		// TODO Auto-generated method stub
		return jbdSendRecordHistDao.getServicePv201607(userCode, wweek);
	}
	
	
}