<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <title><fmt:message key="reportHotProductList.title"/></title>
    <meta name="menu" content="ReportHotProductMenu"/>
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


<div class="span10">
<h2 class="title mgb20">
	热销商品分析
</h2>
<form method="get" action="${ctx}/reportHotProducts" id="searchForm" class="form-search">

<div class="condition">
<table class="personalInfoTab">
	<tbody>
		<tr>
			<td>
				时间：
			</td>
			<td>
				<select>
					<option>----请选择----</option>
				</select>
			</td>
			<td>区域：</td>
			<td>
				<select>
					<option>----请选择----</option>
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
</form>
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
<c:when test="${list == null}">
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
				height:'${fn:length(list)*30+150}'
			},
			title: {
				text: '热销商品分析'
			},
			xAxis: {
				categories: [

				<c:forEach items="${list}" var="resultVar" varStatus="listStatus">
		            '<a href="reportHotProducts?1=1">${resultVar.PRODUCT_NAME}</a>',
				</c:forEach>

				],
				title: {
					text: null
				}
			},
			yAxis: {
				min: 0,
				title: {
					text: '月销量(件)',
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
				name: '热销商品分析',
				type: 'pie', //饼图
				data: [
				<c:forEach items="${list }" var="resultVar" varStatus="listStatus">
		            {name:'${resultVar.PRODUCT_NAME}',y:${resultVar.PRODUCT_NUM }},
				</c:forEach>
				]
			}]
		});
		
		
	});
</script>
</c:otherwise>
</c:choose>