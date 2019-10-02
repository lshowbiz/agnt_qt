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

@Entity
@Table(name="JBD_MEMBER_CONGRATIONS")
public class JbdMemberCongrations extends BaseObject implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4795194972913727587L;
    private Long id;
    private Integer yearMonth;
    private String userCode;
    private Integer starLevel;
    private String remark;//备注
    private String status;//状态(预留)
    private String createUser;
    private Date createTime;
    
    private String chineseName;
    private String englishName;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BD")
    @SequenceGenerator(name = "SEQ_BD", sequenceName = "SEQ_BD", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=12, scale=0)
    public Long getId()
    {
        return id;
    }
    public void setId(Long id)
    {
        this.id = id;
    }
    @Column(name="YEAR_MONTH", precision=8, scale=0)
    public Integer getYearMonth()
    {
        return yearMonth;
    }
    public void setYearMonth(Integer yearMonth)
    {
        this.yearMonth = yearMonth;
    }
    @Column(name="USER_CODE", length=20)
    public String getUserCode()
    {
        return userCode;
    }
    public void setUserCode(String userCode)
    {
        this.userCode = userCode;
    }
    @Column(name="STAR_LEVEL", precision=2, scale=0)
    public Integer getStarLevel()
    {
        return starLevel;
    }
    public void setStarLevel(Integer starLevel)
    {
        this.starLevel = starLevel;
    }
    @Column(name="STATUS", length=1)
    public String getStatus()
    {
        return status;
    }
    public void setStatus(String status)
    {
        this.status = status;
    }
    @Column(name="REMARK", length=4000)
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    @Column(name="CREATE_USER", length=20)
    public String getCreateUser()
    {
        return createUser;
    }
    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }
    @Column(name="CREATE_TIME")
    public Date getCreateTime()
    {
        return createTime;
    }
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    @Column(name="CHINESE_NAME", length=400)
    public String getChineseName()
    {
        return chineseName;
    }
    public void setChineseName(String chineseName)
    {
        this.chineseName = chineseName;
    }
    @Column(name="ENGLISH_NAME", length=400)
    public String getEnglishName()
    {
        return englishName;
    }
    public void setEnglishName(String englishName)
    {
        this.englishName = englishName;
    }
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((chineseName == null) ? 0 : chineseName.hashCode());
        result = prime * result
                + ((createTime == null) ? 0 : createTime.hashCode());
        result = prime * result
                + ((createUser == null) ? 0 : createUser.hashCode());
        result = prime * result
                + ((englishName == null) ? 0 : englishName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((remark == null) ? 0 : remark.hashCode());
        result = prime * result
                + ((starLevel == null) ? 0 : starLevel.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result
                + ((userCode == null) ? 0 : userCode.hashCode());
        result = prime * result
                + ((yearMonth == null) ? 0 : yearMonth.hashCode());
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
        JbdMemberCongrations other = (JbdMemberCongrations) obj;
        if (chineseName == null)
        {
            if (other.chineseName != null)
                return false;
        }
        else if (!chineseName.equals(other.chineseName))
            return false;
        if (createTime == null)
        {
            if (other.createTime != null)
                return false;
        }
        else if (!createTime.equals(other.createTime))
            return false;
        if (createUser == null)
        {
            if (other.createUser != null)
                return false;
        }
        else if (!createUser.equals(other.createUser))
            return false;
        if (englishName == null)
        {
            if (other.englishName != null)
                return false;
        }
        else if (!englishName.equals(other.englishName))
            return false;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (remark == null)
        {
            if (other.remark != null)
                return false;
        }
        else if (!remark.equals(other.remark))
            return false;
        if (starLevel == null)
        {
            if (other.starLevel != null)
                return false;
        }
        else if (!starLevel.equals(other.starLevel))
            return false;
        if (status == null)
        {
            if (other.status != null)
                return false;
        }
        else if (!status.equals(other.status))
            return false;
        if (userCode == null)
        {
            if (other.userCode != null)
                return false;
        }
        else if (!userCode.equals(other.userCode))
            return false;
        if (yearMonth == null)
        {
            if (other.yearMonth != null)
                return false;
        }
        else if (!yearMonth.equals(other.yearMonth))
            return false;
        return true;
    }
    @Override
    public String toString()
    {
        return "JbdMemberCongrations [id=" + id + ", yearMonth=" + yearMonth
                + ", userCode=" + userCode + ", starLevel=" + starLevel
                + ", remark=" + remark + ", status=" + status + ", createUser="
                + createUser + ", createTime=" + createTime + ", chineseName="
                + chineseName + ", englishName=" + englishName + "]";
    }
}
