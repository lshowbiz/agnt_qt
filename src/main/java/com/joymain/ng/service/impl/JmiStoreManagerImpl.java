package com.joymain.ng.service.impl;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.JmiStoreDao;
import com.joymain.ng.model.JmiAddrBook;
import com.joymain.ng.model.JmiMember;
import com.joymain.ng.model.JmiRecommendRef;
import com.joymain.ng.model.JmiStore;
import com.joymain.ng.model.JsysRole;
import com.joymain.ng.model.JsysUserRole;
import com.joymain.ng.service.JmiMemberManager;
import com.joymain.ng.service.JmiRecommendRefManager;
import com.joymain.ng.service.JmiStoreManager;
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
import java.util.List;
import java.util.Map;

import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;

@Service("jmiStoreManager")
@WebService(serviceName = "JmiStoreService", endpointInterface = "com.joymain.ng.service.JmiStoreManager")
public class JmiStoreManagerImpl extends GenericManagerImpl<JmiStore, Long> implements JmiStoreManager {
	
    JmiStoreDao jmiStoreDao;

    @Autowired
    public JmiStoreManagerImpl(JmiStoreDao jmiStoreDao) {
        super(jmiStoreDao);
        this.jmiStoreDao = jmiStoreDao;
    }
    @Autowired
    public JmiMemberManager jmiMemberManager;
    @Autowired
    public JmiRecommendRefManager jmiRecommendRefManager;
    

    @Autowired
    private JsysUserRoleManager jsysUserRoleManager;
    
    

    @Autowired
    private JsysRoleManager jsysRoleManager;
    
	public Pager<JmiStore> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jmiStoreDao.getPager(JmiStore.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	public JmiStore getJmiStoreByUserCode(String userCode) {
		return jmiStoreDao.getJmiStoreByUserCode(userCode);
	}

	public boolean getCheckJmiStore(JmiStore jmiStore,BindingResult errors,HttpServletRequest request){
    	boolean isNotPass=false;

/*    	if(StringUtil.isEmpty(jmiStore.getSubStoreAddr())){
			StringUtil.getErrorsFormat(errors, "isNotNull", "subStoreAddr", LocaleUtil.getLocalText("store.subStoreAddr"));
			isNotPass = true;
    	}
*/
    	if(StringUtil.isEmpty(jmiStore.getMobiletele())){
			StringUtil.getErrorsFormat(errors, "isNotNull", "mobiletele", LocaleUtil.getLocalText("subStore.mobiletele"));
			isNotPass = true;
    	}
    	

    	
    	if(StringUtil.isEmpty(jmiStore.getAddress())){
			StringUtil.getErrorsFormat(errors, "isNotNull", "address", LocaleUtil.getLocalText("subStore.address"));
			isNotPass = true;
    	}
    	if(null==jmiStore.getHonorStar()){
			errors.rejectValue("", "isNotNull",new Object[] {"" }, "");
			StringUtil.getErrorsFormat(errors, "isNotNull", "honorStar", "奖街");
			isNotPass = true;
    	}

    	return isNotPass;
    }
	public List getJmiStoreList(Map map) {
		return jmiStoreDao.getJmiStoreList(map);
	}
	@Override
	public void saveJmiStoreApply(JmiStore jmiStore) {
		
		
		
		JsysUserRole sysUserRole= jsysUserRoleManager.getSysUserRoleByUserCode(jmiStore.getJmiMember().getUserCode());

		String roleId="jocs.member.role.store1.x";
		if("2".equals(jmiStore.getJmiMember().getIsstore())){
			roleId="jocs.member.role.store21.x";
		}
		
		
		
		String memberRoleId =(String) Constants.sysConfigMap.get(jmiStore.getJmiMember().getCompanyCode()).get(roleId);

		JsysRole memberSysRole=jsysRoleManager.getSysRoleByCode(memberRoleId);
		sysUserRole.setRoleId(memberSysRole.getRoleId());
		jsysUserRoleManager.save(sysUserRole);
		this.save(jmiStore);
	}
}