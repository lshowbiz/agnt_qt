package com.joymain.ng.service;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.joymain.ng.model.JbdMemberStarRewards;
@WebService
public interface JbdMemberStarRewardsManager extends GenericManager<JbdMemberStarRewards, Long> {
    
	/**
	 * 奖衔奖励查询
	 * getJbdMemberStarRewardsPage
	 *
	 * @param params
	 * @param page
	 * @return
	 */
    public List getJbdMemberStarRewardsPage(Map<String,String> params);
    
	
}