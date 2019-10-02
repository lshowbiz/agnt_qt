package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JmiUserInvestigation;

/**
 * An interface that provides a data management interface to the JmiUserInvestigation table.
 */
public interface JmiUserInvestigationDao extends GenericDao<JmiUserInvestigation, Long> {

	/**
	 * @Description  根据会员编号查询调查文件答案对象
	 * @author houxyu
	 * @date 2016-4-28
	 * @param userCode
	 * @return
	 */
	public JmiUserInvestigation getJmiUserInvestigationByUserCode(String userCode);
}