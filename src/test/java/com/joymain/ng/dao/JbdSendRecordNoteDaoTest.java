package com.joymain.ng.dao;

import com.joymain.ng.dao.BaseDaoTestCase;
import com.joymain.ng.model.JbdSendRecordNote;
import org.springframework.dao.DataAccessException;

import static org.junit.Assert.*;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.NotTransactional;
import org.springframework.test.annotation.ExpectedException;

import java.util.List;

public class JbdSendRecordNoteDaoTest extends BaseDaoTestCase {
    @Autowired
    private JbdSendRecordNoteDao jbdSendRecordNoteDao;

    @Test
    @ExpectedException(DataAccessException.class)
    public void testAddAndRemoveJbdSendRecordNote() {
        JbdSendRecordNote jbdSendRecordNote = new JbdSendRecordNote();

        // enter all required fields

        log.debug("adding jbdSendRecordNote...");
        jbdSendRecordNote = jbdSendRecordNoteDao.save(jbdSendRecordNote);

        jbdSendRecordNote = jbdSendRecordNoteDao.get(jbdSendRecordNote.getId());

        assertNotNull(jbdSendRecordNote.getId());

        log.debug("removing jbdSendRecordNote...");

        jbdSendRecordNoteDao.remove(jbdSendRecordNote.getId());

        // should throw DataAccessException 
        jbdSendRecordNoteDao.get(jbdSendRecordNote.getId());
    }
}