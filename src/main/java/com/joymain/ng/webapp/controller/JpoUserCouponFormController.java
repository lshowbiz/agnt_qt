package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JpoUserCouponManager;
import com.joymain.ng.model.JpoUserCoupon;
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
@RequestMapping("/jpoUserCouponform*")
public class JpoUserCouponFormController extends BaseFormController {
    private JpoUserCouponManager jpoUserCouponManager = null;

    @Autowired
    public void setJpoUserCouponManager(JpoUserCouponManager jpoUserCouponManager) {
        this.jpoUserCouponManager = jpoUserCouponManager;
    }

    public JpoUserCouponFormController() {
        setCancelView("redirect:jpoUserCoupons");
        setSuccessView("redirect:jpoUserCoupons");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JpoUserCoupon showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return jpoUserCouponManager.get(new Long(id));
        }

        return new JpoUserCoupon();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JpoUserCoupon jpoUserCoupon, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jpoUserCoupon, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jpoUserCouponform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jpoUserCoupon.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jpoUserCouponManager.remove(jpoUserCoupon.getId());
            saveMessage(request, getText("jpoUserCoupon.deleted", locale));
        } else {
            jpoUserCouponManager.save(jpoUserCoupon);
            String key = (isNew) ? "jpoUserCoupon.added" : "jpoUserCoupon.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jpoUserCouponform?id=" + jpoUserCoupon.getId();
            }
        }

        return success;
    }
}
