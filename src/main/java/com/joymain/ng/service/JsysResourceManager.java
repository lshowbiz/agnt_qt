package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JsysResource;
import com.joymain.ng.model.MenuModel;


import java.util.LinkedHashMap;
import java.util.List;
import javax.jws.WebService;

import net.sf.navigator.menu.MenuRepository;

import org.apache.commons.collections.map.LinkedMap;

@WebService
public interface JsysResourceManager extends GenericManager<JsysResource, Long> {
//	public List getGrantedAuthorityResource(String resType);
	public LinkedHashMap getGrantedAuthorityResource(String resType);
	public MenuRepository getMenuRepositoryByUserCode(String userCode,
			LinkedMap displayers);
	public MenuRepository getTopMenuByUserCode(String userCode,
			LinkedMap displayers);
	public MenuRepository getSubMenuByUserCode(String userCode,Long parentId,
			LinkedMap displayers);
	
	public List getTopMenuByUserCode(String userCode);
	public List<MenuModel> getSubMenuByUserCode(String userCode,Long parentId);
	
	public List getCommonMenu(String parmcode);
	public List getTLMenu(String resCode);
	
}