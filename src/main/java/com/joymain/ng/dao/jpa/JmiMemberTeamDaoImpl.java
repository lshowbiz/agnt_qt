package com.joymain.ng.dao.jpa;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.joymain.ng.dao.JmiMemberTeamDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JmiMemberTeam;


@Repository("jmiMemberTeamDao")
public class JmiMemberTeamDaoImpl extends GenericDaoHibernate<JmiMemberTeam, String> implements
		JmiMemberTeamDao {

	public JmiMemberTeamDaoImpl() {
		super(JmiMemberTeam.class);
	}

	public List<JmiMemberTeam> findAllTeamByStatus(String status) {
		String hql ="from JmiMemberTeam t where t.status=:status ";
		Query query = this.getSession().createQuery(hql);
		query.setParameter("status", status);
		return query.list();
	}

	@Override
	public JmiMemberTeam getJmiMemberTeamByUserCode(String userCode) {
		String hql="from JmiMemberTeam t where t.code=:userCode";
		Query query=this.getSession().createQuery(hql);
		query.setParameter("userCode", userCode);
		return (JmiMemberTeam) query.list().get(0);
	}
}
