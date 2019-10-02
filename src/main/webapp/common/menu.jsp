<%@ include file="/common/taglibs.jsp"%>

<menu:useMenuDisplayer name="Velocity" config="navbarMenu.vm" permissions="rolesAdapter">
<div class="nav-collapse collapse">
<ul class="nav">
    <c:if test="${empty pageContext.request.remoteUser}">
        <li class="active">
            <a href="<c:url value="/login"/>"><fmt:message key="login.title"/></a>
        </li>

    </c:if>
	
	
	<%
for(MenuModel mm:menus){
	List<MenuModel> subMenus = mm.getSubMenus();
	 String classStyle = "";
        if(mm.getName().equals(currentSubMenuId)){
        	classStyle = " class=\'active\'";
        }
%>
<%if(mm.getName().equals(currentMenuId)&&subMenus.size()==0&&request.getParameter("currentMenuId")!=null) {
%>
<li class='nav_active'>
<% 	
}else{
	
%>
	<li>
    <menu:displayMenu name="MainMenu"/>
    <menu:displayMenu name="UserMenu"/>
    <menu:displayMenu name="AdminMenu"/>
    <menu:displayMenu name="Logout"/>
    
    
    
    
    <!--JsysUser-START-->
    <menu:displayMenu name="JsysUserMenu"/>
    <!--JsysUser-END-->
    <!--JsysRole-START-->
    <menu:displayMenu name="JsysRoleMenu"/>
    <!--JsysRole-END-->
    <!--JsysResource-START-->
    <menu:displayMenu name="JsysResourceMenu"/>
    <!--JsysResource-END-->
    <!--JalCharacterKey-START-->
    <menu:displayMenu name="JalCharacterKeyMenu"/>
    <!--JalCharacterKey-END-->
    <!--JalCharacterCoding-START-->
    <menu:displayMenu name="JalCharacterCodingMenu"/>
    <!--JalCharacterCoding-END-->
    <!--JalCharacterValue-START-->
    <menu:displayMenu name="JalCharacterValueMenu"/>
    <!--JalCharacterValue-END-->


    <!--JpoMemberOrder-START-->
    <menu:displayMenu name="JpoMemberOrderMenu"/>
    <!--JpoMemberOrder-END-->
    <!--JpoShoppingCartOrder-START-->
    <menu:displayMenu name="JpoShoppingCartOrderMenu"/>
    <!--JpoShoppingCartOrder-END-->
    <!--JpoShoppingCartOrderList-START-->
    <menu:displayMenu name="JpoShoppingCartOrderListMenu"/>
    <!--JpoShoppingCartOrderList-END-->



    <!--JfiBankbookBalance-START-->
    <menu:displayMenu name="JfiBankbookBalanceMenu"/>
    <!--JfiBankbookBalance-END-->
    <!--JfiBankbookTemp-START-->
    <menu:displayMenu name="JfiBankbookTempMenu"/>
    <!--JfiBankbookTemp-END-->
    <!--JfiBankbookJournal-START-->
    <menu:displayMenu name="JfiBankbookJournalMenu"/>
    <!--JfiBankbookJournal-END-->
    <!--FiTransferAccount-START-->
    <menu:displayMenu name="FiTransferAccountMenu"/>
    <!--FiTransferAccount-END-->
    <!--JalCountry-START-->
    <menu:displayMenu name="JalCountryMenu"/>
    <!--JalCountry-END-->
    


    <!--AmNew-START-->
    <menu:displayMenu name="AmNewMenu"/>
    <!--AmNew-END-->
    
    <!--JpmProductSaleImage-START-->
    <menu:displayMenu name="JpmProductSaleImageMenu"/>
    <!--JpmProductSaleImage-END-->
    <!--JpmProductSaleNew-START-->
    <menu:displayMenu name="JpmProductSaleNewMenu"/>
    <!--JpmProductSaleNew-END-->
    <!--JpmProductSaleRelated-START-->
    <menu:displayMenu name="JpmProductSaleRelatedMenu"/>
    <!--JpmProductSaleRelated-END-->
    <!--JpmProductSaleTeamType-START-->
    <menu:displayMenu name="JpmProductSaleTeamTypeMenu"/>
    <!--JpmProductSaleTeamType-END-->
     <!--AmMessage-START-->
    <menu:displayMenu name="AmMessageMenu"/>
    <!--AmMessage-END-->


    <!--JsysId-START-->
    <menu:displayMenu name="JsysIdMenu"/>
    <!--JsysId-END-->

    <!--Jfi99billLog-START-->
    <menu:displayMenu name="Jfi99billLogMenu"/>
    <!--Jfi99billLog-END-->
    <!--FiBankbookTemp-START-->
    <menu:displayMenu name="FiBankbookTempMenu"/>
    <!--FiBankbookTemp-END-->
    <!--FiBankbookJournal-START-->
    <menu:displayMenu name="FiBankbookJournalMenu"/>
    <!--FiBankbookJournal-END-->
    <!--FiBankbookBalance-START-->
    <menu:displayMenu name="FiBankbookBalanceMenu"/>
    <!--FiBankbookBalance-END-->
    <!--FiBcoinJournal-START-->
    <menu:displayMenu name="FiBcoinJournalMenu"/>
    <!--FiBcoinJournal-END-->
    <!--FiBcoinBalance-START-->
    <menu:displayMenu name="FiBcoinBalanceMenu"/>
    <!--FiBcoinBalance-END-->

    <!--ScheduleManage-START-->
    <menu:displayMenu name="ScheduleManageMenu"/>
    <!--ScheduleManage-END-->
    <!--PublicSchedule-START-->
    <menu:displayMenu name="PublicScheduleMenu"/>
    <!--PublicSchedule-END-->
    <!--InwDemand-START-->
    <menu:displayMenu name="InwDemandMenu"/>
    <!--InwDemand-END-->
    <!--InwSuggestion-START-->
    <menu:displayMenu name="InwSuggestionMenu"/>
    <!--InwSuggestion-END-->
    <!--JalLibraryColumn-START-->
    <menu:displayMenu name="JalLibraryColumnMenu"/>
    <!--JalLibraryColumn-END-->
    <!--JalLibraryPlate-START-->
    <menu:displayMenu name="JalLibraryPlateMenu"/>
    <!--JalLibraryPlate-END-->
    <!--JalLibraryFile-START-->
    <menu:displayMenu name="JalLibraryFileMenu"/>
    <!--JalLibraryFile-END-->
    <!--FiCoinLog-START-->
    <menu:displayMenu name="FiCoinLogMenu"/>
    <!--FiCoinLog-END-->
    <!--JpmSalePromoter-START-->
    <menu:displayMenu name="JpmSalePromoterMenu"/>
    <!--JpmSalePromoter-END-->
    <!--JbdTravelPoint2014-START-->
    <menu:displayMenu name="JbdTravelPoint2014Menu"/>
    <!--JbdTravelPoint2014-END-->
    <!--FoundationItem-START-->
    <menu:displayMenu name="FoundationItemMenu"/>
    <!--FoundationItem-END-->
    <!--FoundationOrder-START-->
    <menu:displayMenu name="FoundationOrderMenu"/>
    <!--FoundationOrder-END-->
    <!--FiLovecoinBalance-START-->
    <menu:displayMenu name="FiLovecoinBalanceMenu"/>
    <!--FiLovecoinBalance-END-->
    <!--FiLovecoinJournal-START-->
    <menu:displayMenu name="FiLovecoinJournalMenu"/>
    <!--FiLovecoinJournal-END-->
    
    <!--JpmProductWineTemplateSub-START-->
    <menu:displayMenu name="JpmProductWineTemplateSubMenu"/>
    <!--JpmProductWineTemplateSub-END-->
    
    
    <!--JpmProductWineTemplate-START-->
    <menu:displayMenu name="JpmProductWineTemplateMenu"/>
    <!--JpmProductWineTemplate-END-->
    <!--JpmWineTemplatePicture-START-->
    <menu:displayMenu name="JpmWineTemplatePictureMenu"/>
    <!--JpmWineTemplatePicture-END-->

    <!--JfiPosImport-START-->
    <menu:displayMenu name="JfiPosImportMenu"/>
    <!--JfiPosImport-END-->
    <!--JpmConfigSpecDetailed-START-->
    <menu:displayMenu name="JpmConfigSpecDetailedMenu"/>
    <!--JpmConfigSpecDetailed-END-->
    <!--JpmWineOrderInterface-START-->
    <menu:displayMenu name="JpmWineOrderInterfaceMenu"/>
    <!--JpmWineOrderInterface-END-->
    <!--JpmWineSettingInf-START-->
    <menu:displayMenu name="JpmWineSettingInfMenu"/>
    <!--JpmWineSettingInf-END-->
    <!--JpmSendConsignment-START-->
    <menu:displayMenu name="JpmSendConsignmentMenu"/>
    <!--JpmSendConsignment-END-->
    <!--FiBillAccount-START-->
    <menu:displayMenu name="FiBillAccountMenu"/>
    <!--FiBillAccount-END-->
    <!--FiBillAccountRelation-START-->
    <menu:displayMenu name="FiBillAccountRelationMenu"/>
    <!--FiBillAccountRelation-END-->
    <!--FiGetbillaccountLog-START-->
    <menu:displayMenu name="FiGetbillaccountLogMenu"/>
    <!--FiGetbillaccountLog-END-->
    <!--FiCommonAddr-START-->
    <menu:displayMenu name="FiCommonAddrMenu"/>
    <!--FiCommonAddr-END-->
    <!--FiFundbookBalance-START-->
    <menu:displayMenu name="FiFundbookBalanceMenu"/>
    <!--FiFundbookBalance-END-->
    <!--FiFundbookJournal-START-->
    <menu:displayMenu name="FiFundbookJournalMenu"/>
    <!--FiFundbookJournal-END-->
    <!--FiFundbookTemp-START-->
    <menu:displayMenu name="FiFundbookTempMenu"/>
    <!--FiFundbookTemp-END-->
    <!--FiTransferFundbook-START-->
    <menu:displayMenu name="FiTransferFundbookMenu"/>
    <!--FiTransferFundbook-END-->
    <!--InwIntegrationTotal-START-->
    <menu:displayMenu name="InwIntegrationTotalMenu"/>
    <!--InwIntegrationTotal-END-->
    <!--PdShUrl-START-->
    <menu:displayMenu name="PdShUrlMenu"/>
    <!--PdShUrl-END-->
    <!--JmiValidLevelList-START-->
    <menu:displayMenu name="JmiValidLevelListMenu"/>
    <!--JmiValidLevelList-END-->
    <!--JmiLevelLock-START-->
    <menu:displayMenu name="JmiLevelLockMenu"/>
    <!--JmiLevelLock-END-->
    <!--FiMovieOrder-START-->
    <menu:displayMenu name="FiMovieOrderMenu"/>
    <!--FiMovieOrder-END-->
    <!--JpoCheckedFailed-START-->
    <menu:displayMenu name="JpoCheckedFailedMenu"/>
    <!--JpoCheckedFailed-END-->
    <!--JmiPrizeTourism-START-->
    <menu:displayMenu name="JmiPrizeTourismMenu"/>
    <!--JmiPrizeTourism-END-->
    <!--JmiMemberLog-START-->
    <menu:displayMenu name="JmiMemberLogMenu"/>
    <!--JmiMemberLog-END-->
	    <!--JbdTravelPointAll-START-->
    <menu:displayMenu name="JbdTravelPointAllMenu"/>
    <!--JbdTravelPointAll-END-->

    <!--JmiUserInvestigation-START-->
    <menu:displayMenu name="JmiUserInvestigationMenu"/>
    <!--JmiUserInvestigation-END-->
</ul>
</div>
</menu:useMenuDisplayer>
