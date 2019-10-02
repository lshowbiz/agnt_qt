package com.joymain.ng.webapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.model.AmAnnounce;
import com.joymain.ng.model.JpoUserCoupon;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JpoUserCouponManager;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.webapp.util.RequestUtil;

@Controller
@RequestMapping("/jpoUserCoupons/")
public class JpoUserCouponController {
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

	@RequestMapping(method = RequestMethod.GET)
	public Model handleRequest(@RequestParam(required = false, value = "q") String query) throws Exception {
		Model model = new ExtendedModelMap();
		try {
			model.addAttribute(jpoUserCouponManager.search(query, JpoUserCoupon.class));
		} catch (SearchException se) {
			model.addAttribute("searchError", se.getMessage());
			model.addAttribute(jpoUserCouponManager.getAll());
		}
		return model;
	}

	@RequestMapping(value = "/getMemberForCoupnPage", method = RequestMethod.GET)
	public ModelAndView getJpoUserCouponPage(HttpServletRequest request, HttpServletResponse response) throws Exception {

		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String userId = request.getParameter("userId");
		String token = request.getParameter("token");

		Map<String, String> paramsMap = new HashMap<String, String>();

		// 传参分页数据
		String status = request.getParameter("status");
			if (StringUtils.isEmpty(status)) {
				status = "";
			}
		paramsMap.put("status", status);
		paramsMap.put("userCode", defSysUser.getUserCode());

		List jpoUserCouponToSql = jpoUserCouponManager.getJpoUserCouponToSql(paramsMap);
		request.setAttribute("jpoUserCouponToSql", jpoUserCouponToSql);
		return new ModelAndView("jpoUserCouponsList");

	}

}
