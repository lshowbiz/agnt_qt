<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jmiRecommendRefDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jmiRecommendRefList.jmiRecommendRef"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jmiRecommendRefDetail.heading"/></h2>
    <fmt:message key="jmiRecommendRefDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jmiRecommendRef" method="post" action="jmiRecommendRefform" cssClass="well form-horizontal"
           id="jmiRecommendRefForm" onsubmit="return validateJmiRecommendRef(this)">
<ul>
    <spring:bind path="jmiRecommendRef.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiRecommendRef.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiRecommendRef.layerId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiRecommendRef.layerId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="layerId" id="layerId"  maxlength="255"/>
            <form:errors path="layerId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiRecommendRef.recommendNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiRecommendRef.recommendNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="recommendNo" id="recommendNo"  maxlength="20"/>
            <form:errors path="recommendNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiRecommendRef.treeIndex">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiRecommendRef.treeIndex" styleClass="control-label"/>
        <div class="controls">
            <form:input path="treeIndex" id="treeIndex"  maxlength="4000"/>
            <form:errors path="treeIndex" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiRecommendRef.treeNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiRecommendRef.treeNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="treeNo" id="treeNo"  maxlength="255"/>
            <form:errors path="treeNo" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jmiRecommendRef.userCode}">
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

<v:javascript formName="jmiRecommendRef" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jmiRecommendRefForm']).focus();
    });
</script>
