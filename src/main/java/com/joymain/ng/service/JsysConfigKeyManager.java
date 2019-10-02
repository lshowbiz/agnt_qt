package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JsysConfigKey;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface JsysConfigKeyManager extends GenericManager<JsysConfigKey, Long> {
    
}