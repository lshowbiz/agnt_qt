package com.joymain.ng.dao.jpa;

import com.joymain.ng.model.JsysConfigKey;
import com.joymain.ng.dao.JsysConfigKeyDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jsysConfigKeyDao")
public class JsysConfigKeyDaoJpa extends GenericDaoHibernate<JsysConfigKey, Long> implements JsysConfigKeyDao {

    public JsysConfigKeyDaoJpa() {
        super(JsysConfigKey.class);
    }
}
