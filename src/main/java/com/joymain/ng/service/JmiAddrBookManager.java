package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JmiAddrBook;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import java.util.List;

import javax.jws.WebService;

import org.springframework.validation.BindingResult;

import java.util.Collection;
@WebService
public interface JmiAddrBookManager extends GenericManager<JmiAddrBook, Long> {
    
	public Pager<JmiAddrBook> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
    public List getJmiAddrBookByUserCode(String userCode);
    public JmiAddrBook getDefaultJmiAddrBookByUserCode(String userCode);
	public boolean getCheckJmiAddrBook(BindingResult errors,JmiAddrBook jmiAddrBook);
	public void saveJmiAddrBookPc(JmiAddrBook jmiAddrBook);
	public void saveJmiAddrBook(JmiAddrBook jmiAddrBook,JsysUser user);
    public List getJmiAddrBookByUserCodeBySql(String userCode);
	public void saveDefaultJmiAddrBookPc(String fabId);
	public void saveDefaultJmiAddrBook(String fabId,JsysUser user);
	public void removeJmiAddrBookPc(String fabId);
	public void removeJmiAddrBook(String fabId,JsysUser user);
	public JmiAddrBook getDefaultJmiAddrBookByUserCodeHome(String userCode);
	/**
	 * 保存默认收货地址并存入FI_COMMEN_ADDR商户号用
	 * @param jmiAddrBook
	 */
	public void saveJmiAddrBoookAndFiAddr(JmiAddrBook jmiAddrBook);
	public Long saveJmiAddrBookPcByDwr(JmiAddrBook jmiAddrBook);
	
}