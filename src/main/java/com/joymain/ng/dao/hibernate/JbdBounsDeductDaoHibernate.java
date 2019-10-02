package com.joymain.ng.dao.hibernate;

import java.util.List;
import java.util.Map;

import com.joymain.ng.model.JbdBounsDeduct;
import com.joymain.ng.dao.JbdBounsDeductDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jbdBounsDeductDao")
public class JbdBounsDeductDaoHibernate extends GenericDaoHibernate<JbdBounsDeduct, Long> implements JbdBounsDeductDao {

    public JbdBounsDeductDaoHibernate() {
        super(JbdBounsDeduct.class);
    }
    
    public List getJbdBounsDeduct(Map map){
    	return getSession().createQuery("from JbdBounsDeduct where userCode='"+map.get("userCode")+"' order by createTime desc").list();
    }
}
