<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>
<head>
    <title><fmt:message key="foundationOrderList.title"/></title>
    <meta name="menu" content="FoundationOrderMenu"/>
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
			<h3 class="color2 ml">我的捐赠记录</h3>
		</div>
		<form method="get" action="${ctx}/foundationOrders" id="searchForm">
			 <table class="search_table mt" >
				<tr>
					<td style="width:80px;">开始日期：</td>
	                <td style="width:200px;">
	                 <input id="delStartTime" name="delStartTime" type="text"  value="${param.dealStartTime }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>								
	                </td>
					
					
					<td style="width:80px;">截止日期：</td>
	                <td style="width:200px;">
						<input id="delEndTime" name="delEndTime" type="text" value="${param.dealEndTime }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
	                </td>
					<td >
						<button id="search" type="button" onclick="searchF('${pageContext.request.contextPath}/foundationOrders')">查询</button>
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
						<td>公益项目</td>
						<td>捐赠数量</td>
						<td>支付金额</td>
						<td>状态</td>
						<td>捐赠日期</td>
						<td>操作</td>
					</tr>
				</tbody>
				<tbody class="list_tbody_row">	
				<c:choose>
					<c:when test="${foundationOrderList!=null&&fn:length(foundationOrderList)>0}">
						<c:forEach items="${foundationOrderList}"  var="foundationOrder" varStatus='sc'>
							<tr class="bg-c gry3">

								<td class="w200">${foundationOrder.foundationItem.name}</td>
								<td>${foundationOrder.fiNum}</td>
								<td>${foundationOrder.amount}</td>
								<td>
									<c:if test="${foundationOrder.status==0}">未支付</c:if>
									<c:if test="${foundationOrder.status==1}">已支付</c:if>
								</td>
								<td>
									<fmt:formatDate value="${foundationOrder.createTime}" pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long"/>
								</td>
								<td>
									<c:if test="${foundationOrder.status==0}">
										<a href="${pageContext.request.contextPath}/payFoundation?1=1&orderId=${foundationOrder.orderId}">[立即支付]</a>
									</c:if>

								</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr  class="bg-c gry3">
							<td colspan="6">暂无数据！</td>
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
<%-- 

<div class="span10">
    <h2><fmt:message key="foundationOrderList.heading"/></h2>

    <form method="get" action="${ctx}/foundationOrders" id="searchForm" class="form-search">
    <div id="search" class="input-append">
        <input type="text" size="20" name="q" id="query" value="${param.q}"
               placeholder="<fmt:message key="search.enterTerms"/>" class="input-medium search-query"/>
        <button id="button.search" class="btn" type="submit">
            <i class="icon-search"></i> <fmt:message key="button.search"/>
        </button>
    </div>
    </form>

    <fmt:message key="foundationOrderList.message"/>

    <div id="actions" class="form-actions">
        <a href='<c:url value="/foundationOrderform"/>' class="btn btn-primary">
            <i class="icon-plus icon-white"></i> <fmt:message key="button.add"/></a>
        <a href='<c:url value="/mainMenu"/>' class="btn"><i class="icon-ok"></i> <fmt:message key="button.done"/></a>
    </div>

<display:table name="foundationOrderList" class="table table-condensed table-striped table-hover" requestURI="" id="foundationOrderList" export="true" pagesize="25">
    <display:column property="orderId" sortable="true" href="foundationOrderform" media="html"
        paramId="orderId" paramProperty="orderId" titleKey="foundationOrder.orderId"/>
    <display:column property="orderId" media="csv excel xml pdf" titleKey="foundationOrder.orderId"/>
    <display:column property="amount" sortable="true" titleKey="foundationOrder.amount"/>
    <display:column sortProperty="createTime" sortable="true" titleKey="foundationOrder.createTime">
         <fmt:formatDate value="${foundationOrderList.createTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column sortProperty="endTime" sortable="true" titleKey="foundationOrder.endTime">
         <fmt:formatDate value="${foundationOrderList.endTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="fiId" sortable="true" titleKey="foundationOrder.fiId"/>
    <display:column property="fiNum" sortable="true" titleKey="foundationOrder.fiNum"/>
    <display:column property="field1" sortable="true" titleKey="foundationOrder.field1"/>
    <display:column property="field2" sortable="true" titleKey="foundationOrder.field2"/>
    <display:column property="payType" sortable="true" titleKey="foundationOrder.payType"/>
    <display:column sortProperty="startTime" sortable="true" titleKey="foundationOrder.startTime">
         <fmt:formatDate value="${foundationOrderList.startTime}" pattern="${datePattern}"/>
    </display:column>
    <display:column property="status" sortable="true" titleKey="foundationOrder.status"/>
    <display:column property="userCode" sortable="true" titleKey="foundationOrder.userCode"/>

    <display:setProperty name="paging.banner.item_name"><fmt:message key="foundationOrderList.foundationOrder"/></display:setProperty>
    <display:setProperty name="paging.banner.items_name"><fmt:message key="foundationOrderList.foundationOrders"/></display:setProperty>

    <display:setProperty name="export.excel.filename"><fmt:message key="foundationOrderList.title"/>.xls</display:setProperty>
    <display:setProperty name="export.csv.filename"><fmt:message key="foundationOrderList.title"/>.csv</display:setProperty>
    <display:setProperty name="export.pdf.filename"><fmt:message key="foundationOrderList.title"/>.pdf</display:setProperty>
</display:table>
 --%>