<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglibs.jsp"%>
<head>
    <!--创新共赢-需求(合作共赢)的查询页面  -->
    <script>
    function showLine(){
    //alert("1111");
    }

    </script>
</head>

<body>
    <h2 class="title mgb20"><ng:locale key ="win.Activity"/></h2>
    <div class="gy"></div>
    <c:forEach items="${inwDemandList}" var="inwDemand">
    <div class="cooperation mgtb10 clearfix">
        <a class="cooperationImg fl"><img src="${FILE_URL }/${inwDemand.demand_Image }" alt="photos" width="130" height="105" /></a>
        <div class="cooperationCon fr">
            <h3><a href="inwDemandDetail.html?id=${inwDemand.id }" class="hoverLine colorCS">${inwDemand.name }</a></h3>
            <div class="cooperationText"><a href="inwDemandDetail.html?id=${inwDemand.id }" class="hoverLine" >${inwDemand.summary }</a></div>
           <div class="yourCPs fr"><a href="inwSuggestionReply.html?id=${inwDemand.id }&differenceInw=activity"><ng:locale key="view.your.program.of.activities"/></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="inwSuggestionformActivity.html?id=${inwDemand.id }" class="colorCS bold"><ng:locale key="pleaseSubmit.Your.Event.Proposal"/></a></div>
        </div>
    </div>
    </c:forEach>
   
</body>