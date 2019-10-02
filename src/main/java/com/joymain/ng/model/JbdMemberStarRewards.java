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
@Table(name="JBD_MEMBER_STAR_REWARDS")
public class JbdMemberStarRewards extends BaseObject implements Serializable {

    private Long id;
    private Integer fyear;
    private String userCode;
    private Integer rewards;
    private String isReach;
    private String remark;//备注
    private String memberRemark;//会员备注
    private String createUser;
    private Date createTime;
    
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
    @Column(name="F_YEAR", precision=8, scale=0)
    public Integer getFyear()
    {
        return fyear;
    }
    public void setFyear(Integer fyear)
    {
        this.fyear = fyear;
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
    @Column(name="REWARDS", precision=2, scale=0)
    public Integer getRewards()
    {
        return rewards;
    }
    public void setRewards(Integer rewards)
    {
        this.rewards = rewards;
    }
    @Column(name="IS_REACH", length=1)
    public String getIsReach()
    {
        return isReach;
    }
    public void setIsReach(String isReach)
    {
        this.isReach = isReach;
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
    @Column(name="MEMBER_REMARK", length=4000)
    public String getMemberRemark()
    {
        return memberRemark;
    }
    public void setMemberRemark(String memberRemark)
    {
        this.memberRemark = memberRemark;
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
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((createTime == null) ? 0 : createTime.hashCode());
        result = prime * result
                + ((createUser == null) ? 0 : createUser.hashCode());
        result = prime * result + ((fyear == null) ? 0 : fyear.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((isReach == null) ? 0 : isReach.hashCode());
        result = prime * result + ((remark == null) ? 0 : remark.hashCode());
        result = prime * result + ((rewards == null) ? 0 : rewards.hashCode());
        result = prime * result
                + ((userCode == null) ? 0 : userCode.hashCode());
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
        JbdMemberStarRewards other = (JbdMemberStarRewards) obj;
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
        if (fyear == null)
        {
            if (other.fyear != null)
                return false;
        }
        else if (!fyear.equals(other.fyear))
            return false;
        if (id == null)
        {
            if (other.id != null)
                return false;
        }
        else if (!id.equals(other.id))
            return false;
        if (isReach == null)
        {
            if (other.isReach != null)
                return false;
        }
        else if (!isReach.equals(other.isReach))
            return false;
        if (remark == null)
        {
            if (other.remark != null)
                return false;
        }
        else if (!remark.equals(other.remark))
            return false;
        if (rewards == null)
        {
            if (other.rewards != null)
                return false;
        }
        else if (!rewards.equals(other.rewards))
            return false;
        if (userCode == null)
        {
            if (other.userCode != null)
                return false;
        }
        else if (!userCode.equals(other.userCode))
            return false;
        return true;
    }
    @Override
    public String toString()
    {
        return "JbdMemberStarRewards [id=" + id + ", fyear=" + fyear
                + ", userCode=" + userCode + ", rewards=" + rewards
                + ", isReach=" + isReach + ", remark=" + remark
                + ", createUser=" + createUser + ", createTime=" + createTime
                + "]";
    }
    
}
