    <%@ page pageEncoding="utf-8"%>
        <%@ include file="/common/taglibs.jsp"%>
        <%@ page import="java.util.*" %>
        <%@ page import="java.text.SimpleDateFormat" %>

            <%
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	Date currentTime = new Date();
	String nowTime = dateFormat.format(currentTime);
	String startTime = request.getParameter("startTime") != null 
						? request.getParameter("startTime") : nowTime;
		dateFormat.format(currentTime);
%>
<head>
    <%-- <title><ng:locale key="schedule.message"></ng:locale></title> --%>
    <meta name="heading" content="<ng:locale key='scheduleManageDetail.heading'/>"/>
    <meta name="menu" content="ScheduleManageMenu"/>
    <meta name="parentMenu" content="CommerceMenu"/>

    <!-- <link rel="stylesheet" href="styles/fullcalendar/ca9ck1eg.css"> -->
    <link rel="stylesheet" href="styles/tbsp.css">
    <link rel="stylesheet" href="styles/fullcalendar/account.css">
    <link rel="stylesheet" href="styles/fullcalendar/common_v2.css">
    <script src='styles/fullcalendar/jquery-1.5.2.min.js'></script>

    <!-- <link rel="stylesheet" href="./styles/calendar/calendar-blue.css" />
    <script src="./scripts/calendar/calendar.js"> </script>
    <script src="./scripts/calendar/calendar-setup.js"> </script>
    <script src="./scripts/calendar/lang.jsp"> </script> -->
    <script src="<c:url value='/scripts/My97DatePicker/WdatePicker.js'/>"></script>
    <script>
    var jq = jQuery.noConflict();
    var time = "<%=startTime%>";
    var startTime = "${scheduleManage.startTime}";
    jq(function() {
    //如果是新增时，给上传时间赋值为当前时间
    if (startTime == null || startTime == undefined || startTime == "") {
        jq("#startTime").val(time);
    }
    });
    </script>
</head>
<div class="cont">	
		<div class="bt mt">
			<h3 class="color2 ml"><ng:locale key="schedule.message"/></h3>
		</div>	
	<div class="mt">
        <form:form commandName="scheduleManage" method="post" action="scheduleManageform" id="scheduleManageForm" onsubmit="return validateSchedule(this)">
        <form:errors path="*" cssClass="error" element="div"/>
        <form:hidden path="id" />
        
        <table class="form_edit_table">
    <tbody>
        <tr>
            <td><span class="red">*</span><ng:locale key="schedule.theme"/>：</td>
            <td><form:input path="scheduleName" id="scheduleName"/></td>
        	<td><ng:locale key="schedule.type"/>：</td>
            <td>
            <form:select path="eventType">
            <form:option value="0"><ng:locale key="schedule.tel"/></form:option>
            <form:option value="1"><ng:locale key="schedule.email"/></form:option>
            <form:option value="2"><ng:locale key="schedule.meeting"/></form:option>
            <form:option value="3"><ng:locale key="schedule.callon"/></form:option>
            <form:option value="4"><ng:locale key="schedule.directmail"/></form:option>
            <form:option value="5"><ng:locale key="schedule.note"/></form:option>
            </form:select>
            </td>
        </tr>
        <tr>
            <td><ng:locale key="schedule.state"/>：</td>
            <td>
                <form:select path="status">
                <form:option value="0"><ng:locale key="schedule.notyet"/></form:option>
                <form:option value="1"><ng:locale key="schedule.inhand"/></form:option>
                <form:option value="2"><ng:locale key="schedule.parden"/></form:option>
                <form:option value="3"><ng:locale key="schedule.completed"/></form:option>
                <form:option value="4"><ng:locale key="schedule.delay"/></form:option>
                </form:select>
            </td>
            <td><ng:locale key="schedule.priority"/>：</td>
            <td>
                <form:select path="priority">
                <form:option value="0"><ng:locale key="schedule.low"/></form:option>
                <form:option value="1"><ng:locale key="schedule.middle"/></form:option>
                <form:option value="2"><ng:locale key="schedule.high"/></form:option>
                </form:select>
            </td>
        </tr>
        <tr>
            <td><ng:locale key="schedule.startTime"/>：</td>
            <td>
                <form:input path="startTime" id="startTime" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
            </td>
            <td><ng:locale key="schedule.endTime"/>：</td>
            <td>
                <form:input path="endTime" id="endTime" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})"/>
            </td>
        </tr>
<!--         <tr> -->
<%--             <td><label class="pdl10"><ng:locale key="schedule.linkmanNo"/>:</label></td> --%>
<!--             <td> -->
<%--                 <form:errors path="linkmanId" cssClass="fieldError"/> --%>
<%--                 <form:input path="linkmanId" id="linkmanId" cssClass="text medium" cssErrorClass="text medium error" maxlength="255"/> --%>
<!--             </td> -->
<!--         </tr> -->
        <tr>
            <td><ng:locale key="schedule.remind"/>：</td>
            <td>
                <form:select path="remind">
                <form:option value="0"><ng:locale key="schedule.no"></ng:locale></form:option>
                <form:option value="1"><ng:locale key="schedule.yes"></ng:locale></form:option>
                </form:select>
            </td>
            <td><ng:locale key="schedule.repeat"/>：</td>
            <td>
                <form:select path="repeat">
                <form:option value="0"><ng:locale key="schedule.no"></ng:locale></form:option>
                <form:option value="1"><ng:locale key="schedule.repeat.onlyone"></ng:locale></form:option>
                <form:option value="2"><ng:locale key="schedule.repeat.everyweek"></ng:locale></form:option>
                <form:option value="3"><ng:locale key="schedule.repeat.everymonth"></ng:locale></form:option>
                </form:select>
            </td>
        </tr>
        <tr>
            <td><ng:locale key="schedule.replace"/>：</td>
            <td colspan="3">
                <form:textarea path="place" id="place" style="width:99%;height:64px;margin-top:2px;resize:none;overflow:hidden;"
                 onkeyup="check(place);"/>
            </td>
        </tr>
        <tr>
            <td><ng:locale key="schedule.remark"/>：</td>
            <td colspan="3">
                <form:errors path="remark"/>
                <form:textarea path="remark" id="remark" style="width:99%;height:64px;margin-top:2px;resize:none;overflow:hidden;"
                  onkeyup="check(remark);"/>
                <br>
                <span style="size: 10px;color: red;">提示：字数控制在150字左右。</span>
            </td>
        </tr>
</tbody>
</table>
<div class="tc">
		<button type="submit" class="btn btn-info" name="save"><ng:locale key="operation.button.save"/></button>
        <c:if test="${not empty scheduleManage.id}">
        <button type="submit" name="delete" onclick="bCancel=true;return confirmMessage('确定删除?')"  class="btn btn-success"><ng:locale key="operation.button.delete"/></button>
        </c:if>
      	<button type="button" name="cancel" onclick="location.replace(document.referrer);" class="btn btn-warning"><ng:locale key="operation.button.cancel"/></button></div>
    </form:form>
    </div>
</div>
		<script type="text/javascript">
		 function validateSchedule(theForm){
				 document.getElementById('scheduleName').focus();
					var sname = theForm.scheduleName.value;
					while(sname.lastIndexOf(" ")>=0){
						sname = sname.replace(" ","");
		                }
	             if(sname.length == 0 && sname =="" ){
	             	alert("<ng:locale key='scheduleName.required'/>");
						theForm.scheduleName.focus();
						return false;
		                }
	             if(sname.length >50 ){
		             	alert("<ng:locale key='scheduleName.toolong'/>");
							theForm.scheduleName.focus();
							return false;
			                }
				 return true;
			}
		 
		 function check(st) {
			 var regC = /[^ -~]+/g;
			 var regE = /\D+/g;
			 var str = st.value;

			 if (regC.test(str)){
				 st.value = st.value.substr(0,150);
			 }

			 if(regE.test(str)){
				 st.value = st.value.substr(0,500);
			 }
			 } 

				
		</script>

<v:javascript formName="scheduleManage" cdata="false" dynamicJavascript="true" staticJavascript="false"/>
<script src="<c:url value='/styles/fullcalendar/validator.jsp'/>"></script>

<script>
Form.focusFirstElement($('scheduleManageForm'));
</script>
