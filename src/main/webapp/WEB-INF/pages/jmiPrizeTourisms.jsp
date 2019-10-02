<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>


<link rel="stylesheet" href="css/style.css" />
    <c:if test="${not empty sessionScope.redirectTager }">
    <script>
        window.parent.location.href="login.html";
    </script>
    <c:remove var="redirectTager" scope="session"/>
    </c:if>
        
<body>
<div class="cont">	
			<div class="bt mt">
				<h3 class="color2 ml">奖游信息完善</h3>
			</div>	
		 <div class="mt">	  
		<form:form commandName="jmiPrizeTourism" method="post" action="prizeTourisms" id="jmiPrizeTourismForm" onsubmit="if(isFormPosted()){return true;}{return false;}">
		<table width="100%"  class="form_details_table">
			<colgroup style="width:110px;"></colgroup>
			<colgroup></colgroup>
			<colgroup style="width:110px;"></colgroup>
			<colgroup></colgroup>
			<colgroup style="width:120px;"></colgroup>
			<colgroup></colgroup>
			<tbody>
				<tr>
					<td><ng:locale key="miMember.memberNo"/>：</td>
					<td colspan="5">${ jmiPrizeTourism.userCode} </td>
				</tr>
				<tr>
					<td><ng:locale key="jmiPrizeTourism.cardname"/>：</td>
					<td>${jmiPrizeTourism.cardname }</td>
					<td><ng:locale key="jmiPrizeTourism.cardid"/>：</td>
					<td>${jmiPrizeTourism.cardid }</td>
					<td><ng:locale key="jmiPrizeTourism.phone" />：</td>
					<td>${jmiPrizeTourism.phone }</td>
				</tr>
				<tr>
					<td><ng:locale key="jmiPrizeTourism.province"/>：</td>
					<td><ng:region regionType="p" regionId="${jmiPrizeTourism.province}"></ng:region></td>
					<td><ng:locale key="jmiPrizeTourism.city"/>：</td>
					<td><ng:region regionType="c" regionId="${jmiPrizeTourism.city}"></ng:region></td>
					<td> <ng:locale key="jmiPrizeTourism.area" />：</td>
					<td><ng:region regionType="d" regionId="${jmiPrizeTourism.area}"></ng:region></td>
				</tr>
				
				<tr>
					<td><ng:locale key="jmiPrizeTourism.address" />：</td>
					<td colspan="5">${jmiPrizeTourism.address }</td>
				</tr>
				<tr>
					<td><ng:locale key="jmiPrizeTourism.height" />：</td>
					<td>${jmiPrizeTourism.height }</td>
					<td><ng:locale key="jmiPrizeTourism.weight" />：</td>
					<td>${jmiPrizeTourism.weight }</td>
					<td><ng:locale key="jmiPrizeTourism.clothessize" />：</td>
					<td><ng:code listCode="jmiprizetourism.clothessize" value="${jmiPrizeTourism.clothessize }"/></td>
				</tr>
				<tr>
					<td><ng:locale key="jmiPrizeTourism.avoidcertainfood" />：</td>
					<td>
						<c:forEach items="${avoidcertainfoodList}" var="cb" >
				        <input type="checkbox" name="avoidcertainfoods" disabled="disabled"  <c:if test="${cb.checked=='1'}">checked</c:if> value="${cb.cId}"/>${cb.cValue}&nbsp;&nbsp;
				        </c:forEach>
			        </td>
					<td><ng:locale key="jmiPrizeTourism.passStar" />：</td>
					<td colspan="3"><ng:code listCode="pass.star" value="${jmiPrizeTourism.passStar }"/></td>
				</tr>
				<tr>
					<td colspan="2"><ng:locale key="jmiPrizeTourism.acceptanceaddress" />：</td>
					<td colspan="4">${jmiPrizeTourism.acceptanceaddress }</td>
				</tr>
				<tr>
					<td><ng:locale key="jmiPrizeTourism.isPeer" />：</td>
					<td colspan="5"><ng:code listCode="jmiprizetourism.ispeer" value="${jmiPrizeTourism.ispeer }"/></td>
				</tr>
				<tr class="peer"><th colspan="6">
					<h2 class="title mgb20"><ng:locale key="jmiPrizeTourism.peer.info"/></h2>
			    </th></tr>
			    <tr class="peer">
					<td><ng:locale key="jmiPrizeTourism.peertype" />：</td>
					<td><ng:code listCode="jmiprizetourism.peertype" value="${jmiPrizeTourism.peertype }"/></td>
					<td><ng:locale key="list.sex" />：</td>
					<td colspan="3"><ng:code listCode="testuser.sex" value="${jmiPrizeTourism.peersex }"/></td>
				</tr>
				<tr class="peer">
					<td><ng:locale key="jmiPrizeTourism.cardname" />：</td>
					<td>${jmiPrizeTourism.peercardname }</td>
					<td><ng:locale key="jmiPrizeTourism.cardid" />：</td>
					<td>${jmiPrizeTourism.peercardid }</td>
					<td><ng:locale key="jmiPrizeTourism.phone" />：</td>
					<td>${jmiPrizeTourism.peerphone }</td>
				</tr>
				<tr class="peer">
					<td><ng:locale key="jmiPrizeTourism.province"/>：</td>
					<td><ng:region regionType="p" regionId="${jmiPrizeTourism.peerprovince}"></ng:region></td>
					<td><ng:locale key="jmiPrizeTourism.city"/>：</td>
					<td><ng:region regionType="c" regionId="${jmiPrizeTourism.peercity}"></ng:region></td>
					<td> <ng:locale key="jmiPrizeTourism.area" />：</td>
					<td><ng:region regionType="d" regionId="${jmiPrizeTourism.peerarea}"></ng:region></td>
				</tr>
				
				<tr class="peer">
					<td><ng:locale key="jmiPrizeTourism.address" />：</td>
					<td colspan="5">${jmiPrizeTourism.peeraddress }</td>
				</tr>
				<tr class="peer">
					<td><ng:locale key="jmiPrizeTourism.height" />：</td>
					<td>${jmiPrizeTourism.peerheight }</td>
					<td><ng:locale key="jmiPrizeTourism.weight" />：</td>
					<td>${jmiPrizeTourism.peerweight }</td>
					<td><ng:locale key="jmiPrizeTourism.clothessize" />：</td>
					<td><ng:code listCode="jmiprizetourism.clothessize" value="${jmiPrizeTourism.peerclothessize }"/></td>
				</tr>
				<tr class="peer">
					<td><ng:locale key="jmiPrizeTourism.avoidcertainfood" />：</td>
					<td colspan="5">
					<c:forEach items="${peerAvoidcertainfoodList}" var="cb" >
			        <input type="checkbox" name="peeravoidcertainfoods" disabled="disabled" onchange="chk('peeravoidcertainfoods','peeravoidcertainfood')" onclick="chk('peeravoidcertainfoods','peeravoidcertainfood')" <c:if test="${cb.checked=='1'}">checked</c:if> value="${cb.cId}"/>${cb.cValue}&nbsp;&nbsp;
			        </c:forEach>
					</td>
				</tr>
			</tbody>
		</table>
		<input type="hidden" value="${papernumberCheck }" id="papernumberCheck"/>
	   </form:form>
	   </div>
	 </div>
	<script>
	function isShowPeer(){
		var isPeer = '${jmiPrizeTourism.ispeer }';
		if('1'==isPeer){
			$(".peer").css('display','');
		}else{
			$(".peer").css('display','none');
		}
		
	}
	isShowPeer();
</script>
</body>

