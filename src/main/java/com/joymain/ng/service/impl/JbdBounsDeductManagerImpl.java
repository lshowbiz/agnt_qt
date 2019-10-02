package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JbdBounsDeductDao;
import com.joymain.ng.model.JbdBounsDeduct;
import com.joymain.ng.service.JbdBounsDeductManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

@Service("jbdBounsDeductManager")
@WebService(serviceName = "JbdBounsDeductService", endpointInterface = "com.joymain.ng.service.JbdBounsDeductManager")
public class JbdBounsDeductManagerImpl extends GenericManagerImpl<JbdBounsDeduct, Long> implements JbdBounsDeductManager {
    JbdBounsDeductDao jbdBounsDeductDao;

    @Autowired
    public JbdBounsDeductManagerImpl(JbdBounsDeductDao jbdBounsDeductDao) {
        super(jbdBounsDeductDao);
        this.jbdBounsDeductDao = jbdBounsDeductDao;
    }
	
	public Pager<JbdBounsDeduct> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		return jbdBounsDeductDao.getPager(JbdBounsDeduct.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	public List getJbdBounsDeduct(Map map){
		return jbdBounsDeductDao.getJbdBounsDeduct(map);
	}
}