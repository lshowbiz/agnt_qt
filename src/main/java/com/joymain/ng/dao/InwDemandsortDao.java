package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.InwDemandsort;

/**
 * An interface that provides a data management interface to the InwDemandsort table.
 */
public interface InwDemandsortDao extends GenericDao<InwDemandsort, Long> {
	
	/**
     * 需求分类初始化查询(这个查询时没有任何查询条件的)
     * @author gw 2013-11-06
     * @param  request
     * @return  list
     */
	List getDemandsortList();

	/**
     * 查询该需求大类上的所有小类的集合
     * @author gw 2013-11-06
     * @param  request
     * @return  list
     */
	List getDemandsortDetailList(String id);


	/**
	 * 创新共赢---需求分类-----获取需求分类的分类名称
	 * @author gw  2013-11-08
	 * @param demandsort_id
	 * @return string
	 */
	String getInwDemandsortById(String demandsortId);

	

}