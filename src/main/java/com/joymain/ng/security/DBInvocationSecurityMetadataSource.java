package com.joymain.ng.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntPathRequestMatcher;
//import org.springframework.security.web.util.AntUrlPathMatcher;
//import org.springframework.security.web.util.UrlMatcher;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.util.AntPathMatcher;

import com.joymain.ng.Constants;

import org.springframework.stereotype.Component;

@Component("selfSecurityMetadataSource")
public class DBInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource {
	private static final Logger log = LoggerFactory
			.getLogger(DBInvocationSecurityMetadataSource.class);
	private AntPathMatcher urlMatcher = new AntPathMatcher();

	// private static Map<String, Collection<ConfigAttribute>> resourceMap =
	// null;
	public DBInvocationSecurityMetadataSource() {
		loadResourceDefine();
	}

	private void loadResourceDefine() {
		// TODO Auto-generated method stub
		// resourceMap = Constants.resourceMap;
		// Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();
		// ConfigAttribute ca = new SecurityConfig("ROLE_ADMIN");
		// atts.add(ca);
		// resourceMap.put("/index.jsp", atts);
		// resourceMap.put("/i.jsp", atts);
	}

	// private UrlMatcher urlMatcher = new AntUrlPathMatcher();

	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		String url = ((FilterInvocation) object).getRequestUrl();
		Long logId;
		if(log.isDebugEnabled())
			log.debug("rurl="+url);

		// log.info("getHttpRequest="+((FilterInvocation)object).getRequest().getParameter("strAction"));
		// log.info("getHttpRequest2="+(((FilterInvocation)object).getHttpRequest()).getParameter("strAction"));
		if (StringUtils.isNotEmpty(url) && url.indexOf('?') > -1) {
			url = url.substring(0, url.indexOf('?'));
		}

		
		try {
			String strAction = ((FilterInvocation) object).getHttpRequest()
					.getParameter("strAction");
			if (StringUtils.isNotEmpty(strAction)) {
				url += "?strAction=" + strAction;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
//			log.error("can not get parameter..." + e.getMessage());
		}

		
		// JsysOperationLog sysOperationLog =
		// LogUtil.initSysOperationLog(((FilterInvocation)object).getHttpRequest(),
		// url);
		// String month = DateUtil.getDateTime("yyyyMM", new Date());
		// // log.info("url="+url);
		// logId =LogUtil.saveSysOperationLogBySql(sysOperationLog, month);
		// ContextUtil.bindResource("operationLogId", logId);

		// log.info("request="+((FilterInvocation)object).getHttpRequest());

		Iterator<String> ite = Constants.resourceMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			// log.info("resURL="+resURL+">>>url"+url);

			// urlMatcher = new AntPathRequestMatcher(resURL);
			// urlMatcher.matches(request);
			if (urlMatcher.match("/app" + resURL, url)) {
				// log.info("match="+resURL);

				return (Collection<ConfigAttribute>) Constants.resourceMap
						.get(resURL);
			}
		}
		return null;

	}

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}
