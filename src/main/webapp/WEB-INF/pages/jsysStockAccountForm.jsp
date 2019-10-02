<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
	<style type="text/css">
		.td_style{text-align:right;}
	</style>
</head>
<script type="text/javascript">
	$(document).ready(function(){
		if(${status=='success'}){
			alert("保存成功");
		}
	});
	
	function checkForm(){
		var stockAccount = $("#stockAccount").val();
		var feeStatus = $("#feeStatus").val();
		if(stockAccount==''){
			alert("港股账号不能为空");
			return false;
		}
		if(feeStatus==''){
			alert("是否汇入手续费不能为空");
			return false;
		}
		return true;
	}
</script>

        
<div class="cont">	
			<div class="bt mt">
				<h3 class="color2 ml">港股账号信息</h3>
			</div>	  
			
<!-- <div class="content fr">
	<h2 class="title mgb20">港股账号信息</h2> -->
	<form:form id="stockAccountForm" action="${ctx}/jsysStockAccount/saveStockAccount" method="post" onsubmit="return checkForm();">
		<input type="hidden" id="id" name="id" value="${stockAccount.id}" />
		<input type="hidden" id="userCode" name="userCode" value="${stockAccount.userCode}" />
		<table class="form_edit_table" style="margin-top: 40px;margin-bottom: 100px;">
			<tr>
				<td style="width:40%;text-align:right;"><b style="font-size: 16px;">会员编号：</b></td>
				<td style="padding:14px 0;"><span style="margin-left: 33px; width:300px;font-size:14px;">${stockAccount.userCode}</span></td>
			</tr>
			<tr>
				<td class="tr"><b style="font-size: 16px;">会员姓名：</b></td>
				<td style="padding:14px 0;"><span style="margin-left: 33px; width:300px;font-size:14px;">${stockAccount.userName}</span></td>
			</tr>
			<tr>
				<td class="tr"><b style="font-size: 16px;"><label class="star">是否已汇入手续费：</b></td>
				<td style="padding:14px 0;">
					<span style="margin-left: 33px; width:300px;">
						<ng:list id="feeStatus" name="feeStatus" listCode="yesno" defaultValue="" value="${stockAccount.feeStatus}" showBlankLine="true" style="height:30px;width:8%;"></ng:list>
					</span>
				</td>
			</tr>
			<tr>
				<td class="tr"><b style="font-size: 16px;"><label class="star">港股账号：</b></td>
				<td  colspan="2"  style="padding:14px 0;">
					<input id="stockAccount" name="stockAccount" value="${stockAccount.stockAccount}" maxlength="50" style="height: 45px;margin-left: 33px; width:300px;"/>
				</td>
			</tr>
			
			<tr>
				<td></td>
				<td>
					<!-- <input type="submit" value="保&nbsp;&nbsp;存" class="btn_common corner2" /> -->
					
					 <button type="submit" style="margin-left: 30px; margin-top: 10px;">&nbsp;<span>保&nbsp;&nbsp;存</span>&nbsp;</button>
					 <button type="button" style="margin-left: 10px; margin-top: 10px;" 	class="btn btn-success" onclick="history.go(-1)" >&nbsp;<span>返&nbsp;回</span>&nbsp;</button>
				</td>
			</tr>
		</table>
	</form:form>
</div>