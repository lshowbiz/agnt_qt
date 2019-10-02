package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.FiFundbookJournalManager;
import com.joymain.ng.model.FiFundbookJournal;
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
@RequestMapping("/fiFundbookJournalform*")
public class FiFundbookJournalFormController extends BaseFormController {
    private FiFundbookJournalManager fiFundbookJournalManager = null;

    @Autowired
    public void setFiFundbookJournalManager(FiFundbookJournalManager fiFundbookJournalManager) {
        this.fiFundbookJournalManager = fiFundbookJournalManager;
    }

    public FiFundbookJournalFormController() {
        setCancelView("redirect:fiFundbookJournals");
        setSuccessView("redirect:fiFundbookJournals");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FiFundbookJournal showForm(HttpServletRequest request)
    throws Exception {
        String journalId = request.getParameter("journalId");

        if (!StringUtils.isBlank(journalId)) {
            return fiFundbookJournalManager.get(new Long(journalId));
        }

        return new FiFundbookJournal();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FiFundbookJournal fiFundbookJournal, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(fiFundbookJournal, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "fiFundbookJournalform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (fiFundbookJournal.getJournalId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            fiFundbookJournalManager.remove(fiFundbookJournal.getJournalId());
            saveMessage(request, getText("fiFundbookJournal.deleted", locale));
        } else {
            fiFundbookJournalManager.save(fiFundbookJournal);
            String key = (isNew) ? "fiFundbookJournal.added" : "fiFundbookJournal.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:fiFundbookJournalform?journalId=" + fiFundbookJournal.getJournalId();
            }
        }

        return success;
    }
}
