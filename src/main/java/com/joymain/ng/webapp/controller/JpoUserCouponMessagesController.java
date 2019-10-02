package com.joymain.ng.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JpoUserCouponManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.StringUtil;

@Controller
public class JpoUserCouponMessagesController {

	private JpoUserCouponManager jpoUserCouponManager;

	private JsysUserManager jsysUserManager;

	@Autowired
	public void setJpoUserCouponManager(JpoUserCouponManager jpoUserCouponManager) {
		this.jpoUserCouponManager = jpoUserCouponManager;
	}

	@Autowired
	public void setJsysUserManager(JsysUserManager jsysUserManager) {
		this.jsysUserManager = jsysUserManager;
	}

	@RequestMapping(value = "usercoupon/api/getJpoUserCouponPageToApp")
	@ResponseBody
	public Map<String, Object> getJpoUserCouponPageToApp(HttpServletRequest request) {

		String userId = request.getParameter("userId");
		String token = request.getParameter("token");
		// String pageSize=request.getParameter("pageSize");

		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, String> paramsMap = new HashMap<String, String>();

		// 移动端登录验证
		JsysUser user = jsysUserManager.getUserByToken(userId, token);
		if (user == null) {
			resultMap.put("message", "用户登录失败");
			resultMap.put("returnCode", "-1");
			return resultMap;
		} else {
			// 传参分页数据
			String status = request.getParameter("status");
			if (StringUtils.isEmpty(status)) {
				status="";
			}
			paramsMap.put("status", status);
			paramsMap.put("userCode", user.getUserCode());
			
			List jpoUserCouponToSql = jpoUserCouponManager.getJpoUserCouponToSql(paramsMap);

			// 分页单位：固定写法
			//Integer pageSize = StringUtil.dealPageSize(request);
			//GroupPage page = new GroupPage("", "usercoupon/api/getJpoUserCouponPageToApp?userId=" + userId + "&token="
			//		+ token + "&pageSize=" + pageSize, pageSize, request);
			//List jpoUserCouponList = jpoUserCouponManager.getJpoUserCouponPageToSql(paramsMap, page);
			
			resultMap.put("message", "查询成功");
			resultMap.put("returnCode", "1");
			resultMap.put("dataList", jpoUserCouponToSql);
			//resultMap.put("pageDataList", jpoUserCouponList);
			//resultMap.put("pageCount", page.getPagecount());//总记录数
			//resultMap.put("pageNum", page.getPagenum());//当前页码
			//resultMap.put("pageSize", page.getPagesize());//每页显示记录数
			//resultMap.put("pages", page.getPages());//总页数
		
			return resultMap;
		}

	}

}
