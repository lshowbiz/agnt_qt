package com.joymain.ng.service;

import com.joymain.ng.model.JbdYdRebateList;
import com.joymain.ng.util.GroupPage;

import javax.jws.WebService;
import java.util.List;
import java.util.Map;

@WebService
public interface JbdYdRebateListManager extends GenericManager<JbdYdRebateList, Long>{
	public List getJbdYdRebateListsPage(Map<String, String> params, GroupPage page);
}