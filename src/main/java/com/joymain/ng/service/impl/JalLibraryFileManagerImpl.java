package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JalLibraryFileDao;
import com.joymain.ng.model.JalLibraryFile;
import com.joymain.ng.service.JalLibraryFileManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jalLibraryFileManager")
@WebService(serviceName = "JalLibraryFileService", endpointInterface = "com.joymain.ng.service.JalLibraryFileManager")
public class JalLibraryFileManagerImpl extends GenericManagerImpl<JalLibraryFile, Long> implements JalLibraryFileManager {
    JalLibraryFileDao jalLibraryFileDao;

    @Autowired
    public JalLibraryFileManagerImpl(JalLibraryFileDao jalLibraryFileDao) {
        super(jalLibraryFileDao);
        this.jalLibraryFileDao = jalLibraryFileDao;
    }
	
	public Pager<JalLibraryFile> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jalLibraryFileDao.getPager(JalLibraryFile.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public List selectLibraryFilesByColumnId(String columnId) {
		// TODO Auto-generated method stub
		return jalLibraryFileDao.selectLibraryFilesByColumnId(columnId);
	}
}