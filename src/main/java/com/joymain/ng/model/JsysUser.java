package com.joymain.ng.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.search.annotations.DocumentId;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name="JSYS_USER")
@XmlRootElement
public class JsysUser extends BaseObject implements Serializable,UserDetails {
    private String userCode;
    private String userName;
    private String password;
    private String password2;
    private String state;
    private String companyCode;
    private String userType;
    private String defCharacterCoding;
    private Long ipCheck;
    private String userArea;
    private String firstName;
    private String lastName;
    private String honorStar;
    private String sendStatus;
    private Date lastLoginErrorTime;
    private boolean failureTimes;
    private String pwdVerifycode;
    private Date pwdValidDate;
    private String remark;
    private String blueCloud;
    private Date bcEndTime;
    private String avatarPic;

    private String token;
	private JmiMember jmiMember;
	//不与数据库关联
	private String upGrade;
    
    private Set<JsysRole> jsysRoles = new HashSet<JsysRole>();
    @Id  @DocumentId
    @Column(name = "USER_CODE", unique = true, nullable = false, length = 20)
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
    
    @Column(name="PASSWORD", nullable=false, length=32)
    
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="PASSWORD2", length=32)
    
    public String getPassword2() {
        return this.password2;
    }
    
    public void setPassword2(String password2) {
        this.password2 = password2;
    }
    
    @Column(name="TOKEN", length=32)
    public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Column(name="STATE", nullable=false, length=1)
    
    public String getState() {
        return this.state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    @Column(name="COMPANY_CODE", nullable=false, length=2)
    
    public String getCompanyCode() {
        return this.companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    @Column(name="USER_TYPE", nullable=false, length=2)
    
    public String getUserType() {
        return this.userType;
    }
    
    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    @Column(name="DEF_CHARACTER_CODING", nullable=false, length=10)
    
    public String getDefCharacterCoding() {
        return this.defCharacterCoding;
    }
    
    public void setDefCharacterCoding(String defCharacterCoding) {
        this.defCharacterCoding = defCharacterCoding;
    }
    
    @Column(name="IP_CHECK", precision=2, scale=0)
    
    public Long getIpCheck() {
        return this.ipCheck;
    }
    
    public void setIpCheck(Long ipCheck) {
        this.ipCheck = ipCheck;
    }
    
    @Column(name="USER_AREA", length=50)
    
    public String getUserArea() {
        return this.userArea;
    }
    
    public void setUserArea(String userArea) {
        this.userArea = userArea;
    }
    
    @Column(name="FIRST_NAME", length=100)
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @Column(name="LAST_NAME", length=100)
    
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    @Column(name="HONOR_STAR", length=1)
    
    public String getHonorStar() {
        return this.honorStar;
    }
    
    public void setHonorStar(String honorStar) {
        this.honorStar = honorStar;
    }
    
    @Column(name="SEND_STATUS", length=1)
    
    public String getSendStatus() {
        return this.sendStatus;
    }
    
    public void setSendStatus(String sendStatus) {
        this.sendStatus = sendStatus;
    }
    @Column(name="LAST_LOGIN_ERROR_TIME", length=7)
    
    public Date getLastLoginErrorTime() {
        return this.lastLoginErrorTime;
    }
    
    public void setLastLoginErrorTime(Date lastLoginErrorTime) {
        this.lastLoginErrorTime = lastLoginErrorTime;
    }
    
    @Column(name="FAILURE_TIMES", precision=1, scale=0)
    
    public boolean isFailureTimes() {
        return this.failureTimes;
    }
    
    public void setFailureTimes(boolean failureTimes) {
        this.failureTimes = failureTimes;
    }
    
    @Column(name="PWD_VERIFYCODE", length=32)
    
    public String getPwdVerifycode() {
        return this.pwdVerifycode;
    }
    
    public void setPwdVerifycode(String pwdVerifycode) {
        this.pwdVerifycode = pwdVerifycode;
    }
    
    @Column(name="PWD_VALID_DATE", length=7)
    
    public Date getPwdValidDate() {
        return this.pwdValidDate;
    }
    
    public void setPwdValidDate(Date pwdValidDate) {
        this.pwdValidDate = pwdValidDate;
    }
    
    @Column(name="REMARK", length=1000)
    
    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Column(name="BLUE_CLOUD", length=2)
    
    public String getBlueCloud() {
        return this.blueCloud;
    }
    
    public void setBlueCloud(String blueCloud) {
        this.blueCloud = blueCloud;
    }
    
    @Column(name="BC_END_TIME", length=7)
    
    public Date getBcEndTime() {
        return this.bcEndTime;
    }
    
    public void setBcEndTime(Date bcEndTime) {
        this.bcEndTime = bcEndTime;
    }
    
    @Column(name="AVATAR_PIC", length=200)
    
    public String getAvatarPic() {
        return this.avatarPic;
    }
    
    public void setAvatarPic(String avatarPic) {
        this.avatarPic = avatarPic;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsysUser pojo = (JsysUser) o;

        if (userName != null ? !userName.equals(pojo.userName) : pojo.userName != null) return false;
        if (password != null ? !password.equals(pojo.password) : pojo.password != null) return false;
        if (password2 != null ? !password2.equals(pojo.password2) : pojo.password2 != null) return false;
        if (state != null ? !state.equals(pojo.state) : pojo.state != null) return false;
        if (companyCode != null ? !companyCode.equals(pojo.companyCode) : pojo.companyCode != null) return false;
        if (userType != null ? !userType.equals(pojo.userType) : pojo.userType != null) return false;
        if (defCharacterCoding != null ? !defCharacterCoding.equals(pojo.defCharacterCoding) : pojo.defCharacterCoding != null) return false;
        if (ipCheck != null ? !ipCheck.equals(pojo.ipCheck) : pojo.ipCheck != null) return false;
        if (userArea != null ? !userArea.equals(pojo.userArea) : pojo.userArea != null) return false;
        if (firstName != null ? !firstName.equals(pojo.firstName) : pojo.firstName != null) return false;
        if (lastName != null ? !lastName.equals(pojo.lastName) : pojo.lastName != null) return false;
        if (honorStar != null ? !honorStar.equals(pojo.honorStar) : pojo.honorStar != null) return false;
        if (sendStatus != null ? !sendStatus.equals(pojo.sendStatus) : pojo.sendStatus != null) return false;
        if (lastLoginErrorTime != null ? !lastLoginErrorTime.equals(pojo.lastLoginErrorTime) : pojo.lastLoginErrorTime != null) return false;
        if (failureTimes != pojo.failureTimes) return false;
        if (pwdVerifycode != null ? !pwdVerifycode.equals(pojo.pwdVerifycode) : pojo.pwdVerifycode != null) return false;
        if (pwdValidDate != null ? !pwdValidDate.equals(pojo.pwdValidDate) : pojo.pwdValidDate != null) return false;
        if (remark != null ? !remark.equals(pojo.remark) : pojo.remark != null) return false;
        if (blueCloud != null ? !blueCloud.equals(pojo.blueCloud) : pojo.blueCloud != null) return false;
        if (bcEndTime != null ? !bcEndTime.equals(pojo.bcEndTime) : pojo.bcEndTime != null) return false;
        if (avatarPic != null ? !avatarPic.equals(pojo.avatarPic) : pojo.avatarPic != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (password2 != null ? password2.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (companyCode != null ? companyCode.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (defCharacterCoding != null ? defCharacterCoding.hashCode() : 0);
        result = 31 * result + (ipCheck != null ? ipCheck.hashCode() : 0);
        result = 31 * result + (userArea != null ? userArea.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (honorStar != null ? honorStar.hashCode() : 0);
        result = 31 * result + (sendStatus != null ? sendStatus.hashCode() : 0);
        result = 31 * result + (lastLoginErrorTime != null ? lastLoginErrorTime.hashCode() : 0);
        result = 31 * result + (failureTimes ? 1 : 0);
        result = 31 * result + (pwdVerifycode != null ? pwdVerifycode.hashCode() : 0);
        result = 31 * result + (pwdValidDate != null ? pwdValidDate.hashCode() : 0);
        result = 31 * result + (remark != null ? remark.hashCode() : 0);
        result = 31 * result + (blueCloud != null ? blueCloud.hashCode() : 0);
        result = 31 * result + (bcEndTime != null ? bcEndTime.hashCode() : 0);
        result = 31 * result + (avatarPic != null ? avatarPic.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("userCode").append("='").append(getUserCode()).append("', ");
        sb.append("userName").append("='").append(getUserName()).append("', ");
        sb.append("password").append("='").append(getPassword()).append("', ");
        sb.append("password2").append("='").append(getPassword2()).append("', ");
        sb.append("state").append("='").append(getState()).append("', ");
        sb.append("companyCode").append("='").append(getCompanyCode()).append("', ");
        sb.append("userType").append("='").append(getUserType()).append("', ");
        sb.append("defCharacterCoding").append("='").append(getDefCharacterCoding()).append("', ");
        sb.append("ipCheck").append("='").append(getIpCheck()).append("', ");
        sb.append("userArea").append("='").append(getUserArea()).append("', ");
        sb.append("firstName").append("='").append(getFirstName()).append("', ");
        sb.append("lastName").append("='").append(getLastName()).append("', ");
        sb.append("honorStar").append("='").append(getHonorStar()).append("', ");
        sb.append("sendStatus").append("='").append(getSendStatus()).append("', ");
        sb.append("lastLoginErrorTime").append("='").append(getLastLoginErrorTime()).append("', ");
        sb.append("failureTimes").append("='").append(isFailureTimes()).append("', ");
        sb.append("pwdVerifycode").append("='").append(getPwdVerifycode()).append("', ");
        sb.append("pwdValidDate").append("='").append(getPwdValidDate()).append("', ");
        sb.append("remark").append("='").append(getRemark()).append("', ");
        sb.append("blueCloud").append("='").append(getBlueCloud()).append("', ");
        sb.append("bcEndTime").append("='").append(getBcEndTime()).append("', ");
        sb.append("avatarPic").append("='").append(getAvatarPic()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "jsys_user_role", joinColumns = { @JoinColumn(name = "USER_CODE") }, inverseJoinColumns = { @JoinColumn(name = "ROLE_ID") })
	@JsonIgnore
	public Set<JsysRole> getJsysRoles() {
		return jsysRoles;
	}

	public void setJsysRoles(Set<JsysRole> jsysRoles) {
		this.jsysRoles = jsysRoles;
	}

	@Transient
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return new HashSet<GrantedAuthority>(getJsysRoles());
	}

	@Transient
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getUserCode();
	}

	@Transient
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Transient
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Transient
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Transient
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		if ("1".equals(this.getState())) {
			return true;
		}
		return false;
	}

	@OneToOne(mappedBy = "sysUser")
	@JsonIgnore
	public JmiMember getJmiMember() {
		return jmiMember;
	}

	public void setJmiMember(JmiMember jmiMember) {
		this.jmiMember = jmiMember;
	}

	@Transient
	public String getUpGrade() {
		return upGrade;
	}

	public void setUpGrade(String upGrade) {
		this.upGrade = upGrade;
	}

}
