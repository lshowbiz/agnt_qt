package com.joymain.ng.dao.jpa;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import com.joymain.ng.model.JsysId;
import com.joymain.ng.util.data.SpringStoredProcedure;
import com.joymain.ng.dao.JsysIdDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("jsysIdDao")
public class JsysIdDaoJpa extends GenericDaoHibernate<JsysId, Long> implements JsysIdDao {
	@Autowired
	private DataSource dataSource2 ;

    public JsysIdDaoJpa() {
        super(JsysId.class);
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
}
