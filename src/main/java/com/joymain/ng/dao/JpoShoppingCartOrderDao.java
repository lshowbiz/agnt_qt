package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JpoShoppingCart;
import com.joymain.ng.model.JpoShoppingCartOrder;

/**
 * An interface that provides a data management interface to the JpoShoppingCartOrder table.
 */
public interface JpoShoppingCartOrderDao extends GenericDao<JpoShoppingCartOrder, Long> {
	public  JpoShoppingCartOrder getJpoShoppingCartOrder(JpoShoppingCart JpoShoppingCart);
	public  List<JpoShoppingCartOrder>  getMoilbeJpoShoppingCartOrder(JpoShoppingCart JpoShoppingCart);
	public List getJpoShoppingCartBindingProduct(JpoShoppingCart JpoShoppingCart);
	public List<JpoShoppingCartOrder> getJpoScOrderList(JpoShoppingCart jpoShoppingCart);
	public int getShoppinigCartSum(JpoShoppingCart JpoShoppingCart);
	public void deleMobileJpoShoppingCartOrder(long sc_id);
	
}