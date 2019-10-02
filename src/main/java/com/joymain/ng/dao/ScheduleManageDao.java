package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.model.ScheduleManage;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.data.CommonRecord;

/**
 * An interface that provides a data management interface to the ScheduleManage table.
 */
public interface ScheduleManageDao extends GenericDao<ScheduleManage, Long> {

	public List getScheduleManagesbyCrm(CommonRecord crm, Pager pager);
	public List getScheduleManagesbyCrm(CommonRecord crm);
	
	/**
	 * 手机端：得到公共日程数据集合
	 * Add By WuCF 20140324
	 * @param startDate：起始日期
	 * @param endDate：截止日期
	 * @return
	 */
	public List getMobilePublicSchedules(String startDate,String endDate);
	
	/**
	 * 手机端：得到个人日程数据集合
	 * Add By WuCF 20140324
	 * @param userCode：会员编号
	 * @param startDate：起始日期
	 * @param endDate：截止日期
	 * @return
	 */
	public List getMobileScheduleManages(String userCode,String startDate,String endDate);
	
	/**
	 * 分页 
	 * @author:WuCF
	 * @date:2013-11-29
	 * @param page
	 * @param crm
	 * @return
	 */
	public List getScheduleManagesbyCrmPage(GroupPage page,CommonRecord crm);
	
	public ScheduleManage saveSm(ScheduleManage scheduleManage);
	public List getScheduleByUserCode(String userCode,String today);
	
	/**
	 * Add By WuCF 20131209 
	 * 获得指定行的数据
	 * @param userCode
	 * @param today
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public List getScheduleByUserCode(String userCode, String today,Integer startIndex,Integer endIndex);
}