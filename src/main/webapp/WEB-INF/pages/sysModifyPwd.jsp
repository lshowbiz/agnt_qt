<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>

<div class="cont" >	
<spring:bind path="jsysUser.*">
    <c:if test="${not empty status.errorMessages}">

    <div class="error" id="errorDiv" style="display: none">    
        <c:forEach var="error" items="${status.errorCodes}">
           <div> <c:out value="${error}" escapeXml="false"/></div>
        </c:forEach>
    </div>
    </c:if>
</spring:bind>

<form:form commandName="jsysUser" method="post" action="sysModifyPwd.html" name="sysUserEditForm" id="sysUserEditForm">

	<input type="hidden" name="strAction" value="sysModifyPwd" />
	
	<div class="bt mt">
			<h3 class="color2 ml">个人密码修改</h3>
	</div>
	

	<table class="form_edit_table" style="margin-top: 20px;margin-bottom: 100px;">
			<tr>
				<td style="width:30%;text-align:right;"><b style="font-size: 16px;">密码类型：</b></td>
				<td colspan="2"  style="padding:12px 0;">
					<label  for="userPassword">
						<input type="radio" value="userPassword" name="passwordType" id="userPassword" class="fl" checked="checked" style="margin-left: 33px; "/>
						&nbsp;&nbsp;<ng:locale key="sysUser.userPassword"/></locale>
					</label>
					<label class="fl" for="password2">
						<input type="radio" value="password2" name="passwordType" id="password2" class="fl" style="margin-left: 33px; "/>
						&nbsp;&nbsp;<ng:locale key="sysUser.password2"/></locale>
					</label>
				</td>
			</tr>
			
			<tr>
				<td class="tr"><b style="font-size: 16px;"><ng:locale key="sysUser.oldPassword" />：</b></td>
				<td colspan="2"  style="padding:12px 0;"><input type="password" id="oldPassword" name="oldPassword" style="height: 35px;margin-left: 33px; width:300px;"/></td>
			</tr>
			<tr>
				<td class="tr"><b style="font-size: 16px;"><ng:locale key="sysUser.newPassword" />：</b></td>
				<td style="width: 210px;padding:12px 0;">
					<input type="password" id="newPassword" name="newPassword"  onKeyUp="pwStrength(this.value)" onBlur="pwStrength(this.value)" style="height: 35px;margin-left: 33px; width:300px;"/>
				</td>
				<td style="margin-left: 10px;" align="center">
					<table  width=120  border="1" align="center" style="text-align: center;">  
						<tr>  

							<td  id="strength_L" style="text-align: center;">弱</td>  

							<td  id="strength_M" style="text-align: center;">中</td>  

							<td  id="strength_H" style="text-align: center;">强</td>  
						</tr>  
					</table> 
				</td>
			</tr>
			<tr>
				<td class="tr"><b style="font-size: 16px;"><ng:locale key="sysUser.repeatPassword" />：</b></td>
				<td colspan="2" style="padding:12px 0;">
					<input type="password" id="newPassword2" name="newPassword2" style="height: 35px;margin-left: 33px;width:300px; "/>
				</td>
			</tr>
			
			<tr>
				<td ></td>
				<td colspan="2">
					<button type="button"  onclick="javascript:submitForm(sysUserEditForm);" style="margin-left: 30px; margin-top: 10px;">保&nbsp;存</button>
				</td>
			</tr> 
			
		</tbody>
	</table>
	<!-- <div class="tc"></div> -->

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
		
		
		
		