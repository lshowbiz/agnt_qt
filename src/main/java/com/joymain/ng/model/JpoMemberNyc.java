package com.joymain.ng.model;
// Generated 2016-8-30 9:55:20 by Hibernate Tools 3.1.0.beta4

import java.sql.Timestamp;
import java.util.Date;




public class JpoMemberNyc implements   java.io.Serializable {

    public static final String STATUS_LOCK="1";
    public static final String STATUS_UNLOCK="0";

    // Fields    

    private Long id;
    private String yearOfMonth;
    private String userCode;
    private Date pushAt;

    private String status;

    // Constructors

    /** default constructor */
    public JpoMemberNyc() {
    }

    
    /** full constructor */
    public JpoMemberNyc(String yearOfMonth, String memberNo, Timestamp pushAt, String status) {
        this.yearOfMonth = yearOfMonth;
        this.userCode = memberNo;
        this.pushAt = pushAt;

        this.status = status;
    }


   
    // Property accessors


    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }


    public String getYearOfMonth() {
        return this.yearOfMonth;
    }

    public void setYearOfMonth(String yearOfMonth) {
        this.yearOfMonth = yearOfMonth;
    }


    public String setUserCode() {
        return this.userCode;
    }

    public void setUserCode(String memberNo) {
        this.userCode = memberNo;
    }


    public Date getPushAt() {
        return this.pushAt;
    }

    public void setPushAt(Date pushAt) {
        this.pushAt = pushAt;
    }





    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }





     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("yearOfMonth").append("='").append(getYearOfMonth()).append("' ");			
      buffer.append("memberNo").append("='").append(setUserCode()).append("' ");
      buffer.append("pushAt").append("='").append(getPushAt()).append("' ");			

      buffer.append("status").append("='").append(getStatus()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof JpoMemberNyc) ) return false;
		 JpoMemberNyc castOther = ( JpoMemberNyc ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getYearOfMonth()==castOther.getYearOfMonth()) || ( this.getYearOfMonth()!=null && castOther.getYearOfMonth()!=null && this.getYearOfMonth().equals(castOther.getYearOfMonth()) ) )
 && ( (this.setUserCode()==castOther.setUserCode()) || ( this.setUserCode()!=null && castOther.setUserCode()!=null && this.setUserCode().equals(castOther.setUserCode()) ) )
 && ( (this.getPushAt()==castOther.getPushAt()) || ( this.getPushAt()!=null && castOther.getPushAt()!=null && this.getPushAt().equals(castOther.getPushAt()) ) )
 && ( (this.getStatus()==castOther.getStatus()) || ( this.getStatus()!=null && castOther.getStatus()!=null && this.getStatus().equals(castOther.getStatus()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getYearOfMonth() == null ? 0 : this.getYearOfMonth().hashCode() );
         result = 37 * result + ( setUserCode() == null ? 0 : this.setUserCode().hashCode() );
         result = 37 * result + ( getPushAt() == null ? 0 : this.getPushAt().hashCode() );
         result = 37 * result + ( getStatus() == null ? 0 : this.getStatus().hashCode() );
         return result;
   }   





}
