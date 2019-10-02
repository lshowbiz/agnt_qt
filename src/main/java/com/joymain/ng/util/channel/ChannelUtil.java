package com.joymain.ng.util.channel;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

@WebService
public interface ChannelUtil {

	/**
	 * 盛付通支付数据准备
	 * @param channel
	 * @param flag
	 * @return
	 */
	public ChannelBean getChannelBean(ChannelBean channel, int flag);
}