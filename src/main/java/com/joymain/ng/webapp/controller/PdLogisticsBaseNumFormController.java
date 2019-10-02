package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.PdLogisticsBaseNumManager;
import com.joymain.ng.model.PdLogisticsBaseNum;
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
@RequestMapping("/pdLogisticsBaseNumform*")
public class PdLogisticsBaseNumFormController extends BaseFormController {
    private PdLogisticsBaseNumManager pdLogisticsBaseNumManager = null;

    @Autowired
    public void setPdLogisticsBaseNumManager(PdLogisticsBaseNumManager pdLogisticsBaseNumManager) {
        this.pdLogisticsBaseNumManager = pdLogisticsBaseNumManager;
    }

    public PdLogisticsBaseNumFormController() {
        setCancelView("redirect:pdLogisticsBaseNums");
        setSuccessView("redirect:pdLogisticsBaseNums");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected PdLogisticsBaseNum showForm(HttpServletRequest request)
    throws Exception {
        String numId = request.getParameter("numId");

        if (!StringUtils.isBlank(numId)) {
            return pdLogisticsBaseNumManager.get(new Long(numId));
        }

        return new PdLogisticsBaseNum();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(PdLogisticsBaseNum pdLogisticsBaseNum, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(pdLogisticsBaseNum, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "pdLogisticsBaseNumform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (pdLogisticsBaseNum.getNumId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            pdLogisticsBaseNumManager.remove(pdLogisticsBaseNum.getNumId());
            saveMessage(request, getText("pdLogisticsBaseNum.deleted", locale));
        } else {
            pdLogisticsBaseNumManager.save(pdLogisticsBaseNum);
            String key = (isNew) ? "pdLogisticsBaseNum.added" : "pdLogisticsBaseNum.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:pdLogisticsBaseNumform?numId=" + pdLogisticsBaseNum.getNumId();
            }
        }

        return success;
    }
}
