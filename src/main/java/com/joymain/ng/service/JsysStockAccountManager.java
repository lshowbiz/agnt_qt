package com.joymain.ng.service;

import javax.jws.WebService;

import com.joymain.ng.model.JsysStockAccount;

@WebService
public interface JsysStockAccountManager extends GenericManager<JsysStockAccount, Long> {
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