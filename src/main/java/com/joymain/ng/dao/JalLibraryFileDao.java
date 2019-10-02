package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JalLibraryFile;

/**
 * An interface that provides a data management interface to the JalLibraryFile table.
 */
public interface JalLibraryFileDao extends GenericDao<JalLibraryFile, Long> {
	
	public List selectLibraryFilesByColumnId(final String columnId);
}