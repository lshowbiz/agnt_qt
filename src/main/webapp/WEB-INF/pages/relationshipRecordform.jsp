<%@ include file="/common/taglibs.jsp"%>

<head>
    <!--<title><fmt:message key="relationshipRecordDetail.title"/></title>
    --><meta name="heading" content="<fmt:message key='relationshipRecordDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="relationshipRecordList.relationshipRecord"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="relationshipRecordDetail.heading"/></h2>
    <fmt:message key="relationshipRecordDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="relationshipRecord" method="post" action="relationshipRecordform" cssClass="well form-horizontal"
           id="relationshipRecordForm" onsubmit="return validateRelationshipRecord(this)">
<ul>
    <spring:bind path="relationshipRecord.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="relationshipRecord.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="relationshipRecord.contactTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="relationshipRecord.contactTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="contactTime" id="contactTime" size="11" title="date" datepicker="true"/>
            <form:errors path="contactTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="relationshipRecord.contactType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="relationshipRecord.contactType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="contactType" id="contactType"  maxlength="255"/>
            <form:errors path="contactType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="relationshipRecord.content">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="relationshipRecord.content" styleClass="control-label"/>
        <div class="controls">
            <form:input path="content" id="content"  maxlength="500"/>
            <form:errors path="content" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="relationshipRecord.linkmanId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="relationshipRecord.linkmanId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="linkmanId" id="linkmanId"  maxlength="255"/>
            <form:errors path="linkmanId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="relationshipRecord.loginUserNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="relationshipRecord.loginUserNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="loginUserNo" id="loginUserNo"  maxlength="50"/>
            <form:errors path="loginUserNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="relationshipRecord.subject">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="relationshipRecord.subject" styleClass="control-label"/>
        <div class="controls">
            <form:input path="subject" id="subject"  maxlength="200"/>
            <form:errors path="subject" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="relationshipRecord.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="relationshipRecord.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty relationshipRecord.id}">
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

<v:javascript formName="relationshipRecord" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['relationshipRecordForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
