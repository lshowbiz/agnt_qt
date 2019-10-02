<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ page import="java.util.*"%>
<%@ page import="com.joymain.ng.util.StringUtil" %>
<%@ include file="/common/taglibs.jsp"%>

<html lang="en">
<head>
    <title><decorator:title /><ng:locale key="webapp.name.qt" /></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <link rel="icon" href="<c:url value="/images/favicon.ico"/>" />
    <link rel="stylesheet" href="<c:url value='/styles/style-ng.css'/>" />
    <link href="<c:url value='/styles/index/style.css'/>" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="<c:url value='/styles/lib/bootstrap.min.css'/>" />
    <decorator:head />
    
    <script src="<c:url value='/scripts/lib/jquery-1.8.2.min.js'/>"></script>
    <script src="<c:url value='/scripts/lib/plugins/jquery.cookie.js'/>"></script>
    <script src="<c:url value='/scripts/joyLife.js'/>"></script>
    <script src="<c:url value='/scripts/script.js'/>"></script>
    <script>
        var errorDataSubmited="<ng:locale key="error.data.submited"/>";
    </script>
    <%
    List commonMenus = (List)session.getAttribute("commonMenus");
    %>
</head>

<body
	<decorator:getProperty property="body.id" writeEntireProperty="true"/>
	<decorator:getProperty property="body.class" writeEntireProperty="true"/>>
	<c:set var="currentMenu" scope="request">
		<decorator:getProperty property="meta.menu" />
	</c:set>
	
	<div id="header_div">
		<jsp:include page="/common/header_nav.jsp" />
	</div>
	
	<jsp:include page="/common/mi_reg_mask.jsp" />
	
	<div>
		<jsp:include page="/common/leftmenu.jsp?userCode=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}" />
	</div>
	
	<div style="display:inline;">
	<div id="content_div" class="righ-box ml mt">
		<div>
			<%@ include file="/common/messages.jsp" %>
			<decorator:body />
		</div>
	</div>
	
	<!--右侧快捷入口导航-->
	<div id="list1" class="fr" style="background-color:white;display:inline-block;position:fixed;right:0px;">
		<a href="javascript:void(0)" onclick="tohref('myjfiBankbookJournals','${ctx}/jfiBankbookJournals?1=1');" class="icon">
			<div class="icon gry2">
				<img class="icon-img mt" src="<c:url value='/images/index/icon1.png'/>"><br>我的钱包
			</div>
		</a>
	
		<a href="javascript:void(0)" onclick="tohref('myfiBankbookJournals','${ctx}/fiBankbookJournals?1=1');" class="icon">
			<div class="icon gry2">
				<img class="icon-img mt" src="<c:url value='/images/index/icon2.png'/>"><br>我的基金
			</div>
		</a>
	
		<a href="javascript:void(0)" onclick="tohref('fiBcoinJournals','${ctx}/fiBcoinJournals?1=1');" class="icon">
			<div class="icon gry2">
				<img class="icon-img mt" src="<c:url value='/images/index/icon3.png'/>"><br>我的积分
			</div>
		</a>
	
		<a href="javascript:void(0)" onclick="tohref('jbdMemberLinkCalcHist','${ctx}/jbdMemberLinkCalcHist?1=1');" class="icon">
			<div class="icon gry2">
				<img class="icon-img mt" src="<c:url value='/images/index/icon4.png'/>"><br>奖金查询
			</div>
		</a>

		<a href="javascript:void(0)" onclick="tohref('myjfiBankbookJournals','${ctx}/fiTransferAccounts?1=1');" class="icon">
			<div class="icon gry2">
				<img class="icon-img mt" src="<c:url value='/images/index/icon5.png'/>"><br>会员转账
			</div>
		</a>
		<c:if test="${isStockAccount=='Y'}">
			<a href="${ctx}/jsysStockAccount/jsysStockAccountForm?parentId=712625"  class="icon">
				<div class="icon">
					<img class="icon-img mt" src="<c:url value='/images/baner-5-2.png'/>"><br>港股帐号
				</div>
			</a>
		</c:if>
	<!-- 
		<a href="${ctx}/jfiBankbookJournals?1=1&parentId=712625&currentMenuId=712774&currentSubMenuId=712821" class="icon">
			<div class="icon gry2">
				<img class="icon-img mt" src="<c:url value='/images/index/icon1.png'/>"><br>存折查询
			</div>
		</a>
		<a href="${ctx}/fiBankbookJournals?1=1&parentId=712625&currentMenuId=712774&currentSubMenuId=712822" class="icon">
			<div class="icon gry2">
				<img class="icon-img mt" src="<c:url value='/images/index/icon2.png'/>"><br>基金查询
			</div>
		</a>
		<a href="${ctx}/fiBcoinJournals?1=1&parentId=712625&currentMenuId=712774&currentSubMenuId=712823" class="icon">
			<div class="icon gry2">
				<img class="icon-img mt" src="<c:url value='/images/index/icon3.png'/>"><br>积分查询
			</div>
		</a>
		<a href="${ctx}/jbdMemberLinkCalcHist?1=1&parentId=712625&currentMenuId=712774&currentSubMenuId=712785" class="icon">
			<div class="icon gry2">			
				<img class="icon-img mt" src="<c:url value='/images/index/icon4.png'/>"><br>奖金查询
			</div>
		</a>
		<a href="${ctx}/fiTransferAccounts?1=1&parentId=712625&currentMenuId=712774&currentSubMenuId=712824" class="icon">
			<div class="icon gry2">
			<img class="icon-img mt" src="<c:url value='/images/index/icon5.png'/>"><br>会员转账	
			</div>
		</a>
		 -->
	</div>
	</div>
		 
	<%=(request.getAttribute("scripts") != null) ? request.getAttribute("scripts") : ""%>
					
		
	<jsp:include page="/common/mi_reg_mask_js.jsp" />
			

	<script>


	var $ = jQuery.noConflict();

	if($("#errorDiv").length > 0){
		var errorDiv=$("#errorDiv");
		var arrTips = new Array();
		errorDiv.find('div').each(function(i){

			arrTips.push($(this).text());
		});  
		var msgoutStr='';
		for(i = 0; i < arrTips.length; i++) {
			msgoutStr+=arrTips[i]+'\n';
		}
		if(arrTips.length>0){
			alert(msgoutStr);
		}	
	}
		
		
		//获取购物车中的数据
		//改成ajax方式，DWR会引起JS加载错误
		
		
	$.ajaxSetup({cache:false});
		
	function showCartNumber(){
		$.get("<c:url value='/jpoMemberShopCartNum'/>",function(data){
			 $("#numberTop").html(data);
		}); 
	}
	showCartNumber();
	
	//设置左侧菜单自适应高度
	function leftMenuAutoHeight(){
		var left_menu_ul_height = ($(window).height()-110);
		$("#left_menu_ul").css("height",left_menu_ul_height+"px");
	}
	leftMenuAutoHeight();
	
 	<%
	if(!(org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)){
		com.joymain.ng.model.JsysUser defSysUser=(com.joymain.ng.model.JsysUser)org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		com.joymain.ng.service.FiCommonAddrManager fiCommonAddrManager=(com.joymain.ng.service.FiCommonAddrManager)com.joymain.ng.util.ContextUtil.getSpringBeanByName(application,"fiCommonAddrManager");
		com.joymain.ng.service.JmiMemberManager jmiMemberManager=(com.joymain.ng.service.JmiMemberManager)com.joymain.ng.util.ContextUtil.getSpringBeanByName(application,"jmiMemberManager");
		com.joymain.ng.model.FiCommonAddr fiCommonAddr=fiCommonAddrManager.get(defSysUser.getUserCode());
		com.joymain.ng.model.JmiMember jmiMember=jmiMemberManager.get(defSysUser.getUserCode());
		if((fiCommonAddr==null || jmiMemberManager.getIsInfoEmpty(defSysUser.getUserCode())) ){
			%>
			window.location.href='<c:url value="/loginform/showuserinfo"/>'; 
			<%
		}else if(StringUtil.isEmpty(jmiMember.getPapernumber()) && jmiMember.getMemberLevel().intValue()==500){
		    %>
			window.location.href='<c:url value="/loginform/showuserinfo"/>';
			<%
		}
}

%>
	
	</script>
	</body>
</html>
