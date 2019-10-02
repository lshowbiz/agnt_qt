package com.joymain.ng.dao;

import java.util.List;
import java.util.Map;

import com.joymain.ng.model.JbdCalcDayFb;
import com.joymain.ng.util.GroupPage;


public interface JbdCalcDayFbDao extends GenericDao<JbdCalcDayFb, Long> {
	 public List getJbdCalcDayFbsPage(Map<String, String> params,GroupPage page);
}