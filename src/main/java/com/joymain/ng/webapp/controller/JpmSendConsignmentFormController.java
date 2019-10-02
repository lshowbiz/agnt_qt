package com.joymain.ng.webapp.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joymain.ng.model.JpmSendConsignment;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JpmSendConsignmentManager;

@Controller
@RequestMapping("/jpmSendConsignmentform*")
public class JpmSendConsignmentFormController extends BaseFormController {
    private JpmSendConsignmentManager jpmSendConsignmentManager = null;

    @Autowired
    public void setJpmSendConsignmentManager(JpmSendConsignmentManager jpmSendConsignmentManager) {
        this.jpmSendConsignmentManager = jpmSendConsignmentManager;
    }

    public JpmSendConsignmentFormController() {
        setCancelView("redirect:jpmSendConsignments");
        setSuccessView("redirect:jpmSendConsignments");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected JpmSendConsignment showForm(HttpServletRequest request)
    throws Exception {
        String consignmentNo = request.getParameter("consignmentNo");

        if (!StringUtils.isBlank(consignmentNo)) {
            return jpmSendConsignmentManager.get(new Long(consignmentNo));
        }

        return new JpmSendConsignment();
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JpmSendConsignment jpmSendConsignment, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String specNo = request.getParameter("specNo");
        String specName = request.getParameter("specName");
        JpmSendConsignment consignment = new JpmSendConsignment();
//        consignment.setUserName(request.getParameter("userName"));
//        consignment.setAddress(request.getParameter("address"));
//        consignment.setSendDate(new Date());
//        consignment.setSpecNo(Long.parseLong(request.getParameter("specNo")));
//        consignment.setConsignmenNum(Long.parseLong(request.getParameter("consignmenNum")));
//        consignment.setPhone(request.getParameter("phone"));
//        consignment.setPostalCode(request.getParameter("postalCode"));
//        consignment.setCusCity(request.getParameter("cusCity"));
//        consignment.setCusProvince(request.getParameter("cusProvince"));
//        consignment.setSendUserCode(defSysUser.getUserCode());
        String consignmentNo = request.getParameter("consignmentNo");
        if(StringUtils.isNotEmpty(consignmentNo))
        {
            consignment.setConsignmentNo(Long.parseLong(consignmentNo));
        }
        jpmSendConsignmentManager.save(consignment);
        return "redirect:/jpmSendConsignments/sendConsignmentPage?specNo="+specNo+"&specName="+specName;
    }
}
