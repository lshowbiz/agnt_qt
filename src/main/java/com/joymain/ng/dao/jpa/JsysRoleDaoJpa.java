package com.joymain.ng.dao.jpa;

import com.joymain.ng.model.JsysRole;
import com.joymain.ng.dao.JsysRoleDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jsysRoleDao")
public class JsysRoleDaoJpa extends GenericDaoHibernate<JsysRole, Long> implements JsysRoleDao {

    public JsysRoleDaoJpa() {
        super(JsysRole.class);
    }
    
    /**
     * 根据角色编码获取对应的角色记录
     * @param roleCode
     * @return
     */
    public JsysRole getSysRoleByCode(final String roleCode) {
    	return (JsysRole)this.getObjectByHqlQuery("from JsysRole where roleCode='"+roleCode+"'");
    }
}
