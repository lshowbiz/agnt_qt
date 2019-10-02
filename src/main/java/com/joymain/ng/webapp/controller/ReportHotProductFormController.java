package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.ReportHotProductManager;
import com.joymain.ng.model.ReportHotProduct;
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
@RequestMapping("/reportHotProductform*")
public class ReportHotProductFormController extends BaseFormController {
    private ReportHotProductManager reportHotProductManager = null;

    @Autowired
    public void setReportHotProductManager(ReportHotProductManager reportHotProductManager) {
        this.reportHotProductManager = reportHotProductManager;
    }

    public ReportHotProductFormController() {
        setCancelView("redirect:reportHotProducts");
        setSuccessView("redirect:reportHotProducts");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected ReportHotProduct showForm(HttpServletRequest request)
    throws Exception {
        String reportId = request.getParameter("reportId");

        if (!StringUtils.isBlank(reportId)) {
            return reportHotProductManager.get(new Long(reportId));
        }

        return new ReportHotProduct();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(ReportHotProduct reportHotProduct, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(reportHotProduct, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "reportHotProductform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (reportHotProduct.getReportId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            reportHotProductManager.remove(reportHotProduct.getReportId());
            saveMessage(request, getText("reportHotProduct.deleted", locale));
        } else {
            reportHotProductManager.save(reportHotProduct);
            String key = (isNew) ? "reportHotProduct.added" : "reportHotProduct.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:reportHotProductform?reportId=" + reportHotProduct.getReportId();
            }
        }

        return success;
    }
}
