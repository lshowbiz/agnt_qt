package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JfiDepositBalanceDao;
import com.joymain.ng.model.JfiDepositBalance;
import com.joymain.ng.service.JfiDepositBalanceManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jfiDepositBalanceManager")
@WebService(serviceName = "JfiDepositBalanceService", endpointInterface = "com.joymain.ng.service.JfiDepositBalanceManager")
public class JfiDepositBalanceManagerImpl extends GenericManagerImpl<JfiDepositBalance, Long> implements JfiDepositBalanceManager {
    JfiDepositBalanceDao jfiDepositBalanceDao;

    @Autowired
    public JfiDepositBalanceManagerImpl(JfiDepositBalanceDao jfiDepositBalanceDao) {
        super(jfiDepositBalanceDao);
        this.jfiDepositBalanceDao = jfiDepositBalanceDao;
    }
	
	public Pager<JfiDepositBalance> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jfiDepositBalanceDao.getPager(JfiDepositBalance.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}