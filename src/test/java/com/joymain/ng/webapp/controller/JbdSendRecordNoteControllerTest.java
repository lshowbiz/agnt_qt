package com.joymain.ng.webapp.controller;

import com.joymain.ng.service.JbdSendRecordNoteManager;
import com.joymain.ng.model.JbdSendRecordNote;

import com.joymain.ng.webapp.controller.BaseControllerTestCase;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import static org.junit.Assert.*;

public class JbdSendRecordNoteControllerTest extends BaseControllerTestCase {
    @Autowired
    private JbdSendRecordNoteController controller;

    @Test
    public void testHandleRequest() throws Exception {
        Model model = controller.handleRequest(null);
        Map m = model.asMap();
        assertNotNull(m.get("jbdSendRecordNoteList"));
        assertTrue(((List) m.get("jbdSendRecordNoteList")).size() > 0);
    }

    @Test
    public void testSearch() throws Exception {
        // regenerate indexes
        JbdSendRecordNoteManager jbdSendRecordNoteManager = (JbdSendRecordNoteManager) applicationContext.getBean("jbdSendRecordNoteManager");
        jbdSendRecordNoteManager.reindex();

        Model model = controller.handleRequest("*");
        Map m = model.asMap();
        List results = (List) m.get("jbdSendRecordNoteList");
        assertNotNull(results);
        assertEquals(3, results.size());
    }
}