package com.joymain.ng.dao;

import com.joymain.ng.model.JmiValidLevelList;

/**
 * An interface that provides a data management interface to the JmiValidLevelList table.
 */
public interface JmiValidLevelListDao extends GenericDao<JmiValidLevelList, Long> {
	
	/**
	 * 当前会员当前期别的手工定级记录
	 * @param bdPeriod
	 * @param userCode
	 * @return
	 */
	public JmiValidLevelList getValidLevel(String bdPeriod, String userCode);
}