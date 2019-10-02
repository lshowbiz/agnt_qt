package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JfiPosImportDao;
import com.joymain.ng.model.JfiPosImport;
import com.joymain.ng.service.JfiPosImportManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jfiPosImportManager")
@WebService(serviceName = "JfiPosImportService", endpointInterface = "com.joymain.ng.service.JfiPosImportManager")
public class JfiPosImportManagerImpl extends GenericManagerImpl<JfiPosImport, Long> implements JfiPosImportManager {
    JfiPosImportDao jfiPosImportDao;

    @Autowired
    public JfiPosImportManagerImpl(JfiPosImportDao jfiPosImportDao) {
        super(jfiPosImportDao);
        this.jfiPosImportDao = jfiPosImportDao;
    }
	
	public Pager<JfiPosImport> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jfiPosImportDao.getPager(JfiPosImport.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public List getJfiPosImports(JfiPosImport jfiPosImport) {
		// TODO Auto-generated method stub
		return jfiPosImportDao.getJfiPosImports(jfiPosImport);
	}
}