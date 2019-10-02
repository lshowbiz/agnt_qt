package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JalLibraryColumn;

/**
 * An interface that provides a data management interface to the JalLibraryColumn table.
 */
public interface JalLibraryColumnDao extends GenericDao<JalLibraryColumn, Long> {

	public List getJalLibraryColumnListByPlateIndex(final String plateIndex);
}