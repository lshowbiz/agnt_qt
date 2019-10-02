package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.FoundationItem;

/**
 * An interface that provides a data management interface to the FoundationItem table.
 */
public interface FoundationItemDao extends GenericDao<FoundationItem, Long> {

	public FoundationItem getFoundationItemByType(String type);
	
	public List<FoundationItem> getFoundationItemsByStatusIsEnable();
	
	public FoundationItem getFoundationItemByFiled1(String field); 
}