package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.JalStateProvince;

/**
 * An interface that provides a data management interface to the JalStateProvince table.
 */
public interface JalStateProvinceDao extends GenericDao<JalStateProvince, Long> {
	public List getJalStateProvinceByCountryCode(String countryCode);
	
	public JalStateProvince getJalStateProvinceByProvinceCode(String stateProvinceCode);
}