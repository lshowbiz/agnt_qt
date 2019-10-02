package com.joymain.ng.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JpmWineSettingInfDao;
import com.joymain.ng.model.JpmWineSettingInf;
import com.joymain.ng.service.JpmWineSettingInfManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.wine.WineInterfaceUtil;

@Service("jpmWineSettingInfManager")
@WebService(serviceName = "JpmWineSettingInfService", endpointInterface = "com.joymain.ng.service.JpmWineSettingInfManager")
public class JpmWineSettingInfManagerImpl extends GenericManagerImpl<JpmWineSettingInf, Long> implements JpmWineSettingInfManager {
    JpmWineSettingInfDao jpmWineSettingInfDao;

    @Autowired
    public JpmWineSettingInfManagerImpl(JpmWineSettingInfDao jpmWineSettingInfDao) {
        super(jpmWineSettingInfDao);
        this.jpmWineSettingInfDao = jpmWineSettingInfDao;
    }

    public Pager<JpmWineSettingInf> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
        return jpmWineSettingInfDao.getPager(JpmWineSettingInf.class, searchBeans, OrderBys, currentPage, pageSize);
    }

    @Override
    public List<JpmWineSettingInf> getList(GroupPage page) {

        return jpmWineSettingInfDao.getListPage(page);
    }

    @Override
    public void saveJpmWineSettingInf(JpmWineSettingInf jpmWineSettingInf) {
        jpmWineSettingInfDao.saveJpmWineSettingInf(jpmWineSettingInf);
    }

    @Override
    public int rePushSettingToERP(String settingId) {
        JpmWineSettingInf jpmWineSettingInf = jpmWineSettingInfDao.getJpmWineSettingInf(new Long(settingId));
        int ret = new WineInterfaceUtil().sendSettingToERP(jpmWineSettingInf, 0);
        return ret;
    }
}