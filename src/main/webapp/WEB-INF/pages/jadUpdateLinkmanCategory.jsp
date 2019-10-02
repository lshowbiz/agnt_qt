<%@ page contentType="text/html; charset=UTF-8" language="java" %> 
<%@ include file="/common/taglibs.jsp"%>  
<head>
<!--<title>客户分类－修改</title>-->
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
<form:form commandName="linkmanCategory" method="post" action="jadUpdateLinkmanCategory" id="linkmanCategoryUpdateId" onsubmit="if(isFormPosted()){return true;}{return false;}">
   <div class="mt">	
	<table class="form_details_table">
     <tr>
         <td align="center" width="8%" class="star"><ng:locale key="linkmanCategory.name"/>:</td>
         <td width="35%"><form:input path="name" id="name" cssClass="text medium" size="30" maxlength="50"/></td>
         <td width="" align="center"><button type="submit" class="btn btn-info"  name="save" id="saveId"  ><ng:locale key="operation.button.save"/></button>
         	<button type="button" class="btn btn-success"  name="cancel"  onclick="returnOld()"  /><ng:locale key="operation.button.return"/></button>
         </td>
     </tr>
   </table>
   </div>
   <input type="hidden" name="id" value="${linkmanCategory.id }"/>
</form:form>
</div>
<script type="text/javascript">
 
       window.onload = function showOrHiden(){
             //保存键的公示与隐藏
             var updateMark = "<%=request.getAttribute("updateMark")%>";
             if("updateMark"==updateMark){
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
