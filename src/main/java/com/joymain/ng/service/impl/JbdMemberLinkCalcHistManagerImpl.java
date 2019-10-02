package com.joymain.ng.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JbdMemberLinkCalcHistDao;
import com.joymain.ng.model.JbdMemberLinkCalcHist;
import com.joymain.ng.model.JbdPeriod;
import com.joymain.ng.service.JbdMemberLinkCalcHistManager;
import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.ListUtil;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.StringUtil;
import com.joymain.ng.util.WeekFormatUtil;

@Service("jbdMemberLinkCalcHistManager")
@WebService(serviceName = "JbdMemberLinkCalcHistService", endpointInterface = "com.joymain.ng.service.JbdMemberLinkCalcHistManager")
public class JbdMemberLinkCalcHistManagerImpl extends GenericManagerImpl<JbdMemberLinkCalcHist, Long> implements JbdMemberLinkCalcHistManager {
    JbdMemberLinkCalcHistDao jbdMemberLinkCalcHistDao;

    @Autowired
    public JbdMemberLinkCalcHistManagerImpl(JbdMemberLinkCalcHistDao jbdMemberLinkCalcHistDao) {
        super(jbdMemberLinkCalcHistDao);
        this.jbdMemberLinkCalcHistDao = jbdMemberLinkCalcHistDao;
    }

    @Autowired
    public JbdPeriodManager jbdPeriodManager;
    
	public Pager<JbdMemberLinkCalcHist> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jbdMemberLinkCalcHistDao.getPager(JbdMemberLinkCalcHist.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	public String getLastHonorStar(String userCode) {
        JbdPeriod bdPeriod=jbdPeriodManager.getLatestSendBonus();
		String bdWeek= bdPeriod.getWyear()+""+ (bdPeriod.getWweek()<10?"0" + bdPeriod.getWweek():bdPeriod.getWweek());
        Map map=this.getBdSendRecordMap(userCode, bdWeek);
        if(map==null){
        	return "";
        }
		return map.get("honor_star").toString();
	}
	
	public Map getBdSendRecordMap(String userCode, String wweek) {
		return jbdMemberLinkCalcHistDao.getBdSendRecordMap(userCode, wweek);
	}

	/**
	 * 奖金查询--初始化查询或有条件查询时的service方法 
	 */
	@Override
	public List getJbdSendRecordHistByUserCodeWeek(
			String userCode, String wweek,GroupPage page) {
		
		return jbdMemberLinkCalcHistDao.getJbdSendRecordHistByUserCodeWeek(userCode, wweek,page);
	}
	
	/**
	 * 分页查询 Add By WuCF  2013-11-25
	 * 奖金查询--初始化查询或有条件查询时的service方法
	 */
	@Override
	public List getJbdSendRecordHistByUserCodeWeekPage(
			String userCode, String wweek,int pageNum,int pageSize) {
		
		return jbdMemberLinkCalcHistDao.getJbdSendRecordHistByUserCodeWeekPage(userCode, wweek,pageNum,pageSize);
	}
	
    /**
     * 奖金查询－点击＂查＂图标出现的详细页面的service方法
     * @author Administrator
     * @create Date 2013-06-17
     */
	@Override
	public JbdMemberLinkCalcHist getBonusQueryDetail(String userCode, String wweek) {
		
		return jbdMemberLinkCalcHistDao.getBonusQueryDetail(userCode, wweek);
	}

	/**
     * 奖衔查询
     * getJbdMemberLinkCalcHistByUserCodeWeek
     *
     * @param userCode
     * @param wweek
     * @param page
     * @return
     */
    @Override
    public List getJbdMemberLinkCalcHistByUserCodeWeek(String userCode,
            String wweek, GroupPage page)
    {
        return this.jbdMemberLinkCalcHistDao.getJbdMemberLinkCalcHistByUserCodeWeek(userCode,wweek,page);
    }

    /**
     * 会员奖衔查询手机端接口
     * @param userCode
     * @param wweek
     * @return
     */
    @Override
    public List<Map<String,Object>> getJbdMemberLinkCalcHistByUserCodeWeek(
            String userCode, String wweek, String characterCode)
    {
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> jbdMemberLinkCalcHists = this.jbdMemberLinkCalcHistDao.getJbdMemberLinkCalcHistByUserCodeWeek(userCode,wweek);
        for(Map<String,Object> map : jbdMemberLinkCalcHists) {
            Map<String,Object> temp = new HashMap<String, Object>();
            temp.put("USER_CODE", map.get("USER_CODE"));
            String fWeek = WeekFormatUtil.getFormatWeek("w", map.get("W_WEEK").toString());
            temp.put("W_WEEK", fWeek);//工作周转化成财政周
            
            String honorStar = StringUtil.isObjectBlank(map.get("HONOR_STAR"))?null:map.get("HONOR_STAR").toString();
            String passStar = StringUtil.isObjectBlank(map.get("PASS_STAR"))?null:map.get("PASS_STAR").toString();
            String qualifyStar = StringUtil.isObjectBlank(map.get("QUALIFY_STAR"))?null:map.get("QUALIFY_STAR").toString();
            
            temp.put("HONOR_STAR", ListUtil.getListValue(characterCode, "honor.star.zero", honorStar));
            temp.put("PASS_STAR", ListUtil.getListValue(characterCode, "pass.star.zero", passStar));
            temp.put("QUALIFY_STAR", ListUtil.getListValue(characterCode, "qualify.star.zero", qualifyStar));
            temp.put("QUALIFY_REMARK", map.get("QUALIFY_REMARK"));
            result.add(temp);
        }
        return result;
    }

	@Override
	public Map getJBdLevel(String userCode, String wweek) {
		return jbdMemberLinkCalcHistDao.getJBdLevel(userCode, wweek);
	}

	@Override
	public List getJbdSendTypeList(String userCode, String wweek, GroupPage page) {
		return jbdMemberLinkCalcHistDao.getJbdSendTypeList(userCode, wweek, page);
	}

}