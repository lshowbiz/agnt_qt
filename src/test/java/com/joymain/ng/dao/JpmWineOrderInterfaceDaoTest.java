package com.joymain.ng.dao;

import com.joymain.ng.dao.BaseDaoTestCase;
import com.joymain.ng.model.JpmWineOrderInterface;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.test.annotation.ExpectedException;

import java.util.List;

public class JpmWineOrderInterfaceDaoTest extends BaseDaoTestCase {
    @Autowired
    private JpmWineOrderInterfaceDao jpmWineOrderInterfaceDao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveJpmWineOrderInterface() {
        JpmWineOrderInterface jpmWineOrderInterface = new JpmWineOrderInterface();

        // enter all required fields

        log.debug("adding jpmWineOrderInterface...");
        jpmWineOrderInterface = jpmWineOrderInterfaceDao.save(jpmWineOrderInterface);

        jpmWineOrderInterface = jpmWineOrderInterfaceDao.get(jpmWineOrderInterface.getMoId());

        assertNotNull(jpmWineOrderInterface.getMoId());

        log.debug("removing jpmWineOrderInterface...");

        jpmWineOrderInterfaceDao.remove(jpmWineOrderInterface.getMoId());

        // should throw DataAccessException 
        jpmWineOrderInterfaceDao.get(jpmWineOrderInterface.getMoId());
    }
}