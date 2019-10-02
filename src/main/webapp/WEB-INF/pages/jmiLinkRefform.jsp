<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jmiLinkRefDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jmiLinkRefList.jmiLinkRef"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jmiLinkRefDetail.heading"/></h2>
    <fmt:message key="jmiLinkRefDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jmiLinkRef" method="post" action="jmiLinkRefform" cssClass="well form-horizontal"
           id="jmiLinkRefForm" onsubmit="return validateJmiLinkRef(this)">
<ul>
    <spring:bind path="jmiLinkRef.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiLinkRef.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiLinkRef.departmentPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiLinkRef.departmentPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="departmentPv" id="departmentPv"  maxlength="255"/>
            <form:errors path="departmentPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiLinkRef.layerId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiLinkRef.layerId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="layerId" id="layerId"  maxlength="255"/>
            <form:errors path="layerId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiLinkRef.linkNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiLinkRef.linkNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="linkNo" id="linkNo"  maxlength="20"/>
            <form:errors path="linkNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiLinkRef.num">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiLinkRef.num" styleClass="control-label"/>
        <div class="controls">
            <form:input path="num" id="num"  maxlength="255"/>
            <form:errors path="num" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiLinkRef.treeIndex">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiLinkRef.treeIndex" styleClass="control-label"/>
        <div class="controls">
            <form:input path="treeIndex" id="treeIndex"  maxlength="4000"/>
            <form:errors path="treeIndex" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiLinkRef.treeNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiLinkRef.treeNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="treeNo" id="treeNo"  maxlength="255"/>
            <form:errors path="treeNo" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jmiLinkRef.userCode}">
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

<v:javascript formName="jmiLinkRef" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jmiLinkRefForm']).focus();
    });
</script>
