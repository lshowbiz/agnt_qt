package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JpoShoppingCart;
import com.joymain.ng.model.JpoShoppingCartOrder;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import javax.jws.WebService;
import java.util.Collection;
import java.util.List;
@WebService
public interface JpoShoppingCartOrderManager extends GenericManager<JpoShoppingCartOrder, Long> {
    
	public Pager<JpoShoppingCartOrder> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	
	public String  addProductToShoppingCart(JpoShoppingCart JpoShoppingCart,String token);
	public int  getShoppinigCartSum(JpoShoppingCart JpoShoppingCart);
	public List<JpoShoppingCartOrder> getJpoScOrderList(JpoShoppingCart JpoShoppingCart);
	public JpoShoppingCartOrder getJpoShoppingCartOrder(JpoShoppingCart JpoShoppingCart);
	public List<JpoShoppingCartOrder> getMobileJpoShoppingCartOrder(JpoShoppingCart JpoShoppingCart);
	public void deleMobileJpoShoppingCartOrder(long sc_id);
	
	public int underwearReminder(String unino);
}