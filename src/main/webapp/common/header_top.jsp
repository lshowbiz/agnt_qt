<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp" %>

<script>
function show_Favorite(sURL, sTitle){   sURL =
encodeURI(sURL);try{window.external.addFavorite(sURL, 
sTitle);}catch(e) 
{try{window.sidebar.addPanel(sTitle, sURL, "");}catch 
(e) 
{alert("加入收藏失败，请使用Ctrl+D进行添加,或手动在浏览器里进行设置.");}}
}function 
showList(id,num){if(num==1){document.getElementById(id).style.display="block"}else{document.getElementById(id).style.display="none"}}function 
show_index(url){
      if (document.all) 
{document.body.style.behavior='url(#default#homepage)';document.body.setHomePage(url);}else{
alert("您好,您的浏览器不支持自动设置页面为首页功能,请您手动在浏览器里设置该页面为首页!");}
}
</script>
    <!--
	<a href="javascript:void(0);" onclick="show_index(window.location);">设为首页&nbsp;
		&nbsp; </a>|-->
 
<%
String userNameUtf="";
if(!(org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)){

com.joymain.ng.model.JsysUser defSysUser=(com.joymain.ng.model.JsysUser)org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
userNameUtf=java.net.URLEncoder.encode(defSysUser.getUserName(),"UTF-8");
}

			%> 	
<div class="header_top">
	<div class="centerDiv clearfix">
		<div class="fl">

			<span>CN</span>
			<span>${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.jmiMember.petName}</span>
			<span>/</span>
			<span>${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userCode}</span>	
		</div>
		<ul class="services fr">
			<li class="cart">
				<a class="subLink" href="${pageContext.request.contextPath}/jpoShoppingCartOrderLists/scAll" target="_top"><ng:locale key="shipping.cart"/>
					<span class="red bold"><div id='numberTop'>0</div></span><ng:locale key="pmproduct.unitno.8"/>
					<s></s>
					<b></b>
				</a>
				<b class="right_line"></b>
			</li>
			<li class="favorite">
				<a class="subLink" href="javascript:show_index(window.location);" target="_top"><ng:locale key="self.first"/>
					<b></b>
				</a>
				<b class="right_line"></b>
			</li>
			<!--<li class="favorite">
				<a class="subLink" href="javascript:show_Favorite(window.location,document.title);" target="_top"><ng:locale key="self.favorites"/>
					<b></b>
				</a>
				<b class="right_line"></b>
			</li>
			<li class="siteNav">
				<a class="subLink" href="javascript:void(0);" target="_top"><ng:locale key="self.navigation"/>
					<b></b>
				</a>
				<b class="right_line"></b>
			</li>-->
			<li><a href="${ctx }/loginform/help?1=1" target="_blank"><ng:locale key="button.help"/></a><b class="right_line"></b> </li>

			<li><a href="http://180.101.2.122:8001/EliteWebChat/AccordNameChat.jsp?Customer_ID=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userCode}&customername=<%=userNameUtf %>" target="_blank">在线客服</a><b class="right_line"></b> </li>	
			<li><a href="<c:url value='/logout' />" target="_top"><ng:locale key="menu.logout"/></a></li>
		</ul>
	</div>
</div>