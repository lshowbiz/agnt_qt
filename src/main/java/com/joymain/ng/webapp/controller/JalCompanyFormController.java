package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JalCompanyManager;
import com.joymain.ng.model.JalCompany;
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
@RequestMapping("/jalCompanyform*")
public class JalCompanyFormController extends BaseFormController {
    private JalCompanyManager jalCompanyManager = null;

    @Autowired
    public void setJalCompanyManager(JalCompanyManager jalCompanyManager) {
        this.jalCompanyManager = jalCompanyManager;
    }

    public JalCompanyFormController() {
        setCancelView("redirect:jalCompanies");
        setSuccessView("redirect:jalCompanies");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JalCompany showForm(HttpServletRequest request)
    throws Exception {
        String companyId = request.getParameter("companyId");

        if (!StringUtils.isBlank(companyId)) {
            return jalCompanyManager.get(new Long(companyId));
        }

        return new JalCompany();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JalCompany jalCompany, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jalCompany, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jalCompanyform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jalCompany.getCompanyId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jalCompanyManager.remove(jalCompany.getCompanyId());
            saveMessage(request, getText("jalCompany.deleted", locale));
        } else {
            jalCompanyManager.save(jalCompany);
            String key = (isNew) ? "jalCompany.added" : "jalCompany.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jalCompanyform?companyId=" + jalCompany.getCompanyId();
            }
        }

        return success;
    }
}
