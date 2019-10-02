package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.PublicScheduleManager;
import com.joymain.ng.model.PublicSchedule;
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
@RequestMapping("/publicScheduleform*")
public class PublicScheduleFormController extends BaseFormController {
    private PublicScheduleManager publicScheduleManager = null;

    @Autowired
    public void setPublicScheduleManager(PublicScheduleManager publicScheduleManager) {
        this.publicScheduleManager = publicScheduleManager;
    }

    public PublicScheduleFormController() {
        setCancelView("redirect:publicSchedules");
        setSuccessView("redirect:publicSchedules");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected PublicSchedule showForm(HttpServletRequest request)
    throws Exception {
        String psId = request.getParameter("psId");
        if (!StringUtils.isBlank(psId)) {
        	PublicSchedule publicSchedule = publicScheduleManager.get(new Long(psId));
        	if(publicSchedule==null){
        		publicSchedule = new PublicSchedule();
        	}
        	return publicSchedule;
        }

        return new PublicSchedule();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(PublicSchedule publicSchedule, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(publicSchedule, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "publicScheduleform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (publicSchedule.getPsId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            publicScheduleManager.remove(publicSchedule.getPsId());
            saveMessage(request, getText("publicSchedule.deleted", locale));
        } else {
            publicScheduleManager.save(publicSchedule);
            String key = (isNew) ? "publicSchedule.added" : "publicSchedule.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:publicScheduleform?psId=" + publicSchedule.getPsId();
            }
        }

        return success;
    }
}
