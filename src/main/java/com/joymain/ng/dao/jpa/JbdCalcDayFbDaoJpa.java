package com.joymain.ng.dao.jpa;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JbdCalcDayFbDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JbdCalcDayFb;
import com.joymain.ng.util.GroupPage;

@Repository("jbdCalcDayFbDao")
public class JbdCalcDayFbDaoJpa extends GenericDaoHibernate<JbdCalcDayFb, Long> implements JbdCalcDayFbDao {
	public JbdCalcDayFbDaoJpa() {
		super(JbdCalcDayFb.class);
	}

	@Override
	public List getJbdCalcDayFbsPage(Map<String, String> params, GroupPage page) {
		String userCode = params.get("userCode");
		String startweek = params.get("startweek");
        String endweek = params.get("endweek");
		String sql = "select t.*,(select PET_NAME from jmi_member where user_code=t.user_code) as PET_NAME,(select VALUE_TITLE from JSYS_LIST_VALUE where KEY_ID=(select KEY_ID from JSYS_LIST_KEY where LIST_CODE='member.level') and VALUE_CODE=t.MEMBER_LEVEL) as MEMBER_LEVEL_NAME from JBD_CALC_DAY_FB t where user_code='"+userCode+"'";
		if(StringUtils.isNotEmpty(startweek)){
			sql += " and w_week>="+startweek;
		}
		if(StringUtils.isNotEmpty(endweek)){
			sql += " and w_week<="+endweek;
		}
		return this.findObjectsBySQL(sql, page);
	}
	
}
