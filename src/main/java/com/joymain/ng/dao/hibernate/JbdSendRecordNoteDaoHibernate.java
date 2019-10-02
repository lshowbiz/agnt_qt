package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.JbdSendRecordNote;
import com.joymain.ng.dao.JbdSendRecordNoteDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.hibernate.LockMode;
import org.springframework.stereotype.Repository;

@Repository("jbdSendRecordNoteDao")
public class JbdSendRecordNoteDaoHibernate extends GenericDaoHibernate<JbdSendRecordNote, Long> implements JbdSendRecordNoteDao {

    public JbdSendRecordNoteDaoHibernate() {
        super(JbdSendRecordNote.class);
    }
    

    public JbdSendRecordNote getJbdSendRecordNoteForUpdate( Long id) {

    	JbdSendRecordNote jbdSendRecordNote= (JbdSendRecordNote) this.getSession().get(JbdSendRecordNote.class, id, LockMode.UPGRADE);
 		
 		
         return jbdSendRecordNote;
    }
    
}
