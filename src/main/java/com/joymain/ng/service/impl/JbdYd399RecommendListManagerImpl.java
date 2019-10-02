package com.joymain.ng.service.impl;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JbdYd399RecommendListDao;
import com.joymain.ng.model.VJbdYd399RecommendList;
import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.service.JbdYd399RecommendListManager;
import com.joymain.ng.util.GroupPage;

@Service("jbdYd399RecommendListManager")
@WebService(serviceName = "JbdYd399RecommendListService", endpointInterface = "com.joymain.ng.service.JbdYd399RecommendListManager")
public class JbdYd399RecommendListManagerImpl extends GenericManagerImpl<VJbdYd399RecommendList, Long> implements JbdYd399RecommendListManager {
	   JbdYd399RecommendListDao jbdYd399RecommendListDao;
	    @Autowired
	    public JbdYd399RecommendListManagerImpl(JbdYd399RecommendListDao jbdYd399RecommendListDao) {
	        super(jbdYd399RecommendListDao);
	        this.jbdYd399RecommendListDao = jbdYd399RecommendListDao;
	    }
	    @Autowired
	    public JbdPeriodManager jbdPeriodManager;
	    
		
		/**
		 * 分页查询 Add By WuCF  2013-11-25
		 * 奖金查询--初始化查询或有条件查询时的service方法
		 */
		
		public List getJbdYd399RecommendListByUserCodeWeekPage(Map<String, String> params, GroupPage page){
			return jbdYd399RecommendListDao.getJbdYd399RecommendListByUserCodeWeekPage(params, page);
		}
		
		
/*		*//**
	     * 奖衔查询
	     * getJbdYd399RecommendListByUserCodeWeek
	     *
	     * @param userCode
	     * @param wweek
	     * @param page
	     * @return
	     *//*
	    @Override
	    public List getJbdYd399RecommendListByUserCodeWeek(String userCode,
	            String wweek, GroupPage page)
	    {
	        return this.jbdYd399RecommendListDao.getJbdYd399RecommendListByUserCodeWeek(userCode,wweek,page);
	    }

	    *//**
	     * 会员奖衔查询手机端接口
	     * @param userCode
	     * @param wweek
	     * @return
	     *//*
	    @Override
	    public List<Map<String,Object>> getJbdYd399RecommendListByUserCodeWeek(
	            String userCode, String wweek, String characterCode)
	    {
	        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
	        List<Map<String,Object>> jbdYd399RecommendLists = this.jbdYd399RecommendListDao.getJbdYd399RecommendListByUserCodeWeek(userCode,wweek);
	        for(Map<String,Object> map : jbdYd399RecommendLists) {
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
	    }*/

}