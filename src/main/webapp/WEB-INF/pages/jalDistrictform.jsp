<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jalDistrictDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jalDistrictList.jalDistrict"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jalDistrictDetail.heading"/></h2>
    <fmt:message key="jalDistrictDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jalDistrict" method="post" action="jalDistrictform" cssClass="well form-horizontal"
           id="jalDistrictForm" onsubmit="return validateJalDistrict(this)">
<ul>
    <spring:bind path="jalDistrict.districtId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalDistrict.districtId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="districtId" id="districtId"/>
            <form:errors path="districtId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalDistrict.cityId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalDistrict.cityId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="cityId" id="cityId"  maxlength="255"/>
            <form:errors path="cityId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalDistrict.districtCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalDistrict.districtCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="districtCode" id="districtCode"  maxlength="30"/>
            <form:errors path="districtCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalDistrict.districtName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalDistrict.districtName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="districtName" id="districtName"  maxlength="200"/>
            <form:errors path="districtName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalDistrict.postalcode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalDistrict.postalcode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="postalcode" id="postalcode"  maxlength="10"/>
            <form:errors path="postalcode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jalDistrict.districtId}">
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

<v:javascript formName="jalDistrict" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jalDistrictForm']).focus();
    });
</script>
