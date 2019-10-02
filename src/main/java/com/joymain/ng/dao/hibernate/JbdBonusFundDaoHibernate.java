package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.JbdBonusFund;
import com.joymain.ng.dao.JbdBonusFundDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jbdBonusFundDao")
public class JbdBonusFundDaoHibernate extends GenericDaoHibernate<JbdBonusFund, Long> implements JbdBonusFundDao {

    public JbdBonusFundDaoHibernate() {
        super(JbdBonusFund.class);
    }
    
    public JbdBonusFund getJbdBonusFundByUserCode(String userCode){
    	
    	
    	JbdBonusFund jbdBonusFund=(JbdBonusFund) this.getObjectByHqlQuery("from JbdBonusFund where userCode='"+userCode+"'");
    	
    	
    	return jbdBonusFund;
    }
}
