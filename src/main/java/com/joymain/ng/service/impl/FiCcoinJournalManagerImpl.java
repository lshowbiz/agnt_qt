package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FiCcoinJournalDao;
import com.joymain.ng.model.FiCcoinJournal;
import com.joymain.ng.service.FiCcoinJournalManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("fiCcoinJournalManager")
@WebService(serviceName = "FiCcoinJournalService", endpointInterface = "com.joymain.ng.service.FiCcoinJournalManager")
public class FiCcoinJournalManagerImpl extends GenericManagerImpl<FiCcoinJournal, Long> implements FiCcoinJournalManager {
    FiCcoinJournalDao fiCcoinJournalDao;

    @Autowired
    public FiCcoinJournalManagerImpl(FiCcoinJournalDao fiCcoinJournalDao) {
        super(fiCcoinJournalDao);
        this.fiCcoinJournalDao = fiCcoinJournalDao;
    }
	
	public Pager<FiCcoinJournal> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiCcoinJournalDao.getPager(FiCcoinJournal.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}