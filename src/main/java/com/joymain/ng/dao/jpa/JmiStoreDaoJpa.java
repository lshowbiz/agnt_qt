package com.joymain.ng.dao.jpa;

import java.util.List;
import java.util.Map;

import com.joymain.ng.model.JmiStore;
import com.joymain.ng.dao.JmiStoreDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.springframework.stereotype.Repository;

@Repository("jmiStoreDao")
public class JmiStoreDaoJpa extends GenericDaoHibernate<JmiStore, Long> implements JmiStoreDao {

    public JmiStoreDaoJpa() {
        super(JmiStore.class);
    }
    public JmiStore getJmiStoreByUserCode(String userCode){
    	return (JmiStore) this.getObjectByHqlQuery("from JmiStore jmiStore where jmiMember.userCode='"+userCode+"'");
    }
    public List getJmiStoreList(Map map){
    	
    	String hql="from JmiStore where 1=1";
    	Object jmiMemberUserCode=map.get("jmiMember.userCode");
		if(jmiMemberUserCode!=null){
			hql+=" and jmiMember.userCode ='"+jmiMemberUserCode+"'";
		}
    	return this.getSession().createQuery(hql).list();
    	
    }
}
