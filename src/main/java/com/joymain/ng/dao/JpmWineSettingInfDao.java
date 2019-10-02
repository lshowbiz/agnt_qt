package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JpmWineOrderInterface;
import com.joymain.ng.model.JpmWineSettingInf;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the
 * JpmWineSettingInf table.
 */
public interface JpmWineSettingInfDao extends GenericDao<JpmWineSettingInf, Long> {

    List<JpmWineSettingInf> getListPage(GroupPage page);

    void saveJpmWineSettingInf(JpmWineSettingInf jpmWineSettingInf);

    JpmWineSettingInf getJpmWineSettingInf(Long settingId);

}