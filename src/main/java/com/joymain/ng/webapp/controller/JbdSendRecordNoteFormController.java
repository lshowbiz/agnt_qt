package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JbdSendRecordNoteManager;
import com.joymain.ng.model.JbdSendRecordNote;
import com.joymain.ng.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/jbdSendRecordNoteform*")
public class JbdSendRecordNoteFormController extends BaseFormController {
    private JbdSendRecordNoteManager jbdSendRecordNoteManager = null;

    @Autowired
    public void setJbdSendRecordNoteManager(JbdSendRecordNoteManager jbdSendRecordNoteManager) {
        this.jbdSendRecordNoteManager = jbdSendRecordNoteManager;
    }

    public JbdSendRecordNoteFormController() {
        setCancelView("redirect:jbdSendRecordNotes");
        setSuccessView("redirect:jbdSendRecordNotes");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JbdSendRecordNote showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return jbdSendRecordNoteManager.get(new Long(id));
        }

        return new JbdSendRecordNote();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JbdSendRecordNote jbdSendRecordNote, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jbdSendRecordNote, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jbdSendRecordNoteform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jbdSendRecordNote.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jbdSendRecordNoteManager.remove(jbdSendRecordNote.getId());
            saveMessage(request, getText("jbdSendRecordNote.deleted", locale));
        } else {
            jbdSendRecordNoteManager.save(jbdSendRecordNote);
            String key = (isNew) ? "jbdSendRecordNote.added" : "jbdSendRecordNote.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jbdSendRecordNoteform?id=" + jbdSendRecordNote.getId();
            }
        }

        return success;
    }
}
