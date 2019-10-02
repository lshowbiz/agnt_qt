<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>

<%
com.joymain.ng.service.JbdPeriodManager jbdPeriodManager=(com.joymain.ng.service.JbdPeriodManager)com.joymain.ng.util.ContextUtil.getSpringBeanByName(application,"jbdPeriodManager");
com.joymain.ng.model.JbdPeriod bdPeriod=jbdPeriodManager.getBdPeriodByTime(new java.util.Date());

String bdWeek= bdPeriod.getFyear()+""+ (bdPeriod.getFweek()<10?"0" + bdPeriod.getFweek():bdPeriod.getFweek());
%>


<div class="header_mid">
			<div class="centerDiv clearfix">
				<div class="logo"><img src="${ctx}/images/logo.jpg" alt="JM International"><h1>中脉</h1></div>
				<div class="tools fr tr">
					<div class="dateTime"><a href="${ctx}/jbdPeriodOldAndNewWweekCom?1=1">第<%=bdPeriod.getFmonth() %>财政月&nbsp;&nbsp;第<%=bdWeek %>期</a></div>
					<div class="lan">
						<form action="" method="post">
							<span class="ft12"><ng:locale key="view.langage"/></span>
							<select name="lang" class="mySelect" id="lang">
								<option value="1" selected="selected">中国CHINA</option>
							</select>
						</form>
					</div>
				</div>
			</div>
		</div>