package com.joymain.ng.service.impl;

import com.joymain.ng.dao.PdLogisticsBaseNumDao;
import com.joymain.ng.model.PdLogisticsBaseNum;
import com.joymain.ng.service.PdLogisticsBaseNumManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("pdLogisticsBaseNumManager")
@WebService(serviceName = "PdLogisticsBaseNumService", endpointInterface = "com.joymain.ng.service.PdLogisticsBaseNumManager")
public class PdLogisticsBaseNumManagerImpl extends GenericManagerImpl<PdLogisticsBaseNum, Long> implements PdLogisticsBaseNumManager {
    PdLogisticsBaseNumDao pdLogisticsBaseNumDao;

    @Autowired
    public PdLogisticsBaseNumManagerImpl(PdLogisticsBaseNumDao pdLogisticsBaseNumDao) {
        super(pdLogisticsBaseNumDao);
        this.pdLogisticsBaseNumDao = pdLogisticsBaseNumDao;
    }
	
	public Pager<PdLogisticsBaseNum> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return pdLogisticsBaseNumDao.getPager(PdLogisticsBaseNum.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	/**
	 * @author gw 2014-11-11
	 * 报单中心前台-物流跟踪查询 
	 * @param moId
	 * @return
	 */
	public List getPdLogisticsBaseNumAndDetail(String moId) {
		return pdLogisticsBaseNumDao.getPdLogisticsBaseNumAndDetail(moId);
	}

	/**
	 * 根据物流单号获取物流信息
	 * @author gw 2015-02-11
	 * @param mailNo
	 * @return pdLogisticsBaseNum
	 */
	public PdLogisticsBaseNum getPdLogisticsBaseNumForMailNo(String mailNo) {
		return pdLogisticsBaseNumDao.getPdLogisticsBaseNumForMailNo(mailNo);
	}
}