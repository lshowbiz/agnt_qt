<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!--<title>联系记录-修改</title>-->
<!-- 时间控件的样式 -->
	<script src="<c:url value='/scripts/My97DatePicker/WdatePicker.js'/>"></script>
	<script src="<c:url value='/scripts/validator.jsp'/>"></script>
    <!-- DWR的控件 -->
    <script type="text/javascript" src="<c:url value='/dwr/util.js'/>" ></script> 
    <script type="text/javascript" src="<c:url value='/dwr/engine.js'/>" ></script>
    <script type="text/javascript" src="<c:url value='/dwr/interface/relationshipRecordManager.js'/>" ></script>
    <script src="js/jQuery-1.9.1.min.js"></script>
	<!-- 校验时的提示语 -->
	<spring:bind path="relationshipRecord.*">
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
			<h3 class="color2 ml"><ng:locale  key="relationshipRecord.baseInformation"/></h3>
		</div>	
	<div class="mt">
<form:form commandName="relationshipRecord" method="post" action="jadRelationshipRecordAdd" id="jadRelationshipRecordAddId" onsubmit="if(isFormPosted()){return true;}{return false;}">
	<table class="form_edit_table">
		<tr>
			<td><span class="star"></span><ng:locale key="relationshipRecord.subject"/>：</td>
			<td><form:input path="subject" id="subject" maxlength="200"/></td>
			<td><span class="star"></span><ng:locale key="relationshipRecord.contactType"/>：</td>
			<td><ng:list name="contactType" listCode="relationshiprecord.type" value="${relationshipRecord.contactType}" defaultValue="" styleClass="mySelect"/></td>
			<td id="idOne" colspan="2"><form:input path="id" id="id" maxlength="200"/></td>
		</tr>
		<tr>
			<td><span class="star"></span><ng:locale key="relationshipRecord.linkmanCategory"/>：</td>
			<td>
				<form:select path="lcId" id="lcId" onchange="getLinkmanById(this.value)">
					<form:option label="" value="ungrouped"><ng:locale key='relationshiipRecord.Ungrouped'/></form:option>
					<form:options items="${categoryNameList}"  itemValue="id" itemLabel="name"/>
				</form:select>
			</td> 
			<td><span class="star"></span><ng:locale key="relationshipRecord.contact"/>：</td>
			<td>
				<select name ="linkmanId" id="linkmanId">
					 <option value=""><ng:locale key="list.please.select"/></option>
			   </select>
			</td>
			<td><span class="star"></span><ng:locale key="relationshipRecord.contactTime"/>：</td>
			<td>
				<form:input path="contactTime" id="contactTime" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
			</td>
		</tr>
		<tr>
			<td><ng:locale key="relationshipRecord.content"/>：</td>
			<td colspan="5">
			<form:textarea path="content" id="content" onkeydown="checkNumber(this.value)" style="width:99%;height:70px;margin-top:10px;padding:5px;resize:none;" />
			</td>
		</tr>
	</table>
	<div class="tc">
	<button type="submit" class="btn btn-info" id="update"><ng:locale key="operation.button.save"/></button>
	<button type="button" class="btn btn-success" onclick="goBack()"><ng:locale key="operation.button.cancel"/></button>
	</div>
</form:form> 
<script>
      
        var jq = jQuery.noConflict();
      //文本域输入字符个数的校验
      function checkNumber(content){
              //alert("备注只能输入500个字符");
              if(content.length>250){
                   alert("备注只能输入500个字符");
                   document.getElementById("content").readOnly=true;
              }
      }
      //返回按钮的方法  
      function goBack(){
            window.location.href="jadRelationshipRecordQuery.html";
      } 
      
      //根据客户分类，查询相应分类下的客户名称
     function getLinkmanById(){
              //隐藏主键的值
              document.getElementById("idOne").style.display='none';
              var idlc = jq("#lcId").val();
             var userCode = "<%=request.getAttribute("userCode")%>";
             relationshipRecordManager.getLinkmanNameByLcId(idlc,userCode,linkmanNameLl);
      }
      
      //回调函数
      function linkmanNameLl(valid){
                  DWRUtil.removeAllOptions("linkmanId");
		 		  DWRUtil.addOptions("linkmanId",{'':'<ng:locale key='list.please.select'/>'});  
                  DWRUtil.addOptions("linkmanId",valid,"id","name");
			   		if(''!='${relationshipRecord.linkmanId}'){
			   		jq("#linkmanId").val('${relationshipRecord.linkmanId}');
			   		}
      }
       getLinkmanById();
</script>
</body>