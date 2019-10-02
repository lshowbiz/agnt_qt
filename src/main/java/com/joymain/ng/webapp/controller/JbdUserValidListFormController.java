package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JbdUserValidListManager;
import com.joymain.ng.model.JbdUserValidList;
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
@RequestMapping("/jbdUserValidListform*")
public class JbdUserValidListFormController extends BaseFormController {
    private JbdUserValidListManager jbdUserValidListManager = null;

    @Autowired
    public void setJbdUserValidListManager(JbdUserValidListManager jbdUserValidListManager) {
        this.jbdUserValidListManager = jbdUserValidListManager;
    }

    public JbdUserValidListFormController() {
        setCancelView("redirect:jbdUserValidLists");
        setSuccessView("redirect:jbdUserValidLists");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JbdUserValidList showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return jbdUserValidListManager.get(new Long(id));
        }

        return new JbdUserValidList();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JbdUserValidList jbdUserValidList, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jbdUserValidList, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jbdUserValidListform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jbdUserValidList.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jbdUserValidListManager.remove(jbdUserValidList.getId());
            saveMessage(request, getText("jbdUserValidList.deleted", locale));
        } else {
            jbdUserValidListManager.save(jbdUserValidList);
            String key = (isNew) ? "jbdUserValidList.added" : "jbdUserValidList.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jbdUserValidListform?id=" + jbdUserValidList.getId();
            }
        }

        return success;
    }
}
