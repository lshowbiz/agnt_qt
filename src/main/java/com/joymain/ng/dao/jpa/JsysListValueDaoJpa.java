package com.joymain.ng.dao.jpa;

import java.util.List;

import com.joymain.ng.model.JsysListValue;
import com.joymain.ng.dao.JsysListValueDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("jsysListValueDao")
public class JsysListValueDaoJpa extends GenericDaoHibernate<JsysListValue, Long> implements JsysListValueDao {

    public JsysListValueDaoJpa() {
        super(JsysListValue.class);
    }
	/**
	 * 根据List编码获取对应的List值列表
	 * @param listCode
	 * @param companyCode
	 * @return
	 */
	public List getSysListValuesByCode(final String listCode, final String companyCode){
		String hql="from JsysListValue where jsysListKey.listCode='"+listCode+"' and (exCompanyCode is null or INSTR(exCompanyCode,'"+companyCode+"',1,1)=0)   order by orderNo";
		Query q=getSession().createQuery(hql);
		return q.list();
    }
}
