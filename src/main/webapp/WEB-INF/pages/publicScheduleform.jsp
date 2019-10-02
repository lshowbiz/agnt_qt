<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>

<head>
    <title><ng:locale key="schedule.message"></ng:locale></title>
    <meta name="heading" content="<ng:locale key='scheduleManageDetail.heading'/>"/>
    <meta name="menu" content="ScheduleManageMenu"/>
    <meta name="parentMenu" content="CommerceMenu"/>
    <!-- <link rel=stylesheet href="styles/fullcalendar/ca9ck1eg.css"> -->
	<link rel=stylesheet href="styles/tbsp.css">
	<link rel=stylesheet type=text/css href="styles/fullcalendar/account.css">
	<link rel=stylesheet type=text/css href="styles/fullcalendar/common_v2.css">
	<script type='text/javascript' src='styles/fullcalendar/jquery-1.5.2.min.js'></script>
    <style>
		select {
			width: 145px;
		}
		textarea {
			width: 336px;
			heigth:150px;
		}
		#topnav .guid {
			width: 990px;
		}
	</style>
	
</head>

<div class="cont">	
	<div class="bt mt">
		<h3 class="color2 ml"><ng:locale key="publicSchedule.message"></ng:locale></h3>
	</div>



<form:form commandName="publicSchedule" method="get" action="publicScheduleform" id="publicScheduleForm" onsubmit="return validateScheduleManage(this)">
<form:errors path="*" cssClass="error" element="div"/>
<form:hidden path="psId" />
<%-- <h2 class=h2-single><span class=entity><ng:locale key="publicSchedule.message"></ng:locale> </span></h2> --%>

<div id=main-content class="account-security profile-setting">
<ul id=ah:addressform class="elem-form section security-profile trade:addresseditor">
	<li>
        <label for="publicSchedule" class=label-like>
		<span class="spark-indeed">*</span>
		<ng:locale key="schedule.theme"></ng:locale> ：</label>
        <form:errors path="name" cssClass="fieldError"/>
        <%-- <form:input path="name" id="name" cssClass="text medium" cssErrorClass="text medium error" size="40"/> --%>
        ${publicSchedule.name }
    </li>
 
	  <c:if test="${!empty publicSchedule.type}">
	     <li>
      	  <label for="type" class=label-like><ng:locale key="schedule.type"></ng:locale>：</label>
      	  ${publicSchedule.type }
         </li>
	  </c:if>
	  	

	     <li>
        <label for="startTime" class=label-like><ng:locale key="schedule.startTime"></ng:locale>：</label>
        <form:errors path="startTime" cssClass="fieldError"/>
       <%--  <form:input path="startTime" id="startTime" cssClass="text medium" readonly="false"
        cssErrorClass="text medium error" maxlength="7"/>
        <img src="${ctx}/images/calendar/calendar7.gif" id="img_startTime" 
		style="cursor: pointer;" /> 
		<script> 
			Calendar.setup({
			inputField     :    "startTime", 
			ifFormat       :    "%Y-%m-%d",  
			button         :    "img_startTime", 
			singleClick    :    true
			}); 
		</script>  --%>
		${publicSchedule.startTime }
    </li>
    	<c:if test="${!empty publicSchedule.endTime}">
    <li>
        <label for="endTime" class=label-like><ng:locale key="schedule.endTime"></ng:locale>：</label>
        <form:errors path="endTime" cssClass="fieldError"/>
       <%--  <form:input path="endTime" id="endTime" cssClass="text medium" readonly="false"
        cssErrorClass="text medium error" maxlength="7"/>
        <img src="${ctx}/images/calendar/calendar7.gif" id="img_endTime" 
		style="cursor: pointer;" /> 
		<script> 
			Calendar.setup({
			inputField     :    "endTime", 
			ifFormat       :    "%Y-%m-%d",  
			button         :    "img_endTime", 
			singleClick    :    true
			}); 
		</script>  --%>
		${publicSchedule.endTime }
    </li>
	  </c:if>
    
    <c:if test="${!empty publicSchedule.content}">
    <li>
        <label for="content" class=label-like><ng:locale key="publicSchedule.content"></ng:locale>：</label>
        <form:errors path="content" cssClass="fieldError"/>
        <%-- <form:textarea path="content" id="content" cssClass="text medium" cssErrorClass="text medium error"
        rows="8" cols="6"/> --%>
        ${publicSchedule.content }
    </li>
    </c:if>
    <li>
		<button type="button" class="btn btn-success" onclick="javascript:history.back(-2);" >返&nbsp;&nbsp;回</button>
 	</li>
    
    </ul>
    </div>
    </form:form>
    </div>
    
<v:javascript formName="publicSchedule" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['publicScheduleForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
