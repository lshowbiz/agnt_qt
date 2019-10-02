package com.joymain.ng.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.ScheduleManageDao;
import com.joymain.ng.model.ScheduleManage;
import com.joymain.ng.service.ScheduleManageManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.data.CommonRecord;

@Service("scheduleManageManager")
@WebService(serviceName = "ScheduleManageService", endpointInterface = "com.joymain.ng.service.ScheduleManageManager")
public class ScheduleManageManagerImpl extends GenericManagerImpl<ScheduleManage, Long> implements ScheduleManageManager {
    ScheduleManageDao scheduleManageDao;

    @Autowired
    public ScheduleManageManagerImpl(ScheduleManageDao scheduleManageDao) {
        super(scheduleManageDao);
        this.scheduleManageDao = scheduleManageDao;
    }
    
	public Pager<ScheduleManage> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return scheduleManageDao.getPager(ScheduleManage.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public List getScheduleManagesbyCrm(CommonRecord crm) {
		return scheduleManageDao.getScheduleManagesbyCrm(crm);
	}
	
	/**
	 * 手机端：得到公共日程数据集合
	 * Add By WuCF 20140324
	 * @param startDate：起始日期
	 * @param endDate：截止日期
	 * @return
	 */
	@Override
	public List getMobilePublicSchedules(String startDate,String endDate) {
		return scheduleManageDao.getMobilePublicSchedules(startDate,endDate);
	}

	/**
	 * 手机端：得到个人日程数据集合
	 * Add By WuCF 20140324
	 * @param userCode：会员编号
	 * @param startDate：起始日期
	 * @param endDate：截止日期
	 * @return
	 */
	@Override
	public List getMobileScheduleManages(String userCode, String startDate,
			String endDate) {
		return scheduleManageDao.getMobileScheduleManages(userCode,startDate,endDate);
	}

	/**
	 * 分页 
	 * @author:WuCF
	 * @date:2013-11-29
	 * @param page
	 * @param crm
	 * @return
	 */
	@Override
	public List getScheduleManagesbyCrmPage(GroupPage page,CommonRecord crm) {
		return scheduleManageDao.getScheduleManagesbyCrmPage(page,crm);
	}

    public List getScheduleManagesbyCrm(CommonRecord crm, Pager pager) {
    	return scheduleManageDao.getScheduleManagesbyCrm(crm, pager);
    }

    public ScheduleManage saveSm(ScheduleManage scheduleManage) {
    	return scheduleManageDao.saveSm(scheduleManage);
    }
    
    public ScheduleManage getSchedule(String id){
    	return scheduleManageDao.get(Long.parseLong(id));
    }
    
    public List getScheduleByUserCode(String userCode,String today){
    	return scheduleManageDao.getScheduleByUserCode(userCode,today);
    }
    
    /**
	 * Add By WuCF 20131209 
	 * 获得指定行的数据
	 * @param userCode
	 * @param today
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
    public List getScheduleByUserCode(String userCode,String today,Integer startIndex,Integer endIndex){
    	return scheduleManageDao.getScheduleByUserCode(userCode,today,startIndex,endIndex);
    }
}