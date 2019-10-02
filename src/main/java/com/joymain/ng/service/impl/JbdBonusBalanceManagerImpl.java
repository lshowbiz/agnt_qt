package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JbdBonusBalanceDao;
import com.joymain.ng.model.JbdBonusBalance;
import com.joymain.ng.service.JbdBonusBalanceManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jbdBonusBalanceManager")
@WebService(serviceName = "JbdBonusBalanceService", endpointInterface = "com.joymain.ng.service.JbdBonusBalanceManager")
public class JbdBonusBalanceManagerImpl extends GenericManagerImpl<JbdBonusBalance, String> implements JbdBonusBalanceManager {
    JbdBonusBalanceDao jbdBonusBalanceDao;

    @Autowired
    public JbdBonusBalanceManagerImpl(JbdBonusBalanceDao jbdBonusBalanceDao) {
        super(jbdBonusBalanceDao);
        this.jbdBonusBalanceDao = jbdBonusBalanceDao;
    }
	
	public Pager<JbdBonusBalance> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jbdBonusBalanceDao.getPager(JbdBonusBalance.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}