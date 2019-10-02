<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jpoMemberOrderListDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jpoMemberOrderListList.jpoMemberOrderList"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jpoMemberOrderListDetail.heading"/></h2>
    <fmt:message key="jpoMemberOrderListDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jpoMemberOrderList" method="post" action="jpoMemberOrderListform" cssClass="well form-horizontal"
           id="jpoMemberOrderListForm" onsubmit="return validateJpoMemberOrderList(this)">
<ul>
    <spring:bind path="jpoMemberOrderList.molId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoMemberOrderList.molId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="molId" id="molId"/>
            <form:errors path="molId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoMemberOrderList.coin">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoMemberOrderList.coin" styleClass="control-label"/>
        <div class="controls">
            <form:input path="coin" id="coin"  maxlength="255"/>
            <form:errors path="coin" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoMemberOrderList.moId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoMemberOrderList.moId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="moId" id="moId"  maxlength="255"/>
            <form:errors path="moId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoMemberOrderList.price">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoMemberOrderList.price" styleClass="control-label"/>
        <div class="controls">
            <form:input path="price" id="price"  maxlength="255"/>
            <form:errors path="price" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoMemberOrderList.productId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoMemberOrderList.productId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="productId" id="productId"  maxlength="255"/>
            <form:errors path="productId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoMemberOrderList.productType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoMemberOrderList.productType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="productType" id="productType"  maxlength="20"/>
            <form:errors path="productType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoMemberOrderList.pv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoMemberOrderList.pv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="pv" id="pv"  maxlength="255"/>
            <form:errors path="pv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoMemberOrderList.qty">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoMemberOrderList.qty" styleClass="control-label"/>
        <div class="controls">
            <form:input path="qty" id="qty"  maxlength="255"/>
            <form:errors path="qty" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoMemberOrderList.volume">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoMemberOrderList.volume" styleClass="control-label"/>
        <div class="controls">
            <form:input path="volume" id="volume"  maxlength="255"/>
            <form:errors path="volume" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jpoMemberOrderList.weight">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jpoMemberOrderList.weight" styleClass="control-label"/>
        <div class="controls">
            <form:input path="weight" id="weight"  maxlength="255"/>
            <form:errors path="weight" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jpoMemberOrderList.molId}">
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

<v:javascript formName="jpoMemberOrderList" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jpoMemberOrderListForm']).focus();
    });
</script>
