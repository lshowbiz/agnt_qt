package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.model.JmiMember;
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
@RequestMapping("/jmiMemberform*")
public class JmiMemberFormController extends BaseFormController {
    private JmiMemberManager jmiMemberManager = null;

    @Autowired
    public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
        this.jmiMemberManager = jmiMemberManager;
    }

    public JmiMemberFormController() {
        setCancelView("redirect:jmiMembers");
        setSuccessView("redirect:jmiMembers");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JmiMember showForm(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");

        if (!StringUtils.isBlank(userCode)) {
            return jmiMemberManager.get(new String(userCode));
        }

        return new JmiMember();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JmiMember jmiMember, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }
        if (validator != null) { // validator is null during testing
            validator.validate(jmiMember, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jmiMemberform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jmiMember.getUserCode() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jmiMemberManager.remove(jmiMember.getUserCode());
            saveMessage(request, getText("jmiMember.deleted", locale));
        } else {
            jmiMemberManager.save(jmiMember);
            String key = (isNew) ? "jmiMember.added" : "jmiMember.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jmiMemberform?userCode=" + jmiMember.getUserCode();
            }
        }

        return success;
    }
}
