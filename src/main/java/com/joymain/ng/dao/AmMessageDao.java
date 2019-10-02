package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.model.AmMessage;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the AmMessage table.
 */
public interface AmMessageDao extends GenericDao<AmMessage, Long> {
	/**
	 * find message By userCode
	 * @param userCode
	 * @return list
	 */
	public List findMessageByUserCode(String userCode,String companyCode,String msgClassNo,String type);
	
	/**
	 * find message By userCode
	 * @param userCode
	 * @return list
	 */
	public List findMessageByUserCodePage(GroupPage page,String userCode,String companyCode,String msgClassNo,String type);
	
	public List<AmMessage> findMessage(String msgClassNo,String status,String userCode);
	
	public List<AmMessage> ascfindMessage(String msgClassNo,String status,String userCode);
	public Integer getNoReadReply(String userCode,String companyCode,String msgClassNo);
		
}