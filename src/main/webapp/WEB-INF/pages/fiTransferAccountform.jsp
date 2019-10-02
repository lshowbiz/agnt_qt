<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>
<%
String currentMenuId = (String)session.getAttribute("currentMenuId");
String currentSubMenuId = (String)session.getAttribute("currentSubMenuId");
%>
<head>
    <meta name="heading" content="<fmt:message key='fiTransferAccountDetail.heading'/>"/>
    <style>
.ordersDetails td { height:45px; line-height:45px;}
.ft-green {
	COLOR: #41ab01
}
.div-t{
	width:100%;background-color: #f4f6fc;padding-left: 40px;padding-top: 15px;padding-bottom: 20px;
}
</style>
</head>

<div class="cont">	
	<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
	
	<form:form commandName="fiTransferAccount" method="post" action="fiTransferAccountform" 
	           id="fiTransferAccountForm" name="fiTransferAccountForm" onsubmit="return validateOthers()">
		    <div class="bt mt">
				<h3 class="color2 ml">会员转账申请</h3>
			</div>
                <table class="form_edit_table" style="margin-top: 20px;margin-bottom: 100px;">
            
                      	<tr>
		                <td>
		                	<label for="" class="star">电子存折帐户:</label>
		                </td>
		                <td>${jsysUser.userCode }</td>
		                <td width="100"><label for="" class="star">帐户余额:</label></td>
                        <td><b class="ft-green">${bankbook }</b>元</td>
                        <td></td>
                        <td></td>
		            </tr>
                       <tr>
                           <td width="100"><label for="" class="star">收款会员编号:</label></td>
                           <td width="200"><form:input path="destinationUserCode" id="destinationUserCode" cssClass="formerror"/></td>
                           <td width="100"></td>
                           <td></td>
                           <td></td>
                           <td></td>
                       </tr>
                       <tr>
                           <td><label for="" class="star">金额:</label></td>
                           <td><form:input path="money" id="money" cssClass="formerror" 
                           		onkeyup="if(isNaN(this.value)){this.value=''; return;}this.value=/^\d+\.?\d{0,2}$/.test(this.value) ? this.value : this.value.substring(0,this.value.indexOf('.')+3)"/></td>
                           <td></td>
                           <td></td>
                           <td></td>
                           <td></td>
                       </tr>
                       <tr>
                           <td><label for="" class="star">支付密码:</label></td>
                           <td><input type="password" name="paypassword" id="paypassword"  class="text medium"/>&nbsp;(高阶密码)</td>
                           <td></td>
                           <td></td>
                           <td></td>
                           <td></td>
                       </tr>
                       <tr>
                           <td><label for="">&nbsp;&nbsp;摘要:</label></td>
                           <td>
                            <form:textarea path="notes" id="notes" rows="3" cols="50" htmlEscape="true"/>
                           </td>
                           <td></td>
                           <td></td>
                           <td></td>
                           <td></td>
                       </tr>
                       
                       <tr>
                           <td></td>
                           <td>
                           <table>
                           	<tr>
                           		<td align="left">
                            <button type="submit" name="save" onclick="bCancel=false">
				            	<i class="icon-ok icon-white"></i>&nbsp;确认提交&nbsp;
					        </button></td>
				        	<td>
					        <button id="quxiao" type="button" style="margin-left: 5px" class="btn btn-success" onclick="javascript:history.go(-1);">取消</button>
					    </td>
                           	</tr>
                           </table>
                            
				        </td>
                </table>
         <div class="clear"></div>
     </form:form>
</div>
<script type="text/javascript">

    var checkSubmitFlg = false;

    function validateOthers(){
  	
  		if (!checkSubmitFlg) {
  		
  			var destin_user=document.fiTransferAccountForm.destinationUserCode;
	  		if(destin_user.value==""){
				alert("收款会员编号不能为空");
				destin_user.focus();
				return false;
			}
			
			var paymoney=document.fiTransferAccountForm.money;
			if(paymoney.value==""){
				alert("金额不能为空");
				paymoney.focus();
				return false;
			}
			if(paymoney.value <= 0){
				alert("金额必须大于0");
				paymoney.focus();
				return false;
			}
			var regu = "^[0-9]+[\.][0-9]{0,3}$"; 
			var re = new RegExp(regu); 
			
			var regu2 = "^[0-9]+$"; 
			var re2 = new RegExp(regu2); 
			if(re.test(paymoney.value)!=true && paymoney.value.search(re2) == -1){
				
				alert("金额不符合要求");
				paymoney.focus();
				return false;
			}
	
			if(document.fiTransferAccountForm.paypassword.value==""){
				alert("支付密码不能为空");
				document.fiTransferAccountForm.paypassword.focus();
				return false;
			}
  						
			//if(window.confirm('<jecs:locale key="transfer.sure"/>'+paymoney+'<jecs:locale key="transfer.to"/>'+destin_user.value+'?')){
			if(window.confirm("您确定从电子存折账户转账"+paymoney.value+"给会员"+destin_user.value+'?')){
				//document.getElementById('baocunId').disabled='disabled';
				checkSubmitFlg = true;
				//document.getElementById("save").disabled=true;
				return isFormPosted();
			}else{
			
				return false;	
			}
		}else {

			//重复提交
			alert("请勿重复提交!");
			return false;
		}
	}
</script>    

<v:javascript formName="fiTransferAccount" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['fiTransferAccountForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
        
    });
</script>
