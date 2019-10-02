<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jalLibraryPlateDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jalLibraryPlateList.jalLibraryPlate"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jalLibraryPlateDetail.heading"/></h2>
    <fmt:message key="jalLibraryPlateDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jalLibraryPlate" method="post" action="jalLibraryPlateform" cssClass="well form-horizontal"
           id="jalLibraryPlateForm" onsubmit="return validateJalLibraryPlate(this)">
<ul>
    <spring:bind path="jalLibraryPlate.plateId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryPlate.plateId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="plateId" id="plateId"/>
            <form:errors path="plateId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalLibraryPlate.plateName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryPlate.plateName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="plateName" id="plateName"  maxlength="300"/>
            <form:errors path="plateName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jalLibraryPlate.plateRemark">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jalLibraryPlate.plateRemark" styleClass="control-label"/>
        <div class="controls">
            <form:input path="plateRemark" id="plateRemark"  maxlength="300"/>
            <form:errors path="plateRemark" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jalLibraryPlate.plateId}">
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

<v:javascript formName="jalLibraryPlate" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jalLibraryPlateForm']).focus();
    });
</script>
