package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JalCharacterKeyDao;
import com.joymain.ng.model.JalCharacterKey;
import com.joymain.ng.service.JalCharacterKeyManager;
import com.joymain.ng.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("jalCharacterKeyManager")
@WebService(serviceName = "JalCharacterKeyService", endpointInterface = "com.joymain.ng.service.JalCharacterKeyManager")
public class JalCharacterKeyManagerImpl extends GenericManagerImpl<JalCharacterKey, Long> implements JalCharacterKeyManager {
    JalCharacterKeyDao jalCharacterKeyDao;

    @Autowired
    public JalCharacterKeyManagerImpl(JalCharacterKeyDao jalCharacterKeyDao) {
        super(jalCharacterKeyDao);
        this.jalCharacterKeyDao = jalCharacterKeyDao;
    }
}