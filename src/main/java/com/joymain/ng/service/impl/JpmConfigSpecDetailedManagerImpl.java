package com.joymain.ng.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JpmConfigSpecDetailedDao;
import com.joymain.ng.model.JpmConfigSpecDetailed;
import com.joymain.ng.service.JpmConfigSpecDetailedManager;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@Service("jpmConfigSpecDetailedManager")
@WebService(serviceName = "JpmConfigSpecDetailedService", endpointInterface = "com.joymain.ng.service.JpmConfigSpecDetailedManager")
public class JpmConfigSpecDetailedManagerImpl extends GenericManagerImpl<JpmConfigSpecDetailed, Long> implements JpmConfigSpecDetailedManager {
    JpmConfigSpecDetailedDao jpmConfigSpecDetailedDao;

    @Autowired
    public JpmConfigSpecDetailedManagerImpl(JpmConfigSpecDetailedDao jpmConfigSpecDetailedDao) {
        super(jpmConfigSpecDetailedDao);
        this.jpmConfigSpecDetailedDao = jpmConfigSpecDetailedDao;
    }
	
	public Pager<JpmConfigSpecDetailed> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		return jpmConfigSpecDetailedDao.getPager(JpmConfigSpecDetailed.class, searchBeans, OrderBys, currentPage, pageSize);
	}

    @Override
    public JpmConfigSpecDetailed getJpmConfigSpecDetailedBySpecNo(Long specNo)
    {
        return jpmConfigSpecDetailedDao.getJpmConfigSpecDetailedBySpecNo(specNo);
    }

    @Override
    public Long getJpmConfigSpecDetailedWeightByConfigNo(Long configNo)
    {
        return jpmConfigSpecDetailedDao.getJpmConfigSpecDetailedWeightByConfigNo(configNo);
    }

    @Override
    public void delJpmConfigSpecDetailedBySpecNo(Long specNo)
    {
        jpmConfigSpecDetailedDao.delJpmConfigSpecDetailedBySpecNo(specNo);
    }

    @Override
    public List<JpmConfigSpecDetailed> getJpmConfigSpecDetailedListByConfigNo(Long configNo)
    {
        return jpmConfigSpecDetailedDao.getJpmConfigSpecDetailedListByConfigNo(configNo);
    }

    @Override
    public Long getPriceByConfigNo(String configNo)
    {
        return jpmConfigSpecDetailedDao.getPriceByConfigNo(configNo);
    }
}