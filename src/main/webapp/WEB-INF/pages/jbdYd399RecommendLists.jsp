<%@ page contentType = "text/html; charset=utf-8" language = "java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>

    <meta name="menu" content="FoundationOrderMenu"/>
    <link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
    <script src="./scripts/calendar/calendar.js"> </script>
    <script src="./scripts/calendar/calendar-setup.js"> </script>
    <script src="./scripts/calendar/lang.jsp"> </script>

    <script src="${ctx}/scripts/My97DatePicker/WdatePicker.js"></script>
</head>

    <div class="cont">
        <div class="bt mt">
            <h3 class="color2 ml">399店主推荐奖励查询</h3>
        </div>
        <form action="" method="get" name="jbdYd399RecommendList" id=jbdYd399RecommendList>
        <table class="search_table mt" >
            <tr>
                <td width="80px">开始时间：</td>
                <td width="180px">
                	<input id="startweek" name="startweek" type="text" 
						 value="${param.startweek }" 
						class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" />
                </td>
                <td width="80px">结束时间：</td>
                <td width="180px">
                	<input id="endweek" name="endweek" type="text" 
						 value="${param.endweek }" 
						class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" />
                </td>
                <td>
                    <button type="submit" onclick="loading('<ng:locale key="button.loading"/>');"><ng:locale key="operation.button.search"/></button>
                </td>
               </tr>
        </table>
</form>
        <!---------->
        <div class="mt">
            <table class="prod mt">
                <tbody class="list_tbody_header">
                <tr style="text-align: center">
                    <td>会员编号</td>
                    <td>昵称</td>
                    <td>会员级别</td>
                    <td>结算时间</td>
                    <td>推荐399店主编号</td>
                    <td>399店主推荐奖励（元）</td>
                    <td>发放状态</td>
                </tr>
                </tbody>
                <tbody class="list_tbody_row">

               <c:choose>
                   <c:when test="${jbdYd399RecommendList!=null&&fn:length(jbdYd399RecommendList)>0}">
				 <c:forEach items="${jbdYd399RecommendList}" var="jbdYd399RecommendList" >
					<tr class="bg-c gry3" style="text-align: center"> 
						<td class="color2">${jbdYd399RecommendList.user_code }</td>                   
						<td>${jbdYd399RecommendList.mi_user_name}</td>                 
						<td>
                            <span style="color:#999999;font-weight:bold;"><ng:code listCode="member.level" value="${jbdYd399RecommendList.member_level }"/></span>
						</td>		
						<td>${jbdYd399RecommendList.calc_time}</td>    
						<td>${jbdYd399RecommendList.recommend_no}</td>    
						<td class="color1"><fmt:formatNumber value="${jbdYd399RecommendList.recommend_amount}" type="number" pattern="###,###,##0.00"/></td>
						<td>
                            <span style="color:#999999;font-weight:bold;"><ng:code listCode="mimember.sendstatus" value="${jbdYd399RecommendList.send_status }"/></span>
						</td>		
					</tr>
				</c:forEach>
                   </c:when>
                   <c:otherwise>
                       <tr class="bg-c gry3">
                           <td colspan="8">暂无数据</td>
                       </tr>
                   </c:otherwise>
               </c:choose>
                </tbody>
            </table>
        </div>
		${page.pageInfo }
    </div>

    <script>
    $(function(){
        $('.tabQueryLs tbody').find('tr:odd > td').css('background-color','#E4EBFF');
    });

    </script>














