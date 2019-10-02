package com.joymain.ng.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JbdPeriodDao;
import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.StringUtil;

@Service("jbdPeriodManager")
@WebService(serviceName = "JbdPeriodService", endpointInterface = "com.joymain.ng.service.JbdPeriodManager")
public class JbdPeriodManagerImpl extends GenericManagerImpl<JbdPeriod, Long> implements JbdPeriodManager {

    JbdPeriodDao jbdPeriodDao;

    @Autowired
    public JbdPeriodManagerImpl(JbdPeriodDao jbdPeriodDao) {
        super(jbdPeriodDao);
        this.jbdPeriodDao = jbdPeriodDao;
    }
	
	public Pager<JbdPeriod> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jbdPeriodDao.getPager(JbdPeriod.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	public JbdPeriod getBdPeriodByTime(Date theTime) {
		return jbdPeriodDao.getBdPeriodByTime(theTime);
	}

	public List getLatestBdPeriodsFweek(String theDate, int latelyCount) {
		return jbdPeriodDao.getLatestBdPeriodsFweek(theDate, latelyCount);
	}

	public JbdPeriod getLatestSendBonus() {
		return jbdPeriodDao.getLatestSendBonus();
	}
	public Integer getBdPeriodByTimeFormated(Date theTime) {

		JbdPeriod bdPeriod = this.getBdPeriodByTime(theTime);		
		
		String bdWeek= bdPeriod.getWyear()+""+ (bdPeriod.getWweek()<10?"0" + bdPeriod.getWweek():bdPeriod.getWweek());
		
		return StringUtil.formatInt(bdWeek);
	}

	public JbdPeriod getBdPeriodByFormatedWeek(String formatedFweek) {
		return jbdPeriodDao.getBdPeriodByFormatedWeek(formatedFweek);
	}
	
	public List getBdPeriodsByMonth(String beginIndex,String endIndex) {
		return jbdPeriodDao.findPeriodByYearMon(beginIndex, endIndex);
	}
	
	
	/**
	 * 会员信息系统－查询新旧期别对比的数据
	 * @author gw 2013-07-11
	 * @param wyear
	 * @return　list
	 */
	public List<JbdPeriod> getJbdPeriodOldAndNewWweekCom(int wyear){
		return jbdPeriodDao.getJbdPeriodOldAndNewWweekCom(wyear);
	}
	
	/**
	 * 分页
	 * 会员信息系统－查询新旧期别对比的数据
	 * @author WuCF 2013-11-29
	 * @param wyear
	 * @return　list
	 */
	public List<JbdPeriod> getJbdPeriodOldAndNewWweekComPage(GroupPage page,int wyear){
		return jbdPeriodDao.getJbdPeriodOldAndNewWweekComPage(page,wyear);
	}

	public String getFutureBdYearMonth(String year, String month, int latelyCount) {
		return jbdPeriodDao.getFutureBdYearMonth(year, month, latelyCount);
	}
	/**
	 * 获取对应时间区间的期数,如果期数ID不为空,则不包含对应的期数ID
	 * @param theTime
	 * @param wid
	 * @return
	 */
	public JbdPeriod getBdPeriodByTime(final Date theTime, final Long wid){
		return jbdPeriodDao.getBdPeriodByTime(theTime, wid);
	}
	
	public Integer getBdPeriodByTimeFormated(Date theTime, Long wid) {

		JbdPeriod bdPeriod = this.getBdPeriodByTime(theTime, wid);		
		
		String bdWeek= bdPeriod.getWyear()+""+ (bdPeriod.getWweek()<10?"0" + bdPeriod.getWweek():bdPeriod.getWweek());
		
		return StringUtil.formatInt(bdWeek);
	}

	/**
	 * 获取月结算周期
	 * @param wyear
	 * @return
	 */
	@Override
	public List<JbdPeriod> getJbdPeriodsByMonthStatus(int wyear) {
		// TODO Auto-generated method stub
		return jbdPeriodDao.getJbdPeriodsByMonthStatus(wyear);
	}
	
	/**
	 * 当前日期所在
	 */
	public String getBdPeriodcurrent(){
		return jbdPeriodDao.getBdPeriodcurrent();
	}

	@Override
	public String getFutureBdYearWeek(String year, String week, int latelyCount) {
		return jbdPeriodDao.getFutureBdYearWeek(year, week, latelyCount);
	}
}