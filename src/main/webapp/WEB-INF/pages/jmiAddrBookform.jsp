<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jmiAddrBookDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jmiAddrBookList.jmiAddrBook"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jmiAddrBookDetail.heading"/></h2>
    <fmt:message key="jmiAddrBookDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jmiAddrBook" method="post" action="jmiAddrBookform" cssClass="well form-horizontal"
           id="jmiAddrBookForm" onsubmit="return validateJmiAddrBook(this)">
<form:hidden path="fabId"/>
    <spring:bind path="jmiAddrBook.address">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiAddrBook.address" styleClass="control-label"/>
        <div class="controls">
            <form:input path="address" id="address"  maxlength="500"/>
            <form:errors path="address" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiAddrBook.building">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiAddrBook.building" styleClass="control-label"/>
        <div class="controls">
            <form:input path="building" id="building"  maxlength="500"/>
            <form:errors path="building" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiAddrBook.city">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiAddrBook.city" styleClass="control-label"/>
        <div class="controls">
            <form:input path="city" id="city"  maxlength="20"/>
            <form:errors path="city" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiAddrBook.district">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiAddrBook.district" styleClass="control-label"/>
        <div class="controls">
            <form:input path="district" id="district"  maxlength="20"/>
            <form:errors path="district" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiAddrBook.email">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiAddrBook.email" styleClass="control-label"/>
        <div class="controls">
            <form:input path="email" id="email"  maxlength="30"/>
            <form:errors path="email" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiAddrBook.firstName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiAddrBook.firstName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="firstName" id="firstName"  maxlength="100"/>
            <form:errors path="firstName" cssClass="help-inline"/>
        </div>
    </div>
    <!-- todo: change this to read the identifier field from the other pojo -->
    <form:select path="jmiMember" items="jmiMemberList" itemLabel="label" itemValue="value"/>
    <spring:bind path="jmiAddrBook.lastName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiAddrBook.lastName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="lastName" id="lastName"  maxlength="100"/>
            <form:errors path="lastName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiAddrBook.mobiletele">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiAddrBook.mobiletele" styleClass="control-label"/>
        <div class="controls">
            <form:input path="mobiletele" id="mobiletele"  maxlength="20"/>
            <form:errors path="mobiletele" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiAddrBook.phone">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiAddrBook.phone" styleClass="control-label"/>
        <div class="controls">
            <form:input path="phone" id="phone"  maxlength="30"/>
            <form:errors path="phone" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiAddrBook.postalcode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiAddrBook.postalcode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="postalcode" id="postalcode"  maxlength="10"/>
            <form:errors path="postalcode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiAddrBook.province">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiAddrBook.province" styleClass="control-label"/>
        <div class="controls">
            <form:input path="province" id="province"  maxlength="20"/>
            <form:errors path="province" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jmiAddrBook.town">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jmiAddrBook.town" styleClass="control-label"/>
        <div class="controls">
            <form:input path="town" id="town"  maxlength="20"/>
            <form:errors path="town" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jmiAddrBook.fabId}">
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

<v:javascript formName="jmiAddrBook" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jmiAddrBookForm']).focus();
    });
</script>
