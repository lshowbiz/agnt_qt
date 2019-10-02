package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JpoProductNumLimit;

/**
 * An interface that provides a data management interface to the JpoProductNumLimit table.
 */
public interface JpoProductNumLimitDao extends GenericDao<JpoProductNumLimit, Long> {

	public JpoProductNumLimit getNum(String productNo);
	
	public List getAllPros();
}