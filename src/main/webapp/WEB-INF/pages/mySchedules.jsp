<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<head>
<!-- <title>工作列表</title> -->
</head>
    <script src="<c:url value='/scripts/My97DatePicker/WdatePicker.js'/>"></script>
    <script>
   /*  $(function(){
        $('#tabQueryLs tbody').find('tr:odd > td').css('background-color','#E4EBFF');
    }); */
    </script>


<body>
   <div class="cont">
    	<div class="bt mt">
			<h3 class="color2 ml">工作列表</h3>
		</div>
        <form action="" method="post" name="myScheduleList" id="myScheduleListId">
        <input type="hidden" name="status" value="${status }"/>
            <table class="search_table mt">
                <tr>
                    <td><ng:locale key="schedule.theme"/>：</td>
                    <td><input type="text" name="scheduleName" value="${scheduleName }" maxlength="100"/></td>
                    <td><ng:locale key="schedule.type"/>：</td>
                    <td>
                        <ng:list name="eventType" id="eventType" listCode="schedule.type" defaultValue="" showBlankLine="true" value="${eventType }"/>
                    </td>
                    <td>状态：</td>
                    <td>
						<select name="scheduleStatue">
							<option value="notyet" ${statue=='notyet'?'selected':''}>尚未开始</option>
							<option value="inhand" ${statue=='inhand'?'selected':''}>处理中</option>
							<option value="parden" ${statue=='parden'?'selected':''}>暂停</option>
							<option value="completed" ${statue=='completed'?'selected':''}>已完成</option>
							<option value="delay" ${statue=='delay'?'selected':''}>延期</option>
						</select>
					</td>
                </tr>
                <tr>
                    <td><ng:locale key="schedule.priority"/>：</td>
                    <td>
                        <ng:list name="priority" id="priority" listCode="schedule.priority" defaultValue="" showBlankLine="true" value="${priority }"/>
                    </td>
                    <td><ng:locale key ="schedule.startTime"/>：</td>
                    <td>
                        <input name="startTime" id="startTime" type="text" value="${startTime }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
                    </td>
                    <td><ng:locale key ="schedule.endTime"/>：</td>
                    <td>
                    	<input name="startTime2" id="startTime2" type="text" value="${startTime2 }" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
                    </td>
                    <td><button name="search" id="search" type="submit"><ng:locale key="operation.button.search"/></button></td>
                </tr>
            </table>
        </form>
		<div class="mt">
        <table id="tabQueryLs" class="prod mt">
                <tbody class="list_tbody_header">
                    <tr>
                        <td><ng:locale key ="schedule.theme"/></td>
                        <td><ng:locale key ="schedule.type"/></td>
                        <td><ng:locale key ="schedule.startTime"/></td>
                        <td><ng:locale key ="schedule.endTime"/></td>
                        <td><ng:locale key ="schedule.priority"/></td>
                        <td><ng:locale key ="schedule.state"/></td>
                        <%-- <td><ng:locale key ="schedule.linkmanId"/></td> --%>
                        <td><ng:locale key ="sysOperationLog.moduleName"/></td>
                    </tr>
                 </tbody>
                <tbody class="list_tbody_row">
                 <c:forEach items="${mySchedules}" var="mySche" >
                 <tr onclick="window.location.href='${ctx}/loginform/scheduleInfo?id=${mySche.id }'" class="bg-c gry3">
                       <td>
			      			<c:choose>
								<c:when test="${fns:isAbbreviate(mySche.scheduleName, 15)}">
									${fns:abbreviate(mySche.scheduleName, 15,'...')}
								</c:when>
								<c:otherwise>
									 ${mySche.scheduleName }
								</c:otherwise>
							</c:choose>
                       </td>
                       <td><c:if test="${mySche.eventType ==0}">电话</c:if>
                       <c:if test="${mySche.eventType ==1}">Emali</c:if>
                       <c:if test="${mySche.eventType ==2}">会议</c:if>
                       <c:if test="${mySche.eventType ==3}">拜访</c:if>
                       <c:if test="${mySche.eventType ==4}">直邮</c:if>
                       <c:if test="${mySche.eventType ==5}">短信</c:if> </td>
                       <td>${mySche.startTime }</td>
                       <td>${mySche.endTime }</td>
                       <td><c:if test="${mySche.priority ==0}">低</c:if>
                       <c:if test="${mySche.priority ==1}">中</c:if>
                       <c:if test="${mySche.priority ==2}">高</c:if> </td>
                       <td><c:if test="${mySche.status ==0}">尚未开始</c:if>
                       <c:if test="${mySche.status ==1}">处理中</c:if>
                       <c:if test="${mySche.status ==2}">暂停</c:if>
                       <c:if test="${mySche.status ==3}">已完成</c:if>
                       <c:if test="${mySche.status ==4}">延期</c:if> </td>
                       <%-- <td>${mySche.linkmanId }</td> --%>
                       
                       
                       <td>
                            <img src="../images/pencil.gif" onclick="window.location.href='${ctx}/loginform/scheduleInfo?id=${mySche.id }';" 
                            alt="<ng:locale key="operation.button.edit"/>" style="cursor:pointer;"/>
                       </td>
                       </tr>
                 </c:forEach>
                </tbody>
        </table>
        </div>
        ${page.pageInfo }
        </div>
</body>

