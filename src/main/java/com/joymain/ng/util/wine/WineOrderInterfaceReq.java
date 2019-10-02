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

import com.joymain.ng.util.XmlBeanTransferUtil;

/**
 * 
 * 订单的请求报文
 * 
 * @author soul
 * @email soul.lau0328@gmail.com
 * @qq 278834379
 * @since 2013-12-3 上午9:36:13
 * 
 */
@XmlRootElement(name = "ufinterface")
@XmlAccessorType(XmlAccessType.FIELD)
public class WineOrderInterfaceReq implements java.io.Serializable {

    private static final long serialVersionUID = -4627584493526913267L;

    @XmlAttribute
    private String account = "design";

    @XmlAttribute
    private String billtype = "expsalecust";

    @XmlAttribute
    private String filename = "";

    @XmlAttribute
    private String isexchange = "Y";

    @XmlAttribute
    private String proc = "add";

    @XmlAttribute
    private String receiver = "";

    @XmlAttribute
    private String replace = "Y";

    @XmlAttribute
    private String roottag = "";

    @XmlAttribute
    private String sender = "5001";

    @XmlAttribute
    private String subbilltype = "";

    @XmlElement(name = "bill")
    private So_order so_order;

    public So_order getSo_order() {
        return so_order;
    }

    public void setSo_order(So_order so_order) {
        this.so_order = so_order;
    }

    public String getSubbilltype() {
        return subbilltype;
    }

    public void setSubbilltype(String subbilltype) {
        this.subbilltype = subbilltype;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRoottag() {
        return roottag;
    }

    public void setRoottag(String roottag) {
        this.roottag = roottag;
    }

    public String getReplace() {
        return replace;
    }

    public void setReplace(String replace) {
        this.replace = replace;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getProc() {
        return proc;
    }

    public void setProc(String proc) {
        this.proc = proc;
    }

    public String getIsexchange() {
        return isexchange;
    }

    public void setIsexchange(String isexchange) {
        this.isexchange = isexchange;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getBilltype() {
        return billtype;
    }

    public void setBilltype(String billtype) {
        this.billtype = billtype;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    /*public static void main(String[] args) {
        WineOrderInterfaceReq req = new WineOrderInterfaceReq();

        So_order so_order = new So_order();
        So_order_head so_order_head = new So_order_head();
        so_order.setOrder_id("110");
        so_order.setSo_order_head(so_order_head);
        List<SaleOrderBodyVOEntry> saleOrderBodyVOEntryList = new ArrayList<SaleOrderBodyVOEntry>();

        SaleOrderBodyVOEntry saleOrderBodyVOEntry = new SaleOrderBodyVOEntry();
        saleOrderBodyVOEntryList.add(saleOrderBodyVOEntry);
        saleOrderBodyVOEntryList.add(saleOrderBodyVOEntry);

        so_order.setSaleOrderBodyVOList(saleOrderBodyVOEntryList);

        CustDocBodyVO custDocBodyVO = new CustDocBodyVO();
        CustDocBodyVOEntry custDocBodyVOEntry = new CustDocBodyVOEntry();
        custDocBodyVO.setEntry(custDocBodyVOEntry);
        so_order.setCustDocBodyVO(custDocBodyVO);

        req.setSo_order(so_order);
        String xml = new XmlBeanTransferUtil<WineOrderInterfaceReq>(WineOrderInterfaceReq.class).bean2Xml(req);
        System.out.println(xml);
         WineOrderInterfaceReq reqs = new XmlBeanTransferUtil<WineOrderInterfaceReq>(WineOrderInterfaceReq.class).Xml2Bean(xml);
         System.out.println();
    }*/
}

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class So_order implements java.io.Serializable {
    private static final long serialVersionUID = -8678743692135641385L;

    //导入单据流水号
    @XmlAttribute(name = "id")
    private String order_id = "";

    @XmlElement(name = "billhead")
    private So_order_head so_order_head;

    @XmlElementWrapper(name = "SaleOrderBodyVO")
    @XmlElement(name = "entry")
    private List<SaleOrderBodyVOEntry> saleOrderBodyVOList;

    @XmlElement(name = "CustDocBodyVO")
    private CustDocBodyVO custDocBodyVO;

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public So_order_head getSo_order_head() {
        return so_order_head;
    }

    public void setSo_order_head(So_order_head so_order_head) {
        this.so_order_head = so_order_head;
    }

    public List<SaleOrderBodyVOEntry> getSaleOrderBodyVOList() {
        return saleOrderBodyVOList;
    }

    public void setSaleOrderBodyVOList(List<SaleOrderBodyVOEntry> saleOrderBodyVOList) {
        this.saleOrderBodyVOList = saleOrderBodyVOList;
    }

    public CustDocBodyVO getCustDocBodyVO() {
        return custDocBodyVO;
    }

    public void setCustDocBodyVO(CustDocBodyVO custDocBodyVO) {
        this.custDocBodyVO = custDocBodyVO;
    }

}

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class So_order_head implements java.io.Serializable {

    private static final long serialVersionUID = -7138218886974461193L;

    // Fields    
    private String pk_corp = "103";//公司

    private String creceipttype = "30";//订单类型

    private String cbiztype = "so02";//销售类型

    private String dbilldate = "";////发货日期 √

    private String customerCode = "";// 客户编码 会员编号 √

    private String ccustomerid = "";// 会员编号  √

    private String shippingAddress = "";

    private String coperatorid = "chenl";//操作人 √

    private String csalecorpid = "";//// 会员编号  √

    private String ccalbodyid = "103";

    private String creceiptcustomerid = "";

    private String creceiptcorpid = "";

    private String ndiscountrate = "";

    private String cdeptid = "";

    private String dmakedate = "";// 发货日期 √

    private String fstatus = "";

    private String membercode = "";//会员编号   √

    private String membername = "";//会员姓名   √

    private String memberphone = "";//会员电话    √

    private String memberid = "";//会员身份证

    private String cusprovince = "";//会员所在省 √

    private String cuscity = "";//会员所在市 √

    private String areaclcode;//会员所在区 √

    private String memberaddress = "";//会员详细地址  √   区 放到详细地址里面

    private String postalcode = "";//邮编   √   

    private String membersex = "";//性别  √

    private String ordercode = "";//订单号 √   

    private String billcode = "";//发货单号 √   

    public String getPk_corp() {
        return pk_corp;
    }

    public void setPk_corp(String pk_corp) {
        this.pk_corp = pk_corp;
    }

    public String getCreceipttype() {
        return creceipttype;
    }

    public void setCreceipttype(String creceipttype) {
        this.creceipttype = creceipttype;
    }

    public String getCbiztype() {
        return cbiztype;
    }

    public void setCbiztype(String cbiztype) {
        this.cbiztype = cbiztype;
    }

    public String getDbilldate() {
        return dbilldate;
    }

    public void setDbilldate(String dbilldate) {
        this.dbilldate = dbilldate;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCcustomerid() {
        return ccustomerid;
    }

    public void setCcustomerid(String ccustomerid) {
        this.ccustomerid = ccustomerid;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getCoperatorid() {
        return coperatorid;
    }

    public void setCoperatorid(String coperatorid) {
        this.coperatorid = coperatorid;
    }

    public String getCsalecorpid() {
        return csalecorpid;
    }

    public void setCsalecorpid(String csalecorpid) {
        this.csalecorpid = csalecorpid;
    }

    public String getCcalbodyid() {
        return ccalbodyid;
    }

    public void setCcalbodyid(String ccalbodyid) {
        this.ccalbodyid = ccalbodyid;
    }

    public String getCreceiptcustomerid() {
        return creceiptcustomerid;
    }

    public void setCreceiptcustomerid(String creceiptcustomerid) {
        this.creceiptcustomerid = creceiptcustomerid;
    }

    public String getCreceiptcorpid() {
        return creceiptcorpid;
    }

    public void setCreceiptcorpid(String creceiptcorpid) {
        this.creceiptcorpid = creceiptcorpid;
    }

    public String getNdiscountrate() {
        return ndiscountrate;
    }

    public void setNdiscountrate(String ndiscountrate) {
        this.ndiscountrate = ndiscountrate;
    }

    public String getCdeptid() {
        return cdeptid;
    }

    public void setCdeptid(String cdeptid) {
        this.cdeptid = cdeptid;
    }

    public String getDmakedate() {
        return dmakedate;
    }

    public void setDmakedate(String dmakedate) {
        this.dmakedate = dmakedate;
    }

    public String getFstatus() {
        return fstatus;
    }

    public void setFstatus(String fstatus) {
        this.fstatus = fstatus;
    }

    public String getMembercode() {
        return membercode;
    }

    public void setMembercode(String membercode) {
        this.membercode = membercode;
    }

    public String getMembername() {
        return membername;
    }

    public void setMembername(String membername) {
        this.membername = membername;
    }

    public String getMemberphone() {
        return memberphone;
    }

    public void setMemberphone(String memberphone) {
        this.memberphone = memberphone;
    }

    public String getMemberid() {
        return memberid;
    }

    public void setMemberid(String memberid) {
        this.memberid = memberid;
    }

    public String getCusprovince() {
        return cusprovince;
    }

    public void setCusprovince(String cusprovince) {
        this.cusprovince = cusprovince;
    }

    public String getCuscity() {
        return cuscity;
    }

    public void setCuscity(String cuscity) {
        this.cuscity = cuscity;
    }

    public String getAreaclcode() {
        return areaclcode;
    }

    public void setAreaclcode(String areaclcode) {
        this.areaclcode = areaclcode;
    }

    public String getMemberaddress() {
        return memberaddress;
    }

    public void setMemberaddress(String memberaddress) {
        this.memberaddress = memberaddress;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getMembersex() {
        return membersex;
    }

    public void setMembersex(String membersex) {
        this.membersex = membersex;
    }

    public String getOrdercode() {
        return ordercode;
    }

    public void setOrdercode(String ordercode) {
        this.ordercode = ordercode;
    }

    public String getBillcode() {
        return billcode;
    }

    public void setBillcode(String billcode) {
        this.billcode = billcode;
    }

}

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class CustDocBodyVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private CustDocBodyVOEntry entry;

    public CustDocBodyVOEntry getEntry() {
        return entry;
    }

    public void setEntry(CustDocBodyVOEntry entry) {
        this.entry = entry;
    }
}

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class SaleOrderBodyVOEntry implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id = "";//id 行号

    private String pk_corp = "103";//公司   103

    private String bdericttrans = "Y";//是否直运     Y

    private String cinventoryid = "";//商品编码 √

    private String blargessflag = "N";//是否赠品       N

    private String nnumber = "";//发货数量  √

    private String cconsigncorpid = "102";//发货公司       102

    private String cadvisecalbodyid = "102";//建议发货库存组织     102

    private String cbodywarehouseid = "a1";//发货仓库     a1

    private String creccalbodyid = "103";//收货库存组织   103

    private String creceiptcorpid = "";//发货单号   √

    private String vreceiveaddress = "";//收货地址

    private String dconsigndate = "";//发货日期 √

    private String noriginalcurtaxprice = "";//商品单价 √

    private String invpv = "";//PV

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPk_corp() {
        return pk_corp;
    }

    public void setPk_corp(String pk_corp) {
        this.pk_corp = pk_corp;
    }

    public String getBdericttrans() {
        return bdericttrans;
    }

    public void setBdericttrans(String bdericttrans) {
        this.bdericttrans = bdericttrans;
    }

    public String getCinventoryid() {
        return cinventoryid;
    }

    public void setCinventoryid(String cinventoryid) {
        this.cinventoryid = cinventoryid;
    }

    public String getBlargessflag() {
        return blargessflag;
    }

    public void setBlargessflag(String blargessflag) {
        this.blargessflag = blargessflag;
    }

    public String getNnumber() {
        return nnumber;
    }

    public void setNnumber(String nnumber) {
        this.nnumber = nnumber;
    }

    public String getCconsigncorpid() {
        return cconsigncorpid;
    }

    public void setCconsigncorpid(String cconsigncorpid) {
        this.cconsigncorpid = cconsigncorpid;
    }

    public String getCadvisecalbodyid() {
        return cadvisecalbodyid;
    }

    public void setCadvisecalbodyid(String cadvisecalbodyid) {
        this.cadvisecalbodyid = cadvisecalbodyid;
    }

    public String getCbodywarehouseid() {
        return cbodywarehouseid;
    }

    public void setCbodywarehouseid(String cbodywarehouseid) {
        this.cbodywarehouseid = cbodywarehouseid;
    }

    public String getCreccalbodyid() {
        return creccalbodyid;
    }

    public void setCreccalbodyid(String creccalbodyid) {
        this.creccalbodyid = creccalbodyid;
    }

    public String getCreceiptcorpid() {
        return creceiptcorpid;
    }

    public void setCreceiptcorpid(String creceiptcorpid) {
        this.creceiptcorpid = creceiptcorpid;
    }

    public String getVreceiveaddress() {
        return vreceiveaddress;
    }

    public void setVreceiveaddress(String vreceiveaddress) {
        this.vreceiveaddress = vreceiveaddress;
    }

    public String getDconsigndate() {
        return dconsigndate;
    }

    public void setDconsigndate(String dconsigndate) {
        this.dconsigndate = dconsigndate;
    }

    public String getNoriginalcurtaxprice() {
        return noriginalcurtaxprice;
    }

    public void setNoriginalcurtaxprice(String noriginalcurtaxprice) {
        this.noriginalcurtaxprice = noriginalcurtaxprice;
    }

    public String getInvpv() {
        return invpv;
    }

    public void setInvpv(String invpv) {
        this.invpv = invpv;
    }

}

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class CustDocBodyVOEntry implements Serializable {
    private static final long serialVersionUID = 9063056968468335941L;

    private String areaclcode = "";//收货人所在区    √

    private String cuscity = "";//收货人所在市    √

    private String cusprovince = "";//收货人所在省    √

    private String postalcode = "";//邮编 √

    private String receiveraddress = "";//收货人详细地址   √   区 放到详细地址里面

    private String receivercode = "";//收货人编号    √   发货单号

    private String receiverid = "";//收货人身份证   √  

    private String receivername = "";//收货人姓名    √   

    private String receiverphone = "";//收货人电话   √   

    private String receiversex = "";//性别   √  

    public String getAreaclcode() {
        return areaclcode;
    }

    public void setAreaclcode(String areaclcode) {
        this.areaclcode = areaclcode;
    }

    public String getCuscity() {
        return cuscity;
    }

    public void setCuscity(String cuscity) {
        this.cuscity = cuscity;
    }

    public String getCusprovince() {
        return cusprovince;
    }

    public void setCusprovince(String cusprovince) {
        this.cusprovince = cusprovince;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getReceiveraddress() {
        return receiveraddress;
    }

    public void setReceiveraddress(String receiveraddress) {
        this.receiveraddress = receiveraddress;
    }

    public String getReceivercode() {
        return receivercode;
    }

    public void setReceivercode(String receivercode) {
        this.receivercode = receivercode;
    }

    public String getReceiverid() {
        return receiverid;
    }

    public void setReceiverid(String receiverid) {
        this.receiverid = receiverid;
    }

    public String getReceivername() {
        return receivername;
    }

    public void setReceivername(String receivername) {
        this.receivername = receivername;
    }

    public String getReceiverphone() {
        return receiverphone;
    }

    public void setReceiverphone(String receiverphone) {
        this.receiverphone = receiverphone;
    }

    public String getReceiversex() {
        return receiversex;
    }

    public void setReceiversex(String receiversex) {
        this.receiversex = receiversex;
    }

}