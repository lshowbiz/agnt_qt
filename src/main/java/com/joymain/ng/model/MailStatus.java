package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name="MAIL_STATUS")

@XmlRootElement
public class MailStatus extends BaseObject implements Serializable {
    private Long statusId;
    private String mail_no;//mail_no
    private String logist_comp;//logist_comp

    private List<Items> items = new ArrayList(); //items

    
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_pd")
	@SequenceGenerator(name = "seq_pd", sequenceName = "SEQ_PD", allocationSize = 1)
    @Column(name="STATUS_ID", unique=true, nullable=false, precision=22, scale=0) @DocumentId  
    public Long getStatusId() {
        return this.statusId;
    }
    
    public void setStatusId(Long statusId) {
        this.statusId = statusId;
    }
    
    @Column(name="MAIL_NO", length=100)
    public String getMail_no() {
        return this.mail_no;
    }
    
    public void setMail_no(String mail_no) {
        this.mail_no = mail_no;
    }
    
    @Column(name="LOGIST_COMP", length=200)
    public String getLogist_comp() {
        return this.logist_comp;
    }
    
    public void setLogist_comp(String logist_comp) {
        this.logist_comp = logist_comp;
    }
 
	@OneToMany(mappedBy = "mailStatus", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Items> getItems() {
		return items;
	}

	public void setItems(List<Items> items) {
		this.items = items;
	}

	public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MailStatus pojo = (MailStatus) o;

        if (mail_no != null ? !mail_no.equals(pojo.mail_no) : pojo.mail_no != null) return false;
        if (logist_comp != null ? !logist_comp.equals(pojo.logist_comp) : pojo.logist_comp != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (mail_no != null ? mail_no.hashCode() : 0);
        result = 31 * result + (logist_comp != null ? logist_comp.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("statusId").append("='").append(getStatusId()).append("', ");
        sb.append("mail_no").append("='").append(getMail_no()).append("', ");
        sb.append("logist_comp").append("='").append(getLogist_comp()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

}
