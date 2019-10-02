package com.joymain.ng.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.JmiMemberTeamDao;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiMemberTeam;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiTeamManager;
import com.joymain.ng.util.StringUtil;

@Service("jmiTeamManager")
@WebService(serviceName = "jmiTeamService", endpointInterface = "com.joymain.ng.service.JmiTeamManager")
public class JmiTeamManagerImpl extends GenericManagerImpl<JmiMemberTeam, String> 
	implements JmiTeamManager {
	
	@Autowired
	private JmiMemberTeamDao jmiMemberTeamDao;
	@Autowired
	private JmiMemberManager jmiMemberManager;

	@Autowired
	private JdbcTemplate jdbcTemplate;
	public String teamStr(JsysUser curUser){

		String userCode="";
		List list=jdbcTemplate.queryForList("select fn_get_team_no('"+curUser.getUserCode()+"') as topuser_code from dual");
		if(!list.isEmpty()){
			Map map =(Map) list.get(0);
			if(map!=null){
				userCode=map.get("topuser_code").toString();
			}
		}
		 return userCode;
	 } 
}
