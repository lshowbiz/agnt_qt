<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JalFileLibararyMenu"/>
    <style type="text/css">
    .sortcon-link{padding-top:5px;line-height:18px;font-size:0;position:relative;z-index:999;width:100%;}
    .sortcon-link span.title{padding:0 5px 5px 14px;display:inline-block;float:left;color:#424242;height:18px;font-size:12px;}
    .sortcon-link a{display:inline-block;height:18px;margin-right:4px;color:#506eaa;padding:0 7px;margin-bottom:4px;font-size:12px;_line-height:20px;_overflow:hidden;}
    .sortcon-link a span{color:#939393;font-family:arial;font-size:10px;margin-left:1px;}
    .sortcon-link a.current,.sortcon-link a:hover{background-color:#6486ca;color:#FFF;text-decoration:none;}
    .sortcon-link a.current span,.sortcon-link a:hover span{color:#FFF;}
    .infolp_item{ padding:6px 12px 6px; border-bottom: 1px solid #e3e8da; overflow: hidden; *zoom:1; }
	.infolp_img{ display: block; width: 100px; margin: 4px 0 0; float: left; }
	.infolp_img img{ display: block; overflow: hidden; }
	.infolp_main_box{width: 652px; height:97px; overflow:hidden; float: left;}
	.infolp_main{padding-right:15px; width: 540px; overflow:hidden; float: left; }
	.infolp_tit{ line-height: 24px; color: #447807; font-size: 14px;}
	.infolp_edt{ height: 24px; line-height: 24px; }
	.ul{padding-left:0px; font-size:12px;line-height:20px}
	.infolp_item .link_downa{ margin:30px 0 0; float: left; display:inline }
	.link_downa{ width: 93px; height: 25px; line-height: 29px;text-align: center; font-weight: 700; color: #fff !important; background-position: 0 -350px; background-color:#FF9933; font-size:10px}

	.infolp_tit a{ color:#333333}
	.infolp_tit a:hover{ color: #6486ca;}
	
	.hotwords{@fis-clearfix height:130px}
	.hotwords li{background:#17a2b7;font-size:14px;float:left;height:52px;margin:0 8px 8px 0;text-align:center}
	.hotwords li a:link,.hotwords li a:visited{color:#FFF;padding:2px;line-height:18px;text-decoration:none;display:none;font-family:"微软雅黑"}
	.hotwords li.li_0{background:#00CC33;font-size:14px;font-weight:bold}
	.hotwords li.li_1{background:#53aecf;font-size:14px;font-weight:bold}
	.hotwords li.li_2{background:#FF6600;font-size:14px;font-weight:bold}
	.hotwords li.li_3{background:#73ced6;font-size:14px;font-weight:bold}
	.hotwords li.li_4{background:#CC3300;font-size:14px;font-weight:bold}
	.hotwords li.li_5{background:#339966;font-size:14px;font-weight:bold}
	.hotwords li.li_6{background:#9966CC;font-size:14px;font-weight:bold}
	.hotwords li.li_7{background:#00CC33;font-size:14px;font-weight:bold}
	.hotwords li.li_8{background:#055c6f;font-size:14px;font-weight:bold}
	.hotwords li.li_9{background:#53aecf;font-size:14px;font-weight:bold}
	.hotwords li.li_10{background:#FF6600;font-size:14px;font-weight:bold}
	.hotwords li.li_11{background:#73ced6;font-size:14px;font-weight:bold}
	.hotwords li.li_12{background:#339966;font-size:14px;font-weight:bold}
    </style>
</head>

<c:if test="{'$'}{not empty searchError}">
    <div class="alert alert-error fade in">
        <a href="#" data-dismiss="alert" class="close">&times;</a>
        <c:out value="{'$'}{searchError}"/>
    </div>
</c:if>

<div class="content fr">
	<h2 class="title"><span>资料中心</span></h2>
	<form method="get" action="jalFileLibararies" id="jalFileLibarariesSearchForm" name="jalFileLibarariesSearchForm">
	<input type="hidden" name="typeId">
	<div class="clr">&nbsp;</div>
	<table width="100%" border="0">
					<colgroup style="width:80px;" />
					<colgroup />
					<colgroup style="width:80px;" />
					<colgroup />
					<colgroup style="width:80px;" />
					<!-- 
					<tr>
						<td class="lab">热&nbsp;&nbsp;门：</td>
						<td colspan="5">
						<div class="sortcon-link">
							
							<a href="${pageContext.request.contextPath}/jalFileLibararies" class="current" tracknum="42000">全部</a>
								<a href="${pageContext.request.contextPath}/jalFileLibararies?keyword='中脉年会'" tracknum="42001">中脉年会<span>&nbsp;2</span></a>
								<a href="${pageContext.request.contextPath}/jalFileLibararies?keyword='视频'" tracknum="42001">视频<span>&nbsp;35</span></a>
								<a href="${pageContext.request.contextPath}/jalFileLibararies?keyword='美体'" tracknum="42001">美体<span>&nbsp;1</span></a>
								<a href="${pageContext.request.contextPath}/jalFileLibararies?keyword='2013'" tracknum="42001">2013<span>&nbsp;12</span></a>
								<a href="${pageContext.request.contextPath}/jalFileLibararies?keyword='奥运会'" tracknum="42001">奥运会<span>&nbsp;12</span></a>
								<a href="${pageContext.request.contextPath}/jalFileLibararies?keyword='旅游节'" tracknum="42001">旅游节<span>&nbsp;12</span></a>
								
							<div class="clr"></div>
						</div>
							
						</td>
					</tr>
					<tr>
						<td class="lab">类&nbsp;&nbsp;别：</td>
						<td colspan="5">
						<div class="sortcon-link">
							
							<a href="${pageContext.request.contextPath}/jalFileLibararies" class="current" tracknum="42000">全部</a>
								<a href="${pageContext.request.contextPath}/jalFileLibararies?keyword='产品手册'" tracknum="42001">产品手册<span>&nbsp;569</span></a>
								<a href="${pageContext.request.contextPath}/jalFileLibararies?keyword='精彩视频'" tracknum="42001">精彩视频<span>&nbsp;75</span></a>
								<a href="${pageContext.request.contextPath}/jalFileLibararies?keyword='精彩图片'" tracknum="42001">精彩图片<span>&nbsp;9000</span></a>
								<a href="${pageContext.request.contextPath}/jalFileLibararies?keyword='公司政策'" tracknum="42001">公司政策<span>&nbsp;22</span></a>

								
							<div class="clr"></div>
						</div>
							
						</td>
					</tr>
					 -->
					<tr>
						
						<td class="lab">热&nbsp;&nbsp;搜：</td>
						<td colspan="5">
						<div class="mod h-bd-box" id="news-hotwords" alog-group="focus-top-news-hotwords">
            
            
            <div class="bd">
            		<ul class="hotwords">
            		<li class="li_0"><a href="javascript:toSearchAll()" style="display: block; padding-top: 16px; ">&nbsp;全部&nbsp;</a></li>
            		<c:forEach items="${fileTypeList}" var="t" varStatus="status">
            			
            			<li class="li_${status.index+1}"><a href="javascript:toSearch(${t.VALUE_CODE})" style="display: block; padding-top: 16px; ">&nbsp;${t.VALUE_TITLE }&nbsp;</a></li>
            		</c:forEach>

					</ul>
			</div>
            </div>
							
						</td>
					</tr>
					<!-- 
					<tr>
						<td class="lab"><label for="ordersNum">关键字：</label></td>
						<td><input type="text" name="memberOrderNo" id="memberOrderNo" class="myTextInput mgtb10" /></td>
						<td class="lab"></td>
						<td>
							<select name="orderTime" id="orderTime" class="mySelect" >
								<option value="1">最近一周</option>
								<option value="2">最近一月</option>
								<option value="3">最近一月</option>
								<option value="4">最近一月</option>
								<option value="5">最近一月</option>
							</select>
						</td>
						
					
						<td><a  class="btn btn_mid bold corner" onclick="searchF('${pageContext.request.contextPath}/jalFileLibararies')">查询</a></td>
						<td>&nbsp;</td>
					</tr> -->
				</table>
	
	</form>
	<div class="clr">&nbsp;</div>
	<c:forEach items="${jalFileLibararyList}" var="jalFileLibarary">
		<div class="infolp_item" onMouseOver="hov(this)" onMouseOut="hovout(this)">
		<a href="${pageContext.request.contextPath}/jalFileLibararyform?FId=${jalFileLibarary.FId}" class="infolp_img" target="_blank"><img src="${jalFileLibarary.logoImg}" width="80" height="80" alt=""></a>
		<div class="infolp_main_box">
			<h4 class="infolp_tit"><!--  <span>人气：2233086</span><span>好评率：85.5%</span>--><a href="${pageContext.request.contextPath}/jalFileLibararyform?FId=${jalFileLibarary.FId}">${jalFileLibarary.name}</a></h4>
			<div class="infolp_main">
				<ul class="ul">
				<li>类别：<ng:code  listCode="file.type.list" value="${jalFileLibarary.typeId}"/></li>
				
				<li>简介：<c:choose>
				<c:when test="${fn:length(jalFileLibarary.remark) > 35}">
				<c:out value="${fn:substring(jalFileLibarary.remark, 0, 35)}..." />
				</c:when>
				<c:otherwise>
				<c:out value="${jalFileLibarary.remark}" />
				</c:otherwise>
				</c:choose>
				
				</li>

				
				<li>更新时间：${jalFileLibarary.createTime}&nbsp;&nbsp;&nbsp;&nbsp;大小：${jalFileLibarary.fileSize}</li>
				</ul>
			</div>
			<a href="${pageContext.request.contextPath}/jalFileLibararyform?fid=${jalFileLibarary.FId}" class="link_downa">下载</a>
		</div>
		</div>
	</c:forEach>
	
	</div>
<script type="text/javascript">

	function toSearch(typeId){
		var theForm = document.getElementById("jalFileLibarariesSearchForm")
		
		theForm.typeId.value=typeId;
		theForm.submit();
	}
	function toSearchAll(){
		var theForm = document.getElementById("jalFileLibarariesSearchForm")

		theForm.submit();
	}
</script>