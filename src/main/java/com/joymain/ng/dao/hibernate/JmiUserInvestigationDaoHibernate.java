package com.joymain.ng.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JmiUserInvestigationDao;
import com.joymain.ng.model.JmiUserInvestigation;

@Repository("jmiUserInvestigationDao")
public class JmiUserInvestigationDaoHibernate extends GenericDaoHibernate<JmiUserInvestigation, Long> implements JmiUserInvestigationDao {

    public JmiUserInvestigationDaoHibernate() {
        super(JmiUserInvestigation.class);
    }
    
    public JmiUserInvestigation getJmiUserInvestigationByUserCode(String userCode) {
        
    	try{
			return (JmiUserInvestigation) this.getObjectByHqlQuery("from JmiUserInvestigation where usercode='"+userCode+"'");
		}catch(ClassCastException ex){
			return null;
		}
    }
}
