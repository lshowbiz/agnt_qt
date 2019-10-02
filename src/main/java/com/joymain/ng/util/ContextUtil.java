package com.joymain.ng.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.springframework.web.context.support.WebApplicationContextUtils;

//import com.joymain.jecs.Constants;

/**
 * 保存Spring Context，以便在非web环境获得Spring的依赖注入服务
 * 
 *
 * <p>Title: is a Class</p>
 *
 * <p>Description: 类</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: gt</p>
 *
 * @author Aidy.Q
 * @version 1.0
 */

public class ContextUtil  {
	private static Log log = LogFactory.getLog(ContextUtil.class);
	private static final ThreadLocal loginSession = new ThreadLocal();
	private static ApplicationContext actx;
	private static ServletContext ctx;
	
	public ContextUtil() {
	}

	private static void setContext(ApplicationContext ctx){
		actx=ctx;
	}
	
	public static ApplicationContext getContext(){
		return actx;
	}
	
	public static ServletContext getCtx() {
		return ctx;
	}

	public static void setCtx(ServletContext ctx) {
		ContextUtil.ctx = ctx;
	}

	/*public static Object getSpringBeanByName(String name){
//		return getSpringBeanByName(Constants.context,name);
		return null;
	}*/
	
	
	/**
	 * added by Aidy.Q
	 * @param context
	 * @param name
	 * @return
	 */
	public  static Object getSpringBeanByName(ServletContext context,String name){
		Object o;
		ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
		o=ctx.getBean(name);
		return o;
		
	}
	public void contextInitialized(ServletContextEvent event)
    {
        //super.contextInitialized(event);

        ServletContext context = event.getServletContext();
        setCtx(context);
        ApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        ContextUtil.setContext(ctx);
        log.info("set context....");
    }
	
	/**
	 * Bind the given resource for the given key to the current thread.
	 * @param key key to bind the value to
	 * @param value value to bind
	 * @throws IllegalStateException if there is already a value bound to the thread
	 */
	public static void bindResource(Object key, Object value) throws IllegalStateException {
		Map map = (Map) loginSession.get();
		// set ThreadLocal Map if none found
		if (map == null) {
			map = new HashMap();
			loginSession.set(map);
		}
//		if (map.containsKey(key)) {
//			throw new IllegalStateException("Already value [" + map.get(key) + "] for key [" + key +
//					"] bound to thread [" + Thread.currentThread().getName() + "]");
//		}
		map.put(key, value);
//		if (log.isDebugEnabled()) {
//			log.debug("Bound value [" + value + "] for key [" + key + "] to thread [" +
//					Thread.currentThread().getName() + "]");
//		}
	}
	
	/**
	 * Return all resources that are bound to the current thread.
	 * <p>Mainly for debugging purposes. Resource managers should always invoke
	 * hasResource for a specific resource key that they are interested in.
	 * @return Map with resource keys and resource objects,
	 * or empty Map if currently none bound
	 * @see #hasResource
	 */
	public static Map getResourceMap() {
		Map map = (Map) loginSession.get();
		if (map == null) {
			map = new HashMap();
		}
		return Collections.unmodifiableMap(map);
	}
	
	/**
	 * Retrieve a resource for the given key that is bound to the current thread.
	 * @param key key to check
	 * @return a value bound to the current thread, or <code>null</code> if none
	 */
	public static Object getResource(Object key) {
		Map map = (Map) loginSession.get();
		if (map == null) {
			return null;
		}
		Object value = map.get(key);
//		if (value != null && log.isDebugEnabled()) {
//			log.debug("Retrieved value [" + value + "] for key [" + key + "] bound to thread [" +
//					Thread.currentThread().getName() + "]");
//		}
		return value;
	}
	
}
