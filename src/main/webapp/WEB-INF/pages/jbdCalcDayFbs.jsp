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
            <h3 class="color2 ml">创业保障奖查询</h3>
        </div>
        <form action="" method="get" name="jbdCalcDayFbs" id="jbdCalcDayFbs">
        <table class="search_table mt" >
            <tr>
                <td width="80px">开始期别：</td>
                <td width="180px">
                	<input id="startweek" name="startweek" type="text" 
						maxlength="10" value="${param.startweek }" 
						class="Wdate" onclick="WdatePicker({dateFmt:'yyyyMMdd',readOnly:true})" />
                </td>
                <td width="80px">结束期别：</td>
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
                <tr>
                    <td>会员编号</td>
                    <td>昵称</td>
                    <td>新级别</td>
                    <td>期别</td>
                    <td>创业保障奖</td>
                    <td>应发奖金</td>
                    <td>抵用券</td>
                    <td>服务费</td>
                </tr>
                </tbody>
                <tbody class="list_tbody_row">

               <c:choose>
                   <c:when test="${jbdCalcDayFbs!=null&&fn:length(jbdCalcDayFbs)>0}">
                       <c:forEach items="${jbdCalcDayFbs}" var="jbdCalcDayFb" >
                           <tr class="bg-c gry3">
                               <td>${jbdCalcDayFb.USER_CODE }</td>
                               <td>${jbdCalcDayFb.PET_NAME }</td>
                               <td><ng:code listCode="member.level" value="${jbdCalcDayFb.MEMBER_LEVEL }"/></td>
                               <td>${jbdCalcDayFb.W_WEEK }</td>
                               <td>${jbdCalcDayFb.FB_MONEY }</td>
                               <td>${jbdCalcDayFb.SEND_MONEY }</td>
                               <td>${jbdCalcDayFb.DEDUCT_VOLUME }</td>
                               <td>${jbdCalcDayFb.RAX }</td>
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