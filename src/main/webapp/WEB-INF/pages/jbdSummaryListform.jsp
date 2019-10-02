<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jbdSummaryListDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jbdSummaryListList.jbdSummaryList"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jbdSummaryListDetail.heading"/></h2>
    <fmt:message key="jbdSummaryListDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jbdSummaryList" method="post" action="jbdSummaryListform" cssClass="well form-horizontal"
           id="jbdSummaryListForm" onsubmit="return validateJbdSummaryList(this)">
<ul>
    <spring:bind path="jbdSummaryList.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSummaryList.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSummaryList.WWeek">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSummaryList.WWeek" styleClass="control-label"/>
        <div class="controls">
            <form:input path="WWeek" id="WWeek"  maxlength="255"/>
            <form:errors path="WWeek" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSummaryList.cardType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSummaryList.cardType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="cardType" id="cardType"  maxlength="20"/>
            <form:errors path="cardType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSummaryList.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSummaryList.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSummaryList.inType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSummaryList.inType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="inType" id="inType"  maxlength="255"/>
            <form:errors path="inType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSummaryList.newCheckDate">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSummaryList.newCheckDate" styleClass="control-label"/>
        <div class="controls">
            <form:input path="newCheckDate" id="newCheckDate" size="11" title="date" datepicker="true"/>
            <form:errors path="newCheckDate" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSummaryList.newCompanyCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSummaryList.newCompanyCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="newCompanyCode" id="newCompanyCode"  maxlength="2"/>
            <form:errors path="newCompanyCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSummaryList.newLinkNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSummaryList.newLinkNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="newLinkNo" id="newLinkNo"  maxlength="20"/>
            <form:errors path="newLinkNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSummaryList.newRecommendNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSummaryList.newRecommendNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="newRecommendNo" id="newRecommendNo"  maxlength="20"/>
            <form:errors path="newRecommendNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSummaryList.oldCheckDate">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSummaryList.oldCheckDate" styleClass="control-label"/>
        <div class="controls">
            <form:input path="oldCheckDate" id="oldCheckDate" size="11" title="date" datepicker="true"/>
            <form:errors path="oldCheckDate" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSummaryList.oldLinkNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSummaryList.oldLinkNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="oldLinkNo" id="oldLinkNo"  maxlength="20"/>
            <form:errors path="oldLinkNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSummaryList.oldRecommendNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSummaryList.oldRecommendNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="oldRecommendNo" id="oldRecommendNo"  maxlength="20"/>
            <form:errors path="oldRecommendNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSummaryList.orderType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSummaryList.orderType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="orderType" id="orderType"  maxlength="2"/>
            <form:errors path="orderType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSummaryList.pvAmt">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSummaryList.pvAmt" styleClass="control-label"/>
        <div class="controls">
            <form:input path="pvAmt" id="pvAmt"  maxlength="255"/>
            <form:errors path="pvAmt" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSummaryList.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSummaryList.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdSummaryList.userCreateTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdSummaryList.userCreateTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCreateTime" id="userCreateTime" size="11" title="date" datepicker="true"/>
            <form:errors path="userCreateTime" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jbdSummaryList.id}">
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

<v:javascript formName="jbdSummaryList" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jbdSummaryListForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
