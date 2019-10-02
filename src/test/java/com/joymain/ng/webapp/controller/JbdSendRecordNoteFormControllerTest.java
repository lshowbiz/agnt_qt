package com.joymain.ng.webapp.controller;

import com.joymain.ng.webapp.controller.BaseControllerTestCase;
import com.joymain.ng.model.JbdSendRecordNote;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JbdSendRecordNoteFormControllerTest extends BaseControllerTestCase {
    @Autowired
    private JbdSendRecordNoteFormController form;
    private JbdSendRecordNote jbdSendRecordNote;
    private MockHttpServletRequest request;

    @Test
    public void testEdit() throws Exception {
        log.debug("testing edit...");
        request = newGet("/jbdSendRecordNoteform");
        request.addParameter("id", "-1");

        jbdSendRecordNote = form.showForm(request);
        assertNotNull(jbdSendRecordNote);
    }

    @Test
    public void testSave() throws Exception {
        request = newGet("/jbdSendRecordNoteform");
        request.addParameter("id", "-1");

        jbdSendRecordNote = form.showForm(request);
        assertNotNull(jbdSendRecordNote);

        request = newPost("/jbdSendRecordNoteform");

        jbdSendRecordNote = form.showForm(request);
        // update required fields

        BindingResult errors = new DataBinder(jbdSendRecordNote).getBindingResult();
        form.onSubmit(jbdSendRecordNote, errors, request, new MockHttpServletResponse());
        assertFalse(errors.hasErrors());
        assertNotNull(request.getSession().getAttribute("successMessages"));
    }

    @Test
    public void testRemove() throws Exception {
        request = newPost("/jbdSendRecordNoteform");
        request.addParameter("delete", "");
        jbdSendRecordNote = new JbdSendRecordNote();
        jbdSendRecordNote.setId(-2L);

        BindingResult errors = new DataBinder(jbdSendRecordNote).getBindingResult();
        form.onSubmit(jbdSendRecordNote, errors, request, new MockHttpServletResponse());

        assertNotNull(request.getSession().getAttribute("successMessages"));
    }
}
