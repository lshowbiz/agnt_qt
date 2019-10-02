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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.search.annotations.DocumentId;

@Entity
@Table(name="inw_viewPeople")
@XmlRootElement
public class InwViewpeople  extends BaseObject implements Serializable {

    // Fields    

    private Long id;
    private String suggestionid;
    private String viewStatus;
    private String userCode;
    private Date viewTime;

    //viewStatus字段暂时不用,作扩展字段,INW_VIEWPEOPLE该表里面有关于相关建议的信息,则表明该建议已阅;
    // Constructors

    /** default constructor */
    public InwViewpeople() {
    }

	/** minimal constructor */
    public InwViewpeople(String suggestionid) {
        this.suggestionid = suggestionid;
    }
    
    /** full constructor */
    public InwViewpeople(String suggestionid, String viewStatus, String userCode, Date viewTime) {
        this.suggestionid = suggestionid;
        this.viewStatus = viewStatus;
        this.userCode = userCode;
        this.viewTime = viewTime;
    }
    

   
    public boolean equals(Object other) {
	         if ( (this == other ) ) return true;
			 if ( (other == null ) ) return false;
			 if ( !(other instanceof InwViewpeople) ) return false;
			 InwViewpeople castOther = ( InwViewpeople ) other; 
	         
			 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
	 && ( (this.getSuggestionid()==castOther.getSuggestionid()) || ( this.getSuggestionid()!=null && castOther.getSuggestionid()!=null && this.getSuggestionid().equals(castOther.getSuggestionid()) ) )
	 && ( (this.getViewStatus()==castOther.getViewStatus()) || ( this.getViewStatus()!=null && castOther.getViewStatus()!=null && this.getViewStatus().equals(castOther.getViewStatus()) ) )
	 && ( (this.getUserCode()==castOther.getUserCode()) || ( this.getUserCode()!=null && castOther.getUserCode()!=null && this.getUserCode().equals(castOther.getUserCode()) ) )
	 && ( (this.getViewTime()==castOther.getViewTime()) || ( this.getViewTime()!=null && castOther.getViewTime()!=null && this.getViewTime().equals(castOther.getViewTime()) ) );
	   }
    
    // Property accessors
    /**       
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *          @hibernate.generator-param name="sequence" value="SEQ_AM" 
     *         
     */
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_am")
	@SequenceGenerator(name = "seq_am", sequenceName = "SEQ_AM", allocationSize = 1)
    @Column(name="ID", unique=true, nullable=false, precision=22, scale=0) @DocumentId  
    public Long getId() {
        return this.id;
    }
    /**       
     *      *            @hibernate.property
     *             column="SUGGESTIONID"
     *             length="13"
     *             not-null="true"
     *         
     */

    @Column(name="SUGGESTIONID", length=13,nullable=false)
    public String getSuggestionid() {
        return this.suggestionid;
    }
    
    /**       
     *      *            @hibernate.property
     *             column="USER_CODE"
     *             length="20"
     *         
     */

    @Column(name="USER_CODE", length=20)
    public String getUserCode() {
        return this.userCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="VIEW_STATUS"
     *             length="2"
     *         
     */
    @Column(name="VIEW_STATUS", length=2)
    public String getViewStatus() {
        return this.viewStatus;
    }
    
    /**       
     *      *            @hibernate.property
     *             column="VIEW_TIME"
     *             length="7"
     *         
     */

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="VIEW_TIME", length=7)
    public Date getViewTime() {
        return this.viewTime;
    }
    public int hashCode() {
	         int result = 17;
	         
	         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
	         result = 37 * result + ( getSuggestionid() == null ? 0 : this.getSuggestionid().hashCode() );
	         result = 37 * result + ( getViewStatus() == null ? 0 : this.getViewStatus().hashCode() );
	         result = 37 * result + ( getUserCode() == null ? 0 : this.getUserCode().hashCode() );
	         result = 37 * result + ( getViewTime() == null ? 0 : this.getViewTime().hashCode() );
	         return result;
	   }
    
    public void setId(Long id) {
        this.id = id;
    }
    public void setSuggestionid(String suggestionid) {
        this.suggestionid = suggestionid;
    }
    
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
   

    public void setViewStatus(String viewStatus) {
        this.viewStatus = viewStatus;
    }


   public void setViewTime(Date viewTime) {
    this.viewTime = viewTime;
}
   
   /**
 * toString
 * @return String
 */
 public String toString() {
  StringBuffer buffer = new StringBuffer();

  buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
  buffer.append("suggestionid").append("='").append(getSuggestionid()).append("' ");			
  buffer.append("viewStatus").append("='").append(getViewStatus()).append("' ");			
  buffer.append("userCode").append("='").append(getUserCode()).append("' ");			
  buffer.append("viewTime").append("='").append(getViewTime()).append("' ");			
  buffer.append("]");
  
  return buffer.toString();
 }   



}
