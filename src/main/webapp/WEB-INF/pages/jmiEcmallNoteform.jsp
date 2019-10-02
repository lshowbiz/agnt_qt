<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jmiEcmallNoteDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='jmiEcmallNoteDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jmiEcmallNoteList.jmiEcmallNote"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jmiEcmallNoteDetail.heading"/></h2>
    <fmt:message key="jmiEcmallNoteDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jmiEcmallNote" method="post" action="jmiEcmallNoteform" cssClass="well form-horizontal"
           id="jmiEcmallNoteForm" onsubmit="return validateJmiEcmallNote(this)">
<ul>
    <spring:bind path="jmiEcmallNote.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiEcmallNote.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiEcmallNote.code">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiEcmallNote.code" styleClass="control-label"/>
        <div class="controls">
            <form:input path="code" id="code"  maxlength="255"/>
            <form:errors path="code" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiEcmallNote.createNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiEcmallNote.createNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createNo" id="createNo"  maxlength="20"/>
            <form:errors path="createNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiEcmallNote.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiEcmallNote.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiEcmallNote.info">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiEcmallNote.info" styleClass="control-label"/>
        <div class="controls">
            <form:input path="info" id="info"  maxlength="1000"/>
            <form:errors path="info" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiEcmallNote.noteTyoe">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiEcmallNote.noteTyoe" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="noteTyoe" id="noteTyoe" cssClass="checkbox"/>
            <form:errors path="noteTyoe" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiEcmallNote.remark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiEcmallNote.remark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="remark" id="remark"  maxlength="4000"/>
            <form:errors path="remark" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiEcmallNote.url">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiEcmallNote.url" styleClass="control-label"/>
        <div class="controls">
            <form:input path="url" id="url"  maxlength="1000"/>
            <form:errors path="url" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiEcmallNote.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiEcmallNote.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jmiEcmallNote.id}">
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

<v:javascript formName="jmiEcmallNote" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jmiEcmallNoteForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
