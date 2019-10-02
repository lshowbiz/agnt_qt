package com.joymain.ng.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Properties;

public class PropertiesUtil {
	public static String getPropertie(String fileName, String propName) throws Exception {
		Properties props = getPropertie(fileName);
		return props.getProperty(propName);
	}

	public static Properties getPropertie(String fileName) throws Exception {
		String filePath = PropertiesUtil.class.getClassLoader().getResource(fileName).getPath();//.replaceAll("%20", " ");
		filePath= URLDecoder.decode(filePath, "utf-8");
		Properties props = new Properties();
		System.out.println(filePath);
		File file = new File(filePath);
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		props.load(in);
		return props;
	}
	
	
	public static String getPropertieByPayJump(){
		String path = "http://ddzf.jmtop.com/jecs/";
		try {
			path = getPropertie("packaged/BaseDeploy.properties","payJump");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;
	}
	
	
	public static void main(String[] args) {
		try {
			System.out.println(getPropertie("packaged/BaseDeploy.properties","payJump"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
