package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;

@Entity
@Table(name="AM_ANNOUNCE_RECORD")

@XmlRootElement
public class AmAnnounceRecord extends BaseObject implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long uniNo;
    private String userNo;
    private Date browseTime;
    private AmAnnounce amAnnounce;
    
    @Id      
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_am")
	@SequenceGenerator(name = "seq_am", sequenceName = "SEQ_AM", allocationSize = 1)
    @Column(name="UNI_NO", unique=true, nullable=false, length=13)  
    public Long getUniNo() {
        return this.uniNo;
    }
    
    public void setUniNo(Long uniNo) {
        this.uniNo = uniNo;
    }
    
    @Column(name="USER_NO", nullable=false, length=20)
    public String getUserNo() {
        return this.userNo;
    }
    
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
    @Temporal(TemporalType.TIME)
    @Column(name="BROWSE_TIME", nullable=false, length=7)
    public Date getBrowseTime() {
        return this.browseTime;
    }
    
    public void setBrowseTime(Date browseTime) {
        this.browseTime = browseTime;
    }
    @ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="AA_NO")
	@JsonIgnore
    public AmAnnounce getAmAnnounce() {
		return amAnnounce;
	}

	public void setAmAnnounce(AmAnnounce amAnnounce) {
		this.amAnnounce = amAnnounce;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AmAnnounceRecord pojo = (AmAnnounceRecord) o;

        //if (aaNo != null ? !aaNo.equals(pojo.aaNo) : pojo.aaNo != null) return false;
        if (userNo != null ? !userNo.equals(pojo.userNo) : pojo.userNo != null) return false;
        if (browseTime != null ? !browseTime.equals(pojo.browseTime) : pojo.browseTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        //result = (aaNo != null ? aaNo.hashCode() : 0);
        result = 31 * result + (userNo != null ? userNo.hashCode() : 0);
        result = 31 * result + (browseTime != null ? browseTime.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("uniNo").append("='").append(getUniNo()).append("', ");
        //sb.append("aaNo").append("='").append(getAaNo()).append("', ");
        sb.append("userNo").append("='").append(getUserNo()).append("', ");
        sb.append("browseTime").append("='").append(getBrowseTime()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
