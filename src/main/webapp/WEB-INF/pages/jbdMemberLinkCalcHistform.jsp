<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="heading" content="<fmt:message key='jbdMemberLinkCalcHistDetail.heading'/>"/>
</head>

<c:set var="delObject" scope="request"><fmt:message key="jbdMemberLinkCalcHistList.jbdMemberLinkCalcHist"/></c:set>
<script type="text/javascript">var msgDelConfirm =
   "<fmt:message key="delete.confirm"><fmt:param value="${delObject}"/></fmt:message>";
</script>

<div class="span2">
    <h2><fmt:message key="jbdMemberLinkCalcHistDetail.heading"/></h2>
    <fmt:message key="jbdMemberLinkCalcHistDetail.message"/>
</div>

<div class="span7">
<form:errors path="*" cssClass="alert alert-error fade in" element="div"/>
<form:form commandName="jbdMemberLinkCalcHist" method="post" action="jbdMemberLinkCalcHistform" cssClass="well form-horizontal"
           id="jbdMemberLinkCalcHistForm" onsubmit="return validateJbdMemberLinkCalcHist(this)">
<ul>
    <spring:bind path="jbdMemberLinkCalcHist.id">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.id" styleClass="control-label"/>
        <div class="controls">
            <form:input path="id" id="id"/>
            <form:errors path="id" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.WMonth">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.WMonth" styleClass="control-label"/>
        <div class="controls">
            <form:input path="WMonth" id="WMonth"  maxlength="255"/>
            <form:errors path="WMonth" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.WWeek">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.WWeek" styleClass="control-label"/>
        <div class="controls">
            <form:input path="WWeek" id="WWeek"  maxlength="255"/>
            <form:errors path="WWeek" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.WYear">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.WYear" styleClass="control-label"/>
        <div class="controls">
            <form:input path="WYear" id="WYear"  maxlength="255"/>
            <form:errors path="WYear" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.active">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.active" styleClass="control-label"/>
        <div class="controls">
            <form:input path="active" id="active"  maxlength="1"/>
            <form:errors path="active" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.algebra">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.algebra" styleClass="control-label"/>
        <div class="controls">
            <form:input path="algebra" id="algebra"  maxlength="255"/>
            <form:errors path="algebra" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.areaConsumption">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.areaConsumption" styleClass="control-label"/>
        <div class="controls">
            <form:input path="areaConsumption" id="areaConsumption"  maxlength="255"/>
            <form:errors path="areaConsumption" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.bank">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.bank" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bank" id="bank"  maxlength="200"/>
            <form:errors path="bank" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.bankaddress">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.bankaddress" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bankaddress" id="bankaddress"  maxlength="200"/>
            <form:errors path="bankaddress" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.bankbook">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.bankbook" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bankbook" id="bankbook"  maxlength="200"/>
            <form:errors path="bankbook" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.bankcard">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.bankcard" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bankcard" id="bankcard"  maxlength="200"/>
            <form:errors path="bankcard" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.beforeFreezeStatus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.beforeFreezeStatus" styleClass="control-label"/>
        <div class="controls">
            <form:input path="beforeFreezeStatus" id="beforeFreezeStatus"  maxlength="255"/>
            <form:errors path="beforeFreezeStatus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.bounsMoney">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.bounsMoney" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bounsMoney" id="bounsMoney"  maxlength="255"/>
            <form:errors path="bounsMoney" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.bounsPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.bounsPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="bounsPv" id="bounsPv"  maxlength="255"/>
            <form:errors path="bounsPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.cardType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.cardType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="cardType" id="cardType"  maxlength="1"/>
            <form:errors path="cardType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.checkDate">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.checkDate" styleClass="control-label"/>
        <div class="controls">
            <form:input path="checkDate" id="checkDate" size="11" title="date" datepicker="true"/>
            <form:errors path="checkDate" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.city">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.city" styleClass="control-label"/>
        <div class="controls">
            <form:input path="city" id="city"  maxlength="500"/>
            <form:errors path="city" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.companyCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.companyCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="companyCode" id="companyCode"  maxlength="2"/>
            <form:errors path="companyCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.consumerAmount">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.consumerAmount" styleClass="control-label"/>
        <div class="controls">
            <form:input path="consumerAmount" id="consumerAmount"  maxlength="255"/>
            <form:errors path="consumerAmount" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.consumerStatus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.consumerStatus" styleClass="control-label"/>
        <div class="controls">
            <form:input path="consumerStatus" id="consumerStatus"  maxlength="255"/>
            <form:errors path="consumerStatus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.currentDev">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.currentDev" styleClass="control-label"/>
        <div class="controls">
            <form:input path="currentDev" id="currentDev"  maxlength="255"/>
            <form:errors path="currentDev" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.deductMoney">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.deductMoney" styleClass="control-label"/>
        <div class="controls">
            <form:input path="deductMoney" id="deductMoney"  maxlength="255"/>
            <form:errors path="deductMoney" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.deductTax">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.deductTax" styleClass="control-label"/>
        <div class="controls">
            <form:input path="deductTax" id="deductTax"  maxlength="255"/>
            <form:errors path="deductTax" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.deductedDev">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.deductedDev" styleClass="control-label"/>
        <div class="controls">
            <form:input path="deductedDev" id="deductedDev"  maxlength="255"/>
            <form:errors path="deductedDev" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.departmenKeepPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.departmenKeepPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="departmenKeepPv" id="departmenKeepPv"  maxlength="255"/>
            <form:errors path="departmenKeepPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.devFund">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.devFund" styleClass="control-label"/>
        <div class="controls">
            <form:input path="devFund" id="devFund"  maxlength="255"/>
            <form:errors path="devFund" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.doublePassStar">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.doublePassStar" styleClass="control-label"/>
        <div class="controls">
            <form:input path="doublePassStar" id="doublePassStar"  maxlength="255"/>
            <form:errors path="doublePassStar" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.exchangeRate">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.exchangeRate" styleClass="control-label"/>
        <div class="controls">
            <form:input path="exchangeRate" id="exchangeRate"  maxlength="255"/>
            <form:errors path="exchangeRate" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.exitDate">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.exitDate" styleClass="control-label"/>
        <div class="controls">
            <form:input path="exitDate" id="exitDate" size="11" title="date" datepicker="true"/>
            <form:errors path="exitDate" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.firstMonth">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.firstMonth" styleClass="control-label"/>
        <div class="controls">
            <form:input path="firstMonth" id="firstMonth"  maxlength="255"/>
            <form:errors path="firstMonth" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.franchiseMoney">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.franchiseMoney" styleClass="control-label"/>
        <div class="controls">
            <form:input path="franchiseMoney" id="franchiseMoney"  maxlength="255"/>
            <form:errors path="franchiseMoney" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.franchisePv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.franchisePv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="franchisePv" id="franchisePv"  maxlength="255"/>
            <form:errors path="franchisePv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.freezeStatus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.freezeStatus" styleClass="control-label"/>
        <div class="controls">
            <form:input path="freezeStatus" id="freezeStatus"  maxlength="255"/>
            <form:errors path="freezeStatus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.honorGrade">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.honorGrade" styleClass="control-label"/>
        <div class="controls">
            <form:input path="honorGrade" id="honorGrade"  maxlength="255"/>
            <form:errors path="honorGrade" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.honorPosition">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.honorPosition" styleClass="control-label"/>
        <div class="controls">
            <form:input path="honorPosition" id="honorPosition"  maxlength="255"/>
            <form:errors path="honorPosition" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.honorStar">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.honorStar" styleClass="control-label"/>
        <div class="controls">
            <form:input path="honorStar" id="honorStar"  maxlength="255"/>
            <form:errors path="honorStar" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.identityType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.identityType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="identityType" id="identityType"  maxlength="1"/>
            <form:errors path="identityType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.isstore">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.isstore" styleClass="control-label"/>
        <div class="controls">
            <form:input path="isstore" id="isstore"  maxlength="1"/>
            <form:errors path="isstore" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.keepPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.keepPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="keepPv" id="keepPv"  maxlength="255"/>
            <form:errors path="keepPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.keepUserCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.keepUserCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="keepUserCode" id="keepUserCode"  maxlength="20"/>
            <form:errors path="keepUserCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.lastKeepPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.lastKeepPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="lastKeepPv" id="lastKeepPv"  maxlength="255"/>
            <form:errors path="lastKeepPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.lastKeepUserCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.lastKeepUserCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="lastKeepUserCode" id="lastKeepUserCode"  maxlength="20"/>
            <form:errors path="lastKeepUserCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.leaderDev">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.leaderDev" styleClass="control-label"/>
        <div class="controls">
            <form:input path="leaderDev" id="leaderDev"  maxlength="255"/>
            <form:errors path="leaderDev" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.leaderDevPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.leaderDevPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="leaderDevPv" id="leaderDevPv"  maxlength="255"/>
            <form:errors path="leaderDevPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.linkNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.linkNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="linkNo" id="linkNo"  maxlength="20"/>
            <form:errors path="linkNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.memberType">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.memberType" styleClass="control-label"/>
        <div class="controls">
            <form:input path="memberType" id="memberType"  maxlength="1"/>
            <form:errors path="memberType" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.monthAreaTotalPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.monthAreaTotalPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="monthAreaTotalPv" id="monthAreaTotalPv"  maxlength="255"/>
            <form:errors path="monthAreaTotalPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.monthConsumerPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.monthConsumerPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="monthConsumerPv" id="monthConsumerPv"  maxlength="255"/>
            <form:errors path="monthConsumerPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.monthDoubleAreaPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.monthDoubleAreaPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="monthDoubleAreaPv" id="monthDoubleAreaPv"  maxlength="255"/>
            <form:errors path="monthDoubleAreaPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.monthDoubleMaxPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.monthDoubleMaxPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="monthDoubleMaxPv" id="monthDoubleMaxPv"  maxlength="255"/>
            <form:errors path="monthDoubleMaxPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.monthGroupPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.monthGroupPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="monthGroupPv" id="monthGroupPv"  maxlength="255"/>
            <form:errors path="monthGroupPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.monthMaxPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.monthMaxPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="monthMaxPv" id="monthMaxPv"  maxlength="255"/>
            <form:errors path="monthMaxPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.monthRecommendGroupPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.monthRecommendGroupPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="monthRecommendGroupPv" id="monthRecommendGroupPv"  maxlength="255"/>
            <form:errors path="monthRecommendGroupPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.name">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.name" styleClass="control-label"/>
        <div class="controls">
            <form:input path="name" id="name"  maxlength="200"/>
            <form:errors path="name" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.networkMoney">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.networkMoney" styleClass="control-label"/>
        <div class="controls">
            <form:input path="networkMoney" id="networkMoney"  maxlength="255"/>
            <form:errors path="networkMoney" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.passGrade">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.passGrade" styleClass="control-label"/>
        <div class="controls">
            <form:input path="passGrade" id="passGrade"  maxlength="255"/>
            <form:errors path="passGrade" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.passGradeGroupPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.passGradeGroupPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="passGradeGroupPv" id="passGradeGroupPv"  maxlength="255"/>
            <form:errors path="passGradeGroupPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.passStar">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.passStar" styleClass="control-label"/>
        <div class="controls">
            <form:input path="passStar" id="passStar"  maxlength="255"/>
            <form:errors path="passStar" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.passStarGroupPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.passStarGroupPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="passStarGroupPv" id="passStarGroupPv"  maxlength="255"/>
            <form:errors path="passStarGroupPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.passStatus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.passStatus" styleClass="control-label"/>
        <div class="controls">
            <form:input path="passStatus" id="passStatus"  maxlength="255"/>
            <form:errors path="passStatus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.petName">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.petName" styleClass="control-label"/>
        <div class="controls">
            <form:input path="petName" id="petName"  maxlength="200"/>
            <form:errors path="petName" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.province">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.province" styleClass="control-label"/>
        <div class="controls">
            <form:input path="province" id="province"  maxlength="500"/>
            <form:errors path="province" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.recommendBonusPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.recommendBonusPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="recommendBonusPv" id="recommendBonusPv"  maxlength="255"/>
            <form:errors path="recommendBonusPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.recommendNo">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.recommendNo" styleClass="control-label"/>
        <div class="controls">
            <form:input path="recommendNo" id="recommendNo"  maxlength="20"/>
            <form:errors path="recommendNo" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.salesStatus">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.salesStatus" styleClass="control-label"/>
        <div class="controls">
            <form:input path="salesStatus" id="salesStatus"  maxlength="255"/>
            <form:errors path="salesStatus" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.sendMoney">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.sendMoney" styleClass="control-label"/>
        <div class="controls">
            <form:input path="sendMoney" id="sendMoney"  maxlength="255"/>
            <form:errors path="sendMoney" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.startWeek">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.startWeek" styleClass="control-label"/>
        <div class="controls">
            <form:input path="startWeek" id="startWeek"  maxlength="255"/>
            <form:errors path="startWeek" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.status">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.status" styleClass="control-label"/>
        <div class="controls">
            <form:input path="status" id="status"  maxlength="255"/>
            <form:errors path="status" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.storeExpandPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.storeExpandPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="storeExpandPv" id="storeExpandPv"  maxlength="255"/>
            <form:errors path="storeExpandPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.storeRecommendPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.storeRecommendPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="storeRecommendPv" id="storeRecommendPv"  maxlength="255"/>
            <form:errors path="storeRecommendPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.storeServePv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.storeServePv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="storeServePv" id="storeServePv"  maxlength="255"/>
            <form:errors path="storeServePv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.successLeaderPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.successLeaderPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="successLeaderPv" id="successLeaderPv"  maxlength="255"/>
            <form:errors path="successLeaderPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.successSalesPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.successSalesPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="successSalesPv" id="successSalesPv"  maxlength="255"/>
            <form:errors path="successSalesPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.successSalesRate">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.successSalesRate" styleClass="control-label"/>
        <div class="controls">
            <form:input path="successSalesRate" id="successSalesRate"  maxlength="255"/>
            <form:errors path="successSalesRate" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.totalDev">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.totalDev" styleClass="control-label"/>
        <div class="controls">
            <form:input path="totalDev" id="totalDev"  maxlength="255"/>
            <form:errors path="totalDev" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.userCode">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.userCode" styleClass="control-label"/>
        <div class="controls">
            <form:input path="userCode" id="userCode"  maxlength="20"/>
            <form:errors path="userCode" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.validWeek">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.validWeek" styleClass="control-label"/>
        <div class="controls">
            <form:input path="validWeek" id="validWeek"  maxlength="255"/>
            <form:errors path="validWeek" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.ventureFund">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.ventureFund" styleClass="control-label"/>
        <div class="controls">
            <form:input path="ventureFund" id="ventureFund"  maxlength="255"/>
            <form:errors path="ventureFund" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.ventureLeaderPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.ventureLeaderPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="ventureLeaderPv" id="ventureLeaderPv"  maxlength="255"/>
            <form:errors path="ventureLeaderPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.ventureSalesPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.ventureSalesPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="ventureSalesPv" id="ventureSalesPv"  maxlength="255"/>
            <form:errors path="ventureSalesPv" cssClass="help-inline"/>
        </div>
    </div>
    <spring:bind path="jbdMemberLinkCalcHist.weekGroupPv">
    <div class="control-group${(not empty status.errorMessage) ? ' error' : ''}">
    </spring:bind>
        <appfuse:label key="jbdMemberLinkCalcHist.weekGroupPv" styleClass="control-label"/>
        <div class="controls">
            <form:input path="weekGroupPv" id="weekGroupPv"  maxlength="255"/>
            <form:errors path="weekGroupPv" cssClass="help-inline"/>
        </div>
    </div>

    <div class="form-actions">
        <button type="submit" class="btn btn-primary" name="save" onclick="bCancel=false">
            <i class="icon-ok icon-white"></i> <fmt:message key="button.save"/>
        </button>
        <c:if test="${not empty jbdMemberLinkCalcHist.id}">
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

<v:javascript formName="jbdMemberLinkCalcHist" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script type="text/javascript" src="<c:url value='/scripts/validator.jsp'/>"></script>

<link rel="stylesheet" type="text/css" media="all" href="<c:url value='/scripts/datepicker/css/datepicker.css'/>" />
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/bootstrap-datepicker.js'/>"></script>
<c:if test="${pageContext.request.locale.language != 'en'}">
<script type="text/javascript" src="<c:url value='/scripts/datepicker/js/locales/bootstrap-datepicker.${pageContext.request.locale.language}.js'/>"></script>
</c:if>
<script type="text/javascript">
    $(document).ready(function() {
        $("input[type='text']:visible:enabled:first", document.forms['jbdMemberLinkCalcHistForm']).focus();
        $('.input-append.date').datepicker({format: "<fmt:message key='calendar.format'/>", weekStart: "<fmt:message key='calendar.weekstart'/>", language: '${pageContext.request.locale.language}'});
    });
</script>
