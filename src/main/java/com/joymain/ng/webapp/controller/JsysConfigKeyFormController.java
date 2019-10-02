package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JsysConfigKeyManager;
import com.joymain.ng.model.JsysConfigKey;
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
@RequestMapping("/jsysConfigKeyform*")
public class JsysConfigKeyFormController extends BaseFormController {
    private JsysConfigKeyManager jsysConfigKeyManager = null;

    @Autowired
    public void setJsysConfigKeyManager(JsysConfigKeyManager jsysConfigKeyManager) {
        this.jsysConfigKeyManager = jsysConfigKeyManager;
    }

    public JsysConfigKeyFormController() {
        setCancelView("redirect:jsysConfigKeys");
        setSuccessView("redirect:jsysConfigKeys");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JsysConfigKey showForm(HttpServletRequest request)
    throws Exception {
        String keyId = request.getParameter("keyId");

        if (!StringUtils.isBlank(keyId)) {
            return jsysConfigKeyManager.get(new Long(keyId));
        }

        return new JsysConfigKey();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JsysConfigKey jsysConfigKey, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jsysConfigKey, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jsysConfigKeyform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jsysConfigKey.getKeyId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jsysConfigKeyManager.remove(jsysConfigKey.getKeyId());
            saveMessage(request, getText("jsysConfigKey.deleted", locale));
        } else {
            jsysConfigKeyManager.save(jsysConfigKey);
            String key = (isNew) ? "jsysConfigKey.added" : "jsysConfigKey.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jsysConfigKeyform?keyId=" + jsysConfigKey.getKeyId();
            }
        }

        return success;
    }
}
