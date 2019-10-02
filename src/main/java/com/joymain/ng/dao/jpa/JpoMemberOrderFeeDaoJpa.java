package com.joymain.ng.dao.jpa;

import com.joymain.ng.model.JpoMemberOrderFee;
import com.joymain.ng.dao.JpoMemberOrderFeeDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jpoMemberOrderFeeDao")
public class JpoMemberOrderFeeDaoJpa extends GenericDaoHibernate<JpoMemberOrderFee, Long> implements JpoMemberOrderFeeDao {

    public JpoMemberOrderFeeDaoJpa() {
        super(JpoMemberOrderFee.class);
    }
}
