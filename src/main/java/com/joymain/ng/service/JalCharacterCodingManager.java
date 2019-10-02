package com.joymain.ng.service;

import com.joymain.ng.service.GenericManager;
import com.joymain.ng.model.JalCharacterCoding;

import java.util.List;
import javax.jws.WebService;

@WebService
public interface JalCharacterCodingManager extends GenericManager<JalCharacterCoding, Long> {
    
}