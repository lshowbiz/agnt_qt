package com.joymain.ng.dao;

import java.math.BigDecimal;
import java.util.Map;

import com.joymain.ng.model.JfiQuota;

/**
 * An interface that provides a data management interface to the JfiQuota table.
 */
public interface JfiQuotaDao extends GenericDao<JfiQuota, Long> {
	/**
	 * 1.获得商户号限额表对象
	 * @author WuCF 2016-02-17
	 * @param id
	 * @return
	 */
	public JfiQuota getJfiQuota(String quotaId);
	
	public JfiQuota getJfiQuota(JfiQuota entity);
	
	/**
	 * 获取某个商户号的某个財月到已经收到多少钱
	 * @param entity
	 * @return
	 */
	public Map<String, BigDecimal> getSumMoney(JfiQuota entity) ;
	
	/**
	 * 2.保存限额表对象
	 * @author WuCF 2016-02-17
	 * @param id
	 * @return
	 */
	public void saveJfiQuota(JfiQuota jfiQuota);
	
}