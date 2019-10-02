package com.joymain.ng.service.impl;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JfiAmountDetailsDao;
import com.joymain.ng.model.JfiAmountDetails;
import com.joymain.ng.service.JfiAmountDetailsManager;

@Service("jfiAmountDetailsManager")
@WebService(serviceName = "jfiAmountDetailsManager", endpointInterface = "com.joymain.ng.service.JfiAmountDetailsManager")
public class JfiAmountDetailsManagerImpl extends GenericManagerImpl<JfiAmountDetails, Long> implements JfiAmountDetailsManager {
	JfiAmountDetailsDao jfiAmountDetailsDao;

	/**
	 * 1.获得商户号支付明细对象
	 * @author WuCF 2016-02-17
	 * @param id
	 * @return
	 */
	public JfiAmountDetails getJfiAmountDetails(String id){
		return jfiAmountDetailsDao.getJfiAmountDetails(id);
	}
	
	/**
	 * 2.保存商户号支付明细对象
	 * @author WuCF 2016-02-17
	 * @param id
	 * @return
	 */
	public void saveJfiAmountDetails(JfiAmountDetails jfiAmountDetails){
		jfiAmountDetailsDao.saveJfiAmountDetails(jfiAmountDetails);
	}
}