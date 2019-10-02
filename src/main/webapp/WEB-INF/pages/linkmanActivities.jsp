<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
<!--<title></title>-->
<!-- 客户管理-活动管理-查询 -->
</head>
<script src="<c:url value='/scripts/My97DatePicker/WdatePicker.js'/>"></script>
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
   function linkmanActivityAdd(){
       window.location.href="linkmanActivityform.html?linkmanActivityFunction=linkmanActivityAdd";
   }
   
   //删除
   function linkmanActivityDelete(id){
       if(confirm("<ng:locale key='amMessage.confirmdelete'/>")){
           window.location.href="linkmanActivityDelete.html?id="+id;
       }
   }
   
  </script>

<img style="display:none" src="images/indicator_smallwaitanim.gif" alt="Loading"  align="absmiddle" />

<body>
   <div class="cont">
    	<div class="bt mt">
			<h3 class="color2 ml"><ng:locale key="linkmanActivity.activity"/></h3>
		</div>
        <form action="linkmanActivities" method="get" name="linkmanActivities" id="linkmanActivities">
            <table class="search_table mt">
				<tr>
					<td><ng:locale key="linkmanActivity.eventName"/>：</td>
					<td><input type="text" name="eventName" value="${param.eventName }" maxlength="100"/></td>
					<td><ng:locale key="linkmanActivity.topic"/>：</td>
					<td><input type="text" name="topic" value="${param.topic }" maxlength="100"/></td>
					<td><ng:locale key="linkmanActivity.eventType"/>：</td>
					<td><ng:list name="eventType" listCode="linkmanacitvity.activity" value="${param.eventType}" defaultValue=""/></td>
				</tr>
				<tr>
				    <td>活动开始起始时间：</td>
					<td>
						 <input name="beginTimeBegin" id="beginTimeBegin" type="text" value="${param.beginTimeBegin  }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					</td>
					<td>活动开始截止时间：</td>
					<td>
						<input name="beginTimeEnd" id="beginTimeEnd" type="text" value="${param.beginTimeEnd }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					</td>
				</tr>
				<tr>
					<td>活动结束起始时间：</td>
					<td>
						<input name="endTimeBegin" id="endTimeBegin" type="text" value="${param.endTimeBegin  }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					</td>
					<td>活动结束截止时间：</td>
					<td>
						<input name="endTimeEnd" id="endTimeEnd" type="text" value="${param.endTimeEnd }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
					</td>
					<td colspan="2">
						<button name="search" id="search" type="button" onclick="checkApplication(document.linkmanActivities)"><ng:locale key="operation.button.search"/></button>
						<button name="add" id="add" type="button" onclick="linkmanActivityAdd()" style="margin-left:10px;"><ng:locale key="operation.button.add"/></button>
					</td>
				</tr>
            </table>
        </form>

        <div id="kkk"></div>
        <div class="mt">
        <table id="LinkmanActivityQueryId" class="prod mt">
                <tbody class="list_tbody_header">
                    <tr>
                        <td><ng:locale key ="linkmanActivity.eventName"/></td>
                        <td><ng:locale key ="linkmanActivity.topic"/></td>
                        <td><ng:locale key ="linkmanActivity.beginTime"/></td>
                        <td><ng:locale key ="linkmanActivity.endTime"/></td>
                        <td width="12%"><ng:locale key ="sysOperationLog.moduleName"/></td>
                    </tr>
                 </tbody>
                <tbody class="list_tbody_row">
                 <c:forEach items="${linkmanActivityList}" var="linkmanActivityOb" >
                 <tr class="bg-c gry3">
                       <td>${linkmanActivityOb.event_name }</td>
                       <td>${linkmanActivityOb.topic }</td>
                       <td>${linkmanActivityOb.begin_time }</td>
                       <td>${linkmanActivityOb.end_time }</td>                      
                       <td>
                            <img src="images/search.gif" style="cursor:pointer;" onclick="window.location.href='linkmanActivitiesDetailQuery.html?id=${linkmanActivityOb.id }';" alt="<ng:locale key="menu.editJbdMemberLinkCalcHis"/>"/>
                            &nbsp;&nbsp;
                            <img src="images/pencil.gif" style="cursor:pointer;" onclick="window.location.href='linkmanActivityform.html?id=${linkmanActivityOb.id }&linkmanActivityFunction=linkmanActivityUpdate';" alt="<ng:locale key="operation.button.edit"/>"/>
                            &nbsp;&nbsp;
                            <img src="images/delete.gif" style="cursor:pointer;" onclick="linkmanActivityDelete(${linkmanActivityOb.id })" alt="<ng:locale key="operation.button.delete"/>"/>
                       </td>
                       </tr>
                 </c:forEach>
                </tbody>
        </table>
        </div>
	${page.pageInfo }
	</div>
</body>
