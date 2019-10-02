<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="pdExchangeOrderBackDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='pdExchangeOrderBackDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="pdExchangeOrderBackList.pdExchangeOrderBack"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="pdExchangeOrderBackDetail.heading"/></h2>
    <fmt:message key="pdExchangeOrderBackDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="pdExchangeOrderBack" method="post" action="pdExchangeOrderBackform" cssClass="well form-horizontal"
           id="pdExchangeOrderBackForm" onsubmit="return validatePdExchangeOrderBack(this)">
<ul>
    <spring:bind path="pdExchangeOrderBack.uniNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdExchangeOrderBack.uniNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="uniNo" id="uniNo"/>
            <form:errors path="uniNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdExchangeOrderBack.eoNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdExchangeOrderBack.eoNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="eoNo" id="eoNo"  maxlength="17"/>
            <form:errors path="eoNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdExchangeOrderBack.erpPrice">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdExchangeOrderBack.erpPrice" styleClass="control-label"/>
        <div class="controls">
            <form:input path="erpPrice" id="erpPrice"  maxlength="255"/>
            <form:errors path="erpPrice" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdExchangeOrderBack.erpProductNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdExchangeOrderBack.erpProductNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="erpProductNo" id="erpProductNo"  maxlength="30"/>
            <form:errors path="erpProductNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdExchangeOrderBack.originalQty">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdExchangeOrderBack.originalQty" styleClass="control-label"/>
        <div class="controls">
            <form:input path="originalQty" id="originalQty"  maxlength="255"/>
            <form:errors path="originalQty" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdExchangeOrderBack.price">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdExchangeOrderBack.price" styleClass="control-label"/>
        <div class="controls">
            <form:input path="price" id="price"  maxlength="255"/>
            <form:errors path="price" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdExchangeOrderBack.productNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdExchangeOrderBack.productNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="productNo" id="productNo"  maxlength="20"/>
            <form:errors path="productNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdExchangeOrderBack.pv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdExchangeOrderBack.pv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="pv" id="pv"  maxlength="255"/>
            <form:errors path="pv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdExchangeOrderBack.qty">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdExchangeOrderBack.qty" styleClass="control-label"/>
        <div class="controls">
            <form:input path="qty" id="qty"  maxlength="255"/>
            <form:errors path="qty" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty pdExchangeOrderBack.uniNo}">
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

<v:javascript formName="pdExchangeOrderBack" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['pdExchangeOrderBackForm']).focus();
    });
</script>
