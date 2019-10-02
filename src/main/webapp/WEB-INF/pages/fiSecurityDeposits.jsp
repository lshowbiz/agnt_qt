<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="fiSecurityDepositList.title"/></title>
    <meta name="menu" content="FiSecurityDepositMenu"/>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>
<div class="cont" >	
			<div class="bt mt">
				<h3 class="color2 ml">保证金</h3>
			</div>	
    <table width="98%" border="0" class="ordersDetails">
        <tbody>
            <tr>
                <td class="tr" width="10%">帐户：</td>
                <td>${fiSecurityDeposit.userCode }</td>
                <td class="tr" width="10%">市场用名：</td>
                <td>${fiSecurityDeposit.userName }</td>

            </tr>
			<tr>
                <td class="tr">部门：</td>
                <td><ng:code listCode="fisecuritydeposit.dept" value="${fiSecurityDeposit.dept}"/></td>
                <td class="tr">联系方式：</td>
                <td>${fiSecurityDeposit.tel }</td>
            </tr>
			<tr>
                <td class="tr">余额：</td>
                <td>${fiSecurityDeposit.balance }&nbsp;元</td>
                <td></td>
                <td></td>
            </tr>
        </tbody>
    </table>

			<div class="bt mt">
				<h3 class="color2 ml">扣补明细记录</h3>
			</div>		
			<div class="mt">	
				<table class="prod mt" border="1" style="border-collapse:collapse; border:1px solid #ebebeb;" >
				<tbody class="list_tbody_header">
					<tr>
						<td>交易日期</td>
						<td>说明</td>
						<td>存入</td>
						<td>扣取</td>
						<td>结余</td>
					</tr>
				</tbody>
				<tbody class="list_tbody_row">
				  <c:forEach items="${fiSecurityDepositJournalList}"  var="fiSecurityDepositJournal">
					<tr class="bg-c gry3">
						<td class="w200"><fmt:formatDate value="${fiSecurityDepositJournal.createTime}" pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long"/></td>
						
						<td>
	                    	<span title="${fiSecurityDepositJournal.notes}">
								<c:choose>
									<c:when test="${fns:isAbbreviate(fiSecurityDepositJournal.notes, 35)}">
										<c:out value="${fns:abbreviate(fiSecurityDepositJournal.notes, 35,'...')}" />
									</c:when>
									<c:otherwise>
										<c:out value="${fiSecurityDepositJournal.notes}" />
									</c:otherwise>
								</c:choose>
							</span>
	                    </td>
						<td>
                        <c:if test="${fiSecurityDepositJournal.dealType == 'A'}">
                            ${fiSecurityDepositJournal.amount}
                        </c:if>
                    	</td>
                    	<td>
                        <c:if test="${fiSecurityDepositJournal.dealType == 'D'}">
                            ${fiSecurityDepositJournal.amount}
                        </c:if>
                    	</td>
                    	
                    	<td>${fiSecurityDepositJournal.balance }</td>
                    	
					</tr>
				 </c:forEach>
				</tbody>
			</table>
			</div>	

<%-- 
<div class="span10">
    <h2><fmt:message key="fiSecurityDepositList.heading"/></h2>

    <form method="get" action="${ctx}/fiSecurityDeposits" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="fiSecurityDepositList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/fiSecurityDepositform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="fiSecurityDepositList" class="table table-condensed table-striped table-hover" requestURI="" id="fiSecurityDepositList" export="true" pagesize="25">
    <display:column property="fsdId" sortable="true" href="fiSecurityDepositform" media="html"
        paramId="fsdId" paramProperty="fsdId" titleKey="fiSecurityDeposit.fsdId"/>
    <display:column property="fsdId" media="csv excel xml pdf" titleKey="fiSecurityDeposit.fsdId"/>
    <display:column property="balance" sortable="true" titleKey="fiSecurityDeposit.balance"/>
    <display:column property="dept" sortable="true" titleKey="fiSecurityDeposit.dept"/>
    <display:column property="email" sortable="true" titleKey="fiSecurityDeposit.email"/>
    <display:column property="tel" sortable="true" titleKey="fiSecurityDeposit.tel"/>
    <display:column property="userCode" sortable="true" titleKey="fiSecurityDeposit.userCode"/>
    <display:column property="userName" sortable="true" titleKey="fiSecurityDeposit.userName"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="fiSecurityDepositList.fiSecurityDeposit"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="fiSecurityDepositList.fiSecurityDeposits"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="fiSecurityDepositList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="fiSecurityDepositList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="fiSecurityDepositList.title"/>.pdf</display:setProperty>
</display:table>
--%>
