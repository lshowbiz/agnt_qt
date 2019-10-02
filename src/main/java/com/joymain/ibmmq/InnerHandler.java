package com.joymain.ibmmq;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 发送内部消息的实现类
 * @author lmm
 *
 */
public class InnerHandler implements IJmsHandler {

	private final Log logger = LogFactory.getLog(InnerHandler.class);

	/**
	 * 接收到消息 在此处理
	 * @param model
	 */
	@Override
	public void handMessage(Object model) {
		InnerMessage innerModel = (InnerMessage) model;
		//接收到消息，在此处理相应逻辑，如发送邮件、短信等
		System.out.println("InnerMessage: "+innerModel);
	}

}
