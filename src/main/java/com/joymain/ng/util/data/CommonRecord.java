package com.joymain.ng.util.data;

import java.sql.Types;

import org.apache.commons.lang.StringUtils;

import com.joymain.ng.util.AppException;

public class CommonRecord extends java.util.ArrayList{
	/**
	 * 构造函数
	 */
	public CommonRecord() {
	}


	/**
	 * 增加一个字段，如果该字段已经存在，则将覆盖原值
	 * @param field
	 */
    public void addField(CustomField field) {
        String fieldName = field.getName();
        int index = this.indexOfKey(fieldName);
        if (index == -1){
            this.add(field);
        }else{
            this.set(index,field);
        }
    }

	/**
	 * 增加一个字段，如果该字段已经存在，则将覆盖原值
	 * @param fieldName 字段名
	 * @param value 值
	 */
	public void addField(String fieldName, String value) {
		this.addField(new CustomField(fieldName, value));
	}

	/**
	 * 删除一个字段
	 * @param index 字段索引
	 */
	public void removeField(int index) {
		this.remove(index);
	}

	/**
	 * 删除一个字段
	 * @param fieldName 字段名
	 */
	public void removeField(String fieldName) {
		this.remove(fieldName);
	}

	/**
	 * 返回所有字段
	 * @return 数组3
	 */
	public CustomField[] getFields() {
		int i = 0, count = this.size();
		CustomField[] ret = new CustomField[count];
		for (i = 0; i < count; i++) {
			ret[i] = (CustomField) this.get(i);
		}
		return ret;
	}

	/**
	 * 取值
	 * @param index 索引
	 * @return 对象
	 * @throws Exception
	 */
	public Object getValue(int index) throws Exception {
		try {
			return this.getField(index).getValue();
		}
		catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 取值
	 * @param fieldName  字段名
	 * @return 对象
	 * @throws Exception
	 */
	public Object getValue(String fieldName) throws Exception {
		try {
			return this.getField(fieldName).getValue();
		}
		catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 返回字符串值
	 * @param fieldName 字段名
	 * @return 字符串
	 * @throws Exception
	 */
	public String getString(String fieldName) throws AppException {
		try {
			return this.getField(fieldName).getString();
		}
		catch (Exception e) {
			System.out.println("字段不存在：" + fieldName);
			throw new AppException(e);
		}
	}

	/**
	 * 返回字符串值。如果值为null或是长度为零的字符串，则返回instead
	 * @param fieldName 字段名
	 * @param instead 缺省值
	 * @return 字符串
	 */
	public String getString(String fieldName, String instead) {
		try {
			if (this.indexOfKey(fieldName) != -1) {
				String tmpFieldValue=this.getField(fieldName).getString();
				if(StringUtils.isEmpty(tmpFieldValue)){
					return instead;
				}
				return tmpFieldValue;
			}else {
				return instead;
			}
		}catch (Exception e) {
			return instead;
		}
	}


	/**
	 * 返回字符串值。如果值为null或是长度为零的字符串，则返回instead
	 * @param index 索引
	 * @param instead 缺省值
	 * @return 字符串
	 */
	public String getString(int index, String instead) {
		try {
			return this.getField(index).getString();
		}
		catch (Exception e) {
			return instead;
		}
	}

	/**
	 * 包含子定名称的字段位置，不存在时 返回 -1
	 * @param fieldName 字段名 忽略大小写
	 * @return int
	 */
	public int indexOfKey(String fieldName) {
			int fieldIndex = -1;
			for (int i = 0; i < this.size(); i++) {
				CustomField tempField = this.getField(i);
                if (tempField.getName().equalsIgnoreCase(fieldName)) {
					fieldIndex = i;
                    break;
                }
			}
			return fieldIndex;
	}

	/**
	 * 修改值。如果制定的字段不存在，则增加该字段
	 * @param fieldName 字段名
	 * @param obj 值
	 * @throws Exception
	 */
	public void setValue(String fieldName, Object obj) throws Exception {
		try {
			if (this.indexOfKey(fieldName) != -1) {
				this.getField(fieldName).setValue(obj);
			}else {
				this.addField(new CustomField(fieldName, Types.VARCHAR, obj));
			}
		}
		catch (Exception e) {
			this.addField(new CustomField(fieldName, Types.JAVA_OBJECT, obj));
			//throw new Exception(e);
		}
	}

	/**
	 *
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public String getString(int index) throws Exception {
		try {
			return this.getField(index).getString();
		}
		catch (Exception e) {
			throw new Exception(e);
		}
	}

	/**
	 * 超出 index 异常
	 * @param index
	 * @return Field
	 */
	public CustomField getField(int index) {
		return  (CustomField) this.get(index);
	}

	/**
	 * 没有fieldName 异常
	 * @param fieldName
	 * @return Field
	 */
	public CustomField getField(String fieldName) {
        int index = this.indexOfKey(fieldName);
        return  (CustomField) this.get(index);
	}

	/**
	 *
	 * @param fieldName
	 * @param f
	 */
	public void setField(String fieldName, CustomField f) {
        int index = this.indexOfKey(fieldName);
        this.set(index, f);
	}

	/**
	 *
	 * @param index
	 * @param f
	 */
	public void setField(int index, CustomField f) {
		this.set(index, f);
	}

	/**
	 * 在末尾加入一个CommonRecord对象的所有字段
	 * @param rec CommonRecord对象
	 * @return CommonRecord对象
	 */
	public CommonRecord append(CommonRecord rec) {
		int i = 0, count = rec.size();
		for (i = 0; i < count; i++) {
			this.addField(rec.getField(i));
		}
		return this;
	}



	/**
	 * 取整数值（四舍五入）
	 * @param index
	 * @return
	 * @throws Exception 如果字段不存在或该字段的值为非数值，抛出异常，如果字段值是null值或空串，则返回0
	 */
	public int getIntValue(int index) throws Exception {
		try {
			return this.getField(index).getIntValue();
		}catch (Exception e) {
			throw new Exception("取整数值错误", e);
		}
	}

	/**
	 * 取整数值（四舍五入）
	 * @param key
	 * @return
	 * @throws Exception 如果字段不存在或该字段的值为非数值，抛出异常，如果字段值是null值或空串，则返回0
	 */
	public int getIntValue(String key) throws Exception {
		try {
			return this.getField(key).getIntValue();
		}catch (Exception e) {
			throw new Exception("取整数值错误", e);
		}
	}

	/**
	 * 取浮点数值
	 * @param index
	 * @return
	 * @throws Exception 如果字段不存在或该字段的值为非数值，抛出异常，如果字段值是null值或空串，则返回0
	 */
	public double getDoubleValue(int index) throws Exception {
		try {
			return this.getField(index).getDoubleValue();
		}catch (Exception e) {
			throw new Exception("取浮点值错误", e);
		}
	}

	/**
	 * 取浮点数值
	 * @param key
	 * @return
	 * @throws Exception 如果字段不存在或该字段的值为非数值，抛出异常，如果字段值是null值或空串，则返回0
	 */
	public double getDoubleValue(String key) throws Exception {
		try {
			return this.getField(key).getDoubleValue();
		}catch (Exception e) {
			throw new Exception("取浮点值错误", e);
		}
	}
}
