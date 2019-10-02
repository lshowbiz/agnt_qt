package com.joymain.ng.webapp.controller;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JalLibraryColumnManager;
import com.joymain.ng.service.JalLibraryFileManager;
import com.joymain.ng.util.ConfigUtil;
import com.joymain.ng.model.JalLibraryColumn;
import com.joymain.ng.model.JsysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
/**
 * 资料中心资料列表
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/jalLibraryFiles*")
public class JalLibraryFileController {
    private JalLibraryFileManager jalLibraryFileManager;
    private JalLibraryColumnManager jalLibraryColumnManager;

    @Autowired
    public void setJalLibraryFileManager(JalLibraryFileManager jalLibraryFileManager) {
        this.jalLibraryFileManager = jalLibraryFileManager;
    }
    @Autowired
    public void setJalLibraryColumnManager(JalLibraryColumnManager jalLibraryColumnManager) {
        this.jalLibraryColumnManager = jalLibraryColumnManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Model handleRequest(@RequestParam(value="columnId", required=false) String columnId)
    throws Exception {
        Model model = new ExtendedModelMap();
        try {

            model.addAttribute(jalLibraryFileManager.selectLibraryFilesByColumnId(columnId));

            JalLibraryColumn jalLibraryColumn= jalLibraryColumnManager.get(new Long(columnId));
            model.addAttribute("jalLibraryColumn",jalLibraryColumn);
            
            //获取下载地址
            JsysUser jsysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		String upload_ip = ConfigUtil.getConfigValue(jsysUser.getCompanyCode().toUpperCase(), "upload.ip");
    		String httpUrl=upload_ip+"/jecs_files";
    		model.addAttribute("httpUrl",httpUrl);

        } catch (SearchException se) {
            model.addAttribute("searchError", se.getMessage());
            model.addAttribute(jalLibraryFileManager.getAll());
        }
        return model;
    }
    
}
