package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JsysListKeyDao;
import com.joymain.ng.model.JsysListKey;
import com.joymain.ng.service.JsysListKeyManager;
import com.joymain.ng.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("jsysListKeyManager")
@WebService(serviceName = "JsysListKeyService", endpointInterface = "com.joymain.ng.service.JsysListKeyManager")
public class JsysListKeyManagerImpl extends GenericManagerImpl<JsysListKey, Long> implements JsysListKeyManager {
    JsysListKeyDao jsysListKeyDao;

    @Autowired
    public JsysListKeyManagerImpl(JsysListKeyDao jsysListKeyDao) {
        super(jsysListKeyDao);
        this.jsysListKeyDao = jsysListKeyDao;
    }

	@Override
	public List getSysListBySQL() {
		// TODO Auto-generated method stub
		return jsysListKeyDao.getSysListBySQL();
	}

	@Override
	public JsysListKey getListKeyByCode(String listCode) {
		
		return jsysListKeyDao.getListKeyByCode(listCode);
	}
    
}