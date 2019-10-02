package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JsysIdDao;
import com.joymain.ng.model.JsysId;
import com.joymain.ng.service.JsysIdManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

@Service("jsysIdManager")
@WebService(serviceName = "JsysIdService", endpointInterface = "com.joymain.ng.service.JsysIdManager")
public class JsysIdManagerImpl extends GenericManagerImpl<JsysId, Long> implements JsysIdManager {
    JsysIdDao jsysIdDao;

    @Autowired
    public JsysIdManagerImpl(JsysIdDao jsysIdDao) {
        super(jsysIdDao);
        this.jsysIdDao = jsysIdDao;
    }
	
	public Pager<JsysId> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jsysIdDao.getPager(JsysId.class, searchBeans, OrderBys, currentPage, pageSize);
	}
	
	/**
	 * 取得一个对应表的唯一主键
	 * @param table 指定的数据表
	 * @param idName 指定对应表的那个字段
	 * @return String =""表示未取得唯一主键
	 * @throws Exception 
	 */

	public String buildIdStr(final String idCode){
//		if (idCode == null) {
//			throw new Exception("idCode is empty!");
//		}
		Map resultMap=jsysIdDao.callProcBuildIdStr(idCode);
//		if(resultMap==null || resultMap.get("p_out_code")==null){
//			log.info("过程调用失败");
//			throw new Exception("过程调用失败!");
//		}
		
		return resultMap.get("p_out_code").toString();
	}

}