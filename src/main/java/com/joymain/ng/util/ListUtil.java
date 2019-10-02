package com.joymain.ng.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.joymain.ng.Constants;





public class ListUtil {
	protected static JdbcTemplate jdbcTemplate;
	/**
	 * 根据键值获取对应的字符值<value, title>
	 * @param msgKey
	 * @return
	 */
	public static final LinkedHashMap<String, String> getListOptions(String companyCode, String listCode) {
		Set valueSets = Constants.sysListMap.get(listCode).entrySet();
		LinkedHashMap<String, String> optionMap=new LinkedHashMap<String, String>();
		if (valueSets != null) {
			Iterator iter = valueSets.iterator();
			while (iter.hasNext()) {
				Map.Entry entry=(Map.Entry)iter.next();
				String[] values = (String[])entry.getValue();

				if(StringUtils.contains(values[1],companyCode)){
					//如果当前用户所属公司在排除公司之内,则不显示
					continue;
				}else{
					optionMap.put(entry.getKey().toString(), values[0]);
				}
			}
		}
		
		return optionMap;
	}
	
	
	
	public static final String getListValue(String characterCode, String listCode, String listValue){
		
		
		
		String[] values = Constants.sysListMap.get(listCode).get(listValue);
		if (values != null){
			return LocaleUtil.getLocalText(characterCode, values[0]);
		}else{
			return null;
		}
	}
}