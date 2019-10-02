<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String currentMenuId = (String)session.getAttribute("currentMenuId");
String currentSubMenuId = (String)session.getAttribute("currentSubMenuId");
%>

<link
	href="${pageContext.request.contextPath}/scripts/easyui/css/easyui.css"
	rel="stylesheet" type="text/css" />
<link
	href="${pageContext.request.contextPath}/scripts/easyui/css/icon.css"
	rel="stylesheet" type="text/css" />
<script
	src="${pageContext.request.contextPath}/scripts/easyui/jquery.easyui.min.js"
	type="text/javascript"></script>
<script
	src="${pageContext.request.contextPath}/scripts/easyui/locale/easyui-lang-zh_CN.js"
	type="text/javascript"></script>




<spring:bind path="jbdSendNote.*">
	<c:if test="${not empty status.errorMessages}">
		<div class="error" id="errorDiv" style="display: none">
			<c:forEach var="error" items="${status.errorCodes}">
				<div>
					<c:out value="${error}" escapeXml="false" />
				</div>
			</c:forEach>
		</div>
	</c:if>
</spring:bind>

<div class="cont mt mr">	
    <div class="bt mt">
		<h3 class="color2 ml">提现</h3>
	</div>
<form:form commandName="jbdSendNote" method="post"
	action="jbdSendNoteform" name="sysUserEditForm" id="sysUserEditForm">
	<input type="hidden" name="time" value="${time }" />
	<input type="hidden" name="bankcardModify" id="bankcardModify"
		value="${bankcardModify }" />	
		<table class="form_edit_table" style="margin-top: 80px;margin-bottom: 200px;">

			<tbody>

				<tr>
					<td style="width:30%;text-align:right;">
						<label class="pdl10" style="font-size:18px;">
							余额:
						</label>
					</td>
					<td colspan="4">
						<a href="#" onclick="getTotalAmout();" style="text-decoration: underline;margin-left: 30px;">
							<font size="4"
								color="green"><b>${amountTotal }</b>
							</font>
						</a>
						<input type="hidden" id="amountTotal" name="amountTotal"
							value="${amountTotal }" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="red"><b>（点击金额可全额提现）</b>
						</font>
					</td>
				</tr>

				<tr>
					<td align="right"  style="padding:33px 0;">
						<label class="star" style="font-size:14px;">
							<ng:locale key="miMember.pwd2" />
							:
						</label>
					</td>
					<td colspan="4">
						<input name="password" id="password" type="password" value="" style="margin-left: 30px; height: 35px;"/>

					</td>
				</tr>

				<tr>
					<td align="right" width="100">
						<label class="star" style="font-size:14px;">
							<ng:locale key="busi.order.amount" />
							:
						</label>
					</td>
					<td width='100'>
						<input name="amount1" id="amount1" type="text" value="0" style="margin-left: 30px; height: 35px;"/>
					<td width='10'>
						<span style="margin-top: 25px; font-size: 30px;"><b>.</b></span>
					</td>
					<td width='100'>
						<input name="amount2" id="amount2" type="text" value="0" size="2" maxlength="1" style="height: 35px;"/>
					</td>
					<td style="text-align:left;">
						<input name="amount3" id="amount3" type="text" value="0" size="2" maxlength="1" style="margin-left: 3px; height: 35px;"/>
					</td>
				</tr>

				<tr>
					<td></td>
					<td  colspan="4">
						<!-- <a href="#" onclick="checkForm();" class="btn_common corner2">保&nbsp;存</a> -->
						<button id="baocunId" type="button" style="margin-left: 30px; margin-top: 20px;" onclick="checkForm();" >保存</button>
						
						<button id="quxiao" type="button" style="margin-left: 20px; margin-top: 20px;" class="mt btn btn-success" onclick="javascript:history.go(-1);">取消</button>
					</td>
				</tr>
			</tbody>
		</table>
</form:form>

</div>




<script language="javascript">

function getTotalAmout(){
	var amountTotal = document.getElementById("amountTotal").value;
	var amount1 = document.getElementById("amount1");
	var amount2 = document.getElementById("amount2");
	var amount3 = document.getElementById("amount3");
	var amountTotal=amountTotal+"";
	
	var amountTotals=new Array();
	
	amountTotals=amountTotal.split(".");
	
	if(amountTotals.length>1){
		if(amountTotals[1].length==1){
			amount2.value = amountTotals[1].substr(0,1);
		}else{
			amount2.value = amountTotals[1].substr(0,1);
			amount3.value = amountTotals[1].substr(1,2);
		}
	}
	amount1.value=amountTotals[0];
}



var bCancel1 = true;
function checkForm(){
if(bCancel1==true){
	var amount1 = document.getElementById("amount1").value;
	var amount2 = document.getElementById("amount2").value;
	var amount3 = document.getElementById("amount3").value;
	var extReg1 = /^(0|[1-9]\d*)?$/;
	if(amount1 ==""){
		alert('<ng:locale key="errors.required" args="busi.order.amount" argTransFlag="true"/>');
		return false;
	}
	if(!amount1.match(extReg1)){
		alert('<ng:locale key="errors.invalid" args="busi.order.amount" argTransFlag="true"/>');
		return false;
	}
	var extReg2 = /^(0|[1-9])?$/;
	if(amount2 =="" || amount3 ==""){
		alert('<ng:locale key="errors.required" args="busi.order.amount" argTransFlag="true"/>');
		return false;
	}
	if(!amount2.match(extReg2) || !amount3.match(extReg2)){
		alert('<ng:locale key="errors.invalid" args="busi.order.amount" argTransFlag="true"/>');
		return false;
	}
	if(Number(amount1) + Number(amount2 / 10) + Number(amount3 / 100)<=3){
		alert('<ng:locale key="errors.invalid" args="busi.order.amount" argTransFlag="true"/>');
		return false;
	}
}
	if(isFormPosted()){
		document.getElementById('baocunId').disabled='true';
		document.sysUserEditForm.submit();
	}
}


bankMsg();
function bankMsg(){
	var bankcardModify = document.getElementById("bankcardModify").value;
	$.messager.defaults = { ok: "确认", cancel: "忽略" };
	if('1'==bankcardModify){
	    $.messager.confirm("系统提示", "亲爱的家人：您好！为保证提现成功，请您尽快完善您的银行账户信息。如您已经完善请忽略。", function (data) {
	        if (data) {
	        	window.location="jmiBankInformationChange";
	        }
	        else {
	            //alert("否");
	        }
	    });
	}
	if('2'==bankcardModify){
		$.messager.confirm("系统提示", "亲爱的家人：您好！请您拨打客服热线4001839999提交资料修改申请。如您已修改成功请忽略。", function (data) {
	        if (data) {
	        	
	        }
	        else {
	            //alert("否");
	        }
	    });
	}
}
</script>















