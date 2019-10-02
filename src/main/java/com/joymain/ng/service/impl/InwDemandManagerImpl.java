package com.joymain.ng.service.impl;

import com.joymain.ng.dao.InwDemandDao;
import com.joymain.ng.model.InwDemand;
import com.joymain.ng.service.InwDemandManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;
import com.joymain.ng.util.ListUtil;

@Service("inwDemandManager")
@WebService(serviceName = "InwDemandService", endpointInterface = "com.joymain.ng.service.InwDemandManager")
public class InwDemandManagerImpl extends GenericManagerImpl<InwDemand, Long> implements InwDemandManager {
    InwDemandDao inwDemandDao;

    @Autowired
    public InwDemandManagerImpl(InwDemandDao inwDemandDao) {
        super(inwDemandDao);
        this.inwDemandDao = inwDemandDao;
    }
	
	public Pager<InwDemand> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		return inwDemandDao.getPager(InwDemand.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	/**
     * 创新共赢的需求(合作共赢)的查询
     * @author gw  2013-08-13
     * @param other
     */
	public List getInwDemandList(String other) {
		return inwDemandDao.getInwDemandList(other);
	}

	/**
	 * 创新共赢的需求（合作共赢）的详细查询
	 * @author gw 2013-08-14
	 * @param id
	 * @return
	 */
	public InwDemand getInwDemandDetail(String id) {
		return inwDemandDao.getInwDemandDetail(id);
	}
	
	public String getFileURL(String companyCode){
		return  ListUtil.getListValue(companyCode.toUpperCase(), "jpmproductsaleimage.url", "3");
	}

	/**
	 * 创新共赢---通过ID获取需求表的需求名称
	 * @author gw 2013-11-08
	 * @param id
	 * @return string
	 */
	public String getInwDemandById(String id) {
		return inwDemandDao.getInwDemandById(id);
	}
	
}