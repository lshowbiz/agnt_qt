
<%@ page contentType="text/html; charset=utf-8" language="java" %><%@ include file="/common/taglibs.jsp" %>

<style>

.popupPapernumber {
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
<script src="<c:url value='/dwr/interface/jmiMemberManager.js'/>" ></script>
	
<script>

function savePapaernumber(){
	var papernumber=$('#papernumber').val();
	if(papernumber==''){
		 var errorMsg = '<ng:locale key="errors.required" args="身份证" argTransFlag="true"/>';
		 alert(errorMsg);
		 return;
	}
	 var result=false;
	 dwr.engine.setAsync(false);
     jmiMemberManager.getCheckIdNoIndex(papernumber,function(data){result=data})
     dwr.engine.setAsync(true);

	if(result!=''){
		 alert(result);
		 return;
	}
	
	//alert(1111111);
	jmiMemberManager.savePapernumber(papernumber,callBack);
}


function callBack(valid){
	 alert("更新成功");
	 window.location.reload();
}
</script>
		
	
	

	<div class="mask" id="mask_if"></div>
<div class="popupPapernumber abs" id="popupPapernumber_if" style="display:none;">
		<h2>补充身份证</h2>
		
		
		

	
	<table width="100%" class="personalInfoTab">
		<colgroup style="width:100px;"></colgroup>
		<colgroup style="width:155px;"></colgroup>
		<tbody>
			
			<tr>
				<td class="tr">身份证：</td>
				<td colspan="2"><input type="text" name="papernumber" id="papernumber" class="w100" data-required="true" /></td>
			</tr>
			
			<tr>
				<td></td>
				<td >
				<button onclick="savePapaernumber()" id="js_confirm" type="button" >保&nbsp;存</button>
				</td>
				<td></td>
			</tr>
		</tbody>
	</table>

		
		
	</div>
	
