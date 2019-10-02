package com.joymain.ng.dao;

import java.util.List;
import java.util.Map;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JmiSubStore;

/**
 * An interface that provides a data management interface to the JmiSubStore table.
 */
public interface JmiSubStoreDao extends GenericDao<JmiSubStore, Long> {
	
	 public JmiSubStore getJmiSubStoreByUserCode(String userCode);
 
    public List getJmiSubStoreList(Map map);
}