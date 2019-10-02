package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JalCharacterValue;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface JalCharacterValueManager extends GenericManager<JalCharacterValue, Long> {

	List getAlCharacterValuesByCodeSQL(String characterCode);
    
}