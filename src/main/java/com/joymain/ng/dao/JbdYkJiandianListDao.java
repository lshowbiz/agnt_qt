package com.joymain.ng.dao;

import java.util.List;
import java.util.Map;

import com.joymain.ng.model.JbdYkJiandianList;
import com.joymain.ng.util.GroupPage;


public interface JbdYkJiandianListDao extends GenericDao<JbdYkJiandianList, Long> {
	 public List getJbdYkJiandianList(Map<String, String> params, GroupPage page);
}