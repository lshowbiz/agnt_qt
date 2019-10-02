package com.joymain.ng.service;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebService
public interface MemberOrderApiManager{
	/**
	 * @Description 获取他人订单信息
	 * @author houxyu
	 * @date 2016-4-19
	 * @param request
	 * @param response
	 */
	public void OrderStatistics(HttpServletRequest request, HttpServletResponse response) throws Exception ;
}