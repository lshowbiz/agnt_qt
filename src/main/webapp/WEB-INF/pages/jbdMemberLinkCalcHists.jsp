<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JbdMemberLinkCalcHistMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jbdMemberLinkCalcHistList.heading"/></h2>

    <form method="get" action="${ctx}/jbdMemberLinkCalcHists" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jbdMemberLinkCalcHistList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jbdMemberLinkCalcHistform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jbdMemberLinkCalcHistList" class="table table-condensed table-striped table-hover" requestURI="" id="jbdMemberLinkCalcHistList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="jbdMemberLinkCalcHistform" media="html"
        paramId="id" paramProperty="id" titleKey="jbdMemberLinkCalcHist.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="jbdMemberLinkCalcHist.id"/>
    <display:column property="WMonth" sortable="true" titleKey="jbdMemberLinkCalcHist.WMonth"/>
    <display:column property="WWeek" sortable="true" titleKey="jbdMemberLinkCalcHist.WWeek"/>
    <display:column property="WYear" sortable="true" titleKey="jbdMemberLinkCalcHist.WYear"/>
    <display:column property="active" sortable="true" titleKey="jbdMemberLinkCalcHist.active"/>
    <display:column property="algebra" sortable="true" titleKey="jbdMemberLinkCalcHist.algebra"/>
    <display:column property="areaConsumption" sortable="true" titleKey="jbdMemberLinkCalcHist.areaConsumption"/>
    <display:column property="bank" sortable="true" titleKey="jbdMemberLinkCalcHist.bank"/>
    <display:column property="bankaddress" sortable="true" titleKey="jbdMemberLinkCalcHist.bankaddress"/>
    <display:column property="bankbook" sortable="true" titleKey="jbdMemberLinkCalcHist.bankbook"/>
    <display:column property="bankcard" sortable="true" titleKey="jbdMemberLinkCalcHist.bankcard"/>
    <display:column property="beforeFreezeStatus" sortable="true" titleKey="jbdMemberLinkCalcHist.beforeFreezeStatus"/>
    <display:column property="bounsMoney" sortable="true" titleKey="jbdMemberLinkCalcHist.bounsMoney"/>
    <display:column property="bounsPv" sortable="true" titleKey="jbdMemberLinkCalcHist.bounsPv"/>
    <display:column property="cardType" sortable="true" titleKey="jbdMemberLinkCalcHist.cardType"/>
    <display:column sortProperty="checkDate" sortable="true" titleKey="jbdMemberLinkCalcHist.checkDate">
         <fmt:formatDate value="${jbdMemberLinkCalcHistList.checkDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="city" sortable="true" titleKey="jbdMemberLinkCalcHist.city"/>
    <display:column property="companyCode" sortable="true" titleKey="jbdMemberLinkCalcHist.companyCode"/>
    <display:column property="consumerAmount" sortable="true" titleKey="jbdMemberLinkCalcHist.consumerAmount"/>
    <display:column property="consumerStatus" sortable="true" titleKey="jbdMemberLinkCalcHist.consumerStatus"/>
    <display:column property="currentDev" sortable="true" titleKey="jbdMemberLinkCalcHist.currentDev"/>
    <display:column property="deductMoney" sortable="true" titleKey="jbdMemberLinkCalcHist.deductMoney"/>
    <display:column property="deductTax" sortable="true" titleKey="jbdMemberLinkCalcHist.deductTax"/>
    <display:column property="deductedDev" sortable="true" titleKey="jbdMemberLinkCalcHist.deductedDev"/>
    <display:column property="departmenKeepPv" sortable="true" titleKey="jbdMemberLinkCalcHist.departmenKeepPv"/>
    <display:column property="devFund" sortable="true" titleKey="jbdMemberLinkCalcHist.devFund"/>
    <display:column property="doublePassStar" sortable="true" titleKey="jbdMemberLinkCalcHist.doublePassStar"/>
    <display:column property="exchangeRate" sortable="true" titleKey="jbdMemberLinkCalcHist.exchangeRate"/>
    <display:column sortProperty="exitDate" sortable="true" titleKey="jbdMemberLinkCalcHist.exitDate">
         <fmt:formatDate value="${jbdMemberLinkCalcHistList.exitDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="firstMonth" sortable="true" titleKey="jbdMemberLinkCalcHist.firstMonth"/>
    <display:column property="franchiseMoney" sortable="true" titleKey="jbdMemberLinkCalcHist.franchiseMoney"/>
    <display:column property="franchisePv" sortable="true" titleKey="jbdMemberLinkCalcHist.franchisePv"/>
    <display:column property="freezeStatus" sortable="true" titleKey="jbdMemberLinkCalcHist.freezeStatus"/>
    <display:column property="honorGrade" sortable="true" titleKey="jbdMemberLinkCalcHist.honorGrade"/>
    <display:column property="honorPosition" sortable="true" titleKey="jbdMemberLinkCalcHist.honorPosition"/>
    <display:column property="honorStar" sortable="true" titleKey="jbdMemberLinkCalcHist.honorStar"/>
    <display:column property="identityType" sortable="true" titleKey="jbdMemberLinkCalcHist.identityType"/>
    <display:column property="isstore" sortable="true" titleKey="jbdMemberLinkCalcHist.isstore"/>
    <display:column property="keepPv" sortable="true" titleKey="jbdMemberLinkCalcHist.keepPv"/>
    <display:column property="keepUserCode" sortable="true" titleKey="jbdMemberLinkCalcHist.keepUserCode"/>
    <display:column property="lastKeepPv" sortable="true" titleKey="jbdMemberLinkCalcHist.lastKeepPv"/>
    <display:column property="lastKeepUserCode" sortable="true" titleKey="jbdMemberLinkCalcHist.lastKeepUserCode"/>
    <display:column property="leaderDev" sortable="true" titleKey="jbdMemberLinkCalcHist.leaderDev"/>
    <display:column property="leaderDevPv" sortable="true" titleKey="jbdMemberLinkCalcHist.leaderDevPv"/>
    <display:column property="linkNo" sortable="true" titleKey="jbdMemberLinkCalcHist.linkNo"/>
    <display:column property="memberType" sortable="true" titleKey="jbdMemberLinkCalcHist.memberType"/>
    <display:column property="monthAreaTotalPv" sortable="true" titleKey="jbdMemberLinkCalcHist.monthAreaTotalPv"/>
    <display:column property="monthConsumerPv" sortable="true" titleKey="jbdMemberLinkCalcHist.monthConsumerPv"/>
    <display:column property="monthDoubleAreaPv" sortable="true" titleKey="jbdMemberLinkCalcHist.monthDoubleAreaPv"/>
    <display:column property="monthDoubleMaxPv" sortable="true" titleKey="jbdMemberLinkCalcHist.monthDoubleMaxPv"/>
    <display:column property="monthGroupPv" sortable="true" titleKey="jbdMemberLinkCalcHist.monthGroupPv"/>
    <display:column property="monthMaxPv" sortable="true" titleKey="jbdMemberLinkCalcHist.monthMaxPv"/>
    <display:column property="monthRecommendGroupPv" sortable="true" titleKey="jbdMemberLinkCalcHist.monthRecommendGroupPv"/>
    <display:column property="name" sortable="true" titleKey="jbdMemberLinkCalcHist.name"/>
    <display:column property="networkMoney" sortable="true" titleKey="jbdMemberLinkCalcHist.networkMoney"/>
    <display:column property="passGrade" sortable="true" titleKey="jbdMemberLinkCalcHist.passGrade"/>
    <display:column property="passGradeGroupPv" sortable="true" titleKey="jbdMemberLinkCalcHist.passGradeGroupPv"/>
    <display:column property="passStar" sortable="true" titleKey="jbdMemberLinkCalcHist.passStar"/>
    <display:column property="passStarGroupPv" sortable="true" titleKey="jbdMemberLinkCalcHist.passStarGroupPv"/>
    <display:column property="passStatus" sortable="true" titleKey="jbdMemberLinkCalcHist.passStatus"/>
    <display:column property="petName" sortable="true" titleKey="jbdMemberLinkCalcHist.petName"/>
    <display:column property="province" sortable="true" titleKey="jbdMemberLinkCalcHist.province"/>
    <display:column property="recommendBonusPv" sortable="true" titleKey="jbdMemberLinkCalcHist.recommendBonusPv"/>
    <display:column property="recommendNo" sortable="true" titleKey="jbdMemberLinkCalcHist.recommendNo"/>
    <display:column property="salesStatus" sortable="true" titleKey="jbdMemberLinkCalcHist.salesStatus"/>
    <display:column property="sendMoney" sortable="true" titleKey="jbdMemberLinkCalcHist.sendMoney"/>
    <display:column property="startWeek" sortable="true" titleKey="jbdMemberLinkCalcHist.startWeek"/>
    <display:column property="status" sortable="true" titleKey="jbdMemberLinkCalcHist.status"/>
    <display:column property="storeExpandPv" sortable="true" titleKey="jbdMemberLinkCalcHist.storeExpandPv"/>
    <display:column property="storeRecommendPv" sortable="true" titleKey="jbdMemberLinkCalcHist.storeRecommendPv"/>
    <display:column property="storeServePv" sortable="true" titleKey="jbdMemberLinkCalcHist.storeServePv"/>
    <display:column property="successLeaderPv" sortable="true" titleKey="jbdMemberLinkCalcHist.successLeaderPv"/>
    <display:column property="successSalesPv" sortable="true" titleKey="jbdMemberLinkCalcHist.successSalesPv"/>
    <display:column property="successSalesRate" sortable="true" titleKey="jbdMemberLinkCalcHist.successSalesRate"/>
    <display:column property="totalDev" sortable="true" titleKey="jbdMemberLinkCalcHist.totalDev"/>
    <display:column property="userCode" sortable="true" titleKey="jbdMemberLinkCalcHist.userCode"/>
    <display:column property="validWeek" sortable="true" titleKey="jbdMemberLinkCalcHist.validWeek"/>
    <display:column property="ventureFund" sortable="true" titleKey="jbdMemberLinkCalcHist.ventureFund"/>
    <display:column property="ventureLeaderPv" sortable="true" titleKey="jbdMemberLinkCalcHist.ventureLeaderPv"/>
    <display:column property="ventureSalesPv" sortable="true" titleKey="jbdMemberLinkCalcHist.ventureSalesPv"/>
    <display:column property="weekGroupPv" sortable="true" titleKey="jbdMemberLinkCalcHist.weekGroupPv"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jbdMemberLinkCalcHistList.jbdMemberLinkCalcHist"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jbdMemberLinkCalcHistList.jbdMemberLinkCalcHists"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jbdMemberLinkCalcHistList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jbdMemberLinkCalcHistList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jbdMemberLinkCalcHistList.title"/>.pdf</display:setProperty>
</display:table>
