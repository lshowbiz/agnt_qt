<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp" %>

<head>
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="icon" href="<c:url value="/images/favicon.ico"/>" />
<title><ng:locale key="webapp.name.qt" /></title>
	<link rel="stylesheet" type="text/css" media="all"	href="<c:url value='/styles/style-ng.css'/>" />
    <link rel="stylesheet" href="<c:url value='/styles/index/style.css'/>" />
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
        .step li.step-line { width: 253px; height: 2px; margin-top: 12px; background-color: #C6C6C6;}
        .step li.step-line-h { width: 253px; height: 2px; margin-top: 12px; background-color: #F08200;}
        .tabPswd { width:300px; margin:10px auto;}
        .tabPswd input { height:26px; line-height:26px;margin:5px 10px 5px 0;}
        body{background:#fff; margin:0 auto; width:1003px; margin-left:auto; margin-right:auto; }
    </style>
</head>

<center>
	<div class="header">
		<div class="header_mid">
			<div class="centerDiv clearfix">
				<div class="mt20"><a href="index.html"><img src="${ctx }/images/index/logo.gif" alt="JoyLife" /></a></div>
			</div>
		</div>
	</div>
	<div class="main centerDiv">
         <h2 class="color2"><ng:locale key="passwordReset.mesg" /></h2>
        <ul class="step">
            <li class="step-1"></li>
            <li class="step-line-h"></li>
            <li class="step-2-h"></li>
            <li class="step-line"></li>
            <li class="step-3"></li>
        </ul>
        <div>
   <spring:bind path="jmiMember.*"></spring:bind>
    <form:form  commandName="jmiMember"  method="post" action="passwordReset/sendMessage" name="sendMessage" id="sendMessage"> 
         <table class="tabPswd">
        	<form:hidden  name="userCode" path="userCode"/> 
            <colgroup style="width:100px;"></colgroup>
             <colgroup></colgroup>
                    <colgroup></colgroup>
            <tbody>
                <tr>
                    <td style="padding:10px 0;"><ng:locale key="select.checkType"/></td>
                    <td style="padding:10px 0;">
                        <select class="mySelect">
                            <option value="0" checked="checked"><ng:locale key="sysUser.mobiletele" /></option>
                        </select>
                    </td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td style="padding:10px 0;"><ng:locale key="sysUser.userCode" />：</td> 
                    <td style="padding:10px 0;">${jmiMember.userCode} </td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td style="padding:10px 0;"><ng:locale key="sysUser.mobiletele" />：</td>
                    <td style="padding:10px 0;">${jmiMember.mobiletele}</td>
                    <td>&nbsp;</td>
                </tr>
                 <tr>
                     
                     <td colspan="3" class="tc"  style="padding:10px 0;">
 						 <button href="javascript:submitForm(sendMessage);"  style="background-color:#f39800;border:1px solid #f39800"><ng:locale key="send.message"></ng:locale> </button> 
                     </td>
                 </tr>
            </tbody>
        </table>
    </form:form>
    </div>
	</div>
	<div class="">
		<div class="centerDiv clearfix">
			<div class="fl">Login Time:<span><fmt:formatDate value="${loginCurDate }" pattern="yyyy-MM-dd HH:mm:ss"/></span>&nbsp;<span>(Hong Kong Time)</div>
			<div class="fr"><ng:locale key="zhongmai.copyright"></ng:locale>&nbsp;2010-2015</div>
		</div>
	</div>
</center>
<script type="text/javascript">
<!--
  function submitForm(theForm){
	alert("短信发送中，请稍后留意短信查收......");
	theForm.submit();
}
//-->
</script>






















