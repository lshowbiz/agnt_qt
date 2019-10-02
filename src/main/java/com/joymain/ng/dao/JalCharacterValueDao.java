package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JalCharacterValue;

/**
 * An interface that provides a data management interface to the JalCharacterValue table.
 */
public interface JalCharacterValueDao extends GenericDao<JalCharacterValue, Long> {

	List getAlCharacterValuesByCodeSQL(String characterCode);

}