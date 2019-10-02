package com.joymain.ng.service;

import java.util.List;

import javax.jws.WebService;

import com.joymain.ng.model.JbdMemberFrozen;
@WebService
public interface JbdMemberFrozenManager extends GenericManager<JbdMemberFrozen, String> {
    
    public List getJbdMemberFrozen();
    
	
}