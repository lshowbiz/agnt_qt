package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JpmCouponInfoManager;
import com.joymain.ng.model.JpmCouponInfo;
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
@RequestMapping("/jpmCouponInfoform*")
public class JpmCouponInfoFormController extends BaseFormController {
    private JpmCouponInfoManager jpmCouponInfoManager = null;

    @Autowired
    public void setJpmCouponInfoManager(JpmCouponInfoManager jpmCouponInfoManager) {
        this.jpmCouponInfoManager = jpmCouponInfoManager;
    }

    public JpmCouponInfoFormController() {
        setCancelView("redirect:jpmCouponInfoes");
        setSuccessView("redirect:jpmCouponInfoes");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JpmCouponInfo showForm(HttpServletRequest request)
    throws Exception {
        String cpId = request.getParameter("cpId");

        if (!StringUtils.isBlank(cpId)) {
            return jpmCouponInfoManager.get(new Long(cpId));
        }

        return new JpmCouponInfo();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JpmCouponInfo jpmCouponInfo, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jpmCouponInfo, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jpmCouponInfoform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jpmCouponInfo.getCpId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jpmCouponInfoManager.remove(jpmCouponInfo.getCpId());
            saveMessage(request, getText("jpmCouponInfo.deleted", locale));
        } else {
            jpmCouponInfoManager.save(jpmCouponInfo);
            String key = (isNew) ? "jpmCouponInfo.added" : "jpmCouponInfo.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jpmCouponInfoform?cpId=" + jpmCouponInfo.getCpId();
            }
        }

        return success;
    }
}
