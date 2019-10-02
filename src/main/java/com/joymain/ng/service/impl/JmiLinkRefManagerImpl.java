package com.joymain.ng.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.joymain.ng.dao.JmiLinkRefDao;
import com.joymain.ng.model.JmiLinkRef;
import com.joymain.ng.service.JmiLinkRefManager;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.StringUtil;

@Service("jmiLinkRefManager")
@WebService(serviceName = "JmiLinkRefService", endpointInterface = "com.joymain.ng.service.JmiLinkRefManager")
public class JmiLinkRefManagerImpl extends GenericManagerImpl<JmiLinkRef, String> implements JmiLinkRefManager {
    JmiLinkRefDao jmiLinkRefDao;

    @Autowired
    public JmiLinkRefManagerImpl(JmiLinkRefDao jmiLinkRefDao) {
        super(jmiLinkRefDao);
        this.jmiLinkRefDao = jmiLinkRefDao;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;
	
	public Pager<JmiLinkRef> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jmiLinkRefDao.getPager(JmiLinkRef.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	public List getLinkRefbyLinkNo(String linkNo, String orderByName, String sort) {
		return jmiLinkRefDao.getLinkRefbyLinkNo(linkNo, orderByName, sort);
	}
	
	public List getJmiLinkRefByNo(String userCode){
		return jmiLinkRefDao.getJmiLinkRefByNo(userCode);
	}
	/**
	 * 自动安置
	 * @return
	 */
	public String getLinkNo(String recommendNo){
		if(!StringUtil.isEmpty(recommendNo)){
			JmiLinkRef jmiLinkRef=jmiLinkRefDao.get(recommendNo);
			if(jmiLinkRef!=null){
				String sql="Select * From (Select a.*, Nvl(Cnt, 0), Row_Number() Over(Order By Lv, Create_Time) Rn From (Select c.User_Code, c.Link_No, d.Create_Time, Length(c.Tree_Index) Lv "
						+ " From Jmi_Link_Ref c, Jmi_Member d  Where c.Tree_Index Like  '"+jmiLinkRef.getTreeIndex()+"%' And c.User_Code = d.User_Code) a,  (Select Link_No As User_Code, Count(1) Cnt "
						+ "From Jmi_Link_Ref Where Tree_Index Like '"+jmiLinkRef.getTreeIndex()+"%' Group By Link_No) b Where a.User_Code = b.User_Code(+) And Nvl(Cnt, 0) <= 1) Where Rn = 1";
				List list=jdbcTemplate.queryForList(sql);
				if(!list.isEmpty()){
					Map map=(Map) list.get(0);
					String linkNo=(String) map.get("user_code");
					return linkNo;
				}
			}
		}
		
		return "";
	}
}