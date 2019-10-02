package com.joymain.ng.dao;

import com.joymain.ng.dao.BaseDaoTestCase;
import com.joymain.ng.model.JpmWineSettingInf;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.test.annotation.ExpectedException;

import java.util.List;

public class JpmWineSettingInfDaoTest extends BaseDaoTestCase {
    @Autowired
    private JpmWineSettingInfDao jpmWineSettingInfDao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveJpmWineSettingInf() {
        JpmWineSettingInf jpmWineSettingInf = new JpmWineSettingInf();

        // enter all required fields

        log.debug("adding jpmWineSettingInf...");
        jpmWineSettingInf = jpmWineSettingInfDao.save(jpmWineSettingInf);

        jpmWineSettingInf = jpmWineSettingInfDao.get(jpmWineSettingInf.getSettingId());

        assertNotNull(jpmWineSettingInf.getSettingId());

        log.debug("removing jpmWineSettingInf...");

        jpmWineSettingInfDao.remove(jpmWineSettingInf.getSettingId());

        // should throw DataAccessException 
        jpmWineSettingInfDao.get(jpmWineSettingInf.getSettingId());
    }
}