package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.JpoProductNumLimit;
import com.joymain.ng.model.JpoShoppingCartOrder;
import com.joymain.ng.dao.JpoProductNumLimitDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

@Repository("jpoProductNumLimitDao")
public class JpoProductNumLimitDaoHibernate extends GenericDaoHibernate<JpoProductNumLimit, Long> implements JpoProductNumLimitDao {

    public JpoProductNumLimitDaoHibernate() {
        super(JpoProductNumLimit.class);
    }
    
    public JpoProductNumLimit getNum(String productNo){
    	
    	String hql = " from JpoProductNumLimit  where productNo = '"+ productNo +"'";
    	List<JpoProductNumLimit> list = this.getSession().createQuery(hql).list();
    	if(list!=null && list.size()>=1){	    	   
	    	return (JpoProductNumLimit)list.get(0);
	    } 
    	return null;
    }
    
    public List getAllPros(){
    	return getSession().createQuery(" from JpoProductNumLimit ").list();
    }
}
