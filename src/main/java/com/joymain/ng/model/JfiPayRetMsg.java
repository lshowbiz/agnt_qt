package com.joymain.ng.model;

import java.io.Serializable;
import java.util.Map;

/**
 * 支付公司处理类返回
 * @author jfoy
 *
 */
public class JfiPayRetMsg extends BaseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2908394477810598410L;

	private String url;
	
	private Map<String,String> dataMap;
	
private String xmlStr;
	
	private boolean isHxPay;
	
	public boolean getIsHxPay() {
		return isHxPay;
	}

	public void setIsHxPay(boolean isHxPay) {
		this.isHxPay = isHxPay;
	}

	public String getXmlStr() {
		return xmlStr;
	}

	public void setXmlStr(String xmlStr) {
		this.xmlStr = xmlStr;
	}
	
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map dataMap) {
		this.dataMap = dataMap;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 0;
		result = prime * result + ((dataMap == null) ? 0 : dataMap.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		JfiPayRetMsg other = (JfiPayRetMsg) obj;
		if (dataMap == null) {
			if (other.dataMap != null)
				return false;
		} else if (!dataMap.equals(other.dataMap))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "JfiPayRetMsg [dataMap=" + dataMap + ", url=" + url + "]";
	}


}
