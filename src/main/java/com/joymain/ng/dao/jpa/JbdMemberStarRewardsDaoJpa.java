package com.joymain.ng.dao.jpa;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.joymain.ng.dao.JbdMemberStarRewardsDao;
import com.joymain.ng.dao.JbdPeriodDao;
import com.joymain.ng.dao.hibernate.GenericDaoHibernate;
import com.joymain.ng.model.JbdMemberStarRewards;
import com.joymain.ng.model.JbdPeriod;
@Repository("jbdMemberStarRewardsDao")
public class JbdMemberStarRewardsDaoJpa extends GenericDaoHibernate<JbdMemberStarRewards, Long> implements JbdMemberStarRewardsDao {

    @Autowired
    private JbdPeriodDao jbdPeriodDao;
    
    public JbdMemberStarRewardsDaoJpa() {
        super(JbdMemberStarRewards.class);
    }
	
    @Override
    public List getJbdMemberStarRewardsPage(Map<String, String> params)
    {
        String userCode = params.get("userCode");
        String rewards = params.get("rewards");
        String fyear = params.get("fyear");
        StringBuffer sqlQuery = new StringBuffer(" select * from  JBD_MEMBER_STAR_REWARDS a where a.user_code = '"+userCode+"' ");
        JbdPeriod JbdPeriod = jbdPeriodDao.getBdPeriodByTime(new Date());
        Integer currentFyear = JbdPeriod.getFyear(); //当前财年
        if(StringUtils.isEmpty(fyear)) {
           sqlQuery.append(" and a.f_year="+currentFyear);
        } else {
           sqlQuery.append(" and a.f_year="+Integer.valueOf(fyear));
        }
        if(StringUtils.isNotEmpty(rewards)){
            sqlQuery.append(" and a.rewards="+Integer.valueOf(rewards));
        }
        sqlQuery.append(" order by a.f_year desc");
        return this.jdbcTemplate.queryForList(sqlQuery.toString());
    }
    
}
