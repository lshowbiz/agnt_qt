package com.joymain.ng.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.DocumentId;

@Entity
@Table(name="JPM_SEND_CONSIGNMENT")

@XmlRootElement
public class JpmSendConsignment extends BaseObject implements Serializable {
    private Long consignmentNo;
    private Long fabId;
    private Date sendDate;
    private String status;
    private Long specNo;
    private Long consignmenNum;
    private String sendUserCode;
    private String specName;
    private String address;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_sys")
    @SequenceGenerator(name = "seq_sys", sequenceName = "SEQ_SYS", allocationSize = 1)
    @Id      @Column(name="CONSIGNMENT_NO", unique=true, nullable=false, precision=22, scale=0) @DocumentId    
    public Long getConsignmentNo() {
        return this.consignmentNo;
    }
    
    public void setConsignmentNo(Long consignmentNo) {
        this.consignmentNo = consignmentNo;
    }
    @Transient
    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    @Column(name="FAB_ID", precision=10, scale=0)
    public Long getFabId()
    {
        return fabId;
    }

    public void setFabId(Long fabId)
    {
        this.fabId = fabId;
    }

    @Column(name="SEND_USER_CODE", precision=22, scale=0)
    public String getSendUserCode()
    {
        return sendUserCode;
    }

    public void setSendUserCode(String sendUserCode)
    {
        this.sendUserCode = sendUserCode;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="SEND_DATE", length=7)
    public Date getSendDate() {
        return this.sendDate;
    }
    
    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }
    
    @Column(name="STATUS", length=1)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="SPEC_NO", precision=10, scale=0)
    public Long getSpecNo() {
        return this.specNo;
    }
    
    public void setSpecNo(Long specNo) {
        this.specNo = specNo;
    }
    
    @Column(name="CONSIGNMEN_NUM", precision=22, scale=0)
    public Long getConsignmenNum() {
        return this.consignmenNum;
    }
    
    public void setConsignmenNum(Long consignmenNum) {
        this.consignmenNum = consignmenNum;
    }

    @Transient
    public String getSpecName()
    {
        return specName;
    }

    public void setSpecName(String specName)
    {
        this.specName = specName;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JpmSendConsignment pojo = (JpmSendConsignment) o;

        if (sendDate != null ? !sendDate.equals(pojo.sendDate) : pojo.sendDate != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (specNo != null ? !specNo.equals(pojo.specNo) : pojo.specNo != null) return false;
        if (consignmenNum != null ? !consignmenNum.equals(pojo.consignmenNum) : pojo.consignmenNum != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = 31 * result + (sendDate != null ? sendDate.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (specNo != null ? specNo.hashCode() : 0);
        result = 31 * result + (consignmenNum != null ? consignmenNum.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("consignmentNo").append("='").append(getConsignmentNo()).append("', ");
        sb.append("sendDate").append("='").append(getSendDate()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("specNo").append("='").append(getSpecNo()).append("', ");
        sb.append("consignmenNum").append("='").append(getConsignmenNum()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
