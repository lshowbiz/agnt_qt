package com.joymain.ng.dao.jpa;

import com.joymain.ng.model.JamMsnType;
import com.joymain.ng.dao.JamMsnTypeDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jamMsnTypeDao")
public class JamMsnTypeDaoJpa extends GenericDaoHibernate<JamMsnType, Long> implements JamMsnTypeDao {

    public JamMsnTypeDaoJpa() {
        super(JamMsnType.class);
    }
}
