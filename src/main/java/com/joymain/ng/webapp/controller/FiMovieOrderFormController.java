package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.FiMovieOrderManager;
import com.joymain.ng.model.FiMovieOrder;
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
@RequestMapping("/fiMovieOrderform*")
public class FiMovieOrderFormController extends BaseFormController {
    private FiMovieOrderManager fiMovieOrderManager = null;

    @Autowired
    public void setFiMovieOrderManager(FiMovieOrderManager fiMovieOrderManager) {
        this.fiMovieOrderManager = fiMovieOrderManager;
    }

    public FiMovieOrderFormController() {
        setCancelView("redirect:fiMovieOrders");
        setSuccessView("redirect:fiMovieOrders");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected FiMovieOrder showForm(HttpServletRequest request)
    throws Exception {
        String orderId = request.getParameter("orderId");

        if (!StringUtils.isBlank(orderId)) {
            return fiMovieOrderManager.get(new String(orderId));
        }

        return new FiMovieOrder();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(FiMovieOrder fiMovieOrder, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(fiMovieOrder, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "fiMovieOrderform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (fiMovieOrder.getOrderId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            fiMovieOrderManager.remove(fiMovieOrder.getOrderId());
            saveMessage(request, getText("fiMovieOrder.deleted", locale));
        } else {
            fiMovieOrderManager.save(fiMovieOrder);
            String key = (isNew) ? "fiMovieOrder.added" : "fiMovieOrder.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:fiMovieOrderform?orderId=" + fiMovieOrder.getOrderId();
            }
        }

        return success;
    }
}
