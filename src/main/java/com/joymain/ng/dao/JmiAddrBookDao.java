package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JmiAddrBook;

/**
 * An interface that provides a data management interface to the JmiAddrBook table.
 */
public interface JmiAddrBookDao extends GenericDao<JmiAddrBook, Long> {

    public List getJmiAddrBookByUserCode(String userCode);
    public List getJmiAddrBookByUserCodeBySql(String userCode);
    // 获取默认地址方法
    public JmiAddrBook getDefaultJmiAddrBookByUserCode(String userCode);
}