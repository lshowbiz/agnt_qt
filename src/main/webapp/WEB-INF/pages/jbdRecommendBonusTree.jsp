<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>

<link rel="stylesheet" href="styles/tree_grid.css" />
<style>
	.content { overflow-x:auto;}
    .tabQueryLs th,.tabQueryLs td { line-height:0;padding:0 4px;}
	.tabQueryLs td { text-align:left;}
</style>
<script src="scripts/jQTreeTable/jquery1131.js"></script>
<script src="scripts/jQTreeTable/jQTreeTable.js"></script>
 
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
<div class="cont">	
	<div class="bt mt">
		<h3 class="color2 ml">销售组织业绩</h3>
	</div>
        <form method="get" action="jbdRecommendBonusTreeSelect" target="blank" onsubmit="return validateOthers(this)" id="bdPeriodForm">
			<table class="search_table mt"  >
                    <tr>
                        <td><ng:locale key="bdBounsDeduct.wweek" /><ng:locale key="label.example"/></td>
                        <td>
                        <input type="text" name="formatedWeek" id="formatedWeek" size="8" value="${not empty param.formatedWeek ? param.formatedWeek:curBdPeriod }"/>

                        </td>
                        <td><ng:locale key="miMember.memberNo" />：</td>
                        <td><input type="text" name="userCode" value="${not empty param.userCode ?param.userCode:sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userCode }" size="10"/></td>
                        <td>
                            <button id="search" name="submit" type="submit" ><ng:locale key="operation.button.search"/></button>
                            <input type="hidden" name="strAction" value="bdRecommendBonusTreeQuery"/>
                        </td>
                    </tr>
            </table>
        </form>
    </div>

  <%--<p>
    <ng:locale key="bdBonus.tree.organize"/>:
    <a href="jbdRecommendBonusTree?strAction=bdRecommendBonusTreeQuery&formatedWeek=${param.formatedWeek }&userCode=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userCode }">
    ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userName }
    </a>
    <c:if test="${not empty upRecommendList}">
    <c:forEach items="${upRecommendList}" var="upRecommendRefVar">
    / <a href="jbdRecommendBonusTree?strAction=bdRecommendBonusTreeQuery&formatedWeek=${param.formatedWeek }&userCode=${upRecommendRefVar.user_code }">
    ${upRecommendRefVar.pet_name  }
    </a>
    </c:forEach>

    </c:if>
  </p>
  <p>
    <ng:locale key="bdBonus.tree.tips"/>
    <a href="#" style="padding:0 5px;"><img src="images/bonus_tree/next_5.png" border="0" align="absmiddle"/></a>
  </p>

    <table id="tablemain" width="160%" border="1" class="tabQueryLs">
        <thead>
            <tr>
                <th><ng:locale key="bdNetWorkCostReport.memberCH"/></th>
                <th><ng:locale key="bdSendRecord.cardType"/></th>
                <th><ng:locale key="bdMemberBounsCalcSell.level"/></th>
                <th><ng:locale key="miMember.recommendCount"/></th>
                <th><ng:locale key="bdday.pending_recommend_pv1"/></th>
                <th><ng:locale key="bdday.pending_recommend_pv2"/></th>
                <th><ng:locale key="bdday.pending_pv"/></th>
                <th><ng:locale key="jbdMemberLinkCalcHist.passStar"/></th>
                <c:if test="${param.formatedWeek<201037}">
                <th><ng:locale key="jbdMemberLinkCalcHist.passGrade"/></th>
                </c:if>
                <th><ng:locale key="bdBonus.double_pass_star"/></th>
                <th><ng:locale key="bdBonus.month_double_area_pv"/></th>
                <th><ng:locale key="customerRecord.type"/></th>
                <th><ng:locale key="bdday.pending_recommend_pv"/></th>
                <th>&nbsp;</th>
            </tr>
        </thead>
        <tbody id="treetable">
            <c:forEach items="${jbdDayBonusList}" var="jbdDayBonusVar" varStatus="jbdDayBonusVarStatus">
            <tr>
                <td style="text-align:left;">${jbdDayBonusVar.user_code } - ${jbdDayBonusVar.pet_name }</td>
                <td><ng:code listCode="bd.cardtype" value="${jbdDayBonusVar.card_type }"/></td>
                <td>${jbdDayBonusVar.level}</td>
                <td><img src="images/bonus_tree/tree_star.gif" border="0"/> ${jbdDayBonusVar.recommend_num }</td>
                <td><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${jbdDayBonusVar.month_recommend_pv }</fmt:formatNumber></td>
                <td><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${jbdDayBonusVar.month_consumer_pv1 }</fmt:formatNumber></td>
                <td><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${jbdDayBonusVar.pending_pv }</fmt:formatNumber></td>
                <td><ng:code listCode="pass.star" value="${jbdDayBonusVar.pass_star}"/></td>
                <c:if test="${jbdDayBonusVar.w_week<201037}">
                <td><ng:code listCode="pass.grade" value="${jbdDayBonusVar.pass_grade}"/></td>
                </c:if>
                <td><ng:code listCode="pass.star" value="${jbdDayBonusVar.double_pass_star }"/></td>
                <td><img src="images/bonus_tree/tree_person_pv.gif" border="0"/> <fmt:formatNumber pattern="###,##0.00">${jbdDayBonusVar.month_double_area_pv }</fmt:formatNumber></td>
                <td><ng:code listCode="isstore" value="${jbdDayBonusVar.isstore}"/></td>
                <td><img src="images/bonus_tree/tree_person_pv.gif" border="0"/><fmt:formatNumber pattern="###,##0.00">${jbdDayBonusVar.pending_recommend_pv }</fmt:formatNumber></td>
                <td><a href="jbdRecommendBonusTree?strAction=bdRecommendBonusTreeQuery&formatedWeek=${param.formatedWeek }&userCode=${jbdDayBonusVar.user_code }"><img src="images/bonus_tree/next_5.png" border="0"/></a></td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
	
--%>

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