package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JamMsnTypeDao;
import com.joymain.ng.model.JamMsnType;
import com.joymain.ng.service.JamMsnTypeManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jamMsnTypeManager")
@WebService(serviceName = "JamMsnTypeService", endpointInterface = "com.joymain.ng.service.JamMsnTypeManager")
public class JamMsnTypeManagerImpl extends GenericManagerImpl<JamMsnType, Long> implements JamMsnTypeManager {
    JamMsnTypeDao jamMsnTypeDao;

    @Autowired
    public JamMsnTypeManagerImpl(JamMsnTypeDao jamMsnTypeDao) {
        super(jamMsnTypeDao);
        this.jamMsnTypeDao = jamMsnTypeDao;
    }
	
	public Pager<JamMsnType> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jamMsnTypeDao.getPager(JamMsnType.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}