package com.joymain.ng.service.impl;

import java.util.List;

import com.joymain.ng.dao.JfiChanjetLogDao;
import com.joymain.ng.model.JfiChanjetLog;
import com.joymain.ng.service.JfiChanjetLogManager;
import com.joymain.ng.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

@Service("jfiChanjetLogManager")
@WebService(serviceName = "JfiChanjetLogService", endpointInterface = "com.joymain.ng.service.JfiChanjetLogManager")
public class JfiChanjetLogManagerImpl extends GenericManagerImpl<JfiChanjetLog, Long> implements JfiChanjetLogManager {
    JfiChanjetLogDao jfiChanjetLogDao;

    @Autowired
    public JfiChanjetLogManagerImpl(JfiChanjetLogDao jfiChanjetLogDao) {
        super(jfiChanjetLogDao);
        this.jfiChanjetLogDao = jfiChanjetLogDao;
    }

	@Override
	public List getJfiChanjetLogsByDealId(String dealId) {
		// TODO Auto-generated method stub
		return jfiChanjetLogDao.getJfiChanjetLogsByDealId(dealId);
	}
}