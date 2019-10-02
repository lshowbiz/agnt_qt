package com.joymain.ng.util;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.joymain.ng.Constants;





public class ConfigUtil {
	private static Logger log = LoggerFactory.getLogger(ConfigUtil.class);

	/**
	 * 根据键值获取对应的字符值
	 * @param msgKey
	 * @return
	 */
	public static final String getConfigValue(String companyCode, String configKey) {
		try {
			return (String) Constants.sysConfigMap.get(companyCode).get(configKey);
		} catch (Exception e) {
			log.error("Error(companyCode:"+companyCode+" configKey:"+configKey+"): ", e);
			return null;
		}
	}
}
