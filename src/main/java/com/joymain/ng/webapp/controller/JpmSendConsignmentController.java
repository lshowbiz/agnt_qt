package com.joymain.ng.webapp.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joymain.ng.model.JmiAddrBook;
import com.joymain.ng.model.JpmConfigSpecDetailed;
import com.joymain.ng.model.JpmSendConsignment;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.service.JmiAddrBookManager;
import com.joymain.ng.service.JpmConfigSpecDetailedManager;
import com.joymain.ng.service.JpmSendConsignmentManager;

@Controller
@RequestMapping("/jpmSendConsignments/")
public class JpmSendConsignmentController {
    private JpmSendConsignmentManager jpmSendConsignmentManager;
    @Autowired
    private JalStateProvinceManager jalStateProvinceManager;
    @Autowired
    private JpmConfigSpecDetailedManager jpmConfigSpecDetailedManager;
    
    @Autowired
    private JmiAddrBookManager jmiAddrBookManager;

    @Autowired
    public void setJpmSendConsignmentManager(JpmSendConsignmentManager jpmSendConsignmentManager) {
        this.jpmSendConsignmentManager = jpmSendConsignmentManager;
    }

    /**
     * 查询规格下的发货单信息
     * 
     * @return
     */
    @RequestMapping(value = "/sendConsignmentPage", method = RequestMethod.GET)
    public String getConsignmentList(Model model, HttpServletRequest request, HttpServletResponse response)
    {
        JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String specNo = request.getParameter("specNo");
        String productNo = request.getParameter("productNo");
        String molId = request.getParameter("molId");
        Map<String, String> map = new HashMap<String, String>();
        List<JpmSendConsignment> list = null;
        if(StringUtils.isNotEmpty(specNo))
        {
            list = jpmSendConsignmentManager.getJpmSendConsignmentListBySpecNo(Long.parseLong(specNo));
        }
        //已配送瓶数
        Long wasNum = 0l;
        if(CollectionUtils.isNotEmpty(list))
        {
            for(JpmSendConsignment jpmSendConsignment : list)
            {
                wasNum += jpmSendConsignment.getConsignmenNum();
                JmiAddrBook jmiAddrBook = jmiAddrBookManager.get(jpmSendConsignment.getFabId());
                jpmSendConsignment.setAddress(jpmSendConsignmentManager.getAddresByFabId(jmiAddrBook));
            }
        }
        
        //查询此规格下剩余发货瓶数
        JpmConfigSpecDetailed jpmConfigSpecDetailed = jpmConfigSpecDetailedManager.getJpmConfigSpecDetailedBySpecNo(Long.parseLong(specNo));
        //可配送瓶数
        Long consignmenNum = 0l;
        if(null != jpmConfigSpecDetailed.getProductNum())
        {
            consignmenNum = jpmConfigSpecDetailed.getProductNum() - wasNum;
        }
        
        //查询该用户下所有收货地址信息
        List jmiAddrBooks=jmiAddrBookManager.getJmiAddrBookByUserCode(defSysUser.getUserCode());
        request.setAttribute("jmiAddrBooks", jmiAddrBooks);
        String consignmentNo = request.getParameter("consignmentNo");
        if(StringUtils.isNotEmpty(consignmentNo))
        {
            JpmSendConsignment jpmSendConsignment = jpmSendConsignmentManager.get(Long.parseLong(consignmentNo));
            request.setAttribute("jpmSendConsignment", jpmSendConsignment);
        }
        map.put("specNo", specNo);
        map.put("consignmenNum", consignmenNum.toString());
        map.put("productNo", productNo);
        map.put("molId", molId);
        request.setAttribute("model", new HashMap<String, String>(map));
        List alStateProvinces=jalStateProvinceManager.getJalStateProvinceByCountryCode(defSysUser.getCompanyCode());
        request.setAttribute("alStateProvinces", alStateProvinces);
        request.setAttribute("jpmSendConsignmentList", list);
        return "jpmSendConsignments";
    }    
    
    /**
     * 添加规格发货单页面
     * 
     * @return
     */
    @RequestMapping(value = "/addConsignment", method = RequestMethod.GET)
    public String addConsignment(Model model, HttpServletRequest request, HttpServletResponse response)
    {
        JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String specNo = request.getParameter("specNo");
        String productNo = request.getParameter("productNo");
        String molId = request.getParameter("molId");
        String consignmenNum = request.getParameter("consignmenNum");
        String fabId = request.getParameter("fabId");
        JpmSendConsignment jpmSendConsignment = new JpmSendConsignment();
        jpmSendConsignment.setConsignmenNum(Long.parseLong(consignmenNum));
        jpmSendConsignment.setSpecNo(Long.parseLong(specNo));
        jpmSendConsignment.setFabId(Long.parseLong(fabId));
        jpmSendConsignment.setSendDate(new Date());
        jpmSendConsignment.setSendUserCode(defSysUser.getUserCode());
        jpmSendConsignment.setStatus("0");
        String consignmentNo = request.getParameter("consignmentNo");
        if(StringUtils.isNotEmpty(consignmentNo))
        {
            jpmSendConsignment.setConsignmentNo(Long.parseLong(consignmentNo));
        }
        jpmSendConsignmentManager.save(jpmSendConsignment);
        
        return "redirect:/jpmSendConsignments/sendConsignmentPage?specNo="+specNo+"&productNo="+productNo+"&molId="+molId;
    }    
    
    /**
     * 删除规格发货单
     * 
     * @return
     */
    @RequestMapping(value = "/delConsignment", method = RequestMethod.GET)
    public String delConsignment(Model model, HttpServletRequest request, HttpServletResponse response)
    {
        String specNo = request.getParameter("specNo");
        String productNo = request.getParameter("productNo");
        String molId = request.getParameter("molId");
        String consignmentNo = request.getParameter("consignmentNo");
        jpmSendConsignmentManager.delJpmSendConsignmentByConsignmentNo(Long.parseLong(consignmentNo));
        Map<String, String> map = new HashMap<String, String>();
        map.put("specNo", specNo);
        request.setAttribute("model", new HashMap<String, String>(map));
        return "redirect:/jpmSendConsignments/sendConsignmentPage?specNo="+specNo+"&productNo="+productNo+"&molId="+molId;
    }    
    
}
