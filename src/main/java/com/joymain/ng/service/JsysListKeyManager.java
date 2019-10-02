package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JsysListKey;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface JsysListKeyManager extends GenericManager<JsysListKey, Long> {

	List getSysListBySQL();

    public JsysListKey getListKeyByCode(String listCode);
}