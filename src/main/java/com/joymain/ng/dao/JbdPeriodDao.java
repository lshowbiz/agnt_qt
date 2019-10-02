package com.joymain.ng.dao;

import java.util.Date;
import java.util.List;

import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the JbdPeriod table.
 */
public interface JbdPeriodDao extends GenericDao<JbdPeriod, Long> {

	/**
	 * 获取距某一时间最近的几期
	 * @param theDate
	 * @param latelyCount
	 * @return
	 */
	public List getLatestBdPeriodsFweek(final String theDate, int latelyCount);
	/**
	 * 获取对应时间区间的期数,如果期数ID不为空,则不包含对应的期数ID
	 * @param theTime 检查的时候
	 * @param wid 排除的ID
	 * @return
	 */
	public JbdPeriod getBdPeriodByTime(final Date theTime);
	/**
	 * 获取最后的已发放奖金的周
	 * @return
	 */
	public JbdPeriod getLatestSendBonus();
	/**
	 * 传入财政期别，返回期别对象
	 * @param formatedFweek
	 * @return
	 */
	public JbdPeriod getBdPeriodByFormatedWeek(String formatedFweek);
	/**
	 * get Period by year and month
	 * @param year
	 * @param month
	 * @return JbdPeriod list
	 */
	public List<JbdPeriod> findPeriodByYearMon(String year,String month);
	
	/**
	 * 会员信息系统－查询新旧期别对比的数据
	 * @author gw 2013-07-11
	 * @param wyear
	 * @return　list
	 */
	public List<JbdPeriod> getJbdPeriodOldAndNewWweekCom(int wyear);
	
	/**
	 * 会员信息系统－查询新旧期别对比的数据
	 * @author WuCF 2013-11-29
	 * @param wyear
	 * @return　list
	 */
	public List<JbdPeriod> getJbdPeriodOldAndNewWweekComPage(GroupPage page,int wyear);
	
	/**
	 * 获取未来的后几期
	 * @param Year
	 * @param Month
	 * @param latelyCount
	 * @return
	 */
	public String getFutureBdYearMonth(final String year,final String month,final int latelyCount);
	
	/**
	 * 获取对应时间区间的期数,如果期数ID不为空,则不包含对应的期数ID
	 * @author gw 2013-08-07修改
	 * @param theTime 检查的时候
	 * @param wid 排除的ID
	 * @return
	 */
	public JbdPeriod getBdPeriodByTime(final Date theTime, final Long wid);
	
	/**
	 * 获取月结算周期
	 * @param wyear
	 * @return
	 */
	public List<JbdPeriod> getJbdPeriodsByMonthStatus(int wyear);
	
	public String getBdPeriodcurrent();
	public String getFutureBdYearWeek(final String year,final String week,final int latelyCount);
}