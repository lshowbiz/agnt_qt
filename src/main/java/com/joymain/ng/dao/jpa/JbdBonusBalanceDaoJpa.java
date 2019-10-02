package com.joymain.ng.dao.jpa;

import com.joymain.ng.model.JbdBonusBalance;
import com.joymain.ng.dao.JbdBonusBalanceDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jbdBonusBalanceDao")
public class JbdBonusBalanceDaoJpa extends GenericDaoHibernate<JbdBonusBalance, String> implements JbdBonusBalanceDao {

    public JbdBonusBalanceDaoJpa() {
        super(JbdBonusBalance.class);
    }
}
