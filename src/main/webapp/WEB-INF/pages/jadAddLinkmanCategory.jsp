<%@ page contentType="text/html; charset=UTF-8" language="java"%> 
<%@ include file="/common/taglibs.jsp"%>  
<head>
<!--<title>客户分类－添加</title>-->
<spring:bind path="linkmanCategory.*">
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
		<h3 class="color2 ml">群组设置</h3>
	</div>
 <form:form commandName="linkmanCategory" method="post" action="jadAddLinkmanCategory" id="jadAddLinkmanCategoryId" onsubmit="if(isFormPosted()){return true;}{return false;}">
   <div class="mt">	
	<table class="form_details_table">
     <tr>
         <td width="12%" align="right"><span class="star"><ng:locale key="linkmanCategory.name"/>：</span></td>
         <td width="35%"><form:input path="name" id="name"  size="30" maxlength="60"/></td>
         <td width="" ><button type="submit" class="btn btn-info" style="margin:5px 10px 5px 0;" name="save" id="saveId" ><ng:locale key="operation.button.save"/></button>
         	<button type="button" class="btn btn-success" style="margin:5px 10px 5px 0;" name="cancel"  onclick="returnOld()" ><ng:locale key="operation.button.return"/>
         </td>
     </tr>
   </table>
</div>
</form:form>
</div>

<script type="text/javascript">
 
       window.onload = function showOrHiden(){
             //保存键的公示与隐藏
             var saveMark = "<%=request.getAttribute("saveMark")%>";
             if("saveMark"==saveMark){
                document.getElementById("saveId").style.display='none';
             }else{
                document.getElementById("saveId").style.display='';
             }
       }
       //返回按钮的方法
       function returnOld(){
               window.location.href = "jadlinkmanCategoryQuery.html";
       }

</script>
</body>