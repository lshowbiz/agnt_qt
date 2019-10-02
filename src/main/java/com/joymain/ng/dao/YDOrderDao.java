package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.model.YDOrder;
import com.joymain.ng.util.GroupPage;

public interface YDOrderDao extends GenericDao<YDOrder,Long>{
	
	public List<YDOrder> getYDOrder(String userNo,String orderNo,GroupPage page);
	public List<YDOrder> getYDOrder(String userNo,int pageNum,int pageSize);
}
