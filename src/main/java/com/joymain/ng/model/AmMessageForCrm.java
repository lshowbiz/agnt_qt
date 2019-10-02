package com.joymain.ng.model;

public class AmMessageForCrm implements java.io.Serializable
{
    private String message_bn; //留言编号
    private String uid;//会员编号
    private String id;//留言分类
    private String uname;//会员名称
    private String message_time;//留言时间
    private String message_title;//留言标题 新增字段
    private String message_txt;//留言内容
    private String reply_time;//回复时间
    private String reply_txt;//回复内容
    private String reply_op;//回复人
    private String ip;//IP地址
    
    
    public AmMessageForCrm(String message_bn, String uid, String id,
            String uname, String message_time, String message_title,
            String message_txt, String reply_time, String reply_txt,
            String reply_op, String ip)
    {
        super();
        this.message_bn = message_bn;
        this.uid = uid;
        this.id = id;
        this.uname = uname;
        this.message_time = message_time;
        this.message_title = message_title;
        this.message_txt = message_txt;
        this.reply_time = reply_time;
        this.reply_txt = reply_txt;
        this.reply_op = reply_op;
        this.ip = ip;
    }
    
    public String getMessage_bn()
    {
        return message_bn;
    }
    public void setMessage_bn(String message_bn)
    {
        this.message_bn = message_bn;
    }
    public String getUid()
    {
        return uid;
    }
    public void setUid(String uid)
    {
        this.uid = uid;
    }
    public String getId()
    {
        return id;
    }
    public void setId(String id)
    {
        this.id = id;
    }
    public String getUname()
    {
        return uname;
    }
    public void setUname(String uname)
    {
        this.uname = uname;
    }
    public String getMessage_time()
    {
        return message_time;
    }
    public void setMessage_time(String message_time)
    {
        this.message_time = message_time;
    }
    public String getMessage_title()
    {
        return message_title;
    }
    public void setMessage_title(String message_title)
    {
        this.message_title = message_title;
    }
    public String getMessage_txt()
    {
        return message_txt;
    }
    public void setMessage_txt(String message_txt)
    {
        this.message_txt = message_txt;
    }
    public String getReply_time()
    {
        return reply_time;
    }
    public void setReply_time(String reply_time)
    {
        this.reply_time = reply_time;
    }
    public String getReply_txt()
    {
        return reply_txt;
    }
    public void setReply_txt(String reply_txt)
    {
        this.reply_txt = reply_txt;
    }
    public String getReply_op()
    {
        return reply_op;
    }
    public void setReply_op(String reply_op)
    {
        this.reply_op = reply_op;
    }
    public String getIp()
    {
        return ip;
    }
    public void setIp(String ip)
    {
        this.ip = ip;
    }
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
        result = prime * result
                + ((message_bn == null) ? 0 : message_bn.hashCode());
        result = prime * result
                + ((message_time == null) ? 0 : message_time.hashCode());
        result = prime * result
                + ((message_title == null) ? 0 : message_title.hashCode());
        result = prime * result
                + ((message_txt == null) ? 0 : message_txt.hashCode());
        result = prime * result
                + ((reply_op == null) ? 0 : reply_op.hashCode());
        result = prime * result
                + ((reply_time == null) ? 0 : reply_time.hashCode());
        result = prime * result
                + ((reply_txt == null) ? 0 : reply_txt.hashCode());
        result = prime * result + ((uid == null) ? 0 : uid.hashCode());
        result = prime * result + ((uname == null) ? 0 : uname.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AmMessageForCrm other = (AmMessageForCrm) obj;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (ip == null)
        {
            if (other.ip != null)
                return false;
        }
        else if (!ip.equals(other.ip))
            return false;
        if (message_bn == null)
        {
            if (other.message_bn != null)
                return false;
        }
        else if (!message_bn.equals(other.message_bn))
            return false;
        if (message_time == null)
        {
            if (other.message_time != null)
                return false;
        }
        else if (!message_time.equals(other.message_time))
            return false;
        if (message_title == null)
        {
            if (other.message_title != null)
                return false;
        }
        else if (!message_title.equals(other.message_title))
            return false;
        if (message_txt == null)
        {
            if (other.message_txt != null)
                return false;
        }
        else if (!message_txt.equals(other.message_txt))
            return false;
        if (reply_op == null)
        {
            if (other.reply_op != null)
                return false;
        }
        else if (!reply_op.equals(other.reply_op))
            return false;
        if (reply_time == null)
        {
            if (other.reply_time != null)
                return false;
        }
        else if (!reply_time.equals(other.reply_time))
            return false;
        if (reply_txt == null)
        {
            if (other.reply_txt != null)
                return false;
        }
        else if (!reply_txt.equals(other.reply_txt))
            return false;
        if (uid == null)
        {
            if (other.uid != null)
                return false;
        }
        else if (!uid.equals(other.uid))
            return false;
        if (uname == null)
        {
            if (other.uname != null)
                return false;
        }
        else if (!uname.equals(other.uname))
            return false;
        return true;
    }
    @Override
    public String toString()
    {
        return "AmMessageForCrm [message_bn=" + message_bn + ", uid=" + uid
                + ", id=" + id + ", uname=" + uname + ", message_time="
                + message_time + ", message_title=" + message_title
                + ", message_txt=" + message_txt + ", reply_time=" + reply_time
                + ", reply_txt=" + reply_txt + ", reply_op=" + reply_op
                + ", ip=" + ip + "]";
    }
}
