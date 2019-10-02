<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="foundationItemDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='foundationItemDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="foundationItemList.foundationItem"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="foundationItemDetail.heading"/></h2>
    <fmt:message key="foundationItemDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="foundationItem" method="post" action="foundationItemform" cssClass="well form-horizontal"
           id="foundationItemForm" onsubmit="return validateFoundationItem(this)">
<ul>
    <spring:bind path="foundationItem.fiId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="foundationItem.fiId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="fiId" id="fiId"/>
            <form:errors path="fiId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="foundationItem.amount">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="foundationItem.amount" styleClass="control-label"/>
        <div class="controls">
            <form:input path="amount" id="amount"  maxlength="255"/>
            <form:errors path="amount" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="foundationItem.field1">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="foundationItem.field1" styleClass="control-label"/>
        <div class="controls">
            <form:input path="field1" id="field1"  maxlength="50"/>
            <form:errors path="field1" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="foundationItem.field2">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="foundationItem.field2" styleClass="control-label"/>
        <div class="controls">
            <form:input path="field2" id="field2"  maxlength="50"/>
            <form:errors path="field2" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="foundationItem.name">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="foundationItem.name" styleClass="control-label"/>
        <div class="controls">
            <form:input path="name" id="name"  maxlength="50"/>
            <form:errors path="name" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="foundationItem.prompt">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="foundationItem.prompt" styleClass="control-label"/>
        <div class="controls">
            <form:input path="prompt" id="prompt"  maxlength="200"/>
            <form:errors path="prompt" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="foundationItem.remark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="foundationItem.remark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="remark" id="remark"  maxlength="300"/>
            <form:errors path="remark" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="foundationItem.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="foundationItem.status" styleClass="control-label"/>
        <div class="controls">
            <form:checkbox path="status" id="status" cssClass="checkbox"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="foundationItem.type">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="foundationItem.type" styleClass="control-label"/>
        <div class="controls">
            <form:input path="type" id="type"  maxlength="2"/>
            <form:errors path="type" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="foundationItem.unit">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="foundationItem.unit" styleClass="control-label"/>
        <div class="controls">
            <form:input path="unit" id="unit"  maxlength="20"/>
            <form:errors path="unit" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty foundationItem.fiId}">
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

<v:javascript formName="foundationItem" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['foundationItemForm']).focus();
    });
</script>
