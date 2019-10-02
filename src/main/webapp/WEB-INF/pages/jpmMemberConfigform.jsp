<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jpmMemberConfigDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='jpmMemberConfigDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jpmMemberConfigList.jpmMemberConfig"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jpmMemberConfigDetail.heading"/></h2>
    <fmt:message key="jpmMemberConfigDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jpmMemberConfig" method="post" action="jpmMemberConfigform" cssClass="well form-horizontal"
           id="jpmMemberConfigForm" onsubmit="return validateJpmMemberConfig(this)">
<ul>
    <spring:bind path="jpmMemberConfig.configNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmMemberConfig.configNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="configNo" id="configNo"/>
            <form:errors path="configNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmMemberConfig.amount">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmMemberConfig.amount" styleClass="control-label"/>
        <div class="controls">
            <form:input path="amount" id="amount"  maxlength="255"/>
            <form:errors path="amount" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmMemberConfig.companyCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmMemberConfig.companyCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="companyCode" id="companyCode"  maxlength="2"/>
            <form:errors path="companyCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmMemberConfig.completeamount">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmMemberConfig.completeamount" styleClass="control-label"/>
        <div class="controls">
            <form:input path="completeamount" id="completeamount"  maxlength="255"/>
            <form:errors path="completeamount" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmMemberConfig.createtime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmMemberConfig.createtime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createtime" id="createtime" size="11" title="date" datepicker="true"/>
            <form:errors path="createtime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmMemberConfig.moId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmMemberConfig.moId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="moId" id="moId"  maxlength="255"/>
            <form:errors path="moId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmMemberConfig.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmMemberConfig.status" styleClass="control-label"/>
        <div class="controls">
            <form:input path="status" id="status"  maxlength="2"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmMemberConfig.sysNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmMemberConfig.sysNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sysNo" id="sysNo"  maxlength="255"/>
            <form:errors path="sysNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpmMemberConfig.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpmMemberConfig.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jpmMemberConfig.configNo}">
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

<v:javascript formName="jpmMemberConfig" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jpmMemberConfigForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
