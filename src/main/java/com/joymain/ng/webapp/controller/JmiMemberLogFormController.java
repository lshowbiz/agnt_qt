package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JmiMemberLogManager;
import com.joymain.ng.model.JmiMemberLog;
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
@RequestMapping("/jmiMemberLogform*")
public class JmiMemberLogFormController extends BaseFormController {
    private JmiMemberLogManager jmiMemberLogManager = null;

    @Autowired
    public void setJmiMemberLogManager(JmiMemberLogManager jmiMemberLogManager) {
        this.jmiMemberLogManager = jmiMemberLogManager;
    }

    public JmiMemberLogFormController() {
        setCancelView("redirect:jmiMemberLogs");
        setSuccessView("redirect:jmiMemberLogs");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JmiMemberLog showForm(HttpServletRequest request)
    throws Exception {
        String logId = request.getParameter("logId");

        if (!StringUtils.isBlank(logId)) {
            return jmiMemberLogManager.get(new Long(logId));
        }

        return new JmiMemberLog();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JmiMemberLog jmiMemberLog, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jmiMemberLog, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jmiMemberLogform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jmiMemberLog.getLogId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jmiMemberLogManager.remove(jmiMemberLog.getLogId());
            saveMessage(request, getText("jmiMemberLog.deleted", locale));
        } else {
            jmiMemberLogManager.save(jmiMemberLog);
            String key = (isNew) ? "jmiMemberLog.added" : "jmiMemberLog.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jmiMemberLogform?logId=" + jmiMemberLog.getLogId();
            }
        }

        return success;
    }
}
