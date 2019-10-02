<%@ page contentType = "text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!--<title></title>-->
<!-- 客户管理-活动管理-修改或录入   gw -->
	<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
    <script src="./scripts/calendar/calendar.js"> </script>
    <script src="./scripts/calendar/calendar-setup.js"> </script>
    <script src="./scripts/calendar/lang.jsp"> </script>
	<spring:bind path="linkmanActivity.*">
		<c:if test="${not empty status.errorMessages}">
		<div class="error" id="errorDiv" style="display: none">    
			<c:forEach var="error" items="${status.errorCodes}">
			   <div> <c:out value="${error}" escapeXml="false"/></div>
			</c:forEach>
		</div>
		</c:if>
	</spring:bind>
</head>
<body>
<div class="cont">	
		<div class="bt mt">
			<h3 class="color2 ml"><ng:locale  key="linkmanActivity.activity"/></h3>
		</div>	
	<div class="mt">
    <form:form commandName="linkmanActivity" action="linkmanActivityform" method="post" id="linkmanActivityform" onsubmit="if(isFormPosted()){return true;}{return false;}">
        <table class="form_details_table">
            <tr>
            	<td><ng:locale key="linkmanActivity.eventName"/>：${linkmanActivity.eventName }</td>
				<td><ng:locale key="linkmanActivity.organizer"/>：${linkmanActivity.organizer }</td>
				<td><ng:locale key="linkmanActivity.eventType"/>：<ng:code listCode="linkmanacitvity.activity" value="${linkmanActivity.eventType}" /></td>
            </tr>
            <tr>
            	<td><ng:locale key="linkmanActivity.beginTime"/>：${linkmanActivity.beginTime }</td>
				<td><ng:locale key="linkmanActivity.endTime"/>：${linkmanActivity.endTime }</td>
				<td><ng:locale key="linkmanActivity.preExpenses" />：${linkmanActivity.preExpenses }</td>
            </tr>
            <tr>
                <td><ng:locale key="linkmanActivity.actualExpenditure"/>：${linkmanActivity.actualExpenditure }</td>
                <td colspan="2"><ng:locale key="linkmanActivity.hostVenue"/>：${linkmanActivity.hostVenue }</td>
            </tr>
            <tr>
                <td colspan="3">
                	<ng:locale key="linkmanActivity.participants"/>：
                	${linkmanActivity.participants }
                    <%-- <form:textarea path="participants" id="participants" style="width:90%;height:70px;margin-top:10px;padding:5px;resize:none;" disabled="true"/> --%>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                	<ng:locale key="linkmanActivity.topic"/>：
                	${linkmanActivity.topic }
                    <%-- <form:textarea path="topic" id="topic" style="width:90%;height:70px;margin-top:10px;padding:5px;resize:none;" disabled="true"/> --%>
                </td>
            </tr>
             <tr>
                <td colspan="3">
                	<ng:locale key="linkmanActivity.content"/>：
                	${linkmanActivity.content }
                    <%-- <form:textarea path="content" id="content" style="width:90%;height:70px;margin-top:10px;padding:5px;resize:none;" disabled="true"/> --%>
                </td>
            </tr>
             <tr>
                <td colspan="3">
                	<ng:locale key="linkmanActivity.purpose"/>：
                	${linkmanActivity.purpose }
                    <%-- <form:textarea path="purpose" id="purpose" style="width:90%;height:70px;margin-top:10px;padding:5px;resize:none;" disabled="true"/> --%>
                </td>
            </tr>
             <tr>
                <td colspan="3">
                	<ng:locale key="linkmanActivity.activityResult"/>：
                	${linkmanActivity.activityResult }
                    <%-- <form:textarea path="activityResult" id="activityResult" style="width:90%;height:70px;margin-top:10px;padding:5px;resize:none;" disabled="true"/> --%>
                </td>
            </tr>
             <tr >
                <td colspan="3">
                	<ng:locale key="linkmanActivity.summary"/>：
                	${linkmanActivity.summary }
                    <%-- <form:textarea path="summary" id="summary" style="width:90%;height:70px;margin-top:10px;padding:5px;resize:none;" disabled="true"/> --%>
                </td>
            </tr>
        </table>
  		<div class="tc" style="margin-top: 10px;"><button type="button" onclick="goBack()" class="btn btn-success"><ng:locale key="operation.button.return"/></button></div>
    </form:form>
    </div>
</div>
    <script src="<c:url value='/scripts/validator.jsp'/>"></script>
    <script>
             var jq = jQuery.noConflict();
          window.onload = function getDemandFuctionValue(){
                document.getElementById("id").style.display='none';
                document.getElementById("userCode").style.display='none';
                var linkmanActivityFunction = "<%= request.getAttribute("linkmanActivityFunction")%>";
                jq("#linkmanActivityFunction").val(linkmanActivityFunction);
          }
          
      function checkNumber(content){
              if(content.length>250){
                   alert("备注只能输入500个字符");
                   document.getElementById("content").readOnly=true;
              }
      }
      //返回按钮的方法  
      function goBack(){
            window.location.href="linkmanActivities";
      } 
</script>
</body>