package com.joymain.ng.webapp.listener;

import com.google.common.collect.Maps;


import com.joymain.ng.Constants;
import com.joymain.ng.model.JalCharacterCoding;
import com.joymain.ng.model.JalCompany;
import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.service.JalCharacterCodingManager;
import com.joymain.ng.service.JalCharacterValueManager;
import com.joymain.ng.service.JalCompanyManager;
import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.service.JsysConfigValueManager;
import com.joymain.ng.service.JsysListKeyManager;
import com.joymain.ng.service.JsysResourceManager;
import com.joymain.ng.service.LookupManager;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.joymain.ng.service.GenericManager;
import com.joymain.ng.util.ContextUtil;
import com.joymain.ng.util.WeekFormatUtil;

/**
 * <p>
 * StartupListener class used to initialize and database settings and populate
 * any application-wide drop-downs.
 * <p/>
 * <p>
 * Keep in mind that this listener is executed outside of
 * OpenSessionInViewFilter, so if you're using Hibernate you'll have to
 * explicitly initialize all loaded data at the GenericDao or service level to
 * avoid LazyInitializationException. Hibernate.initialize() works well for
 * doing this.
 * 
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 */
public class StartupListener implements ServletContextListener {
	private static final Logger log = LoggerFactory
			.getLogger(ServletContextListener.class);

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public void contextInitialized(ServletContextEvent event) {
		log.debug("Initializing context...");

		ServletContext context = event.getServletContext();

		// Orion starts Servlets before Listeners, so check if the config
		// object already exists
		Map<String, Object> config = (HashMap<String, Object>) context
				.getAttribute(Constants.CONFIG);

		if (config == null) {
			config = new HashMap<String, Object>();
		}
		//初始化ContextUtil
		if (ContextUtil.getContext() == null) {
			ContextUtil c = new ContextUtil();
			c.contextInitialized(event);
		}
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(context);

		PasswordEncoder passwordEncoder = null;
		try {
			ProviderManager provider = (ProviderManager) ctx
					.getBean("org.springframework.security.authentication.ProviderManager#0");
			for (Object o : provider.getProviders()) {
				AuthenticationProvider p = (AuthenticationProvider) o;
				if (p instanceof RememberMeAuthenticationProvider) {
					config.put("rememberMeEnabled", Boolean.TRUE);
				} else if (ctx.getBean("passwordEncoder") != null) {
					passwordEncoder = (PasswordEncoder) ctx
							.getBean("passwordEncoder");
				}
			}
		} catch (NoSuchBeanDefinitionException n) {
			log.debug("authenticationManager bean not found, assuming test and ignoring...");
			// ignore, should only happen when testing
		}

		context.setAttribute(Constants.CONFIG, config);

		// output the retrieved values for the Init and Context Parameters
		if (log.isDebugEnabled()) {
			log.debug("Remember Me Enabled? " + config.get("rememberMeEnabled"));
			if (passwordEncoder != null) {
				log.debug("Password Encoder: "
						+ passwordEncoder.getClass().getSimpleName());
			}
			log.debug("Populating drop-downs...");
		}

		setupContext(context);
	}

	/**
	 * This method uses the LookupManager to lookup available roles from the
	 * data layer.
	 * 
	 * @param context
	 *            The servlet context
	 */
	public static void setupContext(ServletContext context) {
		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(context);
//		LookupManager mgr = (LookupManager) ctx.getBean("lookupManager");

		// get list of possible roles
//		context.setAttribute(Constants.AVAILABLE_ROLES, mgr.getAllRoles());
		log.debug("Drop-down initialization complete [OK]");

		// Any manager extending GenericManager will do:
		// GenericManager manager = (GenericManager) ctx.getBean("userManager");
		// doReindexing(manager);
		log.debug("Full text search reindexing complete [OK]");
		Constants.context=context;
		loadResourceMap(ctx, true);
		loadLanguage(ctx, true);
		
		loadSystemConfig(ctx, true);
		loadSystemList(ctx, true);
		loadBdPeriod(ctx);
	}

	private static void loadSystemList(ApplicationContext ctx, boolean reload) {
		// TODO Auto-generated method stub

		if (Constants.isLoad() && !reload) {
			return;
		}
		Map<String, Map<String, String[]>> sysListTmpMap = new HashMap<String, Map<String, String[]>>();
		// Constants.sysListMap = new HashMap<String, Map<String, String[]>>();
		JsysListKeyManager jsysListKeyManager = (JsysListKeyManager) ctx
				.getBean("jsysListKeyManager");

		List sysListKeys = jsysListKeyManager.getSysListBySQL();

		for (int i = 0; i < sysListKeys.size(); i++) {
			Map sysListKeyMap = (HashMap) sysListKeys.get(i);
			String listCode = (String) sysListKeyMap.get("LIST_CODE");
			String valueCode = (String) sysListKeyMap.get("VALUE_CODE");
			String valueTitle = (String) sysListKeyMap.get("VALUE_TITLE");
			String exCompanyCode = sysListKeyMap.get("EX_COMPANY_CODE") == null ? ""
					: sysListKeyMap.get("EX_COMPANY_CODE").toString();

			if (sysListTmpMap.containsKey(sysListKeyMap.get("LIST_CODE"))) {
				// 如果已经存在此键值
				Map<String, String[]> valueMap = sysListTmpMap.get(listCode);
				if (valueMap.containsKey(sysListKeyMap.get(valueCode))) {
					// 如果已包含,则不处理
					continue;
				} else {
					valueMap.put(valueCode, new String[] { valueTitle,
							exCompanyCode });
				}
			} else {
				Map<String, String[]> valueMap = new LinkedHashMap<String, String[]>();
				valueMap.put(valueCode, new String[] { valueTitle,
						exCompanyCode });

				sysListTmpMap.put(listCode, valueMap);
			}
		}
		Constants.sysListMap = sysListTmpMap;
		sysListKeys.clear();

	}

	private static void loadSystemConfig(ApplicationContext ctx, boolean reload) {
		if (Constants.isLoad() && !reload) {
			return;
		}
		Map<String, Map<String, String>> sysConfigTmpMap = new HashMap<String, Map<String, String>>();
		Map<String, String> companyTmpMap = new HashMap<String, String>();

		JalCompanyManager jalCompanyManager = (JalCompanyManager) ctx
				.getBean("jalCompanyManager");
		JsysConfigValueManager jsysConfigValueManager = (JsysConfigValueManager) ctx
				.getBean("jsysConfigValueManager");

		List alCompanys = jalCompanyManager.getAll();
		if (alCompanys != null && !alCompanys.isEmpty()) {
			for (int i = 0; i < alCompanys.size(); i++) {
				companyTmpMap.put(
						((JalCompany) alCompanys.get(i)).getCompanyCode(),
						((JalCompany) alCompanys.get(i)).getCompanyName());// 新增分公司列表
				Map<String, String> configDetail = new HashMap<String, String>();
				List sysConfigValues = jsysConfigValueManager
						.getSysConfigValuesByCodeSQL(((JalCompany) alCompanys
								.get(i)).getCompanyCode());

				if (sysConfigValues != null && !sysConfigValues.isEmpty()) {
					for (int j = 0; j < sysConfigValues.size(); j++) {
						HashMap sysConfigValue = (HashMap) sysConfigValues
								.get(j);
						String configValue = (String) sysConfigValue
								.get("CONFIG_VALUE");
						if (StringUtils.isEmpty(configValue)) {
							configValue = (String) sysConfigValue
									.get("DEFAULT_VALUE");
						}
						configDetail.put(
								(String) sysConfigValue.get("CONFIG_CODE"),
								configValue);
					}
				}

				sysConfigTmpMap.put(
						((JalCompany) alCompanys.get(i)).getCompanyCode(),
						configDetail);
				sysConfigValues.clear();
			}
		}
		Constants.companyMap = companyTmpMap;
		Constants.sysConfigMap = sysConfigTmpMap;
		alCompanys.clear();
	}

	private static void loadResourceMap(ApplicationContext ctx, boolean reload) {
		if (Constants.isLoad() && !reload) {
			return;
		}
		JsysResourceManager jsysResourceManager = (JsysResourceManager) ctx
				.getBean("jsysResourceManager");
		Constants.resourceMap = jsysResourceManager
				.getGrantedAuthorityResource("1");
		 log.info("resourceMap="+Constants.resourceMap);

	}

	private static void doReindexing(GenericManager manager) {
		manager.reindexAll(false);
	}

	private static void loadLanguage(ApplicationContext ctx, boolean reload) {
		// TODO Auto-generated method stub\
		log.info("loadLanguage...");
		if (Constants.isLoad() && !reload) {
			return;
		}

		Map<String, Map<String, String>> languageTmpMap = new HashMap<String, Map<String, String>>();
		JalCharacterCodingManager jalCharacterCodingManager = (JalCharacterCodingManager) ctx
				.getBean("jalCharacterCodingManager");
		JalCharacterValueManager jalCharacterValueManager = (JalCharacterValueManager) ctx
				.getBean("jalCharacterValueManager");

		List<JalCharacterCoding> alCharacterCodings = jalCharacterCodingManager
				.getAll();
		// log.info("loadLanguage..alCharacterCodings."+alCharacterCodings.size());
		if (CollectionUtils.isNotEmpty(alCharacterCodings)) {
			for (JalCharacterCoding jalCharacterCoding : alCharacterCodings) {
				Map<String, String> languageDetail = Maps.newHashMap();
				List alCharacterValues = jalCharacterValueManager
						.getAlCharacterValuesByCodeSQL(jalCharacterCoding
								.getCharacterCode());
				if (CollectionUtils.isNotEmpty(alCharacterValues)) {
					for (int j = 0; j < alCharacterValues.size(); j++) {
						HashMap alCharacterValue = (HashMap) alCharacterValues
								.get(j);
						languageDetail.put((String) alCharacterValue
								.get("CHARACTER_KEY"),
								(String) alCharacterValue
										.get("CHARACTER_VALUE"));
					}
				}
				languageTmpMap.put(jalCharacterCoding.getCharacterCode(),
						languageDetail);
				alCharacterValues.clear();
			}
		}

		/*
		 * if (alCharacterCodings != null && !alCharacterCodings.isEmpty()) {
		 * 
		 * for (int i = 0; i < alCharacterCodings.size(); i++) { Map<String,
		 * String> languageDetail = new HashMap<String, String>(); List
		 * alCharacterValues =
		 * jalCharacterValueManager.getAlCharacterValuesByCodeSQL
		 * (((JalCharacterCoding) alCharacterCodings.get(i))
		 * .getCharacterCode());
		 * 
		 * if (alCharacterValues != null && !alCharacterValues.isEmpty()) { for
		 * (int j = 0; j < alCharacterValues.size(); j++) { HashMap
		 * alCharacterValue = (HashMap) alCharacterValues.get(j);
		 * languageDetail.put((String) alCharacterValue.get("CHARACTER_KEY"),
		 * (String) alCharacterValue.get("CHARACTER_VALUE")); } }
		 * 
		 * languageTmpMap.put(((JalCharacterCoding)
		 * alCharacterCodings.get(i)).getCharacterCode(), languageDetail);
		 * alCharacterValues.clear(); } }
		 */
		Constants.localLanguageMap = languageTmpMap;
		// log.info("localLanguageMap="+Constants.localLanguageMap);
		alCharacterCodings.clear();
	}

	/**
	 * Shutdown servlet context (currently a no-op method).
	 * 
	 * @param servletContextEvent
	 *            The servlet context event
	 */
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		// LogFactory.release(Thread.currentThread().getContextClassLoader());
		// Commented out the above call to avoid warning when SLF4J in
		// classpath.
		// WARN: The method class
		// org.apache.commons.logging.impl.SLF4JLogFactory#release() was
		// invoked.
		// WARN: Please see http://www.slf4j.org/codes.html for an explanation.
	}
	/**
	 * 装载期别
	 * @param context
	 * @param ctx
	 */
	public static void loadBdPeriod(ApplicationContext ctx){
		JbdPeriodManager bdPeriodManager = (JbdPeriodManager) ctx.getBean("jbdPeriodManager");
		List<JbdPeriod> bdPeriodList=bdPeriodManager.getAll();
		Constants.periodList = bdPeriodList;
		System.out.println("我的值==="+bdPeriodList);
		
			for (JbdPeriod period : bdPeriodList) {
				
				Integer wweek=period.getWyear()*100+period.getWweek();
				Integer fweek=period.getFyear()*100+period.getFweek();
				

				Integer wmonth=period.getWyear()*100+period.getWmonth();
				Integer fmonth=period.getFyear()*100+period.getFmonth();
				
				WeekFormatUtil.findFweekMap.put(wweek.toString(), fweek.toString());
				WeekFormatUtil.findWweekMap.put(fweek.toString(), wweek.toString());
				
				WeekFormatUtil.findFmonthMap.put(wmonth.toString(), fmonth.toString());
				WeekFormatUtil.findWmonthMap.put(fmonth.toString(), wmonth.toString());
				
				
			}
	}
	
}
