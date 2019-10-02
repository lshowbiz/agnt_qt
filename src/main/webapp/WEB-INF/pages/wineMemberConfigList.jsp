<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>
<%
    String path = request.getContextPath();
    String basePath =
        request.getScheme() + "://" + request.getServerName() + ":"
            + request.getServerPort() + path + "/";
%>

	<h2 class="title mgb20"><span>订单配置</span></h2>

	<form action="${pageContext.request.contextPath}/jpoMemberOrderform" method="post" id="from_del">
		<input type="hidden" value="delOrder" name="strAction"/>
		<input type="hidden" value="" name="order_mo_id" id="order_mo_id"/>

        <table width="100%" class="personalInfoTab mgb20">			
			<colgroup style="width:80px;"></colgroup>
			<colgroup></colgroup>
			<colgroup style="width:92px;"></colgroup>
			<colgroup></colgroup>
			<colgroup style="width:92px;"></colgroup>
			<colgroup></colgroup>
			<colgroup style="width:92px;"></colgroup>
			<colgroup></colgroup>
			<tbody>
				<tr>
					<td><label>商品名称：</label></td>
					<td>
						<input type="text" value='<c:out value="${ model['productName'] }" />' class="inputOs" style="width:194px;" readonly="readonly"/>
					</td>
					<td><label>商品总数量：</label></td>
					<td>
						<input type="text" value='<c:out value="${ model['qty'] }" />' class="inputOs w60" readonly="readonly"/><span>坛</span>
					</td>
					<td><label>商品已配数量：</label></td>
					<td>
						<input type="text" value='<c:out value="${ model['wasConfigNum'] }" />' class="inputOs w60" readonly="readonly"/><span>坛</span>
					</td>
					<td><label>商品未配数量：</label></td>
					<td>
						<input type="text" value='<c:out value="${ model['configNum'] }" />' class="inputOs w60" readonly="readonly"/><span>坛</span>
					</td>
				</tr>
				<tr>
					<td><label>商品总重量：</label></td>
					<td>
						<input type="text" value='<c:out value="${ model['allWeightCount'] }" />' class="inputOs w60" readonly="readonly"/><span>斤</span>
					</td>
					<td><label>商品已配重量：</label></td>
					<td>
						<input type="text" value='<c:out value="${ model['wasWeightCount'] }" />' class="inputOs w60" readonly="readonly"/><span>斤</span>
					</td>
					<td><label>商品未配重量：</label></td>
					<td>
						<input type="text" value='<c:out value="${ model['weightCount'] }" />' class="inputOs w60" readonly="readonly"/><span>斤</span>
					</td>
					<td><label></label></td>
					<td></td>
				</tr>

				<tr>
            	<c:if test="${ model['configNum'] != '0'}">
					<td colspan="8"><a href="${pageContext.request.contextPath}/jpoWineMemberOrders/addConfigPage?saveType=0&moId=<c:out value="${ model['moId'] }" />&productName=<c:out value="${ model['productName'] }" />&productId=<c:out value="${ model['productId'] }" />&molId=<c:out value="${ model['molId'] }" />&productNo=<c:out value="${ model['productNo'] }" />"  class="btn_common corner2 fr">新增配置</a></td>
				</c:if>
				<c:if test="${ model['configNum'] == '0'}">
					<td colspan="8"><a class="btn_common_no corner2 fr">新增配置</a></td>
				</c:if>
				</tr>
	
			</tbody>
        </table>
		
    </form>

	
	<c:if test="${not empty configList }">
		<c:forEach items="${configList}" var="config" varStatus="index">
		<div>
			<table width="100%" class="Summary">
				<colgroup style="width:80px;"></colgroup>
				<colgroup></colgroup>
				<colgroup style="width:80px;"></colgroup>
				<colgroup></colgroup>
				<colgroup style="width:80px;"></colgroup>
				<colgroup></colgroup>
				<colgroup style="width:80px;"></colgroup>
				<tbody>
					<tr>
						<td>商品名称：</td>
						<td class="colorJH ft14 bold">${config.productName}</td>
						<td>配置数量：</td>
						<td class="colorJH ft14 bold">${config.amount }坛</td>
						<td>配置状态：</td>
						<td class="colorJH ft14 bold">
							<c:if test="${config.status eq '0 '}">未配置完成</c:if>
							<c:if test="${config.status eq '1 '}">已配置完成</c:if>
						</td>
						<c:if test="${config.status ne '2 '}">
						<c:if test="${config.status eq '1 '}">
						<td>
							<a href="javascript:void(0);" style="color:blue;" onclick="payment('${pageContext.request.contextPath}/payMemberConfig','${config.configNo }','${config.payment }');" title="支付" class="Payment">支付</a>
<!--								<a class="red ft12" style="color:blue;" href="${pageContext.request.contextPath}/jpoWineMemberOrders/pay?productName=${config.productName}&configNo=${config.configNo}&molId=<c:out value="${ model['molId'] }" />&productNo=<c:out value="${ model['productNo'] }" />" >支付</a>-->
						</td>
						</c:if>
						<c:if test="${config.status eq '0 ' || config.status eq ''}">
						
							<c:if test="${config.oddWeight ne '0'}">
								<td><a class="red ft12" style="color:blue;" href="${pageContext.request.contextPath}/jpoWineMemberOrders/addConfigPage?saveType=1&productName=${config.productName}&configNo=${config.configNo}&molId=<c:out value="${ model['molId'] }" />&productNo=<c:out value="${ model['productNo'] }" />" >新增规格配置</a></td>
								&nbsp;&nbsp;
							</c:if>
							<c:if test="${config.oddWeight eq '0'}">
								<td><a class="red ft12" style="color:blue;" href="${pageContext.request.contextPath}/jpoWineMemberOrders/submitConfig?productName=${config.productName}&configNo=${config.configNo}&molId=<c:out value="${ model['molId'] }" />&productNo=<c:out value="${ model['productNo'] }" />" >提交配置</a></td>
							</c:if>
						
						</c:if>
						</c:if>

					</tr>
				</tbody>
			</table>
			

			<table width="100%" class="tabQueryLs" id="pdlist-${index.count}">
				<colgroup style="width:220px;"></colgroup>
				<colgroup style="width:220px;"></colgroup>
				<colgroup style="width:80px;"></colgroup>
				<colgroup></colgroup>
				<tbody>
					<tr>
						<th class="bold">规格名称</th>
						<th class="bold">配置重量</th>
						<th class="bold">价格</th>
						<c:choose> 
							<c:when test="${config.status eq '0 '}"> 
							<th class="bold">操作</th>
							</c:when> 
							<c:otherwise> 
							<th>&nbsp;</th>
							</c:otherwise> 
						</c:choose> 
					</tr>
					<c:if test="${not empty config.jpmConfigSpecDetailed}">
						<c:forEach items="${config.jpmConfigSpecDetailed}" var="jpmConfigSpecDetailed" varStatus="index">
						<tr class="selt">						   
							<td>${jpmConfigSpecDetailed.productTemplateName }</td>
							<td class="colorCS">${jpmConfigSpecDetailed.complateWeight}</td>
							<td class="colorJH">${jpmConfigSpecDetailed.price }</td>
							<td>
							<c:if test="${config.status ne '2 '}">
							<c:if test="${config.status eq '1 '}">
								<a href="${pageContext.request.contextPath}/jpmSendConsignments/sendConsignmentPage?specNo=${jpmConfigSpecDetailed.specNo}&specName=${jpmConfigSpecDetailed.productTemplateName }&molId=<c:out value="${ model['molId'] }" />&productNo=<c:out value="${ model['productNo'] }" />" class="ft12" style="color:blue;" >配送发货</a>
							</c:if>
							
							<c:if test="${config.status eq '0 ' || config.status eq ''}">
								<a href="${pageContext.request.contextPath}/jpoWineMemberOrders/addConfigPage?saveType=2&configNo=${config.configNo}&specNo=${jpmConfigSpecDetailed.specNo}&molId=<c:out value="${ model['molId'] }" />&productNo=<c:out value="${ model['productNo'] }" />" style="color: blue;">修改配置</a>
								&nbsp;&nbsp;
								<a href="javascript:void(0);" onclick="del('${pageContext.request.contextPath}/jpoWineMemberOrders/delConfig','${config.configNo}','${jpmConfigSpecDetailed.specNo}','<c:out value="${ model['molId'] }" />','<c:out value="${ model['productNo'] }" />');" class="red">删除</a>
							</c:if>
							</c:if>
							</td>	
						</tr>

						<tr class="pj" style="display:none;">
							<th>&nbsp;</th>
							<th>配件名称</th>
							<th>数量</th>
							<th>价格</th>
						</tr>
						
						<c:if test="${not empty jpmConfigSpecDetailed.jpmConfigDetailedList}">
							<c:forEach items="${jpmConfigSpecDetailed.jpmConfigDetailedList}" var="configDetailed">
							<tr class="pj" style="display:none;">
								<td>&nbsp;</td>
								<td>${configDetailed.subName}</td>
								<td class="colorCS">${configDetailed.subAmount}</td>
								<td class="colorJH">${configDetailed.price}</td>
							</tr>
							</c:forEach>
						</c:if>

						
						
															
						</c:forEach>
					</c:if>
				</tbody>
			</table>

			<div class="slideBox"><a href="javascript:void(0);" class="btn btn_ext corner2">显示更多<b></b></a></div>
		</div>
		</c:forEach>
	</c:if>	
	
    <script>
	function del(url,configNo,specNo,molId,productNo){
		if(confirm("你确定要删除吗,删除后将无法恢复数据?")){
	  
			location.href=url+"?configNo="+configNo+"&specNo="+specNo+"&molId="+molId+"&productNo="+productNo;
		}else{
			return false;
		}
	}
	function payment(url,configNo,isPayment)
	{
		if(false == isPayment || 'false' == isPayment){
		alert('您的货物还没有全部配送完成哦!');
		}
		else{
			location.href=url+"?1=1&orderId="+configNo;
		}
	}

	$(function(){
	
		$('.tabQueryLs').delegate('.selt','click',function(){
			var $this = $(this);
			$this.nextUntil('.selt').toggle();
		});
		
		$('.selt a').click(function(e){
			e.stopPropagation();
		});

	});

</script>

