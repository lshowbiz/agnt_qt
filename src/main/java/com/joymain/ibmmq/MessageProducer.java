package com.joymain.ibmmq;

import javax.jms.Queue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;

/**
 * 用于发送jms消息。
 * <br/>
 * 发送邮件，短信，内部消息。
 * 
 * @author pkq
 *
 */
public class MessageProducer
{
    private static final Log logger=LogFactory.getLog(MessageProducer.class);   
    private Queue messageQueue;
    
    private JmsTemplate jmsTemplate;
    
    public void setMessageQueue(Queue messageQueue)
    {
        this.messageQueue = messageQueue;
    }


    public void setJmsTemplate(JmsTemplate jmsTemplate)
    {
        this.jmsTemplate = jmsTemplate;
    }

    public void send(Object model)
    {
        logger.debug("procduce the message");
        //产生邮件\短信\消息发送的消息，加到消息队列中去
                
        jmsTemplate.convertAndSend(messageQueue, model);
    }
}
