package com.joymain.ng.service.impl;

import com.joymain.ng.dao.FiBcoinPayconfigDao;
import com.joymain.ng.dao.FiBcoinPayconfigDetailDao;
import com.joymain.ng.model.FiBcoinPayconfig;
import com.joymain.ng.model.FiBcoinPayconfigDetail;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.service.FiBcoinPayconfigManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebService;

@Service("fiBcoinPayconfigManager")
@WebService(serviceName = "FiBcoinPayconfigService", endpointInterface = "com.joymain.ng.service.FiBcoinPayconfigManager")
public class FiBcoinPayconfigManagerImpl extends GenericManagerImpl<FiBcoinPayconfig, Long> implements FiBcoinPayconfigManager {
    FiBcoinPayconfigDao fiBcoinPayconfigDao;
    FiBcoinPayconfigDetailDao fiBcoinPayconfigDetailDao;

    @Autowired
    public FiBcoinPayconfigManagerImpl(FiBcoinPayconfigDao fiBcoinPayconfigDao) {
        super(fiBcoinPayconfigDao);
        this.fiBcoinPayconfigDao = fiBcoinPayconfigDao;
    }
    
    @Autowired
	public void setFiBcoinPayconfigDetailDao(
			FiBcoinPayconfigDetailDao fiBcoinPayconfigDetailDao) {
		this.fiBcoinPayconfigDetailDao = fiBcoinPayconfigDetailDao;
	}



	public Pager<FiBcoinPayconfig> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return fiBcoinPayconfigDao.getPager(FiBcoinPayconfig.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	
	/**
     * 查询当前时间内积分换购活动
     * @return
     */
	@Override
	public FiBcoinPayconfig getFiBcoinPayconfigByNowTime() {
		
		return fiBcoinPayconfigDao.getFiBcoinPayconfigByNowTime();
	}
	
	/**
     * 查询当前订单积分换购商品权限
     * @param jpoMemberOrder
     * @return
     */
	@Override
	public boolean getCanUseCoinPayByOrder(FiBcoinPayconfig fiBcoinPayconfig, JpoMemberOrder jpoMemberOrder) {
		
		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		
		while (its1.hasNext()) {
        	JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        	String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
        	int buyNum = jpoMemberOrderList.getQty();
        	
        	FiBcoinPayconfigDetail tempFiBcoinPayconfigDetail = fiBcoinPayconfigDetailDao.getFiBcoinPayconfigDetailsByProductNo(fiBcoinPayconfig.getConfigId().toString(), productNo);
        	
        	if(("1").equals(fiBcoinPayconfig.getLimit())){//1：限制部分产品不参加，2：开放部分产品参加
        		
        		if(tempFiBcoinPayconfigDetail!=null){
            		
            		return false;
            	}
        	}
        	if(("2").equals(fiBcoinPayconfig.getLimit())){
        		
        		if(tempFiBcoinPayconfigDetail==null){
        			
            		return false;
            	}else{
            		
            		//是否超过限购范围
	        		if(buyNum > tempFiBcoinPayconfigDetail.getNowQuantity()){
	        			
	        			return false;
	        		}
        		}
        	}
        }
		
		return true;
	}

	/**
	 * 积分混合支付成功，需要对限购的数量做计算当前剩余量
	 */
	@Override
	public void comNowQuantity(JpoMemberOrder jpoMemberOrder,FiBcoinPayconfig fiBcoinPayconfig) {
		
		Iterator its1 = jpoMemberOrder.getJpoMemberOrderList().iterator();
		while (its1.hasNext()) {
        	JpoMemberOrderList jpoMemberOrderList = (JpoMemberOrderList) its1.next();
        	String productNo = jpoMemberOrderList.getJpmProductSaleTeamType().getJpmProductSaleNew().getJpmProduct().getProductNo();
        	int buyNum = jpoMemberOrderList.getQty();
        	
        	FiBcoinPayconfigDetail tempFiBcoinPayconfigDetail = fiBcoinPayconfigDetailDao.getFiBcoinPayconfigDetailsByProductNo(fiBcoinPayconfig.getConfigId().toString(), productNo);
        	if(("2").equals(fiBcoinPayconfig.getLimit())){
        		
        		//计算
        		int lastNum = tempFiBcoinPayconfigDetail.getNowQuantity();//上次的剩余量

        		tempFiBcoinPayconfigDetail.setNowQuantity(lastNum-buyNum);
        	}
		}
	}
}