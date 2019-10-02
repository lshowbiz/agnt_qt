<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<!--<title><fmt:message key="jbdPeriodList.title"/></title>-->
<!-- 新旧期别对比(结算周期表) -->
<meta name="menu" content="JbdPeriodMenu"/>
<div class="cont">
   	<div class="bt mt">
		<h3 class="color2 ml"><ng:locale  key="doComparisonOfOldAndNew"/></h3>
	</div>
 <div class="mt">
<table id="jbdPeriodListT" class="prod mt">
    <tbody class="list_tbody_header">
         <tr>
		       <td><ng:locale key="bdPeriod.fyear"/></td>
		       <td><ng:locale key="bdBounsDeduct.fmonth"/></td>
		       <td>财政旬</td>
		       <td><ng:locale key="bdBounsDeduct.wweek"/></td>
		       <td><ng:locale key="bdPeriod.startTime"/></td>
		       <td><ng:locale key="sysDataLog.endOperatorTime"/></td>
		       <td><ng:locale key="bdPeriod.bonusSend"/></td>
		       <td><ng:locale key="bdPeriod.monthStatus"/></td>
		     <%--   <td><ng:locale key="bdBounsDeduct.wweek"/></td>
		       <td><ng:locale key="bdPeriod.wmonth"/></td> --%>
         </tr>
     </tbody>
     <tbody class="list_tbody_row">
     <c:forEach items="${jbdPeriodList}" var="jbdPeriod">
          <tr class="bg-c gry3">
             <c:if test="${empty jbdPeriod.bonusSend || jbdPeriod.bonusSend != 0 }">
		         <c:set var="color" value="red"/>
	         </c:if>
	         <c:if test="${jbdPeriod.bonusSend == 0 }">
		         <c:set var="color" value=""/>
	         </c:if>
	         <td style="color: ${color }">${jbdPeriod.fyear }</td>
             <td style="color: ${color }">${jbdPeriod.fmonth }</td>
             <td style="color: ${color }">
             
				<c:choose>
				
				
				
             	<c:when test="${(jbdPeriod.fyear*100+jbdPeriod.fweek)>=201544  &&  (jbdPeriod.fyear*100+jbdPeriod.fweek)< 201905 }">
             
	             	--
	             	
             	</c:when>
				
             	<c:when test="${ (jbdPeriod.fyear*100+jbdPeriod.fweek)>=201905 }">
             
             	<fmt:formatDate var="curDates" value="${jbdPeriod.startTime }" pattern="dd"/>
             	
             	
             			<c:choose>
		             	<c:when test="${curDates==01 }">
		             		上旬
		             	</c:when>
		             	<c:when test="${curDates==11 }">
		             		中旬
		             	</c:when>
		             	<c:otherwise>
		             		下旬
		             	</c:otherwise>
		             	
						</c:choose>
             	
             	
             	</c:when>
             	
             	
             	
             	<c:otherwise>
             	--
             	</c:otherwise>
             	
				</c:choose>
             </td>
             <td style="color: ${color }"> 
                 <c:set var="tmpWeek">${jbdPeriod.fweek}</c:set>
			         <c:if test="${fn:length(tmpWeek)==2}">${jbdPeriod.fyear}${jbdPeriod.fweek}</c:if>
			         <c:if test="${fn:length(tmpWeek)==1}">${jbdPeriod.fyear}0${jbdPeriod.fweek}</c:if>
             </td>
             <td style="color: ${color }"><fmt:formatDate value="${jbdPeriod.startTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
             <td style="color: ${color }"><fmt:formatDate value="${jbdPeriod.endTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
             <td style="color: ${color }"><ng:code listCode="bdperiod.bonussend" value="${jbdPeriod.bonusSend}"/></td>
             <td style="color: ${color }"><ng:code listCode="yesno" value="${jbdPeriod.monthStatus}"/></td>
            <%--  <td style="color: ${color }">
                 <c:set var="tmpWeek">${jbdPeriod.wweek}</c:set>
					<c:if test="${fn:length(tmpWeek)==2}">${jbdPeriod.wyear}${jbdPeriod.wweek}</c:if>
					<c:if test="${fn:length(tmpWeek)==1}">${jbdPeriod.wyear}0${jbdPeriod.wweek}</c:if>
             </td>
             <td style="color: ${color }">${jbdPeriod.wmonth }</td> --%>
        </tr> 
     </c:forEach>
     </tbody>
</table>
</div>
</div>