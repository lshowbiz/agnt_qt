package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.MailStatusManager;
import com.joymain.ng.model.MailStatus;
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
@RequestMapping("/mailStatusform*")
public class MailStatusFormController extends BaseFormController {
    private MailStatusManager mailStatusManager = null;

    @Autowired
    public void setMailStatusManager(MailStatusManager mailStatusManager) {
        this.mailStatusManager = mailStatusManager;
    }

    public MailStatusFormController() {
        setCancelView("redirect:mailStatuss");
        setSuccessView("redirect:mailStatuss");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected MailStatus showForm(HttpServletRequest request)
    throws Exception {
        String statusId = request.getParameter("statusId");

        if (!StringUtils.isBlank(statusId)) {
            return mailStatusManager.get(new Long(statusId));
        }

        return new MailStatus();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(MailStatus mailStatus, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(mailStatus, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "mailStatusform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (mailStatus.getStatusId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            mailStatusManager.remove(mailStatus.getStatusId());
            saveMessage(request, getText("mailStatus.deleted", locale));
        } else {
            mailStatusManager.save(mailStatus);
            String key = (isNew) ? "mailStatus.added" : "mailStatus.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:mailStatusform?statusId=" + mailStatus.getStatusId();
            }
        }

        return success;
    }
}
