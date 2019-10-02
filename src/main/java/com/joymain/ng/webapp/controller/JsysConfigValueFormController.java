package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JsysConfigValueManager;
import com.joymain.ng.model.JsysConfigValue;
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
@RequestMapping("/jsysConfigValueform*")
public class JsysConfigValueFormController extends BaseFormController {
    private JsysConfigValueManager jsysConfigValueManager = null;

    @Autowired
    public void setJsysConfigValueManager(JsysConfigValueManager jsysConfigValueManager) {
        this.jsysConfigValueManager = jsysConfigValueManager;
    }

    public JsysConfigValueFormController() {
        setCancelView("redirect:jsysConfigValues");
        setSuccessView("redirect:jsysConfigValues");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JsysConfigValue showForm(HttpServletRequest request)
    throws Exception {
        String valueId = request.getParameter("valueId");

        if (!StringUtils.isBlank(valueId)) {
            return jsysConfigValueManager.get(new Long(valueId));
        }

        return new JsysConfigValue();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JsysConfigValue jsysConfigValue, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jsysConfigValue, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jsysConfigValueform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jsysConfigValue.getValueId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jsysConfigValueManager.remove(jsysConfigValue.getValueId());
            saveMessage(request, getText("jsysConfigValue.deleted", locale));
        } else {
            jsysConfigValueManager.save(jsysConfigValue);
            String key = (isNew) ? "jsysConfigValue.added" : "jsysConfigValue.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jsysConfigValueform?valueId=" + jsysConfigValue.getValueId();
            }
        }

        return success;
    }
}
