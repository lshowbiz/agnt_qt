
<%@ page contentType="text/html; charset=utf-8" language="java" %><%@ include file="/common/taglibs.jsp" %>

<style>

.popupUserName {
	width:400px;
	margin:50px auto;
	padding:10px;
	border:10px solid #CCC;
	border:10px solid rgba(0,0,0,0.2);
	-webkit-border-radius:10px;
	-moz-border-radius:10px;
	border-radius:10px;	
}
</style>	
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/jpoInviteListManager.js'/>" ></script>
	
<script>

function saveUserName(){
	var inviteCode=$('#inviteCode').val();
	if(inviteCode==''){
		 var errorMsg = '邀请码不能为空';
		 alert(errorMsg);
		 return;
	}
	 var result='';
	 dwr.engine.setAsync(false);
	 jpoInviteListManager.getCheckJpoInviteList(inviteCode,function(data){result=data})
     dwr.engine.setAsync(true);

	if(result!=''){
		 alert(result);
		 return;
	}
	jpoInviteListManager.useJpoInviteList(inviteCode,'${param.orderId}',callBack);
}


function callBack(valid){
		if(valid==true){
			alert("申请成功");
			 window.location.reload();
		}else{
			alert("申请失败");
		}
	 
}

function closeDialog()
{
	//document.getElementById("mask_if").style.display="none";
	document.getElementById("popupInviteCode_if").style.display="none";
	
}
</script>
		
	
	

	<div class="mask" id="mask_if"></div>
<div class="popupUserName abs" id="popupInviteCode_if" style="display:none;">
		<h2>邀请码填写</h2>
		
		
		

	
	<table width="100%" class="personalInfoTab">
		<colgroup style="width:100px;"></colgroup>
		<colgroup style="width:155px;"></colgroup>
		<tbody>
			
			<tr>
				<td class="tr">邀请码：</td>
				<td colspan="2"><input type="text" name="inviteCode" id="inviteCode" data-required="true" /></td>
			</tr>
		
			
			<tr>
				<td></td>
				<td>
				<button class="btn btn-info" href="javascript:void(0);"  id="js_confirm"  type="button" onclick="javascript:saveUserName();">保存</button>
				<button href="javascript:void(0);"  id="cancels"  onclick="closeDialog()" type="button" class="btn btn-success">取消</button>
				</td>
				<td></td>
			</tr>
		</tbody>
	</table>

		
		
		
	</div>
	
