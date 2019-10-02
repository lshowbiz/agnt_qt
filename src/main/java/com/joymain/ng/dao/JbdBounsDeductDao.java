package com.joymain.ng.dao;

import java.util.List;
import java.util.Map;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JbdBounsDeduct;

/**
 * An interface that provides a data management interface to the JbdBounsDeduct table.
 */
public interface JbdBounsDeductDao extends GenericDao<JbdBounsDeduct, Long> {

    public List getJbdBounsDeduct(Map map);
}