package com.joymain.ng.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.joymain.ng.dao.JpmWineOrderInterfaceDao;
import com.joymain.ng.model.JpmWineOrderInterface;
import com.joymain.ng.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class JpmWineOrderInterfaceManagerImplTest extends BaseManagerMockTestCase {
    private JpmWineOrderInterfaceManagerImpl manager = null;
    private JpmWineOrderInterfaceDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(JpmWineOrderInterfaceDao.class);
        manager = new JpmWineOrderInterfaceManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetJpmWineOrderInterface() {
        log.debug("testing get...");

        final Long moId = 7L;
        final JpmWineOrderInterface jpmWineOrderInterface = new JpmWineOrderInterface();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(moId)));
            will(returnValue(jpmWineOrderInterface));
        }});

        JpmWineOrderInterface result = manager.get(moId);
        assertSame(jpmWineOrderInterface, result);
    }

    @Test
    public void testGetJpmWineOrderInterfaces() {
        log.debug("testing getAll...");

        final List jpmWineOrderInterfaces = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(jpmWineOrderInterfaces));
        }});

        List result = manager.getAll();
        assertSame(jpmWineOrderInterfaces, result);
    }

    @Test
    public void testSaveJpmWineOrderInterface() {
        log.debug("testing save...");

        final JpmWineOrderInterface jpmWineOrderInterface = new JpmWineOrderInterface();
        // enter all required fields
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(jpmWineOrderInterface)));
        }});

        manager.save(jpmWineOrderInterface);
    }

    @Test
    public void testRemoveJpmWineOrderInterface() {
        log.debug("testing remove...");

        final Long moId = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(moId)));
        }});

        manager.remove(moId);
    }
}