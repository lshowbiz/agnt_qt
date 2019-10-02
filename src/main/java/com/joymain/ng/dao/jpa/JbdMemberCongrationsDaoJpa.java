package com.joymain.ng.dao.jpa;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JbdMemberCongrationsDao;
import com.joymain.ng.dao.JbdPeriodDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JbdMemberCongrations;
import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.util.GroupPage;

@Repository("jbdMemberCongrationsDao")
public class JbdMemberCongrationsDaoJpa extends GenericDaoHibernate<JbdMemberCongrations, Long> implements JbdMemberCongrationsDao {

    @Autowired
    private JbdPeriodDao jbdPeriodDao;
    
    public JbdMemberCongrationsDaoJpa() {
        super(JbdMemberCongrations.class);
    }
	
    @Override
    public List getJbdMemberCongrationsPage(Map<String, String> params,
            GroupPage page)
    {
        String userCode = params.get("userCode");
        String starLevel = params.get("starLevel");
        String yearMonth = params.get("yearMonth");
        StringBuffer sqlQuery = new StringBuffer(" SELECT * FROM  JBD_MEMBER_CONGRATIONS a WHERE a.USER_CODE = '"+userCode+"' and a.STAR_LEVEL<>0 ");
        JbdPeriod JbdPeriod = jbdPeriodDao.getBdPeriodByTime(new Date());
        Integer currentFyear = JbdPeriod.getFyear(); //当前财年
        if(StringUtils.isEmpty(yearMonth)) {
           sqlQuery.append(" and a.YEAR_MONTH like '"+currentFyear+"%'");
        } else {
           sqlQuery.append(" and a.YEAR_MONTH="+Integer.valueOf(yearMonth));
        }
        if(StringUtils.isNotEmpty(starLevel)){
            sqlQuery.append(" and a.STAR_LEVEL="+Integer.valueOf(starLevel));
        }
        sqlQuery.append(" order by a.YEAR_MONTH desc");
        return this.findObjectsBySQL(sqlQuery.toString(), page);
    }
    
}
