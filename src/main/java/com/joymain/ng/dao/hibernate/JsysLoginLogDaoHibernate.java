package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.JsysLoginLog;
import com.joymain.ng.dao.JsysLoginLogDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jsysLoginLogDao")
public class JsysLoginLogDaoHibernate extends GenericDaoHibernate<JsysLoginLog, Long> implements JsysLoginLogDao {

    public JsysLoginLogDaoHibernate() {
        super(JsysLoginLog.class);
    }
}
