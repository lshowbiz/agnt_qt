package com.joymain.ng.dao;

import java.util.List;
import java.util.Map;
import com.joymain.ng.model.VJbdYd399RecommendList;
import com.joymain.ng.util.GroupPage;


public interface JbdYd399RecommendListDao extends GenericDao<VJbdYd399RecommendList, Long> {

	/**
	 * 分页展示
	 * 
	 * @param userCode
	 * @param wweek
	 * @return
	 */

	public List getJbdYd399RecommendListByUserCodeWeekPage(Map<String, String> params, GroupPage page);


}