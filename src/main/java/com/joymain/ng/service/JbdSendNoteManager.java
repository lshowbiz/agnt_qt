package com.joymain.ng.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import com.joymain.ng.model.JbdSendNote;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface JbdSendNoteManager extends GenericManager<JbdSendNote, Long> {
    
	public Pager<JbdSendNote> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);

    public List getJbdSendNote(String userCode);
    
    public List getJbdSendNotePage(GroupPage page,String userCode);
    
	/**
	 * 奖金提现
	 * @param companyCode
	 * @param sysUser
	 * @param operaterCode
	 * @param operaterName
	 * @param amount
	 */
	public void saveJbdSendNoteAndDeductBankbook(final String companyCode, final JsysUser sysUser,String operaterCode, final String operaterName, final BigDecimal amount, final String uniqueCode,String dataType);
}