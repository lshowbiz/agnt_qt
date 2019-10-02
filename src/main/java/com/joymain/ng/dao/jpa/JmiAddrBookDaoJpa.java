package com.joymain.ng.dao.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JmiAddrBookDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JmiAddrBook;

@Repository("jmiAddrBookDao")
public class JmiAddrBookDaoJpa extends GenericDaoHibernate<JmiAddrBook, Long> implements JmiAddrBookDao {

    public JmiAddrBookDaoJpa() {
        super(JmiAddrBook.class);
    }
    public List getJmiAddrBookByUserCode(String userCode){
    	 
    	return this.getSession().createQuery("select a from JmiAddrBook a where a.jmiMember.userCode='"+userCode+"'").list();
    }
    	
    /**
     * 根据会员编号查询默认收货地址
     * @param userCode
     * @return
     */
    public JmiAddrBook getDefaultJmiAddrBookByUserCode(String userCode){
    	List<JmiAddrBook> addrList=this.getSession().createQuery("select a from JmiAddrBook a where  a.jmiMember.userCode='"+userCode+"' and isDefault=1 ").list();
    	if(addrList!=null&&addrList.size()>0)
		{
    		
    		return addrList.get(0);
		}else{
			return null;
		}
		
		
		
    }
    public List getJmiAddrBookByUserCodeBySql(String userCode){
    	return this.jdbcTemplate.queryForList("select * from jmi_addr_book where user_code='"+userCode+"'");
    }
}
