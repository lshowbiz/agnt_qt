package com.joymain.ng.webapp.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.joymain.ng.model.JalCity;
import com.joymain.ng.model.JalDistrict;
import com.joymain.ng.model.JalStateProvince;
import com.joymain.ng.service.JalCityManager;
import com.joymain.ng.service.JalDistrictManager;
import com.joymain.ng.service.JalStateProvinceManager;
import com.joymain.ng.util.StringUtil;


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
public class RegionTag extends TagSupport {
	private String regionId;

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	private String regionType;

	public void setRegionType(String regionType) {
		this.regionType = regionType;
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		// TODO Auto-generated method stub
		if (StringUtil.isEmpty(regionId)) {
			return this.SKIP_BODY;
		}
		try {
			if ("P".equalsIgnoreCase(regionType)) {
				ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(this.pageContext.getServletContext());
				JalStateProvinceManager alStateProvinceManager = (JalStateProvinceManager) context.getBean("jalStateProvinceManager");
				JalStateProvince alStateProvince = alStateProvinceManager.get(regionId);
				if (alStateProvince != null) {
					this.pageContext.getOut().write(alStateProvince.getStateProvinceName());
				}
			}else if ("C".equalsIgnoreCase(regionType)) {
				ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(this.pageContext.getServletContext());
				JalCityManager alCityManager = (JalCityManager) context.getBean("jalCityManager");
				JalCity alCity = alCityManager.get(regionId);
				if (alCity != null) {
					this.pageContext.getOut().write(alCity.getCityName());
				}
			}else if ("D".equalsIgnoreCase(regionType)) {
				ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(this.pageContext.getServletContext());
				JalDistrictManager  alDistrictManager = (JalDistrictManager) context.getBean("jalDistrictManager");
				JalDistrict alDistrict = alDistrictManager.get(regionId);
				if (alDistrict != null) {
					this.pageContext.getOut().write(alDistrict.getDistrictName());
				}
			}
			return this.SKIP_BODY;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return this.SKIP_BODY;
	}

}
