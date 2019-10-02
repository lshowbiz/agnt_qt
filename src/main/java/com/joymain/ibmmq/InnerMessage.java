package com.joymain.ibmmq;

import java.io.Serializable;

/**
 * 内部消息对象。
 * @author lmm
 *
 */
public class InnerMessage implements Serializable
{
    /**
     * 标题
     */
    private String messageName;
    
    /**
     * 内容
     */
    private String messageContent;
    
    public String getMessageName()
    {
        return messageName;
    }
    
    public void setMessageName(String messageName)
    {
        this.messageName = messageName;
    }
    
    public String getMessageContent()
    {
        return messageContent;
    }
    
    public void setMessageContent(String messageContent)
    {
        this.messageContent = messageContent;
    }

    @Override
    public String toString()
    {
        return "InnerMessage [messageName=" + messageName + ", messageContent="
                + messageContent + "]";
    }
    
}
