package com.joymain.ng.service;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebService
public interface PayOtherMemberOrderManager{
	/**
	 * @Description 获取他人订单信息
	 * @author houxyu
	 * @date 2016-4-13
	 * @param request
	 * @param response
	 */
	public void payOtherOrderForm(HttpServletRequest request, HttpServletResponse response) throws Exception ;
	
	/**
	 * @Description 支付他人订单提交
	 * @author houxyu
	 * @date 2016-4-14
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public void payOtherOrderSubmit(HttpServletRequest request, HttpServletResponse response) throws Exception;
}