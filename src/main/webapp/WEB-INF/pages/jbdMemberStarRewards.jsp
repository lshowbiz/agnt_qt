<%@ page contentType = "text/html; charset=utf-8" language = "java"%>
<%@ include file="/common/taglibs.jsp"%>

<body>



<div class="cont">
    	<div class="bt mt">
			<h3 class="color2 ml">奖衔奖励查询</h3>
		</div>
		<form action="" method="get" name="jbdMemberStarRewards" id="jbdMemberStarRewards" onsubmit="return validateMyForm(this);">
                <table class="search_table mt" >
                     <tr>
                         <td style="width:80px;"><ng:locale key="bdPeriod.fyear"/>：</td>
                         <td style="width:200px;"><input name=fyear id="query_fyear" type="text" value="${param.fyear==null?currentFyear:param.fyear }" size="6"  onkeyup="this.value=this.value.replace(/\D/g,'')"/></td>
                 
                         <td style="width:80px;"><ng:locale key="rewards.repolicy"/>：</td>
                         <td style="width:200px;"><font color=red>${errorMsg }</font><ng:list name="rewards" listCode="star.rewards.repolicy.${param.fyear==null?currentFyear:param.fyear }" defaultValue="" value="${param.rewards }" showBlankLine="true" style="height:25px;"></ng:list></td>
                
                         <td>
                         	<button name="search" id="search" type="submit" ><ng:locale key="operation.button.search"/></button>
                         </td>
                     </tr>
                 </table>
            </form>


 <div class="mt">
        <table class="prod mt" border="1">
                <tbody class="list_tbody_header">
                    <tr>
                        <td><ng:locale key ="miMember.memberNo"/></td>
                        <td><ng:locale key ="bdPeriod.fyear"/></td>
                        <td><ng:locale key ="rewards.repolicy"/></td>
                        <td><ng:locale key ="rewards.isReach"/></td>
                    </tr>
                 </tbody>
                <tbody class="list_tbody_row">
                 <c:forEach items="${jbdMemberStarRewards}" var="reward" >
                 <tr class="bg-c gry3">
                      
                       <td>${reward.USER_CODE } </td>
                       <td>
                           ${reward.F_YEAR }
                       </td>
                       <td title="${reward.MEMBER_REMARK }"><ng:code listCode="star.rewards.repolicy.${param.fyear==null?currentFyear:param.fyear }" value="${reward.REWARDS }"/>
                       <c:if test="${not empty reward.MEMBER_REMARK }"><img src="${ctx }/images/iconInformation.gif"/></c:if>
                       </td>
                       <td>
                       <c:if test="${reward.IS_REACH=='0' }">
                           <span style="color:red;font-weight:bold;"><ng:code listCode="yesno" value="${reward.IS_REACH }"/></span>
                       </c:if>
                       <c:if test="${reward.IS_REACH=='1' }">
                           <span style="color:green;font-weight:bold;"><ng:code listCode="yesno" value="${reward.IS_REACH }"/></span>
                       </c:if>
                       </td>
                    </tr>
                       
                       
                       
                 </c:forEach>
                </tbody>
        </table>
        </div>
	${page.pageInfo }





</div>

















	<%-- <div class="main">
        <h2 class="title mgb20">奖衔奖励查询</h2>
        <div class="condition">
            
        </div>
        <div id="loading">
            <table width="100%" class="tabQueryLs" style="margin-top:2px;">
                <thead>
                 <tr>
                     <th width=""></th>
                     <th></th>
                     <th></th>
                     <th></th>
                 </tr>
             </thead>
                <tbody>
             <c:forEach items="${}" var="" >
                   
             </c:forEach>
            </tbody>
            <!-- 
            ${page.pageInfo } -->
            </table>
        </div>
    </div> --%>
    <script>
    $(function(){
        $('.tabQueryLs tbody').find('tr:odd > td').css('background-color','#E4EBFF');
    });
    function validateMyForm(theForm){
    	var query_fyear = $("#query_fyear").val();
    	if(query_fyear==null||query_fyear==""){
    		alert("请输入财政年!");
    		return false;
    	}
    	return true;
    }
    </script>
</body>






















