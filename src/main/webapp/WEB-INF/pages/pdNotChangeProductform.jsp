<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="pdNotChangeProductDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='pdNotChangeProductDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="pdNotChangeProductList.pdNotChangeProduct"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="pdNotChangeProductDetail.heading"/></h2>
    <fmt:message key="pdNotChangeProductDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="pdNotChangeProduct" method="post" action="pdNotChangeProductform" cssClass="well form-horizontal"
           id="pdNotChangeProductForm" onsubmit="return validatePdNotChangeProduct(this)">
<ul>
    <spring:bind path="pdNotChangeProduct.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdNotChangeProduct.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdNotChangeProduct.companyCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdNotChangeProduct.companyCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="companyCode" id="companyCode"  maxlength="2"/>
            <form:errors path="companyCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdNotChangeProduct.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdNotChangeProduct.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdNotChangeProduct.createUserCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdNotChangeProduct.createUserCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createUserCode" id="createUserCode"  maxlength="20"/>
            <form:errors path="createUserCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdNotChangeProduct.isAvailable">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdNotChangeProduct.isAvailable" styleClass="control-label"/>
        <div class="controls">
            <form:input path="isAvailable" id="isAvailable"  maxlength="20"/>
            <form:errors path="isAvailable" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdNotChangeProduct.orderType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdNotChangeProduct.orderType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="orderType" id="orderType"  maxlength="3"/>
            <form:errors path="orderType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdNotChangeProduct.other">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdNotChangeProduct.other" styleClass="control-label"/>
        <div class="controls">
            <form:input path="other" id="other"  maxlength="200"/>
            <form:errors path="other" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdNotChangeProduct.productNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdNotChangeProduct.productNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="productNo" id="productNo"  maxlength="20"/>
            <form:errors path="productNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdNotChangeProduct.teamCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdNotChangeProduct.teamCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="teamCode" id="teamCode"  maxlength="10"/>
            <form:errors path="teamCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty pdNotChangeProduct.id}">
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

<v:javascript formName="pdNotChangeProduct" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['pdNotChangeProductForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
