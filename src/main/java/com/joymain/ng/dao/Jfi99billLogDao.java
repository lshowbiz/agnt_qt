package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.Jfi99billLog;

/**
 * An interface that provides a data management interface to the Jfi99billLog table.
 */
public interface Jfi99billLogDao extends GenericDao<Jfi99billLog, Long> {

	public List getJfi99billLogs(Jfi99billLog jfi99billLog);
	
	public List getSuccessJfi99billLogsByConditions(String dealId);
	
	// 提供给手机查看支付记录
	public List getJfi99billLogsByMobile(Jfi99billLog jfi99billLog,String startCreateTime,String endCreateTime,int pageNum,int pageSize);
}