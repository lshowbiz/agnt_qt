package com.joymain.ng.dao;

import com.joymain.ng.model.JsysStockAccount;

/**
 * An interface that provides a data management interface to the JsysStockAccount table.
 */
public interface JsysStockAccountDao extends GenericDao<JsysStockAccount, Long> {
	/**
	 * 根据id获取港股账号信息
	 * @param id
	 * @return
	 */
	public JsysStockAccount getJsysStockAccountById(Long id);

    /**
     * 根据会员编号获取港股账号信息
     * @param roleCode
     * @return
     */
    public JsysStockAccount getJsysStockAccountByUserCode(String userCode);
    
    /**
     * 保存或修改港股账号信息
     * @param jsysStockAccount
     */
    public void saveOrUpdate(JsysStockAccount jsysStockAccount);
}