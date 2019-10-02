<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!--<title>客户管理 － 客户需求分析</title>-->
<!--客户管理 － 客户需求分析  -->
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
   
   //删除
   function linkmanDemandAnalysisDelete(id){
       if(confirm("<ng:locale key='amMessage.confirmdelete'/>")){
           window.location.href="linkmanDemandAnalysisDelete.html?id="+id;
       }
   }
   
  </script>

<img style="display:none" src="images/indicator_smallwaitanim.gif" alt="Loading"  align="absmiddle" />

<body>
    <div class="cont" >	
			<div class="bt mt">
				<h3 class="color2 ml"><ng:locale key="linkmanDemand.requirementsAnalysis"/></h3>
			</div>		
        <form action="linkmanDemandAnalysisQuery" method="get" name="linkmanDemandAnalysisQuery" id="linkmanDemandAnalysisQuery">
			<!-- <table width="90%" class="mt" style="margin:0 auto; "> -->
			<table class="search_table mt" >
				<tr>
					<td><ng:locale key="linkman.name"/>：</td>
					<td>
						<input type="text" name="name" value="${name }" maxlength="100"/>
					</td>
					
					<td><ng:locale key="common.startTime"/>：</td>
					<td>
						<input type="text" name="registerTimeBegin" id="registerTimeBegin"  value="${registerTimeBegin }" 
							style="cursor:pointer;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					</td>
					
					
					<td><ng:locale key="publicSchedule.endTime"/>：</td>
					<td>
						<input type="text" name="registerTimeEnd" id="registerTimeEnd"  value="${registerTimeEnd }" 
							style="cursor:pointer;" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					</td>
					
					
					<td>
						<button name="search" id="search" type="button" onclick="checkApplication(document.linkmanDemandAnalysisQuery)"><ng:locale key="operation.button.search"/></button>
					</td>
					
				
                </tr>
                
		</table>
        </form>
	<div class="mt">	
		<table class="prod mt" border="1" style="border-collapse:collapse; border:1px solid #ebebeb;" >		
			<tbody class="list_tbody_header">
				<tr>     
					<td><ng:locale key ="linkman.name"/></td>                   
					<td><ng:locale key ="linkmanDemand.registerTime"/></td>                
					<td><ng:locale key ="sysOperationLog.moduleName"/></td>                   
				</tr>
			</tbody>
			<tbody class="list_tbody_row">
				<c:forEach items="${linkmanDemandList}" var="linkmanDemandOb" >
					<tr  class="bg-c gry3">
					   <td>${linkmanDemandOb.name }</td>
					   <td>${linkmanDemandOb.register_Time }</td>
					   <td>
					   		<img src="images/pencil.gif" onclick="window.location.href='linkmanDemandAnalysisUpdateInit.html?id=${linkmanDemandOb.id }';" alt="<ng:locale key="operation.button.edit"/>"/>
							&nbsp;&nbsp;
							<img src="images/delete.gif" onclick="linkmanDemandAnalysisDelete(${linkmanDemandOb.id })" alt="<ng:locale key="operation.button.delete"/>"/>
					   </td>
					</tr>
				</c:forEach>
			</tbody>	
		</table>
	</div>		
	${page.pageInfo }
	</div>
</body>
