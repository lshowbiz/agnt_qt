package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FiSecurityDepositJournalDao;
import com.joymain.ng.model.FiSecurityDepositJournal;
import com.joymain.ng.service.FiSecurityDepositJournalManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("fiSecurityDepositJournalManager")
@WebService(serviceName = "FiSecurityDepositJournalService", endpointInterface = "com.joymain.ng.service.FiSecurityDepositJournalManager")
public class FiSecurityDepositJournalManagerImpl extends GenericManagerImpl<FiSecurityDepositJournal, Long> implements FiSecurityDepositJournalManager {
    FiSecurityDepositJournalDao fiSecurityDepositJournalDao;

    @Autowired
    public FiSecurityDepositJournalManagerImpl(FiSecurityDepositJournalDao fiSecurityDepositJournalDao) {
        super(fiSecurityDepositJournalDao);
        this.fiSecurityDepositJournalDao = fiSecurityDepositJournalDao;
    }
	
	public Pager<FiSecurityDepositJournal> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiSecurityDepositJournalDao.getPager(FiSecurityDepositJournal.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public List<FiSecurityDepositJournal> getFiSecurityDepositJournalsByUserCode(
			String userCode) {
		// TODO Auto-generated method stub
		return fiSecurityDepositJournalDao.getFiSecurityDepositJournalsByUserCode(userCode);
	}
}