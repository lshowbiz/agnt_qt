<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>
<%
String currentMenuId = (String)session.getAttribute("currentMenuId");
String currentSubMenuId = (String)session.getAttribute("currentSubMenuId");
%>
<head>
    <meta name="menu" content="FiFundbookJournalMenu"/>
    <link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
	<script src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
<style type="text/css">
.btn_common2 {
    display:block;
    width:280px;
    height:24px;
    line-height:24px;
    text-align:center;
    color:#FFF;
    font-size: 14px;
    font-weight: bold;
    border:1px solid #F17E11;
    cursor: pointer;
    background:url("./images/bg_btn_common.gif") left center no-repeat;
}
.btn_mini2 {
    width:120px;
    height:26px;
    height:24px\9;
    line-height:26px;
    line-height:24px\9;
    font-size: 12px;
}
.ft-green {
	COLOR: #41ab01
}
</style>
	<script src="./scripts/calendar/calendar.js"> </script> 
	<script src="./scripts/calendar/calendar-setup.js"> </script> 
	<script src="./scripts/calendar/lang.jsp"> </script>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

    <div class="cont mt">
    	<ul class="nav_tab">
    		<security:authorize url="/app/fiBankbookJournals">
   			<li>
   				<a href="javascript:location.href='${pageContext.request.contextPath}/fiBankbookJournals?1=1&currentMenuId=<%=currentMenuId%>&currentSubMenuId=<%=currentSubMenuId%>'">发展基金</a>
   			</li>
   			</security:authorize>
   			<security:authorize url="/app/fiFundbookJournals">
   			<li>
   				<a href="javascript:location.href='${pageContext.request.contextPath}/fiFundbookJournals?1=1&currentMenuId=<%=currentMenuId%>&currentSubMenuId=<%=currentSubMenuId%>'">分红基金</a>
   			</li>
   			</security:authorize>
   			<security:authorize url="/app/fidFundbookJournals">
   			<li class="tab_active">
   				<a href="javascript:location.href='${pageContext.request.contextPath}/fidFundbookJournals?1=1&currentMenuId=<%=currentMenuId%>&currentSubMenuId=<%=currentSubMenuId%>'">定向基金</a>
   			</li>
   			</security:authorize>
   		</ul>
    	
		<!-- <div class="bt mt">
			<h3 class="color2 ml">定向基金</h3>
		</div> -->			
		<div class="mt">	
			<table width="85%" class="mt" style="margin:0 auto; font-size:14px" >
				<tr>
					<td>基金帐户：${fiFundbookBalance.userCode}</td>
					<td>可用余额：<ins class="red">${fiFundbookBalance.balance }&nbsp;元</ins></td>		
					<td><button id="search" type="button" class="btn btn-warning" value="转账给他人定向基金" onclick="javascript:location.href='${pageContext.request.contextPath}/fiTransferFundbookform?bankbookType=2&transferType=2&destOther=1'">转账给他人定向基金</button></td>			
				</tr>
			</table>
		</div>
	</div>	
	
	<div class="cont2">		
		<div>
            <form method="get" action="${ctx}/fidFundbookJournals" id="searchForm" class="form-search">
                <table class="search_table mt">
                      <tr>
                          <td style="width:80px;">开始日期：</td>
                          <td style="width:200px;">
                           <input id="delStartTime" name="delStartTime" type="text" value="${param.dealStartTime!=null && param.dealStartTime!='null' && param.dealStartTime!='' ? param.dealStartTime : '' }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>								
                          </td>
                          <td style="width:80px;">截止日期：</td>
                          <td style="width:200px;">
						<input id="delEndTime" name="delEndTime" type="text" value="${param.dealEndTime!=null && param.dealEndTime!='null' && param.dealEndTime!='' ? param.dealEndTime : '' }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>								
                        	</td>
                          
                          <td>
                          	<button id="search" type="button" onclick="searchF('${pageContext.request.contextPath}/fidFundbookJournals')">查询</button>
                          </td>
                      </tr>
                </table>
            </form>
        </div>
		<div class="mt">	
			<table class="prod mt">		
				 <tbody class="list_tbody_header">
					<tr>     
						<td>交易日期</td>                   
						<td>摘要</td>                
						<td>存入</td>                   
						<td>取出</td>                   
						<td>结余</td>
					</tr>
				</tbody>
				<tbody class="list_tbody_row">
					<c:forEach items="${fiFundbookJournalList}"  var="fiFundbookJournal" varStatus='sc'>
						<tr class="bg-c gry3"> 
							<td class="color2"><fmt:formatDate value="${fiFundbookJournal.dealDate}" pattern="yyyy-MM-dd HH:mm:ss" type="date" dateStyle="long"/></td>
							<td>
								<span title="${fiFundbookJournal.notes}">
									<c:choose>
										<c:when test="${fns:isAbbreviate(fiFundbookJournal.notes, 35)}">
											<c:out value="${fns:abbreviate(fiFundbookJournal.notes, 35,'...')}" />
										</c:when>
										<c:otherwise>
										<c:out value="${fiFundbookJournal.notes}" />
										</c:otherwise>
									</c:choose>
								</span>
							</td>                 
							<td>
								<c:if test="${fiFundbookJournal.dealType == 'A'}">
									${fiFundbookJournal.money}
								</c:if>
							</td>                   
							<td>
								<c:if test="${fiFundbookJournal.dealType == 'D'}">
									${fiFundbookJournal.money}
								</c:if>
							</td>
							<td>${fiFundbookJournal.balance}</td>
						</tr>
					</c:forEach>
				</tbody>	
			</table>
	
			
		</div>	        
      ${page.pageInfo }
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