package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiSmsNote;
import com.joymain.ng.dao.JmiSmsNoteDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jmiSmsNoteDao")
public class JmiSmsNoteDaoHibernate extends GenericDaoHibernate<JmiSmsNote, Long> implements JmiSmsNoteDao {

    public JmiSmsNoteDaoHibernate() {
        super(JmiSmsNote.class);
    }
    
    
    public JmiSmsNote getJmiSmsNoteByUserCode(String userCode){
    	String hqlQuery="from JmiSmsNote where userCode='"+userCode+"' order by createTime desc";
    	
    	
    	return (JmiSmsNote)this.getObjectByHqlQuery(hqlQuery);
    }
    public JmiSmsNote getJmiSmsNoteByUserCodeStatus(String userCode){
    	String hqlQuery="from JmiSmsNote where userCode='"+userCode+"' and status='1' order by createTime desc";
    	
    	
    	return (JmiSmsNote)this.getObjectByHqlQuery(hqlQuery);
    }
}
