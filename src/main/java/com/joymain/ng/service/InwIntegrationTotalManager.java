package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.InwIntegrationTotal;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.model.MiCoinLog;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import javax.jws.WebService;

import java.math.BigDecimal;
import java.util.Collection;
@WebService
public interface InwIntegrationTotalManager extends GenericManager<InwIntegrationTotal, Long> {
    
	public Pager<InwIntegrationTotal> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	/**
     * 用户减掉积分的操作
     * @author yxzz  2014-06-09
     * @param userCode 会员编号
     * @param integratotal 积分
     * @param uniqueCode 唯一码
     * @return boolean
     */
    public boolean minusIntegrationTotal(String userCode,String integratotal,String uniqueCode);
	public InwIntegrationTotal getInwIntegrationTotal(String userCode);
	public MiCoinLog sendCoin(BigDecimal bcoinNum,JsysUser jsysUser ,String mallType,String sendNo);
	
	
}