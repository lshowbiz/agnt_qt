package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JmiMemberUpgradeNoteManager;
import com.joymain.ng.model.JmiMemberUpgradeNote;
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
@RequestMapping("/jmiMemberUpgradeNoteform*")
public class JmiMemberUpgradeNoteFormController extends BaseFormController {
    private JmiMemberUpgradeNoteManager jmiMemberUpgradeNoteManager = null;

    @Autowired
    public void setJmiMemberUpgradeNoteManager(JmiMemberUpgradeNoteManager jmiMemberUpgradeNoteManager) {
        this.jmiMemberUpgradeNoteManager = jmiMemberUpgradeNoteManager;
    }

    public JmiMemberUpgradeNoteFormController() {
        setCancelView("redirect:jmiMemberUpgradeNotes");
        setSuccessView("redirect:jmiMemberUpgradeNotes");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JmiMemberUpgradeNote showForm(HttpServletRequest request)
    throws Exception {
        String munId = request.getParameter("munId");

        if (!StringUtils.isBlank(munId)) {
            return jmiMemberUpgradeNoteManager.get(new Long(munId));
        }

        return new JmiMemberUpgradeNote();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JmiMemberUpgradeNote jmiMemberUpgradeNote, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jmiMemberUpgradeNote, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jmiMemberUpgradeNoteform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jmiMemberUpgradeNote.getMunId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jmiMemberUpgradeNoteManager.remove(jmiMemberUpgradeNote.getMunId());
            saveMessage(request, getText("jmiMemberUpgradeNote.deleted", locale));
        } else {
            jmiMemberUpgradeNoteManager.save(jmiMemberUpgradeNote);
            String key = (isNew) ? "jmiMemberUpgradeNote.added" : "jmiMemberUpgradeNote.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jmiMemberUpgradeNoteform?munId=" + jmiMemberUpgradeNote.getMunId();
            }
        }

        return success;
    }
}
