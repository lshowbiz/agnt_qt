package com.joymain.ng.service.impl;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JbdMemberCongrationsDao;
import com.joymain.ng.model.JbdMemberCongrations;
import com.joymain.ng.service.JbdMemberCongrationsManager;
import com.joymain.ng.util.GroupPage;

@Service("jbdMemberCongrationsManager")
@WebService(serviceName = "JbdMemberCongrationsService", endpointInterface = "com.joymain.ng.service.JbdMemberCongrationsManager")
public class JbdMemberCongrationsManagerImpl extends GenericManagerImpl<JbdMemberCongrations, Long> implements JbdMemberCongrationsManager {
    JbdMemberCongrationsDao JbdMemberCongrationsDao;

    @Autowired
    public JbdMemberCongrationsManagerImpl(JbdMemberCongrationsDao JbdMemberCongrationsDao) {
        super(JbdMemberCongrationsDao);
        this.JbdMemberCongrationsDao = JbdMemberCongrationsDao;
    }

    @Override
    public List getJbdMemberCongrationsPage(Map<String, String> params,
            GroupPage page)
    {
        return JbdMemberCongrationsDao.getJbdMemberCongrationsPage(params,page);
    }

}