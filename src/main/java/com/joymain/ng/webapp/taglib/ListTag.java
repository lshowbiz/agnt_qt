/**
 * 
 */
package com.joymain.ng.webapp.taglib;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;

import com.joymain.ng.Constants;
import com.joymain.ng.util.LocaleUtil;




/**
 * 通过List值显示下拉列表框
 * @author Aidy.Q
 * @jsp.tag name="list" bodycontent="empty" description="通过List值显示下拉列表框"
 */
public class ListTag extends TagSupport {
	protected final Log log = LogFactory.getLog(getClass());
	
	private String listCode = null;
	private String value = null;
	private String defaultValue = null;
	private String disabled = null;
	private String showBlankLine = null;
	private String onchange = null;
	private String multiple = null;
	private String name = null;
	private String id = null;
	private String size = null;
	private String style = null;
	private String styleClass = null;
	private String onclick = null;
	 
	protected String saveBody = null;

	/**
	 * 增加选项
	 * @param sb 
	 * @param key 选项值
	 * @param value 选项显示文字
	 * @param matched 是否匹配
	 */
	protected void addOption(StringBuffer sb, String key, String value, boolean matched) {
		sb.append("<option value=\"");
		sb.append(key);
		sb.append("\"");
		if (matched){
			sb.append(" selected=\"selected\"");
		}
		sb.append(">");

		sb.append(LocaleUtil.getLocalText(value));
		sb.append("</option>");
	}

	public int doEndTag() throws JspException {
		StringBuffer results = new StringBuffer();
		if (saveBody != null){
			results.append(saveBody);
		}
		results.append("</select>");

		try {
			pageContext.getOut().print(results.toString());
			this.value=null;
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return EVAL_PAGE;
	}

	public int doStartTag() throws JspException {
		StringBuffer results = new StringBuffer("<select");
		results.append(" name=\"");
		results.append(name.toString());
		results.append("\"");
		if (id != null) {
			results.append(" id=\"");
			results.append(id.toString());
			results.append("\"");
		}
		if (multiple != null) {
			results.append(" multiple=\"multiple\"");
		}
		if (size != null) {
			results.append(" size=\"");
			results.append(size);
			results.append("\"");
		}
		if (disabled != null) {
			results.append(" disabled=\"disabled\"");
		}
		if (onchange != null) {
			results.append(" onchange=\"" + onchange + "\"");
		}
		if (onclick != null) {
			results.append(" onclick=\"" + onclick + "\"");
		}
		if (style != null) {
			results.append(" style=\"");
			results.append(style);
			results.append("\"");
		}
		if (styleClass != null) {
			results.append(" class=\"");
			results.append(styleClass);
			results.append("\"");
		}
		results.append(">");

		if (showBlankLine != null) {
			addOption(results, "", "", false);
		}
		String lookupCode = (value == null) ? "" : value.toString();
		
		Map<String, String[]> map = Constants.sysListMap.get(listCode);
		if(map==null || map.isEmpty()){
			return SKIP_BODY;
		}
		Set codes = map.entrySet();
		boolean matched = false;
		if ((lookupCode == null) && ((defaultValue == null) || (defaultValue.trim().equals("")))) {
			lookupCode = "";
			matched = false;
		}
		if (codes != null) {
			Iterator iter = codes.iterator();
			while (iter.hasNext()) {
				Map.Entry entry=(Map.Entry)iter.next();
				String[] values = (String[])entry.getValue();
				
				if(StringUtils.contains(values[1],"CN")){
					//如果当前用户所属公司在排除公司之内,则不显示
					continue;
				}else{
					if ((lookupCode != null) && (!lookupCode.equals(""))) {
						matched = entry.getKey().toString().equalsIgnoreCase(lookupCode);
						
					} else if ((defaultValue != null) && (!defaultValue.trim().equals(""))) {
						
						matched = entry.getKey().toString().equalsIgnoreCase(defaultValue);
					}
					addOption(results, entry.getKey().toString(), values[0], matched);
				}
			}
			// 输出至页面
			try {
				pageContext.getOut().print(results.toString());
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}

		// Continue processing this page
		return SKIP_BODY;
	}

	/**
	 * 获取List编码
	 * @jsp.attribute required="true" rtexprvalue="true" description="List编码"
	 * @return
	 */
	public String getListCode() {
    	return listCode;
    }

	public void setListCode(String listCode) {
    	this.listCode = listCode;
    }

	/**
	 * 获取List值
	 * @jsp.attribute required="true" rtexprvalue="true" description="当前值"
	 * @return
	 */
	public String getValue() {
    	return value;
    }

	public void setValue(String value) {
		try {
			this.value = (String) ExpressionEvaluatorManager.evaluate("value", value.toString(), Object.class, this, pageContext);
		} catch (JspException e) {
		} catch (NullPointerException e) {
			this.value="";
		}
    }

	/**
	 * 获取默认值
	 * @jsp.attribute required="true" rtexprvalue="true" description="默认值"
	 * @return
	 */
	public String getDefaultValue() {
    	return defaultValue;
    }

	public void setDefaultValue(String defaultValue) {
    	this.defaultValue = defaultValue;
    }

	/**
	 * 是否Disabled
	 * @jsp.attribute required="false" rtexprvalue="true" description="是否disabled"
	 * @return
	 */
	public String getDisabled() {
    	return disabled;
    }

	public void setDisabled(String disabled) {
    	this.disabled = disabled;
    }

	/**
	 * 是否显示空白行
	 * @jsp.attribute required="false" rtexprvalue="true" description="是否显示空白"
	 * @return
	 */
	public String getShowBlankLine() {
    	return showBlankLine;
    }

	public void setShowBlankLine(String showBlankLine) {
    	this.showBlankLine = showBlankLine;
    }

	/**
	 * 选择行变更的时候触发的事件
	 * @jsp.attribute required="false" rtexprvalue="true" description="选择变更时触发的事件"
	 * @return
	 */
	public String getOnchange() {
    	return onchange;
    }

	public void setOnchange(String onchange) {
    	this.onchange = onchange;
    }

	/**
	 * 是否可以多选
	 * @jsp.attribute required="false" rtexprvalue="true" description="是否显示为多选的样式"
	 * @return
	 */
	public String getMultiple() {
    	return multiple;
    }

	public void setMultiple(String multiple) {
    	this.multiple = multiple;
    }

	/**
	 * 获取List名称
	 * @jsp.attribute required="true" rtexprvalue="true" description="List名称"
	 * @return
	 */
	public String getName() {
    	return name;
    }

	public void setName(String name) {
		try {
			this.name = (String) ExpressionEvaluatorManager.evaluate("name", name.toString(), Object.class, this, pageContext);
		} catch (JspException e) {
		} catch (NullPointerException e) {
			this.name="";
		}
    }

	/**
	 * 获取List ID
	 * @jsp.attribute required="false" rtexprvalue="true" description="List ID"
	 * @return
	 */
	public String getId() {
    	return id;
    }

	public void setId(String id) {
		try {
			this.id = (String) ExpressionEvaluatorManager.evaluate("id", id.toString(), Object.class, this, pageContext);
		} catch (JspException e) {
		} catch (NullPointerException e) {
			this.id="";
		}
    }

	/**
	 * 获取List Size
	 * @jsp.attribute required="false" rtexprvalue="true" 
	 * @return
	 */
	public String getSize() {
    	return size;
    }

	public void setSize(String size) {
    	this.size = size;
    }

	/**
	 * 获取List样式
	 * @jsp.attribute required="false" rtexprvalue="true" description="List对应的HTML的样式"
	 * @return
	 */
	public String getStyle() {
    	return style;
    }

	public void setStyle(String style) {
    	this.style = style;
    }

	/**
	 * 获取CSS样式名称
	 * @jsp.attribute required="false" rtexprvalue="true" description="List对应的CSS样式"
	 * @return
	 */
	public String getStyleClass() {
    	return styleClass;
    }

	public void setStyleClass(String styleClass) {
    	this.styleClass = styleClass;
    }

	/**
	 * 获取List点击时触发的事件
	 * @jsp.attribute required="false" rtexprvalue="true" description="List点击触发的事件"
	 * @return
	 */
	public String getOnclick() {
    	return onclick;
    }

	public void setOnclick(String onclick) {
    	this.onclick = onclick;
    }
}