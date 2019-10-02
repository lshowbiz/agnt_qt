package com.joymain.ng.dao.jpa;

import com.joymain.ng.model.JamMsnDetail;
import com.joymain.ng.dao.JamMsnDetailDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jamMsnDetailDao")
public class JamMsnDetailDaoJpa extends GenericDaoHibernate<JamMsnDetail, Long> implements JamMsnDetailDao {

    public JamMsnDetailDaoJpa() {
        super(JamMsnDetail.class);
    }
}
