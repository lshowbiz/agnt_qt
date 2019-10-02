package com.joymain.ng.dao;

import java.util.List;
import java.util.Map;

import com.joymain.ng.dao.GenericDao;
import com.joymain.ng.model.JbdMemberLinkCalcHist;
import com.joymain.ng.util.GroupPage;

/**
 * An interface that provides a data management interface to the JbdMemberLinkCalcHist table.
 */
public interface JbdMemberLinkCalcHistDao extends GenericDao<JbdMemberLinkCalcHist, Long> {

	/**
	 * 获取某会员在某一期的奖金数据
	 * @param memberNo
	 * @param wweek
	 * @return
	 */
	public Map getBdSendRecordMap(final String userCode, final String wweek);

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
     * 
     * getJbdMemberLinkCalcHistByUserCodeWeek
     *
     * @param userCode
     * @param wweek
     * @return
     */
    public List<Map<String,Object>> getJbdMemberLinkCalcHistByUserCodeWeek(
            String userCode, String wweek);

	public Map getJBdLevel(String userCode ,String wweek);
	public List getJbdSendTypeList(String userCode, String wweek,GroupPage page);
}