package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JsysRole;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface JsysRoleManager extends GenericManager<JsysRole, Long> {

    /**
     * 根据角色编码获取对应的角色记录
     * @param roleCode
     * @return
     */
    public JsysRole getSysRoleByCode(final String roleCode);
}