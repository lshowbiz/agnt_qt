<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiBillAccountRelationDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='fiBillAccountRelationDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="fiBillAccountRelationList.fiBillAccountRelation"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="fiBillAccountRelationDetail.heading"/></h2>
    <fmt:message key="fiBillAccountRelationDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="fiBillAccountRelation" method="post" action="fiBillAccountRelationform" cssClass="well form-horizontal"
           id="fiBillAccountRelationForm" onsubmit="return validateFiBillAccountRelation(this)">
<ul>
    <spring:bind path="fiBillAccountRelation.relationId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBillAccountRelation.relationId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="relationId" id="relationId"/>
            <form:errors path="relationId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBillAccountRelation.billAccountCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBillAccountRelation.billAccountCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="billAccountCode" id="billAccountCode"  maxlength="50"/>
            <form:errors path="billAccountCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBillAccountRelation.city">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBillAccountRelation.city" styleClass="control-label"/>
        <div class="controls">
            <form:input path="city" id="city"  maxlength="20"/>
            <form:errors path="city" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBillAccountRelation.district">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBillAccountRelation.district" styleClass="control-label"/>
        <div class="controls">
            <form:input path="district" id="district"  maxlength="20"/>
            <form:errors path="district" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBillAccountRelation.province">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBillAccountRelation.province" styleClass="control-label"/>
        <div class="controls">
            <form:input path="province" id="province"  maxlength="20"/>
            <form:errors path="province" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty fiBillAccountRelation.relationId}">
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

<v:javascript formName="fiBillAccountRelation" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['fiBillAccountRelationForm']).focus();
    });
</script>
