package com.joymain.ng.util.bill99;
//MYECLIPSE GT 5.5
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.joymain.ng.util.DateUtil;


public class Test{

public static void main(String[] args){
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

	try {
		System.out.println(DateUtil.daysBetweenDates(new Date(),simpleDateFormat.parse("2011-04-11 17:24:45")));
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
