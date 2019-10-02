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
                   <img src="images/search.gif" onclick="window.location.href='jbdSellCalcSubDetailHist.html?userCode=${jbdSellCalcSubHistsObj.user_Code }&wweek=${jbdSellCalcSubHistsObj.w_week }';" alt="<ng:locale key="function.menu.view"/>" style="cursor:pointer"/>
                </security:authorize>
               </td>
    
           </tr>
       </c:forEach>
    </table>
</c:if>    


<c:if test="${type=='ventureLeaderPv201607' }">
    
    <table width="100%" border="0" class = "personalInfoTab mgtb10">
       <tbody>
         <tr class="stripe">
	         <td align="center" class ="tr">代数</td>
	         <td align="center" class ="tr"><ng:locale key="miMember.memberNo"/></td>
	         <td align="center" class ="tr">订单编号</td>
	         <td align="center" class ="tr">订单类型</td>
	         <td align="center" class ="tr">订单PV</td>
	         <td align="center" class ="tr"><ng:locale key="bdCalculatingSubDetail.bounsPoint"/></td>
	         <td align="center" class ="tr">销售奖PV</td>
	         
         </tr>
       </tbody>
       <c:forEach items = "${jbdVentureLeaderSubHists}" var = "jbdSellCalcSubHistsObj">
           <tr class="stripe">
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.nlevel }</td>
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.recommended_Code }</td>
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.member_Order_No }</td>
               <td align="center" class ="tr">
				<ng:code listCode="po.all.order_type" value="${fn:trim(jbdSellCalcSubHistsObj.order_type)}"/> 
				</td>
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.pass_Group_Pv }</td>
               <td align="center" class ="tr">
               	<c:choose> 
		            <c:when test="${jbdSellCalcSubHistsObj.bouns_Point =='1'}">&nbsp;</c:when> 
		            <c:otherwise><fmt:formatNumber value="${jbdSellCalcSubHistsObj.bouns_Point*100 }" type="number" pattern="###,###,###.##"/>%</c:otherwise> 
				</c:choose>
               </td>
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.bouns_Money }</td>
              
           </tr>
       </c:forEach>
    </table>
</c:if>

	<c:if test="${type=='popularizeBonusPv' }">

		<table width="100%" border="0" class = "personalInfoTab mgtb10">
			<tbody>
			<tr class="stripe">
				<td align="center" class ="tr">贡献会员</td>
				<td align="center" class ="tr">贡献订单业绩</td>
				<td align="center" class ="tr">贡献订单编号</td>
				<td align="center" class ="tr">领取比例</td>
				<td align="center" class ="tr">领取奖金</td>

			</tr>
			</tbody>
			<c:forEach items = "${popularizeBonusPvs}" var = "obj">
				<tr class="stripe">
					<td align="center" class ="tr">${obj.reuser_code }</td>
					<td align="center" class ="tr">${obj.order_pv }</td>
					<td align="center" class ="tr">${obj.member_order_no }</td>
					<td align="center" class ="tr">
						<fmt:formatNumber value="${obj.com_rate*100 }" type="number" pattern="###,###,###.##"/>%
					</td>
					<td align="center" class ="tr">
						<fmt:formatNumber value="${obj.recommend_pv }" type="number" pattern="###,###,###.##"/>
					</td>

				</tr>
			</c:forEach>
		</table>
	</c:if>



	<c:if test="${type=='ventureLeaderPv201805' }">

		<table width="100%" border="0" class = "personalInfoTab mgtb10">
			<tbody>
			<tr class="stripe">
				<td align="center" class ="tr">排序</td>
				<td align="center" class ="tr"><ng:locale key="miMember.memberNo"/></td>
				<td align="center" class ="tr">结算日期</td>
				<td align="center" class ="tr">新增业绩</td>
				<td align="center" class ="tr">封顶后奖金PV</td>
				<td align="center" class ="tr">当日保留业绩PV</td>
				<td align="center" class ="tr">上日保留业绩PV</td>
				<td align="center" class ="tr"><ng:locale key="bdCalculatingSubDetail.bounsPoint"/></td>
				<td align="center" class ="tr"></td>
			</tr>
			</tbody>
			<c:forEach items = "${jbdVentureLeaderSubHists}" var = "jbdSellCalcSubHistsObj">
				<tr class="stripe">
					<td align="center" class ="tr">${jbdSellCalcSubHistsObj.num }</td>
					<td align="center" class ="tr">${jbdSellCalcSubHistsObj.user_code }</td>
					<td align="center" class ="tr">${jbdSellCalcSubHistsObj.w_week }</td>
					<td align="center" class ="tr">
								${jbdSellCalcSubHistsObj.week_group_first_pv_lnk}
					</td>
					<td align="center" class ="tr">${jbdSellCalcSubHistsObj.bouns }</td>
					<td align="center" class ="tr">
							${jbdSellCalcSubHistsObj.keep_pv }
					</td>
					<td align="center" class ="tr">${jbdSellCalcSubHistsObj.last_keep_pv }</td>
					<td align="center" class ="tr">${jbdSellCalcSubHistsObj.bouns_point }</td>
					<td>
						<security:authorize url="/app/jbdSellCalcSubDetailHist">
							<img src="images/search.gif" onclick="window.location.href='jbdBonusEnquiryDetails.html?userCodeParam=${jbdSellCalcSubHistsObj.user_code }&wweek=${jbdSellCalcSubHistsObj.w_week}&type=ventureLeaderPvDetail201805';" alt="<ng:locale key="function.menu.view"/>" style="cursor:pointer"/>
						</security:authorize>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>


	<c:if test="${type=='ventureLeaderPvDetail201805' }">

		<table width="100%" border="0" class = "personalInfoTab mgtb10">
			<tbody>
			<tr class="stripe">
				<td align="center" width="30%" class ="tr"><ng:locale key="miMember.memberNo"/></td>
				<td align="center" width="30%" class ="tr">订单编号</td>
				<td align="center" width="30%" class ="tr">业绩</td>
				<td align="center" width="10%" class ="tr"></td>
			</tr>
			</tbody>
			<c:forEach items = "${jbdVentureLeaderSubHistsDetail}" var = "obj">
				<tr class="stripe">
					<td align="center" class ="tr">${obj.reuser_code }</td>
					<td align="center" class ="tr">${obj.reuser_code }</td>
					<td align="center" class ="tr">${obj.order_pv }</td>
					<td align="center" class ="tr"></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<c:if test="${type=='bdjPv201607' }">
    
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
       <c:forEach items = "${storeExpandPvs}" var = "jbdSellCalcSubHistsObj">
           <tr class="stripe">
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.reno }</td>
               <td align="center" class ="tr">
               <ng:code listCode="isstore" value="${fn:trim(jbdSellCalcSubHistsObj.isstore)}"/> 
               </td>
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.order_no }</td>
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.pv_amt }</td>
               <td align="center" class ="tr">
               	<c:choose> 
		            <c:when test="${jbdSellCalcSubHistsObj.bfb =='1'}">&nbsp;</c:when> 
		            <c:otherwise><fmt:formatNumber value="${jbdSellCalcSubHistsObj.bfb*100 }" type="number" pattern="###,###,###.##"/>%</c:otherwise> 
				</c:choose>
               </td>
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.pv }</td>
              
           </tr>
       </c:forEach>
    </table>
</c:if>    

<c:if test="${type=='servicePv201607' }">
    
    <table width="100%" border="0" class = "personalInfoTab mgtb10">
       <tbody>
         <tr class="stripe">
	         <td align="center" class ="tr">部门数</td>
	         <td align="center" class ="tr">会员编号</td>
	         <td align="center" class ="tr">订单编号</td>
	         <td align="center" class ="tr">订单类型</td>
	         <td align="center" class ="tr">订单PV</td>
	         <td align="center" class ="tr">奖金比率</td>
	         <td align="center" class ="tr">服务奖PV</td>
	         
         </tr>
       </tbody>
       <c:forEach items = "${servicePvList}" var = "jbdSellCalcSubHistsObj">
           <tr class="stripe">
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.part_no }</td>
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.reuser_code }</td>
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.member_order_no }</td>
               <td align="center" class ="tr">
               <ng:code listCode="po.all.order_type" value="${fn:trim(jbdSellCalcSubHistsObj.order_type)}"/> 
               </td>
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.rets_group_pv }</td>
               <td align="center" class ="tr">
               	<c:choose> 
		            <c:when test="${jbdSellCalcSubHistsObj.sales_rate =='1'}">&nbsp;</c:when> 
		            <c:otherwise><fmt:formatNumber value="${jbdSellCalcSubHistsObj.sales_rate*100 }" type="number" pattern="###,###,###.##"/>%</c:otherwise> 
				</c:choose>
               </td>
               <td align="center" class ="tr">${jbdSellCalcSubHistsObj.success_sales_pv }</td>
              
           </tr>
       </c:forEach>
    </table>
</c:if> 



   
<c:if test="${type=='ventureLeaderPv' }">
   <table width="100%" border="0" class = "personalInfoTab mgtb10">
   
       <tr class="stripe">
       
       
       
			<c:if test="${param.wweek==201516 }">
			
	         <td align="center" class ="tr"><ng:locale key="bdMemberBounsCalcSell.level"/></td>
	         <td align="center" class ="tr"><ng:locale key="miMember.memberNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="miMember.cardType"/></td>
	         <td align="center" class ="tr"><ng:locale key="group.pv.star.37"/></td>
	         <td align="center" class ="tr"><ng:locale key="bdCalculatingSubDetail.bounsPoint"/></td>
	         <td align="center" class ="tr"><ng:locale key="bd.send.bounspv"/></td>
	         
			</c:if>
			
			<c:if test="${param.wweek>201516 }">
			
	         <td align="center" class ="tr"><ng:locale key="miMember.memberNo"/></td>
	         
	         
	         <td align="center" class ="tr">类别</td>
	         <td align="center" class ="tr">来源业绩</td>
	         <td align="center" class ="tr"><ng:locale key="bdCalculatingSubDetail.bounsPoint"/></td>
	         <td align="center" class ="tr">领取奖金PV</td>
	         <td align="center" class ="tr">查看</td>
	         
	         
			
			</c:if>
       
       
	         
	         
	         
	         
         </tr>
         
	    <c:forEach items = "${jbdVentureLeaderSubHists}" var = "jbdVentureLeaderSubHistsObj">
	   <tr class="stripe">
	   
	   
	   
	   
			<c:if test="${param.wweek==201516 }">
			
	     
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.nlevel }</td>
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.recommended_code }</td>
	        <td align="center" class ="tr"><ng:code listCode="member.level" value="${jbdVentureLeaderSubHist.memberLevel}"/></td>
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.pass_Group_Pv } </td>
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.bouns_Point*100 }</td>
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.bouns_Money }</td>
	         
			</c:if>
			
			<c:if test="${param.wweek>201516 }">
			
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.recommended_code }</td>
	        <td align="center" class ="tr">
	       							 <c:if test="${jbdVentureLeaderSubHistsObj.bounsType=='01' }">
			    						首单
			    					</c:if>
			    					<c:if test="${jbdVentureLeaderSubHistsObj.bounsType=='02' }">
			    						重消 
			    					</c:if>	
	        
	        </td>
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.pass_Group_Pv } </td>
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.bouns_Point*100 }</td>
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.bouns_Money }</td>
	        <td align="center" class ="tr">
	        
                <security:authorize url="/app/jbdSellCalcSubDetailHist">
                   <img src="images/search.gif" onclick="window.location.href='jbdBonusEnquiryDetails.html?userCode=${jbdVentureLeaderSubHistsObj.recommended_code }&wweek=${jbdVentureLeaderSubHistsObj.w_week }&type=ventureLeaderPvDetail';" alt="<ng:locale key="function.menu.view"/>" style="cursor:pointer"/>
                </security:authorize>
                
                
	        </td>
			
			</c:if>
	   
	   
	   </tr>
	   </c:forEach>
   </table>
</c:if> 

<c:if test="${type=='successSalesPv' }">
   <table width="100%" border="0" class = "personalInfoTab mgtb10">
   
   
   
   
     <c:choose>
     
     	<c:when test="${param.wweek >=201701}">
    	 <tr class="stripe">
	         <td align="center" class ="tr">层数</td>
	         <td align="center" class ="tr">会员编号</td>
	         <td align="center" class ="tr">订单编号</td>
	         <td align="center" class ="tr">订单类型</td>
	         <td align="center" class ="tr">订单业绩</td>
	         <td align="center" class ="tr">奖金比率</td>
	         <td align="center" class ="tr">重消奖</td>
       </tr>
    		
			
			
    	</c:when>
     
     
    	<c:when test="${param.wweek >=201532}">
    	 <tr class="stripe">
	         <td align="center" class ="tr">领取会员编号</td>
	         <td align="center" class ="tr">销售业绩</td>
	         <td align="center" class ="tr">卓越奖奖励比率</td>
	         <td align="center" class ="tr">卓越奖PV</td>
       </tr>
    		
			
			
    	</c:when>
    	<c:when test="${param.wweek >201516}">
    		
			  <tr class="stripe">
	         <td align="center" class ="tr">奖衔会员编号</td>
	         <td align="center" class ="tr">销售业绩</td>
	         <td align="center" class ="tr">领导奖奖励比率</td>
	         <td align="center" class ="tr">领导奖PV</td>
       </tr>
    	</c:when>
    	<c:otherwise>
			
			</c:otherwise>
    </c:choose>
   
   
   
     
	    <c:forEach items = "${jbdMemberLinkCalcHists}" var = "jbdMemberLinkCalcHistsObj">
	    
	    
	   <tr class="stripe">
	   
	   		  <c:choose>
     
     	<c:when test="${param.wweek >=201701}">
     	
            <td align="center" class ="tr">${jbdMemberLinkCalcHistsObj.part_no }</td>
            <td align="center" class ="tr">${jbdMemberLinkCalcHistsObj.reuser_code }</td>
            <td align="center" class ="tr"></td>
            <td align="center" class ="tr"></td>
	        <td align="center" class ="tr">${jbdMemberLinkCalcHistsObj.rets_group_pv }</td>
	        <td align="center" class ="tr"><fmt:formatNumber value="${jbdMemberLinkCalcHistsObj.sales_rate*100 }" type="number" pattern="###,###,###.##"/>%</td>
	        <td align="center" class ="tr">${jbdMemberLinkCalcHistsObj.success_sales_pv }</td>
	 
			
    	</c:when>
     

    	<c:otherwise>
			        <td align="center" class ="tr">${jbdMemberLinkCalcHistsObj.reuser_code }</td>
	        <td align="center" class ="tr">${jbdMemberLinkCalcHistsObj.rets_group_pv }</td>
	        <td align="center" class ="tr"><fmt:formatNumber value="${jbdMemberLinkCalcHistsObj.sales_rate*100 }" type="number" pattern="###,###,###.##"/>%</td>
	        <td align="center" class ="tr">${jbdMemberLinkCalcHistsObj.success_sales_pv }</td>
	 
			</c:otherwise>
    </c:choose>
	   
	   
	  </tr>
	   
	   
	   
	   
	   </c:forEach>
   </table>
</c:if> 

<c:if test="${type=='successLeaderPv' }">
   <table width="100%" border="0" class = "personalInfoTab mgtb10">
   
       <tr class="stripe">
	         <td align="center" class ="tr">代数</td>
	         <td align="center" class ="tr">会员编号</td>
	         <td align="center" class ="tr">奖衔</td>
	         <td align="center" class ="tr">奖金比率</td>
	         <td align="center" class ="tr">感恩奖奖金PV</td>
	         <td align="center" class ="tr">维护</td>
       </tr>
	    <c:forEach items = "${jbdSuccessLeaderPvList}" var = "jbdVentureLeaderSubHistsObj">
	   <tr class="stripe">
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.algebra }</td>
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.re_user_code }</td>
	        <td align="center" class ="tr"><ng:code listCode="pass.star.zero" value="${jbdVentureLeaderSubHistsObj.repass_star}"/></td>
	        <td align="center" class ="tr">
	        <fmt:formatNumber value="${jbdVentureLeaderSubHistsObj.success_rate*100 }" type="number" pattern="###,###,###.##"/>%
	        
	        </td>
	        <td align="center" class ="tr">${jbdVentureLeaderSubHistsObj.success_leader_pv }</td>  
	        <td>
                <security:authorize url="/app/jbdSellCalcSubDetailHist">
                   <img src="images/search.gif" onclick="window.location.href='jbdBonusEnquiryDetails.html?userCode=${jbdVentureLeaderSubHistsObj.user_code }&wweek=${jbdVentureLeaderSubHistsObj.w_week }&type=successLeaderPvDetail&passStar=${jbdVentureLeaderSubHistsObj.pass_star }&algebra=${jbdVentureLeaderSubHistsObj.algebra }';" alt="<ng:locale key="function.menu.view"/>" style="cursor:pointer"/>
                </security:authorize>
            </td>
	       
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
       
       <c:choose>
    	<c:when test="${param.wweek ==201516}">
    	     		
			         <td align="center" class ="tr"><ng:locale key="miMember.memberNo"/></td>
			         <td align="center" class ="tr"><ng:locale key="poMemberOrder.memberOrderNo"/></td>
			         <td align="center" class ="tr"><ng:locale key="pd.orderType"/></td>
			         <td align="center" class ="tr"><ng:locale key="report.inPv"/></td>
			
			
    	</c:when>
    	<c:when test="${param.wweek >=201532}">
    	     		
			
			         <td align="center" class ="tr"><ng:locale key="miMember.memberNo"/></td>
			         <td align="center" class ="tr"><ng:locale key="poMemberOrder.memberOrderNo"/></td>
			         <td align="center" class ="tr"><ng:locale key="pd.orderType"/></td>
			         <td align="center" class ="tr">销售PV</td>
			
    	</c:when>
    	<c:when test="${param.wweek >201516}">
    		
					 <td align="center" class ="tr">层数</td>
			         <td align="center" class ="tr"><ng:locale key="miMember.memberNo"/></td>
			         <td align="center" class ="tr">销售业绩</td>
			         <td align="center" class ="tr">奖金比率</td>
			         <td align="center" class ="tr">奖金PV</td>
			
    	</c:when>
    	<c:otherwise>
			
			</c:otherwise>
		</c:choose>
			         
       </tr>
    </tbody>   
	    <c:forEach items = "${jbdSellCalcRecommendBouns}" var = "jbdSellCalcRecommendBounsObj">
	   <tr class="stripe">
	   
	   
	   
		<c:if test="${param.wweek==201516 }">
		
	   
	        <td align="center" class ="tr">${jbdSellCalcRecommendBounsObj.recommended_Code }</td>
	        <td align="center" class ="tr">${jbdSellCalcRecommendBounsObj.recommended_Order_No }</td>
	        <td align="center" class ="tr">
	        <ng:code listCode="po.all.order_type" value="${fn:trim(jbdSellCalcSubDetailHist.recommended_Order_Type)}"/> 
	        </td>
	        <td align="center" class ="tr">${jbdSellCalcRecommendBounsObj.pv }</td>
			         
		</c:if>
		<c:if test="${param.wweek>201516 }">
		
				
	        <td align="center" class ="tr">${jbdSellCalcRecommendBounsObj.nlevel }</td>
	        <td align="center" class ="tr">${jbdSellCalcRecommendBounsObj.recommendedCode }</td>
	        <td align="center" class ="tr">${jbdSellCalcRecommendBounsObj.pv }</td>
	        <td align="center" class ="tr">${jbdSellCalcRecommendBounsObj.bouns_rate*100 }</td>
	   
	        <td align="center" class ="tr">${jbdSellCalcRecommendBounsObj.bouns_pv }</td>
	        
		</c:if>
	   
	        
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





<c:if test="${type=='successLeaderPvDetail' }">
   <table width="100%" border="0" class = "personalInfoTab mgtb10">
    <tbody>
       <tr class="stripe">
	         <td align="center" class ="tr"><ng:locale key="miMember.recommendNo"/></td>
	         <td align="center" class ="tr"><ng:locale key="poMemberOrder.memberOrderNo"/></td>
	         <td align="center" class ="tr">订单PV</td>
       </tr>
    </tbody>   
	    <c:forEach items = "${successLeaderPvDetailList}" var = "storeRecommendPvsObj">
	   <tr class="stripe">
	        <td align="center" class ="tr">${storeRecommendPvsObj.re_user_code }</td>
	        <td align="center" class ="tr">${storeRecommendPvsObj.member_order_no }</td>
	        <td align="center" class ="tr">${storeRecommendPvsObj.pv_amt }</td>
	        
	       
	   </tr>
	   </c:forEach>
   </table>
</c:if>





<c:if test="${type=='ventureLeaderPvDetail' }">
   <table width="100%" border="0" class = "personalInfoTab mgtb10">
    <tbody>
       <tr class="stripe">
	         <td align="center" class ="tr">会员编号</td>
	         <td align="center" class ="tr">国别</td>
	         <td align="center" class ="tr">订单编号</td>
	         <td align="center" class ="tr">订单类型</td>
	         <td align="center" class ="tr">销售PV</td>
       </tr>
    </tbody>   
	    <c:forEach items = "${ventureLeaderPvDetailList}" var = "cuVentureLeaderPvDetail">
	   <tr class="stripe">
	        <td align="center" class ="tr">${cuVentureLeaderPvDetail.user_code }</td>
	        <td align="center" class ="tr">${cuVentureLeaderPvDetail.company_code }</td>
	        <td align="center" class ="tr">${cuVentureLeaderPvDetail.member_order_no }</td>
	        <td align="center" class ="tr">
	        <ng:code listCode="po.all.order_type" value="${fn:trim(cuVentureLeaderPvDetail.order_type)}"/> 
	        
	        </td>
	        <td align="center" class ="tr">${cuVentureLeaderPvDetail.pv_amt }</td>
	        
	       
	   </tr>
	   </c:forEach>
   </table>
</c:if>

<!-- 服务奖明细 的明细 -->
<c:if test="${type=='servicePv' }">





<table width="100%" border="0" class = "personalInfoTab mgtb10">
    <tbody>
       <tr class="stripe">
	         <td align="center" class ="tr">代数</td>
	         <td align="center" class ="tr">会员编号</td>
	         <td align="center" class ="tr">订单类型</td>
	         <td align="center" class ="tr">订单PV</td>
	         <td align="center" class ="tr">奖金比率</td>
	         <td align="center" class ="tr">服务奖PV</td>
       </tr>
    </tbody>   
	    <c:forEach items = "${servicePvList}" var = "servicePv">
	   <tr class="stripe">
	        <td align="center" class ="tr">${servicePv.dia_level }</td>
	        <td align="center" class ="tr">${servicePv.reuser_code }</td>
	        <td align="center" class ="tr"> <ng:code listCode="po.all.order_type" value="${servicePv.order_type}"/> </td>
	        
	        <td align="center" class ="tr">${servicePv.cx_amt }</td>
	        <td align="center" class ="tr"><fmt:formatNumber value="${servicePv.cx_rate*100 }" type="number" pattern="###,###,###.##"/>%</td>
	        <td align="center" class ="tr">${servicePv.cx_pv }</td>
	        
	       
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