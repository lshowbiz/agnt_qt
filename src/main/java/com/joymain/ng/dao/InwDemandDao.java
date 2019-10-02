package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.InwDemand;

/**
 * An interface that provides a data management interface to the InwDemand table.
 */
public interface InwDemandDao extends GenericDao<InwDemand, Long> {

	/**
     * 创新共赢的需求(合作共赢)的查询
     * @author gw  2013-08-13
     * @param other
     */
	List getInwDemandList(String other);

	/**
	 * 创新共赢的需求（合作共赢）的详细查询
	 * @author gw 2013-08-14
	 * @param id
	 * @return
	 */
	InwDemand getInwDemandDetail(String id);

	/**
	 * 创新共赢---通过ID获取需求表的需求名称
	 * @author gw 2013-11-08
	 * @param id
	 * @return string
	 */
	String getInwDemandById(String id);

}