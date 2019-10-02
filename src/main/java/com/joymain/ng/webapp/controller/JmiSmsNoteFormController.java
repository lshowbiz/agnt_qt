package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JmiSmsNoteManager;
import com.joymain.ng.model.JmiSmsNote;
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
@RequestMapping("/jmiSmsNoteform*")
public class JmiSmsNoteFormController extends BaseFormController {
    private JmiSmsNoteManager jmiSmsNoteManager = null;

    @Autowired
    public void setJmiSmsNoteManager(JmiSmsNoteManager jmiSmsNoteManager) {
        this.jmiSmsNoteManager = jmiSmsNoteManager;
    }

    public JmiSmsNoteFormController() {
        setCancelView("redirect:jmiSmsNotes");
        setSuccessView("redirect:jmiSmsNotes");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JmiSmsNote showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return jmiSmsNoteManager.get(new Long(id));
        }

        return new JmiSmsNote();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JmiSmsNote jmiSmsNote, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jmiSmsNote, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jmiSmsNoteform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jmiSmsNote.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jmiSmsNoteManager.remove(jmiSmsNote.getId());
            saveMessage(request, getText("jmiSmsNote.deleted", locale));
        } else {
            jmiSmsNoteManager.save(jmiSmsNote);
            String key = (isNew) ? "jmiSmsNote.added" : "jmiSmsNote.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jmiSmsNoteform?id=" + jmiSmsNote.getId();
            }
        }

        return success;
    }
}
