<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
    <meta name="menu" content="JpoMemberOrderMenu"/>
</head>
<div class="cont">	
<form action="${ctx }/confirmorder" name="bigForm" id="bigForm" method="post">
	<input type="hidden" name="flag" id="flag" value="${flag }"/>
	
	<h2 class="title titleEx mgb20">5万元套餐</h2>
	<table width="100%" class="odts-table">

		<colgroup style="width:160px;"></colgroup>
		<colgroup style=""></colgroup>
		<colgroup style=""></colgroup>
		<colgroup style="width:80px;"></colgroup>
		
		<thead>
			<tr>
				<th>商品编码</th>
				<th>商品名称</th>
				<th>商品价格</th>
				<th>&nbsp;</th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach items="${productList_5}" var="pl_5" varStatus="count">
			<tr>
				<td>${pl_5.jpmProductSaleNew.productNo }</td>
				<td>${pl_5.jpmProductSaleNew.productName }</td>
				<td class="pr">&yen;${pl_5.price}</td>
				<td>
					<input type="radio" name="pro_5" value="${pl_5.pttId }"/>
					<input type="hidden" id="${pl_5.pttId }" name="${pl_5.pttId }" value="${pl_5.price }"/>
				</td>
			</tr>
		</c:forEach>
		</tbody>
		
	</table>
	


	<h2 class="title titleEx mgb20">20万元套餐</h2>
	<table width="100%" class="odts-table">

		<colgroup style="width:160px;"></colgroup>
		<colgroup style=""></colgroup>
		<colgroup style=""></colgroup>
		<colgroup style="width:80px;"></colgroup>
			
		<thead>
			<tr>
				<th>商品编码</th>
				<th>商品名称</th>
				<th>商品价格</th>
				<th>数量</th>
			</tr>
		</thead>
		
		<tbody>
		<c:forEach items="${productList_20}" var="pl_20" varStatus="count">
			<tr>
				<td>${pl_20.jpmProductSaleNew.productNo }</td>
				<td>${pl_20.jpmProductSaleNew.productName }</td>
				<td class="pr">&yen;${pl_20.price}</td>
				<td>
					<input type="text" name="pro_20" value="0" class="odst-input" onkeyup="this.value=this.value.replace(/\D/g,'')" onchange="this.value=this.value.replace(/\D/g,'')" maxlength="6" />
					<input type="hidden" name="pttid" value="${pl_20.pttId }">
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>


	<input type="button" value="确定" onclick="viliPrice();" class="btn_common corner2 fr mgt30 mgb20">
</form>
</div>

	

 <script>
 	$(function(){
 		$(".odts-table tbody input:radio").click(function(){		
 			if($(this).prop("checked")){
 				$(this).closest("tr").addClass("cur");
 				$(this).closest("tr").siblings("tr").removeClass("cur");
 			}
 		});
 		
		$(".odts-table tbody input:text").focusin(function(){
			$(this).closest("tr").addClass("cur");
			$(this).closest("tr").siblings("tr").removeClass("cur");
 		});
 	});
 	
 	function viliPrice(){
 		var qtys_20 = document.getElementsByName('pro_20');
 		var qty_5 = document.getElementsByName('pro_5');
 		var pro_5;var ischeck=false;
 		var flag = document.getElementById('flag').value;
 		
 		for(var i=0;i<qty_5.length;i++){
 	 		if(qty_5[i].checked){
 	 			pro_5 = qty_5[i].value;
 	 			ischeck=true;
 	 			break;
 	 		}
 	 	}
 		if(!ischeck){
 			alert('请选择5万套餐商品！');
 			return;
 		}
 		var sumqty=0;var sumprice=0;
 		for(var i=0;i<qtys_20.length;i++){
 			if(qtys_20[i].value>0){
 				sumqty=sumqty*1+qtys_20[i].value*1;
 			}
 		}
 		//alert('sumqty=='+sumqty+' and flag='+flag);
 		if(flag==45 && sumqty!=2){
 			alert('金额必须是 45W');
 			return;
 		} else if(flag==65 && sumqty!=3){
 			alert('金额必须是 65W');
 			return;
 		}
 		document.getElementById('bigForm').submit();
 	}
 	
 </script>
 