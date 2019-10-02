package com.joymain.ng.dao.jpa;

import com.joymain.ng.model.JbdSummaryList;
import com.joymain.ng.dao.JbdSummaryListDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jbdSummaryListDao")
public class JbdSummaryListDaoJpa extends GenericDaoHibernate<JbdSummaryList, Long> implements JbdSummaryListDao {

    public JbdSummaryListDaoJpa() {
        super(JbdSummaryList.class);
    }
}
