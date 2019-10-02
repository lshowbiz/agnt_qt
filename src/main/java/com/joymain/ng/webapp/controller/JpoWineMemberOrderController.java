/*
 * 文件名：  JpoWineMemberOrder.java 2013-11-25
 * 版权：    Copyright 2000-2013 Huawei Tech. Co. Ltd. All Rights Reserved.
 * 描述：    (Initialize)
 * 作者：    Administrator
 * 时间：    2013-11-25
 * 版本号：  RBT_CNCMSXV5.0D605SP33
 * 评审人:    
 * 评审时间:
 */
package com.joymain.ng.webapp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

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

import com.joymain.ng.model.JpmConfigDetailed;
import com.joymain.ng.model.JpmConfigSpecDetailed;
import com.joymain.ng.model.JpmMemberConfig;
import com.joymain.ng.model.JpmProductWineTemplate;
import com.joymain.ng.model.JpmProductWineTemplateSub;
import com.joymain.ng.model.JpmSendConsignment;
import com.joymain.ng.model.JpmWineTemplatePicture;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JpmConfigDetailedManager;
import com.joymain.ng.service.JpmConfigSpecDetailedManager;
import com.joymain.ng.service.JpmMemberConfigManager;
import com.joymain.ng.service.JpmProductWineTemplateManager;
import com.joymain.ng.service.JpmProductWineTemplateSubManager;
import com.joymain.ng.service.JpmSendConsignmentManager;
import com.joymain.ng.service.JpmWineTemplatePictureManager;
import com.joymain.ng.service.JpoMemberOrderListManager;
import com.joymain.ng.service.JpoWineMemberOrderManager;
import com.joymain.ng.util.ListUtil;

/**
 * <p>
 * Title: (Initialize)
 * </p>
 * <p>
 * Description: (Initialize)
 * </p>
 * 
 * @author jfoy
 * @version [RBT_CNCMSXV5.0D605SP33, 2013-11-25]
 * @since SP33
 */
@Controller
@RequestMapping("/jpoWineMemberOrders/")
public class JpoWineMemberOrderController
{
    @Autowired
    private JpoWineMemberOrderManager jpoWineMemberOrderManager;
    
    @Autowired
    private JpmMemberConfigManager jpmMemberConfigManager;
    
    @Autowired
    private JpmProductWineTemplateManager jpmProductWineTemplateManager;
    
    @Autowired
    private JpmConfigDetailedManager jpmConfigDetailedManager;
    
    @Autowired
    private JpoMemberOrderListManager jpoMemberOrderListManager;
    
    @Autowired
    private JpmProductWineTemplateSubManager jpmProductWineTemplateSubManager;
    
    @Autowired
    private JpmWineTemplatePictureManager jpmWineTemplatePictureManager;
    
    @Autowired
    private JpmConfigSpecDetailedManager jpmConfigSpecDetailedManager;
    
    @Autowired
    private JpmSendConsignmentManager jpmSendConsignmentManager;
    
    /** 保存全新订单 */
    private static final String SAVE_CONFIG = "0";
    
    /** 保存订单下新规格 */
    private static final String SAVE_SPEC_CONFIG = "1";
    
    /** 保存规格下详细信息 */
    private static final String SAVE_DETAILED = "2";
    
    /** 配置单状态为已提交*/
    private static final String CONFIG_STATUS_SUBMIT = "1 ";
    
    /**
     * 查询酒业订单列表
     * 
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "orderAll", method = RequestMethod.GET)
    public String scAll(Model model, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        JsysUser jsysUser =
            (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String memberOrderNo = request.getParameter("memberOrderNo");// 订单编号
        String status = request.getParameter("status");// 配置状态
        String orderType = request.getParameter("orderType");// 订单类型
        String isShipments = request.getParameter("isShipments");// 订单发货状态
        Locale locale = request.getLocale();
        Map<String, String> map = new HashMap<String, String>();
        map.put("memberOrderNo", memberOrderNo);
        map.put("status", status);
        map.put("orderType", orderType);
        map.put("isShipments", isShipments);
        map.put("userCode", jsysUser.getUserCode());
        map.put("companyCode", jsysUser.getCompanyCode());
        request.setAttribute("model", new HashMap<String, String>(map));
        List<JpoMemberOrder> orderList = jpoWineMemberOrderManager.getWineOrderByParam(map);
        
        if (CollectionUtils.isNotEmpty(orderList))
        {
            // 查询订单下符合酒业信息的商品信息
            for (JpoMemberOrder jpoMemberOrder : orderList)
            {
                Map<String, String> searchMap = new HashMap<String, String>();
                searchMap.put("moId", String.valueOf(jpoMemberOrder.getMoId()));
                searchMap.put("userCode", jsysUser.getUserCode());
                searchMap.put("companyCode", jsysUser.getCompanyCode());
                List<JpoMemberOrderList> memberOrderList = jpoWineMemberOrderManager.getWineProductOrderByParam(searchMap);
                Set<JpoMemberOrderList> list = new HashSet<JpoMemberOrderList>(0);
                list.addAll(memberOrderList);
                jpoMemberOrder.setJpoMemberOrderList(list);
            }
        }
        
        request.setAttribute("orderList", orderList);
        return "jpoWineMemberOrders";
    }
    
    /**
     * 酒业商品配置
     * 
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "orderConfigView", method = RequestMethod.GET)
    public String scConfig(Model model, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        JsysUser jsysUser =
            (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String memberOrderNo = request.getParameter("moId");// 订单编号
        Map<String, String> map = new HashMap<String, String>();
        map.put("moId", memberOrderNo);
        map.put("userCode", jsysUser.getUserCode());
        map.put("companyCode", jsysUser.getCompanyCode());
        request.setAttribute("model", new HashMap<String, String>(map));
        List<JpoMemberOrderList> orderList =
            jpoWineMemberOrderManager.getWineProductOrderByParam(map);
        request.setAttribute("orderList", orderList);
        return "wineMemberConfigOrders";
    }
    
    /**
     * 酒业商品配置列表
     * 
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "orderConfigList", method = RequestMethod.GET)
    public String scConfigList(Model model, HttpServletRequest request, HttpServletResponse response)
        throws Exception
    {
        JsysUser jsysUser =
            (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String memberOrderNo = request.getParameter("molId");// 订单编号
        String productNo = request.getParameter("productNo");
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("molId", Long.parseLong(memberOrderNo));
        
        List<JpmMemberConfig> configList = jpoWineMemberOrderManager.getWineConfigByMolId(map);
        Map<String, String> returnMap = new HashMap<String, String>();
        returnMap.put("productNo", productNo);
        returnMap.put("molId", memberOrderNo);
        // 查出所配置商品的信息
        JpoMemberOrderList jpoMemberOrderList = jpoMemberOrderListManager.getJpoMemberOrderListByMolId(map);
        
        // 查询该订单该商品明细下已配置商品数量
        List<JpmMemberConfig> jpmMemberConfigList = jpmMemberConfigManager.getWineConfigByMolId(map);
        Integer wasConfigNum = 0;
        Integer wasConfigWeight = 0;
        for (JpmMemberConfig jpmMemberConfig : jpmMemberConfigList)
        {
            wasConfigNum += jpmMemberConfig.getAmount().intValue();
            //单个订单配置总重量
            Integer configWasWeight = jpmConfigSpecDetailedManager.getJpmConfigSpecDetailedWeightByConfigNo(jpmMemberConfig.getConfigNo())
            .intValue();
            
            //单个订单配置未配置重量
            jpmMemberConfig.setOddWeight(jpmMemberConfig.getWeight().intValue() - configWasWeight);
            wasConfigWeight += configWasWeight;
            
            //判断订单下所有规格是否已经配置发货完毕，根据发货数量
            if(CONFIG_STATUS_SUBMIT.equals(jpmMemberConfig.getStatus()))
            {
                boolean bl = true;
                List<JpmConfigSpecDetailed> detailedList = jpmConfigSpecDetailedManager.getJpmConfigSpecDetailedListByConfigNo(jpmMemberConfig.getConfigNo());
                for(JpmConfigSpecDetailed jpmConfigSpecDetailed : detailedList)
                {
                    List<JpmSendConsignment> mentList = jpmSendConsignmentManager.getJpmSendConsignmentListBySpecNo(jpmConfigSpecDetailed.getSpecNo());
                    Long num = 0l;
                    for(JpmSendConsignment jpmSendConsignment : mentList)
                    {
                        num += jpmSendConsignment.getConsignmenNum();
                    }
                    if(num.intValue() != jpmConfigSpecDetailed.getProductNum().intValue())
                    {
                        bl = false;
                        continue;
                    }
                }
                jpmMemberConfig.setPayment(bl);
            }
        }
        // 商品已配置数量
        returnMap.put("wasConfigNum", wasConfigNum.toString());
        // 商品可配置总数（未配置数量）:商品总数-已配置数量
        Integer configNum = jpoMemberOrderList.getQty() - wasConfigNum;
        returnMap.put("configNum", String.valueOf(configNum));
        // 商品总数
        returnMap.put("qty", String.valueOf(jpoMemberOrderList.getQty()));
        // 商品总重量：商品总数量*商品重量
        Integer allWeightCount =
            jpoMemberOrderList.getQty() * jpoMemberOrderList.getWeight().intValue();
        returnMap.put("allWeightCount", allWeightCount.toString());
        // 商品已配置重量：商品总重量-商品未配置重量
        // Integer wasWeightCount = allWeightCount - weightCount;
        returnMap.put("wasWeightCount", String.valueOf(wasConfigWeight));
        // 商品可配置重量 ：可配总数*商品重量
        // Integer weightCount = configNum *
        // jpoMemberOrderList.getWeight().intValue();
        Integer weightCount = allWeightCount - wasConfigWeight;
        returnMap.put("weightCount", weightCount.toString());
        // 商品名称
        returnMap.put("productName", jpoMemberOrderList.getProductName());
        returnMap.put("productId", String.valueOf(jpoMemberOrderList.getJpmProductSaleTeamType()
            .getPttId()));
        returnMap.put("moId", String.valueOf(jpoMemberOrderList.getJpoMemberOrder().getMoId()));
        //封装配件详细列表信息
        for(JpmMemberConfig jpmMemberConfig : configList)
        {
            for(JpmConfigSpecDetailed jpmConfigSpecDetailed : jpmMemberConfig.getJpmConfigSpecDetailed())
            {
                List<JpmConfigDetailed> list = new ArrayList<JpmConfigDetailed>();
                list = jpmConfigDetailedManager.getJpmConfigDetailedBySpecNo(jpmConfigSpecDetailed.getSpecNo().toString());
                jpmConfigSpecDetailed.setJpmConfigDetailedList(list);
            }
        }
        request.setAttribute("model", new HashMap<String, String>(returnMap));
        request.setAttribute("configList", configList);
        return "wineMemberConfigList";
    }
    
    /**
     * 增加商品配置页面
     * 
     * @param model
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "addConfigPage", method = RequestMethod.GET)
    public String addConfigPage(Model model, HttpServletRequest request,
        HttpServletResponse response) throws Exception
    {
        JsysUser jsysUser =
            (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        String molId = request.getParameter("molId");// 订单编号
        String productNo = request.getParameter("productNo");
        String specNo = request.getParameter("specNo");
        String saveType = request.getParameter("saveType");
        String moId = request.getParameter("moId");
        String productId = request.getParameter("productId");
        String configNo = request.getParameter("configNo");
        String productName = request.getParameter("productName");
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("molId", Long.parseLong(molId));
        // 根据商品id查询商品编码
        Map<String, String> returnMap = new HashMap<String, String>();
        returnMap.put("productNo", productNo);
        returnMap.put("molId", molId);
        returnMap.put("specNo", specNo);
        
        returnMap.put("moId", moId);
        returnMap.put("productId", productId);
        returnMap.put("configNo", configNo);
        returnMap.put("productName", productName);
        
        // 跳转到配置详细页面，需要查询出可选模版信息
        List<JpmProductWineTemplate> templateList =
            jpmProductWineTemplateManager.getTemplateListByProductNo(productNo);
        
        // 查出所配置商品的信息
        JpoMemberOrderList jpoMemberOrderList =
            jpoMemberOrderListManager.getJpoMemberOrderListByMolId(map);
        
        // 查询该订单该商品明细下已配置商品数量
        List<JpmMemberConfig> jpmMemberConfigList =
            jpmMemberConfigManager.getNoStatusWineConfigByMolId(map);
        Integer wasConfigNum = 0;
        for (JpmMemberConfig jpmMemberConfig : jpmMemberConfigList)
        {
            wasConfigNum += jpmMemberConfig.getAmount().intValue();
        }
        
        // 商品可配置总数:商品总数-已配置数量
        Integer configNum = jpoMemberOrderList.getQty() - wasConfigNum;
        returnMap.put("configNum", configNum.toString());
        // 商品配置重量 ：可配总数*商品重量
        Integer weightCount = configNum * jpoMemberOrderList.getWeight().intValue();
        returnMap.put("weightCount", weightCount.toString());
        
        // 如果是新增配置信息，则可配置重量和可配置数量和商品剩余相同
        if (SAVE_CONFIG.equals(saveType))
        {
            returnMap.put("chooseConfigNum", configNum.toString());
            returnMap.put("chooseWeightCount", weightCount.toString());
        }
        else if (SAVE_SPEC_CONFIG.equals(saveType))
        {
            JpmMemberConfig jpmMemberConfig =
                jpmMemberConfigManager.getJpmMemberConfigByConfigNo(Long.parseLong(configNo));
            // 已配重量根据订单配置下所有规格配置重量之和
            Long complateWeight =
                jpmConfigSpecDetailedManager.getJpmConfigSpecDetailedWeightByConfigNo(Long.parseLong(configNo));
            // 可配重量：配置单总重量-已配重量
            Long chooseWeightCount = jpmMemberConfig.getWeight().longValue() - complateWeight;
            returnMap.put("chooseConfigNum", String.valueOf(jpmMemberConfig.getAmount()));
            returnMap.put("chooseWeightCount", String.valueOf(chooseWeightCount));
            returnMap.put("curWeight", "0");
        }
        
        // 如果是修改配置
        if (StringUtils.isNotEmpty(specNo))
        {
            JpmMemberConfig jpmMemberConfig =
                jpmMemberConfigManager.getJpmMemberConfigByConfigNo(Long.parseLong(configNo));
            // 已配重量根据订单配置下所有规格配置重量之和
            Long complateWeight =
                jpmConfigSpecDetailedManager.getJpmConfigSpecDetailedWeightByConfigNo(Long.parseLong(configNo));
            // 可配重量：配置单总重量-已配重量
            Long chooseWeightCount = jpmMemberConfig.getWeight().longValue() - complateWeight;
            returnMap.put("chooseConfigNum", String.valueOf(jpmMemberConfig.getAmount()));
            returnMap.put("chooseWeightCount", String.valueOf(chooseWeightCount));
            
            // 查询对应的配件列表
            List<JpmConfigDetailed> jpmConfigDetailedList =
                jpmConfigDetailedManager.getJpmConfigDetailedBySpecNo(specNo);
            JpmConfigSpecDetailed jpmConfigSpecDetailed = new JpmConfigSpecDetailed();
            jpmConfigSpecDetailed =
                jpmConfigSpecDetailedManager.getJpmConfigSpecDetailedBySpecNo(Long.parseLong(specNo));
            returnMap.put("curWeight", String.valueOf(jpmConfigSpecDetailed.getComplateWeight()));
            JpmProductWineTemplate temp = new JpmProductWineTemplate();
            for (JpmProductWineTemplate template : templateList)
            {
                if (jpmConfigSpecDetailed.getProductTemplateNo()
                    .equals(template.getProductTemplateNo().toString()))
                {
                    temp = template;
                    request.setAttribute("template", template);
                }
            }
            
            Map<String, String> subMap = new HashMap<String, String>();
            if (null != temp.getProductTemplateNo())
            {
                subMap.put("productTemplateNo", temp.getProductTemplateNo().toString());
                List<JpmProductWineTemplateSub> subList =
                    jpmProductWineTemplateSubManager.getJpmProductWineTemplateSubListByProductTemplateNo(subMap);
                for (int i = 0; i < jpmConfigDetailedList.size(); i++)
                {
                    // 可选配置过滤
                    if ("0".equals(jpmConfigDetailedList.get(i).getIsMustSelected()))
                    {
                        // 默认都增加ischeck为已选择
                        jpmConfigDetailedList.get(i).setIsCheck("1");
                        for (int j = 0; j < subList.size(); j++)
                        {
                            if (jpmConfigDetailedList.get(i).getSubNo().equals(subList.get(j)
                                .getSubNo())
                                || "1".equals(subList.get(j).getIsMustSelected()))
                            {
                                subList.remove(j);
                                j -= 1;
                            }
                        }
                    }
                    if(0l != jpmConfigDetailedList.get(i).getSubAmount())
                    {
                        jpmConfigDetailedList.get(i).setUnitPrice(jpmConfigDetailedList.get(i).getPrice() / jpmConfigDetailedList.get(i).getSubAmount());
                    }
                    else
                    {
                        jpmConfigDetailedList.get(i).setUnitPrice(0l);
                    }
                }
                for (JpmProductWineTemplateSub sub : subList)
                {
                    JpmConfigDetailed jpmConfigDetailed = new JpmConfigDetailed();
                    jpmConfigDetailed.setSubNo(sub.getSubNo());
                    jpmConfigDetailed.setSubName(sub.getSubName());
                    // 配置数量*（配件数量/配件父数量）
                    Long count = complateWeight * (sub.getNum() / temp.getParentQty());
                    jpmConfigDetailed.setSubAmount(count);
                    jpmConfigDetailed.setPrice(count * sub.getPrice());
                    jpmConfigDetailed.setUnitPrice(sub.getPrice());
                    jpmConfigDetailed.setIsMustSelected("0");
                    jpmConfigDetailed.setIsCheck("0");
                    jpmConfigDetailed.setIsMainMaterial(sub.getIsMainMaterial());
                    if ("0".equals(sub.getIsMustSelected()))
                    {
                        jpmConfigDetailedList.add(jpmConfigDetailed);
                    }
                }
            }
            //图片地址前缀
            String FILE_URL = ListUtil.getListValue(jsysUser.getCompanyCode().toUpperCase(), "jpmproductsaleimage.url", "1")+"wine/";

            // 查询配件对应的图片集合
            for (JpmConfigDetailed jpmConfigDetailed : jpmConfigDetailedList)
            {
                List<JpmWineTemplatePicture> jpmWineTemplatePictureList =
                    jpmWineTemplatePictureManager.getJpmWineTemplatePictureListBySubNo(jpmConfigDetailed.getSubNo());
                jpmConfigDetailed.setJpmWineTemplatePictureList(jpmWineTemplatePictureList);
                // 把存在的配件的图片名称和图片地址加到配件信息中
                for (JpmWineTemplatePicture pic : jpmWineTemplatePictureList)
                {
                    if (pic.getIdf().equals(jpmConfigDetailed.getIdf()))
                    {
                        jpmConfigDetailed.setPicName(pic.getPictureName());
                        jpmConfigDetailed.setPicUrl(FILE_URL+pic.getPictureNameDist());
                        continue;
                    }
                }
            }
            
            request.setAttribute("jpmConfigDetailedList", jpmConfigDetailedList);
        }
        
        returnMap.put("saveType", saveType);
        request.setAttribute("model", new HashMap<String, String>(returnMap));
        request.setAttribute("templateList", templateList);
        return "wineConfigDetailed";
    }
    
    /**
     * 查询模版子项信息
     * 
     * @return
     */
    @RequestMapping(value = "/configDetailed", method = RequestMethod.GET)
    public String getConfigDetailed(Model model, HttpServletRequest request, HttpServletResponse response)
    {
        String productTemplateNo = request.getParameter("productTemplateNo");
        String molId = request.getParameter("molId");
        String productNo = request.getParameter("productNo");
        String specNo = request.getParameter("specNo");
        String configNum = request.getParameter("configNum");
        String weightCount = request.getParameter("weightCount");
        String chooseConfigNum = request.getParameter("chooseConfigNum");
        String chooseWeightCount = request.getParameter("chooseWeightCount");
        String saveType = request.getParameter("saveType");
        String moId = request.getParameter("moId");
        String productId = request.getParameter("productId");
        String configNo = request.getParameter("configNo");
        String productName = request.getParameter("productName");
        String curWeight = request.getParameter("curWeight");
        Map<String, String> map = new HashMap<String, String>();
        map.put("molId", molId);
        map.put("productTemplateNo", productTemplateNo);
        map.put("productNo", productNo);
        map.put("specNo", specNo);
        map.put("configNum", configNum);
        map.put("weightCount", weightCount);
        map.put("chooseConfigNum", chooseConfigNum);
        map.put("chooseWeightCount", chooseWeightCount);
        map.put("saveType", saveType);
        map.put("moId", moId);
        map.put("productId", productId);
        map.put("configNo", configNo);
        map.put("productName", productName);
        map.put("curWeight", curWeight);
        // 根据模版id查询对应模版下的子项明细
        List<JpmProductWineTemplateSub> subList =
            jpmProductWineTemplateSubManager.getJpmProductWineTemplateSubListByProductTemplateNo(map);
        
        // 查询模版集合
        List<JpmProductWineTemplate> templateList =
            jpmProductWineTemplateManager.getTemplateListByProductNo(productNo);
        
        Long parentQty = 0l;
        for (JpmProductWineTemplate template : templateList)
        {
            if (productTemplateNo.equals(template.getProductTemplateNo().toString()))
            {
                parentQty = template.getParentQty();
                request.setAttribute("template", template);
            }
        }
        List<JpmConfigDetailed> jpmConfigDetailedList = new ArrayList<JpmConfigDetailed>();
        JsysUser jsysUser =
            (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //图片地址前缀
        String FILE_URL = ListUtil.getListValue(jsysUser.getCompanyCode().toUpperCase(), "jpmproductsaleimage.url", "1")+"wine/";
        // 列表计算
        for (JpmProductWineTemplateSub sub : subList)
        {
            // 配置数量*（配件数量/配件父数量）
            Long count = Long.parseLong(curWeight) * (sub.getNum() / parentQty);
            JpmConfigDetailed jpmConfigDetailed = new JpmConfigDetailed();
            jpmConfigDetailed.setIsMustSelected(sub.getIsMustSelected());
            jpmConfigDetailed.setIsMainMaterial(sub.getIsMainMaterial());
            jpmConfigDetailed.setSubNo(sub.getSubNo());
            jpmConfigDetailed.setSubName(sub.getSubName());
            jpmConfigDetailed.setSubAmount(count);
            // 价格
            Long price = count * sub.getPrice();
            //单价
            jpmConfigDetailed.setUnitPrice(sub.getPrice());
            jpmConfigDetailed.setPrice(price);
            List<JpmWineTemplatePicture> picList = new ArrayList<JpmWineTemplatePicture>();
            for(JpmWineTemplatePicture pic : sub.getJpmWineTemplatePicture())
            {
                pic.setPicturePath(FILE_URL+pic.getPictureNameDist());
            }
            picList.addAll(sub.getJpmWineTemplatePicture());
            jpmConfigDetailed.setJpmWineTemplatePictureList(picList);
            jpmConfigDetailedList.add(jpmConfigDetailed);
        }
        request.setAttribute("model", new HashMap<String, String>(map));
        request.setAttribute("jpmConfigDetailedList", jpmConfigDetailedList);
        request.setAttribute("templateList", templateList);
        return "wineConfigDetailed";
    }
    
    /**
     * 删除配置规格
     * 
     * @return
     */
    @RequestMapping(value = "/delConfig", method = RequestMethod.GET)
    public String delConfig(Model model, HttpServletRequest request, HttpServletResponse response)
    {
        String specNo = request.getParameter("specNo");
        String configNo = request.getParameter("configNo");
        Map<String, String> map = new HashMap<String, String>();
        map.put("specNo", specNo);
        map.put("configNo", configNo);
        // if(StringUtils.isNotEmpty(configNo))
        // {
        List<JpmConfigSpecDetailed> list =
            jpmConfigSpecDetailedManager.getJpmConfigSpecDetailedListByConfigNo(Long.parseLong(configNo));
        // for(JpmConfigSpecDetailed jpmConfigSpecDetailed : list)
        // {
        // jpmConfigSpecDetailedManager.delJpmConfigSpecDetailedBySpecNo(jpmConfigSpecDetailed.getSpecNo());
        // }
        jpmConfigSpecDetailedManager.delJpmConfigSpecDetailedBySpecNo(Long.parseLong(specNo));
        if (list.size() == 1)
        {
            jpmMemberConfigManager.delJpmMemberConfig(Long.parseLong(configNo));
        }
        
        // }
        // else
        // {
        // //删除规格信息
        // jpmConfigSpecDetailedManager.delJpmConfigSpecDetailedBySpecNo(Long.parseLong(specNo));
        // }
        String molId = request.getParameter("molId");
        String productNo = request.getParameter("productNo");
        map.put("molId", molId);
        map.put("productNo", productNo);
        request.setAttribute("model", new HashMap<String, String>(map));
        return "redirect:/jpoWineMemberOrders/orderConfigList?molId=" + molId + "&productNo="
            + productNo;
    }
    
    /**
     * 提交配置规格
     * 
     * @return
     */
    @RequestMapping(value = "/submitConfig", method = RequestMethod.GET)
    public String submitConfig(Model model, HttpServletRequest request, HttpServletResponse response)
    {
        String specNo = request.getParameter("specNo");
        String configNo = request.getParameter("configNo");
        Map<String, String> map = new HashMap<String, String>();
        map.put("specNo", specNo);
        map.put("configNo", configNo);
        Long price = jpmConfigSpecDetailedManager.getPriceByConfigNo(configNo);
        //修改配置状态为"1"（已提交）
        jpmMemberConfigManager.modifyJpmMemberConfigStatusByConfigNo(configNo, CONFIG_STATUS_SUBMIT,price);

        String molId = request.getParameter("molId");
        String productNo = request.getParameter("productNo");
        map.put("molId", molId);
        map.put("productNo", productNo);
        request.setAttribute("model", new HashMap<String, String>(map));
        return "redirect:/jpoWineMemberOrders/orderConfigList?molId=" + molId + "&productNo=" + productNo;
    }
    
    /**
     * 支付测试
     * 
     * @return
     */
    @RequestMapping(value = "/pay", method = RequestMethod.GET)
    public String pay(Model model, HttpServletRequest request, HttpServletResponse response)
    {
        String specNo = request.getParameter("specNo");
        String configNo = request.getParameter("configNo");
        Map<String, String> map = new HashMap<String, String>();
        map.put("specNo", specNo);
        map.put("configNo", configNo);
        //修改配置状态为"1"（已提交）
        JsysUser jsysUser =
            (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        jpmMemberConfigManager.checkJpmMemberConfig(configNo, jsysUser);

        String molId = request.getParameter("molId");
        String productNo = request.getParameter("productNo");
        map.put("molId", molId);
        map.put("productNo", productNo);
        request.setAttribute("model", new HashMap<String, String>(map));
        return "redirect:/jpoWineMemberOrders/orderConfigList?molId=" + molId + "&productNo=" + productNo;
    }
}
