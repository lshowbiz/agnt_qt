package com.joymain.ng.dao.hibernate;

import java.util.HashMap;
import java.util.List;

import com.joymain.ng.model.JalFileLibarary;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.dao.JalFileLibararyDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jalFileLibararyDao")
public class JalFileLibararyDaoHibernate extends GenericDaoHibernate<JalFileLibarary, Long> implements JalFileLibararyDao {

    public JalFileLibararyDaoHibernate() {
        super(JalFileLibarary.class);
    }
    
    public List getFileSearchType(){
    	
    	String sql="select * from JSYS_LIST_VALUE where KEY_ID=(select KEY_ID from JSYS_LIST_KEY where LIST_CODE='file.type.list')";
    	
    	List list=this.jdbcTemplate.queryForList(sql);
    	return list;
    }
    
    public List<JalFileLibarary> getJalFileLibararyListByConditions(String typeId){
    	
    	String sql="from JalFileLibarary where 1=1";
    	
    	if(!StringUtil.isEmpty(typeId)){
			sql+=" and typeId='"+typeId+"'";
		}
    	
    	sql+=" order by FId desc";
    	
    	return this.getSession().createQuery(sql).list();
    }
}
