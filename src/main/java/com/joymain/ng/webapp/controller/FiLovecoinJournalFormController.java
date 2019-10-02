package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.FiLovecoinJournalManager;
import com.joymain.ng.model.FiLovecoinJournal;
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
@RequestMapping("/fiLovecoinJournalform*")
public class FiLovecoinJournalFormController extends BaseFormController {
    private FiLovecoinJournalManager fiLovecoinJournalManager = null;

    @Autowired
    public void setFiLovecoinJournalManager(FiLovecoinJournalManager fiLovecoinJournalManager) {
        this.fiLovecoinJournalManager = fiLovecoinJournalManager;
    }

    public FiLovecoinJournalFormController() {
        setCancelView("redirect:fiLovecoinJournals");
        setSuccessView("redirect:fiLovecoinJournals");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FiLovecoinJournal showForm(HttpServletRequest request)
    throws Exception {
        String journalId = request.getParameter("journalId");

        if (!StringUtils.isBlank(journalId)) {
            return fiLovecoinJournalManager.get(new Long(journalId));
        }

        return new FiLovecoinJournal();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FiLovecoinJournal fiLovecoinJournal, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(fiLovecoinJournal, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "fiLovecoinJournalform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (fiLovecoinJournal.getJournalId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            fiLovecoinJournalManager.remove(fiLovecoinJournal.getJournalId());
            saveMessage(request, getText("fiLovecoinJournal.deleted", locale));
        } else {
            fiLovecoinJournalManager.save(fiLovecoinJournal);
            String key = (isNew) ? "fiLovecoinJournal.added" : "fiLovecoinJournal.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:fiLovecoinJournalform?journalId=" + fiLovecoinJournal.getJournalId();
            }
        }

        return success;
    }
}
