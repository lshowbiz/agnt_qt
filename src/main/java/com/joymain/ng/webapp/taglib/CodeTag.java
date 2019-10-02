/**
 * 
 */
package com.joymain.ng.webapp.taglib;

import java.io.IOException;
import java.util.Map;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.joymain.ng.Constants;
import com.joymain.ng.util.LocaleUtil;





/**
 * 显示List值对应的标题
 * @author Aidy.Q
 * @jsp.tag name="code" bodycontent="empty" 
 */
public class CodeTag extends TagSupport {
	protected final Log log = LogFactory.getLog(getClass());

	private String listCode = null;
	protected String saveBody = null;
	private String value = null;

	public int doStartTag() throws JspException {
		StringBuffer results = new StringBuffer("");

		String lookupCode = (value == null) ? "" : value.toString();
//		log.info("listCode");
		Map<String, String[]> map = Constants.sysListMap.get(listCode);
		if(map==null || map.isEmpty()){
			return SKIP_BODY;
		}
//		log.info("listCode2");
		String[] values = map.get(lookupCode);
		if (values != null){	
//			log.info("listCode3");
			results.append(LocaleUtil.getLocalText("zh_CN", values[0]));
//			log.info("listCode4");
		}
		try {
			pageContext.getOut().print(results.toString());
		} catch (IOException e) {
			log.error(e.getMessage());
		}

		return SKIP_BODY;
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	/**
	 * 获取List编码
	 * @jsp.attribute required="true" rtexprvalue="true" description="List Code"
	 * @return
	 */
	public String getListCode() {
    	return listCode;
    }

	public void setListCode(String listCode) {
    	this.listCode = listCode;
    }

	/**
	 * 获取当前的值
	 * @jsp.attribute required="true" rtexprvalue="true" description="Current value"
	 * @return
	 */
	public String getValue() {
    	return value;
    }
	
	public void setValue(String value) {
		try {
			this.value = (String)ExpressionEvaluatorManager.evaluate("value", value.toString(), Object.class, this, pageContext);
		} catch (JspException e) {
		} catch (NullPointerException e) {
			this.value="";
		}
	}
}