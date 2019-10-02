package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JbdSendRecordHistManager;
import com.joymain.ng.model.JbdSendRecordHist;
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
@RequestMapping("/jbdSendRecordHistform*")
public class JbdSendRecordHistFormController extends BaseFormController {
    private JbdSendRecordHistManager jbdSendRecordHistManager = null;

    @Autowired
    public void setJbdSendRecordHistManager(JbdSendRecordHistManager jbdSendRecordHistManager) {
        this.jbdSendRecordHistManager = jbdSendRecordHistManager;
    }

    public JbdSendRecordHistFormController() {
        setCancelView("redirect:jbdSendRecordHists");
        setSuccessView("redirect:jbdSendRecordHists");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JbdSendRecordHist showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return jbdSendRecordHistManager.get(new Long(id));
        }

        return new JbdSendRecordHist();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JbdSendRecordHist jbdSendRecordHist, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jbdSendRecordHist, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jbdSendRecordHistform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jbdSendRecordHist.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jbdSendRecordHistManager.remove(jbdSendRecordHist.getId());
            saveMessage(request, getText("jbdSendRecordHist.deleted", locale));
        } else {
            jbdSendRecordHistManager.save(jbdSendRecordHist);
            String key = (isNew) ? "jbdSendRecordHist.added" : "jbdSendRecordHist.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jbdSendRecordHistform?id=" + jbdSendRecordHist.getId();
            }
        }

        return success;
    }
}
