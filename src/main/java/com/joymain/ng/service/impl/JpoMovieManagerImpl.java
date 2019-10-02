package com.joymain.ng.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.stereotype.Service;

import com.joymain.ng.model.JpoMovie;
import com.joymain.ng.service.JpoMovieManager;

@Service("jpoMovieManager")
@WebService(serviceName = "jpoMovieManager", endpointInterface = "com.joymain.ng.service.impl.JpoMovieManager")
public class JpoMovieManagerImpl extends GenericManagerImpl<JpoMovie, Long> implements JpoMovieManager {

	@Override
	public List<JpoMovie> findMovieByName(String mName) {
		// TODO Auto-generated method stub
		return null;
	}

}
