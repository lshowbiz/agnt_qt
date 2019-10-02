package com.joymain.ng.service;

import java.util.List;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JfiChanjetLog;

import javax.jws.WebService;

@WebService
public interface JfiChanjetLogManager extends GenericManager<JfiChanjetLog, Long> {
    
	public List getJfiChanjetLogsByDealId(String dealId);
}