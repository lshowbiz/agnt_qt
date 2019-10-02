<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>

<div class="cont mt mr">	
	<div class="bt mt">
		<h3 class="color2 ml">旅游积分</h3>
	</div>
	
	<table class="prod mt" border="1" style="border-collapse:collapse; border:1px solid #ebebeb;">
	
	 <tbody border="1" style="border-collapse:collapse; border:1px solid #f5f5f5; border-top:1px solid #ebebeb;  border-top:1px solid #ebebeb; border-bottom:1px solid #ebebeb; border-left:1px solid #ebebeb; line-height:36px">
        <tr>
            <td>活动考核期    </td>
            <td>活动考核期间内最高奖衔  </td>
            <td><ng:locale key="fiCoinLog.coin" /> </td>
        </tr>
    </tbody>
	<tbody border="1" style="border-collapse:collapse; border:1px solid #ebebeb;">
		<c:forEach items="${jbdTravelPointAllList}" var="jbdTravelPointAll" >
		<tr class="bg-c gry3">
                <td>${jbdTravelPointAll.id.FYear }</td>
                <td><ng:code listCode="pass.star.zero" value="${jbdTravelPointAll.id.passStar }"/></td>
                <td>${jbdTravelPointAll.id.total }</td>
        </tr>
		</c:forEach> 
	</tbody>
	</table>
</div>

