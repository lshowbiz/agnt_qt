package com.joymain.ng.service;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.joymain.ng.model.JbdCalcDayFb;
import com.joymain.ng.util.GroupPage;
@WebService
public interface JbdCalcDayFbManager extends GenericManager<JbdCalcDayFb, Long>{
	public List getJbdCalcDayFbsPage(Map<String, String> params, GroupPage page);
}