package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.FiSecurityDepositJournalManager;
import com.joymain.ng.model.FiSecurityDepositJournal;
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
@RequestMapping("/fiSecurityDepositJournalform*")
public class FiSecurityDepositJournalFormController extends BaseFormController {
    private FiSecurityDepositJournalManager fiSecurityDepositJournalManager = null;

    @Autowired
    public void setFiSecurityDepositJournalManager(FiSecurityDepositJournalManager fiSecurityDepositJournalManager) {
        this.fiSecurityDepositJournalManager = fiSecurityDepositJournalManager;
    }

    public FiSecurityDepositJournalFormController() {
        setCancelView("redirect:fiSecurityDepositJournals");
        setSuccessView("redirect:fiSecurityDepositJournals");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FiSecurityDepositJournal showForm(HttpServletRequest request)
    throws Exception {
        String journalId = request.getParameter("journalId");

        if (!StringUtils.isBlank(journalId)) {
            return fiSecurityDepositJournalManager.get(new Long(journalId));
        }

        return new FiSecurityDepositJournal();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FiSecurityDepositJournal fiSecurityDepositJournal, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(fiSecurityDepositJournal, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "fiSecurityDepositJournalform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (fiSecurityDepositJournal.getJournalId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            fiSecurityDepositJournalManager.remove(fiSecurityDepositJournal.getJournalId());
            saveMessage(request, getText("fiSecurityDepositJournal.deleted", locale));
        } else {
            fiSecurityDepositJournalManager.save(fiSecurityDepositJournal);
            String key = (isNew) ? "fiSecurityDepositJournal.added" : "fiSecurityDepositJournal.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:fiSecurityDepositJournalform?journalId=" + fiSecurityDepositJournal.getJournalId();
            }
        }

        return success;
    }
}
