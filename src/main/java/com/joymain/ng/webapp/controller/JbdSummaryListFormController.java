package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JbdSummaryListManager;
import com.joymain.ng.model.JbdSummaryList;
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
@RequestMapping("/jbdSummaryListform*")
public class JbdSummaryListFormController extends BaseFormController {
    private JbdSummaryListManager jbdSummaryListManager = null;

    @Autowired
    public void setJbdSummaryListManager(JbdSummaryListManager jbdSummaryListManager) {
        this.jbdSummaryListManager = jbdSummaryListManager;
    }

    public JbdSummaryListFormController() {
        setCancelView("redirect:jbdSummaryLists");
        setSuccessView("redirect:jbdSummaryLists");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JbdSummaryList showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return jbdSummaryListManager.get(new Long(id));
        }

        return new JbdSummaryList();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JbdSummaryList jbdSummaryList, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jbdSummaryList, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jbdSummaryListform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jbdSummaryList.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jbdSummaryListManager.remove(jbdSummaryList.getId());
            saveMessage(request, getText("jbdSummaryList.deleted", locale));
        } else {
            jbdSummaryListManager.save(jbdSummaryList);
            String key = (isNew) ? "jbdSummaryList.added" : "jbdSummaryList.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jbdSummaryListform?id=" + jbdSummaryList.getId();
            }
        }

        return success;
    }
}
