package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.InwProblem;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.dao.InwProblemDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("inwProblemDao")
public class InwProblemDaoHibernate extends GenericDaoHibernate<InwProblem, Long> implements InwProblemDao {

    public InwProblemDaoHibernate() {
        super(InwProblem.class);
    }

    /**
	 * 创新共赢的共赢帮助的详细查询
	 * @author gw 2013-08-30
	 * @param species
	 * @return  List
	 */
	public List getInwProblemDetail(String species) {
		String sql = " select * from inw_problem where species = '"+species+"' and other='Y'";
		return this.jdbcTemplate.queryForList(sql);
	}
}
