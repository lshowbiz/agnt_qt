package com.joymain.ng.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.joymain.ng.dao.LinkmanEventDao;
import com.joymain.ng.model.LinkmanEvent;
import com.joymain.ng.service.LinkmanEventManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@Service("linkmanEventManager")
@WebService(serviceName = "LinkmanEventService", endpointInterface = "com.joymain.ng.service.LinkmanEventManager")
public class LinkmanEventManagerImpl extends GenericManagerImpl<LinkmanEvent, Long> implements LinkmanEventManager {
    LinkmanEventDao linkmanEventDao;

    @Autowired
    public LinkmanEventManagerImpl(LinkmanEventDao linkmanEventDao) {
        super(linkmanEventDao);
        this.linkmanEventDao = linkmanEventDao;
    }
	
	public Pager<LinkmanEvent> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		return linkmanEventDao.getPager(LinkmanEvent.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	/**
	 * 客户管理-事件管理-初始化查询或有条件查询
	 * @author gw  2013-10-14
	 * @param userCode
	 * @param name
	 * @param title
	 * @param eventType
	 * @param timeBegin
	 * @param timeEnd
	 * @return
	 */
	public List getLinkmanEventList(String userCode, String name, String title,
			String eventType, String timeBegin, String timeEnd) {
		return linkmanEventDao.getLinkmanEventList(userCode,name,title,eventType,timeBegin,timeEnd);
	}
	
	/**
	 * 分页
	 * 客户管理-事件管理-初始化查询或有条件查询
	 * @author WuCF 2013-12-03
	 * @param userCode
	 * @param name
	 * @param title
	 * @param eventType
	 * @param timeBegin
	 * @param timeEnd
	 * @return
	 */
	public List getLinkmanEventListPage(GroupPage page,String userCode, String mCode, String name, String title,
			String eventType, String timeBegin, String timeEnd) {
		return linkmanEventDao.getLinkmanEventListPage(page,userCode, mCode, name,title,eventType,timeBegin,timeEnd);
	}
	
	/**
	 * 客户管理-事件管理-详细查询
	 * @author gw 2013-10-14
	 * @param id
	 * @return
	 */
	public LinkmanEvent getLinkmanEvent(String id) {
		return linkmanEventDao.getLinkmanEventList(id);
	}

	/**
	 * 客户管理-事件管理-录入或修改之前不为空的校验
	 * @author 2013-10-14 gw
	 * @param linkmanEvent
	 * @param errors
	 * @return
	 */
	public boolean getLinkmanEventEmptyCheck(LinkmanEvent linkmanEvent,BindingResult errors) {
		return linkmanEventDao.getLinkmanEventEmptyCheck(linkmanEvent,errors);
	}

	/**
	 * 客户管理-事件管理-录入或修改
	 * @author gw 2013-10-14
	 * @param linkmanEvent
	 */
	public void updateOrAddLinkmanMaintain(LinkmanEvent linkmanEvent) {
		linkmanEventDao.updateOrAddLinkmanMaintain(linkmanEvent);
	}
}