<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>
		<meta name="heading" content="<fmt:message key='miMemberList.heading'/>" />
		<meta name="menu" content="ReportAreaMenu" />
		<meta name="parentMenu" content="SaleMenu" />
		<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
		<link rel="stylesheet" href="./styles/calendar/layout.css" />
		<script src="./scripts/salesanalysis/jquery.min.js"></script> 
		<script src="./scripts/salesanalysis/highcharts.src.js"></script> 
		<script src="./scripts/salesanalysis/exporting.src.js"></script>
		<script src="./scripts/calendar/calendar.js"></script>
		<script src="./scripts/calendar/calendar-setup.js"></script>
		<script src="./scripts/calendar/lang.jsp"></script>
	</head>
	<script>
		function loading(){
			var str = '<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src=images/indicator_smallwaitanim.gif alt=Loading  align=absmiddle/>';
			str += '&nbsp;&nbsp;<ng:locale key="button.loading"/>';
			document.getElementById("container1").innerHTML=str;
		}
	</script>
	<body>
		<form action="" method="get" name="searchForm" id="searchForm">
			<input type="hidden" name="search"  value="Y" />
			<c:choose>
				<c:when
					test="${saleFlag==null || saleFlag=='' || saleFlag=='null' || saleFlag=='userArea' }">
					<!-- 1.会员地域分布 -->
					<h2 class="title mgb20">
						会员地域分布<font color="#F17E11">（按推荐网展示数据）</font>
					</h2>
					<div class="condition">
						
							<table class="personalInfoTab">
								<tbody>
									<tr>
										<td>
											<input type="hidden" name="saleFlag"
												value="${saleFlag }" />
											<input type="hidden" name="selectFlag" value="y" />
											加入时间：
										</td>
										<td>
											<input id="startLogTime" name="startLogTime" type="text"
												size="10" maxlength="10" value="${startLogTime=='null'?'':startLogTime }" />
											<img src="./images/calendar/calendar7.gif"
												id="img_startOperatorTime" style="cursor: pointer;"
												title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
											<script>
					                        Calendar.setup({
					                            inputField     :    "startLogTime",
					                            ifFormat       :    "%Y-%m-%d",
					                            button         :    "img_startOperatorTime",
					                            singleClick    :    true
					                            });
					                        </script>
											-
										</td>
										<td>
											<input id="endLogTime" name="endLogTime" type="text" size="10"
												maxlength="10" value="${endLogTime=='null' ? '':endLogTime }" />
											<img src="./images/calendar/calendar7.gif"
												id="img_endOperatorTime" style="cursor: pointer;"
												title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
											<script>
					                        Calendar.setup({
						                        inputField     :    "endLogTime",
						                        ifFormat       :    "%Y-%m-%d",
						                        button         :    "img_endOperatorTime",
						                        singleClick    :    true
					                        });
					                        </script>
										</td>
										<td>
											<input type="submit" name="submit" value="查询" onclick='loading();'
												class="btn_common btn_mini corner2" />
										</td>
									</tr>
								</tbody>
							</table>
						
					</div>
	
					<table width="100%" border="0" cellspacing="5" cellpadding="0">
						<tr>
							<td align="center" valign="top">
								<div class="table_div">
									<div class="table_LongTable">
										<font color="red">（提示：点击省份名称展示市级数据）</font><br/>
										<div id="container1"></div>
	
									</div>
								</div>
							</td>
						</tr>
					</table>
					
					<c:choose>
						<c:when test="${list==null && search=='Y'}">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><font color="red">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><font color="red">未查询到数据！</font></font>
						</c:when>
						<c:otherwise>
							<script>
								var chart;
								$(function() {
									chart = new Highcharts.Chart({
										chart: {
											renderTo: 'container1',
											defaultSeriesType: 'bar',
											height:'${fn:length(list)*30+150}'
										},
										title: {
											text: '会员地域分布'
										},
										xAxis: {
											categories: [
			
											<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
									            '<a href="salesAnalysisShow?1=1&search=Y&saleFlag=userArea&province=${resultVar.PROVINCE}&startLogTime=${resultVar.STARTLOGTIME}&endLogTime=${resultVar.ENDLOGTIME}">${resultVar.NAME }</a>',
											</c:forEach>
			
											],
											title: {
												text: null
											}
										},
										yAxis: {
											min: 0,
											title: {
												text: '人',
												align: 'high'
											}
										},
										tooltip: {
											formatter: function() {
												return ''+
													 this.y +' 人';
											}
										},
										plotOptions: {
											bar: {
												dataLabels: {
													enabled: true
												}
											}
										},
										legend: {
											layout: 'vertical',
											align: 'right',
											verticalAlign: 'top',
											x: -100,
											y: 100,
											floating: true,
											borderWidth: 1,
											shadow: true
										},
										credits: {
											enabled: false
										},
									        series: [{
											name: '会员地域分析',
											data: [
											<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
									            ${resultVar.SUM },
											</c:forEach>
											]
										}]
									});
									
									
								});
							</script>
						</c:otherwise>
					</c:choose>
				</c:when>
	
				<c:when test="${saleFlag=='memberActivity'}">
					<!-- 2.团队活跃度分析 -->
					<c:choose>
						<c:when test="${saleFlag2=='logCount'}">
							<h2 class="title mgb20">
								登录次数<font color="#F17E11">（按推荐网展示数据）</font>
							</h2>
							<div class="condition">
								
								<table class="personalInfoTab">
									<tbody>
										<tr>
											<td>
												<input type="hidden" name="saleFlag" value="${saleFlag }" />
												<input type="hidden" name="saleFlag2" value="${saleFlag2 }" />
												登录时间：
											</td>
											<td>
												<input id="startLogTime" name="startLogTime" type="text"
													size="10" maxlength="10" value="${startLogTime }" />
												<img src="./images/calendar/calendar7.gif"
													id="img_startOperatorTime" style="cursor: pointer;"
													title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
												<script>
												Calendar.setup({
													inputField     :    "startLogTime",
													ifFormat       :    "%Y-%m-%d",
													button         :    "img_startOperatorTime",
													singleClick    :    true
												});
												</script>
											</td>
											<td>
												<input id="endLogTime" name="endLogTime" type="text" size="10"
													maxlength="10" value="${endLogTime=='null' ? '':endLogTime }" />
												<img src="./images/calendar/calendar7.gif"
													id="img_endOperatorTime" style="cursor: pointer;"
													title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
												<script>
												Calendar.setup({
												inputField     :    "endLogTime",
												ifFormat       :    "%Y-%m-%d",
												button         :    "img_endOperatorTime",
												singleClick    :    true
												});
												</script>
											</td>
											<td>
												<input type="submit" name="submit" value="查询" onclick='loading();' 
													class="btn_common btn_mini corner2" />
											</td>
										</tr>
									</tbody>
								</table>
								
							</div>
			
			
							<table width="100%" border="0" cellspacing="5" cellpadding="0">
								<tr>
									<td align="center" valign="top">
										<div class="table_div">
											<div class="table_LongTable">
												<div id="container1"></div>
											</div>
										</div>
									</td>
								</tr>
							</table>
							<c:choose>
								<c:when test="${list==null && search=='Y'}">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><font color="red">未查询到数据！</font>
								</c:when>
								<c:otherwise>
									<script>
									var chart;
									$(function() {
										chart = new Highcharts.Chart({
											chart: {
												renderTo: 'container1',
												defaultSeriesType: 'column'
											},
											title: {
												text: '登录次数'
											},
											xAxis: {
												categories: [
												<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
										            '${resultVar.NAME }',
												</c:forEach>
												],
												title: {
													text: null
												},
												max:7,
												labels:{
													style:{
														font:'normal 11px Verdana, sans-serif'
													}
												}
											},
											yAxis: {
												min: 0,
												title: {
													text: '次',
													align: 'high'
												}
											},
											tooltip: {
												formatter: function() {
													return ''+
														 this.y +' 次';
												}
											},
											plotOptions: {
												bar: {
													dataLabels: {
														enabled: true
													}
												}
											},
											legend: {
												layout: 'vertical',
												align: 'right',
												verticalAlign: 'top',
												x: -100,
												y: 100,
												floating: true,
												borderWidth: 1,
												shadow: true
											},
											credits: {
												enabled: false
											},
										        series: [{
												name: '<fmt:message key="登录次数"/>',
												data: [
												<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
										            ${resultVar.SUM },
												</c:forEach>
												]
											}]
										});
										
										
									});
									</script>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${saleFlag2=='orderType'}">
							<h2 class="title mgb20">
								订单类型<font color="#F17E11">（按推荐网展示数据）</font>
							</h2>
							<div class="condition">
								
									<table class="personalInfoTab">
										<tbody>
											<tr>
												<td>
													<input type="hidden" name="saleFlag" value="${saleFlag }" />
													<input type="hidden" name="saleFlag2" value="${saleFlag2 }" />
													订单审核时间：
												</td>
												<td>
													<input id="startLogTime" name="startLogTime" type="text"
														size="10" maxlength="10" value="${startLogTime }" />
													<img src="./images/calendar/calendar7.gif"
														id="img_startOperatorTime" style="cursor: pointer;"
														title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
													<script>
													Calendar.setup({
														inputField     :    "startLogTime",
														ifFormat       :    "%Y-%m-%d",
														button         :    "img_startOperatorTime",
														singleClick    :    true
													});
													</script>
												</td>
												<td>
													<input id="endLogTime" name="endLogTime" type="text" size="10"
														maxlength="10" value="${endLogTime=='null' ? '':endLogTime }" />
													<img src="./images/calendar/calendar7.gif"
														id="img_endOperatorTime" style="cursor: pointer;"
														title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
													<script>
													Calendar.setup({
													inputField     :    "endLogTime",
													ifFormat       :    "%Y-%m-%d",
													button         :    "img_endOperatorTime",
													singleClick    :    true
													});
													</script>
												</td>
												<td>
													<input type="submit" name="submit" value="查询" onclick='loading();'
														class="btn_common btn_mini corner2" />
												</td>
											</tr>
										</tbody>
									</table>
								
							</div>
			
			
							<table width="100%" border="0" cellspacing="5" cellpadding="0">
								<tr>
									<td align="center" valign="top">
										<div class="table_div">
											<div class="table_LongTable">
												<div id="container1"></div>
											</div>
										</div>
									</td>
								</tr>
							</table>
							<c:choose>
								<c:when test="${list==null && search=='Y'}">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><font color="red">未查询到数据！</font>
								</c:when>
								<c:otherwise>
									<script>
									var chart;
									$(function() {
										chart = new Highcharts.Chart({
											chart: {
												renderTo: 'container1',
												defaultSeriesType: 'column'
											},
											title: {
												text: '订单类型'
											},
											xAxis: {
												categories: [
												<c:forEach items="${list }" var="resultVar" varStatus="listStatus"> 
										            '<ng:code listCode="po.all.order_type" value="${resultVar.NAME}"/>',
												</c:forEach>
												],
												title: {
													text: null
												},
												max:7,
												labels:{
													style:{
														font:'normal 11px Verdana, sans-serif'
													}
												}
											},
											yAxis: {
												min: 0,
												title: {
													text: '个',
													align: 'high'
												}
											},
											tooltip: {
												formatter: function() {
													return ''+
														 this.y +' 个';
												}
											},
											plotOptions: {
												bar: {
													dataLabels: {
														enabled: true
													}
												}
											},
											legend: {
												layout: 'vertical',
												align: 'right',
												verticalAlign: 'top',
												x: -100,
												y: 100,
												floating: true,
												borderWidth: 1,
												shadow: true
											},
											credits: {
												enabled: false
											},
										        series: [{
												name: '<fmt:message key="订单类型"/>',
												data: [
												<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
										            ${resultVar.SUM },
												</c:forEach>
												]
											}]
										});
										
										
									});
									</script>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${saleFlag2=='orderCount'}">
							<h2 class="title mgb20">
								订单数量<font color="#F17E11">（按推荐网展示数据）</font>
							</h2>
							<div class="condition">
								
									<table class="personalInfoTab">
										<tbody>
											<tr>
												<td>
													<input type="hidden" name="saleFlag" value="${saleFlag }" />
													<input type="hidden" name="saleFlag2" value="${saleFlag2 }" />
													订单审核时间：
												</td>
												<td>
													<input id="startLogTime" name="startLogTime" type="text"
														size="10" maxlength="10" value="${startLogTime }" />
													<img src="./images/calendar/calendar7.gif"
														id="img_startOperatorTime" style="cursor: pointer;"
														title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
													<script>
													Calendar.setup({
														inputField     :    "startLogTime",
														ifFormat       :    "%Y-%m-%d",
														button         :    "img_startOperatorTime",
														singleClick    :    true
													});
													</script>
												</td>
												<td>
													<input id="endLogTime" name="endLogTime" type="text" size="10"
														maxlength="10" value="${endLogTime=='null' ? '':endLogTime }" />
													<img src="./images/calendar/calendar7.gif"
														id="img_endOperatorTime" style="cursor: pointer;"
														title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
													<script>
													Calendar.setup({
													inputField     :    "endLogTime",
													ifFormat       :    "%Y-%m-%d",
													button         :    "img_endOperatorTime",
													singleClick    :    true
													});
													</script>
												</td>
												<td>
													<input type="submit" name="submit" value="查询" onclick='loading();'
														class="btn_common btn_mini corner2" />
												</td>
											</tr>
										</tbody>
									</table>
								
							</div>
			
			
							<table width="100%" border="0" cellspacing="5" cellpadding="0">
								<tr>
									<td align="center" valign="top">
										<div class="table_div">
											<div class="table_LongTable">
												<div id="container1"></div>
											</div>
										</div>
									</td>
								</tr>
							</table>
							<c:choose>
								<c:when test="${list==null && search=='Y'}">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><font color="red">未查询到数据！</font>
								</c:when>
								<c:otherwise>
									<script>
									
								
									var chart;
									$(function() {
										chart = new Highcharts.Chart({
											chart: {
												renderTo: 'container1',
												defaultSeriesType: 'column'
											},
											title: {
												text: '订单数量'
											},
											xAxis: {
												categories: [
												<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
										            '${resultVar.NAME }',
												</c:forEach>
												],
												title: {
													text: null
												},
												max:7,
												labels:{
													style:{
														font:'normal 11px Verdana, sans-serif'
													}
												}
											},
											yAxis: {
												min: 0,
												title: {
													text: '个',
													align: 'high'
												}
											},
											tooltip: {
												formatter: function() {
													return ''+
														 this.y +' 个';
												}
											},
											plotOptions: {
												bar: {
													dataLabels: {
														enabled: true
													}
												}
											},
											legend: {
												layout: 'vertical',
												align: 'right',
												verticalAlign: 'top',
												x: -100,
												y: 100,
												floating: true,
												borderWidth: 1,
												shadow: true
											},
											credits: {
												enabled: false
											},
										        series: [{
												name: '<fmt:message key="订单数量"/>',
												data: [
												<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
										            ${resultVar.SUM },
												</c:forEach>
												]
											}]
										});
										
										
									});
									</script>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${saleFlag2=='orderMoney'}">
							<h2 class="title mgb20">
								订单金额<font color="#F17E11">（按推荐网展示数据）</font>
							</h2>
							<div class="condition">
								
									<table class="personalInfoTab">
										<tbody>
											<tr>
												<td>
													<input type="hidden" name="saleFlag" value="${saleFlag }" />
													<input type="hidden" name="saleFlag2" value="${saleFlag2 }" />
													订单审核时间：
												</td>
												<td>
													<input id="startLogTime" name="startLogTime" type="text"
														size="10" maxlength="10" value="${startLogTime }" />
													<img src="./images/calendar/calendar7.gif"
														id="img_startOperatorTime" style="cursor: pointer;"
														title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
													<script>
													Calendar.setup({
														inputField     :    "startLogTime",
														ifFormat       :    "%Y-%m-%d",
														button         :    "img_startOperatorTime",
														singleClick    :    true
													});
													</script>
												</td>
												<td>
													<input id="endLogTime" name="endLogTime" type="text" size="10"
														maxlength="10" value="${endLogTime=='null' ? '':endLogTime }" />
													<img src="./images/calendar/calendar7.gif"
														id="img_endOperatorTime" style="cursor: pointer;"
														title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
													<script>
													Calendar.setup({
													inputField     :    "endLogTime",
													ifFormat       :    "%Y-%m-%d",
													button         :    "img_endOperatorTime",
													singleClick    :    true
													});
													</script>
												</td>
												<td>
													<input type="submit" name="submit" value="查询" onclick='loading();'
														class="btn_common btn_mini corner2" />
												</td>
											</tr>
										</tbody>
									</table>
								
							</div>
			
			
							<table width="100%" border="0" cellspacing="5" cellpadding="0">
								<tr>
									<td align="center" valign="top">
										<div class="table_div">
											<div class="table_LongTable">
												<div id="container1"></div>
											</div>
										</div>
									</td>
								</tr>
							</table>
							<c:choose>
								<c:when test="${list==null && search=='Y'}">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><font color="red">未查询到数据！</font>
								</c:when>
								<c:otherwise>
									<script>
									
								
									var chart;
									$(function(){
										chart = new Highcharts.Chart({
											chart: {
												renderTo: 'container1',
												defaultSeriesType: 'column'
											},
											title: {
												text: '订单金额'
											},
											xAxis: {
												categories: [
												<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
										            '${resultVar.NAME }',
												</c:forEach>
												],
												title: {
													text: null
												},
												max:7,
												labels:{
													style:{
														font:'normal 11px Verdana, sans-serif'
													}
												}
											},
											yAxis: {
												min: 0,
												title: {
													text: 'RMB',
													align: 'high'
												}
											},
											tooltip: {
												formatter: function() {
													return ''+
														 this.y +' RMB';
												}
											},
											plotOptions: {
												bar: {
													dataLabels: {
														enabled: true
													}
												}
											},
											legend: {
												layout: 'vertical',
												align: 'right',
												verticalAlign: 'top',
												x: -100,
												y: 100,
												floating: true,
												borderWidth: 1,
												shadow: true
											},
											credits: {
												enabled: false
											},
										        series: [{
												name: '<fmt:message key="订单金额"/>',
												data: [
												<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
										            ${resultVar.SUM },
												</c:forEach>
												]
											}]
										});
										
										
									});
									</script>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:when test="${saleFlag2=='orderPv'}">
							<h2 class="title mgb20">
								订单PV<font color="#F17E11">（按推荐网展示数据）</font>
							</h2>
							<div class="condition">
								
									<table class="personalInfoTab">
										<tbody>
											<tr>
												<td>
													<input type="hidden" name="saleFlag" value="${saleFlag }" />
													<input type="hidden" name="saleFlag2" value="${saleFlag2 }" />
													订单审核时间：
												</td>
												<td>
													<input id="startLogTime" name="startLogTime" type="text"
														size="10" maxlength="10" value="${startLogTime }" />
													<img src="./images/calendar/calendar7.gif"
														id="img_startOperatorTime" style="cursor: pointer;"
														title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
													<script>
													Calendar.setup({
														inputField     :    "startLogTime",
														ifFormat       :    "%Y-%m-%d",
														button         :    "img_startOperatorTime",
														singleClick    :    true
													});
													</script>
												</td>
												<td>
													<input id="endLogTime" name="endLogTime" type="text" size="10"
														maxlength="10" value="${endLogTime=='null' ? '':endLogTime }" />
													<img src="./images/calendar/calendar7.gif"
														id="img_endOperatorTime" style="cursor: pointer;"
														title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
													<script>
													Calendar.setup({
													inputField     :    "endLogTime",
													ifFormat       :    "%Y-%m-%d",
													button         :    "img_endOperatorTime",
													singleClick    :    true
													});
													</script>
												</td>
												<td>
													<input type="submit" name="submit" value="查询" onclick='loading();'
														class="btn_common btn_mini corner2" />
												</td>
											</tr>
										</tbody>
									</table>
								
							</div>
			
			
							<table width="100%" border="0" cellspacing="5" cellpadding="0">
								<tr>
									<td align="center" valign="top">
										<div class="table_div">
											<div class="table_LongTable">
												<div id="container1"></div>
											</div>
										</div>
									</td>
								</tr>
							</table>
							<c:choose>
								<c:when test="${list==null && search=='Y'}">
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><font color="red">未查询到数据！</font>
								</c:when>
								<c:otherwise>
									<script>
									
								
									var chart;
									$(function(){
										chart = new Highcharts.Chart({
											chart: {
												renderTo: 'container1',
												defaultSeriesType: 'column'
											},
											title: {
												text: '订单PV'
											},
											xAxis: {
												categories: [
												<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
										            '${resultVar.NAME }',
												</c:forEach>
												],
												title: {
													text: null
												},
												max:7,
												labels:{
													style:{
														font:'normal 11px Verdana, sans-serif'
													}
												}
											},
											yAxis: {
												min: 0,
												title: {
													text: 'PV',
													align: 'high'
												}
											},
											tooltip: {
												formatter: function() {
													return ''+
														 this.y +' PV';
												}
											},
											plotOptions: {
												bar: {
													dataLabels: {
														enabled: true
													}
												}
											},
											legend: {
												layout: 'vertical',
												align: 'right',
												verticalAlign: 'top',
												x: -100,
												y: 100,
												floating: true,
												borderWidth: 1,
												shadow: true
											},
											credits: {
												enabled: false
											},
											series: [{
												name: '<fmt:message key="订单PV"/>',
												data: [
												<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
										            ${resultVar.SUM },
												</c:forEach>
												]
											}]
										});
										
										
									});
									</script>
								</c:otherwise>
							</c:choose>
						</c:when>
					</c:choose>				
				</c:when>
	
				<c:when test="${saleFlag=='teamAdd'}">
					<!-- 3.团队新增会员 -->
					<h2 class="title mgb20">
						团队新增会员<font color="#F17E11">（按推荐网展示数据）</font>
					</h2>
					<div class="condition">
						
							<table class="personalInfoTab">
								<tbody>
									<tr>
										<td>
											<input type="hidden" name="saleFlag"
												value="${saleFlag }" />
											加入时间：
										</td>
										<td>
											<select name="year" id="year" class="mySelect">
												<option value=""></option>
												<c:forEach items="${yearList }" var="yearVar"
													varStatus="yearListStatus">
													<c:if test='${param.year==yearVar }'>
														<option value="${yearVar }" selected>
															${yearVar }
														</option>
													</c:if>
													<c:if test='${param.year!=yearVar }'>
														<option value="${yearVar }">
															${yearVar }
														</option>
													</c:if>
												</c:forEach>
											</select>
										</td>
										<td>
											会员类型：										
										</td>
										<td>
											<!--<ng:list name="userType" listCode="isstore" value="${userType}" defaultValue="" styleClass="mySelect" />-->
											<select name="userType" id="userType" class="mySelect">
												<option value=""><ng:locale key="list.please.select"/></option>
												<option value="0" ${userType=='0' ? 'selected' : '' }><ng:code listCode="isstore" value="0"/></option>
												<option value="1" ${userType=='1' ? 'selected' : '' }><ng:code listCode="isstore" value="1"/></option>
												<option value="2" ${userType=='2' ? 'selected' : '' }><ng:code listCode="isstore" value="2"/></option>
											</select>
										</td>
										<td>
											<input type="submit" name="submit" value="查询" onclick='loading();'
												class="btn_common btn_mini corner2" />
										</td>
									</tr>
								</tbody>
							</table>
						
					</div>
	
	
	
					<table width="100%" border="0" cellspacing="5" cellpadding="0">
						<tr>
							<td align="center" valign="top">
								<div class="table_div">
									<div class="table_LongTable">
										<div id="container1"></div>
									</div>
								</div>
							</td>
						</tr>
					</table>
					<c:choose>
						<c:when test="${list==null && search=='Y'}">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><font color="red">未查询到数据！</font>
						</c:when>
						<c:otherwise>
							<script>
									
								
								var chart;
								$(function(){
									chart = new Highcharts.Chart({
										chart: {
											renderTo: 'container1',
											defaultSeriesType: 'column'
										},
										title: {
											text: '团队新增会员'
										},
										xAxis: {
											categories: [
											<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
												'${resultVar.NAME }',
											</c:forEach>
											],
											title: {
												text: null
											},
											labels:{
												style:{
													font:'normal 11px Verdana, sans-serif'
												}
											}
										},
										yAxis: {
											min: 0,
											title: {
												text: '人',
												align: 'high'
											}
										},
										tooltip: {
											formatter: function() {
												return ''+
													 this.y +' 人';
											}
										},
										plotOptions: {
											bar: {
												dataLabels: {
													enabled: true
												}
											}
										},
										legend: {
											layout: 'vertical',
											align: 'right',
											verticalAlign: 'top',
											x: -100,
											y: 100,
											floating: true,
											borderWidth: 1,
											shadow: true
										},
										credits: {
											enabled: false
										},
											series: [{
											name: '团队新增会员',
											data: [
											<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
												${resultVar.SUM },
											</c:forEach>
											]
										}]
									});
									
									
								});
								</script>
							</c:otherwise>
						</c:choose>
				</c:when>
	
				<c:when test="${saleFlag=='mom'}">
					<!-- 4.环比分析 -->
					<h2 class="title mgb20">
						环比分析<font color="#F17E11">（按推荐网展示数据）</font>
					</h2>
					<div class="condition">
						
							<table class="personalInfoTab">
								<tbody>
									<tr>
										<td>
											<input type="hidden" name="saleFlag"
												value="${saleFlag }" />
											<input type="hidden" name="selectFlag" value="y" />
											订单审核时间：
										</td>
										<td>
											<input id="startLogTime" name="startLogTime" type="text"
												size="10" maxlength="10" value="${startLogTime }" />
											<img src="./images/calendar/calendar7.gif"
												id="img_startOperatorTime" style="cursor: pointer;"
												title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
											<script>
					                        Calendar.setup({
					                            inputField     :    "startLogTime",
					                            ifFormat       :    "%Y-%m-%d",
					                            button         :    "img_startOperatorTime",
					                            singleClick    :    true
					                            });
					                        </script>
											-
										</td>
										<td>
											<input id="endLogTime" name="endLogTime" type="text" size="10"
												maxlength="10" value="${endLogTime }" />
											<img src="./images/calendar/calendar7.gif"
												id="img_endOperatorTime" style="cursor: pointer;"
												title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
											<script>
					                        Calendar.setup({
					                        inputField     :    "endLogTime",
					                        ifFormat       :    "%Y-%m-%d",
					                        button         :    "img_endOperatorTime",
					                        singleClick    :    true
					                        });
					                        </script>
										</td>
										<td>
											统计类型:
										</td>
										<td>
											<select name="type" id="type" class="mySelect">
												<option value="0" ${type=='0' ? 'selected' : '' }>金额</option>
												<option value="1" ${type=='1' ? 'selected' : '' }>PV</option>
											</select>
										</td>
										<td>
											<input type="submit" name="submit" value="查询" onclick='loading();'
												class="btn_common btn_mini corner2" />
										</td>
									</tr>
								</tbody>
							</table>
						
					</div>
	
					<table width="100%" border="0" cellspacing="5" cellpadding="0">
						<tr>
							<td align="center" valign="top">
								<div class="table_div">
									<div class="table_LongTable">
										<div id="container1"></div>
	
									</div>
								</div>
							</td>
						</tr>
					</table>
	
					<c:choose>
						<c:when test="${list==null && search=='Y'}">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><font color="red">未查询到数据！</font>
						</c:when>
						<c:otherwise>
							<script>
									
									var chart;
									$(function(){
										chart = new Highcharts.Chart({
											chart: {
												renderTo: 'container1',
												defaultSeriesType: 'column'
											},
											title: {
												text: '环比分析'
											},
											xAxis: {
												categories: [
												<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
										            '${resultVar.NAME }',
												</c:forEach>
												],
												title: {
													text: null
												},
												labels:{
													style:{
														font:'normal 11px Verdana, sans-serif'
													}
												}
											},
											yAxis: {
												min: 0,
												title: {
													text: '${unit}',
													align: 'high'
												}
											},
											tooltip: {
												formatter: function() {
													return ''+
														 this.y +' ${unit}';
												}
											},
											plotOptions: {
												bar: {
													dataLabels: {
														enabled: true
													}
												}
											},
											legend: {
												layout: 'vertical',
												align: 'right',
												verticalAlign: 'top',
												x: -100,
												y: 100,
												floating: true,
												borderWidth: 1,
												shadow: true
											},
											credits: {
												enabled: false
											},
										        series: [{
												name: '环比分析',
												data: [
												<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
										            ${resultVar.SUM },
												</c:forEach>
												]
											}]
										});
										
										
									});
							</script>
						</c:otherwise>
					</c:choose>
				</c:when>
	
				<c:when test="${saleFlag=='production'}">
					<!-- 5.商品分析 -->
					<h2 class="title mgb20">
						商品分析<font color="#F17E11">（按推荐网展示数据）</font>
					</h2>
					<div class="condition">
						
							<table class="personalInfoTab">
								<tbody>
									<tr>
										<td>
											<input type="hidden" name="saleFlag"
												value="${saleFlag }" />
											订单审核时间
										</td>
										<td>
											<input id="startLogTime" name="startLogTime" type="text"
												size="10" maxlength="10" value="${startLogTime }" />
											<img src="./images/calendar/calendar7.gif"
												id="img_startOperatorTime" style="cursor: pointer;"
												title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
											<script>
	                                    Calendar.setup({
	                                    inputField     :    "startLogTime",
	                                    ifFormat       :    "%Y-%m-%d",
	                                    button         :    "img_startOperatorTime",
	                                    singleClick    :    true
	                                });
	                                </script>
											-
										</td>
										<td>
											<input id="endLogTime" name="endLogTime" type="text" size="10"
												maxlength="10" value="${endLogTime }" />
											<img src="./images/calendar/calendar7.gif"
												id="img_endOperatorTime" style="cursor: pointer;"
												title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
											<script>
	                                    Calendar.setup({
	                                        inputField     :    "endLogTime",
	                                        ifFormat       :    "%Y-%m-%d",
	                                        button         :    "img_endOperatorTime",
	                                        singleClick    :    true
	                                    });
	                                    </script>
										</td>
										<td>
											<input type="submit" name="submit" value="查询" onclick='loading();'
												class="btn_common btn_mini corner2" />
										</td>
									</tr>
								</tbody>
							</table>
						
					</div>
	
	
	
	
					<table width="100%" border="0" cellspacing="5" cellpadding="0">
						<tr>
							<td align="center" valign="top">
								<div class="table_div">
									<div class="table_LongTable">
										<div id="container1"></div>
									</div>
								</div>
							</td>
						</tr>
					</table>
					<c:choose>
						<c:when test="${list==null && search=='Y'}">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><font color="red">未查询到数据！</font>
						</c:when>
						<c:otherwise>
							<script>
								
							
								var chart;
								$(function() {
									chart = new Highcharts.Chart({
										chart: {
											renderTo: 'container1',
											defaultSeriesType: 'bar',
											height: '500'
										},
										title: {
											text: '商品分析 '
										},
										xAxis: {
											categories: [
											<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
									            "<font style='font-weight: bold;font-size:12;' >${resultVar.NAME }</font>",
											</c:forEach>
											],
											title: {
												text: null
											}
										},
										yAxis: {
											min: 0,
											title: {
												text: '件',
												align: 'high'
											}
										},
										tooltip: {
											formatter: function() {
												return ''+
													 this.y +' 件';
											}
										},
										plotOptions: {
											bar: {
												dataLabels: {
													enabled: true
												}
											}
										},
										legend: {
											layout: 'vertical',
											align: 'right',
											verticalAlign: 'top',
											x: -100,
											y: 100,
											floating: true,
											borderWidth: 1,
											shadow: true
										},
										credits: {
											enabled: false
										},
									        series: [{
											name: '商品分析',
											data: [
											<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
									            ${resultVar.SUM },
											</c:forEach>
											]
										}]
									});
									
									
								});
							</script>
						</c:otherwise>
					</c:choose>
				</c:when>
	
				<c:when test="${saleFlag=='champion'}">
					<!-- 6.推荐冠军 -->
					<h2 class="title mgb20">
						推荐冠军<font color="#F17E11">（按推荐网展示数据）</font>
					</h2>
					<div class="condition">
						
							<table class="personalInfoTab">
								<tbody>
									<tr>
										<td>
											<input type="hidden" name="saleFlag"
												value="${saleFlag }" />
											会员加入时间
										</td>
										<td>
											<input id="startLogTime" name="startLogTime" type="text"
												size="10" maxlength="10" value="${startLogTime }" />
											<img src="./images/calendar/calendar7.gif"
												id="img_startOperatorTime" style="cursor: pointer;"
												title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
											<script>
	                                Calendar.setup({
	                                inputField     :    "startLogTime",
	                                ifFormat       :    "%Y-%m-%d",
	                                button         :    "img_startOperatorTime",
	                                singleClick    :    true
	                                });
	                                </script>
											-
										</td>
										<td>
											<input id="endLogTime" name="endLogTime" type="text" size="10"
												maxlength="10" value="${endLogTime }" />
											<img src="./images/calendar/calendar7.gif"
												id="img_endOperatorTime" style="cursor: pointer;"
												title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
											<script>
	                                Calendar.setup({
	                                inputField     :    "endLogTime",
	                                ifFormat       :    "%Y-%m-%d",
	                                button         :    "img_endOperatorTime",
	                                singleClick    :    true
	                                });
	                                </script>
										</td>
										<td>
											<input type="submit" name="submit" value="查询" onclick='loading();'
												class="btn_common btn_mini corner2" />
										</td>
									</tr>
								</tbody>
							</table>
						
					</div>
	
					<table width="100%" border="0" cellspacing="5" cellpadding="0">
						<tr>
							<td align="center" valign="top">
								<!--
								<div class="searchAreaBox">
									
										<input type="hidden" name="saleFlag" value="${saleFlag }" />
										加入时间
										<input id="startLogTime" name="startLogTime" type="text" size="10"
											maxlength="10" value="${startLogTime }" />
										<img src="./images/calendar/calendar7.gif"
											id="img_startOperatorTime" style="cursor: pointer;"
											title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
										<script> 
													Calendar.setup({
													inputField     :    "startLogTime", 
													ifFormat       :    "%Y-%m-%d",  
													button         :    "img_startOperatorTime", 
													singleClick    :    true
													}); 
												</script>
										-
										<input id="endLogTime" name="endLogTime" type="text" size="10"
											maxlength="10" value="${endLogTime }" />
										<img src="./images/calendar/calendar7.gif"
											id="img_endOperatorTime" style="cursor: pointer;"
											title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
										<script> 
													Calendar.setup({
													inputField     :    "endLogTime", 
													ifFormat       :    "%Y-%m-%d",  
													button         :    "img_endOperatorTime", 
													singleClick    :    true
													}); 
												</script>
										<input name="search" class="bgBtn" type="submit" value="查询" onclick='loading();' />
									
								</div>
	                            -->
								<div class="table_div">
									<div class="table_LongTable">
										<div id="container1"></div>
									</div>
								</div>
							</td>
						</tr>
					</table>
					<c:choose>
						<c:when test="${list==null && search=='Y'}">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><font color="red">未查询到数据！</font>
						</c:when>
						<c:otherwise>
							<script>
								
								var chart;
								$(function() {
									chart = new Highcharts.Chart({
										chart: {
											renderTo: 'container1',
											defaultSeriesType: 'column'
										},
										title: {
											text: '推荐冠军'
										},
										xAxis: {
											categories: [
											<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
									            '${resultVar.NAME }',
											</c:forEach>
											],
											title: {
												text: null
											},
											max:9,
											labels:{
												rotation:-12,
												style:{
													font:'normal 11px Verdana, sans-serif'
												}
											}
										},
										yAxis: {
											min: 0,
											title: {
												text: '人',
												align: 'high'
											}
										},
										tooltip: {
											formatter: function() {
												return ''+
													 this.y +' 人';
											}
										},
										plotOptions: {
											bar: {
												dataLabels: {
													enabled: true
												}
											}
										},
										legend: {
											layout: 'vertical',
											align: 'right',
											verticalAlign: 'top',
											x: -100,
											y: 100,
											floating: true,
											borderWidth: 1,
											shadow: true
										},
										credits: {
											enabled: false
										},
										series: [{
											name: '推荐冠军',
											data: [
											<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
									            ${resultVar.SUM },
											</c:forEach>
											]
										}]
									});
									
									
								});
							</script>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when
					test="${saleFlag=='awardTitle' }">
					<!-- 7.团队奖衔 -->
					<h2 class="title mgb20">
						团队奖衔<font color="#F17E11">（按推荐网展示数据）</font>
					</h2>
					<div class="condition">
						
							<table class="personalInfoTab">
								<tbody>
									<tr>
										<td>
											<input type="hidden" name="saleFlag"
												value="${saleFlag }" />
											<input type="hidden" name="selectFlag" value="y" />
											财政月：
										</td>
										<td>
											<input id="month" name="month" type="text" onkeydown='if((event.keyCode<96 || event.keyCode>105) && (event.keyCode<48 || event.keyCode>57) && event.keyCode!=8 && event.keyCode!=13 && event.keyCode!=46 && (event.keyCode<37 || event.keyCode>40)){return false;}'
												size="10" maxlength="10" value="${month=='null'?'':month }" />
										</td>
										<td>
											奖衔类型：				
										</td>
										<td>
											<!--<ng:list name="userType" listCode="isstore" value="${userType}" defaultValue="" styleClass="mySelect" />-->
											<select name="starType" id="starType" class="mySelect">
												<option value=""><ng:locale key="list.please.select"/></option>
												<option value="0" ${starType=='0' ? 'selected' : '' }><ng:code listCode="pass.star.type" value="0"/></option>
												<option value="1" ${starType=='1' ? 'selected' : '' }><ng:code listCode="pass.star.type" value="1"/></option>
											</select>
										</td>
										<td>
											<input type="submit" name="submit" value="查询" onclick='loading();'
												class="btn_common btn_mini corner2" />
										</td>
									</tr>
								</tbody>
							</table>
						
					</div>
	
					<table width="100%" border="0" cellspacing="5" cellpadding="0">
						<tr>
							<td align="center" valign="top">
								<div class="table_div">
									<div class="table_LongTable">
										<div id="container1"></div>
	
									</div>
								</div>
							</td>
						</tr>
					</table>
	
					<c:choose>
						<c:when test="${list==null && search=='Y'}">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><font color="red">未查询到数据！</font>
						</c:when>
						<c:otherwise>
							<script>
								var chart;
								$(function(){
									chart = new Highcharts.Chart({
										chart: {
											renderTo: 'container1',
											defaultSeriesType: 'bar',
											height:'${fn:length(list)*30+150}'
										},
										title: {
											text: '团队奖衔'
										},
										xAxis: {
											categories: [
			
											<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
									            '<ng:code listCode="pass.star.zero" value="${resultVar.NAME }"/>',
											</c:forEach>
			
											],
											title: {
												text: null
											}
										},
										yAxis: {
											min: 0,
											title: {
												text: '人',
												align: 'high'
											}
										},
										tooltip: {
											formatter: function() {
												return ''+
													 this.y +' 人';
											}
										},
										plotOptions: {
											bar: {
												dataLabels: {
													enabled: true
												}
											}
										},
										legend: {
											layout: 'vertical',
											align: 'right',
											verticalAlign: 'top',
											x: -100,
											y: 100,
											floating: true,
											borderWidth: 1,
											shadow: true
										},
										credits: {
											enabled: false
										},
									        series: [{
											name: '团队奖衔',
											data: [
											<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
									            ${resultVar.SUM },
											</c:forEach>
											]
										}]
									});
									
									
								});
							</script>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when
					test="${saleFlag=='addPromotion' }">
					<!-- 8.新增晋级 -->
					<h2 class="title mgb20">
						新增晋级<font color="#F17E11">（按推荐网展示数据）</font>
					</h2>
					<div class="condition">
						
							<table class="personalInfoTab">
								<tbody>
									<tr>
										<td>
											<input type="hidden" name="saleFlag"
												value="${saleFlag }" />
											<input type="hidden" name="selectFlag" value="y" />
											财政月：
										</td>
										<td>
											<input id="month" name="month" type="text" onkeydown='if((event.keyCode<96 || event.keyCode>105) && (event.keyCode<48 || event.keyCode>57) && event.keyCode!=8 && event.keyCode!=13 && event.keyCode!=46 && (event.keyCode<37 || event.keyCode>40)){return false;}'
												size="10" maxlength="10" value="${month=='null'?'':month }" />
										</td>
										<td>
											<input type="submit" name="submit" value="查询" onclick='loading();'
												class="btn_common btn_mini corner2" />
										</td>
									</tr>
								</tbody>
							</table>
						
					</div>
	
					<table width="100%" border="0" cellspacing="5" cellpadding="0">
						<tr>
							<td align="center" valign="top">
								<div class="table_div">
									<div class="table_LongTable">
										<div id="container1"></div>
	
									</div>
								</div>
							</td>
						</tr>
					</table>
	
					<c:choose>
						<c:when test="${list==null && search=='Y'}">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><font color="red">未查询到数据！</font>
						</c:when>
						<c:otherwise>
							<script>
								var chart;
								$(function(){
									chart = new Highcharts.Chart({
										chart: {
											renderTo: 'container1',
											defaultSeriesType: 'bar',
											height:'${fn:length(list)*30+150}'
										},
										title: {
											text: '新增晋级'
										},
										xAxis: {
											categories: [
			
											<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
									            '<ng:code listCode="pass.star.zero" value="${resultVar.NAME }"/>',
											</c:forEach>
			
											],
											title: {
												text: null
											}
										},
										yAxis: {
											min: 0,
											title: {
												text: '人',
												align: 'high'
											}
										},
										tooltip: {
											formatter: function() {
												return ''+
													 this.y +' 人';
											}
										},
										plotOptions: {
											bar: {
												dataLabels: {
													enabled: true
												}
											}
										},
										legend: {
											layout: 'vertical',
											align: 'right',
											verticalAlign: 'top',
											x: -100,
											y: 100,
											floating: true,
											borderWidth: 1,
											shadow: true
										},
										credits: {
											enabled: false
										},
									        series: [{
											name: '新增晋级',
											data: [
											<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
									            ${resultVar.SUM },
											</c:forEach>
											]
										}]
									});
									
									
								});
							</script>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when
					test="${saleFlag=='achievement' }">
					<!-- 9.区域业绩分布 -->
					<h2 class="title mgb20">
						区域业绩分布<font color="#F17E11">（按推荐网展示数据）</font>
					</h2>
					<div class="condition">
						
							<table class="personalInfoTab">
								<tbody>
									<tr>
										<td>
											<input type="hidden" name="saleFlag"
												value="${saleFlag }" />
											<input type="hidden" name="selectFlag" value="y" />
											加入时间：
										</td>
										<td>
											<input id="startLogTime" name="startLogTime" type="text"
												size="10" maxlength="10" value="${startLogTime=='null'?'':startLogTime }" />
											<img src="./images/calendar/calendar7.gif"
												id="img_startOperatorTime" style="cursor: pointer;"
												title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
											<script>
					                        Calendar.setup({
					                            inputField     :    "startLogTime",
					                            ifFormat       :    "%Y-%m-%d",
					                            button         :    "img_startOperatorTime",
					                            singleClick    :    true
					                            });
					                        </script>
											-
										</td>
										<td>
											<input id="endLogTime" name="endLogTime" type="text" size="10"
												maxlength="10" value="${endLogTime=='null' ? '':endLogTime }" />
											<img src="./images/calendar/calendar7.gif"
												id="img_endOperatorTime" style="cursor: pointer;"
												title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
											<script>
					                        Calendar.setup({
						                        inputField     :    "endLogTime",
						                        ifFormat       :    "%Y-%m-%d",
						                        button         :    "img_endOperatorTime",
						                        singleClick    :    true
					                        });
					                        </script>
										</td>
										<td>
											<input type="submit" name="submit" value="查询" onclick='loading();'
												class="btn_common btn_mini corner2" />
										</td>
									</tr>
								</tbody>
							</table>
						
					</div>
	
					<table width="100%" border="0" cellspacing="5" cellpadding="0">
						<tr>
							<td align="center" valign="top">
								<div class="table_div">
									<div class="table_LongTable">
										<font color="red">（提示：点击省份名称展示市级数据）</font><br/>
										<div id="container1"></div>
	
									</div>
								</div>
							</td>
						</tr>
					</table>
	
					<c:choose>
						<c:when test="${list==null && search=='Y'}">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><font color="red">未查询到数据！</font>
						</c:when>
						<c:otherwise>
							<script>
								var chart;
								$(function(){
									chart = new Highcharts.Chart({
										chart: {
											renderTo: 'container1',
											defaultSeriesType: 'bar',
											height:'${fn:length(list)*30+150}'
										},
										title: {
											text: '区域业绩分布'
										},
										xAxis: {
											categories: [
											 <c:choose>
												<c:when test="${province!=null && province!='' && province!='null' }">
													 <c:forEach items="${list }" var="resultVar" varStatus="listStatus">
											            '<a href="salesAnalysisShow?1=1&search=Y&saleFlag=achievement&userCode=${param.userCode}&startLogTime=${startLogTime}&endLogTime=${endLogTime}">${resultVar.NAME}</a>',
													</c:forEach>
												</c:when>
												<c:otherwise>
													 <c:forEach items="${list }" var="resultVar" varStatus="listStatus">
											            '<a href="salesAnalysisShow?1=1&search=Y&saleFlag=achievement&userCode=${param.userCode}&province=${resultVar.CODE}&startLogTime=${startLogTime}&endLogTime=${endLogTime}">${resultVar.NAME}</a>',
													</c:forEach>
												</c:otherwise>
											</c:choose>
													
												 
											],
											title: {
												text: null
											}
										},
										yAxis: {
											min: 0,
											title: {
												text: 'PV',
												align: 'high'
											}
										},
										tooltip: {
											formatter: function() {
												return ''+
													 this.y +' PV';
											}
										},
										plotOptions: {
											bar: {
												dataLabels: {
													enabled: true
												}
											}
										},
										legend: {
											layout: 'vertical',
											align: 'right',
											verticalAlign: 'top',
											x: -100,
											y: 100,
											floating: true,
											borderWidth: 1,
											shadow: true
										},
										credits: {
											enabled: false
										},
									        series: [{
											name: '区域业绩分布',
											data: [
											<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
									            ${resultVar.SUM },
											</c:forEach>
											]
										}]
									});
									
									
								});
							</script>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when
					test="${saleFlag=='highIncome' }">
					<!-- 10.高收入人群 -->
					<h2 class="title mgb20">
						高收入人群<font color="#F17E11">（按推荐网展示数据）</font>
					</h2>
					<div class="condition">
						
							<table class="personalInfoTab">
								<tbody>
									<tr>
										<td>
											<input type="hidden" name="saleFlag"
												value="${saleFlag }" />
											<input type="hidden" name="selectFlag" value="y" />
											加入时间：
										</td>
										<td>
											<input id="startLogTime" name="startLogTime" type="text"
												size="10" maxlength="10" value="${startLogTime=='null'?'':startLogTime }" />
											<img src="./images/calendar/calendar7.gif"
												id="img_startOperatorTime" style="cursor: pointer;"
												title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
											<script>
					                        Calendar.setup({
					                            inputField     :    "startLogTime",
					                            ifFormat       :    "%Y-%m-%d",
					                            button         :    "img_startOperatorTime",
					                            singleClick    :    true
					                            });
					                        </script>
											-
										</td>
										<td>
											<input id="endLogTime" name="endLogTime" type="text" size="10"
												maxlength="10" value="${endLogTime=='null' ? '':endLogTime }" />
											<img src="./images/calendar/calendar7.gif"
												id="img_endOperatorTime" style="cursor: pointer;"
												title="<fmt:message key="Calendar.TT.SEL_DATE"/>" />
											<script>
					                        Calendar.setup({
						                        inputField     :    "endLogTime",
						                        ifFormat       :    "%Y-%m-%d",
						                        button         :    "img_endOperatorTime",
						                        singleClick    :    true
					                        });
					                        </script>
										</td>
										<td>
											最多查询数量：
										</td>
										<td>
											<select name="maxNum" id="maxNum">
												<option value="10" ${param.maxNum==10 ? 'selected' : '' }>10</option>
												<option value="20" ${param.maxNum==20 ? 'selected' : '' }>20</option>
												<option value="50" ${param.maxNum==50 ? 'selected' : '' }>50</option>
											</select>
										</td>
										<td>
											起始金额：<input id="minMoney" name="minMoney" type="text" size="10"
												maxlength="10" value="${param.minMoney=='null' ? '':param.minMoney }" />
										</td>
										<td>
											<input type="submit" name="submit" value="查询" onclick='loading();'
												class="btn_common btn_mini corner2" />
										</td>
									</tr>
								</tbody>
							</table>
						
					</div>
	
					<table width="100%" border="0" cellspacing="5" cellpadding="0">
						<tr>
							<td align="center" valign="top">
								<div class="table_div">
									<div class="table_LongTable">
										<div id="container1"></div>
	
									</div>
								</div>
							</td>
						</tr>
					</table>
	
					<c:choose>
						<c:when test="${list==null && search=='Y'}">
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br/><br/><font color="red">未查询到数据！</font>
						</c:when>
						<c:otherwise>
							<script>
								var chart;
								$(function(){
									chart = new Highcharts.Chart({
										chart: {
											renderTo: 'container1',
											defaultSeriesType: 'bar',
											height:'${fn:length(list)*30+150}'
										},
										title: {
											text: '高收入人群'
										},
										xAxis: {
											categories: [
			
											<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
									            '${resultVar.NAME }',
											</c:forEach>
			
											],
											title: {
												text: null
											}
										},
										yAxis: {
											min: 0,
											title: {
												text: 'RMB',
												align: 'high'
											}
										},
										tooltip: {
											formatter: function() {
												return ''+
													 this.y +' RMB';
											}
										},
										plotOptions: {
											bar: {
												dataLabels: {
													enabled: true
												}
											}
										},
										legend: {
											layout: 'vertical',
											align: 'right',
											verticalAlign: 'top',
											x: -100,
											y: 100,
											floating: true,
											borderWidth: 1,
											shadow: true
										},
										credits: {
											enabled: false
										},
									        series: [{
											name: '高收入人群',
											data: [
											<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
									            ${resultVar.SUM },
											</c:forEach>
											]
										}]
									});
									
									
								});
							</script>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					未知区域！
				</c:otherwise>
			</c:choose>
		</form>
	</body>
</html>
