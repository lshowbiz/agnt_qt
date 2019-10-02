package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.PdShUrl;

/**
 * An interface that provides a data management interface to the PdShUrl table.
 */
public interface PdShUrlDao extends GenericDao<PdShUrl, Long> {

	/**
	 * 根据物流公司编码获取物流公司链接地址
	 * @author gw 2014-07-11
	 * @param shNo 物流公司编码
	 * @return string 
	 */
	String getShUrl(String shNo);

}