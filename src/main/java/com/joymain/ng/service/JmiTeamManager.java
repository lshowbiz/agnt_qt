package com.joymain.ng.service;


import javax.jws.WebService;
import com.joymain.ng.model.JmiMemberTeam;
import com.joymain.ng.model.JsysUser;

@WebService
public interface JmiTeamManager extends GenericManager<JmiMemberTeam, String> {
	/**
	 * 判定当前用户属于某个团队
	 * @param curUser
	 * @return team code or "root"
	 */
	public String teamStr(JsysUser user);
}