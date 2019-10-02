package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JpoShoppingCartOrderList;

/**
 * An interface that provides a data management interface to the JpoShoppingCartOrderList table.
 */
public interface JpoShoppingCartOrderListDao extends GenericDao<JpoShoppingCartOrderList, Long> {
	 public List getsclList(Long scId);

}