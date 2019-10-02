package com.joymain.ng.dao.hibernate;

import com.joymain.ng.dao.JmiYdSendListDao;
import com.joymain.ng.model.JmiYdSendList;
import org.springframework.stereotype.Repository;

@Repository("jmiYdSendListDao")
public class JmiYdSendListDaoHibernate extends GenericDaoHibernate<JmiYdSendList, Long> implements JmiYdSendListDao {

    public JmiYdSendListDaoHibernate() {
        super(JmiYdSendList.class);
    }
    
    
}
