package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JbdUserCardListManager;
import com.joymain.ng.model.JbdUserCardList;
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
@RequestMapping("/jbdUserCardListform*")
public class JbdUserCardListFormController extends BaseFormController {
    private JbdUserCardListManager jbdUserCardListManager = null;

    @Autowired
    public void setJbdUserCardListManager(JbdUserCardListManager jbdUserCardListManager) {
        this.jbdUserCardListManager = jbdUserCardListManager;
    }

    public JbdUserCardListFormController() {
        setCancelView("redirect:jbdUserCardLists");
        setSuccessView("redirect:jbdUserCardLists");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JbdUserCardList showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return jbdUserCardListManager.get(new Long(id));
        }

        return new JbdUserCardList();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JbdUserCardList jbdUserCardList, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jbdUserCardList, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jbdUserCardListform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jbdUserCardList.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jbdUserCardListManager.remove(jbdUserCardList.getId());
            saveMessage(request, getText("jbdUserCardList.deleted", locale));
        } else {
            jbdUserCardListManager.save(jbdUserCardList);
            String key = (isNew) ? "jbdUserCardList.added" : "jbdUserCardList.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jbdUserCardListform?id=" + jbdUserCardList.getId();
            }
        }

        return success;
    }
}
