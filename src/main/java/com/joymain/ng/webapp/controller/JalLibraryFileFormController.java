package com.joymain.ng.webapp.controller;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.service.JalLibraryFileManager;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.model.JalLibraryFile;
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

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/jalLibraryFileform*")
public class JalLibraryFileFormController extends BaseFormController {
    private JalLibraryFileManager jalLibraryFileManager = null;

    @Autowired
    public void setJalLibraryFileManager(JalLibraryFileManager jalLibraryFileManager) {
        this.jalLibraryFileManager = jalLibraryFileManager;
    }

    public JalLibraryFileFormController() {
        setCancelView("redirect:jalLibraryFiles");
        setSuccessView("redirect:jalLibraryFiles");
    }

    @ModelAttribute
    @RequestMapping(method = RequestMethod.GET)
    protected String handleRequest(HttpServletRequest request)
    throws Exception {
    	
//    	//单个下载
//        String fileId = request.getParameter("fileId");
//        if (!StringUtils.isBlank(fileId)) {
//            
//        	JalLibraryFile jalLibraryFile=jalLibraryFileManager.get(new Long(fileId));
//        	log.debug("====================进入下载");
//        	boolean flag = FtpClientUtil.downFile("74.84.128.97", 2962, "guoyong","guoyong2962", "jecs_files", jalLibraryFile.getFileUrl(), "D:/");
//        	log.debug("====================下载结束");
//        	if(flag){
//        		log.debug("====================下载OK");
//        		//执行结果提示
//        		saveMessage(request, LocaleUtil.getLocalText("down.success"));
//        	}
//        }
//
//        //全部下载
//        String columnId = request.getParameter("columnId");
//        if (!StringUtils.isBlank(columnId)) {
//            //return jalLibraryFileManager.get(new Long(fileId));
//        	
//        	List jalLibraryFileList=jalLibraryFileManager.selectLibraryFilesByColumnId(columnId);
//        	for(int i=0;i<jalLibraryFileList.size();i++){
//	        	
//	        	JalLibraryFile jalLibrary = (JalLibraryFile) jalLibraryFileList.get(i);
//	        	log.debug("====================进入下载");
//	        	boolean flag = FtpClientUtil.downFile("74.84.128.97", 2962, "guoyong","guoyong2962", "jecs_files", jalLibrary.getFileUrl(), "D:/");
//	        	log.debug("====================下载结束");
//	        	if(flag){
//	        		log.debug("====================下载OK");
//	        		//执行结果提示
//	        		saveMessage(request, LocaleUtil.getLocalText("down.success"));
//	        	}
//	        }
//        }
        
        return "jalLibraryFiles";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String onSubmit(JalLibraryFile jalLibraryFile, BindingResult errors, HttpServletRequest request,
                           HttpServletResponse response)
    throws Exception {
        if (request.getParameter("cancel") != null) {
            return getCancelView();
        }

        if (validator != null) { // validator is null during testing
            validator.validate(jalLibraryFile, errors);

            if (errors.hasErrors() && request.getParameter("delete") == null) { // don't validate when deleting
                return "jalLibraryFileform";
            }
        }

        log.debug("entering 'onSubmit' method...");

        boolean isNew = (jalLibraryFile.getFileId() == null);
        String success = getSuccessView();
        Locale locale = request.getLocale();

        if (request.getParameter("delete") != null) {
            jalLibraryFileManager.remove(jalLibraryFile.getFileId());
            saveMessage(request, getText("jalLibraryFile.deleted", locale));
        } else {
            jalLibraryFileManager.save(jalLibraryFile);
            String key = (isNew) ? "jalLibraryFile.added" : "jalLibraryFile.updated";
            saveMessage(request, getText(key, locale));

            if (!isNew) {
                success = "redirect:jalLibraryFileform?fileId=" + jalLibraryFile.getFileId();
            }
        }

        return success;
    }
}
