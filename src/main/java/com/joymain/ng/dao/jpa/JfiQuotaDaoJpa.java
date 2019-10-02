package com.joymain.ng.dao.jpa;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JfiQuotaDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JfiQuota;

@Repository("jfiQuotaDao")
@SuppressWarnings("unchecked")
public class JfiQuotaDaoJpa extends GenericDaoHibernate<JfiQuota, Long> implements JfiQuotaDao {

	public JfiQuotaDaoJpa() {
		super(JfiQuota.class);
	}

	/**
	 * 1.获得商户号限额表对象
	 * 
	 * @author gw 2013-08-14
	 * @param id
	 * @return
	 */
	public JfiQuota getJfiQuota(String quotaId) {
		String hql = " from JfiQuota where quotaId = '" + quotaId + "'";
		return (JfiQuota) this.getObjectByHqlQuery(hql);
	}

	public JfiQuota getJfiQuota(JfiQuota entity) {
		String hql = " from JfiQuota where 1=1";
		if (entity.getAccountId() != null) {
			hql += " and accountId=" + entity.getAccountId();
		}
		if (entity.getStatus() != null) {
			hql += " and status=" + entity.getStatus();
		}
		if (entity.getValidityPeriod() != null) {
			hql += " and validityPeriod='" + entity.getValidityPeriod() + "'";
		}
		return (JfiQuota) this.getObjectByHqlQuery(hql);
	}

	/**
	 * 获取某个商户号的某个財月到已经收到多少钱
	 * @param entity
	 * @return
	 */
	public Map<String, BigDecimal> getSumMoney(JfiQuota entity) {
		String sql = "select  (maxMoney - sumMoney) balance,sumMoney,maxMoney from (" +
				"select sum(b.money) sumMoney,avg(a.max_money) maxMoney from  jfi_Quota a,jfi_Amount_detail b where a.quota_id=b.quota_id and a.status = 0 " +
				" and a.validity_period='" + entity.getValidityPeriod() + "' and a.account_id =" + entity.getAccountId() +")";
		return this.findOneObjectBySQL(sql);
	}

	/**
	 * 2.保存限额表对象
	 * 
	 * @author WuCF 2016-02-17
	 * @param id
	 * @return
	 */
	public void saveJfiQuota(JfiQuota jfiQuota) {
		this.save(jfiQuota);
	}
}
