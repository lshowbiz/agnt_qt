package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JmiEcmallNoteDao;
import com.joymain.ng.model.JmiEcmallNote;
import com.joymain.ng.service.JmiEcmallNoteManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jmiEcmallNoteManager")
@WebService(serviceName = "JmiEcmallNoteService", endpointInterface = "com.joymain.ng.service.JmiEcmallNoteManager")
public class JmiEcmallNoteManagerImpl extends GenericManagerImpl<JmiEcmallNote, Long> implements JmiEcmallNoteManager {
    JmiEcmallNoteDao jmiEcmallNoteDao;

    @Autowired
    public JmiEcmallNoteManagerImpl(JmiEcmallNoteDao jmiEcmallNoteDao) {
        super(jmiEcmallNoteDao);
        this.jmiEcmallNoteDao = jmiEcmallNoteDao;
    }
	
	public Pager<JmiEcmallNote> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jmiEcmallNoteDao.getPager(JmiEcmallNote.class, searchBeans, OrderBys, currentPage, pageSize);
	}
}