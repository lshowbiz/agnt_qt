package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JmiEcmallNoteManager;
import com.joymain.ng.model.JmiEcmallNote;
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
@RequestMapping("/jmiEcmallNoteform*")
public class JmiEcmallNoteFormController extends BaseFormController {
    private JmiEcmallNoteManager jmiEcmallNoteManager = null;

    @Autowired
    public void setJmiEcmallNoteManager(JmiEcmallNoteManager jmiEcmallNoteManager) {
        this.jmiEcmallNoteManager = jmiEcmallNoteManager;
    }

    public JmiEcmallNoteFormController() {
        setCancelView("redirect:jmiEcmallNotes");
        setSuccessView("redirect:jmiEcmallNotes");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JmiEcmallNote showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return jmiEcmallNoteManager.get(new Long(id));
        }

        return new JmiEcmallNote();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JmiEcmallNote jmiEcmallNote, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jmiEcmallNote, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jmiEcmallNoteform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jmiEcmallNote.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jmiEcmallNoteManager.remove(jmiEcmallNote.getId());
            saveMessage(request, getText("jmiEcmallNote.deleted", locale));
        } else {
            jmiEcmallNoteManager.save(jmiEcmallNote);
            String key = (isNew) ? "jmiEcmallNote.added" : "jmiEcmallNote.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jmiEcmallNoteform?id=" + jmiEcmallNote.getId();
            }
        }

        return success;
    }
}
