<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="reportHotProductDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='reportHotProductDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="reportHotProductList.reportHotProduct"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="reportHotProductDetail.heading"/></h2>
    <fmt:message key="reportHotProductDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="reportHotProduct" method="post" action="reportHotProductform" cssClass="well form-horizontal"
           id="reportHotProductForm" onsubmit="return validateReportHotProduct(this)">
<ul>
    <spring:bind path="reportHotProduct.reportId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="reportHotProduct.reportId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="reportId" id="reportId"/>
            <form:errors path="reportId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="reportHotProduct.cityId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="reportHotProduct.cityId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="cityId" id="cityId"  maxlength="30"/>
            <form:errors path="cityId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="reportHotProduct.productName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="reportHotProduct.productName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="productName" id="productName"  maxlength="200"/>
            <form:errors path="productName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="reportHotProduct.productNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="reportHotProduct.productNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="productNo" id="productNo"  maxlength="20"/>
            <form:errors path="productNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="reportHotProduct.productNum">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="reportHotProduct.productNum" styleClass="control-label"/>
        <div class="controls">
            <form:input path="productNum" id="productNum"  maxlength="255"/>
            <form:errors path="productNum" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="reportHotProduct.provinceId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="reportHotProduct.provinceId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="provinceId" id="provinceId"  maxlength="20"/>
            <form:errors path="provinceId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="reportHotProduct.timeId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="reportHotProduct.timeId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="timeId" id="timeId"  maxlength="255"/>
            <form:errors path="timeId" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty reportHotProduct.reportId}">
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

<v:javascript formName="reportHotProduct" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['reportHotProductForm']).focus();
    });
</script>
