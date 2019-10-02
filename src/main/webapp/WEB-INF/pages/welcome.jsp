<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en-US">
<head>
	<meta charset="UTF-8">
	<link rel="icon" href="<c:url value="/images/favicon.ico"/>" />
	<title><ng:locale key="webapp.name.qt" /></title>
	<link rel="stylesheet" href="<c:url value='/styles/style-ng.css'/>" />
	<style>
		*{outline: none;}a:visited{color:#4261a1}.tr{text-align:right!important}.divIndex{position:fixed;right:0;bottom:30px;background-color:#badae4;border:1px solid #c6c6c6;width:345px;height:90px;padding:5px;-webkit-border-radius:8px 0 0 0;-moz-border-radius:8px 0 0 0;border-radius:8px 0 0 0}.divIndex span{line-height:180%;font-weight:bold}.divIndex p{line-height:160%;text-indent:2em;color:#666}.ax,.app-link,.app-down,.app-guaten,.gp-link{position:absolute;left:-120px;top:27px}.ax{top:271px;width:99px;border:1px solid #fe204d}.app-guaten{top:168px}.gp-link{top:370px}.iviewSlider{overflow:hidden}#iview-timer{position:absolute;z-index:100;border-radius:5px;cursor:pointer}#iview-timer div{border-radius:3px}#iview-preloader{position:absolute;z-index:1000;-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:3px;border:#000 1px solid;padding:1px;width:100px;height:3px}#iview-preloader div{float:left;-webkit-border-radius:2px;-moz-border-radius:2px;border-radius:2px;height:3px;background:#000;width:0}.iview-strip{display:block;position:absolute;z-index:5}.iview-block{display:block;position:absolute;z-index:5}.iview-directionNav a{position:absolute;top:45%;z-index:9;cursor:pointer}.iview-prevNav{left:0}.iview-nextNav{right:0}.iview-controlNav{position:absolute;z-index:9}.iview-controlNav a{z-index:9;cursor:pointer}.iview-controlNav a.active{font-weight:bold}.iview-controlNav .iview-items ul{list-style:none}.iview-controlNav .iview-items ul li{display:inline;position:relative}.iview-controlNav .iview-tooltip{position:absolute}.iview-caption{position:absolute;z-index:4;overflow:hidden;cursor:default}.iview-video-show{background:#000;position:absolute;width:100%;height:100%;z-index:101}.iview-video-show .iview-video-container{position:relative;width:100%;height:100%}.iview-video-show .iview-video-container a.iview-video-close{position:absolute;right:10px;top:10px;background:#222;color:#FFF;height:20px;width:20px;text-align:center;line-height:29px;font-size:22px;font-weight:bold;overflow:hidden;-webkit-border-radius:15px;-moz-border-radius:15px;border-radius:15px}.iview-video-show .iview-video-container a.iview-video-close:hover{background:#444}#iview{display:block;background:#000;background:rgba(0,0,0,0.7);position:relative;overflow:hidden}#iview .iviewSlider{display:block;width:281px;height:142px;overflow:hidden}#iview .iview-controlNav{position:absolute;bottom:20px;left:418px}#iview .iview-controlNav a{text-indent:-9999px}#iview .iview-controlNav a.iview-control{padding:0;float:left;width:11px;height:11px;background:url("<c:url value='/styles/img/bullets_2.png'/>") no-repeat 0 0;line-height:0;margin-right:7px}#iview .iview-controlNav a.iview-control.active{background-position:0 -11px}#iview div.iview-directionNav{position:absolute;top:60px;left:0;z-index:9;width:100%}#iview div.iview-directionNav a{display:block;cursor:pointer;position:absolute;width:27px;height:27px;background:url("<c:url value='/styles/img/bg_direction_nav.png'/>");text-indent:-9999px;overflow:hidden}#iview div.iview-directionNav a.iview-nextNav{right:0;background-position:27px 0}#iview div.iview-directionNav a.iview-prevNav{left:0;background-position:0 0}
	</style>
</head> 
<body>
	
	<div class="header">
		<jsp:include
			page="/common/header_top.jsp?userCode=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}" />
		<jsp:include
			page="/common/header_mid.jsp?userCode=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}" />
		<jsp:include
			page="/common/top_nav.jsp?userCode=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}" />
	</div>
	<div class="main centerDiv" style="position:relative;min-height:600px;">
		<jsp:include page="/WEB-INF/pages/welContent.jsp"/>
	</div>
	<%-- <script src="http://chat.53kf.com/kf.php?arg=zm2009&style=1&charset=utf-8&username=${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userCode}-${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.userName}"></script> --%>
		 
    
	<%@ include file="/common/messages.jsp" %>
	<div class="footer">
		<div class="centerDiv clearfix">
			<div class="fl">
				Login Time:<span><fmt:formatDate value="${loginCurDate }" pattern="yyyy-MM-dd HH:mm:ss"/></span>&nbsp;<span>(Hong Kong Time)</span>
			</div>
			<div class="fr" style="margin-right:80px;">JM International&nbsp;2010-2013</div>
		</div>
	</div>
	
<c:if test="${not empty fiCommonAddr }">
	<jsp:include page="/common/fi_common_addr.jsp" />
</c:if> 
	
<c:if test="${not empty easyPwdStr }">
	<jsp:include page="/common/sys_modify_pwd.jsp" />
</c:if>
	
<script src="<c:url value='/scripts/lib/jquery-1.8.2.min.js'/>"></script>
<script src="<c:url value='/scripts/lib/plugins/jquery.cookie.js'/>"></script>
<script src="<c:url value='/scripts/script.js'/>"></script>
<script src="<c:url value='/scripts/joyLife.js'/>"></script>
<script src="<c:url value='/scripts/dialogBox.js'/>"></script>	
<script src="<c:url value='/scripts/raphael-min.js'/>"></script>	
<script src="<c:url value='/scripts/jquery.easing.js'/>"></script>	
<script src="<c:url value='/scripts/iview.pack.js'/>"></script>	
<script src="<c:url value='/dwr/util.js'/>"></script>
<script src="<c:url value='/dwr/engine.js'/>"></script>
<script src="<c:url value='/dwr/interface/jmiMemberManager.js'/>"></script>

<script>
	var $ = jQuery.noConflict();
	function toolkit(){
		if( $('.divIndex').length == 2 ){
			$('.divIndex:eq(1)').css({'bottom':'132px'});
		}
	}
	
	function callBackJshigher(indexMap){
		if(indexMap.is_policy_month=='0'){
			 con =   '<table width="97%" border="0" class="tabRank">'+
			'<colgroup style="width:200px;"></colgroup>'+
			'<colgroup></colgroup>'+
			'<colgroup style="width:250px;"></colgroup>'+
			'<colgroup></colgroup>'+
			'<tbody>'+
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
					' <td colspan="4">实际完成业绩</td>'+
				'</tr>'+


				'<tr>'+
					'<td class="tr">您目前冠军部门新增业绩为:</td>'+
					'<td>{18}PV</td>'+
					'<td class="tr">您目前的其余部门网络新增业绩之和:</td>'+
					'<td>{19}PV</td>'+
				'</tr>'+
				
				
				'<tr>'+
					'<td colspan="4">以上数据考核周期为 {14} - {15}</td>'+
				'</tr>'+
				'<tr>'+
					'<td colspan="4">此数据为目前预算信息，尚未最后结算，仅供参考！</td>'+
				'</tr>'+
				
			'</tbody>'+
		'</table>';
		}
		MyDialog({
			boxWidth 	: 861,
			boxContent 	: String.format(con,indexMap.cur_member_level,indexMap.next_member_level,indexMap.pre_min_total,indexMap.cur_min_total,indexMap.cur_month_add,indexMap.min_need_pv,indexMap.pre_max_total,indexMap.cur_max_total,indexMap.cur_month_max_add,indexMap.max_need_pv,indexMap.cur_pass_star,indexMap.next_pass_star,indexMap.cur_pa_duty,indexMap.need_pa_duty,indexMap.checkStime,indexMap.checkEndTime,indexMap.max_pv_need,indexMap.min_pv_need,indexMap.actual_max_pv,indexMap.actual_min_pv,indexMap.is_policy_month)
		});
	}		

	function callBackBJshigher(indexMap){
		

		if(indexMap.is_policy_month=='1'){
			 b_con =   '<table width="97%" border="0" class="tabRank">'+
			'<colgroup style="width:200px;"></colgroup>'+
			'<colgroup></colgroup>'+
			'<colgroup style="width:250px;"></colgroup>'+
			'<colgroup></colgroup>'+
			'<tbody>'+
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
					' <td colspan="4">实际完成业绩</td>'+
				'</tr>'+


				'<tr>'+
					'<td class="tr">您目前冠军部门新增业绩为:</td>'+
					'<td>{18}PV</td>'+
					'<td class="tr">您目前的其余部门网络新增业绩之和:</td>'+
					'<td>{19}PV</td>'+
				'</tr>'+

				'<tr>'+
					' <td colspan="4">政策达成业绩</td>'+
				'</tr>'+
				
				'<tr>'+
					'<td class="tr">您目前冠军部门新增业绩为:</td>'+
					'<td>{8}PV</td>'+
					'<td class="tr">您目前的其余部门网络新增业绩之和:</td>'+
					'<td>{4}PV</td>'+
				'</tr>'+
				
				
				'<tr>'+
					'<td colspan="4">以上数据考核周期为 {14} - {15}</td>'+
				'</tr>'+
				'<tr>'+
					'<td colspan="4">此数据为目前预算信息，尚未最后结算，仅供参考！</td>'+
				'</tr>'+
				
			'</tbody>'+
		'</table>';
		}
		
		MyDialog({
			boxWidth 	: 861,
			boxContent 	: String.format(b_con,indexMap.b_cur_member_level,indexMap.b_next_member_level,indexMap.b_pre_min_total,indexMap.b_cur_min_total,indexMap.b_cur_month_add,indexMap.b_min_need_pv,indexMap.b_pre_max_total,indexMap.b_cur_max_total,indexMap.b_cur_month_max_add,indexMap.b_max_need_pv,indexMap.b_cur_pass_star,indexMap.b_next_pass_star,indexMap.b_cur_pa_duty,indexMap.b_need_pa_duty,indexMap.b_checkStime,indexMap.b_checkEndTime,indexMap.b_max_pv_need,indexMap.b_min_pv_need,indexMap.b_actual_max_pv,indexMap.b_actual_min_pv,indexMap.b_is_policy_month)
		});
	} 
	
	function popupFiAddr(){
		var $mask = $('#mask_if');
		var $popupAddr = $('#popupAddr_if');

		$mask.show().css({
			height:$("body").height()
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
			"height":$("body").height()
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

	

	var con =   '<table width="97%" border="0" class="tabRank">'+
					'<colgroup style="width:200px;"></colgroup>'+
					'<colgroup></colgroup>'+
					'<colgroup style="width:250px;"></colgroup>'+
					'<colgroup></colgroup>'+
					'<tbody>'+
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
							' <td colspan="4">实际完成业绩</td>'+
						'</tr>'+


						'<tr>'+
							'<td class="tr">您目前冠军部门新增业绩为:</td>'+
							'<td>{18}PV</td>'+
							'<td class="tr">您目前的其余部门网络新增业绩之和:</td>'+
							'<td>{19}PV</td>'+
						'</tr>'+
						
						'<tr>'+
							' <td colspan="4">政策达成业绩</td>'+
						'</tr>'+
						
						'<tr>'+
							'<td class="tr">您目前冠军部门新增业绩为:</td>'+
							'<td>{8}PV</td>'+
							'<td class="tr">您目前的其余部门网络新增业绩之和:</td>'+
							'<td>{4}PV</td>'+
						'</tr>'+
						
						
						'<tr>'+
							'<td colspan="4">以上数据考核周期为 {14} - {15}</td>'+
						'</tr>'+
						'<tr>'+
							'<td colspan="4">此数据为目前预算信息，尚未最后结算，仅供参考！</td>'+
						'</tr>'+
						
					'</tbody>'+
				'</table>';

				




				var b_con =   '<table width="97%" border="0" class="tabRank">'+
				'<colgroup style="width:200px;"></colgroup>'+
				'<colgroup></colgroup>'+
				'<colgroup style="width:250px;"></colgroup>'+
				'<colgroup></colgroup>'+
				'<tbody>'+
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
						' <td colspan="4">实际完成业绩</td>'+
					'</tr>'+


					'<tr>'+
						'<td class="tr">您目前冠军部门新增业绩为:</td>'+
						'<td>{18}PV</td>'+
						'<td class="tr">您目前的其余部门网络新增业绩之和:</td>'+
						'<td>{19}PV</td>'+
					'</tr>'+

					
					
					'<tr>'+
						'<td colspan="4">以上数据考核周期为 {14} - {15}</td>'+
					'</tr>'+
					'<tr>'+
						'<td colspan="4">此数据为目前预算信息，尚未最后结算，仅供参考！</td>'+
					'</tr>'+
					
				'</tbody>'+
			'</table>';


	$(function(){

		$("#js_higher").click(function(){
			jmiMemberManager.getBonsuStarView('1',callBackJshigher);
		});

		$("#b_js_higher").click(function(){
			jmiMemberManager.getBonsuStarView('2',callBackBJshigher);
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

	<c:if test="${not empty bankinfo || not empty activeinfo || not empty clAddress }">
	var str='';

	<c:if test="${not empty bankinfo }">
		str+='<ng:locale key="bankinfo.member.new"/>';
	</c:if>

	<c:if test="${not empty bankinfo }">
		str+='\n';
	</c:if>

	<c:if test="${not empty activeinfo }">
		str+='${activeinfo}';
	</c:if>

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

	<c:if test="${not empty notice }">
		open('<c:url value="/loginform/notice"/>');
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
	popupModifyPwd();

</script>
	
	
	<%=(request.getAttribute("scripts") != null) ? request.getAttribute("scripts") : ""%>

</body>
</html>
