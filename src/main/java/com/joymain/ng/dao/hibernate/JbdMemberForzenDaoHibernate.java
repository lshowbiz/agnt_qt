package com.joymain.ng.dao.hibernate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JbdMemberFrozenDao;
import com.joymain.ng.model.JbdMemberFrozen;

@Repository("jbdMemberFrozenDao")
public class JbdMemberForzenDaoHibernate extends GenericDaoHibernate<JbdMemberFrozen, String> implements JbdMemberFrozenDao {

    public JbdMemberForzenDaoHibernate() {
        super(JbdMemberFrozen.class);
    }
    
    public List getJbdMmeberFrozens(){
    	
//    	String hql="select userCode from JbdMemberFrozen";
//		return this.getSession().createQuery(hql).list();
    	
    	String sql="select user_code from Jbd_Member_Frozen";
		List list=this.jdbcTemplate.queryForList(sql);
		
		List<String> userCodes = new ArrayList<String>();
		
		Iterator it = list.iterator();
		while(it.hasNext()){
			Map<String, String> map = (Map) it.next(); 
			String userCode = map.get("user_code");
			userCodes.add(userCode);
		}
		
		return userCodes;
    }
}
