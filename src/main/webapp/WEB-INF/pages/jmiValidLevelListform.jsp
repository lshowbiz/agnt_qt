<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jmiValidLevelListDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='jmiValidLevelListDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jmiValidLevelListList.jmiValidLevelList"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jmiValidLevelListDetail.heading"/></h2>
    <fmt:message key="jmiValidLevelListDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jmiValidLevelList" method="post" action="jmiValidLevelListform" cssClass="well form-horizontal"
           id="jmiValidLevelListForm" onsubmit="return validateJmiValidLevelList(this)">
<ul>
    <spring:bind path="jmiValidLevelList.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiValidLevelList.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiValidLevelList.WWeek">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiValidLevelList.WWeek" styleClass="control-label"/>
        <div class="controls">
            <form:input path="WWeek" id="WWeek"  maxlength="255"/>
            <form:errors path="WWeek" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiValidLevelList.createNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiValidLevelList.createNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createNo" id="createNo"  maxlength="20"/>
            <form:errors path="createNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiValidLevelList.isLock">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiValidLevelList.isLock" styleClass="control-label"/>
        <div class="controls">
            <form:input path="isLock" id="isLock"  maxlength="2"/>
            <form:errors path="isLock" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiValidLevelList.newMemberLevel">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiValidLevelList.newMemberLevel" styleClass="control-label"/>
        <div class="controls">
            <form:input path="newMemberLevel" id="newMemberLevel"  maxlength="255"/>
            <form:errors path="newMemberLevel" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiValidLevelList.oldMemberLevel">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiValidLevelList.oldMemberLevel" styleClass="control-label"/>
        <div class="controls">
            <form:input path="oldMemberLevel" id="oldMemberLevel"  maxlength="255"/>
            <form:errors path="oldMemberLevel" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiValidLevelList.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiValidLevelList.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jmiValidLevelList.id}">
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

<v:javascript formName="jmiValidLevelList" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jmiValidLevelListForm']).focus();
    });
</script>
