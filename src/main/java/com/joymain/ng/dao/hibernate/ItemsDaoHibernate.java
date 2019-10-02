package com.joymain.ng.dao.hibernate;

import com.joymain.ng.model.Items;
import com.joymain.ng.dao.ItemsDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("itemsDao")
public class ItemsDaoHibernate extends GenericDaoHibernate<Items, Long> implements ItemsDao {

    public ItemsDaoHibernate() {
        super(Items.class);
    }
}
