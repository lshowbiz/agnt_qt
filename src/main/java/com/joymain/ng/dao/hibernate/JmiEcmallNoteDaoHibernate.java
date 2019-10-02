package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.JmiEcmallNote;
import com.joymain.ng.dao.JmiEcmallNoteDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jmiEcmallNoteDao")
public class JmiEcmallNoteDaoHibernate extends GenericDaoHibernate<JmiEcmallNote, Long> implements JmiEcmallNoteDao {

    public JmiEcmallNoteDaoHibernate() {
        super(JmiEcmallNote.class);
    }
}
