package com.joymain.ng.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import com.joymain.ng.model.FiTransferAccount;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface FiTransferAccountManager extends GenericManager<FiTransferAccount, Long> {
    
	public Pager<FiTransferAccount> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	//根据用户查询转账单列表
	public List getFiTransferAccountListByUserCode(String userCode,String dealStartTime,String dealEndTime);
	
	//根据用户查询转账单列表
	public List getFiTransferAccountListByUserCodePage(GroupPage page,String userCode,String dealStartTime,String dealEndTime);
	
	 //获取单日转账总额
    public BigDecimal getSumTransferValueByTransferCode(final String transferCode);
    
    //创建转账单
    public void addTransferAccounts(FiTransferAccount fiTransferAccount, JsysUser transferUser) throws Exception;
    
    //创建转账单:新增平台方式
    public void addTransferAccountsNew(FiTransferAccount fiTransferAccount, JsysUser transferUser,String dataType) throws Exception;
}