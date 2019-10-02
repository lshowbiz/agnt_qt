package com.joymain.ng.service;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.joymain.ng.model.JbdDayCustRecommend;
import com.joymain.ng.model.JbdDayCustRecommendOrder;
import com.joymain.ng.util.GroupPage;
@WebService
public interface JbdDayCustRecommendManager extends GenericManager<JbdDayCustRecommend, Long> {
    
	/**
	 * 分页展示
	 * @param userCode
	 * @param wweek
	 * @return
	 */
	public List getJbdDayCustRecommendByUserCodeWeekPage(Map<String, String> params, GroupPage page);
	
	
	public List getJbdDayCustRecommendDetail(String userCode, String wweek, GroupPage page);
	public List getJbdDayCustRecommendDetail2(String userCode, String wweek, GroupPage page);
	
/*	
	*//**
	 * 奖衔查询
	 * getJbdMemberLinkCalcHistByUserCodeWeek
	 *
	 * @param userCode
	 * @param wweek
	 * @param page
	 * @return
	 *//*
    public List getJbdMemberLinkCalcHistByUserCodeWeek(String userCode,
            String wweek, GroupPage page);
    
    *//**
     * 会员奖衔查询手机端接口
     * getJbdMemberLinkCalcHistByUserCodeWeek
     *
     * @param userCode
     * @param wweek
     * @return
     *//*
    public List getJbdMemberLinkCalcHistByUserCodeWeek(
            String userCode, String wweek, String characterCode);
*/
}