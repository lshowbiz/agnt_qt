package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JalCompany;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface JalCompanyManager extends GenericManager<JalCompany, Long> {
	
	/**
	 * 根据公司编码获取对应的公司资料
	 * @param companyCode
	 * @return
	 */
	public JalCompany getAlCompanyByCode(final String companyCode);
}