package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JpmCardSeqManager;
import com.joymain.ng.model.JpmCardSeq;
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
@RequestMapping("/jpmCardSeqform*")
public class JpmCardSeqFormController extends BaseFormController {
    private JpmCardSeqManager jpmCardSeqManager = null;

    @Autowired
    public void setJpmCardSeqManager(JpmCardSeqManager jpmCardSeqManager) {
        this.jpmCardSeqManager = jpmCardSeqManager;
    }

    public JpmCardSeqFormController() {
        setCancelView("redirect:jpmCardSeqs");
        setSuccessView("redirect:jpmCardSeqs");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JpmCardSeq showForm(HttpServletRequest request)
    throws Exception {
        String id = request.getParameter("id");

        if (!StringUtils.isBlank(id)) {
            return jpmCardSeqManager.get(new Long(id));
        }

        return new JpmCardSeq();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JpmCardSeq jpmCardSeq, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jpmCardSeq, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jpmCardSeqform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jpmCardSeq.getId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jpmCardSeqManager.remove(jpmCardSeq.getId());
            saveMessage(request, getText("jpmCardSeq.deleted", locale));
        } else {
            jpmCardSeqManager.save(jpmCardSeq);
            String key = (isNew) ? "jpmCardSeq.added" : "jpmCardSeq.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jpmCardSeqform?id=" + jpmCardSeq.getId();
            }
        }

        return success;
    }
}
