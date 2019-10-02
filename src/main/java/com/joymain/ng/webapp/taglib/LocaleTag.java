/**
 * 
 */
package com.joymain.ng.webapp.taglib;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.joymain.ng.util.LocaleUtil;





/**
 * 多国语言标签
 * @author Aidy.Q
 * @jsp.tag name="locale" bodycontent="empty"
 */
public class LocaleTag extends TagSupport {
	private static final long serialVersionUID = 1L;

	protected final Log log = LogFactory.getLog(getClass());

	private static boolean isLoad = false;

	private String key = null;
	
	private String args=null;
	private String argTransFlag=null;

	/**
	 * 获取args
	 * @jsp.attribute required="false" rtexprvalue="true"
	 * @return
	 */
	public String getArgTransFlag() {
		return argTransFlag;
	}
	/**
	 * 设置arg翻译,true/false
	 * @param args
	 */
	public void setArgTransFlag(String argTransFlag) {
		this.argTransFlag = argTransFlag;
	}
	/**
	 * 获取args
	 * @jsp.attribute required="false" rtexprvalue="true"
	 * @return
	 */
	public String getArgs() {
		return args;
	}
	/**
	 * 设置arg,参数以都好分隔符分割
	 * @param args
	 */
	public void setArgs(String args) {
		this.args = args;
	}

	/**
	 * 获取键值
	 * @jsp.attribute required="true" rtexprvalue="true"
	 * @return
	 */
	public String getKey() {
		return this.key;
	}

	/**
	 * 设置键值
	 * @param key
	 */
	public void setKey(String key) {
		try {
			this.key = (String)ExpressionEvaluatorManager.evaluate("key", key, String.class, this, pageContext);
		} catch (JspException e) {
		} catch (NullPointerException e) {
			this.key="";
		}
	}

	public int doStartTag() throws JspException {
		try {
			String characterValue;
			if (!StringUtils.isEmpty(key)) {
				 
				 if(StringUtils.isNotEmpty(args)){
						String[] argList = args.split(",");
						if("true".equals(argTransFlag)){
							characterValue=LocaleUtil.getLocalText(key, this.getTrans(argList));
						}else{
							characterValue=LocaleUtil.getLocalText(key, argList);
						}
						
					}else{
						characterValue=LocaleUtil.getLocalText("zh_CN",key);
					}
				 if(!StringUtils.isEmpty(characterValue)){
					pageContext.getOut().print(characterValue);
				}
			}	
			
			
			
		} catch (IOException e) {
			log.error(e.getMessage());
		}

		// Continue processing this page
		return SKIP_BODY;
	}
	
	private String[] getTrans(String[] arg){
		String[] ret = new String[arg.length];
		for(int i=0;i<arg.length;i++){
			 ret[i] = LocaleUtil.getLocalText(arg[i]);
			
		}
		return ret;
	}
	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}
}