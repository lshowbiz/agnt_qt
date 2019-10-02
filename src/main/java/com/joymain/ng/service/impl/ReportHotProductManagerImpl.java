package com.joymain.ng.service.impl;

import com.joymain.ng.dao.ReportHotProductDao;
import com.joymain.ng.model.ReportHotProduct;
import com.joymain.ng.service.ReportHotProductManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

@Service("reportHotProductManager")
@WebService(serviceName = "ReportHotProductService", endpointInterface = "com.joymain.ng.service.ReportHotProductManager")
public class ReportHotProductManagerImpl extends GenericManagerImpl<ReportHotProduct, Long> implements ReportHotProductManager {
    ReportHotProductDao reportHotProductDao;

    @Autowired
    public ReportHotProductManagerImpl(ReportHotProductDao reportHotProductDao) {
        super(reportHotProductDao);
        this.reportHotProductDao = reportHotProductDao;
    }
	
	public Pager<ReportHotProduct> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return reportHotProductDao.getPager(ReportHotProduct.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public List getHotProductReport(String jperiod, String province, String city) {
		
		return reportHotProductDao.getHotProductReport(jperiod, province, city);
	}
}