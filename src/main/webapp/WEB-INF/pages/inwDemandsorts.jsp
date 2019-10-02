<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglibs.jsp"%>
<head>
    <!--什么是创新&发展的初始化查询  -->
    <script>
    function showLine(){
    //alert("1111");
    }

    </script>
</head>

<body>
    <h2 class="title mgb20">创新与发展</h2>
	
	<div class="cxBox">
		<div class="cxBoxL fl">
			<p>创新是指人们为了更好的需要，运用已知的信息，不断突破常规，发现或产生某种新颖、独特的有社会价值或个人价值的新事物、新思想的活动。</p>
			<p>新的经营假设的核心是：价值是由顾客和企业共同创造的。价值创造的过程是以顾客及其创造体验为中心。</p>
			<p>当顾客可以全程参与价值链的所有环节的时候，顾客和企业之间就形成了相互依存的关系，通过与顾客之间共同创造。企业可以更充分的理解顾客及其价值的变化，顾客能够根据自己的观点和需求，来知道企业为他们创造价值，从而达成资源的合理有效利用。</p>
		</div>
		<div class="cxBoxR fr">
			<h3>中脉、道和期待与您在以下领域携手共进，一起为您和您的家庭以及社会、国家创造价值：</h3>

			<c:forEach items="${inwDemandSortList}" var="inwDemandsort">
			<div class="cooperation rel" style="width:579px;">
				<a class="cooperationImg fl"><img src="${FILE_URL }/${inwDemandsort.image }" alt="photos" width="147" height="111" /></a>
				<div class="cooperationCon fl" style="width:422px;">
					<h4>${inwDemandsort.name }</h4>
					<div class="cooperationText">${inwDemandsort.require_Introduction }</div>
					<a href="demandsortDetail.html?id=${inwDemandsort.id }" class="abs">了解详情</a>
				</div>
			</div>
			</c:forEach>



			
		</div>
	</div>
	
	<!--
    <c:forEach items="${inwDemandSortList}" var="inwDemandsort">

	<div class="cooperation fl rel">
        <a class="cooperationImg fl"><img src="${FILE_URL }/${inwDemandsort.image }" alt="photos" width="147" height="111" /></a>
        <div class="cooperationCon fl">
            <h3>${inwDemandsort.name }</h3>
            <div class="cooperationText">${inwDemandsort.requireIntroduction }这段文字不要超过 100 个</div>
            <a href="inwSuggestionformNew.html?demandId=${inwDemand.id }&demandsortId=${inwDemand.demandsortId}" class="abs">了解详情</a>
        </div>
    </div>
	
	
	
    </c:forEach>
	-->
</body>
