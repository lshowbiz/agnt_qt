package com.joymain.ng.service.impl;

import com.joymain.ng.Constants;
import com.joymain.ng.dao.JmiYdSendListDao;
import com.joymain.ng.model.JmiYdSendList;
import com.joymain.ng.model.JpmSmssendInfo;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JmiYdSendListManager;
import com.joymain.ng.service.JpmProductSaleNewManager;
import com.joymain.ng.util.ListUtil;
import com.joymain.ng.util.OrderBy;
import com.joymain.ng.util.Pager;
import com.joymain.ng.util.SearchBean;
import com.joymain.ng.util.SmsSend;
import com.joymain.ng.util.StringUtil;
import java.util.Collection;
import java.util.Date;
import javax.jws.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("jmiYdSendListManager")
@WebService(serviceName = "JmiYdSendListService", endpointInterface = "com.joymain.ng.service.JmiYdSendListManager")
public class JmiYdSendListManagerImpl extends GenericManagerImpl<JmiYdSendList, Long> implements JmiYdSendListManager {
    JmiYdSendListDao jmiYdSendListDao;

    @Autowired
    public JmiYdSendListManagerImpl(JmiYdSendListDao jmiYdSendListDao) {
        super(jmiYdSendListDao);
        this.jmiYdSendListDao = jmiYdSendListDao;
    }
}