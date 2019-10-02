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
	
	<colgroup>
  	<col>
    <col>
    <col style="width:20%">
    <col>
    <col style="width:20%">
    <col>
  </colgroup>
		<tbody>
		　　<tr class="stripe">
				<td width="15%" class="tr"><ng:locale  key="bdBounsDeduct.wweek"/>：</td>
				<td width="15%"> 
				 <ng:weekFormat week="${jbdMemberLinkCalcHist.wweek }" weekType="w" /><!-- 期别 -->
				</td>
				<td width="15%" class="tr"><ng:locale  key="bdNetWorkCostReport.memberCH"/>：</td>
				<td width="15%">
					${jbdMemberLinkCalcHist.userCode }<!-- 会员编号 -->
				</td>
				<td width="15%" class="tr"><ng:locale  key="bdNetWorkCostReport.rateCH"/>：</td>
				<td width="15%">
					${jbdMemberLinkCalcHist.exchangeRate} <!-- 汇率 -->
				</td>
　　　　　　　</tr>

　			
			<%-- <tr>
				<td class="tr"><ng:locale  key="bdNetWorkCostReport.memberCH"/>：</td>
				<td>${jbdMemberLinkCalcHist.userCode }</td>
				<td class="tr"> <ng:locale  key="miMember.petName"/>：</td>
				<td>${jbdMemberLinkCalcHist.petName }</td>
				<td class="tr"><ng:locale  key="miMember.store.type"/>：</td>
				<td <ng:code listCode="isstore" value="${jbdMemberLinkCalcHist.isstore}"/>></td>
			</tr> --%>


			<tr>
				<td class="tr"><ng:locale  key="jbdMemberLinkCalcHist.honorStar"/>：</td>
				<td><ng:code listCode="honor.star.zero" value="${jbdMemberLinkCalcHist.honorStar}"/><!-- 荣誉奖衔 --></td>
				<td class="tr"><ng:locale  key="jbdMemberLinkCalcHist.passStar"/>：</td>
				<td><ng:code listCode="pass.star.zero" value="${jbdMemberLinkCalcHist.passStar}"/><!-- 合格奖衔 --></td>
				<td class="tr"><ng:locale  key="miMember.store.type"/>：</td>
				<td> <ng:code listCode="isstore" value="${jbdMemberLinkCalcHist.isstore}"/> <!-- 加盟店类型 --></td>
			</tr>
			
			
			
  <c:if test="${jbdMemberLinkCalcHist.wweek==201516 }">
  <tr class="stripe">
				<td class="tr"> <ng:locale  key="jbdMemberLinkCalcHist.monthOwnPv"/>：</td>
				<td> 
				${jbdMemberLinkCalcHist.monthOwnPv} <!-- 个人当前新增业绩 -->
				</td>
				<td class="tr"> <ng:locale  key="jbdMemberLinkCalcHist.histPv"/>：</td>
				<td>${jbdMemberLinkCalcHist.totalGroup-jbdMemberLinkCalcHist.weekGroupPv+jbdMemberLinkCalcHist.firstPv}<!-- 历史业绩 --></td>
				<td class="tr"><ng:locale  key="jbdMemberLinkCalcHist.cioType"/>:</td>
				<td><ng:code listCode="cio.type" value="${jbdMemberLinkCalcHist.cioType }"/><!-- 达成首席类型 --></td>
			</tr>
			
			
			
			<tr >
				<td class="tr"> <ng:locale  key="jbdMemberLinkCalcHist.monthConsumerPv1"/>：</td>
				<td> 
				${jbdMemberLinkCalcHist.monthConsumerPv} <!-- 个人当月重消业绩 -->
				</td>
				<td class="tr"> <ng:locale  key="bdBonus.month_group_pv"/>：</td>
				<td>${jbdMemberLinkCalcHist.weekGroupPv}<!-- 当月新增业绩 --></td>
				<td class="tr"><ng:locale  key="bdBonus.zyType"/>：</td>
				<td><ng:code listCode="yesno" value="${jbdMemberLinkCalcHist.zyType }"/><!-- 是否达成卓越 --></td>
			</tr>
			
			
			<tr class="stripe">
				<td class="tr"> <ng:locale  key="jbdMemberLinkCalcHist.monthAreaTotalPv1"/>：</td>
				<td> 
				${jbdMemberLinkCalcHist.monthAreaTotalPv} <!-- 当月新增其余部门业绩 -->
				</td>
				<td class="tr"> <ng:locale  key="jbdMemberLinkCalcHist.totalGroup"/>：</td>
				<td>${jbdMemberLinkCalcHist.totalGroup}<!-- 累计业绩 --></td>
				<td class="tr"><ng:locale  key="jbdMemberLinkCalcHist.retainLevel"/>：</td>
				<td><ng:code listCode="member.level" value="${jbdMemberLinkCalcHist.memberLevel}"/><!-- 结算级别 --></td>
			</tr>
			
			
				<tr >
				<td class="tr"><ng:locale  key="jbdMemberLinkCalcHist.monthMaxPv"/>：</td>
				<td>${jbdMemberLinkCalcHist.monthMaxPv}<!-- 当月冠军部门业绩 --></td>
				<td class="tr"><%-- <ng:locale  key="jbdMemberLinkCalcHist.retainGroup"/>： --%></td>
				<td><%-- ${jbdMemberLinkCalcHist.retainGroup} --%> <!-- 当月小组业绩 --></td>
				<td class="tr"> </td>
				<td></td>
			</tr>
			
  </c:if>
			
			
			
			
		
  <c:if test="${jbdMemberLinkCalcHist.wweek>201516 && jbdMemberLinkCalcHist.wweek<201532  }">
  <tr class="stripe">
				<td class="tr"> <ng:locale  key="jbdMemberLinkCalcHist.retainLevel"/>：</td>
				<td> 
				<ng:code listCode="member.level" value="${jbdMemberLinkCalcHist.memberLevel}"/>
				</td>
				<td class="tr"> 冠军部门历史业绩：</td>
				<td>${jbdLevel.lastmaxpart_pv}</td>
				<td class="tr">其余部门历史业绩:</td>
				<td>${jbdLevel.lastarea_pv}</td>
			</tr>
			
			
			
			<tr >
				<td class="tr">个人当月新增业绩：</td>
				<td> 
				${jbdMemberLinkCalcHist.monthOwnPv}
				</td>
				<td class="tr"><nobr> 当月新增冠军部门业绩：</nobr></td> 
				<td>${jbdMemberLinkCalcHist.monthMaxPv}</td>
				<td class="tr">当月新增其余部门业绩：</td>
				<td>${jbdMemberLinkCalcHist.monthAreaTotalPv}</td>
			</tr>
			
			
			<tr class="stripe">
				<td class="tr"> 个人当月重消业绩：</td>
				<td> 
				${jbdMemberLinkCalcHist.monthConsumerPv} <!-- 当月新增其余部门业绩 -->
				</td>
				<td class="tr">冠军部门累计业绩：</td>
				<td>${jbdLevel.maxpart_pv}</td>
				<td class="tr">其余部门累计业绩：</td>
				<td>${jbdLevel.area_pv}</td>
			</tr>
			
			
  </c:if>	
			
			<c:if test="${jbdMemberLinkCalcHist.wweek>=201532   }">
  <tr class="stripe">
				<td class="tr"> 销售级别：</td>
				<td> 
				<ng:code listCode="member.level" value="${jbdMemberLinkCalcHist.memberLevel}"/>
				</td>
				<td class="tr"> <ng:locale  key="miMember.gradeType"/>：</td>
				<td><ng:code listCode="grade.type" value="${jbdMemberLinkCalcHist.gradeType}"/></td>
				<td class="tr">个人当月重消业绩:</td>
				<td>${jbdMemberLinkCalcHist.monthConsumerPv}</td>
			</tr>
			
			
			
			<tr >
				<td class="tr">个人当月新增业绩：</td>
				<td> 
				${jbdMemberLinkCalcHist.monthOwnPv}
				</td>
				<td class="tr"> 当月新增冠军部门业绩：</td>
				<td>${jbdMemberLinkCalcHist.monthMaxPv}</td>
				<td class="tr">当月新增其余部门业绩：</td>
				<td>${jbdMemberLinkCalcHist.monthAreaTotalPv}</td>
			</tr>
			
		
			
			
  </c:if>	
			
			
			
			
			
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
			
			<%-- <tr class="stripe">
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
					<a href="jbdBonusEnquiryDetails.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=ventureSalesPv"><ng:locale key="menu.editJbdMemberLinkCalcHis"/></a>
				</td>
	　　　　</tr> --%>
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

			<tr class="stripe">
				<td><ng:locale  key="jbdMemberLinkCalcHist.ventureLeaderPv37.pv.jicha"/>：</td>
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
			<!-- 代数奖 -->
			<tr>
				<td><ng:locale  key="jbdMemberLinkCalcHist.successLeaderPv.pv.daishu"/>：</td>
				<td>
					<c:choose>
						<c:when test="${empty jbdMemberLinkCalcHist.successLeaderPv}">
							0.00
						</c:when>
						<c:otherwise>
							${jbdMemberLinkCalcHist.successLeaderPv}    	</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${empty jbdMemberLinkCalcHist.successLeaderPv}">
							0.00
						</c:when>
						<c:otherwise>
							<fmt:formatNumber value="${jbdMemberLinkCalcHist.successLeaderPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>  	
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<security:authorize url="/app/jbdBonusEnquiryDetails">
						   <!-- 这里缺少权限设定的标语 -->
					 <a href="jbdBonusEnquiryDetails.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=successLeaderPv">
					<ng:locale key="menu.editJbdMemberLinkCalcHis"/></a>
					</security:authorize>
			   
				</td>
			</tr>
			
			
			<!-- 卓越领导奖 -->
			<tr class="stripe">
				<td>
				
						  <c:choose>
    	<c:when test="${jbdMemberLinkCalcHist.wweek >=201532}">
    	     		
			
				卓越奖(PV)：
			
    	</c:when>
    	<c:otherwise>
			
				<ng:locale  key="jbdMemberLinkCalcHist.successSalesPv.pv.leaderPv"/>：
			</c:otherwise>
				</c:choose>
				
				
				</td>
				<td>
					<c:choose>
						<c:when test="${empty jbdMemberLinkCalcHist.successSalesPv}">
							0.00
						</c:when>
						<c:otherwise>
							${jbdMemberLinkCalcHist.successSalesPv}    	</c:otherwise>
					</c:choose>
				</td>
				<td>
					<c:choose>
						<c:when test="${empty jbdMemberLinkCalcHist.successSalesPv}">
							0.00
						</c:when>
						<c:otherwise>
							<fmt:formatNumber value="${jbdMemberLinkCalcHist.successSalesPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>  	
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					<security:authorize url="/app/jbdBonusEnquiryDetails">
						   <!-- 这里缺少权限设定的标语 -->
					 <a href="jbdBonusEnquiryDetails.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=successSalesPv">
					<ng:locale key="menu.editJbdMemberLinkCalcHis"/></a>
					</security:authorize>
			   
				</td>
			</tr>
			
			
			
			
			
			<!-- 推荐奖 -->
			 <tr >
				<td> 
				
			
     
		  <c:choose>
    	<c:when test="${jbdMemberLinkCalcHist.wweek ==201516}">
    	     		
			 <ng:locale  key="bdBonus.zyBonus"/>：
			
    	</c:when>
    	<c:when test="${jbdMemberLinkCalcHist.wweek >=201532}">
    	     		
			 推广奖(PV)：
			
    	</c:when>
    	<c:when test="${jbdMemberLinkCalcHist.wweek >201516}">
    		 新人奖(PV)：
			
    	</c:when>
    	<c:otherwise>
			
			</c:otherwise>
</c:choose>
     
				
				
				
				</td>
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
			<tr class="stripe">
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
			<tr >
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
			
			
			
			<tr class="stripe">
				<td> <ng:locale  key="franchise.moeny.pv"/>：</td>
				<td>
					<c:choose>
						<c:when test="${empty jbdMemberLinkCalcHist.franchisePv}">
							0.00
						</c:when>
						<c:otherwise>
							${jbdMemberLinkCalcHist.franchisePv} 
						</c:otherwise>
					</c:choose>
				</td>
				<td> 
					<c:choose>
						<c:when test="${empty jbdMemberLinkCalcHist.franchisePv}">
							0.00
						</c:when>
						<c:otherwise>
							 <fmt:formatNumber value="${jbdMemberLinkCalcHist.franchisePv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
						</c:otherwise>
					</c:choose>
				</td>
				<td>
					  <security:authorize url="/app/jbdBonusEnquiryDetails">
						   <a href="jbdBonusEnquiryDetails.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=franchiseMoney">
				   <ng:locale key="menu.editJbdMemberLinkCalcHis"/></a>
					  </security:authorize>
				</td>
			</tr>
			
			
			<c:if test="${jbdMemberLinkCalcHist.wweek>=201604 }">
			
			<tr >
				<td> 服务奖(PV)：</td>
				<td>
					 <fmt:formatNumber value="${jbdMemberLinkCalcHist.ventureSalesPv*1 }" type="number"  pattern="###,###,##0.00" />
				</td>
				<td> 
					 <fmt:formatNumber value="${jbdMemberLinkCalcHist.ventureSalesPv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/>
				</td>
				<td>
					<security:authorize url="/app/jbdBonusEnquiryDetails">
						   <a href="jbdBonusEnquiryDetails.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=servicePv">
				   <ng:locale key="menu.editJbdMemberLinkCalcHis"/></a>
				   </security:authorize>
				 
				</td>
			</tr>
			
			</c:if>
			
			
			
			<tr <c:if test="${jbdMemberLinkCalcHist.wweek>=201604 }">class="stripe"</c:if>>  
				<td><b> <ng:locale  key="bd.send.bounspv"/></b>：</td>
				<td><b>${jbdMemberLinkCalcHist.bounsPv+jbdMemberLinkCalcHist.franchisePv}</b></td>
				<td>
					<security:authorize url="/app/jbdBonusEnquiryDetails">
						   <!-- 这里缺少权限设定的标语 -->
						   <b><fmt:formatNumber value="${jbdMemberLinkCalcHist.bounsMoney+jbdMemberLinkCalcHist.franchisePv*jbdMemberLinkCalcHist.exchangeRate}" type="number" pattern="###,###,##0.00"/></b>
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
		
		　　<%-- <tr class="stripe">
				<td width="20%"><ng:locale  key="franchise.moeny"/>：</td>
				<td width="15%"><fmt:formatNumber value="${jbdMemberLinkCalcHist.franchiseMoney}" type="number" pattern="###,###,##0.00"/></td>
				<td width="15%">&nbsp;</td>
				<td>
					<a href="jbdBonusEnquiryDetails.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=franchiseMoney">
				   <ng:locale key="menu.editJbdMemberLinkCalcHis"/></a>
				</td>
			</tr> --%>
			
			<tr class="stripe">
				<td><ng:locale  key="consumer.amount"/>：</td>
				<td><fmt:formatNumber value="${jbdMemberLinkCalcHist.consumerAmount}" type="number" pattern="###,###,##0.00"/></td>
				<td></td>
				<td>
					<a href="jbdBonusEnquiryDetails.html?userCode=${jbdMemberLinkCalcHist.userCode }&wweek=${jbdMemberLinkCalcHist.wweek }&type=consumerAmount">
				   <ng:locale key="menu.editJbdMemberLinkCalcHis"/></a>
				</td>
			</tr>
			
			<tr >
				<td><ng:locale  key="jbdMemberLinkCalcHist.deductMoney"/>：</td>
				<td><fmt:formatNumber value="${jbdMemberLinkCalcHist.deductMoney}" type="number" pattern="###,###,##0.00"/></td>
				<td></td>
				<td>
					<a href="jbdBounsDeducts">
				   <ng:locale key="menu.editJbdMemberLinkCalcHis"/></a>
				</td>
			</tr>
			
			<tr class="stripe">
				<td><ng:locale  key="jbdMemberLinkCalcHist.deductTax"/>：</td>
				<td><fmt:formatNumber value="${jbdMemberLinkCalcHist.deductTax}" type="number" pattern="###,###,##0.00"/></td>
				<td></td>
				<td><%--<a href="#">查看明细</a>--%> </td>
			</tr>
			
			<c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.companyCode=='CN'}">  
			<tr >
				<td><ng:locale  key="bdSendRecord.networkMoney"/>：</td>
				<td><fmt:formatNumber value="${jbdMemberLinkCalcHist.networkMoney}" type="number" pattern="###,###,##0.00"/></td>
				<td></td>
				<td><%-- <a href="#">查看明细</a> --%> </td>
			</tr>
			</c:if>
			
			<c:if test="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.companyCode=='CN'}">  
			<tr class="stripe">
				<td><ng:locale  key="bd.currentDev.bd"/>：</td>
				<td><fmt:formatNumber value="${jbdMemberLinkCalcHist.currentDev}" type="number" pattern="###,###,##0.00"/></td>
				<td></td>
				<td></td>
			</tr>
			</c:if> 
		</tbody>
		
	</table>
</div>
	
	<%-- <security:authorize url="/app/jbdBonusEnquiryDetails">  --%>     
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
	
	<div class="tc"> <button type="button" class="btn btn-success" onclick="history.go(-1)" >&nbsp;<span>返&nbsp;回</span>&nbsp;</button>	</div> 
    
</div>	
	<%-- </security:authorize> --%>
	
	
	