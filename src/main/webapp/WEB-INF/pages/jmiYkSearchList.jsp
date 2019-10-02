<%@ page contentType = "text/html; charset=utf-8" language = "java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>

	<meta name="menu" content="FoundationOrderMenu"/>
</head>

<div class="cont">
	<div class="bt mt">
		<h3 class="color2 ml">推荐云客</h3>
	</div>
	<form action="${ctx }/jmiYkSearchList" method="get" name="jmiYkSearchList">
		<table class="search_table mt" >
			<tr>
				<td>
					会员编号：
				</td>
				<td>
					<input type="text" name="ykUserCode" value="${param.ykUserCode }"
						   size="20" maxlength="100" />
				</td>

				<td>
					云店账号：
				</td>
				<td>
					<input type="text" name="mobiletele" value="${param.mobiletele }"
						   size="20" maxlength="100" />
				</td>
				<td>
					<button type="submit" onclick="loading('<ng:locale key="button.loading"/>');"><ng:locale key="operation.button.search"/></button>
				</td>
			</tr>
		</table>
	</form>
	<!---------->
	<div class="mt">
		<table class="prod mt">
			<tbody class="list_tbody_header">
			<tr>
				<td>会员编号</td>
				<td>云店账号</td>
				<td>邀请码</td>
				<td>会员身份证</td>
			</tr>
			</tbody>
			<tbody class="list_tbody_row">

			<c:choose>
				<c:when test="${jmiYkSearchList!=null&&fn:length(jmiYkSearchList)>0}">
					<c:forEach items="${jmiYkSearchList}" var="jmiYkSearch" >
						<tr class="bg-c gry3">
						<td>${jmiYkSearch.userCode }</td>
						<td>${jmiYkSearch.mobiletele }</td>
						<td>${jmiYkSearch.inviteCode }</td>
						<td>${jmiYkSearch.paperNumber }</td>

					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr class="bg-c gry3">
						<td colspan="8">暂无数据</td>
					</tr>
				</c:otherwise>
			</c:choose>
			</tbody>
		</table>
	</div>
	${page.pageInfo }
</div>

<script>
	$(function(){
		$('.tabQueryLs tbody').find('tr:odd > td').css('background-color','#E4EBFF');
	});
	function go_print (id) {
		var newWin = window.open('jbdMemberCongrations/showDetail.html?id='+id);
		newWin.print();
		//newWin.close();
	}
</script>