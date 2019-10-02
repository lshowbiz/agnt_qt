package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JsysConfigValue;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface JsysConfigValueManager extends GenericManager<JsysConfigValue, Long> {

	List getSysConfigValuesByCodeSQL(String companyCode);
    
}