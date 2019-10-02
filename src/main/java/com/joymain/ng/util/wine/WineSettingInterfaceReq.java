/**
 * 项目名：jecs-base
 * 包名：com.joymain.jecs.util.wine
 * 文件名：WineSettingInterfaceReq.java
 * 版本信息：@version 1.0
 * 日期：2013-12-3-上午10:28:57
 * Copyright 2013 sbChina Tech. Co. Ltd. All Rights Reserved.  
 */
package com.joymain.ng.util.wine;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 酒业配置单的请求xml
 * 
 * @author soul
 * @email soul.lau0328@gmail.com
 * @qq 278834379
 * @since 2013-12-3 上午10:28:57
 * 
 */
@XmlRootElement(name = "ufinterface")
@XmlAccessorType(XmlAccessType.FIELD)
public class WineSettingInterfaceReq implements Serializable {

    private static final long serialVersionUID = 3344552885697956705L;

    @XmlAttribute
    private String account = "0001";

    @XmlAttribute
    private String billtype = "invbom";

    @XmlAttribute
    private String filename = "filename";

    @XmlAttribute
    private String isexchange = "Y";

    @XmlAttribute
    private String proc = "add";

    @XmlAttribute
    private String receiver = "103";

    @XmlAttribute
    private String replace = "Y";

    @XmlAttribute
    private String roottag = "";

    @XmlAttribute
    private String sender = "5001";

    @XmlAttribute
    private String subbilltype = "";

    @XmlElement
    private Bill bill;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

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

    public String getSubbilltype() {
        return subbilltype;
    }

    public void setSubbilltype(String subbilltype) {
        this.subbilltype = subbilltype;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    /*    public static void main(String[] args) {
            WineSettingInterfaceReq req = new WineSettingInterfaceReq();
            Bill bill = new Bill();
            bill.setBillhead(new Billhead());
            List<Billbody> list = new ArrayList<Billbody>();
            list.add(new Billbody());
            list.add(new Billbody());
            bill.setBillbody(list);
            req.setBill(bill);
            String xml = new XmlBeanTransferUtil<WineSettingInterfaceReq>(WineSettingInterfaceReq.class).bean2Xml(req);
            System.out.println(xml);
        }*/
}

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class Bill implements Serializable {

    private static final long serialVersionUID = 8756688778712433534L;

    @XmlAttribute
    private String id = "";

    @XmlElement
    private Billhead billhead;

    @XmlElementWrapper(name = "billbody")
    @XmlElement(name = "entry")
    private List<Billbody> billbody;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Billhead getBillhead() {
        return billhead;
    }

    public void setBillhead(Billhead billhead) {
        this.billhead = billhead;
    }

    public List<Billbody> getBillbody() {
        return billbody;
    }

    public void setBillbody(List<Billbody> billbody) {
        this.billbody = billbody;
    }

}

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class Billhead implements Serializable {

    private static final long serialVersionUID = 5919916404880058718L;

    @XmlElement
    private String cfgnum = "";//<!-- 商品数量 --><!--cfgnum,不能为空,最大长度为64,类型为:String-->

    @XmlElement
    private String invcode = "";//<!-- 配置单号 --><!--invcode,不能为空,最大长度为64,类型为:String-->

    @XmlElement
    private String invname = "";//<!-- 商品名称 --><!--invname,不能为空,最大长度为64,类型为:String-->

    @XmlElement
    private String pk_corp = "102";//<!--pk_corp,不能为空,最大长度为64,类型为:String-->

    @XmlElement
    private String pk_invcl = "1030";//<!--pk_invcl,不能为空,最大长度为64,类型为:String-->

    @XmlElement
    private String pk_measdoc = "瓶";//<!-- 计量单位 --><!--pk_measdoc,不能为空,最大长度为64,类型为:String-->

    public String getCfgnum() {
        return cfgnum;
    }

    public void setCfgnum(String cfgnum) {
        this.cfgnum = cfgnum;
    }

    public String getInvcode() {
        return invcode;
    }

    public void setInvcode(String invcode) {
        this.invcode = invcode;
    }

    public String getInvname() {
        return invname;
    }

    public void setInvname(String invname) {
        this.invname = invname;
    }

    public String getPk_corp() {
        return pk_corp;
    }

    public void setPk_corp(String pk_corp) {
        this.pk_corp = pk_corp;
    }

    public String getPk_invcl() {
        return pk_invcl;
    }

    public void setPk_invcl(String pk_invcl) {
        this.pk_invcl = pk_invcl;
    }

    public String getPk_measdoc() {
        return pk_measdoc;
    }

    public void setPk_measdoc(String pk_measdoc) {
        this.pk_measdoc = pk_measdoc;
    }
}

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class Billbody implements Serializable {

    private static final long serialVersionUID = 3780785306161769920L;

    @XmlElement
    private String nrowid = "";//<!-- 流水号 -->

    @XmlElement
    private String pk_corp = "102";

    @XmlElement
    private String pk_invbasid = "";//<!-- 材料编号 -->

    @XmlElement
    private String sl = "";//<!-- 数量 -->

    @XmlElement
    private String sdate = "";//<!-- 生效日期-->

    @XmlElement
    private String edate = "";//<!--失效日期-->

    @XmlElement
    private String gcbm = "102";

    @XmlElement
    private String memo = "";//<!-- 备注 -->

    @XmlElement(name = "shxs")
    private String lossRatio = "";//损耗系数

    @XmlElement(name = "sfzl")
    private String isMainMaterial = "";//是否主料 1:是 0：否 默认：0

    @XmlElement(name = "sffl")
    private String isSendMaterial = "";//是否发料 1:是 0：否 默认：0

    @XmlElement(name = "sfwwfl")
    private String isDelegateOut = "";//是否委外 1:是 0：否 默认：0

    @XmlElement(name = "isspecialty")
    private String isFeatureItem = "";//是否特征项 1:是 0：否 默认：0

    @XmlElement(name = "picname")
    private String picName = "";//图片名称（ID） 1:是 0：否 默认：0

    public String getNrowid() {
        return nrowid;
    }

    public void setNrowid(String nrowid) {
        this.nrowid = nrowid;
    }

    public String getPk_corp() {
        return pk_corp;
    }

    public void setPk_corp(String pk_corp) {
        this.pk_corp = pk_corp;
    }

    public String getPk_invbasid() {
        return pk_invbasid;
    }

    public void setPk_invbasid(String pk_invbasid) {
        this.pk_invbasid = pk_invbasid;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getEdate() {
        return edate;
    }

    public void setEdate(String edate) {
        this.edate = edate;
    }

    public String getGcbm() {
        return gcbm;
    }

    public void setGcbm(String gcbm) {
        this.gcbm = gcbm;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getLossRatio() {
        return lossRatio;
    }

    public void setLossRatio(String lossRatio) {
        this.lossRatio = lossRatio;
    }

    public String getIsMainMaterial() {
        return isMainMaterial;
    }

    public void setIsMainMaterial(String isMainMaterial) {
        this.isMainMaterial = isMainMaterial;
    }

    public String getIsSendMaterial() {
        return isSendMaterial;
    }

    public void setIsSendMaterial(String isSendMaterial) {
        this.isSendMaterial = isSendMaterial;
    }

    public String getIsDelegateOut() {
        return isDelegateOut;
    }

    public void setIsDelegateOut(String isDelegateOut) {
        this.isDelegateOut = isDelegateOut;
    }

    public String getIsFeatureItem() {
        return isFeatureItem;
    }

    public void setIsFeatureItem(String isFeatureItem) {
        this.isFeatureItem = isFeatureItem;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }
    
}