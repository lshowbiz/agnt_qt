package com.joymain.ng.dao.jpa;

import java.util.List;

import com.joymain.ng.model.JsysConfigValue;
import com.joymain.ng.dao.JsysConfigValueDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jsysConfigValueDao")
public class JsysConfigValueDaoJpa extends GenericDaoHibernate<JsysConfigValue, Long> implements JsysConfigValueDao {

    public JsysConfigValueDaoJpa() {
        super(JsysConfigValue.class);
    }

	@Override
	public List getSysConfigValuesByCodeSQL(String companyCode) {
		// TODO Auto-generated method stub
		String sql = "select a.config_code, a.default_value, b.config_value from jsys_config_key a" +
    			" left join jsys_config_value b on (a.key_id=b.key_id and b.company_code=?)";
		return this.getJdbcTemplate().queryForList(sql, new String[]{companyCode});
		
	}
    
}
