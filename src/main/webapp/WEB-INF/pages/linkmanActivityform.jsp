<%@ page contentType = "text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!--<title></title>-->
<!-- 客户管理-活动管理-修改或录入   gw -->
    <script src="<c:url value='/scripts/My97DatePicker/WdatePicker.js'/>"></script>
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
			<h3 class="color2 ml"><ng:locale key="linkmanActivity.activity"/></h3>
		</div>	
	<div class="mt">
    <form:form commandName="linkmanActivity" action="linkmanActivityform" method="post" id="linkmanActivityform" onsubmit="if(isFormPosted()){return true;}{return false;}">
        <table class="form_edit_table">
            <tr>
				<td><span class="star"></span><ng:locale key="linkmanActivity.eventName"/>：</td>
				<td><form:input path="eventName" id="eventName"/></td>
				<td><span class="star"></span><ng:locale key="linkmanActivity.organizer"/>：</td>
				<td><form:input path="organizer" id="organizer"/></td>
            </tr>
            <tr>
				<td><ng:locale key="linkmanActivity.beginTime"/>：</td>
				<td>                           
					<form:input path="beginTime" id="beginTime" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				</td>
				<td><ng:locale key="linkmanActivity.endTime"/>：</td>
				<td>                           
					<form:input path="endTime" id="endTime" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
				</td>
            </tr>
            <tr>
                <td><span class="star"></span><ng:locale key="linkmanActivity.eventType"/>：</td>
                <td>
                     <ng:list name="eventType" listCode="linkmanacitvity.activity" value="${linkmanActivity.eventType}" defaultValue=""/>
                </td>
                <td><span class="star"></span><ng:locale key="linkmanActivity.hostVenue"/>：</td>
                <td><form:input path="hostVenue" id="hostVenue"/></td>
            </tr>
            <tr>
                <td><ng:locale key="linkmanActivity.preExpenses"/>：</td>
                <td><form:input path="preExpenses" id="preExpenses"/></td>
                <td><ng:locale key="linkmanActivity.actualExpenditure"/>：</td>
                <td><form:input path="actualExpenditure" id="actualExpenditure"/></td>
            </tr>
            <tr>
                <td><ng:locale key="linkmanActivity.participants"/>：</td>
                <td colspan="3"><form:textarea path="participants" id="participants" style="width:99%;height:70px;margin-top:10px;padding:5px;resize:none;"/></td>
            </tr>
            <tr>
                <td><span class="star"></span><ng:locale key="linkmanActivity.topic"/>：</td>
                <td colspan="3"><form:textarea path="topic" id="topic" style="width:99%;height:70px;margin-top:10px;padding:5px;resize:none;"/></td>
            </tr>
             <tr>
                <td><span class="star"></span><ng:locale key="linkmanActivity.content"/>：</td>
                <td colspan="3"><form:textarea path="content" id="content" style="width:99%;height:70px;margin-top:10px;padding:5px;resize:none;"/></td>
            </tr>
             <tr>
                <td><ng:locale key="linkmanActivity.purpose"/>：</td>
                <td colspan="3"><form:textarea path="purpose" id="purpose" style="width:99%;height:70px;margin-top:10px;padding:5px;resize:none;"/></td>
            </tr>
             <tr>
                <td><ng:locale key="linkmanActivity.activityResult"/>：</td>
                <td colspan="3"><form:textarea path="activityResult" id="activityResult" style="width:99%;height:70px;margin-top:10px;padding:5px;resize:none;"/></td>
            </tr>
             <tr>
                <td><ng:locale key="linkmanActivity.summary"/> ：</td>
                <td colspan="3"><form:textarea path="summary" id="summary" style="width:99%;height:70px;margin-top:10px;padding:5px;resize:none;"/></td>
            </tr>
            
            <tr>
				<td style="display:none"><form:input path="userCode" id="userCode"/></td>
				<td style="display:none"><form:input path="id" id="id"/></td>
				<td colspan="3"><input type="hidden" name="linkmanActivityFunction" value="" id="linkmanActivityFunction"/></td>
            </tr>
        </table>
       <div class="tc"><button type="submit" id="save" class="btn btn-info"><ng:locale key="operation.button.save"/></button>
       <button type="button" onclick="goBack()" class="btn btn-success"><ng:locale key="operation.button.cancel"/></button>
                    </div>
    </form:form>
    </div>
    </div>
    <script src="<c:url value='/scripts/validator.jsp'/>"></script>
    <script>
		var jq = jQuery.noConflict();
		window.onload = function getDemandFuctionValue(){
			//document.getElementById("id").style.display='none';
			//document.getElementById("userCode").style.display='none';
			var linkmanActivityFunction = "<%= request.getAttribute("linkmanActivityFunction")%>";
			
			jq("#linkmanActivityFunction").val(linkmanActivityFunction);
			
			var saveMark = "<%= request.getAttribute("saveMark")%>";
			if("saveMark"==saveMark){
				 document.getElementById("save").style.display='none';
			}else{
				 document.getElementById("save").style.display='';
			}
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