package com.joymain.ng.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.joymain.ng.dao.JpmWineSettingInfDao;
import com.joymain.ng.model.JpmWineSettingInf;
import com.joymain.ng.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class JpmWineSettingInfManagerImplTest extends BaseManagerMockTestCase {
    private JpmWineSettingInfManagerImpl manager = null;
    private JpmWineSettingInfDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(JpmWineSettingInfDao.class);
        manager = new JpmWineSettingInfManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetJpmWineSettingInf() {
        log.debug("testing get...");

        final Long settingId = 7L;
        final JpmWineSettingInf jpmWineSettingInf = new JpmWineSettingInf();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(settingId)));
            will(returnValue(jpmWineSettingInf));
        }});

        JpmWineSettingInf result = manager.get(settingId);
        assertSame(jpmWineSettingInf, result);
    }

    @Test
    public void testGetJpmWineSettingInfs() {
        log.debug("testing getAll...");

        final List jpmWineSettingInfs = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(jpmWineSettingInfs));
        }});

        List result = manager.getAll();
        assertSame(jpmWineSettingInfs, result);
    }

    @Test
    public void testSaveJpmWineSettingInf() {
        log.debug("testing save...");

        final JpmWineSettingInf jpmWineSettingInf = new JpmWineSettingInf();
        // enter all required fields
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(jpmWineSettingInf)));
        }});

        manager.save(jpmWineSettingInf);
    }

    @Test
    public void testRemoveJpmWineSettingInf() {
        log.debug("testing remove...");

        final Long settingId = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(settingId)));
        }});

        manager.remove(settingId);
    }
}