package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JpmWineTemplatePicture;

/**
 * An interface that provides a data management interface to the JpmWineTemplatePicture table.
 */
public interface JpmWineTemplatePictureDao extends GenericDao<JpmWineTemplatePicture, Long> {

    /**
     * 根据配件id查询图片列表
     * @param subNo
     * @return
     */
    public List<JpmWineTemplatePicture> getJpmWineTemplatePictureListBySubNo(String subNo);
}