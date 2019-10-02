package com.joymain.ng.dao.jpa;

import java.util.List;

import org.hibernate.Query;

import com.joymain.ng.model.JsysResource;
import com.joymain.ng.dao.JsysResourceDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

@Repository("jsysResourceDao")
public class JsysResourceDaoJpa extends GenericDaoHibernate<JsysResource, Long> implements JsysResourceDao {

    public JsysResourceDaoJpa() {
        super(JsysResource.class);
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<JsysResource> getGrantedAuthorityResource(String resType) {
		// TODO Auto-generated method stub

		String queryString = "select jr from JsysResource jr where jr.resUrl<>null order by jr.orderNo";
		
		Query query =getSession().createQuery(queryString);
		
		return query.list();
	}

	@Override
	public List getMenusByUserCode(String userCode) {
		// TODO Auto-generated method stub
		
		String sql = "SELECT DISTINCT sr.* FROM jsys_user_role sur,jsys_res_role rr ,(select * from JSYS_RESOURCE where active='1') sr "
				+ "WHERE sur.role_id=rr.role_id AND rr.res_id=sr.RES_ID and sr.RES_TYPE=0  AND sur.user_code='"
				+ userCode + "' ORDER BY sr.TREE_LEVEL,sr.sort_NO";
		return this.getJdbcTemplate().queryForList(sql);
	}

	@Override
	public List getMenusByUserCodeAndLayer(String userCode, String layer) {
		// TODO Auto-generated method stub
		String sql = "SELECT DISTINCT sr.* FROM jsys_user_role sur,jsys_res_role rr ,(select * from JSYS_RESOURCE where active='1') sr "
				+ "WHERE sur.role_id=rr.role_id AND rr.res_id=sr.RES_ID and sr.RES_TYPE=1  AND sur.user_code=? and sr.TREE_LEVEL=? ORDER BY sr.TREE_LEVEL,sr.order_NO";
		return this.getJdbcTemplate().queryForList(sql, new String[]{userCode,layer});
	}
	
	public List getCommonMenu(String parmcode){
		String sql = "SELECT DISTINCT sr.* FROM JSYS_RESOURCE sr "
				+ "WHERE res_code in("+parmcode+") and active='1' ";
		return this.getJdbcTemplate().queryForList(sql, new String[]{});
	}
	
	public List getTLMenu(String resCode){
		String sql = "select a.*,(select res_id from JSYS_RESOURCE where tree_level=1 START WITH res_code=a.res_code CONNECT BY PRIOR parent_id=res_id ) as topid from jsys_resource a "
				+ "WHERE a.res_code='"+resCode+"' and a.active='1' ";
		return this.getJdbcTemplate().queryForList(sql, new String[]{});
	}

	@Override
	public List getSubMenusByUserCodeAndParent(String userCode, Long parentId) {
		// TODO Auto-generated method stub
		String sql = "SELECT DISTINCT sr.* FROM jsys_user_role sur,jsys_res_role rr ,(select * from JSYS_RESOURCE where active='1') sr "
				+ "WHERE sur.role_id=rr.role_id AND rr.res_id=sr.RES_ID and sr.RES_TYPE=1  AND sur.user_code=? and sr.parent_id=? ORDER BY sr.TREE_LEVEL,sr.order_NO";
//		log.info("sql="+sql);
		return this.getJdbcTemplate().queryForList(sql, new Object[]{userCode,parentId});
	}
	
	
    
    
}
