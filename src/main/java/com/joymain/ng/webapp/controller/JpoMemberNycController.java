package com.joymain.ng.webapp.controller;

import com.joymain.ng.model.JpoMemberNyc;
import com.joymain.ng.model.JsysUser;
import com.joymain.ng.service.JsysUserManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangjingl02 on 2016/9/5.
 */
@Controller
@RequestMapping("/api/")
public class JpoMemberNycController {
    private final SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    private JsysUserManager jsysUserManager;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/getMemberNycQualify")
    @ResponseBody
    public Object getMemberNycQualify(HttpServletRequest request){

        String userId = request.getParameter("userId");
        String token = request.getParameter("token");

        final JsysUser jsysUser = jsysUserManager.getUserByToken(userId, token);
        Object object = jsysUserManager.getAuthErrorCode(jsysUser, "List");
        if(null!=object){
            return (List)object;
        }
        Date now=new Date();
        Calendar now30 = Calendar.getInstance();
        now30.setTime(now);
        now30.add(Calendar.DAY_OF_MONTH,30);
//        String sql="SELECT * FROM JPO_MEMBER_NYC WHERE " +
//                " to_timestamp('"+sdf.format(now)+"'||'00:00:00.000000','yyyy-mm-dd hh24:mi:ss.ff') " +
//                " between PUSH_AT and to_timestamp('" + sdf.format(now30.getTime()) + "'||'23:59:59.000000','yyyy-mm-dd hh24:mi:ss.ff') " +
//                " AND status='0' AND MEMBER_NO=?";
        String sql="SELECT * FROM JPO_MEMBER_NYC WHERE  MEMBER_NO=?";

        List<JpoMemberNyc> nycList = jdbcTemplate.query(sql, new RowMapper<JpoMemberNyc>() {
            @Override
            public JpoMemberNyc mapRow(ResultSet rs, int i) throws SQLException {
                JpoMemberNyc n = new JpoMemberNyc();
                n.setId(rs.getLong("ID"));
                n.setUserCode(jsysUser.getUserCode());
                n.setPushAt(rs.getDate("PUSH_AT"));
                n.setYearOfMonth(rs.getString("YEAR_OF_MONTH"));
                n.setStatus(rs.getString("STATUS"));
                return n;
            }
        }, jsysUser.getUserCode());

        return nycList;

    }

}
