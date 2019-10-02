package com.joymain.ng.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
@WebService
public interface JbdPeriodManager extends GenericManager<JbdPeriod, Long> {
    
	public Pager<JbdPeriod> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
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
	 * 当前时间返回固定格式 ex.201022
	 * @param theTime
	 * @return
	 */
	public Integer getBdPeriodByTimeFormated(Date theTime);
	/**
	 * 传入财政期别，返回期别对象
	 * @param formatedFweek
	 * @return
	 */
	public JbdPeriod getBdPeriodByFormatedWeek(String formatedFweek);
	/**
	 * get period by year and month
	 * @param beginIndex is year
	 * @param endIndex is month
	 * @return list
	 */
	public List getBdPeriodsByMonth(String beginIndex,String endIndex);
	
	
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
	 * @param theTime
	 * @param wid
	 * @return
	 */
	public JbdPeriod getBdPeriodByTime(final Date theTime, final Long wid);
	

	/**
	 * 获取对应时间区间的期数,如果期数ID不为空,则不包含对应的期数ID
	 * @param theTime 检查的时候
	 * @param wid 排除的ID
	 * @return
	 */
	public Integer getBdPeriodByTimeFormated(final Date theTime, final Long wid);
	
	/**
	 * 获取月结算周期
	 * @param wyear
	 * @return
	 */
	public List<JbdPeriod> getJbdPeriodsByMonthStatus(int wyear);
	
	
	/**
	 * 当前时间所在期别
	 * @return
	 */
	public String getBdPeriodcurrent();
	public String getFutureBdYearWeek(final String year,final String week,final int latelyCount);
}