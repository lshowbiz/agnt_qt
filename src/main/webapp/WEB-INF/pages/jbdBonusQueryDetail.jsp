<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType = "text/html; charset=utf-8" language = "java"%>



<head>
    <!--<title>团队管理 - 奖金查询详细</title>-->
    <link rel="stylesheet" href="css/style.css" />
</head>
<!--  
   <div id="titleBar" class="searchBar">&nbsp;&nbsp;
		<input type="button" class="button" name="cancel"  onclick="history.back()" value="<ng:locale key="operation.button.return"/>" />
	</div>
-->

<div class="cont">	
		<div class="bt mt">
			<h3 class="color2 ml"><ng:locale  key="member.base.info"/></h3>
		</div>
	
	<table width="100%" class="personalInfoTab mgtb10">
		<tbody>
		　　<tr class="stripe">
				<td width="15%" class="tr"><ng:locale  key="bdBounsDeduct.wweek"/>：</td>
				<td width="15%"> 
				 <ng:weekFormat week="${jbdMemberLinkCalcHist.wweek }" weekType="w" />
				</td>
				<td width="15%" class="tr"> <ng:locale  key="month.bouns"/>：</td>
				<td width="15%">
					<c:if test="${jbdMemberLinkCalcHist.status=='1'}">
					   <ng:locale  key="yesno.yes"/>
					</c:if>
					<c:if test="${jbdMemberLinkCalcHist.status!='1'}">
					   <ng:locale  key="yesno.no"/>
					</c:if>  
				</td>
				<td width="15%" class="tr"><ng:locale  key="is.active"/>：</td>
				<td width="15%">
					<c:if test="${jbdMemberLinkCalcHist.active=='1'}">
					   <ng:locale  key="yesno.yes"/>
					</c:if>
					<c:if test="${jbdMemberLinkCalcHist.active=='0'}">
					   <ng:locale  key="yesno.no"/>
					</c:if>
				</td>
　　　　　　　</tr>

			<tr>
				<td class="tr"><ng:locale  key="bdNetWorkCostReport.memberCH"/>：</td>
				<td>${jbdMemberLinkCalcHist.userCode }</td>
				<td class="tr"> <ng:locale  key="miMember.petName"/>：</td>
				<td>${jbdMemberLinkCalcHist.petName }</td>
				<td class="tr"><ng:locale  key="miMember.isstore"/>：</td>
				<td> <ng:code listCode="isstore" value="${jbdMemberLinkCalcHist.isstore}"/></td>
			</tr>
			
			<tr class="stripe"s>
				<td class="tr"> <ng:locale  key="bdCalculatingSubDetail.cardType"/>：</td>
				<td> <ng:code listCode="bd.cardtype" value="${jbdMemberLinkCalcHist.cardType}"/></td>
				<td class="tr"> <ng:locale  key="jbdMemberLinkCalcHist.honorStar"/>：</td>
				<td><ng:code listCode="honor.star.zero" value="${jbdMemberLinkCalcHist.honorStar}"/></td>
				<td class="tr"><ng:locale  key="jbdMemberLinkCalcHist.passStar"/>：</td>
				<td><ng:code listCode="pass.star.zero" value="${jbdMemberLinkCalcHist.passStar}"/></td>
			</tr>
			
			<tr>
				<td class="tr"><ng:locale  key="jbdMemberLinkCalcHist.monthConsumerPv"/>：</td>
				<td>${jbdMemberLinkCalcHist.monthConsumerPv} </td>
				<td class="tr"><ng:locale  key="jbdMemberLinkCalcHist.monthAreaTotalPv"/>：</td>
				<td>${jbdMemberLinkCalcHist.monthAreaTotalPv}</td>
				<td class="tr"> <ng:locale  key="jbdMemberLinkCalcHist.areaConsumption"/>：</td>
				<td>  ${jbdMemberLinkCalcHist.areaConsumption}</td>
			</tr>
			
			<tr class="stripe">
				<td class="tr"><ng:locale  key="bdNetWorkCostReport.rateCH"/>：</td>
				<td>${jbdMemberLinkCalcHist.exchangeRate} </td>
				<td class="tr"><ng:locale  key="jbdMemberLinkCalcHist.monthMaxPv"/>：</td>
				<td>${jbdMemberLinkCalcHist.monthMaxPv} </td>
				<td>&nbsp;</td>
				<td>&nbsp;</td>
			</tr>
		</tbody>
	</table>
</div>	
<div class="cont2">	
		<div class="bt mt">
			<h3 class="color2 ml"><ng:locale  key="jbdMemberLinkCalcHist.pv"/></h3>
		</div>
	
	<table width="100%" class="personalInfoTab tdTr mgtb10">
		<colgroup style="width:240px;"></colgroup>
		<colgroup></colgroup>
		<colgroup></colgroup>
		<colgroup style="width:240px;"></colgroup>
		<tbody>
			
			<tr class="stripe">
				<td width="20%"><ng:locale  key="jbdMemberLinkCalcHist.ventureSalesPv.pv"/>：</td>
				<td width="15%">  
					<c:choose>
						<c:when test="${empty jbdMemberLinkCalcHist.ventureSalesPv}">
							0.00    	</c:when>
						<c:otherwise>
							${jbdMemberLinkCalcHist.ventureSalesPv} 
						</c:otherwise>
					</c:choose>
				</td>
				<td width="15%">
					<c:choose>
						<c:when test="${empty jbdMemberLinkCalcHist.ventureSalesPv}">
								0.00    	
						</c:when>
						<c:otherwise>
							<fmt:formatNumber value="${jbdMemberLinkCalcHist.ventureSalesPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/> 
						</c:otherwise>
					</c:choose>
				</td>
				<td>
				
	<security:authorize url="/app/jbdBonusEnquiryDetails"> 
					<a href="jbdBonusEnquiryDetails.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=ventureSalesPv"><ng:locale key="menu.editJbdMemberLinkCalcHis"/></a>
			</security:authorize>
				</td>
	　　　　</tr>
	<%-- 	<tr class="stripe">
				<td><ng:locale  key="jbdMemberLinkCalcHist.ventureFund.pv"/>：</td>
				<td><c:choose>
				<c:when test="${empty jbdMemberLinkCalcHist.ventureFund}">
					0.00
				</c:when>
				<c:otherwise>
					${jbdMemberLinkCalcHist.ventureFund}    	
				</c:otherwise>
				</c:choose></td>
				<td><c:choose>
				<c:when test="${empty jbdMemberLinkCalcHist.ventureFund}">
					0.00
				</c:when>
				<c:otherwise>
							<fmt:formatNumber value="${jbdMemberLinkCalcHist.ventureFund*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
				</c:otherwise>
				</c:choose></td>
				<td>
			
				   <security:authorize url="/app/jbdBonusEnquiryDetails">
						   <!-- 这里缺少权限设定的标语 -->
					   <a href="jbdBonusEnquiryDetails.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=ventureFund"><ng:locale key="menu.editJbdMemberLinkCalcHis"/></a>
					</security:authorize>  
				</td>
			</tr> --%>

			<tr>
				<td><ng:locale  key="jbdMemberLinkCalcHist.ventureLeaderPv37.pv"/>：</td>
				<td>
					<c:choose>
						<c:when test="${empty jbdMemberLinkCalcHist.ventureLeaderPv}">
							0.00
						</c:when>
						<c:otherwise>
							${jbdMemberLinkCalcHist.ventureLeaderPv}    	</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${empty jbdMemberLinkCalcHist.ventureLeaderPv}">
							0.00
						</c:when>
						<c:otherwise>
							<fmt:formatNumber value="${jbdMemberLinkCalcHist.ventureLeaderPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>  	
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<security:authorize url="/app/jbdBonusEnquiryDetails">
						   <!-- 这里缺少权限设定的标语 -->
					 <a href="jbdBonusEnquiryDetails.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=ventureLeaderPv">
					<ng:locale key="menu.editJbdMemberLinkCalcHis"/></a>
					</security:authorize>
			   
				</td>
			</tr>
			<tr class="stripe">
				<td> <ng:locale  key="jbdMemberLinkCalcHist.recommendBonusPv.pv"/>：</td>
				<td>
					<c:choose>
						<c:when test="${empty jbdMemberLinkCalcHist.recommendBonusPv}">
							0.00
						</c:when>
						<c:otherwise>
							${jbdMemberLinkCalcHist.recommendBonusPv} 
						</c:otherwise>
					</c:choose>
				</td>
				<td>   
					<c:choose>
						<c:when test="${empty jbdMemberLinkCalcHist.recommendBonusPv}">
							0.00
						</c:when>
						<c:otherwise>
							<fmt:formatNumber value="${jbdMemberLinkCalcHist.recommendBonusPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<security:authorize url="/app/jbdBonusEnquiryDetails">
					  <!-- 这里缺少权限设定的标语 -->
						<a href="jbdBonusEnquiryDetails.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=recommendBonusPv">
						<ng:locale key="menu.editJbdMemberLinkCalcHis"/>  </a>
					</security:authorize>
				</td>
			</tr>
			<tr>
				<td><ng:locale  key="jbdMemberLinkCalcHist.storeExpandPv.pv"/>：</td>
				<td>
					<c:choose>
						<c:when test="${empty jbdMemberLinkCalcHist.storeExpandPv}">
							0.00
						</c:when>
						<c:otherwise>
							${jbdMemberLinkCalcHist.storeExpandPv} 
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${empty jbdMemberLinkCalcHist.storeExpandPv}">
							0.00
						</c:when>
						<c:otherwise>
							<fmt:formatNumber value="${jbdMemberLinkCalcHist.storeExpandPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
						</c:otherwise>
					</c:choose>
				</td>
				<td>
				   <security:authorize url="/app/jbdBonusEnquiryDetails">
					  <!-- 这里缺少权限设定的标语 -->
						<a href="jbdBonusEnquiryDetails.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=storeExpandPv">
						<ng:locale key="menu.editJbdMemberLinkCalcHis"/>  </a>
					</security:authorize>
				</td>
			</tr>
			  <%-- <tr>
					<td><ng:locale  key="jbdMemberLinkCalcHist.storeServePv.pv"/>：</td>
					<td><c:choose>
			<c:when test="${empty jbdMemberLinkCalcHist.storeServePv}">
				0.00
			</c:when>
			<c:otherwise>
				${jbdMemberLinkCalcHist.storeServePv} 
			</c:otherwise>
		</c:choose></td>
			<td>  <c:choose>
			<c:when test="${empty jbdMemberLinkCalcHist.storeServePv}">
				0.00
			</c:when>
			<c:otherwise>
				<fmt:formatNumber value="${jbdMemberLinkCalcHist.storeServePv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
			</c:otherwise>
		</c:choose></td>
					<td>
						<security:authorize url="/app/jbdBonusEnquiryDetails">
						  <!-- 这里缺少权限设定的标语 -->
							<a href="jbdBonusEnquiryDetails.html?userCode=${jbdSellCalcSubDetailHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=storeServePv">
							<ng:locale key="menu.editJbdMemberLinkCalcHis"/>  </a>
						</security:authorize>
					</td>
			  </tr> --%>
			<tr class="stripe">
				<td> <ng:locale  key="jbdMemberLinkCalcHist.storeRecommendPv.pv"/>：</td>
				<td>
					<c:choose>
						<c:when test="${empty jbdMemberLinkCalcHist.storeRecommendPv}">
							0.00
						</c:when>
						<c:otherwise>
							${jbdMemberLinkCalcHist.storeRecommendPv} 
						</c:otherwise>
					</c:choose>
				</td>
				<td> 
					<c:choose>
						<c:when test="${empty jbdMemberLinkCalcHist.storeRecommendPv}">
							0.00
						</c:when>
						<c:otherwise>
							 <fmt:formatNumber value="${jbdMemberLinkCalcHist.storeRecommendPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					  <security:authorize url="/app/jbdBonusEnquiryDetails">
						   <!-- 这里缺少权限设定的标语 -->
						   <a href="jbdBonusEnquiryDetails.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=storeRecommendPv">
						   <ng:locale key="menu.editJbdMemberLinkCalcHis"/>  </a>
					  </security:authorize>
				</td>
			</tr>
			<tr>
				<td><b> <ng:locale  key="bd.send.bounspv"/></b>：</td>
				<td><b>${jbdMemberLinkCalcHist.bounsPv}</b></td>
				<td>
					<security:authorize url="/app/jbdBonusEnquiryDetails">
						   <!-- 这里缺少权限设定的标语 -->
						   <b><fmt:formatNumber value="${jbdMemberLinkCalcHist.bounsMoney}" type="number" pattern="###,###,##0.00"/></b>
					  </security:authorize>
				</td>
				<td>&nbsp;</td>
			</tr>
		</tbody>
	</table>
</div>	
<div class="cont2">	
		<div class="bt mt">
			<h3 class="color2 ml"><ng:locale  key="msgclassno.10"/></h3>
		</div>

	<table width="100%" class="personalInfoTab tdTr mgtb10">
		<colgroup style="width:240px;"></colgroup>
		<colgroup></colgroup>
		<colgroup></colgroup>
		<colgroup style="width:240px;"></colgroup>
		<tbody>
		　　<tr class="stripe">
				<td width="20%"><ng:locale  key="franchise.moeny"/>：</td>
				<td width="15%"><fmt:formatNumber value="${jbdMemberLinkCalcHist.franchiseMoney}" type="number" pattern="###,###,##0.00"/></td>
				<td width="15%">&nbsp;</td>
				<td>
					<a href="jbdBonusEnquiryDetails.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=franchiseMoney">
				   <ng:locale key="menu.editJbdMemberLinkCalcHis"/></a>
				</td>
			</tr>
			
			<tr>
				<td><ng:locale  key="consumer.amount"/>：</td>
				<td><fmt:formatNumber value="${jbdMemberLinkCalcHist.consumerAmount}" type="number" pattern="###,###,##0.00"/></td>
				<td></td>
				<td>
					<a href="jbdBonusEnquiryDetails.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=consumerAmount">
				   <ng:locale key="menu.editJbdMemberLinkCalcHis"/></a>
				</td>
			</tr>
			
			<tr class="stripe">
				<td><ng:locale  key="jbdMemberLinkCalcHist.deductMoney"/>：</td>
				<td><fmt:formatNumber value="${jbdMemberLinkCalcHist.deductMoney}" type="number" pattern="###,###,##0.00"/></td>
				<td></td>
				<td>
					<%--<ng:power powerCode="spWelfareSubPower">
						<a href="spBounsDetail.html?strAction=spWelfareSubPower&userCode=${spBonusCalcHist.userCode }&wweek=${spBonusCalcHist.wweek }">查看明细</a>
					</ng:power>--%>
				</td>
			</tr>
			
			<tr>
				<td><ng:locale  key="jbdMemberLinkCalcHist.deductTax"/>：</td>
				<td><fmt:formatNumber value="${jbdMemberLinkCalcHist.deductTax}" type="number" pattern="###,###,##0.00"/></td>
				<td></td>
				<td><%--<a href="#">查看明细</a>--%> </td>
			</tr>
			
			<c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.companyCode=='CN'}">  
			<tr class="stripe">
				<td><ng:locale  key="bdSendRecord.networkMoney"/>：</td>
				<td><fmt:formatNumber value="${jbdMemberLinkCalcHist.networkMoney}" type="number" pattern="###,###,##0.00"/></td>
				<td></td>
				<td><%-- <a href="#">查看明细</a> --%> </td>
			</tr>
			</c:if>
			
			<c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.companyCode=='CN'}">  
			<tr>
				<td><ng:locale  key="bd.currentDev.bd"/>：</td>
				<td><fmt:formatNumber value="${jbdMemberLinkCalcHist.currentDev}" type="number" pattern="###,###,##0.00"/></td>
				<td></td>
				<td></td>
			</tr>
			</c:if>
		</tbody>
		
	</table>
</div>

	<security:authorize url="/app/jbdBonusEnquiryDetails">   
<div class="cont2">		
			<div class="bt mt">
				<h3 class="color2 ml"><ng:locale  key="bdSendRecord.bonusMoney"/></h3>
			</div>	

	<table width="100%" class="personalInfoTab tdTr mgtb10">
		<colgroup style="width:240px;"></colgroup>
		<colgroup></colgroup>
		<colgroup></colgroup>
		<colgroup style="width:240px;"></colgroup>
		<tbody>
		　　<tr class="stripe">
				<td width="20%"><ng:locale  key="bdSendRecord.bonusMoney"/>：</td>
				<td width="15%"><b><fmt:formatNumber value="${jbdMemberLinkCalcHist.sendMoney}" type="number" pattern="###,###,##0.00"/></b></td>
				<td width="15%"></td>
				<td>&nbsp;</td>
			</tr>
		</tbody>
	</table>
	</div>
	</security:authorize>

<div class="tc"> <button type="button" class="btn btn-success" onclick="history.go(-1)" >&nbsp;<span>返&nbsp;回</span>&nbsp;</button>	</div> 
    
</div>




















