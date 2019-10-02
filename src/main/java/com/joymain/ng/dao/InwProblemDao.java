package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.InwProblem;

/**
 * An interface that provides a data management interface to the InwProblem table.
 */
public interface InwProblemDao extends GenericDao<InwProblem, Long> {

	/**
	 * 创新共赢的共赢帮助的详细查询
	 * @author gw 2013-08-30
	 * @param species
	 * @return  List
	 */
	List getInwProblemDetail(String species);

}