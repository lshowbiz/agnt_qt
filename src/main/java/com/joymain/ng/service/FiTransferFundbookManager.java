package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.FiTransferFundbook;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import java.math.BigDecimal;
import java.util.List;

import javax.jws.WebService;

import java.util.Collection;
@WebService
public interface FiTransferFundbookManager extends GenericManager<FiTransferFundbook, Long> {
    
	public Pager<FiTransferFundbook> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public List getFiTransferFundbookListByUserCodePage(GroupPage page,String userCode,String dealStartTime,String dealEndTime);
	
	public void doTransferFundbookDeduct(FiTransferFundbook fiTransferFundbook, JsysUser transferUser) throws Exception;
	
}