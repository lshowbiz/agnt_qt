package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JalDistrict;

/**
 * An interface that provides a data management interface to the JalDistrict table.
 */
public interface JalDistrictDao extends GenericDao<JalDistrict, Long> {

	public List getAlDistrictByCityId(Long cityId);
}