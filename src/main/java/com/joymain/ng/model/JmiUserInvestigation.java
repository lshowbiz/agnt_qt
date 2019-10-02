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
@Table(name="JMI_USER_INVESTIGATION")

@XmlRootElement
public class JmiUserInvestigation extends BaseObject implements Serializable {
    private Long inverid;
    private String usercode;
    private String subject01;
    private String subject02;
    private String subject03;
    private String subject04;
    private String subject05;
    private String subject06;
    private String subject07;
    private String subject08;
    private String subject09;
    private String subject10;
    private String subject11;
    private String subject12;
    private String subject13;
    private String subject14;
    private String subject15;
    private String subject16;
    private String subject17;
    private String subject18;
    private String subject19;
    private String subject20;
    private Date crateTime;

    @Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_mi")
	@SequenceGenerator(name = "seq_mi", sequenceName = "SEQ_MI", allocationSize = 1)
    @Column(name="INVERID", unique=true, nullable=false, precision=12, scale=0)
    public Long getInverid() {
        return this.inverid;
    }
    
    public void setInverid(Long inverid) {
        this.inverid = inverid;
    }
    
    @Column(name="USERCODE", length=20)
    public String getUsercode() {
        return this.usercode;
    }
    
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }
    
    @Column(name="SUBJECT01", length=20)
    public String getSubject01() {
        return this.subject01;
    }
    
    public void setSubject01(String subject01) {
        this.subject01 = subject01;
    }
    
    @Column(name="SUBJECT02", length=20)
    public String getSubject02() {
        return this.subject02;
    }
    
    public void setSubject02(String subject02) {
        this.subject02 = subject02;
    }
    
    @Column(name="SUBJECT03", length=20)
    public String getSubject03() {
        return this.subject03;
    }
    
    public void setSubject03(String subject03) {
        this.subject03 = subject03;
    }
    
    @Column(name="SUBJECT04", length=20)
    public String getSubject04() {
        return this.subject04;
    }
    
    public void setSubject04(String subject04) {
        this.subject04 = subject04;
    }
    
    @Column(name="SUBJECT05", length=20)
    public String getSubject05() {
        return this.subject05;
    }
    
    public void setSubject05(String subject05) {
        this.subject05 = subject05;
    }
    
    @Column(name="SUBJECT06", length=20)
    public String getSubject06() {
        return this.subject06;
    }
    
    public void setSubject06(String subject06) {
        this.subject06 = subject06;
    }
    
    @Column(name="SUBJECT07", length=20)
    public String getSubject07() {
        return this.subject07;
    }
    
    public void setSubject07(String subject07) {
        this.subject07 = subject07;
    }
    
    @Column(name="SUBJECT08", length=20)
    public String getSubject08() {
        return this.subject08;
    }
    
    public void setSubject08(String subject08) {
        this.subject08 = subject08;
    }
    
    @Column(name="SUBJECT09", length=20)
    public String getSubject09() {
        return this.subject09;
    }
    
    public void setSubject09(String subject09) {
        this.subject09 = subject09;
    }
    
    @Column(name="SUBJECT10", length=20)
    public String getSubject10() {
        return this.subject10;
    }
    
    public void setSubject10(String subject10) {
        this.subject10 = subject10;
    }
    
    @Column(name="SUBJECT11", length=20)
    public String getSubject11() {
        return this.subject11;
    }
    
    public void setSubject11(String subject11) {
        this.subject11 = subject11;
    }
    
    @Column(name="SUBJECT12", length=20)
    public String getSubject12() {
        return this.subject12;
    }
    
    public void setSubject12(String subject12) {
        this.subject12 = subject12;
    }
    
    @Column(name="SUBJECT13", length=20)
    public String getSubject13() {
        return this.subject13;
    }
    
    public void setSubject13(String subject13) {
        this.subject13 = subject13;
    }
    
    @Column(name="SUBJECT14", length=20)
    public String getSubject14() {
        return this.subject14;
    }
    
    public void setSubject14(String subject14) {
        this.subject14 = subject14;
    }
    
    @Column(name="SUBJECT15", length=20)
    public String getSubject15() {
        return this.subject15;
    }
    
    public void setSubject15(String subject15) {
        this.subject15 = subject15;
    }
    
    @Column(name="SUBJECT16", length=20)
    public String getSubject16() {
        return this.subject16;
    }
    
    public void setSubject16(String subject16) {
        this.subject16 = subject16;
    }
    
    @Column(name="SUBJECT17", length=20)
    public String getSubject17() {
        return this.subject17;
    }
    
    public void setSubject17(String subject17) {
        this.subject17 = subject17;
    }
    
    @Column(name="SUBJECT18", length=20)
    public String getSubject18() {
        return this.subject18;
    }
    
    public void setSubject18(String subject18) {
        this.subject18 = subject18;
    }
    
    @Column(name="SUBJECT19", length=20)
    public String getSubject19() {
        return this.subject19;
    }
    
    public void setSubject19(String subject19) {
        this.subject19 = subject19;
    }
    
    @Column(name="SUBJECT20", length=4000)
    public String getSubject20() {
        return this.subject20;
    }
    
    public void setSubject20(String subject20) {
        this.subject20 = subject20;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CRATE_TIME", length=7)
    public Date getCrateTime() {
        return this.crateTime;
    }
    
    public void setCrateTime(Date crateTime) {
        this.crateTime = crateTime;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JmiUserInvestigation pojo = (JmiUserInvestigation) o;

        if (usercode != null ? !usercode.equals(pojo.usercode) : pojo.usercode != null) return false;
        if (subject01 != null ? !subject01.equals(pojo.subject01) : pojo.subject01 != null) return false;
        if (subject02 != null ? !subject02.equals(pojo.subject02) : pojo.subject02 != null) return false;
        if (subject03 != null ? !subject03.equals(pojo.subject03) : pojo.subject03 != null) return false;
        if (subject04 != null ? !subject04.equals(pojo.subject04) : pojo.subject04 != null) return false;
        if (subject05 != null ? !subject05.equals(pojo.subject05) : pojo.subject05 != null) return false;
        if (subject06 != null ? !subject06.equals(pojo.subject06) : pojo.subject06 != null) return false;
        if (subject07 != null ? !subject07.equals(pojo.subject07) : pojo.subject07 != null) return false;
        if (subject08 != null ? !subject08.equals(pojo.subject08) : pojo.subject08 != null) return false;
        if (subject09 != null ? !subject09.equals(pojo.subject09) : pojo.subject09 != null) return false;
        if (subject10 != null ? !subject10.equals(pojo.subject10) : pojo.subject10 != null) return false;
        if (subject11 != null ? !subject11.equals(pojo.subject11) : pojo.subject11 != null) return false;
        if (subject12 != null ? !subject12.equals(pojo.subject12) : pojo.subject12 != null) return false;
        if (subject13 != null ? !subject13.equals(pojo.subject13) : pojo.subject13 != null) return false;
        if (subject14 != null ? !subject14.equals(pojo.subject14) : pojo.subject14 != null) return false;
        if (subject15 != null ? !subject15.equals(pojo.subject15) : pojo.subject15 != null) return false;
        if (subject16 != null ? !subject16.equals(pojo.subject16) : pojo.subject16 != null) return false;
        if (subject17 != null ? !subject17.equals(pojo.subject17) : pojo.subject17 != null) return false;
        if (subject18 != null ? !subject18.equals(pojo.subject18) : pojo.subject18 != null) return false;
        if (subject19 != null ? !subject19.equals(pojo.subject19) : pojo.subject19 != null) return false;
        if (subject20 != null ? !subject20.equals(pojo.subject20) : pojo.subject20 != null) return false;
        if (crateTime != null ? !crateTime.equals(pojo.crateTime) : pojo.crateTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (usercode != null ? usercode.hashCode() : 0);
        result = 31 * result + (subject01 != null ? subject01.hashCode() : 0);
        result = 31 * result + (subject02 != null ? subject02.hashCode() : 0);
        result = 31 * result + (subject03 != null ? subject03.hashCode() : 0);
        result = 31 * result + (subject04 != null ? subject04.hashCode() : 0);
        result = 31 * result + (subject05 != null ? subject05.hashCode() : 0);
        result = 31 * result + (subject06 != null ? subject06.hashCode() : 0);
        result = 31 * result + (subject07 != null ? subject07.hashCode() : 0);
        result = 31 * result + (subject08 != null ? subject08.hashCode() : 0);
        result = 31 * result + (subject09 != null ? subject09.hashCode() : 0);
        result = 31 * result + (subject10 != null ? subject10.hashCode() : 0);
        result = 31 * result + (subject11 != null ? subject11.hashCode() : 0);
        result = 31 * result + (subject12 != null ? subject12.hashCode() : 0);
        result = 31 * result + (subject13 != null ? subject13.hashCode() : 0);
        result = 31 * result + (subject14 != null ? subject14.hashCode() : 0);
        result = 31 * result + (subject15 != null ? subject15.hashCode() : 0);
        result = 31 * result + (subject16 != null ? subject16.hashCode() : 0);
        result = 31 * result + (subject17 != null ? subject17.hashCode() : 0);
        result = 31 * result + (subject18 != null ? subject18.hashCode() : 0);
        result = 31 * result + (subject19 != null ? subject19.hashCode() : 0);
        result = 31 * result + (subject20 != null ? subject20.hashCode() : 0);
        result = 31 * result + (crateTime != null ? crateTime.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("inverid").append("='").append(getInverid()).append("', ");
        sb.append("usercode").append("='").append(getUsercode()).append("', ");
        sb.append("subject01").append("='").append(getSubject01()).append("', ");
        sb.append("subject02").append("='").append(getSubject02()).append("', ");
        sb.append("subject03").append("='").append(getSubject03()).append("', ");
        sb.append("subject04").append("='").append(getSubject04()).append("', ");
        sb.append("subject05").append("='").append(getSubject05()).append("', ");
        sb.append("subject06").append("='").append(getSubject06()).append("', ");
        sb.append("subject07").append("='").append(getSubject07()).append("', ");
        sb.append("subject08").append("='").append(getSubject08()).append("', ");
        sb.append("subject09").append("='").append(getSubject09()).append("', ");
        sb.append("subject10").append("='").append(getSubject10()).append("', ");
        sb.append("subject11").append("='").append(getSubject11()).append("', ");
        sb.append("subject12").append("='").append(getSubject12()).append("', ");
        sb.append("subject13").append("='").append(getSubject13()).append("', ");
        sb.append("subject14").append("='").append(getSubject14()).append("', ");
        sb.append("subject15").append("='").append(getSubject15()).append("', ");
        sb.append("subject16").append("='").append(getSubject16()).append("', ");
        sb.append("subject17").append("='").append(getSubject17()).append("', ");
        sb.append("subject18").append("='").append(getSubject18()).append("', ");
        sb.append("subject19").append("='").append(getSubject19()).append("', ");
        sb.append("subject20").append("='").append(getSubject20()).append("', ");
        sb.append("crateTime").append("='").append(getCrateTime()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
