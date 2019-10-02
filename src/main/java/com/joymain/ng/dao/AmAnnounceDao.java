package com.joymain.ng.dao;

import java.util.List;
import java.util.Map;

import com.joymain.ng.model.AmAnnounce;
import com.joymain.ng.util.GroupPage;

public interface AmAnnounceDao extends GenericDao<AmAnnounce, String>{
	/**
	 * find announce By date
	 * @param startDate
	 * @param endDate
	 * @return list
	 */
	public List<AmAnnounce> findAnnounceByDate(String startDate,String endDate);
	/**
	 * find All Announce
	 * @return list
	 */
	public List<AmAnnounce> findAllAnnounce();
	
	/**
	 * find All Announce
	 * @return list
	 */
	public List<AmAnnounce> findAllAnnounce(String userCode);
	/**
	 * find Announce by rowNum
	 * @param rownum
	 * @return list
	 */
	public List<AmAnnounce> findAnnounceByRowNum(int rownum);
	/**
	 * find announce by column
	 * @param map
	 * @return list
	 */
	public List<AmAnnounce> findAnnounceByColumn(Map<String,String> map);
	
	/**
	 * 根据页码获取公告记录条数
	 * @param pageNum
	 * @return List
	 */

	public int findAnnounceCount(Map map) ;
	
	/**
	 * 根据页码获取公告
	 * @param pageNum
	 * @return List
	 */

	public List findAnnouncePage(GroupPage page,Map map) ;
	public int countNotReadAnnounce(Map map);
	/**
	 * 根据aaNo 获取公告
	 * @
	 */
	public AmAnnounce findAnnounceByaaNo(String aaNo);
	public List getTeamLeader(String userCode);
}
