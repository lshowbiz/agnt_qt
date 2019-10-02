package com.joymain.ng.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JmiValidLevelListDao;
import com.joymain.ng.model.JmiValidLevelList;

@Repository("jmiValidLevelListDao")
public class JmiValidLevelListDaoHibernate extends GenericDaoHibernate<JmiValidLevelList, Long> implements JmiValidLevelListDao {

    public JmiValidLevelListDaoHibernate() {
        super(JmiValidLevelList.class);
    }
    
    public JmiValidLevelList getValidLevel(String bdPeriod, String userCode){
    	
			return (JmiValidLevelList) this.getObjectByHqlQuery("from JmiValidLevelList where WWeek=" + bdPeriod
		        + " and userCode='" + userCode +  "' order by id desc");
    }
}
