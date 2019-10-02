package com.joymain.ng.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JmiLevelLockDao;
import com.joymain.ng.model.JmiLevelLock;

@Repository("jmiLevelLockDao")
public class JmiLevelLockDaoHibernate extends GenericDaoHibernate<JmiLevelLock, Long> implements JmiLevelLockDao {

    public JmiLevelLockDaoHibernate() {
        super(JmiLevelLock.class);
    }
    
    public JmiLevelLock getJmiLevelLock(String userCode){
    	
    	return (JmiLevelLock) this.getObjectByHqlQuery("from JmiLevelLock where  userCode='" + userCode +  "' order by id desc");
    }
}
