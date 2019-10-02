package com.joymain.ng.service;

import javax.jws.WebService;

import com.joymain.ng.model.AmAnnounceRecord;

@WebService
public interface AmAnnounceRecordManager extends GenericManager<AmAnnounceRecord,String>{
	/**
	 * get amannounceRecord by userNo and aaNo
	 * @param userNo
	 * @param aaNo
	 * @return AmAnnounceRecord
	 */
	public AmAnnounceRecord getRecordByUserNo(String userNo,String aaNo);
}
