package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JpmProductWineTemplateSubManager;
import com.joymain.ng.model.JpmProductWineTemplateSub;
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
@RequestMapping("/jpmProductWineTemplateSubform*")
public class JpmProductWineTemplateSubFormController extends BaseFormController {
    private JpmProductWineTemplateSubManager jpmProductWineTemplateSubManager = null;

    @Autowired
    public void setJpmProductWineTemplateSubManager(JpmProductWineTemplateSubManager jpmProductWineTemplateSubManager) {
        this.jpmProductWineTemplateSubManager = jpmProductWineTemplateSubManager;
    }

    public JpmProductWineTemplateSubFormController() {
        setCancelView("redirect:jpmProductWineTemplateSubs");
        setSuccessView("redirect:jpmProductWineTemplateSubs");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JpmProductWineTemplateSub showForm(HttpServletRequest request)
    throws Exception {
        String subNo = request.getParameter("subNo");

        if (!StringUtils.isBlank(subNo)) {
            return jpmProductWineTemplateSubManager.get(new String(subNo));
        }

        return new JpmProductWineTemplateSub();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JpmProductWineTemplateSub jpmProductWineTemplateSub, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jpmProductWineTemplateSub, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jpmProductWineTemplateSubform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jpmProductWineTemplateSub.getSubNo() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jpmProductWineTemplateSubManager.remove(jpmProductWineTemplateSub.getSubNo());
            saveMessage(request, getText("jpmProductWineTemplateSub.deleted", locale));
        } else {
            jpmProductWineTemplateSubManager.save(jpmProductWineTemplateSub);
            String key = (isNew) ? "jpmProductWineTemplateSub.added" : "jpmProductWineTemplateSub.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jpmProductWineTemplateSubform?subNo=" + jpmProductWineTemplateSub.getSubNo();
            }
        }

        return success;
    }
}
