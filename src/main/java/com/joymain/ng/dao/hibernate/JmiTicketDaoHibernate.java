package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.JmiTicket;
import com.joymain.ng.dao.JmiTicketDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.springframework.stereotype.Repository;

@Repository("jmiTicketDao")
public class JmiTicketDaoHibernate extends GenericDaoHibernate<JmiTicket, Long> implements JmiTicketDao {

    public JmiTicketDaoHibernate() {
        super(JmiTicket.class);
    }
    
    public List getJmiTicketByUserCode(String userCode){
    	return this.getSession().createQuery("from JmiTicket where user_code='"+userCode+"'").list();
    }
}
