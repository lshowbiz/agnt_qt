<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!--<title></title>-->
<!-- 客户管理-客户的商品--修改 -->
</head>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script src="./scripts/calendar/calendar.js"></script>
<script src="./scripts/calendar/calendar-setup.js"></script>
<script src="./scripts/calendar/lang.jsp"></script>
<script src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
<script>
function loading(){
	var str = '<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src=images/indicator_smallwaitanim.gif alt=Loading  align=absmiddle/>';
	str += '&nbsp;&nbsp;<ng:locale key="button.loading"/>';
	document.getElementById("kkk").innerHTML=str;
}
   //查询
   function checkApplication(theForm){
      theForm.submit();
   }
   
   //新增
   function linkmanDemandGoodsAdd(){
       window.location.href="linkmanGoodsform.html?linkmanDemandGoodsFunction=linkmanDemandGoodsAdd";
   }
   
   //删除
   function linkmanDemandGoodsDelete(id){
       if(confirm("<ng:locale key='amMessage.confirmdelete'/>")){
           window.location.href="linkmanDemandGoodsDelete.html?id="+id;
       }
   }
   
  </script>

<img style="display:none" src="images/indicator_smallwaitanim.gif" alt="Loading"  align="absmiddle" />

<body>

		
		<div class="cont" >	
			<div class="bt mt">
				<h3 class="color2 ml"><ng:locale key="linkmanGoods.commodity"/></h3>
			</div>		
		        <form action="linkmanDemandGoodsQuery" method="get" name="linkmanDemandGoodsQuery" id="linkmanDemandGoodsQuery">
		        	<table class="search_table mt" >
		                <tr>
		                	<td style="width:80px;"><ng:locale key="linkman.name"/>：</td>
							<td style="width:200px;">
								<input type="text" name="name" value="${name }"  maxlength="100"/>
							</td>
							
							<td style="width:80px;"><ng:locale key="common.startTime"/>：</td>
							<td style="width:200px;">
								<input type="text" name="buyTimeBegin" id="buyTimeBegin"  value="${buyTimeBegin }" 
									style="cursor:pointer;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
								
							</td>
							<td style="width:80px;"><ng:locale key="publicSchedule.endTime"/>：</td>
							<td style="width:200px;">
								<input type="text" name="buyTimeEnd" id="buyTimeEnd"  value="${buyTimeEnd }" 
									style="cursor:pointer;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
							</td>
						</tr>
						<tr>
							<td style="width:80px;"><ng:locale key="linkmanGoods.buyQuantity"/>：</td>
							<td colspan="1">
								<input style="position: relative;width:68px;float: left;" type="text" name="buyQuantityBegin" value = "${buyQuantityBegin }"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
								<span style="float: left;padding: 6px 10px;">-</span>
								<input style="position: relative;width:68px;float: left;" type="text" name="buyQuantityEnd" value="${buyQuantityEnd }"  onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" />
								
							</td>
							<td>
							<button  name="search" id="search" type="button" onclick="checkApplication(document.linkmanDemandGoodsQuery)"><ng:locale key="operation.button.search"/></button>
							</td>
		                </tr>
		            </table>
		        </form>
		    
		    <!----------------------------------------------------------->
		    
			<div class="mt">	
				<table class="prod mt" border="1" style="border-collapse:collapse; border:1px solid #ebebeb;" >		
					<tbody class="list_tbody_header">
						<tr>     
							<td><ng:locale key ="linkman.name"/></td>                   
							<td><ng:locale key ="linkmanGoods.buyGoods"/></td>                
							<td><ng:locale key ="linkmanGoods.buyTime"/></td>  
							<td><ng:locale key ="linkmanGoods.buyQuantity"/></td>  
							<td><ng:locale key ="sysOperationLog.moduleName"/></td>               
						</tr>
					</tbody>
					<tbody class="list_tbody_row">
						<c:forEach items="${linkmanDemandGoodsList}" var="linkmanDemandGoodsOb" >
							<tr class="bg-c gry3">
							    <td>${linkmanDemandGoodsOb.name }</td>
								<td>${linkmanDemandGoodsOb.buy_Goods }</td>
								<td>${linkmanDemandGoodsOb.buy_Time }</td>
								<td>${linkmanDemandGoodsOb.buy_Quantity }</td>                      
								<td>
									<img src="images/search.gif" onclick="window.location.href='linkmanDemandGoodsDetailQuery.html?id=${linkmanDemandGoodsOb.id }';" alt="<ng:locale key="menu.editJbdMemberLinkCalcHis"/>"/>
									&nbsp;&nbsp;
									<img src="images/pencil.gif" onclick="window.location.href='linkmanGoodsform.html?id=${linkmanDemandGoodsOb.id }&linkmanDemandGoodsFunction=linkmanDemandGoodsUpdate';" alt="<ng:locale key="operation.button.edit"/>"/>
									&nbsp;&nbsp;
									<img src="images/delete.gif" onclick="linkmanDemandGoodsDelete(${linkmanDemandGoodsOb.id })" alt="<ng:locale key="operation.button.delete"/>"/>
								</td>
							</tr>
						</c:forEach>
					</tbody>	
				</table>
			</div>
			${page.pageInfo }
		</div>

</body>
