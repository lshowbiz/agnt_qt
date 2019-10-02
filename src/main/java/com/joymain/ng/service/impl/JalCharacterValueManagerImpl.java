package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JalCharacterValueDao;
import com.joymain.ng.model.JalCharacterValue;
import com.joymain.ng.service.JalCharacterValueManager;
import com.joymain.ng.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("jalCharacterValueManager")
@WebService(serviceName = "JalCharacterValueService", endpointInterface = "com.joymain.ng.service.JalCharacterValueManager")
public class JalCharacterValueManagerImpl extends GenericManagerImpl<JalCharacterValue, Long> implements JalCharacterValueManager {
    JalCharacterValueDao jalCharacterValueDao;

    @Autowired
    public JalCharacterValueManagerImpl(JalCharacterValueDao jalCharacterValueDao) {
        super(jalCharacterValueDao);
        this.jalCharacterValueDao = jalCharacterValueDao;
    }

	@Override
	public List getAlCharacterValuesByCodeSQL(String characterCode) {
		// TODO Auto-generated method stub
		return jalCharacterValueDao.getAlCharacterValuesByCodeSQL(characterCode);
	}
    
}