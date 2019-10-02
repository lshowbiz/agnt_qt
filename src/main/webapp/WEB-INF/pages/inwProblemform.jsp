<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <!-- 创新共赢帮助-提问题的页面 -->
    <meta name="heading" content="<fmt:message key='inwProblemDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="inwProblemList.inwProblem"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="inwProblemDetail.heading"/></h2>
    <fmt:message key="inwProblemDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="inwProblem" method="post" action="inwProblemform" cssClass="well form-horizontal"
           id="inwProblemForm" onsubmit="return validateInwProblem(this)">
<ul>
    <spring:bind path="inwProblem.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwProblem.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwProblem.answer">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwProblem.answer" styleClass="control-label"/>
        <div class="controls">
            <form:input path="answer" id="answer"  maxlength="900"/>
            <form:errors path="answer" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwProblem.ask">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwProblem.ask" styleClass="control-label"/>
        <div class="controls">
            <form:input path="ask" id="ask"  maxlength="400"/>
            <form:errors path="ask" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwProblem.other">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwProblem.other" styleClass="control-label"/>
        <div class="controls">
            <form:input path="other" id="other"  maxlength="200"/>
            <form:errors path="other" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="inwProblem.species">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="inwProblem.species" styleClass="control-label"/>
        <div class="controls">
            <form:input path="species" id="species"  maxlength="2"/>
            <form:errors path="species" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty inwProblem.id}">
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

<v:javascript formName="inwProblem" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['inwProblemForm']).focus();
    });
</script>
