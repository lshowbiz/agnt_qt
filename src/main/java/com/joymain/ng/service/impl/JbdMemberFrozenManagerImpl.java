package com.joymain.ng.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JbdMemberFrozenDao;
import com.joymain.ng.model.JbdMemberFrozen;
import com.joymain.ng.service.JbdMemberFrozenManager;

@Service("jbdMemberFrozenManager")
@WebService(serviceName = "JbdMemberFrozenManagerService", endpointInterface = "com.joymain.ng.service.JbdMemberFrozenManager")
public class JbdMemberFrozenManagerImpl extends GenericManagerImpl<JbdMemberFrozen, String> implements JbdMemberFrozenManager {

	
	JbdMemberFrozenDao jbdMemberFrozenDao;

	    @Autowired
	    public JbdMemberFrozenManagerImpl(JbdMemberFrozenDao jbdMemberFrozenDao) {
	        super(jbdMemberFrozenDao);
	        this.jbdMemberFrozenDao = jbdMemberFrozenDao;
	    }
	
	public List getJbdMemberFrozen() {
		// TODO Auto-generated method stub
		return this.jbdMemberFrozenDao.getJbdMmeberFrozens();
	}
   


}