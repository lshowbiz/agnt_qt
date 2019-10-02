<%@ include file="/common/taglibs.jsp"%>
<%@ page contentType = "text/html; charset=utf-8" language = "java"%>

<!--<title>中脉科技-会员信息服务平台</title>-->
<content tag="heading"><ng:locale key="jbdSellCalcSubDetailHistList.heading"/></content>
<meta name="menu" content="JbdSellCalcSubDetailHistMenu"/>

<!-- 
	<div id="titleBar" class="titleBar">
	    <input type="button" class="button" name="cancel"  onclick="history.back()" value="<ng:locale key="operation.button.return"/>" />
	</div>
 -->
<c:set var="footTotalVar">
<tr>
	<td id="frontTd" align="right" class="footer" colspan="6"><jecs:locale key="poMemberOrder.pvAmt"/></td>
	<td class="footer" align="right">
	<b>
<fmt:formatNumber value="${sumPv }" pattern="###,###,##0.00"></fmt:formatNumber>
</b>
</td>
</tr>
</c:set>

   <table width="100%" border="0" class = "personalInfoTab mgtb10">
      <tbody>
       <tr class="stripe">
	         <td align="right" class ="tr" width="15%"><ng:locale key="miMember.memberNo"/></td>
	         <td align="right" class ="tr" width="10%"><ng:locale key="busi.poMemberOrder.company"/></td>
	         <td align="right" class ="tr" width="10%"><ng:locale key="bdMemberBounsCalcSell.level"/></td>
	         <td align="right" class ="tr" width="20%"><ng:locale key="poMemberOrder.memberOrderNo"/></td>
	         <td align="right" class ="tr" width="10%"><ng:locale key="order.type.bd"/></td>
	         <td align="right" class ="tr" width="10%"><ng:locale key="pd.orderType"/></td>
	         <td align="right" class ="tr" width="15%"><ng:locale key="report.inPv"/></td>
         </tr>
       </tbody>  
              <% int i = 0; %>
	    <c:forEach items = "${jbdSellCalcSubDetailHists}" var = "jbdSellCalcSubDetailHistListObj">
	         <% ++i; %>
	         <c:if test="<%=((i%2)==0)%>">
	            <tr class="stripe">
			        <td align="right" width="15%">${jbdSellCalcSubDetailHistListObj.recommended_Code }</td>
			        <td align="right" width="10%">${jbdSellCalcSubDetailHistListObj.recommended_Company_Code }</td>
			        <td align="right" width="10%">${jbdSellCalcSubDetailHistListObj.nlevel }</td>
			        <td align="right" width="20%">${jbdSellCalcSubDetailHistListObj.recommended_Order_No }</td>
			        <td align="right" width="10%">
			        
			           <ng:code listCode="order_class" value="${fn:trim(jbdSellCalcSubDetailHistListObj.recommended_Order_Class)}"/>
			        </td>
			        <td align="right" width="10%">${jbdSellCalcSubDetailHistListObj.recommended_Order_Type }</td>
		            <td align="right" width="15%">${jbdSellCalcSubDetailHistListObj.pv }</td>
	            </tr>
	         </c:if>
	         <c:if test="<%=((i%2)!=0)%>">
	              <tr>
				        <td align="right" width="15%">${jbdSellCalcSubDetailHistListObj.recommended_Code }</td>
				        <td align="right" width="10%">${jbdSellCalcSubDetailHistListObj.recommended_Company_Code }</td>
				        <td align="right" width="10%">${jbdSellCalcSubDetailHistListObj.nlevel }</td>
				        <td align="right" width="20%">${jbdSellCalcSubDetailHistListObj.recommended_Order_No }</td>
				        <td align="right" width="10%">
				        
				           <ng:code listCode="order_class" value="${fn:trim(jbdSellCalcSubDetailHistListObj.recommended_Order_Class)}"/>
				        </td>
				        <td align="right" width="10%">${jbdSellCalcSubDetailHistListObj.recommended_Order_Type }</td>
			            <td align="right" width="15%">${jbdSellCalcSubDetailHistListObj.pv }</td>
	        
	              </tr>
	         </c:if>
	   </c:forEach>
   </table>
