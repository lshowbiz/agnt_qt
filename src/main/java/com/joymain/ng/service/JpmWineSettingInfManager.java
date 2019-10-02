package com.joymain.ng.service;

import java.util.Collection;
import java.util.List;

import javax.jws.WebService;

import com.joymain.ng.model.JpmWineSettingInf;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

@WebService
public interface JpmWineSettingInfManager extends GenericManager<JpmWineSettingInf, Long> {

    public Pager<JpmWineSettingInf> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys, int currentPage, int pageSize);

    public List<JpmWineSettingInf> getList(GroupPage page);

    public void saveJpmWineSettingInf(JpmWineSettingInf jpmWineSettingInf);

    int rePushSettingToERP(String settingId);
}