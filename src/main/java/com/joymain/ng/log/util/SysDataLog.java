package com.joymain.ng.log.util;
// Generated 2008-3-25 15:29:16 by Hibernate Tools 3.1.0.beta4

import java.sql.Timestamp;

import com.joymain.ng.model.BaseObject;



public class SysDataLog extends BaseObject implements java.io.Serializable {


    // Fields    

    private Long logId;
    private String changeType;
    private String tableName;
    private String columnName;
    private String pidName;
    private String pidValue;
    private String beforeChange;
    private String afterChange;
    private String personType;
    private String operatorPerson;
    private Timestamp operatorTime;
    private String moduleName;
    private String hostName;
    private String ipAddress;
    private Long operationLogId;

    
    private String bid;
    private String bidValue;
    
    // Constructors

  


	/** default constructor */
    public SysDataLog() {
    }

    
    /** full constructor */
    public SysDataLog(String changeType, String tableName, String columnName, String pidName, String pidValue, String beforeChange, String afterChange, String operatorPerson, Timestamp operatorTime, String moduleName, String hostName, String ipAddress) {
        this.changeType = changeType;
        this.tableName = tableName;
        this.columnName = columnName;
        this.pidName = pidName;
        this.pidValue = pidValue;
        this.beforeChange = beforeChange;
        this.afterChange = afterChange;
        this.operatorPerson = operatorPerson;
        this.operatorTime = operatorTime;
        this.moduleName = moduleName;
        this.hostName = hostName;
        this.ipAddress = ipAddress;
    }
    

   
    public String getBid() {
		return bid;
	}


	public void setBid(String bid) {
		this.bid = bid;
	}


	public String getBidValue() {
		return bidValue;
	}


	public void setBidValue(String bidValue) {
		this.bidValue = bidValue;
	}




    public Long getLogId() {
        return this.logId;
    }
    
    public void setLogId(Long logId) {
        this.logId = logId;
    }
     

    public String getChangeType() {
        return this.changeType;
    }
    
    public void setChangeType(String changeType) {
        this.changeType = changeType;
    }
    

    public String getTableName() {
        return this.tableName;
    }
    
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
    

    public String getColumnName() {
        return this.columnName;
    }
    
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    

    public String getPidName() {
        return this.pidName;
    }
    
    public void setPidName(String pidName) {
        this.pidName = pidName;
    }
   

    public String getPidValue() {
        return this.pidValue;
    }
    
    public void setPidValue(String pidValue) {
        this.pidValue = pidValue;
    }
     

    public String getBeforeChange() {
        return this.beforeChange;
    }
    
    public void setBeforeChange(String beforeChange) {
        this.beforeChange = beforeChange;
    }
   

    public String getAfterChange() {
        return this.afterChange;
    }
    
    
    public void setAfterChange(String afterChange) {
        this.afterChange = afterChange;
    }
    
    
    public String getPersonType() {
    	return personType;
    }


	public void setPersonType(String personType) {
    	this.personType = personType;
    }

 

    public String getOperatorPerson() {
        return this.operatorPerson;
    }
    
    public void setOperatorPerson(String operatorPerson) {
        this.operatorPerson = operatorPerson;
    }
     

    public Timestamp getOperatorTime() {
        return this.operatorTime;
    }
    
    public void setOperatorTime(Timestamp operatorTime) {
        this.operatorTime = operatorTime;
    }
    

    public String getModuleName() {
        return this.moduleName;
    }
    
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
     

    public String getHostName() {
        return this.hostName;
    }
    
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
     

    public String getIpAddress() {
        return this.ipAddress;
    }
    
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
   
    
    public Long getOperationLogId() {
    	return operationLogId;
    }


	public void setOperationLogId(Long operationLogId) {
    	this.operationLogId = operationLogId;
    }


	/**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("changeType").append("='").append(getChangeType()).append("' ");			
      buffer.append("tableName").append("='").append(getTableName()).append("' ");			
      buffer.append("columnName").append("='").append(getColumnName()).append("' ");			
      buffer.append("pidName").append("='").append(getPidName()).append("' ");			
      buffer.append("pidValue").append("='").append(getPidValue()).append("' ");			
      buffer.append("beforeChange").append("='").append(getBeforeChange()).append("' ");			
      buffer.append("afterChange").append("='").append(getAfterChange()).append("' ");			
      buffer.append("operatorPerson").append("='").append(getOperatorPerson()).append("' ");			
      buffer.append("operatorTime").append("='").append(getOperatorTime()).append("' ");			
      buffer.append("moduleName").append("='").append(getModuleName()).append("' ");			
      buffer.append("hostName").append("='").append(getHostName()).append("' ");			
      buffer.append("ipAddress").append("='").append(getIpAddress()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysDataLog) ) return false;
		 SysDataLog castOther = ( SysDataLog ) other; 
         
		 return ( (this.getLogId()==castOther.getLogId()) || ( this.getLogId()!=null && castOther.getLogId()!=null && this.getLogId().equals(castOther.getLogId()) ) )
 && ( (this.getChangeType()==castOther.getChangeType()) || ( this.getChangeType()!=null && castOther.getChangeType()!=null && this.getChangeType().equals(castOther.getChangeType()) ) )
 && ( (this.getTableName()==castOther.getTableName()) || ( this.getTableName()!=null && castOther.getTableName()!=null && this.getTableName().equals(castOther.getTableName()) ) )
 && ( (this.getColumnName()==castOther.getColumnName()) || ( this.getColumnName()!=null && castOther.getColumnName()!=null && this.getColumnName().equals(castOther.getColumnName()) ) )
 && ( (this.getPidName()==castOther.getPidName()) || ( this.getPidName()!=null && castOther.getPidName()!=null && this.getPidName().equals(castOther.getPidName()) ) )
 && ( (this.getPidValue()==castOther.getPidValue()) || ( this.getPidValue()!=null && castOther.getPidValue()!=null && this.getPidValue().equals(castOther.getPidValue()) ) )
 && ( (this.getBeforeChange()==castOther.getBeforeChange()) || ( this.getBeforeChange()!=null && castOther.getBeforeChange()!=null && this.getBeforeChange().equals(castOther.getBeforeChange()) ) )
 && ( (this.getAfterChange()==castOther.getAfterChange()) || ( this.getAfterChange()!=null && castOther.getAfterChange()!=null && this.getAfterChange().equals(castOther.getAfterChange()) ) )
 && ( (this.getOperatorPerson()==castOther.getOperatorPerson()) || ( this.getOperatorPerson()!=null && castOther.getOperatorPerson()!=null && this.getOperatorPerson().equals(castOther.getOperatorPerson()) ) )
 && ( (this.getOperatorTime()==castOther.getOperatorTime()) || ( this.getOperatorTime()!=null && castOther.getOperatorTime()!=null && this.getOperatorTime().equals(castOther.getOperatorTime()) ) )
 && ( (this.getModuleName()==castOther.getModuleName()) || ( this.getModuleName()!=null && castOther.getModuleName()!=null && this.getModuleName().equals(castOther.getModuleName()) ) )
 && ( (this.getHostName()==castOther.getHostName()) || ( this.getHostName()!=null && castOther.getHostName()!=null && this.getHostName().equals(castOther.getHostName()) ) )
 && ( (this.getIpAddress()==castOther.getIpAddress()) || ( this.getIpAddress()!=null && castOther.getIpAddress()!=null && this.getIpAddress().equals(castOther.getIpAddress()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getLogId() == null ? 0 : this.getLogId().hashCode() );
         result = 37 * result + ( getChangeType() == null ? 0 : this.getChangeType().hashCode() );
         result = 37 * result + ( getTableName() == null ? 0 : this.getTableName().hashCode() );
         result = 37 * result + ( getColumnName() == null ? 0 : this.getColumnName().hashCode() );
         result = 37 * result + ( getPidName() == null ? 0 : this.getPidName().hashCode() );
         result = 37 * result + ( getPidValue() == null ? 0 : this.getPidValue().hashCode() );
         result = 37 * result + ( getBeforeChange() == null ? 0 : this.getBeforeChange().hashCode() );
         result = 37 * result + ( getAfterChange() == null ? 0 : this.getAfterChange().hashCode() );
         result = 37 * result + ( getOperatorPerson() == null ? 0 : this.getOperatorPerson().hashCode() );
         result = 37 * result + ( getOperatorTime() == null ? 0 : this.getOperatorTime().hashCode() );
         result = 37 * result + ( getModuleName() == null ? 0 : this.getModuleName().hashCode() );
         result = 37 * result + ( getHostName() == null ? 0 : this.getHostName().hashCode() );
         result = 37 * result + ( getIpAddress() == null ? 0 : this.getIpAddress().hashCode() );
         return result;
   }   





}
