package com.fgw.project;

import java.util.Date;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;

public class Test {
	
	
	public static void main(String[] args) {
		String dateStr1 = "2021-01-01 22:33:23";
		Date date1 = DateUtil.parse(dateStr1);
		String dateStr2 = "2021-01-04 23:33:23";
		Date date2 = DateUtil.parse(dateStr2);
		boolean s = date2.before(date1);
		//相差一个月，31天
		long betweenDay = DateUtil.between(date2,date1 , DateUnit.DAY);
		System.out.println(s);
	}
}
