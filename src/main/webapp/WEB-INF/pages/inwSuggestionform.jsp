<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglibs.jsp"%>
<head>
<!--<title>创新共赢-意见表的添加</title>-->
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

<form:form commandName="inwSuggestion"  method="post" action="inwSuggestionform" id="inwSuggestionId" name="inwSuggestionform">
    <div id="" style="font-size:16px;" class="title mgb20"><strong><ng:locale key="inwSuggestion.addComment"/></strong></div>
    <table class="detail" width="100%">
         <tr>
         <td align="center" width="10%"><ng:locale key="inwSuggestion.suggestedTopics"/></td>
         <td align="left" width="80%"><form:input path="subject" id="subject" cssClass="text medium" maxlength="100" size="65"/></td>
         <td width="10%"></td>
         <td></td>
         </tr>
         <tr style="line-height: 300%">
         <td align="center" width="10%"><ng:locale key="inwSuggestion.proposalContent"/></td>
         <td width="80%"></td>
         <td width="10%"></td>
         </tr>
         <tr>
         <td width="10%"></td>
         <td width="80%">
         <form:textarea path="content" id="content" cssClass="text medium" cssErrorClass="text medium error" rows="17" cols="71" onkeydown="checkNumber(this.value)"/>
         </td>
         <td width="10%"></td>
         </tr>
         <tr>
         <td width="10%"></td>
         <td width="80%"  nowrap="nowrap"><ng:locale key="inwSuggestion.enterTheWordLimit"/></td>
         <td width="10%"></td>
         </tr>
         <tr>
         <td colspan="2">
            <form:input path="demandId" id="demandId" cssClass="text medium" type="hidden"/>
             <input name="differenceInw" id="differenceInw" cssClass="text medium"/>
         </td>
         </tr>
    </table>
    <table width="100%">
    	   <tr style="line-height:200%">
    	       <td colspan="5"></td>
    	   </tr>    
           <tr style="line-height:300%">
		    <td width="20%" align="center">
		    </td>
		    <td width="20%" align="center">    
		    </td>
		    <td width="20%" nowrap="nowrap"><input type="button" class="btn_common ft14 fr" name="save" id="saveId" onclick="saveInwSuggestion(document.inwSuggestionform)"  value="<ng:locale key="operation.button.save"/>" /></td>
		    <td width="20%" nowrap="nowrap"><input type="button" class="btn_common ft14 fr" name="cancel"  onclick="goBack()" value="<ng:locale key="operation.button.return"/>" /></td>
		    <td width="20%"></td>
          </tr>
     </table>
</form:form>     
  <script type="text/javascript">
  
       
       
       
       //定义一个隐藏域---需求(合作共赢)表的主键
       window.onload = function　definitionHide(){
            var demandId = "<%= session.getAttribute("demandId")%>";
            document.getElementById("demandId").value=demandId;
            document.getElementById("demandId").style.display='none';
            document.getElementById("differenceInw").style.display='none';
            var differenceInw = "<%= request.getAttribute("differenceInw")%>";
             document.getElementById("differenceInw").value=differenceInw;
	       var saveMark = "<%=request.getAttribute("saveMark")%>";
	        //保存键的显示与隐藏
	        if("saveMark"==saveMark){
	          document.getElementById("saveId").style.display='none';
	        }else{
	          document.getElementById("saveId").style.display='';
	        }
      }
      
      function saveInwSuggestion(theForm){
            var demandId = "<%= session.getAttribute("demandId")%>";
            document.getElementById("demandId").value=demandId;
            document.getElementById("demandId").style.display='none';
            theForm.submit();
      }
      
      //文本域输入内容个数的校验
      function checkNumber(mark){
              if(mark.length>1000){
                   alert("<ng:locale key ='inwSuggestion.enterTheWordLimit'/>");
                   document.getElementById("remark").readOnly=true;
              }
       }
       
       //返回按钮的方法
       function goBack(){
           var diffInw = "<%= request.getAttribute("differenceInw")%>";
          if("demand"==diffInw){
               window.location.href="inwDemands.html";
          }else{
               window.location.href="inwDemandsSystem.html";
          }
         
       }
  </script>
