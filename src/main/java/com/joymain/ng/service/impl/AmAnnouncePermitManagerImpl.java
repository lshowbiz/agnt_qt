package com.joymain.ng.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.AmAnnouncePermitDao;
import com.joymain.ng.model.AmAnnouncePermit;
import com.joymain.ng.service.AmAnnouncePermitManager;

@Service("amAnnouncePermitManager")
@WebService(serviceName = "amAnnouncePermitService", endpointInterface = "com.joymain.ng.service.AmAnnouncePermitManager")
public class AmAnnouncePermitManagerImpl extends GenericManagerImpl<AmAnnouncePermit, String> implements AmAnnouncePermitManager {
	
	@Autowired
	AmAnnouncePermitDao amAnnouncePermitDao;
	
	public List<AmAnnouncePermit> findAnnouncePermitInAnnounce(String aaNo,String permitNo) {
		
		return amAnnouncePermitDao.findAnnouncePermitInAnnounce(aaNo, permitNo);
	}

}
