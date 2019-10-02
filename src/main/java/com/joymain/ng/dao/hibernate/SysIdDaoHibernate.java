package com.joymain.ng.dao.hibernate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.SysIdDao;
import com.joymain.ng.model.SysId;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.data.CommonRecord;
import com.joymain.ng.util.data.SpringStoredProcedure;

@Repository("sysIdDao")
public class SysIdDaoHibernate extends GenericDaoHibernate<SysId, Long> implements SysIdDao {
	
	public SysIdDaoHibernate() {
		super(SysId.class);
		// TODO Auto-generated constructor stub
	}
	
	public SysIdDaoHibernate(Class<SysId> persistentClass) {
		super(SysId.class);
		// TODO Auto-generated constructor stub
	}

	private DataSource dataSource2 ;
	@Autowired
    public void setDataSource2(DataSource dataSource2) {
		this.dataSource2 = dataSource2;
	}

	/**
     * @see com.joymain.jecs.sys.dao.SysIdDao#getSysIds(com.joymain.jecs.sys.model.SysId)
     */
    public List getSysIds(final SysId sysId) {
        return getJdbcTemplate().queryForList("from SysId order by id desc");
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysIdDao#getSysId(Long id)
     */
    public SysId getSysId(final Long id) {
//        SysId sysId = (SysId) getJdbcTemplate().get(SysId.class, id);
    	SysId sysId = (SysId) this.getSession().get(SysId.class, id);
        if (sysId == null) {
            log.warn("uh oh, sysId with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(SysId.class, id);
        }

        return sysId;
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysIdDao#saveSysId(SysId sysId)
     */    
    public void saveSysId(final SysId sysId) {
        this.getSession().saveOrUpdate(sysId);
    }

    /**
     * @see com.joymain.jecs.sys.dao.SysIdDao#removeSysId(Long id)
     */
    public void removeSysId(final Long id) {
        this.getSession().delete(getSysId(id));
    }
    
    /**
     * 根据ID获取对应的序列器
     * @param idCode
     * @return
     */
    public SysId getSysIdByCode(final String idCode){
    	return (SysId)this.getObjectByHqlQuery("from SysId where idCode='"+idCode+"'");
    }
    
    /**
	 * 根据外部参数分页获取对应的序列器列表
	 * @param crm
	 * @param pager
	 * @return
	 */
    @Override
	public List getSysIdsByPage(CommonRecord crm, Pager pager) {
	/*	String hqlQuery = "from SysId where 1=1";
		
		String idCode = crm.getString("idCode", "");
		if (!StringUtils.isEmpty(idCode)) {
			hqlQuery += " and idCode like '%" + idCode + "%'";
		}
		
		String idName = crm.getString("idName", "");
		if (!StringUtils.isEmpty(idName)) {
			hqlQuery += " and idName like '%" + idName + "%'";
		}

		// 设置排序
		if (!pager.getLimit().getSort().isSorted()) {
			//缺省排序
			hqlQuery += " order by id desc";
		} else {
			hqlQuery += " ORDER BY " + pager.getLimit().getSort().getProperty() + " " + pager.getLimit().getSort().getSortOrder();
		}
		//

		List sysIds = this.findObjectsByHqlQuery(hqlQuery, pager);

		return sysIds;
		
		*/
    	return null;
	}
	
	/**
	 * 执行函数获取对应值
	 * @param idCode
	 * @return
	 */
	public Map callProcBuildIdStr(final String idCode) {
		SpringStoredProcedure spComp = new SpringStoredProcedure(this.dataSource2, "Fn_Build_JSys_Id",true);

		//注册参数类型,输入参数和输出参数同时注册,否则不能正确编译存储过程
		spComp.setOutParameter("p_out_code", oracle.jdbc.OracleTypes.VARCHAR);
		spComp.setParameter("Vc_Id_Code", java.sql.Types.VARCHAR);

		spComp.compile();

		// 传入输入参数值
		Map<String, Object> inComp = new HashMap<String, Object>();
		inComp.put("Vc_Id_Code", idCode);

		spComp.SetInParam(inComp);
		// 执行存储过程
		Map resultComp = spComp.execute();

		return resultComp;
	}
	
	/**
	 * 换货单和发货退回单生成发货单时发货单编号用新的函数
	 * @author fu 2016-03-17 
	 * @param idCode
	 * @return 
	 */
	public Map callProcBuildIdStrTwo(String idCode){
		SpringStoredProcedure spComp = new SpringStoredProcedure(this.dataSource2, "Fn_Build_jSys_send",true);

		//注册参数类型,输入参数和输出参数同时注册,否则不能正确编译存储过程
		spComp.setOutParameter("p_out_code", oracle.jdbc.OracleTypes.VARCHAR);
		spComp.setParameter("Vc_Id_Code", java.sql.Types.VARCHAR);

		spComp.compile();

		// 传入输入参数值
		Map<String, Object> inComp = new HashMap<String, Object>();
		inComp.put("Vc_Id_Code", idCode);

		spComp.SetInParam(inComp);
		// 执行存储过程
		Map resultComp = spComp.execute();

		return resultComp;
	}


}
