package com.joymain.ng.dao;

import java.util.List;
import java.util.Map;

import com.joymain.ng.model.JbdDayCustRecommend;
import com.joymain.ng.model.JbdDayCustRecommendOrder;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the JbdDayCustRecommend table.
 */
public interface JbdDayCustRecommendDao extends GenericDao<JbdDayCustRecommend, Long> {

/*
	public List getJbdDayCustRecommendByUserCodeWeek(
			String userCode, String wweek,GroupPage page);*/
	
	/**
	 * 分页展示
	 * @param userCode
	 * @param wweek
	 * @return
	 */

	 public List getJbdDayCustRecommendByUserCodeWeekPage(Map<String, String> params,GroupPage page);
	
	
	public List getJbdDayCustRecommendDetail(String userCode, String wweek,GroupPage page);
	public List getJbdDayCustRecommendDetail2(String userCode, String wweek,GroupPage page);

	/**
     * 奖衔查询
     * getJbdDayCustRecommendByUserCodeWeek
     *
     * @param userCode
     * @param wweek
     * @param page
     * @return
     */
   /* public List getJbdDayCustRecommendByUserCodeWeek(String userCode,
            String wweek, GroupPage page);

*/
}