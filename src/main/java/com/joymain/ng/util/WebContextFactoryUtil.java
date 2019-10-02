package com.joymain.ng.util;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取Spring容器对象方法
 * 
 * @author 刘明云
 * 
 */
public class WebContextFactoryUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法,设置上下文环境 {@inheritDoc}
     */
    public void setApplicationContext(ApplicationContext appContext) {
        applicationContext = appContext;
    }

    /**
     * 获取ApplicationContext对象
     * 
     * @return ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义,则返回true
     * 
     * @param name
     *            Bean名称
     * @return boolean
     */
    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /**
     * 
     * 获取Bean
     * 
     * @param beanName
     *            bean名称
     * @return Object
     */
    public static Object getBean(String beanName) {
        if (!WebContextFactoryUtil.containsBean(beanName)) {
            return null;
        } else {
            return applicationContext.getBean(beanName);
        }
    }

    /**
     * 
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
     * 如果与给定名字相应的bean定义没有被找到,将会抛出一个异常（NoSuchBeanDefinitionException）
     * 
     * @param name
     *            Bean名称
     * @return boolean
     */
    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    /**
     * 获取Bean 类型
     * 
     * @param name
     *            Bean名称
     * @return Class
     * @throws NoSuchBeanDefinitionException
     */
    @SuppressWarnings("rawtypes")
    public static Class getType(String name) {
        return applicationContext.getType(name);
    }

    /**
     * 如果给定的bean名字在bean定义中有别名,则返回这些别名
     * 
     * @param name
     *            Bean名称
     * @return String[]
     */
    public static String[] getAliases(String name) {
        return applicationContext.getAliases(name);
    }
}
