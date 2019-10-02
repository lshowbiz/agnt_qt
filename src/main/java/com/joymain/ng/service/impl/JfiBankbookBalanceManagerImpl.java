package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JfiBankbookBalanceDao;
import com.joymain.ng.model.JfiBankbookBalance;
import com.joymain.ng.service.JfiBankbookBalanceManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jfiBankbookBalanceManager")
@WebService(serviceName = "JfiBankbookBalanceService", endpointInterface = "com.joymain.ng.service.JfiBankbookBalanceManager")
public class JfiBankbookBalanceManagerImpl extends GenericManagerImpl<JfiBankbookBalance, String> implements JfiBankbookBalanceManager {
	JfiBankbookBalanceDao jfiBankbookBalanceDao;

    @Autowired
    public JfiBankbookBalanceManagerImpl(JfiBankbookBalanceDao jfiBankbookBalanceDao) {
        super(jfiBankbookBalanceDao);
        this.jfiBankbookBalanceDao = jfiBankbookBalanceDao;
    }
	
	public Pager<JfiBankbookBalance> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jfiBankbookBalanceDao.getPager(JfiBankbookBalance.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public JfiBankbookBalance getJfiBankbookBalance(String userCode) {
		// TODO Auto-generated method stub
		return jfiBankbookBalanceDao.getJfiBankbookBalanceForUpdate(userCode);
	}
}