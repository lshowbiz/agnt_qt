package com.joymain.ng.dao;


import java.util.List;

import com.joymain.ng.model.JpoMovie;

public interface JpoMovieDao extends GenericDao<JpoMovie,Long>{
	/**
	 * find movie by status
	 * @param satus
	 * @return
	 */
	public List<JpoMovie> findMovieByName(char status);
	/**
	 * get movie info by orderNo
	 * @param orderNo
	 * @return jpoMovie
	 */
	public JpoMovie getMovieByOrderNo(String orderNo);
}
