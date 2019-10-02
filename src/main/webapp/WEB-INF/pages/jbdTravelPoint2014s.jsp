<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>


<h2 class="title mgb20">旅游积分2014</h2>

<table width="100%" class="personalInfoTab">
	<colgroup style="width:200px;"></colgroup>
	<colgroup></colgroup>
	<tbody>
		<tr>
			<td class="tr">活动考核期间内最高奖衔：</td>
			<td><ng:code listCode="pass.star.zero" value="${jbdTravelPoint2014.passStar }"/></td>
		</tr>
		<tr>
			<td class="tr"><ng:locale key="fiCoinLog.coin" />：</td>
			<td>${jbdTravelPoint2014.total }</td>
		</tr>
	</tbody>
</table>
