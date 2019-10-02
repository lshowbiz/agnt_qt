<%@ page contentType = "text/html; charset=utf-8" language = "java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>

    <meta name="menu" content="FoundationOrderMenu"/>
    <link rel="stylesheet" href="${ctx}/styles/calendar/calendar-blue.css" />
    <link href="${ctx}/css/style.css" rel="stylesheet" type="text/css" media="all" />	
    <script src="${ctx}/scripts/calendar/calendar.js"> </script>
    <script src="${ctx}/scripts/calendar/calendar-setup.js"> </script>
    <script src="${ctx}/scripts/calendar/lang.jsp"> </script>
	<script src="${ctx}/scripts/lib/jquery-1.8.2.min.js"></script>
    <script src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>

<style>
.orderForms {
	margin:18px 0 20px 0;width: 796px;
}
.orderForms td{
	font-size:12px;
	height:36px;
	line-height:36px;
}
.divcss5{text-decoration:underline;color:#F00;}
.ft-green {
	color: #41ab01;
}
.div-t{
	width:100%; background-color: #f4f6fc; padding-left: 40px; height:40px; line-height:40px
}
.div-td{
	padding-left: 40px;
}
.tck{ width:240px; height:120px;padding:10px; float:left; border:1px dashed #fff; margin:10px 10px 0 0 }
.tck:hover{ width:240px; height:120px;padding:10px; border:1px dashed #d03638; }
.tckPK{ width:700px; height:120px;padding:10px 20px; border:2px dashed #ccc; margin:20px 0 0 130px }
.tckPK .dingdan{width:250px; height:120px; margin:10px 0 10px 40px; line-height:24px; }
.tck-img{width:91px; height:91px; margin:20px}
.tckPK:hover{ width:700px; height:120px;padding:10px 20px; border:2px dashed #d03638; }

.tck-a{width:200px;height:90px; padding:0 20px;background:url(../images/quan-bg.png) center no-repeat; color:#fff; font-weight:700;line-height:90px;}
.tck-b{width:200px;height:90px; padding:0 20px;background:url(../images/quan-bg2.png) center no-repeat; color:#fff; font-weight:700;line-height:90px;}
.money{font-size:48px; font-weight:700; letter-spacing:-2px; }
.yuan{ font-size: 24px;line-height: 1; color:#fff;vertical-align: 18px;}
.tckId{font-size:15px; color:#999999; margin-top:5px; white-space:nowrap;}

#tabbox{ width:100%; overflow:hidden; margin:20px; auto;}
.tab_con{ display:none;}
.tabs{width:100%;}
.tabs li{ color: #666;  margin:0 20px;text-align: center;float: left;font-size: 20px;cursor: pointer;}
.tabs li a {display: block; outline: none;}
.tabs .thistab {padding-bottom:5px; border-bottom:3px solid #d03638; }
.tab_con {width:100%; padding:40px 0;}
</style>
</head>

<div class="cont">

<form id="jfiPay99bill" name="jfiPay99bill" method="GET" action="">
	<input id="payerName" name="payerName" type="hidden" value="${jsysUser.userName }" size="30"/>
	<div class="bt mt20">
			<h3 class="color2 ml">我的代金券</h3>
	</div>

     <div id="tabbox">
		<ul class="tabs" id="tabs">         
            <li class="thistab"><a href="" tab="tab1">已使用代金券</a></li>
			<li><a href="" tab="tab2">未使用代金券</a></li>
		</ul>
		 <ul>
			<li id="tab1" class="tab_con">
               <c:choose>
                   <c:when test="${jpoUserCouponToSql!=null&&fn:length(jpoUserCouponToSql)>0}">
                       <c:forEach items="${jpoUserCouponToSql}" var="jpoUserCoupon" >
							<c:if test="${jpoUserCoupon.jucStatus=='1'}"><%--
								 <div class="div-td mt tck">
										<div class="tck-a fl  mt"><span class="yuan">¥</span><b class="money">${jpoUserCoupon.jciCpValue }</b><span class="ft16">代金券</span></div>
										<p class="tckId">订单编号：${jpoUserCoupon.JpoMemberOrderNo }</p>
								  </div>  
							--%>
							<div class="div-td tckPK">

										<div class="tck-a fl "><span class="yuan">¥</span><b class="money">${jpoUserCoupon.jciCpValue }</b><span class="ft16">代金券</span></div>
										<div class="fl dingdan">
												<p class="tckId"><b>订单编号：</b>${jpoUserCoupon.JpoMemberOrderNo }</p>
												<p class="tckId"><b>使用时间：</b>${jpoUserCoupon.jcrUseTime }</p>
										</div>
										</div>				
							</c:if>
 
                       </c:forEach>
                   </c:when>
               </c:choose>	  			
			</li>	
			
			<li id="tab2" class="tab_con">
			   <c:choose>
                   <c:when test="${jpoUserCouponToSql!=null&&fn:length(jpoUserCouponToSql)>0}">
                       <c:forEach items="${jpoUserCouponToSql}" var="jpoUserCoupon" >
							<c:if test="${jpoUserCoupon.jucStatus=='0'}">
								<%-- <div class="div-td mt tckPK">
										<div class="tck-b fl mt"><span class="yuan">¥</span><b class="money">${jpoUserCoupon.jciCpValue }</b><span class="ft16">代金券</span></div>
											 <div class="div-td mt tck">
													<div class="tck-a fl  mt"><span class="yuan">¥</span><b class="money">${jpoUserCoupon.jciCpValue }</b><span class="ft16">代金券</span></div>
													<p class="tckId">订单编号：${jpoUserCoupon.JpoMemberOrderNo }</p>
											  </div> 
										<div class="fl tck-img"> <a href="${ctx}/jpoShoppingCartOrderform?orderType=93" target="_blank"><img src="../images/quan-use.png"></a>
										</div>
								  </div> 
								  		<div class="div-td mt tck">
								  		<p class="tckId">订单编号：${jpoUserCoupon.JpoMemberOrderNo }</p><%--
								  </div> --%>
								  <div class="div-td mt tck">
								  <div class="tck-b fl 	"><span class="yuan">¥</span><b class="money">${jpoUserCoupon.jciCpValue }</b><span class="ft16">代金券</span></div>
								  </div>
							</c:if>
                       </c:forEach>
                   </c:when>
               	</c:choose>    						   
			</li>	 
		</ul>
	</div>	
</form>
</div>

<script type="text/javascript">
$(document).ready(function() {
    jQuery.jqtab = function(tabtit,tabcon) {
        $(tabcon).hide();
        $(tabtit+" li:first").addClass("thistab").show();
        $(tabcon+":first").show();
    
        $(tabtit+" li").click(function() {
            $(tabtit+" li").removeClass("thistab");
            $(this).addClass("thistab");
            $(tabcon).hide();
            var activeTab = $(this).find("a").attr("tab");
            $("#"+activeTab).fadeIn();
            return false;
        });
        
    };
    /*调用方法如下：*/
    $.jqtab("#tabs",".tab_con");
    
});
</script>
