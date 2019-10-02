package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JalCity;

/**
 * An interface that provides a data management interface to the JalCity table.
 */
public interface JalCityDao extends GenericDao<JalCity, Long> {

    public List getAlCityByProvinceId(Long provinceId);
}