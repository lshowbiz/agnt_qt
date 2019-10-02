<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>


<link rel="stylesheet" href="css/style.css" />
    <c:if test="${not empty sessionScope.redirectTager }">
    <script>
        window.parent.location.href="login.html";
    </script>
    <c:remove var="redirectTager" scope="session"/>
    </c:if>
     
     
   
		<div class="cont">	
			<div class="bt mt">
				<h3 class="color2 ml"><ng:locale key="menu.editJmiMemberProfile"/></h3>
			</div>	  
     <!-- 下面是校验时的提示标语 -->
<spring:bind path="jmiMember.*">
    <c:if test="${not empty status.errorMessages}">
    <div class="error" id="errorDiv" style="display: none">    
        <c:forEach var="error" items="${status.errorCodes}">
           <div> <c:out value="${error}" escapeXml="false"/></div>
        </c:forEach>
    </div>
    </c:if>
</spring:bind>
     
     
     
     
     <div class="mt">	
			<table class="form_details_table">						 
				<tr>     
					<td><ng:locale key="miMember.memberNo" />：${ jmiMember.userCode} - 
						<ng:code listCode="member.level" value="${jmiMember.memberLevel}" /> -
						<ng:code listCode="pass.star" value="${jmiMember.memberStar}" />
						<c:if test="${jmiMember.isstore!='0'}"> - 
							<ng:code listCode="store.type" value="${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.jmiMember.isstore}"></ng:code>
						</c:if>	</td>                                  
					<td><%-- <ng:locale key="miMember.recommendNo"/>：${jmiMember.recommendNo } --%></td>                   
					<td rowspan="3"><img height="110" width="110" src="<c:url value="/qrcode/getQrcode?type=2&content=${ jmiMember.userCode}"/>"></td>                						
				</tr>
					
				<tr>     
					<td><ng:locale key="miMember.petName"/>：	${jmiMember.petName }</td>                   
					<td><ng:locale key="miMember.sex" />：<ng:code listCode="sex" value="${jmiMember.sex }"/></td>                                 
					                 						
				</tr>	
				<tr>     
					<td><ng:locale key="miMember.papertype"/>：	<ng:code listCode="papertype" value="${jmiMember.papertype }"/></td>                   
					<td><ng:locale key="miMember.papernumber"/>： ${ jmiMember.papernumber}</td>                                 
					               						
				</tr>
				
				<tr>
					<td><ng:locale key="miMember.province"/>：<ng:region regionType="p" regionId="${jmiMember.province}"></ng:region></td>
					<td><ng:locale key="miMember.idAddr2"/>：<ng:region regionType="c" regionId="${jmiMember.city}"></ng:region></td>
					<td><ng:locale key="miMember.district" />：<ng:region regionType="d" regionId="${jmiMember.district}"></ng:region></td>
					
				</tr>
				
				
				<tr>
					
					<td><ng:locale key="miMember.idAddr"/>：${ jmiMember.address}</td>
					<td><ng:locale key="miMember.phone" />：${ jmiMember.phone}</td>
					<td><ng:locale key="miMember.mobiletele"/>：${ jmiMember.mobiletele}</td>
				</tr>
				
				 
				<tr>     
					<td><ng:locale key="miMember.postalcode" />：${jmiMember.postalcode}</td>                   
					<td><ng:locale key="miMember.spouseName" />：${jmiMember.spouseName}</td>                                 
					<td><ng:locale key="miMember.spouseIdno" />：${jmiMember.spouseIdno}</td>                  						
				</tr>
				
				<tr>     
					<td><ng:locale key="miMember.addrcl"/>：${ jmiMember.clAddress}</td>                   
					<td><ng:locale key="miMember.email"/>：${jmiMember.email}</td>                                 
					<td><ng:locale key="miMember.faxtele"/>：${ jmiMember.faxtele}</td>                  						
				</tr>
				
				<%-- <tr>
					<td><label class="pdl10"><ng:locale key="member.ugrade.time"/>：</label></td>
					<td>${days }</td>
					<td><label class="pdl10">
						
							 <ng:locale  key="jmiMember.validWeek"/>：
						</label>
					</td>
					<td colspan="3"  >
						<c:if test="${ jmiMember.gradeType!=0}" >
							 
						</c:if>														
					</td>
				</tr> --%>
				
				
				<c:if test="${(null!=jmiMember.bankcard && ''!=jmiMember.bankcard) || '3' ==jmiMember.memberUserType|| '2' ==jmiMember.memberUserType}">
				<tr>
					<c:if test="${'2' ==jmiMember.memberUserType && '1' ==jmiMember.isCloudshop }"><td>会员类型：云客</td></c:if>
					<c:if test="${'3' ==jmiMember.memberUserType && '1' ==jmiMember.isCloudshop }"><td>会员类型：脉客</td></c:if>
					<td><c:if test="${null!=jmiMember.bankcard && ''!=jmiMember.bankcard}">开户行：${ jmiMember.bank}</c:if></td>
					<td><c:if test="${null!=jmiMember.bankcard && ''!=jmiMember.bankcard}">银行帐号：*********${fn:substring(jmiMember.bankcard,fn:length(jmiMember.bankcard)-4,fn:length(jmiMember.bankcard))}</c:if></td>
					<td > </td>
				</tr>
				</c:if>
				
				
				<%-- <tr>      
					<td><ng:locale key="member.ugrade.time"/>：${days }</td>                   
					<td></td>                                 
					<td></td>                  						
				</tr> --%>
			</table> 
			<c:if test="${ jmiMember.gradeType!=0}" >
			<p id="validMonthYearId" class="mt ml">
			 <input type="hidden" value="<ng:monthFormat month='${jmiMember.validWeek}' monthType='w'/>" id="validMonthYear" name="validMonthYear"/>	    
						 
			<!-- 重销截止时间：2018工作年第04工作月  -->
			 
			 <script>
							 document.getElementById('validMonthYearId').innerHTML='<ng:locale  key="jmiMember.validWeek"/>：'+document.getElementById('validMonthYear').value.substr(0, 4)+'<ng:locale  key="bdPeriod.wyear"/><ng:locale  key="jmiMember.di"/>'+document.getElementById('validMonthYear').value.substr(4, 6)+'<ng:locale  key="bdPeriod.wmonth"/>';	
						  </script> 
			
			</p>
			</c:if>
			<c:if test="${ jmiMember.gradeType==0}" >
			</c:if>
			
			<div class="tc" style="margin-top: 10px;"> <button type="button" onclick="updateJmiMember()" >&nbsp;<span>修改信息</span>&nbsp;</button>	</div>	
	</div>				
     
     
     
     </div>
        



	<%-- <h2 class="title mgb20"></h2>
		<form:form commandName="jmiMember" method="post" action="jmiPersonalDataMaintenance" id="jmiMemberForm" onsubmit="if(isFormPosted()){return true;}{return false;}">
		
		
		
		
		
		
		
		
		
		
		
		<table width="100%" class="personalInfoTab">
			<colgroup style="width:110px;"></colgroup>
			<colgroup></colgroup>
			<colgroup style="width:110px;"></colgroup>
			<colgroup></colgroup>
			<colgroup style="width:120px;"></colgroup>
			<colgroup></colgroup>
			<tbody>
				<tr>
					<td><label class="pdl10">：</label>
					</td>
					<td colspan="3">
					</td>
					<td colspan="2" rowspan="6" align="right">
						<div><img src="<c:url value="/qrcode/getQrcode?type=2&content=${ jmiMember.userCode}"/>"></div>
					</td>
				</tr>
				<tr>
					<td><label class="pdl10">：</label></td>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td><label class="star">：</label></td>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td><label class="pdl10">：</label></td>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td><label class="star">：</label></td>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td><label class="pdl10">：</label></td>
					<td colspan="4"></td>
				</tr>
				<tr>
					<td><label class="pdl10">：</label></td>
					<td></td>
					<td><label class="star">：</label></td>
					<td >
						<c:if test="${empty papernumberModify }">
							
						</c:if>
						<c:if test="${not empty papernumberModify }">
							 ${ jmiMember.papernumber}
						</c:if>
					</td>
				</tr>
				
			
				
				<tr>
					<td><label class="pdl10"></label></td>
					<td></td>
					<td><label class="pdl10">：</label></td>
					<td colspan="3"></td>
				</tr>
				
				
				<tr>					   
					<td colspan="6"><a href="javascript:void(0);" class="btn_common corner2 fr" style="margin-top:50px;" value="<ng:locale key="operation.button.edit"/>" onclick="updateJmiMember()">修&nbsp;改</a></td>
				</tr>
			</tbody>
		</table>
		<input type="hidden" value="${papernumberCheck }" id="papernumberCheck"/>
	   </form:form> --%>
	
    <script src="<c:url value='/dwr/util.js'/>" ></script>
    <script src="<c:url value='/dwr/engine.js'/>" ></script>
    <script src="<c:url value='/dwr/interface/jalCityManager.js'/>" ></script>
    <script src="<c:url value='/dwr/interface/jalDistrictManager.js'/>" ></script>
	<script>
		        var qquery = jQuery.noConflict();
		   //在初始化或者选取省份的时候执行该方法
		    function getCity(){
		        var provinceId = qquery("#province").val();
		        //通过ＤＷＲ开源框架，这里调用java后台的项目，并且使用了回调函数
		        jalCityManager.getAlCityByProvinceId(provinceId,callIdCity);
		    }
		    
		    //回调函数
		    function callIdCity(valid){
		        //去掉下拉框中原有的数值
		        DWRUtil.removeAllOptions("city");
		        //给下拉框加上受选项－请选择(select)
		        DWRUtil.addOptions("city",{'':'<ng:locale key='list.please.select'/>'});
		        //将集合valid中的数据放入下拉框中
		        DWRUtil.addOptions("city",valid, "cityId","cityName");
		        if(''!='${jmiMember.city}'){
		        　　　　　//使用别名的jQuery代码
		        　　　　    //将数据库中查询到的城市的代码放入下拉框，就是使下拉框选中对应的城市
		        　　　　　qquery("#city").val('${jmiMember.city}');　
		            //通过城市获取地区
		            getAreaByCity();
		        } 
		    }    
		    
		   //通过城市获取地区的方法
		   function getAreaByCity(){
		   
		         var cityId = qquery("#city").val();
		         //使用ＤＷＲ开源框架，调用java后台的代码
		   　　　　jalDistrictManager.getAlDistrictByCityId(cityId,callIdDistrict);
		   }
		   
		   //回调函数－获取所在地区
		   function callIdDistrict(districtList){
		       DWRUtil.removeAllOptions("district");
		       DWRUtil.addOptions("district",{'':'<ng:locale key='list.please.select'/>'});
		       DWRUtil.addOptions("district",districtList,"districtId","districtName");
		       if(''!='${jmiMember.district}'){
		       
		        　　　　　//使用别名的jQuery代码
		        　　　　    //将数据库中查询到的地区的代码放入下拉框，就是使下拉框选中对应的地区
		        　　　　　qquery("#district").val('${jmiMember.district}');　
		       }
		   }
		   
		   function updateJmiMember(){
		        window.location.href="jmiPersonalDataMaintenanceUpdate.html";
		   }
		    getCity();
</script>





















