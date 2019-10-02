package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JmiDealListManager;
import com.joymain.ng.model.JmiDealList;
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
@RequestMapping("/jmiDealListform*")
public class JmiDealListFormController extends BaseFormController {
    private JmiDealListManager jmiDealListManager = null;

    @Autowired
    public void setJmiDealListManager(JmiDealListManager jmiDealListManager) {
        this.jmiDealListManager = jmiDealListManager;
    }

    public JmiDealListFormController() {
        setCancelView("redirect:jmiDealLists");
        setSuccessView("redirect:jmiDealLists");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JmiDealList showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return jmiDealListManager.get(new Long(id));
        }

        return new JmiDealList();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JmiDealList jmiDealList, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jmiDealList, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jmiDealListform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jmiDealList.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jmiDealListManager.remove(jmiDealList.getId());
            saveMessage(request, getText("jmiDealList.deleted", locale));
        } else {
            jmiDealListManager.save(jmiDealList);
            String key = (isNew) ? "jmiDealList.added" : "jmiDealList.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jmiDealListform?id=" + jmiDealList.getId();
            }
        }

        return success;
    }
}
