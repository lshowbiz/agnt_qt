<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<script src="${ctx}/scripts/joyLife.js" ></script>
<script src="${ctx}/scripts/parsley.js" ></script>
<script src="${ctx}/scripts/dialogBox.js" ></script>
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>
<script src="<c:url value='/dwr/interface/jmiAddrBookManager.js'/>" ></script>
<script src="<c:url value='/scripts/region.js'/>"></script>
<head>
    <meta name="menu" content="JpoMemberOrderMenu"/>
	<style>
    .bigForm-table select { padding: 3px 12px;}
    .bigForm-table input { width: 151px;}
    #memOrder{width: 75%;}
    #memOrder td{border: solid #00F 1px;}
    </style>
</head>
<script>
function getIdCity(orderNo){
	var province=document.getElementById('province_'+orderNo).value;
	var cityElemment=document.getElementById('city_'+orderNo);
	cityElemment.options.length=0;
	var o=new Option("<ng:locale key="list.please.select"/>","");
	cityElemment.options.add(o);  
	var districtElemment=document.getElementById('district_'+orderNo);
	districtElemment.options.length=0;
	var o=new Option("<ng:locale key="list.please.select"/>","");
	districtElemment.options.add(o);  
	$.each(jal_city, function (k, p) { 
		 if (p.state_province_id == province) {
			 var o=new Option(p.city_name,p.city_id);
		     cityElemment.options.add(o);  
		     if(p.city_id=='${jmiMember.city}'){
		        	o.selected=true;
		     }
		 }
	 });
	getIdDistrict(orderNo);
}

function getIdDistrict(orderNo){
	var city=document.getElementById('city_'+orderNo).value;
	var districtElemment=document.getElementById('district_'+orderNo);
    districtElemment.options.length=0;

  	var o=new Option("<ng:locale key="list.please.select"/>","");
  	districtElemment.options.add(o);
 
  	$.each(jal_district, function (k, p) { 
	 if (p.city_id == city) {
		 var o=new Option(p.district_name,p.district_id);
		 districtElemment.options.add(o); 

	   if('${jmiMember.district}'==p.district_id){
	   		o.selected=true;
	   } 
	 }
	});
}

function saveAddr(){
	
	var provinces = document.getElementsByName('province');
	var citys = document.getElementsByName('city');
	var districts = document.getElementsByName('district');
	var firstnames = document.getElementsByName('firtname');
	var lastnames = document.getElementsByName('lastname');
	var addressVs = document.getElementsByName('addressV');
	var postalcodes = document.getElementsByName('postalcode');
	var mobileteles = document.getElementsByName('mobiletele');
	var isselect_province = false;
	var isselect_city = false;
	var isselect_district = false;
	/**/
	for(var i=0;i<provinces.length;i++){
		for(var n=0;n<provinces[i].options.length;n++){
			if(provinces[i].options[n].selected) {
	            var proValue = provinces[i].options[n].value;
	           	if(proValue==''){
	           		isselect_province=true;
	            	break;
	            }
			} 
		}
	}
	if(isselect_province){
		var errorMsg = '<ng:locale key="errors.required" args="shipping.province" argTransFlag="true"/>';
   		alert(errorMsg);
   		return;
	}
	for(var i=0;i<citys.length;i++){
		for(var n=0;n<citys[i].options.length;n++){
			if(citys[i].options[n].selected) {
	            var proValue = citys[i].options[n].value;
	           	if(proValue==''){
	           		isselect_city=true;
	            	break;
	            }
			} 
		}
	}
	if(isselect_city){
		var errorMsg = '<ng:locale key="errors.required" args="shipping.city" argTransFlag="true"/>';
   		alert(errorMsg);
   		return;
	}
	for(var i=0;i<districts.length;i++){
		for(var n=0;n<districts[i].options.length;n++){
			if(districts[i].options[n].selected) {
	            var proValue = districts[i].options[n].value;
	           	if(proValue==''){
	           		isselect_district=true;
	            	break;
	            }
			} 
		}
	}
	if(isselect_district){
		var errorMsg = '<ng:locale key="errors.required" args="shippingRegion.isNotNull" argTransFlag="true"/>';
   		alert(errorMsg);
   		return;
	}
	for(var i=0;i<firstnames.length;i++){
		if(firstnames[i].value==''){
			 var errorMsg = '<ng:locale key="errors.required" args="shipping.firstName" argTransFlag="true"/>';
			 alert(errorMsg);
			 firstnames[i].focus();
			 return;
		}
	}
	
	for(var i=0;i<lastnames.length;i++){
		if(lastnames[i].value==''){
			var errorMsg = '<ng:locale key="errors.required" args="shipping.lastName" argTransFlag="true"/>';
			 alert(errorMsg);
			 lastnames[i].focus();
			 return;
		}
	}
	
	for(var i=0;i<addressVs.length;i++){
		if(addressVs[i].value==''){
			var errorMsg = '<ng:locale key="errors.required" args="shipping.address" argTransFlag="true"/>';
			 alert(errorMsg);
			 addressVs[i].focus();
			 return;
		}
	}
	for(var i=0;i<postalcodes.length;i++){
		if(postalcodes[i].value==''){
			var errorMsg = '<ng:locale key="errors.required" args="shipping.postalcode" argTransFlag="true"/>';
			 alert(errorMsg);
			 postalcodes[i].focus();
			 return;
		}else{
			var patrn=/^([0-9]{6})$/;
			if(!patrn.exec(postalcodes[i].value)){
				alert('邮编错误');
				postalcodes[i].focus();
				return;
			}
		}
	}
	for(var i=0;i<mobileteles.length;i++){
		if(mobileteles[i].value==''){
			var errorMsg = '<ng:locale key="errors.required" args="miMember.mobiletele" argTransFlag="true"/>';
			 alert(errorMsg);
			 mobileteles[i].focus();
			 return;
		} else {
			var patrn=/^([0-9]{11})$/;
			if(!patrn.exec(mobileteles[i].value)){
				alert('手机号码错误');
				mobileteles[i].focus();
				return;
			}
		}
	}
	if(${isBind}){
		if(${userCode_sy !=null}){
			if($('#moeny_1').val()<200){
				alert("订单("+$('#moeny_1').attr('orderCode')+")辅销品金额必须大于200.");
				return;
			}
		}
		if($('#moeny_2').val()<200){
			alert("订单("+$('#moeny_2').attr('orderCode')+")辅销品金额必须大于200.");
			return;
		}
		if($('#moeny_3').val()<200){
			alert("订单("+$('#moeny_3').attr('orderCode')+")辅销品金额必须大于200.");
			return;
		}
		if('${flag }'==65){
			if($('#moeny_4').val()<200){
				alert("订单("+$('#moeny_4').attr('orderCode')+")辅销品金额必须大于200.");
				return;
			}
		}
	}
	$('#bigForm').submit();
}

function showBox(pid){
	var $dialogBox 	= 	$(pid),
		$dialogDiv 	= 	$(".dialog_div"),
		selW    	=   $dialogBox.outerWidth(true),
		selH    	=   $dialogBox.outerHeight(true),
		winL    	=   ( $(window).width() - selW ) / 2,
		winT    	=   ( $(window).height() - selH ) / 2,
		winH  		= 	$(window).height(),
		bodyH 		= 	$('body').height();
		
	var	vHeight = winH > bodyH ? winH : bodyH;
		
	$dialogBox.css({"zIndex":11,'top':167 + $(document).scrollTop() + 'px','left':winL + 'px'}).animate({ 'top':200 + $(document).scrollTop() + 'px','opacity':"show"},300);
	$dialogDiv.animate({opacity:"show"},600).css({"height":vHeight,"width":$('body').width()});
}

function hideBox(sel,_divId,idx){
	var _num = 0;
	$('#div_'+_divId).find('.odst-list li').each(function(i,domEle){
	   var _val = $(domEle).find('span:eq(3) input[name^="fxqty_"]').val();
	   if(_val>0){
		   var  _h = $(domEle).find('span:eq(2)').html()
		   _num += (parseInt(_h)* parseInt(_val));
	   }
	 });
	$('#moeny_'+idx).val(_num);
	$(sel).closest(".dialog_box").animate({ 'top':167 + $(document).scrollTop() + 'px','opacity':"hide",'zIndex': -11},300)
	$(".dialog_div").animate({opacity:"hide"},600).remove();	
}

function hideDialog(){
	$(".dialog_box:visible").animate({ 'top':167 + $(document).scrollTop() + 'px','opacity':"hide",'zIndex': -11},300)
	$(".dialog_div").animate({opacity:"hide"},600).remove();
}

$(function(){
	
	$(".odst-mx").click(function(){	
		var pid 	= $(this).attr("pid");																				
		var $con 	= $('<c:forEach items="${orderList }" var="order" varStatus="status">'+
							'<div class="dialog_box" id="div_<c:out value="${order.sysUser.userCode }"/>" style="display:none;">'+
								'<div class="dialog_title"></div>'+
								'<div class="dialog_content">'+
									'<div class="odst-item">'+
										'<ul class="odst-list">'+
											'<c:forEach items="${productList}" var="pm" varStatus="count">'+
												'<c:forEach items="${pm.value}" var="pt">'+
													'<li>'+
														'<span class="odst-productNo">${pt.jpmProductSaleNew.productNo}</span>'+
														'<span class="odst-productName">${pt.jpmProductSaleNew.productName}</span>'+
														'<span class="odst-productPrice">${pt.price}</span>'+
														'<span>'+
															'<input type="hidden" name="pttid_<c:out value="${order.sysUser.userCode }"/>" value="${pt.pttId}"/>'+
															'<input type="text" name="fxqty_<c:out value="${order.sysUser.userCode }"/>" value="0" class="odst-input" />'+
														'</span>'+
													'</li>'+
												'</c:forEach>'+
											'</c:forEach>'+
										'</ul>'+
									'</div>'+
								'</div>'+
								'<div class="dialog_footer"><a href="javascript:void(0)" onclick="hideBox(this,\'${order.sysUser.userCode }\',\'${status.index+1}\');" class="btn_common corner2 fr" style="margin:10px;">确&nbsp;定</a></div>'+
							'</div>'+
						'</c:forEach>');
		var $mask 	= $('<div class="dialog_div" style="display:none;" ></div>');																		
		$("body").append($mask);	
		if($(".dialog_box").length == 0){
			$("#bigForm").append($con);
		}
		showBox(pid);
	
	});
});

</script>
<form action="${ctx }/bigOrderBuy" name="bigForm" id="bigForm" method="post">
	<input type="hidden" name="isBind" id="isBind" value="${isBind }"/>
	<input type="hidden" name="userCode_sy" id="userCode_sy" value="${userCode_sy }"/>
	<input type="hidden" name="flag" id="flag" value="${flag }"/>
	<!--  
	<input type="hidden" id="moeny_1" value="0"/>
	<input type="hidden" id="moeny_2" value="0"/>
	<input type="hidden" id="moeny_3" value="0"/>
	<input type="hidden" id="moeny_4" value="0"/>-->
	
	<c:forEach items="${orderList }" var="order" varStatus="inox">
		<input type="hidden" id="moeny_${inox.index+1}" value="0" orderCode="${order.memberOrderNo}"/>
		<table width="100%" class="personalInfoTab bigForm-table" style="margin:10px 0;">

			<colgroup style="width:80px;"></colgroup>
			<colgroup></colgroup>
			<colgroup style="width:80px;"></colgroup>
			<colgroup></colgroup>
			<colgroup style="width:60px;"></colgroup>
			<colgroup style="width:50px;"></colgroup>
			<colgroup style="width:80px;"></colgroup>
			<colgroup></colgroup>

			<tbody>
				<tr>
					<td class="tr"><label class=" bold">订单编号：</label></td>
					<td class="bold" colspan="9">
						
						<input type="hidden" name="moId" value="${order.moId }"/>
						<input type="hidden" name="orderNo" value="${order.memberOrderNo }"/>
						
						<span>${order.memberOrderNo }</span>&nbsp;
						<label>订单所属人：</label>
						<span>${order.sysUser.userCode }</span>&nbsp;
						<label>总金额：</label>
						<span class="pr">&yen;${order.amount }</span>&nbsp;
						<label >总PV：</label>
						<span class="pr">${order.pvAmt }</span>&nbsp;
						<label>创建时间：</label>
						<span><fmt:formatDate pattern='yyyy-MM-dd HH:mm:ss' value='${order.orderTime }' /></span>	
					</td>
				</tr>

				
				<tr>
					<td class="pdb10">&nbsp;</td>
					<td class="pdb10" colspan="8">
						<c:forEach items="${order.jpoMemberOrderList}"  var="jpoMemberOrderList" varStatus="status">
							<c:if test="${status.first}">
								<table style="width: 100%;text-align: center;" id="memOrder" border="1">
									<tr class="bold">
										<td style="width: 30%;">名称</td>
										<td style="width: 20%;">编码</td>
										<td style="width: 20%;">价格</td>
										<td style="width: 15%;">PV值</td>
										<td style="width: 15%;">数量</td>
									</tr>
							</c:if>
							<tr>
								<td>${jpoMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productName }</td>
								<td>${jpoMemberOrderList.jpmProductSaleTeamType.jpmProductSaleNew.productNo }</td>
								<td>${jpoMemberOrderList.price}元</td>
								<td>${jpoMemberOrderList.pv}PV</td>
								<td>${jpoMemberOrderList.qty }</td>
							</tr>
							<c:if test="${status.last}">
								</table>
							</c:if>
						</c:forEach>
					</td>
					<td class="pdb10">
						<c:choose>
							<c:when test="${order.amount <= 60000}">
								<c:if test="${isBind ==true && userCode_sy !=null}">
									<a class="odst-mx bold" style="color: red;" href="javascript:;" pid="#div_<c:out value="${order.sysUser.userCode }"/>" >辅销品</a>
								</c:if>		
							</c:when>
							<c:otherwise>
								<c:if test="${isBind ==true }">
								<a class="odst-mx bold" style="color: red;" href="javascript:;" pid="#div_<c:out value="${order.sysUser.userCode }"/>" >辅销品</a>
								</c:if>
							</c:otherwise>
						</c:choose>	
					</td>
				</tr>
			
				<tr>
					<td class="tr"><label class="star">省：</label></td>
					<td>
						<select name="province" id="province_${order.memberOrderNo }" class="mySelect" onchange="getIdCity('<c:out value="${order.memberOrderNo }"/>');"  data-required="true">
							<option value="" selected="selected"><ng:locale key="list.please.select"/></option>
							<c:forEach items="${alStateProvinces }" var="alStateProvinceVar">
								<option value="${alStateProvinceVar.stateProvinceId }">${alStateProvinceVar.stateProvinceName }</option>
							</c:forEach>
						</select>
					</td>
					<td class="tr"><label class="star">市：</label></td>
					<td>
						<select name="city" id="city_${order.memberOrderNo }" onchange="getIdDistrict('<c:out value="${order.memberOrderNo }"/>');" class="mySelect" data-required="true">
							<option value=""><ng:locale key="list.please.select"/></option>
						</select>
					</td>
					<td class="tr"><label class="star">区：</label></td>
					<td colspan="5">
						<select name="district" id="district_${order.memberOrderNo }" class="mySelect"  data-required="true">
						<option value=""><ng:locale key="list.please.select"/></option>
						</select>
					</td>
				</tr>

				<tr>
					<td class="tr"><label class="star">收货地址：</label></td>
					<td colspan="9">
						<input type="text" id="addressV_${order.memberOrderNo }" 
						name="addressV" data-required="true" style="width:699px;"/>
					</td>
				</tr>

				<tr>
					<td class="tr"><label class="star">收货人姓：</label></td>
					<td><input type="text" id="firtname_${order.memberOrderNo }" name="firtname" /></td>
					<td class="tr"><label class="star">收货人名：</label></td>
					<td><input type="text" id="lastname_${order.memberOrderNo }" name="lastname" /></td>
					<td class="tr"><label class="star">邮编：</label></td>
					<td colspan="5"><input type="text" id="postalcode_${order.memberOrderNo }" name="postalcode" /></td>
				</tr>

				<tr>
					<td class="tr"><label class="star">手机：</label></td>
					<td><input type="text" id="mobiletele_${order.memberOrderNo }" name="mobiletele" /></td>
					<td class="tr"><label class="pdl10">固话：</label></td>
					<td colspan="7"><input type="text" id="phone_${order.memberOrderNo }" name="phone" /></td>
				</tr>
			</tbody>
			
		</table>
		<div style="width:100%;height:5px;border-bottom:2px dashed #666;"></div>
	</c:forEach>
	
	<input type="button" value="提交订单" onclick="saveAddr();" class="btn_common corner2 fr mgt30 mgb20">

</form>

	

  
 