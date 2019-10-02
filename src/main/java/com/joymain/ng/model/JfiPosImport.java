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
import javax.persistence.UniqueConstraint;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="JFI_POS_IMPORT", uniqueConstraints = @UniqueConstraint(columnNames="POS_NO") )

@XmlRootElement
public class JfiPosImport extends BaseObject implements Serializable {
    private Long jpiId;
    private String posNo;
    private String tel;
    private String PId;
    private BigDecimal amount;
    private String status;
    private Date checkTime;
    private String checkUser;
    private String userCode;
    private String inc;
    private Date incTime;
    private Date messageTime;
    private String messageCode;
    private String createUser;
    private Date createTime;
    
    private Integer moneyType;//POS支付资金类别 (89：POS现场刷卡支付 ，35：银联POS，106：畅捷通POS)

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_bankbook")
	@SequenceGenerator(name = "seq_bankbook", sequenceName = "SEQ_BANKBOOK", allocationSize = 1)
    @Column(name="JPI_ID", unique=true, nullable=false, precision=10, scale=0) @DocumentId    
    public Long getJpiId() {
        return this.jpiId;
    }
    
    public void setJpiId(Long jpiId) {
        this.jpiId = jpiId;
    }
    
    @Column(name="POS_NO", unique=true, nullable=false, length=200)
    public String getPosNo() {
        return this.posNo;
    }
    
    public void setPosNo(String posNo) {
        this.posNo = posNo;
    }
    
    @Column(name="TEL", nullable=false, length=200)
    public String getTel() {
        return this.tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    @Column(name="P_ID", nullable=false, length=200)
    public String getPId() {
        return this.PId;
    }
    
    public void setPId(String PId) {
        this.PId = PId;
    }
    
    @Column(name="AMOUNT", nullable=false, precision=22, scale=0)
    public BigDecimal getAmount() {
        return this.amount;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    @Column(name="STATUS", nullable=false, length=2)
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CHECK_TIME", length=7)
    public Date getCheckTime() {
        return this.checkTime;
    }
    
    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
    
    @Column(name="CHECK_USER", length=200)
    public String getCheckUser() {
        return this.checkUser;
    }
    
    public void setCheckUser(String checkUser) {
        this.checkUser = checkUser;
    }
    
    @Column(name="USER_CODE", length=200)
    public String getUserCode() {
        return this.userCode;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    
    @Column(name="INC", length=2)
    public String getInc() {
        return this.inc;
    }
    
    public void setInc(String inc) {
        this.inc = inc;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="INC_TIME", length=7)
    public Date getIncTime() {
        return this.incTime;
    }
    
    public void setIncTime(Date incTime) {
        this.incTime = incTime;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="MESSAGE_TIME", length=7)
    public Date getMessageTime() {
        return this.messageTime;
    }
    
    public void setMessageTime(Date messageTime) {
        this.messageTime = messageTime;
    }
    
    @Column(name="MESSAGE_CODE", length=4000)
    public String getMessageCode() {
        return this.messageCode;
    }
    
    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }
    
    @Column(name="CREATE_USER", nullable=false, length=200)
    public String getCreateUser() {
        return this.createUser;
    }
    
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", nullable=false, length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JfiPosImport pojo = (JfiPosImport) o;

        if (posNo != null ? !posNo.equals(pojo.posNo) : pojo.posNo != null) return false;
        if (tel != null ? !tel.equals(pojo.tel) : pojo.tel != null) return false;
        if (PId != null ? !PId.equals(pojo.PId) : pojo.PId != null) return false;
        if (amount != null ? !amount.equals(pojo.amount) : pojo.amount != null) return false;
        if (status != null ? !status.equals(pojo.status) : pojo.status != null) return false;
        if (checkTime != null ? !checkTime.equals(pojo.checkTime) : pojo.checkTime != null) return false;
        if (checkUser != null ? !checkUser.equals(pojo.checkUser) : pojo.checkUser != null) return false;
        if (userCode != null ? !userCode.equals(pojo.userCode) : pojo.userCode != null) return false;
        if (inc != null ? !inc.equals(pojo.inc) : pojo.inc != null) return false;
        if (incTime != null ? !incTime.equals(pojo.incTime) : pojo.incTime != null) return false;
        if (messageTime != null ? !messageTime.equals(pojo.messageTime) : pojo.messageTime != null) return false;
        if (messageCode != null ? !messageCode.equals(pojo.messageCode) : pojo.messageCode != null) return false;
        if (createUser != null ? !createUser.equals(pojo.createUser) : pojo.createUser != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (posNo != null ? posNo.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (PId != null ? PId.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (checkTime != null ? checkTime.hashCode() : 0);
        result = 31 * result + (checkUser != null ? checkUser.hashCode() : 0);
        result = 31 * result + (userCode != null ? userCode.hashCode() : 0);
        result = 31 * result + (inc != null ? inc.hashCode() : 0);
        result = 31 * result + (incTime != null ? incTime.hashCode() : 0);
        result = 31 * result + (messageTime != null ? messageTime.hashCode() : 0);
        result = 31 * result + (messageCode != null ? messageCode.hashCode() : 0);
        result = 31 * result + (createUser != null ? createUser.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("jpiId").append("='").append(getJpiId()).append("', ");
        sb.append("posNo").append("='").append(getPosNo()).append("', ");
        sb.append("tel").append("='").append(getTel()).append("', ");
        sb.append("PId").append("='").append(getPId()).append("', ");
        sb.append("amount").append("='").append(getAmount()).append("', ");
        sb.append("status").append("='").append(getStatus()).append("', ");
        sb.append("checkTime").append("='").append(getCheckTime()).append("', ");
        sb.append("checkUser").append("='").append(getCheckUser()).append("', ");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("inc").append("='").append(getInc()).append("', ");
        sb.append("incTime").append("='").append(getIncTime()).append("', ");
        sb.append("messageTime").append("='").append(getMessageTime()).append("', ");
        sb.append("messageCode").append("='").append(getMessageCode()).append("', ");
        sb.append("createUser").append("='").append(getCreateUser()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

    @Column(name="MONEY_TYPE", length=4)
	public Integer getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(Integer moneyType) {
		this.moneyType = moneyType;
	}

    


    
}
