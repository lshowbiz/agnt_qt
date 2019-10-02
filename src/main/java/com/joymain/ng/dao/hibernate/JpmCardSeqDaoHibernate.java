package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.JpmCardSeq;
import com.joymain.ng.dao.JpmCardSeqDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jpmCardSeqDao")
public class JpmCardSeqDaoHibernate extends GenericDaoHibernate<JpmCardSeq, Long> implements JpmCardSeqDao {

    public JpmCardSeqDaoHibernate() {
        super(JpmCardSeq.class);
    }
}
