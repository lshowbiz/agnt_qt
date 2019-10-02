<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jbdUserCardListDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jbdUserCardListList.jbdUserCardList"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jbdUserCardListDetail.heading"/></h2>
    <fmt:message key="jbdUserCardListDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jbdUserCardList" method="post" action="jbdUserCardListform" cssClass="well form-horizontal"
           id="jbdUserCardListForm" onsubmit="return validateJbdUserCardList(this)">
<ul>
    <spring:bind path="jbdUserCardList.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdUserCardList.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdUserCardList.WWeek">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdUserCardList.WWeek" styleClass="control-label"/>
        <div class="controls">
            <form:input path="WWeek" id="WWeek"  maxlength="255"/>
            <form:errors path="WWeek" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdUserCardList.newCard">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdUserCardList.newCard" styleClass="control-label"/>
        <div class="controls">
            <form:input path="newCard" id="newCard"  maxlength="2"/>
            <form:errors path="newCard" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdUserCardList.oldCard">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdUserCardList.oldCard" styleClass="control-label"/>
        <div class="controls">
            <form:input path="oldCard" id="oldCard"  maxlength="2"/>
            <form:errors path="oldCard" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdUserCardList.updateType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdUserCardList.updateType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="updateType" id="updateType"  maxlength="1"/>
            <form:errors path="updateType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdUserCardList.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdUserCardList.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jbdUserCardList.id}">
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

<v:javascript formName="jbdUserCardList" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jbdUserCardListForm']).focus();
    });
</script>
