package com.joymain.ng.dao;

import java.util.List;
import java.util.Map;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JpmProductWineTemplateSub;

/**
 * An interface that provides a data management interface to the JpmProductWineTemplateSub table.
 */
public interface JpmProductWineTemplateSubDao extends GenericDao<JpmProductWineTemplateSub, String> {

    public List<JpmProductWineTemplateSub> getJpmProductWineTemplateSubListByProductTemplateNo(Map<String,String> map);
    
    public JpmProductWineTemplateSub getJpmProductWineTemplateSubBySubNo(String subNo);
}