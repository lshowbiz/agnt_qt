package com.joymain.ng.webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/foundationContact*")
public class FoundationContactController {

    @RequestMapping(method = RequestMethod.GET)
    public String handleRequest() throws Exception {
        
    	return "contact";
    }
    
}
