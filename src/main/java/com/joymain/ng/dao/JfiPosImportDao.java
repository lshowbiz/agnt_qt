package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JfiPosImport;

/**
 * An interface that provides a data management interface to the JfiPosImport table.
 */
public interface JfiPosImportDao extends GenericDao<JfiPosImport, Long> {

	public List getJfiPosImports(JfiPosImport jfiPosImport);
}