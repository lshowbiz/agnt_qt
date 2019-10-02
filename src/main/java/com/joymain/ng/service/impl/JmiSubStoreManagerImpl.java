package com.joymain.ng.service.impl;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.JmiSubStoreDao;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiRecommendRef;
import com.joymain.ng.model.JmiStore;
import com.joymain.ng.model.JmiSubStore;
import com.joymain.ng.model.JsysRole;
import com.joymain.ng.model.JsysUserRole;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiRecommendRefManager;
import com.joymain.ng.service.JmiSubStoreManager;
import com.joymain.ng.service.JsysRoleManager;
import com.joymain.ng.service.JsysUserRoleManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.LocaleUtil;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.StringUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

@Service("jmiSubStoreManager")
@WebService(serviceName = "JmiSubStoreService", endpointInterface = "com.joymain.ng.service.JmiSubStoreManager")
public class JmiSubStoreManagerImpl extends GenericManagerImpl<JmiSubStore, Long> implements JmiSubStoreManager {
    JmiSubStoreDao jmiSubStoreDao;

    @Autowired
    public JmiSubStoreManagerImpl(JmiSubStoreDao jmiSubStoreDao) {
        super(jmiSubStoreDao);
        this.jmiSubStoreDao = jmiSubStoreDao;
    }
    @Autowired
    public JmiMemberManager jmiMemberManager;
    @Autowired
    public JmiRecommendRefManager jmiRecommendRefManager;
    @Autowired
	public JsysUserRoleManager jsysUserRoleManager;
    @Autowired
    public JsysRoleManager jsysRoleManager;
	public Pager<JmiSubStore> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jmiSubStoreDao.getPager(JmiSubStore.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	public boolean getCheckJmiSubStore(BindingResult errors,JmiSubStore jmiSubStore,HttpServletRequest request){
		boolean isNotPass = false;
		
		if(!StringUtil.isEmpty(getCheckSubRecommendStore(errors, jmiSubStore.getJmiMember().getSubRecommendStore(), jmiSubStore.getJmiMember().getJmiRecommendRef().getUserCode()))){
			isNotPass = true;
		}

    	if(null==jmiSubStore.getProvince()){
			StringUtil.getErrorsFormat(errors,  "isNotNull", "province", "subStore.province");
			isNotPass = true;
    	}
    	if(null==jmiSubStore.getCity()){
    		StringUtil.getErrorsFormat(errors,  "isNotNull", "city", "subStore.city");
			isNotPass = true;
    	}
    	

    	if(!"agree1".equals(request.getParameter("agree1"))){
    		StringUtil.getErrors(errors, "miMember.notAgree", "");
			isNotPass = true;
    	}
    	if(!"agree2".equals(request.getParameter("agree2"))){
    		StringUtil.getErrors(errors, "miMember.notAgree", "");
			isNotPass = true;
    	}
    	
    	
		return isNotPass;
	}
	//jmiSubStore.getJmiMember().getSubRecommendStore()
	
	public String getCheckSubRecommendStore(BindingResult errors,String subRecommendStore,String recommendNo ){
		String errorStr="";
    	if(StringUtil.isEmpty(subRecommendStore)){
    		errorStr=StringUtil.getErrorsFormat(errors, "isNotNull", "jmiMember.subRecommendStore", "miMember.subRecommendStore");
    	}else{
    		JmiMember jmiMemberSubRecommendStore=jmiMemberManager.get(subRecommendStore);
        	if(null==jmiMemberSubRecommendStore){
    			errorStr=StringUtil.getErrors(errors, "miMember.subRecommendStore.notExist", "");
        	}else{
        		JmiRecommendRef jmiRecommendRef=jmiRecommendRefManager.get(recommendNo);
        		JmiRecommendRef defRecommendRef = jmiRecommendRefManager.get(subRecommendStore);
        		if(!StringUtil.getCheckIsDownline(defRecommendRef.getTreeIndex(), jmiRecommendRef.getTreeIndex())){
        			errorStr=StringUtil.getErrors(errors, "miMember.subRecommendStore.notExist", "");
        		}
        	}
        }
        return errorStr;
	}
	public void saveJmiSubStoreCheck(JmiSubStore jmiSubStore,JmiMember jmiMember) {

		Date curDate=new Date();
		jmiSubStore.setBusinessLicense("0");
		jmiSubStore.setStorePic("0");
		jmiSubStore.setContract("0");
		jmiSubStore.setAddrConfirm("0");
		jmiSubStore.setAddrCheck("0");
		jmiSubStore.getJmiMember().setSubStoreStatus("1");
		jmiSubStore.setConfirmStatus("2");
		jmiSubStore.setCreateTime(curDate);
		jmiSubStore.setEditTime(curDate);
		jmiSubStore.setCheckTime(curDate);
		jmiSubStore.setCheckUser(jmiMember.getUserCode());
		

		jmiSubStore.setConfirmUser(jmiMember.getUserCode());
		jmiSubStore.setConfirmTime(curDate);
		
		JsysUserRole sysUserRole= jsysUserRoleManager.getSysUserRoleByUserCode(jmiSubStore.getJmiMember().getUserCode());

		String memberRoleId =(String) Constants.sysConfigMap.get(jmiSubStore.getJmiMember().getCompanyCode()).get("jocs.member.role.store2.x");

		

		JsysRole memberSysRole=jsysRoleManager.getSysRoleByCode(memberRoleId);
		sysUserRole.setRoleId(memberSysRole.getRoleId());
		jsysUserRoleManager.save(sysUserRole);
		jmiSubStore.setConfirmStatus("1");
		
		this.save(jmiSubStore);
		
	}

	public JmiSubStore getJmiSubStoreByUserCode(String userCode) {
		return jmiSubStoreDao.getJmiSubStoreByUserCode(userCode);
	}

	public List getJmiSubStoreList(Map map) {
		return jmiSubStoreDao.getJmiSubStoreList(map);
	}

}