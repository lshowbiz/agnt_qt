<%@ page language="java" errorPage="/error.jsp" contentType="text/html; charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<style>
#organiseQueryTable td { font-size:12px;}
#organiseQueryTable p { line-height:17px; text-indent:0; text-align:center;}
.iBox { width:120px; height:260px; background:url('./images/organise/tb_2.jpg') left top no-repeat;}
.TT_2 a { position:absolute; display:block; width:28px; height:28px; bottom:4px; left:46px;}
</style>

<div class="cont">	
	<div class="bt mt">
		<h3 class="color2 ml">组织状态查询</h3>
	</div>
<form method="get" action="jbdOrganiseStatus" onsubmit="return validateOthers(this)" id="bdPeriodForm">
			<table class="search_table mt"  >
				<tr>
					<td><ng:locale key="miMember.memberNo" />: </td>
					<td>
						<input type="text" name="userCode" value="${not empty param.userCode ?param.userCode:sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userCode }" size="10"  <c:if test="${ empty isUnlimitUser }">readonly="readonly" </c:if>/>
				   
					</td>
				
					<td><ng:locale key="bdBounsDeduct.wweek" />:</td>
					<td>
					
						<select name="wweek">
							<c:forEach items="${latesBdPeriods}" var="latesBdPeriodVar">
								<option value="${latesBdPeriodVar.long_f_week }"<c:if test="${latesBdPeriodVar.long_f_week==param.wweek }"> selected</c:if>>[${latesBdPeriodVar.long_f_week }] ${latesBdPeriodVar.std } ~ ${latesBdPeriodVar.edd }</option>
							</c:forEach>
						</select>
					
					</td>
				   
					<td>
						<button id="search" type="submit" ><ng:locale key="operation.button.search"/></button>
						<input type="hidden" name="strAction" value="bdOrganiseStatusQuery"/>
					</td>
				</tr>
		</table>
</form>
<div style="overflow-x:auto;">
<c:if test="${not empty bdDayBounsCalcMap}">
	<p style="margin-left: 50px">
	<span><ng:locale key="bdBonus.tree.organize"/>：</span><a href="jbdOrganiseStatus?strAction=bdOrganiseStatusQuery&firstMemberNo=${firstMiMember.userCode }&userCode=${firstMiMember.userCode }&wweek=${param.wweek }">
	
		${firstMiMember.petName }
	
	</a>

	<c:if test="${not empty upMiLinkRefs}">
		<c:forEach items="${upMiLinkRefs}" var="upMiLinkRefVar">
			/ <a href="jbdOrganiseStatus?strAction=bdOrganiseStatusQuery&firstMemberNo=${firstMiMember.userCode }&userCode=${upMiLinkRefVar.jmiMember.userCode }&wweek=${param.wweek }">
			${upMiLinkRefVar.jmiMember.petName }
			</a>
		</c:forEach>
		
	</c:if>
	</p>
</c:if>

<c:if test="${not empty bdDayBounsCalcMap}">
	<table width="100%" id="organiseQueryTable">
		<tr align="center">
			<td colspan="9">
				<table width="120" border="0" cellspacing="0">
					<tr>
						<td class="TT_1">
							<div class="rel iBox">
								<p class="ta_b">${bdDayBounsCalcMap.user_code }</p>
								<p class="bold">${bdDayBounsCalcMap.pet_name }</p>
								<p><ng:code listCode="bd.cardtype" value="${bdDayBounsCalcMap.card_type }"/></p>
								
								<p class="ta_g"><ng:locale key="pv.person"/>：</p>	<!-- 当月个人业绩 -->
								<p><span><fmt:formatNumber pattern="###,##0.00">${bdDayBounsCalcMap.month_consumer_pv }</fmt:formatNumber></span><span>pv</span></p>
								
								<p class="ta_b"><ng:locale key="bdBonus.tree.organize"/>：</p>	<!-- 安置网络 -->
								<p><span><fmt:formatNumber pattern="###,##0.00">${bdDayBounsCalcMap.week_group_pv }</fmt:formatNumber></span><span>pv</span></p>
								
								
								<hr width="80%"/>
								<!-- power -->
								<p class="ta_g"><ng:locale key="miLinkRef.total"/>：</p>	<!-- 安置网络人数 -->
								<p><span>${bdDayBounsCalcMap.link_num }</span><span><ng:locale key="unit.person"/></span></p>
								
								
								<p class="ta_r"><ng:locale key="miLinkRef.new"/>：</p>
								<p><span>${bdDayBounsCalcMap.pending_link_num }</span><span><ng:locale key="unit.person"/></span></p>

								<%--
								<p class="ta_r">
									<ng:locale key="bdBonus.tree.organize"/>:
									<c:if test="${not empty bdDayBounsCalcMap.total}">${bdDayBounsCalcMap.total }</c:if>
									<c:if test="${empty bdDayBounsCalcMap.total}">0</c:if>人
								</p> --%>
							</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr align="center"><td colspan="9"><img src="images/organise/tb.jpg" width="640" height="50" /></td></tr>
		
		<tr align="center">
			<td>
				<table width="0" border="0" cellspacing="0">
					<tr>
						
						<c:set var="loopCount" value="0"></c:set>
						<c:forEach items="${childBdDayBonusCalcs}" var="childBdDayBonusCalcVar" varStatus="childBdDayBonusCalcStatus">
							<c:set var="loopCount" value="${loopCount+1}"></c:set>
							
							<c:if test="${childBdDayBonusCalcStatus.index>0}">
							<td width="7">&nbsp;</td>
							</c:if>
							
							<c:set var="cssStyle" value="TT_2"/>
							
							<c:if test="${childBdDayBonusCalcVar.card_type==0}">
								<c:set var="cssStyle" value="TT_3"/>
							</c:if>
							
							<td class="${cssStyle }">
								<div class="rel iBox">
								
									<p class="ta_b">${childBdDayBonusCalcVar.user_code }</p>
									<p class="bold">${childBdDayBonusCalcVar.pet_name }</p>
									<p><ng:code listCode="bd.cardtype" value="${childBdDayBonusCalcVar.card_type }"/></p>
									
									<p class="ta_g"><ng:locale key="pv.person"/>：</p>
									<p>
										<span><fmt:formatNumber pattern="###,##0.00">${childBdDayBonusCalcVar.month_consumer_pv }</fmt:formatNumber></span>
										<span>pv</span>
									</p>
									
									<p class="ta_b"><ng:locale key="bdBonus.tree.organize"/>：</p>
									<p>
										<span><fmt:formatNumber pattern="###,##0.00">${childBdDayBonusCalcVar.week_group_pv }</fmt:formatNumber></span>
										<span>pv</span>
									</p>
									
									<hr width="80%" />
									
									<p class="ta_g"><ng:locale key="miLinkRef.total"/>：</p>
									<p>
										<span>${childBdDayBonusCalcVar.link_num }</span>
										<span><ng:locale key="unit.person"/></span>
									</p>
									
									
									<p class="ta_r"><ng:locale key="miLinkRef.new"/>：</p>
									<p>
										<span>${childBdDayBonusCalcVar.pending_link_num }</span>
										<span><ng:locale key="unit.person"/></span>
									</p>
									
									<c:if test="${childBdDayBonusCalcVar.user_code==bdSendRecord.keep_user_code}">
									<p class="ta_r red">
										<span><ng:locale key="pv.keep"/>：</span>
										<c:if test="${not empty bdSendRecord.keep_pv}">
											<span><fmt:formatNumber pattern="###,##0.00">${bdSendRecord.keep_pv }</fmt:formatNumber></span>
										</c:if>
										<span><c:if test="${empty bdSendRecord.keep_pv}">0.00</c:if>pv</span>
									</p>
									</c:if>
									
									<c:if test="${not empty isUnlimitUser }">
									
										<a href="jbdOrganiseStatus?strAction=bdOrganiseStatusQuery&firstMemberNo=${firstMiMember.userCode }&userCode=${childBdDayBonusCalcVar.user_code }&wweek=${param.wweek }">
										<img src="images/organise/tt_l.gif" width="28" height="28" border="0"/></a>
									
									</c:if>
								</div>
							</td>
						</c:forEach>
						
						
						<c:if test="${loopCount<6}">
							<c:set var="loopVar" value="0,1,2,3,4,5"></c:set>
							<c:forTokens items="${loopVar}" delims="," begin="${loopCount}">
								<td width="7">&nbsp;</td>
								<td width="120" class="TT_3">&nbsp;</td>
							</c:forTokens>
						</c:if>
						
					</tr>
				</table>
			</td>
		</tr>
	</table>

	<br>
	<div style="margin-left: 50px">
	<c:if test="${not empty bdDayBounsCalcMap}">
		<span style="font-size:13px">
		<br>
			<ng:locale key="bdOrganiseStatus.busi.stat.week"/>[ ${param.wweek } ]
		<br>
			<ng:locale key="bdOrganiseStatus.keep.busi.week"/>[ <ng:weekFormat week="${bdSendRecord.w_week }" weekType="w" /> ]
		<br>
			<ng:locale key="bdOrganiseStatus.sample.tips"/>
		</span>
	</c:if>
	<br>
	<table width="100%" border="0" bgcolor="#FFFFCC">
		<tr>
			<td>
			<ng:locale key="bdBounsDeduct.summary"/>:<br>
			<ng:locale key="bd.organise.notice.pv.person"/><br>
			<ng:locale key="bd.organise.notice.pv.group"/><br>
			<HR>
			 <ng:locale key="bd.organise.notice.member.recommend"/><br>
			<ng:locale key="bd.organise.notice.member.new"/><br>
			<%--<ng:locale key="bdBonus.tree.organize"/>: <ng:locale key="bd.organise.notice.group.totalnum"/>--%>
			</td>
		</tr>
	</table>
	</div>
</c:if>
	</div>

</div>
<script type="text/javascript">
function validateOthers(theForm){
	if(theForm.userCode.value==""){
		alert("<ng:locale key="bdBonus.tree.memberNo.required"/>");
		theForm.userCode.focus();
		return false;
	}
	
	return true; 
}


//if (document.all){
//	window.attachEvent('onload',autoSubmitForm)
//}else{
//	window.addEventListener('load',autoSubmitForm,false);
//}
function autoSubmitForm(){
	document.bdOrganiseForm.submit();
}


</script>