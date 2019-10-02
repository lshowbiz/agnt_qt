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
            <h3 class="color2 ml">顾问推荐奖励查询</h3>
        </div>
        <form action="" method="get" name="jbdDayCustRecommend" id=jbdDayCustRecommend>
        <table class="search_table mt" >
            <tr>
                <td width="80px">开始时间：</td>
                <td width="180px">
                	<input id="startweek" name="startweek" type="text" 
						maxlength="10" value="${param.startweek }" 
						class="Wdate" onclick="WdatePicker({dateFmt:'yyyyMMdd',readOnly:true})" />
                </td>
                <td width="80px">结束时间：</td>
                <td width="180px">
                	<input id="endweek" name="endweek" type="text" 
						maxlength="10" value="${param.endweek }" 
						class="Wdate" onclick="WdatePicker({dateFmt:'yyyyMMdd',readOnly:true})" />
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
                    <td>日期</td>
                    <td>级别</td>
                    <td>顾问级别</td>
                    <td>总金额(元)</td>
                    <td>结算日期</td>
                    <td>查看</td>
                </tr>
                </tbody>
                <tbody class="list_tbody_row">

               <c:choose>
                   <c:when test="${jbdDayCustRecommend!=null&&fn:length(jbdDayCustRecommend)>0}">
				 <c:forEach items="${jbdDayCustRecommend}" var="jbdDayCustRecommend" >
					<tr class="bg-c gry3" style="text-align: center"> 
						<td class="color2">${jbdDayCustRecommend.user_code }</td>                   
						<td>${jbdDayCustRecommend.user_name}</td>                 
						<td>${jbdDayCustRecommend.w_week}</td>                 
						<td>
                                <span style="color:#999999;font-weight:bold;"><ng:code listCode="member.level" value="${jbdDayCustRecommend.member_level }"/></span>
						</td>		
						<td>
                                <span style="color:#999999;font-weight:bold;"><ng:code listCode="pass.star" value="${jbdDayCustRecommend.member_star }"/></span>
						</td>		
						<td class="color1"><fmt:formatNumber value="${jbdDayCustRecommend.recommend_pv}" type="number" pattern="###,###,##0.00"/></td>
						
						<td>${jbdDayCustRecommend.com_date}</td>  
                       <td class="w80">
                       <security:authorize url="/jbdDayCustRecommendDetail">
	                   
	                           <img style="cursor:pointer" src="images/search.gif" onclick="window.location.href='jbdDayCustRecommendDetail.html?userCode=${jbdDayCustRecommend.user_code }&wweek=${jbdDayCustRecommend.w_week }';" alt="<ng:locale key="function.menu.view"/>"/>
	                    
	                    </security:authorize>                       
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
    function go_print (id) {
    	var newWin = window.open('/jbdDayCustRecommend.html?id='+id);
    	newWin.print();
    	//newWin.close();
     }
    </script>














