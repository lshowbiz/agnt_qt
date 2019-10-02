
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="com.joymain.ng.service.JsysResourceManager"%>
<%@ page import="java.util.*"%>
<%@page import="com.joymain.ng.util.StringUtil"%>
<%
//获取期别
com.joymain.ng.service.JbdPeriodManager jbdPeriodManager=(com.joymain.ng.service.JbdPeriodManager)com.joymain.ng.util.ContextUtil.getSpringBeanByName(application,"jbdPeriodManager");
com.joymain.ng.model.JbdPeriod bdPeriod=jbdPeriodManager.getBdPeriodByTime(new java.util.Date());
	pageContext.setAttribute("bdPeriod",bdPeriod);
String bdWeek= bdPeriod.getFyear()+""+ (bdPeriod.getFweek()<10?"0" + bdPeriod.getFweek():bdPeriod.getFweek());
JsysResourceManager jsysResourceManager=(JsysResourceManager)com.joymain.ng.util.ContextUtil.getSpringBeanByName(application,"jsysResourceManager");

String userNameUtf="";
if(!(org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)){
	com.joymain.ng.model.JsysUser defSysUser=(com.joymain.ng.model.JsysUser)org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if(!StringUtil.isEmpty(defSysUser.getUserName())){
		userNameUtf=java.net.URLEncoder.encode(defSysUser.getUserName(),"UTF-8");
	}
	
	
	List topmenus = (List)session.getAttribute("topmenus");
}
%>
<!DOCTYPE html>
<html>
<head>
<script src="<c:url value='/scripts/AjaxLoader.js'/>"></script>	
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>框架头部</title>
<script>
	function show_Favorite(sURL, sTitle) {
		sURL = encodeURI(sURL);
		try {
			window.external.addFavorite(sURL, sTitle);
		} catch (e) {
			try {
				window.sidebar.addPanel(sTitle, sURL, "");
			} catch (e) {
				alert("加入收藏失败，请使用Ctrl+D进行添加,或手动在浏览器里进行设置.");
			}
		}
	}
	function showList(id, num) {
		if (num == 1) {
			document.getElementById(id).style.display = "block"
		} else {
			document.getElementById(id).style.display = "none"
		}
	}
	function show_index(url) {
		if (document.all) {
			document.body.style.behavior = 'url(#default#homepage)';
			document.body.setHomePage(url);
		} else {
			alert("您好,您的浏览器不支持自动设置页面为首页功能,请您手动在浏览器里设置该页面为首页!");
		}
	}
</script>
</head>
<body>
<div id="logo1" >	
	<div class="fl mt ml"><img class src="<c:url value='/images/index/logo.gif'/>"></div>
	<div class="fl dat ">
		<H2><a href="${ctx}/jbdPeriodOldAndNewWweekCom?1=1">第<%=bdPeriod.getFmonth() %>财政月

			<c:choose>
				<c:when test="${(bdPeriod.fyear*100+bdPeriod.fweek)>=201803 }">
					<fmt:formatDate var="curDates" value="${bdPeriod.startTime }" pattern="dd"/>
					<c:choose>
						<c:when test="${curDates==01 }">
							上旬
						</c:when>
						<c:when test="${curDates==11 }">
							中旬
						</c:when>
						<c:otherwise>
							下旬
						</c:otherwise>

					</c:choose>


				</c:when>
				<c:otherwise>
					--
				</c:otherwise>
			</c:choose>

			&nbsp;&nbsp;第<%=bdWeek %>期</a></H2>
		<p class="gry2 fl mt">语言：
		<select name="lang" id="lang" class="lang-china tl" style="margin-top:-2px"><option value="1" selected="selected" >中国CHINA</option></select></p>
	</div>
	<div class="sold">	
		<div class="fr mr gry4">
			<a href="javascript:void(0);" class="mr"><span class="mr color2" title="CN ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.jmiMember.petName} / ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userCode}" id="userCard">${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userCode}</span></a>
			<a href="javascript:void(0)" onclick="tohref('002','${pageContext.request.contextPath}/jpoShoppingCartOrderLists/scAll?1=1')" target="_top"><span class="cw-icon glyphicon glyphicon-shopping-cart">&nbsp;购物车<span id='numberTop' style="color:#ed8f2d;font-weight:bold;">0</span>件&gt;&gt;&nbsp;</span></a>				
			<a href="javascript:show_index(window.location);">设为首页</a>&nbsp;|&nbsp;
			<%-- <a href="${ctx }/loginform/help?flag=newHelp&1=1" target="_blank">帮助</a>&nbsp;|&nbsp;--%>
			<a href="http://180.101.2.122:8001/EliteWebChat/AccordNameChat.jsp?Customer_ID=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userCode}&customername=<%=userNameUtf %>" target="_blank">在线客服</a>&nbsp;|&nbsp;
			<a href="<c:url value='/logout' />">退出>></a>
		</div>
		<div class="fr mt" >
			<div id="nav1">
				<div class="nav-c">
					<a href="${ctx}/loginform/showuserinfo" class="nav fl">首   页</a>
					<c:forEach var="menu" items="${topmenus}">
					<a class='nav fl' data-parentId="${menu['RES_ID']}" href="<c:url value="${menu['RES_URL']}&parentId=${menu['RES_ID']}&currentMenuId=${menu['DEF_RESID']}&currentSubMenuId=${menu['DEF_RESID']}"/>">${menu["RES_NAME"]}</a>
					</c:forEach>
				</div>		
			</div>	
		</div>
		<%
		String parentId = (String)request.getParameter("parentId");
		if(null==parentId || "".equals(parentId)){
			parentId=(String)session.getAttribute("parentId");
		}
		%>
		<input id="parentId" type="hidden" value="<%=parentId %>">
		<script>
		var navs = $("#nav1").find(".nav");
		var _parentId = $("#parentId").val();
		for(var i = 0; i< navs.length;i++){
			if (_parentId == $(navs[i]).attr("data-parentId")){
				$(navs[i]).addClass("active_curr");
			}
		}
		</script>
	</div>	
</div>

</body>
<script type="text/javascript">
function tohref(resCode,tourl) {
	var strurl="${ctx}/ajaxController/redirectByRes?resCode="+resCode;
		var loader = new AjaxLoader("GET",strurl);
    var strExist = loader.getReturnText();
    if('1'==strExist){
    	window.location.href=tourl;
    }
}
</script>
</html>