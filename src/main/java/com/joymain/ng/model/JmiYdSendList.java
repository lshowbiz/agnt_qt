package com.joymain.ng.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="JMI_YD_SEND_LIST")

public class JmiYdSendList extends BaseObject implements Serializable {
    private Long id;
    private String userCode;
    private String recommendNo;
    private String sourceCode;
    private Date createTime;
    private Date sendTime;
    private BigDecimal sendNum;


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MI")
    @SequenceGenerator(name = "SEQ_MI", sequenceName = "SEQ_MI", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false)
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    @Column(name="USER_CODE", length=20)
    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    @Column(name="RECOMMEND_NO", length=20)
    public String getRecommendNo() {
        return recommendNo;
    }

    public void setRecommendNo(String recommendNo) {
        this.recommendNo = recommendNo;
    }

    @Column(name="source_code")
    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }
    @Column(name="CREATE_TIME")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    @Column(name="SEND_TIME")
    public Date getSendTime() {
        return sendTime;
    }
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Column(name="SEND_NUM")
    public BigDecimal getSendNum() {
        return sendNum;
    }

    public void setSendNum(BigDecimal sendNum) {
        this.sendNum = sendNum;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());
        sb.append(" [");
        sb.append("id").append("='").append(getId()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("recommendNo").append("='").append(getRecommendNo()).append("', ");
        sb.append("sourceCode").append("='").append(getSourceCode()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("', ");
        sb.append("sendTime").append("='").append(getSendTime()).append("', ");
        sb.append("sendNum").append("='").append(getSendNum()).append("', ");
        sb.append("]");

        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JmiYdSendList pojo = (JmiYdSendList) o;

        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (recommendNo != null ? !recommendNo.equals(pojo.recommendNo) : pojo.recommendNo != null) return false;
        if (sourceCode != null ? !sourceCode.equals(pojo.sourceCode) : pojo.sourceCode != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;
        if (sendTime != null ? !sendTime.equals(pojo.sendTime) : pojo.sendTime != null) return false;
        if (sendNum != null ? !sendNum.equals(pojo.sendNum) : pojo.sendNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {

        int result = 0;
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (recommendNo != null ? recommendNo.hashCode() : 0);
        result = 31 * result + (sourceCode != null ? sourceCode.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (sendTime != null ? sendTime.hashCode() : 0);
        result = 31 * result + (sendNum != null ? sendNum.hashCode() : 0);
        return result;
    }
}
