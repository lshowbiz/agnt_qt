package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JsysListValueManager;
import com.joymain.ng.model.JsysListValue;
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
@RequestMapping("/jsysListValueform*")
public class JsysListValueFormController extends BaseFormController {
    private JsysListValueManager jsysListValueManager = null;

    @Autowired
    public void setJsysListValueManager(JsysListValueManager jsysListValueManager) {
        this.jsysListValueManager = jsysListValueManager;
    }

    public JsysListValueFormController() {
        setCancelView("redirect:jsysListValues");
        setSuccessView("redirect:jsysListValues");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JsysListValue showForm(HttpServletRequest request)
    throws Exception {
        String valueId = request.getParameter("valueId");

        if (!StringUtils.isBlank(valueId)) {
            return jsysListValueManager.get(new Long(valueId));
        }

        return new JsysListValue();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JsysListValue jsysListValue, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jsysListValue, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jsysListValueform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jsysListValue.getValueId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jsysListValueManager.remove(jsysListValue.getValueId());
            saveMessage(request, getText("jsysListValue.deleted", locale));
        } else {
            jsysListValueManager.save(jsysListValue);
            String key = (isNew) ? "jsysListValue.added" : "jsysListValue.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jsysListValueform?valueId=" + jsysListValue.getValueId();
            }
        }

        return success;
    }
}
