package com.joymain.ibmmq;

import java.util.HashMap;
import java.util.Map;

/**
 * 从消息队列中读取对象，并且进行消息发送。
 * 
 * @author lmm
 * 
 */
public class MessageConsumer {

	/**
	 * 处理消息
	 */
	private Map<String, IJmsHandler> handlers = new HashMap<String, IJmsHandler>();

	public void setHandlers(Map<String, IJmsHandler> handlers) {
		this.handlers = handlers;
	}

	/**
	 * 接收到消息，转至相应的处理类进行处理
	 * 
	 * @param model
	 *            发送的对象
	 * @throws Exception
	 */
	public void handMessage(Object model) throws Exception {
	    System.out.println("在handMessage中线程ID是"+Thread.currentThread());
		if (!handlers.isEmpty() && null!=model) {
			handlers.get(model.getClass().getName()).handMessage(model);
		} else {
			throw new Exception("Object:[" + model + "] is not  entity Object ");
		}
	}
}
