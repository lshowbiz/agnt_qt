<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="pdExchangeOrderDetailDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='pdExchangeOrderDetailDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="pdExchangeOrderDetailList.pdExchangeOrderDetail"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="pdExchangeOrderDetailDetail.heading"/></h2>
    <fmt:message key="pdExchangeOrderDetailDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="pdExchangeOrderDetail" method="post" action="pdExchangeOrderDetailform" cssClass="well form-horizontal"
           id="pdExchangeOrderDetailForm" onsubmit="return validatePdExchangeOrderDetail(this)">
<ul>
    <spring:bind path="pdExchangeOrderDetail.uniNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdExchangeOrderDetail.uniNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="uniNo" id="uniNo"/>
            <form:errors path="uniNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdExchangeOrderDetail.eoNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdExchangeOrderDetail.eoNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="eoNo" id="eoNo"  maxlength="17"/>
            <form:errors path="eoNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdExchangeOrderDetail.erpPrice">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdExchangeOrderDetail.erpPrice" styleClass="control-label"/>
        <div class="controls">
            <form:input path="erpPrice" id="erpPrice"  maxlength="255"/>
            <form:errors path="erpPrice" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdExchangeOrderDetail.erpProductNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdExchangeOrderDetail.erpProductNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="erpProductNo" id="erpProductNo"  maxlength="30"/>
            <form:errors path="erpProductNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdExchangeOrderDetail.price">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdExchangeOrderDetail.price" styleClass="control-label"/>
        <div class="controls">
            <form:input path="price" id="price"  maxlength="255"/>
            <form:errors path="price" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdExchangeOrderDetail.productNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdExchangeOrderDetail.productNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="productNo" id="productNo"  maxlength="20"/>
            <form:errors path="productNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdExchangeOrderDetail.pv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdExchangeOrderDetail.pv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="pv" id="pv"  maxlength="255"/>
            <form:errors path="pv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdExchangeOrderDetail.qty">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdExchangeOrderDetail.qty" styleClass="control-label"/>
        <div class="controls">
            <form:input path="qty" id="qty"  maxlength="255"/>
            <form:errors path="qty" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty pdExchangeOrderDetail.uniNo}">
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

<v:javascript formName="pdExchangeOrderDetail" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['pdExchangeOrderDetailForm']).focus();
    });
</script>
