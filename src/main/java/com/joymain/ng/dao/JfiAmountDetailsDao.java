package com.joymain.ng.dao;

import com.joymain.ng.model.JfiAmountDetails;

/**
 * An interface that provides a data management interface to the JfiQuota table.
 */
public interface JfiAmountDetailsDao extends GenericDao<JfiAmountDetails, Long> {
	
	/**
	 * 1.获得商户号支付明细对象
	 * @author WuCF 2016-02-17
	 * @param id
	 * @return
	 */
	public JfiAmountDetails getJfiAmountDetails(String id);
	
	/**
	 * 2.保存商户号支付明细对象
	 * @author WuCF 2016-02-17
	 * @param id
	 * @return
	 */
	public void saveJfiAmountDetails(JfiAmountDetails jfiAmountDetails);
}