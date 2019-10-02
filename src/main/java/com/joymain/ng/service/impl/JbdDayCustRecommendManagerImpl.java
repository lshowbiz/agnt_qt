package com.joymain.ng.service.impl;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JbdDayCustRecommendDao;
import com.joymain.ng.model.JbdDayCustRecommend;
import com.joymain.ng.model.JbdDayCustRecommendOrder;
import com.joymain.ng.service.JbdDayCustRecommendManager;
import com.joymain.ng.service.JbdPeriodManager;
import com.joymain.ng.util.GroupPage;

@Service("jbdDayCustRecommendManager")
@WebService(serviceName = "JbdDayCustRecommendService", endpointInterface = "com.joymain.ng.service.JbdDayCustRecommendManager")
public class JbdDayCustRecommendManagerImpl extends GenericManagerImpl<JbdDayCustRecommend, Long> implements JbdDayCustRecommendManager {
	   JbdDayCustRecommendDao jbdDayCustRecommendDao;

	    @Autowired
	    public JbdDayCustRecommendManagerImpl(JbdDayCustRecommendDao jbdDayCustRecommendDao) {
	        super(jbdDayCustRecommendDao);
	        this.jbdDayCustRecommendDao = jbdDayCustRecommendDao;
	    }

	    @Autowired
	    public JbdPeriodManager jbdPeriodManager;
	    
		
		/**
		 * 分页查询 Add By WuCF  2013-11-25
		 * 奖金查询--初始化查询或有条件查询时的service方法
		 */
		
		public List getJbdDayCustRecommendByUserCodeWeekPage(Map<String, String> params, GroupPage page){
			return jbdDayCustRecommendDao.getJbdDayCustRecommendByUserCodeWeekPage(params, page);
		}
		
		
	    /**
	     * 奖金查询－点击＂查＂图标出现的详细页面的service方法
	     * @author Administrator
	     * @create Date 2013-06-17
	     */
		@Override
		public List getJbdDayCustRecommendDetail(String userCode, String wweek,GroupPage page) {
			
			return jbdDayCustRecommendDao.getJbdDayCustRecommendDetail(userCode, wweek,page);
		}
		@Override
		public List getJbdDayCustRecommendDetail2(String userCode, String wweek,GroupPage page) {
			
			return jbdDayCustRecommendDao.getJbdDayCustRecommendDetail2(userCode, wweek,page);
		}

/*		*//**
	     * 奖衔查询
	     * getJbdDayCustRecommendByUserCodeWeek
	     *
	     * @param userCode
	     * @param wweek
	     * @param page
	     * @return
	     *//*
	    @Override
	    public List getJbdDayCustRecommendByUserCodeWeek(String userCode,
	            String wweek, GroupPage page)
	    {
	        return this.jbdDayCustRecommendDao.getJbdDayCustRecommendByUserCodeWeek(userCode,wweek,page);
	    }

	    *//**
	     * 会员奖衔查询手机端接口
	     * @param userCode
	     * @param wweek
	     * @return
	     *//*
	    @Override
	    public List<Map<String,Object>> getJbdDayCustRecommendByUserCodeWeek(
	            String userCode, String wweek, String characterCode)
	    {
	        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
	        List<Map<String,Object>> jbdDayCustRecommends = this.jbdDayCustRecommendDao.getJbdDayCustRecommendByUserCodeWeek(userCode,wweek);
	        for(Map<String,Object> map : jbdDayCustRecommends) {
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