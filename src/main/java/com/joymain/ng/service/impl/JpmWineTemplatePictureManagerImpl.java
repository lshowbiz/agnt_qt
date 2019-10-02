package com.joymain.ng.service.impl;

import com.joymain.ng.dao.JpmWineTemplatePictureDao;
import com.joymain.ng.model.JpmWineTemplatePicture;
import com.joymain.ng.service.JpmWineTemplatePictureManager;
import com.joymain.ng.service.impl.GenericManagerImpl;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.List;
import javax.jws.WebService;

@Service("jpmWineTemplatePictureManager")
@WebService(serviceName = "JpmWineTemplatePictureService", endpointInterface = "com.joymain.ng.service.JpmWineTemplatePictureManager")
public class JpmWineTemplatePictureManagerImpl extends GenericManagerImpl<JpmWineTemplatePicture, Long> implements JpmWineTemplatePictureManager {
    JpmWineTemplatePictureDao jpmWineTemplatePictureDao;

    @Autowired
    public JpmWineTemplatePictureManagerImpl(JpmWineTemplatePictureDao jpmWineTemplatePictureDao) {
        super(jpmWineTemplatePictureDao);
        this.jpmWineTemplatePictureDao = jpmWineTemplatePictureDao;
    }
	
	public Pager<JpmWineTemplatePicture> getPager(Collection<SearchBean> searchBeans,
			Collection<OrderBy> OrderBys, int currentPage, int pageSize) {
		// TODO Auto-generated method stub
		return jpmWineTemplatePictureDao.getPager(JpmWineTemplatePicture.class, searchBeans, OrderBys, currentPage, pageSize);
	}

	/**
     * 根据配件id查询图片列表
     * @param subNo
     * @return
     */
    @Override
    public List<JpmWineTemplatePicture> getJpmWineTemplatePictureListBySubNo(String subNo)
    {
        return jpmWineTemplatePictureDao.getJpmWineTemplatePictureListBySubNo(subNo);
    }
}