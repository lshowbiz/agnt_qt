package com.joymain.ng.webapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.navigator.menu.MenuRepository;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.joymain.ng.service.JsysResourceManager;

@Controller
@RequestMapping("/sysmenu/")
public class SysMenuController extends BaseController{

	@Autowired
	private JsysResourceManager jsysResourceManager;
	
	@RequestMapping(value="top",method = RequestMethod.GET)
	public ModelAndView showSysMenu(HttpServletRequest request,Model model)throws Exception{
		String userCode = ((UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal()).getUsername();
		List topMenus = jsysResourceManager.getTopMenuByUserCode(userCode);
		model.addAttribute("topMenus",topMenus );
		/*
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		userCode = userDetails.getUsername();
		MenuRepository defaultRepository =(MenuRepository) request.getSession().getServletContext().getAttribute(MenuRepository.MENU_REPOSITORY_KEY);
		MenuRepository repository=jsysResourceManager.getTopMenuByUserCode(userCode, defaultRepository.getDisplayers());
		
		model.addAttribute("repository",repository );*/
		
		
		return new ModelAndView("topmenus");
	}
	@RequestMapping(value="sub",method = RequestMethod.GET)
	public ModelAndView subMenu(HttpServletRequest request,Model model)throws Exception{
		String userCode = request.getParameter("userCode");
		String parentId = request.getParameter("parentId");
		if(!StringUtils.isNumeric(parentId)){
			parentId = "0";
		}
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
			    .getAuthentication()
			    .getPrincipal();
		userCode = userDetails.getUsername();
		
		MenuRepository defaultRepository =(MenuRepository) request.getSession().getServletContext().getAttribute(MenuRepository.MENU_REPOSITORY_KEY);
		MenuRepository repository=jsysResourceManager.getSubMenuByUserCode(userCode,new Long(parentId), defaultRepository.getDisplayers());
		
		model.addAttribute("repository",repository );
		return new ModelAndView("subMenus");
	}
	
}
