package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.FiCoinLogManager;
import com.joymain.ng.model.FiCoinLog;
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
@RequestMapping("/fiCoinLogform*")
public class FiCoinLogFormController extends BaseFormController {
    private FiCoinLogManager fiCoinLogManager = null;

    @Autowired
    public void setFiCoinLogManager(FiCoinLogManager fiCoinLogManager) {
        this.fiCoinLogManager = fiCoinLogManager;
    }

    public FiCoinLogFormController() {
        setCancelView("redirect:fiCoinLogs");
        setSuccessView("redirect:fiCoinLogs");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FiCoinLog showForm(HttpServletRequest request)
    throws Exception {
        String fclId = request.getParameter("fclId");

        if (!StringUtils.isBlank(fclId)) {
            return fiCoinLogManager.get(new Long(fclId));
        }

        return new FiCoinLog();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FiCoinLog fiCoinLog, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(fiCoinLog, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "fiCoinLogform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (fiCoinLog.getFclId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            fiCoinLogManager.remove(fiCoinLog.getFclId());
            saveMessage(request, getText("fiCoinLog.deleted", locale));
        } else {
            fiCoinLogManager.save(fiCoinLog);
            String key = (isNew) ? "fiCoinLog.added" : "fiCoinLog.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:fiCoinLogform?fclId=" + fiCoinLog.getFclId();
            }
        }

        return success;
    }
}
