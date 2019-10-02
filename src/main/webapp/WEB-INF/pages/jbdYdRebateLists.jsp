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
            <h3 class="color2 ml">云店返利奖查询</h3>
        </div>
        <form action="" method="get" name="jbdCalcDayFbs" id="jbdCalcDayFbs">
        <table class="search_table mt" >
            <tr>
                <td width="80px">开始时间：</td>
                <td width="180px">
                	<input id="startCalcTime" name="startCalcTime" type="text"
						maxlength="10" value="${param.startCalcTime }"
						class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" />
                </td>
                <td width="80px">结束时间：</td>
                <td width="180px">
                	<input id="endCalcTime" name="endCalcTime" type="text"
						maxlength="10" value="${param.endCalcTime }"
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
                <tr>
                    <td>会员编号</td>
                    <td>昵称</td>
                    <td>会员级别</td>
                    <td>时间</td>
                    <td>产生返利店主编号</td>
                    <td>产生返利订单编号</td>
                    <td>云店返利奖励</td>
                    <td>发放状态</td>
                </tr>
                </tbody>
                <tbody class="list_tbody_row">


               <c:choose>
                   <c:when test="${jbdYdRebateLists!=null&&fn:length(jbdYdRebateLists)>0}">
                       <c:forEach items="${jbdYdRebateLists}" var="jbdYdRebateList" >
                           <tr class="bg-c gry3">
                               <td>${jbdYdRebateList.USER_CODE }</td>
                               <td>${jbdYdRebateList.MI_USER_NAME }</td>
                               <td><ng:code listCode="member.level" value="${jbdYdRebateList.MEMBER_LEVEL }"/></td>
                               <td>${jbdYdRebateList.CALC_TIME }</td>
                               <td>${jbdYdRebateList.REBATE_USER_CODE }</td>
                               <td>${jbdYdRebateList.REBATE_ORDER_NO }</td>
                               <td>${jbdYdRebateList.SEND_AMOUNT }</td>
                               <td><ng:code listCode="jbdydrebatelist.sendstatus" value="${jbdYdRebateList.SEND_STATUS }"/></td>

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
    	var newWin = window.open('jbdMemberCongrations/showDetail.html?id='+id);
    	newWin.print();
    	//newWin.close();
     }
    </script>