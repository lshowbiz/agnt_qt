package com.joymain.ng.model;
// Generated 2008-3-20 16:23:50 by Hibernate Tools 3.1.0.beta4



/**
 * @struts.form include-all="true" extends="BaseForm"
 * @hibernate.class
 *         table="JSYS_ID"
 *     
 */

public class SysId extends com.joymain.ng.model.BaseObject implements java.io.Serializable {

    // Fields    

    private Long id;
    private String idName;
    private Long idValue;
    private String idCode;
    private Long dateFormat;
    private String dateValue;
    private String prefix;
    private String postfix;
    private Long idLength;
    private Long fixed;
    private Long version;
    private String infix;
    private String seqTable;

    // Constructors
    /**       
     *      *            @hibernate.property
     *             column="SEQ_TABLE"
     *             length="30"
     *             
     *         
     */
    public String getSeqTable() {
		return seqTable;
	}

	public void setSeqTable(String seqTable) {
		this.seqTable = seqTable;
	}

	/** default constructor */
    public SysId() {
    }

	/** minimal constructor */
    public SysId(String idName) {
        this.idName = idName;
    }
   
    // Property accessors
    /**
     *      *            @hibernate.id
     *             generator-class="sequence"
     *             type="java.lang.Long"
     *             column="ID"
     *@hibernate.generator-param name="sequence" value="SEQ_SYS"
     */
    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    /**       
     *      *            @hibernate.property
     *             column="ID_NAME"
     *             length="30"
     *             not-null="true"
     *         
     */

    public String getIdName() {
        return this.idName;
    }
    
    public void setIdName(String idName) {
        this.idName = idName;
    }
    /**       
     *      *            @hibernate.property
     *             column="ID_VALUE"
     *             length="12"
     *         
     */

    public Long getIdValue() {
        return this.idValue;
    }
    
    public void setIdValue(Long idValue) {
        this.idValue = idValue;
    }
    /**       
     *      *            @hibernate.property
     *             column="ID_CODE"
     *             length="30"
     *         
     */

    public String getIdCode() {
        return this.idCode;
    }
    
    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }
    /**       
     *      *            @hibernate.property
     *             column="DATE_FORMAT"
     *             length="12"
     *         
     */

    public Long getDateFormat() {
        return this.dateFormat;
    }
    
    public void setDateFormat(Long dateFormat) {
        this.dateFormat = dateFormat;
    }
    /**       
     *      *            @hibernate.property
     *             column="DATE_VALUE"
     *             length="10"
     *         
     */

    public String getDateValue() {
        return this.dateValue;
    }
    
    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }
    /**       
     *      *            @hibernate.property
     *             column="PREFIX"
     *             length="30"
     *         
     */

    public String getPrefix() {
        return this.prefix;
    }
    
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    /**       
     *      *            @hibernate.property
     *             column="POSTFIX"
     *             length="30"
     *         
     */

    public String getPostfix() {
        return this.postfix;
    }
    
    public void setPostfix(String postfix) {
        this.postfix = postfix;
    }
    /**       
     *      *            @hibernate.property
     *             column="ID_LENGTH"
     *             length="12"
     *         
     */

    public Long getIdLength() {
        return this.idLength;
    }
    
    public void setIdLength(Long idLength) {
        this.idLength = idLength;
    }
    /**       
     *      *            @hibernate.property
     *             column="FIXED"
     *             length="12"
     *         
     */

    public Long getFixed() {
        return this.fixed;
    }
    
    public void setFixed(Long fixed) {
        this.fixed = fixed;
    }
    /**       
     *      *            @hibernate.property
     *             column="VERSION"
     *             length="12"
     *         
     */

    public Long getVersion() {
        return this.version;
    }
    
    public void setVersion(Long version) {
        this.version = version;
    }
    /**       
     *      *            @hibernate.property
     *             column="INFIX"
     *             length="30"
     *         
     */

    public String getInfix() {
        return this.infix;
    }
    
    public void setInfix(String infix) {
        this.infix = infix;
    }
   

    /**
     * toString
     * @return String
     */
     public String toString() {
	  StringBuffer buffer = new StringBuffer();

      buffer.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
      buffer.append("idName").append("='").append(getIdName()).append("' ");			
      buffer.append("idValue").append("='").append(getIdValue()).append("' ");			
      buffer.append("idCode").append("='").append(getIdCode()).append("' ");			
      buffer.append("dateFormat").append("='").append(getDateFormat()).append("' ");			
      buffer.append("dateValue").append("='").append(getDateValue()).append("' ");			
      buffer.append("prefix").append("='").append(getPrefix()).append("' ");			
      buffer.append("postfix").append("='").append(getPostfix()).append("' ");			
      buffer.append("idLength").append("='").append(getIdLength()).append("' ");			
      buffer.append("fixed").append("='").append(getFixed()).append("' ");			
      buffer.append("version").append("='").append(getVersion()).append("' ");			
      buffer.append("infix").append("='").append(getInfix()).append("' ");			
      buffer.append("]");
      
      return buffer.toString();
     }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof SysId) ) return false;
		 SysId castOther = ( SysId ) other; 
         
		 return ( (this.getId()==castOther.getId()) || ( this.getId()!=null && castOther.getId()!=null && this.getId().equals(castOther.getId()) ) )
 && ( (this.getIdName()==castOther.getIdName()) || ( this.getIdName()!=null && castOther.getIdName()!=null && this.getIdName().equals(castOther.getIdName()) ) )
 && ( (this.getIdValue()==castOther.getIdValue()) || ( this.getIdValue()!=null && castOther.getIdValue()!=null && this.getIdValue().equals(castOther.getIdValue()) ) )
 && ( (this.getIdCode()==castOther.getIdCode()) || ( this.getIdCode()!=null && castOther.getIdCode()!=null && this.getIdCode().equals(castOther.getIdCode()) ) )
 && ( (this.getDateFormat()==castOther.getDateFormat()) || ( this.getDateFormat()!=null && castOther.getDateFormat()!=null && this.getDateFormat().equals(castOther.getDateFormat()) ) )
 && ( (this.getDateValue()==castOther.getDateValue()) || ( this.getDateValue()!=null && castOther.getDateValue()!=null && this.getDateValue().equals(castOther.getDateValue()) ) )
 && ( (this.getPrefix()==castOther.getPrefix()) || ( this.getPrefix()!=null && castOther.getPrefix()!=null && this.getPrefix().equals(castOther.getPrefix()) ) )
 && ( (this.getPostfix()==castOther.getPostfix()) || ( this.getPostfix()!=null && castOther.getPostfix()!=null && this.getPostfix().equals(castOther.getPostfix()) ) )
 && ( (this.getIdLength()==castOther.getIdLength()) || ( this.getIdLength()!=null && castOther.getIdLength()!=null && this.getIdLength().equals(castOther.getIdLength()) ) )
 && ( (this.getFixed()==castOther.getFixed()) || ( this.getFixed()!=null && castOther.getFixed()!=null && this.getFixed().equals(castOther.getFixed()) ) )
 && ( (this.getVersion()==castOther.getVersion()) || ( this.getVersion()!=null && castOther.getVersion()!=null && this.getVersion().equals(castOther.getVersion()) ) )
 && ( (this.getInfix()==castOther.getInfix()) || ( this.getInfix()!=null && castOther.getInfix()!=null && this.getInfix().equals(castOther.getInfix()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getId() == null ? 0 : this.getId().hashCode() );
         result = 37 * result + ( getIdName() == null ? 0 : this.getIdName().hashCode() );
         result = 37 * result + ( getIdValue() == null ? 0 : this.getIdValue().hashCode() );
         result = 37 * result + ( getIdCode() == null ? 0 : this.getIdCode().hashCode() );
         result = 37 * result + ( getDateFormat() == null ? 0 : this.getDateFormat().hashCode() );
         result = 37 * result + ( getDateValue() == null ? 0 : this.getDateValue().hashCode() );
         result = 37 * result + ( getPrefix() == null ? 0 : this.getPrefix().hashCode() );
         result = 37 * result + ( getPostfix() == null ? 0 : this.getPostfix().hashCode() );
         result = 37 * result + ( getIdLength() == null ? 0 : this.getIdLength().hashCode() );
         result = 37 * result + ( getFixed() == null ? 0 : this.getFixed().hashCode() );
         result = 37 * result + ( getVersion() == null ? 0 : this.getVersion().hashCode() );
         result = 37 * result + ( getInfix() == null ? 0 : this.getInfix().hashCode() );
         return result;
   }   





}
