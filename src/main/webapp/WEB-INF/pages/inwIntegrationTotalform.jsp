<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="inwIntegrationTotalDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='inwIntegrationTotalDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="inwIntegrationTotalList.inwIntegrationTotal"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="inwIntegrationTotalDetail.heading"/></h2>
    <fmt:message key="inwIntegrationTotalDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="inwIntegrationTotal" method="post" action="inwIntegrationTotalform" cssClass="well form-horizontal"
           id="inwIntegrationTotalForm" onsubmit="return validateInwIntegrationTotal(this)">
<ul>
    <spring:bind path="inwIntegrationTotal.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwIntegrationTotal.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwIntegrationTotal.remark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwIntegrationTotal.remark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="remark" id="remark"  maxlength="200"/>
            <form:errors path="remark" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwIntegrationTotal.totalPoints">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwIntegrationTotal.totalPoints" styleClass="control-label"/>
        <div class="controls">
            <form:input path="totalPoints" id="totalPoints"  maxlength="10"/>
            <form:errors path="totalPoints" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwIntegrationTotal.uniqueCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwIntegrationTotal.uniqueCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="uniqueCode" id="uniqueCode"  maxlength="200"/>
            <form:errors path="uniqueCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwIntegrationTotal.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwIntegrationTotal.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty inwIntegrationTotal.id}">
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

<v:javascript formName="inwIntegrationTotal" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['inwIntegrationTotalForm']).focus();
    });
</script>
