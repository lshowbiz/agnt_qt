<!DOCTYPE html>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>

<html lang="en">
<head>
    <title><ng:locale key="webapp.name.qt" /></title>
    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=9"/>
    <link rel="icon" href="<c:url value="/images/favicon.ico"/>" />
    
	<script type="text/javascript" src="scripts/jQTreeTable/jquery1131.js"></script>
    <link rel="stylesheet" href="<c:url value='/styles/style-ng.css'/>" />
    <script src="<c:url value='/scripts/lib/plugins/jquery.cookie.js'/>"></script>
    <script src="<c:url value='/scripts/script.js'/>"></script>
	
	<script src="scripts/jQTreeTable/jQTreeTable.js"></script>
	<link href="<c:url value='/styles/index/style.css'/>" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="<c:url value='/styles/lib/bootstrap.min.css'/>" />

<style>
	#treetable .collapsed { 
		display: none !important; 
	}
</style>
<c:if test="${not empty jbdDayBonusList }">
<script>




$(function(){
    var map = [
		<c:forEach items="${jbdDayBonusList}" var="jbdDayBonusVar" varStatus="jbdDayBonusVarStatus">
			<c:if test="${jbdDayBonusVarStatus.index>0}">,</c:if>${jbdDayBonusVar.parentId}
		</c:forEach>
	];
    var options = {
    	    openImg: "images/jQTreeTable/tv-collapsable.gif", 
    	    shutImg: "images/jQTreeTable/tv-expandable.gif", 
    	    leafImg: "images/jQTreeTable/tv-item.gif", 
    	    lastOpenImg: "images/jQTreeTable/tv-collapsable-last.gif", 
    	    lastShutImg: "images/jQTreeTable/tv-expandable-last.gif", 
    	    lastLeafImg: "images/jQTreeTable/tv-item-last.gif", 
    	    vertLineImg: "images/jQTreeTable/vertline.gif", 
    	    blankImg: "images/jQTreeTable/blank.gif", 
    	    collapse: true, 
    	    column: 0, 
    	    striped: true, 
    	    highlight: true, 
    	    onselect: function(target){window.status = "You clicked "+target.html();}
    };
    $("#treetable").jqTreeTable(map, options);
});
</script>
</c:if>


<div class="cont2">
    	<div class="bt mt">
			<h3 class="color2 ml">服务组织业绩</h3>
		</div>
    <form method="get" action="jbdBonusTreeSelect" onsubmit="return validateOthers(this)" id="bdPeriodForm">
            <table class="search_table mt">
                <tbody>
                    <tr>
                        <td width="60px"><ng:locale key="bdBounsDeduct.wweek" />：</td>
                        <td width="320px"><input type="text" name="formatedWeek" id="formatedWeek" size="8" value="${not empty param.formatedWeek ? param.formatedWeek:curBdPeriod }"/> <ng:locale key="label.example"/>200806</td>
                        <td width="80px"><ng:locale key="miMember.memberNo" />：</td>
                        <td width="200px"><input type="text" name="userCode" value="${not empty param.userCode ?param.userCode:sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userCode }" size="10"/></td>
                        <td>
                            <button id="search" name="submit" type="submit" ><ng:locale key="operation.button.search"/></button>
                            <input type="hidden" name="strAction" value="bdBonusTreeQuery"/>
                        </td>
                    </tr>
                </tbody>
            </table>
    </form>

<p>
    服务网络:
    <a href="jbdBonusTreeSelect?strAction=bdBonusTreeQuery&formatedWeek=${param.formatedWeek }&userCode=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userCode }">
    ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userName }
    </a>
    <c:if test="${not empty upLinkList}">
    <c:forEach items="${upLinkList}" var="upMiLinkRefVar">
    / <a href="jbdBonusTreeSelect.html?strAction=bdBonusTreeQuery&formatedWeek=${param.formatedWeek }&userCode=${upMiLinkRefVar.user_code }">
    ${upMiLinkRefVar.pet_name  }
    </a>
    </c:forEach>

    </c:if>
</p>
<p>
    <ng:locale key="bdBonus.tree.tips"/>
    <a href="#" style="padding:0 5px;"><img src="images/bonus_tree/next_5.png" border="0" align="absmiddle"/></a>
</p>

<div class="mt">
	<table class="prod mt" id="tablemain" >
		<tbody class="list_tbody_header">
            <tr>
				<td><ng:locale key="bdNetWorkCostReport.memberCH"/></td>
				<td><ng:locale key="bdCalculatingSubDetail.cardType"/></td>
				<td><ng:locale key="bdMemberBounsCalcSell.level"/></td>
				<td><ng:locale key="miLinkRef.total"/></td>
				<td><ng:locale key="bdBonus.tree.personPv"/></td>
				<td><ng:locale key="bdBonus.personFirstPv"/></td>
				<td><ng:locale key="bdday.pending_recommend_pv2"/></td>
				<%-- <td><ng:locale key="bdBonus.tree.groupPv"/></td> --%>
				<td><ng:locale key="bdBonus.month_group_pv"/></td>
				<%-- <td><ng:locale key="bdday.pending_pv"/></td> --%>
				<td><ng:locale key="bd.month.area.total.pv"/></td>
				<%-- <td><ng:locale key="jbdMemberLinkCalcHist.areaConsumption"/></td> --%>
				<td><ng:locale key="jbdMemberLinkCalcHist.passStar"/></td>
				
				
				<c:if test="${doubleView=='1' }">
					<%-- <td><ng:locale key="bdBonus.double_pass_star"/></td>
					<td><ng:locale key="bdBonus.month_double_area_pv"/></td> --%>
					<td>双倍其余部门业绩</td>
					<td>政策双倍后冠军部门业绩</td>
				</c:if>
				
				
				
				<td><ng:locale key="miMember.store.type"/></td>
				<td>&nbsp;</td>
			</tr>
		</tbody> 
		<tbody class="list_tbody_row" id="treetable">
			<c:forEach items="${jbdDayBonusList}" var="jbdDayBonusVar" varStatus="jbdDayBonusVarStatus">
				<tr class="bg-c gry3">
					<td style="text-align:left;">${jbdDayBonusVar.user_code } - ${jbdDayBonusVar.pet_name }</td>
					<td><nobr><ng:code listCode="member.level" value="${jbdDayBonusVar.retain_level }"/></nobr></td>
					<td>${jbdDayBonusVar.level}</td>
					<td><img src="images/bonus_tree/tree_star.gif" border="0"/> ${jbdDayBonusVar.link_num }</td>
					<td><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${jbdDayBonusVar.month_consumer_pv+jbdDayBonusVar.first_pv }</fmt:formatNumber></td>
					<td><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${jbdDayBonusVar.first_pv }</fmt:formatNumber></td>
					<td><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${jbdDayBonusVar.month_consumer_pv }</fmt:formatNumber></td>
					<%-- <td><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${jbdDayBonusVar.week_group_pv }</fmt:formatNumber></td> --%>
					<td><nobr><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${jbdDayBonusVar.week_group_pv }</fmt:formatNumber></nobr></td>
					<%-- <td><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${jbdDayBonusVar.pending_pv }</fmt:formatNumber></td> --%>
					<td><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${jbdDayBonusVar.month_area_total_pv }</fmt:formatNumber></td>
					<%-- <td><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${jbdDayBonusVar.area_consumption }</fmt:formatNumber></td> --%>
					<td><ng:code listCode="pass.star" value="${jbdDayBonusVar.pass_star}"/></td>
					<%--<c:if test="${jbdDayBonusVar.w_week<201037}">
					<td><ng:code listCode="pass.grade" value="${jbdDayBonusVar.pass_grade}"/></td>
					</c:if>--%>
					<%--<td><ng:code listCode="pass.star" value="${jbdDayBonusVar.double_pass_star }"/></td>
					<td><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${jbdDayBonusVar.month_double_area_pv }</fmt:formatNumber></td>--%>
					
					
					<c:if test="${doubleView=='1' }">
						<%-- <td align="right"><ng:code listCode="pass.star" value="${jbdDayBonusVar.double_pass_star }"/></td>
						<td align="right"><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${jbdDayBonusVar.month_double_area_pv }</fmt:formatNumber></td> --%>
						<td align="right"><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${jbdDayBonusVar.month_double_area_pv }</fmt:formatNumber></td>
						<td align="right"><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${jbdDayBonusVar.month_double_max_pv }</fmt:formatNumber></td>
					</c:if>
					
					
					<td><ng:code listCode="isstore" value="${jbdDayBonusVar.isstore}"/></td>
					<td><a href="jbdBonusTreeSelect.html?strAction=bdBonusTreeQuery&formatedWeek=${param.formatedWeek }&userCode=${jbdDayBonusVar.user_code }"><img src="images/bonus_tree/next_5.png" border="0"/></a></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
</div>
<script>
    function validateOthers(theForm){
        if(theForm.formatedWeek.value=="" || theForm.formatedWeek.value.length!=6 || !isUnsignedInteger(theForm.formatedWeek.value)){
            alert("<ng:locale key="week.isError"/>");
            theForm.formatedWeek.focus();
            return false;
        }
        if(theForm.userCode.value==""){
            alert("<ng:locale key="bdBonus.tree.memberNo.required"/>");
            theForm.userCode.focus();
            return false;
        }
        return true;
    }
</script>