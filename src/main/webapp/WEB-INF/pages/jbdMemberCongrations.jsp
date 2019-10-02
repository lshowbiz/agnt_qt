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
            <h3 class="color2 ml">新晋奖衔恭贺函</h3>
        </div>
        <form action="" method="get" name="jbdMemberCongrations" id="jbdMemberCongrations">
        <table class="search_table mt" >
            <tr>
                <td width="60px"><ng:locale key="bdPeriod.fmonth"/>：</td>
                <td width="180px"><input  name="yearMonth" type="text" value="${param.yearMonth }" onkeyup="this.value=this.value.replace(/\D/g,'')"/></td>
                <td width="120px">(如：201401)</td>
                <td width="70px"><ng:locale key="pass.star.type"/>：</td>
                <td width="180px"><ng:list name="starLevel" listCode="qualify.star.zero" defaultValue="" value="${param.starLevel }" showBlankLine="true"></ng:list></td>
                <td>
                    <button name="search" type="submit" onclick="loading('<ng:locale key="button.loading"/>');"><ng:locale key="operation.button.search"/></button>
                </td>
               </tr>
        </table>
</form>
        <!---------->
        <div class="mt">
            <table class="prod mt">
                <tbody class="list_tbody_header">
                <tr>
                    <td width=""><ng:locale key ="miMember.memberNo"/></td>
                    <td><ng:locale key ="bdPeriod.fmonth"/></td>
                    <td><ng:locale key ="pass.star.type"/></td>
                    <td width="120px"><ng:locale key ="sysOperationLog.moduleName"/></td>
                </tr>
                </tbody>
                <tbody class="list_tbody_row">

               <c:choose>
                   <c:when test="${jbdMemberCongrations!=null&&fn:length(jbdMemberCongrations)>0}">
                       <c:forEach items="${jbdMemberCongrations}" var="congration" >
                           <tr class="bg-c gry3">
                               <td>${congration.USER_CODE } </td>
                               <td>
                                       ${congration.YEAR_MONTH }
                               </td>
                               <td><ng:code listCode="qualify.star.zero" value="${congration.STAR_LEVEL }"/></td>
                               <td style="width: 12%;">
                                   <!--
	                           <img style="cursor:pointer" src="images/search.gif" onclick="window.open('jbdMemberCongrations/showDetail.html?id=${congration.id }');" alt="<ng:locale key="function.menu.view"/>"/>
	                           <img style="cursor:pointer" src="images/download.gif" onclick="window.location.href='jbdMemberCongrations/download/${congration.id }.html'" alt="<ng:locale key="button.download"/>"/>
	                           <img style="cursor:pointer" src="images/printer.gif" onclick="go_print(${congration.id });" alt="<ng:locale key="button.print"/>"/>
                        -->
                                   <a style="cursor: pointer;" class="hoverLine colorQL" href="#" onclick="window.open('jbdMemberCongrations/showDetail.html?id=${congration.id }');">查看</a>
                                   <span>|</span>
                                   <a style="cursor: pointer;" class="hoverLine colorQL" href="#" onclick="window.location.href='jbdMemberCongrations/download/${congration.id }.html'">下载</a>
                                   <span>|</span>
                                   <a style="cursor: pointer;" class="hoverLine colorQL" href="#" onclick="go_print(${congration.id });">打印</a>
                               </td>
                           </tr>
                       </c:forEach>
                   </c:when>
                   <c:otherwise>
                       <tr class="bg-c gry3">
                           <td colspan="4">暂无数据</td>
                       </tr>
                   </c:otherwise>
               </c:choose>
                </tbody>
            </table>


        </div>


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