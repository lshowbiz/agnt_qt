<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiBcoinPayconfigDetailDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='fiBcoinPayconfigDetailDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="fiBcoinPayconfigDetailList.fiBcoinPayconfigDetail"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="fiBcoinPayconfigDetailDetail.heading"/></h2>
    <fmt:message key="fiBcoinPayconfigDetailDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="fiBcoinPayconfigDetail" method="post" action="fiBcoinPayconfigDetailform" cssClass="well form-horizontal"
           id="fiBcoinPayconfigDetailForm" onsubmit="return validateFiBcoinPayconfigDetail(this)">
<ul>
    <spring:bind path="fiBcoinPayconfigDetail.detailId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBcoinPayconfigDetail.detailId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="detailId" id="detailId"/>
            <form:errors path="detailId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBcoinPayconfigDetail.configId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBcoinPayconfigDetail.configId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="configId" id="configId"  maxlength="255"/>
            <form:errors path="configId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="fiBcoinPayconfigDetail.productNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="fiBcoinPayconfigDetail.productNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="productNo" id="productNo"  maxlength="20"/>
            <form:errors path="productNo" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty fiBcoinPayconfigDetail.detailId}">
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

<v:javascript formName="fiBcoinPayconfigDetail" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['fiBcoinPayconfigDetailForm']).focus();
    });
</script>
