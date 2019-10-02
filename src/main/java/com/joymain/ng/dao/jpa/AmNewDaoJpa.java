package com.joymain.ng.dao.jpa;

import java.util.List;

import com.joymain.ng.model.AmNew;
import com.joymain.ng.dao.AmNewDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("amNewDao")
public class AmNewDaoJpa extends GenericDaoHibernate<AmNew, String> implements AmNewDao {

    public AmNewDaoJpa() {
        super(AmNew.class);
    }

	@Override
	public List<AmNew> findNewByDate(String sDate, String eDate) {
		String sql = "select * from am_new a where a.create_time>=to_date('"+sDate+"','yyyy-MM-dd hh24:mi') " +
				"and a.create_time<=to_date('"+eDate+"','yyyy-MM-dd hh24:mi') ";
		return this.getJdbcTemplate().query(sql,new AmNew());
	}
}
