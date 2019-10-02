<%@ page contentType = "text/html; charset=utf-8" language = "java"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
<!--<title>团队管理 - 奖金查询-查询明细</title>-->
<link rel="stylesheet" href="css/style.css" />
</head>

<!-- 
	<div id="titleBar" class="searchBar">
		<input type="button" class="button" name="cancel"  onclick="history.back()" value="<ng:locale key="operation.button.return"/>" />
	</div>
 -->
 	
<body>


<div class="cont">	

	<div class="bt mt">
			<h3 class="color2 ml">奖金查询-查询明细</h3>
	</div>
<c:if test="${type=='ventureSalesPv' }">
    
    <table width="100%" border="0" class = "personalInfoTab mgtb10">
       <tbody>
         <tr class="stripe">
	         <td align="center" class ="tr"><ng:locale key="column.headercell.tooltip.sort"/></td>
	         <td align="center" class ="tr"><ng:locale key="miMember.memberNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="jbdSellCalcSubHists.groupPV"/></td>
	         <td align="center" class ="tr"><ng:locale key="jbdSellCalcSubHists.area_Consumption"/></td>
	         <td align="center" class ="tr"><ng:locale key="bdCalculatingSubDetail.bounsPoint"/></td>
	         <td align="center" class ="tr"><ng:locale key="bd.send.bounspv.top"/></td>
	         <td align="center" class ="tr"><ng:locale key="bdPv.pvType05"/></td>
	         <td align="center" class ="tr"><ng:locale key="bdBonus.tree.pre.keepPv"/></td>
	         <security:authorize url="/app/jbdSellCalcSubDetailHist">
	         <td align="center" class ="tr"><ng:locale key="title.view"/></td><!-- 这里缺少权限的标识 -->
	         </security:authorize>
         </tr>
       </tbody>
       <c:forEach items = "${jbdSellCalcSubHists}" var = "jbdSellCalcSubHistsObj">
           <tr class="stripe">
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.serial_Number }</td>
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.user_Code }</td>
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.group_Pv }</td>
              <%--  <c:if test="${param.wweek >= 201037 }"> --%>
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.area_Consumption }</td>
               <%-- </c:if>
               <c:if test="${param.wweek < 201037}">
               <td align="center" class ="tr"></td>
               </c:if> --%>
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.bouns_Point*100 }</td>
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.bouns_Pv }</td>
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.keep_Pv }</td>
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.last_Keep_Pv }</td>
               <td>
                <security:authorize url="/app/jbdSellCalcSubDetailHist">
                   <!-- 这里缺少权限的标识 -->
                   <img src="images/search_16.gif" onclick="window.location.href='jbdSellCalcSubDetailHist.html?userCode=${jbdSellCalcSubHistsObj.user_Code }&wweek=${jbdSellCalcSubHistsObj.w_week }';" alt="<ng:locale key="function.menu.view"/>" style="cursor:pointer"/>
                </security:authorize>
               </td>
    
           </tr>
       </c:forEach>
    </table>
</c:if>    
   
<c:if test="${type=='ventureLeaderPv' }">
   <table width="100%" border="0" class = "personalInfoTab mgtb10">
   
       <tr class="stripe">
	         <td align="center" class ="tr"><ng:locale key="bdMemberBounsCalcSell.level"/></td>
	         <td align="center" class ="tr"><ng:locale key="miMember.memberNo.star"/></td>
	          <%-- <c:if test="${param.wweek>=201037 }"> --%>
	         <td align="center" class ="tr"><ng:locale key="group.pv.star.37"/></td>
	         <%-- </c:if>
	         <c:if test="${param.wweek<201037 }">
	         <td align="center" class ="tr"><ng:locale key="group.pv.star"/></td>
	         </c:if> --%>
	         <td align="center" class ="tr"><ng:locale key="bdCalculatingSubDetail.bounsPoint"/></td>
	         <td align="center" class ="tr"><ng:locale key="bd.send.bounspv"/></td>
         </tr>
         
	    <c:forEach items = "${jbdVentureLeaderSubHists}" var = "jbdVentureLeaderSubHistsObj">
	   <tr class="stripe">
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.nlevel }</td>
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.recommended_code }</td>
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.pass_Group_Pv } </td>
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.bouns_Point*100 }</td>
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.bouns_Money }</td>
	   </tr>
	   </c:forEach>
   </table>
</c:if> 

<c:if test="${type=='successSalesPv' }">
   <table width="100%" border="0" class = "personalInfoTab mgtb10">
   
       <tr class="stripe">
	         <td align="center" class ="tr"><ng:locale key="miMember.memberNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="jbdMemberLinkCalcHist.monthRdisplayommendGroupPv"/></td>
	         <td align="center" class ="tr"><ng:locale key="successSaleBouns.rate"/></td>
       </tr>
	    <c:forEach items = "${jbdMemberLinkCalcHists}" var = "jbdMemberLinkCalcHistsObj">
	   <tr class="stripe">
	        <td align="center" class ="tr">${jbdMemberLinkCalcHistsObj.user_Code }</td>
	        <td align="center" class ="tr">${jbdMemberLinkCalcHistsObj.monthRdisplayommendGroup_Pv }</td>
	        <td align="center" class ="tr">${jbdMemberLinkCalcHistsObj.success_Sales_Rate } </td>
	   </tr>
	   </c:forEach>
   </table>
</c:if> 

<c:if test="${type=='successLeaderPv' }">
   <table width="100%" border="0" class = "personalInfoTab mgtb10">
   
       <tr class="stripe">
	         <td align="center" class ="tr"><ng:locale key="bdMemberBounsCalcSell.level"/></td>
	         <td align="center" class ="tr"><ng:locale key="miMember.memberNo.pass"/></td>
	         <td align="center" class ="tr"><ng:locale key="group.pv.pass"/></td>
	         <td align="center" class ="tr"><ng:locale key="bdCalculatingSubDetail.bounsPoint"/></td>
	         <td align="center" class ="tr"><ng:locale key="bd.send.bounspv"/></td>
       </tr>
	    <c:forEach items = "${jbdVentureLeaderSubHists}" var = "jbdVentureLeaderSubHistsObj">
	   <tr class="stripe">
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.nlevel }</td>
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.rdisplayommended_Code }</td>
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.pass_Group_Pv }</td>
	        <td align="center" class ="tr">
	          <fmt:formatNumber value="${jbdVentureLeaderSubHistsObj.bouns_Point*100 }" type="number" pattern="###,###,###.##"/>%
	        </td>
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.bouns_Money }</td>
	   </tr>
	   </c:forEach>
   </table>
</c:if>

<c:if test="${type=='franchiseMoney' }">
   <table width="100%" border="0" class = "personalInfoTab mgtb10">
   
       <tr class="stripe">
	         <td align="center" class ="tr"><ng:locale key="miMember.memberNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="poMemberOrder.memberOrderNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="bd.fees.pv"/></td>
	         <td align="center" class ="tr"><ng:locale key="billAccount.persent"/></td>
       </tr>
	    <c:forEach items = "${franchiseMoneys}" var = "franchiseMoneysObj">
	   <tr class="stripe">
	        <td align="center" class ="tr">${franchiseMoneysObj.rdisplayommend_no }</td>
	        <td align="center" class ="tr">${franchiseMoneysObj.member_order_no }</td>
	        <td align="center" class ="tr">${franchiseMoneysObj.fees }</td>
	        <td align="center" class ="tr">
		        <c:if test="${not empty franchiseMoneysObj.percentage }">
	   						<fmt:formatNumber value="${franchiseMoneysObj.percentage*100 }" type="number" pattern="###,###,###.##"/>%
			    </c:if>
		        <c:if test="${ empty franchiseMoneysObj.percentage }">
		           ${franchiseMoneysObj.percentage }
		        </c:if>
	        </td>
	        
	   </tr>
	   </c:forEach>
   </table>
</c:if>


<c:if test="${type=='consumerAmount' }">
   <table width="100%" border="0" class = "personalInfoTab mgtb10">
   
       <tr class="stripe">
	         <td align="center" class ="tr"><ng:locale key="miMember.memberNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="poMemberOrder.memberOrderNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="pomember.fee"/></td>
	         <td align="center" class ="tr"><ng:locale key="billAccount.persent"/></td>
       </tr>
	    <c:forEach items = "${consumerAmounts}" var = "consumerAmountsObj">
	   <tr class="stripe">
	        <td align="center" class ="tr">${consumerAmountsObj.rdisplayommend_no }</td>
	        <td align="center" class ="tr">${consumerAmountsObj.member_order_no }</td>
	        <td align="center" class ="tr">${consumerAmountsObj.fees }</td>
	        <td align="center" class ="tr">
	           <c:if test="${not empty consumerAmountsObj.percentage }">
   						<fmt:formatNumber value="${consumerAmountsObj.percentage*100 }" type="number" pattern="###,###,###.##"/>%
			   </c:if>
	           <c:if test="${ empty consumerAmountsObj.percentage }">
	              ${consumerAmountsObj.percentage }
	           </c:if>
	        </td>
	   </tr>
	   </c:forEach>
   </table>
</c:if>

<c:if test="${type=='rdisplayommendBonusPv' }">
   <table width="100%" border="0" class = "personalInfoTab mgtb10">
   
       <tr class="stripe">
	         <td align="center" class ="tr"><ng:locale key="miMember.memberNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="poMemberOrder.memberOrderNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="pd.orderType"/></td>
	         <td align="center" class ="tr"><ng:locale key="report.inPv"/></td>
       </tr>
	    <c:forEach items = "${jbdSellCalcRdisplayommendBouns}" var = "jbdSellCalcRdisplayommendBounsObj">
	   <tr class="stripe">
	        <td align="center" class ="tr">${jbdSellCalcRdisplayommendBounsObj.rdisplayommended_Code }</td>
	        <td align="center" class ="tr">${jbdSellCalcRdisplayommendBounsObj.rdisplayommended_Order_No }</td>
	        <td align="center" class ="tr">${jbdSellCalcRdisplayommendBounsObj.rdisplayommended_Order_Type }</td>
	        <td align="center" class ="tr">${jbdSellCalcRdisplayommendBounsObj.pv }</td>
	   </tr>
	   </c:forEach>
   </table>
</c:if>

<c:if test="${type=='storeExpandPv' }">
   <table width="100%" border="0" class = "personalInfoTab mgtb10">
   
       <tr class="stripe">
	         <td align="center" class ="tr"><ng:locale key="storeExpandPv.recommendedNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="busi.common.store"/></td>
	         <td align="center" class ="tr"><ng:locale key="poMemberOrder.memberOrderNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="bd.fees.pv"/></td>
	         <td align="center" class ="tr"><ng:locale key="billAccount.persent"/></td>
	         <td align="center" class ="tr"><ng:locale key="bd.send.bounspv"/></td>
       </tr>
	    <c:forEach items = "${storeExpandPvs}" var = "storeExpandPvsObj">
	   <tr class="stripe">
	        <td align="center" class ="tr">${storeExpandPvsObj.rdisplayommended_no }</td>
	        <td align="center" class ="tr">${storeExpandPvsObj.isstore }</td>
	        <td align="center" class ="tr">${storeExpandPvsObj.order_no }</td>
	        <td align="center" class ="tr">${storeExpandPvsObj.pv_amt }</td>
	        <td align="center" class ="tr">
	            <fmt:formatNumber value="${storeExpandPvsObj.proportion*100 }" type="number" pattern="###,###,###.##"/>%
	        </td>
	        <td align="center" class ="tr">${storeExpandPvsObj.pv }</td>
	   </tr>
	   </c:forEach>
   </table>
</c:if>

<c:if test="${type=='storeServePv' }">
   <table width="100%" border="0" class = "personalInfoTab mgtb10">
    <tbody>
       <tr class="stripe">
	         <td align="center" class ="tr"><ng:locale key="storeExpandPv.recommendedNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="busi.common.store"/></td>
	         <td align="center" class ="tr"><ng:locale key="poMemberOrder.memberOrderNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="bd.fees.pv"/></td>
	         <td align="center" class ="tr"><ng:locale key="billAccount.persent"/></td>
	         <td align="center" class ="tr"><ng:locale key="bd.send.bounspv"/></td>
       </tr>
    </tbody>   
	    <c:forEach items = "${storeServePvs}" var = "storeServePvsObj">
	   <tr class="stripe">
	        <td align="center" class ="tr">${storeServePvsObj.rdisplayommended_no }</td>
	        <td align="center" class ="tr">${storeServePvsObj.isstore }</td>
	        <td align="center" class ="tr">${storeServePvsObj.order_no }</td>
	        <td align="center" class ="tr">${storeServePvsObj.pv_amt }</td>
	        <td align="center" class ="tr">${storeServePvsObj.proportion }
	            <fmt:formatNumber value="${storeServePvsObj.proportion*100 }" type="number" pattern="###,###,###.##"/>%
	        </td>
	        <td align="center" class ="tr">${storeServePvsObj.pv }</td>
	   </tr>
	   </c:forEach>
   </table>
</c:if>

<c:if test="${type=='storeRdisplayommendPv' }">
   <table width="100%" border="0" class = "personalInfoTab mgtb10">
    <tbody>
       <tr class="stripe">
	         <td align="center" class ="tr"><ng:locale key="miMember.rdisplayommendNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="poMemberOrder.memberOrderNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="report.inPv"/></td>
	         <td align="center" class ="tr"><ng:locale key="billAccount.persent"/></td>
       </tr>
    </tbody>   
	    <c:forEach items = "${storeRdisplayommendPvs}" var = "storeRdisplayommendPvsObj">
	   <tr class="stripe">
	        <td align="center" class ="tr">${storeRdisplayommendPvsObj.rdisplayommended_no }</td>
	        <td align="center" class ="tr">${storeRdisplayommendPvsObj.order_no }</td>
	        <td align="center" class ="tr">${storeRdisplayommendPvsObj.pv_amt }</td>
	        <td align="center" class ="tr">
	            <c:if test="${not empty storeRdisplayommendPvsObj.proportion }">
   						<fmt:formatNumber value="${storeRdisplayommendPvsObj.proportion*100 }" type="number" pattern="###,###,###.##"/>%
   				</c:if>
   				<c:if test="${not empty storeRdisplayommendPvsObj.proportion }" >
   				        ${storeRdisplayommendPvsObj.proportion }
   				</c:if>
	        </td>
	   </tr>
	   </c:forEach>
   </table>
</c:if>

<%-- <c:if test="${type=='ventureFund' }">
   <table width="100%" border="0" class = "personalInfoTab mgtb10">
    <tbody>
       <tr class="stripe">
	         <td align="center" class ="tr"><ng:locale key="miMember.memberNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="poMemberOrder.memberOrderNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="report.inPv"/></td>
	         <td align="center" class ="tr"><ng:locale key="billAccount.persent"/></td>
       </tr>
    </tbody>   
	    <c:forEach items = "${ventureFundPvs}" var = "ventureFundPvsObj">
	   <tr class="stripe">
	        <td>${ventureFundPvsObj.user_code }</td>
	        <td>${ventureFundPvsObj.order_no }</td>
	        <td>${ventureFundPvsObj.pv_amt }</td>
	        <td>
	            <c:if test="${not empty ventureFundPvsObj.percentage }">
   						<fmt:formatNumber value="${ventureFundPvsObj.percentage*100 }" type="number" pattern="###,###,###.##"/>%
   				</c:if>
	            <c:if test="${ empty ventureFundPvsObj.percentage }">
	                    ${ventureFundPvsObj.percentage }
	            </c:if>
	        </td>
	   </tr>
	   </c:forEach>
   </table>
</c:if> --%>

<c:if test="${type=='recommendBonusPv' }">
   <table width="100%" border="0" class = "personalInfoTab mgtb10">
    <tbody>
       <tr class="stripe">
	         <td align="center" class ="tr"><ng:locale key="miMember.memberNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="poMemberOrder.memberOrderNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="pd.orderType"/></td>
	         <td align="center" class ="tr"><ng:locale key="report.inPv"/></td>
       </tr>
    </tbody>   
	    <c:forEach items = "${jbdSellCalcRecommendBouns}" var = "jbdSellCalcRecommendBounsObj">
	   <tr class="stripe">
	        <td align="center" class ="tr">${jbdSellCalcRecommendBounsObj.recommended_Code }</td>
	        <td align="center" class ="tr">${jbdSellCalcRecommendBounsObj.recommended_Order_No }</td>
	        <td align="center" class ="tr">${jbdSellCalcRecommendBounsObj.recommended_Order_Type }
	        <!-- 
	        <ng:code listCode="po.all.order_type" value="${fn:trim(jbdSellCalcSubDetailHist.recommendedOrderType)}"/> -->
	        </td>
	        <td align="center" class ="tr">${jbdSellCalcRecommendBounsObj.pv }</td>
	        
	   </tr>
	   </c:forEach>
   </table>
</c:if>

<c:if test="${type=='storeRecommendPv' }">
   <table width="100%" border="0" class = "personalInfoTab mgtb10">
    <tbody>
       <tr class="stripe">
	         <td align="center" class ="tr"><ng:locale key="miMember.recommendNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="poMemberOrder.memberOrderNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="report.inPv"/></td>
	         <td align="center" class ="tr"><ng:locale key="billAccount.persent"/></td>
       </tr>
    </tbody>   
	    <c:forEach items = "${storeRecommendPvs}" var = "storeRecommendPvsObj">
	   <tr class="stripe">
	        <td align="center" class ="tr">${storeRecommendPvsObj.recommended_no }</td>
	        <td align="center" class ="tr">${storeRecommendPvsObj.order_no }</td>
	        <td align="center" class ="tr">${storeRecommendPvsObj.pv_amt }</td>
	        <td align="center" class ="tr">
	            <c:if test="${not empty storeRecommendPvsObj.proportion }">
   						<fmt:formatNumber value="${storeRecommendPvsObj.proportion*100 }" type="number" pattern="###,###,###.##"/>%
   			   </c:if>
	           <c:if test="${not empty storeRecommendPvsObj.proportion }">
	                ${storeRecommendPvsObj.proportion }
	           </c:if>
	        </td>
	       
	   </tr>
	   </c:forEach>
   </table>
</c:if>
<div class="tc"> <button type="button" class="btn btn-success" onclick="history.go(-1)" >&nbsp;<span>返&nbsp;回</span>&nbsp;</button>	</div> 
   
</div>

</body>
<script src="js/jQuery-1.9.1.min.js"></script>
<script src="js/joyLife.js"></script>
<script type="text/javascript">
window.top.document.getElementById("menuLabel").innerHTML='<jdisplays:locale key="function.menu.basicManage"/>>><jdisplays:locale key="function.menu.bonusManage"/>>><jdisplays:locale key="function.menu.bonusSearch"/>>><jdisplays:locale key="function.menu.addMiMember.bdSubDetails.html"/>';

</script>