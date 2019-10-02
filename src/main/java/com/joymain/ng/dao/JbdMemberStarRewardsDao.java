package com.joymain.ng.dao;

import java.util.List;
import java.util.Map;

import com.joymain.ng.model.JbdMemberStarRewards;

/**
 * An interface that provides a data management interface to the JbdMemberLinkCalcHist table.
 */
public interface JbdMemberStarRewardsDao extends GenericDao<JbdMemberStarRewards, Long> {

    List getJbdMemberStarRewardsPage(Map<String, String> params);

	
}