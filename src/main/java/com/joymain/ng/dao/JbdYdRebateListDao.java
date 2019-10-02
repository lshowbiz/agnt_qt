package com.joymain.ng.dao;

import com.joymain.ng.model.JbdYdRebateList;
import com.joymain.ng.util.GroupPage;

import java.util.List;
import java.util.Map;


public interface JbdYdRebateListDao extends GenericDao<JbdYdRebateList, Long> {
	 public List getJbdYdRebateList(Map<String, String> params, GroupPage page);
}