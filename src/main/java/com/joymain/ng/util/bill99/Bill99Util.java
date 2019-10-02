package com.joymain.ng.util.bill99;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.model.Jfi99billLog;
@WebService
public interface Bill99Util {

	/**
	 * jfi99billLog.getReturnMsg()
	 * 0表示数据被篡改
	 * 1表示扣款失败
	 * 2自定义MD5签名被篡改(快钱签名被破解)
	 * 3支付数据重新发送
	 * 10表示成功校验
	 * @param request
	 * @param userCode
	 * @param companyCode
	 * @return
	 */
	public Jfi99billLog getJfi99billLog(HttpServletRequest request,String userCode,String companyCode);

	/**
	 * 生成BILL99实例
	 * @param bill99
	 * @param flag 0表示直接存款进存折 1表示订单存款
	 * @return
	 */
	public Bill99 getBill99(Bill99 bill99, int flag);
	
	/**
	 * 给手机支付调用生成BILL99实例
	 * @param bill99
	 * @param flag
	 * @return
	 */
	public Bill99 getBill99ForMobil(Bill99 bill99,int flag);
	
}