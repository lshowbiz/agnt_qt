package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JmiRecommendRef;

/**
 * An interface that provides a data management interface to the JmiRecommendRef table.
 */
public interface JmiRecommendRefDao extends GenericDao<JmiRecommendRef, String> {

    public JmiRecommendRef getNewMiLinkRefManagersByRecommendNo(JmiRecommendRef jmiRecommendRef) ;
    public List getJmiRecommendRefbyRecommendNo(String recommendNo,String orderByName,String sort);
    
	/**
	 * 获取某推荐下的会员
	 * @param linkNo
	 */
	public List getJmiRecommendRefsByRecommendNo(String recommendNo);
}