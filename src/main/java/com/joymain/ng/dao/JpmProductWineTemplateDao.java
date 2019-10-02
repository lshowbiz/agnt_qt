package com.joymain.ng.dao;

import java.util.List;
import java.util.Map;

import com.joymain.ng.model.JpmProductWineTemplate;

/**
 * 
 * <p>Title: (Initialize)</p>
 * <p>Description: (Initialize)</p>
 * @author  jfoy
 * @version  [ 2013-12-2]
 * @since
 */
public interface JpmProductWineTemplateDao extends GenericDao<JpmProductWineTemplate, Long> {

    /**
     * 查询出所有可用模版
     * @param map
     * @return
     */
    public List<JpmProductWineTemplate> getTemplateList();
    
    /**
     * 根据商品编码获取对应的模版信息
     * @param map
     * @return
     */
    public List<JpmProductWineTemplate> getTemplateListByProductNo(String productNo);
    
    public JpmProductWineTemplate getJpmProductWineTemplate(Long productTemplateNo);
}