package com.joymain.ng.dao.jpa;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JbdYkJiandianListDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JbdYkJiandianList;
import com.joymain.ng.util.GroupPage;

@Repository("jbdYkJiandianListDao")
public class JbdYkJiandianListDaoJpa extends GenericDaoHibernate<JbdYkJiandianList, Long> implements JbdYkJiandianListDao {
	public JbdYkJiandianListDaoJpa() {
		super(JbdYkJiandianList.class);
	}

	@Override
	public List getJbdYkJiandianList(Map<String, String> params, GroupPage page) {
		String userCode = params.get("userCode");
		String startweek = params.get("startweek");
        String endweek = params.get("endweek");
		String sql = "select t.USER_CODE,  m.create_time,m.PET_NAME,(select VALUE_TITLE from JSYS_LIST_VALUE where KEY_ID=(select KEY_ID from JSYS_LIST_KEY  where LIST_CODE='yun.usertype') and VALUE_CODE=t.USER_TYPE) as USER_TYPE,(select VALUE_TITLE from JSYS_LIST_VALUE where KEY_ID=(select KEY_ID from JSYS_LIST_KEY where LIST_CODE='member.level') and VALUE_CODE=t.MEMBER_LEVEL) as MEMBER_LEVEL,t.REUSER_CODE,t.YK_REF_MONEY,t.W_WEEK from JBD_YK_JIANDIAN_LIST t , jmi_member m ,jmi_member r  where 1=1 and t.user_code=m.user_code and t.reuser_code=r.user_code   ";
		
		if(StringUtils.isNotEmpty(userCode)){
			sql += " and t.USER_CODE='"+userCode+"'";
		}
		if(StringUtils.isNotEmpty(startweek)){
			sql += " and t.W_WEEK>="+startweek;
		}
		if(StringUtils.isNotEmpty(endweek)){
			sql += " and t.W_WEEK<="+endweek;
		}
		sql +=" order by t.W_WEEK desc ,r.create_time desc,t.id  ";
		return this.findObjectsBySQL(sql, page);
	}
	
}
