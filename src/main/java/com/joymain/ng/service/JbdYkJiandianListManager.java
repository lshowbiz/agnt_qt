package com.joymain.ng.service;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.joymain.ng.model.JbdYkJiandianList;
import com.joymain.ng.util.GroupPage;
@WebService
public interface JbdYkJiandianListManager extends GenericManager<JbdYkJiandianList, Long>{
	public List getJbdYkJiandianList(Map<String, String> params, GroupPage page);
}