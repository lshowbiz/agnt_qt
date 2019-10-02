package com.joymain.ng.webapp.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.joymain.ng.model.JbdMemberLinkCalcHist;
import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.model.JmiLinkRef;
import com.joymain.ng.model.JmiRecommendRef;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JbdMemberLinkCalcHistManager;
import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.service.JmiAddrBookManager;
import com.joymain.ng.service.JmiLinkRefManager;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiRecommendRefManager;
import com.joymain.ng.util.StringUtil;

@Controller
public class JmiSelectRecommendRefController {
	private final Log log = LogFactory.getLog(JmiSelectRecommendRefController.class);
	private JmiMemberManager jmiMemberManager;

	@Autowired
	public void setJmiMemberManager(JmiMemberManager jmiMemberManager) {
		this.jmiMemberManager = jmiMemberManager;
	}

	@Autowired
	private JbdMemberLinkCalcHistManager jbdMemberLinkCalcHistManager;
	@Autowired
	private JmiAddrBookManager jmiAddrBookManager;
	@Autowired
	public JbdPeriodManager jbdPeriodManager;
	@Autowired
	private JmiRecommendRefManager jmiRecommendRefManager;
	@Autowired
	private JmiLinkRefManager jmiLinkRefManager;
	Integer integer;

	@RequestMapping(value = "/jmiSelectRecommendRef", method = RequestMethod.GET)
	public String getSelectRecommendNo(HttpServletRequest request) {

		JsysUser defSysUser = (JsysUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String returnView = "jmiSelectRecommendRef";
		String userCode = request.getParameter("userCode");

		if (StringUtil.isEmpty(userCode)) {// 会员员为空
			return returnView;
		}

		JmiLinkRef jmiLinkRef = jmiLinkRefManager.get(userCode);
		if (jmiLinkRef == null) {// 会员不存在
			return returnView;
		}

		JmiRecommendRef miRecommendRef = jmiRecommendRefManager
				.get(jmiLinkRef.getJmiMember().getJmiRecommendRef().getUserCode());
		JmiRecommendRef defRecommendRef = jmiRecommendRefManager.get(defSysUser.getUserCode());
		if (!StringUtil.getCheckIsDownline(defRecommendRef.getTreeIndex(), miRecommendRef.getTreeIndex())) {
			return returnView;
		}

		request.setAttribute("jmiLinkRef", fillInMap(jmiLinkRef));
		List list = jmiLinkRefManager.getLinkRefbyLinkNo(userCode, "a.jmiMember.createTime", "");

		List jmiLinkRefs = new ArrayList();
		for (int i = 0; i < list.size(); i++) {
			JmiLinkRef curJmiLinkRef = (JmiLinkRef) list.get(i);
			Map map = fillInMap(curJmiLinkRef);
			jmiLinkRefs.add(map);
		}

		request.setAttribute("jmiLinkRefs", jmiLinkRefs);

		return returnView;
	}

	private Map fillInMap(JmiLinkRef miLinkRef) {
		JbdPeriod bdPeriod = jbdPeriodManager.getBdPeriodByTime(new Date());
		String bdWeek = bdPeriod.getWyear() + ""
				+ (bdPeriod.getWweek() < 10 ? "0" + bdPeriod.getWweek() : bdPeriod.getWweek());
		Map newMap = new HashMap();
		// JbdMemberLinkCalcHist jbdMemberLinkCalcHist =
		// jbdMemberLinkCalcHistManager.getBonusQueryDetail(miLinkRef.getUserCode(),bdWeek);
		Map map = jmiMemberManager.getJbdDayBounsCalcByUserCode(miLinkRef.getUserCode(), StringUtil.formatInt(bdWeek));
		BigDecimal weekGroupPv = new BigDecimal(0);
		String count = "0";

		if (map != null) {
			weekGroupPv = new BigDecimal(map.get("week_group_pv").toString());
			if(map.get("LINK_NUM")!=null){
				count = "".equals(map.get("LINK_NUM").toString())?"0":map.get("LINK_NUM").toString();
			}
			
		}

		newMap.put("miLinkRefCount", count);
		newMap.put("miLinkRef", miLinkRef);
		newMap.put("gpv", weekGroupPv);

		return newMap;
	}


}
