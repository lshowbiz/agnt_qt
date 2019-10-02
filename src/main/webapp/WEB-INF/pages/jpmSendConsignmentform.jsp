<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script src="<c:url value='/dwr/util.js'/>"></script>
		<script src="<c:url value='/dwr/engine.js'/>"></script>
		<script src="<c:url value='/dwr/interface/jalCityManager.js'/>"></script>
		<script src="<c:url value='/dwr/interface/jalDistrictManager.js'/>"></script>
	</head>
	<body onload="getIdCity();">
		<form:form commandName="jpmSendConsignment" method="post" action="jpmSendConsignmentform" id="jpmSendConsignmentForm" onsubmit="return submitValid();">
			<input type="hidden" name="specNo" id="specNo" value="<c:out value="${ model['specNo'] }" />" />
			<input type="hidden" name="specName" id="specName" value="<c:out value="${ model['specName'] }" />" />
			<input type="hidden" name="num" id="num" value="<c:out value="${ model['consignmenNum'] }" />" />
			<input type="hidden" name="consignmentNo" id="consignmentNo" value="${jpmSendConsignment.consignmentNo }" />
			<input type="hidden" name="city" id="city" value="${jpmSendConsignment.cusCity }" />
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
								收货人名字:
							</label>
						</td>
						<td>
							<input name="userName" id="userName" cssClass="formerror" value="${jpmSendConsignment.consignmentNo }"/>
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>
							<label class="star">
								收货地址:
							</label>
						</td>
						<td>
							<select name="cusProvince" id="cusProvince" cssClass="formerror mySelect" onchange="getIdCity();">
								<option label="" value="">
									<ng:locale key='list.please.select' />
								</option>
								<c:forEach items="${ alStateProvinces}" var="provinces">
								<c:if test="${provinces.stateProvinceId == jpmSendConsignment.cusProvince}">
									<option label="" value="${provinces.stateProvinceId}" selected="selected">
										${provinces.stateProvinceName}
									</option>
								</c:if>
									<option label="" value="${provinces.stateProvinceId}">
										${provinces.stateProvinceName}
									</option>
									<!--                                    <options items="${alStateProvinces}" itemValue="stateProvinceId" itemLabel="stateProvinceName"/>-->
								</c:forEach>
							</select>
						</td>
						<td>
							<label class="star">
								<ng:locale key="miMember.idAddr2" />
								:
							</label>
						</td>
						<td>
							<select name="cusCity" id="cusCity" class="mySelect" onchange="getIdDistrict();" >
								<option value="">
									<ng:locale key="list.please.select" />
								</option>
							</select>
						</td>
						<td>
							<!--                            <label class="pdl10"><ng:locale key="miMember.district" />:</label>-->
						</td>
						<td>
							<!--                                <select name="district" id="district" class="mySelect">-->
							<!--                                    <option value=""><ng:locale key="list.please.select"/></option>-->
							<!--                                </select>-->
						</td>
					</tr>
					<tr>
						<td>
							<label class="star">
								详细地址:
							</label>
						</td>
						<td colspan="4">
							<input name="address" id="address" cssClass="formerror"	cssStyle="width:80%;" value="${jpmSendConsignment.address }"/>
						</td>
						<td></td>
					</tr>
					<tr>
						<td>
							<label class="pdl10">
								邮编:
							</label>
						</td>
						<td>
							<input name="postalCode" id="postalCode" cssClass="formerror" value="${jpmSendConsignment.postalCode }"/>
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>
							<label class="pdl10">
								<ng:locale key="miMember.phone" />
								:
							</label>
						</td>
						<td>
							<input name="phone" id="phone" cssClass="formerror" value="${jpmSendConsignment.phone }"/>
						</td>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td>
							<label class="pdl10">
								发货数量:
							</label>
						</td>
						<td>
							<input name="consignmenNum" id="consignmenNum" cssClass="formerror" value="${jpmSendConsignment.consignmenNum }"/>
						</td>
						<td colspan="2" style="color: red;">您还剩余<c:out value="${ model['consignmenNum'] }" />瓶未分配!</td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td colspan="5">
							<input type="submit" value="保存" class="btn_common corner2 fr mgt30" />
						</td>
						<td>
						<a href="javascript:history.go(-1);" class="btn_common corner2 fr">取消</a>
						</td>
					</tr>
				</tbody>
			</table>
		</form:form>
<script type="text/javascript">
var jq = jQuery.noConflict();

			function getIdCity(){
			   var province=jq("#cusProvince").val();
			   jalCityManager.getAlCityByProvinceId(province,callIdCity);
		   }
		   function callIdCity(valid){
		   			
		 		  DWRUtil.removeAllOptions("cusCity");
		 		  DWRUtil.addOptions("cusCity",{'':'<ng:locale key='list.please.select'/>'});  
                  DWRUtil.addOptions("cusCity",valid,"cityId","cityName");
			   		
			   getIdDistrict();
		   }
		    function getIdDistrict(){
		   		var city=jq("#city").val();
		   		if(null != city && '' != city)
		   		{
		   			jq("#cusCity").val(city);
		   		}
		   		//jalDistrictManager.getAlDistrictByCityId(city,callBackIdDistrict);
		   }
		   function callBackIdDistrict(valid){
	    		DWRUtil.removeAllOptions("district");
	 			DWRUtil.addOptions("district",{'':'<ng:locale key='list.please.select'/>'});  
                DWRUtil.addOptions("district",valid,"districtId","districtName");
		   		if(''!='${jmiStore.district}'){
		   			jq("#district").val('${jmiStore.district}');
		   		}

		   }
		   getIdCity();
	function submitValid()
	{
		var userName = $("#userName").val();
		var cusProvince = $("#cusProvince").val();
		var cusCity = $("#cusCity").val();
		var address = $("#address").val();
		var postalCode = $("#postalCode").val();
		var consignmenNum = $("#consignmenNum").val();
		var num = $("#num").val();
		var phone = $("#phone").val();
		
		if(null == userName || '' == userName || 'null' == userName)
		{
			alert('请填写收货人!');
			return false;
		}
		if(null == cusProvince || '' == cusProvince || 'null' == cusProvince)
		{
			alert('请填写收货地址!');
			return false;
		}
		if(null == cusCity || '' == cusCity || 'null' == cusCity)
		{
			alert('请填写收货地址!');
			return false;
		}
		if(null == address || '' == address || 'null' == address)
		{
			alert('请填写详细地址!');
			return false;
		}
		if(null == postalCode || '' == postalCode || 'null' == postalCode)
		{
			alert('请填写邮编!');
			return false;
		}
		if(null == phone || '' == phone || 'null' == phone)
		{
			alert('请填写联系电话!');
			return false;
		}
		if(null == consignmenNum || '' == consignmenNum || 'null' == consignmenNum)
		{
			alert('请填写发货数量!');
			return false;
		}
		if(0 == consignmenNum || '0' == consignmenNum)
		{
			alert('请填写大于零的发货数量!');
			return false;
		}
		if(Number(consignmenNum) > Number(num))
		{
			alert('对不起,您已经没有'+consignmenNum+'瓶货物需要发送啦!');
			return false;
		}
	}
</script>
	</body>
</html>

