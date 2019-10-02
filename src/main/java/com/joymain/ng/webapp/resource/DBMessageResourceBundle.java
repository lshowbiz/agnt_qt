package com.joymain.ng.webapp.resource;

import java.text.MessageFormat;
import java.util.Locale;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.HierarchicalMessageSource;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.context.NoSuchMessageException;
import org.springframework.util.ClassUtils;

import com.joymain.ng.util.LocaleUtil;


public class DBMessageResourceBundle implements HierarchicalMessageSource,
		BeanClassLoaderAware {

	private Locale locale;
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public String getMessage(String code, Object[] paramArrayOfObject,
			String defaultMessage, Locale paramLocale) {
		// TODO Auto-generated method stub
		String result = com.joymain.ng.util.LocaleUtil.getLocalText(
				paramLocale.toString(), code, defaultMessage,
				paramArrayOfObject);

		return result;
	}

	@Override
	public String getMessage(String paramString, Object[] paramArrayOfObject,
			Locale paramLocale) throws NoSuchMessageException {
		// TODO Auto-generated method stub
		return LocaleUtil.getLocalText(paramLocale.toString(), paramString, "", paramArrayOfObject);
		
	}

	@Override
	public String getMessage(
			MessageSourceResolvable paramMessageSourceResolvable,
			Locale paramLocale) throws NoSuchMessageException {
		// TODO Auto-generated method stub
		String[] codes = paramMessageSourceResolvable.getCodes();
		if(codes == null){
			codes = new String[0];
		}
		for (int i = 0; i < codes.length; i++) {
			String msg =  LocaleUtil.getLocalText(codes[i], paramMessageSourceResolvable.getArguments());
			if (msg != null) {
				return msg;
			}
		}
		if (paramMessageSourceResolvable.getDefaultMessage() != null) {
			return LocaleUtil.getLocalText(paramMessageSourceResolvable.getDefaultMessage(), paramMessageSourceResolvable.getArguments());
		}
		
		if (codes.length > 0) {
			return null;
		}
		throw new NoSuchMessageException(codes.length > 0 ? codes[codes.length - 1] : null, locale);
	}

	private String[] basenames = new String[0];

	private ClassLoader bundleClassLoader;

	private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();
	
	@Override
	public void setBeanClassLoader(ClassLoader classLoader) {
		// TODO Auto-generated method stub
		this.bundleClassLoader = classLoader;
	}

	private MessageSource parentMessageSource;
	@Override
	public void setParentMessageSource(MessageSource paramMessageSource) {
		// TODO Auto-generated method stub
		this.parentMessageSource=paramMessageSource;
	}

	@Override
	public MessageSource getParentMessageSource() {
		// TODO Auto-generated method stub
		return parentMessageSource;
	}

}
