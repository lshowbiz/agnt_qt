package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.PublicSchedule;

/**
 * An interface that provides a data management interface to the PublicSchedule table.
 */
public interface PublicScheduleDao extends GenericDao<PublicSchedule, Long> {

	public List getScheduleByUserCode( String today);
	
	/**
	 * Add By WuCF 20131209 
	 * 查询指定行数的数据
	 * @param today
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public List getScheduleByUserCode( String today,Integer startIndex,Integer endIndex);
}