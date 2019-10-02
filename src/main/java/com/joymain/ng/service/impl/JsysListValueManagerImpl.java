package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JsysListValueDao;
import com.joymain.ng.model.JsysListValue;
import com.joymain.ng.service.JsysListValueManager;
import com.joymain.ng.service.impl.GenericManagerImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import javax.jws.WebService;

@Service("jsysListValueManager")
@WebService(serviceName = "JsysListValueService", endpointInterface = "com.joymain.ng.service.JsysListValueManager")
public class JsysListValueManagerImpl extends GenericManagerImpl<JsysListValue, Long> implements JsysListValueManager {
	
	JsysListValueDao jsysListValueDao;

    @Autowired
    public JsysListValueManagerImpl(JsysListValueDao jsysListValueDao) {
        super(jsysListValueDao);
        this.jsysListValueDao = jsysListValueDao;
    }
	/**
	 * 根据List编码获取对应的List值列表
	 * @param listCode
	 * @param companyCode
	 * @return
	 */
	public List getSysListValuesByCode(final String listCode, final String companyCode){
		return jsysListValueDao.getSysListValuesByCode(listCode, companyCode);
	}
}