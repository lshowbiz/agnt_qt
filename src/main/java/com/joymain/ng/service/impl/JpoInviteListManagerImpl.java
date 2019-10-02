package com.joymain.ng.service.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JpoInviteListDao;
import com.joymain.ng.model.JpoInviteList;
import com.joymain.ng.model.JpoMemberOrder;
import com.joymain.ng.model.JpoMemberOrderList;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JpoInviteListManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.data.CommonRecord;
import com.joymain.ng.service.JpoMemberOrderManager;
@Service("jpoInviteListManager")
@WebService(serviceName = "JpoInviteListService", endpointInterface = "com.joymain.ng.service.JpoInviteListManager")
public class JpoInviteListManagerImpl extends GenericManagerImpl<JpoInviteList, Long> implements JpoInviteListManager {
    JpoInviteListDao jpoInviteListDao;
    @Autowired
	private JpoMemberOrderManager jpoMemberOrderManager;
    @Autowired
    public JpoInviteListManagerImpl(JpoInviteListDao jpoInviteListDao) {
        super(jpoInviteListDao);
        this.jpoInviteListDao = jpoInviteListDao;
    }
	
	public Pager<JpoInviteList> getPager(Collection<SearchBean> searchBeans,
										 Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jpoInviteListDao.getPager(JpoInviteList.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	@Override
	public List getJpoInviteListPage(GroupPage page, CommonRecord crm) {
		// TODO Auto-generated method stub
		return jpoInviteListDao.getJpoInviteListPage(page,crm);
	}

	/**
	 * @author fu 2017-5-26
	 * 手机端我的邀请码查询接口
	 */
	public List getJpoInviteList(Map<String, String> params, GroupPage page) {
		return jpoInviteListDao.getJpoInviteList(params, page);
	}

	

	public boolean useJpoInviteList(String inviteCode,String moId){
    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		JpoInviteList jpoInviteList=jpoInviteListDao.getJpoInviteCode(inviteCode);
		if(StringUtil.isEmpty(moId)){
			return false;
		}
		JpoMemberOrder jpoMemberOrder=jpoMemberOrderManager.get(StringUtil.formatLong(moId));
		if(jpoMemberOrder==null){
			return false;
		}else if(!"41".equals(jpoMemberOrder.getOrderType())){
			return false;
		}
		if(jpoInviteList==null ||  !"0".equals(jpoInviteList.getStatus())|| !defSysUser.getJmiMember().getRecommendNo().equals(jpoInviteList.getUserCode())){
			
		}else{
			if(null==this.getJpoInviteCodeByUserCode(defSysUser.getUserCode())){
				jpoInviteList.setStatus("1");
				jpoInviteList.setUseUserCode(defSysUser.getUserCode());
				jpoInviteList.setUseTime(new Date());
				jpoInviteListDao.save(jpoInviteList);
				jpoMemberOrder.setPvAmt(new BigDecimal("0"));
				Set<JpoMemberOrderList> jpoMemberOrderList = jpoMemberOrder.getJpoMemberOrderList();
				for (JpoMemberOrderList jpoMemberOrderList2 : jpoMemberOrderList) {
					jpoMemberOrderList2.setPv(new BigDecimal("0"));
				}
				jpoMemberOrderManager.save(jpoMemberOrder);
				return true;
			}
			
		}
		return false;
	}

	@Override
	public JpoInviteList getJpoInviteCodeByUserCode(String useUserCode) {
		return jpoInviteListDao.getJpoInviteCodeByUserCode(useUserCode);
	}
	public String getCheckJpoInviteList(String inviteCode){

    	JsysUser defSysUser=(JsysUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	JpoInviteList jpoInviteList=jpoInviteListDao.getJpoInviteCode(inviteCode);
    	if(jpoInviteList==null ||  !"0".equals(jpoInviteList.getStatus()) || !defSysUser.getJmiMember().getRecommendNo().equals(jpoInviteList.getUserCode())){
    		return "邀请不存在或者已使用";
    	}
    	return "";
	}
	
	//新增手机端支付绑定邀请码验证
	@Override
	public JpoInviteList getJpoInviteListForInviteCode(String inviteCode) {
		return jpoInviteListDao.getJpoInviteCode(inviteCode);
	}
	@Override
	public String useJpoInviteListToApp(String inviteCode, String moId, JsysUser user) {
		JpoInviteList jpoInviteList=jpoInviteListDao.getJpoInviteCode(inviteCode);
		if(StringUtil.isEmpty(moId)){
			return "订单号不能为空";
		}
		JpoMemberOrder jpoMemberOrder=jpoMemberOrderManager.get(StringUtil.formatLong(moId));
		if(jpoMemberOrder==null){
			return "订单不存在";
		}else if(!"41".equals(jpoMemberOrder.getOrderType())){
			return "订单不符合要求";
		}
		if(!user.getUserCode().equals(jpoMemberOrder.getSysUser().getUserCode())){
			return "会员与订单不符";
		}
		if(jpoMemberOrder.getSysUser().getJmiMember().getMemberLevel().intValue()!=0){
			return "会员不符合要求";
		}
		if(jpoInviteList==null ||  !"0".equals(jpoInviteList.getStatus())|| !user.getJmiMember().getRecommendNo().equals(jpoInviteList.getUserCode())){
			return "邀请不存在或者已使用";
		}else{
			if(null==this.getJpoInviteCodeByUserCode(user.getUserCode())){
				jpoInviteList.setStatus("1");
				//jpoInviteList.setUserUserCode(user.getUserCode());
				jpoInviteList.setUseUserCode(user.getUserCode());
				jpoInviteList.setUseTime(new Date());
				jpoInviteListDao.save(jpoInviteList);
				jpoMemberOrder.setPvAmt(new BigDecimal("0"));
				Set<JpoMemberOrderList> jpoMemberOrderList = jpoMemberOrder.getJpoMemberOrderList();
				for (JpoMemberOrderList jpoMemberOrderList2 : jpoMemberOrderList) {
					jpoMemberOrderList2.setPv(new BigDecimal("0"));
				}
				jpoMemberOrderManager.save(jpoMemberOrder);
				return "";
			}
		}
		return "绑定异常";
	}
	
}