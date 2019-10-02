package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.AmNew;

/**
 * An interface that provides a data management interface to the AmNew table.
 */
public interface AmNewDao extends GenericDao<AmNew, String> {
	/**
	 * find amNew by start date and end date
	 * @param sDate
	 * @param eDate
	 * @return AmNew list
	 */
	public List<AmNew> findNewByDate(String sDate, String eDate);
}