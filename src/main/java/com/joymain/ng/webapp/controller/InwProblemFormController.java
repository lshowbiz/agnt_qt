package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.InwProblemManager;
import com.joymain.ng.model.InwProblem;
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
@RequestMapping("/inwProblemform*")
public class InwProblemFormController extends BaseFormController {
    private InwProblemManager inwProblemManager = null;

    @Autowired
    public void setInwProblemManager(InwProblemManager inwProblemManager) {
        this.inwProblemManager = inwProblemManager;
    }

    public InwProblemFormController() {
        setCancelView("redirect:inwProblems");
        setSuccessView("redirect:inwProblems");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected InwProblem showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return inwProblemManager.get(new Long(id));
        }

        return new InwProblem();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(InwProblem inwProblem, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(inwProblem, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "inwProblemform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (inwProblem.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            inwProblemManager.remove(inwProblem.getId());
            saveMessage(request, getText("inwProblem.deleted", locale));
        } else {
            inwProblemManager.save(inwProblem);
            String key = (isNew) ? "inwProblem.added" : "inwProblem.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:inwProblemform?id=" + inwProblem.getId();
            }
        }

        return success;
    }
}
