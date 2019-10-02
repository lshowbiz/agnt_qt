<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%@ page import="com.joymain.ng.service.JsysResourceManager"%>
<%@ page import="java.util.*"%>
<%@page import="com.joymain.ng.util.StringUtil"%>
<%
//获取期别
com.joymain.ng.service.JbdPeriodManager jbdPeriodManager=(com.joymain.ng.service.JbdPeriodManager)com.joymain.ng.util.ContextUtil.getSpringBeanByName(application,"jbdPeriodManager");
com.joymain.ng.model.JbdPeriod bdPeriod=jbdPeriodManager.getBdPeriodByTime(new java.util.Date());
pageContext.setAttribute("bdPeriod",bdPeriod);
String bdWeek= bdPeriod.getFyear()+""+ (bdPeriod.getFweek()<10?"0" + bdPeriod.getFweek():bdPeriod.getFweek());
JsysResourceManager jsysResourceManager=(JsysResourceManager)com.joymain.ng.util.ContextUtil.getSpringBeanByName(application,"jsysResourceManager");

String userNameUtf="";
if(!(org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof String)){
	com.joymain.ng.model.JsysUser defSysUser=(com.joymain.ng.model.JsysUser)org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	if(!StringUtil.isEmpty(defSysUser.getUserName())){
		userNameUtf=java.net.URLEncoder.encode(defSysUser.getUserName(),"UTF-8");
	}
	
	//获取一级菜单
	String userCode = defSysUser.getUserCode();
	List topmenus = jsysResourceManager.getTopMenuByUserCode(userCode);
	session.setAttribute("topmenus", topmenus);

	String s = "'jbdMemberLinkCalcHist','jfiBankbookJournals','fiBankbookJournals','fiBcoinJournals','fiTransferAccounts'";
	List commonMenus = jsysResourceManager.getCommonMenu(s);
	session.setAttribute("commonMenus", commonMenus);
}
%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="icon" href="<c:url value="/images/favicon.ico"/>" />
<title><ng:locale key="webapp.name.qt" /></title>

<link href="<c:url value='/styles/index/style.css'/>" rel="stylesheet" type="text/css">
<link href="<c:url value='/styles/style-ng.css'/>" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="<c:url value='/styles/lib/bootstrap.min.css'/>" />

<script>
	function tohref(resCode,tourl) {
		var strurl="${ctx}/ajaxController/redirectByRes?resCode="+resCode;
			var loader = new AjaxLoader("GET",strurl);
	    var strExist = loader.getReturnText();
	    if('1'==strExist){
	    	window.location.href=tourl;
	    }
	}

	function show_Favorite(sURL, sTitle) {
		sURL = encodeURI(sURL);
		try {
			window.external.addFavorite(sURL, sTitle);
		} catch (e) {
			try {
				window.sidebar.addPanel(sTitle, sURL, "");
			} catch (e) {
				alert("加入收藏失败，请使用Ctrl+D进行添加,或手动在浏览器里进行设置.");
			}
		}
	}
	function showList(id, num) {
		if (num == 1) {
			document.getElementById(id).style.display = "block"
		} else {
			document.getElementById(id).style.display = "none"
		}
	}
	function show_index(url) {
		if (document.all) {
			document.body.style.behavior = 'url(#default#homepage)';
			document.body.setHomePage(url);
		} else {
			alert("您好,您的浏览器不支持自动设置页面为首页功能,请您手动在浏览器里设置该页面为首页!");
		}
	}
</script>

	<script>
		var navs = $("#nav1").find(".nav");
		var _parentId = $("#parentId").val();
		for(var i = 0; i< navs.length;i++){
			if (_parentId == $(navs[i]).attr("data-parentId")){
				$(navs[i]).addClass("active_curr");
			}
		}
		</script>
</head>

<body id="bodyid">
	
<div id="logo">	
	<div class="fl mt ml"><img class src="<c:url value='/images/index/logo.gif'/>"></div>
	<div class="fl dat ">
		<H2><a href="${ctx}/jbdPeriodOldAndNewWweekCom?1=1">第<%=bdPeriod.getFmonth() %>财政月
			<c:choose>
				<c:when test="${(bdPeriod.fyear*100+bdPeriod.fweek)>=201803 }">
					<fmt:formatDate var="curDates" value="${bdPeriod.startTime }" pattern="dd"/>
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
			&nbsp;&nbsp;第<%=bdWeek %>期</a></H2>
		<p class="gry2 fl mt">语言：
		<select name="lang" id="lang" class="lang-china tl" style="margin-top:-2px"><option value="1" selected="selected" >中国CHINA</option></select></p>
	</div>
	<div class="sold">	
		<div class="fr mr gry4">			
			<a href="javascript:void(0);" class="mr"><span class="mr" title="CN ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.jmiMember.petName} / ${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userCode}" id="userCard">${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userCode}</span></a>
			<a href="javascript:void(0)" onclick="tohref('002','${pageContext.request.contextPath}/jpoShoppingCartOrderLists/scAll?1=1')" target="_top"><span class="cw-icon glyphicon glyphicon-shopping-cart">&nbsp;购物车<span id='numberTop' style="color:#ed8f2d;font-weight:bold;">0</span>件&gt;&gt;&nbsp;</span></a>				
			<a href="javascript:show_index(window.location);">设为首页</a>&nbsp;|&nbsp;
			<a href="${ctx }/loginform/help?flag=newHelp&1=1" target="_blank">帮助</a>&nbsp;|&nbsp;
			<a href="http://180.101.2.122:8001/EliteWebChat/AccordNameChat.jsp?Customer_ID=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userCode}&customername=<%=userNameUtf %>" target="_blank">在线客服</a>&nbsp;|&nbsp;
			<a href="<c:url value='/logout' />">退出>></a>
		</div>
		<div class="fr mt" >
			<div id="nav1">
				<div class="nav-c">
					<a href="${ctx}/loginform/showuserinfo" class="nav fl">首   页</a>
					<c:forEach var="menu" items="${topmenus}">
					<a class='nav fl' data-parentId="${menu['RES_ID']}" href="<c:url value="${menu['RES_URL']}&parentId=${menu['RES_ID']}&currentMenuId=${menu['DEF_RESID']}&currentSubMenuId=${menu['DEF_RESID']}"/>">${menu["RES_NAME"]}</a>
					</c:forEach>
				</div>		
			</div>	
		</div>
		<input id="parentId" type="hidden" value="<%=request.getParameter("parentId")%>">
		</div>	
</div>	


<div id="list" class="fl mt">
	<a href="javascript:void(0)" onclick="tohref('myjfiBankbookJournals','${ctx}/jfiBankbookJournals?1=1');" class="icon">
		<div class="icon">
			<img class="icon-img mt" src="<c:url value='/images/index/icon1.png'/>"><br>我的钱包
		</div>
	</a>
	
	<a href="javascript:void(0)" onclick="tohref('myfiBankbookJournals','${ctx}/fiBankbookJournals?1=1');" class="icon">
		<div class="icon">
			<img class="icon-img mt" src="<c:url value='/images/index/icon2.png'/>"><br>我的基金
		</div>
	</a>
	
	<a href="javascript:void(0)" onclick="tohref('fiBcoinJournals','${ctx}/fiBcoinJournals?1=1');" class="icon">
		<div class="icon">
			<img class="icon-img mt" src="<c:url value='/images/index/icon3.png'/>"><br>我的积分
		</div>
	</a>
	
	
	<a href="javascript:void(0)" onclick="tohref('jbdMemberLinkCalcHist','${ctx}/jbdMemberLinkCalcHist?1=1');" class="icon">
		<div class="icon">
			<img class="icon-img mt" src="<c:url value='/images/index/icon4.png'/>"><br>奖金查询
		</div>
	</a>
	
	<a href="javascript:void(0)" onclick="tohref('myjfiBankbookJournals','${ctx}/fiTransferAccounts?1=1');" class="icon">
		<div class="icon">
			<img class="icon-img mt" src="<c:url value='/images/index/icon5.png'/>"><br>会员转账
		</div>
	</a>
	<c:if test="${isStockAccount=='Y'}">
		<a href="${ctx}/jsysStockAccount/jsysStockAccountForm?parentId=712625"  class="icon">
			<div class="icon">
				<img class="icon-img mt" src="<c:url value='/images/baner-5-2.png'/>"><br>港股帐号
			</div>
		</a>
	</c:if>
</div> 


<div>
	<div class="righ-box-index mt">
		<!-- <div class="banner" style="overflow:hidden"><a href=""><img style="width:100%" src="<c:url value='/images/index/banner.gif'/>"></a></div> -->
		<!-- <div class="banner" id="bannergd" style="overflow:hidden;width: 100%;"><img style="width:100%" src="../images/banner.gif"></div>-->
		<div class="banner" id="bannergd" style="overflow:hidden;width: 100%;"><jsp:include page="/scripts/indexHome.jsp" /></div>
		<div class="banner">
			<div class="fram fl">
				<h3 class="ml2"><span class="ml2 fr mr">
						<a href="javascript:void(0)" onclick="tohref('todo','${ctx}/scheduleManages/todoMore?id=${ps.id }')" style="line-height:36px" class="gry2">更多>></a>
					</span><span class="cw-icon glyphicon glyphicon-th-list">&nbsp;</span>待办事项
					
				</h3>
				<ul>
					<p class="ml2 mt"/>
					<c:set var="todosize" value="0" />
					<c:if test="${!empty psList}">
					<c:forEach items="${psList }" var="ps">
						<input type="hidden" id="id" value="${ps.id }"/>
						<c:set var="todosize" value="${todosize+1}" />
						<c:if test="${todosize<6}">
							<li href="${ctx}/loginform/scheduleInfo?id=${ps.id }" class="ml2">
								<a href="javascript:void(0)" onclick="tohref('mySchedules','${ctx}/mySchedules?statue=notyet')" style="color:black;">个人事项：</a>  
								<a href="javascript:void(0)" onclick="tohref('mySchedules','${ctx}/loginform/scheduleInfo?id=${ps.id }')" style="color:black;">
								<c:choose>
									<c:when test="${fns:isAbbreviate(ps.scheduleName, 40)}">
										${fns:abbreviate(ps.scheduleName, 40,'...')}
									</c:when>
									<c:otherwise>
										${ps.scheduleName }
									</c:otherwise>
								</c:choose>
		
								</a>
								<span class="gry fr mr" >${ps.startTime }</span>
							</li>
						</c:if>
					</c:forEach>
					</c:if>
					<c:if test="${!empty pbsList}">
					<c:forEach items="${pbsList }" var="pbs">
						<c:set var="todosize" value="${todosize+1}" />
						<c:if test="${todosize<6}">
							<li class="ml2">
								<a href="javascript:void(0)" onclick="tohref('scheduleManage','${ctx }/scheduleManages')" style="color:black;">团队活动：</a>
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
								<span class="gry fr mr">${pbs.startTime }</span>
							</li>
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${!empty noPays}">
					<c:forEach items="${noPays }" var="pay">
						<c:set var="todosize" value="${todosize+1}" />
						<c:if test="${todosize<6}">
							<c:choose>
		                   		<c:when test="${pay.PRODUCTFLAG!=null }">
		                   			<c:if test="${pay.ORDER_USER_CODE==pay.sysUser.USER_CODE}">
		                   				<li class="ml2">
			                   				<span style="color:red;">您有未支付订单：</span><a href="#" title="支付" onclick="javascript:alert('此订单为生态家套餐，请到生态家套餐进行支付！');location.href='${pageContext.request.contextPath}/showBigPage?1=1'" class="Payment">${pay.MEMBER_ORDER_NO }</a>
			                   				<span class="gry fr mr"> ${pay.ORDER_TIME }</span>
		                   				</li>
		                   			</c:if>
		                   		</c:when>
		                   		<c:otherwise>
		                   			<li class="ml2">
		                   				<a href="javascript:void(0)" onclick="tohref('002','${ctx }/jfiPay99bill?orderId=${pay.MO_ID}&flag=1')" style="color: green;">${pay.MEMBER_ORDER_NO } </a>
		                   				<span class="gry fr mr"> ${pay.ORDER_TIME }</span>
		                   			</li>
		                   		</c:otherwise>
		                    </c:choose>
		                 </c:if>				
					</c:forEach>
				</c:if>
				<c:if test="${!empty eoNoList}">
					<c:forEach items="${eoNoList }" var="eoNo">
						<c:set var="todosize" value="${todosize+1}" />
						<c:if test="${todosize<6}">
		                	<li class="ml2">
		               				<span style="color:red;">您有未支付自助换货单：</span>
		               				<a  href="javascript:void(0)" onclick="tohref('queryExchangeOrderList','${ctx}/pdExchangeOrderform/queryExchangeOrderList?1=1')" style="color: green;">${eoNo.eo_no } </a>
		               				<span class="gry fr mr"></span>
		                	</li>
		                </c:if>
					</c:forEach>
				</c:if>
				<c:if test="${strNYCQualify != null}">	
					<c:set var="todosize" value="${todosize+1}" />
					<c:if test="${todosize<6}">
	                	<li class="ml2">
	               				<span style="color:red;">能源车提示：</span>
	               				<a  href="http://www.goodnev.com/" target="_blanks" title="恭喜你获得国之道车友俱乐部资格！点击连接进入购买,请在一个月内加入！逾期视为放弃资格" style="color: green;">
	               				<c:choose>
									<c:when test="${fns:isAbbreviate('恭喜你获得国之道车友俱乐部资格！点击连接进入购买,请在一个月内加入！逾期视为放弃资格', 40)}">
										${fns:abbreviate('恭喜你获得国之道车友俱乐部资格！点击连接进入购买,请在一个月内加入！逾期视为放弃资格', 40,'...')}
									</c:when>
									<c:otherwise>
										恭喜你获得国之道车友俱乐部资格！点击连接进入购买,请在一个月内加入！逾期视为放弃资格
									</c:otherwise>
								</c:choose>
	               				</a>
	               				<span class="gry fr mr"></span>
	                	</li>
	                </c:if>
				</c:if>
				<c:if test="${pdSendInfoUnRead >= 1}">
					<c:set var="todosize" value="${todosize+1}" />
					<c:if test="${todosize<6}">
	                	<li class="ml2">
	              				<span style="color:red;">收货确认提醒：</span>
	              				<a  href="javascript:void(0)" onclick="tohref('pdSendinfoQuery','${ctx}/pdSendinfoQuery')" title="亲爱的中脉家人，您好！您有未确认的收货信息，如有收到货，请确认！" style="color: green;">
	              				<c:choose>
								<c:when test="${fns:isAbbreviate('亲爱的中脉家人，您好！您有未确认的收货信息，如有收到货，请确认！', 40)}">
									${fns:abbreviate('亲爱的中脉家人，您好！您有未确认的收货信息，如有收到货，请确认！', 40,'...')}
								</c:when>
								<c:otherwise>
									亲爱的中脉家人，您好！您有未确认的收货信息，如有收到货，请确认！
								</c:otherwise>
							</c:choose>
	              				</a>
	              				<span class="gry fr mr"></span>
	                	</li>
	                </c:if>
                </c:if>
				</ul>
				<c:if test="${todosize==0 }">
					<p class="ml2" ><span class="gry">您还没有私人待办事项，请到<a href="javascript:void(0)" onclick="tohref('scheduleManage','${ctx}/scheduleManages?1=1')" style="color:red;">日程管理</a>处添加！</span></p>
				</c:if>
			</div>
			<div class="fram fr ml">
				<h3 class="ml2"><span class="ml2 fr mr">
						<a href="javascript:void(0)" onclick="tohref('showAnnounce','${ctx}/announce/showAnnounce?1=1')" style="line-height:36px" class="gry2">更多>></a>
					</span><span class="cw-icon glyphicon glyphicon-bullhorn">&nbsp;</span>业务公告
					
				</h3>
				
				<ul>
					<p class="ml2 mt"/>
					<c:forEach items="${announceList }" var="announce">
						<li class="ml2">
							<a href="javascript:void(0)" onclick="tohref('showAnnounce','${ctx}/announce/detailinfo?aaNo=${announce.aaNo }')" title="${announce.subject }">
								<c:choose>
									<c:when test="${fns:isAbbreviate(announce.subject, 40)}">
										${fns:abbreviate(announce.subject, 40,'...')}
									</c:when>
									<c:otherwise>
										${announce.subject }
									</c:otherwise>
								</c:choose>
							</a>
							<span class="gry fr mr">
							<fmt:parseDate var="create_time" value="${announce.create_time }"></fmt:parseDate>
							<fmt:formatDate value="${create_time}" pattern="yyyy-MM-dd"/>
							</span>
						</li>
					</c:forEach>			
				</ul>				
			</div>
	
			<div class="fram fl">
				<h3 class="ml2"><span class="cw-icon glyphicon glyphicon-bell">&nbsp;</span>业务提醒</h3>
				<p class="ml2 mt"><a href="javascript:void(0);" id="b_js_higher">${recentlyList[1].aa }财月 如何冲刺更高奖衔</a></p>
				<p class="ml2"><a href="javascript:void(0);" class="higher colorQL" id="js_higher">${recentlyList[0].aa }财月 如何冲刺更高奖衔</a></p>
				<%--<p class="ml2">以上数据考核周期为&nbsp;${indexMap.startTime } - ${indexMap.endTime }</p>--%>
				<p class="ml2"><span class="gry">此数据为目前预算信息，尚未最后结算，仅供参考！</span></p>
				<c:if test="${not empty tips4565Package }">
					<p class="ml2"><span class="gry">${tips4565Package }</span></p>
				</c:if>
			</div>
			<div class="fram fr ml">
				<h3 class="ml2"><span class="cw-icon glyphicon glyphicon-volume-up">&nbsp;</span>消息提醒</h3>
				<p class="ml2 mt"><a href="javascript:void(0)" onclick="tohref('showAnnounce','${ctx}/announce/showAnnounce?1=1')" style="color:black;">您有&nbsp;${indexMap.notRead }&nbsp;个公司公告尚未阅读。</a></p>
				<p class="ml2"><a href="javascript:void(0)" onclick="tohref('showMessage','${ctx}/showMessage?1=1')" style="color:black;">您有&nbsp;${indexMap.notReadMesSize }&nbsp;个新的业务咨询已经得到回复。</a></p>
			</div>
			<div class="cl"></div>
			</div>
		</div>
	</div>	
</div>
	


<div class="ad-right">
		<%-- <a href="" target="blank"><img src="<c:url value='/images/index/ad1.gif'/>"></a>
		<a href="" target="blank"><img class="mt20" src="<c:url value='/images/index/ad2.gif'/>"></a> --%>
	 	<a href="${ctx}/foundationOrderform?1=1&parentId=2163653&img=11&fiNum=1&currentMenuId=2163654"><img src="<c:url value='/styles/images/baner-3.png'/>" style="width:220px;height:110px;"></a> 
		<!-- <a href="http://www.guaten.com" target="_blank"><img src="${ctx}/images/guatenphotos/erweim-1.png" class="mt20" style="width:221px;height:110px;"></a> -->
		
		<a href="javascript:void(0);" ><img id="runImg" class="mt" src="<c:url value='/images/index/ad7.png'/>"></a>
<!-- 		<script>
		    var mydiv = document.getElementById('runImg');
		    var imgArray = ['ad5.gif', 'ad6.gif']
		    var i = -1;
		    setInterval(function () {
		        i++;
		        i %= imgArray.length;
		        mydiv.src ="<c:url value='/images/index/" + imgArray[i] + "'/>";
		    }, 3000);
		</script> -->
			<a href="javascript:void(0);" ><img  class="mt" src="<c:url value='/images/index/ad4.gif'/>"></a>
			<a href="http://www.guaten.com" class="fl" target="_blank" style="display:inline-block;" >
					<img  src="${ctx}/images/guatenphotos/erweim-1.png" class="mt" style="width: 110px;height: 110px">
				</a> 
				<a href="${ctx}/loginform/help?flag=guateng" target="_blank" style="display:inline-block;">
					<img  src="${ctx}/images/guatenphotos/erweim-2.png" class="mt" style="width: 110px;height: 110px">
			</a>
</div>	
</body>

<%--<c:if test="${not empty fiCommonAddr }">
	<jsp:include page="/common/fi_common_addr.jsp" />
</c:if> 
	
<c:if test="${not empty easyPwdStr }">
	<jsp:include page="/common/sys_modify_pwd.jsp" />
</c:if>
<c:if test="${not empty papernumberFill   }">
	<jsp:include page="/common/mi_papernumber.jsp" />
</c:if>--%>

<c:choose>

	<c:when test="${not empty fiCommonAddr }">
		<jsp:include page="/common/fi_common_addr.jsp" />
	</c:when>

	<c:when test="${not empty easyPwdStr }">
		<jsp:include page="/common/sys_modify_pwd.jsp" />
	</c:when>
	<c:when test="${not empty papernumberFill   }">
		<jsp:include page="/common/mi_papernumber.jsp" />
	</c:when>
</c:choose>



<script src="<c:url value='/scripts/lib/jquery-1.8.2.min.js'/>"></script>
<script src="<c:url value='/scripts/lib/plugins/jquery.cookie.js'/>"></script>
<script src="<c:url value='/scripts/script.js'/>"></script>
<script src="<c:url value='/scripts/joyLife.js'/>"></script>
<script src="<c:url value='/scripts/dialogBox.js'/>"></script>	
<script src="<c:url value='/scripts/raphael-min.js'/>"></script>	
<script src="<c:url value='/scripts/jquery.easing.js'/>"></script>	
<script src="<c:url value='/scripts/iview.pack.js'/>"></script>	
<script src="<c:url value='/scripts/AjaxLoader.js'/>"></script>	
<script src="<c:url value='/dwr/util.js'/>"></script>
<script src="<c:url value='/dwr/engine.js'/>"></script>
<script src="<c:url value='/dwr/interface/jmiMemberManager.js'/>"></script>
<link rel="stylesheet" href="<c:url value='/styles/index/dialog.css'/>" />


<script>
	var $ = jQuery.noConflict();
	function toolkit(){
		if( $('.divIndex').length == 2 ){
			$('.divIndex:eq(1)').css({'bottom':'132px'});
		}
	}
	
	function callBackJshigher(indexMap){
		if(indexMap.is_policy_month=='0'){
			 con =   '<table width="97%" border="1" class="tabRank"tabRank>'+
			'<colgroup style="width:200px;"></colgroup>'+
			'<colgroup></colgroup>'+
			'<colgroup style="width:250px;"></colgroup>'+
			'<colgroup></colgroup>'+
			'<tbody id="con_tbody" >'+
				'<tr>'+
					'<td class="tr">您目前的奖衔:</td>'+
					'<td>{10}</td>'+
					
					'<td class="tr">完成如下业绩可升至:</td>'+
					
					
					'<td>{11}</td>'+ 
				'</tr>'+

				'<tr>'+
					'<td class="tr">您目前个人责任消费:</td>'+
					'<td>{12}PV</td>'+
					'<td class="tr">还需完成责任消费为:</td>'+
					'<td>{13}PV</td>'+
				'</tr>'+
				

				'<tr>'+
					'<td class="tr">还需完成冠军部门业绩为:</td>'+
					'<td>{16}PV</td>'+
					'<td class="tr">还需完成其余部门业绩为:</td>'+
					'<td>{17}PV</td>'+
				'</tr>'+

				'<tr>'+
					' <td colspan="4"><b>实际完成业绩</b></td>'+
				'</tr>'+


				/* '<tr>'+
					'<td class="tr">您目前整组业绩（金额）:</td>'+
					'<td>{18}元</td>'+
					'<td class="tr">您目前整组业绩（PV）:</td>'+
					'<td>{19}PV</td>'+
				'</tr>'+ */

				'<tr>'+
				'<td class="tr">冠军部门业绩（PV）:</td>'+
				'<td>{8}PV</td>'+
				'<td class="tr">其余部门业绩（PV）:</td>'+
				'<td>{4}PV</td>'+
				'</tr>'+
            <c:if test="${recentlyList[0].aa < 201905 }">
				'<tr>'+
					' <td colspan="4"><b>创业保障奖</b></td>'+
				'</tr>'+
				
				'<tr>'+
				'<td colspan="2">领取会员编号</td>'+
				'<td colspan="2">创业保障奖(元)</td>'+
				'</tr>'+
                 '<tr>'+
                 '<td colspan="4">说明：创业保障奖为历史至今总收入</br><b>以上数据考核周期为 {14} - {15}</b></td>'+
                 '</tr>'+
			 </c:if>
                 <c:if test="${recentlyList[0].aa >= 201905 }">
                 '<tr>'+
				 ' <td colspan="4"><b>推广奖</b></td>'+
				 '</tr>'+


				 '<tr>'+
				 '<td colspan="2">领取会员编号</td>'+
				 '<td colspan="2">推广奖(PV)</td>'+
				 '</tr>'+
                 '<tr>'+
                 '<td colspan="4">说明：推广奖为历史至今总收入</br><b>以上数据考核周期为 {14} - {15}</b></td>'+
                 '</tr>'+
                 </c:if>

				'<tr>'+
					'<td colspan="4" align="right" class="color1">此数据为目前预算信息，尚未最后结算，仅供参考！</td>'+
				'</tr>'+
				
			'</tbody>'+
		'</table>';
		}
		MyDialog({
			boxWidth 	: 861,
			boxContent 	: String.format(con,indexMap.cur_member_level,indexMap.next_member_level,indexMap.pre_min_total,indexMap.cur_min_total,indexMap.cur_month_add,indexMap.min_need_pv,indexMap.pre_max_total,indexMap.cur_max_total,indexMap.cur_month_max_add,indexMap.max_need_pv,indexMap.cur_pass_star,indexMap.next_pass_star,indexMap.cur_pa_duty,indexMap.need_pa_duty,indexMap.checkStime,indexMap.checkEndTime,indexMap.max_pv_need,indexMap.min_pv_need,indexMap.actual_max_pv,indexMap.actual_min_pv,indexMap.is_policy_month)
		});
      <c:if test="${recentlyList[0].aa < 201905 }">
			$.each(indexMap.fb_list,function(n,value){
				var newRow="<tr><td colspan='2'>"+value.REUSER_CODE+"</td><td colspan='2'>"+value.FB_MONEY+"</td></tr>";
				$('#con_tbody').children('tr').eq(6).after(newRow);
			 }
			);
	  </c:if>
      <c:if test="${recentlyList[0].aa >= 201905 }">
		  $.each(indexMap.recommend_list,function(n,value){
				var newRow="<tr><td colspan='2'>"+value.REUSER_CODE+"</td><td colspan='2'>"+value.RECOMMEND_PV+"</td></tr>";
				$('#con_tbody').children('tr').eq(6).after(newRow);
			  }
		  );
	  </c:if>
	}		

	function callBackBJshigher(indexMap){
		
		if(indexMap.is_policy_month=='1'){
			 b_con =   '<table width="97%" border="0" class="tabRank">'+
			'<colgroup style="width:200px;"></colgroup>'+
			'<colgroup></colgroup>'+
			'<colgroup style="width:250px;"></colgroup>'+
			'<colgroup></colgroup>'+
			'<tbody id="con_tbody">'+
				'<tr>'+
					'<td class="tr">您目前的奖衔:</td>'+
					'<td>{10}</td>'+
					
					'<td class="tr">完成如下业绩可升至:</td>'+
					
					
					'<td>{11}</td>'+ 
				'</tr>'+

				'<tr>'+
					'<td class="tr">您目前个人责任消费:</td>'+
					'<td>{12}PV</td>'+
					'<td class="tr">还需完成责任消费为:</td>'+
					'<td>{13}PV</td>'+
				'</tr>'+
				

				'<tr>'+
					'<td class="tr">还需完成冠军部门业绩为:</td>'+
					'<td>{16}PV</td>'+
					'<td class="tr">还需完成其余部门业绩为:</td>'+
					'<td>{17}PV</td>'+
				'</tr>'+

				'<tr>'+
					' <td colspan="4"><b>实际完成业绩</b></td>'+
				'</tr>'+ 



				<c:if test="${recentlyList[1].aa >= 201701 }">
					/* '<tr>'+
						'<td class="tr">您目前整组业绩（金额）:</td>'+
						'<td>{18}元</td>'+
						'<td class="tr">您目前整组业绩（PV）:</td>'+
						'<td>{19}PV</td>'+
					'</tr>'+ */
					'<tr>'+
					'<td class="tr">冠军部门业绩（PV）:</td>'+
					'<td>{8}PV</td>'+
					'<td class="tr">其余部门业绩（PV）:</td>'+
					'<td>{4}PV</td>'+
					'</tr>'+
					



				</c:if>
                 <c:if test="${recentlyList[1].aa < 201905 }">
                 '<tr>'+
						' <td colspan="4"><b>创业保障奖</b></td>'+
					'</tr>'+


					'<tr>'+
					'<td colspan="2">领取会员编号</td>'+
					'<td colspan="2">创业保障奖(元)</td>'+
					'</tr>'
                 </c:if>

				 <c:if test="${recentlyList[1].aa >= 201905 }">
					 '<tr>'+
					 ' <td colspan="4"><b>推广奖</b></td>'+
					 '</tr>'+


					 '<tr>'+
					 '<td colspan="2">领取会员编号</td>'+
					 '<td colspan="2">推广奖(PV)</td>'+
					 '</tr>'+
				 </c:if>

				<c:if test="${recentlyList[1].aa < 201701  }">

					'<tr>'+
						'<td class="tr">您目前冠军部门新增业绩为:</td>'+
						'<td>{18}PV</td>'+
						'<td class="tr">您目前的其余部门网络新增业绩之和:</td>'+
						'<td>{19}PV</td>'+
					'</tr>'+

				</c:if>

			
				
				'<tr>'+
					' <td colspan="4"><b>政策达成业绩</b></td>'+
				'</tr>'+
				
				'<tr>'+
					'<td class="tr">您目前冠军部门新增业绩为:</td>'+
					'<td>{8}PV</td>'+
					'<td class="tr">您目前的其余部门网络新增业绩之和:</td>'+
					'<td>{4}PV</td>'+
				'</tr>'+

               <c:if test="${recentlyList[1].aa < 201905  }">
				'<tr>'+
					'<td colspan="4">说明：创业保障奖为历史至今总收入</br><b>以上数据考核周期为 {14} - {15}</b></td>'+
				'</tr>'+
				</c:if>
				 <c:if test="${recentlyList[1].aa >= 201705  }">
				 '<tr>'+
				 '<td colspan="4">说明：推广奖奖为历史至今总收入</br><b>以上数据考核周期为 {14} - {15}</b></td>'+
				 '</tr>'+
				 </c:if>
				'<tr>'+
					'<td colspan="4" align="right" class="color1">此数据为目前预算信息，尚未最后结算，仅供参考！</td>'+
				'</tr>'+
				
			'</tbody>'+
		'</table>';
		}
		MyDialog({
			boxWidth 	: 861,
			boxContent 	: String.format(b_con,indexMap.b_cur_member_level,indexMap.b_next_member_level,indexMap.b_pre_min_total,indexMap.b_cur_min_total,indexMap.b_cur_month_add,indexMap.b_min_need_pv,indexMap.b_pre_max_total,indexMap.b_cur_max_total,indexMap.b_cur_month_max_add,indexMap.b_max_need_pv,indexMap.b_cur_pass_star,indexMap.b_next_pass_star,indexMap.b_cur_pa_duty,indexMap.b_need_pa_duty,indexMap.b_checkStime,indexMap.b_checkEndTime,indexMap.b_max_pv_need,indexMap.b_min_pv_need,indexMap.b_actual_max_pv,indexMap.b_actual_min_pv,indexMap.b_is_policy_month)
		});
		
		

		<c:if test="${recentlyList[1].aa < 201905 }">
		$.each(indexMap.fb_list,function(n,value){
			var newRow="<tr><td colspan='2'>"+value.REUSER_CODE+"</td><td colspan='2'>"+value.FB_MONEY+"</td></tr>";
			$('#con_tbody').children('tr').eq(6).after(newRow);
   		 }
		);
		</c:if>

      <c:if test="${recentlyList[1].aa >= 201905 }">
      $.each(indexMap.recommend_list,function(n,value){
            var newRow="<tr><td colspan='2'>"+value.REUSER_CODE+"</td><td colspan='2'>"+value.RECOMMEND_PV+"</td></tr>";
            $('#con_tbody').children('tr').eq(6).after(newRow);
          }
      );
      </c:if>

	} 
	
	function popupFiAddr(){
		var $mask = $('#mask_if');
		var $popupAddr = $('#popupAddr_if');
		
		$mask.show().css({
			height:window.screen.availHeight
		});
		$popupAddr.css({

			left:( $(window).width() - $popupAddr.outerWidth() ) / 2,
			top:( $(window).height() - $popupAddr.outerHeight() ) / 2,
			zIndex:"9999",
			background:"#FFF"
		}).animate({
			opacity:"show"
		},600);
	}
    function popupPapernumber(){
      var $mask = $('#mask_if');
      var $popupAddr = $('#popupPapernumber_if');

      $mask.show().css({
        height:window.screen.availHeight
      });
      $popupAddr.css({

        left:( $(window).width() - $popupAddr.outerWidth() ) / 2,
        top:( $(window).height() - $popupAddr.outerHeight() ) / 2,
        zIndex:"9999",
        background:"#FFF"
      }).animate({
        opacity:"show"
      },600);
    }
	function popupModifyPwd(){
		var $mask = $('#mask_if');
		var $popupAddr = $('#popupPwd_if');

		$mask.show().css({
			height:window.screen.availHeight
		});

		$popupAddr.css({

			left:( $(window).width() - $popupAddr.outerWidth() ) / 2,
			top:( $(window).height() - $popupAddr.outerHeight() ) / 2,
			zIndex:"9999",
			background:"#FFF"
		}).animate({
			opacity:"show"
		},600);	
	}

	

	var con =   '<table border="1" width="97%" class="tabRank">'+
					'<colgroup style="width:200px;"></colgroup>'+
					'<colgroup></colgroup>'+
					'<colgroup style="width:250px;"></colgroup>'+
					'<colgroup></colgroup>'+
					'<tbody id="con_tbody">'+
						'<tr>'+
							'<td class="tr">您目前的奖衔:</td>'+
							'<td>{10}</td>'+
							
							'<td class="tr">完成如下业绩可升至:</td>'+
							
							
							'<td>{11}</td>'+ 
						'</tr>'+

						'<tr>'+
							'<td class="tr">您目前个人责任消费:</td>'+
							'<td>{12}PV</td>'+
							'<td class="tr">还需完成责任消费为:</td>'+
							'<td>{13}PV</td>'+
						'</tr>'+
						

						'<tr>'+
							'<td class="tr">还需完成冠军部门业绩为:</td>'+
							'<td>{16}PV</td>'+
							'<td class="tr">还需完成其余部门业绩为:</td>'+
							'<td>{17}PV</td>'+
						'</tr>'+

						'<tr>'+
							' <td colspan="4"><b>实际完成业绩</b></td>'+
						'</tr>'+



					/* 	'<tr>'+
							'<td class="tr">您目前整组业绩（金额）:</td>'+
							'<td>{18}元</td>'+
							'<td class="tr">您目前整组业绩（PV）:</td>'+
							'<td>{19}PV</td>'+
						'</tr>'+ */
						
						'<tr>'+
						'<td class="tr">冠军部门业绩（PV）:</td>'+
						'<td>{8}PV</td>'+
						'<td class="tr">其余部门业绩（PV）:</td>'+
						'<td>{4}PV</td>'+
						'</tr>'+
						
						'<tr>'+
							' <td colspan="4"><b>创业保障奖</b></td>'+
						'</tr>'+
						

						'<tr>'+
						'<td colspan="2">领取会员编号</td>'+
						'<td colspan="2">创业保障奖(元)</td>'+
						'</tr>'+
						
						
						'<tr>'+
							' <td colspan="4"><b>政策达成业绩</b></td>'+
						'</tr>'+
						
						'<tr>'+
							'<td class="tr">您目前冠军部门新增业绩为:</td>'+
							'<td>{8}PV</td>'+
							'<td class="tr">您目前的其余部门网络新增业绩之和:</td>'+
							'<td>{4}PV</td>'+
						'</tr>'+
						
						
						'<tr>'+
							'<td colspan="4">说明：创业保障奖为历史至今总收入</br><b>以上数据考核周期为 {14} - {15}</b></td>'+
						'</tr>'+
						'<tr>'+
							'<td colspan="4" align="right" class="color1">此数据为目前预算信息，尚未最后结算，仅供参考！</td>'+
						'</tr>'+
						
					'</tbody>'+
				'</table>';

				




				var b_con =   '<table width="97%" border="1" class="tabRank">'+
				'<colgroup style="width:200px;"></colgroup>'+
				'<colgroup></colgroup>'+
				'<colgroup style="width:250px;"></colgroup>'+
				'<colgroup></colgroup>'+
				'<tbody id="con_tbody">'+
					'<tr>'+
						'<td class="tr">您目前的奖衔:</td>'+
						'<td>{10}</td>'+
						
						'<td class="tr">完成如下业绩可升至:</td>'+
						
						
						'<td>{11}</td>'+ 
					'</tr>'+

					'<tr>'+
						'<td class="tr">您目前个人责任消费:</td>'+
						'<td>{12}PV</td>'+
						'<td class="tr">还需完成责任消费为:</td>'+
						'<td>{13}PV</td>'+
					'</tr>'+
					

					'<tr>'+
						'<td class="tr">还需完成冠军部门业绩为:</td>'+
						'<td>{16}PV</td>'+
						'<td class="tr">还需完成其余部门业绩为:</td>'+
						'<td>{17}PV</td>'+
					'</tr>'+

					'<tr>'+
						' <td colspan="4"><b>实际完成业绩</b></td>'+
					'</tr>'+ 


					<c:if test="${recentlyList[1].aa >= 201701 }">
					/* '<tr>'+
						'<td class="tr">您目前整组业绩（金额）:</td>'+
						'<td>{18}元</td>'+
						'<td class="tr">您目前整组业绩（PV）:</td>'+
						'<td>{19}PV</td>'+
					'</tr>'+ */
					
					'<tr>'+
					'<td class="tr">冠军部门业绩（PV）:</td>'+
					'<td>{8}PV</td>'+
					'<td class="tr">其余部门业绩（PV）:</td>'+
					'<td>{4}PV</td>'+
					'</tr>'+
					




					
				</c:if>
				<c:if test="${recentlyList[1].aa >= 201905  }">
                    '<tr>'+
                    ' <td colspan="4"><b>推广奖</b></td>'+
                    '</tr>'+


                    '<tr>'+
                    '<td colspan="2">领取会员编号</td>'+
                    '<td colspan="2">推广奖(PV)</td>'+
                    '</tr>'+
				</c:if>
				<c:if test="${recentlyList[1].aa < 201905  }">
                    '<tr>'+
					' <td colspan="4"><b>创业保障奖</b></td>'+
					'</tr>'+
					'<tr>'+
					'<td colspan="2">领取会员编号</td>'+
					'<td colspan="2">创业保障奖(元)</td>'+
					'</tr>'+
				</c:if>

				<c:if test="${recentlyList[1].aa < 201701  }">

					'<tr>'+
						'<td class="tr">您目前冠军部门新增业绩为:</td>'+
						'<td>{18}PV</td>'+
						'<td class="tr">您目前的其余部门网络新增业绩之和:</td>'+
						'<td>{19}PV</td>'+
					'</tr>'+

				</c:if>

                    <c:if test="${recentlyList[1].aa < 201905  }">
						'<tr>'+
						'<td colspan="4">说明：创业保障奖为历史至今总收入</br><b>以上数据考核周期为 {14} - {15}</b></td>'+
						'</tr>'+
                    </c:if>
                    <c:if test="${recentlyList[1].aa >= 201905  }">
						'<tr>'+
						'<td colspan="4">说明：推广奖为历史至今总收入</br><b>以上数据考核周期为 {14} - {15}</b></td>'+
						'</tr>'+
                    </c:if>


					'<tr>'+
						'<td colspan="4" align="right" class="color1">此数据为目前预算信息，尚未最后结算，仅供参考！</td>'+
					'</tr>'+
					
				'</tbody>'+
			'</table>';


	$(function(){

		$("#js_higher").click(function(){
			jmiMemberManager.getBonsuStarView(${recentlyList[0].aa },'0','1',callBackJshigher);
		});

		$("#b_js_higher").click(function(){
			jmiMemberManager.getBonsuStarView(${recentlyList[1].aa },'0','2',callBackBJshigher);
		});

		$(".cls").click(function(){
			$(this).closest(".divIndex").animate({ opacity:"hide" },400,function(){
				if( $(".divIndex:visible") ){
					$(".divIndex:visible").animate({bottom:"30px"},400);
				}
			});
		});
		$('#iview').iView({
			pauseTime: 3000,
			pauseOnHover: true,
			directionNavHoverOpacity: 0
		});
		toolkit();
	});


	String.format = function() {
		if( arguments.length == 0 )
			return null; 

		var str = arguments[0]; 
		for(var i=1;i<arguments.length;i++) {
			var re = new RegExp('\\{' + (i-1) + '\\}','gm');
			str = str.replace(re, arguments[i]);
		}
		return str;
	} 

	<c:if test="${not empty bankinfo  || not empty clAddress }">
	var str='';

	<c:if test="${not empty bankinfo }">
		str+='<ng:locale key="bankinfo.member.new"/>';
	</c:if>

	<c:if test="${not empty bankinfo }">
		str+='\n';
	</c:if>

	<%--<c:if test="${not empty activeinfo }">
		str+='${activeinfo}';
	</c:if>--%>

	/* <c:if test="${not empty clAddress }">
		str+='\n';
		str+='<ng:locale key="${clAddress}"/>';
	</c:if> */
	alert(str);
	</c:if>


	<c:if test="${not empty ylMemberInfo }">>
	alert('<ng:locale key="ylMemberInfo.info"/>');
	</c:if>


	<c:if test="${not empty memberFreeze }">
		alert('<ng:locale key="memberFreeze.tips" args="${memberFreeze }" />')
	</c:if>

	<c:if test="${not empty memberFreeze1 }">
		alert('<ng:locale key="${memberFreeze1 }" />')
	</c:if>

	<c:if test="${not empty addrTips }">
		alert('<ng:locale key="jmiSubStore.addrTips"/>')
	</c:if>

	<c:if test="${not empty memberPV10 }">
		alert('<ng:locale key="order.10.chongxiao"/>')
	</c:if>

	<c:if test="${not empty tips3276 }">
		alert('<ng:locale key="tips3276" args="${tips3276 }" />')
	</c:if>

	<c:if test="${not empty othersTips }">
		alert('<ng:locale key="jmiSubStore.othersTips"/>')
	</c:if>

	/* <c:if test="${not empty jmiStoreTips }">
		alert('<ng:locale key="jmiStore.orderTips"/>')
	</c:if> */

	<c:if test="${not empty upgradetipsTips }">
		alert('${upgradetipsTips}')
	</c:if>

	<c:if test="${not empty jmiStoreAddrTips }">
		alert('<ng:locale key="jmiStore.jmiStoreAddrTips"/>')
	</c:if>


	<c:if test="${not empty specialTips }">
	alert('${specialTips }');
	</c:if>

	<c:if test="${not empty passStarStrTips }">
	alert('${passStarStrTips }');
	</c:if>

	<c:if test="${not empty fiCommonAddr }">
		popupFiAddr();
	</c:if>

    <c:if test="${not empty papernumberFill  }">
    	popupPapernumber();
    </c:if>

	popupModifyPwd();

	<c:if test="${not empty notice }">
		open('<c:url value="/loginform/notice"/>');
	</c:if>
	//显示购物车数量
	function showCartNumber(){
		$.get("<c:url value='/jpoMemberShopCartNum'/>",function(data){
			 $("#numberTop").html(data);
		}); 
	}
	showCartNumber();
	
	
	
	
</script>
	
<%=(request.getAttribute("scripts") != null) ? request.getAttribute("scripts") : ""%>
	

