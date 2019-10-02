package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JsysUserRoleManager;
import com.joymain.ng.model.JsysUserRole;
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
@RequestMapping("/jsysUserRoleform*")
public class JsysUserRoleFormController extends BaseFormController {
    private JsysUserRoleManager jsysUserRoleManager = null;

    @Autowired
    public void setJsysUserRoleManager(JsysUserRoleManager jsysUserRoleManager) {
        this.jsysUserRoleManager = jsysUserRoleManager;
    }

    public JsysUserRoleFormController() {
        setCancelView("redirect:jsysUserRoles");
        setSuccessView("redirect:jsysUserRoles");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JsysUserRole showForm(HttpServletRequest request)
    throws Exception {
        String ruId = request.getParameter("ruId");

        if (!StringUtils.isBlank(ruId)) {
            return jsysUserRoleManager.get(new Long(ruId));
        }

        return new JsysUserRole();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JsysUserRole jsysUserRole, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jsysUserRole, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jsysUserRoleform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jsysUserRole.getRuId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jsysUserRoleManager.remove(jsysUserRole.getRuId());
            saveMessage(request, getText("jsysUserRole.deleted", locale));
        } else {
            jsysUserRoleManager.save(jsysUserRole);
            String key = (isNew) ? "jsysUserRole.added" : "jsysUserRole.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jsysUserRoleform?ruId=" + jsysUserRole.getRuId();
            }
        }

        return success;
    }
}
