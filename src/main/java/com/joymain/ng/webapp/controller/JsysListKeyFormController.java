package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JsysListKeyManager;
import com.joymain.ng.model.JsysListKey;
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
@RequestMapping("/jsysListKeyform*")
public class JsysListKeyFormController extends BaseFormController {
    private JsysListKeyManager jsysListKeyManager = null;

    @Autowired
    public void setJsysListKeyManager(JsysListKeyManager jsysListKeyManager) {
        this.jsysListKeyManager = jsysListKeyManager;
    }

    public JsysListKeyFormController() {
        setCancelView("redirect:jsysListKeys");
        setSuccessView("redirect:jsysListKeys");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JsysListKey showForm(HttpServletRequest request)
    throws Exception {
        String keyId = request.getParameter("keyId");

        if (!StringUtils.isBlank(keyId)) {
            return jsysListKeyManager.get(new Long(keyId));
        }

        return new JsysListKey();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JsysListKey jsysListKey, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jsysListKey, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jsysListKeyform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jsysListKey.getKeyId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jsysListKeyManager.remove(jsysListKey.getKeyId());
            saveMessage(request, getText("jsysListKey.deleted", locale));
        } else {
            jsysListKeyManager.save(jsysListKey);
            String key = (isNew) ? "jsysListKey.added" : "jsysListKey.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jsysListKeyform?keyId=" + jsysListKey.getKeyId();
            }
        }

        return success;
    }
}
