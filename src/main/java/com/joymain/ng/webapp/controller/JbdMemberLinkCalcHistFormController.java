package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JbdMemberLinkCalcHistManager;
import com.joymain.ng.model.JbdMemberLinkCalcHist;
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
@RequestMapping("/jbdMemberLinkCalcHistform*")
public class JbdMemberLinkCalcHistFormController extends BaseFormController {
    private JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager = null;

    @Autowired
    public void setJbdMemberLinkCalcHistManager(JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager) {
        this.jbdMemberLinkCalcHistManager = jbdMemberLinkCalcHistManager;
    }

    public JbdMemberLinkCalcHistFormController() {
        setCancelView("redirect:jbdMemberLinkCalcHists");
        setSuccessView("redirect:jbdMemberLinkCalcHists");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JbdMemberLinkCalcHist showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return jbdMemberLinkCalcHistManager.get(new Long(id));
        }

        return new JbdMemberLinkCalcHist();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JbdMemberLinkCalcHist jbdMemberLinkCalcHist, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jbdMemberLinkCalcHist, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jbdMemberLinkCalcHistform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jbdMemberLinkCalcHist.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jbdMemberLinkCalcHistManager.remove(jbdMemberLinkCalcHist.getId());
            saveMessage(request, getText("jbdMemberLinkCalcHist.deleted", locale));
        } else {
            jbdMemberLinkCalcHistManager.save(jbdMemberLinkCalcHist);
            String key = (isNew) ? "jbdMemberLinkCalcHist.added" : "jbdMemberLinkCalcHist.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jbdMemberLinkCalcHistform?id=" + jbdMemberLinkCalcHist.getId();
            }
        }

        return success;
    }
}
