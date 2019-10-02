package com.joymain.ng.dao;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.joymain.ng.model.LinkmanMaintain;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the LinkmanMaintain table.
 */
public interface LinkmanMaintainDao extends GenericDao<LinkmanMaintain, Long> {


	/**
	 * 客户管理-客户维护-初始化或有条件查询
	 * @author gw 2013-10-12
	 * @param userCode
	 * @param name
	 * @param maintenanceTopic
	 * @param maintenanceMode
	 * @param maintenanceTimeBegin
	 * @param maintenanceTimeEnd
	 * @return list
	 */
	List getLinkmanMaintainList(String userCode, String name,
			String maintenanceTopic, String maintenanceMode,
			String maintenanceTimeBegin, String maintenanceTimeEnd);
	
	/**
	 * 分页
	 * 客户管理-客户维护-初始化或有条件查询
	 * @author WuCF 2013-12-03
	 * @param userCode
	 * @param name
	 * @param maintenanceTopic
	 * @param maintenanceMode
	 * @param maintenanceTimeBegin
	 * @param maintenanceTimeEnd
	 * @return list
	 */
	List getLinkmanMaintainListPage(GroupPage page,String userCode, String name,
			String maintenanceTopic, String maintenanceMode,
			String maintenanceTimeBegin, String maintenanceTimeEnd);

	/**
	 * 客户维护-详细查询或修改之前的查询
	 * @author gw 2013-10-12
	 * @param id
	 * @return LinkmanMaintain
	 */
	LinkmanMaintain getLinkmanMaintain(String id);

	/**
	 * 客户维护-录入或修改之前不为空的校验
	 * @author gw 2013-10-12
	 * @param  linkmanMaintain
	 * @return boolean
	 */
	boolean getLinkmanMaintainEmptyCheck(LinkmanMaintain linkmanMaintain,BindingResult errors);

	/**
	 * 客户维护-录入或修改
	 * @author gw 2013-10-12
	 * @param linkmanMaintain
	 */
	void updateOrAddLinkmanMaintain(LinkmanMaintain linkmanMaintain);
	
}