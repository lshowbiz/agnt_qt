package com.joymain.ng.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.AmAnnounceDao;
import com.joymain.ng.model.AmAnnounce;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.AmAnnounceManager;
import com.joymain.ng.util.GroupPage;

@Service("amAnnounceManager")
@WebService(serviceName = "amAnnounceService", endpointInterface = "com.joymain.ng.service.AmAnnounceManager")
public class AmAnnounceManagerImpl extends GenericManagerImpl<AmAnnounce, String> implements AmAnnounceManager{

	AmAnnounceDao amAnnounceDao;
	
	@Autowired
    public AmAnnounceManagerImpl(AmAnnounceDao amAnnounceDao) {
        super(amAnnounceDao);
        this.amAnnounceDao = amAnnounceDao;
    }
	
	@Override
	public List<AmAnnounce> findAllAnnounce() {
		return amAnnounceDao.findAllAnnounce();
	}
	
	@Override
	public List<AmAnnounce> findAllAnnounce(String userCode) {
		return amAnnounceDao.findAllAnnounce(userCode);
	}

	@Override
	public List<AmAnnounce> findAnnounceByRowNum(int rownum) {
		return amAnnounceDao.findAnnounceByRowNum(rownum);
	}

	@Override
	public AmAnnounce getAnnounceById(String aaNo) {
		return amAnnounceDao.get(aaNo);
	}

	@Override
	public List<AmAnnounce> findAnnounceByColum(Map<String, String> columMap) {
		
		return amAnnounceDao.findAnnounceByColumn(columMap);
	}


	@Override
	public AmAnnounce findAnnounceByaaNo(String aaNo) {
		// TODO Auto-generated method stub
		return amAnnounceDao.findAnnounceByaaNo(aaNo);
	}

	public int countNotReadAnnounce(Map map) {
		return amAnnounceDao.countNotReadAnnounce(map);
	}

	public Map getSearchAnnounceMap(JsysUser defSysUser) {
		String memberPermitClass="";
    	List list=amAnnounceDao.getTeamLeader(defSysUser.getUserCode());
    	if(!list.isEmpty()&&((Map)list.get(0)).get("team_no")!=null){
    		String teamNo=((Map)list.get(0)).get("team_no").toString();
    		memberPermitClass+="','"+teamNo;
    	}else{
    		//memberPermitClass="3"+defSysUser.getJmiMember().getCardType();
    		memberPermitClass="89";
        	//crm.setValue("permitClass", memberPermitClass);
        	//一级生活馆
        	if("1".equals(defSysUser.getJmiMember().getIsstore())){
        		memberPermitClass+="','21";
    			//crm.setValue("permitClass", memberPermitClass+"','21");
        	}
        	
        	//二级生活馆
        	if("2".equals(defSysUser.getJmiMember().getIsstore())){
        		memberPermitClass+="','22";
    			//crm.setValue("permitClass", memberPermitClass+"','22");
        	}
        	memberPermitClass+="','7"+defSysUser.getJmiMember().getMemberLevel();
    	}
    	Map map=new HashMap();

		map.put("companyCode", defSysUser.getCompanyCode());
		map.put("permitClass", memberPermitClass);
		map.put("status", "1");
		map.put("viewFlag", "view");
		map.put("browserNo", defSysUser.getUserCode());
    	 
		
		return map;
	}

	public int findAnnounceCount(Map map) {
		return amAnnounceDao.findAnnounceCount(map);
	}
	
	public List findAnnouncePage(GroupPage page, Map map) {
		return amAnnounceDao.findAnnouncePage(page, map);
	}


}
