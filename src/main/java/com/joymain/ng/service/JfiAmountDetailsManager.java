package com.joymain.ng.service;

import javax.jws.WebService;

import com.joymain.ng.model.JfiAmountDetails;
@WebService
public interface JfiAmountDetailsManager extends GenericManager<JfiAmountDetails, Long> {
	/**
	 * 1.获得商户号支付明细对象
	 * @author WuCF 2016-02-17
	 * @param id
	 * @return
	 */
	public JfiAmountDetails getJfiAmountDetails(String id);
	
	/**
	 * 2.保存商户号支付明细对象
	 * @author WuCF 2016-02-17
	 * @param id
	 * @return
	 */
	public void saveJfiAmountDetails(JfiAmountDetails jfiAmountDetails);
}