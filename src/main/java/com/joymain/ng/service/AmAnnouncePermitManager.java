package com.joymain.ng.service;

import java.util.List;

import javax.jws.WebService;

import com.joymain.ng.model.AmAnnouncePermit;

@WebService
public interface AmAnnouncePermitManager extends GenericManager<AmAnnouncePermit,String>{
	/**
	 * find AmAnnouncePermit in announce
	 * @param aaNo
	 * @param permitNo
	 * @return list
	 */
	public List<AmAnnouncePermit> findAnnouncePermitInAnnounce(String aaNo,String permitNo);
}
