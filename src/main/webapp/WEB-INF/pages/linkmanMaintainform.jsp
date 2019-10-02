<%@ page contentType = "text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!--<title></title>-->
<!-- 客户管理－客户的商品录入/修改 -->
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/relationshipRecordManager.js'/>" ></script>
<script src="<c:url value='/scripts/My97DatePicker/WdatePicker.js'/>"></script>
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
        <table class="form_edit_table">
            <tr>
                <td nowrap="nowrap">
                    <span class="star"></span><ng:locale key="linkman.name"/>：
                </td>
                <td>
                   <form:input path="other" id="other" readonly="true"/>
                   <c:if test="${param.linkmanMaintainFunction == 'linkmanMaintainAdd'}">
                        <%-- <button id="search" type="button" onclick="selectLinkmanAdd()"><ng:locale key="operation.button.search"/></button> --%>
                   		<img src="images/search.gif" onclick="selectLinkmanAdd()" alt="<ng:locale key="operation.button.search"/>"/>
                   </c:if>
                    <c:if test="${param.linkmanMaintainFunction == 'linkmanMaintainUpdate'}">
                        <%-- <button id="search" type="button" onclick="selectLinkman(${linkmanMaintain.id})"><ng:locale key="operation.button.search"/></button> --%>
                   		<img src="images/search.gif" onclick="selectLinkman(${linkmanMaintain.id})" alt="<ng:locale key="operation.button.search"/>"/>
                   </c:if>
                   <form:input path="linkmanId" id="linkmanId" cssClass="text medium"  size="50" cssStyle="display:none"/>
                   <input type="text" id="linkmanMaintainFunction" name="linkmanMaintainFunction" value="${param.linkmanMaintainFunction}" style="display:none">
                    <!-- 
                    <select name ="linkmanId" id="linkmanId" class="mySelect">
                        <option value=""><ng:locale key="list.please.select"/></option>
                    </select>
                     -->
                </td>
                <!--<td>
                   <label class="star"><ng:locale key="relationshipRecord.linkmanCategory"/>：</label>  -->
                   <!--   
                    <form:select path="lcId" id="lcId" class="mySelect" onchange="getLinkmanById(this.value)">
                        <form:option label="" value=""><ng:locale key='relationshiipRecord.Ungrouped'/></form:option>
                        <form:options items="${linkmanCategoryList}"  itemValue="id" itemLabel="name"/>
                    </form:select>
                                
                </td>-->
                <td><span class="star"></span><ng:locale key="linkmanMaintain.maintenanceTopic"/>：</td>
            	<td><form:input path="maintenanceTopic" id="maintenanceTopic" /></td>
            </tr>
            </tr>
            <td><ng:locale key="linkmanMaintain.maintenanceTime"/>：</td>
            <td>
            	<form:input path="maintenanceTime" id="maintenanceTime" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>                          
            </td>
            <td><span class="star"></span><ng:locale key="linkmanMaintain.maintenanceMode"/>：</td>
            <td>
            <ng:list name="maintenanceMode" listCode="linkmanmaintain.type" defaultValue="false" value="${linkmanMaintain.maintenanceMode}"/><!-- 维护方式的参数列表 -->
            </td>
            </tr>
            <tr>
            <td><span class="star"></span><ng:locale key="linkmanMaintain.maintenanceContent"/>：</td>
            <td colspan="3">
            <form:textarea path="maintenanceContent" id="maintenanceContent" style="width:99%;height:70px;margin-top:10px;resize:none;" />
            </td>
            <td></td>
            </tr>
            <tr>
                <td><ng:locale key="linkmanMaintain.maintenanceEffect"/>：</td>
                <td colspan="3">
                    <form:textarea path="maintenanceEffect" id="maintenanceEffect" style="width:99%;height:70px;margin-top:10px;resize:none;"/>
                </td>
            </tr>
            <tr>
                <td><ng:locale key="jamPromotion.analyzed"/>：</td>
                <td colspan="3">
                    <form:textarea path="summary" id="summary" style="width:99%;height:70px;margin-top:10px;resize:none;" />
                </td>
            </tr>
            
            <tr>
            <td><form:input  path="userCode"   id="userCode"  cssStyle="display:none"/></td>
            <td><input type="hidden" name="linkmanMaintainFunction" value="" id="linkmanMaintainFunction"/></td>
            <td><form:input path="id" id="id" cssClass="text medium" cssStyle="display:none"/></td>
            </tr>
        </table>
       <div class="tc" ><button type="submit" name="save" id="save" class="btn btn-info"><ng:locale key="operation.button.save"/></button>
       <button type="button" name="cancel"onclick="goBack()" class="btn btn-success"><ng:locale key="operation.button.cancel"/></button>
                    </div>
    </form:form>
    </div>
</div>
    <script src="<c:url value='/scripts/validator.jsp'/>"></script>
    <script src="js/jQuery-1.9.1.min.js"></script>
    <script src="js/joyLife.js"></script>
    <script>
             var jq = jQuery.noConflict();
          window.onload = function getDemandFuctionValue(){
                //document.getElementById("id").style.display='none';
                //document.getElementById("userCode").style.display='none';
                var saveMark = "<%=request.getAttribute("saveMark")%>";
	             //保存按钮的公示和隐藏
	             if("saveMark"==saveMark){
	                  document.getElementById("save").style.display='none';
	             }else{
	                  document.getElementById("save").style.display='';
	             }
                var linkmanMaintainFunction = "<%= request.getAttribute("linkmanMaintainFunction")%>";
                jq("#linkmanMaintainFunction").val(linkmanMaintainFunction);
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
          //   var ddd = jq("#lcId").val();
          //   var userCode = "<%=request.getAttribute("userCode")%>";
          //   relationshipRecordManager.getLinkmanNameByLcId(ddd,userCode,linkmanNameLl);
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
      
      //选择联系人的方法(新增)
      function selectLinkmanAdd(){
             var linkmanMaintainFunction = document.getElementById("linkmanMaintainFunction").value;
             window.location.href="jadLinkmanQuery.html?strAction=linkmanMaintainSelect&linkmanMaintainFunction="+linkmanMaintainFunction;
      }
      
      
      //选择联系人的方法(编辑)
      function selectLinkman(maintainId){
           var linkmanMaintainFunction = document.getElementById("linkmanMaintainFunction").value;
           window.location.href="jadLinkmanQuery.html?strAction=linkmanMaintainSelect&maintainId="+maintainId+"&linkmanMaintainFunction"+linkmanMaintainFunction;
      }
      
      getLinkmanById();
</script>
</body>