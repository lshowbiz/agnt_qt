package com.joymain.ng.dao.jpa;

import java.util.List;

import com.joymain.ng.model.JalCharacterValue;
import com.joymain.ng.dao.JalCharacterValueDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jalCharacterValueDao")
public class JalCharacterValueDaoJpa extends GenericDaoHibernate<JalCharacterValue, Long> implements JalCharacterValueDao {

    public JalCharacterValueDaoJpa() {
        super(JalCharacterValue.class);
    }

	@Override
	public List getAlCharacterValuesByCodeSQL(String characterCode) {
		// TODO Auto-generated method stub
		String sql="select a.character_key, b.character_value from jal_character_key a, jal_character_value b where a.key_id=b.key_id and b.character_code=?";
		return this.getJdbcTemplate().queryForList(sql, new Object[]{characterCode});
	}
    
}
