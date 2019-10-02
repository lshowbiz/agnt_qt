package com.joymain.ng.util.chanjet;

import java.io.UnsupportedEncodingException;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

import com.joymain.ng.model.JfiChanjetLog;

@WebService
public interface ChanjetUtil {

	/**
	 * 生成ChanjetUtil实例
	 * @param ChanjetUtil
	 * @param flag 0代表充值， 1表示订单支付，2代表公益基金订单支付
	 * @return
	 */
	public Chanjet getChanjet(Chanjet chanjet, int flag) throws UnsupportedEncodingException;
	
	/**
	 * 付款通知验签
	 * @param request
	 * @return
	 */
	public JfiChanjetLog getJfiChanjetLog(HttpServletRequest request);
}