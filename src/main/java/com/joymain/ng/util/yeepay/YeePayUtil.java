package com.joymain.ng.util.yeepay;

import java.util.Map;

import javax.jws.WebService;
/**
 * 易宝支付
 * @author Administrator
 *
 */
@WebService
public interface YeePayUtil {

    /**
     * 获取商户号
     * 
     * @param userCode
     * @return
     */
    public Map<String, String> getMerId(String userCode);
	public YeePay getYeePay(YeePay yeePay, int flag);
}