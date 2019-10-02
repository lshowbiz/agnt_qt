package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JpmSalePromoterDao;
import com.joymain.ng.model.JpmSalePromoter;
import com.joymain.ng.service.JpmSalePromoterManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jpmSalePromoterManager")
@WebService(serviceName = "JpmSalePromoterService", endpointInterface = "com.joymain.ng.service.JpmSalePromoterManager")
public class JpmSalePromoterManagerImpl extends GenericManagerImpl<JpmSalePromoter, Long> implements JpmSalePromoterManager {
    JpmSalePromoterDao jpmSalePromoterDao;

    @Autowired
    public JpmSalePromoterManagerImpl(JpmSalePromoterDao jpmSalePromoterDao) {
        super(jpmSalePromoterDao);
        this.jpmSalePromoterDao = jpmSalePromoterDao;
    }
	
	public Pager<JpmSalePromoter> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jpmSalePromoterDao.getPager(JpmSalePromoter.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	public List<JpmSalePromoter> getSaleByDate(String stime, String isActiva) {
		return jpmSalePromoterDao.getSaleByDate(stime, isActiva);
	}
}