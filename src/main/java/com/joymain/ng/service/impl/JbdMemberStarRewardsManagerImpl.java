package com.joymain.ng.service.impl;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JbdMemberStarRewardsDao;
import com.joymain.ng.model.JbdMemberStarRewards;
import com.joymain.ng.service.JbdMemberStarRewardsManager;

@Service("jbdMemberStarRewardsManager")
@WebService(serviceName = "JbdMemberStarRewardsService", endpointInterface = "com.joymain.ng.service.JbdMemberStarRewardsManager")
public class JbdMemberStarRewardsManagerImpl extends GenericManagerImpl<JbdMemberStarRewards, Long> implements JbdMemberStarRewardsManager {
    JbdMemberStarRewardsDao jbdMemberStarRewardsDao;

    @Autowired
    public JbdMemberStarRewardsManagerImpl(JbdMemberStarRewardsDao jbdMemberStarRewardsDao) {
        super(jbdMemberStarRewardsDao);
        this.jbdMemberStarRewardsDao = jbdMemberStarRewardsDao;
    }

    @Override
    public List getJbdMemberStarRewardsPage(Map<String, String> params)
    {
        return jbdMemberStarRewardsDao.getJbdMemberStarRewardsPage(params);
    }

}