<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>

		<title>My JSP 'wineMemberConfigList.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
		<script src="<c:url value='/dwr/util.js'/>"></script>
		<script src="<c:url value='/dwr/engine.js'/>"></script>
		<script src="<c:url value='/dwr/interface/jpmSendConsignmentManager.js'/>"></script>
		<script src="${pageContext.request.contextPath}/scripts/dialogBox.js" ></script>
		<script src="<c:url value='/dwr/interface/jalCityManager.js'/>"></script>
		<script src="<c:url value='/dwr/interface/jalDistrictManager.js'/>" ></script>
		<script src="<c:url value='/dwr/interface/jmiAddrBookManager.js'/>" ></script>
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="${pageContext.request.contextPath}/scripts/dialogBox.js" ></script>
	</head>
	

	<body>
			<input type="hidden" name="num" id="num" value="<c:out value="${ model['consignmenNum'] }" />" />
			<h2 class="title mgb20">
				发货单配置
			</h2>
			<table width="100%" class="personalInfoTab">
				<colgroup style="width: 100px;"></colgroup>
				<colgroup></colgroup>
				<colgroup style="width: 90px;"></colgroup>
				<colgroup></colgroup>
				<colgroup style="width: 110px;"></colgroup>
				<tbody>
					<tr>
						<td>
							<label class="star">
								收货地址选择:
							</label>
						</td>
						<td>
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<c:forEach items="${jmiAddrBooks }" var="jmiAddrBookVar">
						<tr>
							<td colspan="6">
							<c:if test="${jpmSendConsignment.fabId == jmiAddrBookVar.fabId}">
								<input type="radio" name="fabId" id="${jmiAddrBookVar.fabId}" value="${jmiAddrBookVar.fabId}" checked="checked" />
							</c:if>
							<c:if test="${jpmSendConsignment.fabId != jmiAddrBookVar.fabId}">
								<input type="radio" name="fabId" id="${jmiAddrBookVar.fabId}" value="${jmiAddrBookVar.fabId}"/>
							</c:if>
								<span>
						         	<ng:region regionType="p" regionId="${jmiAddrBookVar.province }"></ng:region>
						         		<input type="hidden"  value="${jmiAddrBookVar.province }">
						         	 </span>
						         <span>
						         	<ng:region regionType="c" regionId="${jmiAddrBookVar.city }"></ng:region>
						         		<input type="hidden"  value="${jmiAddrBookVar.city }">
						         </span> 
						         <span>
						         	<ng:region regionType="d" regionId="${jmiAddrBookVar.district }"></ng:region>
						         		<input type="hidden"  value="${jmiAddrBookVar.district }">
						         </span>
						         <span>${jmiAddrBookVar.address}</span>
								 <span>（${jmiAddrBookVar.firstName}${jmiAddrBookVar.lastName}&nbsp;收）</span>
								 <span>${jmiAddrBookVar.phone}</span>
							</td>
						</tr>
					</c:forEach>
					<tr>
						<td>
							<label class="pdl10">
								发货数量:
							</label>
						</td>
						<td>
							<input name="consignmenNum" id="consignmenNum" cssClass="formerror" value="${jpmSendConsignment.consignmenNum }" 
							onkeydown='if((event.keyCode<96 || event.keyCode>105) && (event.keyCode<48 || event.keyCode>57) && event.keyCode!=8 && event.keyCode!=13 && event.keyCode!=46 && (event.keyCode<37 || event.keyCode>40)){return false;}'/>
							<input type="hidden" name="curConsignmentNum" id="curConsignmentNum" value="${jpmSendConsignment.consignmenNum }"/>
						</td>
						<td colspan="2" style="color: red;">您还剩余<c:out value="${ model['consignmenNum'] }" />瓶未分配!</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="5">
							<a href="javascript:submitValid('${pageContext.request.contextPath}/jpmSendConsignments/addConsignment','<c:out value="${ model['specNo'] }" />','${jpmSendConsignment.consignmentNo }','<c:out value="${ model['productNo'] }" />','<c:out value="${ model['molId'] }" />');" class="btn_common corner2 fr">生成发货单</a>
						</td>
						<td>
							<a href="${pageContext.request.contextPath}/jpoWineMemberOrders/orderConfigList?molId=<c:out value="${ model['molId'] }" />&productNo=<c:out value="${ model['productNo'] }" />" class="btn_common corner2 fr">返回</a>
						</td>
					</tr>
				</tbody>
				<div class="newAddress">
					<a href="javascript:void(0);" class="hoverLine" id="js_newAddr">使用新地址</a>
				</div>
			</table>
	
	
	
	
	
	
	
	
	
<h2 class="title mgb20">
	发货单配置管理
</h2>
<div class="content fr">
	<table class="personalInfoTab">
		<tbody>
			<tr>
				<td>
<!--					<a href="${pageContext.request.contextPath}/jpmSendConsignments/addPage?specNo=<c:out value="${ model['specNo'] }" />&specName=<c:out value="${ model['specName'] }" />&consignmenNum=<c:out value="${ model['consignmenNum'] }" />"  class="btn_common corner2" style="margin-bottom:5px;">新增</a>-->
				</td>
			</tr>
		</tbody>
	</table>
	<table width="100%" border="0" class="tabQueryLs" id="tabQueryLs"
		style="margin-top: 2px;">
		<thead>
			<tr>
				<th>


				</th>
				<th>
					收货地址
				</th>
				<th>
					发货数量
				</th>
				<th>
					配置时间
				</th>
				<th>
					操作
				</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${ jpmSendConsignmentList}" var="consignment">
				
				<tr>
				<td></td>
					<td>
					<input type="hidden" name="consignmentNo" id="consignmentNo" value="${consignment.consignmentNo }">
					<input type="hidden" name="fabId" id="fabId" value="${consignment.fabId }">
						${consignment.address }
					</td>
					<td>
						<input type="hidden" name="consignmenNum" id="consignmenNum" value="${consignment.consignmenNum }">
						${consignment.consignmenNum}
					</td>
					<td>
						${consignment.sendDate }
					</td>
					<td>
						<a href="${pageContext.request.contextPath}/jpmSendConsignments/sendConsignmentPage?specNo=<c:out value="${ model['specNo'] }" />&consignmentNo=${consignment.consignmentNo}&productNo=<c:out value="${ model['productNo'] }" />&molId=<c:out value="${ model['molId'] }" />" class="red ft12" >修改</a>
						 | 
						<a href="#" title="删除" class="red ft12" onclick="del('${pageContext.request.contextPath}/jpmSendConsignments/delConsignment','${consignment.consignmentNo}','<c:out value="${ model['specNo'] }" />','<c:out value="${ model['productNo'] }" />','<c:out value="${ model['molId'] }" />')">删除</a>
					</td>
				</tr>

			</c:forEach>
		</tbody>
	</table>
</div>
<div class="clear"></div>
<div class="mask" id="mask"></div>
	<div class="popupAddr abs" id="popupAddr" style="display:none;">
		<h2>使用新地址</h2>
	<form  id="addrForm">
			<table width="100%" border="0" class="addInfo">
				<colgroup style="width:100px;"></colgroup>
				
				
				<tbody id="address">
					<tr>
						<td class="tr"><label class="star">省：</label></td>
						<td class="w200"><div class="rel">
					             
                        <select name="province" id="province" class="mySelect" onchange="getIdCity();"  data-required="true">
                            <option value="" selected="selected"><ng:locale key="list.please.select"/></option>
                            <c:forEach items="${alStateProvinces }" var="alStateProvinceVar">
                                 <option value="${alStateProvinceVar.stateProvinceId }">${alStateProvinceVar.stateProvinceName }</option>
                            </c:forEach>
                        </select>
					
					
					
						 
						
						</div></td>
						<td class="tr w60"><label class="star">市：</label></td>
						<td class="w200">
						<div class="rel">
					
							<select name="city" id="city" onchange="getIdDistrict();" class="mySelect" data-required="true">
			             <option value=""><ng:locale key="list.please.select"/></option>
		               </select>
						</div>
						
					</td>
						<td class="tr w60"><label>区：</label></td>
						<td class="w100">
                    <div class="rel">
						<select name="district" id="district" class="mySelect"  data-required="true">
							<option value=""><ng:locale key="list.please.select"/></option>
						
						</select>
						</div>
						</td>
						<td class="tr w60"><label class="star">邮编：</label></td>
						<td><div class="rel"><input type="text" id="postalcode" class="w60" data-required="true" data-type="number" data-maxlength="6" /></div></td>
					</tr>
					<tr>
						<td class="tr"><label class="star">街道地址：</label></td>
						<td colspan="4"><div class="rel"><input type="text" name="" id="addressV" data-required="true" style="width:85%;" /></div></td>
						<td colspan="3">&nbsp;</td>
					</tr>
					<tr>
						<td class="tr"><label class="star">收货人姓：</label></td>
						<td><div class="rel"><input type="text" name="" id="firstName" class="w100" data-required="true" /></div></td>
						
					</tr>
					<tr>
					<td class="tr"><label class="star">收货人名：</label></td>
						<td><div class="rel"><input type="text" name="" id="lastName" class="w100" data-required="true" /></div></td>
					</tr>
					<tr>
						<td class="tr"><label class="star" >手机：</label></td>
						<td>
							<div class="rel"><input type="text" name="" id="mobiletele" class="w100" data-required="true" data-type="number" data-maxlength="11" /></div>
						</td>
						<td colspan="6">&nbsp;</td>
					</tr>
					<tr>
						<td class="tr"><label>固话：</label></td>
						<td><input type="text" name="" id="phone" class="w100" /></td>
						<td colspan="6">&nbsp;</td>
					</tr>
<!--					<tr>-->
<!--						<td>&nbsp;</td>-->
<!--						<td>-->
<!--							<label for="defaultAddr">-->
<!--								<input type="checkbox" name="" id="defaultAddr" checked="checked"  id="defaultAddr"/>&nbsp;-->
<!--								<span>设置为默认地址</span>-->
<!--							</label>-->
<!--						</td>-->
<!--						<td colspan="6">设置后系统将在购买时自动选中该收货地址</td>-->
<!--					</tr>-->
					<tr>
						<td>&nbsp;</td>
						<td><a href="javascript:void(0);" class="btn_common btn_mini corner2" id="js_confirm" onclick="saveAddr();">确认</a></td>
						<td colspan="9"><a href="javascript:void(0);" class="btn_common btn_mini corner2" id="js_cancel">取消</a></td>
					</tr>
				</tbody>
			</table>
			</form>
		</div>
<script>
function del(url,consignmentNo,specNo,productNo,molId){
    if(confirm("你确定要删除吗,删除后将无法恢复数据?")){
  		
        location.href=url+"?consignmentNo="+consignmentNo+"&specNo="+specNo+"&productNo="+productNo+"&molId="+molId;
    }
    else{
        return;
    }
}

function callBack(valid){
	 alert("更新成功");
	 window.location.reload();
}

function submitValid(url,specNo,consignmentNo,productNo,molId)
	{
		var fabId = $("input:radio[name='fabId']:checked").val();
		var consignmenNum = $("#consignmenNum").val();
		var num = $("#num").val();
		var curConsignmentNum = $("#curConsignmentNum").val();
		
		
		if(null == fabId || '' == fabId || 'null' == fabId)
		{
			alert('请选择收货地址!');
			return;
		}
		if(null == consignmenNum || '' == consignmenNum || 'null' == consignmenNum)
		{
			alert('请填写发货数量!');
			return;
		}
		if(0 == consignmenNum || '0' == consignmenNum)
		{
			alert('请填写大于零的发货数量!');
			return;
		}
		if(null == curConsignmentNum || '' == curConsignmentNum)
		{
			if(Number(consignmenNum) > Number(num))
			{
				alert('对不起,您已经没有'+consignmenNum+'瓶货物需要发送啦!');
				return;
			}
		}
		else
		{
			if(Number(consignmenNum) > Number(curConsignmentNum))
			{
				alert('对不起,您已经没有'+consignmenNum+'瓶货物需要发送啦!');
				return;
			}
		}
		location.href=url+"?fabId="+fabId+"&consignmenNum="+consignmenNum+"&specNo="+specNo+"&consignmentNo="+consignmentNo+"&productNo="+productNo+"&molId="+molId;
	}
</script>
<script>
   $(function(){		
		
			$('#mask,#js_cancel').click(function(){
				if( $("#popupAddr:visible") ) closeDialog("#js_cancel");
			}); 

			$('#addrForm').coffee({  
				click: {  
					'#js_cancel': function(){  
						$('#addrForm').parsley('reset').find(":text").val('').end().find("select").find(":selected").val('').text("请选择");
					},  
					'#js_confirm': function(){  
						$('#addrForm').parsley('validate');
						
					}  
				}
			});
			
			var initId = $("#js_address").find(":checked").attr('id');
			
			$('#formOrder').find(":hidden[name='fabName']").val(initId);
		});
 	function getIdCity(){
			   var province=$("#province").val();
			   jalCityManager.getAlCityByProvinceId(province,callIdCity);
     }
	function callIdCity(valid){
		 		  DWRUtil.removeAllOptions("city");
		 		  DWRUtil.addOptions("city",{'':'select..'});  
                  DWRUtil.addOptions("city",valid,"cityId","cityName");
			   		if(''!='${jmiMember.city}'){
			   			$("#city").val('${jmiMember.city}');
	
			   		}
			   getIdDistrict();
		   }
   function getIdDistrict(){
		   		var city=$("#city").val();
		   		jalDistrictManager.getAlDistrictByCityId(city,callBackIdDistrict);
		   }
   function callBackIdDistrict(valid){
	    		DWRUtil.removeAllOptions("district");
	 			DWRUtil.addOptions("district",{'':'select..'});  
                DWRUtil.addOptions("district",valid,"districtId","districtName");
		   		if(''!='${jmiMember.district}'){
		   			jq("#district").val('${jmiMember.district}');
		   		}

		   }
		   
   function saveAddr(){
			
			var firstName=$('#firstName').val();
			var lastName=$('#lastName').val();
			var province=$('#province').val();
			var city=$('#city').val();
			var district=$('#district').val();
			var address=$('#addressV').val();
			var postalcode=$('#postalcode').val();
			var mobiletele=$('#mobiletele').val();
			var phone=$('#phone').val();
			var isDefault=$('#defaultAddr').attr('checked');
			if(isDefault=='checked'){
				isDefault='1';
			}else{
				isDefault='';
			}
			
			if(firstName==''){
				 var errorMsg = '<ng:locale key="errors.required" args="shipping.firstName" argTransFlag="true"/>';
				 alert(errorMsg);
				 return;
			}
			if(lastName==''){
				 var errorMsg = '<ng:locale key="errors.required" args="shipping.lastName" argTransFlag="true"/>';
				 alert(errorMsg);
				 return;
			}
			if(province==''){
				 var errorMsg = '<ng:locale key="errors.required" args="shipping.province" argTransFlag="true"/>';
				 alert(errorMsg);
				 return;
			}
			if(city==''){
				 var errorMsg = '<ng:locale key="errors.required" args="shipping.city" argTransFlag="true"/>';
				 alert(errorMsg);
				 return;
			}
			if(address==''){
				 var errorMsg = '<ng:locale key="errors.required" args="shipping.address" argTransFlag="true"/>';
				 alert(errorMsg);
				 return;
			}
			
			if(postalcode==''){
				 var errorMsg = '<ng:locale key="errors.required" args="shipping.postalcode" argTransFlag="true"/>';
				 alert(errorMsg);
				 return;
			}
			if(mobiletele==''){
				 var errorMsg = '<ng:locale key="errors.required" args="miMember.mobiletele" argTransFlag="true"/>';
				 alert(errorMsg);
				 return;
			}else{
				var patrn=/^([0-9]{11})$/;	
				if(!patrn.exec(mobiletele)){
					alert('手机号码错误');
					 return;
				}else{
				
				}
			}
			
			if(isDefault=='checked'){
				isDefault='1';
			}else{
				isDefault='';
			}
			var jmiAddrBook;
			jmiAddrBook={"firstName":firstName,"lastName":lastName,"province":province,"city":city,"district":district,"address":address,"postalcode":postalcode,"mobiletele":mobiletele,"phone":phone,"isDefault":isDefault};
			jmiAddrBookManager.saveJmiAddrBookPc(jmiAddrBook,function(){window.location.reload();});
					
		
		}
</script>
	</body>
</html>