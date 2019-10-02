<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>

<html lang="en">
<head>
	<style>
		.fitting { position:absolute; right:10px; top:175px; box-shadow: 5px 5px 5px #ccc;}
		.fitting a { display: block; width: 190px;}
		.ax { position:fixed; left:30px; bottom:160px; width: 98px; border: 1px solid #FE204D;}
	</style>
    <title><ng:locale key="webapp.name.qt" /></title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    <link rel="icon" href="<c:url value="/images/favicon.ico"/>" />
    <link rel="stylesheet" href="<c:url value='/styles/style-ng.css'/>" />
    <decorator:head />
    
   <%--  
    <script src="<c:url value='/dwr/util.js'/>"></script>
    <script src="<c:url value='/dwr/engine.js'/>"></script>
    <script src="<c:url value='/dwr/interface/jpoShoppingCartOrderManager.js'/>"></script> 
    --%>
    
    <script src="<c:url value='/scripts/lib/jquery-1.8.2.min.js'/>"></script>
    <script src="<c:url value='/scripts/lib/plugins/jquery.cookie.js'/>"></script>
    <script src="<c:url value='/scripts/joyLife.js'/>"></script>
    <script src="<c:url value='/scripts/script.js'/>"></script>
    <script>
        var errorDataSubmited="<ng:locale key="error.data.submited"/>";
    </script>
</head>

<body
	<decorator:getProperty property="body.id" writeEntireProperty="true"/>
	<decorator:getProperty property="body.class" writeEntireProperty="true"/>>
	<c:set var="currentMenu" scope="request">
		<decorator:getProperty property="meta.menu" />
	</c:set>
	<div class="header">
		<jsp:include
			page="/common/header_top.jsp?userCode=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}" />
		<jsp:include
			page="/common/header_mid.jsp?userCode=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}" />
		<jsp:include
			page="/common/top_nav.jsp?userCode=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}" />

	</div>
	
	<jsp:include page="/common/mi_reg_mask.jsp" />

	
	<div class="centerDiv clearfix">
		<jsp:include
			page="/common/left_sidebar.jsp?userCode=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}" />
		<div class="content fr">
		<%@ include file="/common/messages.jsp" %>
			<decorator:body />
		</div>
		<div class="clear"></div>
	</div>
	<!-- <script src="http://chat.53kf.com/kf.php?arg=zm2009&style=1&charset=utf-8&username=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userCode}-${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userName}"></script> -->
		 
    
	<div class="footer">
		<div class="centerDiv clearfix">
			<div class="fl">Login Time:<span><fmt:formatDate value="${loginCurDate }" pattern="yyyy-MM-dd HH:mm:ss"/></span>&nbsp;<span>(HongKong Time)</span></div>
			<div class="fr" style="margin-right:80px;">JM International&nbsp;2010-2015</div>
		</div>
	</div>



	<%=(request.getAttribute("scripts") != null) ? request
					.getAttribute("scripts") : ""%>
					
		
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
	</script>
	
	<div class="ax">
		<a href="${ctx}/foundationOrderform?1=1&img=11&fiNum=1&currentMenuId=2163654">
			<img src="${ctx}/images/365ax.png" width="96" height="96">
		</a>
	</div>


	</body>
</html>
