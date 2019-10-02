package com.joymain.ng.webapp.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;



public class BaseController {
	   Logger log = LoggerFactory.getLogger(this.getClass());
	   
	   @org.springframework.web.bind.annotation.InitBinder
	    public void InitBinder(HttpServletRequest request, ServletRequestDataBinder binder) {
	        // 不要删除下行注释!!!   将来"yyyy-MM-dd"将配置到properties文件中
	        //SimpleDateFormat dateFormat = new SimpleDateFormat(getText("date.format", request.getLocale()));
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        dateFormat.setLenient(false);
	        binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
	    }
}
