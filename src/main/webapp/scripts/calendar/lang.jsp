<%@ page language="java" errorPage="/error.jsp" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
// ** I18N

// Calendar ZH language
// Author: muziq, <muziq@sina.com>
// Encoding: GB2312 or GBK
// Distributed under the same terms as the calendar itself.

// full day names
Calendar._DN = new Array
("<ng:locale key="Calendar.DN.Sunday"/>",
 "<ng:locale key="Calendar.DN.Monday"/>",
 "<ng:locale key="Calendar.DN.Tuesday"/>",
 "<ng:locale key="Calendar.DN.Wednesday"/>",
 "<ng:locale key="Calendar.DN.Thursday"/>",
 "<ng:locale key="Calendar.DN.Friday"/>",
 "<ng:locale key="Calendar.DN.Saturday"/>",
 "<ng:locale key="Calendar.DN.Sunday"/>");

// Please note that the following array of short day names (and the same goes
// for short month names, _SMN) isn't absolutely necessary.  We give it here
// for exemplification on how one can customize the short day names, but if
// they are simply the first N letters of the full name you can simply say:
//
//   Calendar._SDN_len = N; // short day name length
//   Calendar._SMN_len = N; // short month name length
//
// If N = 3 then this is not needed either since we assume a value of 3 if not
// present, to be compatible with translation files that were written before
// this feature.

// short day names
Calendar._SDN = new Array
("<ng:locale key="Calendar.SDN.Sun"/>",
 "<ng:locale key="Calendar.SDN.Mon"/>",
 "<ng:locale key="Calendar.SDN.Tue"/>",
 "<ng:locale key="Calendar.SDN.Wed"/>",
 "<ng:locale key="Calendar.SDN.Thu"/>",
 "<ng:locale key="Calendar.SDN.Fri"/>",
 "<ng:locale key="Calendar.SDN.Sat"/>",
 "<ng:locale key="Calendar.SDN.Sun"/>");

// First day of the week. "0" means display Sunday first, "1" means display
// Monday first, etc.
Calendar._FD = 0;

// full month names
Calendar._MN = new Array
("<ng:locale key="Calendar.MN.January"/>",
 "<ng:locale key="Calendar.MN.February"/>",
 "<ng:locale key="Calendar.MN.March"/>",
 "<ng:locale key="Calendar.MN.April"/>",
 "<ng:locale key="Calendar.MN.May"/>",
 "<ng:locale key="Calendar.MN.June"/>",
 "<ng:locale key="Calendar.MN.July"/>",
 "<ng:locale key="Calendar.MN.August"/>",
 "<ng:locale key="Calendar.MN.September"/>",
 "<ng:locale key="Calendar.MN.October"/>",
 "<ng:locale key="Calendar.MN.November"/>",
 "<ng:locale key="Calendar.MN.December"/>");

// short month names
Calendar._SMN = new Array
("<ng:locale key="Calendar.SMN.Jan"/>",
 "<ng:locale key="Calendar.SMN.Feb"/>",
 "<ng:locale key="Calendar.SMN.Mar"/>",
 "<ng:locale key="Calendar.SMN.Apr"/>",
 "<ng:locale key="Calendar.SMN.May"/>",
 "<ng:locale key="Calendar.SMN.Jun"/>",
 "<ng:locale key="Calendar.SMN.Jul"/>",
 "<ng:locale key="Calendar.SMN.Aug"/>",
 "<ng:locale key="Calendar.SMN.Sep"/>",
 "<ng:locale key="Calendar.SMN.Oct"/>",
 "<ng:locale key="Calendar.SMN.Nov"/>",
 "<ng:locale key="Calendar.SMN.Dec"/>");

// tooltips
Calendar._TT = {};
Calendar._TT["INFO"] = "<ng:locale key="Calendar.TT.INFO"/>";

Calendar._TT["ABOUT"] = "<ng:locale key="Calendar.TT.ABOUT"/>";
Calendar._TT["ABOUT_TIME"] = "<ng:locale key="Calendar.TT.ABOUT_TIME"/>";

Calendar._TT["PREV_YEAR"] = "<ng:locale key="Calendar.TT.PREV_YEAR"/>";
Calendar._TT["PREV_MONTH"] = "<ng:locale key="Calendar.TT.PREV_MONTH"/>";
Calendar._TT["GO_TODAY"] = "<ng:locale key="Calendar.TT.GO_TODAY"/>";
Calendar._TT["NEXT_MONTH"] = "<ng:locale key="Calendar.TT.NEXT_MONTH"/>";
Calendar._TT["NEXT_YEAR"] = "<ng:locale key="Calendar.TT.NEXT_YEAR"/>";
Calendar._TT["SEL_DATE"] = "<ng:locale key="Calendar.TT.SEL_DATE"/>";
Calendar._TT["DRAG_TO_MOVE"] = "<ng:locale key="Calendar.TT.DRAG_TO_MOVE"/>";
Calendar._TT["PART_TODAY"] = "<ng:locale key="Calendar.TT.PART_TODAY"/>";

// the following is to inform that "%s" is to be the first day of week
// %s will be replaced with the day name.
Calendar._TT["DAY_FIRST"] = "<ng:locale key="Calendar.TT.DAY_FIRST"/>";

// This may be locale-dependent.  It specifies the week-end days, as an array
// of comma-separated numbers.  The numbers are from 0 to 6: 0 means Sunday, 1
// means Monday, etc.
Calendar._TT["WEEKEND"] = "0,6";

Calendar._TT["CLOSE"] = "<ng:locale key="Calendar.TT.CLOSE"/>";
Calendar._TT["TODAY"] = "<ng:locale key="Calendar.TT.TODAY"/>";
Calendar._TT["TIME_PART"] = "<ng:locale key="Calendar.TT.TIME_PART"/>";

// date formats
Calendar._TT["DEF_DATE_FORMAT"] = "%Y-%m-%d";
Calendar._TT["TT_DATE_FORMAT"] = "<ng:locale key="Calendar.TT.TT_DATE_FORMAT"/>";

Calendar._TT["WK"] = "<ng:locale key="Calendar.TT.WK"/>";
Calendar._TT["TIME"] = "<ng:locale key="Calendar.TT.TIME"/>:";
