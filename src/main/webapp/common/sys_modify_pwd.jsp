
<%@ page contentType="text/html; charset=utf-8" language="java" %><%@ include file="/common/taglibs.jsp" %>



	<div class="mask" id="mask_if"></div>
<div class="popupPwd abs" id="popupPwd_if" style="display:none;">
		<h2>由于您的密码过于简单，请修改密码</h2>
		
		
		
<form:form commandName="jsysUser" method="post" action="${ctx }/sysModifyPwd" name="sysUserEditForm" id="sysUserEditForm">
	<input type="hidden" name="strAction" value="sysModifyPwd" />
	
	<input type="hidden" name="returnUrl" value="loginform/showuserinfo" />
	
	<input type="hidden" name="passwordType" value="userPassword" />
	
	
	<table width="100%" class="personalInfoTab">
		<colgroup style="width:100px;"></colgroup>
		<colgroup style="width:155px;"></colgroup>
		<tbody>
			<%-- <tr>
				<td>&nbsp;</td>
				<td colspan="2">
					<input type="radio" value="userPassword" name="passwordType" id="userPassword" class="checkbox" checked="checked"/>
					<span><label for="userPassword"><ng:locale key="sysUser.userPassword"/></locale></label></span>
				</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td colspan="2">
					<input type="radio" value="password2" name="passwordType" id="password2" class="checkbox"/>
					<span><label for="password2"><ng:locale key="sysUser.password2"/></locale></label></span>
				</td>
			</tr> --%>
			<tr>
				<td class="tr"><ng:locale key="sysUser.oldPassword" />：</td>
				<td colspan="2"><input type="password" id="oldPassword" name="oldPassword"/></td>
			</tr>
			<tr>
				<td class="tr"><ng:locale key="sysUser.newPassword" />：</td>
				<td>
					<input type="password" id="newPassword" name="newPassword"  onKeyUp="pwStrength(this.value)" onBlur="pwStrength(this.value)"/>
				</td>
				<td>
					<table class="pwdLv" width="30%" border="1">  
						<tr>  

							<td width="33%" align="center" id="strength_L">弱</td>  

							<td width="33%" align="center"  id="strength_M">中</td>  

							<td width="33%" align="center"  id="strength_H">强</td>  
						</tr>  
					</table> 
				</td>
			</tr>
			<tr>
				<td class="tr"><ng:locale key="sysUser.repeatPassword" />：</td>
				<td colspan="2">
					<input type="password" id="newPassword2" name="newPassword2"/>
				</td>
			</tr>
			<tr>
				<td></td>
				<td >
				
			<!-- 	<a href="javascript:submitForm(sysUserEditForm);" class="btn_common corner2">保&nbsp;存</a> -->
				<button onclick="javascript:submitForm(sysUserEditForm);" id="js_confirm" type="button" >保&nbsp;存</button>
				</td>
				<td></td>
			</tr>
		</tbody>
	</table>

</form:form>
		
		
		
	</div>
	
	
<script>

function submitForm(theForm){
	
	
	if(validatePassword(theForm)){
		theForm.submit();
	}
	
	
}



function validatePassword(theForm){
	if(theForm.oldPassword.value==""){
		alert("<ng:locale key="please.input.oldPassword"/>");
		theForm.oldPassword.focus();
		return false;
	}
	
	if(theForm.newPassword.value==""){
		alert("<ng:locale key="please.input.newPassword"/>");
		theForm.newPassword.focus();
		return false;
	}
	if(theForm.newPassword2.value==""){
		alert("<ng:locale key="please.reInput.newPassword"/>");
		theForm.newPassword2.focus();
		return false;
	}
	if(theForm.newPassword.value!=theForm.newPassword2.value){
		alert("<ng:locale key="error.password.not.accord"/>");
		theForm.newPassword.focus();
		return false;
	}
	
	if(checkStrong(theForm.newPassword.value)<=1){
		alert("您的密码过于简单，请重新输入");
		return false;
	}
	return true;
}
</script>
		
	
	