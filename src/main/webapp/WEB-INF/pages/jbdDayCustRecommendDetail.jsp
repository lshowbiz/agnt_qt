<%@ page contentType = "text/html; charset=utf-8" language = "java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>

    <meta name="menu" content="FoundationOrderMenu"/>
    <link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
    <script src="./scripts/calendar/calendar.js"> </script>
    <script src="./scripts/calendar/calendar-setup.js"> </script>
    <script src="./scripts/calendar/lang.jsp"> </script>

    <script src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
</head>

<div class="cont">
		
		<div><button type="button"
				onclick="history.back()">
				<ng:locale key="返回" />
			</button></div>
			
	<div class="bt mt">
		<h3 class="color2 ml">推荐奖励查询明细</h3>
	</div>

	<!-----领取会员编号、代数、领取订单编号、订单类型、订单金额、顾问推荐奖励金额（元）----->
	<div class="mt">
		<table class="prod mt">
			<tbody class="list_tbody_header">
				<tr style="text-align: center">
					<td>领取会员编号</td>
					<td>代数</td>
					<td>领取订单编号</td>
					<td>订单类型</td>
					<td>订单金额</td>
					<td>顾问推荐奖励金额（元）</td>
					<td>结算时间</td>
				</tr>
			</tbody>
			<tbody class="list_tbody_row">

				<c:choose>
					<c:when
						test="${jbdDayCustRecommendOrder!=null&&fn:length(jbdDayCustRecommendOrder)>0}">
						<c:forEach items="${jbdDayCustRecommendOrder}"
							var="jbdDayCustRecommendOrder">
							<tr class="bg-c gry3" style="text-align: center">
								<td class="color2">${jbdDayCustRecommendOrder.reuser_code }</td>
								<td>${jbdDayCustRecommendOrder.recommend_lvl}</td>
								<td>${jbdDayCustRecommendOrder.member_order_no}</td>
								<td><span style="color: #999999; font-weight: bold;"><ng:code
											listCode="po.all.order_type"
											value="${jbdDayCustRecommendOrder.order_type}" /></span></td>
								<td class="color1"><fmt:formatNumber
										value="${jbdDayCustRecommendOrder.order_money}" type="number"
										pattern="###,###,##0.00" /></td>
								<td class="color1"><fmt:formatNumber
										value="${jbdDayCustRecommendOrder.recommend_pv}" type="number"
										pattern="###,###,##0.00" /></td>
								<td>${jbdDayCustRecommendOrder.com_date}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="bg-c gry3">
							<td colspan="8">暂无数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div>
<!-- 	<div id="titleBar" class="searchBar">
		<input type="button" class="pageBtnWrap" name="cancel"
			onclick="history.back()" value="返回" /> -->

		
		<div id="kkpager" class="mt fr">
			<div>
				<span class="pageBtnWrap"><span class="disabled">首页</span><span
					class="disabled">上一页</span><span class="curr">1</span><span
					class="disabled">下一页</span><span class="disabled">末页</span></span>
			</div>
		</div>
		
	</div>
	${page.pageInfo }
</div>


<script>
    $(function(){
        $('.tabQueryLs tbody').find('tr:odd > td').css('background-color','#E4EBFF');
    });
    function go_print (id) {
    	var newWin = window.open('/jbdDayCustRecommend.html?id='+id);
    	newWin.print();
    	//newWin.close();
     }
    </script>














