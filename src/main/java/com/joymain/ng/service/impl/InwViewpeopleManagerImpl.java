package com.joymain.ng.service.impl;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.InwViewpeopleDao;
import com.joymain.ng.model.InwViewpeople;
import com.joymain.ng.service.InwViewpeopleManager;

@Service("inwViewpeopleManager")
@WebService(serviceName = "inwViewpeopleService", endpointInterface = "com.joymain.ng.service.InwViewpeopleManager")
public class InwViewpeopleManagerImpl extends GenericManagerImpl<InwViewpeople, Long> implements InwViewpeopleManager{
	public InwViewpeopleDao inwViewpeopleDao;

	@Autowired
	public InwViewpeopleManagerImpl(InwViewpeopleDao inwViewpeopleDao){
		super(inwViewpeopleDao);
		this.inwViewpeopleDao = inwViewpeopleDao;
	}
	
	
}
