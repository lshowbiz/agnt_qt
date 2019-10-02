<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ include file="/common/taglibs.jsp"%>


<script type="text/javascript" src="<c:url value='/scripts/region.js'/>"></script>
<script src="<c:url value='/dwr/util.js'/>"></script>
<script src="<c:url value='/dwr/engine.js'/>"></script>
<script src="<c:url value='/dwr/interface/jmiAddrBookManager.js'/>"></script>

    <div class="cont">
        <div class="bt mt">
            <h3 class="color2 ml">收货地址管理</h3>
        </div>
        <input type="hidden" id="fabId" name="fabId" value="">
        <%-- <table id="addrFiled" class="search_table mt">
            <tbody>
            <tr  style="height: 30px">
                <td><label for="theName" class="star"><ng:locale key="shipping.firstName"/>:</label></td>
                <td><input type="text" name="firstName" id="firstName"/></td>
                <td></td>
                <td></td>
            </tr>

            <tr  style="height: 30px">
                <td><label for="theName" class="star"><ng:locale key="shipping.lastName"/>:</label></td>
                <td><input type="text" name="lastName" id="lastName"/></td>
                <td></td>
                <td></td>
            </tr>
            <tr  style="height: 30px">
                <td width="95px"><label for="theProvince" class="star">所在地区:</label></td>
                <td width="180px">
                    <select name="province" id="province" class="mySelect" onchange="getIdCity();">
                        <option value="" selected="selected"><ng:locale key="list.please.select"/></option>
                        <c:forEach items="${alStateProvinces }" var="alStateProvinceVar">
                            <option value="${alStateProvinceVar.stateProvinceId }">${alStateProvinceVar.stateProvinceName }</option>
                        </c:forEach>
                    </select>
                </td>
                <td width="180px">
                    <select name="city" id="city" onchange="getIdDistrict();" class="mySelect">
                        <option value=""><ng:locale key="list.please.select"/></option>
                    </select>

                    <input type="hidden" id="hiddenCity" name="hiddenCity"  value="">
                </td>
                <td>
                    <select name="district" id="district"  class="mySelect">
                        <option value=""><ng:locale key="list.please.select"/></option>
                    </select>
                    <input type="hidden" id="hiddenDistrict" name="hiddenDistrict"  value="">
                </td>
            </tr>
            <tr  style="height: 30px">
                <td><label for="theStreet" class="star">街道地址:</label></td>
                <td colspan="3"><input type="text" name="address" id="address" style="width:62%;"/></td>
            </tr>
            <tr style="height: 30px">
                <td><label for="zip" class="star">邮编:</label></td>
                <td><input type="text" name="postalcode" id="postalcode" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="6" /></td>
                <td></td>
                <td></td>
            </tr>
            <tr style="height: 30px">
                <td><label for="theCellphone" class="star">手机号码:</label></td>
                <td><input type="text" name="mobiletele" id="mobiletele" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="11" /></td>
                <td></td>
                <td></td>
            </tr>
            <tr style="height: 30px">
                <td><label for="theTelphone" class="pdl10">固定电话:</label></td>
                <td><input type="text" name="phone" id="phone" maxlength="15"/></td>
                <td></td>
                <td></td>
            </tr>
            <tr style="height: 30px">
                <td><label for="isDefault" class="pdl10">设置默认:</label></td>
                <td><input type="checkbox" name="isDefault" id="isDefault" value="1"/></td>
                <td></td>
                <td></td>
            </tr>
            <tr style="height: 30px">
                <td></td>
                <td></td>
                <td ><a href="javascript:saveAddr();" class="btn_common corner2 fl mgtb10">保存收货地址</a></td>
                <td >
                    <c:if test="${not empty param.returnUrl }">
                        <a href="${param.returnUrl }" class="btn_common corner2 fl mgtb10">返&nbsp;&nbsp;回</a>
                    </c:if>



                </td>
            </tr>
            </tbody>
        </table> --%>
        <div align="right">
<button   href="javascript:void(0);"  id="js_newAddr" style="margin-top: 10px" type="button" >添加新地址</button>
<button   href="javascript:void(0);" onclick="javascript:history.go(-1);"  class="btn btn-success" style="margin-top: 10px" type="button" >返回</button>
        </div>
        <!---------->
        <div class="mt">
            <table id="tabAddrLs" class="prod mt">
                <tbody class="list_tbody_header">
                <tr>
                    <td width="80">收货人</td>
                    <td width="200">所在地区</td>
                    <td>街道地址</td>
                    <td width="60">邮编</td>
                    <td width="110">手机 </td>
                    <td width="80"></td>
                    <td width="85">操作</td>
                </tr>
                </tbody>
                <tbody class="list_tbody_row">

                <c:choose>
                    <c:when test="${jmiAddrBooks!=null&&fn:length(jmiAddrBooks)>0 }">
                        <c:forEach items="${jmiAddrBooks }" var="jmiAddrBookVar">
                            <tr class="bg-c gry3">
                                <td>${jmiAddrBookVar.firstName }${jmiAddrBookVar.lastName }
                                    <input type="hidden"  value="${jmiAddrBookVar.fabId }">
                                    <input type="hidden"  value="${jmiAddrBookVar.firstName }">
                                    <input type="hidden"  value="${jmiAddrBookVar.lastName }">
                                </td>
                                <td>
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

                                </td>
                                <td title="${jmiAddrBookVar.address }">
                                	<c:choose>
										<c:when test="${fns:isAbbreviate(jmiAddrBookVar.address, 40)}">
											${fns:abbreviate(jmiAddrBookVar.address, 40,'...')}
										</c:when>
										<c:otherwise>
											${jmiAddrBookVar.address }
										</c:otherwise>
									</c:choose>
                                	
                                    <input type="hidden"  value="${jmiAddrBookVar.address }">
                                </td>
                                <td>${jmiAddrBookVar.postalcode }
                                    <input type="hidden"  value="${jmiAddrBookVar.postalcode }">
                                </td>
                                <td><span>${jmiAddrBookVar.mobiletele }
						         <input type="hidden"  value="${jmiAddrBookVar.mobiletele }">
						         </span> 
						         <span><input type="hidden"  value="${jmiAddrBookVar.phone }"></span>
						         </td>
                                <c:if test="${jmiAddrBookVar.isDefault=='1' }">
                                    <td class="tdDefaultAddr">
                                        <input type="hidden"  value="${jmiAddrBookVar.isDefault }">
                                        <a href="javascript:void(0);" class="bold colorQL" id="setDefaultAddr_${jmiAddrBookVar.fabId }">默认地址</a></td>
                                </c:if>
                                <c:if test="${jmiAddrBookVar.isDefault!='1' }">
                                    <td class="tdDefaultAddr">
                                        <input type="hidden"  value="${jmiAddrBookVar.isDefault }">
                                        <a href="javascript:void(0);" class="bold colorQL hide" id="setDefaultAddr_${jmiAddrBookVar.fabId }">默认地址</a></td>
                                </c:if>

                                <td class="bdr">
                                    <a href="javascript:void(0);" class="colorQL" id="modifyAddr_1">修改</a>
                                    <span>|</span>
                                    <a href="javascript:void(0);" class="colorQL" id="deleteAddr_1">删除</a>
                                </td>
                            </tr>
                        </c:forEach >
                    </c:when>
                    <c:otherwise>
                <tr class="bg-c gry3">
                    <td colspan="7">暂无数据!</td>
                </tr>
                    </c:otherwise>
                </c:choose>




                </tbody>
            </table>


        </div>


    </div>



<div class="mask" id="mask"></div>
	<div class="popupAddr abs" id="popupAddr" style="display:none;">
		<h2 class="color2">添加新地址</h2>
		<form  id="addrForm">
			<table width="100%" border="0" class="addInfo" class="mt">
				<colgroup style="width:100px;"></colgroup>
				
				<tbody >
					<tr>
						<td class="tr"><label class="star">省：</label></td>
						<td>
					<select name="province" id="province" class="mySelect" onchange="getIdCity();">
                        <option value="" selected="selected"><ng:locale key="list.please.select"/></option>
                        <c:forEach items="${alStateProvinces }" var="alStateProvinceVar">
                            <option value="${alStateProvinceVar.stateProvinceId }">${alStateProvinceVar.stateProvinceName }</option>
                        </c:forEach>
                    </select>
                    						
						</td>
					
						<td class="tr "><label class="star">市：</label></td>
						<td class="w200">
							<div class="rel">						
								<select name="city" id="city" onchange="getIdDistrict();" class="mySelect" data-required="true">
									<option value=""><ng:locale key="list.please.select"/></option>
								</select>
								  <input type="hidden" id="hiddenCity" name="hiddenCity"  value="">
							</div>							
						</td>
						<td class="tr "><label class="star">区：</label></td>
						<td class="w100">
						
								<select name="district" id="district" class="mySelect"  data-required="true">
									<option value=""><ng:locale key="list.please.select"/></option>								
								</select>			
								 <input type="hidden" id="hiddenDistrict" name="hiddenDistrict"  value="">		
						</td>						
					</tr>
					<tr>
						<td class="tr "><label class="star">收货人姓：</label></td>
						<td class="w100"><input type="text" name="" id="firstName" class="w100" data-required="true" /></td>
						<td class="tr"><label class="star">收货人名：</label></td>
						<td>
						<input type="text" name="" id="lastName" class="w100" data-required="true" /></td>
						
						<td class="tr "><label class="star">邮编：</label></td>
						<td class="w100">						
						<input type="text" id="postalcode" onkeyup="this.value=this.value.replace(/\D/g,'')" maxlength="6" class="w60" data-required="true" data-type="number" data-maxlength="6" />
						</td>
					</tr>
					<tr>
						<td class="tr "><label class="star">街道地址：</label></td>
						<td class="w100"><input type="text" name="address" id="address" data-required="true" /></td>
						
					
						<td class="tr"><label class="star" >手机：</label></td>
						<td>
							<input type="text" name="" id="mobiletele" class="w100" onkeyup="this.value=this.value.replace(/\D/g,'')" data-required="true" data-type="number" data-maxlength="11" /></div>
						</td>		
						
						<td class="tr"><label>固话：</label></td>
						<td><input type="text" name="" id="phone" class="w100" /></td>
						
					</tr>
					<tr>
						
						<td colspan="3">
							<label for="defaultAddr">
								<!-- <input type="checkbox"  checked="checked"  name="isDefault" id="isDefault" value="1"/> -->
								<input type="checkbox" name="isDefault" id="isDefault" value="1"/>&nbsp;
								<span>设置为默认地址</span>
							</label>
						</td>
						<td colspan="3" class="tr color1">设置后系统将在购买时自动选中该收货地址</td>
					</tr>
				</tbody>
			</table>
			<div class="tc mt">
				<button class="btn btn-info" href="javascript:void(0);"  id="js_confirm"  type="button" onclick="javascript:saveAddr();">保存</button>
				<button href="javascript:void(0);"  id="cancels" onclick="tohref('${ctx}/jmiAddrBooks');" type="button" class="btn btn-success">取消</button>
			</div>
		</form>
	</div>








<script>

var jq = jQuery.noConflict();
function callBack(valid){
	 alert("更新成功");
	 tohref('${ctx}/jmiAddrBooks');
}

jq.fn.coffee = function(obj){  
	for(var eName in obj)  
		for(var selector in obj[eName])  
			jq(this).on(eName, selector, obj[eName][selector]);  
} 

function setDefaultAddr(sel){
	var id=sel.id.split('_')[1];
    jq(sel).removeClass('hide').addClass('show').parents('tr').siblings().find("[id^='setDefaultAddr']").removeClass('show').addClass('hide');
    jmiAddrBookManager.saveDefaultJmiAddrBookPc(id,callBack);
}

function saveAddr(){

	var fabId=jq('#fabId').val();
	var firstName=jq('#firstName').val();
	var lastName=jq('#lastName').val();
	var province=jq('#province').val();
	var city=jq('#city').val();
	var district=jq('#district').val();
	var address=jq('#address').val();
	var postalcode=jq('#postalcode').val();
	var mobiletele=jq('#mobiletele').val();
	var phone=jq('#phone').val();
	var isDefault=jq('#isDefault').attr('checked');
	
	if(firstName==''){
		 var errorMsg = '<ng:locale key="errors.required" args="shipping.firstName" argTransFlag="true"/>';
		 alert(errorMsg);
		 return;
	}else if(firstName.length>15){
		 var errorMsg = '收货姓过长';
		 alert(errorMsg);
		 return;
	}
	if(lastName==''){
		 var errorMsg = '<ng:locale key="errors.required" args="shipping.lastName" argTransFlag="true"/>';
		 alert(errorMsg);
		 return;
	}else if(lastName.length>15){
		 var errorMsg = '收货名过长';
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
		 var errorMsg = '<ng:locale key="errors.required" args="subStore.district" argTransFlag="true"/>';
		 alert(errorMsg);
		 return;
	}
	if(address==''){
		 var errorMsg = '<ng:locale key="errors.required" args="shipping.address" argTransFlag="true"/>';
		 alert(errorMsg);
		 return;
	}else if(address.length>100){
		 var errorMsg = '街道地址过长';
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
	
	if(isFormPosted()){

		var jmiAddrBook;
		jmiAddrBook={"fabId":fabId,"firstName":firstName,"lastName":lastName,"province":province,"city":city,"district":district,"address":address,"postalcode":postalcode,"mobiletele":mobiletele,"phone":phone,"isDefault":isDefault};
		jmiAddrBookManager.saveJmiAddrBookPc(jmiAddrBook,callBack);
		
	}
	
	

}
 
function modifyAddr(sel){

    var $tr             =   jq(sel).closest('tr');
    var $tbody          =   jq('#addrFiled').find('tbody');
    
    
    

    var fabId           =   $tr.find("td:eq(0)").find("input:eq(0)").val();
    var firstName       =   $tr.find("td:eq(0)").find("input:eq(1)").val();
    var lastName        =   $tr.find("td:eq(0)").find("input:eq(2)").val();
    var province        =   $tr.find("td:eq(1)").find("span:eq(0)").find("input:eq(0)").val();
    var city            =   $tr.find("td:eq(1)").find("span:eq(1)").find("input:eq(0)").val();
    var district        =   $tr.find("td:eq(1)").find("span:eq(2)").find("input:eq(0)").val();
    var address         =   $tr.find("td:eq(2)").find("input:eq(0)").val();
    var postalcode      =   $tr.find("td:eq(3)").find("input:eq(0)").val();
    var mobiletele      =   $tr.find("td:eq(4)").find("span:eq(0)").find("input:eq(0)").val();
    var phone           =   $tr.find("td:eq(4)").find("span:eq(1)").find("input:eq(0)").val();
    var isDefault       =   $tr.find("td:eq(5)").find("input:eq(0)").val();
	
	
	
	jq('#fabId').val(fabId);
	jq('#firstName').val(firstName);
	jq('#lastName').val(lastName);
	jq('#province').val(province);
	jq('#city').val(city);
	jq('#district').val(district);
	jq('#address').val(address);
	jq('#postalcode').val(postalcode);
	jq('#mobiletele').val(mobiletele);
	jq('#phone').val(phone);
	var isDefaultAddr=jq('#isDefault');
	
	if(isDefault=='1'){
  	  isDefaultAddr.prop('checked',true);
	}else{
		isDefaultAddr.prop('checked',false);
	}
	
	jq('#hiddenCity').val(city);
	jq('#hiddenDistrict').val(district);
	
	getIdCity();
	
	
    /*  表示当前修改的地址  */
    $tr.siblings().find('td').removeAttr('style');
    $tr.find('td').css('background-color','#D4D4D4');


}

function deleteAddr(sel){

    var $tr             =   jq(sel).closest('tr');
    var fabId  =   $tr.find("td:eq(0)").find("input:eq(0)").val();
	jmiAddrBookManager.removeJmiAddrBookPc(fabId,callBack);
}




    jq('#tabAddrLs > tbody').coffee({
        'mouseover':{
            '> tr':function(){
                jq(this).find('td').addClass('tabHoverColor').find('a').removeClass('colorQL').addClass('white');
                jq(this).find('td.tdDefaultAddr').find('a').addClass('show');
            }
        },
        'mouseleave':{
            '> tr':function(){
                jq(this).find('td').removeClass('tabHoverColor').find('a').removeClass('white').addClass('colorQL');
                jq(this).find('td.tdDefaultAddr').find('a').removeClass('show');
            }
        },
        'click':{
            "[id^='setDefaultAddr']":function(){
                setDefaultAddr(this);
            },
            "[id^='deleteAddr']":function(){
                deleteAddr(this);
            },
            "[id^='modifyAddr']":function(){
            	popupNewAddr();
                modifyAddr(this);
            }
        }
    });
    


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
		
		
		
		jq.each(jal_city, function (k, p) { 
			 if (p.state_province_id == province) {
				 var o=new Option(p.city_name,p.city_id);
 				if(jq('#hiddenCity').val()==p.city_id){
			    	 o.selected = 'selected';
			     }
			     cityElemment.options.add(o);  
			    
			 }
		 });
		getIdDistrict();
   }
 
    function getIdDistrict(){
		 var city=document.getElementById('city').value;
		 var districtElemment=document.getElementById('district');
	    		
    		districtElemment.options.length=0;
 
      		var o=new Option("<ng:locale key="list.please.select"/>","");
       		districtElemment.options.add(o);
		 
       		jq.each(jal_district, function (k, p) { 
				 if (p.city_id == city) {
					 var o=new Option(p.district_name,p.district_id);
					 if(jq('#hiddenDistrict').val()==p.district_id){
				    	 o.selected = 'selected';
				     }
					 districtElemment.options.add(o); 

				 }
			 });
   }

    function closeDialog111()
    {
    	document.getElementById("mask").style.display="none";
    	document.getElementById("popupAddr").style.display="none";
    }

    
    function tohref(tourl) {
		top.window.location.href=tourl;
	}

</script>
		
		
		
		
		
		