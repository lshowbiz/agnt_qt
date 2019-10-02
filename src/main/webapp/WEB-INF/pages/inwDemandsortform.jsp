<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglibs.jsp"%>
<head>
    <!--创新与发展的详细页面  -->
    <script>
    function showLine(){
    //alert("1111");
    }

    </script>
</head>

<body>
    <h2 class="title mgb20">创新&发展的详细查询页面</h2>
    <!-- <div class="gy"></div> -->

	<c:forEach items="${inwDemandSortDetailList}" var="inwDemand">
	<div class="cooperation fl rel">
        <a class="cooperationImg fl"><img src="${FILE_URL }/${inwDemand.demand_Image }" alt="photos" width="147" height="111" /></a>
        <div class="cooperationCon fl">
            <h3>${inwDemand.name }</h3>
            <div class="cooperationText">${inwDemand.summary}</div>
            <a href="inwSuggestionformNew.html?demandId=${inwDemand.id }&demandsortId=${inwDemand.demandsortId}" class="abs">提交您的建议或方案</a>
        </div>
    </div>
	</c:forEach>

	
</body>
