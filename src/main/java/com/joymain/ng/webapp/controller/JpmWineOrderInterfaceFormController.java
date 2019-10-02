package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JpmWineOrderInterfaceManager;
import com.joymain.ng.model.JpmWineOrderInterface;
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
@RequestMapping("/jpmWineOrderInterfaceform*")
public class JpmWineOrderInterfaceFormController extends BaseFormController {
    private JpmWineOrderInterfaceManager jpmWineOrderInterfaceManager = null;

    @Autowired
    public void setJpmWineOrderInterfaceManager(JpmWineOrderInterfaceManager jpmWineOrderInterfaceManager) {
        this.jpmWineOrderInterfaceManager = jpmWineOrderInterfaceManager;
    }

    public JpmWineOrderInterfaceFormController() {
        setCancelView("redirect:jpmWineOrderInterfaces");
        setSuccessView("redirect:jpmWineOrderInterfaces");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JpmWineOrderInterface showForm(HttpServletRequest request)
    throws Exception {
        String moId = request.getParameter("moId");

        if (!StringUtils.isBlank(moId)) {
            return jpmWineOrderInterfaceManager.get(new Long(moId));
        }

        return new JpmWineOrderInterface();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JpmWineOrderInterface jpmWineOrderInterface, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jpmWineOrderInterface, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jpmWineOrderInterfaceform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jpmWineOrderInterface.getMoId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jpmWineOrderInterfaceManager.remove(jpmWineOrderInterface.getMoId());
            saveMessage(request, getText("jpmWineOrderInterface.deleted", locale));
        } else {
            jpmWineOrderInterfaceManager.save(jpmWineOrderInterface);
            String key = (isNew) ? "jpmWineOrderInterface.added" : "jpmWineOrderInterface.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jpmWineOrderInterfaceform?moId=" + jpmWineOrderInterface.getMoId();
            }
        }

        return success;
    }
}
