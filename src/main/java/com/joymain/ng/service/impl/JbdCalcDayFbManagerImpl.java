package com.joymain.ng.service.impl;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JbdCalcDayFbDao;
import com.joymain.ng.model.JbdCalcDayFb;
import com.joymain.ng.service.JbdCalcDayFbManager;
import com.joymain.ng.util.GroupPage;

@Service("jbdCalcDayFbManager")
@WebService(serviceName = "jbdCalcDayFbService", endpointInterface = "com.joymain.ng.service.JbdCalcDayFbManager")
public class JbdCalcDayFbManagerImpl extends GenericManagerImpl<JbdCalcDayFb, Long> implements JbdCalcDayFbManager {
	private JbdCalcDayFbDao jbdCalcDayFbDao;
	@Autowired
    public JbdCalcDayFbManagerImpl(JbdCalcDayFbDao jbdCalcDayFbDao) {
        super(jbdCalcDayFbDao);
        this.jbdCalcDayFbDao = jbdCalcDayFbDao;
    }
	
	public List getJbdCalcDayFbsPage(Map<String, String> params, GroupPage page){
		return jbdCalcDayFbDao.getJbdCalcDayFbsPage(params, page);
	}
}