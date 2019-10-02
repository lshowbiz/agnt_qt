package com.joymain.ng.service;

import javax.jws.WebService;

import com.joymain.ng.model.JsysUser;
@WebService
public interface MobilePaymentManager {

	public String payOthersOrder(JsysUser jsysUser,String jsonMsg);// 支付他人订单

	public String payTransfersDetails(JsysUser jsysUser,String jsonMsg);// 转账明细

	public String payTransfers(JsysUser jsysUser,String jsonMsg);// 转账
}
