package com.joymain.ng.dao;

import java.math.BigDecimal;
import java.util.List;

import com.joymain.ng.model.FiTransferAccount;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the FiTransferAccount table.
 */
public interface FiTransferAccountDao extends GenericDao<FiTransferAccount, Long> {

	public List getFiTransferAccountListByUserCode(String userCode,String dealStartTime,String dealEndTime);
	
	public List getFiTransferAccountListByUserCodePage(GroupPage page,String userCode,String dealStartTime,String dealEndTime);
	
	public BigDecimal getSumTransferValueByTransferCode(final String transferCode);
	
	/**
	 * Add By WuCF 20160824 保存转账信息并返回主键唯一值 
	 * @param fiTransferAccount
	 * @return
	 */
	public String saveFiTransferAccount(FiTransferAccount fiTransferAccount);
}