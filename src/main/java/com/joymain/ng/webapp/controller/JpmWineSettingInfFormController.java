package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JpmWineSettingInfManager;
import com.joymain.ng.model.JpmWineSettingInf;
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
@RequestMapping("/jpmWineSettingInfform*")
public class JpmWineSettingInfFormController extends BaseFormController {
    private JpmWineSettingInfManager jpmWineSettingInfManager = null;

    @Autowired
    public void setJpmWineSettingInfManager(JpmWineSettingInfManager jpmWineSettingInfManager) {
        this.jpmWineSettingInfManager = jpmWineSettingInfManager;
    }

    public JpmWineSettingInfFormController() {
        setCancelView("redirect:jpmWineSettingInfs");
        setSuccessView("redirect:jpmWineSettingInfs");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JpmWineSettingInf showForm(HttpServletRequest request)
    throws Exception {
        String settingId = request.getParameter("settingId");

        if (!StringUtils.isBlank(settingId)) {
            return jpmWineSettingInfManager.get(new Long(settingId));
        }

        return new JpmWineSettingInf();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JpmWineSettingInf jpmWineSettingInf, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jpmWineSettingInf, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jpmWineSettingInfform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jpmWineSettingInf.getSettingId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jpmWineSettingInfManager.remove(jpmWineSettingInf.getSettingId());
            saveMessage(request, getText("jpmWineSettingInf.deleted", locale));
        } else {
            jpmWineSettingInfManager.save(jpmWineSettingInf);
            String key = (isNew) ? "jpmWineSettingInf.added" : "jpmWineSettingInf.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jpmWineSettingInfform?settingId=" + jpmWineSettingInf.getSettingId();
            }
        }

        return success;
    }
}
