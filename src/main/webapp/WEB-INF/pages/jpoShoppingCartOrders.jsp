<%@ page language="java"  contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
	<div class="cont">		
		<div class="bg-c" style="margin:10px 20px 0 10px;">
			<c:choose>
				<%-- <!-- 360年费单 -->
				<c:when test="${member.memberLevel == 0 ||member.memberLevel=500}">
	       			<security:authorize url="/app/jpoMemberOrder360.html">
						<c:if test="${fn:length(recommendProductList360)>=0}">
							<div class="ord">

								<div class="ord_title">
									<h2>
										<span class="glyphicon glyphicon-tags ft18 colorCS">&nbsp;</span>
										<span class="color2  ft18" >360年费单</span>
										<span ><a  class="fr ft14 mt" href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=36">更多商品>></a></span>
									</h2>
								</div>

								<table class="table-bordered" width="100%">
									<tbody>

									<tr>
										<c:forEach items="${recommendProductList360}" var="productCXD" begin="0" varStatus="stat">

											<td <c:if test="${stat.last&&(fn:length(recommendProductList360)<6)}" > width="${(6-fn:length(recommendProductList360)+1)*(100/6)}%" </c:if> >

												<dl class="rel mt" style="${(stat.last&&(fn:length(recommendProductList360)<6))?'max-width: 180px;':''}">
													<dt class="tc">
														<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productCXD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=36&pptId=${productCXD.pttId }" class="pdLink">
															<img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${productCXD.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}" width="158" height="110" alt="${productCXD.jpmProductSaleNew.productName}" title="${productCXD.jpmProductSaleNew.productName}" />
														</a>
													</dt>
													<dd class="ml mr">
														<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productCXD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=16&pptId=${productCXD.pttId}" title="${productCXD.jpmProductSaleNew.productName}">
															<font color="#33333" size="2">
																<c:choose>
																	<c:when test="${fns:isAbbreviate(productCXD.jpmProductSaleNew.productName, 14)}">
																		<p align="center" class="mt"><font color="#33333" size="2">${fns:abbreviate(productCXD.jpmProductSaleNew.productName, 14,'...')}</font></p>
																	</c:when>
																	<c:otherwise>
																		<p align="center" class="mt"><font color="#33333" size="2">${productCXD.jpmProductSaleNew.productName}</font></p>
																	</c:otherwise>
																</c:choose>
															</font>
														</a>
													</dd>
													<dd class="ml mr red">
														<span></span>
														<span></span>
													</dd>
												</dl>

											</td>
										</c:forEach>
									</tr>

									</tbody>
								</table>

							</div>
						</c:if>
					</security:authorize>
					</c:when>
	       			<!--360  -->  --%>
				<c:when test="${member.freezeStatus == 1 }">
					<security:authorize url="/app/jpoMemberOrderRS.html">
						<div class="ord"> 
							<div class="ord_title">
								<h2>
								<span class="glyphicon glyphicon-tags ft18 colorCS">&nbsp;</span>
								<span class="color2  ft18" >会员续约单</span>
								<span><a  class=" fr ft14 mt" href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=3">更多商品>></a></span>				       		
								</h2>				       		
							</div>

							<table class="table-bordered" width="100%">							
								<tbody>
								
									<tr>
										<c:forEach items="${recommendProductListXYD}" var="productXYD" varStatus="stat" begin="0">
										<td <c:if test="${stat.last&&(fn:length(recommendProductListXYD)<6)}" > width="${(6-fn:length(recommendProductListXYD)+1)*(100/6)}%" </c:if> >
										
											<dl class="rel mt" style="${(stat.last&&(fn:length(recommendProductListXYD)<6))?'max-width: 180px;':''}">		
												<dt class="tc">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productXYD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=3&pptId=${productXYD.pttId }" class="pdLink">
														 <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${productXYD.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}" width="158" height="110" alt="${productXYD.jpmProductSaleNew.productName}" title="${productXYD.jpmProductSaleNew.productName}" />
													</a>
												 </dt>
												<dd class="ml mr">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productXYD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=3&pptId=${productXYD.pttId}" title="${productXYD.jpmProductSaleNew.productName}">
															<font color="#33333" size="2">
																<c:choose>
																	<c:when test="${fns:isAbbreviate(productXYD.jpmProductSaleNew.productName, 14)}">
																		<p align="center" class="mt"><font color="#33333" size="2">${fns:abbreviate(productXYD.jpmProductSaleNew.productName, 14,'...')}</font></p>
																	</c:when>
																	<c:otherwise>
																	   <p align="center" class="mt"><font color="#33333" size="2">${productXYD.jpmProductSaleNew.productName}</font></p>
																	</c:otherwise>
																</c:choose>
															</font>
													 </a>
												</dd>
												<dd class="ml mr red">
														<span></span>
														<span></span>
											   </dd>										           							
											</dl>	
										
										</td>
										</c:forEach>
									</tr>
								
								</tbody>							
							</table>
							                           
						</div>
					</security:authorize>
				</c:when>
			<%-- 	<c:when test="${member.freezeStatus == 0 && member.notFirst == 0 && noSGD!=1}"> --%>
					<c:when test="${fn:length(recommendProductListSGD)>0}">
					<security:authorize url="/app/jpoMemberOrderF.html">
	        		    <div class="ord"> 
							<div class="ord_title">
								<h2>
								<span class="glyphicon glyphicon-tags ft18 colorCS">&nbsp;</span>
								<span class="color2  ft18" >会员首购单</span>
								<span ><a  class="fr ft14 mt" href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=1">更多商品>></a></span>				       		
								</h2>				       		
							</div>

							<table class="table-bordered" width="100%">							
								<tbody>
								
									<tr>
										<c:forEach items="${recommendProductListSGD}" var="productSGD" varStatus="stat" begin="0">
										<td <c:if test="${stat.last&&(fn:length(recommendProductListSGD)<6)}" > width="${(6-fn:length(recommendProductListSGD)+1)*(100/6)}%" </c:if> >
										
											<dl class="rel mt" style="${(stat.last&&(fn:length(recommendProductListSGD)<6))?'max-width: 180px;':''}">		
												<dt class="tc">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productSGD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=1&pptId=${productSGD.pttId }" class="pdLink">
														 <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${productSGD.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}" width="158" height="110" alt="${productSGD.jpmProductSaleNew.productName}" title="${productSGD.jpmProductSaleNew.productName}" />
													</a>
												 </dt>
												<dd class="ml mr">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productSGD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=1&pptId=${productSGD.pttId}" title="${productSGD.jpmProductSaleNew.productName}">
															<font color="#33333" size="2">
																<c:choose>
																	<c:when test="${fns:isAbbreviate(productSGD.jpmProductSaleNew.productName, 14)}">
																		<p align="center" class="mt"><font color="#33333" size="2">${fns:abbreviate(productSGD.jpmProductSaleNew.productName, 14,'...')}</font></p>
																	</c:when>
																	<c:otherwise>
																	   <p align="center" class="mt"><font color="#33333" size="2">${productSGD.jpmProductSaleNew.productName}</font></p>
																	</c:otherwise>
																</c:choose>
															</font>
													 </a>
												</dd>
												<dd class="ml mr red">
														<span></span>
														<span></span>
											   </dd>										           							
											   </dl>	
										
										</td>
										</c:forEach>
									</tr>
								
								</tbody>							
							</table>                          
						</div>
	        		</security:authorize>
	       		</c:when>
					
	       		
	       		<c:when test="${member.freezeStatus == 0 && member.notFirst == 1}">
	       			<c:if test="${upGrade != '0' }">
	       				<security:authorize url="/app/jpoMemberOrderU.html">
	        		    <div class="ord"> 
							<div class="ord_title">
								<h2>
								<span class="glyphicon glyphicon-tags ft18 colorCS">&nbsp;</span>
								<span class="color2  ft18" >会员升级单</span>
								<span ><a  class="fr ft14 mt" href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=2">更多商品>></a></span>				       		
								</h2>				       		
							</div>

							<table class="table-bordered" width="100%">							
								<tbody>
								
									<tr>
										<c:forEach items="${recommendProductListSJD}" var="productSGD" varStatus="stat" begin="0">
										<td <c:if test="${stat.last&&(fn:length(recommendProductListSJD)<6)}" > width="${(6-fn:length(recommendProductListSJD)+1)*(100/6)}%" </c:if> >
										
											<dl class="rel mt" style="${(stat.last&&(fn:length(recommendProductListSJD)<6))?'max-width: 180px;':''}">		
												<dt class="tc">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productSGD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=2&pptId=${productSGD.pttId }" class="pdLink">
														 <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${productSGD.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}" width="158" height="110" alt="${productSGD.jpmProductSaleNew.productName}" title="${productSGD.jpmProductSaleNew.productName}" />
													</a>
												 </dt>
												<dd class="ml mr">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productSGD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=2&pptId=${productSGD.pttId}" title="${productSGD.jpmProductSaleNew.productName}">
															<font color="#33333" size="2">
																<c:choose>
																	<c:when test="${fns:isAbbreviate(productSGD.jpmProductSaleNew.productName, 14)}">
																		<p align="center" class="mt"><font color="#33333" size="2">${fns:abbreviate(productSGD.jpmProductSaleNew.productName, 14,'...')}</font></p>
																	</c:when>
																	<c:otherwise>
																	   <p align="center" class="mt"><font color="#33333" size="2">${productSGD.jpmProductSaleNew.productName}</font></p>
																	</c:otherwise>
																</c:choose>
															</font>
													 </a>
												</dd>
												<dd class="ml mr red">
														<span></span>
														<span></span>
											   </dd>										           							
											   </dl>	
										
										</td>
										</c:forEach>
									</tr>
								
								</tbody>							
							</table>                          
						</div>
	        		</security:authorize>
	       			</c:if>
	       			
					<security:authorize url="/app/jpoMemberOrderP.html">
						<c:if test="${fn:length(recommendProductListP)>0}">
							<div class="ord">

								<div class="ord_title">
									<h2>
										<span class="glyphicon glyphicon-tags ft18 colorCS">&nbsp;</span>
										<span class="color2  ft18" >抵用券换购单</span>
										<span ><a  class="fr ft14 mt" href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=16">更多商品>></a></span>
									</h2>
								</div>

								<table class="table-bordered" width="100%">
									<tbody>

									<tr>
										<c:forEach items="${recommendProductListP}" var="productCXD" begin="0" varStatus="stat">

											<td <c:if test="${stat.last&&(fn:length(recommendProductListP)<6)}" > width="${(6-fn:length(recommendProductListP)+1)*(100/6)}%" </c:if> >

												<dl class="rel mt" style="${(stat.last&&(fn:length(recommendProductListP)<6))?'max-width: 180px;':''}">
													<dt class="tc">
														<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productCXD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=16&pptId=${productCXD.pttId }" class="pdLink">
															<img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${productCXD.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}" width="158" height="110" alt="${productCXD.jpmProductSaleNew.productName}" title="${productCXD.jpmProductSaleNew.productName}" />
														</a>
													</dt>
													<dd class="ml mr">
														<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productCXD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=16&pptId=${productCXD.pttId}" title="${productCXD.jpmProductSaleNew.productName}">
															<font color="#33333" size="2">
																<c:choose>
																	<c:when test="${fns:isAbbreviate(productCXD.jpmProductSaleNew.productName, 14)}">
																		<p align="center" class="mt"><font color="#33333" size="2">${fns:abbreviate(productCXD.jpmProductSaleNew.productName, 14,'...')}</font></p>
																	</c:when>
																	<c:otherwise>
																		<p align="center" class="mt"><font color="#33333" size="2">${productCXD.jpmProductSaleNew.productName}</font></p>
																	</c:otherwise>
																</c:choose>
															</font>
														</a>
													</dd>
													<dd class="ml mr red">
														<span></span>
														<span></span>
													</dd>
												</dl>

											</td>
										</c:forEach>
									</tr>

									</tbody>
								</table>

							</div>
						</c:if>
					</security:authorize>
					<security:authorize url="/app/jpoMemberOrderR.html">
						<div class="ord"> 
					  
							<div class="ord_title">
								<h2>
								<span class="glyphicon glyphicon-tags ft18 colorCS">&nbsp;</span>
								<span class="color2  ft18" >会员重消单</span>
								<span ><a  class="fr ft14 mt" href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=4">更多商品>></a></span>				       		
								</h2>				       		
							</div>	
							
							<table class="table-bordered" width="100%">							
								<tbody>
								
									<tr>
										<c:forEach items="${recommendProductListCXD}" var="productCXD" varStatus="stat" begin="0">
										 
											<td <c:if test="${stat.last&&(fn:length(recommendProductListCXD)<6)}" > width="${(6-fn:length(recommendProductListCXD)+1)*(100/6)}%" </c:if>  >
										
											<dl class="rel mt" style="${(stat.last&&(fn:length(recommendProductListCXD)<6))?'max-width: 180px;':''}">		
												<dt class="tc">
														<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productCXD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=4&pptId=${productCXD.pttId }" class="pdLink">
															 <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${productCXD.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}" width="158" height="110" alt="${productCXD.jpmProductSaleNew.productName}" title="${productCXD.jpmProductSaleNew.productName}" />
														</a>
												</dt>
												<dd class="ml mr">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productCXD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=4&pptId=${productCXD.pttId}" title="${productCXD.jpmProductSaleNew.productName}">
															<font color="#33333" size="2">
																<c:choose>
																	<c:when test="${fns:isAbbreviate(productCXD.jpmProductSaleNew.productName, 14)}">
																		<p align="center" class="mt"><font color="#33333" size="2">${fns:abbreviate(productCXD.jpmProductSaleNew.productName, 14,'...')}</font></p>
																	</c:when>
																	<c:otherwise>
																	   <p align="center" class="mt"><font color="#33333" size="2">${productCXD.jpmProductSaleNew.productName}</font></p>
																	</c:otherwise>
																</c:choose>
															</font>
													 </a>
												</dd>
												<dd class="ml mr red">
														<span></span>
														<span></span>
												</dd>										           							
											</dl>	
										
										</td>
										</c:forEach>
									</tr>
								
								</tbody>							
							</table>

						</div>   
				    </security:authorize>
				 
					<security:authorize url="/app/jpoMemberOrderA.html">
				
						<div class="ord"> 
							<div class="ord_title">
								<h2>
								<span class="glyphicon glyphicon-tag ft18 colorCS">&nbsp;</span>
								<span class="color2  ft18" >会员辅消单</span>
								<span ><a class=" fr ft14 mt" href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=5">更多商品>></a></span>				       		
								</h2>				       		
							</div>	
							
							<table class="table-bordered" width="100%">							
								<tbody>
								
									<tr>
										<c:forEach items="${recommendProductListFXD}" var="productFXD" varStatus="stat" begin="0">
										<td <c:if test="${stat.last&&(fn:length(recommendProductListFXD)<6)}" > width="${(6-fn:length(recommendProductListFXD)+1)*(100/6)}%" </c:if> >
										
											<dl class="rel mt" style="${(stat.last&&(fn:length(recommendProductListFXD)<6))?'max-width: 180px;':''}">		
												<dt class="tc">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productFXD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=5&pptId=${productFXD.pttId }" class="pdLink">
														 <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${productFXD.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}" width="158" height="110" alt="${productFXD.jpmProductSaleNew.productName}" title="${productFXD.jpmProductSaleNew.productName}" />
													</a>
												 </dt>
												<dd class="ml mr">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productCXD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=5&pptId=${productFXD.pttId}" title="${productFXD.jpmProductSaleNew.productName}">
															<font color="#33333" size="2">
																<c:choose>
																	<c:when test="${fns:isAbbreviate(productFXD.jpmProductSaleNew.productName, 14)}">
																		<p align="center" class="mt"><font color="#33333" size="2">${fns:abbreviate(productFXD.jpmProductSaleNew.productName, 14,'...')}</font></p>
																	</c:when>
																	<c:otherwise>
																	   <p align="center" class="mt"><font color="#33333" size="2">${productFXD.jpmProductSaleNew.productName}</font></p>
																	</c:otherwise>
																</c:choose>
															</font>
													 </a>
												</dd>
												<dd class="ml mr red">
														<span></span>
														<span></span>
											   </dd>										           							
											</dl>	
										
										</td>
										</c:forEach>
									</tr>
								
								</tbody>							
							</table>
                           
						</div>
					</security:authorize>
				 
					<security:authorize url="/app/jpoMemberOrderSSubF.html">
				        <div class="ord"> 
							<div class="ord_title">
								<h2>
								<span class="glyphicon glyphicon-tags ft18 colorCS">&nbsp;</span>
								<span class="color2  ft18" >二级店铺首购单</span>
								<span ><a  class="fr ft14 mt" href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=11">更多商品>></a></span>				       		
								</h2>				       		
							</div>

							<table class="table-bordered" width="100%">							
								<tbody>
								
									<tr>
										<c:forEach items="${recommendProductListEJDPSGD}" var="productEJDPSGD" varStatus="stat" begin="0">
										<td <c:if test="${stat.last&&(fn:length(recommendProductListEJDPSGD)<6)}" > width="${(6-fn:length(recommendProductListEJDPSGD)+1)*(100/6)}%" </c:if> >
										
											<dl class="rel mt" style="${(stat.last&&(fn:length(recommendProductListEJDPSGD)<6))?'max-width: 180px;':''}">		
												<dt class="tc">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productEJDPSGD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=11&pptId=${productEJDPSGD.pttId }" class="pdLink">
														 <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${productEJDPSGD.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}" width="158" height="110" alt="${productEJDPSGD.jpmProductSaleNew.productName}" title="${productEJDPSGD.jpmProductSaleNew.productName}" />
													</a>
												 </dt>
												<dd class="ml mr">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productEJDPSGD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=11&pptId=${productEJDPSGD.pttId}" title="${productEJDPSGD.jpmProductSaleNew.productName}">
															<font color="#33333" size="2">
																<c:choose>
																	<c:when test="${fns:isAbbreviate(productEJDPSGD.jpmProductSaleNew.productName, 14)}">
																		<p align="center" class="mt"><font color="#33333" size="2">${fns:abbreviate(productEJDPSGD.jpmProductSaleNew.productName, 14,'...')}</font></p>
																	</c:when>
																	<c:otherwise>
																	   <p align="center" class="mt"><font color="#33333" size="2">${productEJDPSGD.jpmProductSaleNew.productName}</font></p>
																	</c:otherwise>
																</c:choose>
															</font>
													 </a>
												</dd>
												<dd class="ml mr red">
														<span></span>
														<span></span>
											   </dd>										           							
										   </dl>	
										
										</td>
										</c:forEach>
									</tr>
								
								</tbody>							
							</table>
						                          
						</div>
				        
					</security:authorize>
				
					<security:authorize url="/app/jpoMemberOrderSSubU.html">
						<div class="ord"> 
							<div class="ord_title">
								<h2>
								<span class="glyphicon glyphicon-tags ft18 colorCS">&nbsp;</span>
								<span class="color2  ft18" >二级店铺升级单</span>
								<span ><a  class="fr ft14 mt" href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=12">更多商品>></a></span>				       		
								</h2>				       		
							</div>	
							
							
							<table class="table-bordered" width="100%">							
								<tbody>
								
									<tr>
										<c:forEach items="${recommendProductListEJDPSJD}" var="productEJDPSJD" varStatus="stat" begin="0">
										<td <c:if test="${stat.last&&(fn:length(recommendProductListEJDPSJD)<6)}" > width="${(6-fn:length(recommendProductListEJDPSJD)+1)*(100/6)}%" </c:if> >
										
											<dl class="rel mt" style="${(stat.last&&(fn:length(recommendProductListEJDPSJD)<6))?'max-width: 180px;':''}">		
												<dt class="tc">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productEJDPSJD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=12&pptId=${productEJDPSJD.pttId }" class="pdLink">
														 <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${productEJDPSJD.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}" width="158" height="110" alt="${productEJDPSJD.jpmProductSaleNew.productName}" title="${productEJDPSJD.jpmProductSaleNew.productName}" />
													</a>
												 </dt>
												<dd class="ml mr">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productEJDPSJD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=12&pptId=${productEJDPSJD.pttId}" title="${productEJDPSJD.jpmProductSaleNew.productName}">
															<font color="#33333" size="2">
																<c:choose>
																	<c:when test="${fns:isAbbreviate(productEJDPSJD.jpmProductSaleNew.productName, 14)}">
																		<p align="center" class="mt"><font color="#33333" size="2">${fns:abbreviate(productEJDPSJD.jpmProductSaleNew.productName, 14,'...')}</font></p>
																	</c:when>
																	<c:otherwise>
																	   <p align="center" class="mt"><font color="#33333" size="2">${productEJDPSJD.jpmProductSaleNew.productName}</font></p>
																	</c:otherwise>
																</c:choose>
															</font>
													 </a>
												</dd>
												<dd class="ml mr red">
														<span></span>
														<span></span>
											   </dd>										           							
											</dl>	
										
										</td>
										</c:forEach>
									</tr>
								
								</tbody>							
							</table>
							                        
						</div>
					</security:authorize>
				
					<security:authorize url="/app/jpoMemberOrderSSubR.html">
						<div class="ord"> 
							<div class="ord_title">
								<h2>
								<span class="glyphicon glyphicon-tags ft18 colorCS">&nbsp;</span>
								<span class="color2  ft18" >二级店铺重消单</span>
								<span ><a  class="fr ft14 mt" href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=14">更多商品>></a></span>				       		
								</h2>				       		
							</div>	
						
							<table class="table-bordered" width="100%">							
								<tbody>
								
									<tr>
										<c:forEach items="${recommendProductListEJDPCXD}" var="productEJDPCXD" varStatus="stat" begin="0">
										<td <c:if test="${stat.last&&(fn:length(recommendProductListEJDPCXD)<6)}" > width="${(6-fn:length(recommendProductListEJDPCXD)+1)*(100/6)}%" </c:if> >
										
											<dl class="rel mt" style="${(stat.last&&(fn:length(recommendProductListEJDPCXD)<6))?'max-width: 180px;':''}">		
												<dt class="tc">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productEJDPCXD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=14&pptId=${productEJDPCXD.pttId }" class="pdLink">
														 <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${productEJDPCXD.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}" width="158" height="110" alt="${productEJDPCXD.jpmProductSaleNew.productName}" title="${productEJDPCXD.jpmProductSaleNew.productName}" />
													</a>
												 </dt>
												<dd class="ml mr">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productEJDPCXD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=14&pptId=${productEJDPCXD.pttId}" title="${productEJDPCXD.jpmProductSaleNew.productName}">
															<font color="#33333" size="2">
																<c:choose>
																	<c:when test="${fns:isAbbreviate(productEJDPCXD.jpmProductSaleNew.productName, 14)}">
																		<p align="center" class="mt"><font color="#33333" size="2">${fns:abbreviate(productEJDPCXD.jpmProductSaleNew.productName, 14,'...')}</font></p>
																	</c:when>
																	<c:otherwise>
																	   <p align="center" class="mt"><font color="#33333" size="2">${productEJDPCXD.jpmProductSaleNew.productName}</font></p>
																	</c:otherwise>
																</c:choose>
															</font>
													 </a>
												</dd>
												<dd class="ml mr red">
														<span></span>
														<span></span>
											   </dd>										           							
										   </dl>	
										
										</td>
										</c:forEach>
									</tr>
								
								</tbody>							
							</table>
						                         
						</div>
					</security:authorize>
					<security:authorize url="/app/jpoMemberOrderSF.html">
						<div class="ord"> 
							<div class="ord_title">
								<h2>
								<span class="glyphicon glyphicon-tag ft18 colorCS">&nbsp;</span>
								<span class="color2  ft18" >一级店铺首购单</span>
								<span ><a  class="fr ft14 mt" href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=6">更多商品>></a></span>				       		
								</h2>				       		
							</div>	
						
							<table class="table-bordered" width="100%">							
								<tbody>
								
									<tr>
										<c:forEach items="${recommendProductListYJDPSGD}" var="productYJDPSGD" varStatus="stat" begin="0">
										<td <c:if test="${stat.last&&(fn:length(recommendProductListYJDPSGD)<6)}" > width="${(6-fn:length(recommendProductListYJDPSGD)+1)*(100/6)}%" </c:if> >
										
											<dl class="rel mt" style="${(stat.last&&(fn:length(recommendProductListYJDPSGD)<6))?'max-width: 180px;':''}">		
												<dt class="tc">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productYJDPSGD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=6&pptId=${productYJDPSGD.pttId }" class="pdLink">
														 <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${productYJDPSGD.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}" width="158" height="110" alt="${productYJDPSGD.jpmProductSaleNew.productName}" title="${productYJDPSGD.jpmProductSaleNew.productName}" />
													</a>
												 </dt>
												<dd class="ml mr">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productYJDPSGD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=6&pptId=${productYJDPSGD.pttId}" title="${productYJDPSGD.jpmProductSaleNew.productName}">
															<font color="#33333" size="2">
																<c:choose>
																	<c:when test="${fns:isAbbreviate(productYJDPSGD.jpmProductSaleNew.productName, 14)}">
																		<p align="center" class="mt"><font color="#33333" size="2">${fns:abbreviate(productYJDPSGD.jpmProductSaleNew.productName, 14,'...')}</font></p>
																	</c:when>
																	<c:otherwise>
																	   <p align="center" class="mt"><font color="#33333" size="2">${productYJDPSGD.jpmProductSaleNew.productName}</font></p>
																	</c:otherwise>
																</c:choose>
															</font>
													 </a>
												</dd>
												<dd class="ml mr red">
														<span></span>
														<span></span>
											   </dd>										           							
										   </dl>	
										
										</td>
										</c:forEach>
									</tr>
								
								</tbody>							
							</table>
                           
						</div>
					</security:authorize>
				
					<security:authorize url="/app/jpoMemberOrderSR.html">
						<div class="ord"> 
							<div class="ord_title">
								<h2>
								<span class="glyphicon glyphicon-tag ft18 colorCS">&nbsp;</span>
								<span class="color2  ft18" >一级店铺重消单</span>
								<span ><a  class="fr ft14 mt" href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=9">更多商品>></a></span>				       		
								</h2>				       		
							</div>	
						
							<table class="table-bordered" width="100%">							
								<tbody>
								
									<tr>
										<c:forEach items="${recommendProductListYJDPCXD}" var="productYJDPCXD" varStatus="stat" begin="0">
										<td <c:if test="${stat.last&&(fn:length(recommendProductListYJDPCXD)<6)}" > width="${(6-fn:length(recommendProductListYJDPCXD)+1)*(100/6)}%" </c:if> >
										
											<dl class="rel mt" style="${(stat.last&&(fn:length(recommendProductListYJDPCXD)<6))?'max-width: 180px;':''}">		
												<dt class="tc">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productYJDPCXD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=9&pptId=${productYJDPCXD.pttId }" class="pdLink">
														 <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${productYJDPCXD.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}" width="158" height="110" alt="${productYJDPCXD.jpmProductSaleNew.productName}" title="${productYJDPCXD.jpmProductSaleNew.productName}" />
													</a>
												 </dt>
												<dd class="ml mr">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productYJDPCXD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=9&pptId=${productYJDPCXD.pttId}" title="${productYJDPCXD.jpmProductSaleNew.productName}">
															<font color="#33333" size="2">
																<c:choose>
																	<c:when test="${fns:isAbbreviate(productYJDPCXD.jpmProductSaleNew.productName, 14)}">
																		<p align="center" class="mt"><font color="#33333" size="2">${fns:abbreviate(productYJDPCXD.jpmProductSaleNew.productName, 14,'...')}</font></p>
																	</c:when>
																	<c:otherwise>
																	   <p align="center" class="mt"><font color="#33333" size="2">${productYJDPCXD.jpmProductSaleNew.productName}</font></p>
																	</c:otherwise>
																</c:choose>
															</font>
													 </a>
												</dd>
												<dd class="ml mr red">
														<span></span>
														<span></span>
											   </dd>										           							
										   </dl>	
										
										</td>
										</c:forEach>
									</tr>
								
								</tbody>							
							</table>
							                          
						</div>
					</security:authorize>
				        
				   <security:authorize url="/app/jpoMemberOrderRYear.html">
						<div class="ord"> 
							<div class="ord_title">
								<h2>
								<span class="glyphicon glyphicon-tags ft18 colorCS">&nbsp;</span>
								<span class="color2  ft18" >全年重消单</span>
								<span ><a  class="fr ft14 mt" href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=40">更多商品>></a></span>				       		
								</h2>				       		
							</div>	
						
							<table class="table-bordered" width="100%">							
								<tbody>
								
									<tr>
										<c:forEach items="${recommendProductListQNCXD}" var="productQNCXD" varStatus="stat" begin="0">
										<td <c:if test="${stat.last&&(fn:length(recommendProductListQNCXD)<6)}" > width="${(6-fn:length(recommendProductListQNCXD)+1)*(100/6)}%" </c:if> >
										
											<dl class="rel mt" style="${(stat.last&&(fn:length(recommendProductListQNCXD)<6))?'max-width: 180px;':''}">		
												<dt class="tc">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productQNCXD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=40&pptId=${productQNCXD.pttId }" class="pdLink">
														 <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${productQNCXD.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}" width="158" height="110" alt="${productQNCXD.jpmProductSaleNew.productName}" title="${productQNCXD.jpmProductSaleNew.productName}" />
													</a>
												 </dt>
												<dd class="ml mr">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productQNCXD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=40&pptId=${productQNCXD.pttId}" title="${productQNCXD.jpmProductSaleNew.productName}">
															<font color="#33333" size="2">
																<c:choose>
																	<c:when test="${fns:isAbbreviate(productQNCXD.jpmProductSaleNew.productName, 14)}">
																		<p align="center" class="mt"><font color="#33333" size="2">${fns:abbreviate(productQNCXD.jpmProductSaleNew.productName, 14,'...')}</font></p>
																	</c:when>
																	<c:otherwise>
																	   <p align="center" class="mt"><font color="#33333" size="2">${productQNCXD.jpmProductSaleNew.productName}</font></p>
																	</c:otherwise>
																</c:choose>
															</font>
													 </a>
												</dd>
												<dd class="ml mr red">
														<span></span>
														<span></span>
											   </dd>										           							
										   </dl>	
										
										</td>
										</c:forEach>
									</tr>
								
								</tbody>							
							</table>
							                         
						</div>
					</security:authorize>
	       		</c:when>
	       		<c:otherwise>
	       		</c:otherwise>
			</c:choose>
			<!-- 360年费单 -->
			<security:authorize url="/app/recommendProductList41">
					<c:if test="${ydzgd == 1 && fn:length(recommendProductList41)>0}">
	        		    <div class="ord"> 
							<div class="ord_title">
								<h2>
								<span class="glyphicon glyphicon-tags ft18 colorCS">&nbsp;</span>
								<span class="color2  ft18" >360年费单</span>
								<span ><a  class="fr ft14 mt" href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=41">更多商品>></a></span>				       		
								</h2>				       		
							</div>

							<table class="table-bordered" width="100%">							
								<tbody>
								
									<tr>
										<c:forEach items="${recommendProductList41}" var="product41" begin="0" varStatus="stat">
										<td <c:if test="${stat.last&&(fn:length(recommendProductList41)<6)}" > width="${(6-fn:length(recommendProductList41)+1)*(100/6)}%" </c:if> >
										
											<dl class="rel mt" style="${(stat.last&&(fn:length(recommendProductList41)<6))?'max-width: 180px;':''}">		
												<dt class="tc">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${product41.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=41&pptId=${product41.pttId }" class="pdLink">
														 <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${product41.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}" width="158" height="110" alt="${product41.jpmProductSaleNew.productName}" title="${product41.jpmProductSaleNew.productName}" />
													</a>
												 </dt>
												<dd class="ml mr">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${product41.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=41&pptId=${product41.pttId}" title="${product41.jpmProductSaleNew.productName}">
															<font color="#33333" size="2">
																<c:choose>
																	<c:when test="${fns:isAbbreviate(product41.jpmProductSaleNew.productName, 14)}">
																		<p align="center" class="mt"><font color="#33333" size="2">${fns:abbreviate(product41.jpmProductSaleNew.productName, 14,'...')}</font></p>
																	</c:when>
																	<c:otherwise>
																	   <p align="center" class="mt"><font color="#33333" size="2">${product41.jpmProductSaleNew.productName}</font></p>
																	</c:otherwise>
																</c:choose>
															</font>
													 </a>
												</dd>
												<dd class="ml mr red">
														<span></span>
														<span></span>
											   </dd>										           							
											   </dl>	
										
										</td>
										</c:forEach>
									</tr>
								
								</tbody>							
							</table>                          
						</div>
						</c:if>
	        		</security:authorize>
	        		
	        			<security:authorize url="/app/jpoMemberOrderDJQ.html">
						<c:if test="${fn:length(recommendProductListDJQ)>0}">
	        		    <div class="ord"> 
							<div class="ord_title">
								<h2>
								<span class="glyphicon glyphicon-tags ft18 colorCS">&nbsp;</span>
								<span class="color2  ft18" >代金券换购单</span>
								<span ><a  class="fr ft14 mt" href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=93">更多商品>></a></span>				       		
								</h2>				       		
							</div>

							<table class="table-bordered" width="100%">							
								<tbody>
								
									<tr>
										<c:forEach items="${recommendProductListDJQ}" var="productDJQ" begin="0" varStatus="stat">
										<td <c:if test="${stat.last&&(fn:length(recommendProductListDJQ)<6)}" > width="${(6-fn:length(recommendProductListDJQ)+1)*(100/6)}%" </c:if> >
										
											<dl class="rel mt" style="${(stat.last&&(fn:length(recommendProductListDJQ)<6))?'max-width: 180px;':''}">		
												<dt class="tc">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productDJQ.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=93&pptId=${productDJQ.pttId }" class="pdLink">
														 <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${productDJQ.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}" width="158" height="110" alt="${productQQUBBD.jpmProductSaleNew.productName}" title="${productDJQ.jpmProductSaleNew.productName}" />
													</a>
												 </dt>
												<dd class="ml mr">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productDJQ.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=93&pptId=${productDJQ.pttId}" title="${productDJQ.jpmProductSaleNew.productName}">
															<font color="#33333" size="2">
																<c:choose>
																	<c:when test="${fns:isAbbreviate(productDJQ.jpmProductSaleNew.productName, 14)}">
																		<p align="center" class="mt"><font color="#33333" size="2">${fns:abbreviate(productDJQ.jpmProductSaleNew.productName, 14,'...')}</font></p>
																	</c:when>
																	<c:otherwise>
																	   <p align="center" class="mt"><font color="#33333" size="2">${productDJQ.jpmProductSaleNew.productName}</font></p>
																	</c:otherwise>
																</c:choose>
															</font>
													 </a>
												</dd>
												<dd class="ml mr red">
														<span></span>
														<span></span>
											   </dd>										           							
											   </dl>	
										
										</td>
										</c:forEach>
									</tr>
								
								</tbody>							
							</table>                          
						</div>
						</c:if>
	        		</security:authorize>
	        		
	        		<!-- 顾问首单 -->
					 <security:authorize url="/app/jpoMemberOrderF398.html"> 
					 <c:if test="${fn:length(recommendProductListSGD398)>0}">
	        		    <div class="ord"> 
							<div class="ord_title">
								<h2>
								<span class="glyphicon glyphicon-tags ft18 colorCS">&nbsp;</span>
								<span class="color2  ft18" >顾问首单</span>
								<span ><a  class="fr ft14 mt" href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=42">更多商品>></a></span>				       		
								</h2>				       		
							</div>

							<table class="table-bordered" width="100%">							
								<tbody>
								
									<tr>
										<c:forEach items="${recommendProductListSGD398}" var="productSGD" varStatus="stat" begin="0">
										<td <c:if test="${stat.last&&(fn:length(recommendProductListSGD398)<6)}" > width="${(6-fn:length(recommendProductListSGD398)+1)*(100/6)}%" </c:if> >
										
											<dl class="rel mt" style="${(stat.last&&(fn:length(recommendProductListSGD398)<6))?'max-width: 180px;':''}">		
												<dt class="tc">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productSGD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=42&pptId=${productSGD.pttId }" class="pdLink">
														 <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${productSGD.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}" width="158" height="110" alt="${productSGD.jpmProductSaleNew.productName}" title="${productSGD.jpmProductSaleNew.productName}" />
													</a>
												 </dt>
												<dd class="ml mr">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productSGD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=42&pptId=${productSGD.pttId}" title="${productSGD.jpmProductSaleNew.productName}">
															<font color="#33333" size="2">
																<c:choose>
																	<c:when test="${fns:isAbbreviate(productSGD.jpmProductSaleNew.productName, 14)}">
																		<p align="center" class="mt"><font color="#33333" size="2">${fns:abbreviate(productSGD.jpmProductSaleNew.productName, 14,'...')}</font></p>
																	</c:when>
																	<c:otherwise>
																	   <p align="center" class="mt"><font color="#33333" size="2">${productSGD.jpmProductSaleNew.productName}</font></p>
																	</c:otherwise>
																</c:choose>
															</font>
													 </a>
												</dd>
												<dd class="ml mr red">
														<span></span>
														<span></span>
											   </dd>										           							
											   </dl>	
										
										</td>
										</c:forEach>
									</tr>
								
								</tbody>							
							</table>                          
						</div>
						</c:if>
	        		 </security:authorize> 
	        		<!-- 顾问重消单 -->
					 <security:authorize url="/app/jpoMemberOrderGC398.html"> 
					 <c:if test="${fn:length(recommendProductListGC398)>0}">
	        		    <div class="ord"> 
							<div class="ord_title">
								<h2>
								<span class="glyphicon glyphicon-tags ft18 colorCS">&nbsp;</span>
								<span class="color2  ft18" >顾问重消单</span>
								<span ><a  class="fr ft14 mt" href="${pageContext.request.contextPath}/jpoShoppingCartOrderform?orderType=43">更多商品>></a></span>				       		
								</h2>				       		
							</div>

							<table class="table-bordered" width="100%">							
								<tbody>
								
									<tr>
										<c:forEach items="${recommendProductListGC398}" var="productSGD" varStatus="stat" begin="0">
										<td <c:if test="${stat.last&&(fn:length(recommendProductListGC398)<6)}" > width="${(6-fn:length(recommendProductListGC398)+1)*(100/6)}%" </c:if> >
										
											<dl class="rel mt" style="${(stat.last&&(fn:length(recommendProductListGC398)<6))?'max-width: 180px;':''}">		
												<dt class="tc">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productSGD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=43&pptId=${productSGD.pttId }" class="pdLink">
														 <img src="<ng:code listCode="jpmproductsaleimage.url" value="1" />productNew/${productSGD.jpmProductSaleNew.jpmProductSaleImageList[1].imageLink}" width="158" height="110" alt="${productSGD.jpmProductSaleNew.productName}" title="${productSGD.jpmProductSaleNew.productName}" />
													</a>
												 </dt>
												<dd class="ml mr">
													<a href="${pageContext.request.contextPath}/jpoShoppingCartOrderView/cartView?uniNo=${productSGD.jpmProductSaleNew.uniNo}&teamCode=${teamType}&orderType=43&pptId=${productSGD.pttId}" title="${productSGD.jpmProductSaleNew.productName}">
															<font color="#33333" size="2">
																<c:choose>
																	<c:when test="${fns:isAbbreviate(productSGD.jpmProductSaleNew.productName, 14)}">
																		<p align="center" class="mt"><font color="#33333" size="2">${fns:abbreviate(productSGD.jpmProductSaleNew.productName, 14,'...')}</font></p>
																	</c:when>
																	<c:otherwise>
																	   <p align="center" class="mt"><font color="#33333" size="2">${productSGD.jpmProductSaleNew.productName}</font></p>
																	</c:otherwise>
																</c:choose>
															</font>
													 </a>
												</dd>
												<dd class="ml mr red">
														<span></span>
														<span></span>
											   </dd>										           							
											   </dl>	
										
										</td>
										</c:forEach>
									</tr>
								
								</tbody>							
							</table>                          
						</div>
						</c:if>
	        		 </security:authorize> 
	       		
			
</div>
</div>
</div>

<script>
function resizemg(n){

    var l = (r-180*n)/(n-1);
                
    $order.css({
        "width" : r + l,
        "margin-right" : l
    })
    .find("li")
    .css({
       "margin-right" : l 
    });
    
}
function sortorder(){
        
    $order = $(".order"),
    r = $order.removeAttr("style").width(),
    w = $(window).width();
    
    if ( w < 1300 ){      
        $order
        .removeAttr("style")
        .width(960)
        .find("li")
        .css({
            "margin-right" : 15
        });
    }

    if ( w >= 1300 && w < 1423 ) {
        resizemg(5);
    }
    
    if ( w >= 1423 && w < 1903 ) {
        resizemg(6);
    }
    
    if ( w >= 1903 ) {
        resizemg(7);
    }
    
    $(window).load(function(){
        sortorder();
    });

    $(window).resize(function(){
        sortorder();
    });    
}
</script>
