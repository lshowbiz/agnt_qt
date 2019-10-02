package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JprRefundDao;
import com.joymain.ng.model.JprRefund;
import com.joymain.ng.service.JprRefundManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jprRefundManager")
@WebService(serviceName = "JprRefundService", endpointInterface = "com.joymain.ng.service.JprRefundManager")
public class JprRefundManagerImpl extends GenericManagerImpl<JprRefund, String> implements JprRefundManager {
    JprRefundDao jprRefundDao;

    @Autowired
    public JprRefundManagerImpl(JprRefundDao jprRefundDao) {
        super(jprRefundDao);
        this.jprRefundDao = jprRefundDao;
    }
	
	public Pager<JprRefund> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jprRefundDao.getPager(JprRefund.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	public List getJprRefundsListPage(GroupPage page, String roNo,
			String memberOrderNo, String userCode, String timeBegin,
			String timeEnd) {
		return jprRefundDao.getJprRefundsListPage(page,roNo,memberOrderNo,userCode,timeBegin,timeEnd);
	}

	@Override
	public JprRefund getJprRefunds(String roNo) {
		return jprRefundDao.getJprRefunds(roNo);
	}
	
	
}