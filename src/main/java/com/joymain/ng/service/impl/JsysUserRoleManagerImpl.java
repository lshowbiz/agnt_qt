package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JsysUserRoleDao;
import com.joymain.ng.model.JsysUserRole;
import com.joymain.ng.service.JsysUserRoleManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jsysUserRoleManager")
@WebService(serviceName = "JsysUserRoleService", endpointInterface = "com.joymain.ng.service.JsysUserRoleManager")
public class JsysUserRoleManagerImpl extends GenericManagerImpl<JsysUserRole, Long> implements JsysUserRoleManager {
	
    JsysUserRoleDao jsysUserRoleDao;

    @Autowired
    public JsysUserRoleManagerImpl(JsysUserRoleDao jsysUserRoleDao) {
        super(jsysUserRoleDao);
        this.jsysUserRoleDao = jsysUserRoleDao;
    }
	
	public Pager<JsysUserRole> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jsysUserRoleDao.getPager(JsysUserRole.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public JsysUserRole getSysUserRoleByUserCode(String userCode) {
		return jsysUserRoleDao.getSysUserRoleByUserCode(userCode);
	}
}