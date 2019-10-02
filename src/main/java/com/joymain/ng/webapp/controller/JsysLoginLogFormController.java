package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JsysLoginLogManager;
import com.joymain.ng.model.JsysLoginLog;
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
@RequestMapping("/jsysLoginLogform*")
public class JsysLoginLogFormController extends BaseFormController {
    private JsysLoginLogManager jsysLoginLogManager = null;

    @Autowired
    public void setJsysLoginLogManager(JsysLoginLogManager jsysLoginLogManager) {
        this.jsysLoginLogManager = jsysLoginLogManager;
    }

    public JsysLoginLogFormController() {
        setCancelView("redirect:jsysLoginLogs");
        setSuccessView("redirect:jsysLoginLogs");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JsysLoginLog showForm(HttpServletRequest request)
    throws Exception {
        String llId = request.getParameter("llId");

        if (!StringUtils.isBlank(llId)) {
            return jsysLoginLogManager.get(new Long(llId));
        }

        return new JsysLoginLog();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JsysLoginLog jsysLoginLog, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jsysLoginLog, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jsysLoginLogform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jsysLoginLog.getLlId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jsysLoginLogManager.remove(jsysLoginLog.getLlId());
            saveMessage(request, getText("jsysLoginLog.deleted", locale));
        } else {
            jsysLoginLogManager.save(jsysLoginLog);
            String key = (isNew) ? "jsysLoginLog.added" : "jsysLoginLog.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jsysLoginLogform?llId=" + jsysLoginLog.getLlId();
            }
        }

        return success;
    }
}
