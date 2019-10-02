package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.model.JmiMemberTeam;

public interface JmiMemberTeamDao extends GenericDao<JmiMemberTeam, String> {
	/**
	 * find all teams by status
	 * @param status "0" or "1"
	 * @return list
	 */
	public List<JmiMemberTeam> findAllTeamByStatus(String status);
	public JmiMemberTeam getJmiMemberTeamByUserCode(String userCode);
}
