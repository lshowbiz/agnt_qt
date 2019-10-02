package com.joymain.ng.service.impl;

import com.joymain.ng.dao.PdLogisticsBaseDetailDao;
import com.joymain.ng.model.PdLogisticsBaseDetail;
import com.joymain.ng.service.PdLogisticsBaseDetailManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("pdLogisticsBaseDetailManager")
@WebService(serviceName = "PdLogisticsBaseDetailService", endpointInterface = "com.joymain.ng.service.PdLogisticsBaseDetailManager")
public class PdLogisticsBaseDetailManagerImpl extends GenericManagerImpl<PdLogisticsBaseDetail, Long> implements PdLogisticsBaseDetailManager {
    PdLogisticsBaseDetailDao pdLogisticsBaseDetailDao;

    @Autowired
    public PdLogisticsBaseDetailManagerImpl(PdLogisticsBaseDetailDao pdLogisticsBaseDetailDao) {
        super(pdLogisticsBaseDetailDao);
        this.pdLogisticsBaseDetailDao = pdLogisticsBaseDetailDao;
    }
	
	public Pager<PdLogisticsBaseDetail> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return pdLogisticsBaseDetailDao.getPager(PdLogisticsBaseDetail.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	/**
	 * 查询套餐商品在这个物流跟踪单号下的子商品
	 * @author gw 2015-05-04
	 * @param numId PdLogisticsBaseNum所关联表的主键
	 * @param productNo
	 * @return list
	 */
	public List<PdLogisticsBaseDetail> getlistpdLogisticsBaseDetail(Long numId,String productNo) {
		 return pdLogisticsBaseDetailDao.getlistpdLogisticsBaseDetail(numId,productNo);
	}

	/**
	 * 套餐物流信息展示-根据商品编码获取商品名称
	 * @author gw 2015-05-08
	 * @param productNo 商品编码
	 * @return string
	 */
	public String getProductName(String productNo) {
		return pdLogisticsBaseDetailDao.getProductName(productNo);
	}
}