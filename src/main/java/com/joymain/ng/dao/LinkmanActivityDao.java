package com.joymain.ng.dao;

import java.util.List;

import org.springframework.validation.BindingResult;

import com.joymain.ng.model.LinkmanActivity;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the LinkmanActivity table.
 */
public interface LinkmanActivityDao extends GenericDao<LinkmanActivity, Long> {

	/**
	 * 客户管理-活动管理-初始化查询\有条件查询
	 * @author gw 2013-10-16
	 * @param userCode
	 * @param topic
	 * @param eventName
	 * @param eventType
	 * @param beginTimeBegin
	 * @param beginTimeEnd
	 * @param endTimeBegin
	 * @param endTimeEnd
	 * @return
	 */
	List getLinkmanActivityList(String userCode, String topic,
			String eventName, String eventType, String beginTimeBegin,
			String beginTimeEnd, String endTimeBegin, String endTimeEnd);

	/**
	 * 分页
	 * 客户管理-活动管理-初始化查询\有条件查询
	 * @author WuCF 2013-12-03
	 * @param userCode
	 * @param topic
	 * @param eventName
	 * @param eventType
	 * @param beginTimeBegin
	 * @param beginTimeEnd
	 * @param endTimeBegin
	 * @param endTimeEnd
	 * @return
	 */
	List getLinkmanActivityListPage(GroupPage page,String userCode, String topic,
			String eventName, String eventType, String beginTimeBegin,
			String beginTimeEnd, String endTimeBegin, String endTimeEnd);
	
	/**
	 * 客户管理-活动管理-详细查询
	 * @author gw 2013-10-16
	 * @param id
	 * @return
	 */
	LinkmanActivity getLinkmanActivityDetail(String id);

	/**
	 * 客户管理-活动管理-录入或修改之前不为空的校验
	 * @author gw 2013-10-17
	 * @param linkmanActivity
	 * @param errors
	 * @return
	 */
	boolean getLinkmanActivityEmptyCheck(LinkmanActivity linkmanActivity,
			BindingResult errors);

	/**
	 * 客户管理－活动管理－录入或修改
	 * @author gw 2013-10-17
	 * @param linkmanActivity
	 */
	void updateOrAddLinkmanActivity(LinkmanActivity linkmanActivity);

}