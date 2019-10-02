package com.joymain.ng.service;

import java.util.List;

import javax.jws.WebService;

import com.joymain.ng.model.JpoMovie;

@WebService
public interface JpoMovieManager extends GenericManager<JpoMovie, Long>{

	/**
	 * find movie by mName
	 * @param mName
	 * @return JpoMovie list
	 */
	public List<JpoMovie> findMovieByName(String mName);
}
