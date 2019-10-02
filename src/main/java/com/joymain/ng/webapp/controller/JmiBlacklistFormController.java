package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JmiBlacklistManager;
import com.joymain.ng.model.JmiBlacklist;
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
@RequestMapping("/jmiBlacklistform*")
public class JmiBlacklistFormController extends BaseFormController {
    private JmiBlacklistManager jmiBlacklistManager = null;

    @Autowired
    public void setJmiBlacklistManager(JmiBlacklistManager jmiBlacklistManager) {
        this.jmiBlacklistManager = jmiBlacklistManager;
    }

    public JmiBlacklistFormController() {
        setCancelView("redirect:jmiBlacklists");
        setSuccessView("redirect:jmiBlacklists");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JmiBlacklist showForm(HttpServletRequest request)
    throws Exception {
        String blId = request.getParameter("blId");

        if (!StringUtils.isBlank(blId)) {
            return jmiBlacklistManager.get(new Long(blId));
        }

        return new JmiBlacklist();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JmiBlacklist jmiBlacklist, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jmiBlacklist, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jmiBlacklistform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jmiBlacklist.getBlId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jmiBlacklistManager.remove(jmiBlacklist.getBlId());
            saveMessage(request, getText("jmiBlacklist.deleted", locale));
        } else {
            jmiBlacklistManager.save(jmiBlacklist);
            String key = (isNew) ? "jmiBlacklist.added" : "jmiBlacklist.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jmiBlacklistform?blId=" + jmiBlacklist.getBlId();
            }
        }

        return success;
    }
}
