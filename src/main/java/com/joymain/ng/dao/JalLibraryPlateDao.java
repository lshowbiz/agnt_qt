package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JalLibraryPlate;

/**
 * An interface that provides a data management interface to the JalLibraryPlate table.
 */
public interface JalLibraryPlateDao extends GenericDao<JalLibraryPlate, Long> {

	public List getJalLibraryPlatesOrderByIndex();
}