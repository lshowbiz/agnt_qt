package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JalFileLibararyDao;
import com.joymain.ng.model.JalFileLibarary;
import com.joymain.ng.service.JalFileLibararyManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jalFileLibararyManager")
@WebService(serviceName = "JalFileLibararyService", endpointInterface = "com.joymain.ng.service.JalFileLibararyManager")
public class JalFileLibararyManagerImpl extends GenericManagerImpl<JalFileLibarary, Long> implements JalFileLibararyManager {
    JalFileLibararyDao jalFileLibararyDao;

    @Autowired
    public JalFileLibararyManagerImpl(JalFileLibararyDao jalFileLibararyDao) {
        super(jalFileLibararyDao);
        this.jalFileLibararyDao = jalFileLibararyDao;
    }
	
	public Pager<JalFileLibarary> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jalFileLibararyDao.getPager(JalFileLibarary.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	
	public List getFileSearchType(){
		return jalFileLibararyDao.getFileSearchType();
	}

	@Override
	public List<JalFileLibarary> getJalFileLibararyListByConditions(String typeId){
		// TODO Auto-generated method stub
		return jalFileLibararyDao.getJalFileLibararyListByConditions(typeId);
	}
}