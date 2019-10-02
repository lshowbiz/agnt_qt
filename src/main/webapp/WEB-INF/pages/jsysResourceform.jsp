<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jsysResourceDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jsysResourceList.jsysResource"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jsysResourceDetail.heading"/></h2>
    <fmt:message key="jsysResourceDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jsysResource" method="post" action="jsysResourceform" cssClass="well form-horizontal"
           id="jsysResourceForm" onsubmit="return validateJsysResource(this)">
<ul>
    <spring:bind path="jsysResource.resId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysResource.resId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="resId" id="resId"/>
            <form:errors path="resId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysResource.active">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysResource.active" styleClass="control-label"/>
        <div class="controls">
            <form:input path="active" id="active"  maxlength="1"/>
            <form:errors path="active" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysResource.orderNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysResource.orderNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="orderNo" id="orderNo"  maxlength="255"/>
            <form:errors path="orderNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysResource.parentId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysResource.parentId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="parentId" id="parentId"  maxlength="255"/>
            <form:errors path="parentId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysResource.resCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysResource.resCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="resCode" id="resCode"  maxlength="100"/>
            <form:errors path="resCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysResource.resDes">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysResource.resDes" styleClass="control-label"/>
        <div class="controls">
            <form:input path="resDes" id="resDes"  maxlength="500"/>
            <form:errors path="resDes" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysResource.resName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysResource.resName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="resName" id="resName"  maxlength="500"/>
            <form:errors path="resName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysResource.resType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysResource.resType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="resType" id="resType"  maxlength="255"/>
            <form:errors path="resType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysResource.resUrl">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysResource.resUrl" styleClass="control-label"/>
        <div class="controls">
            <form:input path="resUrl" id="resUrl"  maxlength="500"/>
            <form:errors path="resUrl" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysResource.sysType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysResource.sysType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sysType" id="sysType"  maxlength="10"/>
            <form:errors path="sysType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysResource.treeIndex">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysResource.treeIndex" styleClass="control-label"/>
        <div class="controls">
            <form:input path="treeIndex" id="treeIndex"  maxlength="500"/>
            <form:errors path="treeIndex" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysResource.treeLevel">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysResource.treeLevel" styleClass="control-label"/>
        <div class="controls">
            <form:input path="treeLevel" id="treeLevel"  maxlength="255"/>
            <form:errors path="treeLevel" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jsysResource.validateFlag">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jsysResource.validateFlag" styleClass="control-label"/>
        <div class="controls">
            <form:input path="validateFlag" id="validateFlag"  maxlength="1"/>
            <form:errors path="validateFlag" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jsysResource.resId}">
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

<v:javascript formName="jsysResource" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jsysResourceForm']).focus();
    });
</script>
