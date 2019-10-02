package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.FiBillAccountRelationManager;
import com.joymain.ng.model.FiBillAccountRelation;
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
@RequestMapping("/fiBillAccountRelationform*")
public class FiBillAccountRelationFormController extends BaseFormController {
    private FiBillAccountRelationManager fiBillAccountRelationManager = null;

    @Autowired
    public void setFiBillAccountRelationManager(FiBillAccountRelationManager fiBillAccountRelationManager) {
        this.fiBillAccountRelationManager = fiBillAccountRelationManager;
    }

    public FiBillAccountRelationFormController() {
        setCancelView("redirect:fiBillAccountRelations");
        setSuccessView("redirect:fiBillAccountRelations");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FiBillAccountRelation showForm(HttpServletRequest request)
    throws Exception {
        String relationId = request.getParameter("relationId");

        if (!StringUtils.isBlank(relationId)) {
            return fiBillAccountRelationManager.get(new Long(relationId));
        }

        return new FiBillAccountRelation();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FiBillAccountRelation fiBillAccountRelation, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(fiBillAccountRelation, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "fiBillAccountRelationform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (fiBillAccountRelation.getRelationId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            fiBillAccountRelationManager.remove(fiBillAccountRelation.getRelationId());
            saveMessage(request, getText("fiBillAccountRelation.deleted", locale));
        } else {
            fiBillAccountRelationManager.save(fiBillAccountRelation);
            String key = (isNew) ? "fiBillAccountRelation.added" : "fiBillAccountRelation.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:fiBillAccountRelationform?relationId=" + fiBillAccountRelation.getRelationId();
            }
        }

        return success;
    }
}
