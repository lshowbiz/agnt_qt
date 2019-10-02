package com.joymain.ng.webapp.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.WeekFormatUtil;



/**
 * Tag for creating multiple &lt;select&gt; options for displaying a list of
 * company names.
 * 
 * <p>
 * <b>NOTE</b> - This tag requires a Java2 (JDK 1.2 or later) platform.
 * </p>
 * 
 * @author Alvin
 * @version
 * 
 * @jsp.tag name="period" bodycontent="empty"
 */
public class WeekFormatTag extends TagSupport {
	
	private String weekType;
	private String week;
	
	public void setWeek(String week) {
		this.week = week;
	}

	public void setWeekType(String weekType) {
		this.weekType = weekType;
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		if (StringUtil.isEmpty(week)) {
			return this.SKIP_BODY;
		}
		try {
			Object obj=null;
			if("w".equals(weekType)){
				obj=WeekFormatUtil.findFweekMap.get(week);
			}else if("f".equals(weekType)){
				obj=WeekFormatUtil.findWweekMap.get(week);
			}
			if(obj!=null){
				this.pageContext.getOut().write(obj.toString());
			}else{
				this.pageContext.getOut().write("week error!");
			}
			
			
			return this.SKIP_BODY;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.SKIP_BODY;
	}

}
