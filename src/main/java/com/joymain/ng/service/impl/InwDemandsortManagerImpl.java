package com.joymain.ng.service.impl;

import com.joymain.ng.dao.InwDemandsortDao;
import com.joymain.ng.model.InwDemandsort;
import com.joymain.ng.service.InwDemandsortManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("inwDemandsortManager")
@WebService(serviceName = "InwDemandsortService", endpointInterface = "com.joymain.ng.service.InwDemandsortManager")
public class InwDemandsortManagerImpl extends GenericManagerImpl<InwDemandsort, Long> implements InwDemandsortManager {
    InwDemandsortDao inwDemandsortDao;

    @Autowired
    public InwDemandsortManagerImpl(InwDemandsortDao inwDemandsortDao) {
        super(inwDemandsortDao);
        this.inwDemandsortDao = inwDemandsortDao;
    }
	
	public Pager<InwDemandsort> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		return inwDemandsortDao.getPager(InwDemandsort.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	/**
     * 需求分类初始化查询(这个查询时没有任何查询条件的)
     * @author gw 2013-11-06
     * @param  request
     * @return  list
     */
	public List getDemandsortList() {
		return inwDemandsortDao.getDemandsortList();
	}
	
	/**
     * 查询该需求大类上的所有小类的集合
     * @author gw 2013-11-06
     * @param  request
     * @return  list
     */
	public List getDemandsortDetailList(String id) {
		return inwDemandsortDao.getDemandsortDetailList(id);
	}



	/**
	 * 创新共赢---需求分类-----获取需求分类的分类名称
	 * @author gw  2013-11-08
	 * @param demandsort_id
	 * @return string
	 */
	public String getInwDemandsortById(String demandsortId) {
		return inwDemandsortDao.getInwDemandsortById(demandsortId);
	}

	
}