package com.joymain.ng.service.impl;

import com.google.common.collect.Lists;
import com.joymain.ng.dao.JsysResourceDao;
import com.joymain.ng.model.JsysResource;
import com.joymain.ng.model.JsysRole;
import com.joymain.ng.model.MenuModel;

import com.joymain.ng.service.JsysResourceManager;
import com.joymain.ng.service.impl.GenericManagerImpl;


import net.sf.navigator.menu.MenuComponent;
import net.sf.navigator.menu.MenuRepository;

import org.apache.commons.collections.map.LinkedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;

@Service("jsysResourceManager")
@WebService(serviceName = "JsysResourceService", endpointInterface = "com.joymain.ng.service.JsysResourceManager")
public class JsysResourceManagerImpl extends
		GenericManagerImpl<JsysResource, Long> implements JsysResourceManager {
	JsysResourceDao jsysResourceDao;

	@Autowired
	public JsysResourceManagerImpl(JsysResourceDao jsysResourceDao) {
		super(jsysResourceDao);
		this.jsysResourceDao = jsysResourceDao;
	}

	public LinkedHashMap getGrantedAuthorityResource(String resType) {
		// TODO Auto-generated method stub
		LinkedHashMap resourceMap = new LinkedHashMap<String, Collection<ConfigAttribute>>();
		List<JsysResource> resources = jsysResourceDao
				.getGrantedAuthorityResource(resType);
		for (JsysResource jsysResource : resources) {

			Collection<ConfigAttribute> attrs = new ArrayList<ConfigAttribute>();
			ConfigAttribute ca = new SecurityConfig("ROLE_ADMIN");
			attrs.add(ca);
			Set<JsysRole> set = jsysResource.getJsysRoles();
			Iterator iterator = set.iterator();
			while (iterator.hasNext()) {
				ca = new SecurityConfig("ROLE_"
						+ ((JsysRole) iterator.next()).getRoleCode());
				attrs.add(ca);
			}
			resourceMap.put(jsysResource.getResUrl().replace("?1=1", ""), attrs);

		}

		return resourceMap;
	}

	@Override
	public MenuRepository getMenuRepositoryByUserCode(String userCode,
			LinkedMap displayers) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		// Map menuMap = new HashMap();
		MenuRepository repository = new MenuRepository();
		repository.setDisplayers(displayers);
		List menus = jsysResourceDao.getMenusByUserCode(userCode);
		for (int i = 0; i < menus.size(); i++) {
			MenuComponent mc = new MenuComponent();
			// mc.setName((String) ((Map)menus.get(i)).get("RES_NAME"));
			mc.setName(((BigDecimal) ((Map) menus.get(i)).get("RES_ID"))
					.toString());
			mc.setTitle((String) ((Map) menus.get(i)).get("RES_NAME"));
			mc.setUrl((String) ((Map) menus.get(i)).get("RES_URL"));
			mc.setLocation((String) ((Map) menus.get(i)).get("RES_URL"));
			/*
			 * if((Long) ((Map)menus.get(i)).get("PARENT_ID") != 0){
			 * mc.setParent(repository.getMenu(((Long)
			 * ((Map)menus.get(i)).get("PARENT_ID")).toString())); }
			 */
			repository.addMenu(mc);

		}
		return repository;
	}

	@Override
	public MenuRepository getTopMenuByUserCode(String userCode,
			LinkedMap displayers) {
		// TODO Auto-generated method stub
		MenuRepository repository = new MenuRepository();
		repository.setDisplayers(displayers);
		List menus = jsysResourceDao.getMenusByUserCodeAndLayer(userCode, "1");
		for (int i = 0; i < menus.size(); i++) {
			MenuComponent mc = new MenuComponent();
			// mc.setName((String) ((Map)menus.get(i)).get("RES_NAME"));
			mc.setName(((BigDecimal) ((Map) menus.get(i)).get("RES_ID"))
					.toString());
			mc.setTitle((String) ((Map) menus.get(i)).get("RES_NAME"));
			mc.setUrl((String) ((Map) menus.get(i)).get("RES_URL"));
			mc.setLocation((String) ((Map) menus.get(i)).get("RES_URL"));
			/*
			 * if((Long) ((Map)menus.get(i)).get("PARENT_ID") != 0){
			 * mc.setParent(repository.getMenu(((Long)
			 * ((Map)menus.get(i)).get("PARENT_ID")).toString())); }
			 */
			repository.addMenu(mc);

		}
		return repository;
	}

	@Override
	public MenuRepository getSubMenuByUserCode(String userCode, Long parentId,
			LinkedMap displayers) {
		// TODO Auto-generated method stub
		MenuRepository repository = new MenuRepository();
		repository.setDisplayers(displayers);
		List menus = jsysResourceDao.getSubMenusByUserCodeAndParent(userCode,
				parentId);

		for (int i = 0; i < menus.size(); i++) {
			MenuComponent mc = new MenuComponent();
			// mc.setName((String) ((Map)menus.get(i)).get("RES_NAME"));
			mc.setName(((Long) ((Map) menus.get(i)).get("RES_ID")).toString());
			mc.setTitle((String) ((Map) menus.get(i)).get("RES_TITLE"));
			mc.setUrl((String) ((Map) menus.get(i)).get("RES_URL"));
			mc.setLocation((String) ((Map) menus.get(i)).get("RES_URL"));
			if ((Long) ((Map) menus.get(i)).get("PARENT_ID") != 0) {
				mc.setParent(repository.getMenu(((Long) ((Map) menus.get(i))
						.get("PARENT_ID")).toString()));
			}
			repository.addMenu(mc);

		}
		return repository;
	}

	@Override
	public List getTopMenuByUserCode(String userCode) {
		// TODO Auto-generated method stub
		return jsysResourceDao.getMenusByUserCodeAndLayer(userCode, "1");
	}
	@Override
	public List getCommonMenu(String parmcode){
		return jsysResourceDao.getCommonMenu(parmcode);
	}

	@Override
	public List<MenuModel> getSubMenuByUserCode(String userCode, Long parentId) {
		// TODO Auto-generated method stub
		List<MenuModel> menuList = Lists.newArrayList();
		
		List menus = jsysResourceDao.getSubMenusByUserCodeAndParent(userCode,
				parentId);
		log.info("menus.size="+menus.size());
		
		for(int i = 0;i<menus.size();i++){
			MenuModel mc = new MenuModel();
			mc.setName(((BigDecimal) ((Map) menus.get(i)).get("RES_ID")).toString());
			mc.setTitle((String) ((Map) menus.get(i)).get("RES_NAME"));
			mc.setUrl((String) ((Map) menus.get(i)).get("RES_URL"));
			mc.setLocation((String) ((Map) menus.get(i)).get("RES_URL"));
			mc.setResCode((String) ((Map) menus.get(i)).get("RES_CODE"));
			List subMenus = jsysResourceDao.getSubMenusByUserCodeAndParent(userCode,
					((BigDecimal) ((Map) menus.get(i)).get("RES_ID")).longValue());
			for(int j=0;j<subMenus.size();j++){
				MenuModel sub = new MenuModel();
				sub.setName(((BigDecimal) ((Map) subMenus.get(j)).get("RES_ID")).toString());
				sub.setTitle((String) ((Map) subMenus.get(j)).get("RES_NAME"));
				sub.setUrl((String) ((Map) subMenus.get(j)).get("RES_URL"));
				sub.setLocation((String) ((Map) subMenus.get(j)).get("RES_URL"));
				sub.setResCode((String) ((Map) subMenus.get(j)).get("RES_CODE"));
				mc.addSubMenu(sub);
			}
			menuList.add(mc);
		}
		log.info("menuList size>>"+menuList.size());
		return menuList;
	}

	public List getTLMenu(String resCode) {
		// TODO Auto-generated method stub
		return jsysResourceDao.getTLMenu(resCode);
	}

}