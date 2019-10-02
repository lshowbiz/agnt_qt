package com.joymain.ng.service.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joymain.ng.dao.AmAnnounceRecordDao;
import com.joymain.ng.model.AmAnnounceRecord;
import com.joymain.ng.service.AmAnnounceRecordManager;

@Service("amAnnounceRecordManager")
@WebService(serviceName = "amAnnounceRecordService", endpointInterface = "com.joymain.ng.service.AmAnnounceRecordManager")
public class AmAnnounceRecordManagerImpl extends GenericManagerImpl<AmAnnounceRecord, String> implements AmAnnounceRecordManager {
	
	AmAnnounceRecordDao amAnnounceRecordDao;
	
	@Autowired
	public AmAnnounceRecordManagerImpl(AmAnnounceRecordDao amAnnounceRecordDao){
		super(amAnnounceRecordDao);
		this.amAnnounceRecordDao = amAnnounceRecordDao;
	}
	
	@Override
	public AmAnnounceRecord getRecordByUserNo(String userNo, String aaNo) {
		
		return amAnnounceRecordDao.getRecordByUserNo(userNo, aaNo);
	}
	
	

}
