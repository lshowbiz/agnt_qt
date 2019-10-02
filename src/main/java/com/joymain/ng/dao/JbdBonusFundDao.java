package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.JbdBonusFund;

/**
 * An interface that provides a data management interface to the JbdBonusFund table.
 */
public interface JbdBonusFundDao extends GenericDao<JbdBonusFund, Long> {

    public JbdBonusFund getJbdBonusFundByUserCode(String userCode);
}