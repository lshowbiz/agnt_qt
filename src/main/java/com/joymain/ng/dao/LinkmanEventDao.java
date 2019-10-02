package com.joymain.ng.dao;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.joymain.ng.model.LinkmanEvent;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the LinkmanEvent table.
 */
public interface LinkmanEventDao extends GenericDao<LinkmanEvent, Long> {

	/**
	 * 客户管理-事件管理-初始化查询或有条件查询
	 * @author gw  2013-10-14
	 * @param userCode
	 * @param name
	 * @param title
	 * @param eventType
	 * @param timeBegin
	 * @param timeEnd
	 * @paramreturn
	 */
	List getLinkmanEventList(String userCode, String name, String title,String eventType, String timeBegin, String timeEnd);

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
	List getLinkmanEventListPage(GroupPage page,String userCode, String mCode, String name, String title,String eventType, String timeBegin, String timeEnd);

	
	/**
	 * 客户管理-事件管理-详细查询
	 * @author gw 2013-10-14
	 * @param id
	 * @return
	 */
	LinkmanEvent getLinkmanEventList(String id);

	/**
	 * 客户管理-事件管理-录入或修改之前不为空的校验
	 * @author 2013-10-14 gw
	 * @param linkmanEvent
	 * @param errors
	 * @return
	 */
	boolean getLinkmanEventEmptyCheck(LinkmanEvent linkmanEvent,BindingResult errors);

	/**
	 * 客户管理-事件管理-录入或修改
	 * @author gw 2013-10-14
	 * @param linkmanEvent
	 */
	void updateOrAddLinkmanMaintain(LinkmanEvent linkmanEvent);

}