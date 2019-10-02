<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<html lang="en-US">
<head>
    <title><ng:locale key="webapp.name.qt" /></title>
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <meta charset="UTF-8"/>
    <link rel="stylesheet" href="<c:url value='/styles/style-ng.css'/>" />
    <link rel="stylesheet" href="<c:url value='/styles/lib/bootstrap.min.css'/>" />
    <link href="<c:url value='/styles/index/style.css'/>" rel="stylesheet" type="text/css">
    <style>

	.login-logo {margin-bottom:30px;}
    .loginContainer{ background: url("images/login_bg.png") no-repeat center center fixed;
     -webkit-background-size: cover;
     -moz-background-size: cover;
     -o-background-size: cover;
    background-size: cover;
 }
    .loginContainer-img{margin:0 auto; padding:0;text-align:center; }
    .loginForm { border-radius:5px; width:220px;  position:absolute; right:36%; top:20%; -ms-margin-top:20% }
    .loginForm label { font-weight:normal; margin-bottom:-3px; *line-height:60px;}
   .user,.pswd,.chkCode{ margin-top:5px; width: 220px; }
    .loginForm input.chkCode { width:60px; margin-right:10px;}
    #rememberMe {width: 14px;border: none;background: none;}
    .loginFooter{position:absolute; bottom:0px; right:10%; line-height:30px;}
    </style>
    <script src="<c:url value='/scripts/lib/jquery-1.8.2.min.js'/>"></script>
</head>
<%
	//验证码判断
	String err = request.getParameter("urlErr");
	if("3".equals(err)){
		pageContext.setAttribute("urlErr","3");
	}
 %>
<body onload="refreshCaptcha();" class="loginContainer">
<!-- --- <div class="loginContainer-img"><img src="images/login_img.png"></div>-->

	<form method="post" id="loginForm" name="loginForm"
		action="<c:url value='/j_security_check'/>"
		onsubmit="return validateLogin(this);" class="form-signin"
		autocomplete="off">
	
		

			<div class="centerDiv">				
				<div class="loginForm fr">
												
						<div class="login-logo"><a href="index.html"><img style="width:220px" src="images/login-logo.png" alt="JM International" /></a></div>

							<p><label class="ft14 "><ng:locale key="sysOperationLog.operaterName" />：</label></p>
							<p><input style="width:220px" type="text" class="glyphicon glyphicon-user" name="j_username" id="j_username" value="${userCode }"/></p>
						
						
							<p><label class="ft14 "><ng:locale key="sysUser.password" />：</label></p>
							<p><input  style="width:220px" type="password" class="pswd" name="j_password" id="j_password" /></p>
						
					

							<p><label class="ft14 "><ng:locale key="login.verifyCode" />：</label></p>
							<p><input  class="fl" style="width:130px"type="text" class="chkCode" name="j_captcha"  id="j_captcha" />
							<img class="fr" style="vertical-align: middle; height:28px; border:1px solid #ccc;margin-top:4px" id="captchaImg" src="${ctx}/images/jcaptcha.jpg" onclick="javascript:refreshCaptcha()"/> 
							<!-- <td><img style="vertical-align: middle;" id="captchaImg" src="${ctx}/images/jcaptcha.jpg" onclick="javascript:refreshCaptcha()"/>-->
							</p>

           
               
                        	<p class="fl">
                        		<c:choose>
									<c:when test="${urlErr=='1'}"><font color="red"><b>用户名不存在！</b></font></c:when>
									<c:when test="${urlErr=='2'}"><font color="red"><b>登录密码不正确！</b></font></c:when>
									<c:when test="${urlErr=='3'}"><font color="red"><b>验证码不正确！</b></font></c:when>
									<c:when test="${urlErr=='4'}"><font color="white"><b>.</b></font></c:when>
								</c:choose>
                        	</p>
                     
					
							<p>
                                <a href="<c:url value="/passwordReset"/>"  class="ft12 colorCS fr"><ng:locale key="forget.pass" /></a>
                            </p>
	
							<p class="mt">														
	                  			<button type="submit" class="ft18 mt btn btn-info" style="width:220px" value="<ng:locale key="menu.login"/>">登&nbsp;&nbsp;录</button>						
							</p>																		
						
						
													
							<p class="mt"><a href="http://www.joylifeglobal.cn/" class="ft12 colorGL"> JM International></a>	</p>
				
			</div>
			
			<div class="loginFooter">
				<div class="centerDiv">
					<p class="tr ft12" >JM International 2010-2017</p>
				</div>
			</div>
		</div>
	
	</form>
</body>
<script>
document.getElementById("j_username").focus();
function validateLogin(theForm){
	var reg = /^\s*$/;
	if(reg.test(theForm.j_username.value)){
		alert("<ng:locale key='sysUser.userCode.required'/>");
		theForm.j_username.focus();
		return false;
	}
	if(reg.test(theForm.j_password.value)){
		alert("<ng:locale key='sysUser.password.required'/>"); 
		theForm.j_password.focus();
		return false;
	}
	if(reg.test(theForm.j_captcha.value)){
		alert("<ng:locale key='login.verifyCode.null'/>"); 
		theForm.j_captcha.focus();
		return false;
	}
	return true;
}
function refreshCaptcha() {
	$('#captchaImg').hide().attr('src','${ctx}/jcaptcha.jpg?' + Math.floor(Math.random()*100)).fadeIn();
}
</script>
</html>