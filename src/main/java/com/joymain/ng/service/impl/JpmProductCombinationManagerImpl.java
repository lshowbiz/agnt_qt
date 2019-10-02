package com.joymain.ng.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JpmProductCombinationDao;
import com.joymain.ng.model.JpmProductCombination;
import com.joymain.ng.service.JpmProductCombinationManager;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@Service("jpmProductCombinationManager")
@WebService(serviceName = "JpmProductCombinationService", endpointInterface = "com.joymain.ng.service.JpmProductCombinationManager")
public class JpmProductCombinationManagerImpl extends GenericManagerImpl<JpmProductCombination, Long> implements JpmProductCombinationManager {
	
	
	@Autowired
	JpmProductCombinationDao jpmProductCombinationDao;

    
 
	
	public Pager<JpmProductCombination> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jpmProductCombinationDao.getPager(JpmProductCombination.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	/**
	 * 通过商品编码编码获取商品的组合信息
	 * @param productNo:商品编码
	 * @return 商品的一级信息
	 */
	public List<Map<String, Object>> getJpmProductList(String productNo) {
		// TODO Auto-generated method stub
		return jpmProductCombinationDao.getJpmProductList(productNo);
	}

	/**
	 * 根据商品编号判断商品是否是套餐商品
	 * @author gw 2015-05-04
	 * @param productNo
	 * @return boolean
	 */
	public boolean getIsisCombinationProduct(String productNo) {
		return jpmProductCombinationDao.getIsisCombinationProduct(productNo);
	}
	
}