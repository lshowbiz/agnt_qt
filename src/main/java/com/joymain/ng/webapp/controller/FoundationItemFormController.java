package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.FoundationItemManager;
import com.joymain.ng.model.FoundationItem;
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
@RequestMapping("/foundationItemform*")
public class FoundationItemFormController extends BaseFormController {
    private FoundationItemManager foundationItemManager = null;

    @Autowired
    public void setFoundationItemManager(FoundationItemManager foundationItemManager) {
        this.foundationItemManager = foundationItemManager;
    }

    public FoundationItemFormController() {
        setCancelView("redirect:foundationItems");
        setSuccessView("redirect:foundationItems");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FoundationItem showForm(HttpServletRequest request)
    throws Exception {
        String fiId = request.getParameter("fiId");

        if (!StringUtils.isBlank(fiId)) {
            return foundationItemManager.get(new Long(fiId));
        }

        return new FoundationItem();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FoundationItem foundationItem, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(foundationItem, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "foundationItemform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (foundationItem.getFiId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            foundationItemManager.remove(foundationItem.getFiId());
            saveMessage(request, getText("foundationItem.deleted", locale));
        } else {
            foundationItemManager.save(foundationItem);
            String key = (isNew) ? "foundationItem.added" : "foundationItem.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:foundationItemform?fiId=" + foundationItem.getFiId();
            }
        }

        return success;
    }
}
