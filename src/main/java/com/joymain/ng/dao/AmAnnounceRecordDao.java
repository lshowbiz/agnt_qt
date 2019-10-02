package com.joymain.ng.dao;

import com.joymain.ng.model.AmAnnounceRecord;

public interface AmAnnounceRecordDao extends GenericDao<AmAnnounceRecord,String> {
	/**
	 * get amannounceRecord by userNo and aaNo
	 * @param userNo
	 * @param aaNo
	 * @return AmAnnounceRecord
	 */
	public AmAnnounceRecord getRecordByUserNo(String userNo,String aaNo);
}
