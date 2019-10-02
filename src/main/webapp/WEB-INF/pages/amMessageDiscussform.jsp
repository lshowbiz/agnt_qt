<%@ include file="/common/taglibs.jsp"%>
<%@ page language="java"  contentType="text/html;charset=utf-8"%>


<style type="text/css">

table.detail {
	
	font-weight: normal;
	border-collapse:collapse;
	position: relative;
	border: 1px solid #dadada;
}

table.detail th {
	width:150px;
	border: 1px solid #dadada;
	color: #333333;
	text-align: right;
	background: #f0f0f0;
	height: 20px;
	padding: 4px;
	white-space: nowrap;
}

table.detail th label {
	width:100%;
	text-align: right;
}

table.detail th.tallCell {
    vertical-align: top;
}

table.detail th.command{
	border: 1px solid #dadada;
	color: #165AB3;
	text-align: right;
	background: #f0f0f0;
	height: 20px;
	padding: 4px;
}

table.detail td {
	border: 1px solid #dadada;
	color: black;
	padding: 4px;
}

table.detail td.moveOptions {
    text-align: center;
    width: 50px;
    padding: 4px;
}

table.detail td.moveOptions button {
    margin-bottom: 3px;
    width: 45px;
    white-space: nowrap;
}

table.detail td.command{
	border: 1px solid #dadada;
	color: black;
	padding: 4px;
	background: #f0f0f0;
}

table.detail td.title{
	border: 1px solid #dadada;
	color: #6B91C9;
	background: #E1E9F4;
	height: 20px;
	padding: 4px;
	white-space: nowrap;
	font-weight: bold;
}

table.detail td.buttonBar {
    padding-top: 10px;
}

table.detail td.updateStatus {
    font-size: 11px;
    color: #c0c0c0;
}

table.detailSub td{
	border: none;
	padding: 1px;
}

table.inSideTable {
	font-weight: normal;
	border-collapse:collapse;
	position: relative;
}

table.inSideTable td {
	border: 1px none #dadada;
	color: black;
	padding: 4px;
}
</style>
<spring:bind path="amMessage.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error">    
        <c:forEach var="error" items="${status.errorMessages}">
            <img src="<c:url value="images/newIcons/warning_16.gif"/>"
                alt="<ng:locale key="icon.warning"/>" class="icon" />
            <c:out value="${error}" escapeXml="false"/><br />
        </c:forEach>
    </div>
    </c:if>
</spring:bind>
<div class="cont">	
			<div class="bt mt">
				<h3 class="color2 ml">信息</h3>
			</div>	  
<form:form commandName="amMessage" method="post" action="amMessageDiscussform" onsubmit="return checkFrm()" name="amMessageForm" id="amMessageForm">


<input type="hidden" name="strAction" value="${param.strAction }">
<form:hidden path="uniNo"/>

<table class='detail' width="100%" >
	<tr>
	<th rowspan="6">
        <ng:locale  key="amMessage.discuss.quality"/>
    </th>
    </tr>
    <tr>
    	<td><ng:locale  key="amMessage.discuss.5"/></td>
    	<td><form:radiobutton path="discuss" value="5"/><img src="images/bookmark.png"><img src="images/bookmark.png"><img src="images/bookmark.png"><img src="images/bookmark.png"><img src="images/bookmark.png"></td>
    </tr>
    <tr>
    	<td><ng:locale  key="amMessage.discuss.4"/></td>
    	<td> <form:radiobutton path="discuss" value="4"/><img src="images/bookmark.png"><img src="images/bookmark.png"><img src="images/bookmark.png"><img src="images/bookmark.png"></td>
    </tr>
    <tr>
    	<td><ng:locale  key="amMessage.discuss.3"/></td>
    	<td><form:radiobutton path="discuss" value="3"/><img src="images/bookmark.png"><img src="images/bookmark.png"><img src="images/bookmark.png"></td>
    </tr>
    <tr>
    	<td> <ng:locale  key="amMessage.discuss.2"/></td>
    	<td><form:radiobutton path="discuss" value="2"/><img src="images/bookmark.png"><img src="images/bookmark.png"></td>
    </tr>
    <tr>
    	<td><ng:locale  key="amMessage.discuss.1"/></td>
    	<td> <form:radiobutton path="discuss" value="1"/><img src="images/bookmark.png"></td>
    </tr>
    
</table>
<div class="tc" style="margin-top: 10px;">
	<button type="button"  onclick="javascript:checkFrm(document.amMessageForm);" >&nbsp;<span>发送</span>&nbsp;</button>
</div>
</form:form>


</div>


<script type="text/javascript">
	function checkFrm(theForm){
		var discuss=document.getElementsByName("discuss");
		var flag=false;
		for(var i=0;i<discuss.length;i++)
	     {
	      	if(discuss[i].checked){
	      		flag= true;
	      	}
	     }
	     
		if(flag){
			document.amMessageForm.submit();
		}else{
			alert('<ng:locale key="amMessage.discuss.select"/>');
		}
	}
		function discussAmMessage(uniNo){
			var pars=new Array();
			pars[0]="<ng:locale key='amMessage.discuss'/>";
			pars[1]="editAmMessageDiscuss.html?strAction=editAmMessageDiscuss&uniNo=" + uniNo;
			pars[2]=window;
			var ret=showDialog("<%=request.getContextPath()%>",pars,510,250,1);

	}
</script>


