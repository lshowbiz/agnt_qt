package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JfiBankbookTempDao;
import com.joymain.ng.model.JfiBankbookTemp;
import com.joymain.ng.service.JfiBankbookTempManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jfiBankbookTempManager")
@WebService(serviceName = "JfiBankbookTempService", endpointInterface = "com.joymain.ng.service.JfiBankbookTempManager")
public class JfiBankbookTempManagerImpl extends GenericManagerImpl<JfiBankbookTemp, Long> implements JfiBankbookTempManager {
    JfiBankbookTempDao jfiBankbookTempDao;

    @Autowired
    public JfiBankbookTempManagerImpl(JfiBankbookTempDao jfiBankbookTempDao) {
        super(jfiBankbookTempDao);
        this.jfiBankbookTempDao = jfiBankbookTempDao;
    }
	
	public Pager<JfiBankbookTemp> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jfiBankbookTempDao.getPager(JfiBankbookTemp.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}