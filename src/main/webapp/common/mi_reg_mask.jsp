
<%@ page contentType="text/html; charset=utf-8" language="java" %><%@ include file="/common/taglibs.jsp" %>

<c:if test="${pageContext.request.servletPath == '/jmiMemberCreateform' }">


<div class="mask" id="mask"></div>
    <div class="popupReg shadow abs" id="popupReg" style="display:none;width: 650px;">
        <div class="popupTitle"><a href="javascript:void(0);" class="closeMe fr" id="closeMe"></a></div>
        <div class="pupupContent">
            <table width="150%" border="0" class="pupupRegTab">
                <tbody>
                    <tr>
                        <td colspan="2" class="colorXH bold">会员&nbsp;${mi_reg_success.userCode}&nbsp;注册成功!</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><ng:locale key="alCompany.regName"/>:</td>
                        <td>${mi_reg_success.companyCode }	</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><ng:locale key="miMember.miRecommendRef"/>:</td>
                        <td>${mi_reg_success.recommendNo}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><ng:locale key="bdSendRecord.cardType"/>:</td>
                        <td><ng:code listCode="bd.cardtype" value="${mi_reg_success.cardType}"/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><ng:locale key="miMember.memberNo"/>:</td>
                        <td>${mi_reg_success.userCode }</td>
                        <td>(<ng:locale key="operation.notice.MarkMemberNo"/> )</td>
                    </tr>
                    <tr>
                        <td><ng:locale key="memberCreate.tips"/>:</td>
                        <td colspan="2"><ng:locale key="memberCreate.tips.detail"/> </td>
                    </tr>
                    <tr>
                        <td>初始密码为:</td>
                        <td colspan="2">888888 </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td colspan="2">
                        
                       <button type="button"  class="btn btn-success ml" id="returnMe" >&nbsp;<span>返&nbsp;回</span>&nbsp;</button>	 
                        
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>

</c:if>

<c:if test="${pageContext.request.servletPath == '/jmiMemberCreateformML' }">

<div class="mask" id="mask"></div>
    <div class="popupReg shadow abs" id="popupReg" style="display:none;width: 650px;">
        <div class="popupTitle"><a href="javascript:void(0);" class="closeMe fr" id="closeMe"></a></div>
        <div class="pupupContent">
            <table width="150%" border="0" class="pupupRegTab">
                <tbody>
                    <tr>
                        <td colspan="2" class="colorXH bold">会员&nbsp;${mi_reg_success.userCode}&nbsp;注册成功!</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><ng:locale key="alCompany.regName"/>:</td>
                        <td>${mi_reg_success.companyCode }	</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><ng:locale key="miMember.miRecommendRef"/>:</td>
                        <td>${mi_reg_success.recommendNo}</td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><ng:locale key="bdSendRecord.cardType"/>:</td>
                        <td><ng:code listCode="bd.cardtype" value="${mi_reg_success.cardType}"/></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td><ng:locale key="miMember.memberNo"/>:</td>
                        <td>${mi_reg_success.userCode }</td>
                        <td>(<ng:locale key="operation.notice.MarkMemberNo"/> )</td>
                    </tr>
                    <tr>
                        <td><ng:locale key="memberCreate.tips"/>:</td>
                        <td colspan="2"><ng:locale key="memberCreate.tips.detail"/> </td>
                    </tr>
                    <tr>
                        <td>初始密码为:</td>
                        <td colspan="2">888888 </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td colspan="2">
                        
                       <button type="button"  class="btn btn-success ml" id="returnMe" >&nbsp;<span>返&nbsp;回</span>&nbsp;</button>	 
                        
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</c:if>