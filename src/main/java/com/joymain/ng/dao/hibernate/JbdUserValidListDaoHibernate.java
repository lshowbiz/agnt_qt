package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.JbdUserValidList;
import com.joymain.ng.dao.JbdUserValidListDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jbdUserValidListDao")
public class JbdUserValidListDaoHibernate extends GenericDaoHibernate<JbdUserValidList, Long> implements JbdUserValidListDao {

    public JbdUserValidListDaoHibernate() {
        super(JbdUserValidList.class);
    }
}
