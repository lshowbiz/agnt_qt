<%@ page contentType = "text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!--<title></title>-->
<!-- 客户管理-客户的商品-修改(修改版) -->
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/relationshipRecordManager.js'/>" ></script>
<script src="<c:url value='/scripts/My97DatePicker/WdatePicker.js'/>"></script>
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
			<h3 class="color2 ml"><ng:locale  key="linkmanGoods.commodity"/></h3>
		</div>	
	<div class="mt">
    <form:form commandName="linkmanDemand" action="linkmanGoodsform" method="post" id="linkmanGoodsform" onsubmit="if(isFormPosted()){return true;}{return false;}">
        <table class="form_edit_table">
            <tr>
                <td><span class="star"></span><ng:locale key="relationshipRecord.linkmanCategory"/>：</td>
                <td>
                    <form:select path="lcId" id="lcId" onchange="getLinkmanById(this.value)">
                        <form:option label="" value=""><ng:locale key='relationshiipRecord.Ungrouped'/></form:option>
                        <form:options items="${linkmanCategoryList}"  itemValue="id" itemLabel="name"/>
                    </form:select>
                </td>
                <td><span class="star"></span><ng:locale key="relationshipRecord.contact"/>：</td>
                <td>
                    <select name ="linkmanId" id="linkmanId">
                        <option value=""><ng:locale key="list.please.select"/></option>
                    </select>
                </td>

            </tr>
            <tr>
            <td><span class="star"></span><ng:locale key="linkmanDemand.customerDemand"/>：</td>
            <td colspan="3">
            <form:textarea path="customerDemand" id="customerDemand" cssClass="text medium" style="width:99%;height:70px;margin-top:10px;resize:none;" cssErrorClass="text medium error"  readonly="true"/>
            </td>
            </tr>
            <tr>
                <td><span class="star"></span><ng:locale key="linkmanDemand.customersWishes"/>：</td>
                <td colspan="3">
                    <form:textarea path="customersWishes" id="customersWishes" cssClass="text medium" style="width:99%;height:70px;margin-top:10px;resize:none;" cssErrorClass="text medium error" readonly="true" />
                </td>
            </tr>
            <tr style="display:none">
                <td><ng:locale key="linkmanDemand.interests"/>：</td>
                <td colspan="3">
                    <form:textarea path="interests" id="interests" cssClass="text medium" style="width:99%;height:70px;margin-top:10px;resize:none;" cssErrorClass="text medium error" readonly="true" />
                </td>
            </tr>
            <tr style="display:none">
                <td><ng:locale key="linkmanDemand.goodSports"/>：</td>
                <td colspan="3">
                    <form:textarea path="goodSports" id="goodSports" cssClass="text medium" style="width:99%;height:70px;margin-top:10px;resize:none;" cssErrorClass="text medium error" readonly="true" />
                </td>
            </tr>
            <tr style="display:none">
                <td><ng:locale key="linkmanDemand.favoritwActivity"/>：</td>
                <td colspan="3">
                    <form:textarea path="favoritwActivity" id="favoritwActivity" cssClass="text medium" style="width:99%;height:70px;margin-top:10px;resize:none;" cssErrorClass="text medium error"  readonly="true"/>
                </td>
            </tr>
            <tr>
                <td><span class="star"></span><ng:locale key="linkmanDemand.meetDemandMeasure"/>：</td>
                <td colspan="3">
                    <form:textarea path="meetDemandMeasure" id="meetDemandMeasure" cssClass="text medium" style="width:99%;height:70px;margin-top:10px;resize:none;" cssErrorClass="text medium error" />
                </td>
            </tr>
            <tr>
                <td><span class="star"></span><ng:locale key="linkmanDemand.suitableProducts"/>：</td>
                <td colspan="3">
                    <form:textarea path="suitableProducts" id="suitableProducts" cssClass="text medium" style="width:99%;height:70px;margin-top:10px;resize:none;" cssErrorClass="text medium error" />
                </td>
            </tr>
            <tr>
                <td nowrap="nowrap"><ng:locale key="linkmanDemand.executionTime"/>：</td>
                <td>
                    <form:input path="executionTime" id="executionTime" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
            	</td>
            	<td><ng:locale key="linkmanGoods.buyTime"/>：</td>
	            <td nowrap="nowrap">
	            	<form:input path="buyTime" id="buyTime" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	            </td>
            </tr>
            <tr>
            <td><span class="star"></span><ng:locale key="linkmanGoods.buyGoods"/>：</td>
            <td colspan="3">
            <form:textarea path="buyGoods" id="buyGoods" cssClass="text medium" style="width:99%;height:70px;margin-top:10px;resize:none;" cssErrorClass="text medium error"  />
            </td>
            </tr>
            <tr>
                <td><ng:locale key="linkmanGoods.buyQuantity"/>：</td>
                <td colspan="3">
                    <form:input path="buyQuantity" id="buyQuantity" cssClass="text medium" size="20"  onkeyup="this.value=this.value.replace(/\D/g,'')"  maxlength="10"/>
                </td>
            </tr>
            <tr>
                <td><ng:locale key="linkmanGoods.productExperience"/>：</td>
                <td colspan="3">
                    <form:textarea path="productExperience" id="productExperience" cssClass="text medium" style="width:99%;height:70px;margin-top:10px;resize:none;" cssErrorClass="text medium error"/>
                </td>
            </tr>
            <tr>
                <td><ng:locale key="linkmanGoods.usingFeedback"/>：</td>
                <td colspan="3">
                    <form:textarea path="usingFeedback" id="usingFeedback" cssClass="text medium" style="width:99%;height:70px;margin-top:10px;resize:none;" cssErrorClass="text medium error"/>
                </td>
            </tr>
            <tr>
               <td style="display:none"><form:input  path="userCode"   id="userCode"/></td>
               <td style="display:none"><form:input  path="registerTime"  id="registerTime"/></td>
               <td style="display:none"><form:input path="id" id="id" cssClass="text medium"/></td>
               <td></td>
            </tr>
        </table>
       <div class="tc"><input type="hidden" name="linkmanDemandGoodsFunction" value="" id="linkmanDemandGoodsFunction"/>
                	<button type="submit" name="save" id="save" class="btn btn-info"><ng:locale key="operation.button.save"/></button>
                    <button type="button" name="cancel"onclick="goBack()" class="btn btn-success"><ng:locale key="operation.button.cancel"/></button></div>
    </form:form>
    </div>
</div>
    <script src="<c:url value='/scripts/validator.jsp'/>"></script>
    <script src="js/jQuery-1.9.1.min.js"></script>
    <script src="js/joyLife.js"></script>
    <script>
             var jq = jQuery.noConflict();
          window.onload = function getDemandFuctionValue(){
               // document.getElementById("id").style.display='none';
               // document.getElementById("userCode").style.display='none';
               // document.getElementById("registerTime").style.display='none';
                
                var linkmanDemandGoodsFunction = "<%= request.getAttribute("linkmanDemandGoodsFunction")%>";
                jq("#linkmanDemandGoodsFunction").val(linkmanDemandGoodsFunction);
                
                var saveMark = "<%= request.getAttribute("saveMark")%>";
                if("saveMark"==saveMark){
                     document.getElementById("save").style.display='none';
                }else{
                     document.getElementById("save").style.display='';
                }
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
             var saveMark = "<%=request.getAttribute("saveMark")%>";
             //保存按钮的公示和隐藏
             if("saveMark"==saveMark){
                  document.getElementById("save").style.display='none';
             }else{
                  document.getElementById("save").style.display='';
             }
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