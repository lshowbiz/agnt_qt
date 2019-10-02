<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp" %>

<head>
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" href="<c:url value="/images/favicon.ico"/>" />
<%-- <title><ng:locale key="pwd.reset"></ng:locale> | <fmt:message key="webapp.name" /></title> --%>
<title><ng:locale key="webapp.name.qt" /></title>
	<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/styles/style-ng.css'/>" />
	
    <style>
        
        .main { height: 500px; width:1003px; margin:20 auto; border: 1px solid #ea4e41;}
        .main h2 { height: 36px; line-height: 36px; text-indent: 24px; font-size: 14px; 
           background: #f5f5f5;}
            
        .step { width: 674px; margin: 40px auto; overflow: hidden;}
        .step li { float: left;}
        .step li { width: 56px; height: 55px;}
        .step li.step-1 {  background: url("images/bg_step_1.jpg") left center no-repeat;}
        .step li.step-2 {  background: url("images/bg_step_2.jpg") left center no-repeat;}
        .step li.step-2-h {  background: url("images/bg_step_2_h.jpg") left center no-repeat;}
        .step li.step-3 {  background: url("images/bg_step_3.jpg") left center no-repeat;}
        .step li.step-3-h {  background: url("images/bg_step_3_h.jpg") left center no-repeat;}
        .step li.step-line { width: 253px; height: 2px; margin-top: 12px; background-color: #C6C6C6;}l
        .step li.step-line-h { width: 253px; height: 2px; margin-top: 12px; background-color: #F08200;}
        .pswdForm { width: 305px; margin: 0 auto;}
        .pswdForm input { margin: 10px 0;}
        .pswdForm td { height: 38px; line-height: 38px;}
        .promptInfo { width: 200px; height: 48px; margin: 0 auto; padding-left: 60px; background: url("images/icon_success.gif") left center no-repeat;}
        .promptInfo p { line-height: 24px; height: 24px;}
        .promptInfo a {}
    </style>
</head>

<center>
	<div class="header">
		<div class="header_mid">
			<div class="centerDiv clearfix" style="margin-top: 20px;">
				<div class="mt20"><a href="index.html"><img src="${ctx }/images/index/logo.gif" alt="JoyLife" /></a></div>
			</div>
		</div>
	</div>
	<div class="main centerDiv">
        <h2 style="color:#cf3638"><ng:locale key="passwordReset.mesg" /></h2>
        <ul class="step">
            <li class="step-1"></li>
            <li class="step-line-h"></li>
            <li class="step-2-h"></li>
            <li class="step-line-h"></li>
            <li class="step-3-h"></li>
        </ul>
        <div class="promptInfo" style="width:500px;height:200px;font-size:16px;">
            <p class="colorCS bold"> <ng:locale key="passwordReset.success"/></p>
            <div class="ft12" style="margin-top: 20px;margin-bottom: 16px;font-size: 16px;">
            	<p><ng:locale key="passwordReset.remember"></ng:locale> 
            	<a style="text-decoration:underline;line-height:24px;vertical-align:top" href="index.html" class="colorAH"><ng:locale key="menu.login"></ng:locale> </a>
            	</p>
            </div>
        </div>
	</div>
	<div class="">
		<div class="centerDiv clearfix">
			<div class="fl">Login Time:<span><fmt:formatDate value="${loginCurDate }" pattern="yyyy-MM-dd HH:mm:ss"/></span>&nbsp;<span>(Hong Kong Time)</div>
			<div class="fr"><ng:locale key="zhongmai.copyright"></ng:locale> &nbsp;2010-2015</div>
		</div>
	</div>
</center>
</html>





















