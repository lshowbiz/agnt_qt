package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JpoMemberOrderFeeDao;
import com.joymain.ng.model.JpoMemberOrderFee;
import com.joymain.ng.service.JpoMemberOrderFeeManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jpoMemberOrderFeeManager")
@WebService(serviceName = "JpoMemberOrderFeeService", endpointInterface = "com.joymain.ng.service.JpoMemberOrderFeeManager")
public class JpoMemberOrderFeeManagerImpl extends GenericManagerImpl<JpoMemberOrderFee, Long> implements JpoMemberOrderFeeManager {
    JpoMemberOrderFeeDao jpoMemberOrderFeeDao;

    @Autowired
    public JpoMemberOrderFeeManagerImpl(JpoMemberOrderFeeDao jpoMemberOrderFeeDao) {
        super(jpoMemberOrderFeeDao);
        this.jpoMemberOrderFeeDao = jpoMemberOrderFeeDao;
    }
	
	public Pager<JpoMemberOrderFee> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jpoMemberOrderFeeDao.getPager(JpoMemberOrderFee.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}