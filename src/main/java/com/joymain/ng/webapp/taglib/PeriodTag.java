package com.joymain.ng.webapp.taglib;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


import com.joymain.ng.Constants;
import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.util.StringUtil;



public class PeriodTag extends TagSupport{
	private String dateStr;

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		if(StringUtil.isEmpty(this.dateStr)){
			return this.SKIP_BODY;
		}
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date;
		try {
			date = simpleDateFormat.parse(this.dateStr);
			
			long dateLong = date.getTime();
			for (int i = 0 ; i < Constants.periodList.size() ; i++){
				JbdPeriod bdPeriod = (JbdPeriod)Constants.periodList.get(i);
				long startTime = bdPeriod.getStartTime().getTime();
				long endTime = bdPeriod.getEndTime().getTime();
				if(dateLong>=startTime && dateLong<endTime){
					String strPeriod = "";
					if(bdPeriod.getFweek().toString().length()==2){
						strPeriod = bdPeriod.getFyear().toString() + bdPeriod.getFweek().toString();
					}else{
						strPeriod = bdPeriod.getFyear().toString() + "0" + bdPeriod.getFweek().toString();
					}
					this.pageContext.getOut().write(strPeriod);
					return this.SKIP_BODY;
				}
			}
		} catch (IOException io) {
			// TODO Auto-generated catch block
			throw new JspException(io);
		}catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.SKIP_BODY;
	}
	

}
