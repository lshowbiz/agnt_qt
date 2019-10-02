package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.JbdSendRecordNote;

/**
 * An interface that provides a data management interface to the JbdSendRecordNote table.
 */
public interface JbdSendRecordNoteDao extends GenericDao<JbdSendRecordNote, Long> {

    public JbdSendRecordNote getJbdSendRecordNoteForUpdate( Long id) ;
}