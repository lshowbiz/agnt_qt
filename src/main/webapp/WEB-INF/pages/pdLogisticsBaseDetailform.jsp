<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="pdLogisticsBaseDetailDetail.title"/></title>
    <meta name="heading" content="<fmt:message key='pdLogisticsBaseDetailDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="pdLogisticsBaseDetailList.pdLogisticsBaseDetail"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="pdLogisticsBaseDetailDetail.heading"/></h2>
    <fmt:message key="pdLogisticsBaseDetailDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="pdLogisticsBaseDetail" method="post" action="pdLogisticsBaseDetailform" cssClass="well form-horizontal"
           id="pdLogisticsBaseDetailForm" onsubmit="return validatePdLogisticsBaseDetail(this)">
<ul>
    <spring:bind path="pdLogisticsBaseDetail.detailId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBaseDetail.detailId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="detailId" id="detailId"/>
            <form:errors path="detailId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBaseDetail.createTime">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBaseDetail.createTime" styleClass="control-label"/>
        <div class="controls">
            <form:input path="createTime" id="createTime" size="11" title="date" datepicker="true"/>
            <form:errors path="createTime" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBaseDetail.erpGoodsBn">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBaseDetail.erpGoodsBn" styleClass="control-label"/>
        <div class="controls">
            <form:input path="erpGoodsBn" id="erpGoodsBn"  maxlength="100"/>
            <form:errors path="erpGoodsBn" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBaseDetail.goodsBn">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBaseDetail.goodsBn" styleClass="control-label"/>
        <div class="controls">
            <form:input path="goodsBn" id="goodsBn"  maxlength="50"/>
            <form:errors path="goodsBn" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBaseDetail.numId">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBaseDetail.numId" styleClass="control-label"/>
        <div class="controls">
            <form:input path="numId" id="numId"  maxlength="20"/>
            <form:errors path="numId" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBaseDetail.otherOne">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBaseDetail.otherOne" styleClass="control-label"/>
        <div class="controls">
            <form:input path="otherOne" id="otherOne"  maxlength="200"/>
            <form:errors path="otherOne" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBaseDetail.otherTwo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBaseDetail.otherTwo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="otherTwo" id="otherTwo"  maxlength="200"/>
            <form:errors path="otherTwo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="pdLogisticsBaseDetail.qty">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="pdLogisticsBaseDetail.qty" styleClass="control-label"/>
        <div class="controls">
            <form:input path="qty" id="qty"  maxlength="30"/>
            <form:errors path="qty" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty pdLogisticsBaseDetail.detailId}">
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

<v:javascript formName="pdLogisticsBaseDetail" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['pdLogisticsBaseDetailForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
