package com.joymain.ng.util;

import java.text.MessageFormat;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.joymain.ng.Constants;






public class LocaleUtil {
	protected static JdbcTemplate jdbcTemplate;
	/**
	 * 根据键值获取对应的字符值
	 * @param msgKey
	 * @return
	 */
	public static final String getLocalText(String msgKey){
//		String characterCoding=((JsysUser) ContextUtil.getResource("sessionLogin")).getDefCharacterCoding();
		String characterCoding = (String) ContextUtil.getResource("locale");
		return getLocalText(characterCoding, msgKey, msgKey);
	}
	
	/**
	 * 根据键值及语言编码获取对应的字符值
	 * @param msgKey
	 * @return
	 */
	public static final String getLocalText(String characterCoding, String msgKey){
		return getLocalText(characterCoding, msgKey, msgKey);
	}
	
	/**
	 * 根据键值获取对应的字符值,如果没找到则返回所指定的缺省的值
	 * @param characterCoding
	 * @param msgKey
	 * @param defaultMessage
	 * @return
	 */
	public static final String getLocalText(String characterCoding, String msgKey, String defaultMessage){
		try {
			if (!StringUtils.isEmpty(msgKey)) {
//				
				
				String characterValue=(String) Constants.localLanguageMap.get(characterCoding).get(msgKey);
				if(!StringUtils.isEmpty(characterValue)){
					return characterValue;
				}else{
					return defaultMessage;
				}
			}else{
				return defaultMessage;
			}	
		} catch (Exception e) {
			return defaultMessage;			
		}
	}
	
	/**
	 * 根据键值获取对应的字符值,如果没找到则返回所指定的缺省的值
	 * @param characterCoding
	 * @param msgKey
	 * @param defaultMessage
	 * @return
	 */
	public static final String getLocalText(String characterCoding, String msgKey, String defaultMessage,Object[] args){
		try {
			if (!StringUtils.isEmpty(msgKey)) {
				
				
				String characterValue=(String) Constants.localLanguageMap.get(characterCoding).get(msgKey);
				MessageFormat formatter = new MessageFormat(characterValue);
				characterValue=formatter.format(args);
				if(!StringUtils.isEmpty(characterValue)){
					return characterValue;
				}else{
					return defaultMessage;
				}
			}else{
				return defaultMessage;
			}	
		} catch (Exception e) {
			return defaultMessage;			
		}
	}
	
	/**
	 * 将Object[]里各个值逐个填入msgKey对应的语言值内
	 * @param msgKey
	 * @param args
	 * @return
	 */
	public static final String getLocalText(String msgKey, Object[] args){
		String messagePattern=getLocalText(msgKey);
		MessageFormat formatter = new MessageFormat(messagePattern);
		return formatter.format(args);
	}
}