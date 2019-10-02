<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jalCityDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jalCityList.jalCity"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jalCityDetail.heading"/></h2>
    <fmt:message key="jalCityDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jalCity" method="post" action="jalCityform" cssClass="well form-horizontal"
           id="jalCityForm" onsubmit="return validateJalCity(this)">
<ul>
    <spring:bind path="jalCity.cityId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalCity.cityId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="cityId" id="cityId"/>
            <form:errors path="cityId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalCity.cityCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalCity.cityCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="cityCode" id="cityCode"  maxlength="30"/>
            <form:errors path="cityCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalCity.cityName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalCity.cityName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="cityName" id="cityName"  maxlength="200"/>
            <form:errors path="cityName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalCity.stateProvinceId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalCity.stateProvinceId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="stateProvinceId" id="stateProvinceId"  maxlength="255"/>
            <form:errors path="stateProvinceId" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jalCity.cityId}">
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

<v:javascript formName="jalCity" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jalCityForm']).focus();
    });
</script>
