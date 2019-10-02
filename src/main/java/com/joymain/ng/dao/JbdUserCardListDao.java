package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JbdUserCardList;

/**
 * An interface that provides a data management interface to the JbdUserCardList table.
 */
public interface JbdUserCardListDao extends GenericDao<JbdUserCardList, Long> {

	public JbdUserCardList getJbdUserCardListByUserCodeAndWeek(String userCode, Integer wweek);
	public JbdUserCardList getPreviousJbdUserCardList(String userCode,Integer wweek);

	public List getJbdUserCardListByRange(Integer sweek, Integer eweek,String userCode);

	public List getJbdUserCardListByUserCode(String userCode) ;

	public List getNextJbdUserCardList(String userCode,Integer wweek);
}