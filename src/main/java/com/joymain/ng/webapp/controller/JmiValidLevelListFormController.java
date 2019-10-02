package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JmiValidLevelListManager;
import com.joymain.ng.model.JmiValidLevelList;
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
@RequestMapping("/jmiValidLevelListform*")
public class JmiValidLevelListFormController extends BaseFormController {
    private JmiValidLevelListManager jmiValidLevelListManager = null;

    @Autowired
    public void setJmiValidLevelListManager(JmiValidLevelListManager jmiValidLevelListManager) {
        this.jmiValidLevelListManager = jmiValidLevelListManager;
    }

    public JmiValidLevelListFormController() {
        setCancelView("redirect:jmiValidLevelLists");
        setSuccessView("redirect:jmiValidLevelLists");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JmiValidLevelList showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return jmiValidLevelListManager.get(new Long(id));
        }

        return new JmiValidLevelList();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JmiValidLevelList jmiValidLevelList, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jmiValidLevelList, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jmiValidLevelListform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jmiValidLevelList.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jmiValidLevelListManager.remove(jmiValidLevelList.getId());
            saveMessage(request, getText("jmiValidLevelList.deleted", locale));
        } else {
            jmiValidLevelListManager.save(jmiValidLevelList);
            String key = (isNew) ? "jmiValidLevelList.added" : "jmiValidLevelList.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jmiValidLevelListform?id=" + jmiValidLevelList.getId();
            }
        }

        return success;
    }
}
