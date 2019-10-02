package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JbdSummaryListDao;
import com.joymain.ng.model.JbdSummaryList;
import com.joymain.ng.service.JbdSummaryListManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jbdSummaryListManager")
@WebService(serviceName = "JbdSummaryListService", endpointInterface = "com.joymain.ng.service.JbdSummaryListManager")
public class JbdSummaryListManagerImpl extends GenericManagerImpl<JbdSummaryList, Long> implements JbdSummaryListManager {
    JbdSummaryListDao jbdSummaryListDao;

    @Autowired
    public JbdSummaryListManagerImpl(JbdSummaryListDao jbdSummaryListDao) {
        super(jbdSummaryListDao);
        this.jbdSummaryListDao = jbdSummaryListDao;
    }
	
	public Pager<JbdSummaryList> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jbdSummaryListDao.getPager(JbdSummaryList.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}