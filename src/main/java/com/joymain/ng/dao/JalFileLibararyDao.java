package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JalFileLibarary;

/**
 * An interface that provides a data management interface to the JalFileLibarary table.
 */
public interface JalFileLibararyDao extends GenericDao<JalFileLibarary, Long> {
	
	public List getFileSearchType();
	
	public List<JalFileLibarary> getJalFileLibararyListByConditions(String typeId);
}