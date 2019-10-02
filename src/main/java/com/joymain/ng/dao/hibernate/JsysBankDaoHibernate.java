package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.JsysBank;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.dao.JsysBankDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jsysBankDao")
public class JsysBankDaoHibernate extends GenericDaoHibernate<JsysBank, Long> implements JsysBankDao {

    public JsysBankDaoHibernate() {
        super(JsysBank.class);
    }
	public List getSysBankByCompanyCode(String companyCode) {
		String hql="from JsysBank where 1=1";
		if (!StringUtil.isEmpty(companyCode)&&!"AA".equals(companyCode)) {
			hql += " and companyCode ='" + companyCode + "'";
		}
		hql+=" order by orderNo";
		return this.getSession().createQuery(hql).list();
	}
}
