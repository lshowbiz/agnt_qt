<%@ page contentType = "text/html; charset=utf-8" language = "java"%>
<%@ include file="/common/taglibs.jsp"%>




<body>



<div class="cont mt mr">
    	<div class="bt mt">
			<h3 class="color2 ml">奖金扣补查询</h3>
		</div>

<div class="mt">
        <table class="prod mt" border="1">
                <tbody class="list_tbody_header">
                    <tr>
                         <td><ng:locale key ="prRefund.createTime"/></td>
                     <td><ng:locale key ="miMember.memberNo"/></td>
                     <td><ng:locale key ="bdReconsumMoneyReport.typeCH"/></td>
                     <td><ng:locale key ="bdBounsDeduct.wweek"/></td>
                     <td><ng:locale key ="bdBounsDeduct.money"/></td>
                     <td><ng:locale key ="bdBounsDeduct.remainMoney"/></td>
                     <td><ng:locale key ="bdBounsDeduct.deductMoney"/></td>
                     <td><ng:locale key ="bdBounsDeduct.curWwekkRemainMoney"/></td>
                     <td><ng:locale key ="bdBounsDeduct.status"/></td>
                    </tr>
                 </tbody>
                <tbody class="list_tbody_row">
             <c:forEach items="${jbdBounsDeducts}" var="bdBounsDeduct" >
          
                       
                       <tr class="bg-c gry3">
                       <td><fmt:formatDate value="${bdBounsDeduct.createTime }" pattern="yyyy-MM-dd"/>
					</td>
                       <td>${bdBounsDeduct.userCode}</td>
                       <td>
                       		<ng:code listCode="bdbounsdeduct.item" value="${bdBounsDeduct.type}"/>
                       </td>
                       <td>
                          <ng:weekFormat week="${bdBounsDeduct.wweek}" weekType="w" />
                       </td>
                       <td><fmt:formatNumber value="${bdBounsDeduct.money}" type="number" pattern="###,###,##0.00"/></td>
                       <td>
                            <fmt:formatNumber value="${bdBounsDeduct.remainMoney}" type="number" pattern="###,###,##0.00"/>

                       </td>
                       <td>
                             <fmt:formatNumber value="${bdBounsDeduct.deductMoney}" type="number" pattern="###,###,##0.00"/>
                       </td>
                       <td >
                       <fmt:formatNumber value="${bdBounsDeduct.remainMoney-bdBounsDeduct.deductMoney }" type="number" pattern="###,###,##0.00"/>
    				
                       </td>
                       <td><ng:code listCode="bdbounsdeduct.status" value="${bdBounsDeduct.status}"/></td>
  
                    </tr>
                       
                 </c:forEach>
                </tbody>
        </table>
        </div>
	${page.pageInfo }

</div>





	<%-- <div class="main">
        <h2 class="title mgb20"></h2>

        <div id="loading">
            <table width="100%" class="tabQueryLs" id="jbdMemberLinkCalcHistT" style="margin-top:2px;">
                <thead>
                 <tr>
                     <th><ng:locale key ="prRefund.createTime"/></th>
                     <th><ng:locale key ="miMember.memberNo"/></th>
                     <th><ng:locale key ="bdReconsumMoneyReport.typeCH"/></th>
                     <th><ng:locale key ="bdBounsDeduct.wweek"/></th>
                     <th><ng:locale key ="bdBounsDeduct.money"/></th>
                     <th><ng:locale key ="bdBounsDeduct.remainMoney"/></th>
                     <th><ng:locale key ="bdBounsDeduct.deductMoney"/></th>
                     <th><ng:locale key ="bdBounsDeduct.curWwekkRemainMoney"/></th>
                     <th><ng:locale key ="bdBounsDeduct.status"/></th>
                 </tr>
             </thead>
                <tbody>
                   
             </c:forEach>
            </tbody>
            </table>
        </div>
    </div> --%>
    <script>
    $(function(){
        $('.tabQueryLs tbody').find('tr:odd > td').css('background-color','#E4EBFF');
    });
    </script>
</body>


