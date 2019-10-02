package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;
import com.joymain.ng.service.JpmWineTemplatePictureManager;
import com.joymain.ng.model.JpmWineTemplatePicture;
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
@RequestMapping("/jpmWineTemplatePictureform*")
public class JpmWineTemplatePictureFormController extends BaseFormController {
    private JpmWineTemplatePictureManager jpmWineTemplatePictureManager = null;

    @Autowired
    public void setJpmWineTemplatePictureManager(JpmWineTemplatePictureManager jpmWineTemplatePictureManager) {
        this.jpmWineTemplatePictureManager = jpmWineTemplatePictureManager;
    }

    public JpmWineTemplatePictureFormController() {
        setCancelView("redirect:jpmWineTemplatePictures");
        setSuccessView("redirect:jpmWineTemplatePictures");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JpmWineTemplatePicture showForm(HttpServletRequest request)
    throws Exception {
        String idf = request.getParameter("idf");

        if (!StringUtils.isBlank(idf)) {
            return jpmWineTemplatePictureManager.get(new Long(idf));
        }

        return new JpmWineTemplatePicture();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JpmWineTemplatePicture jpmWineTemplatePicture, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jpmWineTemplatePicture, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jpmWineTemplatePictureform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jpmWineTemplatePicture.getIdf() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jpmWineTemplatePictureManager.remove(jpmWineTemplatePicture.getIdf());
            saveMessage(request, getText("jpmWineTemplatePicture.deleted", locale));
        } else {
            jpmWineTemplatePictureManager.save(jpmWineTemplatePicture);
            String key = (isNew) ? "jpmWineTemplatePicture.added" : "jpmWineTemplatePicture.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jpmWineTemplatePictureform?idf=" + jpmWineTemplatePicture.getIdf();
            }
        }

        return success;
    }
}
