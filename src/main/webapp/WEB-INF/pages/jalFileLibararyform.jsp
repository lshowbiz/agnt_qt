<%@ page language="java"  contentType="text/html;charset=utf-8"%><%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jalFileLibararyDetail.heading'/>"/>

</head>
<div class="content fr">
	<h2 class="title"><span>资料中心</span></h2>

<form:form commandName="jalFileLibarary" method="post" action="jalFileLibararyform" cssClass="well form-horizontal"
           id="jalFileLibararyForm">
<input type="hidden" name="fid" value="${jalFileLibarary.FId}"/>   
<div class="pdMainL fl" style="background-color:#FFF;">
			<div class="clearfix" style="margin:15px 0 30px 0;">
				<div class="xzImg fl"><img src="${jalFileLibarary.logoImg}" width="200" height="200"/></div>
				<div class="xzInfo fl">
					<p class="p1 bold"><span style="font-size:18px">${jalFileLibarary.name}</span></p>
					
					<p><span>　　类别：<ng:code  listCode="file.type.list" value="${jalFileLibarary.typeId}"/></span></p>
					<p><span>　　大小：${jalFileLibarary.fileSize}</span></p>
					<p><span>　　作者：${jalFileLibarary.author}</span></p>
					<p><span>　　更新时间：${jalFileLibarary.createTime}</span></p>
					
					<p style="padding-left:45px;padding-top:20px"><button type="submit" class="btn-primary btn_common ft14" name="save" onclick="bCancel=false">
					            	<i class="icon-ok icon-white"></i>&nbsp;点击下载&nbsp;
						        </button></p>
				
						        
				</div>
			</div>
			<ul class="pdTags" id="js_pdTags">
				<li><a href="#tab1" class="current">详情描述</a></li>
				
			</ul>
			<div class="pdContent" id="tab1">

				<div class="pdText">
					<h5 class="ft14 colorCS">[资料简介]</h5>
					<p>${jalFileLibarary.remark }
					</p>
				</div>
				<div class="pdText">
					<h5 class="ft14 colorCS">[详细介绍]</h5>
					<p>${jalFileLibarary.notes }
					</p>
				</div>
				
			</div>

		</div>

</form:form>
</div>

<!--  
<c:set var="delObject" scope="request"><fmt:message key="jalFileLibararyList.jalFileLibarary"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jalFileLibararyDetail.heading"/></h2>
    <fmt:message key="jalFileLibararyDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jalFileLibarary" method="post" action="jalFileLibararyform" cssClass="well form-horizontal"
           id="jalFileLibararyForm" onsubmit="return validateJalFileLibarary(this)">
<ul>
    <spring:bind path="jalFileLibarary.FId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalFileLibarary.FId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="FId" id="FId"/>
            <form:errors path="FId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalFileLibarary.author">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalFileLibarary.author" styleClass="control-label"/>
        <div class="controls">
            <form:input path="author" id="author"  maxlength="300"/>
            <form:errors path="author" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalFileLibarary.createName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalFileLibarary.createName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createName" id="createName"  maxlength="300"/>
            <form:errors path="createName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalFileLibarary.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalFileLibarary.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalFileLibarary.createUserCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalFileLibarary.createUserCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createUserCode" id="createUserCode"  maxlength="20"/>
            <form:errors path="createUserCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalFileLibarary.fileSize">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalFileLibarary.fileSize" styleClass="control-label"/>
        <div class="controls">
            <form:input path="fileSize" id="fileSize"  maxlength="20"/>
            <form:errors path="fileSize" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalFileLibarary.logoImg">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalFileLibarary.logoImg" styleClass="control-label"/>
        <div class="controls">
            <form:input path="logoImg" id="logoImg"  maxlength="300"/>
            <form:errors path="logoImg" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalFileLibarary.name">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalFileLibarary.name" styleClass="control-label"/>
        <div class="controls">
            <form:input path="name" id="name"  maxlength="300"/>
            <form:errors path="name" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalFileLibarary.notes">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalFileLibarary.notes" styleClass="control-label"/>
        <div class="controls">
            <form:input path="notes" id="notes"  maxlength="255"/>
            <form:errors path="notes" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalFileLibarary.remark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalFileLibarary.remark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="remark" id="remark"  maxlength="300"/>
            <form:errors path="remark" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalFileLibarary.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalFileLibarary.status" styleClass="control-label"/>
        <div class="controls">
            <form:input path="status" id="status"  maxlength="255"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalFileLibarary.typeId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalFileLibarary.typeId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="typeId" id="typeId"  maxlength="20"/>
            <form:errors path="typeId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalFileLibarary.url">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalFileLibarary.url" styleClass="control-label"/>
        <div class="controls">
            <form:input path="url" id="url"  maxlength="300"/>
            <form:errors path="url" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jalFileLibarary.FId}">
            <button type="submit" class="btn btn-warning" name="delete" onclick="bCancel=true;return confirmMessage(msgDelConfirm)">
                <i class="icon-trash icon-white"></i> <fmt:message key="button.delete"/>
            </button>
        </c:if>

        <button type="submit" class="btn" name="cancel" onclick="bCancel=true">
            <i class="icon-remove"></i> <fmt:message key="button.cancel"/>
        </button>
    </div>
</form:form>
</div>
-->
<v:javascript formName="jalFileLibarary" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jalFileLibararyForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
