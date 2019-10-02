package com.joymain.ng.service.impl;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JpmWineOrderInterfaceDao;
import com.joymain.ng.model.JpmWineOrderInterface;
import com.joymain.ng.service.JpmWineOrderInterfaceManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.wine.WineInterfaceUtil;

@Service("jpmWineOrderInterfaceManager")
@WebService(serviceName = "JpmWineOrderInterfaceService", endpointInterface = "com.joymain.ng.service.JpmWineOrderInterfaceManager")
public class JpmWineOrderInterfaceManagerImpl extends GenericManagerImpl<JpmWineOrderInterface, Long> implements JpmWineOrderInterfaceManager {
    JpmWineOrderInterfaceDao jpmWineOrderInterfaceDao;

    @Autowired
    public JpmWineOrderInterfaceManagerImpl(JpmWineOrderInterfaceDao jpmWineOrderInterfaceDao) {
        super(jpmWineOrderInterfaceDao);
        this.jpmWineOrderInterfaceDao = jpmWineOrderInterfaceDao;
    }

    public Pager<JpmWineOrderInterface> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
        // TODO Auto-generated method stub
        return jpmWineOrderInterfaceDao.getPager(JpmWineOrderInterface.class, searchBeans, OrderBys, currentPage, pageSize);
    }

    @Override
    public List<JpmWineOrderInterface> getList(GroupPage page) {
        return jpmWineOrderInterfaceDao.getListPage(page);
    }

    @Override
    public void saveJpmWineOrderInterface(JpmWineOrderInterface jpmWineOrderInterface) {
        jpmWineOrderInterfaceDao.saveJpmWineOrderInterface(jpmWineOrderInterface);
    }

    @Override
    public int rePushOrderToERP(String moId) {
        JpmWineOrderInterface jpmWineOrderInterface = jpmWineOrderInterfaceDao.getJpmWineOrderInterface(new Long(moId));
        int ret = new WineInterfaceUtil().sendOrder2ERP(jpmWineOrderInterface, 0);
        return ret;
    }
}