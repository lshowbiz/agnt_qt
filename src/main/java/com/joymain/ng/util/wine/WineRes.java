/**
 * 项目名：jecs-base
 * 包名：com.joymain.jecs.util.wine
 * 文件名：WineRes.java
 * 版本信息：@version 1.0
 * 日期：2013-12-3-上午10:08:58
 * Copyright 2013 sbChina Tech. Co. Ltd. All Rights Reserved.  
 */
package com.joymain.ng.util.wine;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 酒业响应的报文
 * 
 * @author soul
 * @email soul.lau0328@gmail.com
 * @qq 278834379
 * @since 2013-12-3 上午10:08:58
 * 
 */
@XmlRootElement(name = "ufinterface")
@XmlAccessorType(XmlAccessType.FIELD)
public class WineRes implements Serializable {

    private static final long serialVersionUID = -8982900436498412238L;

    @XmlAttribute
    private String billtype;

    @XmlAttribute
    private String filename;

    @XmlAttribute
    private String isexchange;

    @XmlAttribute
    private String proc;

    @XmlAttribute
    private String receiver;

    @XmlAttribute
    private String replace;

    @XmlAttribute
    private String roottag;

    @XmlAttribute
    private String sender;

    @XmlAttribute
    private String successful;

    @XmlElement
    private Sendresult sendresult;

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getIsexchange() {
        return isexchange;
    }

    public void setIsexchange(String isexchange) {
        this.isexchange = isexchange;
    }

    public String getProc() {
        return proc;
    }

    public void setProc(String proc) {
        this.proc = proc;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReplace() {
        return replace;
    }

    public void setReplace(String replace) {
        this.replace = replace;
    }

    public String getRoottag() {
        return roottag;
    }

    public void setRoottag(String roottag) {
        this.roottag = roottag;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSuccessful() {
        return successful;
    }

    public void setSuccessful(String successful) {
        this.successful = successful;
    }

    public Sendresult getSendresult() {
        return sendresult;
    }

    public void setSendresult(Sendresult sendresult) {
        this.sendresult = sendresult;
    }

    /*   public static void main(String[] args) {
           WineRes res = new WineRes();
           res.setBilltype("");
           res.setFilename("");
           res.setIsexchange("");
           res.setProc("");
           res.setReceiver("");
           res.setReplace("");
           res.setRoottag("");
           res.setSender("");
           res.setSuccessful("");
           Sendresult sendresult = new Sendresult();
           sendresult.setBdocid("");
           sendresult.setBillpk("");
           sendresult.setContent("");
           sendresult.setFilename("");
           sendresult.setResultcode("");
           sendresult.setResultdescription("");
           res.setSendresult(sendresult);
           String xml = new XmlBeanTransferUtil<WineRes>(WineRes.class).bean2Xml(res);
           System.out.println(xml);
       }*/
}

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class Sendresult implements Serializable {
    private static final long serialVersionUID = -1245475810936284492L;

    @XmlElement
    private String billpk;

    @XmlElement
    private String bdocid;

    @XmlElement
    private String filename;

    @XmlElement
    private String resultcode;

    @XmlElement
    private String resultdescription;

    @XmlElement
    private String content;

    public String getBillpk() {
        return billpk;
    }

    public void setBillpk(String billpk) {
        this.billpk = billpk;
    }

    public String getBdocid() {
        return bdocid;
    }

    public void setBdocid(String bdocid) {
        this.bdocid = bdocid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getResultdescription() {
        return resultdescription;
    }

    public void setResultdescription(String resultdescription) {
        this.resultdescription = resultdescription;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
