package com.joymain.ng.service.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.JpmProductCombinationDao;
import com.joymain.ng.dao.JpmProductSaleNewDao;
import com.joymain.ng.dao.JpoMemberOrderListDao;
import com.joymain.ng.dao.PdNotChangeProductDao;
import com.joymain.ng.model.JpmProductSaleTeamType;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.PdNotChangeProduct;
import com.joymain.ng.service.JpoMemberOrderListManager;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@Service("jpoMemberOrderListManager")
@WebService(serviceName = "JpoMemberOrderListService", endpointInterface = "com.joymain.ng.service.JpoMemberOrderListManager")
public class JpoMemberOrderListManagerImpl extends GenericManagerImpl<JpoMemberOrderList, Long> implements JpoMemberOrderListManager {
	
    JpoMemberOrderListDao jpoMemberOrderListDao;
    JpmProductSaleNewDao pmProductSaleNewDao;
    
    JpmProductCombinationDao jpmProductCombinationDao;
    PdNotChangeProductDao pdNotChangeProductDao;
    
    @Autowired
    public void setPdNotChangeProductDao(PdNotChangeProductDao pdNotChangeProductDao) {
		this.pdNotChangeProductDao = pdNotChangeProductDao;
	}
	@Autowired
	public void setJpoMemberOrderListDao(JpoMemberOrderListDao jpoMemberOrderListDao) {
		this.jpoMemberOrderListDao = jpoMemberOrderListDao;
	}
    @Autowired
	public void setJpmProductCombinationDao(
			JpmProductCombinationDao jpmProductCombinationDao) {
		this.jpmProductCombinationDao = jpmProductCombinationDao;
	}
	public Pager<JpoMemberOrderList> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jpoMemberOrderListDao.getPager(JpoMemberOrderList.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	/**
     * 根据订单明细流水号查询商品重量等信息　 
     * @param map
     * @return
     */
    @Override
    public JpoMemberOrderList getJpoMemberOrderListByMolId(Map<String, Long> map)
    {
        return jpoMemberOrderListDao.getJpoMemberOrderListByMolId(map);
    }

	@Override
	public Integer getSumQtyByProNo() {
		Integer sum1=0, sum2=0,sum3=0,count=0;
		Long pcount1 = jpoMemberOrderListDao.getProSumByProNo(Constants.PRONO);
		Long pcount2 = jpoMemberOrderListDao.getProSumByProNo(Constants.PRONO1);
		Long pcount3 = jpoMemberOrderListDao.getProSumByProNo(Constants.PRONO2);
		
		if(pcount1 !=null){
			sum1 = pcount1.intValue();
		}
		if(pcount2 !=null){
			sum2 = pcount2.intValue();
		}
		if(pcount3 !=null){
			sum3 = pcount3.intValue();
		}
		count = sum1+sum2+sum3;
		
		return count;
	}

	@Override
	public Integer getSumQtyByProNo(String proNo) {
		Long num = jpoMemberOrderListDao.getProSumByProNo(proNo);
		return num!=null?num.intValue():0;
	}
	
    public Integer getProSumByProNo(String proNo,String statetime,String endtime){
    	Long num = jpoMemberOrderListDao.getProSumByProNo(proNo, statetime, endtime);
    	return num!=null?num.intValue():0;
    }

	@Override
	public JpoMemberOrderList bingProduct(String pttId, Integer qty) {
		JpoMemberOrderList orderItem = new JpoMemberOrderList();
		JpmProductSaleTeamType stt = pmProductSaleNewDao.
				getJpmProductSaleTeamType(pttId);
		if(stt !=null){
			orderItem.setJpmProductSaleTeamType(stt);
			orderItem.setPrice(stt.getPrice());
			orderItem.setQty(qty);
			orderItem.setPv(stt.getPv());
		} else {
			return null;
		}
		return orderItem;
	}
	public JpmProductSaleNewDao getPmProductSaleNewDao() {
		return pmProductSaleNewDao;
	}
    
    @Autowired
	public void setPmProductSaleNewDao(JpmProductSaleNewDao pmProductSaleNewDao) {
		this.pmProductSaleNewDao = pmProductSaleNewDao;
	}

	@Autowired
    public JpoMemberOrderListManagerImpl(JpoMemberOrderListDao jpoMemberOrderListDao) {
        super(jpoMemberOrderListDao);
        this.jpoMemberOrderListDao = jpoMemberOrderListDao;
    }
	
	//判断原订单中的商品是否为可换货商品
	@Override
	public boolean isNotExchangeProduct(JpoMemberOrderList jpoMemberOrderList) {
		String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
		//1.单品可换，套餐商品不可换
		if(jpmProductCombinationDao.getIsisCombinationProduct(productNo)){
			return true;
		}
		//2.部分指定商品不让换货
		else if(pdNotChangeProductDao.getIsNotChangeProduct(productNo)){
			return true;
		}
		//3.赠品不让换货,订单中商品价格为0就可以认为是赠品
		else if(jpoMemberOrderList.getPrice().equals(BigDecimal.ZERO)){
			return true;
		}
		//4.商品表里换货标志为不让换货的不可以换货
//		else if(pmProductSaleNewDao.getIsNotChangeProduct(productNo)){
//			return true;
//		}
		//其他的商品是可换货商品
		return false;
		
	}
}