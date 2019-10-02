<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!--<title>客户管理 － 查看建议详细</title>-->
</head>
<script>
function loading(){
	var str = '<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src=images/indicator_smallwaitanim.gif alt=Loading  align=absmiddle/>';
	str += '&nbsp;&nbsp;<ng:locale key="button.loading"/>';
	document.getElementById("kkk").innerHTML=str;
}
  
</script>

<img style="display:none" src="images/indicator_smallwaitanim.gif" alt="Loading"  align="absmiddle" />

<body>
    <h2 class="title mgb20"><ng:locale key="examine.suggestion.detail"/></h2>
    <form:form commandName="inwSuggestion" method="" action="" id="inwSuggestion" onsubmit="if(isFormPosted()){return true;}{return false;}">
        <table width="100%" border="0" class="personalInfoTab" id="inwSuggestionReplyDetailId" style="margin-top:2px;">

    <tr><th>
        <ng:locale  key="inwSuggestion.suggestedTopics"/>
    </th>
    <td align="left" width="80%">
        ${inwSuggestion.subject}
    </td></tr>

    <tr><th>
        <ng:locale  key="inwSuggestion.proposalContent"/>
    </th>
    <td align="left" width="80%">
         <form:textarea path="content" id="content" cssClass="text medium" cssErrorClass="text medium error" rows="10" cols="60" readonly="true"/>
    </td></tr>
    <tr><th>
        <ng:locale  key="inwSuggestion.createTime"/>
    </th>
    <td align="left" width="80%">
         ${inwSuggestion.createTime}
    </td></tr>
    <tr><th>
        <ng:locale  key="inwSuggestion.userCode"/>
    </th>
    <td align="left" width="80%">
         ${inwSuggestion.userCode}
    </td></tr>
     <tr><th>
        <ng:locale  key="amMessage.replyContent"/>
    </th>
    <td>
      <form:textarea path="replyTontent" id="replyTontent" cssClass="text medium" cssErrorClass="text medium error" rows="10" cols="60" readonly="true"/>
    </td>
    </tr>
    <tr><th>
        <ng:locale  key="proposed.adoption"/>
    </th>
    <td>
          <c:if test="${inwSuggestion.proposedAdoption=='1'}">
             <ng:code listCode="proposed.adoption.list" value="1"/>
          </c:if>
          <c:if test="${inwSuggestion.proposedAdoption=='2'}">
             <ng:code listCode="proposed.adoption.list" value="2"/>
          </c:if>
    </td>
    </tr>
    <tr>
         <td colspan="3">
                <input type="button" class="btn_common corner2 fr mgt30" name="cancel" onclick="returnOld()" value="<ng:locale key="operation.button.return"/>" />
         </td>
    </tr>
</table>
</form:form>
<script>
        //返回
        function returnOld(){
              var differenceInws = "<%= request.getAttribute("differenceInw")%>";
              
             window.location.href="inwSuggestionReply.html?id=${inwSuggestion.demandId}&differenceInw="+differenceInws+" ";
        }
</script>
</body>