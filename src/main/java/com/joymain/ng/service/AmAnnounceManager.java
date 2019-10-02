package com.joymain.ng.service;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.persistence.Column;

import com.joymain.ng.model.AmAnnounce;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.util.GroupPage;

@WebService
public interface AmAnnounceManager extends GenericManager<AmAnnounce,String>{
	/**
	 * find all announce
	 * @return list
	 */
	public List<AmAnnounce> findAllAnnounce();
	
	/**
	 * find all announce 查询指定条件的数据
	 * @return list
	 */
	public List<AmAnnounce> findAllAnnounce(String userCode);
	/**
	 * find announce by rowNum
	 * @param rownum
	 * @return list
	 */
	public List<AmAnnounce> findAnnounceByRowNum(int rownum);
	/**
	 * get Announce by id
	 * @param aaNo
	 * @return AmAnnounce
	 */
	public AmAnnounce getAnnounceById(String aaNo);
	/**
	 * find Announce by Column
	 * @param columMap
	 * @return list
	 */
	public List<AmAnnounce> findAnnounceByColum(Map<String,String> columMap);
	
	/**
	 * 根据页码获取公告记录条数
	 * @param pageNum
	 * @return
	 */
	public int findAnnounceCount(Map map) ;
	
	/**
	 * 根据页码获取公告
	 * @param pageNum
	 * @return
	 */

	public List findAnnouncePage(GroupPage page,Map map) ;
	public int countNotReadAnnounce(Map map);
	
	/**
	 * 根据aaNo 获取公告
	 * @
	 */
	public AmAnnounce findAnnounceByaaNo(String aaNo);

	public Map getSearchAnnounceMap(JsysUser defSysUser);
}