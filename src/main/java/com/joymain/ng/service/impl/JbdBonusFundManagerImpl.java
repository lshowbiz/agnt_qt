package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JbdBonusFundDao;
import com.joymain.ng.model.JbdBonusFund;
import com.joymain.ng.service.JbdBonusFundManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

@Service("jbdBonusFundManager")
@WebService(serviceName = "JbdBonusFundService", endpointInterface = "com.joymain.ng.service.JbdBonusFundManager")
public class JbdBonusFundManagerImpl extends GenericManagerImpl<JbdBonusFund, Long> implements JbdBonusFundManager {
    JbdBonusFundDao jbdBonusFundDao;

    @Autowired
    public JbdBonusFundManagerImpl(JbdBonusFundDao jbdBonusFundDao) {
        super(jbdBonusFundDao);
        this.jbdBonusFundDao = jbdBonusFundDao;
    }
	
	public Pager<JbdBonusFund> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jbdBonusFundDao.getPager(JbdBonusFund.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public JbdBonusFund getJbdBonusFundByUserCode(String userCode) {
		return jbdBonusFundDao.getJbdBonusFundByUserCode(userCode);
	}
}