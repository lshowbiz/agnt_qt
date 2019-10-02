
<%@ page contentType="text/html; charset=utf-8" language="java" %><%@ include file="/common/taglibs.jsp" %>

    <script type="text/javascript" src="<c:url value='/scripts/lib/jquery-1.8.2.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/scripts/region.js'/>"></script>
<script src="<c:url value='/dwr/util.js'/>" ></script> 
<script src="<c:url value='/dwr/engine.js'/>" ></script>

<script src="<c:url value='/dwr/interface/jmiAddrBookManager.js'/>" ></script>
<script src="<c:url value='/dwr/interface/jmiMemberManager.js'/>" ></script>
	
<script>
function saveAddr(){

	var fabId=$('#fabId').val();
	var firstName=$('#firstName').val();
	var lastName=$('#lastName').val();
	var province=$('#province').val();
	var city=$('#city').val();
	var district=$('#district').val();
	var address=$('#address').val();
	var postalcode=$('#postalcode').val();
	var mobiletele=$('#mobiletele').val();
	var phone=$('#phone').val();
	var isDefault=$('#isDefault').attr('checked');
	
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
	if(district==''){
		 var errorMsg = '<ng:locale key="errors.required" args="shipping.district" argTransFlag="true"/>';
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
	
	
	var idno_firstName=$('#idno_firstName').val();
	var idno_lastName=$('#idno_lastName').val();
	var idno_province=$('#idno_province').val();
	var idno_city=$('#idno_city').val();
	var idno_district=$('#idno_district').val();
	var idno_address=$('#idno_address').val();
	var idno_mobiletele=$('#idno_mobiletele').val();
	var petName=$('#petName').val();
	

	<c:if test="${infoEmpty==true }">
	if(idno_firstName==''){
		 var errorMsg = '<ng:locale key="errors.required" args="姓" argTransFlag="true"/>';
		 alert(errorMsg);
		 return;
	}
	if(idno_lastName==''){
		 var errorMsg = '<ng:locale key="errors.required" args="名" argTransFlag="true"/>';
		 alert(errorMsg);
		 return;
	}
	 var result=false;
	 dwr.engine.setAsync(false);
     jmiMemberManager.getIsSameName(idno_firstName,idno_lastName,function(data){result=data})
     dwr.engine.setAsync(true);

	if(!result){
		 alert('姓名有误');
		 return;
	}
	if(idno_province==''){
		 var errorMsg = '<ng:locale key="errors.required" args="身份证省" argTransFlag="true"/>';
		 alert(errorMsg);
		 return;
	}
	if(idno_city==''){
		 var errorMsg = '<ng:locale key="errors.required" args="身份证市" argTransFlag="true"/>';
		 alert(errorMsg);
		 return;
	}
	if(idno_district==''){
		 var errorMsg = '<ng:locale key="errors.required" args="身份证区" argTransFlag="true"/>';
		 alert(errorMsg);
		 return;
	}
	if(idno_address==''){
		 var errorMsg = '<ng:locale key="errors.required" args="地址" argTransFlag="true"/>';
		 alert(errorMsg);
		 return;
	}
	
	if(petName==''){
		 var errorMsg = '<ng:locale key="errors.required" args="miMember.petName" argTransFlag="true"/>';
		 alert(errorMsg);
		 return;
	}
	if(idno_mobiletele==''){
		 var errorMsg = '<ng:locale key="errors.required" args="miMember.mobiletele" argTransFlag="true"/>';
		 alert(errorMsg);
		 return;
	}else{
		var patrn=/^([0-9]{11})$/;	
		if(!patrn.exec(idno_mobiletele)){
			alert('手机号码错误');
			 return;
		}else{
		
		}
	}
	

	 </c:if>
	
	if(isDefault=='checked'){
		isDefault='1';
	}else{
		isDefault='';
	}
	
	if(isFormPosted()){

		var jmiAddrBook;
		jmiAddrBook={"fabId":fabId,"firstName":firstName,"lastName":lastName,"province":province,"city":city,"district":district,"address":address,"postalcode":postalcode,"mobiletele":mobiletele,"phone":phone,"isDefault":isDefault};
		
		var memberInfo;
		<c:if test="${infoEmpty==true }">
		 memberInfo={"idno_firstName":idno_firstName,"idno_lastName":idno_lastName,"idno_province":idno_province,"idno_city":idno_city,"idno_district":idno_district,"idno_address":idno_address,"petName":petName,"idno_mobiletele":idno_mobiletele};
			
		 </c:if>
		jmiMemberManager.saveJmiMemberAndFiAddr(jmiAddrBook,memberInfo,callBack);
			
	}
	
	

}


function callBack(valid){
	 alert("更新成功");
	 window.location.reload();
}
</script>	
<style>
 .info_cs{
<c:if test="${infoEmpty==true }">
 height: 460px;
 
 </c:if>
 }
</style>


	<div class="mask" id="mask_if"></div>
<div class="popupAddr abs info_cs"  id="popupAddr_if" style="display:none; ">
		<h2 class="color2">默认收货地址填写</h2>
		<form  id="addrForm">
			<table width="100%" border="0" class="addInfo">
				<colgroup style="width:100px;"></colgroup>
				
				
				<tbody >
					<tr>
						<td class="tr"><label class="star">省：</label></td>
						<td class="w200">
							<div class="rel">
									 
								<select name="province" id="province" onchange="getIdCity()" class="mySelect" data-required="true">
									<option value=""><ng:locale key="list.please.select"/></option>
								</select>
						         		<input type="hidden" id="hiddenProvince" name="hiddenProvince"  value="">
							</div>
						</td>
						<td class="tr"><label class="star">市：</label></td>
						<td class="w200">
							<div class="rel">
						
								<select name="city" id="city" onchange="getIdDistrict();" class="mySelect" data-required="true">
									<option value=""><ng:locale key="list.please.select"/></option>
								</select>
						         		<input type="hidden" id="hiddenCity" name="hiddenCity"  value="">
							</div>
							
						</td>
						<td class="tr"><label class="star">区：</label></td>
						<td class="w200">
							<div class="rel">
								<select name="district" id="district" class="mySelect"  data-required="true">
									<option value=""><ng:locale key="list.please.select"/></option>
								
								</select>
						         		<input type="hidden" id="hiddenDistrict" name="hiddenDistrict"  value="">
							</div>
						</td>
					</tr>
					<tr>	

						<td class="tr"><label class="star">邮编：</label></td>
						<td><div class="rel"><input type="text" id="postalcode" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="6" class="w60" data-required="true" data-type="number" data-maxlength="6" /></div></td>
					
						<td class="tr"><label class="star">街道地址：</label></td>
						<td colspan="3"><div class="rel"><input type="text" name="" id="address" data-required="true" style="width:93.5%;" /></div></td>
					
					</tr>
					<tr>
						<td class="tr"><label class="star">收货人姓：</label></td>
						<td ><div class="rel"><input type="text" name="" id="firstName" class="w100" data-required="true" /></div></td>
						
					
						<td class="tr"><label class="star">收货人名：</label></td>
						<td><div class="rel"><input type="text" name="" id="lastName" class="w100" data-required="true" /></div></td>
					
						<td class="tr"><label class="star" >手机：</label></td>
						<td>
							<div class="rel"><input type="text" name="" id="mobiletele" class="w100" data-required="true" data-type="number" data-maxlength="11" /></div>
						</td>
				
					</tr>
					<tr>
						<td class="tr"><label>固话：</label></td>
						<td><input type="text" name="" id="phone" class="w100" /></td>
						<td colspan="6">&nbsp;</td>
					</tr>
					<tr>
						
						<td colspan="2">
							<label for="defaultAddr">
								<input type="checkbox" disabled="disabled"  name="" id="defaultAddr" checked="checked"  id="defaultAddr"/>&nbsp;
								<span class="mt">设置为默认地址</span>
							</label>
						</td>
						<td >
							<c:if test="${infoEmpty==true }">
								<label for="defaultAddr">
								<input type="checkbox"   name="sameShipAddr"  onclick="sameShip()" id="sameShipAddr" />&nbsp;
								<span class="mt">身份证信息同收货地址</span></label>
							</c:if>
						</td>
						<td colspan="3" class="tr color1">设置后系统将在购买时自动选中该收货地址</td>
					</tr>
					
					
					
					
					<!-- 会员信息 -->
					<c:if test="${infoEmpty==true }">
					<tr>
						<td class="tr"><label class="star">身份证省：</label></td>
						<td class="w200">
							<div class="rel">
									 
								<select name="idno_province" id="idno_province" onchange="get_idno_city()" class="mySelect" data-required="true">
									<option value=""><ng:locale key="list.please.select"/></option>
								</select>
						         		<input type="hidden" id="idNohiddenProvince" name="idNohiddenProvince"  value="">
							</div>
						</td>
						<td class="tr"><label class="star">身份证市：</label></td>
						<td class="w200">
							<div class="rel">
						
								<select name="idno_city" id="idno_city" onchange="get_idno_district();" class="mySelect" data-required="true">
									<option value=""><ng:locale key="list.please.select"/></option>
								</select>
						         		<input type="hidden" id="idNohiddenCity" name="idNohiddenCity"  value="">
							</div>
							
						</td>
						<td class="tr"><label class="star">身份证区：</label></td>
						<td class="w200">
							<div class="rel">
								<select name="idno_district" id="idno_district" class="mySelect"  data-required="true">
									<option value=""><ng:locale key="list.please.select"/></option>
								
								</select>
						         		<input type="hidden" id="idNohiddenDistrict" name="idNohiddenDistrict"  value="">
							</div>
						</td>
					</tr>
					<tr>	

						<td class="tr"><label class="star">街道地址：</label></td>
						<td colspan="3"><div class="rel"><input type="text" name="idno_address" id="idno_address" data-required="true" style="width:93.5%;" /></div></td>
					
						<td class="tr"><label class="star">昵称：</label></td>
						<td ><div class="rel"><input type="text" name="petName" id="petName" class="w100" data-required="true" /></div></td>
					
					</tr>
					<tr>
						<td class="tr"><label class="star">姓：</label></td>
						<td ><div class="rel"><input type="text" name="idno_firstName" id="idno_firstName" class="w100" data-required="true" /></div></td>
						
					
						<td class="tr"><label class="star">名：</label></td>
						<td><div class="rel"><input type="text" name="idno_lastName" id="idno_lastName" class="w100" data-required="true" /></div></td>
					
						<td class="tr"><label class="star" >手机：</label></td>
						<td>
							<div class="rel"><input type="text" name="idno_mobiletele" id="idno_mobiletele" class="w100" data-required="true" data-type="number" data-maxlength="11" /></div>
						</td>
				
					</tr>
					</c:if>
					
					
					
					
					<tr>
					
						<td colspan="3" class="tr"><button href="javascript:void(0);"  id="js_confirm" type="button" onclick="saveAddr();">确认</button></td>
						<td colspan="3" class="tl"><button href="#" onclick="javascript:location.reload()" class="btn btn-success" type="button">刷新</button></td> 
					</tr>
				</tbody>
			</table>
		</form>
	</div>
	
	
	
	
	
<script>

dwr.engine.setAsync(false);
function getProvince(){
	var provinceElemment=document.getElementById('province');

	var hiddenProvince=$('#hiddenProvince').val();

	for(var oo in jal_state_province){  

		 var o=new Option(jal_state_province[oo].state_province_name,jal_state_province[oo].state_province_id);
		 provinceElemment.options.add(o);  
	}  
	
	if(hiddenProvince!=''){
		$('#province').val(hiddenProvince);
	}
	 getIdCity();
}
	


	
	
function getIdCity(){
	
	var province=document.getElementById('province').value;

	var cityElemment=document.getElementById('city');
		cityElemment.options.length=0;
		var o=new Option("<ng:locale key="list.please.select"/>","");
		cityElemment.options.add(o);  

		
   var districtElemment=document.getElementById('district');
		districtElemment.options.length=0;
		var o=new Option("<ng:locale key="list.please.select"/>","");
		districtElemment.options.add(o);  
	
		for(var oo in jal_city){  
			if (jal_city[oo].state_province_id  == province) {
				 var o=new Option(jal_city[oo].city_name,jal_city[oo].city_id);
			     cityElemment.options.add(o);  
			    
			}
			
		}  

		var hiddenCity=$('#hiddenCity').val();
   		if(hiddenCity!=''){
   			$('#city').val(hiddenCity);
   		}
	getIdDistrict();
}

function getIdDistrict(){
	 var city=document.getElementById('city').value;
	 var districtElemment=document.getElementById('district');
    		
		districtElemment.options.length=0;

  		var o=new Option("<ng:locale key="list.please.select"/>","");
   		districtElemment.options.add(o);
	 
   		
   		for(var oo in jal_district){  
			if (jal_district[oo].city_id  == city) {
				 var o=new Option(jal_district[oo].district_name,jal_district[oo].district_id);
				 districtElemment.options.add(o);  
			    
			}
			
		} 
   		
   		var hiddenDistrict=$('#hiddenDistrict').val();
		if(hiddenDistrict!=''){
	   			$('#district').val(hiddenDistrict);
	   	}
}



function getDefJmiAddrBook(){
	//alert(1111111);
	jmiAddrBookManager.getDefaultJmiAddrBookByUserCodeHome('${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.username}',callGetDefJmiAddrBook);
	
}

function callGetDefJmiAddrBook(valid){
	//alert(valid);
	if(valid!=null){
		$('#fabId').val();
		$('#firstName').val(valid.firstName);
		$('#lastName').val(valid.lastName);
		$('#hiddenProvince').val(valid.province);
		$('#hiddenCity').val(valid.city);
		$('#hiddenDistrict').val(valid.district);
		$('#address').val(valid.address);
		$('#postalcode').val(valid.postalcode);
		$('#mobiletele').val(valid.mobiletele);
		$('#phone').val(valid.phone);
		
		
		
	}

	getProvince();
}

function get_idno_province(){
	var provinceElemment=document.getElementById('idno_province');

	var hiddenProvince=$('#idNohiddenProvince').val();

	for(var oo in jal_state_province){  

		 var o=new Option(jal_state_province[oo].state_province_name,jal_state_province[oo].state_province_id);
		 provinceElemment.options.add(o);  
	}  
	//if(hiddenProvince!=''){
		$('#idno_province').val(hiddenProvince);
	//}
	get_idno_city();
}
	


	
	
function get_idno_city(){
	
	var province=document.getElementById('idno_province').value;

	var cityElemment=document.getElementById('idno_city');
		cityElemment.options.length=0;
		var o=new Option("<ng:locale key="list.please.select"/>","");
		cityElemment.options.add(o);  

		
   var districtElemment=document.getElementById('idno_district');
		districtElemment.options.length=0;
		var o=new Option("<ng:locale key="list.please.select"/>","");
		districtElemment.options.add(o);  
	
		for(var oo in jal_city){  
			if (jal_city[oo].state_province_id  == province) {
				 var o=new Option(jal_city[oo].city_name,jal_city[oo].city_id);
			     cityElemment.options.add(o);  
			    
			}
			
		}  

		var hiddenCity=$('#idNohiddenCity').val();
   		//if(hiddenCity!=''){
   			$('#idno_city').val(hiddenCity);
   		//}
   		get_idno_district();
}

function get_idno_district(){
	 var city=document.getElementById('idno_city').value;
	 var districtElemment=document.getElementById('idno_district');
    		
		districtElemment.options.length=0;

  		var o=new Option("<ng:locale key="list.please.select"/>","");
   		districtElemment.options.add(o);
	 
   		
   		for(var oo in jal_district){  
			if (jal_district[oo].city_id  == city) {
				 var o=new Option(jal_district[oo].district_name,jal_district[oo].district_id);
				 districtElemment.options.add(o);  
			    
			}
			
		} 
   		
   		var hiddenDistrict=$('#idNohiddenDistrict').val();
		//if(hiddenDistrict!=''){
	   			$('#idno_district').val(hiddenDistrict);
	   	//}
}

function sameShip(){
	
	if($('#sameShipAddr').is(':checked')) {
		$('#idNohiddenProvince').val($('#province').val());
		$('#idNohiddenCity').val($('#city').val());
		$('#idNohiddenDistrict').val($('#district').val());

		$('#idno_address').val($('#address').val());
		$('#idno_firstName').val($('#firstName').val());
		$('#idno_lastName').val($('#lastName').val());
		$('#idno_mobiletele').val($('#mobiletele').val());
	}else{
		$('#idNohiddenProvince').val('');
		$('#idNohiddenCity').val('');
		$('#idNohiddenDistrict').val('');
		$('#idno_address').val('');
		$('#idno_firstName').val('');
		$('#idno_lastName').val('');
		$('#idno_mobiletele').val('');
	}
	
	get_idno_province();
}
function callBackGetIsSameName(valid){
	
}


setTimeout("getDefJmiAddrBook()", 500);
get_idno_province();
//alert(11111111);
//getProvince();
</script>