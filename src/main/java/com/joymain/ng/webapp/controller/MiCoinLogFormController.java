package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.MiCoinLogManager;
import com.joymain.ng.model.MiCoinLog;
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
@RequestMapping("/miCoinLogform*")
public class MiCoinLogFormController extends BaseFormController {
    private MiCoinLogManager miCoinLogManager = null;

    @Autowired
    public void setMiCoinLogManager(MiCoinLogManager miCoinLogManager) {
        this.miCoinLogManager = miCoinLogManager;
    }

    public MiCoinLogFormController() {
        setCancelView("redirect:miCoinLogs");
        setSuccessView("redirect:miCoinLogs");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected MiCoinLog showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return miCoinLogManager.get(new Long(id));
        }

        return new MiCoinLog();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(MiCoinLog miCoinLog, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(miCoinLog, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "miCoinLogform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (miCoinLog.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            miCoinLogManager.remove(miCoinLog.getId());
            saveMessage(request, getText("miCoinLog.deleted", locale));
        } else {
            miCoinLogManager.save(miCoinLog);
            String key = (isNew) ? "miCoinLog.added" : "miCoinLog.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:miCoinLogform?id=" + miCoinLog.getId();
            }
        }

        return success;
    }
}
