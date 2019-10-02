package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.PdLogisticsBaseDetailManager;
import com.joymain.ng.model.PdLogisticsBaseDetail;
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
@RequestMapping("/pdLogisticsBaseDetailform*")
public class PdLogisticsBaseDetailFormController extends BaseFormController {
    private PdLogisticsBaseDetailManager pdLogisticsBaseDetailManager = null;

    @Autowired
    public void setPdLogisticsBaseDetailManager(PdLogisticsBaseDetailManager pdLogisticsBaseDetailManager) {
        this.pdLogisticsBaseDetailManager = pdLogisticsBaseDetailManager;
    }

    public PdLogisticsBaseDetailFormController() {
        setCancelView("redirect:pdLogisticsBaseDetails");
        setSuccessView("redirect:pdLogisticsBaseDetails");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected PdLogisticsBaseDetail showForm(HttpServletRequest request)
    throws Exception {
        String detailId = request.getParameter("detailId");

        if (!StringUtils.isBlank(detailId)) {
            return pdLogisticsBaseDetailManager.get(new Long(detailId));
        }

        return new PdLogisticsBaseDetail();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(PdLogisticsBaseDetail pdLogisticsBaseDetail, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(pdLogisticsBaseDetail, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "pdLogisticsBaseDetailform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (pdLogisticsBaseDetail.getDetailId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            pdLogisticsBaseDetailManager.remove(pdLogisticsBaseDetail.getDetailId());
            saveMessage(request, getText("pdLogisticsBaseDetail.deleted", locale));
        } else {
            pdLogisticsBaseDetailManager.save(pdLogisticsBaseDetail);
            String key = (isNew) ? "pdLogisticsBaseDetail.added" : "pdLogisticsBaseDetail.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:pdLogisticsBaseDetailform?detailId=" + pdLogisticsBaseDetail.getDetailId();
            }
        }

        return success;
    }
}
