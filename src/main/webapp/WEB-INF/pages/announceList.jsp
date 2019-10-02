<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>

<link rel="stylesheet" href="<c:url value="/styles/calendar/calendar-blue.css"/>" />
<script src="<c:url value="/scripts/calendar/calendar.js"/>"></script>
<script src="<c:url value="/scripts/calendar/calendar-setup.js"/>"></script>
<script src="<c:url value="/scripts/calendar/lang.jsp"/>"></script>

<script>


</script>
<div class="cont" >	
			<div class="bt mt">
				<h3 class="color2 ml">资讯列表</h3>
			</div>	
			
<%--<div class="condition">
    <form method="post" action="${ctx }/announce/searchAnnounce">
        <table width="100%">
            <colgroup style="width:70px;"></colgroup>
            <colgroup style="width:155px;"></colgroup>
            <colgroup style="width:70px;"></colgroup>
            <colgroup style="width:130px;"></colgroup>
            <colgroup style="width:70px;"></colgroup>
            <tbody>
                <tr>
                    <td><label><spring:message code="message.subjet"/>：</label></td>
                    <td><input type="text" name="annSub" id="annSub" class="mgtb10"/></td>
                    <td><label><spring:message code="announce.status"/>：</label></td>
                    <td>
                        <select id="anStatus" name="anStatus" class="mySelect">
                            <option value="" <c:if test="${mesStatus ==null }">selected="selected"</c:if>></option>
                            <option value="0" <c:if test="${mesStatus ==0 }">selected="selected"</c:if>><spring:message code="message.noback"/></option>
                            <option value="1" <c:if test="${mesStatus ==1 }">selected="selected"</c:if>><spring:message code="message.yiback"/></option>
                        </select>
                    </td>
                    <td><label><spring:message code="announce.category"/>：</label></td>
                    <td>
                        <select name="annoClassNo" id="annoClassNo" class="mySelect">
                            <option value=""></option>
                            <option value="1"><spring:message code="announce.newreour"/></option>
                            <option value="2"><spring:message code="announce.post"/></option>
                            <option value="3"><spring:message code="announce.sysexplanation"/></option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td><label><spring:message code="message.sendTime"/>：</label></td>
                    <td>
                        <input type="text" readonly="readonly" size="10" name="stime" id="stime"/>
                        <img src="${ctx}/images/calendar/calendar7.gif" id="img_startDate" style="cursor: pointer;" title="<ng:locale key="Calendar.TT.SEL_DATE"/>"/>
                        <script>
                        Calendar.setup({
                            inputField     :    "stime",
                            ifFormat       :    "%Y-%m-%d",
                            button         :    "img_startDate",
                            singleClick    :    true
                        });
                        </script>
                    </td>
                    <td><label><spring:message code="announce.to"/></label></td>
                    <td>
                        <input type="text" readonly="readonly" size="10" name="etime" id="etime"/>
                        <img src="${ctx}/images/calendar/calendar7.gif" id="img_endOperatorTime" style="cursor: pointer;" title="<ng:locale key="Calendar.TT.SEL_DATE"/>"/>
                        <script>
                        Calendar.setup({
                            inputField     :    "etime",
                            ifFormat       :    "%Y-%m-%d",
                            button         :    "img_endOperatorTime",
                            singleClick    :    true
                        });
                        </script>
                    </td>
                    <td>
                        <input type="submit" class="btn_common btn_mini corner2" value="<spring:message code="search"/>" />
                    </td>
                </tr>
            </tbody>
        </table>
    </form>
</div>--%>

	<div class="mt">	
		<table class="prod mt" border="1" style="border-collapse:collapse; border:1px solid #ebebeb;" >	
			<tbody class="list_tbody_header">
				<tr>
		            <td>&nbsp;</td>
		            <td><spring:message code="message.subjet"/></td>
		            <td><spring:message code="announce.category"/></td>
		            <td><spring:message code="message.sendTime"/></td>
		        </tr>
		    </tbody>
		    <tbody class="list_tbody_row">	
		        <c:forEach items="${announceList }" var="announce">
			        <tr class="bg-c gry3">
			            <td>
			            	<c:if test="${not empty announce.uni_no }">
			            		 <a href="${ctx}/announce/detailinfo?aaNo=${announce.aano }"><img title="已查看公告" src="${ctx }/images/letter_16.gif"/></a>
			            	</c:if>
			            	<c:if test="${ empty announce.uni_no }"> 
			            		<a href="${ctx}/announce/detailinfo?aaNo=${announce.aano }"><img title="未查看公告" src="${ctx }/images/bubble_16.gif"/></a>
			            	</c:if>
			            </td>
			            <td>
			                <a href="${ctx}/announce/detailinfo?aaNo=${announce.aano }" class="fl pdl10">
			                    <span <c:if test="${announce.highlight }">style="color:red;"</c:if>>${announce.subject }</span>
			                </a>
			            </td>
			            <td>
			            	<ng:code listCode="annoclassno" value="${announce.anno_class_no}"/>
			            </td>
			            <td>${announce.create_time }</td> 
			        </tr>
		        </c:forEach>
		    </tbody>
		</table>
		${page.pageInfo }
	</div>
</div>