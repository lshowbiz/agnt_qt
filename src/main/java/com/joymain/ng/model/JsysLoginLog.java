package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;

@Entity
@Table(name="JSYS_LOGIN_LOG")

@XmlRootElement
public class JsysLoginLog extends BaseObject implements Serializable {
    private Long llId;
    private String userCode;
    private String operaterCode;
    private String ipAddr;
    private Date operateTime;
    private String operationType;
    private String fromDevice;



	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_log")
	@SequenceGenerator(name = "seq_log", sequenceName = "SEQ_LOG", allocationSize = 1)
    @Column(name="LL_ID", unique=true, nullable=false, precision=10, scale=0)    
    public Long getLlId() {
        return this.llId;
    }
    
    public void setLlId(Long llId) {
        this.llId = llId;
    }
    
    @Column(name="USER_CODE", length=100)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="OPERATER_CODE", length=100)
    public String getOperaterCode() {
        return this.operaterCode;
    }
    
    public void setOperaterCode(String operaterCode) {
        this.operaterCode = operaterCode;
    }
    
    @Column(name="IP_ADDR", length=30)
    public String getIpAddr() {
        return this.ipAddr;
    }
    
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
    @Column(name="OPERATE_TIME", length=7)
    public Date getOperateTime() {
        return this.operateTime;
    }
    
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
    
    @Column(name="OPERATION_TYPE", length=5)
    public String getOperationType() {
        return this.operationType;
    }
    
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }
    
    @Column(name="FROM_DEVICE", length=200)
	public String getFromDevice() {
		return fromDevice;
	}

	public void setFromDevice(String fromDevice) {
		this.fromDevice = fromDevice;
	}
	
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsysLoginLog pojo = (JsysLoginLog) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (operaterCode != null ? !operaterCode.equals(pojo.operaterCode) : pojo.operaterCode != null) return false;
        if (ipAddr != null ? !ipAddr.equals(pojo.ipAddr) : pojo.ipAddr != null) return false;
        if (operateTime != null ? !operateTime.equals(pojo.operateTime) : pojo.operateTime != null) return false;
        if (operationType != null ? !operationType.equals(pojo.operationType) : pojo.operationType != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (operaterCode != null ? operaterCode.hashCode() : 0);
        result = 31 * result + (ipAddr != null ? ipAddr.hashCode() : 0);
        result = 31 * result + (operateTime != null ? operateTime.hashCode() : 0);
        result = 31 * result + (operationType != null ? operationType.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("llId").append("='").append(getLlId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("operaterCode").append("='").append(getOperaterCode()).append("', ");
        sb.append("ipAddr").append("='").append(getIpAddr()).append("', ");
        sb.append("operateTime").append("='").append(getOperateTime()).append("', ");
        sb.append("operationType").append("='").append(getOperationType()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
