<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <!-- 创新共赢添加需求-该页面没有用到 -->
    <meta name="heading" content="<fmt:message key='inwDemandDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="inwDemandList.inwDemand"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="inwDemandDetail.heading"/></h2>
    <fmt:message key="inwDemandDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="inwDemand" method="post" action="inwDemandform" cssClass="well form-horizontal"
           id="inwDemandForm" onsubmit="return validateInwDemand(this)">
<ul>
    <spring:bind path="inwDemand.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwDemand.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwDemand.demandImage">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwDemand.demandImage" styleClass="control-label"/>
        <div class="controls">
            <form:input path="demandImage" id="demandImage"  maxlength="255"/>
            <form:errors path="demandImage" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwDemand.detailExplanation">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwDemand.detailExplanation" styleClass="control-label"/>
        <div class="controls">
            <form:input path="detailExplanation" id="detailExplanation"  maxlength="255"/>
            <form:errors path="detailExplanation" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwDemand.name">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwDemand.name" styleClass="control-label"/>
        <div class="controls">
            <form:input path="name" id="name"  maxlength="100"/>
            <form:errors path="name" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwDemand.other">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwDemand.other" styleClass="control-label"/>
        <div class="controls">
            <form:input path="other" id="other"  maxlength="200"/>
            <form:errors path="other" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwDemand.showOrHide">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwDemand.showOrHide" styleClass="control-label"/>
        <div class="controls">
            <form:input path="showOrHide" id="showOrHide"  maxlength="2"/>
            <form:errors path="showOrHide" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwDemand.summary">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwDemand.summary" styleClass="control-label"/>
        <div class="controls">
            <form:input path="summary" id="summary"  maxlength="300"/>
            <form:errors path="summary" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwDemand.verify">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwDemand.verify" styleClass="control-label"/>
        <div class="controls">
            <form:input path="verify" id="verify"  maxlength="200"/>
            <form:errors path="verify" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty inwDemand.id}">
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

<v:javascript formName="inwDemand" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['inwDemandForm']).focus();
    });
</script>
