<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<link rel="stylesheet" href="./styles/calendar/calendar-blue.css" /> 
<script src="./scripts/calendar/calendar.js"></script>
<script src="./scripts/calendar/calendar-setup.js"></script>
<script src="./scripts/calendar/lang.jsp"></script>
<head>
    <!--<title>合作共赢 － 查看建议(新需求)</title> -->
    <script>
    function loading(){
        var str = '<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/indicator_smallwaitanim.gif" alt="Loading" align="absmiddle"/>';
        str += '&nbsp;&nbsp;<ng:locale key="button.loading"/>';
        document.getElementById("kkk").innerHTML=str;
    }
    </script>
</head>

<img style="display:none" src="images/indicator_smallwaitanim.gif" alt="Loading"  align="absmiddle" />
<body>
    <h2 class="title mgb20"><ng:locale key="record.yourSubmission"/></h2>
    <!-- 增加查询条件的以后扩展时再用
     <div class="condition">
        <form action="inwSuggestionReplyNew" method="get" name="inwSuggestionReplyNewQuery" id="inwSuggestionReplyNewQuery">
            <table width="100%" class="personalInfoTab">
				<tbody>
                <tr>
					<td><ng:locale key="linkmanEvent.title"/>：</td>
					<td><input type="text" name="subject" value="${param.subject }" size="22" maxlength="100"/></td>
					<td><ng:locale key="title.date"/>：</td>
					<td>
						<input type="text" name="createTimeBegin" id="createTimeBegin" readonly="readonly" size="10" value="${param.createTimeBegin }"/>
						<img src="./images/calendar/calendar7.gif" id="img_createTimeBegin" style="cursor: pointer;" title="<ng:locale key="Calendar.TT.SEL_DATE"/>"/>
						<script>
							Calendar.setup({
								inputField     :    "createTimeBegin",
								ifFormat       :    "%Y-%m-%d",
								button         :    "img_createTimeBegin",
								singleClick    :    true
							});
						</script>
						-
						<input type="text" name="createTimeEnd" id="createTimeEnd" readonly="readonly" size="10" value="${param.createTimeEnd }"/>
						<img src="./images/calendar/calendar7.gif" id="img_createTimeEnd" style="cursor: pointer;" title="<ng:locale key="Calendar.TT.SEL_DATE"/>"/>
						<script>
							Calendar.setup({
								inputField     :    "createTimeEnd",
								ifFormat       :    "%Y-%m-%d",
								button         :    "img_createTimeEnd",
								singleClick    :    true
							});
						</script>
					</td>
					<td><ng:locale key="schedule.state"/></td>
					<td>
					<select name="status" id="status"  styleClass="mySelect">
					     <option value=""><ng:locale key="list.please.select"/></option>
					     <option value="0"><ng:code listCode="inwsuggestion.status" value="0"></ng:code></option>
   					     <option value="1"><ng:code listCode="inwsuggestion.status" value="1"></ng:code></option>
   					     <option value="2"><ng:code listCode="inwsuggestion.status" value="2"></ng:code></option>
   					     <option value="3"><ng:code listCode="inwsuggestion.status" value="3"></ng:code></option>
   					     <option value="4"><ng:code listCode="inwsuggestion.status" value="4"></ng:code></option>
   					     <option value="5"><ng:code listCode="inwsuggestion.status" value="5"></ng:code></option>
   					     <option value="6"><ng:code listCode="inwsuggestion.status" value="6"></ng:code></option>
					     <option value="7"><ng:code listCode="inwsuggestion.status" value="7"></ng:code></option>
					</select>
					
					</td>
					<td>
						<input name="search" id="search" type="button" onclick="inwSuggestionReplyQQ(document.inwSuggestionReplyNewQuery)" class="btn_common btn_mini corner2" value="<ng:locale key="operation.button.search"/>"/>
					</td>
                </tr>
				</tbody>
            </table>
        </form>
    </div>    
    -->
    <div id="kkk"></div>
    <div class="mgb20" style="min-height:400px;">
        <table width="100%" class="tabQueryLs" id="suggestionReplyListId" style="margin-top:2px;">
            <thead>
                <tr>
                    <th><ng:locale key ="linkmanEvent.title"/></th>
                    <th><ng:locale key ="title.date"/></th>
                    <th><ng:locale key ="schedule.state"/></th>
                    <th><ng:locale key ="inwIntegration.num"/></th>
                    <th><ng:locale key ="sysOperationLog.moduleName"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${suggestionReplyNewList}" var="inwSuggestion" >
                <tr>
					<td width="30%">${inwSuggestion.subject }</td>
					<td width="20%">
					<fmt:formatDate value="${inwSuggestion.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td width="20%">
						<c:if test="${inwSuggestion.status == '0'}">
						  <ng:code listCode="inwsuggestion.status" value="0"></ng:code>
						</c:if>
						<c:if test="${inwSuggestion.status == '1'}">
						  <ng:code listCode="inwsuggestion.status" value="1"></ng:code>
						</c:if>
						<c:if test="${inwSuggestion.status == '2'}">
						  <ng:code listCode="inwsuggestion.status" value="2"></ng:code>
						</c:if>
						<c:if test="${inwSuggestion.status == '3'}">
						  <ng:code listCode="inwsuggestion.status" value="3"></ng:code>
						</c:if>
						<c:if test="${inwSuggestion.status == '4'}">
						  <ng:code listCode="inwsuggestion.status" value="4"></ng:code>
						</c:if>
						<c:if test="${inwSuggestion.status == '5'}">
						  <ng:code listCode="inwsuggestion.status" value="5"></ng:code>
						</c:if>
						<c:if test="${inwSuggestion.status == '6'}">
						  <ng:code listCode="inwsuggestion.status" value="6"></ng:code>
						</c:if>
						<c:if test="${inwSuggestion.status == '7'}">
						  <ng:code listCode="inwsuggestion.status" value="7"></ng:code>
						</c:if>
					</td>
					<td width="15%">${inwSuggestion.integration }</td>
					<td width="15%">
					   <c:choose>
					      <c:when test="${inwSuggestion.replyYesNo == 'Y'}">
					          <img src="images/search.gif" style="cursor:pointer;" onclick="inwDemandSuggestionDetail(${inwSuggestion.id })" title="未阅建议回复,查看详细" alt="<ng:locale key="menu.editJbdMemberLinkCalcHis"/>"/>
					      </c:when>
					      <c:otherwise>
					          <img src="images/search_gray.gif" style="cursor:pointer;" onclick="inwDemandSuggestionDetail(${inwSuggestion.id })" title="已阅建议回复,查看详细" alt="<ng:locale key="menu.editJbdMemberLinkCalcHis"/>"/>
					      </c:otherwise>
					   </c:choose>
					</td>
                </tr>
                <input name = "id" value="${inwSuggestion.id }" id="id" type="hidden"/>
                </c:forEach>
            </tbody>
        </table>
        ${page.pageInfo }
    </div>

	<script>
	
	
	function inwSuggestionReplyQQ(theForm){
	     theForm.submit();
	}
	
	function inwDemandSuggestionDetail(id){
		window.location.href="inwSuggestionReplyDetailNew.html?id="+id;
	}
	</script>
</body>