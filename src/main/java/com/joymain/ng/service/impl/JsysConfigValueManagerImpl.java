package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JsysConfigValueDao;
import com.joymain.ng.model.JsysConfigValue;
import com.joymain.ng.service.JsysConfigValueManager;
import com.joymain.ng.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("jsysConfigValueManager")
@WebService(serviceName = "JsysConfigValueService", endpointInterface = "com.joymain.ng.service.JsysConfigValueManager")
public class JsysConfigValueManagerImpl extends GenericManagerImpl<JsysConfigValue, Long> implements JsysConfigValueManager {
    JsysConfigValueDao jsysConfigValueDao;

    @Autowired
    public JsysConfigValueManagerImpl(JsysConfigValueDao jsysConfigValueDao) {
        super(jsysConfigValueDao);
        this.jsysConfigValueDao = jsysConfigValueDao;
    }

	@Override
	public List getSysConfigValuesByCodeSQL(String companyCode) {
		// TODO Auto-generated method stub
		return jsysConfigValueDao.getSysConfigValuesByCodeSQL(companyCode);
	}
    
}