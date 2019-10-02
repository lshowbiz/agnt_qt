<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="pdShUrlDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='pdShUrlDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="pdShUrlList.pdShUrl"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="pdShUrlDetail.heading"/></h2>
    <fmt:message key="pdShUrlDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="pdShUrl" method="post" action="pdShUrlform" cssClass="well form-horizontal"
           id="pdShUrlForm" onsubmit="return validatePdShUrl(this)">
<ul>
    <spring:bind path="pdShUrl.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdShUrl.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdShUrl.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdShUrl.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdShUrl.createUserCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdShUrl.createUserCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createUserCode" id="createUserCode"  maxlength="20"/>
            <form:errors path="createUserCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdShUrl.exCompanyCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdShUrl.exCompanyCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="exCompanyCode" id="exCompanyCode"  maxlength="200"/>
            <form:errors path="exCompanyCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdShUrl.other">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdShUrl.other" styleClass="control-label"/>
        <div class="controls">
            <form:input path="other" id="other"  maxlength="100"/>
            <form:errors path="other" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdShUrl.shUrl">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdShUrl.shUrl" styleClass="control-label"/>
        <div class="controls">
            <form:input path="shUrl" id="shUrl"  maxlength="200"/>
            <form:errors path="shUrl" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdShUrl.valueCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdShUrl.valueCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="valueCode" id="valueCode"  maxlength="50"/>
            <form:errors path="valueCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdShUrl.valueTitle">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdShUrl.valueTitle" styleClass="control-label"/>
        <div class="controls">
            <form:input path="valueTitle" id="valueTitle"  maxlength="150"/>
            <form:errors path="valueTitle" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty pdShUrl.id}">
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

<v:javascript formName="pdShUrl" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['pdShUrlForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
