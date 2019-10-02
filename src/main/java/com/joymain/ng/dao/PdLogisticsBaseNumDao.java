package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.PdLogisticsBaseNum;

/**
 * An interface that provides a data management interface to the PdLogisticsBaseNum table.
 */
public interface PdLogisticsBaseNumDao extends GenericDao<PdLogisticsBaseNum, Long> {

	/**
	 * @author gw 2014-11-11
	 * 报单中心前台-物流跟踪查询 
	 * @param moId
	 * @return
	 */
	List getPdLogisticsBaseNumAndDetail(String moId);

	/**
	 * 根据物流单号获取物流信息
	 * @author gw 2015-02-11
	 * @param mailNo
	 * @return pdLogisticsBaseNum
	 */
	PdLogisticsBaseNum getPdLogisticsBaseNumForMailNo(String mailNo);

}