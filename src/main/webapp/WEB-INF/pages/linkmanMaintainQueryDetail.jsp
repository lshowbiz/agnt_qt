<%@ page contentType = "text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!--<title></title>-->
<!-- 客户管理－客户的商品录入/修改 -->
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/relationshipRecordManager.js'/>" ></script>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script src="./scripts/calendar/calendar.js"> </script>
<script src="./scripts/calendar/calendar-setup.js"> </script>
<script src="./scripts/calendar/lang.jsp"> </script>	
<spring:bind path="linkmanMaintain.*">
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
			<h3 class="color2 ml"><ng:locale  key="linkmanMaintain.maintain"/></h3>
		</div>	
	<div class="mt">
    <form:form commandName="linkmanMaintain" method="post" action="linkmanMaintainform" id="linkmanMaintainform" onsubmit="if(isFormPosted()){return true;}{return false;}">
        <table class="form_details_table">
            <tr>
                <td nowrap="nowrap">
                	<ng:locale key="linkman.name"/>：${linkmanMaintain.other }</td>
                <!--<td nowrap="nowrap">
	                     <label class="star"><ng:locale key="relationshipRecord.linkmanCategory"/>：</label>
	                -->
	                <!--  
	                    <form:select path="lcId" id="lcId" class="mySelect" onchange="getLinkmanById(this.value)" disabled="true">
	                        <form:option label="" value=""><ng:locale key='relationshiipRecord.Ungrouped'/></form:option>
	                        <form:options items="${linkmanCategoryList}"  itemValue="id" itemLabel="name"/>
	                    </form:select>
	                   
                </td>-->
            	<td><ng:locale key="linkmanMaintain.maintenanceTime"/>：${linkmanMaintain.maintenanceTime }</td>
            	<td>
            		<ng:locale key="linkmanMaintain.maintenanceMode"/>：
            		<ng:code listCode="linkmanmaintain.type" value="${linkmanMaintain.maintenanceMode}"></ng:code>
            	</td>
            </tr>
            <tr>
            	<td colspan="3"><ng:locale key="linkmanMaintain.maintenanceTopic"/>：${linkmanMaintain.maintenanceTopic }</td>
            </tr>
            <tr>
           	 	<td colspan="3">
           	 		<ng:locale key="linkmanMaintain.maintenanceContent"/>：
           	 		${linkmanMaintain.maintenanceContent }
            		<%-- <form:textarea path="maintenanceContent" id="maintenanceContent" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error"  disabled="true"/> --%>
          		</td>
            </tr>
            <tr>
                <td colspan="3">
                	<ng:locale key="linkmanMaintain.maintenanceEffect"/>：
               	 	${linkmanMaintain.maintenanceEffect }
                    <%-- <form:textarea path="maintenanceEffect" id="maintenanceEffect" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error" disabled="true"/> --%>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                	<ng:locale key="jamPromotion.analyzed"/>：
                	${linkmanMaintain.summary }
                    <%-- <form:textarea path="summary" id="summary" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error"  disabled="true"/> --%>
                </td>
            </tr>
        </table>
   		<div class="tc" style="margin-top: 10px;"><button type="button" name="cancel"onclick="goBack()" class="btn btn-success"><ng:locale key="operation.button.return"/></button></div>
    </form:form>
    </div>
</div>
    <script src="<c:url value='/scripts/validator.jsp'/>"></script>
    <script src="js/jQuery-1.9.1.min.js"></script>
    <script src="js/joyLife.js"></script>
    <script>
             var jq = jQuery.noConflict();
          window.onload = function getDemandFuctionValue(){
              //  document.getElementById("id").style.display='none';
             //   document.getElementById("userCode").style.display='none';
          }
          
      function checkNumber(content){
              if(content.length>250){
                   alert("备注只能输入500个字符");
                   document.getElementById("content").readOnly=true;
              }
      }
      //根据客户分类，查询相应分类下的客户名称
     function getLinkmanById(){
    //         var ddd = jq("#lcId").val();
    //         var userCode = "<%=request.getAttribute("userCode")%>";
    //         relationshipRecordManager.getLinkmanNameByLcId(ddd,userCode,linkmanNameLl);
      }
      
      //回调函数
      function linkmanNameLl(valid){
                  DWRUtil.removeAllOptions("linkmanId");
		 		  DWRUtil.addOptions("linkmanId",{'':'<ng:locale key='list.please.select'/>'});  
                  DWRUtil.addOptions("linkmanId",valid,"id","name");
			   		if(''!='${linkmanMaintain.linkmanId}'){
			   		jq("#linkmanId").val('${linkmanMaintain.linkmanId}');
			   		}
      }
      
      //返回按钮的方法  
      function goBack(){
            window.location.href="linkmanMaintains";
      } 
      
      
      getLinkmanById();
</script>
</body>