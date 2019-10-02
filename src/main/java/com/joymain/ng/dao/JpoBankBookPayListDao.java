package com.joymain.ng.dao;

import com.joymain.ng.model.JpoBankBookPayList;
import com.joymain.ng.dao.GenericDao;

public interface JpoBankBookPayListDao extends GenericDao<JpoBankBookPayList,Long>{
	public void savePayList(JpoBankBookPayList paylist);
}
