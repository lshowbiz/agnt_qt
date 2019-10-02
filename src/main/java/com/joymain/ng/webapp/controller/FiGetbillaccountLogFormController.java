package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.FiGetbillaccountLogManager;
import com.joymain.ng.model.FiGetbillaccountLog;
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
@RequestMapping("/fiGetbillaccountLogform*")
public class FiGetbillaccountLogFormController extends BaseFormController {
    private FiGetbillaccountLogManager fiGetbillaccountLogManager = null;

    @Autowired
    public void setFiGetbillaccountLogManager(FiGetbillaccountLogManager fiGetbillaccountLogManager) {
        this.fiGetbillaccountLogManager = fiGetbillaccountLogManager;
    }

    public FiGetbillaccountLogFormController() {
        setCancelView("redirect:fiGetbillaccountLogs");
        setSuccessView("redirect:fiGetbillaccountLogs");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FiGetbillaccountLog showForm(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");

        if (!StringUtils.isBlank(logId)) {
            return fiGetbillaccountLogManager.get(new Long(logId));
        }

        return new FiGetbillaccountLog();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FiGetbillaccountLog fiGetbillaccountLog, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(fiGetbillaccountLog, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "fiGetbillaccountLogform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (fiGetbillaccountLog.getLogId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            fiGetbillaccountLogManager.remove(fiGetbillaccountLog.getLogId());
            saveMessage(request, getText("fiGetbillaccountLog.deleted", locale));
        } else {
            fiGetbillaccountLogManager.save(fiGetbillaccountLog);
            String key = (isNew) ? "fiGetbillaccountLog.added" : "fiGetbillaccountLog.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:fiGetbillaccountLogform?logId=" + fiGetbillaccountLog.getLogId();
            }
        }

        return success;
    }
}
