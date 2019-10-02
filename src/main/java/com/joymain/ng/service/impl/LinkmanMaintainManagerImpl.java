package com.joymain.ng.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.joymain.ng.dao.LinkmanMaintainDao;
import com.joymain.ng.model.LinkmanMaintain;
import com.joymain.ng.service.LinkmanMaintainManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@Service("linkmanMaintainManager")
@WebService(serviceName = "LinkmanMaintainService", endpointInterface = "com.joymain.ng.service.LinkmanMaintainManager")
public class LinkmanMaintainManagerImpl extends GenericManagerImpl<LinkmanMaintain, Long> implements LinkmanMaintainManager {
    LinkmanMaintainDao linkmanMaintainDao;

    @Autowired
    public LinkmanMaintainManagerImpl(LinkmanMaintainDao linkmanMaintainDao) {
        super(linkmanMaintainDao);
        this.linkmanMaintainDao = linkmanMaintainDao;
    }
	
	public Pager<LinkmanMaintain> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		return linkmanMaintainDao.getPager(LinkmanMaintain.class, searchBeans, OrderBys, currentPage, pageSize);
	}

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
			String maintenanceTimeBegin, String maintenanceTimeEnd) {
		return linkmanMaintainDao.getLinkmanMaintainList(userCode,name,maintenanceTopic,maintenanceMode,maintenanceTimeBegin,maintenanceTimeEnd);
	}
	
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
			String maintenanceTimeBegin, String maintenanceTimeEnd) {
		return linkmanMaintainDao.getLinkmanMaintainListPage(page,userCode,name,maintenanceTopic,maintenanceMode,maintenanceTimeBegin,maintenanceTimeEnd);
	}

	/**
	 * 客户维护-详细查询或修改之前的查询
	 * @author gw 2013-10-12
	 */
	public LinkmanMaintain getLinkmanMaintain(String id) {
		return linkmanMaintainDao.getLinkmanMaintain(id);
	}

	/**
	 * 客户维护-录入或修改之前不为空的校验
	 * @author gw 2013-10-12
	 * @param  linkmanMaintain
	 * @return boolean
	 */
	public boolean getLinkmanMaintainEmptyCheck(LinkmanMaintain linkmanMaintain,BindingResult errros) {
		return linkmanMaintainDao.getLinkmanMaintainEmptyCheck(linkmanMaintain,errros);
	}

	/**
	 * 客户维护-录入或修改
	 * @author gw 2013-10-12
	 * @param linkmanMaintain
	 */
	public void updateOrAddLinkmanMaintain(LinkmanMaintain linkmanMaintain) {
		linkmanMaintainDao.updateOrAddLinkmanMaintain(linkmanMaintain);	
	}
}