<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jbdUserValidListDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jbdUserValidListList.jbdUserValidList"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jbdUserValidListDetail.heading"/></h2>
    <fmt:message key="jbdUserValidListDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jbdUserValidList" method="post" action="jbdUserValidListform" cssClass="well form-horizontal"
           id="jbdUserValidListForm" onsubmit="return validateJbdUserValidList(this)">
<ul>
    <spring:bind path="jbdUserValidList.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdUserValidList.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdUserValidList.WWeek">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdUserValidList.WWeek" styleClass="control-label"/>
        <div class="controls">
            <form:input path="WWeek" id="WWeek"  maxlength="255"/>
            <form:errors path="WWeek" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdUserValidList.newFreezeStatus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdUserValidList.newFreezeStatus" styleClass="control-label"/>
        <div class="controls">
            <form:input path="newFreezeStatus" id="newFreezeStatus"  maxlength="255"/>
            <form:errors path="newFreezeStatus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdUserValidList.oldFreezeStatus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdUserValidList.oldFreezeStatus" styleClass="control-label"/>
        <div class="controls">
            <form:input path="oldFreezeStatus" id="oldFreezeStatus"  maxlength="255"/>
            <form:errors path="oldFreezeStatus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdUserValidList.roleId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdUserValidList.roleId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="roleId" id="roleId"  maxlength="255"/>
            <form:errors path="roleId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdUserValidList.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdUserValidList.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jbdUserValidList.id}">
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

<v:javascript formName="jbdUserValidList" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jbdUserValidListForm']).focus();
    });
</script>
