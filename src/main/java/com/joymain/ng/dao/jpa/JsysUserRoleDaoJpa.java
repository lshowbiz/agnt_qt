package com.joymain.ng.dao.jpa;

import com.joymain.ng.model.JsysUserRole;
import com.joymain.ng.dao.JsysUserRoleDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jsysUserRoleDao")
public class JsysUserRoleDaoJpa extends GenericDaoHibernate<JsysUserRole, Long> implements JsysUserRoleDao {

    public JsysUserRoleDaoJpa() {
        super(JsysUserRole.class);
    }

	@Override
	public JsysUserRole getSysUserRoleByUserCode(String userCode) {
		String hql="from JsysUserRole r where r.userCode='"+userCode+"' ";
		return (JsysUserRole)getObjectByHqlQuery(hql);
	}
}
