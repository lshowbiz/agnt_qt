package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.model.JbdSendNote;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the JbdSendNote table.
 */
public interface JbdSendNoteDao extends GenericDao<JbdSendNote, Long> {

    public List getJbdSendNote(String userCode);
    
    public List getJbdSendNotePage(GroupPage page,String userCode);
}