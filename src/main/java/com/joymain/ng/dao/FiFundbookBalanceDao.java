package com.joymain.ng.dao;

import org.hibernate.LockMode;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.FiFundbookBalance;

/**
 * An interface that provides a data management interface to the FiFundbookBalance table.
 */
public interface FiFundbookBalanceDao extends GenericDao<FiFundbookBalance, Long> {
	
	public FiFundbookBalance getFiFundbookBalance(final String userCode,final String backbookType);
	
	/**
     * 获取银行记录
     * @param UserCode
     * @param FundbookType
     * @return
     */
    public FiFundbookBalance getFiFundbookBalanceByUserCodeAndFundbookType(final String userCode, final String bankbookType);
    
    /**
     * 锁定一行表记录
     * @param fbbId
     * @return
     */
    public FiFundbookBalance getFiFundbookBalanceForUpdate(final Long fbbId);
}