package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.MailStatus;
import com.joymain.ng.model.PdLogisticsBaseNum;

/**
 * An interface that provides a data management interface to the MailStatus table.
 */
public interface MailStatusDao extends GenericDao<MailStatus, Long> {

	/**
	 * @author gw 2014-11-24-----------------------------------------需求变更后，这个方法弃用20150806
	 * 物流跟踪查询-保存最后一次的物流查询信息
	 */
	boolean saveMailStatus(MailStatus mailStatus);

	/**
	 * 根据物流单号查询物流实时信息----------------------------有用方法20150806
	 * @author gw 2015-06-17
	 * @param mailNo
	 * @param pdLogisticsBaseNum
	 * @return
	 */
	public MailStatus getMailStatusByMailNo(String mailNo,PdLogisticsBaseNum pdLogisticsBaseNum);

}