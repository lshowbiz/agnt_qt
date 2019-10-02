package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JsysConfigKeyDao;
import com.joymain.ng.model.JsysConfigKey;
import com.joymain.ng.service.JsysConfigKeyManager;
import com.joymain.ng.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("jsysConfigKeyManager")
@WebService(serviceName = "JsysConfigKeyService", endpointInterface = "com.joymain.ng.service.JsysConfigKeyManager")
public class JsysConfigKeyManagerImpl extends GenericManagerImpl<JsysConfigKey, Long> implements JsysConfigKeyManager {
    JsysConfigKeyDao jsysConfigKeyDao;

    @Autowired
    public JsysConfigKeyManagerImpl(JsysConfigKeyDao jsysConfigKeyDao) {
        super(jsysConfigKeyDao);
        this.jsysConfigKeyDao = jsysConfigKeyDao;
    }
}