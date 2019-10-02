package com.joymain.ng.dao.jpa;

import java.util.List;

import com.joymain.ng.model.JpoShoppingCartOrderList;
import com.joymain.ng.dao.JpoShoppingCartOrderListDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jpoShoppingCartOrderListDao")
public class JpoShoppingCartOrderListDaoJpa extends GenericDaoHibernate<JpoShoppingCartOrderList, Long> implements JpoShoppingCartOrderListDao {

    public JpoShoppingCartOrderListDaoJpa() {
        super(JpoShoppingCartOrderList.class);
    }
    public List getsclList(Long scId)
    {
     String sqlQuery="select scl from JpoShoppingCartOrderList scl where scl.jpoShoppingCartOrder.scId="+scId+"";
     return this.getSession().createQuery(sqlQuery).list();
    }
 
}
