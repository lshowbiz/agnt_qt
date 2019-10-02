package com.joymain.ng.service;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.validation.BindingResult;

import com.joymain.ng.model.LinkmanActivity;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface LinkmanActivityManager extends GenericManager<LinkmanActivity, Long> {
    
	public Pager<LinkmanActivity> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);

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
	public List getLinkmanActivityList(String userCode, String topic,
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
	public List getLinkmanActivityListPage(GroupPage page,String userCode, String topic,
			String eventName, String eventType, String beginTimeBegin,
			String beginTimeEnd, String endTimeBegin, String endTimeEnd);

	/**
	 * 客户管理-活动管理-详细查询
	 * @author gw 2013-10-16
	 * @param id
	 * @return
	 */
	public LinkmanActivity getLinkmanActivityDetail(String id);

	/**
	 * 客户管理-活动管理-录入或修改之前不为空的校验
	 * @author gw 2013-10-17
	 * @param linkmanActivity
	 * @param errors
	 * @return
	 */
	public boolean getLinkmanActivityEmptyCheck(
			LinkmanActivity linkmanActivity, BindingResult errors);

	/**
	 * 客户管理－活动管理－录入或修改
	 * @author gw 2013-10-17
	 * @param linkmanActivity
	 */
	public void updateOrAddLinkmanActivity(LinkmanActivity linkmanActivity);
}