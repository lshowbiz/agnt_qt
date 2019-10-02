package com.joymain.ng.dao.hibernate;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JbdSendNoteDao;
import com.joymain.ng.model.JbdSendNote;
import com.joymain.ng.util.GroupPage;

@Repository("jbdSendNoteDao")
public class JbdSendNoteDaoHibernate extends GenericDaoHibernate<JbdSendNote, Long> implements JbdSendNoteDao {

    public JbdSendNoteDaoHibernate() {
        super(JbdSendNote.class);
    }
    
    public List getJbdSendNote(String userCode){

    	String hql="from JbdSendNote where jmiMember.userCode='"+userCode+"' order by createTime desc";
    	
		return this.getSession().createQuery(hql).list();
    }
    
    public List getJbdSendNotePage(GroupPage page,String userCode){

    	String totalHql = "select count(*) from JbdSendNote where jmiMember.userCode='"+userCode+"' order by createTime desc";
    	
    	String hql="from JbdSendNote where jmiMember.userCode='"+userCode+"' order by createTime desc";
    	
//		return this.getSession().createQuery(hql).list();
		return findObjectsByHQL(totalHql,hql,page);
    }
}
