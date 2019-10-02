package com.joymain.ng.dao;

import java.util.List;

import com.joymain.ng.dao.GenericDao;

import com.joymain.ng.model.JmiLinkRef;

/**
 * An interface that provides a data management interface to the JmiLinkRef table.
 */
public interface JmiLinkRefDao extends GenericDao<JmiLinkRef, String> {

    public List getLinkRefbyLinkNo(String linkNo,String orderByName,String sort);
	/**
	 * 根据外部参数获取下一个接点对象
	 * 
	 * @param miLinkRef
	 * @return
	 */
	public JmiLinkRef getNewMiLinkRefManagersByLinkNo(JmiLinkRef miLinkRef, int maxLink);

	public List getJmiLinkRefByNo(String userCode);
}