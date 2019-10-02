<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jalStateProvinceDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jalStateProvinceList.jalStateProvince"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jalStateProvinceDetail.heading"/></h2>
    <fmt:message key="jalStateProvinceDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jalStateProvince" method="post" action="jalStateProvinceform" cssClass="well form-horizontal"
           id="jalStateProvinceForm" onsubmit="return validateJalStateProvince(this)">
<ul>
    <spring:bind path="jalStateProvince.stateProvinceId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalStateProvince.stateProvinceId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="stateProvinceId" id="stateProvinceId"/>
            <form:errors path="stateProvinceId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalStateProvince.countryId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalStateProvince.countryId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="countryId" id="countryId"  maxlength="255"/>
            <form:errors path="countryId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalStateProvince.stateProvinceCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalStateProvince.stateProvinceCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="stateProvinceCode" id="stateProvinceCode"  maxlength="30"/>
            <form:errors path="stateProvinceCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalStateProvince.stateProvinceName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalStateProvince.stateProvinceName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="stateProvinceName" id="stateProvinceName"  maxlength="150"/>
            <form:errors path="stateProvinceName" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jalStateProvince.stateProvinceId}">
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

<v:javascript formName="jalStateProvince" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jalStateProvinceForm']).focus();
    });
</script>
