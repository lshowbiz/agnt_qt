package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.Jfi99billLogManager;
import com.joymain.ng.model.Jfi99billLog;
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
@RequestMapping("/jfi99billLogform*")
public class Jfi99billLogFormController extends BaseFormController {
    private Jfi99billLogManager jfi99billLogManager = null;

    @Autowired
    public void setJfi99billLogManager(Jfi99billLogManager jfi99billLogManager) {
        this.jfi99billLogManager = jfi99billLogManager;
    }

    public Jfi99billLogFormController() {
        setCancelView("redirect:jfi99billLogs");
        setSuccessView("redirect:jfi99billLogs");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected Jfi99billLog showForm(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");

        if (!StringUtils.isBlank(logId)) {
            return jfi99billLogManager.get(new Long(logId));
        }

        return new Jfi99billLog();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(Jfi99billLog jfi99billLog, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jfi99billLog, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jfi99billLogform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jfi99billLog.getLogId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jfi99billLogManager.remove(jfi99billLog.getLogId());
            saveMessage(request, getText("jfi99billLog.deleted", locale));
        } else {
            jfi99billLogManager.save(jfi99billLog);
            String key = (isNew) ? "jfi99billLog.added" : "jfi99billLog.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jfi99billLogform?logId=" + jfi99billLog.getLogId();
            }
        }

        return success;
    }
}
