<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="mailStatusDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='mailStatusDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="mailStatusList.mailStatus"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="mailStatusDetail.heading"/></h2>
    <fmt:message key="mailStatusDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="mailStatus" method="post" action="mailStatusform" cssClass="well form-horizontal"
           id="mailStatusForm" onsubmit="return validateMailStatus(this)">
<ul>
    <spring:bind path="mailStatus.statusId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="mailStatus.statusId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="statusId" id="statusId"/>
            <form:errors path="statusId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="mailStatus.logistComp">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="mailStatus.logistComp" styleClass="control-label"/>
        <div class="controls">
            <form:input path="logistComp" id="logistComp"  maxlength="200"/>
            <form:errors path="logistComp" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="mailStatus.mailNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="mailStatus.mailNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="mailNo" id="mailNo"  maxlength="100"/>
            <form:errors path="mailNo" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty mailStatus.statusId}">
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

<v:javascript formName="mailStatus" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['mailStatusForm']).focus();
    });
</script>
