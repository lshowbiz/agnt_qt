package com.joymain.ng.dao.jpa;

import java.util.List;

import com.joymain.ng.model.JsysListKey;
import com.joymain.ng.dao.JsysListKeyDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jsysListKeyDao")
public class JsysListKeyDaoJpa extends GenericDaoHibernate<JsysListKey, Long> implements JsysListKeyDao {

    public JsysListKeyDaoJpa() {
        super(JsysListKey.class);
    }

	@Override
	public List getSysListBySQL() {
		// TODO Auto-generated method stub
		String sql="select a.list_code, b.value_code, b.value_title, b.ex_company_code " +
				"from jsys_list_key a, jsys_list_value b where a.key_id=b.key_id order by b.order_no";
				return this.getJdbcTemplate().queryForList(sql);
	}

	@Override
	public JsysListKey getListKeyByCode(String listCode) {
		String hql = "from JsysListKey t where t.listCode='"+listCode+"' ";
		return (JsysListKey)getObjectByHqlQuery(hql);
	}
    
}
