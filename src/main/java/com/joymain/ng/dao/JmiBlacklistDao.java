package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JmiBlacklist;

/**
 * An interface that provides a data management interface to the JmiBlacklist table.
 */
public interface JmiBlacklistDao extends GenericDao<JmiBlacklist, Long> {

    public boolean getCheckJmiBlacklist(String papertype, String papernumber);
}