package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.FoundationItem;
import com.joymain.ng.dao.FoundationItemDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.springframework.stereotype.Repository;

@Repository("foundationItemDao")
public class FoundationItemDaoHibernate extends GenericDaoHibernate<FoundationItem, Long> implements FoundationItemDao {

    public FoundationItemDaoHibernate() {
        super(FoundationItem.class);
    }
    
    /**
     * 根据项目类型查询公益项目
     */
	public FoundationItem getFoundationItemByType(String type) {

		String hqlQuery = "from FoundationItem foundationItem where foundationItem.type='"+type+"'";
		return (FoundationItem)this.getObjectByHqlQuery(hqlQuery);
	}

	/**
	 * 查询启用状态下的公益产品
	 */
	@Override
	public List<FoundationItem> getFoundationItemsByStatusIsEnable() {
		
		String hqlQuery = "from FoundationItem foundationItem where foundationItem.status='0' order by foundationItem.type,foundationItem.field2 desc";
		return this.getSession().createQuery(hqlQuery).list();
	}

	@Override
	public FoundationItem getFoundationItemByFiled1(String field) {
		
		String hqlQuery = "from FoundationItem foundationItem where foundationItem.field1='"+field+"'";
		return (FoundationItem)this.getObjectByHqlQuery(hqlQuery); 
	}
}
