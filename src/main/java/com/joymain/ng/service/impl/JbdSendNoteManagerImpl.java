package com.joymain.ng.service.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JbdSendNoteDao;
import com.joymain.ng.dao.JpoBankBookPayListDao;
import com.joymain.ng.model.JbdSendNote;
import com.joymain.ng.model.JpoBankBookPayList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JbdSendNoteManager;
import com.joymain.ng.service.JfiBankbookJournalManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@Service("jbdSendNoteManager")
@WebService(serviceName = "JbdSendNoteService", endpointInterface = "com.joymain.ng.service.JbdSendNoteManager")
public class JbdSendNoteManagerImpl extends GenericManagerImpl<JbdSendNote, Long> implements JbdSendNoteManager {
    JbdSendNoteDao jbdSendNoteDao;

    @Autowired
    public JbdSendNoteManagerImpl(JbdSendNoteDao jbdSendNoteDao) {
        super(jbdSendNoteDao);
        this.jbdSendNoteDao = jbdSendNoteDao;
    }

    @Autowired
    private JfiBankbookJournalManager jfiBankbookJournalManager;

    @Autowired
    private JpoBankBookPayListDao jpoBankBookPayListDao;
	public Pager<JbdSendNote> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jbdSendNoteDao.getPager(JbdSendNote.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	public List getJbdSendNote(String userCode) {
		return jbdSendNoteDao.getJbdSendNote(userCode);
	}
	
	public List getJbdSendNotePage(GroupPage page,String userCode) {
		return jbdSendNoteDao.getJbdSendNotePage(page,userCode);
	}
	
	/**
	 * 奖金提现
	 * @param companyCode
	 * @param sysUser
	 * @param operaterCode
	 * @param operaterName
	 * @param amount
	 */
	public void saveJbdSendNoteAndDeductBankbook(final String companyCode, final JsysUser sysUser,String operaterCode, final String operaterName, final BigDecimal amount, final String uniqueCode,String dataType){
		/*JbdSendNote jbdSendNote = new JbdSendNote();
		jbdSendNote.setCompanyCode(companyCode);
		jbdSendNote.setCreateNo(operaterCode);
		jbdSendNote.setCreateTime(new Date());
		jbdSendNote.setJmiMember(sysUser.getJmiMember());
		jbdSendNote.setRegisterStatus("1");
		jbdSendNote.setReissueStatus("1");*/
		BigDecimal fee = new BigDecimal("3");
		if(amount.compareTo(new BigDecimal("500"))!=-1){
			fee = new BigDecimal("1");
		}
		BigDecimal subtractAmount = amount.subtract(fee);
		/*jbdSendNote.setRemittanceMoney(subtractAmount);
		jbdSendNote.setFee(fee);
		jbdSendNoteDao.save(jbdSendNote);
		Integer[] moneyType = {55,56};
		BigDecimal[] moneyArray = {fee,subtractAmount};
		String[] notes = {"奖金提现手续费","奖金提现"};
		jfiBankbookJournalManager.saveJfiBankbookDeduct(companyCode, sysUser, moneyType, moneyArray, operaterCode, operaterName, uniqueCode, notes,dataType);*/
		
		//2016-8-11 改为插中间表
		JpoBankBookPayList jpoBankBookPayList=new JpoBankBookPayList();
		jpoBankBookPayList.setUserCode(sysUser.getJmiMember().getUserCode());
		jpoBankBookPayList.setUserSPcode(sysUser.getJmiMember().getUserCode());
		jpoBankBookPayList.setMemberOrderNo(uniqueCode);
		jpoBankBookPayList.setInType(11);
		jpoBankBookPayList.setCreateTime(new Date());
		jpoBankBookPayList.setAmount(subtractAmount);
		jpoBankBookPayList.setFee(fee);
		jpoBankBookPayList.setDataType(dataType);
		jpoBankBookPayList.setMoneyType1(56);
		jpoBankBookPayList.setMoneyType2(55);
		jpoBankBookPayList.setNotes("奖金提现");
		jpoBankBookPayList.setCheckUserCode(operaterCode);
		jpoBankBookPayListDao.save(jpoBankBookPayList);
		//
	}
}