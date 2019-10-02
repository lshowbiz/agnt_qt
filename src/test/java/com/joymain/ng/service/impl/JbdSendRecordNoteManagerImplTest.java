package com.joymain.ng.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.joymain.ng.dao.JbdSendRecordNoteDao;
import com.joymain.ng.model.JbdSendRecordNote;
import com.joymain.ng.service.impl.BaseManagerMockTestCase;

import org.jmock.Expectations;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class JbdSendRecordNoteManagerImplTest extends BaseManagerMockTestCase {
    private JbdSendRecordNoteManagerImpl manager = null;
    private JbdSendRecordNoteDao dao = null;

    @Before
    public void setUp() {
        dao = context.mock(JbdSendRecordNoteDao.class);
        manager = new JbdSendRecordNoteManagerImpl(dao);
    }

    @After
    public void tearDown() {
        manager = null;
    }

    @Test
    public void testGetJbdSendRecordNote() {
        log.debug("testing get...");

        final Long id = 7L;
        final JbdSendRecordNote jbdSendRecordNote = new JbdSendRecordNote();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).get(with(equal(id)));
            will(returnValue(jbdSendRecordNote));
        }});

        JbdSendRecordNote result = manager.get(id);
        assertSame(jbdSendRecordNote, result);
    }

    @Test
    public void testGetJbdSendRecordNotes() {
        log.debug("testing getAll...");

        final List jbdSendRecordNotes = new ArrayList();

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).getAll();
            will(returnValue(jbdSendRecordNotes));
        }});

        List result = manager.getAll();
        assertSame(jbdSendRecordNotes, result);
    }

    @Test
    public void testSaveJbdSendRecordNote() {
        log.debug("testing save...");

        final JbdSendRecordNote jbdSendRecordNote = new JbdSendRecordNote();
        // enter all required fields
        
        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).save(with(same(jbdSendRecordNote)));
        }});

        manager.save(jbdSendRecordNote);
    }

    @Test
    public void testRemoveJbdSendRecordNote() {
        log.debug("testing remove...");

        final Long id = -11L;

        // set expected behavior on dao
        context.checking(new Expectations() {{
            one(dao).remove(with(equal(id)));
        }});

        manager.remove(id);
    }
}