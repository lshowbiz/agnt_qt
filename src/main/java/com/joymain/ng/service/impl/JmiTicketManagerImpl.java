package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JmiTicketDao;
import com.joymain.ng.model.JmiTicket;
import com.joymain.ng.service.JmiTicketManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

@Service("jmiTicketManager")
@WebService(serviceName = "JmiTicketService", endpointInterface = "com.joymain.ng.service.JmiTicketManager")
public class JmiTicketManagerImpl extends GenericManagerImpl<JmiTicket, Long> implements JmiTicketManager {
    JmiTicketDao jmiTicketDao;

    @Autowired
    public JmiTicketManagerImpl(JmiTicketDao jmiTicketDao) {
        super(jmiTicketDao);
        this.jmiTicketDao = jmiTicketDao;
    }
	
	public Pager<JmiTicket> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jmiTicketDao.getPager(JmiTicket.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public List getJmiTicketByUserCode(String userCode) {
		return jmiTicketDao.getJmiTicketByUserCode(userCode);
	}
}