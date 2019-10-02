<%@ page contentType = "text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!--<title>联系记录－添加</title>-->
<!-- 日历样式 -->
<script src="<c:url value='/scripts/My97DatePicker/WdatePicker.js'/>"></script>
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/relationshipRecordManager.js'/>" ></script>	
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
    <form:form commandName="relationshipRecord" method="post" action="jadRelationshipRecordAdd" id="relationshipRecordAddId" onsubmit="if(isFormPosted()){return true;}{return false;}">
        <table class="form_edit_table">
            <tr>
                <td><span class="star"></span><ng:locale key="relationshipRecord.subject"/>：</td>
                <td><form:input path="subject" id="subject" maxlength="200"/></td>
                <td><span class="star"></span><ng:locale key="relationshipRecord.contactType"/>：</td>
                <td colspan="2">
                    <ng:list name="contactType" listCode="relationshiprecord.type" value="${relationshipRecord.contactType}" styleClass="mySelect" defaultValue=""/>
                </td>
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
                <td style="vertical-align:top;"><ng:locale key="relationshipRecord.content"/>：</td>
                <td colspan="5">
                    <form:textarea path="content" id="content" cssClass="text medium" style="width:99%;height:70px;margin-top:10px;resize:none;" cssErrorClass="text medium error" onkeydown="checkNumber(this.value)" />
                </td>
            </tr>
        </table>
		<div class="tc">
            <button type="submit" name="save" id="update" class="btn btn-info"><ng:locale key="operation.button.save"/></button>
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
                  document.getElementById("update").style.display='none';
             }else{
                  document.getElementById("update").style.display='';
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
			   		if(''!='${relationshipRecord.linkmanId}'){
			   		jq("#linkmanId").val('${relationshipRecord.linkmanId}');
			   		}
      }
      
      //返回按钮的方法  
      function goBack(){
            window.location.href="jadRelationshipRecordQuery";
      } 
      
      getLinkmanById();
</script>
</body>