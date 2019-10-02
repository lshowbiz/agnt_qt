package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JalCharacterCodingDao;
import com.joymain.ng.model.JalCharacterCoding;
import com.joymain.ng.service.JalCharacterCodingManager;
import com.joymain.ng.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("jalCharacterCodingManager")
@WebService(serviceName = "JalCharacterCodingService", endpointInterface = "com.joymain.ng.service.JalCharacterCodingManager")
public class JalCharacterCodingManagerImpl extends GenericManagerImpl<JalCharacterCoding, Long> implements JalCharacterCodingManager {
    JalCharacterCodingDao jalCharacterCodingDao;

    @Autowired
    public JalCharacterCodingManagerImpl(JalCharacterCodingDao jalCharacterCodingDao) {
        super(jalCharacterCodingDao);
        this.jalCharacterCodingDao = jalCharacterCodingDao;
    }
}