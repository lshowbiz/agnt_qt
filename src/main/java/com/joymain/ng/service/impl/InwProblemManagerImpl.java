package com.joymain.ng.service.impl;

import com.joymain.ng.dao.InwProblemDao;
import com.joymain.ng.model.InwProblem;
import com.joymain.ng.service.InwProblemManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("inwProblemManager")
@WebService(serviceName = "InwProblemService", endpointInterface = "com.joymain.ng.service.InwProblemManager")
public class InwProblemManagerImpl extends GenericManagerImpl<InwProblem, Long> implements InwProblemManager {
    InwProblemDao inwProblemDao;

    @Autowired
    public InwProblemManagerImpl(InwProblemDao inwProblemDao) {
        super(inwProblemDao);
        this.inwProblemDao = inwProblemDao;
    }
	
	public Pager<InwProblem> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		return inwProblemDao.getPager(InwProblem.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	/**
	 * 创新共赢的共赢帮助的详细查询
	 * @author gw 2013-08-30
	 * @param species
	 * @return  List
	 */
	public List getInwProblemDetail(String species) {
		return inwProblemDao.getInwProblemDetail(species);
	}
}