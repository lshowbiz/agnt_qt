package com.joymain.ng.model;

import com.joymain.ng.model.BaseObject;

import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
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
@Table(name="AM_NEW")
@XmlRootElement
public class AmNew extends BaseObject implements RowMapper<AmNew>,Serializable {
    private String newId;
    private String category;
    private String subject;
    private String url;
    private Long newOrder;
    private Date createTime;

    @Id      
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_am")
	@SequenceGenerator(name = "seq_am", sequenceName = "SEQ_AM", allocationSize = 1)
    @Column(name="NEW_ID", unique=true, nullable=false, length=32)     
    public String getNewId() {
        return this.newId;
    }
    
    public void setNewId(String newId) {
        this.newId = newId;
    }
    
    @Column(name="CATEGORY", length=50)
    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    @Column(name="SUBJECT", length=200)
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    @Column(name="URL", length=500)
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    @Column(name="NEW_ORDER", precision=22, scale=0)
    public Long getNewOrder() {
        return this.newOrder;
    }
    
    public void setNewOrder(Long newOrder) {
        this.newOrder = newOrder;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="CREATE_TIME", length=7)
    public Date getCreateTime() {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AmNew pojo = (AmNew) o;

        if (category != null ? !category.equals(pojo.category) : pojo.category != null) return false;
        if (subject != null ? !subject.equals(pojo.subject) : pojo.subject != null) return false;
        if (url != null ? !url.equals(pojo.url) : pojo.url != null) return false;
        if (newOrder != null ? !newOrder.equals(pojo.newOrder) : pojo.newOrder != null) return false;
        if (createTime != null ? !createTime.equals(pojo.createTime) : pojo.createTime != null) return false;

        return true;
    }

    public int hashCode() {
        int result = 0;
        result = (category != null ? category.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (newOrder != null ? newOrder.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);

        return result;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer(getClass().getSimpleName());

        sb.append(" [");
        sb.append("newId").append("='").append(getNewId()).append("', ");
        sb.append("category").append("='").append(getCategory()).append("', ");
        sb.append("subject").append("='").append(getSubject()).append("', ");
        sb.append("url").append("='").append(getUrl()).append("', ");
        sb.append("newOrder").append("='").append(getNewOrder()).append("', ");
        sb.append("createTime").append("='").append(getCreateTime()).append("'");
        sb.append("]");
      
        return sb.toString();
    }

	@Override
	public AmNew mapRow(ResultSet rs, int arg1) throws SQLException {
		AmNew amNew = new AmNew();
		amNew.setCategory(rs.getString("category"));
		amNew.setCreateTime(rs.getDate("create_time"));
		amNew.setNewId(rs.getString("new_id"));
		amNew.setNewOrder(rs.getLong("new_order"));
		amNew.setSubject(rs.getString("subject"));
		amNew.setUrl(rs.getString("url"));
		return amNew;
	}

}
