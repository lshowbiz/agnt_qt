package com.joymain.ng.webapp.controller;

import java.util.List;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JalLibraryColumnManager;
import com.joymain.ng.service.JalLibraryPlateManager;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.util.ListUtil;
import com.joymain.ng.model.JalLibraryColumn;
import com.joymain.ng.model.JsysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 资料中心栏目查询
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/jalLibraryColumns*")
public class JalLibraryColumnController {
    private JalLibraryColumnManager jalLibraryColumnManager;
    private JalLibraryPlateManager jalLibraryPlateManager;

    @Autowired
    public void setJalLibraryPlateManager(JalLibraryPlateManager jalLibraryPlateManager) {
        this.jalLibraryPlateManager = jalLibraryPlateManager;
    }
    @Autowired
    public void setJalLibraryColumnManager(JalLibraryColumnManager jalLibraryColumnManager) {
        this.jalLibraryColumnManager = jalLibraryColumnManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(required = false, value = "q") String query)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {
        	
        	List jalLibraryColumnList1=jalLibraryColumnManager.getJalLibraryColumnListByPlateIndex("1");
            model.addAttribute("jalLibraryColumnList1",jalLibraryColumnList1);
            
            List jalLibraryColumnList2=jalLibraryColumnManager.getJalLibraryColumnListByPlateIndex("2");
            model.addAttribute("jalLibraryColumnList2",jalLibraryColumnList2);
            
            List jalLibraryColumnList3=jalLibraryColumnManager.getJalLibraryColumnListByPlateIndex("3");
            model.addAttribute("jalLibraryColumnList3",jalLibraryColumnList3);
            
            List jalLibraryColumnList4=jalLibraryColumnManager.getJalLibraryColumnListByPlateIndex("4");
            model.addAttribute("jalLibraryColumnList4",jalLibraryColumnList4);
             
            // 查询板块
    		List jalLibraryPlates = jalLibraryPlateManager.getJalLibraryPlatesOrderByIndex();
    		model.addAttribute("jalLibraryPlates", jalLibraryPlates);
    		
    		// 图片读取路径
    		//当前用户
	       	JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	       	// 图片读取路径
    		//String fileUrl = ConfigUtil.getConfigValue(jsysUser.getCompanyCode().toUpperCase(), "column.image.url")+"column/";
    		String fileUrl = ListUtil.getListValue(jsysUser.getCompanyCode().toUpperCase(), "jpmproductsaleimage.url", "1")+"column/";

    		
    		model.addAttribute("fileUrl", fileUrl);    		
        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jalLibraryColumnManager.getAll());
        }
        return model;
    }
    
}
