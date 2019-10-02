<%@ page contentType = "text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!--<title></title>-->
<!-- 客户管理-客户的商品-查询详细-修改(修改版) -->
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/relationshipRecordManager.js'/>" ></script>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
    <script src="./scripts/calendar/calendar.js"> </script>
    <script src="./scripts/calendar/calendar-setup.js"> </script>
    <script src="./scripts/calendar/lang.jsp"> </script>
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
			<h3 class="color2 ml"><ng:locale key="linkmanGoods.commodity"/></h3>
		</div>	
	<div class="mt">
    <form:form commandName="linkmanDemand" action="linkmanGoodsform" method="post" id="linkmanGoodsform" onsubmit="if(isFormPosted()){return true;}{return false;}">
        <table class="form_details_table">
            <tr>
                <td>
                	<ng:locale key="relationshipRecord.linkmanCategory"/>：
                    <%-- <form:select path="lcId" id="lcId" class="mySelect" onchange="getLinkmanById(this.value)">
                        <form:option label="" value=""><ng:locale key='relationshiipRecord.Ungrouped'/></form:option>
                        <form:options items="${linkmanCategoryList}"  itemValue="id" itemLabel="name"/>
                    </form:select> --%>
                    ${linkmanCategory }
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
				<ng:locale key="linkmanDemand.customerDemand"/>：${linkmanDemand.customerDemand }
				<%-- <form:textarea path="customerDemand" id="customerDemand" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error" readonly="true" disabled="true"/> --%>
				</td>
            </tr>
            <tr>
                <td colspan="3">
                	<ng:locale key="linkmanDemand.customersWishes"/>：${linkmanDemand.customersWishes }
                    <%-- <form:textarea path="customersWishes" id="customersWishes" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error" readonly="true" disabled="true"/> --%>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                	<ng:locale key="linkmanDemand.interests"/>：${linkmanDemand.interests }
                    <%-- <form:textarea path="interests" id="interests" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error" readonly="true" disabled="true"/> --%>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                	<ng:locale key="linkmanDemand.goodSports"/>：${linkmanDemand.goodSports }
                    <%-- <form:textarea path="goodSports" id="goodSports" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error" readonly="true" disabled="true"/> --%>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                	<ng:locale key="linkmanDemand.favoritwActivity"/>：${linkmanDemand.favoritwActivity }
                    <%-- <form:textarea path="favoritwActivity" id="favoritwActivity" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error"  readonly="true" disabled="true"/> --%>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                	<ng:locale key="linkmanDemand.meetDemandMeasure"/>：${linkmanDemand.meetDemandMeasure }
                    <%-- <form:textarea path="meetDemandMeasure" id="meetDemandMeasure" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error" disabled="true"/> --%>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                	<ng:locale key="linkmanDemand.suitableProducts"/>：${linkmanDemand.suitableProducts }
                    <%-- <form:textarea path="suitableProducts" id="suitableProducts" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error" disabled="true"/> --%>
                </td>
            </tr>
            <tr>
				<td nowrap="nowrap"> 
					<ng:locale key="linkmanDemand.executionTime"/>：                          
					<%-- <form:input path="executionTime" id="executionTime" readonly="true" size="10" cssClass="readonly" disabled="true"/>
					<img src="./images/calendar/calendar7.gif" id="img_executionTime" style="cursor: pointer;" title="<ng:locale key="Calendar.TT.SEL_DATE"/>"/>
					<script>
					Calendar.setup({
						inputField     :    "executionTime",
						ifFormat       :    "%Y-%m-%d",
						button         :    "img_executionTime",
						singleClick    :    true
					});
					</script> --%>
					${linkmanDemand.executionTime }							
				</td>
	            <td nowrap="nowrap" colspan="2">
	            		<ng:locale key="linkmanGoods.buyTime"/>：
	                    <%--  <form:input path="buyTime" id="buyTime" readonly="true" size="10" cssClass="readonly" disabled="true"/>
	                    <img src="./images/calendar/calendar7.gif" id="img_buyTime" style="cursor: pointer;" title="<ng:locale key="Calendar.TT.SEL_DATE"/>"/>
	                    <script>
	                    Calendar.setup({
	                    inputField     :    "buyTime",
	                    ifFormat       :    "%Y-%m-%d",
	                    button         :    "img_buyTime",
	                    singleClick    :    true
	                    });
	                    </script> --%>
	                    ${linkmanDemand.buyTime }
	            </td>
            </tr>
            <tr>
            <td colspan="3">
            <ng:locale key="linkmanGoods.buyGoods"/>：${linkmanDemand.buyGoods }
            <%-- <form:textarea path="buyGoods" id="buyGoods" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error"  disabled="true"/> --%>
            </td>
            </tr>
            <tr>
                <td colspan="3">
                	<ng:locale key="linkmanGoods.buyQuantity"/>：${linkmanDemand.buyQuantity }
                    <%-- <form:input path="buyQuantity" id="buyQuantity" cssClass="text medium" size="20" disabled="true"/> --%>
                </td>
            </tr>
            <tr>
            <tr>
                <td colspan="3">
                	<ng:locale key="linkmanGoods.productExperience"/>：${linkmanDemand.productExperience }
                    <%-- <form:textarea path="productExperience" id="productExperience" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error" disabled="true"/> --%>
                </td>
            </tr>
            <tr>
                <td colspan="3">
                	<ng:locale key="linkmanGoods.usingFeedback"/>：${linkmanDemand.usingFeedback }
                    <%-- <form:textarea path="usingFeedback" id="usingFeedback" cssClass="text medium" style="width:99%;height:128px;margin-top:10px;resize:none;" cssErrorClass="text medium error" disabled="true"/> --%>
                </td>
            </tr>
            <tr style="display:none;">
                <td><input type="hidden" name="linkmanDemandGoodsFunction" value="" id="linkmanDemandGoodsFunction"/></td>
                <td><form:input path="id" id="id" cssClass="text medium"/></td>
                <td colspan="3"></td>
            </tr>
             <tr style="display:none;">
            <td><td><form:input  path="userCode"   id="userCode"/></td></td>
            <td><td><form:input  path="registerTime"  id="registerTime"/></td></td>
            </tr>
        </table>
   		<div class="tc" style="margin-top: 10px;"><button type="button" name="cancel" onclick="goBack()" class="btn btn-success"><ng:locale key="operation.button.return"/></button></div>
    </form:form>
    </div>
</div>
    <script src="<c:url value='/scripts/validator.jsp'/>"></script>
    <script src="js/jQuery-1.9.1.min.js"></script>
    <script src="js/joyLife.js"></script>
    <script>
             var jq = jQuery.noConflict();
          window.onload = function getDemandFuctionValue(){
                document.getElementById("id").style.display='none';
                document.getElementById("userCode").style.display='none';
                document.getElementById("registerTime").style.display='none';
                
                var linkmanDemandGoodsFunction = "<%= request.getAttribute("linkmanDemandGoodsFunction")%>";
                jq("#linkmanDemandGoodsFunction").val(linkmanDemandGoodsFunction);
                
          }
          
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
            window.location.href="linkmanDemandGoodsQuery";
      } 
      
      getLinkmanById();
</script>
</body>