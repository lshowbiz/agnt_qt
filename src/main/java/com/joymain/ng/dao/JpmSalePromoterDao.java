package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JpmSalePromoter;

/**
 * An interface that provides a data management interface to the JpmSalePromoter table.
 */
public interface JpmSalePromoterDao extends GenericDao<JpmSalePromoter, Long> {
	
	public List<JpmSalePromoter> getSaleByDate(String stime, String isActiva);
}