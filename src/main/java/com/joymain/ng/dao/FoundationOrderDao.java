package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.FoundationOrder;

/**
 * An interface that provides a data management interface to the FoundationOrder table.
 */
public interface FoundationOrderDao extends GenericDao<FoundationOrder, Long> {

	public List<FoundationOrder> getFoundationOrdersByUserCode(String userCode, String startTime, String endTime);
	
	public List getOrdersByItemTypeAndTime(String userCode);
	
	public Long saveFoundationOrder(final FoundationOrder foundationOrder);
}