package com.joymain.ng.service;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.joymain.ng.model.VJbdYd399RecommendList;
import com.joymain.ng.util.GroupPage;
@WebService
public interface JbdYd399RecommendListManager extends GenericManager<VJbdYd399RecommendList, Long> {
    
	/**
	 * 分页展示
	 * @param userCode
	 * @param wweek
	 * @return
	 */
	public List getJbdYd399RecommendListByUserCodeWeekPage(Map<String, String> params, GroupPage page);
	
	
}