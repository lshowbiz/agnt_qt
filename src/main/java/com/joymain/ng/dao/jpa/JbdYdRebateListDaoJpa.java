package com.joymain.ng.dao.jpa;

import com.joymain.ng.dao.JbdYdRebateListDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JbdYdRebateList;
import com.joymain.ng.util.GroupPage;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("jbdYdRebateListDao")
public class JbdYdRebateListDaoJpa extends GenericDaoHibernate<JbdYdRebateList, Long> implements JbdYdRebateListDao {
	public JbdYdRebateListDaoJpa() {
		super(JbdYdRebateList.class);
	}

	@Override
	public List getJbdYdRebateList(Map<String, String> params, GroupPage page) {
		String userCode = params.get("userCode");
		String startCalcTime = params.get("startCalcTime");
        String endCalcTime = params.get("endCalcTime");
		StringBuffer sqlBuffer =new StringBuffer("select t.*,  " );
		sqlBuffer.append("       (select VALUE_TITLE " );
		sqlBuffer.append("          from JSYS_LIST_VALUE " );
		sqlBuffer.append("         where KEY_ID = (select KEY_ID " );
		sqlBuffer.append("                           from JSYS_LIST_KEY " );
		sqlBuffer.append("                          where LIST_CODE = 'member.level') " );
		sqlBuffer.append("           and VALUE_CODE = t.MEMBER_LEVEL) as member_level_name, " );
		sqlBuffer.append("       (select VALUE_TITLE " );
		sqlBuffer.append("          from JSYS_LIST_VALUE " );
		sqlBuffer.append("         where KEY_ID = " );
		sqlBuffer.append("               (select KEY_ID " );
		sqlBuffer.append("                  from JSYS_LIST_KEY " );
		sqlBuffer.append("                 where LIST_CODE = 'jbdydrebatelist.sendstatus') " );
		sqlBuffer.append("           and VALUE_CODE = t.send_status) as send_status_name " );
		sqlBuffer.append("  from V_JBD_YD_REBATE_LIST t " );
		sqlBuffer.append(" where 1 = 1");

		if(StringUtils.isNotEmpty(userCode)){
			sqlBuffer.append(" and t.USER_CODE='"+userCode+"'");
		}
		if(!StringUtils.isEmpty(startCalcTime)){
			sqlBuffer.append( " and CALC_TIME >=to_date('"+startCalcTime+" ','yyyy-mm-dd HH24:MI:SS') ");
		}
		if(!StringUtils.isEmpty(endCalcTime)){
			sqlBuffer.append(" and CALC_TIME<=to_date('"+endCalcTime+"  ','yyyy-mm-dd HH24:MI:SS') ");
		}


		sqlBuffer.append(" order by t.CALC_TIME desc ,t.id  ");

		return this.findObjectsBySQL(sqlBuffer.toString(), page);
	}
	
}
