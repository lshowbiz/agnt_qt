package com.joymain.ng.dao.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JmiBlacklistDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JmiBlacklist;

@Repository("jmiBlacklistDao")
public class JmiBlacklistDaoJpa extends GenericDaoHibernate<JmiBlacklist, Long> implements JmiBlacklistDao {

    public JmiBlacklistDaoJpa() {
        super(JmiBlacklist.class);
    }
    public boolean getCheckJmiBlacklist(String papertype, String papernumber) {
		String hql = "select jmiBlacklist from JmiBlacklist jmiBlacklist where papertype='"+papertype+"' and upper(papernumber)=upper('"+papernumber+"') and status='0' ";
		List list=this.getSession().createQuery(hql).list();
		if(list.size()>0){
			return false;
		}else {
			List list1=this.jdbcTemplateBi.queryForList("select * from v_mem_paper_info where papertype='"+papertype+"' and upper(papernumber)=upper('"+papernumber+"') and item !='AJM' ");
			if(list1.size()>0){
				return false;
			}else{
				return true;
			}
		}
	}
}
