package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FiBillAccountRelationDao;
import com.joymain.ng.model.FiBillAccountRelation;
import com.joymain.ng.service.FiBillAccountRelationManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("fiBillAccountRelationManager")
@WebService(serviceName = "FiBillAccountRelationService", endpointInterface = "com.joymain.ng.service.FiBillAccountRelationManager")
public class FiBillAccountRelationManagerImpl extends GenericManagerImpl<FiBillAccountRelation, Long> implements FiBillAccountRelationManager {
    FiBillAccountRelationDao fiBillAccountRelationDao;

    @Autowired
    public FiBillAccountRelationManagerImpl(FiBillAccountRelationDao fiBillAccountRelationDao) {
        super(fiBillAccountRelationDao);
        this.fiBillAccountRelationDao = fiBillAccountRelationDao;
    }
	
	public Pager<FiBillAccountRelation> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiBillAccountRelationDao.getPager(FiBillAccountRelation.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}