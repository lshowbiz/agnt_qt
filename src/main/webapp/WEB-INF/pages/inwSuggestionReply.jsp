<%@ page contentType="text/html; charset=utf-8" language="java"%>
<%@ include file="/common/taglibs.jsp"%>
<head>
    <!--<title>合作共赢 － 查看建议</title> -->
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
    <h2 class="title mgb20"><ng:locale key="examine.suggestion"/></h2>
    <div id="kkk"></div>
    <div class="mgb20" style="min-height:400px;">
        <table width="100%" border="0" class="tabQueryLs" id="suggestionReplyListId" style="margin-top:2px;">
            <thead>
                <tr>
                <th><ng:locale key ="inwSuggestion.suggestedTopics"/></th>
                <th><ng:locale key ="sysOperationLog.moduleName"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${suggestionReplyList}" var="inwSuggestion" >
                <tr>
                <td width="85%">${inwSuggestion.subject }</td>
                <td width="15%" align="center">
                <img src="images/search.gif" onclick="inwDemandSuggestionDetail(${inwSuggestion.id })" alt="<ng:locale key="menu.editJbdMemberLinkCalcHis"/>"/>
                </td>
                </tr>
                <input name = "id" value="${inwSuggestion.id }" id="id" type="hidden"/>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <div><input name="goBack" type="button" class="btn_common corner2 fr" onclick="returnOld()" value="<ng:locale key="operation.button.return"/>" /></div>

    <!--
    <table width="100%">
    <tr style="line-height:300%"></tr>
         <tr style="line-height:300%">
            <td colspan="3">
                <input name="goBack" type="button" class="btn_common corner2 fr mgt30"  onclick="returnOld()" value="<ng:locale key="operation.button.return"/>" />
            </td>
        </tr>
    </table> -->
<script>
    var differenceInw = "<%= request.getAttribute("differenceInw")%>";
    function returnOld(){
        if("demand"==differenceInw){
            window.location.href="inwDemands";
        }else if("activity"==differenceInw){
            window.location.href="inwDemandsActivity";
        }else{
            window.location.href="inwDemandsSystem";
        }
    }

    function inwDemandSuggestionDetail(id){
        var diff = differenceInw;
        window.location.href="inwSuggestionReplyDetail.html?id="+id+"&differenceInw="+diff+"";
    }
</script>
</body>