package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JmiAddrBook;
import com.joymain.ng.model.JpmSendConsignment;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;
@WebService
public interface JpmSendConsignmentManager extends GenericManager<JpmSendConsignment, Long> {
    
	public Pager<JpmSendConsignment> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public List<JpmSendConsignment> getJpmSendConsignmentListBySpecNo(Long specNo);
	
	public void delJpmSendConsignmentByConsignmentNo(Long consignmentNo);
	
	public JpmSendConsignment getJpmSendConsignmentByConsignmentNo(Long consignmentNo);
	
	public String getAddresByFabId(JmiAddrBook jmiAddrBook);
	
}