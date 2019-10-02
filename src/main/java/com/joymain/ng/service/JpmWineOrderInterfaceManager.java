package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JpmWineOrderInterface;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import java.util.List;
import javax.jws.WebService;
import java.util.Collection;

@WebService
public interface JpmWineOrderInterfaceManager extends GenericManager<JpmWineOrderInterface, Long> {

    public Pager<JpmWineOrderInterface> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys, int currentPage, int pageSize);

    public List<JpmWineOrderInterface> getList(GroupPage page);

    public void saveJpmWineOrderInterface(JpmWineOrderInterface jpmWineOrderInterface);

    int rePushOrderToERP(String moId);
}