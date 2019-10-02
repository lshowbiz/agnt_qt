package com.joymain.ng.dao.hibernate;

import java.util.List;

import com.joymain.ng.model.JpoCheckedFailed;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.dao.JpoCheckedFailedDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

@Repository("jpoCheckedFailedDao")
public class JpoCheckedFailedDaoHibernate extends GenericDaoHibernate<JpoCheckedFailed, Long> implements JpoCheckedFailedDao {

    public JpoCheckedFailedDaoHibernate() {
        super(JpoCheckedFailed.class);
    }
    
	public JpoCheckedFailed getByOrderNo(JpoMemberOrder jpoMemberOrder) {

		Query q = this.getSession().createQuery("from JpoCheckedFailed jpoCheckedFailed where jpoCheckedFailed.jpoMemberOrder.moId =" + jpoMemberOrder.getMoId());
		List<JpoCheckedFailed> list = q.list();
		if (list != null && list.size() > 0) {
			return (JpoCheckedFailed) list.get(0);
		} else {
			return null;
		}

	}

    @Override
    public Integer deleteJpoCheckedFaiiled(String moId)
    {
        StringBuffer sql = new StringBuffer("delete from jpo_checked_failed where mo_id = " + moId);
        this.getJdbcTemplate().execute(sql.toString());
        return 0;
    }
}
