package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JsysResourceManager;
import com.joymain.ng.model.JsysResource;
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
@RequestMapping("/jsysResourceform*")
public class JsysResourceFormController extends BaseFormController {
    private JsysResourceManager jsysResourceManager = null;

    @Autowired
    public void setJsysResourceManager(JsysResourceManager jsysResourceManager) {
        this.jsysResourceManager = jsysResourceManager;
    }

    public JsysResourceFormController() {
        setCancelView("redirect:jsysResources");
        setSuccessView("redirect:jsysResources");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JsysResource showForm(HttpServletRequest request)
    throws Exception {
        String resId = request.getParameter("resId");

        if (!StringUtils.isBlank(resId)) {
            return jsysResourceManager.get(new Long(resId));
        }

        return new JsysResource();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JsysResource jsysResource, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jsysResource, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jsysResourceform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jsysResource.getResId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jsysResourceManager.remove(jsysResource.getResId());
            saveMessage(request, getText("jsysResource.deleted", locale));
        } else {
            jsysResourceManager.save(jsysResource);
            String key = (isNew) ? "jsysResource.added" : "jsysResource.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jsysResourceform?resId=" + jsysResource.getResId();
            }
        }

        return success;
    }
}
