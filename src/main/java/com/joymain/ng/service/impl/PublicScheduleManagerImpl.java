package com.joymain.ng.service.impl;

import com.joymain.ng.dao.PublicScheduleDao;
import com.joymain.ng.model.PublicSchedule;
import com.joymain.ng.service.PublicScheduleManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("publicScheduleManager")
@WebService(serviceName = "PublicScheduleService", endpointInterface = "com.joymain.ng.service.PublicScheduleManager")
public class PublicScheduleManagerImpl extends GenericManagerImpl<PublicSchedule, Long> implements PublicScheduleManager {
    PublicScheduleDao publicScheduleDao;

    @Autowired
    public PublicScheduleManagerImpl(PublicScheduleDao publicScheduleDao) {
        super(publicScheduleDao);
        this.publicScheduleDao = publicScheduleDao;
    }
	
	public Pager<PublicSchedule> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return publicScheduleDao.getPager(PublicSchedule.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	
	public List getScheduleByUserCode( String today){
		return publicScheduleDao.getScheduleByUserCode(today);
	}
	
	/**
	 * Add By WuCF 20131209 
	 * 查询指定行数的数据
	 * @param today
	 * @param startIndex
	 * @param endIndex
	 * @return
	 */
	public List getScheduleByUserCode( String today,Integer startIndex,Integer endIndex){
		return publicScheduleDao.getScheduleByUserCode(today,startIndex,endIndex);
	}
}