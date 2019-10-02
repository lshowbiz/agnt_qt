<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglibs.jsp"%>
<head>
<!--<title>创新共赢-意见表的添加(2013-11-08新需求)(详细查询)</title>-->
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/inwDemandManager.js'/>" ></script>
<script src="<c:url value='/dwr/interface/inwDemandsortManager.js'/>" ></script>


<spring:bind path="inwSuggestion.*">
	<c:if test="${not empty status.errorMessages}">
	<div class="error" id="errorDiv" style="display: none">    
		<c:forEach var="error" items="${status.errorCodes}">
		   <div> <c:out value="${error}" escapeXml="false"/></div>
		</c:forEach>
	</div>
	</c:if>
</spring:bind>
</head>


<h2 class="title mgb20"><ng:locale key="record.yourSubmission"/></h2>
<form:form commandName="inwSuggestion" method="post" action="inwSuggestionformNew" id="inwSuggestionNewId" name="inwSuggestionformNew">
    <table class="personalInfoTab" width="100%">
        <tbody> 
			<tr>
				 <td><ng:locale key="inwSuggestion.suggestedTopics"/>：</td>
				 <td>${inwSuggestion.subject }</td>
				 <td></td>
				 <td></td>
			</tr>

			<tr>
				<td><ng:locale key="inwDemandsort.spece"/>：</td>
				<td><input name="sortName" id="sortName" maxlength="100" size="30" disabled="true"/></td>
				<td><ng:locale key="inwDemand.name"/>：</td>
				<td><input name="name" id="name" maxlength="100" size="30" disabled="true"/></td>
			</tr>
			<tr>
				<td><ng:locale key="schedule.tel"/>：</td>
				<td>${inwSuggestion.phone }</td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><ng:locale key="micro.channel"/>：</td>
				<td>${inwSuggestion.microMessage }</td>
				<td>QQ：</td>
				<td>${inwSuggestion.qq }</td>
			</tr>
			<tr>
				<td><ng:locale key="inwSuggestion.type"/>：</td>
				<td>
					<ng:list listCode="inwsuggestion.suggestionsort" name="suggestionSort" value="${inwSuggestion.suggestionSort}" defaultValue="false" disabled="true" styleClass="mySelect" />
				</td>
				<td><form:input path="demandsortId" id="demandsortId" maxlength="50" size="15" cssStyle="display:none"/></td>
				<td><form:input path="demandId" id="demandId" maxlength="50" size="15" cssStyle="display:none"/></td> 
			</tr>

			<tr>
				<td><ng:locale key="inwSuggestion.proposalContent"/>：</td>
				<td colspan="3">(公司的邮箱是:innovation@jmtop.com,如有更多合作文件请发送邮件!)</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
				<td colspan="3">
					<form:textarea path="content" id="content" readonly="true" style="width:99%;height:200px;resize:none;padding:5px;"/>
				</td>
			</tr>
			<tr>
				<td><ng:locale key ="schedule.state"/>：</td>
				<td>
					<ng:list listCode="inwsuggestion.status" name="suggestionSort" value="${inwSuggestion.status}" defaultValue="false" disabled="true" styleClass="mySelect"/>
				</td>
				<td><ng:locale key ="inwIntegration.num"/>：</td>
				<td>
					${inwSuggestion.integration }
				</td>
			</tr>
			
			<c:if test="${inwSuggestion.firstReplyAudit == 'Y'}">
				<tr>
					<td><ng:locale key ="suggested.reply"/>：</td>
					<td colspan="3">
	                     <form:textarea path="replyTontent" id="replyTontent"  style="width:99%;height:100px;resize:none;padding:5px;" readonly="true"/>
					</td>
				</tr>
			</c:if>	
			
			<c:if test="${inwSuggestion.secondReplyAudit == 'Y'}">				
				<c:if test="${not empty inwSuggestion.replyContentSecond}">
				  <tr>
					  <td><ng:locale key ="suggested.replyTwo"/>：</td>
					  <td colspan="3">
	                     <form:textarea path="replyContentSecond" id="replyContentSecond"  style="width:99%;height:100px;margin-top:10px;resize:none;padding:5px;" readonly="true"/>
					  </td>
				  </tr>
				</c:if>
			</c:if>
			
			<c:if test="${inwSuggestion.thirdReplyAudit == 'Y'}">				
				<c:if test="${not empty inwSuggestion.replyContentThird}">
				    <tr>
					   <td><ng:locale key ="suggested.replyThree"/>：</td>
					   <td colspan="3">
	                         <form:textarea path="replyContentThird" id="replyContentThird"  style="width:99%;height:100px;margin-top:10px;resize:none;padding:5px;" readonly="true"/>
					   </td>
				   </tr>
				</c:if>
			</c:if>
			
			<tr>
				<td>&nbsp;</td>
				<td colspan="3">
					<a href="javascript:void(0);" class="btn_common corner2 fr" onclick="goBack()" name="cancel"><ng:locale key="operation.button.return"/></a>
				</td>
			</tr>
		</tbody>
    </table>

</form:form>     
<script>

	//定义一个隐藏域---需求(合作共赢)表的主键
	window.onload = function　definitionHide(){
		var demandId = "<%= request.getAttribute("demandId")%>";
		inwDemandManager.getInwDemandById(demandId,demandName);
	}

	//回调函数---给name属性赋值
	function demandName(name){
		document.getElementById("name").value=name;
		demandsortNameGet();
	}

	function demandsortNameGet(){
		var demandsortId = "<%= request.getAttribute("demandsortId")%>";
		inwDemandsortManager.getInwDemandsortById(demandsortId,demandsortName);
	}

	//回调函数---给sortName属性赋值
	function demandsortName(sortName){
	document.getElementById("sortName").value=sortName;
	}

	//返回按钮的方法
	function goBack(){
		window.location.href = "inwSuggestionReplyNew";
	}
   
</script>
