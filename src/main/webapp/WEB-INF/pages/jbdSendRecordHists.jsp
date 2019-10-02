<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JbdSendRecordHistMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jbdSendRecordHistList.heading"/></h2>

    <form method="get" action="${ctx}/jbdSendRecordHists" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jbdSendRecordHistList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jbdSendRecordHistform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jbdSendRecordHistList" class="table table-condensed table-striped table-hover" requestURI="" id="jbdSendRecordHistList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="jbdSendRecordHistform" media="html"
        paramId="id" paramProperty="id" titleKey="jbdSendRecordHist.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="jbdSendRecordHist.id"/>
    <display:column property="WMonth" sortable="true" titleKey="jbdSendRecordHist.WMonth"/>
    <display:column property="WWeek" sortable="true" titleKey="jbdSendRecordHist.WWeek"/>
    <display:column property="WYear" sortable="true" titleKey="jbdSendRecordHist.WYear"/>
    <display:column property="active" sortable="true" titleKey="jbdSendRecordHist.active"/>
    <display:column property="bank" sortable="true" titleKey="jbdSendRecordHist.bank"/>
    <display:column property="bankaddress" sortable="true" titleKey="jbdSendRecordHist.bankaddress"/>
    <display:column property="bankbook" sortable="true" titleKey="jbdSendRecordHist.bankbook"/>
    <display:column property="bankcard" sortable="true" titleKey="jbdSendRecordHist.bankcard"/>
    <display:column property="beforeFreezeStatus" sortable="true" titleKey="jbdSendRecordHist.beforeFreezeStatus"/>
    <display:column property="cardType" sortable="true" titleKey="jbdSendRecordHist.cardType"/>
    <display:column property="companyCode" sortable="true" titleKey="jbdSendRecordHist.companyCode"/>
    <display:column property="currentDev" sortable="true" titleKey="jbdSendRecordHist.currentDev"/>
    <display:column property="deductedDev" sortable="true" titleKey="jbdSendRecordHist.deductedDev"/>
    <display:column sortProperty="exitDate" sortable="true" titleKey="jbdSendRecordHist.exitDate">
         <fmt:formatDate value="${jbdSendRecordHistList.exitDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="freezeStatus" sortable="true" titleKey="jbdSendRecordHist.freezeStatus"/>
    <display:column property="leaderDev" sortable="true" titleKey="jbdSendRecordHist.leaderDev"/>
    <display:column property="leaderDevPv" sortable="true" titleKey="jbdSendRecordHist.leaderDevPv"/>
    <display:column property="linkNo" sortable="true" titleKey="jbdSendRecordHist.linkNo"/>
    <display:column property="memberType" sortable="true" titleKey="jbdSendRecordHist.memberType"/>
    <display:column property="name" sortable="true" titleKey="jbdSendRecordHist.name"/>
    <display:column property="operaterCode" sortable="true" titleKey="jbdSendRecordHist.operaterCode"/>
    <display:column sortProperty="operaterTime" sortable="true" titleKey="jbdSendRecordHist.operaterTime">
         <fmt:formatDate value="${jbdSendRecordHistList.operaterTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="petName" sortable="true" titleKey="jbdSendRecordHist.petName"/>
    <display:column property="recommendNo" sortable="true" titleKey="jbdSendRecordHist.recommendNo"/>
    <display:column property="registerStatus" sortable="true" titleKey="jbdSendRecordHist.registerStatus"/>
    <display:column property="reissueStatus" sortable="true" titleKey="jbdSendRecordHist.reissueStatus"/>
    <display:column property="remittanceBNum" sortable="true" titleKey="jbdSendRecordHist.remittanceBNum"/>
    <display:column property="remittanceMoney" sortable="true" titleKey="jbdSendRecordHist.remittanceMoney"/>
    <display:column sortProperty="sendDate" sortable="true" titleKey="jbdSendRecordHist.sendDate">
         <fmt:formatDate value="${jbdSendRecordHistList.sendDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column sortProperty="sendDateDev" sortable="true" titleKey="jbdSendRecordHist.sendDateDev">
         <fmt:formatDate value="${jbdSendRecordHistList.sendDateDev}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="sendLateCause" sortable="true" titleKey="jbdSendRecordHist.sendLateCause"/>
    <display:column property="sendLateRemark" sortable="true" titleKey="jbdSendRecordHist.sendLateRemark"/>
    <display:column property="sendMoney" sortable="true" titleKey="jbdSendRecordHist.sendMoney"/>
    <display:column property="sendRemark" sortable="true" titleKey="jbdSendRecordHist.sendRemark"/>
    <display:column property="sendStatusDev" sortable="true" titleKey="jbdSendRecordHist.sendStatusDev"/>
    <display:column property="sendTrace" sortable="true" titleKey="jbdSendRecordHist.sendTrace"/>
    <display:column property="sendUserDev" sortable="true" titleKey="jbdSendRecordHist.sendUserDev"/>
    <display:column property="startWeek" sortable="true" titleKey="jbdSendRecordHist.startWeek"/>
    <display:column property="status" sortable="true" titleKey="jbdSendRecordHist.status"/>
    <display:column sortProperty="supplyTime" sortable="true" titleKey="jbdSendRecordHist.supplyTime">
         <fmt:formatDate value="${jbdSendRecordHistList.supplyTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="totalDev" sortable="true" titleKey="jbdSendRecordHist.totalDev"/>
    <display:column property="userCode" sortable="true" titleKey="jbdSendRecordHist.userCode"/>
    <display:column property="validWeek" sortable="true" titleKey="jbdSendRecordHist.validWeek"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jbdSendRecordHistList.jbdSendRecordHist"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jbdSendRecordHistList.jbdSendRecordHists"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jbdSendRecordHistList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jbdSendRecordHistList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jbdSendRecordHistList.title"/>.pdf</display:setProperty>
</display:table>
