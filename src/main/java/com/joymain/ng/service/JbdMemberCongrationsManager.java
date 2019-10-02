package com.joymain.ng.service;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.joymain.ng.model.JbdMemberCongrations;
import com.joymain.ng.util.GroupPage;
@WebService
public interface JbdMemberCongrationsManager extends GenericManager<JbdMemberCongrations, Long> {
    
    public List getJbdMemberCongrationsPage(Map<String,String> params, GroupPage page);
    
	
}