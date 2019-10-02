<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
        <div class="indexMainC fl">
	      	<ul class="backLog mgb20">
	      		
	      		<%-- <c:forEach items="${pbsList }" var="pbs">
	      			<li><a href="#" target="_blank" class="fl ft12" style="color: red;">${pbs.name }</a><span class="fr ft12">${pbs.startTime }</span></li>
	      		</c:forEach> --%>
	      		
	      		<c:if test="${!empty psList}">
	      			<c:forEach items="${psList }" var="ps">
	             	<input type="hidden" id="id" value="${ps.id }"/>
		      			<li>
		      				<a href="#" class ="ft12" style="color: red;">个人事项：</a>
			      			<a href="${ctx}/loginform/scheduleInfo?id=${ps.id }" class="ft12" style="color: green;">
			      			<c:choose>
				      			<c:when test="${fn:length(ps.scheduleName) >40 }">
				      				${fn:substring(ps.scheduleName, 0, 40)}...
				      			</c:when>
				      			<c:otherwise>
					      			${ps.scheduleName }
				      			</c:otherwise>
			      			</c:choose></a>
			      			<span class="fr ft12">${ps.startTime }</span>
		      			</li>
		      		</c:forEach>
	      		</c:if>
	      		<c:if test="${!empty pbsList}">
	      			<c:forEach items="${pbsList }" var="pbs">
		      			<li>
		      				<a href="#" class ="ft12" style="color: red;">团队活动：</a>
			      			<a href="${ctx }/publicScheduleform.html?psId=${pbs.psId }" class="ft12" style="color: blue;">
							<c:choose>
				      			<c:when test="${fn:length(pbs.name) >40 }">
				      				${fn:substring(pbs.name, 0, 40)}...
				      			</c:when>
				      			<c:otherwise>
					      			${pbs.name }
				      			</c:otherwise>
			      			</c:choose></a>
			      			<span class="fr ft12">${pbs.startTime }</span>
		      			</li>
		      		</c:forEach>
	      		</c:if>
	      		<%-- <c:if test="${!empty birsList}">
	      			<c:forEach items="${birsList }" var="jmim">
		      			<li>
		      				<a href="#" class ="ft12" style="color: red;">生日提醒：</a>
			      			<a href="#" class="ft12" style="color: green;">${jmim.firstName } ${jmim.papernumber }</a>
			      			<span class="fr ft12"> 将在( <fmt:formatDate value="${jmim.birthday}" pattern="MM/dd"/> )过生日 </span>
		      			</li>
		      		</c:forEach>
	      		</c:if> --%>
	      		<c:if test="${!empty noPays}">
	      			<c:forEach items="${noPays }" var="pay">
		      			<li>
		      				<a href="#" class ="ft12" style="color: red;">未支付订单：</a>
			      			<a href="${ctx }/jfiPay99bill?orderId=${pay.moId}&flag=1" class="ft12" style="color: green;">${pay.memberOrderNo } </a>
			      			<span class="fr ft12"> ${pay.orderTime}  </span>
		      			</li>
		      		</c:forEach>
	      		</c:if>
	      		<c:if test="${empty psList }">
	      			<div  > <a style="color: red;"> 您还没有私人待办事项， 请到<a href="${ctx }/scheduleManages?1=1&currentMenuId=712771&currentSubMenuId=712855&parentId=712621" style="color:red;">日程管理</a>处添加！</a> </div>
	      		</c:if> 
	      		
            </ul>
      	</div>
	</div>
		