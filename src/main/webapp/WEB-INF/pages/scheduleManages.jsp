<%@ page pageEncoding="utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
    <%-- <title><ng:locale key="scheduleManager.title"/></title> --%>
    <meta name="heading" content="<ng:locale key='schedule.heading'/>"/>
    <meta name="menu" content="ScheduleManageMenu"/>
    <meta name="parentMenu" content="CommerceMenu"/>
    <link rel='stylesheet' href='styles/fullcalendar/fullcalendar.css' />
    <script src='styles/fullcalendar/jquery-1.5.2.min.js'></script>
    <script src='styles/fullcalendar/jquery-ui-1.8.11.custom.min.js'></script>
    <script src='styles/fullcalendar/fullcalendar.min.js'></script>
<script>
var jq = jQuery.noConflict();
	jq(function() {

		var calendar = jq('#calendar').fullCalendar({
			header: {
				left: 'prev,next today',
				center: 'title',
				right: 'month,agendaWeek,agendaDay'
			},
			firstDay:6,
			disableDragging:true,
			titleFormat:'yyyy年/MM月',
			monthNames: ["<ng:locale key="Calendar.MN.January"/>",
						"<ng:locale key="Calendar.MN.February"/>",
						"<ng:locale key="Calendar.MN.March"/>",
						"<ng:locale key="Calendar.MN.April"/>",
						"<ng:locale key="Calendar.MN.May"/>",
						"<ng:locale key="Calendar.MN.June"/>",
						"<ng:locale key="Calendar.MN.July"/>",
						"<ng:locale key="Calendar.MN.August"/>",
						"<ng:locale key="Calendar.MN.September"/>",
						"<ng:locale key="Calendar.MN.October"/>",
						"<ng:locale key="Calendar.MN.November"/>",
						"<ng:locale key="Calendar.MN.December"/>"],
			monthNamesShort: ["<ng:locale key="Calendar.SMN.Jan"/>",
						"<ng:locale key="Calendar.SMN.Feb"/>",
						"<ng:locale key="Calendar.SMN.Mar"/>",
						"<ng:locale key="Calendar.SMN.Apr"/>",
						"<ng:locale key="Calendar.SMN.May"/>",
						"<ng:locale key="Calendar.SMN.Jun"/>",
						"<ng:locale key="Calendar.SMN.Jul"/>",
						"<ng:locale key="Calendar.SMN.Aug"/>",
						"<ng:locale key="Calendar.SMN.Sep"/>",
						"<ng:locale key="Calendar.SMN.Oct"/>",
						"<ng:locale key="Calendar.SMN.Nov"/>",
						"<ng:locale key="Calendar.SMN.Dec"/>"],
			dayNames: ["<ng:locale key="Calendar.DN.Sunday"/>",
						"<ng:locale key="Calendar.DN.Monday"/>",
						"<ng:locale key="Calendar.DN.Tuesday"/>",
						"<ng:locale key="Calendar.DN.Wednesday"/>",
						"<ng:locale key="Calendar.DN.Thursday"/>",
						"<ng:locale key="Calendar.DN.Friday"/>",
						"<ng:locale key="Calendar.DN.Saturday"/>",
						"<ng:locale key="Calendar.DN.Sunday"/>"],
			dayNamesShort:["<ng:locale key="Calendar.SDN.Sun"/>",
				        "<ng:locale key="Calendar.SDN.Mon"/>",
						"<ng:locale key="Calendar.SDN.Tue"/>",
						"<ng:locale key="Calendar.SDN.Wed"/>",
						"<ng:locale key="Calendar.SDN.Thu"/>",
						"<ng:locale key="Calendar.SDN.Fri"/>",
				        "<ng:locale key="Calendar.SDN.Sat"/>",
						"<ng:locale key="Calendar.SDN.Sun"/>"],
			buttonText: {
				prev:     '&nbsp;&#9668;&nbsp;',  // left triangle
				next:     '&nbsp;&#9658;&nbsp;',  // right triangle
				prevYear: '&nbsp;&lt;&lt;&nbsp;', // <<
				nextYear: '&nbsp;&gt;&gt;&nbsp;', // >>
				today:    '<ng:locale key="Calendar.TT.TODAY"/>',
				month:    '<ng:locale key="label.month"/>',
				week:     '<ng:locale key="Calendar.TT.WK"/>',
				day:      '<ng:locale key="Calendar.SDN.Sun"/>'
			},
			//selectable: true,
			//selectHelper: true,
			select: function(start, end, allDay) {
				var title = prompt('Event Title:');
				if (title) {
					calendar.fullCalendar('renderEvent',
						{
							title: title,
							start: start,
							end: end,
							allDay: allDay
						},
						true // make the event "stick"
					);
				}
				calendar.fullCalendar('unselect');
			},
			editable: true,
			dayClick: function(date, allDay, jsEvent, view) {
				if (allDay) {
				//	alert('Clicked on the entire day: ' + date);
					//window.location='miProjectform?allDayStatus=true&startTime='+date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate()+' '+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
					window.location='scheduleManageform?&startTime='+date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
				}else{
				//	alert('Clicked on the slot: ' + date);
					//window.location='miProjectform?allDayStatus=false&startTime='+date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate()+' '+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
					window.location='scheduleManageform?&startTime='+date.getFullYear()+'-'+(date.getMonth()+1)+'-'+date.getDate();
				}
				//alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
				//alert('Current view: ' + view.name);

			},
			
			eventClick: function(calEvent, jsEvent, view){
				//alert('Event: ' + calEvent.id);
				//alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
				//alert('View: ' + view.name);
				//window.location='miProjectform?mpId='+calEvent.id;
				if (calEvent.id != 0) {
					window.location='scheduleManageform.html?id='+calEvent.id;
				}
				if (calEvent.color == 'red') {
					window.location='publicScheduleform.html?psId='+calEvent.id;
				}
			},
			events: [
				<c:forEach items="${scheduleManageList }" var="scheduleManage" varStatus="scheduleManageStatus">
					{
						id:${scheduleManage.id },
						title: '${scheduleManage.scheduleName }',
						start: '${scheduleManage.startTime }',
						<c:if test="${not empty scheduleManage.endTime }">
							end: '${scheduleManage.endTime }',
						</c:if>
						editable:false,
					},
				</c:forEach>
				<c:forEach items="${publicSchedules}" var="publicSchedule">
					{
						id: ${publicSchedule.psId },
						title: '${publicSchedule.name}',
						start: '${publicSchedule.startTime}',
						<c:if test="${not empty publicSchedule.endTime}">
							end: '${publicSchedule.endTime}',
						</c:if>
						editable: false,
						color: 'red'
					},
				</c:forEach>
				<c:forEach items="${jbdPeriodList}" var="jbdPeriod">
					{
						id: ${jbdPeriod.wyear}${jbdPeriod.wweek},
						title: '${jbdPeriod.fmonth}结算月',
						start: '${jbdPeriod.endTime}',
						<c:if test="${not empty jbdPeriod.endTime}">
							end: '${jbdPeriod.endTime}',
						</c:if>
						editable: false,
						color: 'red'
					},
				</c:forEach>
				
			]
				/*
				{
					id: 0,
					title: 'D Day',
					start: '2012-06-18',
					editable: false
				}
				*/

		});

	});

</script>
<script>

jq(function(){
	jq('.fc-border-separate').find('td').mouseover(function(){
	
		$(this).addClass('tdBg');
	}).mouseleave(function(){
		$(this).removeClass('tdBg');
	});
});
</script>
</head>
<body>
<div class="cont">	
		<div class="bt mt">
			<h3 class="color2 ml"><ng:locale key="scheduleManager.title"/></h3>
		</div>	
	<div class="mt">
                	<table width="100%" border="0" cellspacing="5" cellpadding="0">
                      <tr>
                        <td align="center" valign="top">
                            <div class="table_div">
                              <div class="table_LongTable">
<div id='calendar'></div>
                              </div>
                            </div>
                        </td>
                      </tr>
                    </table>
</div>
</div>
</body>
