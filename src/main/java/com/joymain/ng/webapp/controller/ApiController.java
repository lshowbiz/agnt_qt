package com.joymain.ng.webapp.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.joymain.ng.Constants;
import com.joymain.ng.model.JsysLoginLog;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JmiTeamManager;
import com.joymain.ng.service.JsysLoginLogManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.ListUtil;
import com.joymain.ng.util.StringUtils;
import com.joymain.ng.webapp.util.RequestUtil;

@Controller
@RequestMapping("/mobileLogin")
public class ApiController {
	private JsysUserManager jsysUserManager;

	@Autowired
	public void setJsysLoginLogManager(JsysLoginLogManager jsysLoginLogManager) {
		this.jsysLoginLogManager = jsysLoginLogManager;
	}

	private JsysLoginLogManager jsysLoginLogManager;

	@Autowired
	private JmiTeamManager jmiTeamManager;

	public void setJmiTeamManager(JmiTeamManager jmiTeamManager) {
		this.jmiTeamManager = jmiTeamManager;
	}

	private final String ENCRYPT_PASSWORD = "whopojiewhosbif4#$%^&Ujnds7r";

	@Autowired
	public void setJsysUserManager(JsysUserManager jsysUserManager) {
		this.jsysUserManager = jsysUserManager;
	}

	@RequestMapping(value = "api/gettoken/{id}/{pass}", method = RequestMethod.GET)
	@ResponseBody
	public Map login(@PathVariable(value = "id") String userCode,
			@PathVariable(value = "pass") String pwd, HttpServletRequest request)
			throws Exception {
		String agent = request.getHeader("User-Agent");
		Map retMap = Maps.newHashMap();
		String loginFlag = Constants.sysConfigMap.get("CN").get(
				"open.to.mobile");
		JsysUser user = jsysUserManager.getUserByPwd(userCode.toUpperCase(),
				pwd);
		if (("1".equals(loginFlag)) && (user != null)) {
			StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
			encryptor.setPassword(ENCRYPT_PASSWORD);
			String token = StringUtils.toMD5(user.getUserCode() + "sb"
					+ user.getUserName() + "sb" + user.getPassword());

			retMap.put("userId", user.getUserCode());
			retMap.put("userName", user.getUserName());
			retMap.put("companyCode", user.getCompanyCode());
			retMap.put("freezeStatus", user.getJmiMember().getFreezeStatus());
			retMap.put("cardType", user.getJmiMember().getCardType());
			retMap.put("token", token);
			String teamType = jmiTeamManager.teamStr(user);
			retMap.put("teamType", teamType);
			String imageUrl = ListUtil.getListValue(user
					.getDefCharacterCoding(), "jpmproductsaleimage.url", "1")
					+ "productNew/";
			retMap.put("imageUrl", imageUrl);
			user.setToken(token);
			try {
				jsysUserManager.save(user);

				JsysLoginLog sysLoginLog = new JsysLoginLog();
				sysLoginLog.setUserCode(user.getUserCode());
				String ipAddress = RequestUtil.getIpAddr(request);
				sysLoginLog.setIpAddr(ipAddress);
				sysLoginLog.setOperaterCode(user.getUserCode());
				sysLoginLog.setOperateTime(new Date());
				if (agent.indexOf("iPhone") != -1&&agent.indexOf("iPad") != -1) {
					sysLoginLog.setOperationType("iPad");
				} else if (agent.indexOf("iPhone") != -1) {
					sysLoginLog.setOperationType("iOS");
				} else if (agent.indexOf("Android") != -1) {
					sysLoginLog.setOperationType("az");
				} else {
					sysLoginLog.setOperationType("other");
				}
				sysLoginLog.setFromDevice(agent);
				jsysLoginLogManager.save(sysLoginLog);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (!"1".equals(loginFlag)) {
			retMap.put("autherror", "2");
		} else {
			retMap.put("autherror", "1");
		}

		return retMap;
	}

	@RequestMapping(value = "api/getmu", method = RequestMethod.GET)
	@ResponseBody
	public List getMoileUsers(String userCode, String token, int pagenum,
			int pageSize, HttpServletRequest request) throws Exception {
		return jsysUserManager.getMobileJsysUser(pagenum, pageSize);
	}

	@RequestMapping(value = "api/getmusum", method = RequestMethod.GET)
	@ResponseBody
	public int getMoileUserSum(String userCode, String token,
			HttpServletRequest request) throws Exception {
		return jsysUserManager.getMobileJsysUserSum();
	}

	@RequestMapping(value = "api/getmloggroup", method = RequestMethod.GET)
	@ResponseBody
	public List getMLogGroup(String userCode, String token,
			HttpServletRequest request) throws Exception {
		return jsysUserManager.getMLogGroup();
	}

	public static void main(String[] args) {
		// StandardPBEStringEncryptor encryptor = new
		// StandardPBEStringEncryptor();
		// String ENCRYPT_PASSWORD = "sbif4#$%^&Ujnds7r";
		// encryptor.setPassword(ENCRYPT_PASSWORD);
		// System.out.println("111"+encryptor.encrypt("CN99998996")+"22222");
		System.out.println(StringUtils
				.toMD5("CN94220122sb周维维sb202cb962ac59075b964b07152d234b70"));
	}

}
