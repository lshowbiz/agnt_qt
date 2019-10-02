package com.joymain.ng.service.impl;

import java.math.BigDecimal;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JfiQuotaDao;
import com.joymain.ng.model.JfiQuota;
import com.joymain.ng.service.JfiQuotaManager;

@Service("jfiQuotaManager")
@WebService(serviceName = "jfiQuotaManager", endpointInterface = "com.joymain.ng.service.JfiQuotaManager")
public class JfiQuotaManagerImpl extends GenericManagerImpl<JfiQuota, Long> implements JfiQuotaManager {
	
	private JfiQuotaDao jfiQuotaDao;
	@Autowired
    public JfiQuotaManagerImpl(JfiQuotaDao jfiQuotaDao){
		super(jfiQuotaDao);
		this.jfiQuotaDao = jfiQuotaDao;
	}
	
	public JfiQuotaDao getJfiQuotaDao() {
		return jfiQuotaDao;
	}

	/**
	 * 1.获得商户号限额表对象
	 * @author WuCF 2016-02-17
	 * @param id
	 * @return
	 */
	public JfiQuota getJfiQuota(String quotaId){
		return jfiQuotaDao.getJfiQuota(quotaId);
	}
	
	public JfiQuota getJfiQuota(JfiQuota entity){
		return jfiQuotaDao.getJfiQuota(entity);
	}
	
	/**
	 * 获取某个商户号的某个財月到已经收到多少钱
	 * @param entity
	 * @return
	 */
	public Map<String, BigDecimal> getSumMoney(JfiQuota entity){
		return jfiQuotaDao.getSumMoney(entity);
	}
	
	
	/**
	 * 2.保存限额表对象
	 * @author WuCF 2016-02-17
	 * @param id
	 * @return
	 */
	public void saveJfiQuota(JfiQuota jfiQuota){
		jfiQuotaDao.saveJfiQuota(jfiQuota);
	}
}