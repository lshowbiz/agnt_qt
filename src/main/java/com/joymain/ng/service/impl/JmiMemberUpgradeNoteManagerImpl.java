package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JmiMemberUpgradeNoteDao;
import com.joymain.ng.model.JmiMemberUpgradeNote;
import com.joymain.ng.service.JmiMemberUpgradeNoteManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jmiMemberUpgradeNoteManager")
@WebService(serviceName = "JmiMemberUpgradeNoteService", endpointInterface = "com.joymain.ng.service.JmiMemberUpgradeNoteManager")
public class JmiMemberUpgradeNoteManagerImpl extends GenericManagerImpl<JmiMemberUpgradeNote, Long> implements JmiMemberUpgradeNoteManager {
    JmiMemberUpgradeNoteDao jmiMemberUpgradeNoteDao;

    @Autowired
    public JmiMemberUpgradeNoteManagerImpl(JmiMemberUpgradeNoteDao jmiMemberUpgradeNoteDao) {
        super(jmiMemberUpgradeNoteDao);
        this.jmiMemberUpgradeNoteDao = jmiMemberUpgradeNoteDao;
    }
	
	public Pager<JmiMemberUpgradeNote> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jmiMemberUpgradeNoteDao.getPager(JmiMemberUpgradeNote.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}