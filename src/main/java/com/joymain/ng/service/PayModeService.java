package com.joymain.ng.service;

import com.joymain.ng.model.JfiPayOrderCompany;
import com.joymain.ng.model.JfiPayRetMsg;
import com.joymain.ng.util.PropertiesUtil;

/**
 * 支付公司逻辑处理公共接口
 * 
 * @author jfoy
 * 
 */
public interface PayModeService {
	//
	public static final String  BASE  = PropertiesUtil.getPropertieByPayJump();
	// 服务端通知地址
	public static final String NOTIFY_URL = BASE + "payReceive";
	// 支付完成后跳转页面
	public static final String PAGE_URL = BASE + "jpoMemberOrders/orderAll";

	/**
	 * 支付公司类型获取转换方法
	 * 
	 * @param jfiPayOrderCompany
	 * @return
	 */
	public JfiPayRetMsg PayCompanyHandle(JfiPayOrderCompany jfiPayOrderCompany);
}
