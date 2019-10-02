package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JpmWineOrderInterface;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the
 * JpmWineOrderInterface table.
 */
public interface JpmWineOrderInterfaceDao extends GenericDao<JpmWineOrderInterface, Long> {

    List<JpmWineOrderInterface> getListPage(GroupPage page);

    void saveJpmWineOrderInterface(JpmWineOrderInterface jpmWineOrderInterface);

    JpmWineOrderInterface getJpmWineOrderInterface(Long long1);

}