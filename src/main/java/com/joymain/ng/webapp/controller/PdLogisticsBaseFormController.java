package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.PdLogisticsBaseManager;
import com.joymain.ng.model.PdLogisticsBase;
import com.joymain.ng.webapp.controller.BaseFormController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping("/pdLogisticsBaseform*")
public class PdLogisticsBaseFormController extends BaseFormController {
    private PdLogisticsBaseManager pdLogisticsBaseManager = null;

    @Autowired
    public void setPdLogisticsBaseManager(PdLogisticsBaseManager pdLogisticsBaseManager) {
        this.pdLogisticsBaseManager = pdLogisticsBaseManager;
    }

    public PdLogisticsBaseFormController() {
        setCancelView("redirect:pdLogisticsBases");
        setSuccessView("redirect:pdLogisticsBases");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected PdLogisticsBase showForm(HttpServletRequest request)
    throws Exception {
        String baseId = request.getParameter("baseId");

        if (!StringUtils.isBlank(baseId)) {
            return pdLogisticsBaseManager.get(new Long(baseId));
        }

        return new PdLogisticsBase();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(PdLogisticsBase pdLogisticsBase, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(pdLogisticsBase, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "pdLogisticsBaseform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (pdLogisticsBase.getBaseId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            pdLogisticsBaseManager.remove(pdLogisticsBase.getBaseId());
            saveMessage(request, getText("pdLogisticsBase.deleted", locale));
        } else {
            pdLogisticsBaseManager.save(pdLogisticsBase);
            String key = (isNew) ? "pdLogisticsBase.added" : "pdLogisticsBase.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:pdLogisticsBaseform?id=" + pdLogisticsBase.getBaseId();
            }
        }
        return success;
    }
}
