package com.joymain.ng.dao;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JmiSmsNote;

/**
 * An interface that provides a data management interface to the JmiSmsNote table.
 */
public interface JmiSmsNoteDao extends GenericDao<JmiSmsNote, Long> {

    public JmiSmsNote getJmiSmsNoteByUserCode(String userCode);
    public JmiSmsNote getJmiSmsNoteByUserCodeStatus(String userCode);
}