package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JmiPrizeTourism;

/**
 * An interface that provides a data management interface to the JmiPrizeTourism table.
 */
public interface JmiPrizeTourismDao extends GenericDao<JmiPrizeTourism, String> {
	/**
	 * 根据会员编号查询奖游信息
	 * @param userCode
	 * @return
	 */
	public JmiPrizeTourism getJmiPrizeTourismByUsercode(String userCode);
	
	/**
	 * 保存
	 * @param jmiPrizeTourism
	 */
	public void saveJmiPrizeTourism(JmiPrizeTourism jmiPrizeTourism);
	
	public String getPassStarByUserCode(String userCode);
	
	public String geIspeerByUserCode(String userCode);
}