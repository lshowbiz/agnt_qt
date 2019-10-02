package com.joymain.ng.dao;

import java.util.List;
import java.util.Map;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JmiStore;

/**
 * An interface that provides a data management interface to the JmiStore table.
 */
public interface JmiStoreDao extends GenericDao<JmiStore, Long> {

    public JmiStore getJmiStoreByUserCode(String userCode);
    public List getJmiStoreList(Map map);
}