package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JpoCheckedFailedDao;
import com.joymain.ng.model.JpoCheckedFailed;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.service.JpoCheckedFailedManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jpoCheckedFailedManager")
@WebService(serviceName = "JpoCheckedFailedService", endpointInterface = "com.joymain.ng.service.JpoCheckedFailedManager")
public class JpoCheckedFailedManagerImpl extends GenericManagerImpl<JpoCheckedFailed, Long> implements JpoCheckedFailedManager {
    JpoCheckedFailedDao jpoCheckedFailedDao;

    @Autowired
    public JpoCheckedFailedManagerImpl(JpoCheckedFailedDao jpoCheckedFailedDao) {
        super(jpoCheckedFailedDao);
        this.jpoCheckedFailedDao = jpoCheckedFailedDao;
    }
	
	public Pager<JpoCheckedFailed> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jpoCheckedFailedDao.getPager(JpoCheckedFailed.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	
	public JpoCheckedFailed getByOrderNo(JpoMemberOrder jpoMemberOrder){
		return jpoCheckedFailedDao.getByOrderNo(jpoMemberOrder);
	}

    @Override
    public Integer deleteJpoCheckedFaiiled(String moId)
    {
        return jpoCheckedFailedDao.deleteJpoCheckedFaiiled(moId);
    }
}