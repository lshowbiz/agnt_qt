<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp" %>
<title><ng:locale key="webapp.name.qt" /></title>
<style>
	.stockAccount { position:fixed; left:30px; bottom:262px; border: 1px solid #FE204D;}
</style>

<div class="centerDiv" style="padding-top:3px">
	<div class="indexMainC fl" style="padding-top:25px">
		
		<div class="indexMainC-top">
			<div class="baner-top baner-1">
				<img src="<c:url value='/styles/images/baner-1.png'/>">
			</div>
			<div class="baner-top baner-2">
				<img src="<c:url value='/styles/images/baner-2.png'/>">
			</div>
			<div class="baner-top baner-3">
				<c:if test="${isGuPiao!='Y'}">
					<a href="${ctx}/foundationOrderform?1=1&img=11&fiNum=1&currentMenuId=2163654">
						<img src="<c:url value='/styles/images/baner-3.png'/>">
					</a>
				</c:if>
				<c:if test="${isGuPiao=='Y'}">
				<a href="${ctx}/foundationOrderform?1=1&img=11&fiNum=1&currentMenuId=2163654" style="display:block;width:115px;height:140px;float:left;">
					<img src="<c:url value='/styles/images/baner-4.png'/>">
				</a>
				<a href="" style="display:block;width:114px;height:140px;float:left;" target="_blank">
					<img src="<c:url value='/styles/images/baner-5.png'/>">
				</a>
				</c:if>
				
			</div>
		</div>
		
		<h3><span class="ft12 fl colorCS">待办事项</span></h3>
		<ul class="backLog mgb20">
		   
			<c:if test="${!empty psList}">
				<c:forEach items="${psList }" var="ps">
				<input type="hidden" id="id" value="${ps.id }"/>
				<li href="${ctx}/loginform/scheduleInfo?id=${ps.id }" >
					<a href="${ctx}/loginform/scheduleInfo?id=${ps.id }&currentMenuId=712771&currentSubMenuId=712855&parentId=712621" class ="ft12 fl red">个人事项：</a>  
					<a href="${ctx}/loginform/scheduleInfo?id=${ps.id }&currentMenuId=712771&currentSubMenuId=712855&parentId=712621" class="ft12 fl" style="color:#53ADE3;">
					<c:choose>
						<c:when test="${fn:length(ps.scheduleName) > 40 }">
							${fn:substring(ps.scheduleName, 0, 40)}...
						</c:when>
						<c:otherwise>
							${ps.scheduleName }
						</c:otherwise>
					</c:choose>
					</a>
					<span class="fr ft12" >${ps.startTime }&nbsp;</span>
				</li>
				</c:forEach>
			</c:if>
			<c:if test="${!empty pbsList}">
				<c:forEach items="${pbsList }" var="pbs">
					<li>
						<a href="${ctx }/publicScheduleform.html?psId=${pbs.psId }&currentMenuId=712771&currentSubMenuId=712855&parentId=712621" class ="ft12 fl">团队活动：</a>
						<a href="${ctx }/publicScheduleform.html?psId=${pbs.psId }&currentMenuId=712771&currentSubMenuId=712855&parentId=712621" class="ft12 fl">
						<c:choose>
							<c:when test="${fn:length(pbs.name) >40 }">
								${fn:substring(pbs.name, 0, 40)}...
							</c:when>
							<c:otherwise>
								${pbs.name }
							</c:otherwise>
						</c:choose></a>
						<span class="fr ft12">${pbs.startTime }&nbsp;</span>
					</li>
				</c:forEach>
			</c:if>
			<%-- 
			<c:if test="${!empty birsList}">
				<c:forEach items="${birsList }" var="jmim">
					<li>
						<span class ="ft12 fl red">生日提醒：</span>
						<a href="#" class="ft12 fl" style="color: blue;">${jmim.firstName } ${jmim.papernumber }</a>
						<span class="fr ft12" > 将在( <fmt:formatDate value="${jmim.birthday }" pattern="MM/dd" />)过生日&nbsp;&nbsp;&nbsp;&nbsp;</span>
					</li>
				</c:forEach>
			</c:if> 
			--%>
			
			<c:if test="${!empty noPays}">
				<c:forEach items="${noPays }" var="pay">
					<c:choose>
                   		<c:when test="${pay.PRODUCTFLAG!=null }">
                   			<c:if test="${pay.ORDER_USER_CODE==pay.sysUser.USER_CODE}">
                   				<li>
	                   				<span class ="ft12 fl red">您有未支付订单：</span><a href="#" title="支付" onclick="javascript:alert('此订单为生态家套餐，请到生态家套餐进行支付！');location.href='${pageContext.request.contextPath}/showBigPage?1=1'" class="Payment">${pay.MEMBER_ORDER_NO }</a>
	                   				<span class="fr ft12"> ${pay.ORDER_TIME }&nbsp;</span>
                   				</li>
                   			</c:if>
                   		</c:when>
                   		<c:otherwise>
                   			<li>
                   				<a href="${ctx }/jfiPay99bill?orderId=${pay.MO_ID}&flag=1&parentId=712619" class="ft12 fl" style="color: green;">${pay.MEMBER_ORDER_NO } </a>
                   				<span class="fr ft12"> ${pay.ORDER_TIME }&nbsp;</span>
                   			</li>
                   		</c:otherwise>
                    </c:choose>						
				</c:forEach>
			</c:if>
			
		</ul>
		
		<c:if test="${empty psList }">
			<p style="line-height:180%;">您还没有私人待办事项，请到<a href="${ctx }/scheduleManages?1=1&currentMenuId=712771&currentSubMenuId=712855&parentId=712621" class="red" style="vertical-align:baseline;margin:0 4px;">日程管理</a>处添加！</p>
		</c:if> 
		
		<h3><span class="ft12 fl colorCS">业务提醒</span></h3>
		<div class="businessInfo mgb20">
	<%-- 		<p>本周安置新增业绩：${ indexMap.Month_Group_Pv}PV</p>
			<p>本周奖衔：${indexMap.level }</p>
			<p>会员级别：<ng:code listCode="bd.cardtype" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.jmiMember.cardType }"></ng:code>  
				 
			
			</p>--%>
			<p><a href="javascript:void(0);" class="higher colorQL" id="b_js_higher">${recentlyList[1].aa }财月 如何冲刺更高奖衔</a></p>
			<p><a href="javascript:void(0);" class="higher colorQL" id="js_higher">${recentlyList[0].aa }财月 如何冲刺更高奖衔</a></p>
			<p class="bold">以上数据考核周期为&nbsp;${indexMap.startTime } - ${indexMap.endTime }</p>
			<p class="colorCS">此数据为目前预算信息，尚未最后结算，仅供参考！</p>
			<c:if test="${not empty tips4565Package }">
				<p class="bold">${tips4565Package }</p>
			</c:if>
		</div>

		<h3><span class="ft12 fl colorCS">消息提醒</span></h3>
		<ul class="msgLs mgb20">
			<li><a href="${ctx}/announce/showAnnounce?currentMenuId=712775&currentSubMenuId=2164074&parentId=712625" class="ft12">您有&nbsp;${indexMap.notRead }&nbsp;个公司公告尚未阅读。</a></li>	
			<li><a href="${ctx}/showMessage?1=1&currentMenuId=712775&currentSubMenuId=2164075&parentId=712625" class="ft12">您有&nbsp;${indexMap.notReadMesSize }&nbsp;个新的业务咨询已经得到回复。</a></li>
			
		<%--
			<c:forEach items="${amNews }" var="amNew">
				<li><a href="${amNew.url}" target="_blank" class="fl ft12">${amNew.subject}</a><span class="fr ft12">${amNew.createTime}</span></li>	
			</c:forEach>
		--%>
		
		</ul>
	</div>
	<div class="indexMainR fl">
		<div class="bussinessPost">
			<h3 class="postTitle"><span>业务公告</span></h3>
			<div class="postBg"></div>
			<ul class="newsLs" style="width:247px;margin:0 auto;">
				<c:forEach items="${announceList }" var="announce">
					<li>
						<a href="${ctx}/announce/detailinfo?aaNo=${announce.aaNo }&currentMenuId=712775&currentSubMenuId=2164074&parentId=712625" class="fl ft12"  title="${announce.subject }"><c:choose>
							<c:when test="${fn:length(announce.subject)<=10}">${announce.subject }</c:when>
							<c:otherwise>${fn:substring(announce.subject, 0, 10)}...</c:otherwise>
						</c:choose></a>
						<span class="fr ft12">
						<fmt:parseDate var="create_time" value="${announce.create_time }"></fmt:parseDate>
						<fmt:formatDate value="${create_time}" pattern="yyyy-MM-dd"/>
						
						</span>
					</li>
				</c:forEach>			
			</ul>
			<a href="${ctx}/announce/showAnnounce?1=1&currentMenuId=712771&currentSubMenuId=2162678&parentId=712621" class="get-more">更多</a>
			<div class="clear"></div>
		</div>
		<div class="showImg1">		
			<div id="iview">
				<div data-iview:image="${FILE_URL }guaten/${images[0]}.jpg" style="cursor:pointer;" onclick="window.open('${links[0] }')"></div>
				<div data-iview:image="${FILE_URL }guaten/${images[1]}.jpg" style="cursor:pointer;" onclick="window.open('${links[1] }')"></div>
				<div data-iview:image="${FILE_URL }guaten/${images[2]}.jpg" style="cursor:pointer;" onclick="window.open('${links[2] }')"></div>
			</div>
		</div>
		<div class="showImg2">
			<a href="http://www.guaten.com" class="fl" target="_blank" style="display:inline-block;width:134px;height:142px;">
				<img src="${ctx}/images/guatenphotos/erweim-1.png">
			</a>
			<a href="${ctx}/loginform/help?flag=guateng" class="fr"  target="_blank" style="display:inline-block;width:143px;height:142px;">
				<img src="${ctx}/images/guatenphotos/erweim-2.png">
			</a>
		</div>
		</div>
		
	</div>
	<div class="clear"></div>
</div>


<!--
<c:if test="${guatengFlag=='Y'}">
	<div class="app-link">
		<a href="http://www.guaten.com" target="_blank">
			<img src="${ctx}/images/guaten_link.png" width="101" height="138">
		</a>
	</div>
	<div class="app-guaten">
		<a href="${ctx}/loginform/help?flag=guateng" target="_blank">
			<img src="${ctx}/images/guatenwang.png" width="101">
		</a>
	</div>
</c:if>
-->


<!--
<div class="app-down">
	<a href="http://test.guaten.com/api/app/html/download.html" target="_blank">
		<img src="${ctx}/images/guaten_down.png" width="101" height="138">
	</a>
</div>
-->


<!--
<c:if test="${isGuPiao=='Y'}">
	<div class="gp-link">
		<a href="http://joymai.oss-cn-hangzhou.aliyuncs.com/20150907%2Ffile.rar" target="_blank">
			<img src="${ctx}/images/ganggu.jpg" width="99" height="99" style="border:1px solid #FE204D;">
		</a>
	</div>
</c:if>

<div class="ax">
	<a href="${ctx}/foundationOrderform?1=1&img=11&fiNum=1&currentMenuId=2163654">
		<img src="${ctx}/images/365ax.png"  width="95" height="95">
	</a>
</div>
-->

<c:if test="${isStockAccount=='Y'}">
	<div class="stockAccount">
		<a href="${ctx}/jsysStockAccount/jsysStockAccountForm">
			<img src="${ctx}/images/baner-5-2.png" width="96" height="96">
		</a>
	</div>
</c:if>

<c:if test="${str365FTitle != null}">
	<div class="divIndex">
		<div style="overflow:hidden;"><span class="colorJH fl heart">爱心提示：</span><a href="javascript:void(0);" class="cls fr">关闭</a></div>
		<p>家基金，守护家人幸福！</p>
		<p>&nbsp;&nbsp;让爱天天住我家！</p>
		<p class="tr"><a href="${ctx}/foundationOrderform?1=1&img=11&fiNum=1&currentMenuId=2163654" class="bold">立即参与&nbsp;&gt;&gt;</a></p>
	</div>
</c:if>

<c:if test="${strNYCQualify != null}">
	<br>
	<div class="divIndex">
		<div style="overflow:hidden;"><span class="colorJH fl heart">能源车提示：</span><a href="javascript:void(0);" class="cls fr">关闭</a></div>
		<p>恭喜你获得国之道车友俱乐部资格！</p>
		<p><a href="http://www.goodnev.com/" target="_blanks">点击连接进入购买,请在一个月内加入！</a></p>
		<p>逾期视为放弃资格</p>
	</div>
	<br>
</c:if>

<c:if test="${pdSendInfoUnRead >= 1}">
	<div class="divIndex">
		<div style="overflow:hidden;">
			<span class="colorJH fl heart">收货确认提醒：</span><a href="javascript:void(0);" class="cls fr">关闭</a>
		</div>
		<p>亲爱的中脉家人，您好！您有未确认的收货信息，如有收到货，请确认！</p>
		<p class="tr">
			<a href="${ctx}/pdSendinfoQuery?1=1&1=1&parentId=712619&currentMenuId=2165109" class="bold">点击进入&nbsp;&gt;&gt;</a>
		</p>
	</div>
</c:if>







