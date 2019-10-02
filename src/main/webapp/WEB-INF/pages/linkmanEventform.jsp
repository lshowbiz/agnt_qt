<%@ page contentType = "text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!--<title></title>-->
<!-- 客户管理－事件管理-录入或修改 -->

<script src="<c:url value='/scripts/My97DatePicker/WdatePicker.js'/>"></script>
<spring:bind path="linkmanEvent.*">
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
			<h3 class="color2 ml"><ng:locale  key="linkmanEvent.event"/></h3>
		</div>	
	<div class="mt">
    <form:form commandName="linkmanEvent" method="post" action="linkmanEventform" id="linkmanEventform" name="linkmanEventform" onsubmit="return validateOthers()">
        <form:hidden path="id" id="id"/>
        <form:hidden  path="userCode"   id="userCode"/>
        <table class="form_edit_table">
            <tr>
            	<td><span class="star"></span><ng:locale key="linkmanEvent.title"/>：</td>
            	<td>
            		<form:input path="title" id="title" cssClass="text medium"  size="50" maxlength="30"/>
            	</td>
            	<td><span class="star"></span>会员编号：</td>
	            <td>
	            	<form:input path="mCode" id="mCode" maxlength="20" cssStyle="ime-mode:disabled"/>
	            </td>
	            <td></td>
	            <td></td>
            </tr>
            <tr>
                <td><span class="star"></span>会员姓名：</td>
	            <td><form:input path="mName" id="mName" maxlength="10"/></td>
				<td><span class="star"></span><ng:locale key="linkmanEvent.eventType"/>：</td>
            	<td>
                	<ng:list name="eventType" listCode="linkmanevent.type" value="${linkmanEvent.eventType}" defaultValue=""/>
           		</td>
           		<td>发生时间：</td>
           		<td>
           			<form:input path="time" id="time" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
           		</td>
            </tr>
            <tr>
                <td><ng:locale key="linkmanEvent.description"/>：</td>
                <td colspan="5">
                    <form:textarea path="description" id="description" cssClass="text medium" style="width:99%;height:70px;margin-top:10px;resize:none;" cssErrorClass="text medium error" onkeydown="checkNumber(this.value)"/>
                </td>
            </tr>
        </table>
       <div class="tc">
          	<button type="submit" name="save" id="save" class="btn btn-info"><ng:locale key="operation.button.save"/></button>
       		<button type="button" name="cancel" onclick="window.location.href='linkmanEvents?1=1'" class="btn btn-success"><ng:locale key="operation.button.cancel"/></button>
       </div>
    </form:form>
	</div>
</div>
<script type="text/javascript">
    function validateOthers(){
		
  		var destin_user=document.linkmanEventform.mCode;
	  	if(destin_user.value==""){
				alert("会员编号不能为空!");
				destin_user.focus();
				return false;
		}
		var mName=document.linkmanEventform.mName;
	  	if(mName.value==""){
				alert("会员姓名不能为空!");
				mName.focus();
				return false;
		}
		var title=document.linkmanEventform.title;
	  	if(title.value==""){
				alert("标题不能为空!");
				title.focus();
				return false;
		}
		var eventType=document.linkmanEventform.eventType;
	  	if(eventType.value=='0'){
				alert("请选择事件类型!");
				eventType.focus();
				return false;
		}
	}
	
	  //文本域输入内容个数的校验
      function checkNumber(mark){
              if(mark.length>300){
                   alert("做多可允许输入300个字，请重新输入！");
                   document.getElementById("description").value="";
                   
              }
       }
	
</script>     
</body>
