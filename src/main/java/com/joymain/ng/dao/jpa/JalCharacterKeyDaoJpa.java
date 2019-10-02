package com.joymain.ng.dao.jpa;

import com.joymain.ng.model.JalCharacterKey;
import com.joymain.ng.dao.JalCharacterKeyDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jalCharacterKeyDao")
public class JalCharacterKeyDaoJpa extends GenericDaoHibernate<JalCharacterKey, Long> implements JalCharacterKeyDao {

    public JalCharacterKeyDaoJpa() {
        super(JalCharacterKey.class);
    }
}
