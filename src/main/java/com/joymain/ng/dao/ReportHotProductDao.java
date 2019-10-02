package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.ReportHotProduct;

/**
 * An interface that provides a data management interface to the ReportHotProduct table.
 */
public interface ReportHotProductDao extends GenericDao<ReportHotProduct, Long> {

	public List getHotProductReport(String jperiod, String province, String city);
}