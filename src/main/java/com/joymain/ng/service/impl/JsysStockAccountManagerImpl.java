package com.joymain.ng.service.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JsysStockAccountDao;
import com.joymain.ng.model.JsysStockAccount;
import com.joymain.ng.service.JsysStockAccountManager;

@Service("jsysStockAccountManager")
@WebService(serviceName = "jsysStockAccountService", endpointInterface = "com.joymain.ng.service.JsysStockAccountManager")
public class JsysStockAccountManagerImpl extends GenericManagerImpl<JsysStockAccount, Long> implements JsysStockAccountManager {

	JsysStockAccountDao jsysStockAccountDao;

	@Autowired
    public JsysStockAccountManagerImpl(JsysStockAccountDao jsysStockAccountDao) {
        super(jsysStockAccountDao);
        this.jsysStockAccountDao = jsysStockAccountDao;
    }

    /**
	 * 根据id获取港股账号信息
	 * @param id
	 * @return
	 */
	public JsysStockAccount getJsysStockAccountById(Long id) {
		return jsysStockAccountDao.getJsysStockAccountById(id);
	}

    /**
     * 根据会员编号获取港股账号信息
     * @param userCode
     * @return
     */
	public JsysStockAccount getJsysStockAccountByUserCode(String userCode) {
		return jsysStockAccountDao.getJsysStockAccountByUserCode(userCode);
	}

	/**
     * 保存或修改港股账号信息
     * @param jsysStockAccount
     */
	public void saveOrUpdate(JsysStockAccount jsysStockAccount) {
		jsysStockAccountDao.saveOrUpdate(jsysStockAccount);
	}

	
}