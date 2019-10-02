<%@ page contentType = "text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!--<title></title>-->
<!-- 客户需求-查看明细 -->
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/relationshipRecordManager.js'/>" ></script>	
<spring:bind path="linkmanDemand.*">
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
			<h3 class="color2 ml"><ng:locale  key="linkmanDemand.customerDemand"/></h3>
		</div>	
	<div class="mt">
    <form:form commandName="linkmanDemand" method="post" action="linkmanDemandform" id="linkmanDemandform" onsubmit="if(isFormPosted()){return true;}{return false;}">
        <table class="form_details_table">
            <tr>
                <td>
                	<ng:locale key="relationshipRecord.linkmanCategory"/>：
                	${linkmanCategory}
                    <%-- <form:select path="lcId" id="lcId" class="mySelect" onchange="getLinkmanById(this.value)">
                        <form:option label="" value="ungrouped"><ng:locale key='relationshiipRecord.Ungrouped'/></form:option>
                        <form:options items="${linkmanCategoryList}"  itemValue="id" itemLabel="name"/>
                    </form:select> --%>
                </td>
                <td colspan="2">
                	<ng:locale key="relationshipRecord.contact"/>：
                    <%-- <select name ="linkmanId" id="linkmanId" class="mySelect">
                        <option value=""><ng:locale key="list.please.select"/></option>
                    </select> --%>
                    ${linkman }
                </td>
            </tr>
            <tr>
            <td colspan="3">
            	<ng:locale key="linkmanDemand.customerDemand"/>：
            	${linkmanDemand.customerDemand }
            <%-- <form:textarea path="customerDemand" id="customerDemand" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error" disabled="true" /> --%>
            </td>
            </tr>
            <tr>
                <td colspan="3">
                	<ng:locale key="linkmanDemand.customersWishes"/>：
                	${linkmanDemand.customersWishes }
                     <%-- <form:textarea path="customersWishes" id="customersWishes" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error" disabled="true" /> --%>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                	<ng:locale key="linkmanDemand.interests"/>：
                	${linkmanDemand.interests }
                    <%-- <form:textarea path="interests" id="interests" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error" disabled="true" /> --%>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                	<ng:locale key="linkmanDemand.goodSports"/>：
                	${linkmanDemand.goodSports }
                    <%-- <form:textarea path="goodSports" id="goodSports" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error"  disabled="true"/> --%>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                	<ng:locale key="linkmanDemand.favoritwActivity"/>：
                	${linkmanDemand.favoritwActivity }
                    <%-- <form:textarea path="favoritwActivity" id="favoritwActivity" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error"  disabled="true"/> --%>
                </td>
            </tr>
        </table>
   		<div class="tc" style="margin-top: 10px;"><button type="button" class="btn btn-success" name="cancel" onclick="goBack()"><ng:locale key="operation.button.return"/></button></div>
    </form:form>
    </div>
</div>
    <script src="<c:url value='/scripts/validator.jsp'/>"></script>
    <script src="js/jQuery-1.9.1.min.js"></script>
    <script src="js/joyLife.js"></script>
    <script>
          var jq = jQuery.noConflict();
      function checkNumber(content){
              //alert("备注只能输入500个字符");
              if(content.length>250){
                   alert("备注只能输入500个字符");
                   document.getElementById("content").readOnly=true;
              }
      }
      //根据客户分类，查询相应分类下的客户名称
     function getLinkmanById(){
             var ddd = jq("#lcId").val();
             var userCode = "<%=request.getAttribute("userCode")%>";
             relationshipRecordManager.getLinkmanNameByLcId(ddd,userCode,linkmanNameLl);
      }
      
      //回调函数
      function linkmanNameLl(valid){
                  DWRUtil.removeAllOptions("linkmanId");
		 		  DWRUtil.addOptions("linkmanId",{'':'<ng:locale key='list.please.select'/>'});  
                  DWRUtil.addOptions("linkmanId",valid,"id","name");
			   		if(''!='${linkmanDemand.linkmanId}'){
			   		jq("#linkmanId").val('${linkmanDemand.linkmanId}');
			   		}
      }
      
      //返回按钮的方法  
      function goBack(){
            window.location.href="linkmanDemandQuery";
      } 
      
      getLinkmanById();
</script>
</body>