package com.joymain.ng.util.data;

import java.sql.Types;

import org.apache.commons.lang.StringUtils;




/**
 * �ֶΰ�װ�� �ֶ���Ʋ���ִ�Сд
 * һ�����ڰ�װ��ݿ��ĳ���ֶΣ�ͬCommonRecord��һ��ʹ��
 * User: Administrator
 */
public class CustomField implements java.io.Serializable {
    protected String name = null;//��ƣ�����ִ�Сд
    protected int	   type = 0 ;
    protected Object value = null;

    public CustomField() {
    }

    /**
     *
     * @param name
     * @param type
     * @param value
     */
    public CustomField(String name, int type, Object value) {
        this.name = name.toLowerCase();
        this.type = type;
        this.value = value;
    }

    /**
     *
     * @param name
     * @param value
     */
    public CustomField(String name, Object value) {
        this(name, Types.VARCHAR, value) ;
    }

    /**
     *
     * @param field
     */
    public CustomField(CustomField field) {
        name = field.getName().toLowerCase() ;
        type = field.getType() ;
        value = field.getValue() ;
    }

    /**
     * �ֶ������ͣ�ֵ��һ��map�ṹ�ַ�
     * @return name + type + value
     */
    public String toString() {
        String str = "";
        str += "name=" + getName();
        str += "\ttype=" + getTypeName();
        str += "\tvalue=" + getValue();
        return str;
    }
    /**
     * ȡ�øö����Stringֵ , null ���� ""
     * @return value -> String
     *
     */
    public String getString() throws Exception {
        String reVal ="";
        if (this.value !=null)
            reVal =this.value.toString();
        return reVal ;
    }
    /**
     * ���Ϊnull��Ϊ�մ�������0,����������Ϊ����ֵ�ͣ����
     * @return doubleֵ��
     *
     */
    public double getDoubleValue() throws Exception {
        String str ="0.0";
        if(this.value != null){
            str = (String) this.value;
            if (!StringUtils.isNumeric(str)) {
                throw new Exception(name +
                        ":��Field��ȡdoubleֵ����fieldΪ����ֵ");
            }
        }
        return Double.parseDouble(str);
    }

    /**
     * ȡ�øö����intֵ(��������)
     * @return intֵ������������Ϊ����ֵ�ͣ�����0
     *
     */
    public int getIntValue() throws Exception {
        double tmp = this.getDoubleValue();
        return (int) java.lang.Math.round(tmp);
    }

    /**
     * �ֶ����
     * @return �ֶ����
     */
    public String getName() {
        return name;
    }



    /**
     * �ֶ�����
     * @return �ֶ�����
     */
    public int getType() {
        return type;
    }

    /**
     * �ֶ�����
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * ȡֵ����
     * @return ֵ����
     */
    public Object getValue() {
        return value;
    }

    /**
     * ����ֵ����
     * @param value
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Get data type name
     */
    public String getTypeName(){
        String typeName = "Unknown Type " + type;
        switch(type) {
            case Types.ARRAY:
                typeName = "ARRAY";
                break;
            case Types.BIGINT:
                typeName = "BIGINT";
                break;
            case Types.BINARY:
                typeName = "BINARY";
                break;
            case Types.BIT:
                typeName = "BIT";
                break;
            case Types.BLOB:
                typeName = "BLOB";
                break;
            case Types.CHAR:
                typeName = "CHAR";
                break;
            case Types.CLOB:
                typeName = "CLOB";
                break;
            case Types.DATE:
                typeName = "DATE";
                break;
            case Types.DECIMAL:
                typeName = "DECIMAL";
                break;
            case Types.DISTINCT:
                typeName = "DISTINCT";
                break;
            case Types.DOUBLE:
                typeName = "DOUBLE";
                break;
            case Types.FLOAT:
                typeName = "FLOAT";
                break;
            case Types.INTEGER:
                typeName = "INTEGER";
                break;
            case Types.JAVA_OBJECT:
                typeName = "JAVA_OBJECT";
                break;
            case Types.LONGVARBINARY:
                typeName = "LONGVARBINARY";
                break;
            case Types.LONGVARCHAR:
                typeName = "LONGVARCHAR";
                break;
            case Types.NULL:
                typeName = "NULL";
                break;
            case Types.NUMERIC:
                typeName = "NUMERIC";
                break;
            case Types.OTHER:
                typeName = "OTHER";
                break;
            case Types.REAL:
                typeName = "REAL";
                break;
            case Types.REF:
                typeName = "REF";
                break;
            case Types.SMALLINT:
                typeName = "SMALLINT";
                break;
            case Types.STRUCT:
                typeName = "STRUCT";
                break;
            case Types.TIME:
                typeName = "TIME";
                break;
            case Types.TIMESTAMP:
                typeName = "TIMESTAMP";
                break;
            case Types.TINYINT:
                typeName = "TINYINT";
                break;
            case Types.VARBINARY:
                typeName = "VARBINARY";
                break;
            case Types.VARCHAR:
                typeName = "VARCHAR";
                break;
        }
        return typeName;
    }// end of getTypeName()

    public static void main(String[] argv) {
        CustomField f = new CustomField("f1","123.54");
    }

}// end of class
