package com.joymain.ng.model;



import java.sql.Clob;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.io.Serializable;

@Entity
@Table(name="JSYS_OPERATION_LOG")
public class JsysOperationLog extends BaseObject implements Serializable {
    private Long logId;
    private String operaterCode;
    private String operaterName;
    private String ipAddr;
    private Date operateTime;
    private String visitUrl;
    private String operateData;
    private String companyCode;
    private String moduleName;
    private Long doType;
    private String userType;
    private Long doResult;
    private String userCode;
    private String userName;

    @Id  @GeneratedValue(strategy = GenerationType.AUTO)    @Column(name="LOG_ID", unique=true, nullable=false, precision=18, scale=0)    
    public Long getLogId() {
        return this.logId;
    }
    
    public void setLogId(Long logId) {
        this.logId = logId;
    }
    
    @Column(name="OPERATER_CODE", length=100)
    public String getOperaterCode() {
        return this.operaterCode;
    }
    
    public void setOperaterCode(String operaterCode) {
        this.operaterCode = operaterCode;
    }
    
    @Column(name="OPERATER_NAME", length=300)
    public String getOperaterName() {
        return this.operaterName;
    }
    
    public void setOperaterName(String operaterName) {
        this.operaterName = operaterName;
    }
    
    @Column(name="IP_ADDR", length=30)
    public String getIpAddr() {
        return this.ipAddr;
    }
    
    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="OPERATE_TIME", length=7)
    public Date getOperateTime() {
        return this.operateTime;
    }
    
    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }
    
    @Column(name="VISIT_URL", length=200)
    public String getVisitUrl() {
        return this.visitUrl;
    }
    
    public void setVisitUrl(String visitUrl) {
        this.visitUrl = visitUrl;
    }
    
    @Column(name="OPERATE_DATA")
    public String getOperateData() {
        return this.operateData;
    }
    
    public void setOperateData(String operateData) {
        this.operateData = operateData;
    }
    
    @Column(name="COMPANY_CODE", length=2)
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="MODULE_NAME", length=150)
    public String getModuleName() {
        return this.moduleName;
    }
    
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    
    @Column(name="DO_TYPE", precision=2, scale=0)
    public Long getDoType() {
        return this.doType;
    }
    
    public void setDoType(Long doType) {
        this.doType = doType;
    }
    
    @Column(name="USER_TYPE", length=2)
    public String getUserType() {
        return this.userType;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    @Column(name="DO_RESULT", precision=2, scale=0)
    public Long getDoResult() {
        return this.doResult;
    }
    
    public void setDoResult(Long doResult) {
        this.doResult = doResult;
    }
    
    @Column(name="USER_CODE", length=100)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="USER_NAME", length=300)
    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsysOperationLog pojo = (JsysOperationLog) o;

        if (operaterCode != null ? !operaterCode.equals(pojo.operaterCode) : pojo.operaterCode != null) return false;
        if (operaterName != null ? !operaterName.equals(pojo.operaterName) : pojo.operaterName != null) return false;
        if (ipAddr != null ? !ipAddr.equals(pojo.ipAddr) : pojo.ipAddr != null) return false;
        if (operateTime != null ? !operateTime.equals(pojo.operateTime) : pojo.operateTime != null) return false;
        if (visitUrl != null ? !visitUrl.equals(pojo.visitUrl) : pojo.visitUrl != null) return false;
        if (operateData != null ? !operateData.equals(pojo.operateData) : pojo.operateData != null) return false;
        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (moduleName != null ? !moduleName.equals(pojo.moduleName) : pojo.moduleName != null) return false;
        if (doType != null ? !doType.equals(pojo.doType) : pojo.doType != null) return false;
        if (userType != null ? !userType.equals(pojo.userType) : pojo.userType != null) return false;
        if (doResult != null ? !doResult.equals(pojo.doResult) : pojo.doResult != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (userName != null ? !userName.equals(pojo.userName) : pojo.userName != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (operaterCode != null ? operaterCode.hashCode() : 0);
        result = 31 * result + (operaterName != null ? operaterName.hashCode() : 0);
        result = 31 * result + (ipAddr != null ? ipAddr.hashCode() : 0);
        result = 31 * result + (operateTime != null ? operateTime.hashCode() : 0);
        result = 31 * result + (visitUrl != null ? visitUrl.hashCode() : 0);
        result = 31 * result + (operateData != null ? operateData.hashCode() : 0);
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (moduleName != null ? moduleName.hashCode() : 0);
        result = 31 * result + (doType != null ? doType.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (doResult != null ? doResult.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("logId").append("='").append(getLogId()).append("', ");
        sb.append("operaterCode").append("='").append(getOperaterCode()).append("', ");
        sb.append("operaterName").append("='").append(getOperaterName()).append("', ");
        sb.append("ipAddr").append("='").append(getIpAddr()).append("', ");
        sb.append("operateTime").append("='").append(getOperateTime()).append("', ");
        sb.append("visitUrl").append("='").append(getVisitUrl()).append("', ");
        sb.append("operateData").append("='").append(getOperateData()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("moduleName").append("='").append(getModuleName()).append("', ");
        sb.append("doType").append("='").append(getDoType()).append("', ");
        sb.append("userType").append("='").append(getUserType()).append("', ");
        sb.append("doResult").append("='").append(getDoResult()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("userName").append("='").append(getUserName()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
