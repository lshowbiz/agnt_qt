<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jmiBlacklistDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jmiBlacklistList.jmiBlacklist"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jmiBlacklistDetail.heading"/></h2>
    <fmt:message key="jmiBlacklistDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jmiBlacklist" method="post" action="jmiBlacklistform" cssClass="well form-horizontal"
           id="jmiBlacklistForm" onsubmit="return validateJmiBlacklist(this)">
<ul>
    <spring:bind path="jmiBlacklist.blId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiBlacklist.blId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="blId" id="blId"/>
            <form:errors path="blId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiBlacklist.blackType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiBlacklist.blackType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="blackType" id="blackType"  maxlength="20"/>
            <form:errors path="blackType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiBlacklist.companyCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiBlacklist.companyCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="companyCode" id="companyCode"  maxlength="2"/>
            <form:errors path="companyCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiBlacklist.createNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiBlacklist.createNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createNo" id="createNo"  maxlength="20"/>
            <form:errors path="createNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiBlacklist.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiBlacklist.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiBlacklist.papernumber">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiBlacklist.papernumber" styleClass="control-label"/>
        <div class="controls">
            <form:input path="papernumber" id="papernumber"  maxlength="100"/>
            <form:errors path="papernumber" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiBlacklist.papertype">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiBlacklist.papertype" styleClass="control-label"/>
        <div class="controls">
            <form:input path="papertype" id="papertype"  maxlength="20"/>
            <form:errors path="papertype" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiBlacklist.remark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiBlacklist.remark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="remark" id="remark"  maxlength="500"/>
            <form:errors path="remark" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jmiBlacklist.blId}">
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

<v:javascript formName="jmiBlacklist" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jmiBlacklistForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
