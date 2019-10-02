<%@ page contentType = "text/html; charset=utf-8" language = "java"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
</head>

<body>
<script src="<c:url value='/scripts/dialogBox.js'/>"></script>	
<script>
  
function switchAll() {
	var selectedAll=document.getElementsByName("selectedAll");
		var selectedId=document.getElementsByName("selectedId");
	if(selectedId!=undefined){
		for(var i=0;i<selectedId.length;i++){
			selectedId[i].checked=selectedAll[0].checked;
		}
	}
}
  
function tipsSelect(){
	
	var elements=document.getElementsByName("selectedId");

	if(elements==undefined){
		alert("<ng:locale key="please.select.bdSendRegister"/>");
		return false;
	}
	var selectedOne=false;
	for(var i=0;i<elements.length;i++){
		if(elements[i].checked){
			selectedOne=true;
			//theForm.bonusStr.value+=","+elements[i].value;
		}
	}
	
	if(!selectedOne){
		alert("<ng:locale key="please.select.bdSendRegister"/>");
		return false;
	}
	
	
	
	var con = 	'<div style="padding:10px;">'+
					'<h2 class="title">会员奖金领取方式</h2>' +
					'<p style="padding:2em;"><a href="javascript:registerSuccess(jbdMemberLinkCalcHist,1);" class="btn_common corner2">方式一</a>（注：奖金90%进入电子存折，10%转发展基金）</p>'+
					'<p style="padding:2em;"><a href="javascript:registerSuccess(jbdMemberLinkCalcHist,2);" class="btn_common corner2">方式二</a>（注：奖金120%转发展基金）</p>'+
				'</div>';

	MyDialog({
		boxContent 	: con,
		title 		: false,
		okBtn 		: false,
		noBtn 		: true,
		boxWidth	: 600,
        boxHeight	: 'auto'
	});
	
}
  
  
  

function registerSuccess(theForm,sendTypeSelect){

	var tips="尊敬的会员您好，您是否确认选择方式一，奖金90%进电子存折，10%转发展基金";
	
	if(sendTypeSelect=='2'){
		tips="尊敬的会员您好，您是否确认选择方式二，奖金120%进发展基金";
	}
	
	
	if(confirm(tips))
	{
		var elements=document.getElementsByName("selectedId");

		if(elements==undefined){
			alert("<ng:locale key="please.select.bdSendRegister"/>");
			return false;
		}
		var selectedOne=false;
		for(var i=0;i<elements.length;i++){
			if(elements[i].checked){
				selectedOne=true;
				theForm.bonusStr.value+=","+elements[i].value;
			}
		}
		
		if(!selectedOne){
			alert("<ng:locale key="please.select.bdSendRegister"/>");
			return false;
		}
		theForm.strAction.value="sendBonusType";
		theForm.sendTypeSelect.value=sendTypeSelect;
				if(isFormPosted()){
					theForm.submit();
				}
	}
	
}
  
  
  
  
</script>

<div class="cont">	
	<div class="bt mt">
		<h3 class="color2 ml">奖金领取方式</h3>
	</div>
	
	<div>
		<form action="" method="get" name="jbdMemberLinkCalcHist" id="jbdMemberLinkCalcHist">
		<input type="hidden" name="bonusStr" value=""/>
		<input type="hidden" name="sendTypeSelect" id="sendTypeSelect" value=""/>
		<input type="hidden" name="strAction" id="strAction" value=""/>
			<table class="search_table mt">
				<tbody>
					<tr>
						<td width="60px"><ng:locale key="bdBounsDeduct.wweek"/>：</td>
						<td width="260px"><input name="wweek" id="wweek" type="text" value="${param.wweek }" size="8" 
						onkeyup="this.value=this.value.replace(/\D/g,'')"/>(如：201301)</td>
						<%-- <td>发放状态：</td>
						<td><ng:list name="" listCode="" defaultValue="" value=""></ng:list>  </td>
						<td>选择方式：</td>
						<td></td>  --%>
					   	<td width="100px">
							<button name="search" type="submit" class="" 
							onclick="loading('<ng:locale key="button.loading"/>');" ><ng:locale key="operation.button.search"/></button>
						</td>
					   	<td width="100px">
							<button name="getBonusId" id="getBonusId" onclick="tipsSelect();" type="button" 
							class="btn btn-success">申请领取</button>
						</td>
						<td>&nbsp;</td>
					</tr>
				</tbody>
			 </table>
		</form>
	</div>
	<div id="loading">
		<table class="prod mt" border="1" style="border-collapse:collapse; border:1px solid #ebebeb;">
			<tbody border="1" style="border-collapse:collapse; border:1px solid #f5f5f5; border-top:1px solid #ebebeb;  border-top:1px solid #ebebeb; border-bottom:1px solid #ebebeb; border-left:1px solid #ebebeb; line-height:36px">
			 <tr>
				<td><input type=checkbox name=selectedAll id=selectedAll class=checkbox onclick=switchAll();></td>
				<td><ng:locale key ="bdBounsDeduct.wweek"/></td>
				<td><ng:locale key ="miMember.memberNo"/></td>
				<td><ng:locale key ="bdSendRecord.bonusMoney"/></td>
				<td><ng:locale key ="miMember.freezestatus"/></td>
				<td>选择方式</td>
				<td><ng:locale key ="fiBankbookTemp.status"/></td>
				<td><ng:locale key ="bdSendRecord.sendDate"/></td>
			 </tr>
		 	</tbody>
			<tbody border="1" style="border-collapse:collapse; border:1px solid #ebebeb;">
		 	<c:forEach items="${jbdMemberLinkCalcHist}" var="jbdSendRecordHist" >
				<tr class="bg-c gry3">
					<td>
					<c:if test="${ empty jbdSendRecordHist.send_date 
						&& jbdSendRecordHist.send_status_dev=='1' 
						&& jbdSendRecordHist.w_week >=201516 
						&& jbdSendRecordHist.freeze_status==0 
						&& jbdSendRecordHist.remittance_money>0 }">
					<input type="checkbox" name="selectedId" id="selectedId" value="${jbdSendRecordHist.id }" class="checkbox"/>
					</c:if>
					</td>
					<td>
					   <ng:weekFormat week="${jbdSendRecordHist.w_week}" weekType="w"></ng:weekFormat>
				   	</td>
				   	<td>${jbdSendRecordHist.user_code } </td>
					<td>
						<fmt:formatNumber value="${jbdSendRecordHist.remittance_money+jbdSendRecordHist.current_dev}" 
						type="number" pattern="###,###,##0.00"/>
					</td>
				   	<td>
						<c:if test = "${jbdSendRecordHist.freeze_status =='0'}">
						   <ng:locale key="member.status0"/>
						</c:if>
						<c:if test = "${jbdSendRecordHist.freeze_status =='1'}">
						   <ng:locale key="miMember.freezestatus1"/>
						</c:if>
					</td>
				   	<td>
						<ng:code listCode="jbd.send.type" value="${jbdSendRecordHist.send_type}"/> 
				   	</td>
				   	<td>
					   <c:if test="${jbdSendRecordHist.register_status=='1'||jbdSendRecordHist.register_status=='3' }">
						<span size="2">
							<ng:locale key="bdSendRecord.unSend"/>
						</span>
						</c:if>
						<c:if test="${jbdSendRecordHist.register_status=='2' }">
						<span class="colorCS">
							<ng:locale key="bdSendRecord.sended"/>
						</span>
						</c:if>
						<c:if test="${jbdSendRecordHist.register_status=='4' }">
							<ng:locale key="busi.bd.notSend"/>
						</c:if>
				   	</td>
				   	<td class="colorCS">
						<fmt:parseDate var="send_date" value="${jbdSendRecordHist.send_date }"/>
					   	<fmt:formatDate value="${send_date }" pattern="yyyy-MM-dd"/>
				   	</td>
				</tr>
		 </c:forEach>
		</tbody>
		${page.pageInfo }
		</table>
	</div>
</div>
<script>
$(function(){
	$('.tabQueryLs tbody').find('tr:odd > td').css('background-color','#E4EBFF');
});
</script>
   
</body>








