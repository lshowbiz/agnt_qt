package com.joymain.ng.service;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.validation.BindingResult;

import com.joymain.ng.model.LinkmanMaintain;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface LinkmanMaintainManager extends GenericManager<LinkmanMaintain, Long> {
    
	public Pager<LinkmanMaintain> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);

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
	public List getLinkmanMaintainList(String userCode, String name,
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
	public List getLinkmanMaintainListPage(GroupPage page,String userCode, String name,
			String maintenanceTopic, String maintenanceMode,
			String maintenanceTimeBegin, String maintenanceTimeEnd);
    
	/**
	 * 客户维护-详细查询
	 * @author gw 2013-10-12
	 * @param id
	 * @return linkmanMaintain
	 */
	public LinkmanMaintain getLinkmanMaintain(String id);
    
	/**
	 * 客户维护-录入或修改之前不为空的校验
	 * @param linkmanMaintain
	 * @return boolean
	 */
	public boolean getLinkmanMaintainEmptyCheck(LinkmanMaintain linkmanMaintain,BindingResult errors);

	/**
	 * 客户维护-录入或修改
	 * @author gw 2013-10-12
	 * @param linkmanMaintain
	 */
	public void updateOrAddLinkmanMaintain(LinkmanMaintain linkmanMaintain);
}