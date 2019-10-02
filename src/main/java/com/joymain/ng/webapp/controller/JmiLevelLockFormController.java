package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JmiLevelLockManager;
import com.joymain.ng.model.JmiLevelLock;
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
@RequestMapping("/jmiLevelLockform*")
public class JmiLevelLockFormController extends BaseFormController {
    private JmiLevelLockManager jmiLevelLockManager = null;

    @Autowired
    public void setJmiLevelLockManager(JmiLevelLockManager jmiLevelLockManager) {
        this.jmiLevelLockManager = jmiLevelLockManager;
    }

    public JmiLevelLockFormController() {
        setCancelView("redirect:jmiLevelLocks");
        setSuccessView("redirect:jmiLevelLocks");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JmiLevelLock showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return jmiLevelLockManager.get(new Long(id));
        }

        return new JmiLevelLock();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JmiLevelLock jmiLevelLock, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jmiLevelLock, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jmiLevelLockform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jmiLevelLock.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jmiLevelLockManager.remove(jmiLevelLock.getId());
            saveMessage(request, getText("jmiLevelLock.deleted", locale));
        } else {
            jmiLevelLockManager.save(jmiLevelLock);
            String key = (isNew) ? "jmiLevelLock.added" : "jmiLevelLock.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jmiLevelLockform?id=" + jmiLevelLock.getId();
            }
        }

        return success;
    }
}
