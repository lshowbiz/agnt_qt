package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JamMsnDetailDao;
import com.joymain.ng.model.JamMsnDetail;
import com.joymain.ng.service.JamMsnDetailManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jamMsnDetailManager")
@WebService(serviceName = "JamMsnDetailService", endpointInterface = "com.joymain.ng.service.JamMsnDetailManager")
public class JamMsnDetailManagerImpl extends GenericManagerImpl<JamMsnDetail, Long> implements JamMsnDetailManager {
    JamMsnDetailDao jamMsnDetailDao;

    @Autowired
    public JamMsnDetailManagerImpl(JamMsnDetailDao jamMsnDetailDao) {
        super(jamMsnDetailDao);
        this.jamMsnDetailDao = jamMsnDetailDao;
    }
	
	public Pager<JamMsnDetail> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jamMsnDetailDao.getPager(JamMsnDetail.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}