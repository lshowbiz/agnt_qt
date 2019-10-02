package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JmiLinkRefManager;
import com.joymain.ng.model.JmiLinkRef;
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
@RequestMapping("/jmiLinkRefform*")
public class JmiLinkRefFormController extends BaseFormController {
    private JmiLinkRefManager jmiLinkRefManager = null;

    @Autowired
    public void setJmiLinkRefManager(JmiLinkRefManager jmiLinkRefManager) {
        this.jmiLinkRefManager = jmiLinkRefManager;
    }

    public JmiLinkRefFormController() {
        setCancelView("redirect:jmiLinkRefs");
        setSuccessView("redirect:jmiLinkRefs");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JmiLinkRef showForm(HttpServletRequest request)
    throws Exception {
        String userCode = request.getParameter("userCode");

        if (!StringUtils.isBlank(userCode)) {
            return jmiLinkRefManager.get(new String(userCode));
        }

        return new JmiLinkRef();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JmiLinkRef jmiLinkRef, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jmiLinkRef, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jmiLinkRefform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jmiLinkRef.getUserCode() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jmiLinkRefManager.remove(jmiLinkRef.getUserCode());
            saveMessage(request, getText("jmiLinkRef.deleted", locale));
        } else {
            jmiLinkRefManager.save(jmiLinkRef);
            String key = (isNew) ? "jmiLinkRef.added" : "jmiLinkRef.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jmiLinkRefform?userCode=" + jmiLinkRef.getUserCode();
            }
        }

        return success;
    }
}
