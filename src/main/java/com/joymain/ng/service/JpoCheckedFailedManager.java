package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JpoCheckedFailed;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface JpoCheckedFailedManager extends GenericManager<JpoCheckedFailed, Long> {
    
	public Pager<JpoCheckedFailed> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public JpoCheckedFailed getByOrderNo(JpoMemberOrder jpoMemberOrder);
	
	public Integer deleteJpoCheckedFaiiled(String moId);
}
