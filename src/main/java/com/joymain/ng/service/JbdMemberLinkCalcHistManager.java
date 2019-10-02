package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JbdMemberLinkCalcHist;
import com.joymain.ng.util.GroupPage;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;

import javax.jws.WebService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
@WebService
public interface JbdMemberLinkCalcHistManager extends GenericManager<JbdMemberLinkCalcHist, Long> {
    
	public Pager<JbdMemberLinkCalcHist> getPager(Collection<SearchBean> searchBeans, Collection<OrderBy> OrderBys,
			int currentPage, int pageSize);
	public String getLastHonorStar(String userCode);
	public List getJbdSendRecordHistByUserCodeWeek(
			String userCode, String wweek,GroupPage page);
	
	/**
	 * 分页展示
	 * @param userCode
	 * @param wweek
	 * @return
	 */
	public List getJbdSendRecordHistByUserCodeWeekPage(
			String userCode, String wweek,int pageNum,int pageSize);
	
	
	public JbdMemberLinkCalcHist getBonusQueryDetail(String userCode, String wweek);
	public Map getBdSendRecordMap(String userCode, String wweek) ;
	
	/**
	 * 奖衔查询
	 * getJbdMemberLinkCalcHistByUserCodeWeek
	 *
	 * @param userCode
	 * @param wweek
	 * @param page
	 * @return
	 */
    public List getJbdMemberLinkCalcHistByUserCodeWeek(String userCode,
            String wweek, GroupPage page);
    
    /**
     * 会员奖衔查询手机端接口
     * getJbdMemberLinkCalcHistByUserCodeWeek
     *
     * @param userCode
     * @param wweek
     * @return
     */
    public List getJbdMemberLinkCalcHistByUserCodeWeek(
            String userCode, String wweek, String characterCode);

	public Map getJBdLevel(String userCode ,String wweek);
	public List getJbdSendTypeList(String userCode, String wweek,GroupPage page);
	
}