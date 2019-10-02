package com.joymain.ng.webapp.controller;

import java.util.List;

import com.joymain.ng.dao.SearchException;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.model.JalStateProvince;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JalStateProvinceController {
    private JalStateProvinceManager jalStateProvinceManager;

    @Autowired
    public void setJalStateProvinceManager(JalStateProvinceManager jalStateProvinceManager) {
        this.jalStateProvinceManager = jalStateProvinceManager;
    }

    @RequestMapping(value="/regions.json",method = RequestMethod.GET)
    @ResponseBody
    public List getJsonRegion(){
    	
    	List regions=jalStateProvinceManager.getJalStateProvinceByCountryCode("CN");
    	return regions;
    }
}
