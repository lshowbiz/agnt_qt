package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JpmProductWineTemplateManager;
import com.joymain.ng.model.JpmProductWineTemplate;
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
@RequestMapping("/jpmProductWineTemplateform*")
public class JpmProductWineTemplateFormController extends BaseFormController {
    private JpmProductWineTemplateManager jpmProductWineTemplateManager = null;

    @Autowired
    public void setJpmProductWineTemplateManager(JpmProductWineTemplateManager jpmProductWineTemplateManager) {
        this.jpmProductWineTemplateManager = jpmProductWineTemplateManager;
    }

    public JpmProductWineTemplateFormController() {
        setCancelView("redirect:jpmProductWineTemplates");
        setSuccessView("redirect:jpmProductWineTemplates");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JpmProductWineTemplate showForm(HttpServletRequest request)
    throws Exception {
        String productTemplateNo = request.getParameter("productTemplateNo");

        if (!StringUtils.isBlank(productTemplateNo)) {
            return jpmProductWineTemplateManager.get(new Long(productTemplateNo));
        }

        return new JpmProductWineTemplate();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JpmProductWineTemplate jpmProductWineTemplate, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jpmProductWineTemplate, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jpmProductWineTemplateform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jpmProductWineTemplate.getProductTemplateNo() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jpmProductWineTemplateManager.remove(jpmProductWineTemplate.getProductTemplateNo());
            saveMessage(request, getText("jpmProductWineTemplate.deleted", locale));
        } else {
            jpmProductWineTemplateManager.save(jpmProductWineTemplate);
            String key = (isNew) ? "jpmProductWineTemplate.added" : "jpmProductWineTemplate.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jpmProductWineTemplateform?productTemplateNo=" + jpmProductWineTemplate.getProductTemplateNo();
            }
        }

        return success;
    }
}
