package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JprRefund;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface JprRefundManager extends GenericManager<JprRefund, String> {
    
	public Pager<JprRefund> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);

	public List getJprRefundsListPage(GroupPage page, String roNo,
			String memberOrderNo, String userCode, String timeBegin,
			String timeEnd);

	public JprRefund getJprRefunds(String roNo);
	
}