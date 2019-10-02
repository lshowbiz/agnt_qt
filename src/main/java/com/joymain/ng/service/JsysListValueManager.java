package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JsysListValue;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface JsysListValueManager extends GenericManager<JsysListValue, Long> {
	/**
	 * 根据List编码获取对应的List值列表
	 * @param listCode
	 * @param companyCode
	 * @return
	 */
	public List getSysListValuesByCode(final String listCode, final String companyCode);
    
}