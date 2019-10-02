<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="jbdSendRecordNoteList.title"/></title>
    <meta name="menu" content="JbdSendRecordNoteMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="span10">
    <h2><fmt:message key="jbdSendRecordNoteList.heading"/></h2>

    <form method="get" action="${ctx}/jbdSendRecordNotes" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="jbdSendRecordNoteList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/jbdSendRecordNoteform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="jbdSendRecordNoteList" class="table table-condensed table-striped table-hover" requestURI="" id="jbdSendRecordNoteList" export="true" pagesize="25">
    <display:column property="id" sortable="true" href="jbdSendRecordNoteform" media="html"
        paramId="id" paramProperty="id" titleKey="jbdSendRecordNote.id"/>
    <display:column property="id" media="csv excel xml pdf" titleKey="jbdSendRecordNote.id"/>
    <display:column property="WMonth" sortable="true" titleKey="jbdSendRecordNote.WMonth"/>
    <display:column property="WWeek" sortable="true" titleKey="jbdSendRecordNote.WWeek"/>
    <display:column property="WYear" sortable="true" titleKey="jbdSendRecordNote.WYear"/>
    <display:column property="active" sortable="true" titleKey="jbdSendRecordNote.active"/>
    <display:column property="bank" sortable="true" titleKey="jbdSendRecordNote.bank"/>
    <display:column property="bankaddress" sortable="true" titleKey="jbdSendRecordNote.bankaddress"/>
    <display:column property="bankbook" sortable="true" titleKey="jbdSendRecordNote.bankbook"/>
    <display:column property="bankcard" sortable="true" titleKey="jbdSendRecordNote.bankcard"/>
    <display:column property="beforeFreezeStatus" sortable="true" titleKey="jbdSendRecordNote.beforeFreezeStatus"/>
    <display:column property="cardType" sortable="true" titleKey="jbdSendRecordNote.cardType"/>
    <display:column property="companyCode" sortable="true" titleKey="jbdSendRecordNote.companyCode"/>
    <display:column property="currentDev" sortable="true" titleKey="jbdSendRecordNote.currentDev"/>
    <display:column property="deductedDev" sortable="true" titleKey="jbdSendRecordNote.deductedDev"/>
    <display:column sortProperty="exitDate" sortable="true" titleKey="jbdSendRecordNote.exitDate">
         <fmt:formatDate value="${jbdSendRecordNoteList.exitDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="freezeStatus" sortable="true" titleKey="jbdSendRecordNote.freezeStatus"/>
    <display:column property="leaderDev" sortable="true" titleKey="jbdSendRecordNote.leaderDev"/>
    <display:column property="leaderDevPv" sortable="true" titleKey="jbdSendRecordNote.leaderDevPv"/>
    <display:column property="linkNo" sortable="true" titleKey="jbdSendRecordNote.linkNo"/>
    <display:column property="memberLevel" sortable="true" titleKey="jbdSendRecordNote.memberLevel"/>
    <display:column property="memberType" sortable="true" titleKey="jbdSendRecordNote.memberType"/>
    <display:column property="name" sortable="true" titleKey="jbdSendRecordNote.name"/>
    <display:column property="operaterCode" sortable="true" titleKey="jbdSendRecordNote.operaterCode"/>
    <display:column sortProperty="operaterTime" sortable="true" titleKey="jbdSendRecordNote.operaterTime">
         <fmt:formatDate value="${jbdSendRecordNoteList.operaterTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="petName" sortable="true" titleKey="jbdSendRecordNote.petName"/>
    <display:column property="recommendNo" sortable="true" titleKey="jbdSendRecordNote.recommendNo"/>
    <display:column property="registerStatus" sortable="true" titleKey="jbdSendRecordNote.registerStatus"/>
    <display:column property="reissueStatus" sortable="true" titleKey="jbdSendRecordNote.reissueStatus"/>
    <display:column property="remittanceBNum" sortable="true" titleKey="jbdSendRecordNote.remittanceBNum"/>
    <display:column property="remittanceMoney" sortable="true" titleKey="jbdSendRecordNote.remittanceMoney"/>
    <display:column sortProperty="sendDate" sortable="true" titleKey="jbdSendRecordNote.sendDate">
         <fmt:formatDate value="${jbdSendRecordNoteList.sendDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column sortProperty="sendDateDev" sortable="true" titleKey="jbdSendRecordNote.sendDateDev">
         <fmt:formatDate value="${jbdSendRecordNoteList.sendDateDev}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="sendLateCause" sortable="true" titleKey="jbdSendRecordNote.sendLateCause"/>
    <display:column property="sendLateRemark" sortable="true" titleKey="jbdSendRecordNote.sendLateRemark"/>
    <display:column property="sendMoney" sortable="true" titleKey="jbdSendRecordNote.sendMoney"/>
    <display:column property="sendRemark" sortable="true" titleKey="jbdSendRecordNote.sendRemark"/>
    <display:column property="sendStatusDev" sortable="true" titleKey="jbdSendRecordNote.sendStatusDev"/>
    <display:column property="sendTrace" sortable="true" titleKey="jbdSendRecordNote.sendTrace"/>
    <display:column property="sendUserDev" sortable="true" titleKey="jbdSendRecordNote.sendUserDev"/>
    <display:column property="startWeek" sortable="true" titleKey="jbdSendRecordNote.startWeek"/>
    <display:column sortProperty="status" sortable="true" titleKey="jbdSendRecordNote.status">
        <input type="checkbox" disabled="disabled" <c:if test="${jbdSendRecordNoteList.status}">checked="checked"</c:if>/>
    </display:column>
    <display:column sortProperty="supplyTime" sortable="true" titleKey="jbdSendRecordNote.supplyTime">
         <fmt:formatDate value="${jbdSendRecordNoteList.supplyTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="totalDev" sortable="true" titleKey="jbdSendRecordNote.totalDev"/>
    <display:column property="userCode" sortable="true" titleKey="jbdSendRecordNote.userCode"/>
    <display:column property="validWeek" sortable="true" titleKey="jbdSendRecordNote.validWeek"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="jbdSendRecordNoteList.jbdSendRecordNote"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="jbdSendRecordNoteList.jbdSendRecordNotes"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="jbdSendRecordNoteList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="jbdSendRecordNoteList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="jbdSendRecordNoteList.title"/>.pdf</display:setProperty>
</display:table>
