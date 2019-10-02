package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JsysRoleDao;
import com.joymain.ng.model.JsysRole;
import com.joymain.ng.service.JsysRoleManager;
import com.joymain.ng.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("jsysRoleManager")
@WebService(serviceName = "JsysRoleService", endpointInterface = "com.joymain.ng.service.JsysRoleManager")
public class JsysRoleManagerImpl extends GenericManagerImpl<JsysRole, Long> implements JsysRoleManager {

    JsysRoleDao jsysRoleDao;

    @Autowired
    public JsysRoleManagerImpl(JsysRoleDao jsysRoleDao) {
        super(jsysRoleDao);
        this.jsysRoleDao = jsysRoleDao;
    }

	public JsysRole getSysRoleByCode(String roleCode) {
		return jsysRoleDao.getSysRoleByCode(roleCode);
	}
}