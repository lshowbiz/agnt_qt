package com.joymain.ng.dao.jpa;

import com.joymain.ng.model.JalCharacterCoding;
import com.joymain.ng.dao.JalCharacterCodingDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jalCharacterCodingDao")
public class JalCharacterCodingDaoJpa extends GenericDaoHibernate<JalCharacterCoding, Long> implements JalCharacterCodingDao {

    public JalCharacterCodingDaoJpa() {
        super(JalCharacterCoding.class);
    }
}
