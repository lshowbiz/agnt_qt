package com.joymain.ibmmq;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 用于发送或接收map对象的实现类
 * @author lmm
 *
 */
public class MapHandler implements IJmsHandler {

	private final Log logger = LogFactory.getLog(MapHandler.class);

	/**
	 * 接收到消息 在此处理
	 * @param model
	 */
	@Override
	public void handMessage(Object model) {
	    //接收到消息，在此处理相应逻辑，如发送邮件、短信等
		Map map = (Map) model;
		System.out.println("MapMessage: "+map);
	}

}
