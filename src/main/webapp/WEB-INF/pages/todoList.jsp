<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<script src="<c:url value='/scripts/AjaxLoader.js'/>"></script>	
<script>

function tohref(resCode,tourl) {
	var strurl="${ctx}/ajaxController/redirectByRes?resCode="+resCode;
		var loader = new AjaxLoader("GET",strurl);
    var strExist = loader.getReturnText();
    if('1'==strExist){
    	window.location.href=tourl;
    }
}
</script>
<div class="cont" >	
			<div class="bt mt">
				<h3 class="color2 ml">待办列表</h3>
			</div>	

	<div class="mt">	
		<table class="prod mt" border="1" style="border-collapse:collapse; border:1px solid #ebebeb;" >	
			<tbody class="list_tbody_header">
				<tr>
		            <td width="120">待办类别</td>
		            <td><spring:message code="message.subjet"/></td>
		            <td width="150">创建时间</td>
		        </tr>
		    </tbody>
		    <tbody class="list_tbody_row">
		    	<c:if test="${!empty psList}">
		        <c:forEach items="${psList }" var="ps" varStatus="status">
			        <tr class="bg-c gry3">
			        	<td>
			        		<c:if test="${ status.index !=-1 }">
			            		<a href="javascript:void(0)" onclick="tohref('mySchedules','${ctx}/mySchedules?statue=notyet')" style="color:black;">个人事项</a> 
			            	</c:if>
			        	</td>
			            <td title="${ps.scheduleName }">
			            	<li class="ml2">
			            	<a href="javascript:void(0)" onclick="tohref('mySchedules','${ctx}/loginform/scheduleInfo?id=${ps.id }')" style="color:black;">
							<c:choose>
								<c:when test="${fns:isAbbreviate(ps.scheduleName, 80)}">
									${fns:abbreviate(ps.scheduleName, 80,'...')}
								</c:when>
								<c:otherwise>
									${ps.scheduleName }
								</c:otherwise>
							</c:choose>
							</a>
							</li>
			            </td>
			            <td>${ps.startTime }</td> 
			        </tr>
		        </c:forEach>
		        </c:if>
		        <c:if test="${!empty pbsList}">
		        <c:forEach items="${pbsList }" var="pbs"  varStatus="status">
			        <tr class="bg-c gry3">
			        	<td>
			        		<c:if test="${ status.index !=-1 }">
			            		<a href="javascript:void(0)" onclick="tohref('scheduleManage','${ctx }/scheduleManages')" style="color:black;">团队活动</a> 
			            	</c:if>
			        	</td>
			            <td title="${pbs.name }">
			            	<li class="ml2">
			            	<a  href="javascript:void(0)" onclick="tohref('scheduleManage','${ctx }/publicScheduleform.html?psId=${pbs.psId }')" style="color:black;">
							<c:choose>
								<c:when test="${fns:isAbbreviate(pbs.name, 40)}">
									${fns:abbreviate(pbs.name, 40,'...')}
								</c:when>
								<c:otherwise>
									${pbs.name }
								</c:otherwise>
							</c:choose>
							</a>
							</li>
			            </td>
			            <td>${pbs.startTime }</td> 
			        </tr>
		        </c:forEach>
		        </c:if>
		        <c:if test="${!empty noPays}">
		        <c:forEach items="${noPays }" var="pay" varStatus="status">
			        <tr class="bg-c gry3">
			        	<td>
			        		<c:if test="${ status.index !=-1 }">
			            		<a href="javascript:void(0)" onclick="tohref('002','${ctx }/jpoMemberOrders/orderAll?strAction=2')" style="color:black;">未支付订单 </a>
			            	</c:if>
			        	</td>
			            <td>
			            	<c:choose>
		                   		<c:when test="${pay.PRODUCTFLAG!=null }">
		                   			<c:if test="${pay.ORDER_USER_CODE==pay.sysUser.USER_CODE}">
		                   				<li class="ml2">
			                   				<span style="color:red;">您有未支付订单：</span><a href="#" title="支付" onclick="javascript:alert('此订单为生态家套餐，请到生态家套餐进行支付！');location.href='${pageContext.request.contextPath}/showBigPage?1=1'" class="Payment">${pay.MEMBER_ORDER_NO }</a>
		                   				</li>
		                   			</c:if>
		                   		</c:when>
		                   		<c:otherwise>
		                   			<li class="ml2">
		                   				<a href="javascript:void(0)" onclick="tohref('002','${ctx }/jfiPay99bill?orderId=${pay.MO_ID}&flag=1')" style="color: green;">${pay.MEMBER_ORDER_NO } </a>
		                   			</li>
		                   		</c:otherwise>
		                    </c:choose>		
			            </td>
			            <td>${fn:substring(pay.ORDER_TIME, 0, 10)}</td> 
			            
			        </tr>
		        </c:forEach>
		        </c:if>
		        <c:if test="${!empty eoNoList}">
		        <c:forEach items="${eoNoList }" var="eoNo" varStatus="status">
			        <tr class="bg-c gry3">
		        		<td>
			        		<c:if test="${ status.index !=-1 }">
			            		<a href="javascript:void(0)" onclick="tohref('queryExchangeOrderList','${ctx}/pdExchangeOrderform/queryExchangeOrderList?1=1')" style="color:black;">未支付换货单 </a>
			            	</c:if>
			        	</td>
			            <td>
			            	<li class="ml2">
			            		<a  href="javascript:void(0)" onclick="tohref('queryExchangeOrderList','${ctx}/pdExchangeOrderform/queryExchangeOrderList?1=1')" style="color: green;">${eoNo.eo_no } </a>
			            	</li>
			            </td>
			            <td><fmt:formatDate value="${eoNo.CREATE_TIME }" type="date" dateStyle="default" /></td> 
			        </tr>
		        </c:forEach>
		        </c:if>
		        
		        
		        
		        <c:if test="${strNYCQualify != null}">
			        <tr class="bg-c gry3">
			        	<td>
			        		<c:if test="${ status.index !=-1 }">
			            		<a href="javascript:void(0)" style="color:black;">能源车提示</a> 
			            	</c:if>
			        	</td>
			            <td title="${pbs.name }" colspan="2">
			            	<li class="ml2">
			            	<a  href="http://www.goodnev.com/" target="_blanks" title="恭喜你获得国之道车友俱乐部资格！点击连接进入购买,请在一个月内加入！逾期视为放弃资格" style="color: green;">
               				<c:choose>
								<c:when test="${fns:isAbbreviate('恭喜你获得国之道车友俱乐部资格！点击连接进入购买,请在一个月内加入！逾期视为放弃资格', 80)}">
									${fns:abbreviate('恭喜你获得国之道车友俱乐部资格！点击连接进入购买,请在一个月内加入！逾期视为放弃资格', 80,'...')}
								</c:when>
								<c:otherwise>
									恭喜你获得国之道车友俱乐部资格！点击连接进入购买,请在一个月内加入！逾期视为放弃资格
								</c:otherwise>
							</c:choose>
               				</a>
							</li>
			            </td>
			        </tr>
		        </c:if>
		        
		        <c:if test="${pdSendInfoUnRead >= 1}">
			        <tr class="bg-c gry3">
			        	<td>
			        		<c:if test="${ status.index !=-1 }">
			            		<a href="javascript:void(0)" style="color:black;">收货确认提醒</a> 
			            	</c:if>
			        	</td>
			            <td title="${pbs.name }" colspan="2">
			            	<li class="ml2">
			            	<a  href="javascript:void(0)" onclick="tohref('pdSendinfoQuery','${ctx}/pdSendinfoQuery')" title="亲爱的中脉家人，您好！您有未确认的收货信息，如有收到货，请确认！" style="color: green;">
	              				<c:choose>
									<c:when test="${fns:isAbbreviate('亲爱的中脉家人，您好！您有未确认的收货信息，如有收到货，请确认！', 80)}">
										${fns:abbreviate('亲爱的中脉家人，您好！您有未确认的收货信息，如有收到货，请确认！', 80,'...')}
									</c:when>
									<c:otherwise>
										亲爱的中脉家人，您好！您有未确认的收货信息，如有收到货，请确认！
									</c:otherwise>
								</c:choose>
              				</a>
							</li>
			            </td>
			        </tr>
		        </c:if>
		        
		    </tbody>
		</table>
	</div>
</div>