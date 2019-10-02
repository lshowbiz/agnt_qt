package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JsysRoleManager;
import com.joymain.ng.model.JsysRole;
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
@RequestMapping("/jsysRoleform*")
public class JsysRoleFormController extends BaseFormController {
    private JsysRoleManager jsysRoleManager = null;

    @Autowired
    public void setJsysRoleManager(JsysRoleManager jsysRoleManager) {
        this.jsysRoleManager = jsysRoleManager;
    }

    public JsysRoleFormController() {
        setCancelView("redirect:jsysRoles");
        setSuccessView("redirect:jsysRoles");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JsysRole showForm(HttpServletRequest request)
    throws Exception {
        String roleId = request.getParameter("roleId");

        if (!StringUtils.isBlank(roleId)) {
            return jsysRoleManager.get(new Long(roleId));
        }

        return new JsysRole();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JsysRole jsysRole, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jsysRole, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jsysRoleform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jsysRole.getRoleId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jsysRoleManager.remove(jsysRole.getRoleId());
            saveMessage(request, getText("jsysRole.deleted", locale));
        } else {
            jsysRoleManager.save(jsysRole);
            String key = (isNew) ? "jsysRole.added" : "jsysRole.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jsysRoleform?roleId=" + jsysRole.getRoleId();
            }
        }

        return success;
    }
}
