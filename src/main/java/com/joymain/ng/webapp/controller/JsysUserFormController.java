package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JsysUserManager;
import com.joymain.ng.model.JsysUser;
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
@RequestMapping("/jsysUserform*")
public class JsysUserFormController extends BaseFormController {
    private JsysUserManager jsysUserManager = null;

    @Autowired
    public void setJsysUserManager(JsysUserManager jsysUserManager) {
        this.jsysUserManager = jsysUserManager;
    }

    public JsysUserFormController() {
        setCancelView("redirect:jsysUsers");
        setSuccessView("redirect:jsysUsers");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JsysUser showForm(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");

        if (!StringUtils.isBlank(userCode)) {
            return jsysUserManager.get(new String(userCode));
        }

        return new JsysUser();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JsysUser jsysUser, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jsysUser, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jsysUserform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jsysUser.getUserCode() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jsysUserManager.remove(jsysUser.getUserCode());
            saveMessage(request, getText("jsysUser.deleted", locale));
        } else {
            jsysUserManager.save(jsysUser);
            String key = (isNew) ? "jsysUser.added" : "jsysUser.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jsysUserform?userCode=" + jsysUser.getUserCode();
            }
        }

        return success;
    }
}
