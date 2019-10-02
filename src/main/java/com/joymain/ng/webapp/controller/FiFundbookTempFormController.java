package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.FiFundbookTempManager;
import com.joymain.ng.model.FiFundbookTemp;
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
@RequestMapping("/fiFundbookTempform*")
public class FiFundbookTempFormController extends BaseFormController {
    private FiFundbookTempManager fiFundbookTempManager = null;

    @Autowired
    public void setFiFundbookTempManager(FiFundbookTempManager fiFundbookTempManager) {
        this.fiFundbookTempManager = fiFundbookTempManager;
    }

    public FiFundbookTempFormController() {
        setCancelView("redirect:fiFundbookTemps");
        setSuccessView("redirect:fiFundbookTemps");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FiFundbookTemp showForm(HttpServletRequest request)
    throws Exception {
        String tempId = request.getParameter("tempId");

        if (!StringUtils.isBlank(tempId)) {
            return fiFundbookTempManager.get(new Long(tempId));
        }

        return new FiFundbookTemp();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FiFundbookTemp fiFundbookTemp, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(fiFundbookTemp, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "fiFundbookTempform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (fiFundbookTemp.getTempId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            fiFundbookTempManager.remove(fiFundbookTemp.getTempId());
            saveMessage(request, getText("fiFundbookTemp.deleted", locale));
        } else {
            fiFundbookTempManager.save(fiFundbookTemp);
            String key = (isNew) ? "fiFundbookTemp.added" : "fiFundbookTemp.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:fiFundbookTempform?tempId=" + fiFundbookTemp.getTempId();
            }
        }

        return success;
    }
}
