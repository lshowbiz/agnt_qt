<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>
<head>
    <title></title>
    <meta name="menu" content="FiLovecoinJournalMenu"/>
    <link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
	<script src="./scripts/calendar/calendar.js"> </script> 
	<script src="./scripts/calendar/calendar-setup.js"> </script> 
	<script src="./scripts/calendar/lang.jsp"> </script>
    <script src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>
    <div class="cont">
        <div class="bt mt">
            <h3 class="color2 ml">爱心积分</h3>
        </div>
        <table class="search_table mt" >
            <tr>
                <td width="100px" >爱心积分帐户：</td>
                <td width="100px">${fiLovecoinBalance.userCode}</td>
                <td width="100px">爱心积分余额：</td>
                <td>${fiLovecoinBalance.balance }</td>
            </tr>
        </table>


        <div class="bt mt">
            <h3 class="color2 ml">最近交易记录</h3>
        </div>
        <form method="get" action="${ctx}/fiLovecoinJournals" id="searchForm">
	        <table class="search_table mt">
	            <tr>
	                <td style="width:80px">交易日期：</td>
	                <td style="width:200px;">
	                    <input id="delStartTime" name="delStartTime" type="text"  value="${param.dealStartTime }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	                </td>
	                
	                <td style="width:80px;">交易日期：</td>
	                <td style="width:200px;">
	                    <input id="delEndTime" name="delEndTime" type="text"  value="${param.dealEndTime }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	                </td>
	                <td>
	               		<button id="search" type="button" onclick="searchF('${pageContext.request.contextPath}/fiLovecoinJournals')">查询</button>
	                </td>
	            </tr>
	        </table>
        </form>
        <!---------->
        <div class="mt">
            <table class="prod mt">
                <tbody class="list_tbody_header">
                <tr>
                    <!-- <th>会员编号</th> -->
                    <td>交易日期</td>
                    <td>摘要</td>
                    <td>存入</td>
                    <td>取出</td>
                    <td>结余</td>
                </tr>
                </tbody>
                <tbody class="list_tbody_row">


                <c:choose>
                    <c:when test="${fiLovecoinJournalList!=null&&fn:length(fiLovecoinJournalList)>0}">
                        <c:forEach items="${fiLovecoinJournalList}"  var="fiLovecoinJournal" varStatus='sc'>
                            <tr class="bg-c gry3">
                                <!-- <td>${jfiLovecoinJournal.USER_CODE}</td> -->
                                <td class="w200">
                                    <fmt:formatDate value="${fiLovecoinJournal.dealDate}"   pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long" />
                                </td>

                                <td>
			                    	<span title="${fiLovecoinJournal.notes}">
				                        <c:choose>
											<c:when test="${fns:isAbbreviate(fiLovecoinJournal.notes, 35)}">
												<c:out value="${fns:abbreviate(fiLovecoinJournal.notes, 35,'...')}" />
											</c:when>
											<c:otherwise>
												<c:out value="${fiLovecoinJournal.notes}" />
											</c:otherwise>
										</c:choose>
									</span>
                                </td>
                                <td>
                                    <c:if test="${fiLovecoinJournal.dealType == 'A'}">
                                        ${fiLovecoinJournal.coin}
                                    </c:if>
                                </td>
                                <td>
                                    <c:if test="${fiLovecoinJournal.dealType == 'D'}">
                                        ${fiLovecoinJournal.coin}
                                    </c:if>
                                </td>
                                <td >${fiLovecoinJournal.balance}</td>

                            </tr>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <tr class="bg-c gry3">
                            <td colspan="5">暂无数据</td>
                        </tr>
                    </c:otherwise>
                </c:choose>
                </tbody>
            </table>


        </div>

       
    </div>






<script>
function searchF(url)
{
    var startTime= $("#delStartTime").val();
    var endTime=$("#delEndTime").val();

    location.href=url+"?dealStartTime="+startTime+"&dealEndTime="+endTime;
}
$(function(){
          $('#tabQueryLs tbody').find('tr:odd > td').css('background-color','#E4EBFF');
});
</script>

<!-- 
<div class="span10">
    <h2><fmt:message key="fiLovecoinJournalList.heading"/></h2>

    <form method="get" action="${ctx}/fiLovecoinJournals" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="fiLovecoinJournalList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/fiLovecoinJournalform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="fiLovecoinJournalList" class="table table-condensed table-striped table-hover" requestURI="" id="fiLovecoinJournalList" export="true" pagesize="25">
    <display:column property="journalId" sortable="true" href="fiLovecoinJournalform" media="html"
        paramId="journalId" paramProperty="journalId" titleKey="fiLovecoinJournal.journalId"/>
    <display:column property="journalId" media="csv excel xml pdf" titleKey="fiLovecoinJournal.journalId"/>
    <display:column property="appId" sortable="true" titleKey="fiLovecoinJournal.appId"/>
    <display:column property="balance" sortable="true" titleKey="fiLovecoinJournal.balance"/>
    <display:column property="coin" sortable="true" titleKey="fiLovecoinJournal.coin"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="fiLovecoinJournal.createTime">
         <fmt:formatDate value="${fiLovecoinJournalList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="createrCode" sortable="true" titleKey="fiLovecoinJournal.createrCode"/>
    <display:column property="createrName" sortable="true" titleKey="fiLovecoinJournal.createrName"/>
    <display:column sortProperty="dealDate" sortable="true" titleKey="fiLovecoinJournal.dealDate">
         <fmt:formatDate value="${fiLovecoinJournalList.dealDate}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="dealType" sortable="true" titleKey="fiLovecoinJournal.dealType"/>
    <display:column property="moneyType" sortable="true" titleKey="fiLovecoinJournal.moneyType"/>
    <display:column property="notes" sortable="true" titleKey="fiLovecoinJournal.notes"/>
    <display:column property="remark" sortable="true" titleKey="fiLovecoinJournal.remark"/>
    <display:column property="serial" sortable="true" titleKey="fiLovecoinJournal.serial"/>
    <display:column property="uniqueCode" sortable="true" titleKey="fiLovecoinJournal.uniqueCode"/>
    <display:column property="userCode" sortable="true" titleKey="fiLovecoinJournal.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="fiLovecoinJournalList.fiLovecoinJournal"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="fiLovecoinJournalList.fiLovecoinJournals"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="fiLovecoinJournalList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="fiLovecoinJournalList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="fiLovecoinJournalList.title"/>.pdf</display:setProperty>
</display:table>
 -->
