<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <!-- 退货单查询 -->
</head>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script src="./scripts/calendar/calendar.js"></script>
<script src="./scripts/calendar/calendar-setup.js"></script>
<script src="./scripts/calendar/lang.jsp"></script>
<script src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>

<img style="display:none" src="images/indicator_smallwaitanim.gif" alt="Loading"  align="absmiddle" />
<body>
    <div class="cont">
		<div class="bt mt">
			<h3 class="color2 ml">退货单信息</h3>
		</div>			
        <form action="jprRefunds" method="get" name="jprRefunds" id="jprRefunds">
            <table class="search_table mt">
					<tr>
					    <td width="60px" style="white-space:nowrap;overflow:hidden;word-break:break-all;">退货单编号：</td>
					    <td width="200px"><input type="text" name="roNo" id="roNo"  value="${param.roNo }" maxlength="100"/></td>
					    <td width="60px" style="white-space:nowrap;overflow:hidden;word-break:break-all;">制单日期开始：</td>
						<td width="200px"><input name="timeBegin" id="timeBegin" type="text" value="${param.timeBegin }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/></td>
						<td width="60px" style="white-space:nowrap;overflow:hidden;word-break:break-all;">制单日期结束：</td>
						<td width="200px"><input name="timeEnd" id="timeEnd" type="text" value="${param.timeEnd }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/></td>
					</tr>
					<tr>
						<td width="60px" style="white-space:nowrap;overflow:hidden;word-break:break-all;">原订单编号：</td>
						<td width="200px"><input type="text" name="memberOrderNo" id="memberOrderNo" value="${param.memberOrderNo }" maxlength="100"/></td>
						<td><button id="search" type="submit" onclick="loading();" >查询</button></td>
					</tr>
            </table>
        </form>

        <div class="mt">	
			<table class="prod mt">		
                <tbody class="list_tbody_header">
                    <tr>
                        <td align="center">退货单编号</td>
                        <td align="center">原订单编号</td>
                        <td align="center">制单日期</td>
                        <td align="center">退款状态</td>
                        <td align="center">查看</td>
                    </tr>
                </tbody>
		        <tbody class="list_tbody_row">	
                 <c:forEach items="${jprRefundsList}" var="jprRefundOb" >
                 <tr class="bg-c gry3"> 
                       <td align="center">${jprRefundOb.roNo }</td>
                       <td align="center">${jprRefundOb.memberOrderNo }</td>
                       <td align="center">
                       <fmt:formatDate value="${jprRefundOb.orderDate }" pattern="yyyy-MM-dd"/></td>
                       <td align="center">
                           <c:if test="${jprRefundOb.refundStatus=='1'}">
                                     <ng:code listCode="pr.refund" value="1"/>
                           </c:if>
                           <c:if test="${jprRefundOb.refundStatus=='2'}">
                                     <ng:code listCode="pr.refund" value="2"/>
                           </c:if>
                           <c:if test="${jprRefundOb.refundStatus=='3'}">
                                     <ng:code listCode="pr.refund" value="3"/>
                           </c:if>
                       </td>
                       <td align="center">
                            <img src="images/search.gif" onclick="window.location.href='jprRefundform.html?roNo=${jprRefundOb.roNo }&strAction=jprRefundQueryDetail';" alt="<ng:locale key="menu.editJbdMemberLinkCalcHis"/>" title="查看明细"/>
                       </td>
                       </tr>
                 </c:forEach>
                </tbody>
        </table>
	${page.pageInfo }
	</div>
	</div>
</body>

