<%@ page contentType = "text/html; charset=utf-8" language = "java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>

	<meta name="menu" content="FoundationOrderMenu"/>
</head>

<div class="cont">
	<div class="bt mt">
		<h3 class="color2 ml">我的邀请码/激活码</h3>
	</div>
	<form action="${ctx }/jpoInviteLists/jpoInviteListMaintenance" method="get" name="jpoInviteList">
		<table class="search_table mt" >
			<tr>
				<td>
					邀请码/激活码：
				</td>
				<td>
					<input type="text" name="inviteCode" value="${param.inviteCode }"
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
				<%--        <td >会员编号</td>		--%>
				<td>邀请码/激活码</td>
				<%--        <td>订单编号</td>		--%>
				<td>创建时间</td>
				<td>状态</td>
				<td>使用会员编号</td>
				<td>使用时间</td>
				<%--
                       <td>版本</td>
                       <td>条目说明</td>
                 --%>
			</tr>
			</tbody>
			<tbody class="list_tbody_row">

			<c:choose>
				<c:when test="${jpoInviteLists!=null&&fn:length(jpoInviteLists)>0}">
					<c:forEach items="${jpoInviteLists}" var="jpoInviteList" >
						<tr class="bg-c gry3">
								<%--
                                <td>
                                    ${jpoInviteList.userCode }
                                </td>
                        --%>
							<td>
									${jpoInviteList.inviteCode }
							</td>
								<%--
                                    <td>
                                        ${jpoInviteList.memberOrderNo }
                                    </td>
                                --%>
							<td>
									${jpoInviteList.createTime }
							</td>
							<!-- 状态 -->
							<td>
								<ng:code listCode="po.invitelist.status" value="${jpoInviteList.status}"/>
							</td>

							<td>
									${jpoInviteList.useUserCode }
							</td>

							<td>
									${jpoInviteList.useTime }
							</td>
								<%--
                                    <td>
                                        ${jpoInviteList.version }
                                    </td>
                                --%>
							<!-- 条目说明 -->
						</tr>
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