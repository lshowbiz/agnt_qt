package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.model.AmAnnouncePermit;

public interface AmAnnouncePermitDao extends GenericDao<AmAnnouncePermit, String>{
	/**
	 * find announcePermit by aano and permitNo in permit permitClass
	 * @param aaNo
	 * @param permitClass
	 * @return list
	 */
	public List<AmAnnouncePermit> findAnnouncePermitInAnnounce(String aaNo,String permitClass);
}
