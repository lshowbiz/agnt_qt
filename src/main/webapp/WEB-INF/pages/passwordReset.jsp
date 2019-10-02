<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ include file="/common/messages.jsp" %>

<head>
    <%-- <title><ng:locale key="pwd.reset"></ng:locale></title> --%>
    <title><ng:locale key="webapp.name.qt" /></title>
    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="<c:url value="/images/favicon.ico"/>" />
    <link rel="stylesheet" href="<c:url value='/styles/style-ng.css'/>" />
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
            <li class="step-line"></li>
            <li class="step-2"></li>
            <li class="step-line"></li>
            <li class="step-3"></li>
        </ul>
      
        <div>
            <form:form commandName="jmiMember" method="post" action="passwordReset" name="passwordReset" id="passwordReset">
                <table class="tabPswd">
                    <colgroup style="width:100px;"></colgroup>
                    <colgroup></colgroup>
                    <colgroup></colgroup>
                    <tbody>
                        <tr>
                            <td class="tr"><ng:locale key="sysUser.userCode" />：</td>
                            <td><form:input type="text" id="userCode" path="userCode"/></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="tr"><ng:locale key="miMember.papernumber" />：</td>
                            <td><form:input path="papernumber" id="papernumber" cssClass="formerror" /></td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="tr"><ng:locale key="sysUser.mobiletele" />：</td>
                            <td>
                                <form:input type="text" id="mobiletele" path="mobiletele"/>
                            </td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="tr"><ng:locale key="sysUser.validateCode"/>：</td>
                            <td>
                                <input type="text" style="width:45%" name="verifyCode"/><img style="vertical-align:middle;" src="<c:url value="/passwordReset/generateverifycode"/>?rnd=<%=Math.random() * 1000000%>" />
                            </td>
                            <td>&nbsp;</td>     
                        </tr>
                        <tr>
                        	<td>&nbsp;</td>                     	
                        </tr>
                        <tr>                                                  
                            <td colspan="3" class="tc"><button type="button"  onclick="javascript:submitForm(passwordReset);"style="background-color:#f39800;border:1px solid #f39800"><ng:locale key="button.submit"></ng:locale></button>
                            <button type="button" onclick="tohref('${ctx}/login.jsp');" class="ml" ><ng:locale key="operation.button.return"></ng:locale></button></td>
                        </tr>
                    </tbody>
                </table>
            </form:form>
        </div>
    </div>
	<div>
		<div class="centerDiv clearfix">
			<div class="fl">Login Time:<span><fmt:formatDate value="${loginCurDate }" pattern="yyyy-MM-dd HH:mm:ss"/></span>&nbsp;<span>(Hong Kong Time)</div>
			<div class="fr"><ng:locale key="zhongmai.copyright"/>&nbsp;2010-2015</div>
		</div>
	</div>
</center>
	
    <script>
    function submitForm(theForm){
        if(validatePassword(theForm)){
            theForm.submit();
        }
    }

    function validatePassword(theForm){
        if(theForm.userCode.value==""){
            alert("<ng:locale key="please.input.userCode"/>");
            theForm.userCode.focus();
            return false;
        }

         if(theForm.papernumber.value==""){
            alert("<ng:locale key="please.input.papernumber"/>");
            theForm.papernumber.focus();
            return false;
        }
        if(theForm.mobiletele.value==""){
            alert("<ng:locale key="please.reInput.mobiletele"/>");
            theForm.mobiletele.focus();
            return false;
        }
        return true;
    }
    
    function tohref(tourl) {
    	top.window.location.href=tourl;
	}
	</script>	






















