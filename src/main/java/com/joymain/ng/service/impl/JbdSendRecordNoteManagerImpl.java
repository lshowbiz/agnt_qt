package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JbdSendRecordNoteDao;
import com.joymain.ng.model.JbdSendRecordNote;
import com.joymain.ng.service.JbdSendRecordNoteManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

@Service("jbdSendRecordNoteManager")
@WebService(serviceName = "JbdSendRecordNoteService", endpointInterface = "com.joymain.ng.service.JbdSendRecordNoteManager")
public class JbdSendRecordNoteManagerImpl extends GenericManagerImpl<JbdSendRecordNote, Long> implements JbdSendRecordNoteManager {
    JbdSendRecordNoteDao jbdSendRecordNoteDao;

    @Autowired
    public JbdSendRecordNoteManagerImpl(JbdSendRecordNoteDao jbdSendRecordNoteDao) {
        super(jbdSendRecordNoteDao);
        this.jbdSendRecordNoteDao = jbdSendRecordNoteDao;
    }
	
	public Pager<JbdSendRecordNote> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jbdSendRecordNoteDao.getPager(JbdSendRecordNote.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public JbdSendRecordNote getJbdSendRecordNoteForUpdate(Long id) {
		// TODO Auto-generated method stub
		return jbdSendRecordNoteDao.getJbdSendRecordNoteForUpdate(id);
	}
}