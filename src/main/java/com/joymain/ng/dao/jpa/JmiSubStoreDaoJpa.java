package com.joymain.ng.dao.jpa;

import java.util.List;
import java.util.Map;

import com.joymain.ng.model.JmiSubStore;
import com.joymain.ng.dao.JmiSubStoreDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jmiSubStoreDao")
public class JmiSubStoreDaoJpa extends GenericDaoHibernate<JmiSubStore, Long> implements JmiSubStoreDao {

    public JmiSubStoreDaoJpa() {
        super(JmiSubStore.class);
    }
    public JmiSubStore getJmiSubStoreByUserCode(String userCode){
    	return (JmiSubStore) this.getObjectByHqlQuery("from JmiSubStore where jmiMember.userCode='"+userCode+"'");
    }
    public List getJmiSubStoreList(Map map){
    	
    	String hql="from JmiSubStore where 1=1";
    	Object jmiMemberUserCode=map.get("jmiMember.userCode");
		if(jmiMemberUserCode!=null){
			hql+=" and jmiMember.userCode ='"+jmiMemberUserCode+"'";
		}
    	return this.getSession().createQuery(hql).list();
    	
    }
}
