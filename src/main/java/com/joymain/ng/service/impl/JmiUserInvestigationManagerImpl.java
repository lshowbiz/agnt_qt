package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JmiUserInvestigationDao;
import com.joymain.ng.model.JmiUserInvestigation;
import com.joymain.ng.service.JmiUserInvestigationManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jmiUserInvestigationManager")
@WebService(serviceName = "JmiUserInvestigationService", endpointInterface = "com.joymain.ng.service.JmiUserInvestigationManager")
public class JmiUserInvestigationManagerImpl extends GenericManagerImpl<JmiUserInvestigation, Long> implements JmiUserInvestigationManager {
    JmiUserInvestigationDao jmiUserInvestigationDao;

    @Autowired
    public JmiUserInvestigationManagerImpl(JmiUserInvestigationDao jmiUserInvestigationDao) {
        super(jmiUserInvestigationDao);
        this.jmiUserInvestigationDao = jmiUserInvestigationDao;
    }
	
	public Pager<JmiUserInvestigation> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jmiUserInvestigationDao.getPager(JmiUserInvestigation.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	public JmiUserInvestigation getJmiUserInvestigationByUserCode(
			String userCode) {
		// TODO Auto-generated method stub
		return jmiUserInvestigationDao.getJmiUserInvestigationByUserCode(userCode);
	}
}